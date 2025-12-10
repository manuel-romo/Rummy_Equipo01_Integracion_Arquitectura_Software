
package negocio;

import excepciones.RummyException;
import interfaces.IFiltro;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.IComando;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class FachadaObjetosNegocio implements IFiltro{

    private IFiltro filtroSiguiente;
    private Partida partida;
    
    @Override
    public void ejecutar(IComando comando)  {
        try {
            partida.ejecutar(comando);
        } catch (RummyException ex) {
            Logger.getLogger(FachadaObjetosNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setFiltroSiguiente(IFiltro filtroSiguiente) {
        this.filtroSiguiente = filtroSiguiente;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    public void enviarComando(IComando comando){
        
        filtroSiguiente.ejecutar(comando);
        
    }


}
