

package ejercerturno.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Panel que representa una ficha y puede ser arrastrado por el usuario.
 * @author 
 */
public class PanelFicha extends JPanel {

    private Integer idFicha;
    private String valor;
    private Color colorValor;
    private IGestorEventos gestorEventos;
    
    private boolean seleccionada;
    private boolean enMovimiento;
    
    private Color COLOR_FONDO_NO_SELECCIONADA = new Color(240, 238, 223);
    private Color COLOR_FONDO_SELECCIONADA = new Color(242, 238, 172);
    
    private Color colorInicialTransicion = new Color(242, 238, 172);
    private Color colorFinalTransicion 
            = new Color(COLOR_FONDO_SELECCIONADA.getRed() - 90, COLOR_FONDO_SELECCIONADA.getGreen() - 90, COLOR_FONDO_SELECCIONADA.getBlue() - 90);;
    private Color colorActualTransicion;
    private Timer timer;
    private float pasoTransicionActual;
    private final float TAMANIO_PASO_TRANSICION = 0.01f;
    
    private Dimension tamanioPanel = new Dimension(50, 70);
    
    private MouseAdapter mouseAdapter;

    public PanelFicha(IGestorEventos gestorEventos, Integer idFicha, String valor, Color colorTexto, boolean seleccionada) {
        this.idFicha = idFicha;
        this.valor = valor;
        this.colorValor = colorTexto;
        this.gestorEventos = gestorEventos;
        this.seleccionada = seleccionada;
        
        setPreferredSize(tamanioPanel);
        configurarListeners();
        configurarTemporizador();
    }
    
    public PanelFicha(Integer idFicha, String valor, Color colorTexto, boolean seleccionada) {
        this.idFicha = idFicha;
        this.valor = valor;
        this.colorValor = colorTexto;
        this.seleccionada = seleccionada;
        
        setPreferredSize(tamanioPanel);
        configurarListeners();
        configurarTemporizador();
    }

    // Getters y setters
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
        repaint();
    }

    public Integer getIdFicha() {
        return idFicha;
    }

    public Color getColorValor() {
        return colorValor;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
        
        if(seleccionada){
            setBackground(COLOR_FONDO_SELECCIONADA);
        }
        else{
            setBackground(COLOR_FONDO_NO_SELECCIONADA);
        }
    }
    
    public void setEnMovimiento(boolean enMovimiento){
        
        this.enMovimiento = enMovimiento;
        
    }
    
    private void configurarListeners(){
        mouseAdapter  = new MouseAdapter() {
            
            long tiempoInicioClic = 0;
            
            @Override
            public void mousePressed(MouseEvent e) {
                
                if(!enMovimiento){
                    tiempoInicioClic = System.currentTimeMillis();

                    if(isSeleccionada()){
                        gestorEventos.presionarFicha();
                        pasoTransicionActual = 0.0f;
                        timer.start();
                    }
                }
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                
                if(!enMovimiento){
                    long tiempoActual = System.currentTimeMillis();
                    timer.stop();

                    gestorEventos.dejarPresionarFicha();

                    if(tiempoActual - tiempoInicioClic >= 500 && isSeleccionada()){

                        gestorEventos.iniciarArrastreFichas();

                    } else if(tiempoActual - tiempoInicioClic >= 150){
                        gestorEventos.seleccionarFicha(e, true);

                    } else{
                        
                        if(!enMovimiento){
                            gestorEventos.seleccionarFicha(e, !isSeleccionada());
                        }
                        

                    }
                } else{
                    gestorEventos.soltarFichasMovimiento(e);
                }
                
            }
            
            @Override
            public void mouseDragged(MouseEvent e){
                gestorEventos.arrastreFichaMovimiento(e);
            }
            

        };
        
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

    }
    
    private void configurarTemporizador(){
        
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                pasoTransicionActual += TAMANIO_PASO_TRANSICION;
                if (pasoTransicionActual > 0.5f) {
                    pasoTransicionActual = 0.5f;
                    timer.stop();
                }

                colorActualTransicion = interpolarColor(colorInicialTransicion, colorFinalTransicion, pasoTransicionActual);
                
                setBackground(colorActualTransicion);
                repaint();
            }
        });     
    }
    
    private Color interpolarColor(Color colorInicio, Color colorFin, float progreso) {
        int r = (int) (colorInicio.getRed() + (colorFin.getRed() - colorInicio.getRed()) * progreso / 0.5);
        int g = (int) (colorInicio.getGreen() + (colorFin.getGreen() - colorInicio.getGreen()) * progreso / 0.5);
        int b = (int) (colorInicio.getBlue() + (colorFin.getBlue() - colorInicio.getBlue()) * progreso / 0.5);
        return new Color(r, g, b);
    }
    
    
    
    // Método para pintar la ficha
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(this.getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(colorValor);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString(valor, getWidth() / 2 - g.getFontMetrics().stringWidth(valor) / 2, getHeight() / 2 + 7);
    }
    
    public void presionarFicha(){
             
        if(isSeleccionada()){
            pasoTransicionActual = 0.0f;
            timer.start();
        }
        
    }
    
    public void dejarPresionarFicha(){
        timer.stop();
        setSeleccionada(true);
        

    }
    
}