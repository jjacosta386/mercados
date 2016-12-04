package co.com.bvc.portal.mercados.persistencia.imp;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.jdbc.JDBCDaoImp;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilDto;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.CierreGeneral;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InformacionTicker;
import co.com.bvc.portal.mercados.modelo.RentaFijaAutocomplete;
import co.com.bvc.portal.mercados.modelo.RentaFijaOperacion;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;
import co.com.bvc.portal.mercados.persistencia.IRentaFijaDao;
import co.com.bvc.portal.mercados.servicio.IRentaFija;
import co.com.bvc.portal.mercados.util.IConsultasMercados;

// TODO: Auto-generated Javadoc
/**
 * The Class RentaFijaDaoSpringJdbc.
 */
public class RentaFijaDaoSpringJdbc extends JDBCDaoImp implements IRentaFijaDao {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IRentaFijaDao#getDetalleExcel(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RentaFijaSumaOperacion> getDetalleExcel(String fechaIni, String fechaFin, String nemo){
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
					IConsultasMercados.RENTAFIJA_DETALLE_EXCEL,
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
	 * co.com.bvc.portal.mercados.persistencia.IRentaFijaDao#getOperaciones(
	 * java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	public List<RentaFijaOperacion> getOperaciones(String fechaIni,
			String fechaFin, String tipoSesion, String tipoOperacion,
			String tipoMercado) throws PersistenciaException {
		List<RentaFijaOperacion> ret = new LinkedList<RentaFijaOperacion>();
		String sufijoTabla = "a";

		if (fechaIni == null
				|| UtilFechas.getHoy("yyyy-MM-dd").equals(fechaIni.subSequence(0, 10))) {
			// se consulta el día actual
			sufijoTabla = "";
			String fechas[] = UtilFechas.getFechasComparadorDiaActual();
			fechaIni = fechas[0];
			fechaFin = fechas[1];
		}

		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.RENTAFIJA_OPERACIONES,
				new String[] { "a", "rueda", "tipoMercado", "tipoOperacion",
						"fechaIni", "fechaFin" },
				new Object[] {
						sufijoTabla,
						tipoSesion,
						tipoMercado,
						tipoOperacion == IRentaFija.ALL_OPE ? IRentaFija.MERCADO_TODOS
								: tipoOperacion, fechaIni, fechaFin });
		try {
			ret = UtilDto.obtenerObjetos(RentaFijaOperacion.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException pe = new PersistenciaException(
					"error al cargar el resultado del query "
							+ IConsultasMercados.RENTAFIJA_OPERACIONES
							+ " en objetos de tipo RentaFijaOperacion");
			pe.initCause(ex);
			throw pe;
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IRentaFijaDao#getRentaFijaOperaciones
	 * (java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@SuppressWarnings( { "unchecked" })
	public List<RentaFijaOperacion> getRentaFijaOperaciones(String dia,
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

		if (tipoOperacion == IRentaFija.SIMULTANEA) {
			nombreConsulta = IConsultasMercados.RENTA_FIJA_DETALLE_SIMULTANEAS;
		} else if (tipoOperacion == IRentaFija.REPOS) {
			nombreConsulta = IConsultasMercados.RENTA_FIJA_DETALLE_REPOS;
		} else {
			nombreConsulta = IConsultasMercados.RENTA_FIJA_DETALLE_GENERAL;
		}

		List<Map> resQuery = cargarPorNombreQuery(nombreConsulta, new String[] {
				"a", "rueda", "tipoMercado", "tipoOperacion", "fechaIni",
				"fechaFin", "nemo" }, new Object[] {
				sufijoTabla,
				sesion,
				tipoMercado,
				tipoOperacion == IRentaFija.ALL_OPE ? IRentaFija.MERCADO_TODOS
						: tipoOperacion, fechas[0], fechas[1], nemo });
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
	 * co.com.bvc.portal.mercados.persistencia.IRentaFijaDao#getEmisor(java.
	 * lang.String)
	 */
	@SuppressWarnings("unchecked")
	public EmisorTitulo getEmisor(String nemo) throws PersistenciaException {

		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.RENTAFIJA_EMISOR, nombresParametros,
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IRentaFijaDao#getRentaFijaTitulo
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public RentaFijaResumenTitulo getRentaFijaTitulo(String nemo)
			throws PersistenciaException {

		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.RENTAFIJA_TITULO, nombresParametros,
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
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IRentaFijaDao#rentaFijaTicker()
	 */
	@SuppressWarnings("unchecked")
	public List<InformacionTicker> rentaFijaTicker()
			throws PersistenciaException {
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.RENTA_FIJA_TICKER,
				new String[] { "fechaFin" }, new String[] { UtilFechas
						.getFechasComparadorDiaActual()[1] });
		List<InformacionTicker> resDto = new ArrayList<InformacionTicker>();
		try {
			resDto = UtilDto.obtenerObjetos(InformacionTicker.class, resQuery);
		} catch (Exception e) {
			throw new PersistenciaException(
					"Error al cargar los datos de la consulta "
							+ IConsultasMercados.RENTA_FIJA_TICKER
							+ " en objetos de tipo InformacionTicker");
		}
		if (resDto != null && !resDto.isEmpty()){
			for (InformacionTicker res1 : resDto) {
				Double tasaAnt =cargarUltimaTasaRentafija(res1.getNemo());
				double var = 0d;
				if (tasaAnt != null){
					var = (res1.getUltimoPrecioTasa() / tasaAnt - 1) * 100;
				}
				res1.setVariacion(var);
			}
		}
		return resDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IRentaFijaDao#historicoNemo(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> graficaRentaFija(boolean hoy, String nemo)
			throws PersistenciaException {

		Double tasaAnt = cargarUltimaTasaRentafija(nemo);
		String fecha = null;
		String sufijo = null;
		String consulta = null;
		if (hoy) {
			fecha = UtilFechas.fechaMenos20Minutos(new GregorianCalendar());
			sufijo = "";
			consulta = IConsultasMercados.RENTA_FIJA_GRAFICA;
		} else {
			fecha = UtilFechas.getHoy("yyyy-MM-dd");
			sufijo = "a";
			consulta = IConsultasMercados.RENTA_FIJA_GRAFICA_HIST;
		}
		List<ICierre> respuesta = new ArrayList<ICierre>();

		List<Map> resQuery = cargarPorNombreQuery(
				consulta, new String[] { "a",
						"fechaFin", "nemo" }, new String[] { sufijo, fecha,
						nemo });

		try {
			respuesta = UtilDto.obtenerObjetos(CierreGeneral.class, resQuery);
		} catch (Exception ex) {
			PersistenciaException pex = new PersistenciaException(
					"Error al cargar el resultado de la consulta: "
							+ IConsultasMercados.RENTA_FIJA_GRAFICA
							+ " en objetos de tipo CierreGeneral");
			pex.initCause(ex);
			throw pex;
		}

		for (Iterator iterator = respuesta.iterator(); iterator.hasNext();) {
			ICierre resumen = (ICierre) iterator.next();
			resumen.setValorUltimoCierre(tasaAnt);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IRentaFijaDao#getDatosAutocomplete
	 * ()
	 */
	@SuppressWarnings("unchecked")
	public List<RentaFijaAutocomplete> getDatosAutocomplete()
			throws PersistenciaException {
		List<RentaFijaAutocomplete> resTotal = new ArrayList<RentaFijaAutocomplete>();
		String[] nombresParametros = {};
		String[] parametros = {};
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.RENTA_FIJA_AUTOCOMPLETE, nombresParametros,
				parametros);

		Iterator it = resQuery.iterator();

		while (it.hasNext()) {
			RentaFijaAutocomplete res = new RentaFijaAutocomplete();
			Map tmp = (Map) it.next();
			res.setNemo((String) tmp.get("Nemo"));
			resTotal.add(res);
		}

		return resTotal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IRentaFijaDao#cargarIntradiaRentafija
	 * (java.lang.String)
	 */
	public ICierre cargarIntradiaRentafija(String nemo)
			throws PersistenciaException {
		throw new UnsupportedOperationException(
				"cambio en el módulo de gráficas");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IRentaFijaDao#
	 * cargarUltimaTasaRentafija(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Double cargarUltimaTasaRentafija(String nemo)
			throws PersistenciaException {
		
		Double ret;
		
		ControladorCacheJCS cache = JCSFactory
			.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);
		
		String queryKey = "ULTIMA_TASA." + UtilFechas.getHoy("yyyyMMdd") + "."
		+ nemo;
		
		ret = (Double) cache.getObject(queryKey);
		
		
		if (ret == null) {
			synchronized (this) {
				ret = (Double) cache.getObject(queryKey);
				if (ret == null) {
					log.debug("Procesando Ultima Tasa Renta Fija : " + nemo);
					String[] nombresParametros = { "nemo" };
					String[] parametros = { nemo };
					List<Map> resQuery = cargarPorNombreQuery(
							IConsultasMercados.CARGAR_ULTIMA_TASA_RENTAFIJA,
							nombresParametros, parametros);
					ret = new Double(0);
					if (resQuery.size() > 0)
						ret = new Double(resQuery.get(0).get("valorCierre").toString());
					cache.putObject(queryKey, ret);
					return ret;
				}
			}
		} else {
			log.debug("Cargando desde Cache Ultima Tasa Renta Fija :" + nemo);
		}
		
		
		return ret;
	}

}