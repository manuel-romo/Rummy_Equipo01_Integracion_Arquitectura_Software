
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
    private final int MAXIMO_NUMERO_FICHAS;
    private final int NUMERO_COMODINES;

    public ComandoConfirmacionEnvioIniciarJuego(String nombreJugador, boolean confirmacion, int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
        this.nombreJugador = nombreJugador;
        this.confirmacion = confirmacion;
        this.MAXIMO_NUMERO_FICHAS = MAXIMO_NUMERO_FICHAS;
        this.NUMERO_COMODINES = NUMERO_COMODINES;
    }

    public int getMAXIMO_NUMERO_FICHAS() {
        return MAXIMO_NUMERO_FICHAS;
    }

    public int getNUMERO_COMODINES() {
        return NUMERO_COMODINES;
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
