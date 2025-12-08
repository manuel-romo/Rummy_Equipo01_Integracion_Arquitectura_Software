
package ejercerturno.vista;

/**
 *
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 * 
 */
public interface IComponente {
    
    public abstract void agregarComponente(IComponente componente);
    public abstract void removerComponente(IComponente componente);
    public abstract void aceptar(IVisitor visitor);
    public abstract PosicionPanel getPosicion();
}
