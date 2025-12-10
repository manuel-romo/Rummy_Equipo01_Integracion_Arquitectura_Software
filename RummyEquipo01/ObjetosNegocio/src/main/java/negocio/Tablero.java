package negocio;

import comandos.respuesta.ComandoCambioTurno;
import comandos.respuesta.ComandoIniciarTurno;
import comandos.respuesta.ComandoRespuestaAbandonar;
import comandos.respuesta.ComandoRespuestaMovimiento;
import comandos.respuesta.ComandoRespuestaReestablecer;
import comandos.respuesta.ComandoTableroInvalido;
import comandos.solicitud.ComandoAbandonar;
import comandos.solicitud.ComandoAgregarFichasJugador;
import comandos.solicitud.ComandoAgregarFichasTablero;
import comandos.solicitud.ComandoAgregarFichasTableroGrupo;
import comandos.respuesta.ComandoFinPartida;
import comandos.respuesta.ComandoJugadorAbandonoPartida;
import comandos.respuesta.ComandoJugadorPartidaGanada;
import comandos.respuesta.ComandoPartidaGanada;
import comandos.respuesta.ComandoRespuestaConfirmacionSolicitarFin;
import comandos.respuesta.ComandoRespuestaSolicitarFin;
import comandos.solicitud.ComandoConfirmacionAbandonar;
import comandos.solicitud.ComandoConfirmacionSolicitarFin;
import comandos.solicitud.ComandoQuitarFichasJugador;
import comandos.solicitud.ComandoQuitarFichasTablero;
import comandos.solicitud.ComandoReestablecerTablero;
import comandos.solicitud.ComandoSeleccionarFichasTablero;
import comandos.solicitud.ComandoSolicitarFin;
import comandos.solicitud.ComandoTerminarTurno;
import comandos.solicitud.ComandoTomarFicha;
import dtos.FichaComodinDTO;
import dtos.FichaDTO;
import dtos.FichaNormalDTO;
import dtos.GrupoDTO;
import dtos.JugadorDTO;
import dtos.MontonDTO;
import dtos.TableroDTO;
import enumeradores.ColorFicha;
import enumeradores.ColorFichaDTO;
import enumeradores.TipoComando;
import excepciones.RummyException;
import fabrica.FabricaGrupos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import interfaces.IComando;

/**
 * Clase de Negocio para el servidor el cual valida todo el tablero cuando se
 * termina el turno.
 *
 * @author Juan Heras
 */
public class Tablero {

    private Jugador jugadorTurno;
    private List<Jugador> jugadores = new LinkedList<>();
    private List<Ficha> fichas = new LinkedList<>();
    private List<Grupo> grupos = new LinkedList<>();
    private Monton monton;
    private FachadaObjetosNegocio fachadaTablero;

    private List<Ficha> fichasInicialesJugadorTurno = new LinkedList<>();
    private List<Grupo> gruposInicialesTurno = new LinkedList<>();
    private Grupo grupoPrimerTurno;

    private Ficha fichaTomada;
    private List<String> nombresJugadoresSolicitanFin = new LinkedList<>();

    private int numeroGrupoActual = 0;

    private  int MAXIMO_NUMERO_FICHA = 13;
    private  int NUMERO_COMODINES = 2;

    private final String MENSAJE_CAMBIO_TURNO = "Ha iniciado el turno del jugador ";
    private final String MENSAJE_INICIO_TURNO = "¡Ha iniciado su turno!";
    private final String MENSAJE_QUITAR_FICHA_GRUPO_PRIMER_TURNO = "No puede quitar fichas de otros grupos en su primer turno.";
    private final String MENSAJE_GRUPO_INVALIDO = "No se puede formar ese grupo.";
    private final String MENSAJE_AGREGAR_FICHA_GRUPO_PRIMER_TURNO = "No puede agregar fichas a otros grupos en su primer turno.";
    private final String MENSAJE_AGREGAR_FICHAS_TABLERO_JUGADOR = "No puede agregar fichas del tablero a su mano.";
    private final String MENSAJE_AGREGAR_FICHA_MAS_UN_GRUPO_PRIMER_TURNO = "No puede agregar más de un grupo en su primer turno.";
    private final String MENSAJE_TABLERO_INVALIDO = "Uno o más grupos del tablero son inválidos.";
    
    private final String MENSAJE_CONFIRMACION_ABANDONO_PARTIDA = "¿Desea abandonar la partida?";
    private final String MENSAJE_JUGADOR_ABANDONO_PARTIDA = " ha abandonado la partida.";
    
    private final String MENSAJE_CONFIRMACION_FIN_PARTIDA = "¿Desea solicitar el fin de la partida?";
    private final String MENSAJE_SOLICITUD_FIN_PARTIDA_JUGADOR = " ha solicitado el fin de la partida. ¿Quiere finalizar la partida?";
    private final String MENSAJE_ESPERANDO_CONFIRMACION_JUGADORES = "Esperando jugadores";
    
    private final String MENSAJE_SOLICITUD_FIN_PARTIDA_ACEPTADA = "La partida ha finalizado";
    private final String MENSAJE_SOLICITUD_FIN_PARTIDA_CANCELADO = "La partida continuará";
    
    private final String MENSAJE_PARTIDA_GANADA = "¡Has ganado la partida!";
    private final String MENSAJE_JUGADOR_PARTIDA_GANADA = " ha ganado la partida.";

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void iniciarJuego(int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES ) {
        MAXIMO_NUMERO_FICHA = MAXIMO_NUMERO_FICHAS;
        this.NUMERO_COMODINES = NUMERO_COMODINES;
        
        ColorFicha[] ordenColores = {
            ColorFicha.COLOR_A, ColorFicha.COLOR_A,
            ColorFicha.COLOR_B, ColorFicha.COLOR_B,
            ColorFicha.COLOR_C, ColorFicha.COLOR_C,
            ColorFicha.COLOR_D, ColorFicha.COLOR_D
        };
        
        List<Ficha> fichasMonton = new LinkedList<>();
        int id = 1;
        for (ColorFicha color : ordenColores) {
            id = fichasMonton.size() + 1;
            crearMonton(id, fichasMonton, color);
        }
        id = fichasMonton.size()+1;
        crearFichasComodines(id, fichasMonton);
        
        this.fichas.addAll(fichasMonton);
        
        
        List<Ficha> fichasJugador1 = repartirMano(fichasMonton, 14);
        List<Ficha> fichasJugador2 = repartirMano(fichasMonton, 14);
        
        

        monton = new Monton(fichasMonton);

        Jugador jugador1 = new Jugador("avatar1.png", "Francisco34", true, fichasJugador1);

        Jugador jugador2 = new Jugador("avatar2.png", "Sandy43", true, fichasJugador2);
        
        


        jugadores = new LinkedList<>(Arrays.asList(jugador1, jugador2));
        Collections.shuffle(jugadores);
        
        jugadorTurno = jugadores.getFirst();
        

        gruposInicialesTurno = new LinkedList<>();
        
        List<Ficha> copiafichasJugador = new LinkedList<>(jugadorTurno.getFichas());
        fichasInicialesJugadorTurno = copiafichasJugador;
        notificarTodosCambioTurno();

    }
    public void crearMonton(int idInicial, List<Ficha> lista, ColorFicha color) {
        for(int i = 1; i <= MAXIMO_NUMERO_FICHA; i++) {
             lista.add(new FichaNormal(idInicial, color, false, i));
             idInicial++;
        }
    }   
    
    public List<Ficha> crearFichasComodines(int id, List<Ficha> fichasMonton){
        for(int i = 0; i<NUMERO_COMODINES ; i++){
            fichasMonton.add(new FichaComodin(id, ColorFicha.COLOR_COMODIN,true, "*"));
            id++;
        }
        return fichasMonton;
    }
    
    public static List<Ficha> repartirMano(List<Ficha> monton, int cantidad) {
        
        List<Ficha> fichasJugador = new ArrayList<>();

        if (monton.size() < cantidad) {
            return null;
        }

        Collections.shuffle(monton);

        // Se eliminan las fichas del montón y se agregan a las fichas del jugador.
        for (int i = 0; i < cantidad; i++) {
            Ficha fichaRobada = monton.remove(0); 
            fichasJugador.add(fichaRobada);
        }

        return fichasJugador;
    }
    

    public void ejecutar(IComando comando) throws RummyException {

        TipoComando tipoComando = TipoComando.fromNombre(comando.getTipo());

        switch (tipoComando) {

            case TipoComando.SELECCIONAR_FICHAS_TABLERO:

                ComandoSeleccionarFichasTablero comandoSeleccionarFichasTablero = (ComandoSeleccionarFichasTablero) comando;

                seleccionarFichasTablero(
                        comandoSeleccionarFichasTablero.getIdsFichas(),
                        comandoSeleccionarFichasTablero.getNombreJugador()
                );

                break;

            case TipoComando.AGREGAR_FICHAS_TABLERO:

                ComandoAgregarFichasTablero comandoAgregarFichasTablero = (ComandoAgregarFichasTablero) comando;

                agregarFichasTablero(
                        comandoAgregarFichasTablero.getIdsFichas(),
                        comandoAgregarFichasTablero.getNombreJugador()
                );

                break;

            case TipoComando.AGREGAR_FICHAS_JUGADOR:

                ComandoAgregarFichasJugador comandoAgregarFichasJugador = (ComandoAgregarFichasJugador) comando;

                agregarFichasJugador(
                        comandoAgregarFichasJugador.getIdsFichas(),
                        comandoAgregarFichasJugador.getNombreJugador()
                );

                break;

            case TipoComando.AGREGAR_FICHAS_TABLERO_GRUPO:

                ComandoAgregarFichasTableroGrupo comandoAgregarFichasTableroGrupo = (ComandoAgregarFichasTableroGrupo) comando;

                agregarFichasTableroGrupos(
                        comandoAgregarFichasTableroGrupo.getIdsFichas(),
                        comandoAgregarFichasTableroGrupo.getIdsFichasGrupo(),
                        comandoAgregarFichasTableroGrupo.getNombreJugador());

                break;

            case TipoComando.QUITAR_FICHAS_JUGADOR:

                ComandoQuitarFichasJugador comandoQuitarFichasJugador = (ComandoQuitarFichasJugador) comando;

                quitarFichasJugador(
                        comandoQuitarFichasJugador.getIdsFichas(),
                        comandoQuitarFichasJugador.getNombreJugador());

                break;

            case TipoComando.QUITAR_FICHAS_TABLERO:

                ComandoQuitarFichasTablero comandoQuitarFichasTablero = (ComandoQuitarFichasTablero) comando;

                quitarFichasTablero(
                        comandoQuitarFichasTablero.getIdsFichas(),
                        comandoQuitarFichasTablero.getNombreJugador());

                break;

            case TipoComando.TOMAR_FICHA:
                
                ComandoTomarFicha comandoTomarFicha = (ComandoTomarFicha) comando;

                tomarFicha(comandoTomarFicha.getNombreJugador());
                
                break;
                
            case TipoComando.RESTABLECER_TABLERO:
                
                ComandoReestablecerTablero comandoReestablecerTablero = (ComandoReestablecerTablero) comando;

                reestablecerTablero(comandoReestablecerTablero.getNombreJugador());
                
                break;
                
            case TipoComando.TERMINAR_TURNO:

                ComandoTerminarTurno comandoTerminarTurno = (ComandoTerminarTurno) comando;

                terminarTurno(comandoTerminarTurno.getNombreJugador());

                break;

            case TipoComando.COMANDO_ABANDONAR:

                ComandoAbandonar comandoAbandonar = (ComandoAbandonar) comando;

                solicitarAbandono(comandoAbandonar.getNombreJugador());

                break;
                
            case TipoComando.COMANDO_CONFIRMACION_ABANDONAR:

                ComandoConfirmacionAbandonar comandoConfirmacionAbandonar = (ComandoConfirmacionAbandonar) comando;

                confirmarAbandono(
                        comandoConfirmacionAbandonar.getNombreJugador(),
                        comandoConfirmacionAbandonar.isConfirmacion());

                break;
                
            case TipoComando.COMANDO_SOLICITAR_FIN:

                ComandoSolicitarFin comandoSolicitarFin = (ComandoSolicitarFin) comando;

                solicitarFin(comandoSolicitarFin.getNombreJugador());

                break;
                
            case TipoComando.COMANDO_CONFIRMACION_SOLICITAR_FIN:

                ComandoConfirmacionSolicitarFin comandoConfirmacionSolicitarFin = (ComandoConfirmacionSolicitarFin) comando;

                confirmarSolicitarFin(
                        comandoConfirmacionSolicitarFin.getNombreJugador(), 
                        comandoConfirmacionSolicitarFin.isConfirmacion());

                break;
                
            default:
                throw new AssertionError();
        }

    }

    public void seleccionarFichasTablero(Integer[] idsFichas, String nombreJugador) {

        boolean esPrimerTurnoJugador = esPrimerTurnoJugador(nombreJugador);

        if (esPrimerTurnoJugador) {

            for (int idFicha : idsFichas) {

                Ficha ficha = encontrarFichaPorId(idFicha);

                if (ficha != null && ficha.isTieneGrupo()) {

                    IComando comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                            obtenerTableroDto(jugadorTurno.getNombre()),
                            false,
                            nombreJugador);

                    fachadaTablero.enviarComando(comandoRespuestaMovimiento);

                }

            }

        } else {

            ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                    obtenerTableroDto(jugadorTurno.getNombre()),
                    false,
                    nombreJugador);

            fachadaTablero.enviarComando(comandoRespuestaMovimiento);

        }

    }

    public void agregarFichasJugador(Integer[] idsFichas, String nombreJugador) {

        List<Ficha> fichasInicialesTurno = obtenerFichasInicialesTurno();

        List<Ficha> fichasAgregar = new LinkedList<>();
        for (int id : idsFichas) {
            Ficha ficha = encontrarFichaPorId(id);

            if (fichasInicialesTurno.contains(ficha)) {

                ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                        obtenerTableroDto(jugadorTurno.getNombre()),
                        false,
                        nombreJugador,
                        MENSAJE_AGREGAR_FICHAS_TABLERO_JUGADOR);

                fachadaTablero.enviarComando(comandoRespuestaMovimiento);

                return;
            }

            fichasAgregar.add(ficha);
        }

        jugadorTurno.getFichas().addAll(fichasAgregar);

        ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                obtenerTableroDto(jugadorTurno.getNombre()),
                true,
                nombreJugador);

        fachadaTablero.enviarComando(comandoRespuestaMovimiento);

    }

    public List<Ficha> obtenerFichasInicialesTurno() {

        List<Ficha> fichasInicialesTurno = new LinkedList<>();

        for (Grupo grupo : gruposInicialesTurno) {

            fichasInicialesTurno.addAll(grupo.getFichas());

        }

        return fichasInicialesTurno;

    }

    public void agregarFichasTablero(Integer[] idsFichas, String nombreJugador) {

        boolean esPrimerTurno = esPrimerTurnoJugador(nombreJugador);

        if (esPrimerTurno && grupoPrimerTurno != null) {

            ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                    obtenerTableroDto(jugadorTurno.getNombre()),
                    false,
                    nombreJugador,
                    MENSAJE_AGREGAR_FICHA_MAS_UN_GRUPO_PRIMER_TURNO);

            fachadaTablero.enviarComando(comandoRespuestaMovimiento);
            return;

        } else {

            List<Ficha> fichasAgregar = new LinkedList<>();
            for (int id : idsFichas) {
                Ficha ficha = encontrarFichaPorId(id);
                fichasAgregar.add(ficha);
            }

            Grupo grupo;
            try {
                grupo = FabricaGrupos.crearGrupo(
                        ++numeroGrupoActual,
                        fichasAgregar,
                        esPrimerTurno,
                        MAXIMO_NUMERO_FICHA);
            } catch (RummyException ex) {

                ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                        obtenerTableroDto(jugadorTurno.getNombre()),
                        false,
                        nombreJugador,
                        ex.getMessage());

                fachadaTablero.enviarComando(comandoRespuestaMovimiento);

                return;
            }

            if (grupo == null) {
                ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                        obtenerTableroDto(jugadorTurno.getNombre()),
                        false,
                        nombreJugador,
                        MENSAJE_GRUPO_INVALIDO);

                fachadaTablero.enviarComando(comandoRespuestaMovimiento);
                return;
            }

            grupos.add(grupo);

            if (esPrimerTurno) {

                grupoPrimerTurno = grupo;

            }

            ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                    obtenerTableroDto(jugadorTurno.getNombre()),
                    true,
                    nombreJugador);

            fachadaTablero.enviarComando(comandoRespuestaMovimiento);
        }

    }

    public void agregarFichasTableroGrupos(Integer[] idsFichas, Integer[] idsFichasGrupo, String nombreJugador) {

        List<Ficha> fichasAgregar = new LinkedList<>();
        for (int id : idsFichas) {
            Ficha ficha = encontrarFichaPorId(id);
            fichasAgregar.add(ficha);
        }

        if (idsFichasGrupo.length == 2) {

            Grupo primerGrupoAgregar = null;
            if (idsFichasGrupo[0] != null) {
                primerGrupoAgregar = encontrarFichaPorId(idsFichasGrupo[0]).getGrupo();
            }

            Grupo segundoGrupoAgregar = null;
            if (idsFichasGrupo[1] != null) {
                segundoGrupoAgregar = encontrarFichaPorId(idsFichasGrupo[1]).getGrupo();
            }

            try {

                if (primerGrupoAgregar != null && segundoGrupoAgregar != null) {

                    // No se pueden agregar fichas a grupos si es el primer turno del jugador.
                    if (esPrimerTurnoJugador(nombreJugador)) {

                        ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                                obtenerTableroDto(jugadorTurno.getNombre()),
                                false,
                                jugadorTurno.getNombre(),
                                MENSAJE_AGREGAR_FICHA_GRUPO_PRIMER_TURNO);

                        fachadaTablero.enviarComando(comandoRespuestaMovimiento);

                        return;

                    }

                    // Se crea una lista con la combinación de todas las fichas.
                    List<Ficha> fichasFusionadas = new LinkedList<>();
                    fichasFusionadas.addAll(primerGrupoAgregar.getFichas());
                    fichasFusionadas.addAll(fichasAgregar);
                    fichasFusionadas.addAll(segundoGrupoAgregar.getFichas());

                    Grupo nuevoGrupoUnificado = FabricaGrupos.crearGrupo(
                        ++numeroGrupoActual,
                        fichasFusionadas,
                        esPrimerTurnoJugador(nombreJugador),
                        MAXIMO_NUMERO_FICHA);
                    
                   
                    if(nuevoGrupoUnificado == null){
                        ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                                obtenerTableroDto(jugadorTurno.getNombre()),
                                false,
                                jugadorTurno.getNombre(),
                                MENSAJE_GRUPO_INVALIDO);

                        fachadaTablero.enviarComando(comandoRespuestaMovimiento);
                        
                        return;
                    }

                    // Si el nuevo grupo es válido, se eliminan los grupos anteriores.
                    this.grupos.remove(primerGrupoAgregar);
                    this.grupos.remove(segundoGrupoAgregar);

                    // Se agrega el nuevo grupo.
                    this.grupos.add(nuevoGrupoUnificado);

                } // Agregar fichas sólo al primer grupo
                else if (primerGrupoAgregar != null) {

                    if (esPrimerTurnoJugador(nombreJugador) && primerGrupoAgregar.isPrimerTurno()) {

                        ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                                obtenerTableroDto(jugadorTurno.getNombre()),
                                false,
                                jugadorTurno.getNombre(),
                                MENSAJE_AGREGAR_FICHA_GRUPO_PRIMER_TURNO);

                        fachadaTablero.enviarComando(comandoRespuestaMovimiento);

                        return;

                    }

                    // Se crea una lista con la combinación de todas las fichas.
                    List<Ficha> fichasFusionadas = new LinkedList<>();
                    fichasFusionadas.addAll(primerGrupoAgregar.getFichas());
                    fichasFusionadas.addAll(fichasAgregar);

                    Grupo nuevoGrupoUnificado = FabricaGrupos.crearGrupo(
                        ++numeroGrupoActual,
                        fichasFusionadas,
                        esPrimerTurnoJugador(nombreJugador),
                        MAXIMO_NUMERO_FICHA);
                    
                    if(nuevoGrupoUnificado == null){
                        ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                                obtenerTableroDto(jugadorTurno.getNombre()),
                                false,
                                jugadorTurno.getNombre(),
                                MENSAJE_GRUPO_INVALIDO);

                        fachadaTablero.enviarComando(comandoRespuestaMovimiento);
                        
                        return;
                    }

                    // Si el nuevo grupo es válido, se eliminan el grupo existente.
                    this.grupos.remove(primerGrupoAgregar);

                    // Se agrega el nuevo grupo.
                    this.grupos.add(nuevoGrupoUnificado);
                    
                } // Agregar fichas sólo al segundo grupo
                else if (segundoGrupoAgregar != null) {

                    if (esPrimerTurnoJugador(nombreJugador) && segundoGrupoAgregar.isPrimerTurno()) {

                        ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                                obtenerTableroDto(jugadorTurno.getNombre()),
                                false,
                                jugadorTurno.getNombre(),
                                MENSAJE_AGREGAR_FICHA_GRUPO_PRIMER_TURNO);

                        fachadaTablero.enviarComando(comandoRespuestaMovimiento);

                        return;

                    }
                    
                    // Se crea una lista con la combinación de todas las fichas.
                    List<Ficha> fichasFusionadas = new LinkedList<>();
                    fichasFusionadas.addAll(fichasAgregar);
                    fichasFusionadas.addAll(segundoGrupoAgregar.getFichas());

                    Grupo nuevoGrupoUnificado = FabricaGrupos.crearGrupo(
                        ++numeroGrupoActual,
                        fichasFusionadas,
                        esPrimerTurnoJugador(nombreJugador),
                        MAXIMO_NUMERO_FICHA);
                    
                    if(nuevoGrupoUnificado == null){
                        ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                                obtenerTableroDto(jugadorTurno.getNombre()),
                                false,
                                jugadorTurno.getNombre(),
                                MENSAJE_GRUPO_INVALIDO);

                        fachadaTablero.enviarComando(comandoRespuestaMovimiento);
                        
                        return;
                    }

                    // Si el nuevo grupo es válido, se eliminan el grupo existente.
                    this.grupos.remove(segundoGrupoAgregar);

                    // Se agrega el nuevo grupo.
                    this.grupos.add(nuevoGrupoUnificado);

                }

                ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                        obtenerTableroDto(jugadorTurno.getNombre()),
                        true,
                        nombreJugador);

                fachadaTablero.enviarComando(comandoRespuestaMovimiento);

            } catch (RummyException ex) {

                ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                        obtenerTableroDto(jugadorTurno.getNombre()),
                        false,
                        nombreJugador,
                        ex.getMessage());

                fachadaTablero.enviarComando(comandoRespuestaMovimiento);
            }
        }
    }

    public void quitarFichasJugador(Integer[] idsFichas, String nombreJugador) {

        List<Ficha> fichasQuitar = new LinkedList<>();

        for (int idFicha : idsFichas) {

            fichasQuitar.add(encontrarFichaPorId(idFicha));

        }

        jugadorTurno.getFichas().removeAll(fichasQuitar);
        
        if(jugadorTurno.getFichas().size() == 0){
            
            for(Jugador jugador: jugadores){
                
                if(jugador.getNombre().equals(jugadorTurno.getNombre())){
                    
                    ComandoPartidaGanada comandoPartidaGanada = new ComandoPartidaGanada(
                            jugador.getNombre(), 
                            MENSAJE_PARTIDA_GANADA);
                    
                    fachadaTablero.enviarComando(comandoPartidaGanada);
                    
                    
                } else {
                    
                    ComandoJugadorPartidaGanada comandoJugadorPartidaGanada = new ComandoJugadorPartidaGanada(
                            jugador.getNombre(), 
                            jugador.getNombre() + MENSAJE_JUGADOR_PARTIDA_GANADA);
                    
                    fachadaTablero.enviarComando(comandoJugadorPartidaGanada);
                    
                    
                }
                
                ComandoFinPartida comandoFinPartida = new ComandoFinPartida(jugador.getNombre());
                
                fachadaTablero.enviarComando(comandoFinPartida);
                
            }
            
        } else{
            
            ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                obtenerTableroDto(jugadorTurno.getNombre()),
                true,
                nombreJugador);

            fachadaTablero.enviarComando(comandoRespuestaMovimiento);
        
        }

        

    }

    // Revisar
    public void quitarFichasTablero(Integer[] idsFichas, String nombreJugador) {
    
        if (idsFichas == null || idsFichas.length == 0){
            return;
        }

        // Validación de Turno
        if (esPrimerTurnoJugador(nombreJugador)) {
            ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                        obtenerTableroDto(jugadorTurno.getNombre()),
                        false,
                        nombreJugador,
                        MENSAJE_QUITAR_FICHA_GRUPO_PRIMER_TURNO);
            
            fachadaTablero.enviarComando(comandoRespuestaMovimiento);
            
            return;
        }

        // Se valida el movimiento en el grupo
        Grupo grupoObjetivo = null;
        List<Ficha> fichasQuitar = new ArrayList<>();

        for (int i = 0; i < idsFichas.length; i++) {
            int id = idsFichas[i];
            Ficha ficha = encontrarFichaPorId(id);

            Grupo grupoActual = ficha.getGrupo();

            if (i == 0) {
                grupoObjetivo = grupoActual;
            } 
            else if (!grupoActual.equals(grupoObjetivo)) {
                ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                        obtenerTableroDto(jugadorTurno.getNombre()),
                        false,
                        nombreJugador);
            
                fachadaTablero.enviarComando(comandoRespuestaMovimiento);
                return;
            
            }

            fichasQuitar.add(ficha);
        }

        List<List<Ficha>> subgruposResultantes = simularSeparacion(grupoObjetivo.getFichas(), fichasQuitar);

        try {
            for (List<Ficha> subgrupo : subgruposResultantes) {
                if (!subgrupo.isEmpty()) {

                    Grupo.validarCreacionGrupo(subgrupo, false, MAXIMO_NUMERO_FICHA);
                }
            }
        } catch (RummyException ex) {
            ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                        obtenerTableroDto(jugadorTurno.getNombre()),
                    false,
                    nombreJugador,
                    ex.getMessage());

            fachadaTablero.enviarComando(comandoRespuestaMovimiento);
            return;
        }
        
        
        this.grupos.remove(grupoObjetivo);

        if (!subgruposResultantes.isEmpty()) {

            for (List<Ficha> subgrupo: subgruposResultantes) {
                
                try {

                    Grupo nuevoGrupo = FabricaGrupos.crearGrupo(
                        ++numeroGrupoActual, 
                        subgrupo, 
                        false, 
                        MAXIMO_NUMERO_FICHA
                    );
                    
                    this.grupos.add(nuevoGrupo);

                } catch (RummyException ex) {

                }
            }
        }

        ComandoRespuestaMovimiento comandoExito = new ComandoRespuestaMovimiento(
                obtenerTableroDto(jugadorTurno.getNombre()), true, nombreJugador
        );
        fachadaTablero.enviarComando(comandoExito);
    }

    public List<List<Ficha>> simularSeparacion(List<Ficha> fichasOriginales, List<Ficha> fichasAQuitar) {
        List<List<Ficha>> subgrupos = new ArrayList<>();
        List<Ficha> bufferActual = new ArrayList<>();

        for (Ficha ficha: fichasOriginales) {
            // Se verifica si la ficha actual está en la lista de eliminación.
            boolean eliminar = fichasAQuitar.stream().anyMatch(fq -> fq.getId() == ficha.getId());

            if (eliminar) {
                // Se rompe la cadena. Si traíamos fichas en el buffer, guardamos ese fragmento.
                if (!bufferActual.isEmpty()) {
                    subgrupos.add(new LinkedList<>(bufferActual));
                    bufferActual.clear();
                }
            } else {
                // No se elimina, se agrega al buffer actual
                bufferActual.add(ficha);
            }
        }

        // Si al terminar el bucle quedó algo en el buffer, es el último fragmento.
        if (!bufferActual.isEmpty()) {
            subgrupos.add(new ArrayList<>(bufferActual));
        }

        return subgrupos;
    }
    
    public void terminarTurno(String nombreJugador) {

        if (validarTablero(nombreJugador)) {

            if (jugadorTurno.isPrimerTurno()) {
                jugadorTurno.setPrimerTurno(false);
            }

            if(grupoPrimerTurno != null){
                grupoPrimerTurno.setPrimerTurno(false);
                grupoPrimerTurno = null;
            }

            pasarSiguienteJugador();

            gruposInicialesTurno = new LinkedList<>();
            fichasInicialesJugadorTurno = new LinkedList<>();

            for (Grupo grupo : grupos) {

                List<Ficha> copiaFichas = new LinkedList<>(grupo.getFichas());

                try {
                    Grupo grupoCopia = FabricaGrupos.crearGrupo(
                            grupo.getNumero(),
                            copiaFichas,
                            false,
                            MAXIMO_NUMERO_FICHA);

                    gruposInicialesTurno.add(grupoCopia);

                } catch (RummyException ex) {

                }

            }
            
            fichaTomada = null;
            
            List<Ficha> copiafichasJugador = new LinkedList<>(jugadorTurno.getFichas());
            fichasInicialesJugadorTurno = copiafichasJugador;
            
            notificarTodosCambioTurno();

        } else {

            IComando comandoTableroInvalido = new ComandoTableroInvalido(
                    nombreJugador, 
                    MENSAJE_TABLERO_INVALIDO);

            fachadaTablero.enviarComando(comandoTableroInvalido);

        }

    }

    public void pasarSiguienteJugador() {

        int indice = jugadores.indexOf(jugadorTurno);
        if (indice == jugadores.size() - 1) {
            jugadorTurno = jugadores.get(0);
        } else {
            jugadorTurno = jugadores.get(indice + 1);
        }

    }

    public void notificarTodosCambioTurno() {

        for (Jugador jugador : jugadores) {
            if (!jugador.getNombre().equals(jugadorTurno.getNombre())) {

                ComandoCambioTurno comandoCambioTurno = new ComandoCambioTurno(
                        obtenerTableroDto(jugador.getNombre()),
                        jugador.getNombre(),
                        MENSAJE_CAMBIO_TURNO + jugadorTurno.getNombre());

                fachadaTablero.enviarComando(comandoCambioTurno);

            } else {

                ComandoIniciarTurno comandoIniciarTurno = new ComandoIniciarTurno(
                        obtenerTableroDto(jugador.getNombre()),
                        jugador.getNombre(),
                        MENSAJE_INICIO_TURNO);

                fachadaTablero.enviarComando(comandoIniciarTurno);

            }
        }
       
    }

    public void tomarFicha(String nombreJugador) {
  
        Ficha fichaSeleccionada = null;
        if (!monton.getFichasMonton().isEmpty() && fichaTomada == null) {

            Random rand = new Random();

            int indiceAleatorio = rand.nextInt(monton.getFichasMonton().size());

            fichaSeleccionada = monton.getFichasMonton().get(indiceAleatorio);

            monton.getFichasMonton().remove(fichaSeleccionada);
            
            fichaTomada = fichaSeleccionada;
            
            jugadorTurno.getFichas().add(fichaSeleccionada);

                ComandoRespuestaMovimiento comandoRespuestaMovimiento = new ComandoRespuestaMovimiento(
                obtenerTableroDto(jugadorTurno.getNombre()), 
                true, 
                nombreJugador
            );
            fachadaTablero.enviarComando(comandoRespuestaMovimiento);
            
            return;
            
        }

    }
    
    public void reestablecerTablero(String nombreJugador){
        
        grupos = new LinkedList<>(gruposInicialesTurno);
        
        jugadorTurno.setFichas(new LinkedList<>(fichasInicialesJugadorTurno));
        
        if(fichaTomada != null){
            jugadorTurno.getFichas().add(fichaTomada);
        }
        
        if(esPrimerTurnoJugador(nombreJugador)){
            
            grupoPrimerTurno = null;
            
        }
        
        ComandoRespuestaReestablecer comandoRespuestaReestablecer
                = new ComandoRespuestaReestablecer(
                        obtenerTableroDto(nombreJugador),
                        nombreJugador);
        
        fachadaTablero.enviarComando(comandoRespuestaReestablecer);
        
    }
    
    public void solicitarAbandono(String nombreJugador){
        
        if(nombreJugador.equals(jugadorTurno.getNombre())){
            
            ComandoRespuestaAbandonar comandoRespuestaConfirmacionAbandonar 
                    = new ComandoRespuestaAbandonar(jugadorTurno.getNombre(), MENSAJE_CONFIRMACION_ABANDONO_PARTIDA);
            
            fachadaTablero.enviarComando(comandoRespuestaConfirmacionAbandonar);
                
        }
        
    }

    public void confirmarAbandono(String nombreJugador, boolean confirmacion){
        
        if(confirmacion){
            
            if(nombreJugador.equals(jugadorTurno.getNombre())){
            
                Jugador jugadorRemover = jugadorTurno;

                monton.getFichasMonton().addAll(jugadorTurno.getFichas());

                reestablecerTablero(nombreJugador);
                
                ComandoFinPartida comandoFinPartida = new ComandoFinPartida(jugadorTurno.getNombre());

                fachadaTablero.enviarComando(comandoFinPartida);
                
                for(Jugador jugador: jugadores){
                    
                    if(!jugador.getNombre().equals(jugadorTurno.getNombre())){
                        
                        ComandoJugadorAbandonoPartida comandoJugadorAbandonoPartida 
                                = new ComandoJugadorAbandonoPartida(
                                        jugador.getNombre(), 
                                        nombreJugador + MENSAJE_JUGADOR_ABANDONO_PARTIDA);
                        
                        fachadaTablero.enviarComando(comandoJugadorAbandonoPartida);
                    }
                     
                }

                jugadores.remove(jugadorRemover);
                
                if(jugadores.size() > 1){
                    terminarTurno(nombreJugador);
                } else{
                    
                    ComandoPartidaGanada comandoPartidaGanada = new ComandoPartidaGanada(
                            jugadores.get(0).getNombre(), 
                            MENSAJE_PARTIDA_GANADA);
                    
                    fachadaTablero.enviarComando(comandoPartidaGanada);
                    
                    ComandoFinPartida comandoFinPartidaJugadorUnico 
                            = new ComandoFinPartida(jugadores.get(0).getNombre());
                    
                    fachadaTablero.enviarComando(comandoFinPartidaJugadorUnico);
                    
                }
                
            }

        }
        
        
    }
    
    public void solicitarFin(String nombreJugador){
        
        if(nombreJugador.equals(jugadorTurno.getNombre())){
            
            ComandoRespuestaSolicitarFin comandoRespuestaSolicitarFin 
                = new ComandoRespuestaSolicitarFin(
                        nombreJugador,
                        MENSAJE_CONFIRMACION_FIN_PARTIDA);
            
            fachadaTablero.enviarComando(comandoRespuestaSolicitarFin);
            
        } else{
            
            if(!nombresJugadoresSolicitanFin.isEmpty() 
                    && nombresJugadoresSolicitanFin.size() == 1
                    && nombresJugadoresSolicitanFin.get(0).equals(jugadorTurno.getNombre())){
                
                ComandoRespuestaSolicitarFin comandoRespuestaSolicitarFin 
                = new ComandoRespuestaSolicitarFin(
                        nombreJugador,
                        jugadorTurno.getNombre() + MENSAJE_SOLICITUD_FIN_PARTIDA_JUGADOR);
            
                fachadaTablero.enviarComando(comandoRespuestaSolicitarFin);
                
            }
            
            
        }
        
    }
    
    public void confirmarSolicitarFin(String nombreJugador, boolean confirmacion){
        
        if(!confirmacion && !nombresJugadoresSolicitanFin.isEmpty()){
            
            nombresJugadoresSolicitanFin = new LinkedList<>();
            
            for(Jugador jugador: jugadores){

                ComandoRespuestaConfirmacionSolicitarFin comandoRespuestaConfirmacionSolicitarFin 
                    = new ComandoRespuestaConfirmacionSolicitarFin(
                            jugador.getNombre(), 
                            MENSAJE_SOLICITUD_FIN_PARTIDA_CANCELADO);
            
                fachadaTablero.enviarComando(comandoRespuestaConfirmacionSolicitarFin);

            }
            
            nombresJugadoresSolicitanFin = new LinkedList<>();
            
        } else if(confirmacion){
            
            nombresJugadoresSolicitanFin.add(nombreJugador);

            if(nombresJugadoresSolicitanFin.size() == 1){

                for(Jugador jugador: jugadores){

                    if(!jugador.getNombre().equals(nombresJugadoresSolicitanFin.get(0))){
                        ComandoRespuestaSolicitarFin comandoRespuestaSolicitarFin 
                        = new ComandoRespuestaSolicitarFin(
                                jugador.getNombre(),
                                jugadorTurno.getNombre() + MENSAJE_SOLICITUD_FIN_PARTIDA_JUGADOR);

                        fachadaTablero.enviarComando(comandoRespuestaSolicitarFin);
                    }

                }

            } else if(nombresJugadoresSolicitanFin.size() == jugadores.size()){
                
                for(Jugador jugador: jugadores){
                    
                    ComandoRespuestaConfirmacionSolicitarFin comandoRespuestaConfirmacionSolicitarFin 
                            = new ComandoRespuestaConfirmacionSolicitarFin(
                                    jugador.getNombre(), 
                                    MENSAJE_SOLICITUD_FIN_PARTIDA_ACEPTADA);
                    
                    fachadaTablero.enviarComando(comandoRespuestaConfirmacionSolicitarFin);
                    
                    ComandoFinPartida comandoFinPartida = new ComandoFinPartida(jugador.getNombre());
                    
                    fachadaTablero.enviarComando(comandoFinPartida);
                    
                }
                
            } else {
                ComandoRespuestaConfirmacionSolicitarFin comandoRespuestaConfirmacionSolicitarFin 
                = new ComandoRespuestaConfirmacionSolicitarFin(
                        nombreJugador,
                        MENSAJE_ESPERANDO_CONFIRMACION_JUGADORES);

                fachadaTablero.enviarComando(comandoRespuestaConfirmacionSolicitarFin);
            }

        }
        
    }
    
    public boolean esPrimerTurnoJugador(String nombreJugador) {

        String nombreJugadorTurno = jugadorTurno.getNombre();

        return jugadorTurno.isPrimerTurno();

    }

    public TableroDTO obtenerTableroDto(String nombreJugador) {

        JugadorDTO jugadorDto = null;

        List<JugadorDTO> otrosJugadoresDto = new LinkedList<>();

        for (Jugador jugador : jugadores) {

            if (!jugador.getNombre().equals(nombreJugador)) {

                List<FichaDTO> listaFichasOtroJugadorDto = new LinkedList<>();

                for (int i = 0; i < jugador.getFichas().size(); i++) {

                    listaFichasOtroJugadorDto.add(null);

                }

                JugadorDTO otroJugadorDto = new JugadorDTO(
                        listaFichasOtroJugadorDto,
                        jugador.getAvatar(),
                        jugador.getNombre());

                otrosJugadoresDto.add(otroJugadorDto);

            } else {

                List<FichaDTO> listaFichasJugadorDto = new LinkedList<>();

                for (Ficha ficha : jugador.getFichas()) {

                    listaFichasJugadorDto.add(obtenerFichaDto(ficha));

                }

                jugadorDto = new JugadorDTO(
                        listaFichasJugadorDto,
                        jugador.getAvatar(),
                        jugador.getNombre());

            }

        }

        List<GrupoDTO> gruposDTO = new LinkedList<>();

        for (Grupo grupo : grupos) {

            gruposDTO.add(obtenerGrupoDto(grupo));

        }

        MontonDTO montonDto = obtenerMontoDTO(monton);

        return new TableroDTO(
                gruposDTO.toArray(new GrupoDTO[0]),
                montonDto,
                jugadorDto,
                otrosJugadoresDto.toArray(new JugadorDTO[0]));

    }

    public GrupoDTO obtenerGrupoDto(Grupo grupo) {

        List<FichaDTO> listaFichasDto = new LinkedList<>();

        for (Ficha ficha : grupo.getFichas()) {

            listaFichasDto.add(obtenerFichaDto(ficha));

        }

        return new GrupoDTO(listaFichasDto);

    }

    public FichaDTO obtenerFichaDto(Ficha ficha) {

        ColorFichaDTO colorFichaDto = null;

        switch (ficha.getColor()) {

            case ColorFicha.COLOR_A:

                colorFichaDto = ColorFichaDTO.COLOR_A;

                break;

            case ColorFicha.COLOR_B:

                colorFichaDto = ColorFichaDTO.COLOR_B;

                break;

            case ColorFicha.COLOR_C:

                colorFichaDto = ColorFichaDTO.COLOR_C;

                break;

            case ColorFicha.COLOR_D:

                colorFichaDto = ColorFichaDTO.COLOR_D;

                break;

            case ColorFicha.COLOR_COMODIN:

                colorFichaDto = ColorFichaDTO.COLOR_COMODIN;

                break;

            default:
                throw new AssertionError();
        }

        if (ficha instanceof FichaComodin) {

            String valorComodin = ((FichaComodin) ficha).getValor();
            return new FichaComodinDTO(colorFichaDto, ficha.getId(), valorComodin, "Comodin");

        } else if (ficha instanceof FichaNormal) {

            int numeroFicha = ((FichaNormal) ficha).getNumero();
            return new FichaNormalDTO(colorFichaDto, ficha.getId(), numeroFicha, "Normal");

        }

        return null;

    }

    public MontonDTO obtenerMontoDTO(Monton monton) {

        return new MontonDTO(monton.getCantidadFichas());

    }

    public Ficha encontrarFichaPorId(int idFicha) {

        for (Ficha ficha : fichas) {

            if (ficha.getId() == idFicha) {
                return ficha;
            }
        }
        return null;
    }

    public boolean validarTablero(String nombreJugador) {

        boolean esPrimerTurno = esPrimerTurnoJugador(nombreJugador);
        
        for (Grupo grupo : grupos) {
            if (!grupo.comprobarValidez()) {
                return false;
            }
        }
        return true;
    }

    public void setFachada(FachadaObjetosNegocio fachadaTablero) {
        this.fachadaTablero = fachadaTablero;
    }

}
