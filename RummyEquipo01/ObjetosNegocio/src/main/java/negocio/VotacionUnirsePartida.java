
package negocio;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa la votación para que un nuevo jugador pueda unirse a la partida.
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class VotacionUnirsePartida {

    private String nombreSolicitante;
    private int votosFavor = 0;
    private int votosContra = 0;
    private int totalJugadores;
    private Set<String> nombresVotos = new HashSet<>();

    public VotacionUnirsePartida(String nombreSolicitante, int totalJugadoresEnSala) {
        this.nombreSolicitante = nombreSolicitante;
        this.totalJugadores = totalJugadores;
    }

    public boolean agregarVoto(String nombreVotante, boolean esFavor) {
        if (nombresVotos.contains(nombreVotante)) {
            return false;
        }
        
        nombresVotos.add(nombreVotante);
        
        if (esFavor){
            votosFavor++;
        }
        else{
            votosContra++;
        }
        
        return true;
    }

    public boolean esAceptado() {
        int mayoria = (totalJugadores / 2) + 1;
        return votosFavor >= mayoria;
    }

    public boolean esRechazado() {
        int mayoria = (totalJugadores / 2) + 1;
        return votosContra >= mayoria;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

}
