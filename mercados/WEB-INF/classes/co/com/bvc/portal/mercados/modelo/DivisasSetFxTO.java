package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class DivisasSetFxTO.
 */
public class DivisasSetFxTO implements Serializable {

	/**
	 * identificador único
	 */
	private static final long serialVersionUID = -1505150817121150420L;

	/** The precio apertura. */
	private Double precioApertura;

	/** The precio ultimo. */
	private Double precioUltimo;

	/** The precio promedio. */
	private Double precioPromedio;

	/** The precio minimo. */
	private Double precioMinimo;

	/** The precio maximo. */
	private Double precioMaximo;

	/** The volumen negociado. */
	private Double volumenNegociado;

	/** The volumen ultimo. */
	private Double volumenUltimo;

	/** The volumen promedio. */
	private Double volumenPromedio;

	/** The volumen minimo. */
	private Double volumenMinimo;

	/** The volumen maximo. */
	private Double volumenMaximo;

	/** The num transacciones. */
	private Double numTransacciones;

	/** The tipo mercado. */
	private String tipoMercado;

	/** The trm. */
	private Double trm;

	/**
	 * Gets the precio apertura.
	 * 
	 * @return the precio apertura
	 */
	public Double getPrecioApertura() {
		return precioApertura == null ? 0 : precioApertura;
	}

	/**
	 * Sets the precio apertura.
	 * 
	 * @param precioApertura
	 *            the new precio apertura
	 */
	public void setPrecioApertura(Double precioApertura) {
		this.precioApertura = precioApertura;
	}

	/**
	 * Gets the precio ultimo.
	 * 
	 * @return the precio ultimo
	 */
	public Double getPrecioUltimo() {
		return precioUltimo == null ? 0 : precioUltimo;
	}

	/**
	 * Sets the precio ultimo.
	 * 
	 * @param precioUltimo
	 *            the new precio ultimo
	 */
	public void setPrecioUltimo(Double precioUltimo) {
		this.precioUltimo = precioUltimo;
	}

	/**
	 * Gets the precio promedio.
	 * 
	 * @return the precio promedio
	 */
	public Double getPrecioPromedio() {
		return precioPromedio == null ? 0 : precioPromedio;
	}

	/**
	 * Sets the precio promedio.
	 * 
	 * @param precioPromedio
	 *            the new precio promedio
	 */
	public void setPrecioPromedio(Double precioPromedio) {
		this.precioPromedio = precioPromedio;
	}

	/**
	 * Gets the precio minimo.
	 * 
	 * @return the precio minimo
	 */
	public Double getPrecioMinimo() {
		return precioMinimo == null ? 0 : precioMinimo;
	}

	/**
	 * Sets the precio minimo.
	 * 
	 * @param precioMinimo
	 *            the new precio minimo
	 */
	public void setPrecioMinimo(Double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	/**
	 * Gets the precio maximo.
	 * 
	 * @return the precio maximo
	 */
	public Double getPrecioMaximo() {
		return precioMaximo == null ? 0 : precioMaximo;
	}

	/**
	 * Sets the precio maximo.
	 * 
	 * @param precioMaximo
	 *            the new precio maximo
	 */
	public void setPrecioMaximo(Double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	/**
	 * Gets the volumen negociado.
	 * 
	 * @return the volumen negociado
	 */
	public Double getVolumenNegociado() {
		return volumenNegociado == null ? 0 : volumenNegociado;
	}

	/**
	 * Sets the volumen negociado.
	 * 
	 * @param volumenNegociado
	 *            the new volumen negociado
	 */
	public void setVolumenNegociado(Double volumenNegociado) {
		this.volumenNegociado = volumenNegociado;
	}

	/**
	 * Gets the volumen ultimo.
	 * 
	 * @return the volumen ultimo
	 */
	public Double getVolumenUltimo() {
		return volumenUltimo == null ? 0 : volumenUltimo;
	}

	/**
	 * Sets the volumen ultimo.
	 * 
	 * @param volumenUltimo
	 *            the new volumen ultimo
	 */
	public void setVolumenUltimo(Double volumenUltimo) {
		this.volumenUltimo = volumenUltimo;
	}

	/**
	 * Gets the volumen promedio.
	 * 
	 * @return the volumen promedio
	 */
	public Double getVolumenPromedio() {
		return volumenPromedio == null ? 0 : volumenPromedio;
	}

	/**
	 * Sets the volumen promedio.
	 * 
	 * @param volumenPromedio
	 *            the new volumen promedio
	 */
	public void setVolumenPromedio(Double volumenPromedio) {
		this.volumenPromedio = volumenPromedio;
	}

	/**
	 * Gets the volumen minimo.
	 * 
	 * @return the volumen minimo
	 */
	public Double getVolumenMinimo() {
		return volumenMinimo == null ? 0 : volumenMinimo;
	}

	/**
	 * Sets the volumen minimo.
	 * 
	 * @param volumenMinimo
	 *            the new volumen minimo
	 */
	public void setVolumenMinimo(Double volumenMinimo) {
		this.volumenMinimo = volumenMinimo;
	}

	/**
	 * Gets the volumen maximo.
	 * 
	 * @return the volumen maximo
	 */
	public Double getVolumenMaximo() {
		return volumenMaximo == null ? 0 : volumenMaximo;
	}

	/**
	 * Sets the volumen maximo.
	 * 
	 * @param volumenMaximo
	 *            the new volumen maximo
	 */
	public void setVolumenMaximo(Double volumenMaximo) {
		this.volumenMaximo = volumenMaximo;
	}

	/**
	 * Gets the num transacciones.
	 * 
	 * @return the num transacciones
	 */
	public Double getNumTransacciones() {
		return numTransacciones == null ? 0 : numTransacciones;
	}

	/**
	 * Sets the num transacciones.
	 * 
	 * @param numTransacciones
	 *            the new num transacciones
	 */
	public void setNumTransacciones(Double numTransacciones) {
		this.numTransacciones = numTransacciones;
	}

	/**
	 * Gets the variacion.
	 * 
	 * @return the variacion
	 */
	public Double getVariacion() {
		if (getPrecioApertura() != 0) {
			return ((getPrecioUltimo() / getPrecioApertura()) - 1) * 100;
		}
		return 0D;
	}

	/**
	 * Gets the trm.
	 * 
	 * @return the trm
	 */
	public Double getTrm() {
		return trm == null ? 0D : trm;
	}

	/**
	 * Sets the trm.
	 * 
	 * @param trm
	 *            the new trm
	 */
	public void setTrm(Double trm) {
		this.trm = trm;
	}

	/**
	 * Gets the tipo mercado.
	 * 
	 * @return the tipo mercado
	 */
	public String getTipoMercado() {
		return tipoMercado;
	}

	/**
	 * Sets the tipo mercado.
	 * 
	 * @param tipoMercado
	 *            the new tipo mercado
	 */
	public void setTipoMercado(String tipoMercado) {
		this.tipoMercado = tipoMercado;
	}

}
