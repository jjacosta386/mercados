package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumenAccion.
 */
@SuppressWarnings("serial")
public class CotizaPaisMGC implements IEntidadPortal {

	/** The empresa. */
	private String empresa = "";
	
	/** The volumen. */
	private Double volumen = 0d;
	
	/** The ultimo precio. */
	private Double precioUltimo = 0d;
	
	/** The variacion. */
	private Double variacion = 0d;
	
	/** The variacionPorcentual. */
	private Double variacionPorcentual = 0d;
	
	/** The cantidad. */
	private Double cantidad = 0d;
	
	/** The accion. */
	private String accion = "";
	
	/** The precioMaximo. */
	private Double precioMaximo = 0d;
	
	/** The precioMinimo. */
	private Double precioMinimo = 0d;
	
	/** The precioMedio. */
	private Double precioMedio = 0d;
	
	/** The precioAnterior. */
	private Double precioAnterior = 0d;
	
	/** The idEmisor. */
	private String idEmisor = "";
	
	/** The cotizacion. */
	private String cotizacion = "";
	
	/** The url_emisor. */
	private String url_emisor = "";
	
	/** The url_bolsa. */
	private String url_bolsa = "";
	
	

	public String getUrl_bolsa() {
		return url_bolsa;
	}

	public void setUrl_bolsa(String url_bolsa) {
		this.url_bolsa = url_bolsa;
	}

	public String getUrl_emisor() {
		return url_emisor;
	}

	public void setUrl_emisor(String url_emisor) {
		this.url_emisor = url_emisor;
	}

	public String getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	public String getIdEmisor() {
		return idEmisor;
	}

	public void setIdEmisor(String idEmisor) {
		this.idEmisor = idEmisor;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

	public Double getPrecioUltimo() {
		return precioUltimo;
	}

	public void setPrecioUltimo(Double precioUltimo) {
		this.precioUltimo = precioUltimo;
	}

	public Double getVariacion() {
		return variacion;
	}

	public void setVariacion(Double variacion) {
		this.variacion = variacion;
	}

	public Double getVariacionPorcentual() {
		return variacionPorcentual;
	}

	public void setVariacionPorcentual(Double variacionPorcentual) {
		this.variacionPorcentual = variacionPorcentual;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Double getPrecioMaximo() {
		return precioMaximo;
	}

	public void setPrecioMaximo(Double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	public Double getPrecioMinimo() {
		return precioMinimo;
	}

	public void setPrecioMinimo(Double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	public Double getPrecioMedio() {
		return precioMedio;
	}

	public void setPrecioMedio(Double precioMedio) {
		this.precioMedio = precioMedio;
	}

	public Double getPrecioAnterior() {
		return precioAnterior;
	}

	public void setPrecioAnterior(Double precioAnterior) {
		this.precioAnterior = precioAnterior;
	}

	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(Serializable arg0) {
		// TODO Auto-generated method stub
		
	}	
}
