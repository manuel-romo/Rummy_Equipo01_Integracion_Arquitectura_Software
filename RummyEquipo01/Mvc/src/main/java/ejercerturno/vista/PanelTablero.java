
package ejercerturno.vista;

import com.sun.java.accessibility.util.AWTEventMonitor;
import ejercerturno.dto.FichaPresentacionDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class PanelTablero extends JPanel implements IComponente{

    private Color COLOR_FONDO = new Color(60, 130, 72);
    private PanelCasilla[] panelesCasillas;
    private int CANTIDAD_FILAS_CASILLAS = 20;
    private int CANTIDAD_COLUMNAS_CASILLAS = 50;
    private int ESPACION_VERTICAL_CASILLAS = 0;
    private int ESPACION_HORIZONTAL_CASILLAS = 0;
    
    private PosicionPanel POSICION_PANEL = PosicionPanel.CENTRO;
    private Dimension tamanio = new Dimension(1650,1600);
    
    private Map<Integer, Integer> mapaCasillasFichas;
    
    private IGestorEventos gestorEventos;
    
    public PanelTablero(PanelCasilla[] panelesCasillas){
        
        this.panelesCasillas = panelesCasillas;
        
        setBackground(COLOR_FONDO);
        setPreferredSize(tamanio);
        
        setLayout(new GridLayout(
                CANTIDAD_FILAS_CASILLAS, 
                CANTIDAD_COLUMNAS_CASILLAS, 
                ESPACION_HORIZONTAL_CASILLAS, 
                ESPACION_VERTICAL_CASILLAS));
        
        configurarPanelesCasillas();
    }
    
    public void setGestorEventos(IGestorEventos gestorEventos){
        this.gestorEventos = gestorEventos;
    }
    
    private void configurarPanelesCasillas(){
        
        for(PanelCasilla panelCasilla: panelesCasillas){
            
            add(panelCasilla);
            
        }
        
    }
    
    private void agregarFichasTablero(FichaInformacionPanel[] fichas){
        
        for(Map.Entry<Integer, Integer> entrada: mapaCasillasFichas.entrySet()){

            PanelCasilla panelCasilla = obtenerCasillaPorId(entrada.getKey());

            FichaInformacionPanel ficha = obtenerInformacionFichaPorId(entrada.getValue(), fichas);
            
            if(ficha != null){
                
                PanelFicha panelFicha = new PanelFicha(
                    gestorEventos, 
                    ficha.getId(), 
                    ficha.getValor(), 
                    ficha.getColor(),
                    false);

                panelCasilla.agregarFicha(panelFicha);
                               
            } else{
                
                panelCasilla.removeAll();
                
            }
            
        }
            
    }
    
    private PanelCasilla obtenerCasillaPorId(Integer idCasilla){
        
        for(PanelCasilla panelCasilla: panelesCasillas){
            
            if(panelCasilla.getId().equals(idCasilla)){
                return panelCasilla;
            }
        }
        return null;
    }
    
    
    private FichaInformacionPanel obtenerInformacionFichaPorId(Integer idFicha, FichaInformacionPanel[] fichas){
        
        for(FichaInformacionPanel ficha: fichas){
            
            if(ficha.getId().equals(idFicha)){
                return ficha;
            }
                
        }
        
        return null;
        
    }
    
    
    @Override
    public void agregarComponente(IComponente componente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removerComponente(IComponente componente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }

    public void pintar(IEstadoTablero estadoTablero) {
        
        if(estadoTablero.getTablero() != null){
            mapaCasillasFichas = estadoTablero.getTablero().getMapaCasillasFichas();
        
            FichaInformacionPanel[] fichas = estadoTablero.getTablero().getFichasTablero();

            agregarFichasTablero(fichas);
        }

    }

    @Override
    public PosicionPanel getPosicion() {
        return POSICION_PANEL;
    }

    
    
}
