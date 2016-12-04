/**
 * 
 */
package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * Clase que define los datos que se muestran en la interfaz del mercado de derivados.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class MercadoDerivados implements IEntidadPortal{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2278734473897587071L;

	/** The contrato. */
	private String contrato;
	
	/** The contratos. */
	private Integer contratos;
	
		/** The volumen. */
	private Long volumen;
	
	/** The volumen MWH. */
	private Long volumenMwh;

	/** The open interest. */
	private Double openInterest;
	
	/** The participacion. */
	private Double participacion;	

	/** The order. */
	private int order;
	
	
	
	
	// private String contratocorto;
	
	public String getContratocorto() {
		return contrato;
	}

	public void setContratocorto(String contratocorto) {
		this.contrato = contratocorto;
	}
	
	
	
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * Gets the contrato.
	 * 
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}

	/**
	 * Sets the contrato.
	 * 
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	/**
	 * Gets the contratos.
	 * 
	 * @return the contratos
	 */
	public Integer getContratos() {
		return contratos;
	}

	/**
	 * Sets the contratos.
	 * 
	 * @param contratos the contratos to set
	 */
	public void setContratos(Integer contratos) {
		this.contratos = contratos;
	}

	/**
	 * Gets the volumen.
	 * 
	 * @return the volumen
	 */
	public Long getVolumen() {
		return volumen;
	}

	/**
	 * Sets the volumen.
	 * 
	 * @param volumen the volumen to set
	 */
	public void setVolumen(Long volumen) {
		this.volumen = volumen;
	}
	
	public Long getVolumenMwh() {
		return volumenMwh;
	}

	public void setVolumenMwh(Long volumenMwh) {
		this.volumenMwh = volumenMwh;
	}
	
	/**
	 * Gets the open interest.
	 * 
	 * @return the openInterest
	 */
	public Double getOpenInterest() {
		return openInterest;
	}

	/**
	 * Sets the open interest.
	 * 
	 * @param openInterest the openInterest to set
	 */
	public void setOpenInterest(Double openInterest) {
		this.openInterest = openInterest;
	}

	/**
	 * Gets the participacion.
	 * 
	 * @return the participacion
	 */
	public Double getParticipacion() {
		return participacion;
	}

	/**
	 * Sets the participacion.
	 * 
	 * @param participacion the participacion to set
	 */
	public void setParticipacion(Double participacion) {
		this.participacion = participacion;
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