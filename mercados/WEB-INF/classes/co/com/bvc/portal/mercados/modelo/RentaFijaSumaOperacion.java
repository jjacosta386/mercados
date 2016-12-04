package co.com.bvc.portal.mercados.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class RentaFijaSumaOperacion.
 */
public class RentaFijaSumaOperacion {
	
	/** The nemo. */
	private String nemo;
	
	/** The emisor. */
	private String emisor;
	
	/** The cantidad. */
	private Double cantidad = 0d;
	
	/** The volumen. */
	private Double volumen = 0d;
	
	/** The ultimo precio sucio. */
	private Double ultimoPrecioSucio = 0d;
	
	/** The ultima tasa. */
	private Float ultimaTasa = 0f;
	
	/** The fecha ultima operacion. */
	private Date fechaUltimaOperacion;
	
	/** The sdf. */
	//private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss.SSSSSS");
	
	/**
	 * Sets the nemo.
	 * 
	 * @param nemo the new nemo
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
	 * Sets the emisor.
	 * 
	 * @param emisor the new emisor
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
	 * Sets the cantidad.
	 * 
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
	 * Gets the cantidad.
	 * 
	 * @return the cantidad
	 */
	public Double getCantidad() {
		return cantidad;
	}
	
	/**
	 * Sets the volumen.
	 * 
	 * @param volumen the new volumen
	 */
	public void setVolumen(Double volumen) {
		this.volumen = volumen;
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
	 * Sets the ultimo precio sucio.
	 * 
	 * @param ultimoPrecioSucio the new ultimo precio sucio
	 */
	public void setUltimoPrecioSucio(Double ultimoPrecioSucio) {
		this.ultimoPrecioSucio = ultimoPrecioSucio;
	}
	
	/**
	 * Gets the ultimo precio sucio.
	 * 
	 * @return the ultimo precio sucio
	 */
	public Double getUltimoPrecioSucio() {
		return ultimoPrecioSucio;
	}
	
	/**
	 * Sets the ultima tasa.
	 * 
	 * @param ultimaTasa the new ultima tasa
	 */
	public void setUltimaTasa(Float ultimaTasa) {
		this.ultimaTasa = ultimaTasa;
	}
	
	/**
	 * Gets the ultima tasa.
	 * 
	 * @return the ultima tasa
	 */
	public Float getUltimaTasa() {
		return ultimaTasa;
	}
	
	/**
	 * Sets the fecha ultima operacion.
	 * 
	 * @param fechaUltimaOperacion the new fecha ultima operacion
	 */
	public void setFechaUltimaOperacion(Date fechaUltimaOperacion) {
		this.fechaUltimaOperacion = fechaUltimaOperacion;
	}
	
	/**
	 * Gets the fecha ultima operacion.
	 * 
	 * @return the fecha ultima operacion
	 */
	public Date getFechaUltimaOperacion() {
		return fechaUltimaOperacion;
	}

	/**
	 * Gets the fecha ultima operacion format.
	 * 
	 * @return the fecha ultima operacion format
	 */
	public String getFechaUltimaOperacionFormat(){
		SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return (fechaUltimaOperacion != null)? _sdf.format(fechaUltimaOperacion): ""; 
	}

	public void setFechaString(String fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm");
		try{
			fechaUltimaOperacion = sdf.parse(fecha);
		}catch(Exception ex){
			fechaUltimaOperacion = new Date();
		}
	}
	
}
