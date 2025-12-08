
package ejercerturno.vista;

import java.awt.Color;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class FichaInformacionPanel {
    
    private Integer id;
    private String valor;
    private Color color;

    public FichaInformacionPanel(Integer id, String valor, Color color) {
        this.id = id;
        this.valor = valor;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public String getValor() {
        return valor;
    }

    public Color getColor() {
        return color;
    }
    
}
