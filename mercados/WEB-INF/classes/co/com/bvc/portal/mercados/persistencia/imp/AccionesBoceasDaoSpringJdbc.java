package co.com.bvc.portal.mercados.persistencia.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.jdbc.JDBCDaoImp;
import co.com.bvc.portal.comun.util.UtilDto;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.RentaFijaOperacion;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.ResumenBoceas;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.persistencia.IAccionesBoceasDao;
import co.com.bvc.portal.mercados.servicio.IRentaFija;
import co.com.bvc.portal.mercados.util.IConstantesAcciones;
import co.com.bvc.portal.mercados.util.IConstantesConsultasAcciones;
import co.com.bvc.portal.mercados.util.IConsultasMercados;

public class AccionesBoceasDaoSpringJdbc extends JDBCDaoImp implements IAccionesBoceasDao {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());
	private Object rentaFijaDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesDao#
	 * obtenerVolumenesNegociados(java.lang.String)
	 */
	@SuppressWarnings("unchecked")

	
	public List<ResumenAccion> cargarMasTranzadasDia(String fecha)
			throws PersistenciaException {

		List<ResumenAccion> respuesta = new ArrayList<ResumenAccion>();
		String sufijoTabla = "a";
		String tipoSesion = "BOCE";
		
		if(fecha ==null || "".equals(fecha.trim())){
		
			fecha=UtilFechas.getHoy("yyyy-MM-dd");
			sufijoTabla="";
		
		}
		
		List<Map> resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_BOCEAS_HOY,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN,"a", "rueda" },
					new String[] { fecha,sufijoTabla,tipoSesion });
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccion.class, resQuery);

		} catch (Exception ex) {
			throw new PersistenciaException(
					"error obteniendo las Boceas del día " + fecha);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesTTVDao#
	 * cargarMasTranzadasDiaUltDia()
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccion> cargarMasTranzadasDiaUltDia() throws Exception {
		String fecha20Min = UtilFechas
				.fechaMenos20Minutos(new GregorianCalendar());
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_BOCEAS_HOY,
				new String[] { IConstantesConsultasAcciones.FECHA_FIN },
				new String[] { fecha20Min });
		List<ResumenAccion> respuesta = UtilDto.obtenerObjetos(
				ResumenAccion.class, resQuery);
		return respuesta;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesDao#
	 * obtenerVolumenesNegociados(java.lang.String)
	 */
	@SuppressWarnings("unchecked")

	
	public List<ResumenAccion> cargarMasTranzadasDiaSimul(String fecha)
			throws PersistenciaException {

		List<ResumenAccion> respuesta = new ArrayList<ResumenAccion>();
		String[] fechaMinutos = null;
		String sufijoTabla = "a";
		String tipoSesion = "SIBO";
		
		if(fecha ==null || "".equals(fecha.trim())){
		
			fecha=UtilFechas.getHoy("yyyy-MM-dd");
			sufijoTabla="";
		
		}
		
		List<Map> resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_BOCEAS_HOY,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN,"a", "rueda" },
					new String[] { fecha,sufijoTabla,tipoSesion });
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccion.class, resQuery);

		} catch (Exception ex) {
			throw new PersistenciaException(
					"error obteniendo las Boceas del día " + fecha);
		}

		return respuesta;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesTTVDao#
	 * cargarMasTranzadasDiaUltDia()
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccion> cargarMasTranzadasDiaUltDiaSimul() throws Exception {
		String fecha20Min = UtilFechas
				.fechaMenos20Minutos(new GregorianCalendar());
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_BOCEASIMUL_HOY,
				new String[] { IConstantesConsultasAcciones.FECHA_FIN },
				new String[] { fecha20Min });
		List<ResumenAccion> respuesta = UtilDto.obtenerObjetos(
				ResumenAccion.class, resQuery);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesTTVDao#
	 * operacionesUltimoDia(java.lang.String)
	 */
	public List<OperacionDiaAcciones> operacionesUltimoDia(String nemo)
			throws PersistenciaException {

		String consulta = "SELECT "
			+ " boce.v_rftr_nemo nemo, "
			+ " boce.v_rftr_cantidad cantidad, "
			+ " boce.v_rftr_monto volumen, "
			+ " boce.v_rftr_tasa tasa, "
			+ " cumope.v_cumope_fec_cump hora, "
			+ " CASE WHEN v_rftr_mercado = 'P' THEN 'boceario' ELSE 'Secundario' END mercado "
			+ " FROM unicabd.tbrftrh boce "
			+ " INNER JOIN tbcumope cumope ON cumope.v_cumope_hor_gra = boce.v_rftr_hor_gra "
			+ " WHERE boce.v_rftr_rusiopel = 'BOCE' "
			+ " AND boce.v_rftr_nemo = ? "
			+ " AND boce.v_rftr_hor_gra > ?";

		Object[] params = { nemo, UtilFechas.getHoy("yyyy-MM-dd")};

		return this.cargarOperaciones(consulta, params);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesTTVDao#
	 * operacionesUltimoDia(java.lang.String)
	 */
	public List<OperacionDiaAcciones> operacionesSimulUltimoDia(String nemo)
			throws PersistenciaException {

		String consulta = "SELECT "
			+ " boce.v_rftr_nemo nemo, "
			+ " boce.v_rftr_cantidad cantidad, "
			+ " boce.v_rftr_monto volumen, "
			+ " boce.v_rftr_tasa tasa, "
			+ " cumope.v_cumope_fec_cump hora, "
			+ " CASE WHEN v_rftr_mercado = 'P' THEN 'boceario' ELSE 'Secundario' END mercado "
			+ " FROM unicabd.tbrftrh boce "
			+ " INNER JOIN tbcumope cumope ON cumope.v_cumope_hor_gra = boce.v_rftr_hor_gra "
			+ " WHERE boce.v_rftr_rusiopel = 'SIBO' "
			+ " AND boce.v_rftr_nemo = ? "
			+ " AND boce.v_rftr_hor_gra > ?";

		Object[] params = { nemo, UtilFechas.getHoy("yyyy-MM-dd") };

		return this.cargarOperaciones(consulta, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesTTVDao#operaciones
	 * (java.lang.String, java.lang.String)
	 */
	public List<OperacionDiaAcciones> operaciones(String nemo, String fecha)
			throws PersistenciaException {
		
		
		/*List<RentaFijaOperacion> operaciones = this.rentaFijaDao
		.getRentaFijaOperaciones(dia, IRentaFija.SESION_ALL
				.equals(tipoSesion) ? MERCADO_TODOS : tipoSesion,
				tipoMercado, tipoOperacion, nemo);*/

		String consulta = "SELECT "
				+ " boce.v_rftr_nemo nemo, "
				+ " boce.v_rftr_cantidad cantidad, "
				+ " boce.v_rftr_monto volumen, "
				+ " boce.v_rftr_tasa tasa, "
				+ " cumope.v_cumope_fec_cump hora, "
				+ " CASE WHEN v_rftr_mercado = 'P' THEN 'boceario' ELSE 'Secundario' END mercado "
				+ " FROM unicabd.tbrftrh boce "
				+ " INNER JOIN tbcumope cumope ON cumope.v_cumope_hor_gra = boce.v_rftr_hor_gra "
				+ " WHERE boce.v_rftr_rusiopel = 'BOCE' "
				+ " AND boce.v_rftr_nemo = ? "
				+ " AND boce.v_rftr_hor_gra > ?"
				+ "	AND boce.v_rftr_hor_gra >= ? ";
		
		String[] fechas = UtilFechas.getFechasComparador(fecha);
		Object[] params = { nemo, fechas[1], fechas[0]};

		return this.cargarOperaciones(consulta, params);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesBOceasDao#operacionesSimul
	 * (java.lang.String, java.lang.String)
	 */
	public List<OperacionDiaAcciones> operacionesSimul(String nemo, String fecha)
			throws PersistenciaException {
		
		
		/*List<RentaFijaOperacion> operaciones = this.rentaFijaDao
		.getRentaFijaOperaciones(dia, IRentaFija.SESION_ALL
				.equals(tipoSesion) ? MERCADO_TODOS : tipoSesion,
				tipoMercado, tipoOperacion, nemo);*/

		String consulta = "SELECT "
				+ " boce.v_rftr_nemo nemo, "
				+ " boce.v_rftr_cantidad cantidad, "
				+ " boce.v_rftr_monto volumen, "
				+ " boce.v_rftr_tasa tasa, "
				+ " cumope.v_cumope_fec_cump hora, "
				+ " CASE WHEN v_rftr_mercado = 'P' THEN 'boceario' ELSE 'Secundario' END mercado "
				+ " FROM unicabd.tbrftrh boce "
				+ " INNER JOIN tbcumope cumope ON cumope.v_cumope_hor_gra = boce.v_rftr_hor_gra "
				+ " WHERE boce.v_rftr_rusiopel = 'SIBO' "
				+ " AND boce.v_rftr_nemo = ? "
				+ " AND boce.v_rftr_hor_gra > ?"
				+ "	AND boce.v_rftr_hor_gra <= ? ";
		
		String[] fechas = UtilFechas.getFechasComparador(fecha);
		Object[] params = { nemo, fechas[1], fechas[0]};

		return this.cargarOperaciones(consulta, params);
	}

	/**
	 * Cargar operaciones.
	 * 
	 * @param consulta
	 *            the consulta
	 * @param params
	 *            the params
	 * 
	 * @return the list< operacion dia acciones>
	 */
	@SuppressWarnings("unchecked")
	private List<OperacionDiaAcciones> cargarOperaciones(String consulta,
			Object params[]) {

		Collection tmp = this.getJdbcTemplate().query(consulta, params,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						OperacionDiaAcciones titulo = new OperacionDiaAcciones();
						titulo.setCantidad(rs.getDouble("cantidad"));

						//titulo.setCreadormercado(rs.getString("creadormercado"
						// ));
						//titulo.setMercado(rs.getString("mercado"));
						titulo.setNemotecnico(rs.getString("nemo"));
						titulo.setPrecio(rs.getDouble("tasa"));
						// titulo.setPromotorLiquidez(rs.getString(
						// "promotorLiquidez"));
						//titulo.setTipoOperacion(rs.getString("tipoOperacion"))
						// 
						titulo.setVolumen(rs.getDouble("volumen"));
						/*titulo
								.setVolumenRecompra(rs
										.getDouble("montoRecompra"));*/

						DateFormat df = new SimpleDateFormat("yyyyMMdd");
						try {
							titulo.setFechaCumpliento(df.parse(rs
									.getString("hora")));
						} catch (ParseException e) {
							log.error(e.getMessage());
						}

						return titulo;
					}
				});

		return new ArrayList<OperacionDiaAcciones>(tmp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesTTVDao#
	 * cargaDatosTituloUltimoDia(co.com.bvc.portal.mercados.modelo.TituloAccion,
	 * java.lang.String)
	 */
	

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesTTVDao#
	 * cargaDatosTituloUltimoDia(co.com.bvc.portal.mercados.modelo.TituloAccion)
	 */
	public void cargaDatosTituloUltimoDia(TituloAccion titulo)
			throws PersistenciaException {

		String fecha20Min = UtilFechas.fechaMenos20Minutos(Calendar
				.getInstance());
		fecha20Min = "'" + fecha20Min + "'";

		String consulta = "SELECT "
			+ " boce.v_rftr_nemo nemo, "
			+ " SUM(boce.v_rftr_cantidad) cantidad, "
			+ " SUM(boce.v_rftr_monto) volumen "
			+ " FROM unicabd.tbrftrh boce "
			+ " WHERE boce.v_rftr_rusiopel = 'BOCE' "
			+ " AND boce.v_rftr_hor_gra < ? "
			+ " AND boce.v_rftr_nemo = ? "
			+ " GROUP BY boce.v_rftr_nemo "
			+ " ORDER BY volumen DESC";

		log.info(" sql ttvs ultdia: " + consulta);

		Object[] params = { titulo.getNemotecnico() };

		List<TituloAccion> tmp = this.cargarDatosTitulo(consulta, params);

		for (TituloAccion tit : tmp) {
			titulo.setCantidad(tit.getCantidad());
			titulo.setVolumen(tit.getVolumen());
			titulo.setHora(tit.getHora());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesTTVDao#
	 * cargaDatosTituloFecha(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public List<TituloAccion> cargaDatosTituloFecha(String nemo,
			String fechaInicio, String fechaFin) throws PersistenciaException {

		String consulta = "SELECT "
			+ "	boce.v_rftr_nemo nemo, "
			+ "	SUBSTR(boce.v_rftr_hor_gra,1,10) fecha, "
			+ "	SUM(boce.v_rftr_cantidad) cantidad, "
			+ "	SUM(boce.v_rftr_monto) volumen "
			+ "	FROM unicabd.tbrftrh boce "
			+ "	WHERE boce.v_rftr_tipofe = 'BOCE' "
			+ "	AND boce.v_rftr_hor_gra < ? "
			+ "	AND boce.v_rftr_hor_gra >= ? "
			+ "	AND  boce.v_rftr_nemo = ? "
			+ "	GROUP BY boce.v_rftr_nemo, SUBSTR(boce.v_rftr_hor_gra,1,10) "
			+ "	ORDER BY fecha ASC";

		Object[] params = { UtilFechas.getFechasComparador(fechaFin)[1],
				UtilFechas.getFechasComparador(fechaInicio)[0], nemo };

		return this.cargarDatosTitulo(consulta, params);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesTTVDao#
	 * cargaDatosTituloFecha(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public List<TituloAccion> cargaDatosSimulTituloFecha(String nemo,
			String fechaInicio, String fechaFin) throws PersistenciaException {

		String consulta = "SELECT "
			+ "	boce.v_rftr_nemo nemo, "
			+ "	SUBSTR(boce.v_rftr_hor_gra,1,10) fecha, "
			+ "	SUM(boce.v_rftr_cantidad) cantidad, "
			+ "	SUM(boce.v_rftr_monto) volumen "
			+ "	FROM unicabd.tbrftrh boce "
			+ "	WHERE boce.v_rftr_tipofe = 'SIBO' "
			+ "	AND boce.v_rftr_hor_gra < ? "
			+ "	AND boce.v_rftr_hor_gra >= ? "
			+ "	AND  boce.v_rftr_nemo = ? "
			+ "	GROUP BY boce.v_rftr_nemo, SUBSTR(boce.v_rftr_hor_gra,1,10) "
			+ "	ORDER BY fecha ASC";

		Object[] params = { UtilFechas.getFechasComparador(fechaFin)[1],
				UtilFechas.getFechasComparador(fechaInicio)[0], nemo };

		return this.cargarDatosTitulo(consulta, params);
	}

	/**
	 * Cargar datos titulo.
	 * 
	 * @param consulta
	 *            the consulta
	 * @param params
	 *            the params
	 * 
	 * @return the list< titulo accion>
	 */
	@SuppressWarnings("unchecked")
	private List<TituloAccion> cargarDatosTitulo(String consulta,
			Object params[]) {

		Collection tmp = this.getJdbcTemplate().query(consulta, params,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TituloAccion resumen = new TituloAccion();
						resumen.setNemotecnico(rs.getString("nemo"));
						resumen.setVolumen(rs.getDouble("volumen"));
						resumen.setCantidad(rs.getDouble("cantidad"));

						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

						try {
							resumen.getHora().setTime(
									df.parse(rs.getString("fecha")));
						} catch (Throwable e) {
							log.warn(e.getMessage());
						}

						return resumen;
					}
				});

		return (new ArrayList<TituloAccion>(tmp));
	}

	public void cargaDatosTituloFecha(TituloAccion titulo, String fecha)
		throws PersistenciaException {
	
		String consulta = "SELECT "
			+ "	boce.v_rftr_nemo nemo, "
			+ "	SUBSTR(boce.v_rftr_hor_gra,1,10) fecha, "
			+ "	SUM(boce.v_rftr_cantidad) cantidad, "
			+ "	SUM(boce.v_rftr_monto) volumen "
			+ "	FROM unicabd.tbrftrh boce "
			+ "	WHERE boce.v_rftr_tipofe = 'Y' "
			+ "	AND boce.v_rftr_hor_gra < ? "
			+ "	AND boce.v_rftr_hor_gra >= ? "
			+ "	AND boce.v_rftr_nemo = ? "
			+ "	GROUP BY boce.v_rftr_nemo, SUBSTR(boce.v_rftr_hor_gra,1,10)  ORDER BY fecha ASC";
	
		Object[] params = { UtilFechas.getFechasComparador(fecha)[1],
			UtilFechas.getFechasComparador(fecha)[0],
			titulo.getNemotecnico() };
	
		List<TituloAccion> tmp = this.cargarDatosTitulo(consulta, params);
	
		for (TituloAccion tit : tmp) {
			titulo.setCantidad(tit.getCantidad());
			titulo.setVolumen(tit.getVolumen());
			titulo.setHora(tit.getHora());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesBoceasDao#getBoceasOperaciones
	 * (java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@SuppressWarnings( { "unchecked" })
	public List<RentaFijaOperacion> getBoceasOperaciones(String dia,
			String sesion, String tipoMercado, String tipoOperacion, String nemo)
			throws PersistenciaException {

		String sufijoTabla = null;
		String nombreConsulta = null;
		String[] fechas = null;

		if (dia == null || UtilFechas.getHoy("yyyy-MM-dd").equals(dia)) {
			sufijoTabla = "";
			fechas = UtilFechas.getFechasComparadorDiaActual();
		} else {
			sufijoTabla = "a";
			fechas = UtilFechas.getFechasComparador(dia);
		}

		if (tipoOperacion == IConstantesAcciones.SIMULTANEA) {
			nombreConsulta = IConstantesConsultasAcciones.BOCEAS_DETALLE_SIMULTANEAS;
		} else {
			nombreConsulta = IConstantesConsultasAcciones.BOCEAS_DETALLE_GENERAL;
		}

		List<Map> resQuery = cargarPorNombreQuery(nombreConsulta, new String[] {
				"a", "rueda",  "tipoOperacion", "fechaIni",
				"fechaFin", "nemo" }, new Object[] {
				sufijoTabla,
				sesion,
				tipoOperacion , fechas[0], fechas[1], nemo });
		List<RentaFijaOperacion> res = new ArrayList<RentaFijaOperacion>();

		try {
			res = UtilDto.obtenerObjetos(RentaFijaOperacion.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException pEx = new PersistenciaException(
					"Error al cargar el resultado de la consulta "
							+ nombreConsulta
							+ " en objetos de tipo RentaFijaOperacion");
			pEx.initCause(ex);
			throw pEx;
		}
		return res;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesBoceasDao#getEmisor(java.
	 * lang.String)
	 */
	@SuppressWarnings("unchecked")
	public EmisorTitulo getEmisor(String nemo) throws PersistenciaException {

		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.BOCEAS_EMISOR, nombresParametros,
				parametros);

		Iterator it = resQuery.iterator();
		EmisorTitulo emi = new EmisorTitulo();

		while (it.hasNext()) {
			Map tmp = (Map) it.next();
			emi.setRazonSocial((String) tmp.get("RazonSocial"));
			emi.setSigla((String) tmp.get("Sigla"));
			emi.setNit((String) tmp.get("Nit"));
			emi.setSector((String) tmp.get("Sector"));
			emi.setPresidente((String) tmp.get("Presidente"));
			emi.setDireccion((String) tmp.get("Direccion"));
			emi.setCiudad((String) tmp.get("Ciudad"));
			emi.setSituacion((String) tmp.get("Situacion"));
			emi.setTelefono((String) tmp.get("Telefono"));
			emi.setFax((String) tmp.get("Fax"));
		}
		return emi;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesBoceasDao#getDetalleExcelBocea(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RentaFijaSumaOperacion> getDetalleExcelBocea(String fechaIni, String fechaFin, String nemo){
		List<RentaFijaSumaOperacion> ret = new LinkedList<RentaFijaSumaOperacion>();
		String sufijoTabla = "a";
		if (fechaIni == null
				|| UtilFechas.getHoy("yyyy-MM-dd").equals(fechaIni.subSequence(0, 10))) {
			// se consulta el día actual
			sufijoTabla = "";
			String fechas[] = UtilFechas.getFechasComparadorDiaActual();
			fechaIni = fechas[0];
			fechaFin = fechas[1];
		}
		try{
			List<Map> resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.BOCEA_DETALLE_EXCEL,
					new String[] { "a", "nemo",
							"fechaIni", "fechaFin" },
					new Object[] {
							sufijoTabla,
							nemo,
							fechaIni,
							fechaFin });
			ret  = UtilDto.obtenerObjetos(RentaFijaSumaOperacion.class, resQuery);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesBoceasDao#getRentaFijaTituloBoceas
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public RentaFijaResumenTitulo getRentaFijaTituloBoceas(String nemo)
			throws PersistenciaException {

		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.BOCEAS_TITULO, nombresParametros,
				parametros);

		Iterator it = resQuery.iterator();
		RentaFijaResumenTitulo res = new RentaFijaResumenTitulo();

		while (it.hasNext()) {
			Map tmp = (Map) it.next();
			res.setNemo((String) tmp.get("Nemo"));
			res.setCodSuper((String) tmp.get("CodigoSuper"));
			res.setTipoTitulo((String) tmp.get("TipoTitulo"));
			res.setIsin1((String) tmp.get("Isin1"));
			res.setIsin2((String) tmp.get("Isin2"));
			res.setIsin3((String) tmp.get("Isin3"));
			res.setEmisor((String) tmp.get("Emisor"));
			Integer f = (Integer) tmp.get("FechaEmision");
			String femision = f.toString();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyyMMdd");
			java.util.Date fechaemision = null;
			try {
				fechaemision = sdf.parse(femision);
			} catch (ParseException e) {
				// e.printStackTrace();
			}
			res.setFechaEmision(fechaemision);

			Integer f2 = (Integer) tmp.get("FechaVencimiento");
			String fvenc = f2.toString();
			java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(
					"yyyyMMdd");
			java.util.Date fechavencimiento = null;
			try {
				fechavencimiento = sdf2.parse(fvenc);
			} catch (ParseException e) {
				// e.printStackTrace();
			}
			res.setFechaVencimiento(fechavencimiento);

			res.setEstado((String) tmp.get("Estado"));
			res.setMoneda((String) tmp.get("Moneda"));
			res.setTasaReferencia((String) tmp.get("TasaReferencia"));
			Float tcupon = (Float) tmp.get("TasaCupon");
			String tcup = tcupon.toString();
			res.setTasaCupon(tcup);
			res.setModalidadPago((String) tmp.get("ModalidadPago"));

		}

		return res;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesDao#
	 * obtenerVolumenesNegociados(java.lang.String)
	 */
	@SuppressWarnings("unchecked")

	
	public List<ResumenBoceas> ExcelBoceas(String fecha)
			throws PersistenciaException {

		List<ResumenBoceas> respuesta = new ArrayList<ResumenBoceas>();
		String sufijoTabla = "a";
		String tipoSesion = "BOCE";
		
		if(fecha ==null || "".equals(fecha.trim())){
		
			fecha=UtilFechas.getHoy("yyyy-MM-dd");
			sufijoTabla="";
		
		}
		
		List<Map> resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_BOCEAS_HOY,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN,"a", "rueda" },
					new String[] { fecha,sufijoTabla,tipoSesion });
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenBoceas.class, resQuery);

		} catch (Exception ex) {
			throw new PersistenciaException(
					"error obteniendo las Boceas del día " + fecha);
		}

		return respuesta;
	}
	
	public List<ResumenBoceas> ExcelBoceasSimul(String fecha)
	throws PersistenciaException {

		List<ResumenBoceas> respuesta = new ArrayList<ResumenBoceas>();
		String sufijoTabla = "a";
		String tipoSesion = "SIBO";
		
		if(fecha ==null || "".equals(fecha.trim())){
		
			fecha=UtilFechas.getHoy("yyyy-MM-dd");
			sufijoTabla="";
		
		}
		
		List<Map> resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_BOCEAS_HOY,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN,"a", "rueda" },
					new String[] { fecha,sufijoTabla,tipoSesion });
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenBoceas.class, resQuery);
		
		} catch (Exception ex) {
			throw new PersistenciaException(
					"error obteniendo las Boceas del día " + fecha);
		}
		
		return respuesta;
	}


}
