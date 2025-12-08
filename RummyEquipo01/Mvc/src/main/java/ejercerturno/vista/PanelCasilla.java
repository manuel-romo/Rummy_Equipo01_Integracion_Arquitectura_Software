

package ejercerturno.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class PanelCasilla extends JPanel{
    
    private Integer id;
    private Dimension TAMANIO_CASILLA = new Dimension(60, 80);
    private Color COLOR_FONDO_NO_SOBRE = new Color(217, 217, 217, 40);
    private Color COLOR_FONDO_SOBRE = new Color(217, 217, 217, 80);
    
    private PanelFicha panelFicha;
    
    public PanelCasilla(Integer id){
        this.id = id;
        setOpaque(false);
        setPreferredSize(TAMANIO_CASILLA);
        setMinimumSize(TAMANIO_CASILLA);
        setMaximumSize(TAMANIO_CASILLA);
        setBackground(COLOR_FONDO_NO_SOBRE);
        configurarMouseListener();
        pintar();
        
    }

    public Integer getId() {
        return id;
    }

    public PanelFicha getPanelFicha() {
        return panelFicha;
    }
    
    public void agregarFicha(PanelFicha panelFicha){
        
        removeAll();
        add(panelFicha);
        this.panelFicha = panelFicha;
        pintar();
        
    }
    
    private void pintar(){
        
        if(panelFicha != null){
            
            this.panelFicha = panelFicha;
            add(panelFicha);
            
        } else{
            
            this.panelFicha = null;
            removeAll();
            
        }
        repaint();
               
    }
    

    private void configurarMouseListener(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(COLOR_FONDO_SOBRE);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(COLOR_FONDO_NO_SOBRE);
                repaint();
            }
        });
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        // Primero, llama al método de la clase padre para que haga su trabajo
        super.paintComponent(g);

        // Establece el color del "pincel" al color de fondo actual del panel
        g.setColor(getBackground());

        // Dibuja un rectángulo del tamaño del panel con el color semi-transparente
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
}