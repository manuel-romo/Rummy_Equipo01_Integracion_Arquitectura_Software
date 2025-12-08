
package ejercerturno.dto;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class TableroPresentacionDTO {
    
    private FichaPresentacionDTO[] fichas;

    public TableroPresentacionDTO(FichaPresentacionDTO[] fichas) {
        this.fichas = fichas;
    }

    public FichaPresentacionDTO[] getFichas() {
        return fichas;
    }
    
    
}
