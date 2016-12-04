package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * The Class OperacionDiaAcciones.
 */
@SuppressWarnings("serial")
public class OperacionDiaAccionesMGC implements IEntidadPortal {

	/** The fecha hora. */
	private Calendar fechaHora = Calendar.getInstance();
	
	/** The nemotecnico. */
	private String nemotecnico = "";
	
	/** The precio. */
	private double precio = 0;
	
	/** The cantidad. */
	private double cantidad = 0;
	
	/** The volumen. */
	private double volumen = 0;
	
	/** The tipo operacion. */
	private String tipoOperacion = "";
	
	/** The promotor liquidez. */
	private String promotorLiquidez = "";
	
	/** The creadormercado. */
	private String creadormercado = "";
	
	/** The fecha cumpliento. */
	private Date fechaCumpliento = Calendar.getInstance().getTime();
	
	/** The mercado. */
	private String mercado = "";
	
	/** The ind fija. */
	private String indFija = "";

	/** The volumen recompra. */
	private double volumenRecompra = 0;

	/**
	 * Sets the fecha hora string.
	 * 
	 * @param fechaHoraString the new fecha hora string
	 */
	public void setFechaHoraString(String fechaHoraString) {
		if (fechaHoraString != null && !"".equals(fechaHoraString)) {
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
			try {
				fechaHora = new GregorianCalendar();
				fechaHora.setTime(sdf.parse(fechaHoraString));
			} catch (Exception e) {
				fechaHora = new GregorianCalendar();
			}
		}
	}

	/**
	 * Sets the fecha cumpliento string.
	 * 
	 * @param fechaCumplientoString the new fecha cumpliento string
	 */
	public void setFechaCumplientoString(String fechaCumplientoString) {
		if (fechaCumplientoString != null && !"".equals(fechaCumplientoString)) {
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			try {
				fechaCumpliento = df.parse(fechaCumplientoString);
			} catch (ParseException e) {
				fechaCumpliento = (new GregorianCalendar()).getTime();
			}
		}
	}

	/**
	 * Gets the volumen recompra.
	 * 
	 * @return the volumen recompra
	 */
	public double getVolumenRecompra() {
		return volumenRecompra;
	}

	/**
	 * Sets the volumen recompra.
	 * 
	 * @param volumenRecompra the new volumen recompra
	 */
	public void setVolumenRecompra(double volumenRecompra) {
		this.volumenRecompra = volumenRecompra;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#getId()
	 */
	public Serializable getId() {
		return this.nemotecnico;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#setId(java.io.Serializable)
	 */
	public void setId(Serializable id) {
		this.nemotecnico = (String) id;
	}

	/**
	 * Gets the fecha hora.
	 * 
	 * @return the fecha hora
	 */
	public Calendar getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 * 
	 * @param fechaHora the new fecha hora
	 */
	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the nemotecnico.
	 * 
	 * @return the nemotecnico
	 */
	public String getNemotecnico() {
		return nemotecnico;
	}

	/**
	 * Sets the nemotecnico.
	 * 
	 * @param nemotecnico the new nemotecnico
	 */
	public void setNemotecnico(String nemotecnico) {
		this.nemotecnico = nemotecnico;
	}

	/**
	 * Gets the precio.
	 * 
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Sets the precio.
	 * 
	 * @param precio the new precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
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
	 * @param cantidad the new cantidad
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
	 * @param volumen the new volumen
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

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
	 * @param tipoOperacion the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Gets the promotor liquidez.
	 * 
	 * @return the promotor liquidez
	 */
	public String getPromotorLiquidez() {
		return promotorLiquidez;
	}

	/**
	 * Sets the promotor liquidez.
	 * 
	 * @param promotorLiquidez the new promotor liquidez
	 */
	public void setPromotorLiquidez(String promotorLiquidez) {
		this.promotorLiquidez = promotorLiquidez;
	}

	/**
	 * Gets the creadormercado.
	 * 
	 * @return the creadormercado
	 */
	public String getCreadormercado() {
		return creadormercado;
	}

	/**
	 * Sets the creadormercado.
	 * 
	 * @param creadormercado the new creadormercado
	 */
	public void setCreadormercado(String creadormercado) {
		this.creadormercado = creadormercado;
	}

	/**
	 * Gets the fecha cumpliento.
	 * 
	 * @return the fecha cumpliento
	 */
	public Date getFechaCumpliento() {
		return fechaCumpliento;
	}

	/**
	 * Sets the fecha cumpliento.
	 * 
	 * @param fechaCumpliento the new fecha cumpliento
	 */
	public void setFechaCumpliento(Date fechaCumpliento) {
		this.fechaCumpliento = fechaCumpliento;
	}

	/**
	 * Gets the mercado.
	 * 
	 * @return the mercado
	 */
	public String getMercado() {
		return mercado;
	}

	/**
	 * Sets the mercado.
	 * 
	 * @param mercado the new mercado
	 */
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	/**
	 * Gets the ind fija.
	 * 
	 * @return the ind fija
	 */
	public String getIndFija() {
		return indFija;
	}

	/**
	 * Sets the ind fija.
	 * 
	 * @param indFija the new ind fija
	 */
	public void setIndFija(String indFija) {
		this.indFija = indFija;
	}

}
