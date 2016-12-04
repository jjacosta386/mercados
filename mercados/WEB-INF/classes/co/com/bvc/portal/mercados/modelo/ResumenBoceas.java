package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumenAccion.
 */
@SuppressWarnings("serial")
public class ResumenBoceas implements IEntidadPortal {

	/** The nemo tecnico. */
	private String nemoTecnico = "";
	
	/** The volumen. */
	private Double volumen = 0d;
	
		
	
	/** The cantidad. */
	private Double cantidad = 0d;
	
	
	
	
	/**
	 * Gets the cantidad.
	 * 
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad == null? 0 : cantidad;
	}

	/**
	 * Sets the cantidad.
	 * 
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	

	/**
	 * Gets the nemo tecnico.
	 * 
	 * @return the nemo tecnico
	 */
	public String getNemoTecnico() {
		return nemoTecnico;
	}

	/**
	 * Sets the nemo tecnico.
	 * 
	 * @param nemoTecnico the new nemo tecnico
	 */
	public void setNemoTecnico(String nemoTecnico) {
		this.nemoTecnico = nemoTecnico;
	}

	/**
	 * Gets the volumen.
	 * 
	 * @return the volumen
	 */
	public double getVolumen() {
		return volumen == null? 0 : volumen;
	}

	/**
	 * Sets the volumen.
	 * 
	 * @param volumen the new volumen
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(Serializable arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
