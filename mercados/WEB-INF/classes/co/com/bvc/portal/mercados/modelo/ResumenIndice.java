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

public class ResumenIndice implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7747872611619076479L;

	/** The fecha int. */
	private Integer fechaInt = 0;
	
	/** The indice. */
	private String indice = "";
	
	/** The nombre indice. */
	private String nombreIndice = "";
	
	/** The valor hoy. */
	private double valorHoy = 0;
	
	/** The valor ayer. */
	private double valorAyer = 0;
	
	/** The valor12 meses. */
	private double valor12Meses = 0;
	
	/** The variacion hoy. */
	private double variacionHoy = 0;
	
	/** The variacion abs. */
	private double variacionAbs = 0;
	
	/** The variacion anual. */
	private double variacionAnual = 0;
	
	/** The fecha. */
	private Calendar fecha = Calendar.getInstance();
	
	/** The codigo. */
	private String codigo = "";
	
	/** The mercado. */
	private String mercado = "";
	
	/** The value. */
	private String value = "";
	
	/** The clave. */
	private String clave = "";
	
	/** The tir. */
	private String tir = "";
	
	/** The duracion. */
	private String duracion = "";

	/**
	 * Gets the codigo.
	 * 
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 * 
	 * @param codigo the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Gets the codigo.
	 * 
	 * @return the codigo
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
	 * Gets the clave.
	 * 
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 * 
	 * @param codigo the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param codigo the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Gets the tir.
	 * 
	 * @return the tir
	 */
	public String getTir() {
		return tir;
	}

	/**
	 * Sets the tir.
	 * 
	 * @param codigo the new tir
	 */
	public void setTir(String tir) {
		this.tir = tir;
	}
	
	/**
	 * Gets the duracion.
	 * 
	 * @return the duracion
	 */
	public String getDuracion() {
		return duracion;
	}

	/**
	 * Sets the duracion.
	 * 
	 * @param codigo the new duracion
	 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
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
	 * Gets the variacion anual.
	 * 
	 * @return the variacion anual
	 */
	public double getVariacionAnual() {
		return variacionAnual;
	}

	/**
	 * Sets the variacion anual.
	 * 
	 * @param variacionAnual the new variacion anual
	 */
	public void setVariacionAnual(double variacionAnual) {
		this.variacionAnual = variacionAnual;
	}

	/**
	 * Gets the fecha.
	 * 
	 * @return the fecha
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 * 
	 * @param fecha the new fecha
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the fecha int.
	 * 
	 * @return the fecha int
	 */
	public Integer getFechaInt() {
		return fechaInt;
	}

	/**
	 * Sets the fecha int.
	 * 
	 * @param fechaInt the new fecha int
	 */
	public void setFechaInt(Integer fechaInt) {
		this.fechaInt = fechaInt;
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try{
			this.fecha.setTime(sdf.parse(fechaInt.toString()));
		}catch (Exception e) {
			this.fecha.setTime(new Date());
		}
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

	/**
	 * Gets the valor12 meses.
	 * 
	 * @return the valor12 meses
	 */
	public double getValor12Meses() {
		return valor12Meses;
	}

	/**
	 * Sets the valor12 meses.
	 * 
	 * @param valor12Meses the new valor12 meses
	 */
	public void setValor12Meses(double valor12Meses) {
		this.valor12Meses = valor12Meses;
	}

	/**
	 * Gets the variacion12 meses.
	 * 
	 * @return the variacion12 meses
	 */
	public double getVariacion12Meses(){
		double ret = 0;
		if (valor12Meses != 0){
			ret =  ((valorHoy/valor12Meses) -1) * 100;
		}
		return ret;
	}	
}
/**************************************************End of ResumenIndice.java***********************************/