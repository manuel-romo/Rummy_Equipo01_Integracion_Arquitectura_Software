
package comandos.respuesta;

import interfaces.IComando;



/**
 *
 * @author juanpheras
 */
public class ComandoRegistroFallido implements IComando {

    private final String type = "ComandoRegistroFallido";
    private String nombreJugador;
    private String mensaje;

    public ComandoRegistroFallido(String nombreJugador, String mensaje) {
        this.nombreJugador = nombreJugador;
        this.mensaje = mensaje;
    }
    
    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
    
    @Override
    public String getTipo() {
        return type;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }
    
}