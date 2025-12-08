
package ejercerturno.vista;

import java.awt.event.MouseEvent;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public interface IGestorEventos {
    
    public abstract void seleccionarFicha(MouseEvent e, boolean seleccionada);
    public abstract void fichaSoltada(MouseEvent e);
    public abstract void quitarFichasCasillas();
    public abstract void iniciarArrastreFichas();
    public abstract void presionarFicha();
    public abstract void dejarPresionarFicha();
    public abstract void borrarFichasMovimiento();
    public abstract void arrastreFichaMovimiento(MouseEvent e);
    public abstract void soltarFichasMovimiento(MouseEvent e);
    public abstract void tomarFicha();
    public abstract void reestablecerTablero();
    public abstract void terminarTurno();
    public abstract void abandonarPartida();
    public abstract void confirmarAbandonarPartida(boolean confirmacion);
    public abstract void finalizarPartida();
    public abstract void confirmarFinalizarPartida(boolean confirmacion);
}
