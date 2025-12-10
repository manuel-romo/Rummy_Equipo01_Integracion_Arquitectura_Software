
package iniciarpartida.vista;

import definiciones.IReceptorEventosIniciarPartida;
import definiciones.IGestorEventosInicioPartida;


public class GestorEventosInicioPartida implements IGestorEventosInicioPartida{

    private IReceptorEventosIniciarPartida receptorEventos;
    
    @Override
    public void solicitarInicioJuego(int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
        receptorEventos.solicitarInicioJuego(MAXIMO_NUMERO_FICHAS,NUMERO_COMODINES);
    }

    @Override
    public void confirmarEnvioSolicitudInicioJuego(boolean confirmacion,int MAXIMO_NUMERO_FICHAS,int NUMERO_COMODINES) {
        receptorEventos.confirmarEnvioSolicitudInicioJuego(confirmacion,MAXIMO_NUMERO_FICHAS,NUMERO_COMODINES);
    }
    @Override
    public void confirmarInicioJuego(boolean confirmacion,int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
        receptorEventos.confirmarInicioJuego(confirmacion,MAXIMO_NUMERO_FICHAS,NUMERO_COMODINES);
    }
    
    @Override
    public void aceptarAceptacionInicioJuego() {
        receptorEventos.aceptarAceptacionInicioJuego();
    }

    public void setReceptorEventos(IReceptorEventosIniciarPartida receptorEventos) {
        this.receptorEventos = receptorEventos;
    } 

}
