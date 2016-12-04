package co.com.bvc.portal.mercados.modelo;

import java.util.Calendar;

/**
 * The Interface ICierre.
 */

public interface ICierre extends Comparable<ICierre> {
	
	/**
	 * Sets the fecha hora.
	 * 
	 * @param fecha the new fecha hora
	 */
	public void setFechaHora(Calendar fecha);

	/**
	 * Sets the valor cierre.
	 * 
	 * @param valor the new valor cierre
	 */
	public void setValorCierre(double valor);
	
	/**
	 * Sets the valor min.
	 * 
	 * @param valor the new valor min
	 */
	public void setValorMin(double valor);
	
	/**
	 * Sets the valor max.
	 * 
	 * @param valor the new valor max
	 */
	public void setValorMax(double valor);

	/**
	 * Sets the volumen.
	 * 
	 * @param volumen the new volumen
	 */
	public void setVolumen(double volumen);

	/**
	 * Sets the volumen.
	 * 
	 * @param volumen the new volumen
	 */
	/*public void setVolumenMwh(double volumenMwh);*/
	
	/**
	 * Gets the fecha hora.
	 * 
	 * @return the fecha hora
	 */
	public Calendar getFechaHora();

	/**
	 * Gets the valor cierre.
	 * 
	 * @return the valor cierre
	 */
	public double getValorCierre();
	
	/**
	 * Gets the valor min.
	 * 
	 * @return the valor min
	 */
	public double getValorMin();
	
	/**
	 * Gets the valor max.
	 * 
	 * @return the valor max
	 */
	public double getValorMax();
	
	/**
	 * Gets the volumen.
	 * 
	 * @return the volumen
	 */
	public double getVolumen();
	
	/**
	 * Gets the volumen.
	 * 
	 * @return the volumen
	 */
	/*public double getVolumenMwh();*/
	
	/**
	 * Sets the valor ultimo cierre.
	 * 
	 * @param valor the new valor ultimo cierre
	 */
	public void setValorUltimoCierre(double valor);
	
	/**
	 * Gets the valor ultimo cierre.
	 * 
	 * @return the valor ultimo cierre
	 */
	public double getValorUltimoCierre();
	
	/**
	 * Sets the fecha string.
	 * 
	 * @param fechaString the new fecha string
	 */
	public void setFechaString(String fechaString);
	
	/**
	 * Gets the fecha string.
	 * 
	 * @return the fecha string
	 */
	public String getFechaString(); 
}