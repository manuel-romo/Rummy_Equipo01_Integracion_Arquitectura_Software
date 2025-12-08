
package definiciones;

import ejercerturno.dto.JugadorExternoPresentacionDTO;
import ejercerturno.dto.JugadorPrincipalPresentacionDTO;
import ejercerturno.dto.MontonPresentacionDTO;
import ejercerturno.dto.TableroPresentacionDTO;


public interface IModeloEjercerTurno extends IModelo{
    
    public abstract JugadorPrincipalPresentacionDTO obtenerJugadorPrincipal();
    
    public abstract JugadorExternoPresentacionDTO[] obtenerJugadoresExternos();
    
    public abstract MontonPresentacionDTO obtenerMontonPresentacion();
    
    public abstract TableroPresentacionDTO obtenerTablero();
    
    public abstract String obtenerMensaje();
    
    public abstract boolean isVistaHabilitada();
    
    public abstract boolean isVistaVisible();
    
    public abstract boolean isTableroInvalido();
    
    public abstract boolean isMovimientoInvalido();
    
    public abstract boolean isNuevoTurno();
    
}
