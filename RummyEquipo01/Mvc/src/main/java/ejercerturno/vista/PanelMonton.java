    
package ejercerturno.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class PanelMonton extends JPanel implements IComponente{

    private PosicionPanel POSICION_PANEL = PosicionPanel.IZQUIERDA_ABAJO;
    private Color COLOR_FONDO = new Color(255, 234, 150);
    private String VALOR_LABEL_PANEL = "Fichas restantes: ";
    private JLabel labelNumeroFichasMonton = new JLabel();
    
    private Color COLOR_FONDO_TOMAR_FICHA = new Color(252, 163, 86);
    private Color COLOR_FONDO_REESTABLECER_TABLERO = new Color(117, 26, 26);
    
    private LineBorder BORDE_BOTONES = new LineBorder(Color.BLACK, 3, true);
    
    private JButton botonTomarFicha = new JButton("Tomar ficha");
    private JButton botonReestablecerTablero = new JButton("Reestablecer tablero");
    
    JPanel panelContenedor = new JPanel();
    JPanel panelAuxiliar = new JPanel();
    
    Font fuenteDatosJugador = new Font("Arial", Font.BOLD, 16);
    
    private IGestorEventos gestorEventos;
    
    public PanelMonton(){
        
        setBackground(COLOR_FONDO);
        
        setLayout(new GridBagLayout());
        
        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
        panelContenedor.setOpaque(false);
         
        configurarBotonTomarFicha();
        configurarBotonReestablecerTablero();
        
        add(panelContenedor);
        
        repaint();
                

    }
    
    private void configurarBotonTomarFicha(){
        
        JPanel panelBotonTomarFicha = new JPanel(new FlowLayout());
        panelBotonTomarFicha.setOpaque(false);
        
        botonTomarFicha.setFont(fuenteDatosJugador);
        
        botonTomarFicha.setBackground(COLOR_FONDO_TOMAR_FICHA);
        botonTomarFicha.setForeground(Color.WHITE);
        
        botonTomarFicha.setBorder(BORDE_BOTONES);
        
        botonTomarFicha.addActionListener(e -> gestorEventos.tomarFicha());
        
        panelBotonTomarFicha.add(botonTomarFicha);
        
        panelContenedor.add(panelBotonTomarFicha);
        
    }
    
    private void configurarBotonReestablecerTablero(){
        
        JPanel panelBotonReestablecerTablero = new JPanel(new FlowLayout());
        
        panelBotonReestablecerTablero.setOpaque(false);
        
        botonReestablecerTablero.setFont(fuenteDatosJugador);
        
        botonReestablecerTablero.setBackground(COLOR_FONDO_REESTABLECER_TABLERO);
        botonReestablecerTablero.setForeground(Color.WHITE);
        
        botonReestablecerTablero.setBorder(BORDE_BOTONES);
        
        botonReestablecerTablero.addActionListener(e -> gestorEventos.reestablecerTablero());
        
        panelBotonReestablecerTablero.add(botonReestablecerTablero);
        
        panelContenedor.add(panelBotonReestablecerTablero);
        
    }
    
    private void actualizarNumeroFichasRestantes(String numeroFichasRestantes){
        
        panelAuxiliar.removeAll();
        
        panelAuxiliar.setLayout(new BoxLayout(panelAuxiliar, BoxLayout.Y_AXIS));
        panelAuxiliar.setOpaque(false);
                
        JLabel labelMonton = new JLabel(VALOR_LABEL_PANEL);
        
        labelMonton.setFont(fuenteDatosJugador);
        
        JPanel panelAuxiliarEncabezado = new JPanel();
        panelAuxiliarEncabezado.setOpaque(false);      
        panelAuxiliarEncabezado.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panelAuxiliarEncabezado.add(labelMonton);
        
        panelAuxiliar.add(panelAuxiliarEncabezado);
        
        labelNumeroFichasMonton = new JLabel(numeroFichasRestantes);
        
        labelNumeroFichasMonton.setFont(fuenteDatosJugador);
        
        JPanel panelAuxiliarFichasMonton = new JPanel();
        panelAuxiliarFichasMonton.setOpaque(false);
        panelAuxiliarFichasMonton.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        panelAuxiliarFichasMonton.add(labelNumeroFichasMonton);
        
        panelAuxiliar.add(panelAuxiliarFichasMonton);
        
        panelContenedor.add(panelAuxiliar);
        
    }
    
    @Override
    public void agregarComponente(IComponente componente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removerComponente(IComponente componente) {}

    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }

    public void pintar(IEstadoMonton estadoMonton) {
        
        if(estadoMonton.getMonton() != null){
            String numeroFichasMonton = estadoMonton.getMonton().getNumeroFichasMonton();
            actualizarNumeroFichasRestantes(numeroFichasMonton);
        }
        
        repaint();
    }

    @Override
    public PosicionPanel getPosicion() {
        return POSICION_PANEL;
    }

    public void setGestorEventos(IGestorEventos gestorEventos) {
        this.gestorEventos = gestorEventos;
    }
    
}
