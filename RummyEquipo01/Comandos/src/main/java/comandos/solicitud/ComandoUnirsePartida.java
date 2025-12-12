
package comandos.solicitud;

import interfaces.IComando;


public class ComandoUnirsePartida implements IComando{
    
    private final String type = "ComandoUnirsePartida";
    private String nombreJugador;
    private String ip;
    private String puerto;

    public ComandoUnirsePartida(String nombreJugador, String ip, String puerto) {
        this.nombreJugador = nombreJugador;
        this.ip = ip;
        this.puerto = puerto;
    }
 
    @Override
    public String getTipo() {
        return type;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }

    public String getIp() {
        return ip;
    }

    public String getPuerto() {
        return puerto;
    }
}
