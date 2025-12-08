
package ejercerturno.dto;

/**
 *
 * @author romom
 */
public class JugadorExternoPresentacionDTO{
    private String avatar;
    private String nombre;
    private int fichasRestantes;
    private int numeroSecuencia;

    public JugadorExternoPresentacionDTO(String avatar, String nombre, int numeroSecuencia, int fichasRestantes) {
        this.avatar = avatar;
        this.nombre = nombre;
        this.fichasRestantes = fichasRestantes;
        this.numeroSecuencia = numeroSecuencia;
    }
    
    public JugadorExternoPresentacionDTO(String avatar, String nombre, int fichasRestantes) {
        this.avatar = avatar;
        this.nombre = nombre;
        this.fichasRestantes = fichasRestantes;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFichasRestantes() {
        return fichasRestantes;
    }

    public int getNumeroSecuencia() {
        return numeroSecuencia;
    }

    public void setNumeroSecuencia(int numeroSecuencia) {
        this.numeroSecuencia = numeroSecuencia;
    }
    
    

    
    
    
    
}
