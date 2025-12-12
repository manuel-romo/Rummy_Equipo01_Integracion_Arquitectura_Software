/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos.solicitud;

import interfaces.IComando;

/**
 *
 * @author pedro
 */
public class ComandoConfigurarPartida implements IComando {
    private final String type = "ComandoConfigurarPartida";
    private String nombreJugador;
    private final int maximoNumeroFichas;
    private final int numeroComodines;
    private String ip;
    private String puerto;

    public ComandoConfigurarPartida(String nombreJugador, int maximoNumeroFichas, int numeroComodines, String ip, String puerto) {
        this.nombreJugador = nombreJugador;
        this.maximoNumeroFichas = maximoNumeroFichas;
        this.numeroComodines = numeroComodines;
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

    public int getMaximoNumeroFichas() {
        return maximoNumeroFichas;
    }

    public int getNumeroComodines() {
        return numeroComodines;
    }

    public String getIp() {
        return ip;
    }

    public String getPuerto() {
        return puerto;
    }
    
    
    
    
}
