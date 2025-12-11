
package iniciarpartida.vista;

import definiciones.IGestorEventosInicioPartida;
import iniciarpartida.dto.EstadoPartida;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;


public class PanelConfiguracionPartida extends JPanel {

    private static final Color COLOR_FONDO_AZUL = new Color(30, 152, 198);
    private static final Color COLOR_FONDO_AMARILLO = new Color(255, 232, 151);
    private static final Color COLOR_BTN_CANCELAR = new Color(219, 81, 81);
    private static final Color COLOR_BTN_CONFIRMAR = new Color(91, 155, 214);
    private static final Color COLOR_TEXTO = Color.BLACK;
    private static final Color COLOR_TEXTO_BLANCO = Color.WHITE;

    private static final Font FUENTE_TITULO = new Font("Lucida Fax", Font.PLAIN, 24);
    private static final Font FUENTE_TEXTO = new Font("Segoe UI", Font.PLAIN, 24);
    private static final Font FUENTE_PEQUENA = new Font("Segoe UI", Font.PLAIN, 18);
    private static final Font FUENTE_BOTON = new Font("Segoe UI", Font.BOLD, 16);

    private static final int ANCHO_PANEL_CENTRAL = 600;
    private static final int ALTO_PANEL_CENTRAL = 450;
    private static final int ANCHO_SPINNER = 110;
    private static final int ALTO_SPINNER = 40;
    private static final int ANCHO_BOTON = 120;
    private static final int ALTO_BOTON = 45;

    private static final Insets MARGEN_TITULO = new Insets(20, 0, 30, 0);
    private static final Insets MARGEN_COMPONENTES = new Insets(10, 0, 10, 0);
    private static final Insets MARGEN_BOTONES = new Insets(30, 10, 10, 10);

    private static final int FICHAS_MIN = 5;
    private static final int FICHAS_MAX = 13;
    private static final int FICHAS_INICIAL = 5;
    private static final int FICHAS_STEP = 1;

    private static final int COMODINES_MIN = 0;
    private static final int COMODINES_MAX = 4;
    private static final int COMODINES_INICIAL = 2;
    private static final int COMODINES_STEP = 1;

    private JSpinner spinnerFichas;
    private JSpinner spinnerComodines;
    private JButton btnCancelar;
    private JButton btnConfirmar;

    private IGestorEventosInicioPartida gestorEventos;

    public PanelConfiguracionPartida() {
        configurarPanelPrincipal();
    }

    private void configurarPanelPrincipal() {
        this.setLayout(new GridBagLayout());
        this.setBackground(COLOR_FONDO_AZUL);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(COLOR_FONDO_AMARILLO);
        panelCentral.setPreferredSize(new Dimension(ANCHO_PANEL_CENTRAL, ALTO_PANEL_CENTRAL));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblTituloFichas = new JLabel("Elija el rango de número de las fichas:");
        lblTituloFichas.setFont(FUENTE_TITULO);
        lblTituloFichas.setForeground(COLOR_TEXTO);

        gbc.gridy = 0;
        gbc.insets = MARGEN_TITULO;
        panelCentral.add(lblTituloFichas, gbc);

        JPanel pnlFichas = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        pnlFichas.setBackground(COLOR_FONDO_AMARILLO);

        JLabel lblUno = new JLabel("1");
        lblUno.setFont(FUENTE_TEXTO);

        JLabel lblA = new JLabel("a");
        lblA.setFont(FUENTE_PEQUENA);

        spinnerFichas = crearSpinner(FICHAS_INICIAL, FICHAS_MIN, FICHAS_MAX, FICHAS_STEP);

        pnlFichas.add(lblUno);
        pnlFichas.add(lblA);
        pnlFichas.add(spinnerFichas);

        gbc.gridy = 1;
        gbc.insets = MARGEN_COMPONENTES;
        panelCentral.add(pnlFichas, gbc);

        JLabel lblTituloComodines = new JLabel("Elija el número de comodines");
        lblTituloComodines.setFont(FUENTE_TEXTO);

        gbc.gridy = 2;
        gbc.insets = new Insets(30, 0, 20, 0);
        panelCentral.add(lblTituloComodines, gbc);

        spinnerComodines = crearSpinner(COMODINES_INICIAL, COMODINES_MIN, COMODINES_MAX, COMODINES_STEP);

        gbc.gridy = 3;
        gbc.insets = MARGEN_COMPONENTES;
        panelCentral.add(spinnerComodines, gbc);

        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        pnlBotones.setBackground(COLOR_FONDO_AMARILLO);

        btnCancelar = crearBoton("Cancelar", COLOR_BTN_CANCELAR);
        btnCancelar.addActionListener(e -> accionCancelar());

        btnConfirmar = crearBoton("Confirmar", COLOR_BTN_CONFIRMAR);
        btnConfirmar.addActionListener(e -> enviarDatos());

        pnlBotones.add(btnCancelar);
        pnlBotones.add(btnConfirmar);

        gbc.gridy = 4;
        gbc.insets = MARGEN_BOTONES;
        panelCentral.add(pnlBotones, gbc);

        this.add(panelCentral);
    }

    private JSpinner crearSpinner(int val, int min, int max, int step) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(val, min, max, step));
        spinner.setFont(FUENTE_TEXTO);
        spinner.setPreferredSize(new Dimension(ANCHO_SPINNER, ALTO_SPINNER));
        
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        return spinner;
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(FUENTE_BOTON);
        btn.setBackground(colorFondo);
        btn.setForeground(COLOR_TEXTO_BLANCO);
        
        if (texto.equalsIgnoreCase("Cancelar")) {
            btn.setForeground(COLOR_TEXTO);
        }

        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(ANCHO_BOTON, ALTO_BOTON));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public void enviarDatos() {
        int maxFichas = (Integer) spinnerFichas.getValue();
        int numComodines = (Integer) spinnerComodines.getValue();
        gestorEventos.enviarDatosConfigurarPartida(maxFichas, numComodines);
    }

    private void accionCancelar() {
        gestorEventos.cancelarConfiguracionPartida();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void setGestorEventos(IGestorEventosInicioPartida gestorEventos) {
        this.gestorEventos = gestorEventos;
    }

    public void actualizar(EstadoPartida estadoPartida, String mensaje) {
        if (estadoPartida != EstadoPartida.CONFIGURADA) {
            JOptionPane.showMessageDialog(this, mensaje);
            
            gestorEventos.cancelarConfiguracionPartida();
            
        } else{
            gestorEventos.iniciarRegistroJugador();
        }
    }
}