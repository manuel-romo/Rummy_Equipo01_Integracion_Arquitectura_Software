
package ejercerturno.dto;

/**
 *
 * @author 
 * 
 */
public abstract class FichaPresentacionDTO {
    
    private int idFicha;

    public FichaPresentacionDTO(int idFicha) {
        this.idFicha = idFicha;
    }
    
    public int getIdFicha() {
        return idFicha;
    }
    
}
