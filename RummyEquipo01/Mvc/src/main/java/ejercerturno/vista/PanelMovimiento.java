package ejercerturno.vista;


import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



public class PanelMovimiento extends JPanel implements IComponente {

    private List<PanelFicha> fichasArrastradas;
    
    private List<PanelFicha> fichasArrastreActual = new LinkedList<>();
    
    private PanelFicha[] fichasInicialesArrastre;
    
    private boolean arrastrandoFichas = false;

    private IGestorEventos gestorEventos;
    
    private boolean faseSoltado = false;
    
    private MouseAdapter ma;

    public PanelMovimiento() {
        setOpaque(false);
        setLayout(null);
        
        ma = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                
                if (fichasArrastradas == null) {
                    return;
                }

                int indiceInicio = 0;
                int indiceMaximo = fichasArrastradas.size();
                boolean moverEnGrupo = true;

                Point puntoInicioEvento = e.getPoint();

                if (e.getComponent() instanceof PanelFicha) {
                    PanelFicha panelFicha = (PanelFicha) e.getComponent();
                    puntoInicioEvento = SwingUtilities.convertPoint(panelFicha, puntoInicioEvento, PanelMovimiento.this);

                    fichasArrastreActual = new LinkedList<>();
                    fichasArrastreActual.add((PanelFicha)e.getComponent());

                    moverEnGrupo = false;
                    for (int i = 0; i < fichasArrastradas.size(); i++) {
                        if (fichasArrastradas.get(i).getIdFicha().equals(panelFicha.getIdFicha())) {
                            indiceInicio = i;
                            indiceMaximo = i + 1;
                            break;
                        }
                    }

                } else{
                    fichasArrastreActual = new LinkedList<>(fichasArrastradas);
                }

                for (int i = indiceInicio; i < indiceMaximo; i++) {
                    int nuevoX = puntoInicioEvento.x - 30;
                    int nuevoY = puntoInicioEvento.y - 30;

                    if (moverEnGrupo) {
                        fichasArrastradas.get(i).setLocation(nuevoX + (fichasArrastradas.get(i).getWidth() + 15) * i, nuevoY);
                    } else {
                        fichasArrastradas.get(i).setLocation(nuevoX, nuevoY);
                    }
                }

                revalidate();
                repaint();
            }

            
            @Override
            public void mouseReleased(MouseEvent e) {
                faseSoltado = true;
                gestorEventos.fichaSoltada(e);
                   
            }

            
        };
        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    public void iniciarArrastre(PanelFicha[] fichas) {
        
        this.faseSoltado = false;
        
        this.fichasInicialesArrastre = fichas;
        this.fichasArrastradas =  new LinkedList<>();
        
        this.arrastrandoFichas = true;
        
        for(int i = 0; i < fichas.length; i++){

            Point puntoEnGlassPane = SwingUtilities.convertPoint(fichas[i], fichas[i].getLocation(), this);

            PanelFicha fichaCopia = new PanelFicha(
                            gestorEventos,
                            fichas[i].getIdFicha(),
                            fichas[i].getValor(), 
                            fichas[i].getColorValor(), 
                            fichas[i].isSeleccionada());
            
            fichaCopia.setLocation(puntoEnGlassPane);
            
            fichaCopia.setSize(fichas[i].getSize());
            
            fichasArrastradas.add(i, fichaCopia);
            
            add(fichaCopia);
            
            fichaCopia.setVisible(true);
            
            fichaCopia.setEnMovimiento(true);
            
        }
        
        fichasArrastreActual = new LinkedList<>(fichasArrastradas);
        
        setVisible(true);

        repaint();
    }
    
    public void setGestorEventos(IGestorEventos gestorEventos){
        this.gestorEventos = gestorEventos;
    }
    
    public void arrastrarFichas(MouseEvent e){
        ma.mouseDragged(e);
    }
    
    public void dejarArrastrarFichas(MouseEvent e){
        ma.mouseReleased(e);
    }
    
    public void pintar(IEstadoPanelMovimiento estadoPanelMovimiento){
        
        boolean movimientoValido = estadoPanelMovimiento.getMovimientoValido();
        
        if(!arrastrandoFichas){
            
            this.setVisible(false);
            
        } else{
            
            this.setVisible(true);

            if (faseSoltado) {

                if (movimientoValido) {
                    this.borrarContenido();
                }

            } else {

                if (!movimientoValido) {
                    this.borrarContenido();
                }
            }
              
        }
        
        
    }
    
    public void borrarContenido(){
        
        for(PanelFicha ficha: fichasArrastreActual){ 
            fichasArrastradas.remove(ficha);
            remove(ficha); 
        }
        
        fichasArrastreActual = new LinkedList<>();
        
        if(fichasArrastradas.isEmpty()){
            arrastrandoFichas = false;
            this.setVisible(false);
        } else{
            arrastrandoFichas = true;
        }
        
        revalidate();
        repaint();
                    
    }

    public Integer obtenerIndiceFichaArrastrada() {
        
        if(fichasArrastreActual.size() == 1){
            
            for(int i = 0; i < fichasInicialesArrastre.length; i++){
                
                if(fichasInicialesArrastre[i].getIdFicha().equals(fichasArrastreActual.get(0).getIdFicha())){
                    
                    return i;
                }
            
            }
              
        }
        
        return null;
        
        
    }
    
    @Override
    public void agregarComponente(IComponente componente) {
        
    }

    @Override
    public void removerComponente(IComponente componente) {
        
    }

    @Override
    public void aceptar(IVisitor visitor) {
        visitor.visitar(this);
    }

    @Override
    public PosicionPanel getPosicion() {
        return null;
    }

}