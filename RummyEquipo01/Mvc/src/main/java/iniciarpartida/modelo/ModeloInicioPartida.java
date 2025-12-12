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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import utils.DireccionUtils;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class ModeloInicioPartida implements IPublicador, IModeloInicioPartida {

    private String MENSAJE_ERROR_CONFIGURAR_PARTIDA = "Ha ocurrido un error al configurar la partida.";
    private FachadaMvc fachadaMvc;

    private String nombreJugador;

    private Map<Integer, Color> mapaColores;
    private boolean jugadorRegistrado = false;

    private List<ISuscriptor> suscriptores = new LinkedList<>();

    private String mensaje;

    private List<JugadorInicioPartidaDTO> jugadores;

    private int cantidadJugadoresIniciarJuego = 0;

    private EtapaActual etapaActual;

    private boolean vistaVisible;
    
    private EstadoPartida estadoPartida;
    
    private String nombreJugadorSolicitanteUnion = null;
    
    
    private boolean creandoPartida = false;
    
    private boolean juegoIniciado = false;

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
    private final String CODIGO_MENSAJE_RECHAZO_INICIO = "RI: ";
    
    private final String CODIGO_MENSAJE_SOLICITUD_UNION = "SU: ";
    private final String CODIGO_MENSAJE_RECHAZO_UNION = "RU: ";
    private final String CODIGO_MENSAJE_ACEPTACION_UNION = "AU: ";

    public void iniciarInicio(){
        
        nombreJugador = null;
        vistaVisible = true;
        etapaActual = EtapaActual.INICIO;
        notificar();
        
    }
    
    private void iniciarRegistroNombreJugador(){
        
        vistaVisible = true;
        etapaActual = EtapaActual.REGISTRO_NOMBRE_JUGADOR;
        notificar();
        
    }
    
    public void iniciarCreacionPartida(){
        
        creandoPartida = true;
        iniciarRegistroNombreJugador();
        
    }

    // Solicitar unirse a partida
    
    public void iniciarUnionPartida(){
        
        creandoPartida = false;
        iniciarRegistroNombreJugador();
    }
    
    
    public void registrarNombreJugador(String nombre){
        
        this.nombreJugador = nombre;
        
        if(creandoPartida){
            iniciarConfiguracionPartida();
        } else{
            introducirDireccionIP();
        }
    }
   
    
    private void iniciarConfiguracionPartida(){
        
        vistaVisible = true;
        etapaActual = EtapaActual.CONFIGURACION_PARTIDA;
        
        notificar();
        
    }
    
    private void introducirDireccionIP(){

        vistaVisible = true;
        etapaActual = EtapaActual.REGISTRO_IP;
        
        notificar();
        
    }
    
    public void iniciarRegistroJugador(){

        vistaVisible = true;
        etapaActual = EtapaActual.REGISTRO_JUGADOR;
        
        notificar();
        
    }

    public void finalizar() {

        this.jugadores = null;
        this.mensaje = null;
        etapaActual = null;
        vistaVisible = false;
        juegoIniciado = false;

        System.out.println("NOTIFICANDO FINALIZAR");
        notificar();

    }
    
    public void solicitarUnirsePartida(){
        
        fachadaMvc.solicitarUnirsePartida(nombreJugador, DireccionUtils.obtenerIPsReales(), DireccionUtils.getPuerto());
        
    }
    
    public void confirmarUnirsePartida(boolean confirmacion){
        
        fachadaMvc.confirmarUnirsePartida(nombreJugador, confirmacion, nombreJugadorSolicitanteUnion);
        
    }
    
    // Registro de configuración de partida
    public void enviarDatosPartidaConfigurada(int maximoNumeroFichas, int numeroComodines){
        fachadaMvc.enviarDatosPartidaConfigurada(
                nombreJugador, 
                maximoNumeroFichas, 
                numeroComodines, 
                DireccionUtils.obtenerIPsReales(), 
                DireccionUtils.getPuerto());
    }
    
    // Envío de registro de jugador
    public void enviarRegistroJugador(String avatar, Map<Integer, Color> mapaColores) {
        this.setMapaColores(mapaColores);
        fachadaMvc.enviarRegistroJugador(nombreJugador, avatar);
    }

    // Solicitud de inicio de juego
    public void solicitarInicioJuego() {

        fachadaMvc.solicitarInicioJuego(nombreJugador);

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

        if (!decision) {
            this.mensaje = CODIGO_MENSAJE_RECHAZO_INICIO + mensaje;
            nombreJugador = null;
        } else{
            juegoIniciado = true;
            this.mensaje = null;
        }
        
        notificar();

    }
    

    public void notificarPartidaConfigurada(boolean exito) {
        
        if(exito){
            estadoPartida = EstadoPartida.CONFIGURADA;
            
        } else{
            estadoPartida = EstadoPartida.ERROR;
            mensaje = MENSAJE_ERROR_CONFIGURAR_PARTIDA;
        }

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
    
    public void notificarNuevoJugadorSolicitaUnirse(String mensaje, String nombreJugadorSolicitante){
        
        this.mensaje = CODIGO_MENSAJE_SOLICITUD_UNION + mensaje;
        this.nombreJugadorSolicitanteUnion = nombreJugadorSolicitante;
        
        notificar();
        
    }
    
    public void notificarRespuestaConfirmacionUnirse(String mensaje, boolean confirmacion){
        
        if(confirmacion){
            this.mensaje = CODIGO_MENSAJE_ACEPTACION_UNION + mensaje;
        } else{
            this.mensaje = CODIGO_MENSAJE_RECHAZO_UNION + mensaje;
        }
        
        notificar();
        
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

    @Override
    public boolean isJuegoIniciado() {
        return juegoIniciado;
    }

}
