/**
 * 
 */
package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.util.Set;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * Clase que define los datos que se muestran en la interfaz sobre las enitdades
 * de los derivados.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class DerivadoResumen implements IEntidadPortal {

	/** The tipo derivado. */
	private String tipoDerivado;

	/** The contrato. */
	private String contrato;

	/** The nemo. */
	private String nemo;

	/** The contratos. */
	private Double contratos;

	/** The open interest. */
	private Double openInterest;

	/** The volumen. */
	private Double volumen;

	/** The volumen MWH. */
	private Double volumenMwh;
	

	/** The derivados extendidos. */
	private Set<DerivadoResumenExtendido> derivadosExtendidos;

	/**
	 * Gets the tipo derivado.
	 * 
	 * @return the tipoDerivado
	 */
	public String getTipoDerivado() {
		return tipoDerivado;
	}

	/**
	 * Sets the tipo derivado.
	 * 
	 * @param tipoDerivado the tipoDerivado to set
	 */
	public void setTipoDerivado(String tipoDerivado) {
		this.tipoDerivado = tipoDerivado;
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
	 * Gets the contratos.
	 * 
	 * @return the contratos
	 */
	public Double getContratos() {
		return contratos;
	}

	/**
	 * Sets the contratos.
	 * 
	 * @param contratos the contratos to set
	 */
	public void setContratos(Double contratos) {
		this.contratos = contratos;
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
	
	public Double getVolumenMwh() {
		return volumenMwh;
	}

	public void setVolumenMwh(Double volumenMwh) {
		this.volumenMwh = volumenMwh;
	}


	/**
	 * Gets the derivados extendidos.
	 * 
	 * @return the derivadosExtendidos
	 */
	public Set<DerivadoResumenExtendido> getDerivadosExtendidos() {
		return derivadosExtendidos;
	}

	/**
	 * Sets the derivados extendidos.
	 * 
	 * @param derivadosExtendidos the derivadosExtendidos to set
	 */
	public void setDerivadosExtendidos(
			Set<DerivadoResumenExtendido> derivadosExtendidos) {
		this.derivadosExtendidos = derivadosExtendidos;
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