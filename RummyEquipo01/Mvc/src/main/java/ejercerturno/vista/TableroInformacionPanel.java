
package ejercerturno.vista;

import java.util.Map;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class TableroInformacionPanel {
    
    private FichaInformacionPanel[] fichasTablero;
    private Map<Integer, Integer> mapaCasillasFichas;

    public TableroInformacionPanel(FichaInformacionPanel[] fichasTablero, Map<Integer, Integer> mapaCasillasFichas) {
        this.fichasTablero = fichasTablero;
        this.mapaCasillasFichas = mapaCasillasFichas;
    }

    public FichaInformacionPanel[] getFichasTablero() {
        return fichasTablero;
    }

    public Map<Integer, Integer> getMapaCasillasFichas() {
        return mapaCasillasFichas;
    }
    
    
    
    
}
