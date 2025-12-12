
package ensamblador;

import cliente.ColaMensajesEnviar;
import cliente.GestorConexiones;
import deserializador.Deserializador;
import directorio.DirectorioJugadores;
import negocio.FachadaObjetosNegocio;
import negocio.Partida;
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
            
            Partida partida = new Partida();
            
            Servidor servidorServidor = new Servidor(50000);
            
            ColaMensajesRecibidos colaMensajesRecibidos = new ColaMensajesRecibidos();
            
            Deserializador deserializador = new Deserializador();
            
            // Conexión de componentes (Envío).
            
            colaMensajesEnviar.setSuscriptor(gestorConexiones);
            
            directorioJugadores.setDispatcher(colaMensajesEnviar);
            
            serializador.setFiltroSiguiente(directorioJugadores);
            
            fachadaObjetosNegocio.setFiltroSiguiente(serializador);
            
            partida.setFachada(fachadaObjetosNegocio);
            
            fachadaObjetosNegocio.setPartida(partida);
            
            // Conexión de componentes (Recepción).
            
            servidorServidor.setReceptor(colaMensajesRecibidos);
            
            colaMensajesRecibidos.setReceptor(deserializador);
            
            deserializador.setFiltroSiguiente(fachadaObjetosNegocio);
            
            new Thread(colaMensajesEnviar).start();
            
            new Thread(servidorServidor).start();
            new Thread(colaMensajesRecibidos).start();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
}
