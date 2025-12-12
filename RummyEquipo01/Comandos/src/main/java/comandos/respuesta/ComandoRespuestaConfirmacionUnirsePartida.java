
package comandos.respuesta;

import interfaces.IComando;


public class ComandoRespuestaConfirmacionUnirsePartida implements IComando{
    
    private final String type = "ComandoRespuestaConfirmacionUnirsePartida";
    private String nombreJugador;
    private String mensaje;
    private boolean confirmacion;

    public ComandoRespuestaConfirmacionUnirsePartida(String nombreJugador, String mensaje, boolean confirmacion) {
        this.nombreJugador = nombreJugador;
        this.mensaje = mensaje;
        this.confirmacion = confirmacion;
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

    public boolean isConfirmacion() {
        return confirmacion;
    }
}
