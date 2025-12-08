
package ejercerturno.vista;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class JugadorExternoInformacionPanel {
    private String avatar;
    private String nombre;
    private String fichasRestantes;
    private int numeroSecuencia;

    public JugadorExternoInformacionPanel(String avatar, String nombre, String fichasRestantes, int numeroSecuencia) {
        this.avatar = avatar;
        this.nombre = nombre;
        this.fichasRestantes = fichasRestantes;
        this.numeroSecuencia = numeroSecuencia;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFichasRestantes() {
        return fichasRestantes;
    }

    public int getNumeroSecuencia() {
        return numeroSecuencia;
    }
    
    
}
