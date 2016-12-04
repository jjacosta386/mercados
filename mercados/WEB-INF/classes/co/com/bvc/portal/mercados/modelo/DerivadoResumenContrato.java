/**
 * 
 */
package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * Clase que define los datos que se muestran en la interfaz sobre las entidades
 * en resumen del contrato.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class DerivadoResumenContrato extends DerivadoResumenExtendido implements
		IEntidadPortal {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3718403219480220775L;

	/** The precio mayor. */
	private Double precioMayor;

	/** The precio menor. */
	private Double precioMenor;

	/** The precio apertura. */
	private Double precioApertura;

	/** The mejor bid. */
	private Double mejorBid;

	/** The mejor offer. */
	private Double mejorOffer;

	/** The ultima operacion. */
	private String ultimaOperacion;

	/**
	 * Gets the precio mayor.
	 * 
	 * @return the precioMayor
	 */
	public Double getPrecioMayor() {
		return precioMayor;
	}

	/**
	 * Sets the precio mayor.
	 * 
	 * @param precioMayor the precioMayor to set
	 */
	public void setPrecioMayor(Double precioMayor) {
		this.precioMayor = precioMayor;
	}

	/**
	 * Gets the precio menor.
	 * 
	 * @return the precioMenor
	 */
	public Double getPrecioMenor() {
		return precioMenor;
	}

	/**
	 * Sets the precio menor.
	 * 
	 * @param precioMenor the precioMenor to set
	 */
	public void setPrecioMenor(Double precioMenor) {
		this.precioMenor = precioMenor;
	}

	/**
	 * Gets the precio apertura.
	 * 
	 * @return the precioApertura
	 */
	public Double getPrecioApertura() {
		return precioApertura;
	}

	/**
	 * Sets the precio apertura.
	 * 
	 * @param precioApertura the precioApertura to set
	 */
	public void setPrecioApertura(Double precioApertura) {
		this.precioApertura = precioApertura;
	}

	/**
	 * Gets the mejor bid.
	 * 
	 * @return the mejorBid
	 */
	public Double getMejorBid() {
		return mejorBid;
	}

	/**
	 * Sets the mejor bid.
	 * 
	 * @param mejorBid the mejorBid to set
	 */
	public void setMejorBid(Double mejorBid) {
		this.mejorBid = mejorBid;
	}

	/**
	 * Gets the mejor offer.
	 * 
	 * @return the mejorOffer
	 */
	public Double getMejorOffer() {
		return mejorOffer;
	}

	/**
	 * Sets the mejor offer.
	 * 
	 * @param mejorOffer the mejorOffer to set
	 */
	public void setMejorOffer(Double mejorOffer) {
		this.mejorOffer = mejorOffer;
	}

	/**
	 * Gets the ultima operacion.
	 * 
	 * @return the ultimaOperacion
	 */
	public String getUltimaOperacion() {
		if (StringUtils.isNotEmpty(ultimaOperacion))
			return StringUtils.substring(ultimaOperacion, 0, 19);
		return ultimaOperacion;
	}

	/**
	 * Sets the ultima operacion.
	 * 
	 * @param ultimaOperacion the ultimaOperacion to set
	 */
	public void setUltimaOperacion(String ultimaOperacion) {
		this.ultimaOperacion = ultimaOperacion;
	}

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