
package ejercerturno.dto;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class JugadorPrincipalPresentacionDTO{
    private FichaPresentacionDTO[] fichas;

    public JugadorPrincipalPresentacionDTO(FichaPresentacionDTO[] fichas) {
        this.fichas = fichas;
    }

    public FichaPresentacionDTO[] getFichas() {
        return fichas;
    }
    
    
}
