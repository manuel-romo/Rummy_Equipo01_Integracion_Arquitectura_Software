
package comandos.solicitud;

import interfaces.IComando;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class ComandoIniciarJuego implements IComando{
    private String type = "ComandoIniciarPartida"; 
    private String nombreJugador;

    public ComandoIniciarJuego() {
        this.nombreJugador = nombreJugador;
    }


    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }

    @Override
    public String getTipo() {
        return type;
    }
    
    
}
