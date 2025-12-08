
package ejercerturno.vista;

import java.util.Map;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class JugadorPrincipalInformacionPanel {
   
    private FichaInformacionPanel[] fichasJugadorPrincipal;
    private Map<Integer, Integer> mapaCasillasFichas;

    public JugadorPrincipalInformacionPanel(FichaInformacionPanel[] fichasJugadorPrincipal, Map<Integer, Integer> mapaCasillasFichas) {
        this.fichasJugadorPrincipal = fichasJugadorPrincipal;
        this.mapaCasillasFichas = mapaCasillasFichas;
    }

    public FichaInformacionPanel[] getFichasJugadorPrincipal() {
        return fichasJugadorPrincipal;
    }

    public Map<Integer, Integer> getMapaCasillasFichas() {
        return mapaCasillasFichas;
    }
    
    
}
