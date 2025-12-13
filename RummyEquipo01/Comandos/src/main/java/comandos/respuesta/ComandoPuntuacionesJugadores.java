
package comandos.respuesta;

import dtos.JugadorPuntuacionDTO;
import interfaces.IComando;
import java.util.List;

/**
 *
 * @author Romo López Manuel
 * Id: 00000253080
 */
public class ComandoPuntuacionesJugadores implements IComando{
    
    private String type = "ComandoPuntuacionesJugadores";
    private String nombreJugador;
    private List<JugadorPuntuacionDTO> jugadoresPuntuacion;

    public ComandoPuntuacionesJugadores(String nombreJugador, List<JugadorPuntuacionDTO> jugadoresPuntuacion) {
        this.nombreJugador = nombreJugador;
        this.jugadoresPuntuacion = jugadoresPuntuacion;
    }

    @Override
    public String getTipo() {
        return type;
    }

    public List<JugadorPuntuacionDTO> getJugadoresPuntuacion() {
        return jugadoresPuntuacion;
    }
    
    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }
}
