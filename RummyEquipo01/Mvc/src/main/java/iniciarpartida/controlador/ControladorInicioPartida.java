
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
    
    
    //Método Juan P
    public void enviarRegistro(String avatar, Map<Integer,Color> mapaColores){
        modelo.enviarRegistro(avatar, mapaColores);
    }
    
    //Método ConfigurarPartida
    public void enviarRegistro(int maximoNumeroFichas, int numeroComodines){
        modelo.enviarDatos(maximoNumeroFichas, numeroComodines);
    }
    
}
