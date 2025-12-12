
package comandos.solicitud;

import interfaces.IComando;


public class ComandoConfirmacionUnirsePartida implements IComando {
    
    private final String type = "ComandoConfirmacionUnirsePartida";
    private String nombreJugador;
    private boolean respuesta;
    private String nombreJugadorSolicitante;

    public ComandoConfirmacionUnirsePartida(String nombreJugador, boolean respuesta, String nombreJugadorSolicitante) {
        this.nombreJugador = nombreJugador;
        this.respuesta = respuesta;
        this.nombreJugadorSolicitante = nombreJugadorSolicitante;
    }
 
    @Override
    public String getTipo() {
        return type;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public String getNombreJugadorSolicitante() {
        return nombreJugadorSolicitante;
    }
    
}
