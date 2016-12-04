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
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.jdbc.JDBCDaoImp;
import co.com.bvc.portal.comun.util.UtilDto;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCTTVDao;
import co.com.bvc.portal.mercados.util.IConstantesConsultasAcciones;

public class AccionesMGCTTVDaoSpringJdbc extends JDBCDaoImp implements IAccionesMGCTTVDao {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesDao#
	 * obtenerVolumenesNegociados(java.lang.String)
	 */
	@SuppressWarnings("unchecked")

	
	public List<ResumenAccionMGC> cargarMasTranzadasDia(String fecha)
			throws PersistenciaException {

		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();

		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_TTV_HISTORIA,
				new String[] { IConstantesConsultasAcciones.FECHA_FIN },
				new String[] { fecha });
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);

		} catch (Exception ex) {
			throw new PersistenciaException(
					"error obteniendo las TTVs del día " + fecha);
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
	public List<ResumenAccionMGC> cargarMasTranzadasDiaUltDia() throws Exception {
		String fecha20Min = UtilFechas
				.fechaMenos20Minutos(new GregorianCalendar());
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_TTV_HOY,
				new String[] { IConstantesConsultasAcciones.FECHA_FIN },
				new String[] { fecha20Min });
		List<ResumenAccionMGC> respuesta = UtilDto.obtenerObjetos(
				ResumenAccionMGC.class, resQuery);
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
			+ "	ttvs.v_rftr_nemo nemo, "
			+ "	ttvs.v_rftr_cantidad cantidad, "
			+ "	ttvs.v_rftr_monto volumen, "
			+ "	ttvs.v_rftr_tasarepo tasa, "
			+ "	cumope.v_cumope_fec_cump hora, "
			+ "	ttvs.v_rftr_monto_recompra montoRecompra, "
			+ "	CASE WHEN v_rftr_mercado = 'P' THEN 'Primario' ELSE 'Secundario' END mercado "
			+ "	FROM unicabd.tbrftr ttvs "
			+ "	INNER JOIN tbcumope cumope ON cumope.v_cumope_hor_gra = ttvs.v_rftr_hor_gra "
			+ "	WHERE ttvs.v_rftr_tipofe = 'Y' "
			+ "	AND ttvs.v_rftr_nemo = ? "
			+ "	AND ttvs.v_rftr_hor_gra > ?";

		Object[] params = { nemo, UtilFechas.getHoy("yyyy-MM-dd"), nemo };

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

		String consulta = "SELECT "
			+ "	ttvs.v_rftra_nemo nemo, "
			+ "	ttvs.v_rftra_cantidad cantidad, "
			+ "	ttvs.v_rftra_monto volumen, "
			+ "	ttvs.v_rftra_tasarepo tasa, "
			+ "	cumope.v_cumope_fec_cump hora, "
			+ "	ttvs.v_rftra_monto_recompra montoRecompra, "
			+ "	CASE WHEN v_rftra_mercado = 'P' THEN 'Primario' ELSE 'Secundario' END mercado "
			+ "	FROM unicabd.tbrftra ttvs "
			+ "	INNER JOIN tbcumope cumope ON cumope.v_cumope_hor_gra = ttvs.v_rftra_hor_gra "
			+ "	WHERE  ttvs.v_rftra_tipofe   = 'Y' "
			+ "	AND ttvs.v_rftra_nemo     = ? "
			+ "	AND ttvs.v_rftra_hor_gra  < ? "
			+ "	AND ttvs.v_rftra_hor_gra >= ? ";
		
		String[] fechas = UtilFechas.getFechasComparador(fecha);
		Object[] params = { nemo, fechas[1], fechas[0], nemo, fechas[1], fechas[0] };

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
						titulo.setMercado(rs.getString("mercado"));
						titulo.setNemotecnico(rs.getString("nemo"));
						titulo.setPrecio(rs.getDouble("tasa"));
						// titulo.setPromotorLiquidez(rs.getString(
						// "promotorLiquidez"));
						//titulo.setTipoOperacion(rs.getString("tipoOperacion"))
						// 
						titulo.setVolumen(rs.getDouble("volumen"));
						titulo
								.setVolumenRecompra(rs
										.getDouble("montoRecompra"));

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
	 * cargaDatosTituloFecha(co.com.bvc.portal.mercados.modelo.TituloAccion,
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
			+ "	ttvs.v_rftr_nemo nemo, "
			+ "	SUM(ttvs.v_rftr_cantidad) cantidad, "
			+ "	SUM(ttvs.v_rftr_monto) volumen "
			+ "	FROM unicabd.tbrftr ttvs "
			+ "	WHERE ttvs.v_rftr_tipofe = 'Y' "
			+ "	AND ttvs.v_rftr_hor_gra < ? "
			+ "	AND ttvs.v_rftr_nemo = ? "
			+ "	GROUP BY ttvs.v_rftr_nemo "
			+ "	ORDER BY volumen DESC";

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
			+ "	ttvs.v_rftra_nemo nemo, "
			+ "	SUBSTR(ttvs.v_rftra_hor_gra,1,10) fecha, "
			+ "	SUM(ttvs.v_rftra_cantidad) cantidad, "
			+ "	SUM(ttvs.v_rftra_monto) volumen "
			+ "	FROM unicabd.tbrftra ttvs "
			+ "	WHERE ttvs.v_rftra_tipofe = 'Y' "
			+ "	AND ttvs.v_rftra_hor_gra < ? "
			+ "	AND ttvs.v_rftra_hor_gra >= ? "
			+ "	AND  ttvs.v_rftra_nemo = ? "
			+ "	GROUP BY ttvs.v_rftra_nemo, SUBSTR(ttvs.v_rftra_hor_gra,1,10) "
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
			+ "	ttvs.v_rftra_nemo nemo, "
			+ "	SUBSTR(ttvs.v_rftra_hor_gra,1,10) fecha, "
			+ "	SUM(ttvs.v_rftra_cantidad) cantidad, "
			+ "	SUM(ttvs.v_rftra_monto) volumen "
			+ "	FROM unicabd.tbrftra ttvs "
			+ "	WHERE ttvs.v_rftra_tipofe = 'Y' "
			+ "	AND ttvs.v_rftra_hor_gra < ? "
			+ "	AND ttvs.v_rftra_hor_gra >= ? "
			+ "	AND ttvs.v_rftra_nemo = ? "
			+ "	GROUP BY ttvs.v_rftra_nemo, SUBSTR(ttvs.v_rftra_hor_gra,1,10)  ORDER BY fecha ASC";
	
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

}
