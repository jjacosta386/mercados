package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import co.com.bvc.portal.mercados.servicio.IAccionesMGC;

@SuppressWarnings("serial")
public class AccionesMGCBusquedaForm implements Serializable {
	
	/** The dia fecha. */
	private String diaFecha = "";

	/** The mes fecha. */
	private String mesFecha = "";

	/** The anio fecha. */
	private String anioFecha = "";

	/** The tipo mercado. */
	private Integer tipoMercado = IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC;

	/** The nemo. */
	private String nemo = "";
	
	/** The Tipo Instrumento. */
	private Integer tipoInstrumento;

	/**
	 * Gets the tipo instrumento.
	 * 
	 * @return the tipoInstrumento
	 */
	public Integer getTipoInstrumento() {
		return tipoInstrumento;
	}

	/**
	 * Sets the tipo instrumento.
	 * 
	 * @param tipoInstrumento
	 */
	public void setTipoInstrumento(Integer tipoInstrumento) {
		this.tipoInstrumento = tipoInstrumento;
	}
	
	/** The patrocinador. */
	private String patrocinador = "";
	
	/** The nombreEmisor. */
	private String nombreEmisor = "";
	
	/** The cotizacion. */
	private String cotizacion = "";
	
	/** The Pais. */
	private String pais = "";
	
	/** The accion. */
	private String accion = "";
	
	/** The empresa. */
	private String empresa = "";

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Instantiates a new acciones busqueda form.
	 */
	public AccionesMGCBusquedaForm(){
		Calendar cal = new GregorianCalendar();
		diaFecha = completarCadena("" + cal.get(Calendar.DAY_OF_MONTH), 2);
		mesFecha = completarCadena("" + (cal.get(Calendar.MONTH) + 1), 2);
		anioFecha = completarCadena("" + cal.get(Calendar.YEAR), 4);
	}
	
	/**
	 * Gets the dia fecha.
	 * 
	 * @return the dia fecha
	 */
	public String getDiaFecha() {
		return diaFecha;
	}

	/**
	 * Sets the dia fecha.
	 * 
	 * @param diaFecha the new dia fecha
	 */
	public void setDiaFecha(String diaFecha) {
		this.diaFecha = diaFecha;
	}

	/**
	 * Gets the mes fecha.
	 * 
	 * @return the mes fecha
	 */
	public String getMesFecha() {
		return mesFecha;
	}

	/**
	 * Sets the mes fecha.
	 * 
	 * @param mesFecha the new mes fecha
	 */
	public void setMesFecha(String mesFecha) {
		this.mesFecha = mesFecha;
	}

	/**
	 * Gets the anio fecha.
	 * 
	 * @return the anio fecha
	 */
	public String getAnioFecha() {
		return anioFecha;
	}

	/**
	 * Sets the anio fecha.
	 * 
	 * @param anioFecha the new anio fecha
	 */
	public void setAnioFecha(String anioFecha) {
		this.anioFecha = anioFecha;
	}

	/**
	 * Gets the tipo mercado.
	 * 
	 * @return the tipo mercado
	 */
	public Integer getTipoMercado() {
		return tipoMercado;
	}

	/**
	 * Sets the tipo mercado.
	 * 
	 * @param tipoMercado the new tipo mercado
	 */
	public void setTipoMercado(Integer tipoMercado) {
		this.tipoMercado = tipoMercado;
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
	 * Sets the nemo.
	 * 
	 * @param nemo the new nemo
	 */
	public void setNemo(String nemo) {
		this.nemo = nemo;
	}

	/**
	 * Completar cadena.
	 * 
	 * @param valor the valor
	 * @param tamanio the tamanio
	 * 
	 * @return the string
	 */
	private String completarCadena(String valor, int tamanio){
		if (valor.length() < tamanio){
			valor = completarCadena("0" + valor, tamanio);
		}
		return valor;
	}

	/**
	 * Gets the fecha requerida.
	 * 
	 * @return the fecha requerida
	 */
	public String getFechaRequerida(){
		if (anioFecha != null && mesFecha != null && diaFecha != null && !anioFecha.equalsIgnoreCase("-") && !mesFecha.equalsIgnoreCase("-") && !anioFecha.equalsIgnoreCase("-"))
			return anioFecha + "-" + mesFecha + "-" + diaFecha;
		else
			return "";
	}
	
	/**
	 * Gets the patrocinador.
	 * 
	 * @return the patrocinador
	 */
	public String getPatrocinador() {
		return patrocinador;
	}

	/**
	 * Sets the patrocinador.
	 * 
	 * @param nemo the new patrocinador
	 */
	public void setPatrocinador(String patrocinador) {
		this.patrocinador = patrocinador;
	}
	
	/**
	 * Gets the nombreEmisor.
	 * 
	 * @return the nombreEmisor
	 */
	public String getNombreEmisor() {
		return nombreEmisor;
	}

	/**
	 * Sets the nombreEmisor.
	 * 
	 * @param nemo the new nombreEmisor
	 */
	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}
	
	/**
	 * Gets the cotizacion.
	 * 
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 * 
	 * @param nemo the new cotizacion
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	/**
	 * Gets the pais.
	 * 
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Sets the pais.
	 * 
	 * @param nemo the new pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

}
