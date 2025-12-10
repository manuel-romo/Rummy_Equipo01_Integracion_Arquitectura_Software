
package definiciones;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public interface IReceptorEventosIniciarPartida {
    
    public abstract void solicitarInicioJuego(int MAXIMO_NUMERO_FICHAS,int NUMERO_COMODINES);
    
    public abstract void confirmarEnvioSolicitudInicioJuego(boolean confirmacion);
    
    public abstract void confirmarInicioJuego(boolean confirmacion,int MAXIMO_NUMERO_FICHAS,int NUMERO_COMODINES);
    
    public abstract void aceptarAceptacionInicioJuego();
    
}
