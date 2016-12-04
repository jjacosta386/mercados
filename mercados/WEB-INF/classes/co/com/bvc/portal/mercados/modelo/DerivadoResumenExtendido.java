/**
 * 
 */
package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * Clase que define los datos que se muestran en la interfaz sobre las entidades
 * de los derivados.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class DerivadoResumenExtendido extends DerivadoResumen implements
		IEntidadPortal {

	/** The precio. */
	private Double precio;

	/** The variacion. */
	private Double variacion;
	
	/** The vencimiento. */
	private String vencimiento;
	
	
	private String fecha;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the precio.
	 * 
	 * @return the precio
	 */
	public Double getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 * 
	 * @param precio the precio to set
	 */
	public void setPrecio(Double precio) {
		this.precio = precio;
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
	 * Gets the vencimiento.
	 * 
	 * @return the vencimiento
	 */
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * Sets the vencimiento.
	 * 
	 * @param vencimiento the vencimiento to set
	 */
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8552052706718006225L;

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