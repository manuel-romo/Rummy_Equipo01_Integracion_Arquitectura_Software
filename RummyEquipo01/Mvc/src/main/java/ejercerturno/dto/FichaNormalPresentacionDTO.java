
package ejercerturno.dto;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class FichaNormalPresentacionDTO extends FichaPresentacionDTO{
    
    private int valor;
    private ColorFichaPresentacionDTO color;

    public FichaNormalPresentacionDTO(int valor, int idFicha, ColorFichaPresentacionDTO color) {
        super(idFicha);
        this.valor = valor;
        this.color = color;
    }

    public int getValor() {
        return valor;
    }

    public ColorFichaPresentacionDTO getColor() {
        return color;
    }
    
    
    
}
