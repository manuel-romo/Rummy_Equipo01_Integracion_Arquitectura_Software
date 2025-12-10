
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
    private final int MAXIMO_NUMERO_FICHAS;
    private final int NUMERO_COMODINES;

    public ComandoIniciarJuego(int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
        this.nombreJugador = nombreJugador;
        this.MAXIMO_NUMERO_FICHAS = MAXIMO_NUMERO_FICHAS;
        this.NUMERO_COMODINES = NUMERO_COMODINES;
    }

    public int getMAXIMO_NUMERO_FICHAS() {
        return MAXIMO_NUMERO_FICHAS;
    }

    public int getNUMERO_COMODINES() {
        return NUMERO_COMODINES;
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
