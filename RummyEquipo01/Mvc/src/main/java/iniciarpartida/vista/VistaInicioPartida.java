
package iniciarpartida.vista;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
import definiciones.IModelo;
import definiciones.IReceptorEventosIniciarPartida;
import definiciones.ISuscriptor;
import iniciarpartida.controlador.ControladorInicioPartida;
import java.awt.Dimension;
import javax.swing.JFrame;
import definiciones.IModeloInicioPartida;
import iniciarpartida.dto.EstadoPartida;
import iniciarpartida.dto.EtapaActual;
import iniciarpartida.dto.JugadorInicioPartidaPresentacionDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class VistaInicioPartida extends JFrame implements ISuscriptor, IReceptorEventosIniciarPartida{
    
    private ControladorInicioPartida controlador;
    private Dimension TAMANIO_VENTANA = new Dimension(900, 800);
    private final String TITULO_VENTANA = "Rummy";
    private final String ICONO_VENTANA = "/iconoVentanaEjercerTurno.png";
    
    private PanelPrincipal panelPrincipal;
    private PanelRegistroNombreJugador panelRegistroNombreJugador;
    private PanelConfiguracionPartida panelConfiguracionPartida;
    private PanelRegistroJugador panelRegistroJugador;
    private PanelIngresoIP panelIngresoIP;
    private PanelSalaEspera panelSalaEspera;
    
    public VistaInicioPartida(
            ControladorInicioPartida controlador, 
            PanelPrincipal panelPrincipal,
            PanelRegistroNombreJugador panelRegistroNombreJugador,
            PanelConfiguracionPartida panelConfiguracionPartida,
            PanelRegistroJugador panelRegistroJugador,
            PanelIngresoIP panelIngresoIP,
            PanelSalaEspera panelSalaEspera){
        
        this.controlador = controlador;
        this.panelPrincipal = panelPrincipal;
        this.panelRegistroNombreJugador = panelRegistroNombreJugador;
        this.panelConfiguracionPartida = panelConfiguracionPartida;
        this.panelRegistroJugador = panelRegistroJugador;
        this.panelIngresoIP = panelIngresoIP;
        this.panelSalaEspera = panelSalaEspera;
        
        
        setResizable(false);
        setTitle(TITULO_VENTANA);
        
        Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource(ICONO_VENTANA));
        setIconImage(icono);
        
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(TAMANIO_VENTANA);
        this.setLocationRelativeTo(null);
         
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
        
        SwingUtilities.invokeLater(() -> {
            
            IModeloInicioPartida modeloInicioPartida = (IModeloInicioPartida) modelo;

            // Configuración de aprtida
            EstadoPartida estadoPartida = modeloInicioPartida.obtenerEstadoPartida();
            String mesajeConfiguracionPartida = modeloInicioPartida.obtenerMensaje();
            String mensaje = modeloInicioPartida.obtenerMensaje();
            
            // Registro de jugador
            String nombreJugador = modeloInicioPartida.obtenerNombreJugador();
            
            // Solicitud de inicio de partida
            List<JugadorInicioPartidaPresentacionDTO> jugadores = modeloInicioPartida.obtenerJugadores();
            EtapaActual etapaActual = modeloInicioPartida.obtenerEtapaActual();
            int cantidadJugadoresIniciarJuego = modeloInicioPartida.obtenerCantidadJugadoresIniciarJuego();
            
            boolean juegoIniciado = modeloInicioPartida.isJuegoIniciado();
            
            boolean vistaVisible = modeloInicioPartida.isVistaVisible();

            hacerVisible(vistaVisible);
            
            if (etapaActual != null) {

                this.getContentPane().removeAll();

                switch (etapaActual) {
                    case EtapaActual.INICIO:
                        this.getContentPane().add(panelPrincipal, BorderLayout.CENTER);
                        break;

                    case EtapaActual.REGISTRO_NOMBRE_JUGADOR:
                        this.getContentPane().add(panelRegistroNombreJugador, BorderLayout.CENTER);
                        break;

                    case EtapaActual.CONFIGURACION_PARTIDA:
                        if (estadoPartida != null) {
                            panelConfiguracionPartida.actualizar(estadoPartida, mesajeConfiguracionPartida);
                        }
                        this.getContentPane().add(panelConfiguracionPartida, BorderLayout.CENTER);
                        break;

                    case EtapaActual.REGISTRO_JUGADOR:

                        panelRegistroJugador.actualizar(nombreJugador);
                        this.getContentPane().add(panelRegistroJugador, BorderLayout.CENTER);
                        panelRegistroJugador.setVisible(true);
 
                        break;
                        
                    case EtapaActual.REGISTRO_IP:
                        
                        this.getContentPane().add(panelIngresoIP, BorderLayout.CENTER);
                        panelIngresoIP.mostrar(mensaje);
                        break;

                    case EtapaActual.SALA_ESPERA:
                        
                        this.getContentPane().add(panelSalaEspera, BorderLayout.CENTER);
                        List<JugadorInicioPartidaInformacionPanel> jugadoresInformacion = obtenerJugadoresInformacion(jugadores);
                        panelSalaEspera.actualizar(jugadoresInformacion, mensaje, cantidadJugadoresIniciarJuego);
                        break;

                    default:
                        break;
                }

                this.getContentPane().revalidate();
                this.getContentPane().repaint();
            }
            
            if(juegoIniciado){
                controlador.aceptarAceptacionInicioJuego();
                
            }
            
            
        });
        
    }
    
    @Override
    public void iniciarCreacionPartida() {
        controlador.iniciarCreacionPartida();
    }
    
    @Override
    public void iniciarUnionPartida() {
        controlador.iniciarUnionPartida();
    }
    
    @Override
    public void registrarNombreJugador(String nombre) {
        controlador.registrarNombreJugador(nombre);
    }

    @Override
    public void enviarDatosConfigurarPartida(int maximoFichas, int numeroComodines) {
        controlador.enviarDatosPartidaConfigurada(maximoFichas, numeroComodines);
    }
    
    @Override
    public void volverInicio() {
        controlador.volverInicio();
    }
    
    @Override
    public void solicitarUnirsePartida() {
        controlador.solicitarUnirsePartida();
    }
    
    @Override
    public void confirmarUnirsePartida(boolean confirmacion) {
        controlador.confirmarUnirsePartida(confirmacion);
    }

    @Override
    public void enviarRegistroJugador(String avatarSeleccionado, Map<Integer, Color> mapaColores) {
        controlador.enviarRegistroJugador(avatarSeleccionado, mapaColores);
    }

    @Override
    public void solicitarInicioJuego() {
        controlador.solicitarInicioJuego();
    }

    @Override
    public void confirmarEnvioSolicitudInicioJuego(boolean confirmacion) {
        controlador.confirmarEnvioSolicitudInicioJuego(confirmacion);
    }

    @Override
    public void confirmarInicioJuego(boolean confirmacion) {
       controlador.confirmarInicioJuego(confirmacion);
    } 

     @Override
    public void aceptarAceptacionInicioJuego() {
        controlador.aceptarAceptacionInicioJuego();
    }

    @Override
    public void iniciarRegistroJugador() {
        controlador.iniciarRegistroJugador();
    }

    
}

