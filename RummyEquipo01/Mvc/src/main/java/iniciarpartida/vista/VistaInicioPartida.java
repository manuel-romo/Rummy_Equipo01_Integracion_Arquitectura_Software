
package iniciarpartida.vista;

import definiciones.IModelo;
import definiciones.IReceptorEventosIniciarPartida;
import definiciones.ISuscriptor;
import iniciarpartida.controlador.ControladorInicioPartida;
import java.awt.Dimension;
import javax.swing.JFrame;
import definiciones.IModeloInicioPartida;
import iniciarpartida.dto.EtapaActual;
import iniciarpartida.dto.JugadorInicioPartidaPresentacionDTO;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class VistaInicioPartida extends JFrame implements ISuscriptor, IReceptorEventosIniciarPartida{
    
    private ControladorInicioPartida controlador;
    private Dimension TAMANIO_VENTANA = new Dimension(900, 800);
    private final String TITULO_VENTANA = "Rummy";
    private final String ICONO_VENTANA = "/iconoVentanaEjercerTurno.png";
    private PanelSalaEspera panelSalaEspera;
    
    public VistaInicioPartida(ControladorInicioPartida controlador, PanelSalaEspera panelSalaEspera){
        
        this.controlador = controlador;
        this.panelSalaEspera = panelSalaEspera;
        setResizable(false);
        setTitle(TITULO_VENTANA);
        
        Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource(ICONO_VENTANA));
        setIconImage(icono);
        
        this.setSize(TAMANIO_VENTANA);
        this.setLocationRelativeTo(null);
        
        this.add(panelSalaEspera);
         
    }
    
    private List<JugadorInicioPartidaInformacionPanel> obtenerJugadoresInformacion(List<JugadorInicioPartidaPresentacionDTO> jugadores){
        
        List<JugadorInicioPartidaInformacionPanel> jugadoresInformacion = new LinkedList<>();
        
        if(jugadores == null){
            return null;
        }
        
        for(JugadorInicioPartidaPresentacionDTO jugadorInicioPartidaPresentacion: jugadores){
            
            jugadoresInformacion.add(
                    new JugadorInicioPartidaInformacionPanel(
                            jugadorInicioPartidaPresentacion.getNombre(), 
                            jugadorInicioPartidaPresentacion.getAvatar()));
            
        }
        
        return jugadoresInformacion;
        
    }
    
    private void hacerVisible(boolean vistaVisible){
        setVisible(vistaVisible);
    }

    @Override
    public void actualizar(IModelo modelo) {
        
        IModeloInicioPartida modeloInicioTurno = (IModeloInicioPartida)modelo;
        
        String mensaje = modeloInicioTurno.obtenerMensaje();
        List<JugadorInicioPartidaPresentacionDTO> jugadores = modeloInicioTurno.obtenerJugadores();
        EtapaActual etapaActual = modeloInicioTurno.obtenerEtapaActual();
        int cantidadJugadoresIniciarJuego = modeloInicioTurno.obtenerCantidadJugadoresIniciarJuego();
        
        boolean vistaVisible = modeloInicioTurno.isVistaVisible();
        
        hacerVisible(vistaVisible);
        
        if(etapaActual != null){
            
            switch (etapaActual) {
                case EtapaActual.SALA_ESPERA:

                    List<JugadorInicioPartidaInformacionPanel> jugadoresInformacion = obtenerJugadoresInformacion(jugadores);

                    panelSalaEspera.actualizar(jugadoresInformacion, mensaje, cantidadJugadoresIniciarJuego);

                    break;
                default:
                    throw new AssertionError();
            }
            
        }
        
    }

    @Override
    public void solicitarInicioJuego(int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
        controlador.solicitarInicioJuego(MAXIMO_NUMERO_FICHAS,NUMERO_COMODINES);
    }

    @Override
    public void confirmarEnvioSolicitudInicioJuego(boolean confirmacion,int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
        controlador.confirmarEnvioSolicitudInicioJuego(confirmacion,MAXIMO_NUMERO_FICHAS,NUMERO_COMODINES);
    }

    @Override
    public void confirmarInicioJuego(boolean confirmacion,int MAXIMO_NUMERO_FICHAS, int NUMERO_COMODINES) {
       controlador.confirmarInicioJuego(confirmacion,MAXIMO_NUMERO_FICHAS,NUMERO_COMODINES);
    } 

     @Override
    public void aceptarAceptacionInicioJuego() {
        controlador.aceptarAceptacionInicioJuego();
    }
    
}
