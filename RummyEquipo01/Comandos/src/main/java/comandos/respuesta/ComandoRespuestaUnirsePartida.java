
package comandos.respuesta;

import interfaces.IComando;


public class ComandoRespuestaUnirsePartida implements IComando{
    
    private final String type = "ComandoRespuestaUnirsePartida";
    private String nombreJugador;
    private String mensaje;
    private String nombreJugadorSolicitante;

    public ComandoRespuestaUnirsePartida(String nombreJugador, String mensaje, String nombreJugadorSolicitante) {
        this.nombreJugador = nombreJugador;
        this.mensaje = mensaje;
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

    public String getMensaje() {
        return mensaje;
    }

    public String getNombreJugadorSolicitante() {
        return nombreJugadorSolicitante;
    }
    
    
}
