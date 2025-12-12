
package comandos.respuesta;

import interfaces.IComando;

/**
 *
 * @author pedro
 */
public class ComandoPartidaConfigurada implements IComando {
    private String type = "ComandoPartidaConfigurada";
    private String nombreJugador;
    private Boolean exito;
    private String mensaje;

    public ComandoPartidaConfigurada(String nombreJugador, Boolean exito, String mensaje) {
        this.nombreJugador = nombreJugador;
        this.exito = exito;
        this.mensaje = mensaje;
    }
    

    public Boolean getExito() {
        return exito;
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
