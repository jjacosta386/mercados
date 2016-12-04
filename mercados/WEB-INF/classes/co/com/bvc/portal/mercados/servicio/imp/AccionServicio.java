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

import org.apache.commons.collections.map.LinkedMap;
import org.apache.log4j.Logger;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.servicio.IServicioUtil;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.AccionComparador;
import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.IntradiaTitulo;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.RentaFijaOperacion;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.modelo.VolumenAccion;
import co.com.bvc.portal.mercados.persistencia.IAccionesBoceasDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesReposDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesTTVDao;
import co.com.bvc.portal.mercados.servicio.IAcciones;
import co.com.bvc.portal.mercados.servicio.IRentaFija;
import co.com.bvc.portal.mercados.util.IConstantesAcciones;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;

// TODO: Auto-generated Javadoc
/**
 * @author BVC
 * @description The Class AccionServicio.
 */

public class AccionServicio implements IAcciones {

	/** The acciones dao. */ 
	private IAccionesDao accionesDao;

	/** The acciones repos dao. */
	private IAccionesReposDao accionesReposDao;

	/** The acciones ttv dao. */
	private IAccionesTTVDao accionesTTVDao;
	
	/** The acciones boceas dao. */
	private IAccionesBoceasDao accionesBoceasDao;

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The servicio util. */
	private IServicioUtil servicioUtil;

	/**
	 * Gets the acciones repos dao.
	 * 
	 * @return the acciones repos dao
	 */
	public IAccionesReposDao getAccionesReposDao() {
		return accionesReposDao;
	}

	/**
	 * Sets the acciones repos dao.
	 * 
	 * @param accionesReposDao the new acciones repos dao
	 */
	public void setAccionesReposDao(IAccionesReposDao accionesReposDao) {
		this.accionesReposDao = accionesReposDao;
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
	 * @param accionesDao the new acciones dao
	 */
	public void setAccionesDao(IAccionesDao accionesDao) {
		this.accionesDao = accionesDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAcciones#cargarDatosTituloDia(java
	 * .lang.String, java.lang.String, int, java.util.List)
	 */
	
	/*
	 * "BVC", null, IAcciones.TIPO_MERCADO_COMPRAVENTAS, listOp,
					false
	 */
	public TituloAccion cargarDatosTituloDia(String nemo, String fecha,
			int tipoMercado, List<OperacionDiaAcciones> operaciones, boolean delay)
			throws Exception {

		TituloAccion taPrecios;
		TituloAccion ret;

		if (TIPO_MERCADO_COMPRAVENTAS == tipoMercado) {
			taPrecios = cargaDatosValoresCPTitulo(operaciones);
		} else {
			taPrecios = cargaDatosValoresCPTitulo(nemo, fecha, delay);
		}

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "DATOS_ACCION." + UtilFechas.getHoy("yyyyMMdd") + "."
				+ nemo;

		ret = (TituloAccion) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (TituloAccion) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Datos Accion : " + nemo);
					ret = accionesDao.cargarDatosTituloUltimoDia(nemo);
					cache.putObject(queryKey, ret);
				}
			}
		} else {
			log.debug("Cargando desde Cache datos Accion :" + nemo);
		}

		IntradiaTitulo intTit = accionesDao.ultimoPrecioAccion(
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
	 * @see co.com.bvc.portal.mercados.servicio.IAcciones#cargarDatosTituloDia(java.lang.String, int, java.lang.String, java.lang.String)
	 */
	public List<TituloAccion> cargarDatosTituloDia(String nemo,
			int tipoMercado, String fechaIni, String fechaFin) throws Exception {
		if (tipoMercado == TIPO_MERCADO_COMPRAVENTAS) {

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = new GregorianCalendar();
			cal.setTime(sdf.parse(fechaIni));
			cal.add(Calendar.DAY_OF_YEAR, -1);

			List<TituloAccion> titAcciones = this.accionesDao
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
		if (tipoMercado == TIPO_MERCADO_REPOS) {
			return this.accionesReposDao.cargaDatosTituloFecha(nemo, fechaIni,
					fechaFin);
		}
		if (tipoMercado == TIPO_MERCADO_TTV) {
			return this.accionesTTVDao.cargaDatosTituloFecha(nemo, fechaIni,
					fechaFin);
		}
		if (tipoMercado == TIPO_MERCADO_BOCEAS) {
			return this.accionesBoceasDao.cargaDatosTituloFecha(nemo, fechaIni,
					fechaFin);
		}
		if (tipoMercado == TIPO_MERCADO_BOCEAS_SIMUL) {
			return this.accionesBoceasDao.cargaDatosSimulTituloFecha(nemo, fechaIni,
					fechaFin);
		}
		return new ArrayList<TituloAccion>();

	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAcciones#cargarHistoricoTitulo(java.lang.String)
	 */
	public List<ICierre> cargarHistoricoTitulo(String titulo)
			throws PersistenciaException {
		return this.accionesDao.cargarHistoricoTitulo(titulo);
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAcciones#operacionesDia(java.lang.String, java.lang.String, int, boolean)
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

		if (tipoMercado == TIPO_MERCADO_COMPRAVENTAS) {
			return this.accionesDao.operacionesDia(nemo, fecha, delay);
		}

		if (tipoMercado == TIPO_MERCADO_REPOS) {
			log.info("operaciones dia repos");
			if (fecha == null) {
				return this.accionesReposDao.operacionesUltimoDia(nemo);
			}
			return this.accionesReposDao.operaciones(nemo, fecha);
		}
		
		if (tipoMercado == TIPO_MERCADO_TTV) {
			log.info("operaciones dia ttvs");
			if (fecha == null) {
				return this.accionesTTVDao.operacionesUltimoDia(nemo);
			}
			return this.accionesTTVDao.operaciones(nemo, fecha);
		}
		
		if (tipoMercado == TIPO_MERCADO_BOCEAS) {
			log.info("operaciones dia boceas");
			if (fecha == null) {
				return this.accionesBoceasDao.operacionesUltimoDia(nemo);
			}
			return this.accionesBoceasDao.operaciones(nemo, fecha);
		}
		
		if (tipoMercado == TIPO_MERCADO_BOCEAS_SIMUL) {
			log.info("operaciones dia boceas simultaneas");
			if (fecha == null) {
				return this.accionesBoceasDao.operacionesSimulUltimoDia(nemo);
			}
			return this.accionesBoceasDao.operacionesSimul(nemo, fecha);
		}

		return new ArrayList<OperacionDiaAcciones>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAcciones#cargarVolumenDia(java.lang
	 * .String)
	 */
	public List<VolumenAccion> cargarVolumenDia(String fecha) throws Exception {
		List<VolumenAccion> respuesta = new ArrayList<VolumenAccion>();
		VolumenAccion vol1, vol2, vol3, vol4, vol5 ,vol6;

		double[] volumenes = accionesDao.obtenerVolumenesNegociados(fecha);

		vol1 = new VolumenAccion();
		vol1.setDescripcionVolumen(IConstantesAcciones.NOMBRE_CV);
		vol1.setVolumen(volumenes[0]);
		respuesta.add(vol1);

		vol2 = new VolumenAccion();
		vol2.setDescripcionVolumen(IConstantesAcciones.NOMBRE_REPOS);
		vol2.setVolumen(volumenes[1]);
		respuesta.add(vol2);

		vol3 = new VolumenAccion();
		vol3.setDescripcionVolumen(IConstantesAcciones.NOMBRE_TTV);
		vol3.setVolumen(volumenes[2]);
		respuesta.add(vol3);
		
		vol4 = new VolumenAccion();
		vol4.setDescripcionVolumen(IConstantesAcciones.NOMBRE_BOCEAS);
		vol4.setVolumen(volumenes[3]);
		respuesta.add(vol4);
		
		vol5 = new VolumenAccion();
		vol5.setDescripcionVolumen(IConstantesAcciones.NOMBRE_BOCEAS_SIMUL);
		vol5.setVolumen(volumenes[4]);
		respuesta.add(vol5);

		double suma = 0d;

		for (double v : volumenes) {
			suma += v;
		}

		if (suma > 0) {
			vol1.setPorcentajeParticipacion((vol1.getVolumen() / suma) * 100);
			vol2.setPorcentajeParticipacion((vol2.getVolumen() / suma) * 100);
			vol3.setPorcentajeParticipacion((vol3.getVolumen() / suma) * 100);
			vol4.setPorcentajeParticipacion((vol4.getVolumen() / suma) * 100);
			vol5.setPorcentajeParticipacion((vol5.getVolumen() / suma) * 100);
		}

		vol6 = new VolumenAccion();
		vol6.setDescripcionVolumen("Total");
		vol6.setVolumen(suma);
		vol6.setPorcentajeParticipacion(100);
		respuesta.add(vol6);

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAcciones#accionesMasTranzadasDia
	 * (java.lang.String, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccion> accionesMasTranzadasDia(String fecha,
			int resultados, int tipoMercado) throws Exception {

		log.info("accionesMasTranzadasDia fecha: " + fecha + " tipoMercado: "
				+ tipoMercado);

		List<ResumenAccion> ret;
		
		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "ACCIONES_MAS_TRANZADAS."
				+ ((fecha == null) ? UtilFechas.getHoy("yyyyMMdd") : fecha
						.replaceAll("-", "")) + "." + tipoMercado + "."
				+ resultados;

		ret = (List<ResumenAccion>) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (List<ResumenAccion>) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Acciones mas Tranzadas Dia fecha : "
							+ fecha + " tipo Mercado:" + tipoMercado);
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					if (sdf.format(new Date()).equalsIgnoreCase(fecha)) {
						fecha = null;
					}

					if (tipoMercado == TIPO_MERCADO_COMPRAVENTAS) {
						ret = this.accionesDao.accionesMasTranzadasDia(fecha);
						if ((fecha == null || "".equals(fecha.trim()))
								&& ret != null && ret.size() > 0) {
							for (ResumenAccion res : ret) {
								IntradiaTitulo iT =  accionesDao
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
								res.setVariacion(Double.isNaN(var)? 0d: var );
							}
						}

						cache.putObject(queryKey, ret);
						return ret;
					}

					if (tipoMercado == TIPO_MERCADO_REPOS) {
						if (fecha == null || fecha.equalsIgnoreCase("")) {
							ret = this.accionesReposDao
									.cargarMasTranzadasDiaUltDia();
							cache.putObject(queryKey, ret);
							return ret;
						}

						ret = this.accionesReposDao
								.cargarMasTranzadasDia(fecha);
						cache.putObject(queryKey, ret);
						return ret;
					}
					if (tipoMercado == TIPO_MERCADO_TTV) {
						if (fecha == null || fecha.equalsIgnoreCase("")) {
							ret = this.accionesTTVDao.cargarMasTranzadasDiaUltDia();
							cache.putObject(queryKey, ret);
							return ret;
						}

						ret = this.accionesTTVDao.cargarMasTranzadasDia(fecha);
						cache.putObject(queryKey, ret);
						return ret;
					}
					
					if (tipoMercado == TIPO_MERCADO_BOCEAS) {
						/*if (fecha == null || fecha.equalsIgnoreCase("")) {
							ret = this.accionesBoceasDao
									.cargarMasTranzadasDiaUltDia();
							cache.putObject(queryKey, ret);
							return ret;
						}*/

						ret = this.accionesBoceasDao
								.cargarMasTranzadasDia(fecha);
						cache.putObject(queryKey, ret);
						return ret;
					}
					
					if (tipoMercado == TIPO_MERCADO_BOCEAS_SIMUL) {
						/*if (fecha == null || fecha.equalsIgnoreCase("")) {
							ret = this.accionesBoceasDao
									.cargarMasTranzadasDiaUltDiaSimul();
							cache.putObject(queryKey, ret);
							return ret;
						}*/

						ret = this.accionesBoceasDao
								.cargarMasTranzadasDiaSimul(fecha);
						cache.putObject(queryKey, ret);
						return ret;
					}

					ret = new ArrayList<ResumenAccion>();
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
	public Map<String, List<ResumenAccion>> accionesMasTranzadasHome(String fecha,
			int resultados, int tipoMercado) throws Exception {

		log.info("accionesMasTranzadasHome fecha: " + fecha + " tipoMercado: "
				+ tipoMercado);

		List<ResumenAccion> ret;
		List<ResumenAccion> ret2;
		List<ResumenAccion> ret3;
		List<ResumenAccion> ret4;
		Map<String, List<ResumenAccion>> listas = new LinkedMap();
		Map<String, List<ResumenAccion>> listas2 = new LinkedMap();
		
		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "ACCIONES_MAS_TRANZADAS_HOME."
				+ ((fecha == null) ? UtilFechas.getHoy("yyyyMMdd") : fecha
						.replaceAll("-", "")) + "." + tipoMercado + "."
				+ resultados;

		listas.put("cache", (List<ResumenAccion>) cache.getObject(queryKey)); // ACCIONES-015
		if (listas.get("cache") == null) {
			synchronized (this) {
				listas.put("cache", (List<ResumenAccion>) cache.getObject(queryKey));
				if (listas.get("cache") == null) {
					log.debug("Procesando Acciones mas Tranzadas Dia fecha : "
							+ fecha + " tipo Mercado:" + tipoMercado);
					

						ret = this.accionesDao.accionesMasTranzadasHome(fecha);
						
						if ( ret != null && ret.size() > 0) {
							for (ResumenAccion res : ret) {
								IntradiaTitulo iT =  accionesDao.ultimoPrecioAccion(fecha,
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
							}
						}
									
		
						List<ResumenAccion> accionesSuben = new ArrayList<ResumenAccion>(ret);
						List<ResumenAccion> accionesBajan = new ArrayList<ResumenAccion>(ret);
		
						Collections.sort(accionesSuben, new AccionComparador(AccionComparador.SUBE));
						Collections.sort(accionesBajan, new AccionComparador(AccionComparador.BAJA));
		
		
	
						
						if (ret != null && ret.size() > 5){
							ret2=ret.subList(0, 5);
							listas2.put("listaAcciones", ret2 );
						}else{
						    listas2.put("listaAcciones", ret);
						}
						if (accionesSuben != null && accionesSuben.size() > 5){
							ret3=accionesSuben.subList(0, 5);
							listas2.put("listaAccionesSube", this.sacarAcciones(AccionComparador.SUBE, ret3 ));
						}else{
							listas2.put("listaAccionesSube", this.sacarAcciones(AccionComparador.SUBE, accionesSuben));
						}
						if (accionesBajan != null && accionesBajan.size() > 5){
							ret4=accionesBajan.subList(0, 5);
							listas2.put("listaAccionesBaja", this.sacarAcciones(AccionComparador.BAJA,  ret4 ));
						}else{
							listas2.put("listaAccionesBaja", this.sacarAcciones(AccionComparador.BAJA,  accionesBajan));
						}
						
						listas=listas2;
						
						cache.putObject(queryKey, listas2.get("listaAcciones"));
						cache.putObject("listaAccionesSube", listas2.get("listaAccionesSube"));
						cache.putObject("listaAccionesBaja", listas2.get("listaAccionesBaja"));
						return listas;
		
				}
			}
		} else {
			listas.put("listaAcciones", (List<ResumenAccion>) cache.getObject(queryKey));
			listas.put("listaAccionesSube", (List<ResumenAccion>) cache.getObject("listaAccionesSube"));
			listas.put("listaAccionesBaja", (List<ResumenAccion>) cache.getObject("listaAccionesBaja"));
			log.debug("Cargando desde cache Acciones mas Tranzadas Dia fecha : "
					+ fecha + " tipo Mercado:" + tipoMercado);
		}
		
		return listas;

	}
	
		

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IAcciones#sacarAcciones(int,
	 * java.util.List)
	 */
	public List<ResumenAccion> sacarAcciones(int tipoLista,
			List<ResumenAccion> lista) {
		boolean sube = (AccionComparador.SUBE == tipoLista);
		for (int i = 0; i < lista.size();) {
			ResumenAccion resumenAccion = (ResumenAccion) lista.get(i);
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
				TIPO_MERCADO_COMPRAVENTAS, delay);
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAcciones#getBoceasDetalleOperaciones
	 * (java.lang.String, java.lang.String, int, java.lang.String)
	 */
	public List<RentaFijaOperacion> getBoceasDetalleOperaciones(String dia,
			String tipoSesion, String tipoOperacion, String nemo,
			String tipoMercado) throws PersistenciaException {
		List<RentaFijaOperacion> operaciones = this.accionesBoceasDao
				.getBoceasOperaciones(dia, tipoSesion,
						tipoMercado, tipoOperacion, nemo);
		return operaciones;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see 
	 * co.com.bvc.portal.mercados.servicio.IAcciones#getResumenEmisorBoceas(java.
	 * lang.String)
	 */
	public EmisorTitulo getResumenEmisorBoceas(String nemo)
			throws PersistenciaException {

		EmisorTitulo re = new EmisorTitulo();
		re = this.accionesBoceasDao.getEmisor(nemo);
		// this.emisorFinal = re;
		return re;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAcciones#getSumaOperacionesGraficaBocea
	 * (java.lang.String, java.lang.String, java.lang.String, int,
	 * java.lang.String)
	 */
	public List<RentaFijaSumaOperacion> getSumaOperacionesGraficaBocea(String dia1,
			String dia2, String nemo)
			throws PersistenciaException {
		EmisorTitulo emi = getResumenEmisorBoceas(nemo);
		List<RentaFijaSumaOperacion> ret = this.accionesBoceasDao.getDetalleExcelBocea(dia1, dia2, nemo);
		
		if (ret != null && !ret.isEmpty()){
			for (Iterator<RentaFijaSumaOperacion> iterator = ret.iterator(); iterator.hasNext();) {
				RentaFijaSumaOperacion rentaFijaSumaOperacion = (RentaFijaSumaOperacion) iterator
						.next();
				rentaFijaSumaOperacion.setEmisor(emi.getSigla());
			}
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IAcciones#getResumenTituloBoceas(java.
	 * lang.String)
	 */
	public RentaFijaResumenTitulo getResumenTituloBoceas(String nemo)
			throws PersistenciaException {
		RentaFijaResumenTitulo rt = new RentaFijaResumenTitulo();
		rt = this.accionesBoceasDao.getRentaFijaTituloBoceas(nemo);
		return rt;
	}

	/**
	 * Gets the acciones ttv dao.
	 * 
	 * @return the acciones ttv dao
	 */
	public IAccionesTTVDao getAccionesTTVDao() {
		return accionesTTVDao;
	}

	/**
	 * Sets the acciones ttv dao.
	 * 
	 * @param accionesTTVDao the new acciones ttv dao
	 */
	public void setAccionesTTVDao(IAccionesTTVDao accionesTTVDao) {
		this.accionesTTVDao = accionesTTVDao;
	}
	
	/**
	 * Gets the acciones ttv dao.
	 * 
	 * @return the acciones ttv dao
	 */
	public IAccionesBoceasDao getAccionesBoceasDao() {
		return accionesBoceasDao;
	}

	/**
	 * Sets the acciones ttv dao.
	 * 
	 * @param accionesTTVDao the new acciones ttv dao
	 */
	public void setAccionesBoceasDao(IAccionesBoceasDao accionesBoceasDao) {
		this.accionesBoceasDao = accionesBoceasDao;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IAcciones#accionesTodas()
	 */
	public List<AccionesAutocomplete> accionesTodas() {
		List<AccionesAutocomplete> res = new ArrayList<AccionesAutocomplete>();
		try {
			res = this.accionesDao.getAccionesTodas();
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

}
/********************************************************End of AccionServicio.java************************************************/