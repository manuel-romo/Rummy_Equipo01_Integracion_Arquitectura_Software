
package iniciarpartida.controlador;

import ejercerturno.controlador.ControladorEjercerTurno;
import iniciarpartida.modelo.ModeloInicioPartida;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author Romo López Manuel
 */
public class ControladorInicioPartida {
    
    private ModeloInicioPartida modelo;
    private ControladorEjercerTurno controladorEjercerTurno;

    public ControladorInicioPartida(ModeloInicioPartida modelo) {
        this.modelo = modelo;
    }  
    
    public void iniciarRegistroNombreJugador() {
        modelo.iniciarRegistroNombreJugador();
    }
    
    // Registrar nombre de jugador
    public void registrarNombreJugador(String nombre){
        modelo.iniciarConfiguracionPartida(nombre);
    }
    
    // Configurar partida
    public void enviarDatosPartidaConfigurada(int maximoNumeroFichas, int numeroComodines){
        modelo.enviarDatosPartidaConfigurada(maximoNumeroFichas, numeroComodines);
    }
    
    public void cancelarConfiguracionPartida() {
        modelo.iniciarInicio();
    }
    
    // Registrar jugador
    public void iniciarRegistroJugador(){
        modelo.iniciarRegistroJugador();
    }
    
    public void enviarRegistroJugador(String avatar, Map<Integer,Color> mapaColores){
        modelo.enviarRegistroJugador(avatar, mapaColores);
    }
    
    // Solicitar inicio de juego
    public void iniciarSalaEspera(){
        modelo.iniciarSalaEspera();
    }
    
    public void solicitarInicioJuego() {
        modelo.solicitarInicioJuego();
    }

    public void confirmarEnvioSolicitudInicioJuego(boolean confirmacion) {
        modelo.confirmarEnvioSolicitudInicioJuego(confirmacion);
    }

    public void confirmarInicioJuego(boolean confirmacion) {
        modelo.confirmarInicioJuego(confirmacion);
    }
    
    public void aceptarAceptacionInicioJuego(){
        modelo.finalizar();
        controladorEjercerTurno.iniciar();  
    }

    public void setControladorEjercerTurno(ControladorEjercerTurno controladorEjercerTurno) {
        this.controladorEjercerTurno = controladorEjercerTurno;
    }
    
}
