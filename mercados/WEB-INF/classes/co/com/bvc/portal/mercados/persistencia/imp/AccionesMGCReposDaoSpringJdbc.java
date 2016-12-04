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
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCReposDao;
import co.com.bvc.portal.mercados.util.IConstantesConsultasAcciones;

public class AccionesMGCReposDaoSpringJdbc extends JDBCDaoImp implements IAccionesMGCReposDao{
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesReposDao#
	 * cargarMasTranzadasDia(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> cargarMasTranzadasDia(String fecha)
			throws PersistenciaException {

		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();

		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_REPOS_HISTORIA,
				new String[] { IConstantesConsultasAcciones.FECHA_FIN },
				new String[] { fecha });
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);

		} catch (Exception ex) {
			throw new PersistenciaException(
					"error obteniendo los repos del día " + fecha);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesReposDao#
	 * cargarMasTranzadasDiaUltDia()
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> cargarMasTranzadasDiaUltDia() throws Exception {
		String fecha20Min = UtilFechas
				.fechaMenos20Minutos(new GregorianCalendar());
		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_REPOS_HOY,
				new String[] { IConstantesConsultasAcciones.FECHA_FIN },
				new String[] { fecha20Min });
		List<ResumenAccionMGC> respuesta = UtilDto.obtenerObjetos(
				ResumenAccionMGC.class, resQuery);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesReposDao#
	 * operacionesUltimoDia(java.lang.String)
	 */
	public List<OperacionDiaAcciones> operacionesUltimoDia(String nemo)
			throws PersistenciaException {

		String consulta = "SELECT repos.v_rftr_nemo nemo                   , "
				+ "       repos.v_rftr_cantidad cantidad           , "
				+ "       repos.v_rftr_monto volumen               , "
				+ "       repos.v_rftr_tasarepo tasa               , "
				+ "       cumope.v_cumope_fec_cump hora            , "
				+ "       repos.v_rftr_monto_recompra montoRecompra, "
				+ "       CASE "
				+ "              WHEN "
				+ "                     ( "
				+ "                            v_rftr_mercado = 'P' "
				+ "                     ) "
				+ "              THEN 'Primario' "
				+ "              ELSE 'Secundario' "
				+ "       END mercado "
				+ "FROM   unicabd.tbrftr repos "
				+ "       INNER JOIN "
				+ "              (SELECT v_cumope_fec_cump, "
				+ "                      v_cumope_hor_gra "
				+ "              FROM    tbcumope "
				+ "              WHERE   v_cumope_nemo    = ? "
				+ "                  AND v_cumope_hor_gra > ? "
				+ "              ) cumope "
				+ "ON "
				+ "       ( "
				+ "              cumope.v_cumope_hor_gra = repos.v_rftr_hor_gra "
				+ "       ) " + "WHERE  repos.v_rftr_tipofe = 'A' "
				+ "   AND repos.v_rftr_nemo   = ?";

		Object[] params = { nemo, UtilFechas.getHoy("yyyy-MM-dd"), nemo };

		return this.cargarOperaciones(consulta, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesReposDao#operaciones
	 * (java.lang.String, java.lang.String)
	 */
	public List<OperacionDiaAcciones> operaciones(String nemo, String fecha)
			throws PersistenciaException {

		String consulta = "SELECT repos.v_rftra_nemo nemo                   , "
				+ "       repos.v_rftra_cantidad cantidad           , "
				+ "       repos.v_rftra_monto volumen               , "
				+ "       repos.v_rftra_tasarepo tasa               , "
				+ "       cumope.v_cumope_fec_cump hora             , "
				+ "       repos.v_rftra_monto_recompra montoRecompra, "
				+ "       CASE "
				+ "              WHEN "
				+ "                     ( "
				+ "                            v_rftra_mercado = 'P' "
				+ "                     ) "
				+ "              THEN 'Primario' "
				+ "              ELSE 'Secundario' "
				+ "       END mercado "
				+ "FROM   unicabd.tbrftrah repos "
				+ "       INNER JOIN "
				+ "              (SELECT v_cumope_fec_cump, "
				+ "                      v_cumope_hor_gra "
				+ "              FROM    tbcumope "
				+ "              WHERE   v_cumope_nemo     = ? "
				+ "                  AND v_cumope_hor_gra  < ? "
				+ "                  AND v_cumope_hor_gra >= ? "
				+ "              ) cumope "
				+ "ON "
				+ "       ( "
				+ "              cumope.v_cumope_hor_gra = repos.v_rftra_hor_gra "
				+ "       ) " + "WHERE  repos.v_rftra_tipofe   = 'A' "
				+ "   AND repos.v_rftra_nemo     = ? "
				+ "   AND repos.v_rftra_hor_gra  < ? "
				+ "   AND repos.v_rftra_hor_gra >= ?";

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
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesReposDao#
	 * cargaDatosTituloFecha(co.com.bvc.portal.mercados.modelo.TituloAccion,
	 * java.lang.String)
	 */
	public void cargaDatosTituloFecha(TituloAccion titulo, String fecha)
			throws PersistenciaException {

		String consulta = "select  repos.v_rftra_nemo nemo,"
				+ " substr(repos.v_rftra_hor_gra,1,10) fecha,"
				+ " sum(repos.v_rftra_cantidad) cantidad,"
				+ " sum(repos.v_rftra_monto) volumen"
				+ " from unicabd.tbrftrah repos"
				+ " where repos.v_rftra_tipofe = 'A'  AND"
				+ " repos.v_rftra_hor_gra < ?  AND"
				+ " repos.v_rftra_hor_gra >= ? "
				+ " AND  repos.v_rftra_nemo = ? "
				+ " group by repos.v_rftra_nemo, substr(repos.v_rftra_hor_gra,1,10)  order by fecha asc";

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
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesReposDao#
	 * cargaDatosTituloUltimoDia(co.com.bvc.portal.mercados.modelo.TituloAccion)
	 */
	public void cargaDatosTituloUltimoDia(TituloAccion titulo)
			throws PersistenciaException {

		String fecha20Min = UtilFechas.fechaMenos20Minutos(Calendar
				.getInstance());
		fecha20Min = "'" + fecha20Min + "'";

		String consulta = " select " + " repos.v_rftr_nemo nemo, "
				+ " sum(repos.v_rftr_cantidad) cantidad, "
				+ " sum(repos.v_rftr_monto) volumen "
				+ " from unicabd.tbrftr repos "
				+ " where repos.v_rftr_tipofe = 'A' " + " AND  "
				+ " repos.v_rftr_hor_gra < " + fecha20Min + " "
				+ " repos.v_rftr_nemo = ? " + " group by repos.v_rftr_nemo "
				+ " order by volumen desc";

		log.info(" sql repos ultdia: " + consulta);

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
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesReposDao#
	 * cargaDatosTituloFecha(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public List<TituloAccion> cargaDatosTituloFecha(String nemo,
			String fechaInicio, String fechaFin) throws PersistenciaException {

		String consulta = "select  repos.v_rftra_nemo nemo,"
				+ " substr(repos.v_rftra_hor_gra,1,10) fecha,"
				+ " sum(repos.v_rftra_cantidad) cantidad,"
				+ " sum(repos.v_rftra_monto) volumen"
				+ " from unicabd.tbrftra repos"
				+ " where repos.v_rftra_tipofe = 'A'  AND"
				+ " repos.v_rftra_hor_gra < ?  AND"
				+ " repos.v_rftra_hor_gra >= ? "
				+ " AND  repos.v_rftra_nemo = ? "
				+ " group by repos.v_rftra_nemo, substr(repos.v_rftra_hor_gra,1,10)  order by fecha asc";

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

}
