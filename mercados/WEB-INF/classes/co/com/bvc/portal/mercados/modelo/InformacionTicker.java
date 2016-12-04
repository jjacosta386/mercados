/**
 * 
 */
package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * Bean que contiene la información que se quiere mostrar en el ticker.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class InformacionTicker implements IEntidadPortal {

	/** The ultimo precio tasa. */
	private Double ultimoPrecioTasa;

	/** The nemo. */
	private String nemo;

	/** The variacion. */
	private Double variacion;

	/** The hora ultima marcacion. */
	private String horaUltimaMarcacion;

	/** The volumen. */
	private Double volumen;
	
	/**
	 * Gets the ultimo precio tasa.
	 * 
	 * @return the ultimoPrecioTasa
	 */
	public Double getUltimoPrecioTasa() {
		return ultimoPrecioTasa;
	}

	/**
	 * Sets the ultimo precio tasa.
	 * 
	 * @param ultimoPrecioTasa the ultimoPrecioTasa to set
	 */
	public void setUltimoPrecioTasa(Double ultimoPrecioTasa) {
		this.ultimoPrecioTasa = ultimoPrecioTasa;
	}

	/**
	 * Gets the nemo.
	 * 
	 * @return the nemo
	 */
	public String getNemo() {
		return nemo;
	}

	/**
	 * Sets the nemo.
	 * 
	 * @param nemo the nemo to set
	 */
	public void setNemo(String nemo) {
		this.nemo = nemo;
	}

	/**
	 * Gets the variacion.
	 * 
	 * @return the variacion
	 */
	public Double getVariacion() {
		return variacion;
	}

	/**
	 * Sets the variacion.
	 * 
	 * @param variacion the variacion to set
	 */
	public void setVariacion(Double variacion) {
		this.variacion = variacion;
	}

	/**
	 * Gets the hora ultima marcacion.
	 * 
	 * @return the horaUltimaMarcacion
	 */
	public String getHoraUltimaMarcacion() {
		return horaUltimaMarcacion;
	}

	/**
	 * Sets the hora ultima marcacion.
	 * 
	 * @param horaUltimaMarcacion the horaUltimaMarcacion to set
	 */
	public void setHoraUltimaMarcacion(String horaUltimaMarcacion) {
		this.horaUltimaMarcacion = horaUltimaMarcacion;
	}

	/**
	 * Gets the volumen.
	 * 
	 * @return the volumen
	 */
	public Double getVolumen() {
		return volumen;
	}

	/**
	 * Sets the volumen.
	 * 
	 * @param volumen the volumen to set
	 */
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}
	
	/**
	 * Gets the calendar fecha.
	 * 
	 * @return the calendar fecha
	 */
	public Calendar getCalendarFecha(){
		if(StringUtils.isEmpty(getHoraUltimaMarcacion()))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss.SSSSSS");
		Calendar result = new GregorianCalendar();
		try{
			result.setTime(sdf.parse(getHoraUltimaMarcacion()));
		}catch (ParseException e) {
			result = new GregorianCalendar();
		}
		return result;
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1917283319181883597L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#getId()
	 */
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.comun.modelo.IEntidadPortal#setId(java.io.Serializable)
	 */
	public void setId(Serializable arg0) {
		// TODO Auto-generated method stub

	}

}