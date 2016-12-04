package co.com.bvc.portal.mercados.servicio.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import co.com.bvc.portal.comun.servicio.IServicioUtil;
import co.com.bvc.portal.mercados.modelo.DivisasRegistroTO;
import co.com.bvc.portal.mercados.persistencia.IDivisasDao;
import co.com.bvc.portal.mercados.servicio.IDivisasRegistro;
import co.com.bvc.portal.comun.util.UtilFechas;

// TODO: Auto-generated Javadoc
/**
 * The Class DivisasRegistro.
 */
public class DivisasRegistro implements IDivisasRegistro {

	/** The divisas dao spring jdbc. */
	private IDivisasDao divisasDaoSpringJdbc;

	/** The servicio util. */
	private IServicioUtil servicioUtil;

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IDivisasRegistro#obtenerDetallePorDivisaYMercado(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<DivisasRegistroTO> obtenerDetallePorDivisaYMercado(String codigo, String mercado, String fecha) {
		try {
			boolean hoy = getHoy().equals(fecha);
			String[] fechas = getRango(fecha, hoy);
			return divisasDaoSpringJdbc.obtenerDetallePorDivisaYMercado(fechas[0], fechas[1], codigo, mercado, getHoy().equals(fecha));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DivisasRegistroTO>();
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IDivisasRegistro#obtenerResumenDivisaYMercadoPorFecha(java.lang.String)
	 */
	public List<DivisasRegistroTO> obtenerResumenDivisaYMercadoPorFecha(String fecha) {
		try {
			boolean hoy = getHoy().equals(fecha);
			String[] fechas = getRango(fecha, hoy);
			return divisasDaoSpringJdbc.obtenerResumenDivisaYMercadoPorFecha(fechas[0], fechas[1], getHoy().equals(fecha));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DivisasRegistroTO>();
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IDivisas#obtenerResumenMercadoPorFecha(java.lang.String)
	 */
	public Double obtenerResumenMercadoPorFecha(String fecha) {
		try {
			UtilFechas.getFechasComparador(fecha);
			if (getHoy().equals(fecha)) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = servicioUtil.getUltimoDiaHabilBursatil(new GregorianCalendar());
				String fechaFin = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());
				return divisasDaoSpringJdbc.obtenerResumenMercadoPorFecha(sdf.format(cal.getTime()) + ":00:00:000000", fechaFin, true);
			}
			return divisasDaoSpringJdbc.obtenerResumenMercadoPorFecha(fecha + ":00:00:000000", fecha + ":23:59:999999", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0D;
	}

	/**
	 * Gets the hoy.
	 * 
	 * @return the hoy
	 */
	private String getHoy() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	/**
	 * Gets the rango.
	 * 
	 * @param fecha the fecha
	 * @param hoy the hoy
	 * 
	 * @return the rango
	 */
	private String[] getRango(String fecha, boolean hoy) {
		String[] ret = new String[2];
		if (hoy) {
			ret[0] = fecha + ":00:00:00";
			ret[1] = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());
		} else {
			ret = UtilFechas.getFechasComparador(fecha);
		}
		return ret;
	}

	/**
	 * Gets the divisas dao spring jdbc.
	 * 
	 * @return the divisas dao spring jdbc
	 */
	public IDivisasDao getDivisasDaoSpringJdbc() {
		return divisasDaoSpringJdbc;
	}

	/**
	 * Sets the divisas dao spring jdbc.
	 * 
	 * @param divisasDaoSpringJdbc the new divisas dao spring jdbc
	 */
	public void setDivisasDaoSpringJdbc(IDivisasDao divisasDaoSpringJdbc) {
		this.divisasDaoSpringJdbc = divisasDaoSpringJdbc;
	}

	/**
	 * Gets the servicio util.
	 * 
	 * @return the servicio util
	 */
	public IServicioUtil getServicioUtil() {
		return servicioUtil;
	}

	/**
	 * Sets the servicio util.
	 * 
	 * @param servicioUtil the new servicio util
	 */
	public void setServicioUtil(IServicioUtil servicioUtil) {
		this.servicioUtil = servicioUtil;
	}

}
