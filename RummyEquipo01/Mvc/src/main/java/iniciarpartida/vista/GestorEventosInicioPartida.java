
package iniciarpartida.vista;

import definiciones.IReceptorEventosIniciarPartida;
import definiciones.IGestorEventosInicioPartida;
import java.awt.Color;
import java.util.Map;


public class GestorEventosInicioPartida implements IGestorEventosInicioPartida{

    private IReceptorEventosIniciarPartida receptorEventos;
    
    @Override
    public void iniciarCreacionPartida(){
        receptorEventos.iniciarCreacionPartida();
    }
    
    @Override
    public void iniciarUnionPartida(){
        receptorEventos.iniciarUnionPartida();
    }
    
    // Registro de nombre de jugador
    @Override
    public void registrarNombreJugador(String nombre) {
        receptorEventos.registrarNombreJugador(nombre);
    }

    // Configuración de partida
    @Override
    public void enviarDatosConfigurarPartida(int maximoFichas, int numeroComodines) {
        receptorEventos.enviarDatosConfigurarPartida(maximoFichas, numeroComodines);
    }
    
    @Override
    public void volverInicio() {
        receptorEventos.volverInicio();
    }

    // Registro de jugador
    @Override
    public void iniciarRegistroJugador() {
        receptorEventos.iniciarRegistroJugador();
    }
    
    @Override
    public void enviarRegistroJugador(String avatarSeleccionado, Map<Integer, Color> mapaColores) {
        receptorEventos.enviarRegistroJugador(avatarSeleccionado, mapaColores);
    }

    // Solicitar unirse a la partida
    @Override
    public void solicitarUnirsePartdida() {
        receptorEventos.solicitarUnirsePartida();
    }
    
    @Override
    public void confirmarUnirsePartida(boolean cofirmacion) {
        receptorEventos.confirmarUnirsePartida(cofirmacion);
    }
    
    // Solicitud de inicio de juego
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
