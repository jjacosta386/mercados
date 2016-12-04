package co.com.bvc.portal.mercados.persistencia.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.jdbc.JDBCDaoImp;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilDto;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.modelo.CierreGeneral;
import co.com.bvc.portal.mercados.modelo.CotizaPaisMGC;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InformacionTicker;
import co.com.bvc.portal.mercados.modelo.IntradiaTitulo;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.persistencia.IAccionesDao;
import co.com.bvc.portal.mercados.util.IConstantesConsultasAcciones;

// TODO: Auto-generated Javadoc
/**
 * The Class AccionesDaoSpringJdbc.
 */
public class AccionesDaoSpringJdbc extends JDBCDaoImp implements IAccionesDao {

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
		double vBoceas = 0d;
		double vBoceasSimul = 0d;
		if (fecha == null || "".equals(fecha.trim())) {
			fecha = UtilFechas.fechaMenos20Minutos(new GregorianCalendar()).substring(0, 16);
			params = new String[] {IConstantesConsultasAcciones.TABLA_VOLMENES_TOTALES_DIA,"<", fecha };
		} else {
			params = new String[] {IConstantesConsultasAcciones.TABLA_VOLMENES_TOTALES_HISTORIA,"", fecha };
		}
		List<Map> resQuery = cargarPorNombreQuery(IConstantesConsultasAcciones.VOLUMENES_TOTALES_POR_FECHA,new String[] { IConstantesConsultasAcciones.TABLA,IConstantesConsultasAcciones.OPERADOR,IConstantesConsultasAcciones.FECHA_FIN }, params);

		if (resQuery != null && !resQuery.isEmpty()) {
			Map fila = resQuery.get(0);
			vCompraventas = (Double) fila.get("compraventa");
			vRepos = (Double) fila.get("repos");
			vTTV = (Double) fila.get("ttv");
			vBoceas = (Double) fila.get("boceas");
			vBoceasSimul = (Double) fila.get("boceasSimul");
		}

		return new double[] { vCompraventas, vRepos, vTTV,vBoceas, vBoceasSimul };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesDao#ultimoPrecioAccion
	 * (java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public IntradiaTitulo ultimoPrecioAccion(String fecha, String nemo)
			throws PersistenciaException {

		if (fecha == null || "".equals(fecha.trim())) {
			fecha = UtilFechas.getHoy("yyyyMMdd");
		}

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		String queryKey = "ULTIMO_PRECIO_ACCION." + fecha + "." + nemo;

		IntradiaTitulo ret;

		ret = (IntradiaTitulo) cache.getObject(queryKey); // ACCIONES-015
		if (ret == null) {
			synchronized (this) {
				ret = (IntradiaTitulo) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Ultimo precio accion nemo : " + nemo
							+ " fecha: " + fecha);
					ret = new IntradiaTitulo();

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
			log.debug("Cargando desde cache Ultimo precio accion nemo : " + nemo
					+ " fecha: " + fecha);
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IAccionesDao#horaUltimaOperacion
	 * (java.lang.String)
	 */
	
	@SuppressWarnings("unchecked")
	public String horaUltimaOperacion(String fecha)
			throws PersistenciaException {

		String respuesta = null;
		List<Map> resQuery = null;
		/*ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		
		String queryKey = "HORA_ULTIMA_OPERACION_ACCIONES."+fecha;*/
		try{
			/*respuesta = (String) cache.getObject(queryKey);    
			log.info("LLave Cache. "+queryKey+" Respuesta: "+respuesta);
			if (null == respuesta) {                              	  
				synchronized(this) {
					respuesta = (String) cache.getObject(queryKey); 
					if ( null == respuesta){                     
						log.info("Procesando Hora Ultima Operacion Acciones");*/
						resQuery = cargarPorNombreQuery(
								IConstantesConsultasAcciones.HORA_ULTIMA_OPERACION,
								new String[] { IConstantesConsultasAcciones.FECHA_INI,  IConstantesConsultasAcciones.FECHA_FIN},
								new String[] { fecha+":00:00", fecha+":23:59"});
						
						if (resQuery != null && !resQuery.isEmpty()) {
							Map fila = resQuery.get(0);
							respuesta = (String) fila.get("hora");
						}
						if(null == respuesta)
							respuesta = "1600";
						
						/*cache.putObject(queryKey, respuesta);                    
					}                      
				}               
			}else {
				log.info("Cargando desde Cache Hora Ultima Operacion Acciones."); 
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.HORA_ULTIMA_OPERACION);
			throw exc;
		}
		
			
		//log.info("Hora Respuesta: "+respuesta);
		return respuesta;
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
							String horaUltimaOperacion = horaUltimaOperacion(fecha);
							horaUltimaOperacion = horaUltimaOperacion.replace(":", "");
							//log.info("Fecha: "+fecha + " Hora: "+horaUltimaOperacion);
							//if(fecha !=null && UtilFechas.getHoy("yyyy").equals(fecha.subSequence(0, 4))){
								resumen.getFechaHora().setTime(
										df.parse(fecha + horaUltimaOperacion));
							/*}else {
							resumen.getFechaHora().setTime(
									df.parse(fecha + "1300"));
							}*/
						} catch (Exception e) {
							e.printStackTrace();
						}
						return resumen;
					}
				});

		respuesta = new ArrayList<ICierre>(tmp);
		if (respuesta != null && !respuesta.isEmpty()) {
			IntradiaTitulo it = ultimoPrecioAccion(null, titulo);
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
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#cargarHistoricoTituloBVC(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoTituloBVC(String titulo)
			throws PersistenciaException {
		List<ICierre> respuesta;
		Object params[] = { titulo };
		
		String fechaCache = UtilFechas.getHoy("yyyyMMdd");
		
		ControladorCacheJCS cache = JCSFactory
		.getRegionControladorCache(JCSFactory.MEM_30SECS);
		String queryKey = "CARGAR_HISTORICO_BVC." + fechaCache ;

		
		respuesta = (List<ICierre>) cache.getObject(queryKey); // ACCIONES-015
		if (respuesta == null) {
			synchronized (this) {
				respuesta = (List<ICierre>) cache.getObject(queryKey);
				if (respuesta == null) {
		
					String fechaC = UtilFechas.getHoy("yyyy");
					String fecha1 = UtilFechas.getHoy("MM-dd");
					int fechaT = Integer.valueOf(fechaC);
					fechaT = fechaT-1;
					fecha1=fechaT+"-"+fecha1;
			
					String consulta = "select hist.v_acmta_fecha fecha, "
							+ "hist.v_acmta_pre_cie cierre, "
							+ "hist.v_acmta_uni_tra cantidad, "
							+ "hist.V_ACMTA_PRE_MAY precioMayor, "
							+ "hist.V_ACMTA_PRE_MEN precioMenor "
							+ "	from unicabd.TBACMTA hist "
							+ "	where hist.v_ACMTA_nemo = ? "
							+ " AND hist.V_ACMTA_FIJO_PRECIO = 'S'"
							+ " AND hist.v_acmta_fecha >= '"+fecha1+"'"
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
										if(fecha !=null && UtilFechas.getHoy("yyyy").equals(fecha.subSequence(0, 4))){
											resumen.getFechaHora().setTime(
													df.parse(fecha + "1600"));
										}else {
										resumen.getFechaHora().setTime(
												df.parse(fecha + "1300"));
										}
									} catch (ParseException e) {
										e.printStackTrace();
									}
									return resumen;
								}
							});
			
					respuesta = new ArrayList<ICierre>(tmp);
					if (respuesta != null && !respuesta.isEmpty()) {
						IntradiaTitulo it = ultimoPrecioAccion(null, titulo);
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
					
				}
			}
		} else {
			log.debug("Cargando desde cache cargar Historico Titulo BVC " );
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
		this.cargarDatosRpgQtobin(titulo);
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
		String queryKey = "INFO_EMISOR." + emisor + "."
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
			IntradiaTitulo intTit = ultimoPrecioAccion(null, res1.getNemo());
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
	public List<ResumenAccion> accionesMasTranzadasDia(String fecha)
			throws PersistenciaException {

		List<ResumenAccion> respuesta = new ArrayList<ResumenAccion>();
		List<Map> resQuery = null;

		if (fecha == null || "".equals(fecha.trim())) {
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
			respuesta = UtilDto.obtenerObjetos(ResumenAccion.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.LISTA_ACCIONES_DIA_PASADO);
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
				+ "order by fecha";

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
		String consulta = "select V_ACMI_NEMO as Accion from tbacmi where V_ACMI_ESTADO = '0' and V_ACMI_MGC = 'A'" ;

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
		String consulta = "select  * from ((select V_ACMI_NEMO as Accion from tbacmi where v_acmi_mgc <> 'M' and v_acmi_mgc <> 'E')"
			             +" UNION ALL (select prim.V_DEMI_NEMO as Nemo from tbdemi prim, tbrfmi seg "
			             +" where prim.V_DEMI_NEMO_RFMI = seg.V_RFMI_NEMO and seg.V_RFMI_TITULO = 'BOC')) c order by 1";

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
				IntradiaTitulo it = ultimoPrecioAccion(null, titulo);
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
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IAccionesDao#accionesMasTranzadasHome(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ResumenAccion> accionesMasTranzadasHome(String fecha)
			throws PersistenciaException {

		List<ResumenAccion> respuesta = new ArrayList<ResumenAccion>();
		List<ResumenAccion> ret = new ArrayList<ResumenAccion>();
		List<Map> resQuery = null;
		

			fecha = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());
			resQuery = cargarPorNombreQuery(
					IConstantesConsultasAcciones.LISTA_ACCIONES_HOY,
					new String[] { IConstantesConsultasAcciones.FECHA_FIN },
					new String[] { fecha });
			
		
		try {
			respuesta = UtilDto.obtenerObjetos(ResumenAccion.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException exc = new PersistenciaException(
					"Error al cargar los objetos de la consulta "
							+ IConstantesConsultasAcciones.LISTA_ACCIONES_HOY);
			throw exc;
		}
		
			
		
		return respuesta;
	}
	
	

}