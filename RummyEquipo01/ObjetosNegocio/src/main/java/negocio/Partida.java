package negocio;

import comandos.directorio.ComandoAgregarDireccionJugador;
import comandos.respuesta.ComandoCargarJugadores;
import comandos.respuesta.ComandoDecisionIniciarJuego;
import comandos.respuesta.ComandoNuevaSolicitudIniciarJuego;
import comandos.respuesta.ComandoActualizarJugadoresInicioJuego;
import comandos.respuesta.ComandoPartidaConfigurada;
import comandos.respuesta.ComandoRegistroFallido;
import comandos.respuesta.ComandoRespuestaConfirmacionUnirsePartida;
import comandos.respuesta.ComandoRespuestaIniciarJuego;
import comandos.respuesta.ComandoRespuestaUnirsePartida;
import comandos.solicitud.ComandoAbandonar;
import comandos.solicitud.ComandoAgregarFichasJugador;
import comandos.solicitud.ComandoAgregarFichasTablero;
import comandos.solicitud.ComandoAgregarFichasTableroGrupo;
import comandos.solicitud.ComandoConfigurarPartida;
import comandos.solicitud.ComandoConfirmacionAbandonar;
import comandos.solicitud.ComandoConfirmacionEnvioIniciarJuego;
import comandos.solicitud.ComandoConfirmacionIniciarJuego;
import comandos.solicitud.ComandoConfirmacionSolicitarFin;
import comandos.solicitud.ComandoConfirmacionUnirsePartida;
import comandos.solicitud.ComandoIniciarJuego;
import comandos.solicitud.ComandoQuitarFichasJugador;
import comandos.solicitud.ComandoQuitarFichasTablero;
import comandos.solicitud.ComandoReestablecerTablero;
import comandos.solicitud.ComandoRegistrarJugador;
import comandos.solicitud.ComandoSeleccionarFichasTablero;
import comandos.solicitud.ComandoSolicitarFin;
import comandos.solicitud.ComandoTerminarTurno;
import comandos.solicitud.ComandoTomarFicha;
import comandos.solicitud.ComandoUnirsePartida;
import dtos.JugadorInicioPartidaDTO;
import enumeradores.TipoComando;
import excepciones.RummyException;
import java.util.LinkedList;
import java.util.List;
import interfaces.IComando;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class Partida {

    private List<Jugador> jugadores = new LinkedList<>();
    private List<Jugador> jugadoresInicioJuego = new LinkedList<>();
    
    private ConcurrentHashMap<String, VotacionUnirsePartida> votacionesUnirsePartida = new ConcurrentHashMap<>();

    private FachadaObjetosNegocio fachada;

    private Tablero tablero;
    
    private final String MENSAJE_JUGADOR_DESEA_UNIRSE = " desea unirse a la partida. ¿Desea aceptarlo?";
    private final String MENSAJE_ACEPTACION_UNIRSE = "¡Lo han aceptado!";
    private final String MENSAJE_RECHAZO_UNIRSE = "Ha sido rechazado";

    private final String MENSAJE_CONFIRMACION_INICIO_JUEGO = "¿Desea solicitar el inicio de la partida?";
    private final String MENSAJE_NUEVA_SOLICITUD_INICIO_JUEGO = " quiere iniciar la partida. ¿Desea inicar?";
    private final String MENSAJE_INICIO_JUEGO = "¡La partida comenzará!";
    private final String MENSAJE_RECHAZO_INICIO_JUEGO = "No se ha aceptado el inicio de la partida";
    private final String MENSAJE_PARTIDA_CONFIGURADA = "¡Partida configurada exitosamente!";

    public void setFachada(FachadaObjetosNegocio fachada) {
        this.fachada = fachada;
    }

    public void ejecutar(IComando comando) throws RummyException {

        TipoComando tipoComando = TipoComando.fromNombre(comando.getTipo());

        switch (tipoComando) {

            case TipoComando.COMANDO_INICIAR_JUEGO:

                ComandoIniciarJuego comandoIniciarJuego = (ComandoIniciarJuego) comando;

                solicitarInicioJuego(comandoIniciarJuego.getNombreJugador());

                break;

            case TipoComando.COMANDO_CONFIRMACION_ENVIO_INICIAR_JUEGO:

                ComandoConfirmacionEnvioIniciarJuego comandoConfirmacionEnvioIniciarJuego = (ComandoConfirmacionEnvioIniciarJuego) comando;

                enviarSolicitudInicioJuego(
                        comandoConfirmacionEnvioIniciarJuego.getNombreJugador(),
                        comandoConfirmacionEnvioIniciarJuego.isConfirmacion());

                break;

            case TipoComando.COMANDO_CONFIRMACION_INICIAR_JUEGO:

                ComandoConfirmacionIniciarJuego comandoConfirmacionIniciarJuego = (ComandoConfirmacionIniciarJuego) comando;

                registrarConfirmacionInicioJuego(
                        comandoConfirmacionIniciarJuego.getNombreJugador(),
                        comandoConfirmacionIniciarJuego.isConfirmacion());

                break;

            case TipoComando.SELECCIONAR_FICHAS_TABLERO:

                ComandoSeleccionarFichasTablero comandoSeleccionarFichasTablero = (ComandoSeleccionarFichasTablero) comando;

                tablero.seleccionarFichasTablero(
                        comandoSeleccionarFichasTablero.getIdsFichas(),
                        comandoSeleccionarFichasTablero.getNombreJugador()
                );

                break;

            case TipoComando.AGREGAR_FICHAS_TABLERO:

                ComandoAgregarFichasTablero comandoAgregarFichasTablero = (ComandoAgregarFichasTablero) comando;

                tablero.agregarFichasTablero(
                        comandoAgregarFichasTablero.getIdsFichas(),
                        comandoAgregarFichasTablero.getNombreJugador()
                );

                break;

            case TipoComando.AGREGAR_FICHAS_JUGADOR:

                ComandoAgregarFichasJugador comandoAgregarFichasJugador = (ComandoAgregarFichasJugador) comando;

                tablero.agregarFichasJugador(
                        comandoAgregarFichasJugador.getIdsFichas(),
                        comandoAgregarFichasJugador.getNombreJugador()
                );

                break;

            case TipoComando.AGREGAR_FICHAS_TABLERO_GRUPO:

                ComandoAgregarFichasTableroGrupo comandoAgregarFichasTableroGrupo = (ComandoAgregarFichasTableroGrupo) comando;

                tablero.agregarFichasTableroGrupos(
                        comandoAgregarFichasTableroGrupo.getIdsFichas(),
                        comandoAgregarFichasTableroGrupo.getIdsFichasGrupo(),
                        comandoAgregarFichasTableroGrupo.getNombreJugador());

                break;

            case TipoComando.QUITAR_FICHAS_JUGADOR:

                ComandoQuitarFichasJugador comandoQuitarFichasJugador = (ComandoQuitarFichasJugador) comando;

                tablero.quitarFichasJugador(
                        comandoQuitarFichasJugador.getIdsFichas(),
                        comandoQuitarFichasJugador.getNombreJugador());

                break;

            case TipoComando.QUITAR_FICHAS_TABLERO:

                ComandoQuitarFichasTablero comandoQuitarFichasTablero = (ComandoQuitarFichasTablero) comando;

                tablero.quitarFichasTablero(
                        comandoQuitarFichasTablero.getIdsFichas(),
                        comandoQuitarFichasTablero.getNombreJugador());

                break;

            case TipoComando.TOMAR_FICHA:

                ComandoTomarFicha comandoTomarFicha = (ComandoTomarFicha) comando;

                tablero.tomarFicha(comandoTomarFicha.getNombreJugador());

                break;

            case TipoComando.RESTABLECER_TABLERO:

                ComandoReestablecerTablero comandoReestablecerTablero = (ComandoReestablecerTablero) comando;

                tablero.reestablecerTablero(comandoReestablecerTablero.getNombreJugador());

                break;

            case TipoComando.TERMINAR_TURNO:

                ComandoTerminarTurno comandoTerminarTurno = (ComandoTerminarTurno) comando;

                tablero.terminarTurno(comandoTerminarTurno.getNombreJugador());

                break;

            case TipoComando.COMANDO_ABANDONAR:

                ComandoAbandonar comandoAbandonar = (ComandoAbandonar) comando;

                tablero.solicitarAbandono(comandoAbandonar.getNombreJugador());

                break;

            case TipoComando.COMANDO_CONFIRMACION_ABANDONAR:

                ComandoConfirmacionAbandonar comandoConfirmacionAbandonar = (ComandoConfirmacionAbandonar) comando;

                tablero.confirmarAbandono(
                        comandoConfirmacionAbandonar.getNombreJugador(),
                        comandoConfirmacionAbandonar.isConfirmacion());

                break;

            case TipoComando.COMANDO_SOLICITAR_FIN:

                ComandoSolicitarFin comandoSolicitarFin = (ComandoSolicitarFin) comando;

                tablero.solicitarFin(comandoSolicitarFin.getNombreJugador());

                break;

            case TipoComando.COMANDO_CONFIRMACION_SOLICITAR_FIN:

                ComandoConfirmacionSolicitarFin comandoConfirmacionSolicitarFin = (ComandoConfirmacionSolicitarFin) comando;

                tablero.confirmarSolicitarFin(
                        comandoConfirmacionSolicitarFin.getNombreJugador(),
                        comandoConfirmacionSolicitarFin.isConfirmacion());

                break;

            case TipoComando.COMANDO_REGISTRAR_JUGADOR:

                ComandoRegistrarJugador comandoRegistrarJugador = (ComandoRegistrarJugador) comando;

                if (validarRegistroJugadores(comandoRegistrarJugador.getAvatar())) {
                    actualizarJugador(comandoRegistrarJugador.getNombreJugador(), comandoRegistrarJugador.getAvatar());
                    
                    cargarJugadoresEspera();
                    
                } else {
                    IComando comandoRespuesta = new ComandoRegistroFallido(
                            comandoRegistrarJugador.getNombreJugador(), 
                            "El avatar seleccionado ya está en uso");
                    fachada.enviarComando(comandoRespuesta);
                }

                break;
            case TipoComando.COMANDO_CONFIGURAR_PARTIDA:
                
                ComandoConfigurarPartida comandoConfigurarPartida = (ComandoConfigurarPartida) comando;
                
                tablero = new Tablero(
                        jugadores, 
                        comandoConfigurarPartida.getMaximoNumeroFichas(), 
                        comandoConfigurarPartida.getNumeroComodines());
                
                tablero.setMaximoNumeroFichas(comandoConfigurarPartida.getMaximoNumeroFichas());
                tablero.setNumeroComodines(comandoConfigurarPartida.getNumeroComodines());
                
                Jugador nuevoJugador = new Jugador(null, comandoConfigurarPartida.getNombreJugador());
                
                jugadores.add(nuevoJugador);

                IComando comandoRespuesta = new ComandoPartidaConfigurada(comandoConfigurarPartida.getNombreJugador(),
                        Boolean.TRUE, MENSAJE_PARTIDA_CONFIGURADA);
                String [] direccion = {comandoConfigurarPartida.getIp(), comandoConfigurarPartida.getPuerto()};
                IComando comandoAgregarJugador = new ComandoAgregarDireccionJugador(direccion, comandoConfigurarPartida.getNombreJugador());
                fachada.enviarComando(comandoAgregarJugador);
                fachada.enviarComando(comandoRespuesta);
                
                
                break;
                
                
            case TipoComando.COMANDO_UNIRSE_PARTIDA:
            
                ComandoUnirsePartida comadnComandoUnirsePartida = (ComandoUnirsePartida) comando;
                
                registrarSolicitudUnirsePartida(comadnComandoUnirsePartida.getNombreJugador(), 
                        new String[]{comadnComandoUnirsePartida.getIp(), comadnComandoUnirsePartida.getPuerto()});
            
                break;
                
            case TipoComando.COMANDO_CONFIRMACION_UNIRSE_PARTIDA:
                
                ComandoConfirmacionUnirsePartida comandoConfirmacionUnirsePartida = (ComandoConfirmacionUnirsePartida) comando;
                
                registrarConfirmacionJugadorUnirsePartida(
                        comandoConfirmacionUnirsePartida.getNombreJugador(), 
                        comandoConfirmacionUnirsePartida.isRespuesta(),
                        comandoConfirmacionUnirsePartida.getNombreJugadorSolicitante());
                
                break;
                
            default:
                throw new AssertionError();
        }

    }

    private void registrarSolicitudUnirsePartida(String nombreJugador, String[] direccion){
        
        ComandoAgregarDireccionJugador comandoAgregarDireccionJugador = new ComandoAgregarDireccionJugador(direccion, nombreJugador);
        
        fachada.enviarComando(comandoAgregarDireccionJugador);
        
        for(Jugador jugador: jugadores){
            
            ComandoRespuestaUnirsePartida comandoRespuestaUnirsePartida = new ComandoRespuestaUnirsePartida(
                    jugador.getNombre(), 
                    nombreJugador + MENSAJE_JUGADOR_DESEA_UNIRSE,
                    nombreJugador);
            
            fachada.enviarComando(comandoRespuestaUnirsePartida);
        }
        
    }
    
    private void registrarConfirmacionJugadorUnirsePartida(String nombreJugador, boolean respuesta, String nombreJugadorSolicitante){
        
        if(jugadorExiste(nombreJugador)){
            
            VotacionUnirsePartida votacionUnirsePartida = votacionesUnirsePartida.get(nombreJugadorSolicitante);
            
            if(votacionUnirsePartida == null){
                
                votacionUnirsePartida 
                        = new VotacionUnirsePartida(
                                nombreJugadorSolicitante, 
                                jugadores.size());
                
                
                votacionesUnirsePartida.put(nombreJugadorSolicitante, votacionUnirsePartida);
                
            }
            
            votacionUnirsePartida.agregarVoto(nombreJugador, respuesta);
            
            if (votacionUnirsePartida.esAceptado()) {
                finalizarVotacion(nombreJugadorSolicitante, true);
            } 
            else if (votacionUnirsePartida.esRechazado()) {
                finalizarVotacion(nombreJugadorSolicitante, false);
            }
        }
    }
    
    private void finalizarVotacion(String nombreJugadorSolicitante, boolean aceptado){
        
        votacionesUnirsePartida.remove(nombreJugadorSolicitante);
        
        ComandoRespuestaConfirmacionUnirsePartida comandoRespuestaConfirmacionUnirsePartida;
        if(aceptado){
            
            jugadores.add(new Jugador(null, nombreJugadorSolicitante));
            
             comandoRespuestaConfirmacionUnirsePartida
                    = new ComandoRespuestaConfirmacionUnirsePartida(
                            nombreJugadorSolicitante, 
                            MENSAJE_ACEPTACION_UNIRSE, 
                            true);
             
            fachada.enviarComando(comandoRespuestaConfirmacionUnirsePartida);
                
            List<JugadorInicioPartidaDTO> jugadoresInicioPartida = new LinkedList<>();

            for (Jugador jugador: jugadores) {
                
                
                jugadoresInicioPartida.add(
                    new JugadorInicioPartidaDTO(
                            jugador.getNombre(),
                            jugador.getAvatar()));  

            }

            for (Jugador jugador : jugadores) {

                if(jugador.getNombre() != nombreJugadorSolicitante){
                    ComandoCargarJugadores comandoCargarJugadores
                        = new ComandoCargarJugadores(
                                jugador.getNombre(),
                                jugadoresInicioPartida);

                    fachada.enviarComando(comandoCargarJugadores);
                }

            }
            
        } else{
            
            comandoRespuestaConfirmacionUnirsePartida
                    = new ComandoRespuestaConfirmacionUnirsePartida(
                            nombreJugadorSolicitante, 
                            MENSAJE_RECHAZO_UNIRSE, 
                            false);
            
            fachada.enviarComando(comandoRespuestaConfirmacionUnirsePartida);
        }
        
        
    }
            
            
    private void solicitarInicioJuego(String nombreJugador) {

        if (jugadorExiste(nombreJugador)) {

            ComandoRespuestaIniciarJuego comandoRespuestaIniciarJuego
                    = new ComandoRespuestaIniciarJuego(
                            nombreJugador,
                            MENSAJE_CONFIRMACION_INICIO_JUEGO);

            fachada.enviarComando(comandoRespuestaIniciarJuego);

            return;

        }

    }

    private void cargarJugadoresEspera() {

        List<JugadorInicioPartidaDTO> jugadoresInicioPartida = new LinkedList<>();

        for (Jugador jugador : jugadores) {

            jugadoresInicioPartida.add(
                    new JugadorInicioPartidaDTO(
                            jugador.getNombre(),
                            jugador.getAvatar()));

        }

        for (Jugador jugador : jugadores) {

            ComandoCargarJugadores comandoCargarJugadores
                    = new ComandoCargarJugadores(
                            jugador.getNombre(),
                            jugadoresInicioPartida);

            fachada.enviarComando(comandoCargarJugadores);

        }

    }

    private void enviarSolicitudInicioJuego(String nombreJugador, boolean confirmacion) {

        if (jugadorExiste(nombreJugador)) {

            if (confirmacion) {

                jugadoresInicioJuego.add(obtenerJugador(nombreJugador));

                ComandoActualizarJugadoresInicioJuego comandoNuevoJugadorSolicitaInicioJuego
                        = new ComandoActualizarJugadoresInicioJuego(nombreJugador, jugadoresInicioJuego.size());

                fachada.enviarComando(comandoNuevoJugadorSolicitaInicioJuego);

                for (Jugador jugador : jugadores) {

                    if (!jugador.getNombre().equals(nombreJugador)) {

                        ComandoNuevaSolicitudIniciarJuego comandoNuevaSolicitudIniciarJuego
                                = new ComandoNuevaSolicitudIniciarJuego(
                                        jugador.getNombre(),
                                        nombreJugador + MENSAJE_NUEVA_SOLICITUD_INICIO_JUEGO);

                        fachada.enviarComando(comandoNuevaSolicitudIniciarJuego);

                        return;

                    }
                }

            }

        }

    }

    private void registrarConfirmacionInicioJuego(String nombreJugador, boolean confirmacion) {

        if (jugadorExiste(nombreJugador)) {

            if (confirmacion) {

                Jugador jugadorConfirmacion = obtenerJugador(nombreJugador);

                jugadoresInicioJuego.add(jugadorConfirmacion);

                if (jugadoresInicioJuego.size() == jugadores.size()) {

                    for (Jugador jugador : jugadores) {

                        ComandoDecisionIniciarJuego comandoDecisionIniciarJuego
                                = new ComandoDecisionIniciarJuego(
                                        jugador.getNombre(),
                                        MENSAJE_INICIO_JUEGO,
                                        true);

                        fachada.enviarComando(comandoDecisionIniciarJuego);

                    }

                    //TODO
                    tablero.iniciarJuego();

                } else {

                    for (Jugador jugador : jugadores) {

                        ComandoActualizarJugadoresInicioJuego comandoNuevoJugadorSolicitaInicioJuego
                                = new ComandoActualizarJugadoresInicioJuego(
                                        jugador.getNombre(),
                                        jugadoresInicioJuego.size());

                        fachada.enviarComando(comandoNuevoJugadorSolicitaInicioJuego);

                    }
                }

            } else {

                jugadoresInicioJuego = new LinkedList<>();

                for (Jugador jugador : jugadores) {

                    ComandoDecisionIniciarJuego comandoDecisionIniciarJuego
                            = new ComandoDecisionIniciarJuego(
                                    jugador.getNombre(),
                                    MENSAJE_RECHAZO_INICIO_JUEGO,
                                    false);

                    fachada.enviarComando(comandoDecisionIniciarJuego);

                    ComandoActualizarJugadoresInicioJuego comandoActualizarJugadoresInicioJuego
                            = new ComandoActualizarJugadoresInicioJuego(
                                    jugador.getNombre(),
                                    jugadoresInicioJuego.size());

                    fachada.enviarComando(comandoActualizarJugadoresInicioJuego);

                }

            }

        }

    }

    private void actualizarJugador(String nombre, String avatar) {

        if (jugadorExiste(nombre)) {

            obtenerJugador(nombre).setAvatar(avatar);
            
        }
    }

    private boolean validarRegistroJugadores(String avatar) {

        // Validar que Tablero exista
        if (tablero == null) {
            return false;
        }

        // Recorrer jugadores ya creados
        for (Jugador jugador: jugadores) {

            if (jugador.getAvatar() == null) {
                continue;
            }
            // Validar avatar repetido
            if (jugador.getAvatar().equalsIgnoreCase(avatar)) {
                return false;
            }
        }

        return true;
    }

    private boolean jugadorExiste(String nombreJugador) {

        boolean jugadorExiste = false;

        for (Jugador jugador : jugadores) {

            if (jugador.getNombre().equals(nombreJugador)) {
                jugadorExiste = true;
            }
        }

        return jugadorExiste;
    }

    private Jugador obtenerJugador(String nombreJugador) {

        for (Jugador jugador : jugadores) {

            if (jugador.getNombre().equals(nombreJugador)) {
                return jugador;
            }
        }

        return null;

    }

}
