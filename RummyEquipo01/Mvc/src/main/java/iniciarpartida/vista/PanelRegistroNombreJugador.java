
package iniciarpartida.vista;

import definiciones.IGestorEventosInicioPartida;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author romom
 */
public class PanelRegistroNombreJugador extends JPanel{
    
    private static final Color COLOR_FONDO_AZUL = new Color(30, 152, 198);
    private static final Color COLOR_FONDO_AMARILLO = new Color(255, 232, 151);
    private static final Color COLOR_BTN_CONFIRMAR = new Color(91, 155, 214);
    private static final Color COLOR_TEXTO = Color.BLACK;

    private static final Font FUENTE_TITULO = new Font("Lucida Fax", Font.PLAIN, 24);
    private static final Font FUENTE_TEXTO = new Font("Segoe UI", Font.PLAIN, 20);
    private static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 18);

    private static final int ANCHO_PANEL_CENTRAL = 450;
    private static final int ALTO_PANEL_CENTRAL = 250;
    private static final int ANCHO_TEXTFIELD = 250;
    private static final int ALTO_TEXTFIELD = 40;
    private static final int ANCHO_BOTON = 150;
    private static final int ALTO_BOTON = 50;

    private JTextField txtNombre;
    private JButton btnConfirmar;
    private IGestorEventosInicioPartida gestorEventos;

    public PanelRegistroNombreJugador() {
        configurarPanelPrincipal();
    }

    private void configurarPanelPrincipal() {
        this.setLayout(new GridBagLayout());
        this.setBackground(COLOR_FONDO_AZUL);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(COLOR_FONDO_AMARILLO);
        panelCentral.setPreferredSize(new Dimension(ANCHO_PANEL_CENTRAL, ALTO_PANEL_CENTRAL));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.gridx = 0;

        JLabel lblTitulo = new JLabel("Ingresa tu nombre:");
        lblTitulo.setFont(FUENTE_TITULO);
        lblTitulo.setForeground(COLOR_TEXTO);
        gbc.gridy = 0;
        panelCentral.add(lblTitulo, gbc);

        txtNombre = new JTextField();
        txtNombre.setFont(FUENTE_TEXTO);
        txtNombre.setPreferredSize(new Dimension(ANCHO_TEXTFIELD, ALTO_TEXTFIELD));
        txtNombre.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 25, 0);
        panelCentral.add(txtNombre, gbc);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(FUENTE_BOTON);
        btnConfirmar.setBackground(COLOR_BTN_CONFIRMAR);
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirmar.setPreferredSize(new Dimension(ANCHO_BOTON, ALTO_BOTON));
        
        btnConfirmar.addActionListener(e -> enviarNombre());
        
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        panelCentral.add(btnConfirmar, gbc);

        this.add(panelCentral);
    }

    private void enviarNombre() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre.", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            gestorEventos.registrarNombreJugador(nombre); 
        }
    }

    public void setGestorEventos(IGestorEventosInicioPartida gestorEventos) {
        this.gestorEventos = gestorEventos;
    }
 
}
