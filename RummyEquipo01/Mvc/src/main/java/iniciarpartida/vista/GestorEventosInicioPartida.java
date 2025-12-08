
package iniciarpartida.vista;

import definiciones.IReceptorEventosIniciarPartida;
import definiciones.IGestorEventosInicioPartida;


public class GestorEventosInicioPartida implements IGestorEventosInicioPartida{

    private IReceptorEventosIniciarPartida receptorEventos;
    
    @Override
    public void solicitarInicioJuego() {
        receptorEventos.solicitarInicioJuego();
    }

    @Override
    public void confirmarEnvioSolicitudInicioJuego(boolean confirmacion) {
        receptorEventos.confirmarEnvioSolicitudInicioJuego(confirmacion);
    }

    @Override
    public void confirmarInicioJuego(boolean confirmacion) {
        receptorEventos.confirmarInicioJuego(confirmacion);
    }
    
    @Override
    public void aceptarAceptacionInicioJuego() {
        receptorEventos.aceptarAceptacionInicioJuego();
    }

    public void setReceptorEventos(IReceptorEventosIniciarPartida receptorEventos) {
        this.receptorEventos = receptorEventos;
    } 
    
}
