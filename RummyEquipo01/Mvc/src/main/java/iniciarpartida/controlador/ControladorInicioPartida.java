
package iniciarpartida.controlador;

import ejercerturno.controlador.ControladorEjercerTurno;
import iniciarpartida.modelo.ModeloInicioPartida;

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
    
    
}
