/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos.respuesta;

import interfaces.IComando;


/**
 *
 * @author Juan Heras
 */
public class ComandoRegistroExitoso implements IComando {
    private final String type = "ComandoRegistroExitoso";
    private String nombreJugador;
    private String IP;
    private Integer PORT;

    public ComandoRegistroExitoso(String nombreJugador) {
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