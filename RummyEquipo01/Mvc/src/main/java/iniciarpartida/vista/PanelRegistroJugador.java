
package iniciarpartida.vista;

import definiciones.IGestorEventosInicioPartida;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * Vista manual para el registro de jugador.
 * @author juanpheras
 */
public class PanelRegistroJugador extends JPanel {

    private static final int ANCHO_AVATAR = 50;
    private static final int ALTO_AVATAR = 50;
    private static final int ANCHO_PANEL_COLOR = 40;
    private static final int ALTO_PANEL_COLOR = 40;
    private static final int ANCHO_BOTON = 180;
    private static final int ALTO_BOTON = 45;
    
    private static final int TAMANO_FUENTE = 24;
    private static final int COLUMNAS_CAMPO_TEXTO = 15;
    private static final int GROSOR_BORDE_SELECCION = 3;
    
    private static final int MARGIN_ESTANDAR = 10;
    private static final int MARGIN_SUPERIOR_BOTON = 40;
    private static final int GAP_AVATARES = 10;
    private static final int GAP_COLORES = 15;
    
    private static final int ID_COLOR_A = 1;
    private static final int ID_COLOR_B = 2;
    private static final int ID_COLOR_C = 3;
    private static final int ID_COLOR_D = 4;

    private static final Color COLOR_FONDO = new Color(255, 232, 151); // Amarillo suave
    private static final Color COLOR_BOTON = new Color(102, 153, 255); // Azul claro
    private static final Color COLOR_TEXTO_DISABLED = Color.WHITE;
    private static final Color COLOR_BORDE_SELECCION = Color.YELLOW;
    private static final Color COLOR_BORDE_ERROR = Color.RED;
    private static final Color COLOR_BORDE_DEFAULT = Color.GRAY;

    private JTextField txtNombre;
    private JLabel lblAvatarSeleccionadoVisual; 
    private JPanel pnlColorA, pnlColorB, pnlColorC, pnlColorD;
    private JButton btnContinuar;
    
    private String linkAvatarSeleccionado;
    
    private final String[] rutasAvatares = {
        "/imgs/avatar1.png", "/imgs/avatar2.png", "/imgs/avatar3.png",
        "/imgs/avatar4.png", "/imgs/avatar5.png", "/imgs/avatar6.jpg"
    };
    
    private IGestorEventosInicioPartida gestorEventos;


    private void initGUI() {
        this.setLayout(new GridBagLayout());
        this.setBackground(COLOR_FONDO);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(MARGIN_ESTANDAR, MARGIN_ESTANDAR, MARGIN_ESTANDAR, MARGIN_ESTANDAR);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        Font fuenteLabels = new Font("Helvetica Neue", Font.PLAIN, TAMANO_FUENTE);

        // --- FILA 1: NOMBRE ---
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(fuenteLabels);
        gbc.gridx = 0; gbc.gridy = 0;
        this.add(lblNombre, gbc);

        txtNombre = new JTextField(COLUMNAS_CAMPO_TEXTO);
        txtNombre.setFont(fuenteLabels);
        txtNombre.setEditable(false);
        txtNombre.setBackground(COLOR_TEXTO_DISABLED);
        gbc.gridx = 1; gbc.gridy = 0;
        this.add(txtNombre, gbc);

        // --- FILA 2: AVATAR ---
        JLabel lblAvatar = new JLabel("Avatar:");
        lblAvatar.setFont(fuenteLabels);
        gbc.gridx = 0; gbc.gridy = 1;
        this.add(lblAvatar, gbc);

        JPanel pnlAvatares = new JPanel(new FlowLayout(FlowLayout.LEFT, GAP_AVATARES, 0));
        pnlAvatares.setBackground(COLOR_FONDO);
        
        for (String ruta : rutasAvatares) {
            JLabel lblIcono = new JLabel();
            lblIcono.setPreferredSize(new Dimension(ANCHO_AVATAR, ALTO_AVATAR));
            lblIcono.setCursor(new Cursor(Cursor.HAND_CURSOR));
            configurarIconoAvatar(lblIcono, ruta);
            pnlAvatares.add(lblIcono);
        }
        
        gbc.gridx = 1; gbc.gridy = 1;
        this.add(pnlAvatares, gbc);

        // --- FILA 3: COLORES ---
        JLabel lblColores = new JLabel("Colores de fichas:");
        lblColores.setFont(fuenteLabels);
        gbc.gridx = 0; gbc.gridy = 2;
        this.add(lblColores, gbc);

        JPanel pnlColores = new JPanel(new FlowLayout(FlowLayout.LEFT, GAP_COLORES, 0));
        pnlColores.setBackground(COLOR_FONDO);

        pnlColorA = crearPanelColor(Color.RED);
        pnlColorB = crearPanelColor(Color.BLUE);
        pnlColorC = crearPanelColor(Color.GREEN);
        pnlColorD = crearPanelColor(Color.BLACK);

        pnlColores.add(pnlColorA);
        pnlColores.add(pnlColorB);
        pnlColores.add(pnlColorC);
        pnlColores.add(pnlColorD);

        gbc.gridx = 1; gbc.gridy = 2;
        this.add(pnlColores, gbc);

        // --- FILA 4: BOTÓN ---
        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(fuenteLabels);
        btnContinuar.setBackground(COLOR_BOTON);
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setPreferredSize(new Dimension(ANCHO_BOTON, ALTO_BOTON));
        
        btnContinuar.addActionListener(e -> accionContinuar());

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        // Margen superior extra para separar el botón
        gbc.insets = new Insets(MARGIN_SUPERIOR_BOTON, MARGIN_ESTANDAR, MARGIN_ESTANDAR, MARGIN_ESTANDAR);
        this.add(btnContinuar, gbc);
    }

    private JPanel crearPanelColor(Color colorInicial) {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(ANCHO_PANEL_COLOR, ALTO_PANEL_COLOR));
        p.setBackground(colorInicial);
        p.setBorder(BorderFactory.createLineBorder(COLOR_BORDE_DEFAULT));
        p.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                elegirColor(p);
            }
        });
        return p;
    }

    private void configurarIconoAvatar(JLabel label, String ruta) {
        ImageIcon icon = cargarYEscalar(ruta, ANCHO_AVATAR, ALTO_AVATAR);
        if (icon != null) {
            label.setIcon(icon);
        } else {
            label.setText("Img?"); 
            label.setBorder(new LineBorder(COLOR_BORDE_ERROR));
        }

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarAvatar(label, ruta);
            }
        });
    }

    private void seleccionarAvatar(JLabel label, String ruta) {
        if (lblAvatarSeleccionadoVisual != null) {
            lblAvatarSeleccionadoVisual.setBorder(null);
        }
        label.setBorder(new LineBorder(COLOR_BORDE_SELECCION, GROSOR_BORDE_SELECCION));
        lblAvatarSeleccionadoVisual = label;
        linkAvatarSeleccionado = ruta;
    }

    private void elegirColor(JPanel panel) {
        Color nuevoColor = JColorChooser.showDialog(this, "Elige un color", panel.getBackground());
        if (nuevoColor != null) {
            if (esColorRepetido(nuevoColor, panel)) {
                JOptionPane.showMessageDialog(this, "Ese color ya está en uso.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                panel.setBackground(nuevoColor);
            }
        }
    }

    private boolean esColorRepetido(Color color, JPanel panelActual) {
        JPanel[] paneles = {pnlColorA, pnlColorB, pnlColorC, pnlColorD};
        for (JPanel p : paneles) {
            if (p != panelActual && p.getBackground().equals(color)) {
                return true;
            }
        }
        return false;
    }

    private void accionContinuar() {
        if (linkAvatarSeleccionado == null || linkAvatarSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un avatar.");
            return;
        }
        gestorEventos.enviarRegistroJugador(linkAvatarSeleccionado, obtenerMapaColores());
    }

    private Map<Integer, Color> obtenerMapaColores() {
        Map<Integer, Color> mapa = new HashMap<>();
        mapa.put(ID_COLOR_A, pnlColorA.getBackground());
        mapa.put(ID_COLOR_B, pnlColorB.getBackground());
        mapa.put(ID_COLOR_C, pnlColorC.getBackground());
        mapa.put(ID_COLOR_D, pnlColorD.getBackground());
        return mapa;
    }

    private ImageIcon cargarYEscalar(String ruta, int w, int h) {
        try {
            java.net.URL imgURL = getClass().getResource(ruta);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void setNombreJugador(String nombre) {
        this.txtNombre.setText(nombre);
    }

    public void setGestorEventos(IGestorEventosInicioPartida gestorEventos) {
        this.gestorEventos = gestorEventos;
    }

}
