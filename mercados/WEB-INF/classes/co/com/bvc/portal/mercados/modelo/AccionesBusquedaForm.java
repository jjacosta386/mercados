package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import co.com.bvc.portal.mercados.servicio.IAcciones;

// TODO: Auto-generated Javadoc
/**
 * The Class AccionesBusquedaForm.
 */
@SuppressWarnings("serial")
public class AccionesBusquedaForm implements Serializable {

	/** The dia fecha. */
	private String diaFecha = "";

	/** The mes fecha. */
	private String mesFecha = "";

	/** The anio fecha. */
	private String anioFecha = "";

	/** The tipo mercado. */
	private Integer tipoMercado = IAcciones.TIPO_MERCADO_COMPRAVENTAS;

	/** The nemo. */
	private String nemo = "";

	/**
	 * Instantiates a new acciones busqueda form.
	 */
	public AccionesBusquedaForm(){
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
		if (anioFecha != null && mesFecha != null && diaFecha != null){
			return anioFecha + "-" + mesFecha + "-" + diaFecha;
		}
		return null;
	}
}
