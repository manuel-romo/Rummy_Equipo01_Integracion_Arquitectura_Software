
package comandosSolicitud;

import interfaces.ICommand;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class ComandoIniciarJuego implements ICommand{
    
    private String type = "ComandoIniciarJuego";
    private String nombreJugador;

    public ComandoIniciarJuego(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }
    
    
}
