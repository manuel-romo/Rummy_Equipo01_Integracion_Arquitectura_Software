
package comandosRespuesta;

import dto.JugadorInicioPartidaDTO;
import interfaces.ICommand;
import java.util.List;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class ComandoCargarJugadores implements ICommand{
    
    private String type = "ComandoCargarJugadores";
    private String nombreJugador;
    private List<JugadorInicioPartidaDTO> jugadores;

    public ComandoCargarJugadores(String nombreJugador, List<JugadorInicioPartidaDTO> jugadores) {
        this.nombreJugador = nombreJugador;
        this.jugadores = jugadores;
    }

    @Override
    public String getType() {
        return type;
    }

    public List<JugadorInicioPartidaDTO> getJugadores() {
        return jugadores;
    }
    
    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }
    
}
