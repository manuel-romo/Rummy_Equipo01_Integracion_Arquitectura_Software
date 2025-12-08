
package ensamblador;

import cliente.ColaMensajesEnviar;
import cliente.GestorConexiones;
import deserializador.Deserializador;
import directorio.DirectorioJugadores;
import java.util.LinkedList;
import java.util.List;
import objetos_negocio.FachadaObjetosNegocio;
import objetos_negocio.Jugador;
import objetos_negocio.Partida;
import objetos_negocio.Tablero;
import serializador.Serializador;
import servidor.ColaMensajesRecibidos;
import servidor.Servidor;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class EnsambladorServidor {

    public static void main(String[] args) {
        
        try {
            
            // Creación de clases de componentes.
            GestorConexiones gestorConexiones = new GestorConexiones();
            
            ColaMensajesEnviar colaMensajesEnviar = new ColaMensajesEnviar();
            
            DirectorioJugadores directorioJugadores = new DirectorioJugadores();
            
            Serializador serializador = new Serializador();
            
            FachadaObjetosNegocio fachadaObjetosNegocio = new FachadaObjetosNegocio();
            
            Tablero tablero = new Tablero();
            
            List<Jugador> jugadores = new LinkedList<>();
            
            Jugador jugador1 = new Jugador("/avatar1.png", "Francisco34");
            Jugador jugador2 = new Jugador("/avatar2.png", "Sandy43");
            
            jugadores.add(jugador1);
            jugadores.add(jugador2);
            
            Partida partida = new Partida(jugadores);
            
            Servidor servidorServidor = new Servidor(50000);
            
            ColaMensajesRecibidos colaMensajesRecibidos = new ColaMensajesRecibidos();
            
            Deserializador deserializador = new Deserializador();
            
            // Conexión de componentes (Envío).
            
            colaMensajesEnviar.setSuscriptor(gestorConexiones);
            
            directorioJugadores.setDispatcher(colaMensajesEnviar);
            
            serializador.setFiltroSiguiente(directorioJugadores);
            
            fachadaObjetosNegocio.setFiltroSiguiente(serializador);
            
            tablero.setFachada(fachadaObjetosNegocio);
            
            partida.setTablero(tablero);
            partida.setFachada(fachadaObjetosNegocio);
            
            fachadaObjetosNegocio.setPartida(partida);
            
            // Conexión de componentes (Recepción).
            
            servidorServidor.setReceptor(colaMensajesRecibidos);
            
            colaMensajesRecibidos.setReceptor(deserializador);
            
            deserializador.setFiltroSiguiente(fachadaObjetosNegocio);
            
            new Thread(colaMensajesEnviar).start();
            
            new Thread(servidorServidor).start();
            new Thread(colaMensajesRecibidos).start();
            
            partida.cargarJugadores();
            
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
}
