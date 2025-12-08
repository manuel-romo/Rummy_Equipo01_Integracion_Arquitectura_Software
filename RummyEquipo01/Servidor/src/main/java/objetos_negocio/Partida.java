
package objetos_negocio;

import comandosRespuesta.ComandoCargarJugadores;
import comandosRespuesta.ComandoDecisionIniciarJuego;
import comandosRespuesta.ComandoNuevaSolicitudIniciarJuego;
import comandosRespuesta.ComandoActualizarJugadoresInicioJuego;
import comandosRespuesta.ComandoRespuestaIniciarJuego;
import comandosSolicitud.ComandoAbandonar;
import comandosSolicitud.ComandoAgregarFichasJugador;
import comandosSolicitud.ComandoAgregarFichasTablero;
import comandosSolicitud.ComandoAgregarFichasTableroGrupo;
import comandosSolicitud.ComandoConfirmacionAbandonar;
import comandosSolicitud.ComandoConfirmacionEnvioIniciarJuego;
import comandosSolicitud.ComandoConfirmacionIniciarJuego;
import comandosSolicitud.ComandoConfirmacionSolicitarFin;
import comandosSolicitud.ComandoIniciarJuego;
import comandosSolicitud.ComandoQuitarFichasJugador;
import comandosSolicitud.ComandoQuitarFichasTablero;
import comandosSolicitud.ComandoReestablecerTablero;
import comandosSolicitud.ComandoSeleccionarFichasTablero;
import comandosSolicitud.ComandoSolicitarFin;
import comandosSolicitud.ComandoTerminarTurno;
import comandosSolicitud.ComandoTomarFicha;
import comandosSolicitud.CommandType;
import dto.JugadorInicioPartidaDTO;
import excepciones.RummyException;
import interfaces.ICommand;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class Partida {
    
    private List<Jugador> jugadores;
    private List<Jugador> jugadoresInicioJuego = new LinkedList<>();
    
    private FachadaObjetosNegocio fachada;
    
    private Tablero tablero;
    
    private final String MENSAJE_CONFIRMACION_INICIO_JUEGO = "¿Desea solicitar el inicio de la partida?";
    private final String MENSAJE_NUEVA_SOLICITUD_INICIO_JUEGO = " quiere iniciar la partida. ¿Desea inicar?";
    private final String MENSAJE_INICIO_JUEGO = "¡La partida comenzará!";
    private final String MENSAJE_RECHAZO_INICIO_JUEGO = "No se ha aceptado el inicio de la partida";

    public Partida(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    public void cargarJugadores(){
        
        cargarJugadoresEspera();
        
    }

    public void setFachada(FachadaObjetosNegocio fachada) {
        this.fachada = fachada;
    }
    
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    public void ejecutar(ICommand comando) throws RummyException {

        CommandType tipoComando = CommandType.fromNombre(comando.getType());

        switch (tipoComando) {

            case CommandType.COMANDO_INICIAR_JUEGO:

                ComandoIniciarJuego comandoIniciarJuego = (ComandoIniciarJuego) comando;

                solicitarInicioJuego(comandoIniciarJuego.getNombreJugador());

                break;
                
            case CommandType.COMANDO_CONFIRMACION_ENVIO_INICIAR_JUEGO:

                ComandoConfirmacionEnvioIniciarJuego comandoConfirmacionEnvioIniciarJuego = (ComandoConfirmacionEnvioIniciarJuego) comando;

                enviarSolicitudInicioJuego(
                        comandoConfirmacionEnvioIniciarJuego.getNombreJugador(), 
                        comandoConfirmacionEnvioIniciarJuego.isConfirmacion());

                break;
                
            case CommandType.COMANDO_CONFIRMACION_INICIAR_JUEGO:

                ComandoConfirmacionIniciarJuego comandoConfirmacionIniciarJuego = (ComandoConfirmacionIniciarJuego) comando;

                registrarConfirmacionInicioJuego(
                        comandoConfirmacionIniciarJuego.getNombreJugador(), 
                        comandoConfirmacionIniciarJuego.isConfirmacion());

                break;
                
                
            case CommandType.SELECCIONAR_FICHAS_TABLERO:

                ComandoSeleccionarFichasTablero comandoSeleccionarFichasTablero = (ComandoSeleccionarFichasTablero) comando;

                tablero.seleccionarFichasTablero(
                        comandoSeleccionarFichasTablero.getIdsFichas(),
                        comandoSeleccionarFichasTablero.getNombreJugador()
                );

                break;

            case CommandType.AGREGAR_FICHAS_TABLERO:

                ComandoAgregarFichasTablero comandoAgregarFichasTablero = (ComandoAgregarFichasTablero) comando;

                tablero.agregarFichasTablero(
                        comandoAgregarFichasTablero.getIdsFichas(),
                        comandoAgregarFichasTablero.getNombreJugador()
                );

                break;

            case CommandType.AGREGAR_FICHAS_JUGADOR:

                ComandoAgregarFichasJugador comandoAgregarFichasJugador = (ComandoAgregarFichasJugador) comando;

                tablero.agregarFichasJugador(
                        comandoAgregarFichasJugador.getIdsFichas(),
                        comandoAgregarFichasJugador.getNombreJugador()
                );

                break;

            case CommandType.AGREGAR_FICHAS_TABLERO_GRUPO:

                ComandoAgregarFichasTableroGrupo comandoAgregarFichasTableroGrupo = (ComandoAgregarFichasTableroGrupo) comando;

                tablero.agregarFichasTableroGrupos(
                        comandoAgregarFichasTableroGrupo.getIdsFichas(),
                        comandoAgregarFichasTableroGrupo.getIdsFichasGrupo(),
                        comandoAgregarFichasTableroGrupo.getNombreJugador());

                break;

            case CommandType.QUITAR_FICHAS_JUGADOR:

                ComandoQuitarFichasJugador comandoQuitarFichasJugador = (ComandoQuitarFichasJugador) comando;

                tablero.quitarFichasJugador(
                        comandoQuitarFichasJugador.getIdsFichas(),
                        comandoQuitarFichasJugador.getNombreJugador());

                break;

            case CommandType.QUITAR_FICHAS_TABLERO:

                ComandoQuitarFichasTablero comandoQuitarFichasTablero = (ComandoQuitarFichasTablero) comando;

                tablero.quitarFichasTablero(
                        comandoQuitarFichasTablero.getIdsFichas(),
                        comandoQuitarFichasTablero.getNombreJugador());

                break;

            case CommandType.TOMAR_FICHA:
                
                ComandoTomarFicha comandoTomarFicha = (ComandoTomarFicha) comando;

                tablero.tomarFicha(comandoTomarFicha.getNombreJugador());
                
                break;
                
            case CommandType.RESTABLECER_TABLERO:
                
                ComandoReestablecerTablero comandoReestablecerTablero = (ComandoReestablecerTablero) comando;

                tablero.reestablecerTablero(comandoReestablecerTablero.getNombreJugador());
                
                break;
                
            case CommandType.TERMINAR_TURNO:

                ComandoTerminarTurno comandoTerminarTurno = (ComandoTerminarTurno) comando;

                tablero.terminarTurno(comandoTerminarTurno.getNombreJugador());

                break;

            case CommandType.COMANDO_ABANDONAR:

                ComandoAbandonar comandoAbandonar = (ComandoAbandonar) comando;

                tablero.solicitarAbandono(comandoAbandonar.getNombreJugador());

                break;
                
            case CommandType.COMANDO_CONFIRMACION_ABANDONAR:

                ComandoConfirmacionAbandonar comandoConfirmacionAbandonar = (ComandoConfirmacionAbandonar) comando;

                tablero.confirmarAbandono(
                        comandoConfirmacionAbandonar.getNombreJugador(),
                        comandoConfirmacionAbandonar.isConfirmacion());

                break;
                
            case CommandType.COMANDO_SOLICITAR_FIN:

                ComandoSolicitarFin comandoSolicitarFin = (ComandoSolicitarFin) comando;

                tablero.solicitarFin(comandoSolicitarFin.getNombreJugador());

                break;
                
            case CommandType.COMANDO_CONFIRMACION_SOLICITAR_FIN:

                ComandoConfirmacionSolicitarFin comandoConfirmacionSolicitarFin = (ComandoConfirmacionSolicitarFin) comando;

                tablero.confirmarSolicitarFin(
                        comandoConfirmacionSolicitarFin.getNombreJugador(), 
                        comandoConfirmacionSolicitarFin.isConfirmacion());

                break;
                
            default:
                throw new AssertionError();
        }

    }
    
    private void solicitarInicioJuego(String nombreJugador){
            
        if(jugadorExiste(nombreJugador)){
            for(Jugador jugador: jugadores){
            
                if(jugador.getNombre().equals(nombreJugador)){
                    
                    ComandoRespuestaIniciarJuego comandoRespuestaIniciarJuego = 
                            new ComandoRespuestaIniciarJuego(
                                    nombreJugador, 
                                    MENSAJE_CONFIRMACION_INICIO_JUEGO);
                    
                    fachada.enviarComando(comandoRespuestaIniciarJuego);
                    
                    return;

                }
            }
            
        }
            
    }
    
    private void cargarJugadoresEspera(){
        
        List<JugadorInicioPartidaDTO> jugadoresInicioPartida = new LinkedList<>();
        
        for(Jugador jugador: jugadores){
            
            jugadoresInicioPartida.add(
                    new JugadorInicioPartidaDTO(
                            jugador.getNombre(), 
                            jugador.getAvatar()));
            
        }
         
        for(Jugador jugador: jugadores){
            
            ComandoCargarJugadores comandoCargarJugadores 
                    = new ComandoCargarJugadores(
                            jugador.getNombre(), 
                            jugadoresInicioPartida);
            
            fachada.enviarComando(comandoCargarJugadores);
            
            
        }
        
    }
    
    private void enviarSolicitudInicioJuego(String nombreJugador, boolean confirmacion){
        
        if(jugadorExiste(nombreJugador)){
            
            if(confirmacion){
                
                jugadoresInicioJuego.add(obtenerJugador(nombreJugador));
                
                ComandoActualizarJugadoresInicioJuego comandoNuevoJugadorSolicitaInicioJuego 
                        = new ComandoActualizarJugadoresInicioJuego(nombreJugador, jugadoresInicioJuego.size());
                
                fachada.enviarComando(comandoNuevoJugadorSolicitaInicioJuego);
                
                for(Jugador jugador: jugadores){
                    
                    if(!jugador.getNombre().equals(nombreJugador)){

                        ComandoNuevaSolicitudIniciarJuego comandoNuevaSolicitudIniciarJuego = 
                                new ComandoNuevaSolicitudIniciarJuego(
                                        jugador.getNombre(), 
                                        nombreJugador + MENSAJE_NUEVA_SOLICITUD_INICIO_JUEGO);

                        fachada.enviarComando(comandoNuevaSolicitudIniciarJuego);

                        return;

                    }
                }
                
            }
            
        }
        
        
    }
    
    private void registrarConfirmacionInicioJuego(String nombreJugador, boolean confirmacion){
        
        if(jugadorExiste(nombreJugador)){
            
            if(confirmacion){
                
                Jugador jugadorConfirmacion = obtenerJugador(nombreJugador);
                
                jugadoresInicioJuego.add(jugadorConfirmacion);
                
                if(jugadoresInicioJuego.size() == jugadores.size()){
                    
                    for(Jugador jugador: jugadores){
                    
                        ComandoDecisionIniciarJuego comandoDecisionIniciarJuego 
                                = new ComandoDecisionIniciarJuego(
                                        jugador.getNombre(), 
                                        MENSAJE_INICIO_JUEGO,
                                        true);
                        
                        fachada.enviarComando(comandoDecisionIniciarJuego);

                    }
                    
                    //TODO
                    tablero.iniciarJuego();
                    
                } else{
                    
                    for(Jugador jugador: jugadores){
                    
                        ComandoActualizarJugadoresInicioJuego comandoNuevoJugadorSolicitaInicioJuego 
                                = new ComandoActualizarJugadoresInicioJuego(
                                        jugador.getNombre(), 
                                        jugadoresInicioJuego.size());
                        
                        fachada.enviarComando(comandoNuevoJugadorSolicitaInicioJuego);

                    }
                }
                
                
            } else{
                
                jugadoresInicioJuego = new LinkedList<>();
                 
                for(Jugador jugador: jugadores){
                    
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
    
    private boolean jugadorExiste(String nombreJugador){
        
        boolean jugadorExiste = false;
        
        for(Jugador jugador: jugadores){
            
            if(jugador.getNombre().equals(nombreJugador)){
                jugadorExiste = true;
            }
        }
        
        return jugadorExiste;
    }
    
    private Jugador obtenerJugador(String nombreJugador){
        
        for(Jugador jugador: jugadores){
            
            if(jugador.getNombre().equals(nombreJugador)){
                return jugador;
            }
        }
        
        return null;
        
    }
    
    
}
