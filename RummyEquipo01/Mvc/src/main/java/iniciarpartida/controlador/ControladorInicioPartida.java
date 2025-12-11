
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
    
    public void solicitarInicioJuego(int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
        modelo.solicitarInicioJuego(MAXIMO_NUMERO_FICHAS, NUMERO_COMODINES);
    }

    public void confirmarEnvioSolicitudInicioJuego(boolean confirmacion,int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
        modelo.confirmarEnvioSolicitudInicioJuego(confirmacion,MAXIMO_NUMERO_FICHAS,NUMERO_COMODINES);
    }

    public void confirmarInicioJuego(boolean confirmacion,int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
        modelo.confirmarInicioJuego(confirmacion,MAXIMO_NUMERO_FICHAS,NUMERO_COMODINES);
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
    public void enviarDatos(String nombreJugador, int maximoNumeroFichas, int numeroComodines){
        modelo.enviarDatos(nombreJugador, maximoNumeroFichas, numeroComodines);
    }
    
}
