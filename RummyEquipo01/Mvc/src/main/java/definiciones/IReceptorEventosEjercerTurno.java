
package definiciones;

import javax.swing.JPanel;


public interface IReceptorEventosEjercerTurno {
    
    public abstract void quitarFichasTablero(Integer[] idsCasillas, Integer[] idsFichas);
    
    public abstract void quitarFichasJugador(Integer[] idsCasillas, Integer[] posicionesFichas);
    
    public abstract void agregarFichasTablero(Integer[] idsCasillas, Integer[] idsFichas);
    
    public abstract void agregarFichasTablero(Integer[] idsCasillas, Integer[] idsFichas, Integer[] idsFichasGrupo);
    
    public abstract void agregarFichasJugador(Integer[] idsCasillas, Integer[] idsFichas);
    
    public abstract void tomarFicha();
    
    public abstract void reestablecerTablero();
    
    public abstract void terminarTurno();
    
    public abstract void abandonarPartida();
    
    public abstract void confirmarAbandonarPartida(boolean confirmacion);
    
    public abstract void finalizarPartida();
    
    public abstract void confirmarFinalizarPartida(boolean confirmacion);
    
}
