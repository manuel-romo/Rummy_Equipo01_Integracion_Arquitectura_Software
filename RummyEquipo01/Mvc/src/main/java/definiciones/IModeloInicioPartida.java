package definiciones;

import iniciarpartida.dto.EstadoPartida;
import iniciarpartida.dto.EtapaActualInicioPartida;
import iniciarpartida.dto.JugadorInicioPartidaPresentacionDTO;
import java.awt.Color;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public interface IModeloInicioPartida extends IModelo {

    public abstract String obtenerMensaje();

    public abstract List<JugadorInicioPartidaPresentacionDTO> obtenerJugadores();

    public abstract EtapaActualInicioPartida obtenerEtapaActual();

    public abstract int obtenerCantidadJugadoresIniciarJuego();

    public abstract boolean isVistaVisible();

    // métodos de registrar jugador jp 
    public abstract boolean isJugadorRegistrado();

    public abstract Map<Integer, Color> getMapaColores();

    public abstract String obtenerNombreJugador();

    //AQUI TERMINA REGISTAR JUGADOR JP
    
    //métodos de configurarPartida
    
    public abstract EstadoPartida obtenerEstadoPartida();
    
    public abstract boolean isJuegoIniciado();
    
}
