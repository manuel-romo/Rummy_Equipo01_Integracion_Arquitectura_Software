
package definiciones;

import java.awt.Color;
import java.util.Map;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public interface IGestorEventosInicioPartida {
    
    // Inicio
    public abstract void iniciarRegistroNombreJugador();
    
    // Registro de nombre de jugador
    public abstract void registrarNombreJugador(String nombre);
    
    // Configurar partida
    public abstract void enviarDatosConfigurarPartida(int maximoFichas, int numeroComodines);
    
    public abstract void cancelarConfiguracionPartida();
    
    // Registrar jugador
    public abstract void iniciarRegistroJugador();
    
    public abstract void enviarRegistroJugador(String linkAvatarSeleccionado, Map<Integer, Color> mapaColores);
    
    // Solicitar inicio de juego
    
    public abstract void solicitarInicioJuego();
    
    public abstract void confirmarEnvioSolicitudInicioJuego(boolean confirmacion);
    
    public abstract void confirmarInicioJuego(boolean confirmacion);
    
    public abstract void aceptarAceptacionInicioJuego();
    
    
    
}
