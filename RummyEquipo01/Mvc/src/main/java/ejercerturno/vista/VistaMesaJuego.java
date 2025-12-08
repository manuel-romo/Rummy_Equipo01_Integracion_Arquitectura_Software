
package ejercerturno.vista;

import definiciones.IModelo;
import definiciones.ISuscriptor;
import ejercerturno.controlador.ControladorEjercerTurno;
import ejercerturno.dto.ColorFichaPresentacionDTO;
import ejercerturno.dto.ComodinPresentacionDTO;
import ejercerturno.dto.FichaNormalPresentacionDTO;
import ejercerturno.dto.FichaPresentacionDTO;
import ejercerturno.dto.JugadorExternoPresentacionDTO;
import ejercerturno.dto.JugadorPrincipalPresentacionDTO;
import ejercerturno.dto.MontonPresentacionDTO;
import ejercerturno.dto.TableroPresentacionDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import ejercerturno.vista.IComponente;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ejercerturno.vista.EstadoActual;
import ejercerturno.vista.FichaInformacionPanel;
import ejercerturno.vista.JugadorExternoInformacionPanel;
import ejercerturno.vista.JugadorPrincipalInformacionPanel;
import ejercerturno.vista.MontonInformacionPanel;
import ejercerturno.vista.TableroInformacionPanel;
import ejercerturno.vista.VisitorPintar;
import definiciones.IModeloEjercerTurno;
import definiciones.IReceptorEventosEjercerTurno;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class VistaMesaJuego extends JFrame implements ISuscriptor, IReceptorEventosEjercerTurno{
    
    private List<IComponente> componentes = new LinkedList<>();
    
    private Dimension tamanioVentanaVista = new Dimension(1000, 800);
    private ControladorEjercerTurno controlador;
    
    private Color COLOR_COMODIN = Color.BLACK;
    
    private Map<Integer, Color> mapaColoresJugador;
    
    /**
     * Mapa con clave igual al id de Casilla y valor igual al id de Ficha, en el tablero.
     */
    private Map<Integer, Integer> mapaIdsCasillasFichasTablero;
    
    /**
     * Mapa con clave igual al id de Casilla y valor igual al id de Ficha, en la mano del jugador.
     */
    private Map<Integer, Integer> mapaIdsCasillasFichasJugador;
    
    private Integer[] idsCasillasAgregarJugador;
    
    private Integer[] idsCasillasAgregarTablero;
    private Integer[] idsCasillasQuitarTablero;
    
    private Integer[] idsCasillasQuitarJugador;
    
    private Integer[] idsFichasAgregar;
    private Integer[] idsFichasQuitar;
    
    private boolean movimientoInvalido;
    private boolean tableroInvalido;
    
    private final int ANCHO_FILA = 25;
    private final int CANTIDAD_CASILLAS_MAXIMA_CARGA_FILA = 10;
    
    private int minimoCasillasJugador;
    
    private final String TITULO_VENTANA = "Rummy";
    private final String ICONO_VENTANA = "/iconoVentanaEjercerTurno.png";
    
    public VistaMesaJuego(
            ControladorEjercerTurno controlador,
            IComponente componente,
            IComponente componenteGlassPane,
            Map<Integer, Color> mapaColoresJugador, 
            Map<Integer, Integer> mapaIdsCasillasFichasTablero, 
            Map<Integer, Integer> mapaIdsCasillasFichasMano){
        
        this.controlador = controlador;
        this.mapaColoresJugador = mapaColoresJugador;
        this.mapaIdsCasillasFichasTablero = mapaIdsCasillasFichasTablero;
        this.mapaIdsCasillasFichasJugador = mapaIdsCasillasFichasMano;
        this.minimoCasillasJugador = mapaIdsCasillasFichasMano.size();
        
        setSize(tamanioVentanaVista);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(TITULO_VENTANA);
        
        Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource(ICONO_VENTANA));
        setIconImage(icono);

        agregarPanelComponente(componente);
        agregarGlassPane(componenteGlassPane);
        
        agregarComponente(componente);
        agregarComponente(componenteGlassPane);
    }
    
    public void agregarPanelComponente(IComponente componente){
        add((JPanel)componente);
 
    }
    
    public void agregarGlassPane(IComponente componente){
        this.setGlassPane((JPanel)componente);
    }
    
    public void agregarComponente(IComponente componente){
        componentes.add(componente);
    }

    
    @Override
    public void quitarFichasJugador(Integer[] idsCasillas, Integer[] idsFichas){
        
        idsCasillasQuitarJugador = idsCasillas;
        idsFichasQuitar = idsFichas;
        
        controlador.quitarFichasJugador(idsFichas);
    }
    
    @Override
    public void quitarFichasTablero(Integer[] idsCasillas, Integer[] idsFichas){
        
        idsCasillasQuitarTablero = idsCasillas;
        
        controlador.quitarFichasTablero(idsFichas);
    }
    
    @Override
    public void agregarFichasJugador(Integer[] idsCasillas, Integer[] idsFichas) {
        
        idsCasillasAgregarJugador = idsCasillas;
        idsFichasAgregar = idsFichas;
        
        controlador.agregarFichasJugador(idsFichas);
        
    }
    
    @Override
    public void agregarFichasTablero(Integer[] idsCasillas, Integer[] idsFichas, Integer[] idsFichasGrupo){
        idsCasillasAgregarTablero = idsCasillas;
        idsFichasAgregar = idsFichas;
        
        controlador.agregarFichasTablero(idsFichas, idsFichasGrupo);
    }
    
    @Override
    public void agregarFichasTablero(Integer[] idsCasillas, Integer[] idsFichas){
        idsCasillasAgregarTablero = idsCasillas;
        idsFichasAgregar = idsFichas;
        
        controlador.agregarFichasTablero(idsFichas);
    }
    
    @Override
    public void tomarFicha() {
        controlador.tomarFicha();
    }

    @Override
    public void reestablecerTablero() {
        controlador.reestablecerTablero();
    }
    
    @Override
    public void terminarTurno(){
        controlador.terminarTurno();
    }
    
    @Override
    public void abandonarPartida() {
        controlador.abandonarPartida();
    }

    @Override
    public void finalizarPartida() {
        controlador.finalizarPartida();
    }
    
    @Override
    public void confirmarAbandonarPartida(boolean confirmacion) {
        controlador.confirmarAbandonarPartida(confirmacion);
    }

    @Override
    public void confirmarFinalizarPartida(boolean confirmacion) {
        controlador.confirmarFinalizarPartida(confirmacion);
    }
    
    private void habilitarVista(boolean vistaHabilitada){
        this.setEnabled(vistaHabilitada);
    }
    
    private void hacerVistaVisible(boolean vistaVisible){
        this.setVisible(vistaVisible);
    }
    
    private Color determinarColor(ColorFichaPresentacionDTO colorFicha){
        
        switch (colorFicha) {
            
            case ColorFichaPresentacionDTO.COLOR_A:
                
                return mapaColoresJugador.get(1);

            case ColorFichaPresentacionDTO.COLOR_B:
                
                return mapaColoresJugador.get(2);
             
            case ColorFichaPresentacionDTO.COLOR_C:
                
                return mapaColoresJugador.get(3);
                
            case ColorFichaPresentacionDTO.COLOR_D:
                
                return mapaColoresJugador.get(4);
                
            default:
              return null;
        }
        
    }
    
    private FichaInformacionPanel[] obtenerFichasInformacionPanel(FichaPresentacionDTO[] fichas){
        
        if(fichas == null){
            return null;
        }
        
        List<FichaInformacionPanel> listaFichaInformacionPanel = new LinkedList<>();

        for(FichaPresentacionDTO ficha: fichas){
            
            if(ficha != null){
                if(ficha instanceof FichaNormalPresentacionDTO){
                
                FichaNormalPresentacionDTO fichaNormal = (FichaNormalPresentacionDTO) ficha;
                
                listaFichaInformacionPanel.add(new FichaInformacionPanel(
                    fichaNormal.getIdFicha(),
                    String.valueOf(fichaNormal.getValor()),
                    determinarColor(fichaNormal.getColor())
                    ));   
                } else{

                    ComodinPresentacionDTO comodin = (ComodinPresentacionDTO) ficha;

                    listaFichaInformacionPanel.add(new FichaInformacionPanel(
                        comodin.getIdFicha(),
                        comodin.getValor(),
                        COLOR_COMODIN));  
                }
            }
            
            
        }
        
        FichaInformacionPanel[] fichasInformacionPanel = listaFichaInformacionPanel.toArray(new FichaInformacionPanel[0]);
        
        return fichasInformacionPanel;

    }
    
    private JugadorExternoInformacionPanel[] obtenerJugadoresExternoInformacion(JugadorExternoPresentacionDTO[] jugadoresExternoPresentacionDTOs){
        
        if(jugadoresExternoPresentacionDTOs == null){
            return null;
        }
        
        List<JugadorExternoInformacionPanel> listaJugadoresExternoInformacion = new LinkedList<>();
        
        for(JugadorExternoPresentacionDTO jugadorExternoPresentacionDTO: jugadoresExternoPresentacionDTOs){
            
            listaJugadoresExternoInformacion.add(new JugadorExternoInformacionPanel(
                    jugadorExternoPresentacionDTO.getAvatar(),
                    jugadorExternoPresentacionDTO.getNombre(),
                    String.valueOf(jugadorExternoPresentacionDTO.getFichasRestantes()),
                    jugadorExternoPresentacionDTO.getNumeroSecuencia()));
            
        }
        
        JugadorExternoInformacionPanel[] jugadoresExternoInformacion = listaJugadoresExternoInformacion.toArray(new JugadorExternoInformacionPanel[0]);
    
        return jugadoresExternoInformacion;
        
    }
    
    private JugadorPrincipalInformacionPanel obtenerJugadorPrincipalInformacionPanel(JugadorPrincipalPresentacionDTO jugadorPrincipalPresentacionDTO){
        
        if(jugadorPrincipalPresentacionDTO == null){
            return null;
        }
        
        FichaPresentacionDTO[] fichas = jugadorPrincipalPresentacionDTO.getFichas();
        
        actualizarMapaCasillasIdsFichasJugador(fichas);
        
        JugadorPrincipalInformacionPanel jugadorPrincipalInformacionPanel 
                = new JugadorPrincipalInformacionPanel(obtenerFichasInformacionPanel(fichas), 
                        mapaIdsCasillasFichasJugador);
  
        return jugadorPrincipalInformacionPanel;
        
    }
    
    private void actualizarMapaCasillasIdsFichasJugador(FichaPresentacionDTO[] fichas) {
        
        List<Integer> idsFichasEntrantes = new LinkedList<>();
        for (FichaPresentacionDTO ficha : fichas) {
            idsFichasEntrantes.add(ficha.getIdFicha());
        }
        
        for (Integer idFicha : idsFichasEntrantes) {
        
            boolean fichaFueRemovida = false;
            
            if(idsFichasQuitar != null){
                for(Integer id: idsFichasQuitar){
                    if(id.equals(idFicha)){
                        fichaFueRemovida = true;
                    }
                }
            }
            
            boolean fichaFueAgregada = false;
            
            if(idsFichasAgregar != null){   
                for(Integer id: idsFichasAgregar){       
                    if(id.equals(idFicha)){
                        fichaFueAgregada = true;    
                    }  
                }
            }
            
            if (!mapaIdsCasillasFichasJugador.containsValue(idFicha) && !fichaFueRemovida && !fichaFueAgregada) {
            
                int casillaDestino = 1;
                
                // Se busca una clave que no exista o cuyo valor sea nulo.
                while (mapaIdsCasillasFichasJugador.containsKey(casillaDestino) && 
                       mapaIdsCasillasFichasJugador.get(casillaDestino) != null) {
                    casillaDestino++;
                }
                
                mapaIdsCasillasFichasJugador.put(casillaDestino, idFicha);
            }
        }
        
        // Se crean casillas vacías si no se ha llegado al mínimo.
        for (int i = 1; i <= minimoCasillasJugador; i++) {
            mapaIdsCasillasFichasJugador.putIfAbsent(i, null);
        }
    }

    
    private MontonInformacionPanel obtenerMontonInformacionPanel(MontonPresentacionDTO montonPresentacionDTO){
        
        if(montonPresentacionDTO == null){
            return null;
        }
        
        MontonInformacionPanel montonInformacionPanel
                = new MontonInformacionPanel(String.valueOf(montonPresentacionDTO.getNumeroFichasMonton()));
        
        return montonInformacionPanel;
        
    }
    
    private TableroInformacionPanel obtenerTableroInformacionPanel(TableroPresentacionDTO tableroPresentacionDTO, boolean nuevoTurno){
        
        if(tableroPresentacionDTO == null){
            return null;
        }
        
        FichaPresentacionDTO[] fichas = tableroPresentacionDTO.getFichas();
        
        actualizarFichasCasillasTablero(fichas, nuevoTurno);
        
        TableroInformacionPanel tableroInformacionPanel 
                = new TableroInformacionPanel(obtenerFichasInformacionPanel(fichas), mapaIdsCasillasFichasTablero);
        
        return tableroInformacionPanel;
        
    }
    
    
    private void actualizarFichasCasillasTablero(FichaPresentacionDTO[] fichas, boolean nuevoTurno){
        
        if(nuevoTurno && fichas.length != 0){
            
            mapaIdsCasillasFichasTablero.replaceAll((idCasilla, idFicha) -> null);
            
            int j = 1;
            int numeroFila = 0;
            
            for(int i = 0; i < fichas.length; i++){
            
                if(fichas[i] == null && j > numeroFila * ANCHO_FILA + CANTIDAD_CASILLAS_MAXIMA_CARGA_FILA){
                    
                    j = (numeroFila + 1) * ANCHO_FILA + 1;
                    numeroFila++;
                    
                } else if(fichas[i] == null){
                    
                    j++;
                    
                } else{
                    
                    mapaIdsCasillasFichasTablero.put(j, fichas[i].getIdFicha());
                    
                    j++;
                    
                }
                
                
                
            }
        }
    }
    
    private VisitorPintar crearVisitorPintar(EstadoActual estadoActual){
        VisitorPintar visitorPintar = new VisitorPintar(estadoActual);
        return visitorPintar;
    }

    @Override
    public void actualizar(IModelo modelo) {
        
        IModeloEjercerTurno modeloEjercerTurno = (IModeloEjercerTurno)modelo;
                
        JugadorPrincipalPresentacionDTO jugadorPrincipalPresentacionDTO = modeloEjercerTurno.obtenerJugadorPrincipal();
        JugadorExternoPresentacionDTO[] jugadoresExternoPresentacionDTOs = modeloEjercerTurno.obtenerJugadoresExternos();
        TableroPresentacionDTO tableroPresentacionDTO = modeloEjercerTurno.obtenerTablero();
        MontonPresentacionDTO montonPresentacionDTO = modeloEjercerTurno.obtenerMontonPresentacion();
        String mensaje = modeloEjercerTurno.obtenerMensaje();
        
        
        JugadorExternoInformacionPanel[] jugadoresExternosInformacionPanel 
                = obtenerJugadoresExternoInformacion(jugadoresExternoPresentacionDTOs);
        
        JugadorPrincipalInformacionPanel jugadorPrincipalInformacionPanel
                = obtenerJugadorPrincipalInformacionPanel(jugadorPrincipalPresentacionDTO);
        
        MontonInformacionPanel montonInformacionPanel = obtenerMontonInformacionPanel(montonPresentacionDTO);
        
        boolean nuevoTurno = modeloEjercerTurno.isNuevoTurno();
        
        TableroInformacionPanel tableroInformacionPanel = obtenerTableroInformacionPanel(tableroPresentacionDTO, nuevoTurno);
        
        movimientoInvalido = modeloEjercerTurno.isMovimientoInvalido();
        tableroInvalido = modeloEjercerTurno.isTableroInvalido();
        
        actualizarMapaCasillasFichas(!movimientoInvalido);
        
        EstadoActual estadoActual = new EstadoActual(
                jugadoresExternosInformacionPanel,
                jugadorPrincipalInformacionPanel,
                montonInformacionPanel,
                tableroInformacionPanel,
                mensaje,
                !movimientoInvalido,
                !tableroInvalido);
        
        
        VisitorPintar visitorPintar = crearVisitorPintar(estadoActual);
        
        for(IComponente componente: componentes){
            componente.aceptar(visitorPintar);
        }
        repaint();
        revalidate();

        boolean vistaHabilitada = modeloEjercerTurno.isVistaHabilitada();
        boolean vistaVisible = modeloEjercerTurno.isVistaVisible();
        
        habilitarVista(vistaHabilitada);
        hacerVistaVisible(vistaVisible);
      
    }
    
    private void actualizarMapaCasillasFichas(boolean movimientoValido){
        
        
        if(movimientoValido){
            
            if(idsFichasAgregar != null && idsCasillasAgregarTablero != null){
                
                for(int i = 0; i < idsFichasAgregar.length; i++){
                    
                    mapaIdsCasillasFichasTablero.put(idsCasillasAgregarTablero[i], idsFichasAgregar[i]);

                }

                idsCasillasAgregarTablero = null;

                idsFichasAgregar = null;

            }
            
            else if(idsCasillasQuitarTablero != null){
                
                for(int i = 0; i < idsCasillasQuitarTablero.length; i++){
                    
                    mapaIdsCasillasFichasTablero.put(idsCasillasQuitarTablero[i], null);

                }

                idsCasillasQuitarTablero = null;
                
            }
            
            else if(idsFichasAgregar != null && idsCasillasAgregarJugador != null){
                
                for(int i = 0; i < idsFichasAgregar.length; i++){
                    
                    mapaIdsCasillasFichasJugador.put(idsCasillasAgregarJugador[i], idsFichasAgregar[i]);

                }
                
                idsCasillasAgregarJugador = null;

                idsFichasAgregar = null;
 
            }
            else if(idsCasillasQuitarJugador != null){

                for(int i = 0; i < idsCasillasQuitarJugador.length; i++){

                    mapaIdsCasillasFichasJugador.put(idsCasillasQuitarJugador[i], null);
                       
                }
                
                

                idsCasillasQuitarJugador = null;
                idsFichasQuitar = null;
                
            }
            
            
        } else{
            
            idsFichasAgregar = null;
            idsCasillasAgregarTablero = null;
            idsCasillasAgregarJugador = null;
            idsCasillasQuitarJugador = null;
            idsCasillasQuitarTablero = null;
            
        }
        
        
    }
    
}
