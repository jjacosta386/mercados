package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumenAccion.
 */
@SuppressWarnings("serial")
public class ResumenAccion implements IEntidadPortal {

	/** The nemo tecnico. */
	private String nemoTecnico = "";
	
	/** The volumen. */
	private Double volumen = 0d;
	
	/** The ultimo precio. */
	private Double ultimoPrecio = 0d;
	
	/** The variacion. */
	private Double variacion = 0d;
	
	/** The cierre anterior. */
	private Double cierreAnterior = 0d;
	
	/** The fecha. */
	private Calendar fecha = Calendar.getInstance();
	
	/** The cantidad. */
	private Double cantidad = 0d;
	
	/** The orden. */
	private String orden = "";
	
	/** The plazo inicial. */
	private int plazoInicial = 0;
	
	/** The plazo final. */
	private int plazoFinal = 0;
	
	/** The volumen inicial. */
	private Double volumenInicial = 0d;
	
	/** The volumen final. */
	private Double volumenFinal = 0d;
	
	
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
	 * Gets the cierre anterior.
	 * 
	 * @return the cierre anterior
	 */
	public double getCierreAnterior() {
		return cierreAnterior== null? 0 : cierreAnterior;
	}

	/**
	 * Sets the cierre anterior.
	 * 
	 * @param cierreAnterior the new cierre anterior
	 */
	public void setCierreAnterior(double cierreAnterior) {
		this.cierreAnterior = cierreAnterior;
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

	/**
	 * Gets the ultimo precio.
	 * 
	 * @return the ultimo precio
	 */
	public double getUltimoPrecio() {
		return ultimoPrecio == null? 0 : ultimoPrecio;
	}

	/**
	 * Sets the ultimo precio.
	 * 
	 * @param ultimoPrecio the new ultimo precio
	 */
	public void setUltimoPrecio(double ultimoPrecio) {
		this.ultimoPrecio = ultimoPrecio;
	}

	/**
	 * Gets the variacion.
	 * 
	 * @return the variacion
	 */
	public double getVariacion() {
		return variacion == null? 0 : variacion;
	}

	/**
	 * Sets the variacion.
	 * 
	 * @param variacion the new variacion
	 */
	public void setVariacion(double variacion) {
		this.variacion = variacion;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#getId()
	 */
	public Serializable getId() {
		return this.nemoTecnico;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#setId(java.io.Serializable)
	 */
	public void setId(Serializable nemo) {
		this.nemoTecnico = (String) nemo;
	}

	/**
	 * Sets the hora.
	 * 
	 * @param fecha the new hora
	 */
	public void setHora(Calendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the hora.
	 * 
	 * @return the hora
	 */
	public Calendar getHora() {
		return fecha;
	}

	/**
	 * Sets the orden.
	 * 
	 * @param orden the orden
	 * @param volumen the volumen
	 */
	public void setOrden(String orden, double volumen) {
		this.orden = orden;
		if (orden == "S"){
			this.setVolumenInicial(volumen);
		}else {
			this.setVolumenFinal(volumen);
		}
	}

	/**
	 * Gets the orden.
	 * 
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * Sets the plazo inicial.
	 * 
	 * @param plazoInicial the new plazo inicial
	 */
	public void setPlazoInicial(int plazoInicial) {
		this.plazoInicial = plazoInicial;
	}

	/**
	 * Gets the plazo inicial.
	 * 
	 * @return the plazo inicial
	 */
	public int getPlazoInicial() {
		return plazoInicial;
	}

	/**
	 * Sets the plazo final.
	 * 
	 * @param plazoFinal the new plazo final
	 */
	public void setPlazoFinal(int plazoFinal) {
		this.plazoFinal = plazoFinal;
	}

	/**
	 * Gets the plazo final.
	 * 
	 * @return the plazo final
	 */
	public int getPlazoFinal() {
		return plazoFinal;
	}

	/**
	 * Sets the volumen inicial.
	 * 
	 * @param volumenInicial the new volumen inicial
	 */
	public void setVolumenInicial(double volumenInicial) {
		this.volumenInicial = volumenInicial;
	}

	/**
	 * Gets the volumen inicial.
	 * 
	 * @return the volumen inicial
	 */
	public double getVolumenInicial() {
		if ("S".equalsIgnoreCase(orden)){
			return volumenInicial;
		}
		return 0;
	}

	/**
	 * Sets the volumen final.
	 * 
	 * @param volumenFinal the new volumen final
	 */
	public void setVolumenFinal(double volumenFinal) {
		this.volumenFinal = volumenFinal;
	}

	/**
	 * Gets the volumen final.
	 * 
	 * @return the volumen final
	 */
	public double getVolumenFinal() {
		if ("R".equalsIgnoreCase(orden)){
			return volumenFinal;
		}
		return 0;
	}
	
}
