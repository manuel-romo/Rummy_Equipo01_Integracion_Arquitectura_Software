
package dto;

public class JugadorInicioPartidaDTO {
    
    private String nombre;
    private String avatar;

    public JugadorInicioPartidaDTO(String nombre, String avatar) {
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

