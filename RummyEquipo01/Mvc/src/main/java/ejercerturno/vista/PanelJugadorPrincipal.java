
package ejercerturno.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import ejercerturno.vista.FichaInformacionPanel;


public class PanelJugadorPrincipal extends JPanel implements IComponente{

    private Color COLOR_FONDO_MANO = new Color(69, 42, 32);
    
    private Color COLOR_FONDO_TERMINAR_TURNO = new Color(30, 152, 198);
    private Color COLOR_FONDO_ABANDONAR = new Color(219, 81, 81);
    private Color COLOR_FONDO_FINALIZAR_PARTIDA = new Color(255, 223, 167);
    
    private Insets SEPARACION_CASILLAS = new Insets(4, 4, 0, 4);
    
    private final PosicionPanel POSICION_PANEL = PosicionPanel.ABAJO_CENTRO;
    
    private List<PanelCasilla> panelesCasillas;
    
    private JPanel panelManoFichas = new JPanel();
    private JPanel panelOpciones = new JPanel();
    
    private JPanel panelSuperiorFichas = new JPanel();
    private JPanel panelInferiorFichas = new JPanel();
    
    private JPanel panelEsquinaSuperiorDerechaMano = new JPanel();
    private JPanel panelEsquinaSuperiorIzquierdaMano = new JPanel();
    
    Font fuenteDatosJugador = new Font("Arial", Font.BOLD, 15);
    
    private JButton botonTerminarTurno = new JButton("Terminar turno");
    private JButton botonFinalizarPartida = new JButton("Finalizar partida");
    private JButton botonAbandonar = new JButton("Abandonar");
    
    private LineBorder BORDE_BOTONES = new LineBorder(Color.BLACK, 3, true);
    
    private Map<Integer, Integer> mapaCasillasFichas;
    
    private IGestorEventos gestorEventos;
    
    private boolean enTurno;
    
    public PanelJugadorPrincipal(List<PanelCasilla> panelesCasillas){
        
        
        this.panelesCasillas = panelesCasillas;
        
        setOpaque(false);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        
        configurarPanelManoFichas();
        configurarPanelesMano();
        configurarPanelOpciones();
        configurarBotonesOpciones();
    }
    
    public void setGestorEventos(IGestorEventos gestorEventos){
        this.gestorEventos = gestorEventos;
    }
    
    private void configurarPanelManoFichas(){
        
        GridBagConstraints gridBagConstraintsPanelMano = new GridBagConstraints();
        
        gridBagConstraintsPanelMano.gridx = 1;
        gridBagConstraintsPanelMano.gridy = 0;
        gridBagConstraintsPanelMano.weightx = 20;
        gridBagConstraintsPanelMano.weighty = 1;
        gridBagConstraintsPanelMano.gridwidth = 1;
        gridBagConstraintsPanelMano.gridheight = 2;
        gridBagConstraintsPanelMano.fill = GridBagConstraints.BOTH;
        
        panelManoFichas.setLayout(new GridBagLayout());
        panelManoFichas.setOpaque(false);
        
        JScrollPane scrollPane = new JScrollPane(panelManoFichas);
        
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        
        add(scrollPane, gridBagConstraintsPanelMano);
        
    }
    
    private void configurarPanelOpciones(){
        GridBagConstraints gbcPanelOpciones = new GridBagConstraints();
        
        gbcPanelOpciones.gridx = 2;
        gbcPanelOpciones.gridy = 0;
        gbcPanelOpciones.weightx = 2;
        gbcPanelOpciones.weighty = 1;
        gbcPanelOpciones.gridwidth = 1;
        gbcPanelOpciones.gridheight = 1;
        gbcPanelOpciones.fill = GridBagConstraints.BOTH;
        
        add(panelOpciones, gbcPanelOpciones);
        
        panelOpciones.setLayout(new GridBagLayout());
    }
    
    private void configurarPanelesMano(){
        
        GridBagConstraints gbcPanelSuperiorFichas = new GridBagConstraints();
        
        gbcPanelSuperiorFichas.gridx = 1;
        gbcPanelSuperiorFichas.gridy = 0;
        gbcPanelSuperiorFichas.weightx = 1;
        gbcPanelSuperiorFichas.weighty = 1;
        gbcPanelSuperiorFichas.gridwidth = 1;
        gbcPanelSuperiorFichas.gridheight = 1;
        gbcPanelSuperiorFichas.fill = GridBagConstraints.BOTH;
        
        panelManoFichas.add(panelSuperiorFichas, gbcPanelSuperiorFichas);
         
        panelSuperiorFichas.setLayout(new GridBagLayout());
        panelSuperiorFichas.setBackground(COLOR_FONDO_MANO);
        
        
        GridBagConstraints gbcPanelInferiorFichas = new GridBagConstraints();
        
        gbcPanelInferiorFichas.gridx = 0;
        gbcPanelInferiorFichas.gridy = 1;
        gbcPanelInferiorFichas.weightx = 0.7;
        gbcPanelInferiorFichas.weighty = 1;
        gbcPanelInferiorFichas.gridwidth = 3;
        gbcPanelInferiorFichas.gridheight = 1;
        gbcPanelInferiorFichas.fill = GridBagConstraints.BOTH;

        panelManoFichas.add(panelInferiorFichas, gbcPanelInferiorFichas);
        
        panelInferiorFichas.setLayout(new GridBagLayout());
        panelInferiorFichas.setBackground(COLOR_FONDO_MANO);
        
        
        
        GridBagConstraints gbcPanelEsquinaSuperiorDerecha = new GridBagConstraints();
        
        gbcPanelEsquinaSuperiorDerecha.gridx = 0;
        gbcPanelEsquinaSuperiorDerecha.gridy = 0;
        gbcPanelEsquinaSuperiorDerecha.weightx = 3;
        gbcPanelEsquinaSuperiorDerecha.weighty = 1;
        gbcPanelEsquinaSuperiorDerecha.gridwidth = 1;
        gbcPanelEsquinaSuperiorDerecha.gridheight = 1;
        gbcPanelEsquinaSuperiorDerecha.fill = GridBagConstraints.BOTH;
        
        panelManoFichas.add(panelEsquinaSuperiorDerechaMano, gbcPanelEsquinaSuperiorDerecha);
        panelEsquinaSuperiorDerechaMano.setOpaque(false);
        
        GridBagConstraints gbcPanelEsquinaSuperiorIzquierda = new GridBagConstraints();
        
        gbcPanelEsquinaSuperiorIzquierda.gridx = 2;
        gbcPanelEsquinaSuperiorIzquierda.gridy = 0;
        gbcPanelEsquinaSuperiorIzquierda.weightx = 3;
        gbcPanelEsquinaSuperiorIzquierda.weighty = 1;
        gbcPanelEsquinaSuperiorIzquierda.gridwidth = 1;
        gbcPanelEsquinaSuperiorIzquierda.gridheight = 1;
        gbcPanelEsquinaSuperiorIzquierda.fill = GridBagConstraints.BOTH;
        
        panelManoFichas.add(panelEsquinaSuperiorIzquierdaMano, gbcPanelEsquinaSuperiorIzquierda);
        panelEsquinaSuperiorIzquierdaMano.setOpaque(false);
        
        
        int cantidadFichasPanelInferior = 0;
        int cantidadFichasPanelSuperior = 0;
        
        for(PanelCasilla panelCasilla: panelesCasillas){
            
            if(cantidadFichasPanelInferior < panelesCasillas.size()/2 + 1){
                
                GridBagConstraints gbcPanelFichaActual = new GridBagConstraints();
        
                gbcPanelFichaActual.gridx = cantidadFichasPanelInferior;
                gbcPanelFichaActual.gridy = 1;
                gbcPanelFichaActual.fill = GridBagConstraints.BOTH;

                gbcPanelFichaActual.insets = SEPARACION_CASILLAS;
                panelInferiorFichas.add(panelCasilla, gbcPanelFichaActual);
        
                cantidadFichasPanelInferior++;
                
            } else{
                
                GridBagConstraints gbcPanelFichaActual = new GridBagConstraints();
        
                gbcPanelFichaActual.gridx = cantidadFichasPanelSuperior;
                gbcPanelFichaActual.gridy = 0;
                gbcPanelFichaActual.fill = GridBagConstraints.BOTH;

                gbcPanelFichaActual.insets = SEPARACION_CASILLAS;
                panelSuperiorFichas.add(panelCasilla, gbcPanelFichaActual);
                
                cantidadFichasPanelSuperior++;
            }
            
        }
        
    }
    
    private void agregarFichasMano(FichaInformacionPanel[] fichas){

        if(mapaCasillasFichas.size() > panelesCasillas.size()){
            
            int diferencia = mapaCasillasFichas.size() - panelesCasillas.size();
            
            int ultimoIndicePanelCasilla = panelesCasillas.size();
            
            for(int i = ultimoIndicePanelCasilla + 1; i <= ultimoIndicePanelCasilla + diferencia; i++){
                
                panelesCasillas.add(new PanelCasilla(i));
                
            }
            
            configurarPanelesMano();
        }
        
        for(Map.Entry<Integer, Integer> entrada: mapaCasillasFichas.entrySet()){
            
            PanelCasilla panelCasilla = obtenerCasillaPorId(entrada.getKey());

            FichaInformacionPanel ficha = obtenerInformacionFichaPorId(entrada.getValue(), fichas);

            
            if(ficha != null){
                PanelFicha panelFicha = new PanelFicha(
                    gestorEventos, 
                    ficha.getId(), 
                    ficha.getValor(), 
                    ficha.getColor(),
                    false);

                panelCasilla.agregarFicha(panelFicha);
            }
            
            
        }
        
        for(PanelCasilla panelCasilla: panelesCasillas){
            
            if(!mapaCasillasFichas.keySet().contains(panelCasilla.getId())){
                
                panelCasilla.removeAll();
                
            }
            
        }
            
            

    }
    
    
    private PanelCasilla obtenerCasillaPorId(Integer idCasilla){
        
        for(PanelCasilla panelCasilla: panelesCasillas){
            
            if(panelCasilla.getId().equals(idCasilla)){
                panelCasilla.removeAll();
                return panelCasilla;
            }
        }
        return null;
    }
    
    
    private FichaInformacionPanel obtenerInformacionFichaPorId(Integer idFicha, FichaInformacionPanel[] fichas){
        
        for(FichaInformacionPanel ficha: fichas){

            if(ficha.getId().equals(idFicha)){
                return ficha;
            }
                
        }
        
        return null;
        
    }
   
    
    private void configurarBotonesOpciones(){
        
        panelOpciones.setOpaque(false);
        
        botonTerminarTurno.setFont(fuenteDatosJugador);
        
        botonTerminarTurno.setBackground(COLOR_FONDO_TERMINAR_TURNO);
        botonTerminarTurno.setForeground(Color.WHITE);
        
        botonTerminarTurno.setBorder(BORDE_BOTONES);
        
        botonTerminarTurno.addActionListener(e -> gestorEventos.terminarTurno());
        
        GridBagConstraints gbcBtnTerminarTurno = new GridBagConstraints();
        
        gbcBtnTerminarTurno.gridx = 0;
        gbcBtnTerminarTurno.gridy = 0;
        gbcBtnTerminarTurno.weightx = 1;
        gbcBtnTerminarTurno.weighty = 1;
        gbcBtnTerminarTurno.gridwidth = 2;
        gbcBtnTerminarTurno.gridheight = 1;
        gbcBtnTerminarTurno.fill = GridBagConstraints.BOTH;

        gbcBtnTerminarTurno.insets = SEPARACION_CASILLAS;
        panelOpciones.add(botonTerminarTurno, gbcBtnTerminarTurno);
        
        
        botonFinalizarPartida.setFont(fuenteDatosJugador);
        
        botonFinalizarPartida.setBackground(COLOR_FONDO_FINALIZAR_PARTIDA);
        botonFinalizarPartida.setForeground(Color.BLACK);
        
        botonFinalizarPartida.setBorder(BORDE_BOTONES);
        
        botonFinalizarPartida.addActionListener(e -> gestorEventos.finalizarPartida());
        
        GridBagConstraints gbcBtnFinalizarPartida= new GridBagConstraints();
        
        gbcBtnFinalizarPartida.gridx = 1;
        gbcBtnFinalizarPartida.gridy = 1;
        gbcBtnFinalizarPartida.weightx = 1;
        gbcBtnFinalizarPartida.weighty = 1;
        gbcBtnFinalizarPartida.gridwidth = 1;
        gbcBtnFinalizarPartida.gridheight = 1;
        gbcBtnFinalizarPartida.fill = GridBagConstraints.BOTH;

        gbcBtnFinalizarPartida.insets = SEPARACION_CASILLAS;
        panelOpciones.add(botonFinalizarPartida, gbcBtnFinalizarPartida);
        
        
        botonAbandonar.setFont(fuenteDatosJugador);
        
        botonAbandonar.setBackground(COLOR_FONDO_ABANDONAR);
        botonAbandonar.setForeground(Color.WHITE);
        
        botonAbandonar.setBorder(BORDE_BOTONES);
        
        botonAbandonar.addActionListener(e -> gestorEventos.abandonarPartida());
        
        GridBagConstraints gbcBtnAbandonarPartida = new GridBagConstraints();
        
        gbcBtnAbandonarPartida.gridx = 0;
        gbcBtnAbandonarPartida.gridy = 1;
        gbcBtnAbandonarPartida.weightx = 1;
        gbcBtnAbandonarPartida.weighty = 1;
        gbcBtnAbandonarPartida.gridwidth = 1;
        gbcBtnAbandonarPartida.gridheight = 1;
        gbcBtnAbandonarPartida.fill = GridBagConstraints.BOTH;

        gbcBtnAbandonarPartida.insets = SEPARACION_CASILLAS;
        panelOpciones.add(botonAbandonar, gbcBtnAbandonarPartida);
        
    }
    
    
    @Override
    public void agregarComponente(IComponente componente) {
        
    }

    @Override
    public void removerComponente(IComponente componente) {
        
    }

    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }

    public void pintar(IEstadoJugadorPrincipal estadoJugadorPrincpal) {
        
        if(estadoJugadorPrincpal.getJugadorPrincipal() != null){
            mapaCasillasFichas = estadoJugadorPrincpal.getJugadorPrincipal().getMapaCasillasFichas();
            FichaInformacionPanel[] fichas = estadoJugadorPrincpal.getJugadorPrincipal().getFichasJugadorPrincipal();
            agregarFichasMano(fichas);
        }
        
    }

    public boolean tienePosicion() {
        return false;
    }

    @Override
    public PosicionPanel getPosicion() {
        return POSICION_PANEL;

    }
    
}
