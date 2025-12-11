
package comandos.solicitud;

import interfaces.IComando;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class ComandoConfirmacionEnvioIniciarJuego implements IComando{
    
    private String nombreJugador;
    private final String type = "ComandoConfirmacionEnvioIniciarJuego";
    private boolean confirmacion;

    public ComandoConfirmacionEnvioIniciarJuego(String nombreJugador, boolean confirmacion) {
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
    public String getTipo() {
        return type;
    }
    
}
