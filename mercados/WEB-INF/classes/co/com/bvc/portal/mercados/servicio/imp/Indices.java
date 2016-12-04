package co.com.bvc.portal.mercados.servicio.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.servicio.IServicioUtil;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.IndicesAutocomplete;
import co.com.bvc.portal.mercados.modelo.InfoIndicesSectoriales;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.ResumenIndice;
import co.com.bvc.portal.mercados.modelo.ResumenIndiceLista;
import co.com.bvc.portal.mercados.persistencia.IAccionesDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao;
import co.com.bvc.portal.mercados.persistencia.IIndiceDao;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IIndices;
import co.com.bvc.portal.mercados.servicio.IServicioMercadosUtil;
import co.com.bvc.portal.mercados.util.IConstantesConsultasIndices;
import co.com.bvc.portal.mercados.vista.form.FormularioIndices;

/**
 * @author BVC
 * @description The Class Indices. Implementación de la logica de manejo de los datos de
 * indices
 */

public class Indices implements IIndices {

	/** The log. */
	private static Logger log = Logger.getLogger(Indices.class);

	/** The acciones dao. */
	private IAccionesDao accionesDao;

	/** The acciones dao. */
	private IAccionesMGCDao accionesMGCDao;

	/** The indices dao. */
	private IIndiceDao indicesDao;

	/** The servicio mercados util. */
	private IServicioMercadosUtil servicioMercadosUtil;

	/** The fecha inicio indices recientes. */
	private Integer fechaInicioIndicesRecientes;

	/** The indices recientes. */
	private List<String> indicesRecientes;

	/** The servicio util. */
	private IServicioUtil servicioUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IIndices#cargarHome()
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarHome() {

		List<ResumenIndice> ret;

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "CARGAR_HOME_INDICES."
				+ UtilFechas.getHoy("yyyyMMdd");

		ret = (List<ResumenIndice>) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (List<ResumenIndice>) cache.getObject(queryKey);
				if (ret == null) {

					DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Calendar cal = servicioUtil
							.getUltimoDiaHabilBursatil(new GregorianCalendar());
					// List<ResumenIndice> ret = new ArrayList<ResumenIndice>();
					try {
						ret = indicesDao.cargarDatosHome(sdf.format(cal
								.getTime()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ret;

				}
			}
		} else {
			log.debug("Cargando desde Cargar Home Indices : "
					+ UtilFechas.getHoy("yyyyMMdd"));
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IIndices#cargarHomeRF()
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarHomeRF() {

		List<ResumenIndice> ret;

		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "CARGAR_HOME_INDICES_RF."
				+ UtilFechas.getHoy("yyyyMMdd");

		ret = (List<ResumenIndice>) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (List<ResumenIndice>) cache.getObject(queryKey);
				if (ret == null) {

					DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Calendar cal = servicioUtil
							.getUltimoDiaHabilBursatil(new GregorianCalendar());
					// List<ResumenIndice> ret = new ArrayList<ResumenIndice>();
					try {
						ret = indicesDao.cargarDatosHomeRF(sdf.format(cal
								.getTime()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ret;

				}
			}
		} else {
			log.debug("Cargando desde Cargar Home Indices : "
					+ UtilFechas.getHoy("yyyyMMdd"));
		}

		return ret;
	}
	
	/******************Se agrega funcionalidad para Mercado Monetario***************/
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> cargarHomeMM() {

		List<ResumenIndice> ret;

		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_HOURLY);
		String queryKey = "CARGAR_HOME_INDICES_MM."
				+ UtilFechas.getHoy("yyyyMMdd");

		ret = (List<ResumenIndice>) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (List<ResumenIndice>) cache.getObject(queryKey);
				if (ret == null) {

					DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Calendar cal = servicioUtil
							.getUltimoDiaHabilBursatil(new GregorianCalendar());
					// List<ResumenIndice> ret = new ArrayList<ResumenIndice>();
					try {
						ret = indicesDao.cargarDatosHomeMM(sdf.format(cal
								.getTime()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ret;

				}
			}
		} else {
			log.debug("Cargando desde Cargar Home Indices : "
					+ UtilFechas.getHoy("yyyyMMdd"));
		}

		return ret;
	}
	/******************************************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerPosiblesIndices()
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> obtenerPosiblesIndices() {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_HOURLY);
		String queryKey = "POSIBLES_INDICES." + UtilFechas.getHoy("yyyyMMdd");

		Map<String, String> ret;

		ret = (Map) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (Map) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Obtener Indices : "
							+ UtilFechas.getHoy("yyyyMMdd"));
					ret = new TreeMap<String, String>();
					try {

						List<Map> resultQuery = indicesDao
								.cargarPorNombreQuery(
										IConstantesConsultasIndices.INDICES_PARA_SELECT,
										new String[] {}, new Object[] {});
						for (Iterator<Map> iterator = resultQuery.iterator(); iterator
								.hasNext();) {
							Map map = iterator.next();
							ret.put(map.get(
									IConstantesConsultasIndices.COLUMNA_CLAVE)
									.toString(),
									map.get(IConstantesConsultasIndices.COLUMNA_VALOR)
											.toString());
						}
					} catch (PersistenciaException ex) {
						ex.printStackTrace();
					}
					cache.putObject(queryKey, ret);
					return ret;
				}
			}
		} else {
			log.debug("Cargando desde Cache Obtener Indices : "
					+ UtilFechas.getHoy("yyyyMMdd"));
		}

		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerIndicesMercado(co.
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> obtenerIndicesMercado() {
		
		List<ResumenIndice> ret = new ArrayList<ResumenIndice>();
		
		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "RESUMEN_INDICES_MONETARIO."
				+ UtilFechas.getHoy("yyyyMMdd");

		ret = (List<ResumenIndice>) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (List<ResumenIndice>) cache.getObject(queryKey);
				if (ret == null) {

			        try {
						ret = indicesDao.obtenerIndicesMercado();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ret;

				}
			}
		} else {
			log.debug("Cargando desde Cargar Home Indices : "
					+ UtilFechas.getHoy("yyyyMMdd"));
		}
		return ret;
		
	    
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenIndice(co.
	 * com.bvc.portal.mercados.vista.form.FormularioIndices)
	 */
	public ResumenIndice obtenerResumenIndice(FormularioIndices form) {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_60SECS);
		String queryKey = "RESUMEN_INDICE." + form.getTimeRequerido() + "."
				+ form.getCodigoIndice();

		ResumenIndice resumen = (ResumenIndice) cache.getObject(queryKey);
		if (resumen == null) {
			synchronized (this) {
				resumen = (ResumenIndice) cache.getObject(queryKey);
				if (resumen == null) {
					log.debug("Procesando Resumen Indice : "
							+ form.getCodigoIndice() + " fecha: "
							+ form.getTimeRequerido());
					resumen = new ResumenIndice();
					String fechaHace12Meses = obtenerHace12Meses(form);
					try {
						resumen = indicesDao.cargarResumenPorIndiceYFecha(
								form.getCodigoIndice(),
								form.getTimeRequerido(), fechaHace12Meses);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					cache.putObject(queryKey, resumen);
					return resumen;
				}
			}
		} else {
			log.debug("Cargando desde Cache Resumen Indice : "
					+ form.getCodigoIndice() + " fecha: "
					+ form.getTimeRequerido());
		}

		return resumen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerIndiceMetadata(co
	 * .com.bvc.portal.mercados.vista.form.FormularioIndices)
	 */
	public InfoIndicesSectoriales obtenerIndiceMetadata(FormularioIndices form) {
		InfoIndicesSectoriales meta = new InfoIndicesSectoriales();
		try {
			meta = indicesDao.obtenerMetadataPorIndice(form.getCodigoIndice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return meta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenIndices(co
	 * .com.bvc.portal.mercados.vista.form.FormularioIndices)
	 */
	public List<List<ResumenIndice>> obtenerResumenIndices(
			FormularioIndices form) {
		List<List<ResumenIndice>> ret = new ArrayList<List<ResumenIndice>>();
		String fecha = form != null ? form.getTimeRequerido() : null;
		if (fecha == null || fecha.trim().length() < 1) {
			fecha = UtilFechas.getHoy("yyyyMMdd");
		}
		try {
			List<ResumenIndice> listaPrincipales = indicesDao
					.cargarResumenIndices(fecha, false);
			List<ResumenIndice> listaSeccionales = indicesDao
					.cargarResumenIndices(fecha, true);
			ret.add(0, listaPrincipales);
			ret.add(1, listaSeccionales);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenIndices(co
	 * .com.bvc.portal.mercados.vista.form.FormularioIndices)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndiceLista> obtenerResumenIndicesXLS(String fecha) {
		List<ResumenIndiceLista> ret = new ArrayList<ResumenIndiceLista>();
		List<ResumenIndiceLista> ret2 = new ArrayList<ResumenIndiceLista>();
		List<ResumenIndiceLista> listasIN = new ArrayList<ResumenIndiceLista>();
		List<ResumenIndiceLista> listaPrincipales= new ArrayList<ResumenIndiceLista>();
		List<ResumenIndiceLista> resumen = new ArrayList<ResumenIndiceLista>();
		if (fecha == null || fecha.trim().length() < 1) {
			fecha = UtilFechas.getHoy("yyyyMMdd");
		}

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_60SECS);
		String queryKey = "RESUMEN_DESCARGAXLS."
				+ UtilFechas.getHoy("yyyyMMdd");

		resumen = (List<ResumenIndiceLista>) cache.getObject(queryKey); // ACCIONES-015
		if (resumen == null) {
			synchronized (this) {
				resumen = (List<ResumenIndiceLista>) cache.getObject(queryKey);
				if (resumen == null) {

			        try {
			        	 listaPrincipales = indicesDao
								.cargarResumenIndicesXLS(fecha, false);
			        	ret = indicesDao.cargarResumenIndicesXLSRF(fecha);
			        	ret2 = indicesDao.cargarResumenIndicesXLSMM(fecha);
			        	listasIN.addAll(listaPrincipales);
			        	listasIN.addAll(ret);
			        	listasIN.addAll(ret2);
			        	
					} catch (Exception e) {
						e.printStackTrace();
					}
			        if(null != listasIN && !listasIN.isEmpty()){
			        	ResumenIndiceLista indice = new ResumenIndiceLista();
						List<ResumenIndiceLista> resDto = new ArrayList<ResumenIndiceLista>();
						for (Iterator<ResumenIndiceLista> iter = listasIN.iterator(); iter.hasNext();){
							indice=iter.next();
							ResumenIndiceLista objResumenIndice = new ResumenIndiceLista();
							objResumenIndice.setNombreIndice((String)indice.getNombreIndice());
							objResumenIndice.setMercado((String)indice.getMercado());
							objResumenIndice.setValorHoy((Double)indice.getValorHoy());
							objResumenIndice.setValorAyer((Double)indice.getValorAyer());
							objResumenIndice.setVariacionAbs((Double)indice.getVariacionAbs());
							objResumenIndice.setVariacionHoy((Double)indice.getVariacionHoy());
							resDto.add(objResumenIndice);
							
						}
						resumen=  resDto;
					}
			        
				}
				return resumen;
			}
		} else {
			log.debug("Cargando desde Cargar Home Indices : "
					+ UtilFechas.getHoy("yyyyMMdd"));
		}
		
		return resumen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenIndicesRF(co
	 * .com.bvc.portal.mercados.vista.form.FormularioIndices)
	 */
	public List<ResumenIndice> obtenerResumenIndicesRF(FormularioIndices form) {
		List<ResumenIndice> ret = new ArrayList<ResumenIndice>();
		String fecha = form != null ? form.getTimeRequerido() : null;
		if (fecha == null || fecha.trim().length() < 1) {
			fecha = UtilFechas.getHoy("yyyyMMdd");
		}
		try {
			ret = indicesDao.cargarResumenIndicesRF(fecha);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenIndicesRF(co
	 * .com.bvc.portal.mercados.vista.form.FormularioIndices)
	 */
	public ResumenIndice obtenerResumenIndicesBucarRF(FormularioIndices form) {
				
		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_60SECS);
		String queryKey = "RESUMEN_INDICE_BUSCAR_RF." + form.getTimeRequerido() + "."
				+ form.getCodigoIndice();

		ResumenIndice resumen = (ResumenIndice) cache.getObject(queryKey);
		if (resumen == null) {
			synchronized (this) {
				resumen = (ResumenIndice) cache.getObject(queryKey);
				if (resumen == null) {
					
					resumen = new ResumenIndice();
					String fecha = form != null ? form.getTimeRequerido() : null;
					if (fecha == null || fecha.trim().length() < 1) {
						fecha = UtilFechas.getHoy("yyyyMMdd");
					}
					try {
						resumen = indicesDao.cargarResumenIndicesBuscarRF(fecha,form.getCodigoIndice());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					resumen.setMercado("RENTA FIJA");
					cache.putObject(queryKey, resumen);
					return resumen;
				}
			}
		} else {
			log.debug("Cargando desde Cache Resumen Buscar RF Indice : "
					+ form.getCodigoIndice() + " fecha: "
					+ form.getTimeRequerido());
		}
		
		return resumen;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenIndicesMM(co
	 * .com.bvc.portal.mercados.vista.form.FormularioIndices)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenIndice> obtenerResumenIndicesMM(FormularioIndices form) {
		List<ResumenIndice> ret = new ArrayList<ResumenIndice>();
		String fecha = form != null ? form.getTimeRequerido() : null;
		if (fecha == null || fecha.trim().length() < 1) {
			fecha = UtilFechas.getHoy("yyyyMMdd");
		}
		

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "RESUMEN_INDICES_MONETARIO."
				+ UtilFechas.getHoy("yyyyMMdd");

		ret = (List<ResumenIndice>) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (List<ResumenIndice>) cache.getObject(queryKey);
				if (ret == null) {

			        try {
						ret = indicesDao.cargarResumenIndicesMM(fecha);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ret;

				}
			}
		} else {
			log.debug("Cargando desde Cargar Home Indices : "
					+ UtilFechas.getHoy("yyyyMMdd"));
		}
		
		
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenIndicesMM(co
	 * .com.bvc.portal.mercados.vista.form.FormularioIndices)
	 */
	public ResumenIndice obtenerResumenIndicesBuscarMM(FormularioIndices form) {
		
		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_60SECS);
		String queryKey = "RESUMEN_INDICE_BUSCAR_MM." + form.getTimeRequerido() + "."
				+ form.getCodigoIndice();

		ResumenIndice resumen = (ResumenIndice) cache.getObject(queryKey);
		if (resumen == null) {
			synchronized (this) {
				resumen = (ResumenIndice) cache.getObject(queryKey);
				if (resumen == null) {
					
					resumen = new ResumenIndice();
					String fecha = form != null ? form.getTimeRequerido() : null;
					if (fecha == null || fecha.trim().length() < 1) {
						fecha = UtilFechas.getHoy("yyyyMMdd");
					}
					try {
						resumen = indicesDao.cargarResumenIndicesBuscarMM(fecha,form.getCodigoIndice());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					resumen.setMercado("MERCADO MONETARIO");
					cache.putObject(queryKey, resumen);
					return resumen;
				}
			}
		} else {
			log.debug("Cargando desde Cache Resumen Buscar MM Indice : "
					+ form.getCodigoIndice() + " fecha: "
					+ form.getTimeRequerido());
		}
		
		return resumen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerContenidoIndice(java
	 * .lang.String, java.lang.String)
	 */
	public String obtenerContenidoIndice(String codigo, String tipoCont) {
		String ret = "";
		try {
			InfoIndicesSectoriales meta = indicesDao
					.obtenerMetadataPorIndice(codigo);
			if (meta != null) {
				if ("generalidades".equals(tipoCont)) {
					ret = meta.getContenidoGeneralidades();
				} else if ("formula".equals(tipoCont)) {
					ret = meta.getContenidoFormulas();
				} else if ("seleccionCanasta".equals(tipoCont)) {
					ret = meta.getContenidoSeleccionCanasta();
				} else {
					ret = meta.getContenidoCalculoPonderacion();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret != null ? ret.replaceAll("[\\r]", "") : "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#salvarMetadataIndice(co.
	 * com.bvc.portal.mercados.modelo.InfoIndicesSectoriales)
	 */
	public void salvarMetadataIndice(InfoIndicesSectoriales infoInd) {
		boolean ponerComa = false;
		String parametro = "";
		if (infoInd.getContenidoGeneralidades() != null
				&& infoInd.getContenidoGeneralidades().trim().length() > 0) {
			parametro += "V_INDACCSEC_CONTENIDO_GENERALIDADES='"
					+ infoInd.getContenidoGeneralidades() + "'";
			ponerComa = true;
		}

		if (infoInd.getContenidoFormulas() != null
				&& infoInd.getContenidoFormulas().trim().length() > 0) {
			if (ponerComa) {
				parametro += ", ";
			}
			parametro += "V_INDACCSEC_CONTENIDO_FORMULA='"
					+ infoInd.getContenidoFormulas() + "'";
			ponerComa = true;
		}
		if (infoInd.getContenidoSeleccionCanasta() != null
				&& infoInd.getContenidoSeleccionCanasta().trim().length() > 0) {
			if (ponerComa) {
				parametro += ", ";
			}
			parametro += "V_INDACCSEC_CONTENIDO_SELECCION_CANASTA='"
					+ infoInd.getContenidoSeleccionCanasta() + "'";
			ponerComa = true;
		}
		if (infoInd.getContenidoCalculoPonderacion() != null
				&& infoInd.getContenidoCalculoPonderacion().trim().length() > 0) {
			if (ponerComa) {
				parametro += ", ";
			}
			parametro += "V_INDACCSEC_CONTENIDO_CALCULO_PONDERACION='"
					+ infoInd.getContenidoCalculoPonderacion() + "'";
			ponerComa = true;
		}
		try {
			indicesDao.salvarInfoIndice(parametro, infoInd.getIndice());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenRangoFecha
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<ResumenIndice> obtenerResumenRangoFecha(String codIndice,
			String fechaMin, String fechaMax) {
		List<ResumenIndice> ret = new ArrayList<ResumenIndice>();
		Map<Integer, Double> mapHace12 = new TreeMap<Integer, Double>();
		try {
			ret = indicesDao.cargarResumenPorIndiceYRangoFecha(codIndice,
					fechaMax, fechaMin);
			mapHace12 = indicesDao.getMapaFechaValorHoy(codIndice,
					obtenerHace12Meses(Integer.parseInt(fechaMax)),
					obtenerHace12Meses(Integer.parseInt(fechaMin)));
			Iterator<Integer> iter = mapHace12.keySet().iterator();
			Integer fechaDisp = iter.hasNext() ? iter.next()
					: Integer.MAX_VALUE;
			Integer fechaSig = iter.hasNext() ? iter.next() : Integer.MAX_VALUE;
			for (Iterator<ResumenIndice> iterator = ret.iterator(); iterator
					.hasNext();) {
				ResumenIndice resumenIndice = (ResumenIndice) iterator.next();
				Integer fec12Atras = resumenIndice.getFechaInt() - 10000;
				while (iter.hasNext()) {
					if (fechaSig <= fec12Atras) {
						fechaDisp = fechaSig;
						fechaSig = iter.next();
					} else {
						break;
					}
				}
				resumenIndice
						.setValor12Meses(mapHace12.get(fechaDisp) == null ? 0d
								: mapHace12.get(fechaDisp));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenRangoFechaRF
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<ResumenIndice> obtenerResumenRangoFechaRF(String codIndice,
			String fechaMin, String fechaMax) {
		List<ResumenIndice> ret = new ArrayList<ResumenIndice>();
		Map<Integer, Double> mapHace12 = new TreeMap<Integer, Double>();
		try {
			ret = indicesDao.cargarResumenPorIndiceYRangoFechaRF(codIndice,fechaMax, fechaMin);
			mapHace12 = indicesDao.getMapaFechaValorHoyRF(codIndice,
					obtenerHace12Meses(Integer.parseInt(fechaMax)),
					obtenerHace12Meses(Integer.parseInt(fechaMin)));
			Iterator<Integer> iter = mapHace12.keySet().iterator();
			Integer fechaDisp = iter.hasNext() ? iter.next()
					: Integer.MAX_VALUE;
			Integer fechaSig = iter.hasNext() ? iter.next() : Integer.MAX_VALUE;
			for (Iterator<ResumenIndice> iterator = ret.iterator(); iterator
					.hasNext();) {
				ResumenIndice resumenIndice = (ResumenIndice) iterator.next();
				Integer fec12Atras = resumenIndice.getFechaInt() - 10000;
				while (iter.hasNext()) {
					if (fechaSig <= fec12Atras) {
						fechaDisp = fechaSig;
						fechaSig = iter.next();
					} else {
						break;
					}
				}
				resumenIndice
						.setValor12Meses(mapHace12.get(fechaDisp) == null ? 0d
								: mapHace12.get(fechaDisp));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#obtenerResumenRangoFechaMM
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<ResumenIndice> obtenerResumenRangoFechaMM(String codIndice,
			String fechaMin, String fechaMax) {
		List<ResumenIndice> ret = new ArrayList<ResumenIndice>();
		Map<Integer, Double> mapHace12 = new TreeMap<Integer, Double>();
		try {
			ret = indicesDao.cargarResumenPorIndiceYRangoFechaMM(codIndice,fechaMax, fechaMin);
			mapHace12 = indicesDao.getMapaFechaValorHoyRF(codIndice,
					obtenerHace12Meses(Integer.parseInt(fechaMax)),
					obtenerHace12Meses(Integer.parseInt(fechaMin)));
			Iterator<Integer> iter = mapHace12.keySet().iterator();
			Integer fechaDisp = iter.hasNext() ? iter.next()
					: Integer.MAX_VALUE;
			Integer fechaSig = iter.hasNext() ? iter.next() : Integer.MAX_VALUE;
			for (Iterator<ResumenIndice> iterator = ret.iterator(); iterator
					.hasNext();) {
				ResumenIndice resumenIndice = (ResumenIndice) iterator.next();
				Integer fec12Atras = resumenIndice.getFechaInt() - 10000;
				while (iter.hasNext()) {
					if (fechaSig <= fec12Atras) {
						fechaDisp = fechaSig;
						fechaSig = iter.next();
					} else {
						break;
					}
				}
				resumenIndice
						.setValor12Meses(mapHace12.get(fechaDisp) == null ? 0d
								: mapHace12.get(fechaDisp));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#getMensajeMercadoAbierto
	 * (co.com.bvc.portal.mercados.vista.form.FormularioIndices)
	 */
	public String getMensajeMercadoAbierto(FormularioIndices form) {
		GregorianCalendar cal = new GregorianCalendar();
		String fechaHoy = UtilFechas.getHoy("yyyyMMdd");
		if (form != null) {
			String fechaCons = form.getAnio() + form.getMes() + form.getDia();
			if (!fechaHoy.equals(fechaCons)) {
				cal = new GregorianCalendar(Integer.parseInt(form.getAnio()),
						Integer.parseInt(form.getMes()) - 1,
						Integer.parseInt(form.getDia()));
			}
		}
		try {
			return servicioMercadosUtil.getMensajeMercadoAbierto(cal,
					IMercadoDao.ACCIONES);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Gets the indices dao.
	 * 
	 * @return the indices dao
	 */
	public IIndiceDao getIndicesDao() {
		return indicesDao;
	}

	/**
	 * Sets the indices dao.
	 * 
	 * @param indicesDao
	 *            the new indices dao
	 */
	public void setIndicesDao(IIndiceDao indicesDao) {
		this.indicesDao = indicesDao;
	}

	/**
	 * Gets the servicio mercados util.
	 * 
	 * @return the servicio mercados util
	 */
	public IServicioMercadosUtil getServicioMercadosUtil() {
		return servicioMercadosUtil;
	}

	/**
	 * Sets the servicio mercados util.
	 * 
	 * @param servicioMercadosUtil
	 *            the new servicio mercados util
	 */
	public void setServicioMercadosUtil(
			IServicioMercadosUtil servicioMercadosUtil) {
		this.servicioMercadosUtil = servicioMercadosUtil;
	}

	/**
	 * Gets the fecha inicio indices recientes.
	 * 
	 * @return the fecha inicio indices recientes
	 */
	public Integer getFechaInicioIndicesRecientes() {
		return fechaInicioIndicesRecientes;
	}

	/**
	 * Sets the fecha inicio indices recientes.
	 * 
	 * @param fechaInicioIndicesRecientes
	 *            the new fecha inicio indices recientes
	 */
	public void setFechaInicioIndicesRecientes(
			Integer fechaInicioIndicesRecientes) {
		this.fechaInicioIndicesRecientes = fechaInicioIndicesRecientes;
	}

	/**
	 * Gets the indices recientes.
	 * 
	 * @return the indices recientes
	 */
	public List<String> getIndicesRecientes() {
		return indicesRecientes;
	}

	/**
	 * Sets the indices recientes.
	 * 
	 * @param indicesRecientes
	 *            the new indices recientes
	 */
	public void setIndicesRecientes(List<String> indicesRecientes) {
		this.indicesRecientes = indicesRecientes;
	}

	/**
	 * Obtener hace12 meses.
	 * 
	 * @param form
	 *            the form
	 * 
	 * @return the string
	 */
	private String obtenerHace12Meses(FormularioIndices form) {
		return obtenerHace12Meses(Integer.valueOf(form.getTimeRequerido()));
	}

	/**
	 * Obtener hace12 meses.
	 * 
	 * @param fecha
	 *            the fecha
	 * 
	 * @return the string
	 */
	private String obtenerHace12Meses(Integer fecha) {
		Integer fechaHace12Meses = fecha - 10000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = new GregorianCalendar();
		try {
			cal.setTime(sdf.parse(fechaHace12Meses.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cal = servicioUtil.getUltimoDiaHabilBursatil(cal);
		return sdf.format(cal.getTime());
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
	 * @param servicioUtil
	 *            the new servicio util
	 */
	public void setServicioUtil(IServicioUtil servicioUtil) {
		this.servicioUtil = servicioUtil;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IIndices#compararIndices(java.lang
	 * .String, java.lang.String, java.util.List, java.lang.String)
	 */
	public String compararIndices(String nemo, String nombreMostrar,
			List<String> indicesAMostrar, String tipoGrafico, String tiempo, String tipomercado, List<String> mercados) {

		log.debug("Ingresó a la función compararIndices  conf= "+nemo);
		Map<String, String> indices = null;
		indices = obtenerPosiblesIndices();
		String urlFile = "/mercados/GraficosServlet?home=no&tipo="
				+ tipoGrafico + "&mercInd="+ tipomercado + "&nemo=" + nemo;
		if (tiempo != null && tiempo.trim().length() > 0) {
			urlFile += "&tiempo=" + tiempo;
		}
		String comparaciones = "";
		comparaciones = comparaciones + "<data_set>\n\t"
				+ "<main_drop_down selected='true'></main_drop_down>\n\t"
				+ "<compare_list_box selected='false'></compare_list_box>\n\t"
				+ "<title>" + nombreMostrar + "</title>" + "<short>"
				+ nombreMostrar + "</short>\n\t" + "<color>4682B4</color>\n\t"
				+ "<file_name>" + urlFile + "</file_name>\n\t" + "<csv>\n\t\t"
				+ "<reverse>true</reverse>\n\t\t"
				+ "<separator>,</separator>\n\t\t"
				+ "<date_format>YYYY-MM-DD hh:mm</date_format>\n\t\t"
				+ "<decimal_separator>.</decimal_separator>\n\t\t"
				+ "<columns>\n\t\t\t" + "<column>date</column>\n\t\t\t"
				+ "<column>close</column>\n\t\t\t"
				+ "<column>volume</column>\n\t\t\t"
				+ "<column>last</column>\n\t\t" + "</columns>\n\t" + "</csv>\n"
				+ "</data_set>\n";
		//log.debug(comparaciones);

		for (Iterator<String> iter = indices.keySet().iterator(); iter
				.hasNext();) {
			String nemoActual = iter.next();
			String nombreMostrarActual = indices.get(nemoActual);
			String mercInd = "RV";
			String mercRV = "";
			String mercRF = "";
			String mercMM = "";
			if (!nemo.equalsIgnoreCase(nemoActual)) {
				if (indicesAMostrar != null
						&& !indicesAMostrar.contains(nemoActual)) {
					continue;
				}
				mercRV=nemoActual+"RV";
				mercRF=nemoActual+"RF";
				mercMM=nemoActual+"MM";
				if ("ACCION".equalsIgnoreCase(tipoGrafico)
						|| "BVC".equalsIgnoreCase(tipoGrafico)) {
					if (mercados.contains(mercRV) || mercados.contains(mercRF) || mercados.contains(mercMM)) {
						if (mercados.contains(mercRV)) {mercInd = "RV";}
						else if (mercados.contains(mercRF)) {mercInd = "RF";}
						else if (mercados.contains(mercMM)){ mercInd = "MM";}
					}
				}
				String urlComp = "/mercados/GraficosServlet?tipo=INDICE&home=no&nemo="
						+ nemoActual + "&mercInd=" + mercInd;
				if (tiempo != null && tiempo.trim().length() > 0) {
					urlComp += "&tiempo=" + tiempo ;
				}
				comparaciones = comparaciones
						+ "<data_set>\n\t"
						+ "<main_drop_down selected='false'>false</main_drop_down>\n\t"
						+ "<title>" + nombreMostrarActual + "</title>"
						+ "<short>" + nombreMostrarActual + "</short>\n\t"
						+ "<file_name>" + urlComp + "</file_name>\n\t\t"
						+ "<csv>\n\t\t\t" + "<reverse>true</reverse>\n\t\t\t"
						+ "<separator>,</separator>\n\t\t\t"
						+ "<date_format>YYYY-MM-DD hh:mm</date_format>\n\t\t\t"
						+ "<decimal_separator>.</decimal_separator>\n\t\t\t"
						+ "<columns>\n\t\t\t\t" + "<column>date</column>\n"
						+ "<column>close</column>\n\t\t\t\t"
						+ "<column>volume</column>\n\t\t\t\t"
						+ "<column>last</column>\n\t\t\t" + "</columns>\n\t\t"
						+ "</csv>\n\t" + "</data_set>\n";
			}
		}

		ArrayList<String> acciones = null;
		try {
			if (tipoGrafico.equalsIgnoreCase("ACCIONMGC")
					|| tipoGrafico.equalsIgnoreCase("ETFMGC")) {
				acciones = this.accionesMGCDao.getAccionesActivas();
			} else
				acciones = this.accionesDao.getAccionesActivas();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		Iterator<String> ita = acciones.iterator();

		while (ita.hasNext()) {
			String nemoActual = (String) ita.next();
			if (!nemo.equalsIgnoreCase(nemoActual)) {
				String urlComp = "/mercados/GraficosServlet?mercInd=RV&tipo="
						+ tipoGrafico + "&home=no&nemo=" + nemoActual;
				if (tiempo != null && tiempo.trim().length() > 0) {
					urlComp += "&tiempo=" + tiempo;
				}
				comparaciones = comparaciones
						+ "<data_set>\n\t"
						+ "<main_drop_down selected='false'>false</main_drop_down>\n\t"
						+ "<title>" + nemoActual + "</title>" + "<short>"
						+ nemoActual + "</short>\n\t" + "<file_name>" + urlComp
						+ "</file_name>\n\t" + "<csv>\n\t\t"
						+ "<reverse>true</reverse>\n\t\t"
						+ "<separator>,</separator>\n\t\t"
						+ "<date_format>YYYY-MM-DD hh:mm</date_format>\n\t\t"
						+ "<decimal_separator>.</decimal_separator>\n\t\t"
						+ "<columns>\n\t\t\t" + "<column>date</column>\n\t\t\t"
						+ "<column>close</column>\n\t\t\t"
						+ "<column>volume</column>\n\t\t\t"
						+ "<column>last</column>\n\t\t" + "</columns>\n\t"
						+ "</csv>\n" + "</data_set>\n";
			}

		}// System.out.print("    comparaciones: "+comparaciones);
		return comparaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IIndices#getIndicesListar()
	 */
	public List<IndicesAutocomplete> getIndicesListar() {

		List<String> ret = new ArrayList<String>();
		List<IndicesAutocomplete> res = new ArrayList<IndicesAutocomplete>();

		try {
			ret = this.indicesDao.getIndicesComparacion();
		} catch (PersistenciaException e) {
		}

		Iterator<String> it = ret.iterator();
		while (it.hasNext()) {
			String tmp = (String) it.next();
			IndicesAutocomplete itmp = new IndicesAutocomplete();
			itmp.setNemo(tmp);
			res.add(itmp);
		}
		return res;
	}

	/**
	 * Gets the acciones dao.
	 * 
	 * @return the acciones dao
	 */
	public IAccionesDao getAccionesDao() {
		return accionesDao;
	}

	/**
	 * Sets the acciones dao.
	 * 
	 * @param accionesDao
	 *            the new acciones dao
	 */
	public void setAccionesDao(IAccionesDao accionesDao) {
		this.accionesDao = accionesDao;
	}

	/**
	 * Gets the acciones dao.
	 * 
	 * @return the acciones dao
	 */
	public IAccionesMGCDao getAccionesMGCDao() {
		return accionesMGCDao;
	}

	/**
	 * Sets the acciones dao.
	 * 
	 * @param accionesDao
	 *            the new acciones dao
	 */
	public void setAccionesMGCDao(IAccionesMGCDao accionesMGCDao) {
		this.accionesMGCDao = accionesMGCDao;
	}
}
/***********************************************End of Indices.java**********************************************************/