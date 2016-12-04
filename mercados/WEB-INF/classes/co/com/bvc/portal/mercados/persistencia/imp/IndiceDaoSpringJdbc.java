package co.com.bvc.portal.mercados.persistencia.imp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.jdbc.JDBCDaoImp;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilDto;
import co.com.bvc.portal.mercados.modelo.CierreGeneral;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InfoIndicesSectoriales;
import co.com.bvc.portal.mercados.modelo.ResumenIndice;
import co.com.bvc.portal.mercados.modelo.ResumenIndiceLista;
import co.com.bvc.portal.mercados.persistencia.IIndiceDao;
import co.com.bvc.portal.mercados.util.IConstantesConsultasIndices;

/**
 * @author BVC
 * @contributor Andres_Leon
 * @description The Class IndiceDaoSpringJdbc. Implementación de los metodos de acceso a los
 * datos de indices
 */

public class IndiceDaoSpringJdbc extends JDBCDaoImp implements IIndiceDao {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarDatosHome(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarDatosHome(String fecha) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES_HOME, new String[] {
						IConstantesConsultasIndices.OPERADOR,
						IConstantesConsultasIndices.FILTRO_FECHA },
				new String[] { "", fecha });
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<ResumenIndice>();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarDatosHome(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarDatosHomeRF(String fecha) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES_HOMERF, new String[] {
						IConstantesConsultasIndices.FILTRO_FECHA },
				new String[] { fecha });
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		for(ResumenIndice objResumenIndice: resDto){
			log.debug("Codigo: " + objResumenIndice.getIndice() + "FechaInt: "+ objResumenIndice.getFechaInt() + "Valor hoy: " + objResumenIndice.getValorHoy()+
					"Variacion hoy:" + objResumenIndice.getVariacionHoy() + "variacion12meses: "+objResumenIndice.getValor12Meses());
		}
		return (resDto != null) ? resDto : new ArrayList<ResumenIndice>();
	}
	
	/******************Se agrega funcionalidad para Mercado Monetario***************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarDatosHomeMM(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarDatosHomeMM(String fecha) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES_HOMEMM, new String[] {
						IConstantesConsultasIndices.FILTRO_FECHA },
				new String[] { fecha });
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		for(ResumenIndice objResumenIndice: resDto){
			log.debug("Codigo: " + objResumenIndice.getIndice() + "FechaInt: "+ objResumenIndice.getFechaInt() + "Valor hoy: " + objResumenIndice.getValorHoy()+
					"Variacion hoy:" + objResumenIndice.getVariacionHoy() + "variacion12meses: "+objResumenIndice.getValor12Meses());
		}
		return (resDto != null) ? resDto : new ArrayList<ResumenIndice>();
	}
	/******************************************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarHistoricoIndice
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoIndice(String indice, String fecha)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "HISTORICO_INDICE." + fecha + "." + indice;

		List<ICierre> respuesta;

		respuesta = (List) cache.getObject(queryKey);
		if (respuesta == null) {
			synchronized (this) {
				respuesta = (List) cache.getObject(queryKey);
				if (respuesta == null) {
					log.debug("Procesando Historico Indice : " + indice
							+ " fecha " + fecha);
					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasIndices.GRAFICA_HISTORICA,
							new String[] {
									IConstantesConsultasIndices.FILTRO_CODIGO,
									IConstantesConsultasIndices.FILTRO_FECHA },
							new String[] { indice, fecha });
					respuesta = UtilDto.obtenerObjetos(CierreGeneral.class,
							resQuery);
					if (respuesta != null && !respuesta.isEmpty()) {
						ICierre cierre = respuesta.get(0);
						for (ICierre tmpC : respuesta) {
							tmpC.setValorUltimoCierre(cierre.getValorCierre());
						}
					}
					cache.putObject(queryKey, respuesta);
					return respuesta;
				}
			}
		} else {
			log.debug("Cargando desde Cache Historico Indice:" + indice
					+ " fecha " + fecha);
		}

		return respuesta;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarHistoricoIndice
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoHOMEIndice(String indice, String fecha)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "HISTORICO_INDICEHOME." + fecha + "." + indice;

		List<ICierre> respuesta;

		respuesta = (List) cache.getObject(queryKey);
		if (respuesta == null) {
			synchronized (this) {
				respuesta = (List) cache.getObject(queryKey);
				if (respuesta == null) {
					log.debug("Procesando Historico Indice HOME: " + indice
							+ " fecha " + fecha);
					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasIndices.GRAFICA_HISTORICAHOME,
							new String[] {
									IConstantesConsultasIndices.FILTRO_CODIGO,
									IConstantesConsultasIndices.FILTRO_FECHA },
							new String[] { indice, fecha });
					respuesta = UtilDto.obtenerObjetos(CierreGeneral.class,
							resQuery);
					if (respuesta != null && !respuesta.isEmpty()) {
						ICierre cierre = respuesta.get(0);
						for (ICierre tmpC : respuesta) {
							tmpC.setValorUltimoCierre(cierre.getValorCierre());
						}
					}
					cache.putObject(queryKey, respuesta);
					return respuesta;
				}
			}
		} else {
			log.debug("Cargando desde Cache Historico Indice HOME:" + indice
					+ " fecha " + fecha);
		}

		return respuesta;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarHistoricoIndiceRF
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoIndiceRF(String indice, String fecha)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "HISTORICO_INDICE_RF." + fecha + "." + indice;
		
		
		List<Map> resQuery = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		

		List<ICierre> respuesta;
		List<ICierre> respuesta2 = new ArrayList<ICierre>();

		respuesta = /*(List) cache.getObject(queryKey)*/null;
		if (respuesta == null) {
			synchronized (this) {
				respuesta = /*(List) cache.getObject(queryKey)*/null;
				if (respuesta == null) {
					log.debug("Procesando Historico Indice RF: " + indice
							+ " fecha " + fecha);
					resQuery = cargarPorNombreQuery(
							IConstantesConsultasIndices.GRAFICA_HISTORICA_RF,
							new String[] {
									IConstantesConsultasIndices.FILTRO_CODIGO,
									IConstantesConsultasIndices.FILTRO_FECHA },
							new String[] { indice, fecha });
					respuesta = UtilDto.obtenerObjetos(CierreGeneral.class,
							resQuery);
					//log.debug("getValorCierre RF: " + respuesta.get(0).getValorCierre());System.out.print("getValorCierre RF: " + respuesta.get(0).getValorCierre());
					if (respuesta != null && !respuesta.isEmpty()) {
						ICierre cierre = respuesta.get(0);
						for (ICierre tmpC : respuesta) {
							tmpC.setValorUltimoCierre(cierre.getValorCierre());
						}
					}
					cache.putObject(queryKey, respuesta);
					return respuesta;
				}
			}
		} else {
			log.debug("Cargando desde Cache Historico Indice HOME:" + indice
					+ " fecha " + fecha);
		}

		return respuesta;

	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarHistoricoIndiceHOMERF
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoIndiceHOMERF(String indice, String fecha)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "HISTORICO_INDICE_HOMERF." + fecha + "." + indice;
		
		
		List<Map> resQuery = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		

		List<ICierre> respuesta;
		List<ICierre> respuesta2 = new ArrayList<ICierre>();

		respuesta = /*(List) cache.getObject(queryKey)*/null;
		if (respuesta == null) {
			synchronized (this) {
				respuesta = /*(List) cache.getObject(queryKey)*/null;
				if (respuesta == null) {
					log.debug("Procesando Historico Indice RF HOME: " + indice
							+ " fecha " + fecha);
					resQuery = cargarPorNombreQuery(
							IConstantesConsultasIndices.GRAFICA_HISTORICA_HOMERF,
							new String[] {
									IConstantesConsultasIndices.FILTRO_CODIGO,
									IConstantesConsultasIndices.FILTRO_FECHA },
							new String[] { indice, fecha });
					
					respuesta = UtilDto.obtenerObjetos(CierreGeneral.class,
							resQuery);
					if (respuesta != null && !respuesta.isEmpty()) {
						ICierre cierre = respuesta.get(0);
						for (ICierre tmpC : respuesta) {
							tmpC.setValorUltimoCierre(cierre.getValorCierre());
						}
					}
					cache.putObject(queryKey, respuesta);
					return respuesta;
				}
			}
		} else {
			log.debug("Cargando desde Cache Historico Indice HOME:" + indice
					+ " fecha " + fecha);
		}

		return respuesta;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarHistoricoIndiceMM
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoIndiceMM(String indice, String fecha)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "HISTORICO_INDICE_MM." + fecha + "." + indice;
		
		
		List<Map> resQuery = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		

		List<ICierre> respuesta;
		List<ICierre> respuesta2 = new ArrayList<ICierre>();

		respuesta = /*(List) cache.getObject(queryKey)*/null;
		if (respuesta == null) {
			synchronized (this) {
				respuesta = /*(List) cache.getObject(queryKey)*/null;
				if (respuesta == null) {
					log.debug("Procesando Historico Indice MM: " + indice
							+ " fecha " + fecha);
					resQuery = cargarPorNombreQuery(
							IConstantesConsultasIndices.GRAFICA_HISTORICA_MM,
							new String[] {
									IConstantesConsultasIndices.FILTRO_CODIGO,
									IConstantesConsultasIndices.FILTRO_FECHA },
							new String[] { indice, fecha });
					
					for (Map tmpRespuesta2 : resQuery) {
						ICierre respuestaTMP = new CierreGeneral();
						Calendar objCalendar = Calendar.getInstance();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						objCalendar.setTime((java.util.Date) tmpRespuesta2.get("fechaGrafico"));
						//log.debug("resultado2 consulta rtf MM fecha: "+ tmpRespuesta2.get("fechaGrafico"));
						//respuestaTMP.setFechaString((String)tmpRespuesta2.get("fechaGrafico"));
						respuestaTMP.setFechaHora(objCalendar);
						respuestaTMP.setValorCierre((Double)tmpRespuesta2.get("valorCierre"));
						respuesta2.add(respuestaTMP);
					}
					
					if (respuesta2 != null && !respuesta2.isEmpty()) {
						ICierre cierre = respuesta2.get(0);
						for (ICierre tmpC : respuesta2) {
							tmpC.setValorUltimoCierre(cierre.getValorCierre());
						}
					}
					
					respuesta = UtilDto.obtenerObjetos(CierreGeneral.class,
							resQuery);
					/*if (respuesta != null && !respuesta.isEmpty()) {
						ICierre cierre = respuesta.get(0);
						for (ICierre tmpC : respuesta) {
							tmpC.setValorUltimoCierre(cierre.getValorCierre());
						}
					}*/
					cache.putObject(queryKey, respuesta);
					
					
					/*for (ICierre tmpRespuesta : respuesta) {
						log.debug("resultado consulta rtf fecha1: "+ tmpRespuesta.getFechaString()+" fecha2: "+sdf.format(tmpRespuesta.getFechaHora().getTime()));
					}
					
					for (ICierre tmpRespuesta3 : respuesta) {
						log.debug("resultado consulta3 rtf fecha1: "+ tmpRespuesta3.getFechaString()+" fecha2: "+sdf.format(tmpRespuesta3.getFechaHora().getTime()));
					}*/
					
					return respuesta2;
				}
			}
		} else {
			log.debug("Cargando desde Cache Historico Indice:" + indice
					+ " fecha " + fecha);
		}

		return respuesta;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarHistoricoIndiceHOMEMM
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoIndiceHOMEMM(String indice, String fecha)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "HISTORICO_INDICE_HOMEMM." + fecha + "." + indice;
		
		
		List<Map> resQuery = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		

		List<ICierre> respuesta;
		List<ICierre> respuesta2 = new ArrayList<ICierre>();

		respuesta = /*(List) cache.getObject(queryKey)*/null;
		if (respuesta == null) {
			synchronized (this) {
				respuesta = /*(List) cache.getObject(queryKey)*/null;
				if (respuesta == null) {
					log.debug("Procesando Historico Indice MM HOME: " + indice
							+ " fecha " + fecha);
					resQuery = cargarPorNombreQuery(
							IConstantesConsultasIndices.GRAFICA_HISTORICA_HOMEMM,
							new String[] {
									IConstantesConsultasIndices.FILTRO_CODIGO,
									IConstantesConsultasIndices.FILTRO_FECHA },
							new String[] { indice, fecha });
					
					for (Map tmpRespuesta2 : resQuery) {
						ICierre respuestaTMP = new CierreGeneral();
						Calendar objCalendar = Calendar.getInstance();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						objCalendar.setTime((java.util.Date) tmpRespuesta2.get("fechaGrafico"));
						//objCalendar.setTime(formatter.parse((String) tmpRespuesta2.get("fechaGrafico")));
						//log.debug("resultado2 consulta rtf fecha: "+ tmpRespuesta2.get("fechaGrafico"));
						//respuestaTMP.setFechaString((String)tmpRespuesta2.get("fechaGrafico"));
						respuestaTMP.setFechaHora(objCalendar);
						respuestaTMP.setValorCierre((Double)tmpRespuesta2.get("valorCierre"));
						respuesta2.add(respuestaTMP);
					}
					
					if (respuesta2 != null && !respuesta2.isEmpty()) {
						ICierre cierre = respuesta2.get(0);
						for (ICierre tmpC : respuesta2) {
							tmpC.setValorUltimoCierre(cierre.getValorCierre());
						}
					}
					
					respuesta = UtilDto.obtenerObjetos(CierreGeneral.class,
							resQuery);
					/*if (respuesta != null && !respuesta.isEmpty()) {
						ICierre cierre = respuesta.get(0);
						for (ICierre tmpC : respuesta) {
							tmpC.setValorUltimoCierre(cierre.getValorCierre());
						}
					}*/
					cache.putObject(queryKey, respuesta);
					
					
					/*for (ICierre tmpRespuesta : respuesta) {
						log.debug("resultado consulta rtf fecha1: "+ tmpRespuesta.getFechaString()+" fecha2: "+sdf.format(tmpRespuesta.getFechaHora().getTime()));
					}
					
					for (ICierre tmpRespuesta3 : respuesta) {
						log.debug("resultado consulta3 rtf fecha1: "+ tmpRespuesta3.getFechaString()+" fecha2: "+sdf.format(tmpRespuesta3.getFechaHora().getTime()));
					}*/
					
					return respuesta2;
				}
			}
		} else {
			log.debug("Cargando desde Cache Historico Indice:" + indice
					+ " fecha " + fecha);
		}

		return respuesta;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarHistoricoIndice
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoIndiceHome(String indice, String fecha)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "HISTORICO_INDICE_HOME." + fecha + "." + indice;

		List<ICierre> respuesta;

		respuesta = (List) cache.getObject(queryKey);
		if (respuesta == null) {
			synchronized (this) {
				respuesta = (List) cache.getObject(queryKey);
				if (respuesta == null) {
					log.debug("Procesando Historico Indice : " + indice
							+ " fecha " + fecha);
					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasIndices.GRAFICA_HISTORICA_HOME,
							new String[] {
									IConstantesConsultasIndices.FILTRO_CODIGO,
									IConstantesConsultasIndices.FILTRO_FECHA },
							new String[] { indice, fecha });
					respuesta = UtilDto.obtenerObjetos(CierreGeneral.class,
							resQuery);
					if (respuesta != null && !respuesta.isEmpty()) {
						ICierre cierre = respuesta.get(0);
						for (ICierre tmpC : respuesta) {
							tmpC.setValorUltimoCierre(cierre.getValorCierre());
						}
					}
					cache.putObject(queryKey, respuesta);
					return respuesta;
				}
			}
		} else {
			log.debug("Cargando desde Cache Historico Indice:" + indice
					+ " fecha " + fecha);
		}

		return respuesta;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IIndiceDao#
	 * cargarResumenPorIndiceYFecha(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public ResumenIndice cargarResumenPorIndiceYFecha(String codigoIndice,
			String fecha, String fechaHace12Meses)
			throws PersistenciaException, Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICE_POR_FECHA,
				new String[] { IConstantesConsultasIndices.FILTRO_CODIGO,
						IConstantesConsultasIndices.FECHA_DOCE_MESES,
						IConstantesConsultasIndices.FILTRO_FECHA },
				new Object[] { codigoIndice, fechaHace12Meses, fecha });
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		return (resDto != null && !resDto.isEmpty()) ? (ResumenIndice) resDto
				.get(0) : new ResumenIndice();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IIndiceDao#
	 * obtenerIndicesMercado(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List <ResumenIndice> obtenerIndicesMercado()
			throws  Exception {
		
				List<Map> resQuery = cargarPorNombreQuery(
						IConstantesConsultasIndices.INDICES_POR_MERCADO, new String[] {},
						new String[] {   });
				List<ResumenIndice> resDto = null;
				try {
					resDto = UtilDto.obtenerObjetos(
							ResumenIndice.class, resQuery);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				return (resDto != null) ? resDto : new ArrayList<ResumenIndice>();		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#obtenerMetadataPorIndice
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public InfoIndicesSectoriales obtenerMetadataPorIndice(String codigoIndice)
			throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.METADATA_INDICE,
				new String[] { IConstantesConsultasIndices.FILTRO_CODIGO },
				new String[] { codigoIndice });
		List<InfoIndicesSectoriales> resDto = UtilDto.obtenerObjetos(
				InfoIndicesSectoriales.class, resQuery);
		return (resDto != null && !resDto.isEmpty()) ? (InfoIndicesSectoriales) resDto
				.get(0)
				: new InfoIndicesSectoriales();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#salvarInfoIndice(java
	 * .lang.String, java.lang.String)
	 */
	public void salvarInfoIndice(String parametro, String codigo)
			throws Exception {
		String sql = getQuery(
				IConstantesConsultasIndices.ACTUALIZAR_CONTENIDOS,
				new String[] { IConstantesConsultasIndices.SETER,
						IConstantesConsultasIndices.FILTRO_CODIGO },
				new String[] { parametro, codigo });
		super.getJdbcTemplate().execute(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IIndiceDao#
	 * cargarResumenPorIndiceYRangoFecha(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarResumenPorIndiceYRangoFecha(
			String codIndice, String fechaMax, String fechaMin)
			throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICE_POR_RANGO_FECHA,
				new String[] { IConstantesConsultasIndices.FILTRO_CODIGO,
						IConstantesConsultasIndices.FILTRO_FECHA_MAX,
						IConstantesConsultasIndices.FILTRO_FECHA_MIN },
				new Object[] { codIndice, fechaMax, fechaMin });
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<ResumenIndice>();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IIndiceDao#
	 * cargarResumenPorIndiceYRangoFechaRF(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarResumenPorIndiceYRangoFechaRF(
			String codIndice, String fechaMax, String fechaMin)
			throws Exception {
		List<ResumenIndice> resDto = new ArrayList<ResumenIndice>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar objFecha = new GregorianCalendar();
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICE_POR_RANGO_FECHA_RENTA_FIJA,
				new String[] { IConstantesConsultasIndices.FILTRO_CODIGO,
						IConstantesConsultasIndices.FILTRO_FECHA_MAX,
						IConstantesConsultasIndices.FILTRO_FECHA_MIN },
				new Object[] { codIndice, fechaMax, fechaMin });
		
		if(null != resQuery && !resQuery.isEmpty()){
			for(Map respuesta: resQuery){
				ResumenIndice objResumenIndice = new ResumenIndice();
				objResumenIndice.setIndice((String)respuesta.get("indice"));
				objResumenIndice.setValorHoy((Double)respuesta.get("valorHoy"));
				objResumenIndice.setValorAyer((Double)respuesta.get("valorAyer"));
				objResumenIndice.setVariacionHoy((Double)respuesta.get("variacionHoy"));
				objResumenIndice.setVariacionAbs((Double)respuesta.get("variacionAbs"));
				objResumenIndice.setVariacionAnual((Double)respuesta.get("variacionAnual"));
				String fechaint=String.valueOf(respuesta.get("fechaInt"));  
				objFecha.setTime(sdf.parse(fechaint));
				objResumenIndice.setFechaInt(Integer.valueOf(sdf.format(objFecha.getTime())));
				resDto.add(objResumenIndice);
			}
			
		}
		return resDto;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IIndiceDao#
	 * cargarResumenPorIndiceYRangoFechaMM(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarResumenPorIndiceYRangoFechaMM(
			String codIndice, String fechaMax, String fechaMin)
			throws Exception {
		List<ResumenIndice> resDto = new ArrayList<ResumenIndice>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar objFecha = new GregorianCalendar();
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICE_POR_RANGO_FECHA_MONETARIO,
				new String[] { IConstantesConsultasIndices.FILTRO_CODIGO,
						IConstantesConsultasIndices.FILTRO_FECHA_MAX,
						IConstantesConsultasIndices.FILTRO_FECHA_MIN },
				new Object[] { codIndice, fechaMax, fechaMin });
		
		if(null != resQuery && !resQuery.isEmpty()){
			for(Map respuesta: resQuery){
				ResumenIndice objResumenIndice = new ResumenIndice();
				objResumenIndice.setIndice((String)respuesta.get("indice"));
				objResumenIndice.setValorHoy((Double)respuesta.get("valorHoy"));
				objResumenIndice.setValorAyer((Double)respuesta.get("valorAyer"));
				objResumenIndice.setVariacionHoy((Double)respuesta.get("variacionHoy"));
				objResumenIndice.setVariacionAbs((Double)respuesta.get("variacionAbs"));
				objResumenIndice.setVariacionAnual((Double)respuesta.get("variacionAnual"));
				String fechaint=String.valueOf(respuesta.get("fechaInt")); 
				objFecha.setTime(sdf.parse(fechaint));
				objResumenIndice.setFechaInt(Integer.valueOf(sdf.format(objFecha.getTime())));
				resDto.add(objResumenIndice);
			}
			
		}
		return resDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#getMapaFechaValorHoy
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, Double> getMapaFechaValorHoy(String codIndice,
			String fechaMax, String fechaMin) throws Exception {
		Map<Integer, Double> ret = new TreeMap<Integer, Double>();
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.MAPA_FECHA_VALOR_HOY, new String[] {
						IConstantesConsultasIndices.FILTRO_CODIGO,
						IConstantesConsultasIndices.FILTRO_FECHA_MAX,
						IConstantesConsultasIndices.FILTRO_FECHA_MIN },
				new Object[] { codIndice, fechaMax, fechaMin });
		for (Iterator<Map> iter = resQuery.iterator(); iter.hasNext();) {
			Map mapAct = iter.next();
			ret.put(Integer.valueOf(mapAct.get("fecha").toString()),
					(Double) mapAct.get("valor"));
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#getMapaFechaValorHoyRF
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Map<Integer, Double> getMapaFechaValorHoyRF(String codIndice,
			String fechaMax, String fechaMin) throws Exception {
		Map<Integer, Double> ret = new TreeMap<Integer, Double>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		GregorianCalendar objFecha = new GregorianCalendar();
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.MAPA_FECHA_VALOR_HOY_RENTA_FIJA, new String[] {
						IConstantesConsultasIndices.FILTRO_CODIGO,
						IConstantesConsultasIndices.FILTRO_FECHA_MAX,
						IConstantesConsultasIndices.FILTRO_FECHA_MIN },
				new Object[] { codIndice, fechaMax, fechaMin });
		for (Iterator<Map> iter = resQuery.iterator(); iter.hasNext();) {
			Map mapAct = iter.next();
			objFecha.setTime((Date)(mapAct.get("fecha")));
			ret.put(Integer.valueOf(sdf.format(objFecha.getTime())),
					(Double) mapAct.get("valor"));
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IIndiceDao#
	 * cargarResumenPorIndiceYRangoFechaRecientes(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarResumenPorIndiceYRangoFechaRecientes(
			String codIndice, String fechaMax, String fechaMin)
			throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICE_POR_RANGO_FECHA_RECIENTES,
				new String[] { IConstantesConsultasIndices.FILTRO_CODIGO,
						IConstantesConsultasIndices.FILTRO_FECHA_MAX,
						IConstantesConsultasIndices.FILTRO_FECHA_MIN,
						IConstantesConsultasIndices.FILTRO_FECHA_MENOR },
				new Object[] { codIndice, fechaMax, fechaMin, fechaMin });
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<ResumenIndice>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarResumenIndices
	 * (java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarResumenIndices(String fecha,
			boolean seccional) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES, new String[] {
						IConstantesConsultasIndices.OPERADOR,
						IConstantesConsultasIndices.FILTRO_FECHA },
				new String[] { seccional ? "NOT" : "", fecha });
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<ResumenIndice>();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarResumenIndicesXLS
	 * (java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndiceLista> cargarResumenIndicesXLS(String fecha,
			boolean seccional) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES, new String[] {
						IConstantesConsultasIndices.OPERADOR,
						IConstantesConsultasIndices.FILTRO_FECHA },
				new String[] { seccional ? "NOT" : "", fecha });
		List<ResumenIndiceLista> resDto = UtilDto.obtenerObjetos(
				ResumenIndiceLista.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<ResumenIndiceLista>();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarResumenIndicesRF
	 * (java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarResumenIndicesRF(String fecha) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES_RF, new String[] {
						IConstantesConsultasIndices.FILTRO_FECHA },
				new String[] {  fecha });
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<ResumenIndice>();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarResumenIndicesXLSRF
	 * (java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndiceLista> cargarResumenIndicesXLSRF(String fecha) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES_RF, new String[] {
						IConstantesConsultasIndices.FILTRO_FECHA },
				new String[] {  fecha });
		List<ResumenIndiceLista> resDto = UtilDto.obtenerObjetos(
				ResumenIndiceLista.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<ResumenIndiceLista>();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarResumenIndicesMM
	 * (java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarResumenIndicesMM(String fecha) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES_MM, new String[] {
						IConstantesConsultasIndices.FILTRO_FECHA },
				new String[] {  fecha });
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<ResumenIndice>();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarResumenIndicesXLSMM
	 * (java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndiceLista> cargarResumenIndicesXLSMM(String fecha) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES_MM, new String[] {
						IConstantesConsultasIndices.FILTRO_FECHA },
				new String[] {  fecha });
		List<ResumenIndiceLista> resDto = UtilDto.obtenerObjetos(
				ResumenIndiceLista.class, resQuery);
		return (resDto != null) ? resDto : new ArrayList<ResumenIndiceLista>();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarResumenIndicesFF
	 * (java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public ResumenIndice cargarResumenIndicesBuscarRF(String fecha,String codIndice) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES_BUSCARRF, new String[] {
						IConstantesConsultasIndices.FILTRO_FECHA,
						IConstantesConsultasIndices.FILTRO_CODIGO},
				new String[] {  fecha, codIndice});
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		return (resDto != null && !resDto.isEmpty()) ? (ResumenIndice) resDto
				.get(0) : new ResumenIndice();
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#cargarResumenIndicesMM
	 * (java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	public ResumenIndice cargarResumenIndicesBuscarMM(String fecha,String codIndice) throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasIndices.RESUMEN_INDICES_BUSCARMM, new String[] {
						IConstantesConsultasIndices.FILTRO_FECHA,
						IConstantesConsultasIndices.FILTRO_CODIGO},
				new String[] {  fecha, codIndice});
		List<ResumenIndice> resDto = UtilDto.obtenerObjetos(
				ResumenIndice.class, resQuery);
		return (resDto != null && !resDto.isEmpty()) ? (ResumenIndice) resDto
				.get(0) : new ResumenIndice();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#getIndicesComparacion
	 * ()
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getIndicesComparacion()
			throws PersistenciaException {
		ArrayList<String> res;
		String consulta = "select V_INDACCSEC_CODIGO as nemo from portalbvc.tbindaccsec";

		Collection tmp = this.getJdbcTemplate().query(consulta,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String accion = rs.getString("nemo");
						return accion;
					}
				});

		res = new ArrayList<String>(tmp);
		return res;
	}

	/**
	 * Cargar intradia indices historico.
	 * 
	 * @param titulo
	 *            the titulo
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws Exception
	 *             the exception
	 * 
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarIntradiaTitulo(java.lang.String,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarIntradiaIndicesHistorico(String titulo)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_60SECS);
		String queryKey = "INTRADIA_INDICES." + titulo;

		List<ICierre> respuesta;

		respuesta = (List) cache.getObject(queryKey);

		if (respuesta == null) {
			synchronized (this) {
				respuesta = (List) cache.getObject(queryKey);
				if (respuesta == null) {
					log.debug("Procesando Intradia Indice : " + titulo);
					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasIndices.GRAFICA_INTRADIA,
							new String[] { IConstantesConsultasIndices.FILTRO_CODIGO },
							new String[] { titulo });

					respuesta = UtilDto.obtenerObjetos(CierreGeneral.class,
							resQuery);

					cache.putObject(queryKey, respuesta);
					return respuesta;
				}
			}
		} else {
			log.debug("Cargando desde Cache Intradia Indice : " + titulo);
		}

		return respuesta;
	}
	
	/**
	 * Cargar intradia indices historico RF.
	 * 
	 * @param titulo
	 *            the titulo
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws Exception
	 *             the exception
	 * 
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarIntradiaTitulo(java.lang.String,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarIntradiaIndicesHistoricoRF(String titulo)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_60SECS);
		String queryKey = "INTRADIA_INDICES_RF." + titulo;

		List<ICierre> respuesta;

		respuesta = (List) cache.getObject(queryKey);

		if (respuesta == null) {
			synchronized (this) {
				respuesta = (List) cache.getObject(queryKey);
				if (respuesta == null) {
					log.debug("Procesando Intradia Indice : " + titulo);
					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasIndices.GRAFICA_INTRADIA_RF,
							new String[] { IConstantesConsultasIndices.FILTRO_CODIGO },
							new String[] { titulo });

					respuesta = UtilDto.obtenerObjetos(CierreGeneral.class,
							resQuery);

					cache.putObject(queryKey, respuesta);
					return respuesta;
				}
			}
		} else {
			log.debug("Cargando desde Cache Intradia Indice RF: " + titulo);
		}

		return respuesta;
	}

	/**
	 * Cargar intradia indice.
	 * 
	 * @param titulo
	 *            the titulo
	 * 
	 * @return the i cierre
	 * 
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarIntradiaTitulo(java.lang.String,
	 *      java.lang.String)
	 */
	public ICierre cargarIntradiaIndice(String titulo) {
		Object params[] = { titulo };
		String SQL = "" + "SELECT  MAX(v_indacc_time)  AS fecha   , "
				+ "        v_indacc_valor_hoy  AS valorHoy, "
				+ "        v_indacc_valor_ayer AS valorAyer "
				+ "FROM    tbindtra " + "WHERE   v_indacc_codigo = ?";

		ICierre cierre = (ICierre) this.getJdbcTemplate().queryForObject(SQL,
				params, new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ICierre cierre = new CierreGeneral();
						Calendar instance = Calendar.getInstance();
						instance.setTime(rs.getTimestamp("fecha"));

						cierre.setFechaHora(instance);
						cierre.setValorCierre(rs.getFloat("valorHoy"));
						cierre.setValorUltimoCierre(rs.getFloat("valorAyer"));
						return cierre;
					}
				});
		return cierre;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IIndiceDao#deleteOperationsData()
	 */
	public void deleteOperationsData() {
		this.getJdbcTemplate().execute("TRUNCATE unicabd.tbindtra");
	}
}
/*******************************************************End of IndiceDaoSpringJdbc.java**************************/