/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos.respuesta;

import interfaces.IComando;

/**
 *
 * @author pedro
 */
public class ComandoPartidaConfigurada implements IComando {
    private String tipo = "ComandoPartidaConfigurada";
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
        return tipo;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }
}
