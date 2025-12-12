
package ensamblador;

import cliente.ColaMensajesEnviar;
import cliente.GestorConexiones;
import com.formdev.flatlaf.FlatIntelliJLaf;
import deserializador.Deserializador;
import directorioServidor.DirectorioServidor;
import ejercerturno.controlador.ControladorEjercerTurno;
import ejercerturno.modelo.ModeloEjercerTurno;
import interfaces.ISuscriptor;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ejercerturno.vista.GestorEventos;
import ejercerturno.vista.IComponente;
import ejercerturno.vista.IGestorEventos;
import ejercerturno.vista.PanelCasilla;
import ejercerturno.vista.PanelJugadorExterno;
import ejercerturno.vista.PanelJugadorPrincipal;
import ejercerturno.vista.PanelMesaJuego;
import ejercerturno.vista.PanelMonton;
import ejercerturno.vista.PanelMovimiento;
import ejercerturno.vista.PanelTablero;
import ejercerturno.vista.PosicionPanel;
import ejercerturno.vista.VistaMesaJuego;
import fachada.FachadaMvc;
import iniciarpartida.controlador.ControladorInicioPartida;
import iniciarpartida.modelo.ModeloInicioPartida;
import iniciarpartida.vista.GestorEventosInicioPartida;
import iniciarpartida.vista.PanelSalaEspera;
import iniciarpartida.vista.VistaInicioPartida;
import serializador.Serializador;
import servidor.ColaMensajesRecibidos;
import servidor.Servidor;
import definiciones.IReceptorEventosEjercerTurno;
import iniciarpartida.vista.PanelConfiguracionPartida;
import iniciarpartida.vista.PanelIngresoIP;
import iniciarpartida.vista.PanelPrincipal;
import iniciarpartida.vista.PanelRegistroJugador;
import iniciarpartida.vista.PanelRegistroNombreJugador;
import utils.DireccionUtils;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class EnsambladorCliente {

    private static final String PUERTO_SERVIDOR = "50000";
    private static final String DIRECCION_IP_SERVIDOR = "127.0.0.1";
    
    private static final String PUERTO = "0";
    
    private static final int TOTAL_CASILLAS_TABLERO =500;
    private static final int TOTAL_CASILLAS_MANO = 14;
    
    public static void main(String[] args) {
        
        try {
            FlatIntelliJLaf.setup(); 

        } catch (Exception e) {}
        
//        Scanner escaner = new Scanner(System.in);
//        System.out.print("Ingrese el nombre del Jugador: ");
//        String nombreJugador = escaner.nextLine();
//        
//        System.out.print("Ingrese la dirección IP del servidor: ");
//        String ipServidor = escaner.nextLine();
//        
//        String puertoCliente = null;
//        
//        String nombreJugador2 = "a";
//        String direccionImagenAvatarJugador2 = null;
//        
//        if(nombreJugador.equals("Francisco34")){
//            
//            puertoCliente = "51000";
//            
//            nombreJugador2 = "Sandy43";
//            direccionImagenAvatarJugador2 = "avatar2.png";
//        }
//        
//        if(nombreJugador.equals("Sandy43")){
//            
//            puertoCliente = "52000";
//            
//            nombreJugador2 = "Francisco34";
//            direccionImagenAvatarJugador2 = "avatar1.png";
//            
//        }
        
        // Inicialización de datos MVC inicio partida.
        
        ModeloInicioPartida modeloInicioPartida = new ModeloInicioPartida();
        
        ControladorInicioPartida controladorInicioPartida = new ControladorInicioPartida(modeloInicioPartida);
        
        GestorEventosInicioPartida gestorEventosIniciarPartida = new GestorEventosInicioPartida();
        
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        PanelRegistroNombreJugador panelRegistroNombreJugador = new PanelRegistroNombreJugador();
        PanelConfiguracionPartida panelConfiguracionPartida = new PanelConfiguracionPartida();
        PanelRegistroJugador panelRegistroJugador = new PanelRegistroJugador();
        PanelIngresoIP panelIngresoIP = new PanelIngresoIP();
        PanelSalaEspera panelSalaEspera = new PanelSalaEspera();
        
        panelPrincipal.setGestorEventos(gestorEventosIniciarPartida);
        panelRegistroNombreJugador.setGestorEventos(gestorEventosIniciarPartida);
        panelConfiguracionPartida.setGestorEventos(gestorEventosIniciarPartida);
        panelRegistroJugador.setGestorEventos(gestorEventosIniciarPartida);
        panelIngresoIP.setGestorEventos(gestorEventosIniciarPartida);
        panelSalaEspera.setGestorEventos(gestorEventosIniciarPartida);
        
        VistaInicioPartida vistaInicioPartida = new VistaInicioPartida(
                controladorInicioPartida,
                panelPrincipal,
                panelRegistroNombreJugador,
                panelConfiguracionPartida,
                panelRegistroJugador,
                panelIngresoIP,
                panelSalaEspera);
        
        gestorEventosIniciarPartida.setReceptorEventos(vistaInicioPartida);
        
        modeloInicioPartida.suscribirse(vistaInicioPartida);
        
        
        // Inicialización de datos MVC ejercer turno.
        // Creación de clases de componentes
        
        PanelCasilla[] panelesCasillaTablero = new PanelCasilla[TOTAL_CASILLAS_TABLERO];

        for (int i = 0; i < TOTAL_CASILLAS_TABLERO; i++) {
            panelesCasillaTablero[i] = new PanelCasilla(i + 1);
        }
        
        
        IComponente panelTablero = new PanelTablero(panelesCasillaTablero);
        
        List<PanelCasilla> panelesCasillaJugador = new LinkedList<>();
         
        for (int i = 0; i < TOTAL_CASILLAS_MANO; i++) {
            panelesCasillaJugador.add(new PanelCasilla(i + 1));
        }
        
        IComponente panelJugadorPrincipal = new PanelJugadorPrincipal(panelesCasillaJugador);
        IComponente panelJugadorExterno1 = new PanelJugadorExterno(PosicionPanel.CENTRO_ARRIBA, "prueba1", "avatar1.png");
//        IComponente panelJugadorExterno2 = new PanelJugadorExterno(PosicionPanel.DERECHA_CENTRO);
//        IComponente panelJugadorExterno3 = new PanelJugadorExterno(PosicionPanel.IZQUIERDA_CENTRO);
        
        IComponente panelMesaJuego = new PanelMesaJuego();
        IComponente panelMonton = new PanelMonton();
        
        panelMesaJuego.agregarComponente(panelMonton);
        panelMesaJuego.agregarComponente(panelTablero);
        panelMesaJuego.agregarComponente(panelJugadorExterno1);
//        panelMesaJuego.agregarComponente(panelJugadorExterno2);
//        panelMesaJuego.agregarComponente(panelJugadorExterno3);
        panelMesaJuego.agregarComponente(panelJugadorPrincipal);

        Map<Integer, Color> mapaColoresSeleccionados = Map.of(
            1, Color.RED,
            2, Color.BLUE,
            3, Color.GREEN,
            4, Color.DARK_GRAY
        );      
        
        Map<Integer,Integer> mapaIdsCasillasPanelesJugador = new HashMap<>();
        
        
        Map<Integer,Integer> mapaIdsCasillasPanelesTablero = new HashMap<>();
        
        for (int i = 0; i < TOTAL_CASILLAS_TABLERO; i++) {
            mapaIdsCasillasPanelesTablero.put(i + 1, null);
        }
        
        ModeloEjercerTurno modeloEjercerTurno = new ModeloEjercerTurno();
        ControladorEjercerTurno controladorEjercerTurno = new ControladorEjercerTurno(modeloEjercerTurno);
        
        controladorInicioPartida.setControladorEjercerTurno(controladorEjercerTurno);
        
        IComponente panelMovimiento = new PanelMovimiento();
        
        VistaMesaJuego vistaMesaJuego = new VistaMesaJuego(
                controladorEjercerTurno,
                panelMesaJuego, 
                panelMovimiento,
                mapaColoresSeleccionados,
                mapaIdsCasillasPanelesTablero,
                mapaIdsCasillasPanelesJugador);
        
        modeloEjercerTurno.suscribirse(vistaMesaJuego);
                
        IGestorEventos gestorEventos = new GestorEventos(vistaMesaJuego, (IReceptorEventosEjercerTurno)vistaMesaJuego);
        
        ((PanelMesaJuego)panelMesaJuego).setGestorEventos(gestorEventos);
        ((PanelTablero) panelTablero).setGestorEventos(gestorEventos);
        ((PanelJugadorPrincipal) panelJugadorPrincipal).setGestorEventos(gestorEventos);
        ((PanelMonton)panelMonton).setGestorEventos(gestorEventos);
        ((PanelMovimiento) panelMovimiento).setGestorEventos(gestorEventos);

        
        Serializador serializadorCliente = new Serializador();

        DirectorioServidor directorioServidor = new DirectorioServidor(new String[]{DIRECCION_IP_SERVIDOR, PUERTO_SERVIDOR});
 
        ISuscriptor gestorConexiones = new GestorConexiones(); 
        
        ColaMensajesEnviar colaMensajesEnviar = new ColaMensajesEnviar();
        
        colaMensajesEnviar.setSuscriptor(gestorConexiones);
        
        new Thread(colaMensajesEnviar).start();
        
        ColaMensajesRecibidos colaMensajesRecibidos = new ColaMensajesRecibidos();
        
        Servidor servidor = new Servidor(Integer.valueOf(PUERTO));
        
        DireccionUtils.setPuerto(String.valueOf(servidor.obtenerPuerto()));
        
        servidor.setReceptor(colaMensajesRecibidos);
        
        new Thread(servidor).start();
        new Thread(colaMensajesRecibidos).start();

        Deserializador deserializadorCliente = new Deserializador();
        
        
        // Conexión de componentes (Envío):
        
        FachadaMvc fachadaMvc = new FachadaMvc();
        
        modeloEjercerTurno.setFachadaMvc(fachadaMvc);
        modeloInicioPartida.setFachadaMvc(fachadaMvc);
        
        fachadaMvc.setModeloEjercerTurno(modeloEjercerTurno);
        fachadaMvc.setModeloInicioPartida(modeloInicioPartida);
        
        fachadaMvc.setFiltroSiguiente(serializadorCliente);
        
        serializadorCliente.setFiltroSiguiente(directorioServidor);
        
        directorioServidor.setDispatcher(colaMensajesEnviar);
        
        // Conexión de componentes (Recepción):
        colaMensajesRecibidos.setReceptor(deserializadorCliente);
        
        deserializadorCliente.setFiltroSiguiente(fachadaMvc);
        
        modeloInicioPartida.iniciarInicio();
        
    }
}
