package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.com.bvc.portal.mercados.servicio.IRentaFija;

/**
 * The Class RentaFijaOperacion.
 */
public class RentaFijaOperacion implements Serializable{

	private static final long serialVersionUID = 6468432456599601241L;

	/** The titulo codigo. */
	private String tituloCodigo;

	/** The titulo nombre. */
	private String tituloNombre;

	/** The nemo. */
	private String nemo;

	/** The emisor. */
	private String emisor;

	/** The precio sucio. */
	private double precioSucio;

	/** The tasa. */
	private float tasa;

	/** The tasa cupon. */
	private float tasaCupon;

	/** The cantidad. */
	private double cantidad;

	/** The volumen. */
	private double volumen;

	/** The tipo operacion. */
	private String tipoOperacion;

	/** The tipo plazo. */
	private String tipoPlazo;

	/** The fecha emision. */
	private Date fechaEmision;

	/** The modalidad pago. */
	private String modalidadPago;

	/** The fecha vencimiento. */
	private Date fechaVencimiento;

	/** The sesion negociacion. */
	private String sesionNegociacion;

	/** The tipo deuda. */
	private String tipoDeuda;

	/** The fecha operacion. */
	private Date fechaOperacion;

	/** The hor gra. */
	private String horGra;

	/** The mercado. */
	private String mercado;

	/** The Constant sdf. */
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * Gets the tipo operacion.
	 * 
	 * @return the tipo operacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 * 
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the precio sucio.
	 * 
	 * @return the precio sucio
	 */
	public double getPrecioSucio() {
		return precioSucio;
	}

	/**
	 * Sets the precio sucio.
	 * 
	 * @param precioSucio
	 *            the new precio sucio
	 */
	public void setPrecioSucio(double precioSucio) {
		this.precioSucio = precioSucio;
	}

	/**
	 * Gets the tasa.
	 * 
	 * @return the tasa
	 */
	public float getTasa() {
		return tasa;
	}

	/**
	 * Set the tasa.
	 * 
	 * @param tasa
	 */
	public void setTasa(float tasa) {
		this.tasa = tasa;
	}

	/**
	 * Sets the tasa ultima.
	 * 
	 * @param tasa
	 *            the new tasa ultima
	 */
	public void setTasaUltima(float tasa) {
		this.tasa = tasa;
	}

	/**
	 * Gets the tasa cupon.
	 * 
	 * @return the tasa cupon
	 */
	public float getTasaCupon() {
		return tasaCupon;
	}

	/**
	 * Sets the tasa cupon.
	 * 
	 * @param tasaCupon
	 *            the new tasa cupon
	 */
	public void setTasaCupon(float tasaCupon) {
		this.tasaCupon = tasaCupon;
	}

	/**
	 * Gets the cantidad.
	 * 
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 * 
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the volumen.
	 * 
	 * @return the volumen
	 */
	public double getVolumen() {
		return volumen;
	}

	/**
	 * Sets the volumen.
	 * 
	 * @param volumen
	 *            the new volumen
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	/**
	 * Gets the tipo plazo.
	 * 
	 * @return the tipo plazo
	 */
	public String getTipoPlazo() {
		return tipoPlazo;
	}

	/**
	 * Sets the tipo plazo.
	 * 
	 * @param tipoPlazo
	 *            the new tipo plazo
	 */
	public void setTipoPlazo(String tipoPlazo) {
		this.tipoPlazo = tipoPlazo;
	}

	/**
	 * Gets the fecha emision.
	 * 
	 * @return the fecha emision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * Sets the fecha emision.
	 * 
	 * @param fechaEmision
	 *            the new fecha emision
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * Gets the modalidad pago.
	 * 
	 * @return the modalidad pago
	 */
	public String getModalidadPago() {
		return modalidadPago;
	}

	/**
	 * Sets the modalidad pago.
	 * 
	 * @param modalidadPago
	 *            the new modalidad pago
	 */
	public void setModalidadPago(String modalidadPago) {
		this.modalidadPago = modalidadPago;
	}

	/**
	 * Gets the fecha vencimiento.
	 * 
	 * @return the fecha vencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 * 
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the sesion negociacion.
	 * 
	 * @return the sesion negociacion
	 */
	public String getSesionNegociacion() {
		return sesionNegociacion;
	}

	/**
	 * Sets the sesion negociacion.
	 * 
	 * @param sesionNegociacion
	 *            the new sesion negociacion
	 */
	public void setSesionNegociacion(String sesionNegociacion) {
		this.sesionNegociacion = sesionNegociacion;
	}

	/**
	 * Sets the tipo deuda.
	 * 
	 * @param tipoDeuda
	 *            the new tipo deuda
	 */
	public void setTipoDeuda(String tipoDeuda) {
		this.tipoDeuda = tipoDeuda;
	}

	/**
	 * Gets the tipo deuda.
	 * 
	 * @return the tipo deuda
	 */
	public String getTipoDeuda() {
		return tipoDeuda;
	}

	/**
	 * Checks if is registro.
	 * 
	 * @return true, if is registro
	 */
	public boolean isRegistro() {
		return IRentaFija.MERCADO_REGISTRO.equals(mercado);
	}

	/**
	 * Checks if is transaccional.
	 * 
	 * @return true, if is transaccional
	 */
	public boolean isTransaccional() {

		return IRentaFija.MERCADO_TRANSACCIONAL.equals(mercado);

	}

	/**
	 * Sets the nemo.
	 * 
	 * @param nemo
	 *            the new nemo
	 */
	public void setNemo(String nemo) {
		this.nemo = nemo;
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
	 * Sets the titulo nombre.
	 * 
	 * @param tituloNombre
	 *            the new titulo nombre
	 */
	public void setTituloNombre(String tituloNombre) {
		this.tituloNombre = tituloNombre;
	}

	/**
	 * Gets the titulo nombre.
	 * 
	 * @return the titulo nombre
	 */
	public String getTituloNombre() {
		return tituloNombre;
	}

	/**
	 * Sets the titulo codigo.
	 * 
	 * @param tituloCodigo
	 *            the new titulo codigo
	 */
	public void setTituloCodigo(String tituloCodigo) {
		this.tituloCodigo = tituloCodigo;
	}

	/**
	 * Gets the titulo codigo.
	 * 
	 * @return the titulo codigo
	 */
	public String getTituloCodigo() {
		return tituloCodigo;
	}

	/**
	 * Sets the emisor.
	 * 
	 * @param emisor
	 *            the new emisor
	 */
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	/**
	 * Gets the emisor.
	 * 
	 * @return the emisor
	 */
	public String getEmisor() {
		return emisor;
	}

	/**
	 * Sets the fecha operacion.
	 * 
	 * @param fechaOperacion
	 *            the new fecha operacion
	 */
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * Gets the fecha operacion.
	 * 
	 * @return the fecha operacion
	 */
	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Sets the hor gra.
	 * 
	 * @param horGra
	 *            the new hor gra
	 */
	public void setHorGra(String horGra) {
		this.horGra = horGra;
	}

	/**
	 * Gets the hor gra.
	 * 
	 * @return the hor gra
	 */
	public String getHorGra() {
		return horGra;
	}

	/**
	 * Gets the fecha emision format.
	 * 
	 * @return the fecha emision format
	 */
	public String getFechaEmisionFormat() {
		return (fechaEmision != null) ? sdf.format(fechaEmision) : "";
	}

	/**
	 * Gets the fecha vencimiento format.
	 * 
	 * @return the fecha vencimiento format
	 */
	public String getFechaVencimientoFormat() {
		return (fechaVencimiento != null) ? sdf.format(fechaVencimiento) : "";
	}

	/**
	 * Gets the fecha operacion format.
	 * 
	 * @return the fecha operacion format
	 */
	public String getFechaOperacionFormat() {
		return (fechaOperacion != null) ? sdf.format(fechaOperacion) : "";
	}

	/**
	 * Sets the mercado.
	 * 
	 * @param mercado
	 *            the new mercado
	 */
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	/**
	 * Gets the mercado.
	 * 
	 * @return the mercado
	 */
	public String getMercado() {
		return mercado;
	}

	public void setFechaEmisionS(String fechaEmisionS) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			fechaEmision = sdf.parse(fechaEmisionS);
		} catch (ParseException e) {
			fechaEmision = new Date();
		}
	}

	public void setFechaVencimientoS(String fechaVencimientoS) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			fechaVencimiento = sdf.parse(fechaVencimientoS);
		} catch (ParseException e) {
			fechaVencimiento = new Date();
		}
	}

	public void setFechaOperacionS(String fechaOperacionS) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd:HH:mm:ss.SSSSSS");
		try {
			fechaOperacion = sdf.parse(fechaOperacionS);
		} catch (ParseException e) {
			fechaOperacion = new Date();
		}
	}

	
}
