
package iniciarpartida.vista;

import definiciones.IGestorEventosInicioPartida;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class PanelIngresoIP extends JPanel {

    private JTextField txtIP;
    private JButton btnContinuar;
    private JButton btnCancelar;
    private IGestorEventosInicioPartida gestorEventos;
    
     private final String CODIGO_MENSAJE_ACEPTACION_UNION = "AU: ";
    private final String CODIGO_MENSAJE_RECHAZO_UNION = "RU: ";
    
    private final String TITULO_ACEPTACION_UNION = "Aceptado";
    private final String TITULO_RECHAZO_UNION = "Aceptado";
    
    private final Color COLOR_FONDO = new Color(30, 152, 198);

    public PanelIngresoIP() {
        configurarComponentes();
    }
    
    /**
     * Método para preparar el panel antes de mostrarlo.
     * Reinicia el campo de texto y asegura visibilidad.
     */
    public void mostrar(String mensaje) {
        this.txtIP.setText("127.0.0.1");
        this.setVisible(true);
        this.revalidate();
        this.repaint();
        
        mostrarMensaje(mensaje);
    }
    
    private void mostrarMensaje(String mensaje){
        
        if(mensaje != null){
            if(mensaje.startsWith(CODIGO_MENSAJE_ACEPTACION_UNION)){
                
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_ACEPTACION_UNION, 
                JOptionPane.INFORMATION_MESSAGE);
                
                gestorEventos.iniciarRegistroJugador();
                
            } else if(mensaje.startsWith(CODIGO_MENSAJE_RECHAZO_UNION)){
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_RECHAZO_UNION, 
                JOptionPane.INFORMATION_MESSAGE);
            }
        }   
    }
    
    public String getIPIngresada() {
        return txtIP.getText();
    }
    
    public void setListenerContinuar(ActionListener l) {
        btnContinuar.addActionListener(l);
    }
    
    public void setListenerCancelar(ActionListener l) {
        btnCancelar.addActionListener(l);
    }

    private void configurarComponentes() {
        this.setLayout(new GridBagLayout());
        this.setBackground(COLOR_FONDO);
        
        JPanel contenido = new JPanel();
        contenido.setLayout(null);
        contenido.setPreferredSize(new Dimension(650, 420));
        contenido.setOpaque(false);

        Color amarillo = new Color(255, 237, 153);

        try {
            ImageIcon logo = new ImageIcon(getClass().getResource("/imgs/src.png"));
            JLabel logoLabel = new JLabel(logo);
            logoLabel.setBounds(20, 15, 150, 60);
            contenido.add(logoLabel);
        } catch (Exception e) {}

        // Panel Amarillo
        JPanel panel = new JPanel();
        panel.setBackground(amarillo);
        panel.setBounds(60, 100, 520, 220);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(amarillo, 20));
        contenido.add(panel);

        // Texto
        JLabel lblTexto = new JLabel("Ingrese IP de partida LAN:", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTexto.setBounds(40, 20, 440, 40);
        panel.add(lblTexto);

        // Campo IP
        txtIP = new JTextField("127.0.0.1");
        txtIP.setHorizontalAlignment(JTextField.CENTER);
        txtIP.setFont(new Font("Segoe UI", Font.BOLD, 20));
        txtIP.setBounds(130, 80, 260, 40);
        panel.add(txtIP);

        // Botón Cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(90, 150, 150, 40);
        btnCancelar.setBackground(new Color(239, 83, 80));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnCancelar.addActionListener( e -> gestorEventos.volverInicio());
        panel.add(btnCancelar);

        // Botón Continuar
        btnContinuar = new JButton("Continuar");
        btnContinuar.setBounds(280, 150, 150, 40);
        btnContinuar.setBackground(new Color(30, 136, 229));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnContinuar.addActionListener(e -> gestorEventos.solicitarUnirsePartdida());
        panel.add(btnContinuar);
        
        this.add(contenido);
    }

    public void setGestorEventos(IGestorEventosInicioPartida gestorEventos) {
        this.gestorEventos = gestorEventos;
    }

}