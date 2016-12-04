package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

/**
 * Clase que define los datos que se muestran en la interfaz sobre los derivados.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class Derivado implements IEntidadPortal {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4901896877081861433L;

	/** nombre del derivado. */
	private String nombreDerivado;

	/** sigla del derivado. */
	private String siglaDerivado;

	/** Lista de los derivados del tipo (resumen). */
	private Set<DerivadoResumen> derivadosResumen = new LinkedHashSet<DerivadoResumen>();

	/**
	 * Gets the nombre derivado.
	 * 
	 * @return the nombreDerivado
	 */
	public String getNombreDerivado() {
		return nombreDerivado;
	}

	/**
	 * Sets the nombre derivado.
	 * 
	 * @param nombreDerivado the nombreDerivado to set
	 */
	public void setNombreDerivado(String nombreDerivado) {
		this.nombreDerivado = nombreDerivado;
	}

	/**
	 * Gets the sigla derivado.
	 * 
	 * @return the siglaDerivado
	 */
	public String getSiglaDerivado() {
		return siglaDerivado;
	}

	/**
	 * Sets the sigla derivado.
	 * 
	 * @param siglaDerivado the siglaDerivado to set
	 */
	public void setSiglaDerivado(String siglaDerivado) {
		this.siglaDerivado = siglaDerivado;
	}

	/**
	 * Gets the derivados resumen.
	 * 
	 * @return the derivadosResumen
	 */
	public Set<DerivadoResumen> getDerivadosResumen() {
		return derivadosResumen;
	}

	/**
	 * Sets the derivados resumen.
	 * 
	 * @param derivadosResumen the derivadosResumen to set
	 */
	public void setDerivadosResumen(
			Set<DerivadoResumen> derivadosResumen) {
		this.derivadosResumen = derivadosResumen;
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