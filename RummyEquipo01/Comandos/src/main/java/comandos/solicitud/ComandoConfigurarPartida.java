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
    private final String tipo = "ComandoConfigurarPartida";
    private String nombreJugador;
    private final int maximoNumeroFichas;
    private final int numeroComodines;
    

    public ComandoConfigurarPartida(String nombreJugador, int maximoNumeroFichas, int numeroComodines) {
        this.nombreJugador = nombreJugador;
        this.maximoNumeroFichas = maximoNumeroFichas;
        this.numeroComodines = numeroComodines;
    }
    
    
    @Override
    public String getTipo() {
        return tipo;
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
    
    
    
    
}
