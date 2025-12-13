
package ejercerturno.dto;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class JugadorPuntuacionPresentacionDTO {
    
    private String nombre;
    private String avatar;
    private String puntaje;

    public JugadorPuntuacionPresentacionDTO(String nombre, String avatar, String puntaje) {
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

    public String getPuntaje() {
        return puntaje;
    }
    
}
