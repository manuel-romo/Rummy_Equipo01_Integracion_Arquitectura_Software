
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
    
    public void iniciar(){
        modelo.iniciar();
    }
    
    public void iniciarCreacionPartida() {
        modelo.iniciarCreacionPartida();
    }
    
    public void iniciarUnionPartida() {
        modelo.iniciarUnionPartida();
    }
    
    // Registrar nombre de jugador
    public void registrarNombreJugador(String nombre){
        modelo.registrarNombreJugador(nombre);
    }
    
    // Configurar partida
    public void enviarDatosPartidaConfigurada(int maximoNumeroFichas, int numeroComodines){
        modelo.enviarDatosPartidaConfigurada(maximoNumeroFichas, numeroComodines);
    }
    
    public void volverInicio() {
        modelo.iniciar();
    }
    
    // Registrar jugador
    public void iniciarRegistroJugador(){
        modelo.iniciarRegistroJugador();
    }
    
    public void enviarRegistroJugador(String avatar, Map<Integer,Color> mapaColores){
        modelo.enviarRegistroJugador(avatar, mapaColores);
    }
    
    public void solicitarUnirsePartida(){
        
        modelo.solicitarUnirsePartida();
        
    }
    
    public void confirmarUnirsePartida(boolean confirmacion){
        
        modelo.confirmarUnirsePartida(confirmacion);
    }
    
    // Solicitar inicio de juego
    
    public void solicitarInicioJuego() {
        modelo.solicitarInicioJuego();
    }

    public void confirmarEnvioSolicitudInicioJuego(boolean confirmacion) {
        modelo.confirmarEnvioSolicitudInicioJuego(confirmacion);
    }

    public void confirmarInicioJuego(boolean confirmacion) {
        modelo.confirmarInicioJuego(confirmacion);
    }
    
    public void aceptarAceptacionInicioJuego(String nombreJugador){
        
        modelo.finalizar();
        controladorEjercerTurno.iniciar(nombreJugador);  
    }

    public void setControladorEjercerTurno(ControladorEjercerTurno controladorEjercerTurno) {
        this.controladorEjercerTurno = controladorEjercerTurno;
    }
    
}
