package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author BVC
 * @contributor Andres_Leon
 * @description The Class ResumenIndice.
 */

public class ResumenIndiceLista implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7747872611619076477L;

	
	/** The indice. */
	private String indice = "";
	
	/** The nombre indice. */
	private String nombreIndice = "";
	
	/** The valor hoy. */
	private double valorHoy = 0;
	
	/** The valor ayer. */
	private double valorAyer = 0;
		
	/** The variacion hoy. */
	private double variacionHoy = 0;
	
	/** The variacion abs. */
	private double variacionAbs = 0;
	
	/** The mercado. */
	private String mercado = "";
	
	/**
	 * Gets the mercado.
	 * 
	 * @return the mercado
	 */
	public String getMercado() {
		return mercado;
	}

	/**
	 * Sets the codigo.
	 * 
	 * @param codigo the new codigo
	 */
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}
	
	/**
	 * Gets the indice.
	 * 
	 * @return the indice
	 */
	public String getIndice() {
		return indice;
	}

	/**
	 * Sets the indice.
	 * 
	 * @param indice the new indice
	 */
	public void setIndice(String indice) {
		this.indice = indice;
	}

	/**
	 * Gets the valor hoy.
	 * 
	 * @return the valor hoy
	 */
	public double getValorHoy() {
		return valorHoy;
	}

	/**
	 * Sets the valor hoy.
	 * 
	 * @param valorHoy the new valor hoy
	 */
	public void setValorHoy(double valorHoy) {
		this.valorHoy = valorHoy;
	}

	/**
	 * Gets the valor ayer.
	 * 
	 * @return the valor ayer
	 */
	public double getValorAyer() {
		return valorAyer;
	}

	/**
	 * Sets the valor ayer.
	 * 
	 * @param valorAyer the new valor ayer
	 */
	public void setValorAyer(double valorAyer) {
		this.valorAyer = valorAyer;
	}

	/**
	 * Gets the variacion hoy.
	 * 
	 * @return the variacion hoy
	 */
	public double getVariacionHoy() {
		return variacionHoy;
	}

	/**
	 * Sets the variacion hoy.
	 * 
	 * @param variacionHoy the new variacion hoy
	 */
	public void setVariacionHoy(double variacionHoy) {
		this.variacionHoy = variacionHoy;
	}

	/**
	 * Gets the variacion abs.
	 * 
	 * @return the variacion abs
	 */
	public double getVariacionAbs() {
		return variacionAbs;
	}

	/**
	 * Sets the variacion abs.
	 * 
	 * @param variacionAbs the new variacion abs
	 */
	public void setVariacionAbs(double variacionAbs) {
		this.variacionAbs = variacionAbs;
	}

	/**
	 * Gets the nombre indice.
	 * 
	 * @return the nombre indice
	 */
	public String getNombreIndice() {
		return nombreIndice;
	}

	/**
	 * Sets the nombre indice.
	 * 
	 * @param nombreIndice the new nombre indice
	 */
	public void setNombreIndice(String nombreIndice) {
		this.nombreIndice = nombreIndice;
	}

	
}
/**************************************************End of ResumenIndiceLista.java***********************************/
