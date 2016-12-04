/**
 * 
 */
package co.com.bvc.portal.mercados.exception;

/**
 * Excepci�n que se lanza al encontrar una posible inyecci�n de sql
 * 
 * @author William Bernal
 * 
 */
public class SqlInjectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5999998756461319932L;

	/**
	 * Mensaje estandar que se mostrar� cuando se lance una excepcion de estas
	 */
	private static final String mensajeExcepcion = "Posible inyecci�n de sql encontrada";

	public SqlInjectionException(String arg0) {
		super(mensajeExcepcion + ": " + arg0);
	}

}