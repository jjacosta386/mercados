package co.com.bvc.portal.mercados.persistencia.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import co.com.bvc.portal.comun.persistencia.jdbc.JDBCDaoImp;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.IConstantesComunConsultasNombradas;
import co.com.bvc.portal.comun.util.UtilDto;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.CierreGeneral;
import co.com.bvc.portal.mercados.modelo.DivisasRegistroTO;
import co.com.bvc.portal.mercados.modelo.DivisasSetFxTO;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.persistencia.IDivisasDao;
import co.com.bvc.portal.mercados.servicio.IDivisasSetFx;
import co.com.bvc.portal.mercados.util.IConstantesConsultasDivisas;

// TODO: Auto-generated Javadoc
/**
 * The Class DivisasDaoSpringJdbc.
 */
@SuppressWarnings("unchecked")
public class DivisasDaoSpringJdbc extends JDBCDaoImp implements IDivisasDao {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDivisasDao#
	 * obtenerDetallePorDivisaYMercado(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, boolean)
	 */
	public List<DivisasRegistroTO> obtenerDetallePorDivisaYMercado(
			String fechaIni, String fechaFin, String codigo, String mercado,
			boolean hoy) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasDivisas.DETALLE_REGISTRO_POR_DIVISA_MERCADO_FECHA,
				new String[] { IConstantesConsultasDivisas.SUFIJO_TABLA,
						IConstantesConsultasDivisas.NOMBRE_DIVISA,
						IConstantesConsultasDivisas.INICIAL_MERCADO,
						IConstantesConsultasDivisas.FECHA_INICIAL,
						IConstantesConsultasDivisas.FECHA_FINAL },
				new Object[] { hoy ? "" : "a", codigo, mercado, fechaIni,
						fechaFin });
		List<DivisasRegistroTO> resDto = UtilDto.obtenerObjetos(
				DivisasRegistroTO.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<DivisasRegistroTO>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDivisasDao#
	 * obtenerResumenDivisaYMercadoPorFecha(java.lang.String, java.lang.String,
	 * boolean)
	 */
	public List<DivisasRegistroTO> obtenerResumenDivisaYMercadoPorFecha(
			String fechaIni, String fechaFin, boolean hoy) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasDivisas.TOTAL_REGISTRO_DIVISA_MERCADO_POR_FECHA,
				new String[] { IConstantesConsultasDivisas.SUFIJO_TABLA,
						IConstantesConsultasDivisas.FECHA_INICIAL,
						IConstantesConsultasDivisas.FECHA_FINAL },
				new Object[] { hoy ? "" : "a", fechaIni, fechaFin });
		List<DivisasRegistroTO> resDto = UtilDto.obtenerObjetos(
				DivisasRegistroTO.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<DivisasRegistroTO>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDivisasDao#
	 * obtenerResumenMercadoPorFecha(java.lang.String, java.lang.String,
	 * boolean)
	 */
	public Double obtenerResumenMercadoPorFecha(String fechaIni,
			String fechaFin, boolean hoy) throws Exception {
		Double ret = 0D;
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasDivisas.MONTO_TOTAL_REGISTRO_POR_FECHA,
				new String[] { IConstantesConsultasDivisas.SUFIJO_TABLA,
						IConstantesConsultasDivisas.FECHA_INICIAL,
						IConstantesConsultasDivisas.FECHA_FINAL },
				new Object[] { hoy ? "" : "a", fechaIni, fechaFin });
		if (resQuery != null && !resQuery.isEmpty()) {
			Map fila = resQuery.get(0);
			ret = (Double) fila.get("monto");
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDivisasDao#getIntradiaDolar()
	 */
	public ICierre getIntradiaDolar() throws Exception {
		Calendar cal = new GregorianCalendar();
		String fecha = UtilFechas.fechaMenos20Minutos(cal);
		List<ICierre> resDto = getIntradiaHistoricoDolar(fecha);
		return (resDto != null && !resDto.isEmpty()) ? resDto.get(0)
				: new CierreGeneral();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDivisasDao#getIntradiaHistoricoDolar
	 * (java.lang.String)
	 */
	public List<ICierre> getIntradiaHistoricoDolar(String fecha)
			throws Exception {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:kk:mm");
		Calendar cal = new GregorianCalendar();
		cal.setTime(sdf.parse(fecha));
		fecha = UtilFechas.fechaMenos20MinutosString(fecha, "yyyy-MM-dd:kk:mm");
		boolean isFestivoAmericano = isFestivoAmericano(cal);
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasDivisas.OBTENER_GRAFICA,
				new String[] { IConstantesConsultasDivisas.FECHA,
						IConstantesConsultasDivisas.HORA,
						IConstantesConsultasDivisas.FILTRO_CODIGO_MERCADO },
				new Object[] {
						fecha.substring(0, 10),
						fecha.substring(11).replaceAll(":", "") + "00",
						isFestivoAmericano ? IDivisasSetFx.CODIGO_MERCADO_NEXT_DAY
								: IDivisasSetFx.CODIGO_MERCADO_SPOT });
		List<ICierre> resDto = UtilDto.obtenerObjetos(CierreGeneral.class,
				resQuery);
		return (resDto != null) ? resDto : new ArrayList<ICierre>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDivisasDao#obtenerEstadoDia(
	 * java.lang.String)
	 */
	public DivisasSetFxTO obtenerEstadoDia(String fecha, Integer codMercado)
			throws Exception {
		String hora = fecha.substring(11).replaceAll(":", "") + "00";
		String query = getQuery(
				IConstantesConsultasDivisas.OBTENER_DETALLE_SET_FX,
				new String[] { IConstantesConsultasDivisas.HORA,
						IConstantesConsultasDivisas.FILTRO_CODIGO_MERCADO },
				new Object[] { hora, codMercado });
		List<Map> resQuery = cargarPorQuery(query);
		List<DivisasSetFxTO> resDto = UtilDto.obtenerObjetos(
				DivisasSetFxTO.class, resQuery);
		DivisasSetFxTO divisa = (resDto == null || resDto.isEmpty()) ? new DivisasSetFxTO()
				: resDto.get(0);
		divisa.setTrm(getValorTRMPorFecha(fecha.substring(0, 10).replaceAll(
				"-", "")));
		return divisa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDivisasDao#getValorTRMPorFecha
	 * (java.lang.String)
	 */
	public Double getValorTRMPorFecha(String fecha) throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "CALCULO_TRM_FECHA." + fecha;

		Double ret;

		ret = (Double) cache.getObject(queryKey);

		if (ret == null) {
			synchronized (this) {
				ret = (Double) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Datos TRM fecha : " + fecha);
					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasDivisas.TRM_POR_FECHA,
							new String[] { IConstantesConsultasDivisas.FECHA },
							new String[] { fecha });
					ret = (resQuery == null || resQuery.isEmpty()) ? 0D
							: Double.parseDouble(resQuery.get(0).get(
									"v_mdi_valor").toString());
					cache.putObject(queryKey, ret);
					return ret;
				}
			}
		} else {
			log.debug("Cargando desde Cache datos TRM fecha :" + fecha);
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDivisasDao#isFestivoAmericano
	 * (java.util.Calendar)
	 */
	public boolean isFestivoAmericano(Calendar fecha) throws Exception {
		if (fecha == null) {
			fecha = new GregorianCalendar();
		}

		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "IS_FESTIVO_AMERICANO." + sdf.format(fecha.getTime());

		Boolean ret = (Boolean) cache.getObject(queryKey);

		if (ret == null) {
			synchronized (this) {
				ret = (Boolean) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando is Festivo Americano: " + fecha);
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasDivisas.FESTIVO_AMERICANO,
							new String[] { IConstantesComunConsultasNombradas.FECHA },
							new String[] { sdf.format(fecha.getTime()) });
					if (resQuery != null && !resQuery.isEmpty()) {
						Date dato = (Date) resQuery.get(0).get("fecha");
						if (dato != null) {
							ret = new Boolean(true);
							cache.putObject(queryKey, ret);
							return ret;
						}
					}
					ret = new Boolean(false);
					cache.putObject(queryKey, ret);
					return ret;

				}
			}
		} else {
			log.debug("Cargando desde Cache is Festivo Americano : " + fecha);
		}

		return ret;

	}

}
