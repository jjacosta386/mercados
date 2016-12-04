package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

import co.com.bvc.portal.mercados.servicio.IRentaFija;
import co.com.bvc.portal.mercados.servicio.imp.RentaFija;

// TODO: Auto-generated Javadoc
/**
 * The Class RentaFijaBusqueda.
 */
@SuppressWarnings("serial")
public class RentaFijaBusqueda implements Serializable {

	/** The dia fecha. */
	private String diaFecha = "";

	/** The mes fecha. */
	private String mesFecha = "";

	/** The anio fecha. */
	private String anioFecha = "";

	/** The tipo mercado. */
	private String tipoMercado = IRentaFija.MERCADO_TODOS;

	/** The tipo sesion. */
	private String tipoSesion = IRentaFija.SESION_ALL;

	/** The tipo deuda. */
	private String tipoDeuda = IRentaFija.DEUDA_ALL;

	/** The tipo operacion. */
	private String tipoOperacion = IRentaFija.ALL_OPE;

	/** The nemo. */
	private String nemo = "";

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
	 * @param diaFecha
	 *            the new dia fecha
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
	 * @param mesFecha
	 *            the new mes fecha
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
	 * @param anioFecha
	 *            the new anio fecha
	 */
	public void setAnioFecha(String anioFecha) {
		this.anioFecha = anioFecha;
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

	/**
	 * Gets the tipo sesion.
	 * 
	 * @return the tipo sesion
	 */
	public String getTipoSesion() {
		return tipoSesion;
	}

	/**
	 * Sets the tipo sesion.
	 * 
	 * @param tipoSesion
	 *            the new tipo sesion
	 */
	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
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
	 * Sets the tipo deuda.
	 * 
	 * @param tipoDeuda
	 *            the new tipo deuda
	 */
	public void setTipoDeuda(String tipoDeuda) {
		this.tipoDeuda = tipoDeuda;
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
	 * @param tipoOperacion
	 *            the new tipo operacion
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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
	 * Gets the params.
	 * 
	 * @return the params
	 */
	public String getParams() {

		if (this.tipoDeuda.equals("")) {
			this.tipoDeuda = RentaFija.DEUDA_ALL;
		}
		if (this.tipoOperacion.equals("")) {
			this.tipoOperacion = IRentaFija.ALL_OPE;
		}

		String params = "";

		if (!this.anioFecha.equals("") && !this.mesFecha.equals("")
				&& !this.diaFecha.equals("")) {
			params += "&fechaIni=" + this.anioFecha + "-" + this.mesFecha + "-"
					+ this.diaFecha;
		}

		params += "&deuda=" + this.tipoDeuda;
		params += "&ope=" + this.tipoOperacion;

		String sesionParam = "&sesion=";

		if (this.tipoSesion.equals("")) {
			// if tipoSesion esta vacio entonces el tipo Mercado tiene validez
			sesionParam += this.getTipoMercado().equals("") ? RentaFija.SESION_ALL
					: this.getTipoMercado();
		} else {
			sesionParam += this.tipoSesion;
		}
		params += "&tipoMercado="
				+ (this.getTipoMercado() == null
						|| "".equals(this.getTipoMercado()) ? IRentaFija.MERCADO_TODOS
						: this.getTipoMercado());
		return params + sesionParam;

	}

}
