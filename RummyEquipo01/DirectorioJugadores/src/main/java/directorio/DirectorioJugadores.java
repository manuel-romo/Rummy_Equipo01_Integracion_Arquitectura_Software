package directorio;

import comandos.directorio.ComandoAgregarDireccionJugador;
import comandos.envolventes.ComandoEnvolvente;
import enumeradores.TipoComando;
import interfaces.IDispatcher;
import interfaces.IFiltro;
import java.util.Map;
import interfaces.IComando;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * Filtro encargado de enviar el mensaje de un comando envolvente a la dirección
 * del jugador correspondiente.
 *
 *
 *
 * @author ramon
 */
public class DirectorioJugadores implements IFiltro {

    private Map<String, String[]> jugadoresDirecciones = new HashMap<>();

    private IDispatcher dispatcher;

    public void agregarJugador(String nombreJugador, String[] direccion) {
        jugadoresDirecciones.put(nombreJugador, direccion);
    }

    public void setDispatcher(IDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    private void agregarDireccionNombreJugador(String nombreJugador, String[] direccionJugador) {

        jugadoresDirecciones.put(nombreJugador, direccionJugador);

    }

    @Override
    public void ejecutar(IComando comando) {

        TipoComando tipoComando = TipoComando.fromNombre(comando.getTipo());

        switch (tipoComando) {

            case TipoComando.COMANDO_AGREGAR_DIRECCION_JUGADOR:

                ComandoAgregarDireccionJugador comandoAgregarDireccionJugador = (ComandoAgregarDireccionJugador) comando;

                agregarDireccionNombreJugador(
                        comandoAgregarDireccionJugador.getNombreJugador(), 
                        comandoAgregarDireccionJugador.getDireccion());

                break;

            case TipoComando.COMANDO_ENVOLVENTE:

                ComandoEnvolvente envolvente = (ComandoEnvolvente) comando;
                
                dispatcher.agregarMensaje(envolvente.getMensajeSerializado(), jugadoresDirecciones.get(comando.getNombreJugador()));

                break;
        }
    }

}
