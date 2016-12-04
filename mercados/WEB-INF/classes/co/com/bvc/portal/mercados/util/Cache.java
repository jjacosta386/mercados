package co.com.bvc.portal.mercados.util;

import java.io.Serializable;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class Cache.
 */
public class Cache implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4149465570812536244L;
	
	/** The time stamp. */
	private Calendar timeStamp = Calendar.getInstance();
	
	/** The objeto cacheado. */
	private Object objetoCacheado = null;

	/**
	 * Gets the time stamp.
	 * 
	 * @return the time stamp
	 */
	public Calendar getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Gets the objeto cacheado.
	 * 
	 * @return the objeto cacheado
	 */
	public Object getObjetoCacheado() {
		return objetoCacheado;
	}

	/**
	 * Sets the objeto cacheado.
	 * 
	 * @param objetoCacheado the new objeto cacheado
	 */
	public void setObjetoCacheado(Object objetoCacheado) {
		this.objetoCacheado = objetoCacheado;
	}

	/**
	 * Sets the time stamp.
	 * 
	 * @param timeStamp the new time stamp
	 */
	public void setTimeStamp(Calendar timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * Recargar cache.
	 * 
	 * @return true, if successful
	 */
	public boolean recargarCache(){
		Calendar tmp = (Calendar) this.timeStamp.clone();
		tmp.add(Calendar.MINUTE, 3);
		Calendar actual = Calendar.getInstance();
		
		return actual.after(tmp);
	}
	
	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty(){
		return this.objetoCacheado == null ? true : false;
	}

}
