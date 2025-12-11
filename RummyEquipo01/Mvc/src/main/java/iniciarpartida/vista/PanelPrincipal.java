
package iniciarpartida.vista;

import definiciones.IGestorEventosInicioPartida;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelPrincipal extends JPanel {

    private final Color COLOR_FONDO = new Color(30, 152, 198); // Azul fondo principal
    private final Color COLOR_AMARILLO_PANEL = new Color(255, 232, 151); // Amarillo del panel central
    private final Color COLOR_BOTON_CREAR = new Color(30, 152, 198); // Azul (igual al fondo)
    private final Color COLOR_BOTON_UNIRSE = new Color(220, 80, 80); // Rojo suave
    private final Color COLOR_TEXTO_BLANCO = Color.WHITE;
    
    private final Font FUENTE_BOTON = new Font("Arial", Font.BOLD, 20);

    private final int ANCHO_PANEL_CENTRAL = 500;
    private final int ALTO_PANEL_CENTRAL = 300;
    private final int RADIO_ESQUINAS_PANEL = 0;

    private final int ANCHO_BOTON = 250;
    private final int ALTO_BOTON = 55;
    private final int RADIO_ESQUINAS_BOTON = 10;

    // Espaciado
    private final int GAP_BOTONES = 40;

    // Textos
    private final String TEXTO_CREAR = "Crear Partida";
    private final String TEXTO_UNIRSE = "Unirse partida";

    private IGestorEventosInicioPartida gestorEventos;

    public PanelPrincipal() {
        configurarPanelPrincipal();
    }

    private void configurarPanelPrincipal() {

        this.setBackground(COLOR_FONDO);
        this.setLayout(new GridBagLayout());

        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(COLOR_AMARILLO_PANEL);
        panelCentral.setPreferredSize(new Dimension(ANCHO_PANEL_CENTRAL, ALTO_PANEL_CENTRAL));
        panelCentral.setLayout(new GridBagLayout()); 
        
        BotonBordesRedondeados btnCrear = new BotonBordesRedondeados(TEXTO_CREAR, COLOR_BOTON_CREAR);
        btnCrear.setPreferredSize(new Dimension(ANCHO_BOTON, ALTO_BOTON));
        btnCrear.addActionListener(e -> gestorEventos.iniciarRegistroNombreJugador());

        GridBagConstraints gbcBtn = new GridBagConstraints();
        gbcBtn.gridx = 0; 
        gbcBtn.gridy = 0;
        gbcBtn.insets = new Insets(0, 0, GAP_BOTONES, 0);
        panelCentral.add(btnCrear, gbcBtn);

        BotonBordesRedondeados btnUnirse = new BotonBordesRedondeados(TEXTO_UNIRSE, COLOR_BOTON_UNIRSE);
        btnUnirse.setPreferredSize(new Dimension(ANCHO_BOTON, ALTO_BOTON));
        btnUnirse.addActionListener(e -> System.out.println("Acción Unirse: Pendiente"));

        gbcBtn.gridy = 1;
        gbcBtn.insets = new Insets(0, 0, 0, 0);
        panelCentral.add(btnUnirse, gbcBtn);

        this.add(panelCentral);
    }

    class BotonBordesRedondeados extends JButton {
        private Color colorFondo;

        public BotonBordesRedondeados(String texto, Color color) {
            super(texto);
            this.colorFondo = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(COLOR_TEXTO_BLANCO);
            setFont(FUENTE_BOTON);
            setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Sombra ligera (opcional, para dar profundidad)
            g2.setColor(new Color(0,0,0,30));
            g2.fillRoundRect(2, 2, getWidth(), getHeight(), RADIO_ESQUINAS_BOTON, RADIO_ESQUINAS_BOTON);

            // Fondo redondeado
            g2.setColor(colorFondo);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), RADIO_ESQUINAS_BOTON, RADIO_ESQUINAS_BOTON);
            
            // Borde gris fino (como en tu imagen)
            g2.setColor(Color.GRAY);
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, RADIO_ESQUINAS_BOTON, RADIO_ESQUINAS_BOTON);
            
            super.paintComponent(g);
        }
    }

    public void setGestorEventos(IGestorEventosInicioPartida gestorEventos) {
        this.gestorEventos = gestorEventos;
    }
    
}