package ejercerturno.modelo;


import ejercerturno.dto.ColorFichaPresentacionDTO;
import ejercerturno.dto.FichaPresentacionDTO;
import ejercerturno.dto.JugadorExternoPresentacionDTO;
import ejercerturno.dto.JugadorPrincipalPresentacionDTO;
import ejercerturno.dto.MontonPresentacionDTO;
import ejercerturno.dto.TableroPresentacionDTO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import ejercerturno.dto.ComodinPresentacionDTO;
import dtos.FichaComodinDTO;
import dtos.FichaDTO;
import dtos.FichaNormalDTO;
import ejercerturno.dto.FichaNormalPresentacionDTO;
import dtos.GrupoDTO;
import dtos.JugadorDTO;
import dtos.MontonDTO;
import dtos.TableroDTO;
import definiciones.IPublicador;
import definiciones.ISuscriptor;
import enumeradores.ColorFichaDTO;

import definiciones.IModeloEjercerTurno;
import fachada.FachadaMvc;

/**
 * Clase Modelo que representa la parte lógica del patrón MVC para el caso de
 * uso "Ejercer Turno".
 *
 * Se encarga de coordinar la comunicación entre la vista y la capa de negocio
 * (a través de la fachada), manejar el estado actual del tablero y notificar a
 * las vistas suscritas cuando hay cambios.
 *
 * En esta versión, la fachada funciona como un mock que simula la lógica del
 * tablero real, permitiendo probar el flujo completo del caso de uso sin
 * depender todavía de las entidades finales.
 */
public class ModeloEjercerTurno implements IPublicador, IModeloEjercerTurno{
    
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
    
    /**
     * Lista de suscriptores del modelo para notificar cambios a la vista.
     */
    private List<ISuscriptor> suscriptores = new ArrayList<>();
    
    /**
     * Indica si el tablero actual es inválido.
     */
    
    private boolean tableroInvalido;
    /**
     * Indica si la vista está habilitada para interactuar.
     */
    
    private boolean vistaVisible;
    
    private boolean vistaHabilitada;
    /**
     * Indica si el último movimiento realizado fue inválido.
     */
    
    private boolean movimientoInvalido;
    
    private boolean nuevoTurno;
    
    private String mensaje;
    
    private TableroDTO tablero;
    
    private FachadaMvc fachadaMvc;
    
    /**
     * Nombre del jugador.
     */
    private String nombreJugador;
    
    /**
     * Obtiene la lista de vistas suscritas al modelo.
     *
     * @return lista de objetos que implementan {@link ISuscriptor}.
     */
    public List<ISuscriptor> getSuscriptores() {
        return suscriptores;
    }

    /**
     * Asigna la lista de vistas suscritas al modelo.
     *
     * @param suscriptores lista de suscriptores.
     */
    public void setSuscriptores(List<ISuscriptor> suscriptores) {
        this.suscriptores = suscriptores;
    }

    /**
     * Indica si el tableroQuitar es inválido.
     *
     * @return {@code true} si el tableroQuitar no cumple las reglas, {@code false} en
     * caso contrario.
     */
    @Override
    public boolean isTableroInvalido() {
        return tableroInvalido;
    }

    /**
     * Define si el tableroQuitar es inválido.
     *
     * @param tableroInvalido valor booleano indicando si el tableroQuitar es
        inválido.
     */
    public void setTableroInvalido(boolean tableroInvalido) {
        this.tableroInvalido = tableroInvalido;
    }
    
    public void setVistaVisible(boolean vistaVisible) {
        this.vistaVisible = vistaVisible;
    }
    
    /**
     * Define si la vista está habilitada para la interacción del jugador.
     *
     * @param vistaHabilitado valor booleano que indica si la vista está activa.
     */
    public void setVistaHabilitado(boolean vistaHabilitado) {
        this.vistaHabilitada = vistaHabilitado;
    }

    /**
     * Indica si el último movimiento fue inválido.
     *
     * @return {@code true} si el movimiento no fue válido, {@code false} en
     * caso contrario.
     */
    @Override
    public boolean isMovimientoInvalido() {
        return movimientoInvalido;
    }

    /**
     * Define si el último movimiento fue inválido.
     *
     * @param movimientoInvalido valor booleano que indica si el movimiento fue
     * inválido.
     */
    public void setMovimientoInvalido(boolean movimientoInvalido) {
        this.movimientoInvalido = movimientoInvalido;
    }

    /**
     * Simula la selección de fichas del tableroQuitar.
     *
     * @param posicionesFichas arreglo con las posiciones seleccionadas.
     */
    public void seleccionarFichasTablero(Integer[] posicionesFichas) {
        
        fachadaMvc.seleccionarFichasTablero(posicionesFichas, nombreJugador);
        
    }

    /**
     * Simula la acción de quitar fichas del jugador actual.
     *
     * @param posicionesFichas arreglo de posiciones de fichas a eliminar.
     */
    public void quitarFichasJugador(Integer[] posicionesFichas) {
        
        fachadaMvc.quitarFichasJugador(posicionesFichas, nombreJugador);
        
    }

    /**
     * Quita fichas del tableroQuitar según los identificadores proporcionados.
     *
     * @param idFichas colección de IDs de fichas a eliminar.
     */
    public void quitarFichasTablero(Integer[] idsFichas) {
        
        fachadaMvc.quitarFichasTablero(idsFichas, nombreJugador);

    }

    /**
     * Agrega fichas a un grupo ya existente del jugador.
     *
     * @param idsFichas colección de IDs de fichas a agregar.
     * @param numeroGrupo número del grupo al que se agregarán las fichas.
     */
    public void agregarFichasJugador(Integer[] idsFichas) {
        
        fachadaMvc.agregarFichasJugador(idsFichas, nombreJugador);
        
    }
    
    /**
     * Agrega fichas a un grupo ya existente del tablero.
     *
     * @param idsFichas colección de IDs de fichas a agregar.
     * @param numeroGrupo número del grupo al que se agregarán las fichas.
     */
    public void agregarFichasTablero(Integer[] idsFichas, Integer[] idsFichasGrupo) {
        
        fachadaMvc.agregarFichasTablero(idsFichas, idsFichasGrupo, nombreJugador);
        
    }

    /**
     * Agrega fichas al tableroQuitar que no pertenecen a un grupo existente.
     *
     * @param idsFichas colección de IDs de fichas a agregar.
     */
    public void agregarFichasTablero(Integer[] idsFichas) {
        
        fachadaMvc.agregarFichasTablero(idsFichas, nombreJugador);
        
    }
    
    public void tomarFicha(){
        
        fachadaMvc.tomarFicha(nombreJugador);
        
    }
    
    public void reestablecerTablero(){
        
        fachadaMvc.reestablecerTablero(nombreJugador);
    }
    
    /**
     * Finaliza el turno actual y pasa al siguiente jugador. Si el tablero no es
     * válido, se marca el estado correspondiente.
     */
    public void terminarTurno() {
        
        fachadaMvc.terminarTurno(nombreJugador);

    }
    
    public void abandonarPartida(){
        
        fachadaMvc.abandonarPartida(nombreJugador);
        
    }
    
    public void finalizarPartida(){
        
        fachadaMvc.finalizarPartida(nombreJugador);
        
    }
    
    public void confirmarAbandonoPartida(boolean confirmacion){
        
        fachadaMvc.confirmarAbandonoPartida(confirmacion, nombreJugador);
        
    }
    
    public void confirmarSolicitudFin(boolean confirmacion){
        
        fachadaMvc.confirmarSolicitudFin(confirmacion, nombreJugador);
        
    }
    
    public void iniciar(){
        
        vistaVisible = true;
        
        notificar();
    }

    public void iniciarTurno(TableroDTO tablero, String mensaje){
    
        this.nuevoTurno = true;
        this.tablero = tablero;
        vistaHabilitada = true;
        this.mensaje = CODIGO_MENSAJE_INCIO_TURNO + mensaje;
        
        notificar();
        
    }
    
    public void cambiarTurno(TableroDTO tablero, String mensaje){
        
        this.nuevoTurno = true;
        this.tablero = tablero;
        vistaHabilitada = false;
        this.mensaje = CODIGO_MENSAJE_CAMBIO_TURNO + mensaje;
        
        notificar();
        
    }
    
    public void responderMovimiento(TableroDTO tablero, boolean movimientoValido, String mensaje){
        
        this.nuevoTurno = false;
        this.tablero = tablero;
        this.movimientoInvalido = !movimientoValido;
        
        if(mensaje != null){
            this.mensaje = CODIGO_MENSAJE_RESPUESTA_MOVIMIENTO + mensaje;
        } else{
            this.mensaje = null;
        }
        
        notificar();
    }
    
    public void tomarFicha(TableroDTO tablero){
        
        this.nuevoTurno = false;
        this.tablero = tablero;
        this.movimientoInvalido = false;
        
        this.mensaje = null;
        
        notificar();
        
    }
    
    public void reestablecerTablero(TableroDTO tablero){
        
        this.nuevoTurno = true;
        this.tablero = tablero;
        this.movimientoInvalido = false;
        
        this.mensaje = null;
        
        notificar();
        
    }
    
    public void avisarTableroInvalido(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = true;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_TABLERO_INVALIDO + mensaje;
        
        notificar();
        
    }
    
    public void responderSolicitudAbandono(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_SOLICITUD_ABANDONO + mensaje;
        
        notificar();
        
    }
   
    public void notificarAbandonoJugador(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_ABANDONO_JUGADOR + mensaje;
        
        notificar();
        
    }
    
    public void responderSolicitudFin(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_RESPONDER_SOLICITUD_FIN + mensaje;
        
        notificar();
        
    }
    
    public void responderConfirmacionSolicitudFin(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_RESPONDER_CONFIRMACION_SOLICITUD_FIN + mensaje;
        
        notificar();
        
    }
    
    public void notificarPartidaGanada(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_PARTIDA_GANADA + mensaje;
        
        notificar();
        
    }
    
    public void notificarJugadorPartidaGanada(String mensaje){
        
        this.nuevoTurno = false;
        this.tableroInvalido = false;
        this.movimientoInvalido = false;
        
        this.mensaje = CODIGO_MENSAJE_JUGADOR_PARTIDA_GANADA + mensaje;
        
        notificar();
        
    }
    
    public void terminarJuego(){
        
        System.exit(0);
        
    }
    
    
    /**
     * Suscribe una nueva vista (suscriptor) al modelo.
     *
     * @param suscriptor instancia que implementa {@link ISuscriptor}.
     */
    @Override
    public void suscribirse(ISuscriptor suscriptor) {
        suscriptores.add(suscriptor);
    }

    /**
     * Elimina una vista (suscriptor) de la lista de notificación.
     *
     * @param suscriptor instancia que implementa {@link ISuscriptor}.
     */
    @Override
    public void desuscribirse(ISuscriptor suscriptor) {
        suscriptores.remove(suscriptor);
    }

    /**
     * Notifica a todas las vistas suscritas los cambios realizados en el
     * modelo.
     */
    @Override
    public void notificar() {
        for (ISuscriptor suscriptor : suscriptores) {
            suscriptor.actualizar(this);
        }
    }

    @Override
    public JugadorPrincipalPresentacionDTO obtenerJugadorPrincipal() {
        
        if(tablero == null){
            return null;
        }
        
        List<FichaDTO> fichasDTO = tablero.getJugadorTurno().getFichas();
        
        List<FichaPresentacionDTO> fichasPresentacion = new LinkedList<>();
        
        for (FichaDTO fichaDTO : fichasDTO) {
            
            FichaPresentacionDTO fichaPresentacion = obtenerFichaPresentacionDTO(fichaDTO);
            fichasPresentacion.add(fichaPresentacion);
            
        }
        
        FichaPresentacionDTO[] fichasArreglo = fichasPresentacion.toArray(new FichaPresentacionDTO[0]);
        
        return new JugadorPrincipalPresentacionDTO(fichasArreglo);
    }

    @Override
    public MontonPresentacionDTO obtenerMontonPresentacion() {
        
        if(tablero == null){
            return null;
        }
        
        return obtenerMontonPresentacionDTO(tablero.getMonton());
        
    }
    
    private MontonPresentacionDTO obtenerMontonPresentacionDTO(MontonDTO montonDTO){
        
        if(tablero == null){
            return null;
        }
        
        return new MontonPresentacionDTO(montonDTO.getCantidadFichas());
        
    }

    @Override
    public String obtenerMensaje() {
        
        return mensaje;

    }

    @Override
    public boolean isVistaHabilitada() {
        return vistaHabilitada;
    }
    
    @Override
    public boolean isVistaVisible() {
        return vistaVisible;
    }
    
    @Override
    public boolean isNuevoTurno() {
        return nuevoTurno;
    }

    @Override
    public JugadorExternoPresentacionDTO[] obtenerJugadoresExternos() {
        
        if(tablero == null){
            return null;
        }
        
        JugadorDTO[] jugadores = tablero.getJugadores();
        
        List<JugadorExternoPresentacionDTO> jugadoresExternosPresentacionDTO = new LinkedList<>();
        
        for(JugadorDTO jugador: jugadores){

            List<FichaDTO> fichasDTO = jugador.getFichas();
           
            int cantidadFichasRestante = fichasDTO.size();
            
            jugadoresExternosPresentacionDTO.add(new JugadorExternoPresentacionDTO(
                    jugador.getAvatar(), 
                    jugador.getNombre(),
                    cantidadFichasRestante));
            
        }

        JugadorExternoPresentacionDTO[] jugadoresExternosPresentacion 
                = jugadoresExternosPresentacionDTO.toArray(new JugadorExternoPresentacionDTO[0]);
        
        return jugadoresExternosPresentacion;
        
 
    }

    @Override
    public TableroPresentacionDTO obtenerTablero() {
        
        if(tablero == null){
            return null;
        }
        GrupoDTO[] grupos = tablero.getGrupos();
        
        List<FichaPresentacionDTO> fichasPresentacion = new LinkedList<>();
        
        for(GrupoDTO grupo: grupos){
            
            List<FichaDTO> fichasDTO = grupo.getFichas();
            
            fichasPresentacion.add(null);
            for(FichaDTO fichaDTO: fichasDTO){
                
                fichasPresentacion.add(obtenerFichaPresentacionDTO(fichaDTO));
                
            }
            
        }
        
        TableroPresentacionDTO tableroPresentacion 
                = new TableroPresentacionDTO(fichasPresentacion.toArray(new FichaPresentacionDTO[0]));
        
        return tableroPresentacion;
        
    }
    
    private FichaPresentacionDTO obtenerFichaPresentacionDTO(FichaDTO fichaDTO){

        FichaPresentacionDTO fichaPresentacionDTO;
        
        if(fichaDTO instanceof FichaNormalDTO){
            
            FichaNormalDTO fichaNormalDTO = (FichaNormalDTO) fichaDTO;
            
            ColorFichaDTO colorFicha = fichaNormalDTO.getColor();
            ColorFichaPresentacionDTO colorFichaPresentacion;

            switch (colorFicha) {

                case ColorFichaDTO.COLOR_A:
                    colorFichaPresentacion = ColorFichaPresentacionDTO.COLOR_A;
                    break;
                case ColorFichaDTO.COLOR_B:
                    colorFichaPresentacion = ColorFichaPresentacionDTO.COLOR_B;
                    break;   
                case ColorFichaDTO.COLOR_C:
                    colorFichaPresentacion = ColorFichaPresentacionDTO.COLOR_C;
                    break;  
                case ColorFichaDTO.COLOR_D:
                    colorFichaPresentacion = ColorFichaPresentacionDTO.COLOR_D;
                    break;

                default:
                    throw new AssertionError();
            }
        
        fichaPresentacionDTO = new FichaNormalPresentacionDTO(
                fichaNormalDTO.getNumero(), 
                fichaNormalDTO.getId(),
                colorFichaPresentacion);
        
            
        } else{
            
            FichaComodinDTO fichaComodinDTO = (FichaComodinDTO) fichaDTO;
             
            fichaPresentacionDTO = new ComodinPresentacionDTO(
                fichaComodinDTO.getValor(), 
                fichaComodinDTO.getId());
            
        }
        
        return fichaPresentacionDTO;
        
    }

    public void setFachadaMvc(FachadaMvc fachadaMvc) {
        this.fachadaMvc = fachadaMvc;
    }
}
