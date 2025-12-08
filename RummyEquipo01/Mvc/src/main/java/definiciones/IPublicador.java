

package definiciones;

import definiciones.ISuscriptor;

/**
 * IPublicador.java
 
 Interfaz que define los métodos que debe tener el Publicador en el patrón MVC.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public interface IPublicador{
    /**
     * Método que permite suscribirse a un objeto que quiere observar a este Publicador.
     * @param suscriptor Objeto que implementa la interfaz ISuscriptor, representa a un
     * observador de este Publicador.
     */
    public abstract void suscribirse(ISuscriptor suscriptor);
    
    /**
     * Método que permite a un objeto suscriptor desuscribirse para ya no observar a este Publicador.
     * @param suscriptor Objeto que implementa la interfaz ISuscriptor, representa a un
     * observador de este Publicador.
     */
    public abstract void desuscribirse(ISuscriptor suscriptor);
    
    /**
     * Método que permite actualizar a todos los observadores.
     */
    public abstract void notificar();
}
