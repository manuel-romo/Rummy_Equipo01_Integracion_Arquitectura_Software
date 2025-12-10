
package iniciarpartida.vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.net.URL;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import definiciones.IGestorEventosInicioPartida;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */

public class PanelSalaEspera extends JPanel {

    // Colores.
    private final Color COLOR_FONDO = new Color(30, 152, 198);
    private final Color COLOR_AMARILLO = new Color(255, 232, 151);
    private final Color COLOR_BLANCO = Color.WHITE;
    private final Color COLOR_VERDE_BOTON = new Color(100, 180, 100);
    private final Color COLOR_ROJO_BOTON = new Color(220, 80, 80);
    private final Color COLOR_TEXTO_NEGRO = Color.BLACK;

    // Fuentes.
    private final Font FUENTE_ENCABEZADO = new Font("Arial", Font.BOLD, 36);
    private final Font FUENTE_NOMBRE_JUGADOR = new Font("Arial", Font.PLAIN, 22);
    private final Font FUENTE_BOTON = new Font("Arial", Font.BOLD, 18);
    private final Font FUENTE_ESPERANDO = new Font("Arial", Font.ITALIC, 14);
    
    // Códigos de mensajes.
    private final String CODIGO_MENSAJE_NUEVA_SOLICITUD_INICIO = "SI: ";
    private final String CODIGO_MENSAJE_CONFIRMAR_ENVIO_SOLICITUD_INICIO = "CE: ";
    private final String CODIGO_MENSAJE_ACEPTACION_INICIO = "AI: ";
    private final String CODIGO_MENSAJE_RECHAZO_INICIO = "RI: ";
    
    // Títulos de mensajes.
    private final String TITULO_NUEVA_SOLICITUD_INICIO = "Solicitud de inicio de juego";
    private final String TITULO_CONFIRMACION_ENVIO_SOLICITUD_INICIO = "Confirmar envío";
    private final String TITULO_ACEPTACION_INICIO = "Inicio de partida";
    private final String TITULO_RECHAZO_INICIO = "Rechazo de inicio";

    // Dimensiones para el panel de lista de jugadores.
    private final int ANCHO_PANEL_LISTA = 500;
    private final int ALTO_PANEL_LISTA = 450;
    private final int RADIO_ESQUINAS_PANEL = 30;
    private final int PADDING_PANEL_LISTA = 30;

    // Dimensiones para panel de cada jugador.
    private final int ANCHO_TIRA_JUGADOR = 400;
    private final int ALTO_TIRA_JUGADOR = 70;
    private final int RADIO_ESQUINAS_TIRA = 20;
    private final int ANCHO_AVATAR = 50;
    private final int ALTO_AVATAR = 50;
    private final int GAP_HORIZONTAL_TIRA = 20;
    private final int GAP_VERTICAL_TIRA = 10;

    // Dimensiones de botones.
    private final int ANCHO_BOTON_INICIAR = 150;
    private final int ALTO_BOTON_INICIAR = 45;
    private final int ANCHO_BOTON_SALIR = 120;
    private final int ALTO_BOTON_SALIR = 40;
    private final int RADIO_ESQUINAS_BOTON = 20;

    // Espaciados.
    private final int ESPACIO_ENTRE_JUGADORES = 15;
    private final int ESPACIO_ENTRE_BOTON_TEXTO = 10;
    private final int MARGEN_SUPERIOR_TITULO = 20;
    private final int MARGEN_INFERIOR_TITULO = 20;
    private final int MARGEN_DERECHO_SALIR = 30;
    private final int MARGEN_INFERIOR_SALIR = 30;

    // Textos.
    private final String ENCABEZADO_PANEL = "Sala de Espera";
    private final String TEXTO_BOTON_INICIAR = "Iniciar";
    private final String TEXTO_BOTON_SALIR = "Salir";
    private final String TEXTO_ESPERANDO = "Esperando jugadores...";
    private final String TEXTO_PLACEHOLDER_AVATAR = "[?]";

    // Datos de jugadores.
    List<JugadorInicioPartidaInformacionPanel> jugadores;
    
    // Mensaje.
    private String mensaje;
    
    // Cantidad de jugadores que han solicitado el inicio del juego.
    private int cantidadJugadoresIniciarJuego;

    // Componentes.
    private JPanel panelListaJugadores; 
    
    private IGestorEventosInicioPartida gestorEventos;

    public PanelSalaEspera(IGestorEventosInicioPartida gestorEventos) {
        
        this.gestorEventos = gestorEventos;
        
        configurarPanelPrincipal();
        actualizarPanelJugadores();
    }

    public void actualizar(List<JugadorInicioPartidaInformacionPanel> jugadores, String mensaje, int cantidadJugadoresIniciarJuego) {
        
        this.jugadores = jugadores;
        this.mensaje = mensaje;
        this.cantidadJugadoresIniciarJuego = cantidadJugadoresIniciarJuego;
        
        mostrarMensaje();
        actualizarPanelJugadores();
        
    }

    private void configurarPanelPrincipal() {

        // Fondo y layout
        setBackground(COLOR_FONDO);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Título del panel
        JLabel labelTitulo = new JLabel(ENCABEZADO_PANEL);
        labelTitulo.setFont(FUENTE_ENCABEZADO);
        labelTitulo.setForeground(COLOR_BLANCO);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; 
        gbc.weighty = 0.1; 
        gbc.insets = new Insets(MARGEN_SUPERIOR_TITULO, 0, MARGEN_INFERIOR_TITULO, 0);
        gbc.anchor = GridBagConstraints.SOUTH;
        add(labelTitulo, gbc);

        // Configuración de panel Jugadores.
        panelListaJugadores = new PanelBordesRedondeados(RADIO_ESQUINAS_PANEL, COLOR_AMARILLO);
        panelListaJugadores.setLayout(new BoxLayout(panelListaJugadores, BoxLayout.Y_AXIS));
        
        // Tamaño panel de lista de jugadores.
        panelListaJugadores.setPreferredSize(new Dimension(ANCHO_PANEL_LISTA, ALTO_PANEL_LISTA));
        
        // Marge intenerno.
        panelListaJugadores.setBorder(new EmptyBorder(PADDING_PANEL_LISTA, PADDING_PANEL_LISTA, PADDING_PANEL_LISTA, PADDING_PANEL_LISTA));

        // Posición de panel jugadores.
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelListaJugadores, gbc);

        // Botón Salir.
        BotonBordesRedondeados botonSalirDeSala = new BotonBordesRedondeados(TEXTO_BOTON_SALIR, COLOR_ROJO_BOTON);
        botonSalirDeSala.setPreferredSize(new Dimension(ANCHO_BOTON_SALIR, ALTO_BOTON_SALIR));
        
        GridBagConstraints gbcSalir = new GridBagConstraints();
        gbcSalir.gridx = 0; 
        gbcSalir.gridy = 2;
        gbcSalir.weighty = 0.5; 
        gbcSalir.weightx = 1.0;
        gbcSalir.anchor = GridBagConstraints.SOUTHEAST;
        gbcSalir.insets = new Insets(0, 0, MARGEN_INFERIOR_SALIR, MARGEN_DERECHO_SALIR);
        
        add(botonSalirDeSala, gbcSalir);
    }

    private void actualizarPanelJugadores() {

        panelListaJugadores.removeAll();

        if(jugadores != null){
            
            for (JugadorInicioPartidaInformacionPanel jugador: jugadores) {
            
                JPanel panelJugador = crearPanelJugador(jugador.getNombre(), jugador.getAvatar());

                // Alineación central.
                panelJugador.setAlignmentX(Component.CENTER_ALIGNMENT);

                panelListaJugadores.add(panelJugador);

                // Espacio entre cada jugador.
                panelListaJugadores.add(Box.createVerticalStrut(ESPACIO_ENTRE_JUGADORES)); 
                
            }
        }
        

        panelListaJugadores.add(Box.createVerticalGlue());
        
        // Botón de inicio.
        
        String textoBoton = TEXTO_BOTON_INICIAR;
        
        if (this.cantidadJugadoresIniciarJuego > 0) {
            
            int totalJugadores = jugadores.size();

            textoBoton = TEXTO_BOTON_INICIAR + " (" + this.cantidadJugadoresIniciarJuego + "/" + totalJugadores + ")";
        }
        
        BotonBordesRedondeados botonIniciarJuego = new BotonBordesRedondeados(textoBoton, COLOR_VERDE_BOTON);
        botonIniciarJuego.addActionListener(e -> gestorEventos.solicitarInicioJuego(1,1));
        
        botonIniciarJuego.setPreferredSize(new Dimension(ANCHO_BOTON_INICIAR, ALTO_BOTON_INICIAR));
        botonIniciarJuego.setMaximumSize(new Dimension(ANCHO_BOTON_INICIAR, ALTO_BOTON_INICIAR));
        botonIniciarJuego.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelListaJugadores.add(botonIniciarJuego);
        
        // Espacio entre botón y texto.
        panelListaJugadores.add(Box.createVerticalStrut(ESPACIO_ENTRE_BOTON_TEXTO));

        // Texto de espera de jugadores.
        JLabel labelEsperandoJugadores = new JLabel(TEXTO_ESPERANDO);
        labelEsperandoJugadores.setFont(FUENTE_ESPERANDO);
        labelEsperandoJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelListaJugadores.add(labelEsperandoJugadores);
        
        // Refrescar visualmente
        panelListaJugadores.revalidate();
        panelListaJugadores.repaint();
    }

    private JPanel crearPanelJugador(String nombre, String rutaAvatar) {
        
        // Panel de jugador
        JPanel panel = new PanelBordesRedondeados(RADIO_ESQUINAS_TIRA, COLOR_BLANCO);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, GAP_HORIZONTAL_TIRA, GAP_VERTICAL_TIRA)); 
        
        // Configuración de ancho y alto de panel.
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, ALTO_TIRA_JUGADOR));
        panel.setPreferredSize(new Dimension(ANCHO_TIRA_JUGADOR, ALTO_TIRA_JUGADOR)); 

        // Obtención de avatar.
        JLabel labelAvatar = new JLabel();
        URL imagenUrl = getClass().getResource(rutaAvatar);
        if (imagenUrl != null) {
            ImageIcon icon = new ImageIcon(imagenUrl);
            Image img = icon.getImage().getScaledInstance(ANCHO_AVATAR, ALTO_AVATAR, Image.SCALE_SMOOTH);
            labelAvatar.setIcon(new ImageIcon(img));
        } else {
            labelAvatar.setText(TEXTO_PLACEHOLDER_AVATAR);
        }

        // Nombre de jugador.
        JLabel labelNombre = new JLabel(nombre);
        labelNombre.setFont(FUENTE_NOMBRE_JUGADOR);
        labelNombre.setForeground(COLOR_TEXTO_NEGRO);

        panel.add(labelAvatar);
        panel.add(labelNombre);

        return panel;
    }
    
    private void mostrarMensaje(){
        
        if(mensaje != null){
            
            if(mensaje.startsWith(CODIGO_MENSAJE_NUEVA_SOLICITUD_INICIO)){
                
                int respuesta = JOptionPane.showConfirmDialog(
                    this, 
                    mensaje.substring(3), 
                    TITULO_NUEVA_SOLICITUD_INICIO, 
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                gestorEventos.confirmarInicioJuego((respuesta == JOptionPane.OK_OPTION),13,1);
                
            } 
            
            else if(mensaje.startsWith(CODIGO_MENSAJE_CONFIRMAR_ENVIO_SOLICITUD_INICIO)){
                
                int respuesta = JOptionPane.showConfirmDialog(
                        this, 
                        mensaje.substring(3), 
                        TITULO_CONFIRMACION_ENVIO_SOLICITUD_INICIO, 
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                gestorEventos.confirmarEnvioSolicitudInicioJuego((respuesta == JOptionPane.OK_OPTION),13,1);
                
            } else if(mensaje.startsWith(CODIGO_MENSAJE_ACEPTACION_INICIO)){
                
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_ACEPTACION_INICIO, 
                JOptionPane.INFORMATION_MESSAGE);

                gestorEventos.aceptarAceptacionInicioJuego();
                
            } else if(mensaje.startsWith(CODIGO_MENSAJE_RECHAZO_INICIO)){
                
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_RECHAZO_INICIO, 
                JOptionPane.INFORMATION_MESSAGE);

            }
        }
        
    }

    // Clase interna para los paneles redondeados.
    class PanelBordesRedondeados extends JPanel {
        private int radio;
        private Color color;

        public PanelBordesRedondeados(int radio, Color color) {
            this.radio = radio;
            this.color = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radio, radio);
            super.paintComponent(g);
        }
    }

    // Clase interna para botones redondeados.
    class BotonBordesRedondeados extends JButton {
        private Color colorFondo;

        public BotonBordesRedondeados(String texto, Color color) {
            super(texto);
            this.colorFondo = color;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(FUENTE_BOTON);
            setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Fondo redondeado
            g2.setColor(colorFondo);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), RADIO_ESQUINAS_BOTON, RADIO_ESQUINAS_BOTON);
            
            super.paintComponent(g);
        }
    }
}