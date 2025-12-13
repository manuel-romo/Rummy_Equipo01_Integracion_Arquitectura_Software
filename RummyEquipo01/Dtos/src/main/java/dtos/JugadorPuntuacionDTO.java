
package dtos;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class JugadorPuntuacionDTO {
    private String nombre;
    private String avatar;
    private int puntaje;

    public JugadorPuntuacionDTO(String nombre, String avatar, int puntaje) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getPuntaje() {
        return puntaje;
    }
    
}
