
package definiciones;

import iniciarpartida.dto.EtapaActual;
import iniciarpartida.dto.JugadorInicioPartidaPresentacionDTO;
import java.util.List;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public interface IModeloInicioPartida extends IModelo {
    
    
    public abstract String obtenerMensaje();
    
    public abstract List<JugadorInicioPartidaPresentacionDTO> obtenerJugadores();
    
    public abstract EtapaActual obtenerEtapaActual();
    
    public abstract int obtenerCantidadJugadoresIniciarJuego();
    
    public abstract boolean isVistaVisible();
    
}
