
package ejercerturno.vista;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import ejercerturno.vista.IComponente;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class PanelMesaJuego extends JPanel implements IComponente{

    private PosicionPanel POSICION_PANEL = PosicionPanel.SIN_POSICION;
    
    private List<IComponente> componentes = new LinkedList<>();
    
    private Color COLOR_FONDO = new Color(207, 196, 136);
    
    private final String CODIGO_MENSAJE_INCIO_TURNO = "IT: ";
    private final String CODIGO_MENSAJE_CAMBIO_TURNO = "CT: ";
    private final String CODIGO_MENSAJE_RESPUESTA_MOVIMIENTO = "RM: ";
    private final String CODIGO_MENSAJE_TABLERO_INVALIDO = "TI: ";
    private final String CODIGO_MENSAJE_SOLICITUD_ABANDONO = "RA: ";
    private final String CODIGO_MENSAJE_ABANDONO_JUGADOR = "JA: ";
    private final String CODIGO_MENSAJE_RESPONDER_SOLICITUD_FIN = "RF: ";
    private final String CODIGO_MENSAJE_RESPONDER_CONFIRMACION_SOLICITUD_FIN = "RC: ";
    private final String CODIGO_MENSAJE_PARTIDA_GANADA = "PG: ";
    private final String CODIGO_MENSAJE_JUGADOR_PARTIDA_GANADA = "JG: ";
    
    private final String TITULO_MENSAJE_INICIO_TURNO = "Inicio de turno";
    private final String TITULO_MENSAJE_CAMBIO_TURNO = "Cambio de turno";
    private final String TITULO_MENSAJE_ACCION_INVALIDA = "Acción inválida";
    private final String TITULO_MENSALE_TABLERO_INVALIDO = "Tablero inválido";
    private final String TITULO_MENSAJE_ABANDONO = "Abandono de partida";
    private final String TITULO_MENSAJE_FINALIZAR = "Finalización de partida";
    private final String TITULO_MENSAJE_PARTIDA_GANADA = "Partida ganada";
    private final String TITULO_MENSAJE_JUGADOR_PARTIDA_GANADA = "Partida perdida";
    
    private IGestorEventos gestorEventos;
    
    public PanelMesaJuego(){
        
        setBackground(COLOR_FONDO);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        
    }
    
    private void aniadirComponente(IComponente componente){
 
        JPanel panel = (JPanel)componente;
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
         
        if(componente.getPosicion().equals(PosicionPanel.CENTRO_ARRIBA)){
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.weightx = 1;
            gridBagConstraints.weighty = 4;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = GridBagConstraints.BOTH;

            add(panel, gridBagConstraints);

        } 
        else if(componente.getPosicion().equals(PosicionPanel.IZQUIERDA_CENTRO)){
        
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.weightx = 1;
            gridBagConstraints.weighty = 6;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = GridBagConstraints.BOTH;

            add(panel, gridBagConstraints);
        }  

        else if(componente.getPosicion().equals(PosicionPanel.DERECHA_CENTRO)){
        
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.weightx = 1;
            gridBagConstraints.weighty = 6;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = GridBagConstraints.BOTH;

            add(panel, gridBagConstraints);
        }
        else if(componente.getPosicion().equals(PosicionPanel.CENTRO)){
            
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.weightx = 100;
            gridBagConstraints.weighty = 20;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = GridBagConstraints.BOTH;

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            
            add(scrollPane, gridBagConstraints);
        }
        else if(componente.getPosicion().equals(PosicionPanel.ABAJO_CENTRO)){
            
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.weightx = 3;
            gridBagConstraints.weighty = 3;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = GridBagConstraints.BOTH;

            add(panel, gridBagConstraints);
            
        } else if(componente.getPosicion().equals(PosicionPanel.IZQUIERDA_ABAJO)){
            
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.weightx = 1;
            gridBagConstraints.weighty = 1;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = GridBagConstraints.BOTH;

            add(panel, gridBagConstraints);
        }
    }
    
    private void mostrarMensajeMovimientoInvalido(String mensaje){
        
        if(mensaje != null){
            
            if(mensaje.startsWith(CODIGO_MENSAJE_INCIO_TURNO)){
                
                JOptionPane.showMessageDialog(
                        this, 
                        mensaje.substring(3), 
                        TITULO_MENSAJE_INICIO_TURNO, 
                        JOptionPane.INFORMATION_MESSAGE);
                
            } 
            
            else if(mensaje.startsWith(CODIGO_MENSAJE_CAMBIO_TURNO)){
                JOptionPane.showMessageDialog(
                        this, 
                        mensaje.substring(3), 
                        TITULO_MENSAJE_CAMBIO_TURNO, 
                        JOptionPane.INFORMATION_MESSAGE);
            }
            
            else if(mensaje.startsWith(CODIGO_MENSAJE_RESPUESTA_MOVIMIENTO)){
                
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_MENSAJE_ACCION_INVALIDA, 
                JOptionPane.ERROR_MESSAGE);
                
            } else if(mensaje.startsWith(CODIGO_MENSAJE_TABLERO_INVALIDO)){
                
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_MENSALE_TABLERO_INVALIDO, 
                JOptionPane.ERROR_MESSAGE);
                
            } else if(mensaje.startsWith(CODIGO_MENSAJE_SOLICITUD_ABANDONO)){
                
                int respuesta = JOptionPane.showConfirmDialog(
                    this, 
                    mensaje.substring(3), 
                    TITULO_MENSAJE_ABANDONO, 
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                gestorEventos.confirmarAbandonarPartida((respuesta == JOptionPane.OK_OPTION));
            
            } else if(mensaje.startsWith(CODIGO_MENSAJE_RESPONDER_SOLICITUD_FIN)){
                
                int respuesta = JOptionPane.showConfirmDialog(
                    this, 
                    mensaje.substring(3), 
                    TITULO_MENSAJE_FINALIZAR, 
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                gestorEventos.confirmarFinalizarPartida((respuesta == JOptionPane.OK_OPTION));
                
            } else if(mensaje.startsWith(CODIGO_MENSAJE_ABANDONO_JUGADOR)){
                
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_MENSAJE_ABANDONO, 
                JOptionPane.INFORMATION_MESSAGE);
                
            } else if(mensaje.startsWith(CODIGO_MENSAJE_RESPONDER_CONFIRMACION_SOLICITUD_FIN)){
                
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_MENSAJE_FINALIZAR, 
                JOptionPane.INFORMATION_MESSAGE);
                
            } else if(mensaje.startsWith(CODIGO_MENSAJE_PARTIDA_GANADA)){
                
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_MENSAJE_PARTIDA_GANADA, 
                JOptionPane.INFORMATION_MESSAGE);
                
            } else if(mensaje.startsWith(CODIGO_MENSAJE_JUGADOR_PARTIDA_GANADA)){
                
                JOptionPane.showMessageDialog(
                this, 
                mensaje.substring(3), 
                TITULO_MENSAJE_JUGADOR_PARTIDA_GANADA, 
                JOptionPane.INFORMATION_MESSAGE);
                
            }

        }
        
        
    }

    public void setGestorEventos(IGestorEventos gestorEventos) {
        this.gestorEventos = gestorEventos;
    }

    @Override
    public void agregarComponente(IComponente componente) {
        componentes.add(componente);
        aniadirComponente(componente);
        
    }

    @Override
    public void removerComponente(IComponente componente) {
        componentes.remove(componente);
    }

    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
        
        for(IComponente componente: componentes){
            componente.aceptar(visitor);
        }
        
    }

    public void pintar(IEstadoMesaJuego estadoMesaJuego) {
        
        String mensaje = estadoMesaJuego.getMensaje();
        
        mostrarMensajeMovimientoInvalido(mensaje);
        repaint();
    }

    @Override
    public PosicionPanel getPosicion() {
        return POSICION_PANEL;
    }
    
}
