
package deserializador;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import comandosRespuesta.ComandoCambioTurno;
import comandosRespuesta.ComandoCargarJugadores;
import comandosRespuesta.ComandoFinPartida;
import comandosRespuesta.ComandoIniciarTurno;
import comandosRespuesta.ComandoJugadorAbandonoPartida;
import comandosRespuesta.ComandoJugadorPartidaGanada;
import comandosRespuesta.ComandoPartidaGanada;
import comandosRespuesta.ComandoRespuestaAbandonar;
import comandosRespuesta.ComandoDecisionIniciarJuego;
import comandosRespuesta.ComandoNuevaSolicitudIniciarJuego;
import comandosRespuesta.ComandoActualizarJugadoresInicioJuego;
import comandosRespuesta.ComandoRespuestaConfirmacionSolicitarFin;
import comandosRespuesta.ComandoRespuestaIniciarJuego;
import comandosRespuesta.ComandoRespuestaReestablecer;
import comandosRespuesta.ComandoRespuestaMovimiento;
import comandosRespuesta.ComandoRespuestaSolicitarFin;
import comandosRespuesta.ComandoRespuestaTomarFicha;
import comandosRespuesta.ComandoTableroInvalido;
import comandosSolicitud.ComandoAbandonar;
import comandosSolicitud.ComandoAgregarFichasJugador;
import comandosSolicitud.ComandoAgregarFichasTablero;
import comandosSolicitud.ComandoAgregarFichasTableroGrupo;
import comandosSolicitud.ComandoConfirmacionAbandonar;
import comandosSolicitud.ComandoConfirmacionEnvioIniciarJuego;
import comandosSolicitud.ComandoConfirmacionIniciarJuego;
import comandosSolicitud.ComandoConfirmacionSolicitarFin;
import comandosSolicitud.ComandoIniciarJuego;
import comandosSolicitud.ComandoQuitarFichasJugador;
import comandosSolicitud.ComandoQuitarFichasTablero;
import comandosSolicitud.ComandoReestablecerTablero;
import comandosSolicitud.ComandoSeleccionarFichasTablero;
import comandosSolicitud.ComandoSolicitarFin;
import comandosSolicitud.ComandoTerminarTurno;
import comandosSolicitud.ComandoTomarFicha;
import dto.FichaDTO;
import interfaces.ICommand;
import interfaces.IFiltro;
import interfaces.IReceptorExterno;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author ramon
 */
public class Deserializador implements IReceptorExterno{
    
    private IFiltro filtroSiguiente;
    private final Gson gson;
    private final Map<String, Class<? extends ICommand>> registroComandos;

    public Deserializador() {

        this.registroComandos = new HashMap<>();
        registrarComandos();

        this.gson = new GsonBuilder()
                .registerTypeAdapter(FichaDTO.class, new FichaAdaptador())
                .create();
    }
    
    private void registrarComandos() {
        registroComandos.put("ComandoIniciarTurno", ComandoIniciarTurno.class);
        registroComandos.put("ComandoCambioTurno", ComandoCambioTurno.class);
        registroComandos.put("ComandoTomarFicha", ComandoTomarFicha.class);
        registroComandos.put("ComandoRespuestaTomarFicha", ComandoRespuestaTomarFicha.class);
        registroComandos.put("ComandoRespuestaMovimiento", ComandoRespuestaMovimiento.class);
        registroComandos.put("ComandoReestablecerRespuesta", ComandoRespuestaReestablecer.class);
        registroComandos.put("ComandoTableroInvalido", ComandoTableroInvalido.class);
        registroComandos.put("ComandoAgregarFichasTablero", ComandoAgregarFichasTablero.class);
        registroComandos.put("ComandoAgregarFichasTableroGrupo", ComandoAgregarFichasTableroGrupo.class);
        registroComandos.put("ComandoAgregarFichasJugador", ComandoAgregarFichasJugador.class);
        registroComandos.put("ComandoQuitarFichasJugador", ComandoQuitarFichasJugador.class);
        registroComandos.put("ComandoQuitarFichasTablero", ComandoQuitarFichasTablero.class);
        registroComandos.put("ComandoReestablecerTablero", ComandoReestablecerTablero.class);
        registroComandos.put("ComandoSeleccionarFichasTablero", ComandoSeleccionarFichasTablero.class);
        registroComandos.put("ComandoTerminarTurno", ComandoTerminarTurno.class);
        
        registroComandos.put("ComandoSolicitarFin", ComandoSolicitarFin.class);
        registroComandos.put("ComandoRespuestaSolicitarFin", ComandoRespuestaSolicitarFin.class);
        registroComandos.put("ComandoConfirmacionSolicitarFin", ComandoConfirmacionSolicitarFin.class);
        registroComandos.put("ComandoRespuestaConfirmacionSolicitarFin", ComandoRespuestaConfirmacionSolicitarFin.class);
        
        registroComandos.put("ComandoAbandonar", ComandoAbandonar.class);
        registroComandos.put("ComandoRespuestaAbandonar", ComandoRespuestaAbandonar.class);
        registroComandos.put("ComandoConfirmacionAbandonar", ComandoConfirmacionAbandonar.class);
        registroComandos.put("ComandoJugadorAbandonoPartida", ComandoJugadorAbandonoPartida.class);
        
        registroComandos.put("ComandoFinPartida", ComandoFinPartida.class);
        
        registroComandos.put("ComandoPartidaGanada", ComandoPartidaGanada.class);
        registroComandos.put("ComandoJugadorPartidaGanada", ComandoJugadorPartidaGanada.class);
        
        
        // SOLICITAR INICIO PARTIDA
        registroComandos.put("ComandoIniciarJuego", ComandoIniciarJuego.class);
        registroComandos.put("ComandoConfirmacionIniciarJuego", ComandoConfirmacionIniciarJuego.class);
        registroComandos.put("ComandoConfirmacionEnvioIniciarJuego", ComandoConfirmacionEnvioIniciarJuego.class);
        
        registroComandos.put("ComandoNuevaSolicitudIniciarJuego", ComandoNuevaSolicitudIniciarJuego.class);
        registroComandos.put("ComandoRespuestaIniciarJuego", ComandoRespuestaIniciarJuego.class);
        registroComandos.put("ComandoActualizarJugadoresInicioJuego", ComandoActualizarJugadoresInicioJuego.class);
        registroComandos.put("ComandoDecisionIniciarJuego", ComandoDecisionIniciarJuego.class);
        
        registroComandos.put("ComandoCargarJugadores", ComandoCargarJugadores.class);
        
    }
    
    /**
     * Método que recibe el JSON y deserializa el comando para que se pueda utilizar.
     * @param Comando serializado que se deserializa.
     * @return 
     */
    private ICommand deserializarRespuesta(String respuesta) {
        try {

            JsonObject objetoJson = JsonParser.parseString(respuesta).getAsJsonObject();
            
            if (!objetoJson.has("type")){
                return null;
            }
            
            String type = objetoJson.get("type").getAsString();
            
            Class<? extends ICommand> claseComando = registroComandos.get(type);

            if (claseComando != null) {
                
                return gson.fromJson(objetoJson, claseComando);
            }

        } catch (JsonSyntaxException e) {
            System.out.println(e.getMessage());
            System.err.println("Error al deserializar JSON: " + e.getMessage());
        }
        return null;
    }

    public void setFiltroSiguiente(IFiltro filtroSiguiente) {
        this.filtroSiguiente = filtroSiguiente;
    }

    @Override
    public void notificar(String mensajeRecibido) {
        filtroSiguiente.ejecutar(deserializarRespuesta(mensajeRecibido));
        
    }
    
    
}
