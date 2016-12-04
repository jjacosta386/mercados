package co.com.bvc.portal.mercados.servicio.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import co.com.bvc.portal.mercados.modelo.DivisasSetFxTO;
import co.com.bvc.portal.mercados.persistencia.IDivisasDao;
import co.com.bvc.portal.mercados.servicio.IDivisasSetFx;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilFechas;

// TODO: Auto-generated Javadoc
/**
 * The Class DivisasSetFx.
 */
public class DivisasSetFx implements IDivisasSetFx {

	/** The divisas dao spring jdbc. */
	private IDivisasDao divisasDaoSpringJdbc;
	
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IDivisas#obtenerResumenMercadoPorFecha
	 * (java.lang.String)
	 */
	public Double obtenerResumenMercadoPorFecha(String fecha) {
		DivisasSetFxTO divi = getDetalleDia(fecha);
		return divi.getVolumenNegociado() == null ? 0D : divi
				.getVolumenNegociado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IDivisasSetFx#getDetalleDia(java.
	 * lang.String)
	 */
	public DivisasSetFxTO getDetalleDia(String fecha) {
				
		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_30SECS);
        String queryKey = "CALCULO_DOLAR_DIARIO." + fecha.replace("-","");
				
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm");
		if (UtilFechas.getHoy("yyyy-MM-dd").equals(fecha)){
			try{
				fecha = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());
			}catch (Exception e) {
				e.printStackTrace();
				fecha = sdf.format((new GregorianCalendar()).getTime());
			}
		}else{
			fecha  += ":23:59";
		}
		try{
			
			Calendar cal = new GregorianCalendar();
			cal.setTime(sdf.parse(fecha));
			boolean festivoAmericano = divisasDaoSpringJdbc.isFestivoAmericano(cal);
			
			queryKey+="."+festivoAmericano;
			
			DivisasSetFxTO ret = (DivisasSetFxTO)cache.getObject(queryKey);
			
			if (ret == null) {
				synchronized (this) {
					ret = (DivisasSetFxTO) cache.getObject(queryKey);
					if (ret == null) {
						log.debug("Procesando Datos obtenerEstadoDia : " + fecha);
						ret = divisasDaoSpringJdbc.obtenerEstadoDia(fecha, festivoAmericano? CODIGO_MERCADO_NEXT_DAY: CODIGO_MERCADO_SPOT);						
						cache.putObject(queryKey, ret);
					}
				}
			} else {
				log.debug("Cargando desde Cache obtenerEstadoDia : " + fecha);
			}			
			
			ret.setTipoMercado(festivoAmericano? "Next Day": "SPOT");
			return ret;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

}
