
package comandosRespuesta;

import interfaces.ICommand;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class ComandoDecisionIniciarJuego implements ICommand{
    
    private String type = "ComandoDecisionIniciarJuego";
    private String nombreJugador;
    private String mensaje;
    private boolean decision;

    public ComandoDecisionIniciarJuego(String nombreJugador, String mensaje, boolean decision) {
        this.nombreJugador = nombreJugador;
        this.mensaje = mensaje;
        this.decision = decision;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getMensaje() {
        return mensaje;
    } 

    public boolean isDecision() {
        return decision;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }
    
    
}
