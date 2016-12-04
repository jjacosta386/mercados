/**
 * 
 */
package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

import co.com.bvc.portal.comun.util.UtilFechas;

// TODO: Auto-generated Javadoc
/**
 * Clase que define los datos de busqueda sobre los derivados.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class BusquedaDerivados implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The nemo. */
	private String nemo = "";

	/** The tipo contrato. */
	private String tipoContrato = "";

	/** The tipo opcion. */
	private String tipoOpcion = "";

	/** The contrato. */
	private String contrato = "";

	/** The dia fecha. */
	private String diaFecha = "";

	/** The mes fecha. */
	private String mesFecha = "";

	/** The anio fecha. */
	private String anioFecha = "";

	/** The dia fecha. */
	private String diaFechaFin = "";

	/** The mes fecha. */
	private String mesFechaFin = "";

	/** The anio fecha. */
	private String anioFechaFin = "";

	/**
	 * determina si no se puso ningún filtro en el buscador.
	 * 
	 * @param busquedaDerivados
	 *            Objeto que representa el buscador
	 * 
	 * @return true, if checks if is busqueda vacia
	 * 
	 *         true si no se uso ningún filtro en el buscador, false en caso
	 *         contrario
	 */
	public static boolean isBusquedaVacia(BusquedaDerivados busquedaDerivados) {
		if (busquedaDerivados == null)
			return true;
		if (busquedaDerivados.getTipoContrato().equals("")
				&& busquedaDerivados.getTipoOpcion().equals("")
				&& busquedaDerivados.getContrato().equals("")
				&& (busquedaDerivados.getDiaFecha().equals("")
						|| busquedaDerivados.getMesFecha().equals("")
						|| busquedaDerivados.getAnioFecha().equals("") || UtilFechas
						.getFechaConsultaUltimoDia(busquedaDerivados
								.getDiaFecha()
								+ "-"
								+ busquedaDerivados.getMesFecha()
								+ "-"
								+ busquedaDerivados.getAnioFecha())))
			return true;
        	return false;
	}

	/**
	 * Checks if is fecha vacia.
	 * 
	 * @param busquedaDerivados
	 *            the busqueda derivados
	 * 
	 * @return true, if is fecha vacia
	 */
	public static boolean isFechaVacia(BusquedaDerivados busquedaDerivados) {
		if (busquedaDerivados == null)
			return true;
		if (busquedaDerivados.getDiaFecha().equals("")
				|| busquedaDerivados.getMesFecha().equals("")
				|| busquedaDerivados.getAnioFecha().equals(""))
			return true;
		return false;
	}

	/**
	 * Checks if is fecha hoy.
	 * 
	 * @param busquedaDerivados
	 *            the busqueda derivados
	 * 
	 * @return true, if is fecha hoy
	 */
	public static boolean isFechaHoy(BusquedaDerivados busquedaDerivados) {
		if (busquedaDerivados == null)
			return true;
		if (StringUtils.isNotEmpty(busquedaDerivados.anioFecha)
				&& StringUtils.isNotEmpty(busquedaDerivados.mesFecha)
				&& StringUtils.isNotEmpty(busquedaDerivados.diaFecha)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String fechaHoy = busquedaDerivados.getDiaFecha() + "-"
					+ busquedaDerivados.getMesFecha() + "-"
					+ busquedaDerivados.getAnioFecha();
			if (fechaHoy.equalsIgnoreCase(sdf.format(new GregorianCalendar()
					.getTime())))
				return true;
		}
		return false;
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
	 * @param nemo
	 *            the nemo to set
	 */
	public void setNemo(String nemo) {
		this.nemo = nemo;
	}

	/**
	 * Gets the tipo contrato.
	 * 
	 * @return the tipoContrato
	 */
	public String getTipoContrato() {
		return tipoContrato;
	}

	/**
	 * Sets the tipo contrato.
	 * 
	 * @param tipoContrato
	 *            the tipoContrato to set
	 */
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	/**
	 * Gets the tipo opcion.
	 * 
	 * @return the tipoOpcion
	 */
	public String getTipoOpcion() {
		return tipoOpcion;
	}

	/**
	 * Sets the tipo opcion.
	 * 
	 * @param tipoOpcion
	 *            the tipoOpcion to set
	 */
	public void setTipoOpcion(String tipoOpcion) {
		this.tipoOpcion = tipoOpcion;
	}

	/**
	 * Gets the contrato.
	 * 
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}

	/**
	 * Sets the contrato.
	 * 
	 * @param contrato
	 *            the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	/**
	 * Gets the dia fecha.
	 * 
	 * @return the diaFecha
	 */
	public String getDiaFecha() {
		return diaFecha;
	}

	public String getDiaFechaFin() {
		return diaFechaFin;
	}
	/**
	 * Sets the dia fecha.
	 * 
	 * @param diaFecha
	 *            the diaFecha to set
	 */
	public void setDiaFecha(String diaFecha) {
		this.diaFecha = diaFecha;
	}

	public void setDiaFechaFin(String diaFechaFin) {
		this.diaFechaFin = diaFechaFin;
	}

	/**
	 * Gets the mes fecha.
	 * 
	 * @return the mesFecha
	 */
	public String getMesFecha() {
		return mesFecha;
	}

	public String getMesFechaFin() {
		return mesFechaFin;
	}
	
	/**
	 * Sets the mes fecha.
	 * 
	 * @param mesFecha
	 *            the mesFecha to set
	 */
	public void setMesFecha(String mesFecha) {
		this.mesFecha = mesFecha;
	}

	public void setMesFechaFin(String mesFechaFin) {
		this.mesFechaFin = mesFechaFin;
	}
	
	/**
	 * Gets the anio fecha.
	 * 
	 * @return the anioFecha
	 */
	public String getAnioFecha() {
		return anioFecha;
	}

	public String getAnioFechaFin() {
		return anioFechaFin;
	}
	
	/**
	 * Sets the anio fecha.
	 * 
	 * @param anioFecha
	 *            the anioFecha to set
	 */
	public void setAnioFecha(String anioFecha) {
		this.anioFecha = anioFecha;
	}
	
	public void setAnioFechaFin(String anioFechaFin) {
		this.anioFechaFin = anioFechaFin;
	}	

}