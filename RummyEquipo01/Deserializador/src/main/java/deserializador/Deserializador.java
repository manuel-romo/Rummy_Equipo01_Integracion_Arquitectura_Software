package deserializador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import comandos.respuesta.ComandoActualizarJugadoresInicioJuego;
import comandos.respuesta.ComandoCambioTurno;
import comandos.respuesta.ComandoCargarJugadores;
import comandos.respuesta.ComandoDecisionIniciarJuego;
import comandos.respuesta.ComandoFinPartida;
import comandos.respuesta.ComandoIniciarTurno;
import comandos.respuesta.ComandoJugadorAbandonoPartida;
import comandos.respuesta.ComandoJugadorPartidaGanada;
import comandos.respuesta.ComandoNuevaSolicitudIniciarJuego;
import comandos.respuesta.ComandoPartidaConfigurada;
import comandos.respuesta.ComandoPartidaGanada;
import comandos.respuesta.ComandoRegistroExitoso;
import comandos.respuesta.ComandoRegistroFallido;
import comandos.respuesta.ComandoRespuestaAbandonar;
import comandos.respuesta.ComandoRespuestaConfirmacionSolicitarFin;
import comandos.respuesta.ComandoRespuestaConfirmacionUnirsePartida;
import comandos.respuesta.ComandoRespuestaIniciarJuego;
import comandos.respuesta.ComandoRespuestaMovimiento;
import comandos.respuesta.ComandoRespuestaReestablecer;
import comandos.respuesta.ComandoRespuestaSolicitarFin;
import comandos.respuesta.ComandoRespuestaTomarFicha;
import comandos.respuesta.ComandoRespuestaUnirsePartida;
import comandos.respuesta.ComandoTableroInvalido;
import comandos.solicitud.ComandoAbandonar;
import comandos.solicitud.ComandoAgregarFichasJugador;
import comandos.solicitud.ComandoAgregarFichasTablero;
import comandos.solicitud.ComandoAgregarFichasTableroGrupo;
import comandos.solicitud.ComandoConfigurarPartida;
import comandos.solicitud.ComandoConfirmacionAbandonar;
import comandos.solicitud.ComandoConfirmacionEnvioIniciarJuego;
import comandos.solicitud.ComandoConfirmacionIniciarJuego;
import comandos.solicitud.ComandoConfirmacionSolicitarFin;
import comandos.solicitud.ComandoConfirmacionUnirsePartida;
import comandos.solicitud.ComandoIniciarJuego;
import comandos.solicitud.ComandoQuitarFichasJugador;
import comandos.solicitud.ComandoQuitarFichasTablero;
import comandos.solicitud.ComandoReestablecerTablero;
import comandos.solicitud.ComandoRegistrarJugador;
import comandos.solicitud.ComandoSeleccionarFichasTablero;
import comandos.solicitud.ComandoSolicitarFin;
import comandos.solicitud.ComandoTerminarTurno;
import comandos.solicitud.ComandoTomarFicha;
import comandos.solicitud.ComandoUnirsePartida;
import dtos.FichaDTO;
import interfaces.IFiltro;
import interfaces.IReceptorExterno;
import java.util.HashMap;
import java.util.Map;
import interfaces.IComando;

/**
 *
 * @author ramon
 */
public class Deserializador implements IReceptorExterno {

    private IFiltro filtroSiguiente;
    private final Gson gson;
    private final Map<String, Class<? extends IComando>> registroComandos;

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
        //REGISTRO JUGADOR JP
        registroComandos.put("ComandoRegistrarJugador", ComandoRegistrarJugador.class);
        registroComandos.put("ComandoRegistroFallido", ComandoRegistroFallido.class);
        registroComandos.put("ComandoRegistroExitoso", ComandoRegistroExitoso.class);

        //CONFIGURAR PARTIDA PEDRO
        registroComandos.put("ComandoConfigurarPartida", ComandoConfigurarPartida.class);
        registroComandos.put("ComandoPartidaConfigurada", ComandoPartidaConfigurada.class);
        
        // SOLICITAR UNIRSE PARTIDA
        registroComandos.put("ComandoUnirsePartida", ComandoUnirsePartida.class);
        registroComandos.put("ComandoRespuestaUnirsePartida", ComandoRespuestaUnirsePartida.class);
        registroComandos.put("ComandoConfirmacionUnirsePartida", ComandoConfirmacionUnirsePartida.class);
        registroComandos.put("ComandoRespuestaConfirmacionUnirsePartida", ComandoRespuestaConfirmacionUnirsePartida.class);

    }

    /**
     * Método que recibe el JSON y deserializa el comando para que se pueda
     * utilizar.
     *
     * @param Comando serializado que se deserializa.
     * @return
     */
    private IComando deserializarRespuesta(String respuesta) {
        try {
            if (respuesta == null || respuesta.trim().isEmpty()) {
                System.err.println("Error: Recibido string vacío o nulo.");
                return null;
            }

            JsonObject objetoJson = JsonParser.parseString(respuesta).getAsJsonObject();

            if (!objetoJson.has("type")) {
                System.err.println("Error: El JSON recibido no tiene campo 'type': " + respuesta);
                return null;
            }

            String type = objetoJson.get("type").getAsString();

            Class<? extends IComando> claseComando = registroComandos.get(type);

            if (claseComando != null) {
                return gson.fromJson(objetoJson, claseComando);
            } else {
                System.err.println("Error: El tipo '" + type + "' no existe en registroComandos.");
                System.err.println("Tipos registrados disponibles: " + registroComandos.keySet());
                return null;
            }

        } catch (JsonSyntaxException e) {
            System.err.println("Error al deserializar JSON: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en deserializar: " + e.getMessage());
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
