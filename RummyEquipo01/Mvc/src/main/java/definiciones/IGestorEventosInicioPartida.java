
package definiciones;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public interface IGestorEventosInicioPartida {
    
    public abstract void solicitarInicioJuego();
    
    public abstract void confirmarEnvioSolicitudInicioJuego(boolean confirmacion);
    
    public abstract void confirmarInicioJuego(boolean confirmacion);
    
    public abstract void aceptarAceptacionInicioJuego();
    
    
}
