
package iniciarpartida.modelo;

import definiciones.IModeloInicioPartida;
import definiciones.IPublicador;
import definiciones.ISuscriptor;
import dto.JugadorInicioPartidaDTO;
import fachada.FachadaMvc;
import iniciarpartida.dto.EtapaActual;
import iniciarpartida.dto.JugadorInicioPartidaPresentacionDTO;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class ModeloInicioPartida implements IPublicador, IModeloInicioPartida{
    
    private FachadaMvc fachadaMvc;
    
    private String nombreJugador;
    
    private List<ISuscriptor> suscriptores = new LinkedList<>();
    
    private String mensaje;
    
    private List<JugadorInicioPartidaDTO> jugadores;
    
    private int cantidadJugadoresIniciarJuego = 0;
    
    private EtapaActual etapaActual;
    
    private boolean vistaVisible;
         
    // Códigos de mensajes.
    private final String CODIGO_MENSAJE_NUEVA_SOLICITUD_INICIO = "SI: ";
    private final String CODIGO_MENSAJE_CONFIRMAR_ENVIO_SOLICITUD_INICIO = "CE: ";
    private final String CODIGO_MENSAJE_ACEPTACION_INICIO = "AI: ";
    private final String CODIGO_MENSAJE_RECHAZO_INICIO = "RI: ";

    public ModeloInicioPartida(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
    
    
    public void iniciarSalaEspera(){
        
        vistaVisible = true;
        etapaActual = EtapaActual.SALA_ESPERA;
        notificar();
        
    }
    
    
    public void finalizar(){
        
        this.jugadores = null;
        this.mensaje = null;
        etapaActual = null;
        vistaVisible = false;
        
        notificar();
        
    }
    
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
    
    public void cargarJugadores(List<JugadorInicioPartidaDTO> jugadores){
        
        this.jugadores = jugadores;
        this.mensaje = null;
        etapaActual = EtapaActual.SALA_ESPERA;
        
        notificar();
        
    }
    
    public void notificarNuevaSolicitudIniciarJuego(String mensaje){
        
        this.mensaje = CODIGO_MENSAJE_NUEVA_SOLICITUD_INICIO + mensaje;
        etapaActual = EtapaActual.SALA_ESPERA;
        
        notificar();
        
    }
    
    public void notificarRespuestaIniciarJuego(String mensaje){
        
        this.mensaje = CODIGO_MENSAJE_CONFIRMAR_ENVIO_SOLICITUD_INICIO + mensaje;
        etapaActual = EtapaActual.SALA_ESPERA;
        
        notificar();
        
    }
    
    public void notificarActualizacionJugadoresIniciarJuego(int cantidadJugadores){
        
        this.mensaje = null;
        cantidadJugadoresIniciarJuego = cantidadJugadores;
        etapaActual = EtapaActual.SALA_ESPERA;
        
        notificar();
        
    }
    
    public void notificarDecisionIniciarJuego(String mensaje, boolean decision){
        
        etapaActual = EtapaActual.SALA_ESPERA;
        
        if(decision){
            notificarAceptacionIniciarJuego(mensaje);
        } else{
            notificarRechazoIniciarJuego(mensaje);
        }
        
    }
    
    private void notificarAceptacionIniciarJuego(String mensaje){
        
        this.mensaje = CODIGO_MENSAJE_ACEPTACION_INICIO + mensaje;
        notificar();
    }
    
    private void notificarRechazoIniciarJuego(String mensaje){
        
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
        
        if(jugadores == null){
            return null;
        }
        
        for(JugadorInicioPartidaDTO jugador: jugadores){
            
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
        
        for(ISuscriptor suscriptor: suscriptores){
            
            suscriptor.actualizar(this);
            
        }
        
    }

    public void setFachadaMvc(FachadaMvc fachadaMvc) {
        this.fachadaMvc = fachadaMvc;
    }


}
