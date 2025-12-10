package definiciones;

import iniciarpartida.dto.EtapaActual;
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

    public abstract EtapaActual obtenerEtapaActual();

    public abstract int obtenerCantidadJugadoresIniciarJuego();

    public abstract boolean isVistaVisible();

    // métodos de registrar jugador jp 
    boolean isJugadorRegistrado();

    Map<Integer, Color> getMapaColores();

    String obtenerNombreJugador();

    //AQUI TERMINA REGISTAR JUGADOR JP
}
