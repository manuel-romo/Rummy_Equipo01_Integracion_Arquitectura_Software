
package iniciarpartida.vista;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class JugadorInicioPartidaInformacionPanel {
    
    private String nombre;
    private String avatar;

    public JugadorInicioPartidaInformacionPanel(String nombre, String avatar) {
        this.nombre = nombre;
        this.avatar = avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAvatar() {
        return avatar;
    }
    
}
