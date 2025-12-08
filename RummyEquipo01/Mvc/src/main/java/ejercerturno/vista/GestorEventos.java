
package ejercerturno.vista;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import definiciones.IReceptorEventosEjercerTurno;


public class GestorEventos implements IGestorEventos{

    private IReceptorEventosEjercerTurno receptorEventos;
    
    private JFrame framePrincipal;
    
    private PanelMovimiento panelMovimiento;
            
    private List<PanelFicha> fichasMovimiento = new LinkedList<>();
    
    private List<PanelCasilla> paneleslCasillaEliminarFicha = new LinkedList<>();
    private List<PanelCasilla> panelesCasillaAgregarFicha = new LinkedList<>();
    
    public GestorEventos (JFrame framePrincipal, IReceptorEventosEjercerTurno receptorEventos){
       
        this.receptorEventos = receptorEventos;
        this.framePrincipal = framePrincipal;
        
        this.panelMovimiento = (PanelMovimiento)framePrincipal.getGlassPane();
         
    }
    
    @Override
    public void seleccionarFicha(MouseEvent e, boolean seleccionada) {
        
        PanelFicha ficha = (PanelFicha)e.getSource();
        
        if(!ficha.isSeleccionada() && seleccionada){
            
            if (!fichasMovimiento.isEmpty() && fichasMovimiento.get(0).getParent() == null) {
                fichasMovimiento.clear();
           }
            
            boolean seleccionValida = true;
            
            if(!fichasMovimiento.isEmpty()){
                    
                PanelFicha fichaMovimientoReferencia = fichasMovimiento.get(0);
                Container contenedorAbueloFichaReferencia = fichaMovimientoReferencia.getParent().getParent();

                Container contenedorAbueloFichaSeleccionada = ficha.getParent().getParent();

                if(contenedorAbueloFichaSeleccionada instanceof PanelTablero){

                    List<PanelFicha> fichasAdyacentesIzquierda = new LinkedList<>();
                    List<PanelFicha> fichasAdyacentesDerecha = new LinkedList<>();

                    if(contenedorAbueloFichaReferencia instanceof PanelJugadorPrincipal){
                        
                        return;

                    }

                    PanelCasilla panelCasilla = (PanelCasilla)fichaMovimientoReferencia.getParent();

                    Point posicionPanelCasilla = panelCasilla.getLocation();

                    int posicionFichaDerecha = 1;

                    while(true){

                        Point puntoLugarVerificar = new Point();

                        puntoLugarVerificar.setLocation(posicionPanelCasilla.x + (double)(panelCasilla.getWidth()/2) + ((panelCasilla.getWidth()) * posicionFichaDerecha), 
                                posicionPanelCasilla.y + (double)(panelCasilla.getWidth() / 2));

                        Component componenteVerificar = contenedorAbueloFichaSeleccionada.getComponentAt(puntoLugarVerificar);

                        posicionFichaDerecha++;

                        if(componenteVerificar instanceof PanelCasilla){

                            PanelCasilla panelCasillaVerificar = (PanelCasilla)componenteVerificar;

                            if(panelCasillaVerificar.getComponentCount() > 0){

                                PanelFicha panelFicha = panelCasillaVerificar.getPanelFicha();

                                fichasAdyacentesDerecha.add(panelFicha);

                                if(!panelFicha.isSeleccionada()){                  
                                    break;   
                                }

                            } else{
                                break;  
                            }

                        } else{
                            break;
                        }

                    }

                    int posicionFichaIzquierda = 1;

                    while(true){

                        Point puntoLugarVerificar = new Point();

                        puntoLugarVerificar.setLocation(posicionPanelCasilla.x + (double)(panelCasilla.getWidth()/2) - (panelCasilla.getWidth() * posicionFichaIzquierda), 
                                posicionPanelCasilla.y + (double)(panelCasilla.getWidth() / 2));

                        Component componenteVerificar = contenedorAbueloFichaSeleccionada.getComponentAt(puntoLugarVerificar);

                        posicionFichaIzquierda++;

                        if(componenteVerificar instanceof PanelCasilla){

                            PanelCasilla panelCasillaVerificar = (PanelCasilla)componenteVerificar;

                            if(panelCasillaVerificar.getComponentCount() > 0){

                                PanelFicha panelFicha = panelCasillaVerificar.getPanelFicha();

                                fichasAdyacentesIzquierda.add(panelFicha);

                                if(!panelFicha.isSeleccionada()){                  
                                    break;   
                                }

                            } else{
                                break;  
                            }

                        } else{
                            break;
                        }

                    }

                    boolean fichaEncontrada = false;
                    for(PanelFicha fichaAdyacente: fichasAdyacentesDerecha){

                        if(fichaAdyacente.getIdFicha() == ficha.getIdFicha()){
                            fichaEncontrada = true;
                        }
                    }

                    for(PanelFicha fichaAdyacente: fichasAdyacentesIzquierda){

                        if(fichaAdyacente.getIdFicha() == ficha.getIdFicha()){
                            fichaEncontrada = true;
                        }
                    }
                    
                    seleccionValida = fichaEncontrada;


                } else{

                    if (contenedorAbueloFichaReferencia instanceof PanelTablero) {

                        // Se deseleccionan visualmente las fichas viejas del tablero.
                        for (PanelFicha panelFicha: fichasMovimiento) {
                            panelFicha.setSeleccionada(false);
                        }

                        // Se limpia la lista anterior.
                        fichasMovimiento.clear();

                        // Se valida la nueva selección.
                        seleccionValida = true;

                    }


                }
            }
            
            if(seleccionValida){

                ficha.setSeleccionada(true);

                fichasMovimiento.add(ficha);

            }
            
        } else if(ficha.isSeleccionada() && !seleccionada){
            
            Container contenedorAbueloFichaSeleccionada = ficha.getParent().getParent();
            
            boolean tieneFichaAdyacenteDerechaSeleccionada = false;
            boolean tieneFichaAdyacenteIzquierdaSeleccionada = false;
            
            if(contenedorAbueloFichaSeleccionada instanceof PanelTablero && fichasMovimiento.size() > 1){
                
                PanelCasilla panelCasilla = (PanelCasilla)ficha.getParent();

                // Verificación derecha
                Point posicionPanelCasilla = panelCasilla.getLocation();

                Point puntoLugarVerificarDerecha = new Point();

                puntoLugarVerificarDerecha.setLocation(posicionPanelCasilla.x + (double)(panelCasilla.getWidth()/2) + panelCasilla.getWidth(), 
                        posicionPanelCasilla.y + (double)(panelCasilla.getWidth() / 2));

                Component componenteVerificarDerecha = contenedorAbueloFichaSeleccionada.getComponentAt(puntoLugarVerificarDerecha);

                if(componenteVerificarDerecha instanceof PanelCasilla){

                    PanelCasilla panelCasillaVerificar = (PanelCasilla)componenteVerificarDerecha;

                    if(panelCasillaVerificar.getComponentCount() > 0){

                        PanelFicha panelFicha = panelCasillaVerificar.getPanelFicha();

                        if(panelFicha.isSeleccionada()){                  
                            tieneFichaAdyacenteDerechaSeleccionada = true;
                        }
                    }
                }
                
                // Verificación izquierda
                Point puntoLugarVerificarIzquierda = new Point();

                puntoLugarVerificarIzquierda.setLocation(posicionPanelCasilla.x + (double)(panelCasilla.getWidth()/2) - panelCasilla.getWidth(), 
                        posicionPanelCasilla.y + (double)(panelCasilla.getWidth() / 2));

                Component componenteVerificarIzquierda = contenedorAbueloFichaSeleccionada.getComponentAt(puntoLugarVerificarIzquierda);

                if(componenteVerificarIzquierda instanceof PanelCasilla){

                    PanelCasilla panelCasillaVerificar = (PanelCasilla)componenteVerificarIzquierda;

                    if(panelCasillaVerificar.getComponentCount() > 0){

                        PanelFicha panelFicha = panelCasillaVerificar.getPanelFicha();

                        if(panelFicha.isSeleccionada()){                  
                            tieneFichaAdyacenteIzquierdaSeleccionada = true;
                        }
                    }
                }
                
            }
            
            if(!(tieneFichaAdyacenteDerechaSeleccionada && tieneFichaAdyacenteIzquierdaSeleccionada)){
                
                ficha.setSeleccionada(false);
            
                fichasMovimiento.remove(ficha);
            }
            
            
        }
    }
    
    @Override
    public void iniciarArrastreFichas(){
        
        PanelFicha[] fichasArrastradas = fichasMovimiento.toArray(new PanelFicha[0]);
        
        panelMovimiento.iniciarArrastre(fichasArrastradas);
        
        quitarFichasCasillas();

    }
    
    @Override
    public void presionarFicha(){
        
        for(PanelFicha ficha: fichasMovimiento){
            ficha.presionarFicha();
        }
        
    }
    
    @Override
    public void dejarPresionarFicha(){
        
        for(PanelFicha ficha: fichasMovimiento){
            ficha.dejarPresionarFicha();
        }
        
    }
    
    

    @Override
    public void fichaSoltada(MouseEvent e) {
        
        List<Integer> listaIdsFichasAgregar = new LinkedList<>();
        
        Integer[] idsFichasGrupoAgregar = new Integer[2];
        
        panelesCasillaAgregarFicha = new LinkedList<>();
        
        boolean fichasSoltadasPanelJugador = false;

        int indiceInicioFichasMovimiento = 0;
        int indiceFinFichasMovimiento = fichasMovimiento.size();
        
        // Se obtiene el índice de la única ficha arrastrada, si es sólo una.
        Integer indiceFichaArrastrada = panelMovimiento.obtenerIndiceFichaArrastrada();
        
        System.out.println("indice de ficha arras es nulo?"); 
        System.out.println(indiceFichaArrastrada);
        
        if(indiceFichaArrastrada != null){
            
            indiceInicioFichasMovimiento = indiceFichaArrastrada;
            indiceFinFichasMovimiento = indiceFichaArrastrada + 1;
        }
        
        for(int i = indiceInicioFichasMovimiento; i < indiceFinFichasMovimiento; i++){
            
            PanelFicha ficha = fichasMovimiento.get(i);
            
            int indiceFichaMovimiento = fichasMovimiento.indexOf(ficha);
            
            if(indiceFichaArrastrada != null){
                
                indiceFichaMovimiento = 0;
                
            }
            
            int offsetX = indiceFichaMovimiento * (ficha.getWidth() + 15);
            
            Point dropPoint = e.getPoint();
            
            if(e.getComponent() instanceof PanelFicha){
                dropPoint = SwingUtilities.convertPoint(e.getComponent(), dropPoint, panelMovimiento);
            }
            
            dropPoint.x = dropPoint.x + offsetX;
            
            Point contentPanePoint = SwingUtilities.convertPoint(panelMovimiento, dropPoint, framePrincipal.getContentPane());
            
            Component componenteDestino = framePrincipal.getContentPane().findComponentAt(contentPanePoint);  
            
            if(componenteDestino instanceof PanelCasilla){
                
                System.out.println("Soltando ficha en panel casilla");

                PanelCasilla casillaDestino = (PanelCasilla)componenteDestino;

                panelesCasillaAgregarFicha.add(casillaDestino);

                if(casillaDestino.getComponentCount() > 0){

                    return;

                } else{

                    Container contenedorCasillas = casillaDestino.getParent();

                    if(contenedorCasillas.getParent().getParent().getParent().getParent() instanceof PanelJugadorPrincipal){
                        
                        fichasSoltadasPanelJugador = true;
                        
                    } else{
                    
                        // Búsqueda de Ficha derecha
                        int puntoX_Derecha = casillaDestino.getX() + casillaDestino.getWidth() + 25;
                        int puntoY_Centro = casillaDestino.getY() + casillaDestino.getHeight() / 2;

                        Point puntoPruebaDerecha = SwingUtilities.convertPoint(
                            contenedorCasillas, 
                            new Point(puntoX_Derecha, puntoY_Centro), 
                            framePrincipal.getContentPane()
                        );

                        Component componenteDerecho = framePrincipal.getContentPane().findComponentAt(puntoPruebaDerecha);

                        if (componenteDerecho instanceof PanelCasilla || componenteDerecho instanceof PanelFicha) {

                            if(componenteDerecho instanceof PanelFicha){
                                componenteDerecho = componenteDerecho.getParent();
                            }

                            PanelCasilla casillaDerecha = (PanelCasilla) componenteDerecho;

                            if (casillaDerecha.getComponentCount() > 0) {

                                PanelFicha fichaEncontrada = (PanelFicha) casillaDerecha.getComponent(0);

                                idsFichasGrupoAgregar[1] = fichaEncontrada.getIdFicha();
                            }
                        }

                        // Búsqueda Ficha izquierda
                        int puntoX_Izquierda = casillaDestino.getX() - 25;

                        Point puntoPruebaIzquierda = SwingUtilities.convertPoint(
                            contenedorCasillas, 
                            new Point(puntoX_Izquierda, puntoY_Centro), 
                            framePrincipal.getContentPane()
                        );

                        Component componenteIzquierdo = framePrincipal.getContentPane().findComponentAt(puntoPruebaIzquierda);

                        if (componenteIzquierdo instanceof PanelCasilla || componenteIzquierdo instanceof PanelFicha) {

                            if(componenteIzquierdo instanceof PanelFicha){
                                componenteIzquierdo = componenteIzquierdo.getParent();
                            }

                            PanelCasilla casillaIzquierda = (PanelCasilla) componenteIzquierdo;
                            if (casillaIzquierda.getComponentCount() > 0) {
                                PanelFicha fichaEncontrada = (PanelFicha) casillaIzquierda.getComponent(0);

                                idsFichasGrupoAgregar[0] = fichaEncontrada.getIdFicha();
                            }
                        }
                        
                    }
                    
                    listaIdsFichasAgregar.add(ficha.getIdFicha());
                }
                
            } else{
                
                return;

            }

        }
        
        Integer[] idsFichasAgregar = listaIdsFichasAgregar.toArray(new Integer[0]);

        Integer[] casillasAgregar = panelesCasillaAgregarFicha.stream()
                .map(panel -> panel.getId())
                .toArray(Integer[]::new);
        
        
        if(fichasSoltadasPanelJugador){
            
            receptorEventos.agregarFichasJugador(casillasAgregar, idsFichasAgregar);
            
        } else{
            
            if(idsFichasGrupoAgregar[0] != null || idsFichasGrupoAgregar[1] != null){

                receptorEventos.agregarFichasTablero(casillasAgregar, idsFichasAgregar, idsFichasGrupoAgregar);

            } else{
                System.out.println("QUE FICHAS SE ESTÁ INTENTANDO AGREGAR?");
                
                for(Integer id: idsFichasAgregar){
                    
                    System.out.println(id);
                    
                }

                receptorEventos.agregarFichasTablero(casillasAgregar, idsFichasAgregar);
            }
            
        }
        
    }

    
    @Override
    public void quitarFichasCasillas() {
        
        paneleslCasillaEliminarFicha = new LinkedList<>();
        List<Integer> idsFichasQuitar = new LinkedList<>();
        
        boolean fichasJugador = true;
        
        for(PanelFicha ficha: fichasMovimiento){
            
            if(ficha.getParent() != null){

                Integer idFichaEliminar = ficha.getIdFicha();

                PanelCasilla panelCasillaQuitar = (PanelCasilla)ficha.getParent();

                paneleslCasillaEliminarFicha.add(panelCasillaQuitar);
                
                idsFichasQuitar.add(idFichaEliminar);

                if(!(ficha.getParent().getParent().getParent().getParent().getParent().getParent() instanceof PanelJugadorPrincipal)){
                    fichasJugador = false;
                }

            }
        }
        
        Integer[] fichasQuitar = idsFichasQuitar.toArray(new Integer[0]);

        Integer[] casillasQuitar = paneleslCasillaEliminarFicha.stream()
            .map(panel -> panel.getId())
            .toArray(Integer[]::new);

        if(fichasJugador){
            
            receptorEventos.quitarFichasJugador(casillasQuitar, fichasQuitar);

        } else{
            
            receptorEventos.quitarFichasTablero(casillasQuitar, fichasQuitar);
            
        }
        
    }
    
    @Override
    public void borrarFichasMovimiento(){
        
        fichasMovimiento = new LinkedList<>();
        panelMovimiento.borrarContenido();
        
    }
    
    @Override
    public void arrastreFichaMovimiento(MouseEvent e){
        
        panelMovimiento.arrastrarFichas(e);
        
    }
    
    @Override
    public void soltarFichasMovimiento(MouseEvent e){
        
        panelMovimiento.dejarArrastrarFichas(e);
        
    }

    @Override
    public void terminarTurno() {
        
        receptorEventos.terminarTurno();
        
    }

    @Override
    public void tomarFicha() {
        receptorEventos.tomarFicha();
    }

    @Override
    public void reestablecerTablero() {
        receptorEventos.reestablecerTablero();
    }

    @Override
    public void abandonarPartida() {
        
        receptorEventos.abandonarPartida();
        
    }

    @Override
    public void confirmarAbandonarPartida(boolean confirmacion) {
       
        receptorEventos.confirmarAbandonarPartida(confirmacion);
        
    }
    
    @Override
    public void finalizarPartida() {
        
        receptorEventos.finalizarPartida();
        
    }

    @Override
    public void confirmarFinalizarPartida(boolean confirmacion) {
        
        receptorEventos.confirmarFinalizarPartida(confirmacion);
        
    }
    
}
