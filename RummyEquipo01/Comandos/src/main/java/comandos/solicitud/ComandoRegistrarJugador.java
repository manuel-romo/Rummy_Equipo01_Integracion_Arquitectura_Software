/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comandos.solicitud;

import interfaces.IComando;

/**
 *
 * @author Juan Heras
 */
public class ComandoRegistrarJugador implements IComando {

    private final String type = "ComandoRegistrarJugador";
    private String nombreJugador;
    private String avatar;
    private String IP;

    public ComandoRegistrarJugador(String nombreJugador, String avatar) {
        this.nombreJugador = nombreJugador;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getTipo() {
        return type;
    }

    @Override
    public String getNombreJugador() {
        return nombreJugador;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

}
