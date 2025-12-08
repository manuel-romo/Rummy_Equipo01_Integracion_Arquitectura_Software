
package fachada;

import comandosRespuesta.ComandoCambioTurno;
import comandosRespuesta.ComandoCargarJugadores;
import comandosRespuesta.ComandoFinPartida;
import comandosRespuesta.ComandoIniciarTurno;
import comandosRespuesta.ComandoJugadorAbandonoPartida;
import comandosRespuesta.ComandoJugadorPartidaGanada;
import comandosRespuesta.ComandoPartidaGanada;
import comandosRespuesta.ComandoRespuestaAbandonar;
import comandosRespuesta.ComandoDecisionIniciarJuego;
import comandosRespuesta.ComandoNuevaSolicitudIniciarJuego;
import comandosRespuesta.ComandoActualizarJugadoresInicioJuego;
import comandosRespuesta.ComandoRespuestaConfirmacionSolicitarFin;
import comandosRespuesta.ComandoRespuestaIniciarJuego;
import comandosRespuesta.ComandoRespuestaMovimiento;
import comandosRespuesta.ComandoRespuestaReestablecer;
import comandosRespuesta.ComandoRespuestaSolicitarFin;
import comandosRespuesta.ComandoRespuestaTomarFicha;
import comandosRespuesta.ComandoTableroInvalido;
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
import ejercerturno.modelo.ModeloEjercerTurno;
import iniciarpartida.modelo.ModeloInicioPartida;
import interfaces.ICommand;
import interfaces.IFiltro;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class FachadaMvc implements IFiltro{

    private ModeloEjercerTurno modeloEjercerTurno;
    private ModeloInicioPartida modeloInicioPartida;
    
    private IFiltro filtroSiguiente;
    
    
    
    // Métodos de MVC Inicio partida.
    public void solicitarInicioJuego(String nombreJugador){
        
        ComandoIniciarJuego comandoIniciarJuego = new ComandoIniciarJuego(nombreJugador);
        
        filtroSiguiente.ejecutar(comandoIniciarJuego);
        
    }
    
    public void confirmarEnvioSolicitudInicioJuego(String nombreJugador, boolean confirmacion){
     
        ComandoConfirmacionEnvioIniciarJuego comandoConfirmacionEnvioIniciarJuego 
                = new ComandoConfirmacionEnvioIniciarJuego(nombreJugador, confirmacion);
        
        filtroSiguiente.ejecutar(comandoConfirmacionEnvioIniciarJuego);
    }
    
    public void confirmarInicioJuego(String nombreJugador, boolean confirmacion){
        
        ComandoConfirmacionIniciarJuego comandoConfirmacionIniciarJuego = new ComandoConfirmacionIniciarJuego(nombreJugador, confirmacion);
        
        filtroSiguiente.ejecutar(comandoConfirmacionIniciarJuego);
        
    }
    
    // Métodos de MVC Ejercer turno.
    
    
    public void seleccionarFichasTablero(Integer[] posicionesFichas, String nombreJugador) {
        
        ICommand comandoSeleccionarFichasTablero = new ComandoSeleccionarFichasTablero(posicionesFichas, nombreJugador);
        
        filtroSiguiente.ejecutar(comandoSeleccionarFichasTablero);
        
    }
    
    public void quitarFichasJugador(Integer[] posicionesFichas, String nombreJugador) {
        
        ICommand comandoQuitarFichasJugador = new ComandoQuitarFichasJugador(posicionesFichas, nombreJugador);
        
        filtroSiguiente.ejecutar(comandoQuitarFichasJugador);
        
    }
    
    public void quitarFichasTablero(Integer[] idsFichas, String nombreJugador) {
        
        ICommand comandoQuitarFichasTablero = new ComandoQuitarFichasTablero(idsFichas, nombreJugador);
        
        filtroSiguiente.ejecutar(comandoQuitarFichasTablero);

    }
    
    public void agregarFichasJugador(Integer[] idsFichas, String nombreJugador) {
        
        ICommand comandoAgregarFichasJugador = new ComandoAgregarFichasJugador(idsFichas, nombreJugador);
        
        filtroSiguiente.ejecutar(comandoAgregarFichasJugador);
        
    }
    
    public void agregarFichasTablero(Integer[] idsFichas, Integer[] idsFichasGrupo, String nombreJugador) {
        
        ICommand comandoAgregarFichasTableroGrupo = new ComandoAgregarFichasTableroGrupo(idsFichas, idsFichasGrupo, nombreJugador);
        
        filtroSiguiente.ejecutar(comandoAgregarFichasTableroGrupo);
        
    }
    
     public void agregarFichasTablero(Integer[] idsFichas, String nombreJugador) {
        
        ICommand comandoAgregarFichasTablero = new ComandoAgregarFichasTablero(idsFichas, nombreJugador);
        
        filtroSiguiente.ejecutar(comandoAgregarFichasTablero);
        
    }
     
     public void tomarFicha(String nombreJugador){
        
        ICommand comandoTomarFicha = new ComandoTomarFicha(nombreJugador);
        
        filtroSiguiente.ejecutar(comandoTomarFicha);
        
    }
     
    public void reestablecerTablero(String nombreJugador){
        
        ICommand comandoReestablecerTablero = new ComandoReestablecerTablero(nombreJugador);
        
        filtroSiguiente.ejecutar(comandoReestablecerTablero);
    }
     
    public void terminarTurno(String nombreJugador) {
        
        ICommand comandoFinalizarTurno = new ComandoTerminarTurno(nombreJugador);
        filtroSiguiente.ejecutar(comandoFinalizarTurno);

    }
     
    public void abandonarPartida(String nombreJugador){
        
        ComandoAbandonar comandoAbandonar = new ComandoAbandonar(nombreJugador);
        filtroSiguiente.ejecutar(comandoAbandonar);
        
    }
    
    public void finalizarPartida(String nombreJugador){
        
        ComandoSolicitarFin comandoSolicitarFin = new ComandoSolicitarFin(nombreJugador);
        
        filtroSiguiente.ejecutar(comandoSolicitarFin);
        
    }
    
    public void confirmarAbandonoPartida(boolean confirmacion, String nombreJugador){
        
        ComandoConfirmacionAbandonar comandoConfirmacionAbandonar 
                = new ComandoConfirmacionAbandonar(nombreJugador, confirmacion);
        
        filtroSiguiente.ejecutar(comandoConfirmacionAbandonar);
        
    }
    
    public void confirmarSolicitudFin(boolean confirmacion, String nombreJugador){
        
        ComandoConfirmacionSolicitarFin comandoConfirmacionSolicitarFin 
                = new ComandoConfirmacionSolicitarFin(nombreJugador, confirmacion);
        
        filtroSiguiente.ejecutar(comandoConfirmacionSolicitarFin);
        
    }
    
    
    @Override
    public void ejecutar(ICommand comando) {

        
        CommandType tipoComando = CommandType.fromNombre(comando.getType());
        
        switch (tipoComando) {
            
            // Comandos MVC Ejercer Turno
            case CommandType.INICIAR_TURNO:
                
                ComandoIniciarTurno comandoIniciarTurno = (ComandoIniciarTurno) comando;
                modeloEjercerTurno.iniciarTurno( 
                        comandoIniciarTurno.getTablero(), 
                        comandoIniciarTurno.getMensaje());
                
                break;
                
            case CommandType.CAMBIO_TURNO:
                
                ComandoCambioTurno comandoCambioTurno = (ComandoCambioTurno) comando;
                modeloEjercerTurno.cambiarTurno(
                        comandoCambioTurno.getTablero(),
                        comandoCambioTurno.getMensaje());

                break;
                
            case CommandType.RESPUESTA_MOVIMIENTO:
                
                ComandoRespuestaMovimiento comandoRespuestaMovimiento = (ComandoRespuestaMovimiento) comando;
                
                modeloEjercerTurno.responderMovimiento(
                        comandoRespuestaMovimiento.getTablero(), 
                        comandoRespuestaMovimiento.isMovimientoValido(),
                        comandoRespuestaMovimiento.getMensaje());
                
                break;
                
            case CommandType.COMANDO_TABLERO_INVALIDO:
                
                ComandoTableroInvalido comandoTableroInvalido = (ComandoTableroInvalido) comando;
                
                String mensaje = comandoTableroInvalido.getMensaje();
                
                modeloEjercerTurno.avisarTableroInvalido(mensaje);
                
                break;
                
            case CommandType.RESPUESTA_TOMAR_FICHA:
                
                ComandoRespuestaTomarFicha comandoRespuestaTomarFicha = (ComandoRespuestaTomarFicha) comando;
                modeloEjercerTurno.tomarFicha(
                        comandoRespuestaTomarFicha.getTablero());

                break;
                
            case CommandType.RESPUESTA_REESTABLECER:
                
                ComandoRespuestaReestablecer comandoRespuestaReestablecer = (ComandoRespuestaReestablecer) comando;
                modeloEjercerTurno.reestablecerTablero(
                        comandoRespuestaReestablecer.getTablero());

                break;
                
                
            case CommandType.COMANDO_RESPUESTA_ABANDONAR:
                
                ComandoRespuestaAbandonar comandoRespuestaAbandonar = (ComandoRespuestaAbandonar) comando;
                
                modeloEjercerTurno.responderSolicitudAbandono(comandoRespuestaAbandonar.getMensaje());
                
                break;
                 
            case CommandType.COMANDO_JUGADOR_ABANDONO:
                
                ComandoJugadorAbandonoPartida comandoJugadorAbandonoPartida = (ComandoJugadorAbandonoPartida) comando;
                
                modeloEjercerTurno.notificarAbandonoJugador(
                        comandoJugadorAbandonoPartida.getMensaje());
                
                break;
                
            case CommandType.COMANDO_RESPUESTA_SOLICITAR_FIN:
                
                ComandoRespuestaSolicitarFin comandoRespuestaSolicitarFin = (ComandoRespuestaSolicitarFin) comando;
                
                comandoRespuestaSolicitarFin.getMensaje();
                
                modeloEjercerTurno.responderSolicitudFin(comandoRespuestaSolicitarFin.getMensaje());
                
                break;
                
            case CommandType.COMANDO_RESPUESTA_CONFIRMACION_SOLICITAR_FIN:
                
                ComandoRespuestaConfirmacionSolicitarFin comandoRespuestaConfirmacionSolicitarFin = (ComandoRespuestaConfirmacionSolicitarFin) comando;
                
                comandoRespuestaConfirmacionSolicitarFin.getMensaje();
                
                modeloEjercerTurno.responderConfirmacionSolicitudFin(comandoRespuestaConfirmacionSolicitarFin.getMensaje());
                
                break;
                
            case CommandType.COMANDO_FIN_PARTIDA:
                
                ComandoFinPartida comandoFinPartida = (ComandoFinPartida) comando;
                
                modeloEjercerTurno.terminarJuego();
                
                break;
                
            case CommandType.COMANDO_PARTIDA_GANADA:
                
                ComandoPartidaGanada comandoPartidaGanada = (ComandoPartidaGanada) comando;
                
                modeloEjercerTurno.notificarPartidaGanada(comandoPartidaGanada.getMensaje());
                 
                break;
                
            case CommandType.COMANDO_JUGADOR_PARTIDA_GANADA:
                
                ComandoJugadorPartidaGanada comandoJugadorPartidaGanada = (ComandoJugadorPartidaGanada) comando;
                
                modeloEjercerTurno.notificarJugadorPartidaGanada(comandoJugadorPartidaGanada.getMensaje());
                 
                break;

            
            // Comandos MVC InicioPartida
                
            case CommandType.COMANDO_CARGAR_JUGADORES:
                
                ComandoCargarJugadores comandoCargarJugadores = (ComandoCargarJugadores) comando;
                
                modeloInicioPartida.cargarJugadores(comandoCargarJugadores.getJugadores());
                
                 
                break;
                
            case CommandType.COMANDO_NUEVA_SOLICITUD_INICIAR_JUEGO:
                
                ComandoNuevaSolicitudIniciarJuego comandoNuevaSolicitudIniciarJuego = (ComandoNuevaSolicitudIniciarJuego) comando;
                
                modeloInicioPartida.notificarNuevaSolicitudIniciarJuego(comandoNuevaSolicitudIniciarJuego.getMensaje());
                
                 
                break;
                
            case CommandType.COMANDO_ACTUALIZAR_JUGADORES_INICIO_JUEGO:
                
                ComandoActualizarJugadoresInicioJuego comandoActualizarJugadoresInicioJuego = (ComandoActualizarJugadoresInicioJuego) comando;
                
                modeloInicioPartida.notificarActualizacionJugadoresIniciarJuego(
                        comandoActualizarJugadoresInicioJuego.getCantidadJugadoresInicioJuego());
                 
                break;
                
            case CommandType.COMANDO_RESPUESTA_INICIAR_JUEGO:
                
                ComandoRespuestaIniciarJuego comandoRespuestaIniciarJuego = (ComandoRespuestaIniciarJuego) comando;
                
                modeloInicioPartida.notificarRespuestaIniciarJuego(comandoRespuestaIniciarJuego.getMensaje());
                 
                break;
                
            case CommandType.COMANDO_DECISION_INICIAR_JUEGO:
                
                ComandoDecisionIniciarJuego comandoDecisionIniciarJuego = (ComandoDecisionIniciarJuego) comando;
                
                modeloInicioPartida.notificarDecisionIniciarJuego(
                        comandoDecisionIniciarJuego.getMensaje(), 
                        comandoDecisionIniciarJuego.isDecision());
                 
                break;
                
  
            default:
                throw new AssertionError();
        }
        
    }

    public void setModeloEjercerTurno(ModeloEjercerTurno modeloEjercerTurno) {
        this.modeloEjercerTurno = modeloEjercerTurno;
    }

    public void setModeloInicioPartida(ModeloInicioPartida modeloInicioPartida) {
        this.modeloInicioPartida = modeloInicioPartida;
    }

    public void setFiltroSiguiente(IFiltro filtroSiguiente) {
        this.filtroSiguiente = filtroSiguiente;
    }
    
}
