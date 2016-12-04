package co.com.bvc.portal.mercados.vista.form;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author BVC
 * @contributor Andres_Leon
 * @description The Class FormularioIndices. Formulario que se utiliza para definir los datos
 * de busqueda sobre indices
 */

public class FormularioIndices implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1417055544599764719L;

	/** The dia. */
	private String dia;

	/** The mes. */
	private String mes;

	/** The anio. */
	private String anio;

	/** The codigo indice. */
	private String codigoIndice;
	
	/** The codigo indice. */
	private String mercadoIn;

	/**
	 * Instantiates a new formulario indices.
	 */
	public FormularioIndices() {
		Calendar cal = new GregorianCalendar();
		dia = completarTamanio(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)),
				2);
		mes = completarTamanio(String.valueOf(cal.get(Calendar.MONTH) + 1), 2);
		anio = completarTamanio(String.valueOf(cal.get(Calendar.YEAR)), 4);
	}

	/**
	 * Gets the time requerido.
	 * 
	 * @return the time requerido
	 */
	public String getTimeRequerido() {
		if (anio != null && mes != null && dia != null) {
			if (anio.trim().length() > 0 && mes.trim().length() > 0
					&& dia.trim().length() > 0) {
				return anio + mes + dia;
			}
		}
		return null;
	}

	/**
	 * Sets the time requerido.
	 * 
	 * @param fecha
	 *            the new time requerido
	 */
	public void setTimeRequerido(String fecha) {
		dia = fecha.substring(6);
		mes = fecha.substring(4, 6);
		anio = fecha.substring(0, 4);
	}

	/**
	 * Gets the dia.
	 * 
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * Sets the dia.
	 * 
	 * @param dia
	 *            the new dia
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * Gets the mes.
	 * 
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 * 
	 * @param mes
	 *            the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the anio.
	 * 
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 * 
	 * @param anio
	 *            the new anio
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the codigo indice.
	 * 
	 * @return the codigo indice
	 */
	public String getCodigoIndice() {
		return codigoIndice;
	}

	/**
	 * Sets the codigo indice.
	 * 
	 * @param codigoIndice
	 *            the new codigo indice
	 */
	public void setCodigoIndice(String codigoIndice) {
		this.codigoIndice = codigoIndice;
	}
	
	/**
	 * Gets the mercado indice.
	 * 
	 * @return the mercado indice
	 */
	public String getMercadoIn() {
		return mercadoIn;
	}

	/**
	 * Sets the mercado indice.
	 * 
	 * @param mercadoIndice
	 *            the new mercado indice
	 */
	public void setMercadoIn(String mercadoIn) {
		this.mercadoIn = mercadoIn;
	}

	/**
	 * Completar tamanio.
	 * 
	 * @param valor
	 *            the valor
	 * @param tamanio
	 *            the tamanio
	 * 
	 * @return the string
	 */
	private String completarTamanio(String valor, int tamanio) {
		if (valor.length() < tamanio) {
			valor = completarTamanio("0" + valor, tamanio);
		}
		return valor;
	}

}
/****************************************************End of FormularioIndices.java**********************************/