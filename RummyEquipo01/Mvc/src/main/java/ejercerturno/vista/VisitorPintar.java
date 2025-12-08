
package ejercerturno.vista;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class VisitorPintar implements IVisitor{
    
    private EstadoActual estadoActual;
    
    public VisitorPintar(EstadoActual estadoActual){
        
        this.estadoActual = estadoActual;
        
    }

    @Override
    public void visitar(PanelMonton panelMonton) {
        IEstadoMonton estadoMonton = (IEstadoMonton)estadoActual;
        panelMonton.pintar(estadoMonton);
    }

    @Override
    public void visitar(PanelJugadorPrincipal panelJugadorPrincipal) {
        IEstadoJugadorPrincipal estadoJugadorPrincipal = (IEstadoJugadorPrincipal) estadoActual;
        panelJugadorPrincipal.pintar(estadoJugadorPrincipal);
        
    }

    @Override
    public void visitar(PanelJugadorExterno panelJugadorExerno) {
        IEstadoJugadorExterno estadoJugadorExterno = (IEstadoJugadorExterno) estadoActual;
        panelJugadorExerno.pintar(estadoJugadorExterno);
    }

    @Override
    public void visitar(PanelMesaJuego panelMesaJuego) {
        IEstadoMesaJuego estadoMesaJuego = (IEstadoMesaJuego) estadoActual;
        panelMesaJuego.pintar(estadoMesaJuego);
    }

    @Override
    public void visitar(PanelTablero panelTablero) {
        
        IEstadoTablero estadoTablero = (IEstadoTablero) estadoActual;
        panelTablero.pintar(estadoTablero);
    }

    @Override
    public void visitar(PanelMovimiento panelMovimiento) {
        
        IEstadoPanelMovimiento estadoPanelMovimiento = (IEstadoPanelMovimiento) estadoActual;
        panelMovimiento.pintar(estadoPanelMovimiento);
        
    }
    
}
