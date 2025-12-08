
package iniciarpartida.dto;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class JugadorInicioPartidaPresentacionDTO {
    
    private String nombre;
    private String avatar;

    public JugadorInicioPartidaPresentacionDTO(String nombre, String avatar) {
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
