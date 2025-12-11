package iniciarpartida.modelo;

import definiciones.IModeloInicioPartida;
import definiciones.IPublicador;
import definiciones.ISuscriptor;
import dtos.JugadorInicioPartidaDTO;
import fachada.FachadaMvc;
import iniciarpartida.dto.EstadoPartida;
import iniciarpartida.dto.EtapaActual;
import iniciarpartida.dto.JugadorInicioPartidaPresentacionDTO;
import java.awt.Color;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class ModeloInicioPartida implements IPublicador, IModeloInicioPartida {

    private FachadaMvc fachadaMvc;

    private String nombreJugador;

    private Map<Integer, Color> mapaColores; //JP
    private boolean jugadorRegistrado = false;

    private List<ISuscriptor> suscriptores = new LinkedList<>();

    private String mensaje;

    private List<JugadorInicioPartidaDTO> jugadores;

    private int cantidadJugadoresIniciarJuego = 0;

    private EtapaActual etapaActual;

    private boolean vistaVisible;

    //Pedro
    private int maximoNumeroFicha;

    private int numeroComodines;

    private EstadoPartida estadoPartida;

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void setMapaColores(Map<Integer, Color> mapaColores) {
        this.mapaColores = mapaColores;
    }

    // Códigos de mensajes.
    private final String CODIGO_MENSAJE_NUEVA_SOLICITUD_INICIO = "SI: ";
    private final String CODIGO_MENSAJE_CONFIRMAR_ENVIO_SOLICITUD_INICIO = "CE: ";
    private final String CODIGO_MENSAJE_ACEPTACION_INICIO = "AI: ";
    private final String CODIGO_MENSAJE_RECHAZO_INICIO = "RI: ";

    public ModeloInicioPartida(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void iniciarSalaEspera() {

        vistaVisible = true;
        etapaActual = EtapaActual.SALA_ESPERA;
        estadoPartida = EstadoPartida.INICIADA;
        notificar();

    }

    public void finalizar() {

        this.jugadores = null;
        this.mensaje = null;
        etapaActual = null;
        vistaVisible = false;

        notificar();

    }

    public void solicitarInicioJuego() {

        fachadaMvc.solicitarInicioJuego();

    }

    public void confirmarEnvioSolicitudInicioJuego(boolean confirmar) {

        fachadaMvc.confirmarEnvioSolicitudInicioJuego(nombreJugador, confirmar);

    }

    public void confirmarInicioJuego(boolean confirmar) {

        fachadaMvc.confirmarInicioJuego(nombreJugador, confirmar);

    }

    // Recepción
    public void cargarJugadores(List<JugadorInicioPartidaDTO> jugadores) {

        this.jugadores = jugadores;
        this.mensaje = null;
        etapaActual = EtapaActual.SALA_ESPERA;

        notificar();

    }

    public void notificarNuevaSolicitudIniciarJuego(String mensaje) {

        this.mensaje = CODIGO_MENSAJE_NUEVA_SOLICITUD_INICIO + mensaje;
        etapaActual = EtapaActual.SALA_ESPERA;

        notificar();

    }

    public void notificarRespuestaIniciarJuego(String mensaje) {

        this.mensaje = CODIGO_MENSAJE_CONFIRMAR_ENVIO_SOLICITUD_INICIO + mensaje;
        etapaActual = EtapaActual.SALA_ESPERA;

        notificar();

    }

    public void notificarActualizacionJugadoresIniciarJuego(int cantidadJugadores) {

        this.mensaje = null;
        cantidadJugadoresIniciarJuego = cantidadJugadores;
        etapaActual = EtapaActual.SALA_ESPERA;

        notificar();

    }

    public void notificarDecisionIniciarJuego(String mensaje, boolean decision) {

        etapaActual = EtapaActual.SALA_ESPERA;

        if (decision) {
            notificarAceptacionIniciarJuego(mensaje);
        } else {
            notificarRechazoIniciarJuego(mensaje);
        }

    }

    public void notificarPartidaConfigurada(boolean exito) {
        estadoPartida = exito ? EstadoPartida.CONFIGURADA : EstadoPartida.ERROR;
        notificar();
    }

    private void notificarAceptacionIniciarJuego(String mensaje) {

        this.mensaje = CODIGO_MENSAJE_ACEPTACION_INICIO + mensaje;
        notificar();
    }

    private void notificarRechazoIniciarJuego(String mensaje) {

        this.mensaje = CODIGO_MENSAJE_RECHAZO_INICIO + mensaje;
        notificar();

    }

    @Override
    public String obtenerMensaje() {
        return mensaje;
    }

    @Override
    public List<JugadorInicioPartidaPresentacionDTO> obtenerJugadores() {

        List<JugadorInicioPartidaPresentacionDTO> jugadoresPresentacion = new LinkedList<>();

        if (jugadores == null) {
            return null;
        }

        for (JugadorInicioPartidaDTO jugador : jugadores) {

            jugadoresPresentacion.add(
                    new JugadorInicioPartidaPresentacionDTO(
                            jugador.getNombre(),
                            jugador.getAvatar())
            );

        }

        return jugadoresPresentacion;

    }

    @Override
    public EtapaActual obtenerEtapaActual() {
        return etapaActual;
    }

    @Override
    public int obtenerCantidadJugadoresIniciarJuego() {
        return cantidadJugadoresIniciarJuego;
    }

    @Override
    public boolean isVistaVisible() {
        return vistaVisible;
    }

    @Override
    public void suscribirse(ISuscriptor suscriptor) {
        suscriptores.add(suscriptor);
    }

    @Override
    public void desuscribirse(ISuscriptor suscriptor) {
        suscriptores.remove(suscriptor);
    }

    @Override
    public void notificar() {

        for (ISuscriptor suscriptor : suscriptores) {

            suscriptor.actualizar(this);

        }

    }

    public void setFachadaMvc(FachadaMvc fachadaMvc) {
        this.fachadaMvc = fachadaMvc;
    }

    public void enviarRegistro(String avatar, Map<Integer, Color> mapaColores) {
        this.setMapaColores(mapaColores);
        fachadaMvc.registrarJugador(nombreJugador, avatar);

    }

    /**
     * *
     * Método que sucede cuando el registro de jugador tuvo éxito. Se cambia el
     * estado de jugador inscrito a true.
     */
    public void notificarRegistroJugador() {
        this.jugadorRegistrado = true;
        notificar();
    }

    public void notificarRegistroJugadorFallido(String mensaje) {
        this.mensaje = mensaje;
        notificar();
    }

    //Configurar partida
    public void enviarDatos(String nombreJugador, int maximoNumeroFichas, int numeroComodines) {
        maximoNumeroFichas = this.maximoNumeroFicha;
        numeroComodines = this.numeroComodines;
        fachadaMvc.configurarPartida(nombreJugador, maximoNumeroFichas, numeroComodines, obtenerIPsReales(), "51000");
        notificar();
    }

    //Obtener IP
    public String obtenerIPsReales() {
        StringBuilder ipsReales = new StringBuilder();

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) {
                    continue;
                }

                Enumeration<InetAddress> direcciones = ni.getInetAddresses();
                while (direcciones.hasMoreElements()) {
                    InetAddress addr = direcciones.nextElement();

                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        ipsReales.append("IP real encontrada: ").append(addr.getHostAddress()).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ipsReales.length() == 0) {
            return "No se encontró ninguna IP real.";
        }

        return ipsReales.toString();
    }

    @Override
    public boolean isJugadorRegistrado() {
        return jugadorRegistrado;
    }

    @Override
    public Map<Integer, Color> getMapaColores() {
        return mapaColores;
    }

    @Override
    public String obtenerNombreJugador() {
        return nombreJugador;
    }

    @Override
    public EstadoPartida obtenerEstadoPartida() {
        return estadoPartida;
    }

}
