package fachada;

import comandos.respuesta.ComandoCambioTurno;
import comandos.respuesta.ComandoCargarJugadores;
import comandos.respuesta.ComandoFinPartida;
import comandos.respuesta.ComandoIniciarTurno;
import comandos.respuesta.ComandoJugadorAbandonoPartida;
import comandos.respuesta.ComandoJugadorPartidaGanada;
import comandos.respuesta.ComandoPartidaGanada;
import comandos.respuesta.ComandoRespuestaAbandonar;
import comandos.respuesta.ComandoDecisionIniciarJuego;
import comandos.respuesta.ComandoNuevaSolicitudIniciarJuego;
import comandos.respuesta.ComandoActualizarJugadoresInicioJuego;
import comandos.respuesta.ComandoPartidaConfigurada;
import comandos.respuesta.ComandoRegistroExitoso;
import comandos.respuesta.ComandoRegistroFallido;
import comandos.respuesta.ComandoRespuestaConfirmacionSolicitarFin;
import comandos.respuesta.ComandoRespuestaIniciarJuego;
import comandos.respuesta.ComandoRespuestaMovimiento;
import comandos.respuesta.ComandoRespuestaReestablecer;
import comandos.respuesta.ComandoRespuestaSolicitarFin;
import comandos.respuesta.ComandoRespuestaTomarFicha;
import comandos.respuesta.ComandoTableroInvalido;
import comandos.solicitud.ComandoAbandonar;
import comandos.solicitud.ComandoAgregarFichasJugador;
import comandos.solicitud.ComandoAgregarFichasTablero;
import comandos.solicitud.ComandoAgregarFichasTableroGrupo;
import comandos.solicitud.ComandoConfigurarPartida;
import comandos.solicitud.ComandoConfirmacionAbandonar;
import comandos.solicitud.ComandoConfirmacionEnvioIniciarJuego;
import comandos.solicitud.ComandoConfirmacionIniciarJuego;
import comandos.solicitud.ComandoConfirmacionSolicitarFin;
import comandos.solicitud.ComandoIniciarJuego;
import comandos.solicitud.ComandoQuitarFichasJugador;
import comandos.solicitud.ComandoQuitarFichasTablero;
import comandos.solicitud.ComandoReestablecerTablero;
import comandos.solicitud.ComandoRegistrarJugador;
import comandos.solicitud.ComandoSeleccionarFichasTablero;
import comandos.solicitud.ComandoSolicitarFin;
import comandos.solicitud.ComandoTerminarTurno;
import comandos.solicitud.ComandoTomarFicha;
import enumeradores.TipoComando;
import ejercerturno.modelo.ModeloEjercerTurno;
import iniciarpartida.modelo.ModeloInicioPartida;
import interfaces.IFiltro;
import interfaces.IComando;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 *
 */
public class FachadaMvc implements IFiltro {

    private ModeloEjercerTurno modeloEjercerTurno;
    private ModeloInicioPartida modeloInicioPartida;

    private IFiltro filtroSiguiente;

    // Métodos de MVC Inicio partida.

    public void enviarRegistroJugador(String nombreJugador, String avatar) {
        ComandoRegistrarJugador comandoRegistro = new ComandoRegistrarJugador(nombreJugador, avatar);
        filtroSiguiente.ejecutar(comandoRegistro);
    }

    public void enviarDatosPartidaConfigurada(String nombreJugador, int maximoNumeroFichas, int numeroComodines, String ip, String puerto) {
        ComandoConfigurarPartida comandoConfigurarPartida = new ComandoConfigurarPartida(nombreJugador, maximoNumeroFichas, numeroComodines, ip, puerto);
        filtroSiguiente.ejecutar(comandoConfigurarPartida);
    }
    
    
    public void solicitarInicioJuego() {

        ComandoIniciarJuego comandoIniciarJuego = new ComandoIniciarJuego();

        filtroSiguiente.ejecutar(comandoIniciarJuego);

    }

    public void confirmarEnvioSolicitudInicioJuego(String nombreJugador, boolean confirmacion) {

        ComandoConfirmacionEnvioIniciarJuego comandoConfirmacionEnvioIniciarJuego
                = new ComandoConfirmacionEnvioIniciarJuego(nombreJugador,confirmacion);

        filtroSiguiente.ejecutar(comandoConfirmacionEnvioIniciarJuego);
    }

    public void confirmarInicioJuego(String nombreJugador, boolean confirmacion) {

        ComandoConfirmacionIniciarJuego comandoConfirmacionIniciarJuego = new ComandoConfirmacionIniciarJuego(nombreJugador, confirmacion);

        filtroSiguiente.ejecutar(comandoConfirmacionIniciarJuego);

    }

    // Métodos de MVC Ejercer turno.
    public void seleccionarFichasTablero(Integer[] posicionesFichas, String nombreJugador) {

        IComando comandoSeleccionarFichasTablero = new ComandoSeleccionarFichasTablero(posicionesFichas, nombreJugador);

        filtroSiguiente.ejecutar(comandoSeleccionarFichasTablero);
        
    }

    public void quitarFichasJugador(Integer[] posicionesFichas, String nombreJugador) {

        IComando comandoQuitarFichasJugador = new ComandoQuitarFichasJugador(posicionesFichas, nombreJugador);

        filtroSiguiente.ejecutar(comandoQuitarFichasJugador);

    }

    public void quitarFichasTablero(Integer[] idsFichas, String nombreJugador) {

        IComando comandoQuitarFichasTablero = new ComandoQuitarFichasTablero(idsFichas, nombreJugador);

        filtroSiguiente.ejecutar(comandoQuitarFichasTablero);

    }

    public void agregarFichasJugador(Integer[] idsFichas, String nombreJugador) {

        IComando comandoAgregarFichasJugador = new ComandoAgregarFichasJugador(idsFichas, nombreJugador);

        filtroSiguiente.ejecutar(comandoAgregarFichasJugador);

    }

    public void agregarFichasTablero(Integer[] idsFichas, Integer[] idsFichasGrupo, String nombreJugador) {

        IComando comandoAgregarFichasTableroGrupo = new ComandoAgregarFichasTableroGrupo(idsFichas, idsFichasGrupo, nombreJugador);

        filtroSiguiente.ejecutar(comandoAgregarFichasTableroGrupo);

    }

    public void agregarFichasTablero(Integer[] idsFichas, String nombreJugador) {

        IComando comandoAgregarFichasTablero = new ComandoAgregarFichasTablero(idsFichas, nombreJugador);

        filtroSiguiente.ejecutar(comandoAgregarFichasTablero);

    }

    public void tomarFicha(String nombreJugador) {

        IComando comandoTomarFicha = new ComandoTomarFicha(nombreJugador);

        filtroSiguiente.ejecutar(comandoTomarFicha);

    }

    public void reestablecerTablero(String nombreJugador) {

        IComando comandoReestablecerTablero = new ComandoReestablecerTablero(nombreJugador);

        filtroSiguiente.ejecutar(comandoReestablecerTablero);
    }

    public void terminarTurno(String nombreJugador) {

        IComando comandoFinalizarTurno = new ComandoTerminarTurno(nombreJugador);
        filtroSiguiente.ejecutar(comandoFinalizarTurno);

    }

    public void abandonarPartida(String nombreJugador) {

        ComandoAbandonar comandoAbandonar = new ComandoAbandonar(nombreJugador);
        filtroSiguiente.ejecutar(comandoAbandonar);

    }

    public void finalizarPartida(String nombreJugador) {

        ComandoSolicitarFin comandoSolicitarFin = new ComandoSolicitarFin(nombreJugador);

        filtroSiguiente.ejecutar(comandoSolicitarFin);

    }

    public void confirmarAbandonoPartida(boolean confirmacion, String nombreJugador) {

        ComandoConfirmacionAbandonar comandoConfirmacionAbandonar
                = new ComandoConfirmacionAbandonar(nombreJugador, confirmacion);

        filtroSiguiente.ejecutar(comandoConfirmacionAbandonar);

    }

    public void confirmarSolicitudFin(boolean confirmacion, String nombreJugador) {

        ComandoConfirmacionSolicitarFin comandoConfirmacionSolicitarFin
                = new ComandoConfirmacionSolicitarFin(nombreJugador, confirmacion);

        filtroSiguiente.ejecutar(comandoConfirmacionSolicitarFin);

    }

    @Override
    public void ejecutar(IComando comando) {

        TipoComando tipoComando = TipoComando.fromNombre(comando.getTipo());

        switch (tipoComando) {

            // Comandos MVC Ejercer Turno
            case TipoComando.INICIAR_TURNO:

                ComandoIniciarTurno comandoIniciarTurno = (ComandoIniciarTurno) comando;
                modeloEjercerTurno.iniciarTurno(
                        comandoIniciarTurno.getTablero(),
                        comandoIniciarTurno.getMensaje());

                break;

            case TipoComando.CAMBIO_TURNO:

                ComandoCambioTurno comandoCambioTurno = (ComandoCambioTurno) comando;
                modeloEjercerTurno.cambiarTurno(
                        comandoCambioTurno.getTablero(),
                        comandoCambioTurno.getMensaje());

                break;

            case TipoComando.RESPUESTA_MOVIMIENTO:

                ComandoRespuestaMovimiento comandoRespuestaMovimiento = (ComandoRespuestaMovimiento) comando;

                modeloEjercerTurno.responderMovimiento(
                        comandoRespuestaMovimiento.getTablero(),
                        comandoRespuestaMovimiento.isMovimientoValido(),
                        comandoRespuestaMovimiento.getMensaje());

                break;

            case TipoComando.COMANDO_TABLERO_INVALIDO:

                ComandoTableroInvalido comandoTableroInvalido = (ComandoTableroInvalido) comando;

                String mensaje = comandoTableroInvalido.getMensaje();

                modeloEjercerTurno.avisarTableroInvalido(mensaje);

                break;

            case TipoComando.RESPUESTA_TOMAR_FICHA:

                ComandoRespuestaTomarFicha comandoRespuestaTomarFicha = (ComandoRespuestaTomarFicha) comando;
                modeloEjercerTurno.tomarFicha(
                        comandoRespuestaTomarFicha.getTablero());

                break;

            case TipoComando.RESPUESTA_REESTABLECER:

                ComandoRespuestaReestablecer comandoRespuestaReestablecer = (ComandoRespuestaReestablecer) comando;
                modeloEjercerTurno.reestablecerTablero(
                        comandoRespuestaReestablecer.getTablero());

                break;

            case TipoComando.COMANDO_RESPUESTA_ABANDONAR:

                ComandoRespuestaAbandonar comandoRespuestaAbandonar = (ComandoRespuestaAbandonar) comando;

                modeloEjercerTurno.responderSolicitudAbandono(comandoRespuestaAbandonar.getMensaje());

                break;

            case TipoComando.COMANDO_JUGADOR_ABANDONO:

                ComandoJugadorAbandonoPartida comandoJugadorAbandonoPartida = (ComandoJugadorAbandonoPartida) comando;

                modeloEjercerTurno.notificarAbandonoJugador(
                        comandoJugadorAbandonoPartida.getMensaje());

                break;

            case TipoComando.COMANDO_RESPUESTA_SOLICITAR_FIN:

                ComandoRespuestaSolicitarFin comandoRespuestaSolicitarFin = (ComandoRespuestaSolicitarFin) comando;

                comandoRespuestaSolicitarFin.getMensaje();

                modeloEjercerTurno.responderSolicitudFin(comandoRespuestaSolicitarFin.getMensaje());

                break;

            case TipoComando.COMANDO_RESPUESTA_CONFIRMACION_SOLICITAR_FIN:

                ComandoRespuestaConfirmacionSolicitarFin comandoRespuestaConfirmacionSolicitarFin = (ComandoRespuestaConfirmacionSolicitarFin) comando;

                comandoRespuestaConfirmacionSolicitarFin.getMensaje();

                modeloEjercerTurno.responderConfirmacionSolicitudFin(comandoRespuestaConfirmacionSolicitarFin.getMensaje());

                break;

            case TipoComando.COMANDO_FIN_PARTIDA:

                ComandoFinPartida comandoFinPartida = (ComandoFinPartida) comando;

                modeloEjercerTurno.terminarJuego();

                break;

            case TipoComando.COMANDO_PARTIDA_GANADA:

                ComandoPartidaGanada comandoPartidaGanada = (ComandoPartidaGanada) comando;

                modeloEjercerTurno.notificarPartidaGanada(comandoPartidaGanada.getMensaje());

                break;

            case TipoComando.COMANDO_JUGADOR_PARTIDA_GANADA:

                ComandoJugadorPartidaGanada comandoJugadorPartidaGanada = (ComandoJugadorPartidaGanada) comando;

                modeloEjercerTurno.notificarJugadorPartidaGanada(comandoJugadorPartidaGanada.getMensaje());

                break;

            // Comandos MVC InicioPartida
            case TipoComando.COMANDO_CARGAR_JUGADORES:

                ComandoCargarJugadores comandoCargarJugadores = (ComandoCargarJugadores) comando;

                modeloInicioPartida.cargarJugadores(comandoCargarJugadores.getJugadores());

                break;

            case TipoComando.COMANDO_NUEVA_SOLICITUD_INICIAR_JUEGO:

                ComandoNuevaSolicitudIniciarJuego comandoNuevaSolicitudIniciarJuego = (ComandoNuevaSolicitudIniciarJuego) comando;

                modeloInicioPartida.notificarNuevaSolicitudIniciarJuego(comandoNuevaSolicitudIniciarJuego.getMensaje());

                break;

            case TipoComando.COMANDO_ACTUALIZAR_JUGADORES_INICIO_JUEGO:

                ComandoActualizarJugadoresInicioJuego comandoActualizarJugadoresInicioJuego = (ComandoActualizarJugadoresInicioJuego) comando;

                modeloInicioPartida.notificarActualizacionJugadoresIniciarJuego(
                        comandoActualizarJugadoresInicioJuego.getCantidadJugadoresInicioJuego());

                break;

            case TipoComando.COMANDO_RESPUESTA_INICIAR_JUEGO:

                ComandoRespuestaIniciarJuego comandoRespuestaIniciarJuego = (ComandoRespuestaIniciarJuego) comando;

                modeloInicioPartida.notificarRespuestaIniciarJuego(comandoRespuestaIniciarJuego.getMensaje());

                break;

            case TipoComando.COMANDO_DECISION_INICIAR_JUEGO:

                ComandoDecisionIniciarJuego comandoDecisionIniciarJuego = (ComandoDecisionIniciarJuego) comando;

                modeloInicioPartida.notificarDecisionIniciarJuego(
                        comandoDecisionIniciarJuego.getMensaje(),
                        comandoDecisionIniciarJuego.isDecision());

                break;

            case TipoComando.COMANDO_PARTIDA_CONFIGURADA:
                ComandoPartidaConfigurada comandoPartidaConfigurada = (ComandoPartidaConfigurada) comando;
                
                boolean exito = comandoPartidaConfigurada.getExito();

                modeloInicioPartida.notificarPartidaConfigurada(exito);
                break;
                
            case TipoComando.COMANDO_REGISTRO_EXITOSO:
                ComandoRegistroExitoso comandoRegistroExitoso = (ComandoRegistroExitoso) comando;

                modeloInicioPartida.notificarRegistroJugador();
                
                break;
                
            case TipoComando.COMANDO_REGISTRO_FALLIDO:
                ComandoRegistroFallido comandoRegistroFallido = (ComandoRegistroFallido) comando;

                modeloInicioPartida.notificarRegistroJugadorFallido(comandoRegistroFallido.getMensaje());
                
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
