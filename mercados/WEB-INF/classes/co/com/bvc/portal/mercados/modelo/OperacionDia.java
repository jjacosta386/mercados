/**
 * 
 */
package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * Clase que define los datos que se muestran en la interfaz con respecto a las operaciones en el día.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class OperacionDia implements IEntidadPortal {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2341688755222153173L;

	/** The precio. */
	private float precio;
	
	/** The contratos. */
	private float contratos;
	
	/** The volumen. */
	private float volumen;

	/**
	 * Gets the precio.
	 * 
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 * 
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * Gets the contratos.
	 * 
	 * @return the contratos
	 */
	public float getContratos() {
		return contratos;
	}

	/**
	 * Sets the contratos.
	 * 
	 * @param contratos the contratos to set
	 */
	public void setContratos(float contratos) {
		this.contratos = contratos;
	}

	/**
	 * Gets the volumen.
	 * 
	 * @return the volumen
	 */
	public float getVolumen() {
		return volumen;
	}

	/**
	 * Sets the volumen.
	 * 
	 * @param volumen the volumen to set
	 */
	public void setVolumen(float volumen) {
		this.volumen = volumen;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#getId()
	 */
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#setId(java.io.Serializable)
	 */
	public void setId(Serializable arg0) {
		// TODO Auto-generated method stub
		
	}
	
}