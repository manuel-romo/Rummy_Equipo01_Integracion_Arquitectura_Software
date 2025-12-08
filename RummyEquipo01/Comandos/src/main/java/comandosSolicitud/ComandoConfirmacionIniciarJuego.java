
package comandosSolicitud;

import interfaces.ICommand;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class ComandoConfirmacionIniciarJuego implements ICommand{
    
    private String nombreJugador;
    private final String type = "ComandoConfirmacionIniciarJuego";
    private boolean confirmacion;

    public ComandoConfirmacionIniciarJuego(String nombreJugador, boolean confirmacion) {
        this.nombreJugador = nombreJugador;
        this.confirmacion = confirmacion;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public boolean isConfirmacion() {
        return confirmacion;
    }

    @Override
    public String getType() {
        return type;
    }
    
}
