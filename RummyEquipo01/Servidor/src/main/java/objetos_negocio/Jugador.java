
package objetos_negocio;

import java.util.List;

/**
 * Clase que representa un jugador en el tablero
 * @author Juan Heras
 */
public class Jugador {
    
    private String avatar;
    private String nombre;
    private boolean primerTurno;
    private List<Ficha> fichas;

    public Jugador(String avatar, String nombre, boolean primerTurno, List<Ficha> fichas) {
        this.avatar = avatar;
        this.nombre = nombre;
        this.primerTurno = primerTurno;
        this.fichas = fichas;
    }

    public Jugador(String avatar, String nombre) {
        this.avatar = avatar;
        this.nombre = nombre;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isPrimerTurno() {
        return primerTurno;
    }

    public void setPrimerTurno(boolean primerTurno) {
        this.primerTurno = primerTurno;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(List<Ficha> fichas) {
        this.fichas = fichas;
    }
    
    public void quitarFichas(List<Ficha> fichasQuitar){
        
        fichas.removeAll(fichasQuitar);
        
    }
    
    
    
    
    
    
}
