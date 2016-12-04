package co.com.bvc.portal.mercados.servicio.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.log4j.Logger;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.servicio.IServicioUtil;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.AccionMGCComparador;
import co.com.bvc.portal.mercados.modelo.AccionesMGCBusquedaForm;
import co.com.bvc.portal.mercados.modelo.CotizaPaisMGC;
import co.com.bvc.portal.mercados.modelo.EtfsMGCComparador;
import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.modelo.HorarioMercadoMGC;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.IntradiaTitulo;
import co.com.bvc.portal.mercados.modelo.IntradiaTituloMGC;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.ResumenEtfsMGC;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.modelo.VolumenAccionMGC;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCReposDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCTTVDao;
import co.com.bvc.portal.mercados.servicio.IAccionesMGC;
import co.com.bvc.portal.mercados.util.IConstantesAcciones;

/**
 * @author BVC
 * @description The class AccionMGCServicio
 * 
 */

public class AccionMGCServicio implements IAccionesMGC{

	/** The acciones dao. */ 
	private IAccionesMGCDao accionesMGCDao;

	/** The acciones repos dao. */
	private IAccionesMGCReposDao accionesMGCReposDao;

	/** The acciones ttv dao. */
	private IAccionesMGCTTVDao accionesMGCTTVDao;

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The servicio util. */
	private IServicioUtil servicioUtil;

	/**
	 * Gets the acciones repos dao.
	 * 
	 * @return the acciones repos dao 
	 */
	public IAccionesMGCReposDao getAccionesMGCReposDao() {
		return accionesMGCReposDao;
	}

	/**
	 * Sets the acciones repos dao.
	 * 
	 * @param accionesReposDao the new acciones repos dao
	 */
	public void setAccionesMGCReposDao(IAccionesMGCReposDao accionesMGCReposDao) {
		this.accionesMGCReposDao = accionesMGCReposDao;
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
	 * @param accionesDao the new acciones dao
	 */
	public void setAccionesMGCDao(IAccionesMGCDao accionesMGCDao) {
		this.accionesMGCDao = accionesMGCDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAccionesMGC#cargarDatosTituloDia(java
	 * .lang.String, java.lang.String, int, java.util.List)
	 */
	
	/*
	 * "BVC", null, IAcciones.TIPO_MERCADO_ACCIONES_MGC, listOp,
					false
	 */
	public TituloAccion cargarDatosTituloDia(String nemo, String fecha,
			int tipoMercado, List<OperacionDiaAcciones> operaciones, boolean delay)
			throws Exception {

		TituloAccion taPrecios;
		TituloAccion ret;

		if (TIPO_MERCADO_ACCIONES_MGC == tipoMercado) {
			taPrecios = cargaDatosValoresCPTitulo(operaciones);
		} else {
			taPrecios = cargaDatosValoresCPTitulo(nemo, fecha, delay);
		}

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "DATOSMGC_ACCION." + UtilFechas.getHoy("yyyyMMdd") + "."
				+ nemo;

		ret = (TituloAccion) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (TituloAccion) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Datos Accion : " + nemo);
					ret = accionesMGCDao.cargarDatosTituloUltimoDia(nemo);
					cache.putObject(queryKey, ret);
				}
			}
		} else {
			log.debug("Cargando desde Cache datos Accion :" + nemo);
		}

		IntradiaTituloMGC intTit = accionesMGCDao.ultimoPrecioAccion(
				(fecha == null || "".equals(fecha.trim())) ? null : fecha
						.replaceAll("-", ""), nemo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = new GregorianCalendar();
		if (intTit != null && intTit.getPrecio() != null){
			ret.setPrecioCierreAnterior(intTit.getPrecio());
			cal.setTime(sdf.parse(intTit.getHora()));
		}else{
			ret.setPrecioCierreAnterior(ret.getPrecioCierre());
		}
		ret.setHora(cal);

		ret.setPrecioCierre(taPrecios.getPrecioCierre() == 0 ? ret
				.getPrecioCierreAnterior() : taPrecios.getPrecioCierre());
		ret.setCantidad(taPrecios.getCantidad());
		ret.setVolumen(taPrecios.getVolumen());
		ret.setPrecioMenor(taPrecios.getPrecioMenor());
		ret.setPrecioMayor(taPrecios.getPrecioMayor());
		ret.setPrecioMedio(taPrecios.getPrecioMedio());
		if (ret.getPrecioCierreAnterior() != 0d) {
			ret.setVariacion(((ret.getPrecioCierre() / ret
					.getPrecioCierreAnterior()) - 1) * 100);
		}
		if (taPrecios.getHora() != null) {
			ret.setHora(taPrecios.getHora());
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#cargarDatosTituloDia(java.lang.String, int, java.lang.String, java.lang.String)
	 */
	public List<TituloAccion> cargarDatosTituloDia(String nemo,
			int tipoMercado, String fechaIni, String fechaFin) throws Exception {
		if (tipoMercado == TIPO_MERCADO_ACCIONES_MGC) {

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = new GregorianCalendar();
			cal.setTime(sdf.parse(fechaIni));
			cal.add(Calendar.DAY_OF_YEAR, -1);

			List<TituloAccion> titAcciones = this.accionesMGCDao
					.cargarDatosTitulo(nemo, sdf.format(servicioUtil
							.getUltimoDiaHabilBursatil(cal).getTime()),
							fechaFin);

			if (!titAcciones.isEmpty() && titAcciones != null) {

				Iterator<TituloAccion> iter = titAcciones.iterator();

				TituloAccion titPas = iter.next();

				while (iter.hasNext()) {
					TituloAccion titAct = iter.next();
					titAct.setPrecioCierreAnterior(titPas.getPrecioCierre());
					titAct.setVarAbsoluta(null);
					titPas = titAct;
				}
			}

			titAcciones.remove(0);
			return titAcciones;
		}
		if (tipoMercado == TIPO_MERCADO_REPOS_MGC) {
			return this.accionesMGCReposDao.cargaDatosTituloFecha(nemo, fechaIni,
					fechaFin);
		}
		if (tipoMercado == TIPO_MERCADO_TTV_MGC) {
			return this.accionesMGCTTVDao.cargaDatosTituloFecha(nemo, fechaIni,
					fechaFin);
		}
		return new ArrayList<TituloAccion>();

	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#cargarHistoricoTitulo(java.lang.String)
	 */
	public List<ICierre> cargarHistoricoTitulo(String titulo)
			throws PersistenciaException {
		return this.accionesMGCDao.cargarHistoricoTitulo(titulo);
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#operacionesDia(java.lang.String, java.lang.String, int, boolean)
	 */
	/*
	 * "BVC", null,
							IAcciones.TIPO_MERCADO_COMPRAVENTAS, false
	 */
	public List<OperacionDiaAcciones> operacionesDia(String nemo, String fecha,
			int tipoMercado, boolean delay) throws Exception {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (sdf.format(new Date()).equals(fecha)) {
			fecha = null;
		}

		if (tipoMercado == TIPO_MERCADO_ACCIONES_MGC) {
			return this.accionesMGCDao.operacionesDia(nemo, fecha, delay);
		}

		if (tipoMercado == TIPO_MERCADO_REPOS_MGC) {
			log.info("operaciones dia repos");
			if (fecha == null) {
				return this.accionesMGCReposDao.operacionesUltimoDia(nemo);
			}
			return this.accionesMGCReposDao.operaciones(nemo, fecha);
		}
		
		if (tipoMercado == TIPO_MERCADO_TTV_MGC) {
			log.info("operaciones dia ttvs");
			if (fecha == null) {
				return this.accionesMGCTTVDao.operacionesUltimoDia(nemo);
			}
			return this.accionesMGCTTVDao.operaciones(nemo, fecha);
		}

		return new ArrayList<OperacionDiaAcciones>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAccionesMGC#cargarVolumenDia(java.lang
	 * .String)
	 */
	public List<VolumenAccionMGC> cargarVolumenDia(String fecha) throws Exception {
		List<VolumenAccionMGC> respuesta = new ArrayList<VolumenAccionMGC>();
		VolumenAccionMGC vol1, vol2, vol3, vol4,vol5;

		double[] volumenes = accionesMGCDao.obtenerVolumenesNegociados(fecha);

		vol1 = new VolumenAccionMGC();
		vol1.setDescripcionVolumen(IConstantesAcciones.NOMBRE_ACCIONES_MGC);
		vol1.setVolumen(volumenes[0]);
		respuesta.add(vol1);

		vol2 = new VolumenAccionMGC();
		vol2.setDescripcionVolumen(IConstantesAcciones.NOMBRE_ETF_MGC);
		vol2.setVolumen(volumenes[3]);
		respuesta.add(vol2);

		/*vol3 = new VolumenAccionMGC();
		vol3.setDescripcionVolumen(IConstantesAcciones.NOMBRE_TTV_MGC);
		vol3.setVolumen(volumenes[2]);
		respuesta.add(vol3);
		
		vol5 = new VolumenAccionMGC();
		vol5.setDescripcionVolumen(IConstantesAcciones.NOMBRE_REPOS_MGC);
		vol5.setVolumen(volumenes[1]);
		respuesta.add(vol5);*/

		double suma = 0d;

		for (double v : volumenes) {
			suma += v;
		}

		if (suma > 0) {
			vol1.setPorcentajeParticipacion((vol1.getVolumen() / suma) * 100);
			vol2.setPorcentajeParticipacion((vol2.getVolumen() / suma) * 100);
			//vol3.setPorcentajeParticipacion((vol3.getVolumen() / suma) * 100);
			//vol5.setPorcentajeParticipacion((vol5.getVolumen() / suma) * 100);
		}

		vol4 = new VolumenAccionMGC();
		vol4.setDescripcionVolumen("Total");
		vol4.setVolumen(suma);
		vol4.setPorcentajeParticipacion(100);
		respuesta.add(vol4);

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAccionesMGC#accionesMasTranzadasDia
	 * (java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> accionesMasTranzadasDia(String fecha,
			int resultados, int tipoMercado) throws Exception {
		log.info("accionesMGCMasTranzadasDia fecha: " + fecha + " tipoMercado: "
				+ tipoMercado);

		List<ResumenAccionMGC> ret;
		String fechAux = "";

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "ACCIONESMGC_MAS_TRANZADAS."
				+ ((null == fecha) ? UtilFechas.getHoy("yyyyMMdd") : fecha
						.replaceAll("-", "")) + "." + tipoMercado + "."
				+ resultados;

		ret = (List<ResumenAccionMGC>) cache.getObject(queryKey); // ACCIONES-015
		if (null == ret) {
			synchronized (this) {
				ret = (List<ResumenAccionMGC>) cache.getObject(queryKey);
				if (null == ret) {
					log.debug("Procesando AccionesMGC mas Tranzadas Dia fecha : " + fecha + " tipo Mercado:" + tipoMercado);
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					if (sdf.format(new Date()).equalsIgnoreCase(fecha)) {
						fecha = null;
					}
					if (IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC == tipoMercado) {
						ret = this.accionesMGCDao.accionesMasTranzadasDia(fecha);
						if(fecha!=null){
							fechAux=fecha.replaceAll("-", "");
						}
						if (ret != null && ret.size() > 0) {
							for (ResumenAccionMGC res : ret) {
								IntradiaTituloMGC iT =  accionesMGCDao.ultimoPrecioAccion(fechAux,res.getNemoTecnico());
								double valorAyer = res.getUltimoPrecio();
								if (null != iT && null != iT.getPrecio()){
									valorAyer = iT.getPrecio();
								}
								res.setCierreAnterior(valorAyer);
								if (res.getUltimoPrecio() == 0) {
									res.setUltimoPrecio(valorAyer);
								}
								double var = (res.getUltimoPrecio()	/ res.getCierreAnterior() - 1) * 100;
								res.setVariacion(Double.isNaN(var)? 0d: var );
								List<ResumenAccionMGC> respEmr = this.accionesMGCDao.consultaDatosEmisor(res.getNemoTecnico());
								if(respEmr.isEmpty()){
									res.setIdEmisor("");
									res.setNombreEmr("");
								}else{
								    res.setIdEmisor(respEmr.get(0).getIdEmisor());
								    res.setNombreEmr(respEmr.get(0).getNombreEmr());
								}
							}
						}

						cache.putObject(queryKey, ret);
						return ret;
					}

					if (TIPO_MERCADO_REPOS_MGC == tipoMercado) {
						if (fecha == null || fecha.equalsIgnoreCase("")) {
							ret = this.accionesMGCReposDao
									.cargarMasTranzadasDiaUltDia();
							cache.putObject(queryKey, ret);
							return ret;
						}

						ret = this.accionesMGCReposDao
								.cargarMasTranzadasDia(fecha);
						cache.putObject(queryKey, ret);
						return ret;
					}
					if (TIPO_MERCADO_TTV_MGC == tipoMercado) {
						if (fecha == null || fecha.equalsIgnoreCase("")) {
							ret = this.accionesMGCTTVDao.cargarMasTranzadasDiaUltDia();
							cache.putObject(queryKey, ret);
							return ret;
						}

						ret = this.accionesMGCTTVDao.cargarMasTranzadasDia(fecha);
						cache.putObject(queryKey, ret);
						return ret;
					}

					ret = new ArrayList<ResumenAccionMGC>();
					cache.putObject(queryKey, ret);
					return ret;

				}
			}
		} else {
			log.debug("Cargando desde cache Acciones mas Tranzadas Dia fecha : "
					+ fecha + " tipo Mercado:" + tipoMercado);
		}
		return ret;

	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAcciones#accionesMasTranzadasHome
	 * (java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<ResumenAccionMGC>> accionesMasTranzadasHome(String fecha,
			int resultados, int tipoMercado) throws Exception {

		log.info("accionesMasTranzadasHome fecha: " + fecha + " tipoMercado: "
				+ tipoMercado);

		List<ResumenAccionMGC> ret;
		Map<String, List<ResumenAccionMGC>> listas = new LinkedMap();
		Map<String, List<ResumenAccionMGC>> listas2 = new LinkedMap();
		
		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "ACCIONES_MGC_MASTRANZADAS_HOME."
				+ ((fecha == null) ? UtilFechas.getHoy("yyyyMMdd") : fecha
						.replaceAll("-", "")) + "." + tipoMercado + "."
				+ resultados;

		listas.put("cacheMGC", (List<ResumenAccionMGC>) cache.getObject(queryKey)); // ACCIONES-015
		if (listas.get("cacheMGC") == null) {
			synchronized (this) {
				listas.put("cacheMGC", (List<ResumenAccionMGC>) cache.getObject(queryKey));
				if (listas.get("cacheMGC") == null) {
					log.debug("Procesando Acciones mas Tranzadas Dia MGC fecha : "
							+ fecha + " tipo Mercado:" + tipoMercado);
					

						ret = this.accionesMGCDao.accionesMasTranzadasHome(fecha);
						
						if (ret != null && ret.size() > 0) {
							for (ResumenAccionMGC res : ret) {
								IntradiaTituloMGC iT =  accionesMGCDao.ultimoPrecioAccion(fecha,res.getNemoTecnico());
								double valorAyer = res.getUltimoPrecio();
								if (null != iT && null != iT.getPrecio()){
									valorAyer = iT.getPrecio();
								}
								res.setCierreAnterior(valorAyer);
								if (res.getUltimoPrecio() == 0) {
									res.setUltimoPrecio(valorAyer);
								}
								double var = (res.getUltimoPrecio()	/ res.getCierreAnterior() - 1) * 100;
								res.setVariacion(Double.isNaN(var)? 0d: var );
								List<ResumenAccionMGC> respEmr = this.accionesMGCDao.consultaDatosEmisor(res.getNemoTecnico());
								if(respEmr.isEmpty()){
									res.setIdEmisor("");
									res.setNombreEmr("");
								}else{
								    res.setIdEmisor(respEmr.get(0).getIdEmisor());
								    res.setNombreEmr(respEmr.get(0).getNombreEmr());
								}
							}
						}
									
		
						List<ResumenAccionMGC> accionesSuben = new ArrayList<ResumenAccionMGC>(ret);
						List<ResumenAccionMGC> accionesBajan = new ArrayList<ResumenAccionMGC>(ret);
		
						Collections.sort(accionesSuben, new AccionMGCComparador(AccionMGCComparador.SUBE));
						Collections.sort(accionesBajan, new AccionMGCComparador(AccionMGCComparador.BAJA));
		
		
	
						
						if (ret != null && ret.size() > 5){
							listas2.put("listaAccionesMGC", ret.subList(0, 5) );
						}else{
						    listas2.put("listaAccionesMGC", ret);
						}
						if (accionesSuben != null && accionesSuben.size() > 5){
							listas2.put("listaAccionesMGCSube", this.sacarAcciones(AccionMGCComparador.SUBE, accionesSuben.subList(0, 5) ));
						}else{
							listas2.put("listaAccionesMGCSube", this.sacarAcciones(AccionMGCComparador.SUBE, accionesSuben));
						}
						if (accionesBajan != null && accionesBajan.size() > 5){
							listas2.put("listaAccionesMGCBaja", this.sacarAcciones(AccionMGCComparador.BAJA,  accionesBajan.subList(0, 5) ));
						}else{
							listas2.put("listaAccionesMGCBaja", this.sacarAcciones(AccionMGCComparador.BAJA,  accionesBajan));
						}
						
						listas=listas2;
						
						cache.putObject(queryKey, listas2.get("listaAccionesMGC"));
						cache.putObject("listaAccionesMGCSube", listas2.get("listaAccionesMGCSube"));
						cache.putObject("listaAccionesMGCBaja", listas2.get("listaAccionesMGCBaja"));
						return listas;
		
				}
			}
		} else {
			listas.put("listaAccionesMGC", (List<ResumenAccionMGC>) cache.getObject(queryKey));
			listas.put("listaAccionesMGCSube", (List<ResumenAccionMGC>) cache.getObject("listaAccionesMGCSube"));
			listas.put("listaAccionesMGCBaja", (List<ResumenAccionMGC>) cache.getObject("listaAccionesMGCBaja"));
			log.debug("Cargando desde cache Acciones mas Tranzadas Dia MGC fecha : "
					+ fecha + " tipo Mercado:" + tipoMercado);
		}
		
		return listas;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#sacarAcciones(int,
	 * java.util.List)
	 */
	public List<ResumenAccionMGC> sacarAcciones(int tipoLista,
			List<ResumenAccionMGC> lista) {
		boolean sube = (AccionMGCComparador.SUBE == tipoLista);
		for (int i = 0; i < lista.size();) {
			ResumenAccionMGC resumenAccion = (ResumenAccionMGC) lista.get(i);
			if (sube) {
				if (resumenAccion.getVariacion() <= 0) {
					lista.remove(i);
				} else {
					i++;
				}
			} else {
				if (resumenAccion.getVariacion() >= 0) {
					lista.remove(i);
				} else {
					i++;
				}
			}
		}
		return lista;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#sacarAccionesETF(int,
	 * java.util.List)
	 */
	public List<ResumenEtfsMGC> sacarAccionesETF(int tipoLista,
			List<ResumenEtfsMGC> lista) {
		boolean sube = (AccionMGCComparador.SUBE == tipoLista);
		for (int i = 0; i < lista.size();) {
			ResumenEtfsMGC resumenAccion = (ResumenEtfsMGC) lista.get(i);
			if (sube) {
				if (resumenAccion.getVariacion() <= 0) {
					lista.remove(i);
				} else {
					i++;
				}
			} else {
				if (resumenAccion.getVariacion() >= 0) {
					lista.remove(i);
				} else {
					i++;
				}
			}
		}
		return lista;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#sacarEtfs(int,
	 * java.util.List)
	 */
	public List<ResumenEtfsMGC> sacarEtfs(int tipoLista,
			List<ResumenEtfsMGC> lista) {
		boolean sube = (EtfsMGCComparador.SUBE == tipoLista);
		for (int i = 0; i < lista.size();) {
			ResumenEtfsMGC resumenAccion = (ResumenEtfsMGC) lista.get(i);
			if (sube) {
				if (resumenAccion.getVariacion() <= 0) {
					lista.remove(i);
				} else {
					i++;
				}
			} else {
				if (resumenAccion.getVariacion() >= 0) {
					lista.remove(i);
				} else {
					i++;
				}
			}
		}
		return lista;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAccionesMGC#listadoAcccionesETFMGC
	 * (java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> listadoAccionesETFMGC(String fecha,
			int resultados, int tipoMercado) throws Exception {

		log.info("listadoAcccionesETFMGC fecha: " + fecha + " tipoMercado: "
				+ tipoMercado);
        
		List<ResumenAccionMGC> ret;

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "LISTADO_ACCIONES_ETF_MGC."
				+ ((fecha == null) ? UtilFechas.getHoy("yyyyMMdd") : fecha
						.replaceAll("-", "")) + "." + tipoMercado + "."
				+ resultados;

		ret = (List<ResumenAccionMGC>) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (List<ResumenAccionMGC>) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Listado de Acciones y ETF's  fecha : "
							+ fecha + " tipo Mercado:" + tipoMercado);
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					if (sdf.format(new Date()).equalsIgnoreCase(fecha)) {
						fecha = null;
					}

					if (IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC == tipoMercado) {
						ret = this.accionesMGCDao.listaAccionesMGC();
					}else ret = this.accionesMGCDao.listaEtfMGC();
					
						if ((fecha == null || "".equals(fecha.trim()))
								&& ret != null && ret.size() > 0) {
							
							for (ResumenAccionMGC res : ret) {
								/*IntradiaTituloMGC iT =  accionesMGCDao
													.ultimoPrecioAccion(fecha,
										res.getNemoTecnico());
								double valorAyer = res.getUltimoPrecio();
								if (iT != null && iT.getPrecio() != null){
									valorAyer = iT.getPrecio();
								}
								res.setCierreAnterior(valorAyer);
								if (res.getUltimoPrecio() == 0) {
									res.setUltimoPrecio(valorAyer);
								}
								double var = (res.getUltimoPrecio()
										/ res.getCierreAnterior() - 1) * 100;
								res.setVariacion(Double.isNaN(var)? 0d: var );*/
								List<ResumenAccionMGC> respEmr = this.accionesMGCDao.consultaDatosEmisor(res.getNemoTecnico());
								if(respEmr.isEmpty()){
									res.setIdEmisor("");
									res.setNombreEmr("");
								}else{
								    res.setIdEmisor(respEmr.get(0).getIdEmisor());
								    res.setNombreEmr(respEmr.get(0).getNombreEmr());
								}
							}
						}

						cache.putObject(queryKey, ret);
						return ret;
					//}

					/*if (TIPO_MERCADO_ETF_MGC == tipoMercado) {
						if (fecha == null || fecha.equalsIgnoreCase("")) {
							ret = this.accionesMGCReposDao
									.cargarMasTranzadasDiaUltDia();
							cache.putObject(queryKey, ret);
							return ret;
						}

						ret = this.accionesMGCReposDao
								.cargarMasTranzadasDia(fecha);
						cache.putObject(queryKey, ret);
						return ret;
					}

					ret = new ArrayList<ResumenAccionMGC>();
					cache.putObject(queryKey, ret);
					return ret;*/

				}
			}
		} else {
			log.debug("Cargando desde cache Acciones mas Tranzadas Dia fecha : "
					+ fecha + " tipo Mercado:" + tipoMercado);
		}
		return ret;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAccionesMGC#listadoTotalAccionesETFMGC
	 * (java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> listadoTotalAccionesETFMGC(String fecha,
			int resultados, int tipoMercado) throws Exception {

		log.info("listadoAcccionesETFMGC fecha: " + fecha + " tipoMercado: "
				+ tipoMercado);

		List<ResumenAccionMGC> ret;

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "LISTADO_TOTALACCIONES_ETF_MGC."
				+ ((fecha == null) ? UtilFechas.getHoy("yyyyMMdd") : fecha
						.replaceAll("-", "")) + "." + tipoMercado + "."
				+ resultados;

		ret = (List<ResumenAccionMGC>) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (List<ResumenAccionMGC>) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Listado de Acciones y ETF's  fecha : "
							+ fecha + " tipo Mercado:" + tipoMercado);
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					if (sdf.format(new Date()).equalsIgnoreCase(fecha)) {
						fecha = null;
					}

					ret = this.accionesMGCDao.listaTotalAccionesEtf();
						if ((fecha == null || "".equals(fecha.trim()))
								&& ret != null && ret.size() > 0) {
							
							for (ResumenAccionMGC res : ret) {
								List<ResumenAccionMGC> respEmr = this.accionesMGCDao.consultaDatosEmisor(res.getNombreEmr());
								if(respEmr.isEmpty()){
									res.setIdEmisor("");
									res.setNombreEmr("");
								}else{
								    res.setIdEmisor(respEmr.get(0).getIdEmisor());
								    res.setNombreEmr(respEmr.get(0).getNombreEmr());
								}
							}
						}

						cache.putObject(queryKey, ret);
						return ret;
					
				}
			}
		} else {
			log.debug("Cargando desde cache Acciones mas Tranzadas Dia fecha : "
					+ fecha + " tipo Mercado:" + tipoMercado);
		}
		return ret;

	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#cargarlistasMGC()
	 */
	public Model cargarlistasMGC(Model model) {
		try {
			model = this.accionesMGCDao.consultaListasMGC(model);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#listasFiltrosMGC()
	 */
	public List<ResumenAccionMGC> listasFiltroMGC(ResumenAccionMGC listaMGC) {
		List<ResumenAccionMGC> lista = null;
		try {
			lista = this.accionesMGCDao.consultaListasFiltroMGC(listaMGC);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#listasFiltrosMGC()
	 */
	public List<ResumenAccionMGC> tipoMercadoMGCService(String nemo) {
		List<ResumenAccionMGC> lista = null;
		try {
			lista = this.accionesMGCDao.tipoMercadoMGC(nemo);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * carga las operaciones de compraventa y retorna los datos del titulo.
	 * 
	 * @param nemo the nemo
	 * @param fecha the fecha
	 * @param delay the delay
	 * 
	 * @return the titulo accion
	 * 
	 * @throws Exception the exception
	 * 
	 * @see IAcciones.cargaDatosValoresCPTitulo
	 */
	private TituloAccion cargaDatosValoresCPTitulo(String nemo, String fecha, boolean delay)
			throws Exception {
		List<OperacionDiaAcciones> ret = operacionesDia(nemo, fecha,
				TIPO_MERCADO_ACCIONES_MGC, delay);
		return cargaDatosValoresCPTitulo(ret);
	}

	/**
	 * carga los datos del detalle del titulo según el mercado de compraventas a
	 * partir de la lista de las operaciones.
	 * 
	 * @param listaOperaciones the lista operaciones
	 * 
	 * @return precioCierre, cantidad, volumen, precioMenor, precioMayor,
	 * precioMedio, fechaUltimaMarcacion
	 */
	private TituloAccion cargaDatosValoresCPTitulo(
			List<OperacionDiaAcciones> listaOperaciones) {
		Double minValue = Double.MAX_VALUE;
		Double maxValue = Double.MIN_VALUE;
		Double midValue = 0d;
		Double cantidad = 0d;
		Double volumen = 0d;
		Double cantidad4MidPrice = 0d;
		Double volumen4MidPrice = 0d;
		Double precioCierre = null;

		TituloAccion ret = new TituloAccion();
		ret.setHora(null);
		if (listaOperaciones != null && !listaOperaciones.isEmpty()) {
			for (Iterator<OperacionDiaAcciones> it = listaOperaciones
					.iterator(); it.hasNext();) {
				OperacionDiaAcciones opAct = it.next();
				if (minValue > opAct.getPrecio()) {
					if ("S".equalsIgnoreCase(opAct.getIndFija())) {
						minValue = opAct.getPrecio();
					}
				}
				if (maxValue < opAct.getPrecio()) {
					if ("S".equalsIgnoreCase(opAct.getIndFija())) {
						maxValue = opAct.getPrecio();
					}
				}
				cantidad += opAct.getCantidad();
				volumen += opAct.getVolumen();
				if ("S".equalsIgnoreCase(opAct.getIndFija())) {
					precioCierre = opAct.getPrecio();
					ret.setHora(opAct.getFechaHora());
				}
				if ("Secundario".equals(opAct.getMercado())
						&& "Normal".equals(opAct.getTipoOperacion())) {
					cantidad4MidPrice += opAct.getCantidad();
					volumen4MidPrice += opAct.getVolumen();
				}
			}
		}
		if (Double.MAX_VALUE == minValue) {
			ret.setPrecioMenor(0d);
		} else {
			ret.setPrecioMenor(minValue);
		}
		if (Double.MIN_VALUE == maxValue) {
			ret.setPrecioMayor(0d);
		} else {
			ret.setPrecioMayor(maxValue);
		}
		if (cantidad4MidPrice != 0) {
			midValue = (volumen4MidPrice / cantidad4MidPrice);
		}
		if (precioCierre != null) {
			ret.setPrecioCierre(precioCierre);
		}
		ret.setPrecioMedio(midValue);
		ret.setCantidad(cantidad);
		ret.setVolumen(volumen);
		return ret;
	}

	/**
	 * Gets the acciones ttv dao.
	 * 
	 * @return the acciones ttv dao
	 */
	public IAccionesMGCTTVDao getAccionesMGCTTVDao() {
		return accionesMGCTTVDao;
	}

	/**
	 * Sets the acciones ttv dao.
	 * 
	 * @param accionesTTVDao the new acciones ttv dao
	 */
	public void setAccionesMGCTTVDao(IAccionesMGCTTVDao accionesMGCTTVDao) {
		this.accionesMGCTTVDao = accionesMGCTTVDao;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#accionesTodas()
	 */
	public List<AccionesAutocomplete> accionesTodas() {
		List<AccionesAutocomplete> res = new ArrayList<AccionesAutocomplete>();
		try {
			res = this.accionesMGCDao.getAccionesTodas();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#emisoresTodos()
	 */
	public List<AccionesAutocomplete> emisoresTodos() {
		List<AccionesAutocomplete> res = new ArrayList<AccionesAutocomplete>();
		try {
			res = this.accionesMGCDao.getEmisoresTodos();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#CotizaAccionesTodas()
	 */
	public List<AccionesAutocomplete> CotizaAccionesTodas() {
		List<AccionesAutocomplete> res = new ArrayList<AccionesAutocomplete>();
		try {
			res = this.accionesMGCDao.getCotizacionAccionTodos();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#CotizaEmisoresTodos()
	 */
	public List<AccionesAutocomplete> CotizaEmisoresTodos() {
		List<AccionesAutocomplete> res = new ArrayList<AccionesAutocomplete>();
		try {
			res = this.accionesMGCDao.getCotizacionEmisorTodos();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return res;
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAccionesMGC#etfsMasTranzadasDia
	 * (java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")

	public List<ResumenEtfsMGC> etfsMasTranzadasDia(String fecha,
			int resultados, int tipoMercado) throws Exception {
		log.info("etfsMasTranzadasDia fecha: " + fecha + " tipoMercado: "
				+ tipoMercado);

		List<ResumenEtfsMGC> ret;
		String fechAux="";

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "ETFSMGC_MAS_TRANZADAS."
				+ ((fecha == null) ? UtilFechas.getHoy("yyyyMMdd") : fecha
						.replaceAll("-", "")) + "." + tipoMercado + "."
				+ resultados;

		ret = (List<ResumenEtfsMGC>) cache.getObject(queryKey); // ETF's
		if (null == ret) {
			synchronized (this) {
				ret = (List<ResumenEtfsMGC>) cache.getObject(queryKey);
				if (null == ret) {
					log.debug("Procesando ETF's mas Tranzadas Dia fecha : "
							+ fecha + " tipo Mercado:" + tipoMercado);
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					if (sdf.format(new Date()).equalsIgnoreCase(fecha)) {
						fecha = null;
					}

					if (IAccionesMGC.TIPO_MERCADO_ETF_MGC == tipoMercado) {
						ret = this.accionesMGCDao.etfsMasTranzadasDia(fecha);
						if(fecha!=null){
							fechAux=fecha.replaceAll("-", "");
						}
						if (ret != null && ret.size() > 0) {
							for (ResumenEtfsMGC res : ret) {
								IntradiaTituloMGC iT =  accionesMGCDao
													.ultimoPrecioEtf(fechAux,
										res.getNemoTecnico());
								double valorAyer = res.getUltimoPrecio();
								if (iT != null && iT.getPrecio() != null){
									valorAyer = iT.getPrecio();
								}
								res.setCierreAnterior(valorAyer);
								if (res.getUltimoPrecio() == 0) {
									res.setUltimoPrecio(valorAyer);
								}
								double var = (res.getUltimoPrecio()
										/ res.getCierreAnterior() - 1) * 100;
								res.setVariacion(Double.isNaN(var)? 0d: var );
								List<ResumenAccionMGC> respEmr = this.accionesMGCDao.consultaDatosEmisor(res.getNemoTecnico());
								if(respEmr.isEmpty()){
									res.setIdEmisor("");
									res.setNombreEmr("");
								}else{
								    res.setIdEmisor(respEmr.get(0).getIdEmisor());
								    res.setNombreEmr(respEmr.get(0).getNombreEmr());
								}
							}
						}

						cache.putObject(queryKey, ret);
						return ret;
					}

					ret = new ArrayList<ResumenEtfsMGC>();
					cache.putObject(queryKey, ret);
					return ret;

				}
			}
		} else {
			log.debug("Cargando desde cache ETF's mas Tranzadas Dia fecha : "
					+ fecha + " tipo Mercado:" + tipoMercado);
		}
		return ret;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAccionesMGC#etfsMasTranzadasDiaHome
	 * (java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<ResumenEtfsMGC>> etfsMasTranzadasDiaHome(String fecha,
			int resultados, int tipoMercado) throws Exception {

		log.info("etfsMasTranzadasDiaHome fecha: " + fecha + " tipoMercado: "
				+ tipoMercado);

		List<ResumenEtfsMGC> ret;
		Map<String, List<ResumenEtfsMGC>> listas = new LinkedMap();
		Map<String, List<ResumenEtfsMGC>> listas2 = new LinkedMap();
		String fechAux="";
		
		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "ETFSMGC_MAS_TRANZADAS_HOME."
				+ ((fecha == null) ? UtilFechas.getHoy("yyyyMMdd") : fecha
						.replaceAll("-", "")) + "." + tipoMercado + "."
				+ resultados;

		listas.put("cacheETFMGC", (List<ResumenEtfsMGC>) cache.getObject(queryKey)); // ACCIONES-015
		if (listas.get("cacheETFMGC") == null) {
			synchronized (this) {
				listas.put("cacheETFMGC", (List<ResumenEtfsMGC>) cache.getObject(queryKey));
				if (listas.get("cacheETFMGC") == null) {
					log.debug("Procesando Acciones mas Tranzadas Dia ETF fecha : "
							+ fecha + " tipo Mercado:" + tipoMercado);
					

					    ret = this.accionesMGCDao.etfsMasTranzadasDiaHome(fecha);
						
					    if(fecha!=null){
							fechAux=fecha.replaceAll("-", "");
						}
						if (ret != null && ret.size() > 0) {
							for (ResumenEtfsMGC res : ret) {
								IntradiaTituloMGC iT =  accionesMGCDao
													.ultimoPrecioEtf(fechAux,
										res.getNemoTecnico());
								double valorAyer = res.getUltimoPrecio();
								if (iT != null && iT.getPrecio() != null){
									valorAyer = iT.getPrecio();
								}
								res.setCierreAnterior(valorAyer);
								if (res.getUltimoPrecio() == 0) {
									res.setUltimoPrecio(valorAyer);
								}
								double var = (res.getUltimoPrecio()
										/ res.getCierreAnterior() - 1) * 100;
								res.setVariacion(Double.isNaN(var)? 0d: var );
								List<ResumenAccionMGC> respEmr = this.accionesMGCDao.consultaDatosEmisor(res.getNemoTecnico());
								if(respEmr.isEmpty()){
									res.setIdEmisor("");
									res.setNombreEmr("");
								}else{
								    res.setIdEmisor(respEmr.get(0).getIdEmisor());
								    res.setNombreEmr(respEmr.get(0).getNombreEmr());
								}
							}
						}
									
		
						List<ResumenEtfsMGC> accionesSuben = new ArrayList<ResumenEtfsMGC>(ret);
						List<ResumenEtfsMGC> accionesBajan = new ArrayList<ResumenEtfsMGC>(ret);
		
						Collections.sort(accionesSuben, new EtfsMGCComparador(EtfsMGCComparador.SUBE));
						Collections.sort(accionesBajan, new EtfsMGCComparador(EtfsMGCComparador.BAJA));
		
		
	
						
						if (ret != null && ret.size() > 5){
							listas2.put("listaEtfsMGC", ret.subList(0, 5) );
						}else{
						    listas2.put("listaEtfsMGC", ret);
						}
						if (accionesSuben != null && accionesSuben.size() > 5){
							listas2.put("listaEtfsMGCSube", this.sacarEtfs(EtfsMGCComparador.SUBE, accionesSuben.subList(0, 5) ));
						}else{
							listas2.put("listaEtfsMGCSube", this.sacarEtfs(EtfsMGCComparador.SUBE, accionesSuben));
						}
						if (accionesBajan != null && accionesBajan.size() > 5){
							listas2.put("listaEtfsMGCBaja", this.sacarEtfs(EtfsMGCComparador.BAJA,  accionesBajan.subList(0, 5) ));
						}else{
							listas2.put("listaEtfsMGCBaja", this.sacarEtfs(EtfsMGCComparador.BAJA,  accionesBajan));
						}
						
						listas=listas2;
						
						cache.putObject(queryKey, listas2.get("listaEtfsMGC"));
						cache.putObject("listaEtfsMGCSube", listas2.get("listaEtfsMGCSube"));
						cache.putObject("listaEtfsMGCBaja", listas2.get("listaEtfsMGCBaja"));
						return listas;
		
				}
			}
		} else {
			listas.put("listaEtfsMGC", (List<ResumenEtfsMGC>) cache.getObject(queryKey));
			listas.put("listaEtfsMGCSube", (List<ResumenEtfsMGC>) cache.getObject("listaEtfsMGCSube"));
			listas.put("listaEtfsMGCBaja", (List<ResumenEtfsMGC>) cache.getObject("listaEtfsMGCBaja"));
			log.debug("Cargando desde cache Acciones mas Tranzadas Dia ETF fecha : "
					+ fecha + " tipo Mercado:" + tipoMercado);
		}
		
		return listas;

	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAccionesMGC#obtenerHorarioNegociacionMGC
	 * (int mercado)
	 */
	@SuppressWarnings("unchecked")

	public List<HorarioMercadoMGC> obtenerHorarioNegociacionMGC(int mercado)
			throws PersistenciaException {
		log.debug("Ingresó a la función obtenerHorarioNegociacionMGC con mercado: "+ mercado);
		return accionesMGCDao.obtenerHorarioNegociacionMGC(mercado);
	}

	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> reposMasTranzadasDia(String fecha, int i, int tipoMercado) throws Exception {
		List<ResumenAccionMGC> ret;

		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "REPOS_MAS_TRANZADAS."+ ((fecha == null) ? UtilFechas.getHoy("yyyyMMdd") : fecha.replaceAll("-", ""));

		ret = (List<ResumenAccionMGC>) cache.getObject(queryKey);
		if (null == ret) {
			synchronized (this) {
				ret = (List<ResumenAccionMGC>) cache.getObject(queryKey);
				if (null == ret) {
					ret = this.accionesMGCDao.reposMasTranzadasDia(fecha);
					if (ret != null && ret.size() > 0) {
						for (ResumenAccionMGC res : ret) {
							IntradiaTituloMGC iT =  accionesMGCDao.ultimoPrecioAccion(fecha,res.getNemoTecnico());
							double valorAyer = res.getUltimoPrecio();
							if (iT != null && iT.getPrecio() != null){
								valorAyer = iT.getPrecio();
							}
							res.setCierreAnterior(valorAyer);
							if (res.getUltimoPrecio() == 0) {
								res.setUltimoPrecio(valorAyer);
							}
							double var = (res.getUltimoPrecio()
									/ res.getCierreAnterior() - 1) * 100;
							res.setVariacion(Double.isNaN(var)? 0d: var );
							List<ResumenAccionMGC> respEmr = this.accionesMGCDao.consultaDatosEmisor(res.getNemoTecnico());
							if(respEmr.isEmpty()){
								res.setIdEmisor("");
								res.setNombreEmr("");
							}else{
							    res.setIdEmisor(respEmr.get(0).getIdEmisor());
							    res.setNombreEmr(respEmr.get(0).getNombreEmr());
							}
						}
					}

					cache.putObject(queryKey, ret);
					return ret;
				}
			}
		} else {
			log.debug("Cargando desde cache ETF's mas Tranzadas Dia fecha : "
					+ fecha + " tipo Mercado:" + tipoMercado);
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> ttvMasTranzadasDia(String fecha, int i, int tipoMercado) throws Exception {
		List<ResumenAccionMGC> ret;

		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "TTV_MAS_TRANZADAS."+ ((fecha == null) ? UtilFechas.getHoy("yyyyMMdd") : fecha.replaceAll("-", ""));

		ret = (List<ResumenAccionMGC>) cache.getObject(queryKey); // ETF's
		if (null == ret) {
			synchronized (this) {
				ret = (List<ResumenAccionMGC>) cache.getObject(queryKey);
				if (null == ret) {
					ret = this.accionesMGCDao.ttvMasTranzadasDia(fecha);
					if (ret != null && ret.size() > 0) {
						for (ResumenAccionMGC res : ret) {
							IntradiaTituloMGC iT =  accionesMGCDao.ultimoPrecioAccion(fecha,res.getNemoTecnico());
							double valorAyer = res.getUltimoPrecio();
							if (iT != null && iT.getPrecio() != null){
								valorAyer = iT.getPrecio();
							}
							res.setCierreAnterior(valorAyer);
							if (res.getUltimoPrecio() == 0) {
								res.setUltimoPrecio(valorAyer);
							}
							double var = (res.getUltimoPrecio()
									/ res.getCierreAnterior() - 1) * 100;
							res.setVariacion(Double.isNaN(var)? 0d: var );
							List<ResumenAccionMGC> respEmr = this.accionesMGCDao.consultaDatosEmisor(res.getNemoTecnico());
							if(respEmr.isEmpty()){
								res.setIdEmisor("");
								res.setNombreEmr("");
							}else{
							    res.setIdEmisor(respEmr.get(0).getIdEmisor());
							    res.setNombreEmr(respEmr.get(0).getNombreEmr());
							}
						}
					}

					cache.putObject(queryKey, ret);
					return ret;
				}
			}
		} else {
			log.debug("Cargando desde cache ETF's mas Tranzadas Dia fecha : "
					+ fecha + " tipo Mercado:" + tipoMercado);
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAccionesMGC#cotizaTotalPaisMGC
	 * (int mercado)  
	 */
	@SuppressWarnings("unchecked")

	public List<CotizaPaisMGC> cotizaTotalPaisMGC()
			throws PersistenciaException {
		log.debug("Ingresó a la función cotizaTotalPaisMGC ");
		
		List<CotizaPaisMGC> ret;

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "COTIZA_PAIS_MGC.";

		ret = (List<CotizaPaisMGC>) cache.getObject(queryKey); 
		if (ret == null) {
			synchronized (this) {
				ret = (List<CotizaPaisMGC>) cache.getObject(queryKey);
				if (ret == null) {
		           		
					ret= accionesMGCDao.cotizaTotalPaisDaoMGC();
		
					if (ret != null && ret.size() > 0) {
			
						for (CotizaPaisMGC res : ret) {
							List<ResumenAccionMGC> respEmr = this.accionesMGCDao.consultaDatosEmisor(res.getEmpresa());
							if(respEmr.isEmpty()){
								res.setIdEmisor("");
							}else{
								res.setIdEmisor(respEmr.get(0).getIdEmisor());
							}
						}
			
						}
					cache.putObject(queryKey, ret);
					return ret;
				}
			}
       } else {
    	   log.debug("Cargando desde cache Cotizacion mercado de origen ");
       }
       return ret;
	  }
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#cargarlistasMGC()
	 */
	public Model cargarlistasCotizaMGC(Model model) {
		try {
			model = this.accionesMGCDao.consultaListaCotizaMGC(model);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAccionesMGC#listasFiltrosMGC()
	 */
	public List<CotizaPaisMGC> CotizaFiltroMGC(CotizaPaisMGC listaMGC) {
		List<CotizaPaisMGC> lista = null;
		try {
			lista = this.accionesMGCDao.consultaCotizaFiltroMGC(listaMGC);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
/****************************************End of AccionMGCServicio.java*************************************/