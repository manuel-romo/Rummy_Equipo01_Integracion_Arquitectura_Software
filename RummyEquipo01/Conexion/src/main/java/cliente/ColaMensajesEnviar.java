
package cliente;

import mensajes.Mensaje;
import interfaces.IDispatcher;
import interfaces.ISuscriptor;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public class ColaMensajesEnviar implements IDispatcher, Runnable{
    
    private BlockingQueue<Mensaje> colaMensajes = new LinkedBlockingDeque<Mensaje>();
    
    private ISuscriptor suscriptor;
    
    @Override
    public void agregarMensaje(String mensaje, String[] direccion) {
        
        String ip;
        String puertoStr;
        int puerto;

        try {
            ip = direccion[0];
            puertoStr = direccion[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error de validación: El formato de dirección es [ip, puerto]. Descartando mensaje.");
            return;
        }

        try {
            puerto = Integer.parseInt(puertoStr);

        } catch (NumberFormatException e) {
            System.err.println("Error de validación: El puerto '" + puertoStr + "' no es un número. Descartando mensaje.");
            return;
        }
        

        if (puerto < 0 || puerto > 65535) {
            System.err.println("Error de validación: El puerto " + puerto + " está fuera de rango (1-65535). Descartando mensaje.");
            return;
        }
    
        try {
            colaMensajes.put(new Mensaje(ip, puerto, mensaje)); 

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
    
    @Override
    public void run() {
        try {

            while (true) {
                
                Mensaje mensaje = colaMensajes.take();
                
                suscriptor.notificar(mensaje);
            }
            
        } catch (InterruptedException e) {
            
            Thread.currentThread().interrupt();
        }
    }

    public void setSuscriptor(ISuscriptor suscriptor) {
        this.suscriptor = suscriptor;
    }
    
    
    
    
}
