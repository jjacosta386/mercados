/**
 * 
 */
package co.com.bvc.portal.mercados.persistencia.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.jdbc.JDBCDaoImp;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilDto;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.BusquedaDerivados;
import co.com.bvc.portal.mercados.modelo.CierreGeneral;
import co.com.bvc.portal.mercados.modelo.DerivadoCaracteristicasContrato;
import co.com.bvc.portal.mercados.modelo.DerivadoResumen;
import co.com.bvc.portal.mercados.modelo.DerivadoResumenExtendido;
import co.com.bvc.portal.mercados.modelo.DerivadosAutocomplete;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InformacionTicker;
import co.com.bvc.portal.mercados.modelo.MercadoDerivados;
import co.com.bvc.portal.mercados.persistencia.IDerivadosDao;
import co.com.bvc.portal.mercados.util.IConsultasMercados;

// TODO: Auto-generated Javadoc
/**
 * Implementación de la interfaz de acceso a los datos de los derivados.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class DerivadosDaoSpringJdbc extends JDBCDaoImp implements IDerivadosDao {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getMercadoDerivados
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Set<MercadoDerivados> getMercadoDerivados(String fechaAyer)
			throws Exception {

		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_60SECS);
		String queryKey = "MERCADO_DERIVADOS." + fechaAyer;
		Set<MercadoDerivados> result = (Set) cache.getObject(queryKey);
		if (result == null) {
			synchronized (this) {
				result = (Set) cache.getObject(queryKey);			
				if (result == null) {
					result = new LinkedHashSet<MercadoDerivados>();
					String[] nombresParametros = { "fechaMenosV", "fechaAyer" };
					String[] parametros = {UtilFechas.fechaMenos20Minutos(new GregorianCalendar()),	fechaAyer};
					log.info("Estos son los valores de las fechas fechaMenosV: "+parametros[0]+" fechaAyer: "+parametros[1]);
					List<Map> resQuery = cargarPorNombreQuery(IConsultasMercados.MERCADOS_DERIVADOS,nombresParametros, parametros);

					List<MercadoDerivados> resDto = UtilDto.obtenerObjetos(MercadoDerivados.class, resQuery);

					result.addAll(resDto);
					MercadoDerivados res2 = new MercadoDerivados();
					Double oi = new Double(0);
					Integer totalContratos = 0;
					Long volumen = new Long(0);
					for (Iterator iterator = result.iterator(); iterator.hasNext();) {
						MercadoDerivados merc = (MercadoDerivados) iterator.next();
						totalContratos = totalContratos + merc.getContratos();
						oi += merc.getOpenInterest() == null ? 0 : merc.getOpenInterest();
						volumen = volumen + merc.getVolumen();
					}
					res2.setContrato("Total");
					res2.setParticipacion(new Double(100));
					res2.setOpenInterest(oi);
					res2.setContratos(totalContratos);
					res2.setVolumen(volumen);
					for (Iterator iterator = result.iterator(); iterator
							.hasNext();) {
						MercadoDerivados merc = (MercadoDerivados) iterator
								.next();
						Double participacion = res2.getContratos() == 0 ? 0
								: new Double((merc.getContratos() * 100)
										/ res2.getContratos());
						merc.setParticipacion(participacion);
					}
					result.add(res2);
					cache.putObject(queryKey, result);
					return result;
				}
			}
		} else {
			log.debug("Cargando desde Cache  getMercadosDerivados : "
					+ fechaAyer);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDerivadosDao#
	 * getMercadoDerivadosHistoria
	 * (co.com.bvc.portal.mercados.modelo.BusquedaDerivados)
	 */
	@SuppressWarnings("unchecked")
	public Set<MercadoDerivados> getMercadoDerivadosHistoria(
			BusquedaDerivados busquedaDerivados) throws Exception {
		Set<MercadoDerivados> result = new LinkedHashSet<MercadoDerivados>();

		String[] nombresParametros = { "fecha", "date" };
		String fecha = busquedaDerivados.getAnioFecha() + "-" + busquedaDerivados.getMesFecha() + "-" + busquedaDerivados.getDiaFecha();
		String fechaInt = busquedaDerivados.getAnioFecha() + busquedaDerivados.getMesFecha() + busquedaDerivados.getDiaFecha();
		String[] parametros = { fecha, fechaInt }; 
		List<Map> resQuery = cargarPorNombreQuery(IConsultasMercados.MERCADOS_DERIVADOS_HISTORICO,nombresParametros, parametros);
		List<MercadoDerivados> resDto = UtilDto.obtenerObjetos(MercadoDerivados.class, resQuery);
		result.addAll(resDto);

		MercadoDerivados res2 = new MercadoDerivados();
		Double oi = new Double(0);
		Integer totalContratos = new Integer(0);
		Long volumen = new Long(0);
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			MercadoDerivados merc = (MercadoDerivados) iterator.next();
			oi = oi + merc.getOpenInterest();
			totalContratos = totalContratos + merc.getContratos();
			volumen = volumen + merc.getVolumen();
		}
		res2.setContrato("Total");
		res2.setOpenInterest(oi);
		res2.setContratos(totalContratos);
		res2.setVolumen(volumen);
		res2.setParticipacion(new Double(100));
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			MercadoDerivados merc = (MercadoDerivados) iterator.next();
			merc.setParticipacion(new Double((merc.getContratos() * 100) / res2.getContratos()));
		}
		result.add(res2);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getFuturos()
	 */
	@SuppressWarnings("unchecked")
	public Set<DerivadoResumen> getDerivados(String opcion,
			String nombreDerivado) throws Exception {
		Set<DerivadoResumen> result = new LinkedHashSet<DerivadoResumen>();
		if (opcion.equalsIgnoreCase("opcf")) {
			String[] nombresParametros = { "fechaMenosV" };
			String[] parametros = { UtilFechas
					.fechaMenos20Minutos(new GregorianCalendar()) };
			List<Map> resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADOS_RESUMEN_OPCF,
					nombresParametros, parametros);
			List<DerivadoResumen> resDto = UtilDto.obtenerObjetos(
					DerivadoResumen.class, resQuery);
			result.addAll(resDto);
		} else {
			String[] nombresParametros = { "opcion", "fechaMenosV" };
			String[] parametros = { opcion,
					UtilFechas.fechaMenos20Minutos(new GregorianCalendar()) };
			List<Map> resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADOS_RESUMEN, nombresParametros,
					parametros);
			List<DerivadoResumen> resDto = UtilDto.obtenerObjetos(
					DerivadoResumen.class, resQuery);
			result.addAll(resDto);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getDerivados(co
	 * .com.bvc.portal.mercados.modelo.BusquedaDerivados, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Set<DerivadoResumen> getDerivados(
			BusquedaDerivados busquedaDerivados)
			throws Exception {

		Set<DerivadoResumen> result;

		boolean cached = false;
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)) {
			cached = true;
		}

		if (cached) {
			ControladorCacheJCS cache = JCSFactory
					.getRegionControladorCache(JCSFactory.MEM_60SECS);
			String queryKey = "GET_DERIVADOS."
					+ busquedaDerivados.getTipoContrato();
			result = (Set) cache.getObject(queryKey);
			if (result == null) {
				synchronized (this) {
					result = (Set) cache.getObject(queryKey);
					if (result == null) {
						log.debug("Procesando getDerivados : "
								+ busquedaDerivados.getAnioFecha()
								+ busquedaDerivados.getMesFecha()
								+ busquedaDerivados.getDiaFecha()
								+ " tipoContrato "
								+ busquedaDerivados.getTipoContrato());
						result = getDerivadosInnerProc(busquedaDerivados);
						cache.putObject(queryKey, result);
						return result;
					}
				}
			} else {
				log.debug("Cargando desde Cache getDerivados : "
						+ busquedaDerivados.getAnioFecha()
						+ busquedaDerivados.getMesFecha()
						+ busquedaDerivados.getDiaFecha() + " tipoContrato "
						+ busquedaDerivados.getTipoContrato());
			}
			return result;

		}

		return getDerivadosInnerProc(busquedaDerivados);

	}

	/**
	 * Gets the derivados inner proc.
	 * 
	 * @param busquedaDerivados
	 *            the busqueda derivados
	 * @param fechaAyer
	 *            the fecha ayer
	 * 
	 * @return the derivados inner proc
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unchecked")
	private Set<DerivadoResumen> getDerivadosInnerProc(
			BusquedaDerivados busquedaDerivados)
			throws Exception {
		Set<DerivadoResumen> result = new LinkedHashSet<DerivadoResumen>();
		String[] nombresParametros = { "and1", "and2", "fechaG", "fechaSG" };
		String and1 = " ", and2 = " ";
		String fechaG = busquedaDerivados.getAnioFecha() + "-" + busquedaDerivados.getMesFecha() + "-" + busquedaDerivados.getDiaFecha();
		String fecha = fechaG.replaceAll("-", "");
		if (!busquedaDerivados.getTipoContrato().equalsIgnoreCase("")) {
			and1 = and1 + " and V_DRMI_TIPO_CONTRATO = '"
					+ busquedaDerivados.getTipoContrato() + "' ";
			if (busquedaDerivados.getTipoContrato().equalsIgnoreCase("O")
					&& !busquedaDerivados.getTipoOpcion().equalsIgnoreCase(""))
				and1 = and1 + " and V_DRMI_PUT_CALL = '"
						+ busquedaDerivados.getTipoOpcion() + "' ";
		}
		if (!busquedaDerivados.getContrato().equalsIgnoreCase("")) {
			and2 = and2 + " and V_DRMI_NOM_CONTRATO like '%"
					+ busquedaDerivados.getContrato() + "%' ";
		}
		String[] parametros = { and1, and2, fechaG, fecha };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.DERIVADOS_PARAMETROS_BUSQUEDA_HISTORICO,
				nombresParametros, parametros);
		List<DerivadoResumen> resDto = UtilDto.obtenerObjetos(
				DerivadoResumen.class, resQuery);
		result.addAll(resDto);
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getDerivadosDia
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Set<DerivadoResumenExtendido> getDerivadosDia(String nemoDerivado,
			BusquedaDerivados busquedaDerivados) throws Exception {
		Set<DerivadoResumenExtendido> result = new LinkedHashSet<DerivadoResumenExtendido>();
		String[] nombresParametros = { "nemoDerivado", "and1"};
		String and1 = "";
		if (!BusquedaDerivados.isFechaVacia(busquedaDerivados)
				&& !BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			and1 = " and tr.V_DRTRA_HOR_GRA like '"
					+ busquedaDerivados.getAnioFecha() + "-"
					+ busquedaDerivados.getMesFecha() + "-"
					+ busquedaDerivados.getDiaFecha() + "%'";
		}
		else
			and1 = " and V_DRTR_HOR_GRA < '" + UtilFechas.fechaMenos20Minutos(new GregorianCalendar())+"'";
		String[] parametros = { nemoDerivado, and1 };
		List<Map> resQuery;
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)){
			resQuery = cargarPorNombreQuery(IConsultasMercados.DERIVADOS_DIA,
					nombresParametros, parametros);
		}
		else
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADOS_DIA_HISTORICO,
					nombresParametros, parametros);
		List<DerivadoResumenExtendido> resDto = UtilDto.obtenerObjetos(
				DerivadoResumenExtendido.class, resQuery);
		result.addAll(resDto);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getDerivadosDia
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Set<DerivadoResumenExtendido> getDerivadosDiaOPCF(
			String nemoDerivado, BusquedaDerivados busquedaDerivados)
			throws Exception {
		Set<DerivadoResumenExtendido> result = new LinkedHashSet<DerivadoResumenExtendido>();
		String[] nombresParametros = { "nemoDerivado", "and1" };
		String and1 = "";
		if (!BusquedaDerivados.isFechaVacia(busquedaDerivados)
				&& !BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			and1 = " and tr.V_ACTRA_HOR_GRA like '"
					+ busquedaDerivados.getAnioFecha() + "-"
					+ busquedaDerivados.getMesFecha() + "-"
					+ busquedaDerivados.getDiaFecha() + "%'";
		}
		String[] parametros = { nemoDerivado, and1 };
		List<Map> resQuery;
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados))
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADOS_DIA_OPCF, nombresParametros,
					parametros);
		else
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADOS_DIA_HISTORICO_OPCF,
					nombresParametros, parametros);
		List<DerivadoResumenExtendido> resDto = UtilDto.obtenerObjetos(
				DerivadoResumenExtendido.class, resQuery);
		result.addAll(resDto);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getDerivadosExtendidos
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Set<DerivadoResumenExtendido> getDerivadosExtendidos(String contrato, BusquedaDerivados busquedaDerivados) throws Exception {
		Set<DerivadoResumenExtendido> result = new LinkedHashSet<DerivadoResumenExtendido>();
		String fechaCons = "";
		String openFh = "";
		if (!BusquedaDerivados.isFechaVacia(busquedaDerivados)) {
			fechaCons = busquedaDerivados.getAnioFecha() + "-" + busquedaDerivados.getMesFecha() + "-" + busquedaDerivados.getDiaFecha();
			openFh = busquedaDerivados.getAnioFecha() + busquedaDerivados.getMesFecha() + busquedaDerivados.getDiaFecha();
		}
 		String[] nombresParametros = { "conContrato", "contrato", "and1", "and2", "fechaMenosV", "fechaCons", "openFh" };
		String and1 = "", and2 = "";
	
		List<Map> resQuery = new ArrayList<Map>();
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados) || BusquedaDerivados.isFechaHoy(busquedaDerivados)){
			String[] parametros = {contrato == null ? "" : "AND V_ACTR_PLALIQ  = ':contrato'",contrato == null ? "" : contrato, and1, and2, UtilFechas.fechaMenos20Minutos(new GregorianCalendar()), fechaCons, openFh };
			resQuery = cargarPorNombreQuery(IConsultasMercados.DERIVADOS_EXTENDIDOS, nombresParametros,parametros);
		}else {
			and2 = "";
			and1 = "";
			String[] parametros = {contrato == null ? "" : "AND V_ACTRA_PLALIQ  = ':contrato'", contrato == null ? "" : contrato, and1, and2, UtilFechas.fechaMenos20Minutos(new GregorianCalendar()), fechaCons, openFh };
			resQuery = cargarPorNombreQuery(IConsultasMercados.DERIVADOS_EXTENDIDOS_HISTORICO,nombresParametros, parametros);
		}
		
		List<DerivadoResumenExtendido> resDto = UtilDto.obtenerObjetos(
				DerivadoResumenExtendido.class, resQuery);
		result.addAll(resDto);
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getPrecioApertura
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Double getPrecioApertura(String nemo,
			BusquedaDerivados busquedaDerivados) throws PersistenciaException {
		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = new ArrayList<Map>();
		Double result = new Double(0);
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_APERTURA,
					nombresParametros, parametros);
		} else {
			String[] nombresParams = { "nemo", "fechaAnt" };
			String[] params = {
					nemo,
					busquedaDerivados.getAnioFecha() + "-"
							+ busquedaDerivados.getMesFecha() + "-"
							+ busquedaDerivados.getDiaFecha() };
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_APERTURA_HISTORICO,
					nombresParams, params);
		}
		if (resQuery.size() > 0)
			result = new Double(resQuery.get(0).get("precio").toString());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getPrecioApertura
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Double getPrecioAperturaOPCF(String nemo,
			BusquedaDerivados busquedaDerivados) throws PersistenciaException {
		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = new ArrayList<Map>();
		Double result = new Double(0);
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_APERTURA_OPCF,
					nombresParametros, parametros);
		} else {
			String[] nombresParams = { "nemo", "fechaAnt" };
			String[] params = {
					nemo,
					busquedaDerivados.getAnioFecha() + "-"
							+ busquedaDerivados.getMesFecha() + "-"
							+ busquedaDerivados.getDiaFecha() };
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_APERTURA_HISTORICO_OPCF,
					nombresParams, params);
		}
		if (resQuery.size() > 0)
			result = new Double(resQuery.get(0).get("precio").toString());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getPrecioMayorContrato
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Double getPrecioMayorContrato(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception {
		String[] nombresParametros = { "nemo", "fechaMenosV" };
		String[] parametros = { nemo, UtilFechas.fechaMenos20Minutos(new GregorianCalendar()) };
		List<Map> resQuery = new ArrayList<Map>();
		Double result = new Double(0);
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_MAYOR_CONTRATO,
					nombresParametros, parametros);
		} else {
			String[] nombresParams = { "nemo", "fechaAnt" };
			String[] params = {
					nemo,
					busquedaDerivados.getAnioFecha() + "-"
							+ busquedaDerivados.getMesFecha() + "-"
							+ busquedaDerivados.getDiaFecha() };
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_MAYOR_CONTRATO_HISTORICO,
					nombresParams, params);
		}
		if (resQuery.size() > 0 && resQuery.get(0).get("Precio") != null)
			result = new Double((Double)resQuery.get(0).get("precio"));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getPrecioMayorContrato
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Double getPrecioMayorContratoOPCF(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception {
		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = new ArrayList<Map>();
		Double result = new Double(0);
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_MAYOR_CONTRATO_OPCF,
					nombresParametros, parametros);
		} else {
			String[] nombresParams = { "nemo", "fechaAnt" };
			String[] params = {
					nemo,
					busquedaDerivados.getAnioFecha() + "-"
							+ busquedaDerivados.getMesFecha() + "-"
							+ busquedaDerivados.getDiaFecha() };
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_MAYOR_CONTRATO_HISTORICO_OPCF,
					nombresParams, params);
		}
		if (resQuery.size() > 0 && resQuery.get(0).get("precio") != null)
			result = new Double(resQuery.get(0).get("precio").toString());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getPrecioMenorContrato
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Double getPrecioMenorContrato(String nemo,
			BusquedaDerivados busquedaDerivados) throws PersistenciaException {
		String[] nombresParametros = { "nemo", "fechaMenosV" };
		String[] parametros = { nemo, UtilFechas.fechaMenos20Minutos(new GregorianCalendar()) };
		Double result = new Double(0);
		List<Map> resQuery = new ArrayList<Map>();
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_MENOR_CONTRATO,
					nombresParametros, parametros);
		} else {
			String[] nombresParams = { "nemo", "fechaAnt" };
			String[] params = {
					nemo,
					busquedaDerivados.getAnioFecha() + "-"
							+ busquedaDerivados.getMesFecha() + "-"
							+ busquedaDerivados.getDiaFecha() };
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_MENOR_CONTRATO_HISTORICO,
					nombresParams, params);
		}
		if (resQuery.size() > 0 && resQuery.get(0).get("precio") != null)
			result = new Double((Double)resQuery.get(0).get("precio"));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getPrecioMenorContrato
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Double getPrecioMenorContratoOPCF(String nemo,
			BusquedaDerivados busquedaDerivados) throws PersistenciaException {
		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		Double result = new Double(0);
		List<Map> resQuery = new ArrayList<Map>();
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_MENOR_CONTRATO_OPCF,
					nombresParametros, parametros);
		} else {
			String[] nombresParams = { "nemo", "fechaAnt" };
			String[] params = {
					nemo,
					busquedaDerivados.getAnioFecha() + "-"
							+ busquedaDerivados.getMesFecha() + "-"
							+ busquedaDerivados.getDiaFecha() };
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_PRECIO_MENOR_CONTRATO_HISTORICO_OPCF,
					nombresParams, params);
		}
		if (resQuery.size() > 0)
			result = new Double(resQuery.get(0).get("precio").toString());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDerivadosDao#
	 * getUltimaOperacionDerivado(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String getUltimaOperacionDerivado(String nemo,
			BusquedaDerivados busquedaDerivados) throws PersistenciaException {
		String[] nombresParametros = { "nemo", "fechaMenosV" };
		String[] parametros = { nemo, UtilFechas.fechaMenos20Minutos(new GregorianCalendar()) };
		String result = "";
		List<Map> resQuery = new ArrayList<Map>();
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.ULTIMA_OPERACION_DERIVADO,
					nombresParametros, parametros);
		} else {
			String[] nombresParams = { "nemo", "fechaAnt" };
			String[] params = {
					nemo,
					busquedaDerivados.getAnioFecha() + "-"
							+ busquedaDerivados.getMesFecha() + "-"
							+ busquedaDerivados.getDiaFecha() };
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.ULTIMA_OPERACION_DERIVADO_HISTORICO,
					nombresParams, params);
		}
		if (resQuery.size() > 0 && resQuery.get(0).get("ultima") != null)
			result = resQuery.get(0).get("ultima").toString();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDerivadosDao#
	 * getUltimaOperacionDerivado(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String getUltimaOperacionDerivadoOPCF(String nemo,
			BusquedaDerivados busquedaDerivados) throws PersistenciaException {
		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		String result = "";
		List<Map> resQuery = new ArrayList<Map>();
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.ULTIMA_OPERACION_DERIVADO_OPCF,
					nombresParametros, parametros);
		} else {
			String[] nombresParams = { "nemo", "fechaAnt" };
			String[] params = {
					nemo,
					busquedaDerivados.getAnioFecha() + "-"
							+ busquedaDerivados.getMesFecha() + "-"
							+ busquedaDerivados.getDiaFecha() };
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.ULTIMA_OPERACION_DERIVADO_HISTORICO_OPCF,
					nombresParams, params);
		}
		if (resQuery.size() > 0)
			result = resQuery.get(0).get("ultima").toString();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDerivadosDao#
	 * getDerivadoResumenContrato(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public DerivadoCaracteristicasContrato getDerivadoResumenContrato(
			String nemo, BusquedaDerivados busquedaDerivados) throws Exception {
		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.DERIVADO_RESUMEN_CONTRATO,
				nombresParametros, parametros);
		List<DerivadoCaracteristicasContrato> resDto = UtilDto.obtenerObjetos(
				DerivadoCaracteristicasContrato.class, resQuery);
		DerivadoCaracteristicasContrato result = new DerivadoCaracteristicasContrato();
		if (resDto.size() > 0) {
			result = resDto.get(0);
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			result.setFechaInscripcion(sdf.parse(result.getFechaInsc()
					.toString()));
			result.setFechaVencimiento(sdf.parse(result.getFechaVcto()
					.toString()));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDerivadosDao#
	 * getDerivadoResumenExtendido(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public DerivadoResumenExtendido getDerivadoResumenExtendido(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception {
		DerivadoResumenExtendido result = new DerivadoResumenExtendido();
		List<Map> resQuery = new ArrayList<Map>();
		List<DerivadoResumenExtendido> resDto = new ArrayList<DerivadoResumenExtendido>();
		String[] nombresParametros = { "nemo", "fechaMenosV" };
		// String and1 = "";
		// if (StringUtils.isNotEmpty(busquedaDerivados.getDiaFecha())
		// && StringUtils.isNotEmpty(busquedaDerivados.getMesFecha())
		// && StringUtils.isNotEmpty(busquedaDerivados.getAnioFecha()))
		// and1 = " and ";
		String[] parametros = { nemo, UtilFechas.fechaMenos20Minutos(new GregorianCalendar()) };
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_RESUMEN_EXTENDIDO,
					nombresParametros, parametros);
			resDto = UtilDto.obtenerObjetos(DerivadoResumenExtendido.class,
					resQuery);
			if (resDto.size() > 0)
				result = resDto.get(0);
		} else {
			String fechaCons = busquedaDerivados.getAnioFecha() + "-" + busquedaDerivados.getMesFecha() + "-" + busquedaDerivados.getDiaFecha();
			String opInFecha = busquedaDerivados.getAnioFecha() + busquedaDerivados.getMesFecha() + busquedaDerivados.getDiaFecha();
			String[] nombresParams = { "nemo", "fechaHoy", "opInFecha" };
			String[] params = { nemo, fechaCons, opInFecha };
			resQuery = cargarPorNombreQuery(IConsultasMercados.DERIVADO_RESUMEN_EXTENDIDO_HISTORICO,nombresParams, params);
			resDto = UtilDto.obtenerObjetos(DerivadoResumenExtendido.class,resQuery);
			if (resDto.size() > 0)
				result = resDto.get(0);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDerivadosDao#
	 * getDerivadoResumenExtendido(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public DerivadoResumenExtendido getDerivadoResumenExtendidoOPCF(
			String nemo, BusquedaDerivados busquedaDerivados) throws Exception {
		DerivadoResumenExtendido result = new DerivadoResumenExtendido();
		List<Map> resQuery = new ArrayList<Map>();
		List<DerivadoResumenExtendido> resDto = new ArrayList<DerivadoResumenExtendido>();
		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados)
				|| BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_RESUMEN_EXTENDIDO_OPCF,
					nombresParametros, parametros);
			resDto = UtilDto.obtenerObjetos(DerivadoResumenExtendido.class,
					resQuery);
			if (resDto.size() > 0)
				result = resDto.get(0);
		} else {
			String fechaMenosMes = (Integer.parseInt(busquedaDerivados
					.getMesFecha()) - 1)
					+ "";
			String fechaCons = busquedaDerivados.getAnioFecha() + "-"
					+ busquedaDerivados.getMesFecha() + "-"
					+ busquedaDerivados.getDiaFecha();
			String fechaMenos1Mes = fechaMenosMes.length() < 2 ? "0"
					+ fechaMenosMes : fechaMenosMes;
			fechaMenos1Mes = busquedaDerivados.getAnioFecha() + "-"
					+ fechaMenos1Mes + "-" + busquedaDerivados.getDiaFecha()
					+ ":00:00:00";
			String[] nombresParams = { "nemo", "fechaIni", "fechaFin",
					"fechaMes" };
			String[] params = { nemo, fechaCons + ":00:00:00",
					fechaCons + ":23:59:99", fechaMenos1Mes };

			List<Map> fechas = cargarPorNombreQuery(
					IConsultasMercados.FECHAS_PARA_RESUMEN_EXTENDIDO_HISTORICO_OPCF,
					nombresParams, params);
			String fechaDiaAnt = "";
			String fechaDiaAct = "";
			if (fechas != null && !fechas.isEmpty()) {
				Map linea = (Map) fechas.iterator().next();
				fechaDiaAnt = (String) linea.get("fechaDiaAnt");
				fechaDiaAct = (String) linea.get("fechaDiaAct");
			}

			resQuery = cargarPorNombreQuery(
					IConsultasMercados.DERIVADO_RESUMEN_EXTENDIDO_HISTORICO_OPCF,
					new String[] { "nemo", "fechaMes", "fechaIni", "fechaFin",
							"fechaHoy" }, new String[] { nemo, fechaDiaAnt,
							fechaCons + ":00:00:00", fechaCons + ":23:59:99",
							fechaDiaAct });
			resDto = UtilDto.obtenerObjetos(DerivadoResumenExtendido.class,
					resQuery);
			if (resDto.size() > 0)
				result = resDto.get(0);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#cargarHistoricoDerivado
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoDerivado(String nemo) throws Exception {
		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.HISTORICO_DERIVADO, nombresParametros,
				parametros);
		List<ICierre> resDto = UtilDto.obtenerObjetos(CierreGeneral.class,
				resQuery);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd:kk:mm:ss");
		for (Iterator iterator = resDto.iterator(); iterator.hasNext();) {
			ICierre cierre = (ICierre) iterator.next();
			cierre.getFechaHora().setTime(df.parse(cierre.getFechaString()));
		}
		return resDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#cargarHistoricoDerivado
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarHistoricoDerivadoOPCF(String nemo)
			throws Exception {
		String[] nombresParametros = { "nemo" };
		String[] parametros = { nemo };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.HISTORICO_DERIVADO_OPCF, nombresParametros,
				parametros);
		List<ICierre> resDto = UtilDto.obtenerObjetos(CierreGeneral.class,
				resQuery);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd:kk:mm:ss");
		for (Iterator iterator = resDto.iterator(); iterator.hasNext();) {
			ICierre cierre = (ICierre) iterator.next();
			cierre.getFechaHora().setTime(df.parse(cierre.getFechaString()));
		}
		return resDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getMejorBid(java
	 * .lang.String, co.com.bvc.portal.mercados.modelo.BusquedaDerivados)
	 */
	@SuppressWarnings("unchecked")
	public Double getMejorBid(String nemo, BusquedaDerivados busquedaDerivados)
			throws Exception {
		String[] nombresParametros = { "nemo", "and1" };
		String and1 = " ";
		if (busquedaDerivados == null) {
			and1 = and1
					+ " and V_DRPM_FECHA = "
					+ "(select fechaHoy from "
					+ "(select max(CONCAT(CONCAT(SUBSTRING(tr.V_DRTR_HOR_GRA, 1, 4), "
					+ "SUBSTRING(tr.V_DRTR_HOR_GRA, 6, 2)),SUBSTRING(tr.V_DRTR_HOR_GRA, 9, 2))) fechaHoy "
					+ "from unicabd.tbdrtr tr) fechaActual)";
		} else {
			and1 = and1 + " and V_DRPM_FECHA = '"
					+ busquedaDerivados.getAnioFecha()
					+ busquedaDerivados.getMesFecha()
					+ busquedaDerivados.getDiaFecha() + "'";
		}
		String[] parametros = { nemo, and1 };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.DERIVADO_MENOR_BID, nombresParametros,
				parametros);
		Double result = new Double(0);
		if (resQuery.size() > 0)
			result = new Double(resQuery.get(0).get("menorBid").toString());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getMejorOffer(java
	 * .lang.String, co.com.bvc.portal.mercados.modelo.BusquedaDerivados)
	 */
	@SuppressWarnings("unchecked")
	public Double getMejorOffer(String nemo, BusquedaDerivados busquedaDerivados)
			throws Exception {
		String[] nombresParametros = { "nemo", "and1" };
		String and1 = " ";
		if (busquedaDerivados == null) {
			and1 = and1
					+ " and V_DRPM_FECHA = "
					+ "(select fechaHoy from "
					+ "(select max(CONCAT(CONCAT(SUBSTRING(tr.V_DRTR_HOR_GRA, 1, 4), "
					+ "SUBSTRING(tr.V_DRTR_HOR_GRA, 6, 2)),SUBSTRING(tr.V_DRTR_HOR_GRA, 9, 2))) fechaHoy "
					+ "from unicabd.tbdrtr tr) fechaActual)";
		} else {
			and1 = and1 + " and V_DRPM_FECHA = '"
					+ busquedaDerivados.getAnioFecha()
					+ busquedaDerivados.getMesFecha()
					+ busquedaDerivados.getDiaFecha() + "'";
		}
		String[] parametros = { nemo, and1 };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.DERIVADO_MEJOR_OFFER, nombresParametros,
				parametros);
		Double result = new Double(0);
		if (resQuery.size() > 0)
			result = new Double(resQuery.get(0).get("mejorOffer").toString());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#derivadosTicker()
	 */
	@SuppressWarnings("unchecked")
	public List<InformacionTicker> derivadosTicker()
			throws PersistenciaException {
		String fecha20Min = UtilFechas
				.fechaMenos20Minutos(new GregorianCalendar());
		String fechaHoy = UtilFechas.getHoy("yyyyMMdd");
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.DERIVADOS_TICKER, new String[] {
						"fechaMenosV", "fechaAyer" }, new String[] {
						fecha20Min, fechaHoy });
		List<InformacionTicker> resDto = new ArrayList<InformacionTicker>();
		try {
			resDto = UtilDto.obtenerObjetos(InformacionTicker.class, resQuery);
		} catch (Exception ex) {
			throw new PersistenciaException(
					"Error al cargar los datos de la consulta "
							+ IConsultasMercados.DERIVADOS_TICKER
							+ " en objetos de tipo InformacionTicker");
		}
		return resDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDerivadosDao#
	 * obtenerTotalContratosUltDia()
	 */
	@SuppressWarnings("unchecked")
	public Double obtenerTotalContratosUltDia() throws Exception {
		List<Map> resQuery = cargarPorNombreQuery(IConsultasMercados.TOTAL_CONTRATOS_DIA_ANTERIOR);
		return (resQuery != null && !resQuery.isEmpty()) ? (Double) resQuery.get(0).get("suma") : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#cargarIntradiaDerivados
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public ICierre cargarIntradiaDerivados(String nemo)
			throws PersistenciaException {
		String[] nombresParametros = { "nemo", "fechaAnt" };
		String[] parametros = { nemo,
				UtilFechas.fechaMenos20Minutos(new GregorianCalendar()) };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.CARGAR_INTRADIA_DERIVADOS,
				nombresParametros, parametros);
		List<ICierre> resDto;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:kk:mm");
			resDto = UtilDto.obtenerObjetos(CierreGeneral.class, resQuery);
			for (Iterator iterator = resDto.iterator(); iterator.hasNext();) {
				ICierre cierre = (ICierre) iterator.next();
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(sdf.parse(cierre.getFechaString()));
				cierre.setFechaHora(cal);
			}

		} catch (Exception e) {
			PersistenciaException ex = new PersistenciaException();
			throw ex;
		}
		if (resDto.size() > 0)
			return resDto.get(0);
		else
			return new CierreGeneral();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDerivadosDao#
	 * cargarIntradiaDerivadosHistorico(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarIntradiaDerivadosHistorico(String nemo)
			throws PersistenciaException {
		String[] nombresParametros = { "nemo", "fechaAnt" };
		String[] parametros = { nemo,
				UtilFechas.fechaMenos20Minutos(new GregorianCalendar()) };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.CARGAR_INTRADIA_DERIVADOS_HISTORICO,
				nombresParametros, parametros);
		List<ICierre> resDto;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:kk:mm");
			resDto = UtilDto.obtenerObjetos(CierreGeneral.class, resQuery);
			for (Iterator iterator = resDto.iterator(); iterator.hasNext();) {
				ICierre cierre = (ICierre) iterator.next();
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(sdf.parse(cierre.getFechaString()));
				cierre.setFechaHora(cal);
			}
		} catch (Exception e) {
			PersistenciaException ex = new PersistenciaException();
			throw ex;
		}
		return resDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.persistencia.IDerivadosDao#
	 * cargarIntradiaDerivadosHistorico(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> cargarIntradiaDerivadosHistoricoOPCF(String nemo)
			throws PersistenciaException {
		String[] nombresParametros = { "nemo", "fechaAnt" };
		String[] parametros = { nemo,
				UtilFechas.fechaMenos20Minutos(new GregorianCalendar()) };
		List<Map> resQuery = cargarPorNombreQuery(
				IConsultasMercados.CARGAR_INTRADIA_DERIVADOS_HISTORICO_OPCF,
				nombresParametros, parametros);
		List<ICierre> resDto;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:kk:mm:ss");
			resDto = UtilDto.obtenerObjetos(CierreGeneral.class, resQuery);
			for (Iterator iterator = resDto.iterator(); iterator.hasNext();) {
				ICierre cierre = (ICierre) iterator.next();
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(sdf.parse(cierre.getFechaString()));
				cierre.setFechaHora(cal);
			}
		} catch (Exception e) {
			PersistenciaException ex = new PersistenciaException();
			throw ex;
		}
		return resDto;
	}

	@SuppressWarnings("unchecked")
	public Integer verificarContratoDerivados(String nemo) throws PersistenciaException {
		String[] nombresParametros = {"nemo"};
		String[] parametros = {nemo};
		
		List<Map> result = cargarPorNombreQuery(IConsultasMercados.VERIFICAR_CONTRATO_DERIVADOS, nombresParametros, parametros);
		
		Long longNumber = (Long)result.get(0).get("cuenta");
		
		return longNumber.intValue();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.persistencia.IDerivadosDao#getAccionesTodas()
	 */
	@SuppressWarnings("unchecked")
	public List<DerivadosAutocomplete> getAccionesTodas()
			throws PersistenciaException {
		List<DerivadosAutocomplete> res;
		String consulta = "select distinct V_DRTRA_NEMO as Derivados from tbdrtra";

		Collection tmp = this.getJdbcTemplate().query(consulta,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						DerivadosAutocomplete res = new DerivadosAutocomplete();
						res.setNemo(rs.getString("Derivados"));

						return res;
					}
				});

		res = new ArrayList<DerivadosAutocomplete>(tmp);
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<DerivadoResumenExtendido> getDerivadoResumenExtendidoGrafica(String nemo,BusquedaDerivados busquedaDerivados) throws Exception {
		List<Map> resQuery = new ArrayList<Map>();
		List<DerivadoResumenExtendido> resDto = new ArrayList<DerivadoResumenExtendido>();
		String[] nombresParametros = {"nemo", "fechaIni", "fechaFin"};
		
		String fechaIni = busquedaDerivados.getAnioFecha() + "-" + busquedaDerivados.getMesFecha() + "-" + busquedaDerivados.getDiaFecha();
		String fechaFin = busquedaDerivados.getAnioFechaFin() + "-" + busquedaDerivados.getMesFechaFin() + "-" + busquedaDerivados.getDiaFechaFin();
		
		String[] parametros = {nemo};
		if (BusquedaDerivados.isFechaVacia(busquedaDerivados) || BusquedaDerivados.isFechaHoy(busquedaDerivados)) {
			resQuery = cargarPorNombreQuery(IConsultasMercados.DERIVADO_RESUMEN_EXTENDIDO,nombresParametros, parametros);
			resDto = UtilDto.obtenerObjetos(DerivadoResumenExtendido.class,resQuery);
		} else {
			String[] nombresParams = {"nemo", "fechaIni", "fechaFin"};
			String[] params = {nemo, fechaIni, fechaFin};
			resQuery = cargarPorNombreQuery(IConsultasMercados.DERIVADO_RESUMEN_EXTENDIDO_HISTORICO_GRAFICA, nombresParams, params);
			resDto = UtilDto.obtenerObjetos(DerivadoResumenExtendido.class,resQuery);
		}
		return resDto;
	}

}