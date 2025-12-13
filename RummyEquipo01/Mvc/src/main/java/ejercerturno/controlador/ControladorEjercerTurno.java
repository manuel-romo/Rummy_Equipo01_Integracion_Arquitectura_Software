

package ejercerturno.controlador;

import ejercerturno.modelo.ModeloEjercerTurno;
import iniciarpartida.controlador.ControladorInicioPartida;

public class ControladorEjercerTurno {

    private ModeloEjercerTurno modelo;
    private ControladorInicioPartida controladorInicioPartida;

    public ControladorEjercerTurno(ModeloEjercerTurno modelo){
        this.modelo = modelo;
    }
    
    public void iniciar(String nombreJugador){     
        modelo.iniciar(nombreJugador);
    }
    
    public void seleccionarFichasTablero(Integer[] idsFichas) {
        modelo.seleccionarFichasTablero(idsFichas);
    }

    public void quitarFichasJugador(Integer[] posicionesFichas) {
        modelo.quitarFichasJugador(posicionesFichas);
    }

    public void quitarFichasTablero(Integer[] idsFichas) {
        modelo.quitarFichasTablero(idsFichas);
    }
    
    public void agregarFichasJugador(Integer[] idsFichas) {
        modelo.agregarFichasJugador(idsFichas);
    }

    public void agregarFichasTablero(Integer[] idsFichas, Integer idsFichasGrupo[]) {
        modelo.agregarFichasTablero(idsFichas, idsFichasGrupo);
    }
    
    public void agregarFichasTablero(Integer[] idsFichas) {
        modelo.agregarFichasTablero(idsFichas);
    }
    
    public void tomarFicha(){
        modelo.tomarFicha();
    }
    
    public void reestablecerTablero(){
        modelo.reestablecerTablero();
    }
    
    public void terminarTurno(){
        modelo.terminarTurno(); 
    }
    
    public void abandonarPartida(){
        modelo.abandonarPartida();
    }
    
    public void confirmarAbandonarPartida(boolean confirmacion){
        modelo.confirmarAbandonoPartida(confirmacion);
    }
    
    public void finalizarPartida(){
        modelo.finalizarPartida();
    }
    
    public void volverInicio(){
        modelo.finalizar();
        controladorInicioPartida.iniciar();
    }
    
    public void confirmarFinalizarPartida(boolean confirmacion){
        modelo.confirmarSolicitudFin(confirmacion);
    }

    public void setControladorInicioPartida(ControladorInicioPartida controladorInicioPartida) {
        this.controladorInicioPartida = controladorInicioPartida;
    }

}