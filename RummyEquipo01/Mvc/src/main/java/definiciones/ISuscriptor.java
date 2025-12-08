
package definiciones;


/**
 * ISuscriptor.java
 * 
 * Interfaz que define el método que los objetos que desean observar a un Publicador
 * en el patrón MVC puedan hacerlo y se conviertan en Observadores.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */

public interface ISuscriptor {
    
    /**
     * Permite que al publicador actualizar a este Sucriptor que lo observa. 
     * @param modelo Objeto que implementa la interfaz IModeloEjercerTurno, es el modelo al que accede
 la vista suscriptora.
     * @param publicador Objeto que implementa la interfaz IPublicador, es el 
     * Publicador que se envía a sí mismo.
     */
    public abstract void actualizar(IModelo modelo);
    
}
