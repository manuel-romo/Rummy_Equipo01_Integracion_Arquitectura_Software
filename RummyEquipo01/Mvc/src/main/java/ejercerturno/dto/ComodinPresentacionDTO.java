
package ejercerturno.dto;

/**
 *
 * @author 
 */
public class ComodinPresentacionDTO extends FichaPresentacionDTO{
    
    private String valor;

    public ComodinPresentacionDTO(String valor, int idFicha) {
        super(idFicha);
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
