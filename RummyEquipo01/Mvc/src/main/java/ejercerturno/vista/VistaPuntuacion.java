
package ejercerturno.vista;

import definiciones.IModelo;
import definiciones.IModeloEjercerTurno;
import definiciones.ISuscriptor;
import ejercerturno.controlador.ControladorEjercerTurno;
import ejercerturno.dto.JugadorPuntuacionPresentacionDTO;
import iniciarpartida.dto.EtapaActualEjercerTurno;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class VistaPuntuacion extends JFrame implements ISuscriptor {

    private ControladorEjercerTurno controlador;
    
    // Colores
    private final Color COLOR_FONDO = new Color(36, 147, 190);
    private final Color COLOR_TABLA_BORDE = new Color(60, 50, 90);
    private final Color COLOR_BANNER = new Color(230, 190, 210);
    private final Color COLOR_BOTON = new Color(217, 83, 79);
    
    private final String IMAGEN_CARTAS = "fichas.png";

    private JPanel panelTabla;
    private JButton btnContinuar;

    public VistaPuntuacion(ControladorEjercerTurno controlador) {
        this.controlador = controlador;
        setTitle("Rummy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(COLOR_FONDO);
        add(mainPanel);

        panelTabla = new JPanel(new GridBagLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_TABLA_BORDE, 8, true),
                new EmptyBorder(20, 40, 40, 40)
        ));

        mainPanel.add(panelTabla);

        btnContinuar = new JButton("Continuar");
        btnContinuar.setBackground(COLOR_BOTON);
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnContinuar.setFocusPainted(false);
        btnContinuar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        btnContinuar.addActionListener(e -> controlador.volverInicio());

        GridBagConstraints gbcBtn = new GridBagConstraints();
        gbcBtn.gridx = 0; gbcBtn.gridy = 1;
        gbcBtn.anchor = GridBagConstraints.EAST;
        gbcBtn.weightx = 1.0;
        gbcBtn.insets = new Insets(20, 0, 0, 50);
        
        mainPanel.add(btnContinuar, gbcBtn);
    }

    private void habilitarVista(boolean vistaHabilitada){
        this.setEnabled(vistaHabilitada);
    }
    
    private void hacerVistaVisible(boolean vistaVisible){
        this.setVisible(vistaVisible);
    }

    @Override
    public void actualizar(IModelo modelo) {

        IModeloEjercerTurno modeloEjercerTurno = (IModeloEjercerTurno) modelo;
        
        panelTabla.removeAll();
        construirEncabezados();

        List<JugadorPuntuacionPresentacionDTO> listaJugadores = modeloEjercerTurno.obtenerPuntuacionesJugadores(); 
        EtapaActualEjercerTurno etapaActualEjercerTurno = modeloEjercerTurno.obtenerEtapaActualEjercerTurno();

        int posicion = 1;
        int gridRow = 2; 

        for (JugadorPuntuacionPresentacionDTO jugador : listaJugadores) {

            agregarFilaJugador(
                panelTabla, 
                gridRow, 
                String.valueOf(posicion), 
                jugador.getNombre(), 
                jugador.getPuntaje(),
                jugador.getAvatar()
            );
            
            posicion++;
            gridRow++;
        }

        panelTabla.revalidate();
        panelTabla.repaint();
        
        boolean vistaHabilitada = modeloEjercerTurno.isVistaHabilitada();
        boolean vistaVisible = modeloEjercerTurno.isVistaVisible() && etapaActualEjercerTurno == EtapaActualEjercerTurno.PUNTUACIONES;
        
        habilitarVista(vistaHabilitada);
        hacerVistaVisible(vistaVisible);
    }

    private void construirEncabezados() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Posiciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 36));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(COLOR_BANNER);
        lblTitulo.setForeground(new Color(100, 20, 20));
        lblTitulo.setBorder(BorderFactory.createLineBorder(COLOR_TABLA_BORDE, 2));

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 6; // Abarca todo el ancho
        gbc.insets = new Insets(-40, 50, 20, 50);
        gbc.ipady = 20;
        panelTabla.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblHeaderJug = new JLabel("Jugadores");
        lblHeaderJug.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panelTabla.add(lblHeaderJug, gbc);

        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setPreferredSize(new Dimension(2, 200));
        sep.setForeground(COLOR_TABLA_BORDE);
        GridBagConstraints gbcSep = new GridBagConstraints();
        gbcSep.gridx = 3; gbcSep.gridy = 1; gbcSep.gridheight = 10; // Que baje bastante
        gbcSep.fill = GridBagConstraints.VERTICAL;
        panelTabla.add(sep, gbcSep);

        JLabel lblHeaderPuntos = new JLabel("Puntaje");
        lblHeaderPuntos.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 4; gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelTabla.add(lblHeaderPuntos, gbc);
    }

    private void agregarFilaJugador(JPanel panel, int row, String pos, String nombre, String puntaje, String rutaAvatar) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = row;

        JLabel lblPos = new JLabel(pos);
        lblPos.setFont(new Font("SansSerif", Font.BOLD, 24));
        gbc.gridx = 0;
        panel.add(lblPos, gbc);

        ImageIcon iconoAvatar = cargarIcono(rutaAvatar, 40);
        JLabel lblAvatar = new JLabel(iconoAvatar);
        gbc.gridx = 1;
        panel.add(lblAvatar, gbc);

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        panel.add(lblNombre, gbc);

        JLabel lblPuntos = new JLabel(puntaje);
        lblPuntos.setFont(new Font("SansSerif", Font.BOLD, 24));
        gbc.gridx = 4;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblPuntos, gbc);
        
        ImageIcon iconoCartas = cargarIcono(IMAGEN_CARTAS, 30);
        JLabel lblIconoCartas = new JLabel(iconoCartas);
        gbc.gridx = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblIconoCartas, gbc);
    }

    
    private ImageIcon cargarIcono(String ruta, int tamaño) {
        try {

            ImageIcon iconoOriginal = new ImageIcon(ruta);
            
            if (iconoOriginal.getIconWidth() == -1) {

                java.net.URL url = getClass().getClassLoader().getResource(ruta);
                if (url != null) {
                    iconoOriginal = new ImageIcon(url);
                } else {
                    System.err.println("No se pudo cargar imagen: " + ruta);
                    return null;
                }
            }

            Image img = iconoOriginal.getImage();
            Image newImg = img.getScaledInstance(tamaño, tamaño, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);

        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + ruta);
            return null;
        }
    }
}
