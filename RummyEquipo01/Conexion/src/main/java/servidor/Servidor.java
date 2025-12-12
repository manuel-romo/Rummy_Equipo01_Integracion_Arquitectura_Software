
package servidor;

import interfaces.IReceptor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable{

    private IReceptor receptor;
    private boolean estaCorriendo = true;
    private ServerSocket serverSocket;

    public Servidor(int puerto) {

        ServerSocket ss;
        try {
            ss = new ServerSocket(puerto);
            this.serverSocket = ss;
            System.out.println("Hilo iniciado, escuchando en el puerto " + serverSocket.getLocalPort() + "...");
        
        } catch (IOException ex) {
            System.err.println("Error de IOException: " + ex.getMessage());
        }
        
    }

    @Override
    public void run() {

        try {

            while (estaCorriendo) {
                
                Socket socketCliente = serverSocket.accept(); 
                
                System.out.println("Nuevo cliente conectado: " + socketCliente.getInetAddress().getHostAddress());

                ManejadorCliente manejador = new ManejadorCliente(socketCliente, receptor);
                
                new Thread(manejador).start();
            }
            
        } catch (IOException e) {
            
            if (estaCorriendo) {
                System.err.println("Error de IOException: " + e.getMessage());
            } else {
                System.out.println("Servidor detenido correctamente.");
            }
        }
    }
    
    
    public void detener() {
        estaCorriendo = false;
        try {

            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            
        } catch (IOException e) {
            System.err.println("Error al cerrar el socket: " + e.getMessage());
        }
    }

    public void setReceptor(IReceptor receptor) {
        this.receptor = receptor;
    }
    
    public int obtenerPuerto(){
        return serverSocket.getLocalPort();
    }
    

    
    
}
