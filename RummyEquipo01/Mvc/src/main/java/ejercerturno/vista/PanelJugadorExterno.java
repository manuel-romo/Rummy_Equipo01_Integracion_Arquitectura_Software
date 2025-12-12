
package ejercerturno.vista;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ejercerturno.vista.PanelFichasRestantesJugador;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */

public class PanelJugadorExterno extends JPanel implements IComponente{
    
    private PosicionPanel posicionPanel;
    private Color COLOR_FONDO_PANEL_MANO = new Color(69, 42, 32);
    
    private JPanel panelMano = new JPanel();
    private JPanel panelLateralMano1 = new JPanel();
    private JPanel panelLateralMano2 = new JPanel();
    
    private JPanel panelSuperiorLateralMano1 = new JPanel();
    private JPanel panelSuperiorLateralMano2 = new JPanel();
    
    private JPanel panelEsquina1 = new JPanel();
    private JPanel panelEsquina2 = new JPanel();
    
    JLabel labelFichasRestantes = new JLabel();
    JLabel labelNombreJugador = new JLabel();
    JLabel labelAvatarJugador = new JLabel();

    private String nombreJugador;
    private String avatarJugador;
    private String cantidadFichasRestantes;
    
    private String IMAGEN_FICHAS = "fichas.png";
    
    private int ANCHO_ALTO_ICONOS = 35;
    
    Font fuenteDatosJugador = new Font("Arial", Font.BOLD, 26);
    
    public PanelJugadorExterno(PosicionPanel posicionPanel, String nombreJugador, String avatarJugador){
        
        this.nombreJugador = nombreJugador;
        this.avatarJugador = avatarJugador;
        this.posicionPanel = posicionPanel;
                
        setOpaque(false);

        panelMano.setLayout(new GridBagLayout());
         
        panelMano.setBackground(COLOR_FONDO_PANEL_MANO);
        panelLateralMano1.setBackground(COLOR_FONDO_PANEL_MANO);
        panelLateralMano2.setBackground(COLOR_FONDO_PANEL_MANO);
        panelSuperiorLateralMano1.setOpaque(false);
        panelSuperiorLateralMano2.setOpaque(false);
        panelEsquina1.setOpaque(false);
        panelEsquina2.setOpaque(false);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        
        if(posicionPanel.equals(PosicionPanel.IZQUIERDA_CENTRO)){
            configurarPanelManoIzquierdaCentro();
            configurarPanelLateralMano1IzquierdaCentro();
            configurarPanelLateralMano2IzquierdaCentro();
            configurarPanelSuperiorLateralMano1IzquierdaCentro();
            configurarPanelSuperiorLateralMano2IzquierdaCentro();
            configurarPanelEsquina1IzquierdaCentro();
            configurarPanelEsquina2IzquierdaCentro();
            configurarPanelDatosJugadorIzquierdaCentro();
        } 
        else if(posicionPanel.equals(PosicionPanel.DERECHA_CENTRO)){
            configurarPanelManoDerechaCentro();
            configurarPanelLateralMano1DerechaCentro();
            configurarPanelLateralMano2DerechaCentro();
            configurarPanelSuperiorLateralMano1DerechaCentro();
            configurarPanelSuperiorLateralMano2DerechaCentro();
            configurarPanelEsquina1DerechaCentro();
            configurarPanelEsquina2DerechaCentro();
            configurarPanelDatosJugadorDerechaCentro();
        } 
        else if(posicionPanel.equals(PosicionPanel.CENTRO_ARRIBA)){
            configurarPanelManoCentroArriba();
            configurarPanelLateralMano1CentroArriba();
            configurarPanelLateralMano2CentroArriba();
            configurarPanelSuperiorLateralMano1CentroArriba();
            configurarPanelSuperiorLateralMano2CentroArriba();
            configurarPanelEsquina1CentroArriba();
            configurarPanelEsquina2CentroArriba();
            configurarPanelDatosJugadorCentroArriba();
        }
        
    }
    
     // Métodos para panel en Derecha Centro
    
    private void configurarPanelManoDerechaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelMano = new GridBagConstraints();
        
        gridBagConstraintsPanelMano.gridx = 7;
        gridBagConstraintsPanelMano.gridy = 4;
        gridBagConstraintsPanelMano.weightx = 1;
        gridBagConstraintsPanelMano.weighty = 6;
        gridBagConstraintsPanelMano.gridwidth = 2;
        gridBagConstraintsPanelMano.gridheight = 3;
        gridBagConstraintsPanelMano.fill = GridBagConstraints.BOTH;
        add(panelMano, gridBagConstraintsPanelMano);
    }
    
    private void configurarPanelLateralMano1DerechaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelLateralMano1 = new GridBagConstraints();
        
        gridBagConstraintsPanelLateralMano1.gridx = 7;
        gridBagConstraintsPanelLateralMano1.gridy = 3;
        gridBagConstraintsPanelLateralMano1.weightx = 0.1;
        gridBagConstraintsPanelLateralMano1.weighty = 1;
        gridBagConstraintsPanelLateralMano1.gridwidth = 1;
        gridBagConstraintsPanelLateralMano1.gridheight = 1;
        gridBagConstraintsPanelLateralMano1.fill = GridBagConstraints.BOTH;
        
        add(panelLateralMano1, gridBagConstraintsPanelLateralMano1);
    }
    
    private void configurarPanelLateralMano2DerechaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelLateralMano2 = new GridBagConstraints();
        
        gridBagConstraintsPanelLateralMano2.gridx = 7;
        gridBagConstraintsPanelLateralMano2.gridy = 7;
        gridBagConstraintsPanelLateralMano2.weightx = 0.1;
        gridBagConstraintsPanelLateralMano2.weighty = 1;
        gridBagConstraintsPanelLateralMano2.gridwidth = 1;
        gridBagConstraintsPanelLateralMano2.gridheight = 1;
        gridBagConstraintsPanelLateralMano2.fill = GridBagConstraints.BOTH;
       
        add(panelLateralMano2, gridBagConstraintsPanelLateralMano2);
    }
    
    private void configurarPanelSuperiorLateralMano1DerechaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelSuperiorLateralMano1 = new GridBagConstraints();
        
        gridBagConstraintsPanelSuperiorLateralMano1.gridx = 8;
        gridBagConstraintsPanelSuperiorLateralMano1.gridy = 3;
        gridBagConstraintsPanelSuperiorLateralMano1.weightx = 0.1;
        gridBagConstraintsPanelSuperiorLateralMano1.weighty = 1;
        gridBagConstraintsPanelSuperiorLateralMano1.gridwidth = 1;
        gridBagConstraintsPanelSuperiorLateralMano1.gridheight = 1;
        gridBagConstraintsPanelSuperiorLateralMano1.fill = GridBagConstraints.BOTH;
        
        add(panelSuperiorLateralMano1, gridBagConstraintsPanelSuperiorLateralMano1);
        
    }
    
    private void configurarPanelSuperiorLateralMano2DerechaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelSuperiorLateralMano2 = new GridBagConstraints();
        
        gridBagConstraintsPanelSuperiorLateralMano2.gridx = 8;
        gridBagConstraintsPanelSuperiorLateralMano2.gridy = 7;
        gridBagConstraintsPanelSuperiorLateralMano2.weightx = 0.1;
        gridBagConstraintsPanelSuperiorLateralMano2.weighty = 1;
        gridBagConstraintsPanelSuperiorLateralMano2.gridwidth = 1;
        gridBagConstraintsPanelSuperiorLateralMano2.gridheight = 1;
        gridBagConstraintsPanelSuperiorLateralMano2.fill = GridBagConstraints.BOTH;
        
        add(panelSuperiorLateralMano2, gridBagConstraintsPanelSuperiorLateralMano2);
        
    }
    
    private void configurarPanelEsquina1DerechaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelEsquina1 = new GridBagConstraints();
        
        gridBagConstraintsPanelEsquina1.gridx = 7;
        gridBagConstraintsPanelEsquina1.gridy = 2;
        gridBagConstraintsPanelEsquina1.weightx = 1;
        gridBagConstraintsPanelEsquina1.weighty = 1;
        gridBagConstraintsPanelEsquina1.gridwidth = 2;
        gridBagConstraintsPanelEsquina1.gridheight = 1;
        gridBagConstraintsPanelEsquina1.fill = GridBagConstraints.BOTH;
        
        add(panelEsquina1, gridBagConstraintsPanelEsquina1);
        
    }
    
    private void configurarPanelEsquina2DerechaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelEsquina1 = new GridBagConstraints();
        
        gridBagConstraintsPanelEsquina1.gridx = 7;
        gridBagConstraintsPanelEsquina1.gridy = 8;
        gridBagConstraintsPanelEsquina1.weightx = 1;
        gridBagConstraintsPanelEsquina1.weighty = 1;
        gridBagConstraintsPanelEsquina1.gridwidth = 2;
        gridBagConstraintsPanelEsquina1.gridheight = 1;
        gridBagConstraintsPanelEsquina1.fill = GridBagConstraints.BOTH;
        
        add(panelEsquina2, gridBagConstraintsPanelEsquina1);
        
    }
    
    private void configurarPanelDatosJugadorDerechaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelDatosJugador = new GridBagConstraints();
        
        gridBagConstraintsPanelDatosJugador.gridx = 0;
        gridBagConstraintsPanelDatosJugador.gridy = 0;
        
        JPanel panelDatosJugador = new JPanel();
        panelDatosJugador.setLayout(new BoxLayout(panelDatosJugador, BoxLayout.Y_AXIS));
        panelDatosJugador.setOpaque(false);
                
        labelNombreJugador = new JLabel(nombreJugador) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setFont(getFont());
                g2.setColor(getForeground());

                String texto = nombreJugador;

                FontMetrics fm = g2.getFontMetrics();
                int anchoTexto = fm.stringWidth(texto);
                int altoTexto = fm.getAscent();

                g2.translate(getWidth() / 2, getHeight() / 2);

                g2.rotate(Math.toRadians(90));

                g2.drawString(texto, -anchoTexto / 2, altoTexto / 2 - 2);

                g2.dispose();
            }
        };
        
        labelNombreJugador.setPreferredSize(new java.awt.Dimension(10, 110));
        
        labelNombreJugador.setForeground(Color.WHITE);
        labelNombreJugador.setFont(fuenteDatosJugador);

        labelNombreJugador.repaint();
        
        configurarLabelImagenAvatarJugadorDerechaCentro();
        
        panelDatosJugador.add(labelAvatarJugador);
        
        panelDatosJugador.add(labelNombreJugador);
        
        panelDatosJugador.add(obtenerPanelFichasRestantesDerechaCentro());
        
        panelMano.add(panelDatosJugador, gridBagConstraintsPanelDatosJugador);
        
    }
    
    private JPanel obtenerPanelFichasRestantesDerechaCentro(){
        
        
        JPanel panelFichasRestantes = new PanelFichasRestantesJugador(12);
        panelFichasRestantes.setBackground(Color.WHITE);
        
        URL urlImagen = getClass().getClassLoader().getResource(IMAGEN_FICHAS);
         
        ImageIcon iconoFichas = new ImageIcon(urlImagen);
        
        Image imagenOrignal = iconoFichas.getImage();

        Image imagenEscalada = imagenOrignal.getScaledInstance(
            ANCHO_ALTO_ICONOS, 
            ANCHO_ALTO_ICONOS, 
            Image.SCALE_SMOOTH
        );

        ImageIcon iconoFichasEscalado = new ImageIcon(imagenEscalada);
            
        JLabel labelFichas = new JLabel(iconoFichasEscalado);
        
        panelFichasRestantes.add(labelFichas);
 
        labelFichasRestantes = new JLabel(cantidadFichasRestantes);
        
        labelFichasRestantes.setFont(fuenteDatosJugador);
        
        panelFichasRestantes.add(labelFichasRestantes);
        
        return panelFichasRestantes;
        
        
    }
    
    private void configurarLabelImagenAvatarJugadorDerechaCentro(){
        
        try{
            URL url = getClass().getClassLoader().getResource(avatarJugador);

            BufferedImage original = ImageIO.read(url);

            int ancho = original.getWidth();
            int alto = original.getHeight();

            BufferedImage rotada = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = rotada.createGraphics();

            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
                                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            AffineTransform at = new AffineTransform();
            at.translate(alto / 2.0, ancho / 2.0);
            at.rotate(Math.toRadians(90));
            at.translate(-ancho / 2.0, -alto / 2.0);

            g2.setTransform(at);
            g2.drawImage(original, 0, 0, null);
            g2.dispose();
            
            Image imagenEscalada = rotada.getScaledInstance(
                ANCHO_ALTO_ICONOS, 
                ANCHO_ALTO_ICONOS, 
                Image.SCALE_SMOOTH
            );
            
            labelAvatarJugador.setIcon(new ImageIcon(imagenEscalada));

            labelAvatarJugador.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));


        } catch (Exception e) {}
    }
    
    
    // Métodos para panel en Izquierda Centro
    private void configurarPanelManoIzquierdaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelMano = new GridBagConstraints();
        
        gridBagConstraintsPanelMano.gridx = 0;
        gridBagConstraintsPanelMano.gridy = 4;
        gridBagConstraintsPanelMano.weightx = 1;
        gridBagConstraintsPanelMano.weighty = 5;
        gridBagConstraintsPanelMano.gridwidth = 2;
        gridBagConstraintsPanelMano.gridheight = 3;
        gridBagConstraintsPanelMano.fill = GridBagConstraints.BOTH;
        add(panelMano, gridBagConstraintsPanelMano);
    }
    
    private void configurarPanelLateralMano1IzquierdaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelLateralMano1 = new GridBagConstraints();
        
        gridBagConstraintsPanelLateralMano1.gridx = 1;
        gridBagConstraintsPanelLateralMano1.gridy = 7;
        gridBagConstraintsPanelLateralMano1.weightx = 0.1;
        gridBagConstraintsPanelLateralMano1.weighty = 1;
        gridBagConstraintsPanelLateralMano1.gridwidth = 1;
        gridBagConstraintsPanelLateralMano1.gridheight = 1;
        gridBagConstraintsPanelLateralMano1.fill = GridBagConstraints.BOTH;
        
        add(panelLateralMano1, gridBagConstraintsPanelLateralMano1);
    }
    
    private void configurarPanelLateralMano2IzquierdaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelLateralMano2 = new GridBagConstraints();
        
        gridBagConstraintsPanelLateralMano2.gridx = 1;
        gridBagConstraintsPanelLateralMano2.gridy = 3;
        gridBagConstraintsPanelLateralMano2.weightx = 0.1;
        gridBagConstraintsPanelLateralMano2.weighty = 1;
        gridBagConstraintsPanelLateralMano2.gridwidth = 1;
        gridBagConstraintsPanelLateralMano2.gridheight = 1;
        gridBagConstraintsPanelLateralMano2.fill = GridBagConstraints.BOTH;
       
        add(panelLateralMano2, gridBagConstraintsPanelLateralMano2);
    }
    
    private void configurarPanelSuperiorLateralMano1IzquierdaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelSuperiorLateralMano1 = new GridBagConstraints();
        
        gridBagConstraintsPanelSuperiorLateralMano1.gridx = 0;
        gridBagConstraintsPanelSuperiorLateralMano1.gridy = 7;
        gridBagConstraintsPanelSuperiorLateralMano1.weightx = 0.1;
        gridBagConstraintsPanelSuperiorLateralMano1.weighty = 1;
        gridBagConstraintsPanelSuperiorLateralMano1.gridwidth = 1;
        gridBagConstraintsPanelSuperiorLateralMano1.gridheight = 1;
        gridBagConstraintsPanelSuperiorLateralMano1.fill = GridBagConstraints.BOTH;
        
        add(panelSuperiorLateralMano1, gridBagConstraintsPanelSuperiorLateralMano1);
        
    }
    
    private void configurarPanelSuperiorLateralMano2IzquierdaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelSuperiorLateralMano2 = new GridBagConstraints();
        
        gridBagConstraintsPanelSuperiorLateralMano2.gridx = 0;
        gridBagConstraintsPanelSuperiorLateralMano2.gridy = 3;
        gridBagConstraintsPanelSuperiorLateralMano2.weightx = 0.1;
        gridBagConstraintsPanelSuperiorLateralMano2.weighty = 1;
        gridBagConstraintsPanelSuperiorLateralMano2.gridwidth = 1;
        gridBagConstraintsPanelSuperiorLateralMano2.gridheight = 1;
        gridBagConstraintsPanelSuperiorLateralMano2.fill = GridBagConstraints.BOTH;
        
        add(panelSuperiorLateralMano2, gridBagConstraintsPanelSuperiorLateralMano2);
        
    }
    
    private void configurarPanelEsquina1IzquierdaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelEsquina1 = new GridBagConstraints();
        
        gridBagConstraintsPanelEsquina1.gridx = 0;
        gridBagConstraintsPanelEsquina1.gridy = 8;
        gridBagConstraintsPanelEsquina1.weightx = 1;
        gridBagConstraintsPanelEsquina1.weighty = 1;
        gridBagConstraintsPanelEsquina1.gridwidth = 2;
        gridBagConstraintsPanelEsquina1.gridheight = 1;
        gridBagConstraintsPanelEsquina1.fill = GridBagConstraints.BOTH;
        
        add(panelEsquina1, gridBagConstraintsPanelEsquina1);
        
    }
    
    private void configurarPanelEsquina2IzquierdaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelEsquina1 = new GridBagConstraints();
        
        gridBagConstraintsPanelEsquina1.gridx = 0;
        gridBagConstraintsPanelEsquina1.gridy = 2;
        gridBagConstraintsPanelEsquina1.weightx = 1;
        gridBagConstraintsPanelEsquina1.weighty = 1;
        gridBagConstraintsPanelEsquina1.gridwidth = 2;
        gridBagConstraintsPanelEsquina1.gridheight = 1;
        gridBagConstraintsPanelEsquina1.fill = GridBagConstraints.BOTH;
        
        add(panelEsquina2, gridBagConstraintsPanelEsquina1);
        
    }
    
    private void configurarPanelDatosJugadorIzquierdaCentro(){
        
        GridBagConstraints gridBagConstraintsPanelDatosJugador = new GridBagConstraints();
        
        gridBagConstraintsPanelDatosJugador.gridx = 0;
        gridBagConstraintsPanelDatosJugador.gridy = 2;
        
        JPanel panelDatosJugador = new JPanel();
        panelDatosJugador.setLayout(new BoxLayout(panelDatosJugador, BoxLayout.Y_AXIS));
        panelDatosJugador.setOpaque(false);
                
        labelNombreJugador = new JLabel(nombreJugador) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setFont(getFont());
                g2.setColor(getForeground());

                String texto = nombreJugador;

                FontMetrics fm = g2.getFontMetrics();
                int anchoTexto = fm.stringWidth(texto);
                int altoTexto = fm.getAscent();

                g2.translate(getWidth() / 2, getHeight() / 2);

                g2.rotate(Math.toRadians(-90));

                g2.drawString(texto, -anchoTexto / 2, altoTexto / 2 - 2);

                g2.dispose();
            }
        };
        
        labelNombreJugador.setPreferredSize(new java.awt.Dimension(10, 100));
        
        labelNombreJugador.setForeground(Color.WHITE);
        labelNombreJugador.setFont(fuenteDatosJugador);

        labelNombreJugador.repaint();
        panelDatosJugador.add(labelNombreJugador);
        
        configurarLabelImagenAvatarJugadorIzquierdaCentro();
                
        panelDatosJugador.add(labelAvatarJugador);
        
        panelDatosJugador.add(obtenerPanelFichasRestantesIzquierdaCentro());
        
        panelMano.add(panelDatosJugador, gridBagConstraintsPanelDatosJugador);
        
    }
    
    private JPanel obtenerPanelFichasRestantesIzquierdaCentro(){
        
        
        JPanel panelFichasRestantes = new PanelFichasRestantesJugador(12);
        panelFichasRestantes.setBackground(Color.WHITE);
        
        URL urlImagen = getClass().getClassLoader().getResource(IMAGEN_FICHAS);
         
        ImageIcon iconoFichas = new ImageIcon(urlImagen);
        
        Image imagenOrignal = iconoFichas.getImage();

        Image imagenEscalada = imagenOrignal.getScaledInstance(
            ANCHO_ALTO_ICONOS, 
            ANCHO_ALTO_ICONOS, 
            Image.SCALE_SMOOTH
        );

        ImageIcon iconoFichasEscalado = new ImageIcon(imagenEscalada);
            
        JLabel labelFichas = new JLabel(iconoFichasEscalado);
        
        panelFichasRestantes.add(labelFichas);
        
        labelFichasRestantes = new JLabel(cantidadFichasRestantes);
        
        labelFichasRestantes.setFont(fuenteDatosJugador);
        
        panelFichasRestantes.add(labelFichasRestantes);
        
        return panelFichasRestantes;
        
    }
    
    private void configurarLabelImagenAvatarJugadorIzquierdaCentro(){
        
        try{
            URL url = getClass().getClassLoader().getResource(avatarJugador);

            BufferedImage original = ImageIO.read(url);

            int ancho = original.getWidth();
            int alto = original.getHeight();

            BufferedImage rotada = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = rotada.createGraphics();

            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
                                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            AffineTransform at = new AffineTransform();
            at.translate(alto / 2.0, ancho / 2.0);
            at.rotate(Math.toRadians(-90));
            at.translate(-ancho / 2.0, -alto / 2.0);

            g2.setTransform(at);
            g2.drawImage(original, 0, 0, null);
            g2.dispose();
            
            Image imagenEscalada = rotada.getScaledInstance(
                ANCHO_ALTO_ICONOS, 
                ANCHO_ALTO_ICONOS, 
                Image.SCALE_SMOOTH
            );
            
            labelAvatarJugador.setIcon(new ImageIcon(imagenEscalada));

            labelAvatarJugador.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
    
        } catch (Exception e) {}
    }
    
    
    
    // Métodos para panel en Centro Arriba
    
    private void configurarPanelManoCentroArriba(){
        
        GridBagConstraints gridBagConstraintsPanelMano = new GridBagConstraints();
        
        gridBagConstraintsPanelMano.gridx = 3;
        gridBagConstraintsPanelMano.gridy = 0;
        gridBagConstraintsPanelMano.weightx = 1.5;
        gridBagConstraintsPanelMano.weighty = 1;
        gridBagConstraintsPanelMano.gridwidth = 3;
        gridBagConstraintsPanelMano.gridheight = 2;
        gridBagConstraintsPanelMano.fill = GridBagConstraints.BOTH;
        
        panelMano.repaint();
        
        add(panelMano, gridBagConstraintsPanelMano);
    }
    
    private void configurarPanelLateralMano1CentroArriba(){
        
        GridBagConstraints gridBagConstraintsPanelLateralMano1 = new GridBagConstraints();
        
        gridBagConstraintsPanelLateralMano1.gridx = 2;
        gridBagConstraintsPanelLateralMano1.gridy = 1;
        gridBagConstraintsPanelLateralMano1.weightx = 0.1;
        gridBagConstraintsPanelLateralMano1.weighty = 1;
        gridBagConstraintsPanelLateralMano1.gridwidth = 1;
        gridBagConstraintsPanelLateralMano1.gridheight = 1;
        gridBagConstraintsPanelLateralMano1.fill = GridBagConstraints.BOTH;
        
        add(panelLateralMano1, gridBagConstraintsPanelLateralMano1);
    }
    
    private void configurarPanelLateralMano2CentroArriba(){
        
        GridBagConstraints gridBagConstraintsPanelLateralMano2 = new GridBagConstraints();
        
        gridBagConstraintsPanelLateralMano2.gridx = 6;
        gridBagConstraintsPanelLateralMano2.gridy = 1;
        gridBagConstraintsPanelLateralMano2.weightx = 0.1;
        gridBagConstraintsPanelLateralMano2.weighty = 1;
        gridBagConstraintsPanelLateralMano2.gridwidth = 1;
        gridBagConstraintsPanelLateralMano2.gridheight = 1;
        gridBagConstraintsPanelLateralMano2.fill = GridBagConstraints.BOTH;
       
        add(panelLateralMano2, gridBagConstraintsPanelLateralMano2);
    }
    
    private void configurarPanelSuperiorLateralMano1CentroArriba(){
        
        GridBagConstraints gridBagConstraintsPanelSuperiorLateralMano1 = new GridBagConstraints();
        
        gridBagConstraintsPanelSuperiorLateralMano1.gridx = 2;
        gridBagConstraintsPanelSuperiorLateralMano1.gridy = 0;
        gridBagConstraintsPanelSuperiorLateralMano1.weightx = 0.1;
        gridBagConstraintsPanelSuperiorLateralMano1.weighty = 1;
        gridBagConstraintsPanelSuperiorLateralMano1.gridwidth = 1;
        gridBagConstraintsPanelSuperiorLateralMano1.gridheight = 1;
        gridBagConstraintsPanelSuperiorLateralMano1.fill = GridBagConstraints.BOTH;
        
        add(panelSuperiorLateralMano1, gridBagConstraintsPanelSuperiorLateralMano1);
        
    }
    
    private void configurarPanelSuperiorLateralMano2CentroArriba(){
        
        GridBagConstraints gridBagConstraintsPanelSuperiorLateralMano2 = new GridBagConstraints();
        
        gridBagConstraintsPanelSuperiorLateralMano2.gridx = 6;
        gridBagConstraintsPanelSuperiorLateralMano2.gridy = 0;
        gridBagConstraintsPanelSuperiorLateralMano2.weightx = 0.1;
        gridBagConstraintsPanelSuperiorLateralMano2.weighty = 1;
        gridBagConstraintsPanelSuperiorLateralMano2.gridwidth = 1;
        gridBagConstraintsPanelSuperiorLateralMano2.gridheight = 1;
        gridBagConstraintsPanelSuperiorLateralMano2.fill = GridBagConstraints.BOTH;
        
        add(panelSuperiorLateralMano2, gridBagConstraintsPanelSuperiorLateralMano2);
        
    }
    
    private void configurarPanelEsquina1CentroArriba(){
        
        GridBagConstraints gridBagConstraintsPanelEsquina1 = new GridBagConstraints();
        
        gridBagConstraintsPanelEsquina1.gridx = 0;
        gridBagConstraintsPanelEsquina1.gridy = 0;
        gridBagConstraintsPanelEsquina1.weightx = 1;
        gridBagConstraintsPanelEsquina1.weighty = 1;
        gridBagConstraintsPanelEsquina1.gridwidth = 2;
        gridBagConstraintsPanelEsquina1.gridheight = 2;
        gridBagConstraintsPanelEsquina1.fill = GridBagConstraints.BOTH;
        
        add(panelEsquina1, gridBagConstraintsPanelEsquina1);
        
    }
    
    private void configurarPanelEsquina2CentroArriba(){
        
        GridBagConstraints gridBagConstraintsPanelEsquina2 = new GridBagConstraints();
        
        gridBagConstraintsPanelEsquina2.gridx = 7;
        gridBagConstraintsPanelEsquina2.gridy = 0;
        gridBagConstraintsPanelEsquina2.weightx = 1;
        gridBagConstraintsPanelEsquina2.weighty = 1;
        gridBagConstraintsPanelEsquina2.gridwidth = 2;
        gridBagConstraintsPanelEsquina2.gridheight = 2;
        gridBagConstraintsPanelEsquina2.fill = GridBagConstraints.BOTH;
        
        add(panelEsquina2, gridBagConstraintsPanelEsquina2);
        
    }
    
    private void configurarPanelDatosJugadorCentroArriba(){
        
        GridBagConstraints gridBagConstraintsPanelDatosJugador = new GridBagConstraints();
        
        gridBagConstraintsPanelDatosJugador.gridx = 0;
        gridBagConstraintsPanelDatosJugador.gridy = 0;
        
        JPanel panelDatosJugador = new JPanel(new FlowLayout());
        panelDatosJugador.setOpaque(false);
        
        panelDatosJugador.add(obtenerPanelFichasRestantesCentroArriba());
        
        configurarLabelImagenAvatarJugadorCentroArriba();
        
        panelDatosJugador.add(labelAvatarJugador);
        
        labelNombreJugador = new JLabel(nombreJugador);
        labelNombreJugador.setForeground(Color.WHITE);
        labelNombreJugador.setFont(fuenteDatosJugador);
        
        panelDatosJugador.add(labelNombreJugador);
        
        panelMano.add(panelDatosJugador, gridBagConstraintsPanelDatosJugador);
        
    }
    
    private JPanel obtenerPanelFichasRestantesCentroArriba(){
        
        
        JPanel panelFichasRestantes = new PanelFichasRestantesJugador(12);
        panelFichasRestantes.setBackground(Color.WHITE);
        
        URL urlImagen = getClass().getClassLoader().getResource(IMAGEN_FICHAS);
         
        ImageIcon iconoFichas = new ImageIcon(urlImagen);
        
        Image imagenOrignal = iconoFichas.getImage();

        Image imagenEscalada = imagenOrignal.getScaledInstance(
            ANCHO_ALTO_ICONOS, 
            ANCHO_ALTO_ICONOS, 
            Image.SCALE_SMOOTH
        );

        ImageIcon iconoFichasEscalado = new ImageIcon(imagenEscalada);
            
        JLabel labelFichas = new JLabel(iconoFichasEscalado);
        
        panelFichasRestantes.add(labelFichas);
        
        labelFichasRestantes = new JLabel(cantidadFichasRestantes);
        
        labelFichasRestantes.setFont(fuenteDatosJugador);
        
        panelFichasRestantes.add(labelFichasRestantes);
        
        return panelFichasRestantes;
        
    }
    
    private void configurarLabelImagenAvatarJugadorCentroArriba(){
        
        URL urlImagen = getClass().getClassLoader().getResource(avatarJugador);
        
        if(urlImagen != null){
            ImageIcon iconoAvatar = new ImageIcon(urlImagen);
        
            Image imagenOrignal = iconoAvatar.getImage();

            Image imagenEscalada = imagenOrignal.getScaledInstance(
                ANCHO_ALTO_ICONOS, 
                ANCHO_ALTO_ICONOS, 
                Image.SCALE_SMOOTH
            );

            ImageIcon iconoAvatarEscalado = new ImageIcon(imagenEscalada);

            labelAvatarJugador.setIcon(iconoAvatarEscalado);
        }
        

         
    }
    
    
    @Override
    public void agregarComponente(IComponente componente) {}

    @Override
    public void removerComponente(IComponente componente) {}

    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }

    public void pintar(IEstadoJugadorExterno estadoJugadorExterno) {
        
        JugadorExternoInformacionPanel[] jugadoresExternosInformacion = estadoJugadorExterno.getJugadoresExternos();
        
        JugadorExternoInformacionPanel jugadorExternoInformacionPanel = null;
        
        if(jugadoresExternosInformacion != null){
            if(jugadoresExternosInformacion.length == 3){
            
                switch (posicionPanel) {
                    case PosicionPanel.IZQUIERDA_CENTRO:

                        jugadorExternoInformacionPanel = jugadoresExternosInformacion[0];


                        break;

                    case PosicionPanel.CENTRO_ARRIBA:

                        jugadorExternoInformacionPanel = jugadoresExternosInformacion[1];

                        break;

                    case PosicionPanel.DERECHA_CENTRO:

                        jugadorExternoInformacionPanel = jugadoresExternosInformacion[2];

                        break;

                    default:
                        throw new AssertionError();
                }

            } else if(jugadoresExternosInformacion.length == 2){

                switch (posicionPanel) {
                    case PosicionPanel.IZQUIERDA_CENTRO:

                        jugadorExternoInformacionPanel = jugadoresExternosInformacion[0];

                        break;

                    case PosicionPanel.CENTRO_ARRIBA:

                        jugadorExternoInformacionPanel = jugadoresExternosInformacion[1];

                        break;

                    default:
                        throw new AssertionError();
                }

            } else if(jugadoresExternosInformacion.length == 1){

                if(posicionPanel == PosicionPanel.CENTRO_ARRIBA){

                    jugadorExternoInformacionPanel = jugadoresExternosInformacion[0];

                    cantidadFichasRestantes = jugadorExternoInformacionPanel.getFichasRestantes();

                }

            }
            
            nombreJugador = jugadorExternoInformacionPanel.getNombre();
            avatarJugador = jugadorExternoInformacionPanel.getAvatar();
            cantidadFichasRestantes = jugadorExternoInformacionPanel.getFichasRestantes();

            labelFichasRestantes.setText(cantidadFichasRestantes);
            labelNombreJugador.setText(nombreJugador);
        }

        switch (posicionPanel) {
            case PosicionPanel.DERECHA_CENTRO:
                
                configurarLabelImagenAvatarJugadorDerechaCentro();
                
                break;
                
            case PosicionPanel.IZQUIERDA_CENTRO:
                
                configurarLabelImagenAvatarJugadorIzquierdaCentro();
                
                break; 
                
            case PosicionPanel.CENTRO_ARRIBA:
                configurarLabelImagenAvatarJugadorCentroArriba();
                
                break;
                
            default:
                throw new AssertionError();
        }
        
        revalidate();
        repaint();
    }

    @Override
    public PosicionPanel getPosicion() {
        return posicionPanel;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void setAvatarJugador(String avatarJugador) {
        this.avatarJugador = avatarJugador;
    }
    
}
