package co.com.bvc.portal.mercados.persistencia.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ui.Model;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.jdbc.JDBCDaoImp;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilDto;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.comun.entidades.Ciudad;
import co.com.bvc.portal.comun.entidades.Sector;
import co.com.bvc.portal.comun.entidades.TipoEmision;
import co.com.bvc.portal.comun.util.IConstatesTipos;
import co.com.bvc.portal.mercados.modelo.AccionesMGCBusquedaForm;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.CotizaPaisMGC;
import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.modelo.CierreGeneral;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InformacionTicker;
import co.com.bvc.portal.mercados.modelo.IntradiaTituloMGC;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.ResumenEtfsMGC;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao;
import co.com.bvc.portal.mercados.util.IConstantesConsultasAcciones;
import co.com.bvc.portal.mercados.modelo.HorarioMercadoMGC;

public class AccionesMGCDaoSpringJdbc extends JDBCDaoImp implements IAccionesMGCDao{
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IAccionesDao#
	 * obtenerVolumenesNegociados(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public double[] obtenerVolumenesNegociados(String fecha) throws PersistenciaException {
		String[] params = null;
		double vCompraventas = 0d;
		double vRepos = 0d;
		double vTTV = 0d;
		double vETF = 0d;
		if (fecha == null || "".equals(fecha.trim())) {
			fecha = UtilFechas.fechaMenos20Minutos(new GregorianCalendar()).substring(0, 16);
			params = new String[] {IConstantesConsultasAcciones.TABLA_VOLMENES_TOTALES_DIA,"<", fecha };
		} else {
			params = new String[] {IConstantesConsultasAcciones.TABLA_VOLMENES_TOTALES_HISTORIA,"", fecha };
		}
		List<Map> resQuery = cargarPorNombreQuery(IConstantesConsultasAcciones.VOLUMENES_TOTALES_POR_FECHA,new String[] { IConstantesConsultasAcciones.TABLA,IConstantesConsultasAcciones.OPERADOR,IConstantesConsultasAcciones.FECHA_FIN }, params);

		if (resQuery != null && !resQuery.isEmpty()) {
			Map fila = resQuery.get(0);
			vCompraventas = (Double) fila.get("vol_acciones_mgc");
			vRepos = (Double) fila.get("vol_repos_mgc");
			vTTV = (Double) fila.get("vol_ttv_mgc");
			vETF = (Double) fila.get("vol_etf_mgc");
		}

		return new double[] { vCompraventas, vRepos, vTTV,vETF };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesDao#ultimoPrecioAccion
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public IntradiaTituloMGC ultimoPrecioAccion(String fecha, String nemo)
			throws PersistenciaException {

		if (null == fecha || "".equals(fecha.trim())) {
			fecha = UtilFechas.getHoy("yyyyMMdd");
		}

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "ULTIMO_PRECIO_ACCIONMGC." + fecha + "." + nemo;

		IntradiaTituloMGC ret;

		ret = (IntradiaTituloMGC) cache.getObject(queryKey); // ACCIONES-015
		if (null == ret) {
			synchronized (this) {
				ret = (IntradiaTituloMGC) cache.getObject(queryKey);
				if (null == ret) {
					log.debug("Procesando Ultimo precio accion mgc nemo : " + nemo
							+ " fecha: " + fecha);
					ret = new IntradiaTituloMGC();

					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasAcciones.ULTIMO_PRECIO_POR_ACCION,
							new String[] {
									IConstantesConsultasAcciones.FECHA_FIN,
									IConstantesConsultasAcciones.NEMOTECNICO },
							new String[] { fecha, nemo });
					if (null != resQuery && !resQuery.isEmpty()) {
						ret.setPrecio((Double) resQuery.get(0).get(
								"precioAnterior"));
						ret.setHora(resQuery.get(0).get("v_ultpre_fec_ult_pre")
								.toString());
					}
					cache.putObject(queryKey, ret);
					return ret;
				}
			}
		} else {
			log.debug("Cargando desde cache Ultimo precio accion mgc nemo : " + nemo
					+ " fecha: " + fecha);
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesDao#ultimoPrecioEtf
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public IntradiaTituloMGC ultimoPrecioEtf(String fecha, String nemo)
			throws PersistenciaException {

		if (fecha == null || "".equals(fecha.trim())) {
			fecha = UtilFechas.getHoy("yyyyMMdd");
		}

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "ULTIMO_PRECIO_ETFMGC." + fecha + "." + nemo;

		IntradiaTituloMGC ret;

		ret = (IntradiaTituloMGC) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (IntradiaTituloMGC) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Ultimo precio accion mgc nemo : " + nemo
							+ " fecha: " + fecha);
					ret = new IntradiaTituloMGC();

					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasAcciones.ULTIMO_PRECIO_POR_ACCION,
							new String[] {
									IConstantesConsultasAcciones.FECHA_FIN,
									IConstantesConsultasAcciones.NEMOTECNICO },
							new String[] { fecha, nemo });
					if (resQuery != null && !resQuery.isEmpty()) {
						ret.setPrecio((Double) resQuery.get(0).get(
								"precioAnterior"));
						ret.setHora(resQuery.get(0).get("v_ultpre_fec_ult_pre")
								.toString());
					}
					cache.putObject(queryKey, ret);
					return ret;
				}
			}
		} else {
			log.debug("Cargando desde cache Ultimo precio etf mgc nemo : " + nemo
					+ " fecha: " + fecha);
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarHistoricoTitulo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoTitulo(String titulo)
			throws PersistenciaException {
		List<ICierre> respuesta;
		Object params[] = { titulo };

		String consulta = "select hist.v_acmta_fecha fecha, "
				+ "hist.v_acmta_pre_cie cierre, "
				+ "hist.v_acmta_uni_tra cantidad, "
				+ "hist.V_ACMTA_PRE_MAY precioMayor, "
				+ "hist.V_ACMTA_PRE_MEN precioMenor "
				+ "	from unicabd.TBACMTA hist "
				+ "	where hist.v_ACMTA_nemo = ? "
				+ " AND hist.V_ACMTA_FIJO_PRECIO = 'S'"
				+ " order by hist.v_acmta_fecha desc";

		Collection tmp = this.getJdbcTemplate().query(consulta, params,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ICierre resumen = new CierreGeneral();
						resumen.setValorCierre(rs.getDouble("cierre"));
						resumen.setVolumen(rs.getDouble("cantidad"));
						resumen.setValorMin(rs.getDouble("precioMenor"));
						resumen.setValorMax(rs.getDouble("precioMayor"));
						String fecha = rs.getString("fecha");
						DateFormat df = new SimpleDateFormat("yyyy-MM-ddHHmm");
						try {
							resumen.getFechaHora().setTime(
									df.parse(fecha + "1300"));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return resumen;
					}
				});

		respuesta = new ArrayList<ICierre>(tmp);
		if (respuesta != null && !respuesta.isEmpty()) {
			IntradiaTituloMGC it = ultimoPrecioAccion(null, titulo);
			if (it != null && it.getPrecio() != null){
				for (ICierre cierre : respuesta) {
					cierre.setValorUltimoCierre(it.getPrecio());
				}
			}else{
				for (ICierre cierre : respuesta) {
					cierre.setValorUltimoCierre(cierre.getValorCierre());
				}
			}
		}
		return respuesta;

	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarHistoricoMGCTitulo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoMGCTitulo(String titulo,String tipoMGC)
			throws PersistenciaException {
		List<ICierre> respuesta;
		Object params[] = { titulo };

		String consulta = "select hist.v_acmta_fecha fecha, "
				+ "hist.v_acmta_pre_cie cierre, "
				+ "hist.v_acmta_uni_tra cantidad, "
				+ "hist.V_ACMTA_PRE_MAY precioMayor, "
				+ "hist.V_ACMTA_PRE_MEN precioMenor "
				+ "	from unicabd.TBACMTA hist,unicabd.tbacmi acmi "
				+ "	where hist.v_ACMTA_nemo = ? "
				+ " AND hist.V_ACMTA_FIJO_PRECIO = 'S'"
				+ " AND  hist.v_ACMTA_nemo=acmi.v_ACMI_nemo"
				+" AND acmi.v_ACMI_mgc ='"+tipoMGC+"'"
				+ " order by hist.v_acmta_fecha desc";

		Collection tmp = this.getJdbcTemplate().query(consulta, params,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ICierre resumen = new CierreGeneral();
						resumen.setValorCierre(rs.getDouble("cierre"));
						resumen.setVolumen(rs.getDouble("cantidad"));
						resumen.setValorMin(rs.getDouble("precioMenor"));
						resumen.setValorMax(rs.getDouble("precioMayor"));
						String fecha = rs.getString("fecha");
						DateFormat df = new SimpleDateFormat("yyyy-MM-ddHHmm");
						try {
							resumen.getFechaHora().setTime(
									df.parse(fecha + "1300"));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return resumen;
					}
				});

		respuesta = new ArrayList<ICierre>(tmp);
		if (respuesta != null && !respuesta.isEmpty()) {
			IntradiaTituloMGC it = ultimoPrecioAccion(null, titulo);
			if (it != null && it.getPrecio() != null){
				for (ICierre cierre : respuesta) {
					cierre.setValorUltimoCierre(it.getPrecio());
				}
			}else{
				for (ICierre cierre : respuesta) {
					cierre.setValorUltimoCierre(cierre.getValorCierre());
				}
			}
		}
		return respuesta;

	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarDatosTituloUltimoDia(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public TituloAccion cargarDatosTituloUltimoDia(String nemo)
			throws Exception {
		TituloAccion titulo = new TituloAccion();
		String consulta = "SELECT `tbacmi`.`V_ACMI_NEMO`  AS `nemotecnico`            , "
				+ "`tbacmi`.`V_ACMI_EMISOR`                    AS `emisor`              , "
				+ "`tbacmi`.`V_ACMI_CODIGO_SUPER`              AS `codigoSuper`         , "
				+ "`tbacmi`.`V_ACMI_DETALLE_DIVID`             AS `dividendos`          , "
				+ "`tbacmi`.`V_ACMI_NUM_ACC_CIR`               AS `accionesCirculacion` , "
				+ "`tbacmi`.`V_ACMI_VALOR_NOMINAL`             AS `valorNominal`        ,   "
				+ "`tbacmi`.`V_ACMI_PRECIO_BASE`               AS `precioBase`          ,      "
				+ "`tbacmi`.`V_ACMI_PRECIO_MINIMO`             AS `precioMinimo`        ,        "
				+ "`tbacmi`.`V_ACMI_PRECIO_MAXIMO`             AS `precioMaximo`        ,        "
				+ "`tbacmi`.`V_ACMI_BURSATILIDAD`              AS `bursatilidad`        ,        "
				+ "`tbacmi`.`V_ACMI_ESTADO`                    AS `estado`              ,        "
				+ "`tbacmi`.`V_ACMI_COD_ISIN_1`                AS `isin` 				  "
				+ "FROM   unicabd.tbacmi "
				+ "where `tbacmi`.`V_ACMI_NEMO` = ?  limit 1";
		Object params[] = { nemo };
		log.info("query: " + consulta + " nemo: " + nemo);
		Collection<TituloAccion> tmp = this.getJdbcTemplate().query(consulta,
				params, new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TituloAccion titulo = new TituloAccion();
						titulo.setAccionesCirculacion(rs
								.getDouble("accionesCirculacion"));
						titulo.setBursatilidad(rs.getString("bursatilidad"));
						titulo.setCodigoSuper(rs.getString("codigoSuper"));
						titulo.setDividendos(rs.getString("dividendos"));
						titulo.setIsin(rs.getString("isin"));
						titulo.setEstado(rs.getString("estado"));
						titulo.setNemotecnico(rs.getString("nemotecnico"));
						titulo.setPrecioBase(rs.getDouble("precioBase"));
						titulo.setPrecioMaximo(rs.getDouble("precioMaximo"));
						titulo.setPrecioMinimo(rs.getDouble("precioMinimo"));
						titulo.setValorNominal(rs.getDouble("valorNominal"));
						EmisorTitulo emisor = new EmisorTitulo();
						emisor.setSigla(rs.getString("emisor"));
						titulo.setEmisor(emisor);
						return titulo;
					}
				});
		if (tmp != null && !tmp.isEmpty()) {
			titulo = tmp.iterator().next();
			if (titulo != null && titulo.getEmisor() != null) {
				titulo.setEmisor(this.cargarEmisor(titulo.getEmisor()
						.getSigla()));
			}
		}
		titulo.setIdEmisor(((ResumenAccionMGC)(this.consultaDatosEmisor(nemo).get(0))).getIdEmisor());
		this.cargarDatosRpgQtobin(titulo);
		this.cargarDatosExtendidosAcciones(titulo);
		return titulo;
	}

	/**
	 * Cargar datos rpg qtobin.
	 * 
	 * @param ta the ta
	 */
	@SuppressWarnings("unchecked")
	private void cargarDatosRpgQtobin(TituloAccion ta) {
		try {
			List<Map> resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.QTOBIN_RPG_POR_NEMO,
					new String[] { IConstantesConsultasAcciones.NEMOTECNICO },
					new String[] { ta.getNemotecnico() });
			List<TituloAccion> res = UtilDto.obtenerObjetos(TituloAccion.class,
					resQuery);
			if (res != null && !res.isEmpty()) {
				TituloAccion val = res.get(0);
				ta.setQtobin(val.getQtobin());
				ta.setRpg(val.getRpg());
			}
		} catch (Exception ex) {
			log.error("error obteniendo el qtobin y el rpg de la acción "
					+ ta.getNemotecnico());
		}

	}
	
	/**
	 * Cargar datos extendidos de acciones MGC.
	 * 
	 * @param TituloAccion tituloAccion
	 * 
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	private void cargarDatosExtendidosAcciones(TituloAccion tituloAccion) {
		try {
			List<Map> resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.DATOS_EXTENDIDOS_ACCIONES_MGC,
					new String[] { IConstantesConsultasAcciones.NEMOTECNICO },
					new String[] { tituloAccion.getNemotecnico() });
			
			if (resQuery != null && !resQuery.isEmpty()) {
				Map fila = resQuery.get(0);
				tituloAccion.setPaisOrigen((String) fila.get("paisOrigen"));
				tituloAccion.setPatrocinador((String) fila.get("patrocinador"));
				tituloAccion.setCotizacionPrincipal((String) fila.get("cotizacionPrincipal"));
				tituloAccion.setOtrasBolsas((String) fila.get("otrasBolsas"));
				tituloAccion.setCusip((String) fila.get("cusip"));
				log.info("EN EL DAO CUSID: "+(String) fila.get("cusip"));
			}
		} catch (Exception ex) {
			log.error("error obteniendo información extendida de acciones MGC "
					+ tituloAccion.getNemotecnico());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesDao#operacionesDia(java
	 * .lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<OperacionDiaAcciones> operacionesDia(String nemo, String fecha,
			boolean delay) throws Exception {
		List<OperacionDiaAcciones> respuesta = new ArrayList<OperacionDiaAcciones>();
		String[] fechaMinutos = null;
		String sufijo = "";
		List<Map> resQuery = null;
		if (fecha == null) {
			fechaMinutos = UtilFechas.getFechasComparadorDiaActual();
			if (!delay) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm");
				fechaMinutos[1] = sdf.format(new GregorianCalendar().getTime());
			}
		} else {
			fechaMinutos = UtilFechas.getFechasComparador(fecha);
			sufijo = "a";
		}
		resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.OPERACIONES_COMPRAVENTA,
				new String[] { IConstantesConsultasAcciones.NEMOTECNICO,
						IConstantesConsultasAcciones.SUFIJO_TABLA,
						IConstantesConsultasAcciones.FECHA_FIN,
						IConstantesConsultasAcciones.FECHA_INI }, new String[] {
						nemo, sufijo, fechaMinutos[1], fechaMinutos[0] });

		respuesta = UtilDto
				.obtenerObjetos(OperacionDiaAcciones.class, resQuery);

		return respuesta;

	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarEmisor(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public EmisorTitulo cargarEmisor(String emisor) throws Exception {

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_HOURLY);
		String queryKey = "INFOMGC_EMISOR." + emisor + "."
				+ UtilFechas.getHoy("yyyyMMddHH");

		EmisorTitulo ret;
		ret = (EmisorTitulo) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (EmisorTitulo) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Informacion Emisor : " + emisor);
					List<Map> resQuery = cargarPorNombreQuery(
							IConstantesConsultasAcciones.EMISOR_POR_CODIGO,
							new String[] { IConstantesConsultasAcciones.OPERADOR },
							new String[] { emisor });

					List<EmisorTitulo> res = UtilDto.obtenerObjetos(
							EmisorTitulo.class, resQuery);

					if (res != null && !res.isEmpty()) {
						ret = (EmisorTitulo) res.get(0);
						cache.putObject(queryKey, ret);
						return ret;
					}

					ret = new EmisorTitulo();
					cache.putObject(queryKey, ret);
				}
			}
		} else {
			log.debug("Cargando desde el cache: " + emisor);
		}
		return ret;

	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#accionesTicker()
	 */
	@SuppressWarnings("unchecked")
	public List<InformacionTicker> accionesTicker()  
			throws PersistenciaException {
		List<InformacionTicker> respuesta;

		String fecha20Min = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());

		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.TICKER_ACCIONES,
				new String[] { IConstantesConsultasAcciones.FECHA_FIN },
				new String[] { fecha20Min });
		try{
			respuesta = UtilDto.obtenerObjetos(InformacionTicker.class, resQuery);
		}catch (Exception e){
			throw new PersistenciaException("Error al cargar la consulta " +
					IConstantesConsultasAcciones.TICKER_ACCIONES + 
					" en objetos de tipo InformacionTicker");
		}

		for (InformacionTicker res1 : respuesta) {
			IntradiaTituloMGC intTit = ultimoPrecioAccion(null, res1.getNemo());
			double var = 0d;
			if (intTit != null && intTit.getPrecio() != null){
				var = (res1.getUltimoPrecioTasa() / intTit.getPrecio() - 1) * 100;
			}
			res1.setVariacion(var);
		}

		return respuesta;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#accionesMasTranzadasDia(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> accionesMasTranzadasDia(String fecha)
			throws PersistenciaException {

		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;

		if (null == fecha || "".equals(fecha.trim())) {
			fecha = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());
			resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_ACCIONES_HOY,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN },
					new String[] { fecha });
		} else {
			resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_ACCIONES_DIA_PASADO,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN },
					new String[] { fecha });
		}
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.LISTA_ACCIONES_DIA_PASADO);
			throw exc;
		}
		//System.out.println(resQuery.get(0).get("nemoTecnico")+" "+resQuery.get(0).get("variacion"));
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#etfsMasTranzadasDia(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenEtfsMGC> etfsMasTranzadasDia(String fecha)
			throws PersistenciaException {

		List<ResumenEtfsMGC> respuesta = new ArrayList<ResumenEtfsMGC>();
		List<Map> resQuery = null;

		if (null == fecha || "".equals(fecha.trim())) {
			fecha = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());
			resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_ETF_HOY,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN },
					new String[] { fecha });
		} else {
			resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_ETF_DIA_PASADO,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN },
					new String[] { fecha });
		}
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenEtfsMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.LISTA_ETF_DIA_PASADO);
			throw exc;
		}
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#consultaDatosEmisor(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> consultaDatosEmisor(String nemo)
			throws PersistenciaException {

		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;

		resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.CONSULTA_EMISOR,
					new String[] { IConstantesConsultasAcciones.NEMO_EMISOR },
					new String[] { nemo });
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							);
			throw exc;
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarDatosTitulo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<TituloAccion> cargarDatosTitulo(String nemo, String fechaInic,
			String fechaFin) throws PersistenciaException {
		List<TituloAccion> resultado = new ArrayList<TituloAccion>();

		String cons = "select tod.V_ACMTA_NEMO nombre, "
				+ "tod.V_ACMTA_FECHA fecha, tod.V_ACMTA_MONTO volumen, "
				+ "tod.V_ACMTA_UNI_TRA cantidad, "
				+ "tod.V_ACMTA_PRE_CIE precioCierre, "
				+ "tod.V_ACMTA_PRE_MAY precioMayor, "
				+ "tod.V_ACMTA_PRE_MED precioMedio, "
				+ "tod.V_ACMTA_PRE_MEN precioMenor, "
				+ "tod.V_ACMTA_VAR_PRE variacion "
				+ "from unicabd.tbacmta tod " + "where tod.V_ACMTA_NEMO = '"
				+ nemo + "' " + "and tod.V_ACMTA_FECHA >= '" + fechaInic + "' "
				+ "and tod.V_ACMTA_FECHA <= '" + fechaFin + "' "
				+ "  order by fecha";

		Collection tmp = this.getJdbcTemplate().query(cons, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				TituloAccion tit = new TituloAccion();

				tit.setNemotecnico(rs.getString("nombre"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = new GregorianCalendar();
				try {
					cal.setTime(sdf.parse(rs.getString("fecha")));
				} catch (Exception e) {
					e.printStackTrace();
				}
				tit.setHora(cal);
				tit.setVolumen(rs.getDouble("volumen"));
				tit.setCantidad(rs.getDouble("cantidad"));
				tit.setPrecioCierre(rs.getDouble("precioCierre"));
				tit.setPrecioMayor(rs.getDouble("precioMayor"));
				tit.setPrecioMedio(rs.getDouble("precioMedio"));
				tit.setPrecioMenor(rs.getDouble("precioMenor"));
				tit.setVariacion(rs.getDouble("variacion"));
				
				return tit;
			}
		});

		resultado = new ArrayList<TituloAccion>(tmp);

		return resultado;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#getAccionesActivas()
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<String> getAccionesActivas() throws PersistenciaException {
		ArrayList<String> res;
		String consulta = "select V_ACMI_NEMO as Accion from tbacmi where V_ACMI_ESTADO != 'E' and V_ACMI_MGC IN ('M','E')";
		Collection tmp = this.getJdbcTemplate().query(consulta,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String accion = rs.getString("Accion");
						return accion;
					}
				});

		res = new ArrayList<String>(tmp);
		return res;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#getAccionesTodas()
	 */
	@SuppressWarnings("unchecked")
	public List<AccionesAutocomplete> getAccionesTodas()
			throws PersistenciaException {
		List<AccionesAutocomplete> res;
		String consulta = "select V_ACMI_NEMO as Accion from tbacmi where v_acmi_mgc = 'M' or  v_acmi_mgc = 'E'";

		Collection tmp = this.getJdbcTemplate().query(consulta,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						AccionesAutocomplete res = new AccionesAutocomplete();
						res.setNemo(rs.getString("Accion"));

						return res;
					}
				});

		res = new ArrayList<AccionesAutocomplete>(tmp);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#getAccionesTodas()
	 */
	@SuppressWarnings("unchecked")
	public List<AccionesAutocomplete> getEmisoresTodos()
			throws PersistenciaException {
		List<AccionesAutocomplete> res;
		String consulta = "SELECT "+
						"v_emi_razon_social nombreEmisor "+
						"FROM unicabd.tbacmi "+
						"INNER JOIN unicabd.tbutemis ON v_acmi_emisor = v_emi_emisor "+
						"INNER JOIN PORTALBVC.emisor emi ON CODIGO_EMR = V_EMI_EMISOR "+
						"WHERE v_acmi_estado = '0' AND v_acmi_mgc IN ('M', 'E') ";

		Collection tmp = this.getJdbcTemplate().query(consulta,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						AccionesAutocomplete res = new AccionesAutocomplete();
						res.setNombreEmisor(rs.getString("nombreEmisor"));

						return res;
					}
				});

		res = new ArrayList<AccionesAutocomplete>(tmp);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#getAccionesTodas()
	 */
	@SuppressWarnings("unchecked")
	public List<AccionesAutocomplete> getCotizacionEmisorTodos()
			throws PersistenciaException {
		List<AccionesAutocomplete> res;
		String consulta = "select emisor as nombreEmisor "+
						"from unicabd.preciosmgc,portalbvc.url_emisor url "+
						"where url.codigo=especie ";

		Collection tmp = this.getJdbcTemplate().query(consulta,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						AccionesAutocomplete res = new AccionesAutocomplete();
						res.setNombreEmisor(rs.getString("nombreEmisor"));

						return res;
					}
				});

		res = new ArrayList<AccionesAutocomplete>(tmp);
		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#getAccionesTodas()
	 */
	@SuppressWarnings("unchecked")
	public List<AccionesAutocomplete> getCotizacionAccionTodos()
			throws PersistenciaException {
		List<AccionesAutocomplete> res;
		String consulta = "select especie as accion "+
						"from unicabd.preciosmgc,portalbvc.url_emisor url "+
						"where url.codigo=especie ";

		Collection tmp = this.getJdbcTemplate().query(consulta,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						AccionesAutocomplete res = new AccionesAutocomplete();
						res.setNemo(rs.getString("accion"));

						return res;
					}
				});

		res = new ArrayList<AccionesAutocomplete>(tmp);
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarIntradiaTitulo
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarIntradiaTituloHistoricoDia(String titulo,
			String fechaIni, String fechaFin, boolean delay)
			throws PersistenciaException {
		List<ICierre> res;
		if (delay) {
			try {
				fechaFin = UtilFechas.fechaMenos20MinutosString(fechaFin,
						"yyyy-MM-dd:kk:mm");
			} catch (Exception e) {
				fechaFin = UtilFechas
						.fechaMenos20Minutos(new GregorianCalendar());
			}
		}

		List<Map> resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.GRAFICO_INTRADIA_ACCION,
				new String[] { IConstantesConsultasAcciones.NEMOTECNICO,
						IConstantesConsultasAcciones.FECHA_FIN,
						IConstantesConsultasAcciones.FECHA_INI }, new String[] {
						titulo, fechaFin, fechaIni });
		try {
			res = UtilDto.obtenerObjetos(CierreGeneral.class, resQuery);
			if (res != null && !res.isEmpty()) {
				IntradiaTituloMGC it = ultimoPrecioAccion(null, titulo);
				if (it != null && it.getPrecio() != null){
					for (ICierre cierre : res) {
						cierre.setValorUltimoCierre(it.getPrecio());
					}
				}else{
					for (ICierre cierre : res) {
						cierre.setValorUltimoCierre(cierre.getValorCierre());
					}
				}
			}
		} catch (Exception ex) {
			throw new PersistenciaException(
					"error al obtener el gráfico intradia");
		}

		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao#listaAccionesEtf(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> listaAccionesMGC()
			throws PersistenciaException {

		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;
        
		resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_ACCIONES_MGC
				);
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.LISTA_ACCIONES_MGC);
			throw exc;
		}
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao#listaEtfMGC(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> listaEtfMGC()
			throws PersistenciaException {

		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;
        
		resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_ETF_MGC
				);
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.LISTA_ETF_MGC);
			throw exc;
		}
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao#listaTotalAccionesEtf(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> listaTotalAccionesEtf()
			throws PersistenciaException {

		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;
        
		resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_TOTAL_ACCIONES_ETF_MGC
				);
		log.info("La consulta es: "+resQuery);
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.LISTA_TOTAL_ACCIONES_ETF_MGC);
			throw exc;
		}
		log.info("La respuesta es: "+respuesta);
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao#listaFiltroAccionesEtf(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> consultaListasFiltroMGC(ResumenAccionMGC listaMGC)
			throws PersistenciaException {

		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;
		String pais = "" ,cot = "",patro = "",fecha = "",consulta= "", nom="",mgc="";
		String [] temp = null;
		
		
        
		if(listaMGC.getPais()!=null  && !"".equals(listaMGC.getPais()) && !"%".equals(listaMGC.getPais()))
			pais=" and upper(v_emi_pais_origen)=upper('"+listaMGC.getPais()+"')";
		if(listaMGC.getPatrocinador()!=null && !"".equals(listaMGC.getPatrocinador()) && !"%".equals(listaMGC.getPatrocinador()))
			patro=" and upper(v_emi_patrocinador)=upper('"+listaMGC.getPatrocinador()+"')";
		if(listaMGC.getCotizacion()!=null && !"".equals(listaMGC.getCotizacion()) && !"%".equals(listaMGC.getCotizacion()))
			cot=" and upper(v_emi_cot_principal)=upper('"+listaMGC.getCotizacion()+"')";
		if(listaMGC.getRazonSocial()!=null && !"".equals(listaMGC.getRazonSocial()) )
			nom="  and (upper(`V_ACMI_NEMO`)= upper('"+listaMGC.getRazonSocial()+"') or upper(`v_emi_razon_social`) like upper('"+listaMGC.getRazonSocial()+"%') ) ";
		if(listaMGC.getNombreEmr()!=null && !"".equals(listaMGC.getNombreEmr()) && !"0".equals(listaMGC.getNombreEmr()) )
			mgc="  AND v_acmi_mgc = '"+listaMGC.getNombreEmr()+"' ";
		if(listaMGC.getFechaList()!=null && listaMGC.getFechaList()!=""){
			temp=listaMGC.getFechaList().split("-");
			fecha=" and v_emi_fecha_listado='"+temp[0]+temp[1]+temp[2]+"'";
		}
		consulta=pais+patro+cot+nom+mgc+fecha;
		
		resQuery = cargarPorNombreQuery(IConstantesConsultasAcciones.CONSULTA_FILTRO_MGC,
				new String[] {IConstantesConsultasAcciones.TIPO_MERCADO,IConstantesConsultasAcciones.CONSULTA_LISTA_MGC },
				new Object[] {listaMGC.getNombreEmr(),consulta});
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
			System.out.println(respuesta.size());	
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException("Error al cargar los objetos de la consulta "+ IConstantesConsultasAcciones.LISTA_TOTAL_ACCIONES_ETF_MGC);
			throw exc;
		}
		return respuesta;
	}
	
	/**
	 * Metodo que Carga las listas para el formulario de busqueda de emisores y
	 * pone estas listas en el modelo que se le pasa como parametro.
	 * 
	 * @param model Modelo en el que quedaran cargadas las listas que se
	 * visualizaran en el formulario
	 * 
	 * @return Modelo con las listas cargadas
	 * @throws PersistenciaException 
	 */
	@SuppressWarnings("unchecked")
	public Model consultaListasMGC(Model model) throws PersistenciaException {
		
		List<ResumenAccionMGC> lista_pais = null;
		List<ResumenAccionMGC> lista_patrocinador = null;
		List<ResumenAccionMGC> lista_cotizacion= null;
		
		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;
		
		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_30SECS);
		
		String queryKey = "LISTA_PAIS_MGC."+UtilFechas.getHoy("yyyyMMdd");
		try{
		lista_pais = (List<ResumenAccionMGC>) cache.getObject(queryKey);  //ACCIONES-015   
		}catch (Exception e) {
			e.printStackTrace();
		}
		if (lista_pais == null) {                              	  
			synchronized(this) {
				lista_pais = (List<ResumenAccionMGC>) cache.getObject(queryKey); 
				if ( lista_pais == null){                     
					log.info("Procesando Lista Pais");
					resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.CONSULTA_PAIS_MGC
					);
					try {
						respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
					} catch (Exception ex) {
						PersistenciaException exc = new PersistenciaException(
								"Error al cargar los objetos de la consulta "
										+ IConstantesConsultasAcciones.CONSULTA_PAIS_MGC);
						throw exc;
					}
					lista_pais = respuesta;
					cache.putObject(queryKey, lista_pais);                    
				}                      
			}               
		}
		else {
			log.info("Cargando desde Cache Lista Pais"); 
		}
		
		queryKey = "LISTA_PATROCINADOR_MGC."+UtilFechas.getHoy("yyyyMMdd");
		try{
		lista_patrocinador = (List<ResumenAccionMGC>) cache.getObject(queryKey);  //ACCIONES-015 
		}catch (Exception eo) {
			eo.printStackTrace();
		}
		if (lista_patrocinador == null) {                              	  
			synchronized(this) {
				lista_patrocinador = (List<ResumenAccionMGC>) cache.getObject(queryKey); 
				if ( lista_patrocinador== null){                     
					log.info("Procesando Lista Patrocinador");
					resQuery = cargarPorNombreQuery(
							IConstantesConsultasAcciones.CONSULTA_PATROCINADOR_MGC
							);
							try {
								respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
							} catch (Exception ex) {
								PersistenciaException exc = new PersistenciaException(
										"Error al cargar los objetos de la consulta "
												+ IConstantesConsultasAcciones.CONSULTA_PATROCINADOR_MGC);
								throw exc;
							}
							lista_patrocinador = respuesta;
					cache.putObject(queryKey, lista_patrocinador);                    
				}                      
			}               
		}
		else {
			log.info("Cargando desde Cache Lista Patrocinador"); 
		}
		
		queryKey = "LISTA_COTIZACION_MGC."+UtilFechas.getHoy("yyyyMMdd");
		try{
		lista_cotizacion= (List<ResumenAccionMGC>) cache.getObject(queryKey);  //ACCIONES-015
		}catch (Exception ee) {
			ee.printStackTrace();
		}
		if (lista_cotizacion == null) {                              	  
			synchronized(this) {
				lista_cotizacion = (List<ResumenAccionMGC>) cache.getObject(queryKey); 
				if ( lista_cotizacion== null){                     
					log.info("Procesando Lista Cotizacion");
					resQuery = cargarPorNombreQuery(
							IConstantesConsultasAcciones.CONSULTA_COTIZACION_MGC
							);
							try {
								respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
							} catch (Exception ex) {
								PersistenciaException exc = new PersistenciaException(
										"Error al cargar los objetos de la consulta "
												+ IConstantesConsultasAcciones.CONSULTA_COTIZACION_MGC);
								throw exc;
							}
							lista_cotizacion = respuesta;
					cache.putObject(queryKey, lista_cotizacion);                    
				}                      
			}               
		}
		else {
			log.info("Cargando desde Cache Lista Cotizacion"); 
		}
		
		model.addAttribute(IConstantesConsultasAcciones.LISTA_PAIS_MGC,
				lista_pais);
		model.addAttribute(IConstantesConsultasAcciones.LISTA_PATROCINADOR_MGC,
				lista_patrocinador);
		model.addAttribute(IConstantesConsultasAcciones.LISTA_COTIZACION_MGC,
				lista_cotizacion);
		
		return model;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao#listaEtfMGC(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> tipoMercadoMGC(String nemo)
			throws PersistenciaException {
		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;

		resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.CONSULTA_TIPO_MGC,
					new String[] { IConstantesConsultasAcciones.NEMO_EMISOR },
					new String[] { nemo });
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							);
			throw exc;
		}
		return respuesta;
	}
	
	
	/**
	 * Cargar intradia titulo.
	 * 
	 * @param titulo the titulo
	 * 
	 * @return the i cierre
	 * 
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarIntradiaTitulo(java.lang.String,
	 * java.lang.String)
	 */
	public ICierre cargarIntradiaTitulo(String titulo) {
		Object params[] = { titulo, titulo, titulo };
		String SQL = "" + "SELECT v_actr_hor_gra       AS hora          , "
				+ "       v_actr_precio        AS precio        , "
				+ "       V_ACMI_PRE_CIE_DI_AN AS precioAnterior, "
				+ "       V_ACTR_CANTIDAD      AS volumen " + "FROM   tbactr, "
				+ "       tbacmi " + "WHERE  v_actr_nemo    = ? "
				+ "   AND v_acmi_nemo    = ? " + "   AND v_actr_hor_gra = "
				+ "       (SELECT MAX(v_actr_hor_gra) "
				+ "       FROM   tbactr "
				+ "       WHERE  v_actr_nemo     = ? "
				+ "          AND v_actr_ind_fija = 'S' " + "       )";

		ICierre cierre = (ICierre) this.getJdbcTemplate().queryForObject(SQL,
				params, new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						ICierre cierre = new CierreGeneral();
						String fecha = rs.getString("hora");

						SimpleDateFormat frm = new SimpleDateFormat(
								"yyyy-MM-dd:kk:mm");
						Date parse;
						try {
							parse = frm.parse(fecha);
							frm = new SimpleDateFormat("yyyy-MM-dd kk:mm");
							fecha = frm.format(parse);
							Calendar instance = Calendar.getInstance();
							instance.setTime(frm.parse(fecha));
							cierre.setFechaHora(instance);
							cierre.setValorCierre(rs.getFloat("precio"));
							cierre.setValorUltimoCierre(rs
									.getFloat("precioAnterior"));
							cierre.setVolumen(rs.getFloat("volumen"));
						} catch (ParseException e) {
							e.printStackTrace();
						}

						return cierre;
					}
				});
		return cierre;
	}

	@SuppressWarnings("unchecked")
	public List<HorarioMercadoMGC> obtenerHorarioNegociacionMGC(int mercado)
			throws PersistenciaException {
		List<HorarioMercadoMGC> listHorarioMGC = new ArrayList<HorarioMercadoMGC>();
		String mercadoTmp = String.valueOf(mercado);
		List<Map> resQuery = null;
		GregorianCalendar vHoraInicio = new GregorianCalendar();
		GregorianCalendar vHoraFin = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.HORARIO_NEGOCIACION_MGC,
				new String[] { IConstantesConsultasAcciones.SEGMENTO_MERCADO },
				new String[] { mercadoTmp });
		if (null != resQuery && !resQuery.isEmpty()) {
			for(Map respuesta: resQuery){
				HorarioMercadoMGC vHorarioMercado = new HorarioMercadoMGC();
				vHorarioMercado.setNombreMercado((String)respuesta.get("descripcion"));
				
				vHoraInicio.setTime((Timestamp)respuesta.get("hora_apertura"));
				vHorarioMercado.setHoraInicio(formatter.format( vHoraInicio.getTime()));
				
				vHoraFin.setTime((Timestamp)respuesta.get("hora_cierre"));
				vHorarioMercado.setHoraFin(formatter.format( vHoraFin.getTime()));
				listHorarioMGC.add(vHorarioMercado);
			}
		}
		// TODO Auto-generated method stub
		return listHorarioMGC;
	}

	public List<ResumenAccionMGC> reposMasTranzadasDia(String fecha) throws PersistenciaException, ParseException {
		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;
		
		if (fecha.equalsIgnoreCase(UtilFechas.getHoy("yyyy-MM-dd"))){
			fecha = UtilFechas.fechaMenos20MinutosString(fecha,"yyyy-MM-dd:kk:mm");
			
			resQuery = cargarPorNombreQuery(IConstantesConsultasAcciones.LISTA_REPOS_HOY,
					new String[] {IConstantesConsultasAcciones.FECHA_FIN},
					new Object[] {fecha});
		} else {
			resQuery = cargarPorNombreQuery(IConstantesConsultasAcciones.LISTA_REPOS_HISTORIA,
					new String[] {IConstantesConsultasAcciones.FECHA_FIN},
					new Object[] {fecha});
		}
        
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta " + IConstantesConsultasAcciones.LISTA_ETF_MGC);
			throw exc;
		}
		return respuesta;
	}
	
	public List<ResumenAccionMGC> ttvMasTranzadasDia(String fecha) throws PersistenciaException, ParseException {
		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;
		
		if (fecha.equalsIgnoreCase(UtilFechas.getHoy("yyyy-MM-dd"))){
			fecha = UtilFechas.fechaMenos20MinutosString(fecha,"yyyy-MM-dd:kk:mm");
			
			resQuery = cargarPorNombreQuery(IConstantesConsultasAcciones.RESUMEN_TTV_ACTUAL,
					new String[] {IConstantesConsultasAcciones.FECHA_FIN},
					new Object[] {fecha});
		} else {
			resQuery = cargarPorNombreQuery(IConstantesConsultasAcciones.RESUMEN_TTV_HISTORIA,
					new String[] {IConstantesConsultasAcciones.FECHA_FIN},
					new Object[] {fecha});
		}
        
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta " + IConstantesConsultasAcciones.LISTA_ETF_MGC);
			throw exc;
		}
		return respuesta;
	}
	
	@SuppressWarnings("unchecked")
	public List<CotizaPaisMGC> cotizaTotalPaisDaoMGC()
			throws PersistenciaException {
		List<CotizaPaisMGC> respuesta = new ArrayList<CotizaPaisMGC>();
		List<Map> resQuery = null;

		resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.CONSULTA_COTIZA_PAIS_MGC
					);
		
		try {
			respuesta = UtilDto.obtenerObjetos(CotizaPaisMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							);
			throw exc;
		}
		return respuesta;
	}
	
	/**
	 * Metodo que Carga las listas para el formulario de busqueda de emisores y
	 * pone estas listas en el modelo que se le pasa como parametro.
	 * 
	 * @param model Modelo en el que quedaran cargadas las listas que se
	 * visualizaran en el formulario
	 * 
	 * @return Modelo con las listas cargadas
	 * @throws PersistenciaException 
	 */
	@SuppressWarnings("unchecked")
	public Model consultaListaCotizaMGC(Model model) throws PersistenciaException {
		
		List<CotizaPaisMGC> lista_cotizacion= null;
		
		List<CotizaPaisMGC> respuesta = new ArrayList<CotizaPaisMGC>();
		List<Map> resQuery = null;
		
		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_30SECS);
		
		String queryKey = "LISTA_COTIZACION_PAIS_MGC."+UtilFechas.getHoy("yyyyMMdd");
		try{
		 lista_cotizacion = (List<CotizaPaisMGC>) cache.getObject(queryKey);  //ACCIONES-015   
		}catch (Exception e) {
			e.printStackTrace();
		}
		if (lista_cotizacion == null) {                              	  
			synchronized(this) {
				lista_cotizacion = (List<CotizaPaisMGC>) cache.getObject(queryKey); 
				if ( lista_cotizacion == null){                     
					log.info("Procesando Lista Cotizacion Pais MGC");
					resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.CONSULTA_COTIZACION_PAIS_MGC
					);
					try {
						respuesta = UtilDto.obtenerObjetos(CotizaPaisMGC.class, resQuery);
					} catch (Exception ex) {
						PersistenciaException exc = new PersistenciaException(
								"Error al cargar los objetos de la consulta "
										+ IConstantesConsultasAcciones.CONSULTA_COTIZACION_PAIS_MGC);
						throw exc;
					}
					lista_cotizacion = respuesta;
					cache.putObject(queryKey, lista_cotizacion);                    
				}                      
			}               
		}
		else {
			log.info("Cargando desde Cache Lista Pais"); 
		}
		
		
		
		
		model.addAttribute(IConstantesConsultasAcciones.LISTA_COTIZACION_PAIS_MGC,
				lista_cotizacion);
		
		return model;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao#listaFiltroAccionesEtf(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CotizaPaisMGC> consultaCotizaFiltroMGC(CotizaPaisMGC listaMGC)
			throws PersistenciaException {

		List<CotizaPaisMGC> respuesta = new ArrayList<CotizaPaisMGC>();
		List<Map> resQuery = null;
		String accion = "" ,cot = "",empresa = "",consulta= " where url.codigo=especie ";
		String [] temp = null;
		
		
        
		if(listaMGC.getCotizacion()!=null && !"".equals(listaMGC.getCotizacion()) && !"%".equals(listaMGC.getCotizacion()))
			cot=" and upper(cotizacion_principal)=upper('"+listaMGC.getCotizacion()+"')";
		if(listaMGC.getEmpresa()!=null && !"".equals(listaMGC.getEmpresa())  )
			empresa=" and  emisor like '"+listaMGC.getEmpresa()+"%' ";
		if(listaMGC.getAccion()!=null && !"".equals(listaMGC.getAccion())  )
			accion=" and  especie like '"+listaMGC.getAccion()+"%' ";
		
		
		
		       
		consulta=consulta+empresa+accion+cot;
		
		resQuery = cargarPorNombreQuery(IConstantesConsultasAcciones.CONSULTA_COTIZA_FILTRO_MGC,
				new String[] {IConstantesConsultasAcciones.CONSULTA_COTIZA_MGC },
				new Object[] {consulta});
		
		try {
			respuesta = UtilDto.obtenerObjetos(CotizaPaisMGC.class, resQuery);
			System.out.println(respuesta.size());	
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException("Error al cargar los objetos de la consulta "+ IConstantesConsultasAcciones.CONSULTA_COTIZA_FILTRO_MGC);
			throw exc;
		}
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao#accionesMasTranzadasHome(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccionMGC> accionesMasTranzadasHome(String fecha)
			throws PersistenciaException {

		List<ResumenAccionMGC> respuesta = new ArrayList<ResumenAccionMGC>();
		List<ResumenAccionMGC> ret = new ArrayList<ResumenAccionMGC>();
		List<Map> resQuery = null;
		

			fecha = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());
			resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_ACCIONES_HOY,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN },
					new String[] { fecha });
			
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccionMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.LISTA_ACCIONES_HOY);
			throw exc;
		}
		
			
		
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDaoMGC#etfsMasTranzadasDiaHome(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenEtfsMGC> etfsMasTranzadasDiaHome(String fecha)
			throws PersistenciaException {

		List<ResumenEtfsMGC> respuesta = new ArrayList<ResumenEtfsMGC>();
		List<Map> resQuery = null;
		
		
		fecha = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());
		resQuery = cargarPorNombreQuery(
				IConstantesConsultasAcciones.LISTA_ETF_HOY,
				new String[] { IConstantesConsultasAcciones.FECHA_FIN },
				new String[] { fecha });


		try {
			respuesta = UtilDto.obtenerObjetos(ResumenEtfsMGC.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.LISTA_ETF_HOY);
			throw exc;
		}
		return respuesta;
	}
	
}
