package co.com.bvc.portal.mercados.servicio.imp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.Especie;
import co.com.bvc.portal.mercados.modelo.RentaFijaAutocomplete;
import co.com.bvc.portal.mercados.modelo.RentaFijaOperacion;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumen;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenEspecie;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTipoTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;
import co.com.bvc.portal.mercados.persistencia.IRentaFijaDao;
import co.com.bvc.portal.mercados.servicio.IRentaFija;

/**
 * The Class RentaFija.
 */
public class RentaFija implements IRentaFija {

	/** The log. */
	private static Logger log = Logger.getLogger(RentaFija.class);

	/** The renta fija dao. */
	private IRentaFijaDao rentaFijaDao;

	/** The emisor final. */
	// private EmisorTitulo emisorFinal;

	/**
	 * Gets the renta fija dao.
	 * 
	 * @return the renta fija dao
	 */
	public IRentaFijaDao getRentaFijaDao() {
		return rentaFijaDao;
	}

	/**
	 * Sets the renta fija dao.
	 * 
	 * @param rentaFijaDao
	 *            the new renta fija dao
	 */
	public void setRentaFijaDao(IRentaFijaDao rentaFijaDao) {
		this.rentaFijaDao = rentaFijaDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IEspecieMercado#getDetalleEspecie
	 * (int)
	 */
	public Especie getDetalleEspecie(int accion) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IEspecieMercado#getListaEspecieActiva
	 * ()
	 */
	public List<?> getListaEspecieActiva() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IEspecieMercado#getListaEspecieInactiva
	 * ()
	 */
	public List<?> getListaEspecieInactiva() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IMercado#getCierresHistoricoEspecie
	 * (int)
	 */
	public List<?> getCierresHistoricoEspecie(int especie) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IMercado#getEstadoMercado()
	 */
	public boolean getEstadoMercado() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IMercado#getOperacionesEspecieDia
	 * (java.util.Date, int)
	 */
	public List<?> getOperacionesEspecieDia(Date dia, int especie) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IMercado#getResumenEspecieDia(java
	 * .util.Date)
	 */
	public List<?> getResumenEspecieDia(Date dia) {
		return null;
	}

	/**
	 * Registrar operacion.
	 * 
	 * @param titulosNegociados
	 *            the titulos negociados
	 * @param ope
	 *            the ope
	 */
	@SuppressWarnings("unchecked")
	private static void registrarOperacion(
			Map<String, RentaFijaResumenTipoTitulo> titulosNegociados,
			RentaFijaOperacion ope) {

		if (titulosNegociados.get(ope.getTituloCodigo()) == null) {
			// primera vez que aparece el tipo de titulo

			// Se registran los datos en la especie
			SortedMap<String, RentaFijaResumenEspecie> especies = new TreeMap();
			RentaFijaResumenEspecie resumenEspecie = new RentaFijaResumenEspecie();
			resumenEspecie.setNemotecnico(ope.getNemo());
			resumenEspecie.setCantidad(ope.getCantidad());
			resumenEspecie.setVolumen(ope.getVolumen());
			resumenEspecie.setEmisor(ope.getEmisor());
			resumenEspecie.setFechaHora(ope.getHorGra());
			resumenEspecie.setUltimoPrecioSucio(ope.getPrecioSucio());

			especies.put(resumenEspecie.getNemotecnico(), resumenEspecie);

			// Se registran los datos en el tipo de titulo
			RentaFijaResumenTipoTitulo resumenTitulo = new RentaFijaResumenTipoTitulo();
			resumenTitulo.setNombre(ope.getTituloNombre());
			resumenTitulo.setCodigo(ope.getTituloCodigo());
			resumenTitulo.setCantidad(ope.getCantidad());
			resumenTitulo.setVolumen(ope.getVolumen());
			resumenTitulo.setEspecies(especies);

			titulosNegociados.put(ope.getTituloCodigo(), resumenTitulo);

		} else {

			RentaFijaResumenTipoTitulo resumenTitulo = titulosNegociados
					.get(ope.getTituloCodigo());
			SortedMap<String, RentaFijaResumenEspecie> especies = resumenTitulo
					.getEspecies();

			if (especies.get(ope.getNemo()) == null) {

				RentaFijaResumenEspecie resumenEspecie = new RentaFijaResumenEspecie();
				resumenEspecie.setNemotecnico(ope.getNemo());
				resumenEspecie.setCantidad(ope.getCantidad());
				resumenEspecie.setVolumen(ope.getVolumen());
				resumenEspecie.setEmisor(ope.getEmisor());
				resumenEspecie.setFechaHora(ope.getHorGra());
				resumenEspecie.setUltimoPrecioSucio(ope.getPrecioSucio());
				especies.put(resumenEspecie.getNemotecnico(), resumenEspecie);
				resumenTitulo.setCantidad(resumenTitulo.getCantidad()
						+ ope.getCantidad());
				resumenTitulo.setVolumen(resumenTitulo.getVolumen()
						+ ope.getVolumen());
			} else {

				RentaFijaResumenEspecie resumenEspecie = especies.get(ope
						.getNemo());
				resumenEspecie.setCantidad(resumenEspecie.getCantidad()
						+ ope.getCantidad());
				resumenEspecie.setVolumen(resumenEspecie.getVolumen()
						+ ope.getVolumen());

				if (resumenEspecie.getFechaHora().compareTo(ope.getHorGra()) < 0) {
					// actualizar ultimo precio
					resumenEspecie.setUltimoPrecioSucio(ope.getPrecioSucio());
				}

				resumenTitulo.setCantidad(resumenTitulo.getCantidad()
						+ ope.getCantidad());
				resumenTitulo.setVolumen(resumenTitulo.getVolumen()
						+ ope.getVolumen());

			}

		}

	}

	/**
	 * Calcular resumen.
	 * 
	 * @param rf
	 *            the rf
	 * @param operaciones
	 *            the operaciones
	 * @param tipoDeuda
	 *            the tipo deuda
	 * @param tipoOperacion
	 *            the tipo operacion
	 */
	@SuppressWarnings("unchecked")
	public static void calcularResumen(RentaFijaResumen rf,
			List<RentaFijaOperacion> operaciones, String tipoDeuda,
			String tipoOperacion) {

		Map<String, RentaFijaResumenTipoTitulo> titulosNegociadosPUBLICO = new HashMap();
		Map<String, RentaFijaResumenTipoTitulo> titulosNegociadosPRIVADO = new HashMap();

		double debug = 0;
		// int contRegistro = 0;
		// int contTransaccional = 0;

		for (RentaFijaOperacion ope : operaciones) {

			debug += ope.getVolumen();

			// Verificar filtros
			if ((ope.getTipoDeuda().equals(tipoDeuda) || tipoDeuda
					.equals(RentaFija.DEUDA_ALL))
					&& (ope.getTipoOperacion().equals(tipoOperacion) ||  RentaFija.ALL_OPE.equals(tipoOperacion))) { // Si
				/*
				 * la operacion cumple con los filtros puede ser contabilizada
				 * Si es registro
				 */
				if (ope.isRegistro()) {

					rf.getVolumenRegistroxOPE()[getIndiceTipoOperacion(ope.getTipoOperacion())] += ope
							.getVolumen();
					// contRegistro = contRegistro +1;

				}// Si es transaccional
				else if (ope.isTransaccional()) {

					rf.getVolumenTransaccionalxOPE()[getIndiceTipoOperacion(ope.getTipoOperacion())] += ope
							.getVolumen();
					// contTransaccional = contTransaccional +1;

				}

				if (ope.getTipoDeuda().equals(RentaFija.DEUDA_PUBLICO)) {
					registrarOperacion(titulosNegociadosPUBLICO, ope);
				} else if (ope.getTipoDeuda().equals(RentaFija.DEUDA_PRIVADA)) {
					registrarOperacion(titulosNegociadosPRIVADO, ope);

				}
			}

		}// end loop

		double totalRegistro = 0;
		double totalTransaccional = 0;

		for (int i = 0; i < RentaFija.operaciones.length; i++) {
			totalRegistro += rf.getVolumenRegistroxOPE()[i];
			totalTransaccional += rf.getVolumenTransaccionalxOPE()[i];
		}

		rf.setTotalVolumenRegistro(totalRegistro);
		rf.setTotalVolumenTransaccional(totalTransaccional);

		for (int i = 0; i < RentaFija.operaciones.length && totalRegistro > 0; i++) {
			rf.getPorcentajesRegistroxOPE()[i] = (float) ((rf
					.getVolumenRegistroxOPE()[i] / totalRegistro) * 100);
		}
		for (int i = 0; i < RentaFija.operaciones.length
				&& totalTransaccional > 0; i++) {
			rf.getPorcentajesTransaccionalxOPE()[i] = (float) ((rf
					.getVolumenTransaccionalxOPE()[i] / totalTransaccional) * 100);
		}

		RentaFijaResumenTipoTitulo comparator = new RentaFijaResumenTipoTitulo();

		ArrayList<RentaFijaResumenTipoTitulo> allDeudaPublica = new ArrayList(
				titulosNegociadosPUBLICO.values());
		ArrayList<RentaFijaResumenTipoTitulo> allDeudaPrivada = new ArrayList(
				titulosNegociadosPRIVADO.values());

		rf.setTitulosDeudaPublica(allDeudaPublica);
		Collections.sort((List) rf.getTitulosDeudaPublica(), comparator);

		rf.setTitulosDeudaPrivada(allDeudaPrivada);
		Collections.sort((List) rf.getTitulosDeudaPrivada(), comparator);

		rf.setEspecies5Publica(calcular5EspeciesMasNegociadas(rf
				.getTitulosDeudaPublica()));
		rf.setEspecies5Privada(calcular5EspeciesMasNegociadas(rf
				.getTitulosDeudaPrivada()));

	}

	/**
	 * Calcular5 especies mas negociadas.
	 * 
	 * @param tipoTitulos
	 *            the tipo titulos
	 * 
	 * @return the collection< renta fija resumen especie>
	 */
	private static Collection<RentaFijaResumenEspecie> calcular5EspeciesMasNegociadas(
			Collection<RentaFijaResumenTipoTitulo> tipoTitulos) {

		ArrayList<RentaFijaResumenEspecie> _5 = new ArrayList<RentaFijaResumenEspecie>();
		RentaFijaResumenEspecie comparator = new RentaFijaResumenEspecie();

		for (RentaFijaResumenTipoTitulo tipoTitulo : tipoTitulos) {
			_5.addAll(tipoTitulo.getEspecies().values());

		}

		Collections.sort(_5, comparator);

		return _5.subList(0, _5.size() >= 5 ? 5 : _5.size());
	}

	/**
	 * Obtener resumen titulos.
	 * 
	 * @param titulosNegociadosPUBLICO
	 *            the titulos negociados publico
	 * @param titulosNegociadosPRIVADO
	 *            the titulos negociados privado
	 * @param operaciones
	 *            the operaciones
	 * @param tipoDeuda
	 *            the tipo deuda
	 * @param tipoOperacion
	 *            the tipo operacion
	 */
	public void obtenerResumenTitulos(
			Map<String, RentaFijaResumenTipoTitulo> titulosNegociadosPUBLICO,
			Map<String, RentaFijaResumenTipoTitulo> titulosNegociadosPRIVADO,
			List<RentaFijaOperacion> operaciones, String tipoDeuda,
			String tipoOperacion) {

		for (RentaFijaOperacion ope : operaciones) {
			// Verificar Deuda and Verificar Sesion de Negociacion
			if ((ope.getTipoDeuda().equals(tipoDeuda) || tipoDeuda
					.equals(RentaFija.DEUDA_ALL))
					&& (ope.getTipoOperacion().equals(tipoOperacion) || RentaFija.ALL_OPE.equals(tipoOperacion))) { // Si
				// la
				// operacion
				// cumple
				// con
				// los
				// filtros
				// puede
				// ser
				// contabilizada

				if (ope.getTipoDeuda().equals(RentaFija.DEUDA_PUBLICO)) {
					registrarOperacion(titulosNegociadosPUBLICO, ope);
				} else if (ope.getTipoDeuda().equals(RentaFija.DEUDA_PRIVADA)) {
					registrarOperacion(titulosNegociadosPRIVADO, ope);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IRentaFija#getResumen(java.lang.String
	 * , java.lang.String, java.lang.String, int, java.lang.String)
	 */
	public RentaFijaResumen getResumen(String dia, String tipoDeuda,
			String tipoOperacion, String tipoSesion, String tipoMercado)
			throws PersistenciaException {

		if (dia == null) {
			dia = UtilFechas.getHoy("yyyy-MM-dd");
		}

		RentaFijaResumen rf = null;
		String fechaIni = null;
		String fechaFin = null;

		/*
		 * establece si es el caso defecto, el que usa el home
		 */

		boolean defaultRentaFija = UtilFechas.getHoy("yyyy-MM-dd").equals(dia)
				&& tipoDeuda.equals(RentaFija.DEUDA_ALL)
				&& tipoOperacion == RentaFija.ALL_OPE
				&& tipoSesion.equals(RentaFija.SESION_ALL)
				&& MERCADO_TODOS.equals(tipoMercado);

		ControladorCacheJCS cache = JCSFactory
				.getRegionControladorCache(JCSFactory.MEM_60SECS);
		
		String queryKey = "HOME_RENTA_FIJA."+dia;

		if (defaultRentaFija) {
			log.debug("Trying to get Home Renta Fija" );
			rf = (RentaFijaResumen) cache.getObject(queryKey);
			if (rf != null) {
				log.debug("Returning Home Renta Fija from Cache");
				return rf;
			} else {// RF default no está en cache
				synchronized (this) {
					rf = (RentaFijaResumen) cache.getObject(queryKey);
					if (rf == null) {
						log.debug("Procesando Home Renta Fija ");
						rf = new RentaFijaResumen();
						List<RentaFijaOperacion> operaciones = this.rentaFijaDao
								.getOperaciones(null, null, MERCADO_TODOS,
										ALL_OPE, MERCADO_TODOS);
						calcularResumen(rf, operaciones, tipoDeuda,
								tipoOperacion);
						cache.putObject(queryKey, rf);
						return rf;
					}
					return rf;
				}
			}
		}
		if (!UtilFechas.getHoy("yyyy-MM-dd").equals(dia)) {
			// no se consulta el día actual
			String[] fechas = UtilFechas.getFechasComparador(dia);
			fechaIni = fechas[0];
			fechaFin = fechas[1];
		}
		// No se maneja cache para los casos con filtro
		log.debug("Case : Not renta fija home");
		rf = new RentaFijaResumen();
		List<RentaFijaOperacion> operaciones = this.rentaFijaDao
				.getOperaciones(fechaIni, fechaFin, tipoSesion
						.equals(RentaFija.SESION_ALL) ? MERCADO_TODOS
						: tipoSesion, tipoOperacion, tipoMercado);
		log.debug("Renta Fija Get operations Done....next calculate resume...");
		calcularResumen(rf, operaciones, tipoDeuda, tipoOperacion);
		return rf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IRentaFija#getRentaFijaDetalleOperaciones
	 * (java.lang.String, java.lang.String, int, java.lang.String)
	 */
	public List<RentaFijaOperacion> getRentaFijaDetalleOperaciones(String dia,
			String tipoSesion, String tipoOperacion, String nemo,
			String tipoMercado) throws PersistenciaException {
		List<RentaFijaOperacion> operaciones = this.rentaFijaDao
				.getRentaFijaOperaciones(dia, IRentaFija.SESION_ALL
						.equals(tipoSesion) ? MERCADO_TODOS : tipoSesion,
						tipoMercado, tipoOperacion, nemo);
		return operaciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IRentaFija#getResumenMerge(java.lang
	 * .String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public RentaFijaResumen getResumenMerge(String fechaIni, String fechaFin,
			String tipoDeuda, String tipoOperacion, String tipoSesion,
			String tipoMercado) throws PersistenciaException {

		RentaFijaResumen rf = new RentaFijaResumen();

		List<RentaFijaOperacion> operaciones = this.rentaFijaDao
				.getOperaciones(fechaIni, fechaFin, tipoSesion
						.equals(RentaFija.SESION_ALL) ? MERCADO_TODOS
						: tipoSesion, tipoOperacion, tipoMercado);
		log.info("RENTA FIJA OPERACIONES...DONE...");
		calcularResumen(rf, operaciones, tipoDeuda, tipoOperacion);

		double totalRegistro = 0;
		double totalTransaccional = 0;

		for (int i = 0; i < RentaFija.operaciones.length; i++) {
			totalRegistro += rf.getVolumenRegistroxOPE()[i];
			totalTransaccional += rf.getVolumenTransaccionalxOPE()[i];
		}

		rf.setTotalVolumenRegistro(totalRegistro);
		rf.setTotalVolumenTransaccional(totalTransaccional);

		for (int i = 0; i < RentaFija.operaciones.length && totalRegistro > 0; i++) {

			rf.getPorcentajesRegistroxOPE()[i] = (float) ((rf
					.getVolumenRegistroxOPE()[i] / totalRegistro) * 100);

		}
		for (int i = 0; i < RentaFija.operaciones.length
				&& totalTransaccional > 0; i++) {

			rf.getPorcentajesTransaccionalxOPE()[i] = (float) ((rf
					.getVolumenTransaccionalxOPE()[i] / totalTransaccional) * 100);
		}

		// Hace falta calcular las variaciones con respecto al dia anterior para
		// los titulos extraidos

		ArrayList<RentaFijaResumenTipoTitulo> deudasPublicas = (ArrayList<RentaFijaResumenTipoTitulo>) rf
				.getTitulosDeudaPublica();

		Iterator it = deudasPublicas.iterator();
		RentaFijaResumenTipoTitulo rfrt = new RentaFijaResumenTipoTitulo();
		ArrayList<RentaFijaResumenEspecie> listadoDeudaPublica = new ArrayList<RentaFijaResumenEspecie>();

		while (it.hasNext()) {
			rfrt = (RentaFijaResumenTipoTitulo) it.next();
			SortedMap<String, RentaFijaResumenEspecie> rfrtEspecies = rfrt
					.getEspecies();

			for (RentaFijaResumenEspecie rfv : rfrtEspecies.values()) {
				listadoDeudaPublica.add(rfv);
			}
		}

		rf.setListadoTitulosDeudaPublica(listadoDeudaPublica);

		ArrayList<RentaFijaResumenTipoTitulo> deudasPrivadas = (ArrayList<RentaFijaResumenTipoTitulo>) rf
				.getTitulosDeudaPrivada();

		it = deudasPrivadas.iterator();
		rfrt = new RentaFijaResumenTipoTitulo();
		ArrayList<RentaFijaResumenEspecie> listadoDeudaPrivada = new ArrayList<RentaFijaResumenEspecie>();

		while (it.hasNext()) {
			rfrt = (RentaFijaResumenTipoTitulo) it.next();
			SortedMap<String, RentaFijaResumenEspecie> rfrtEspecies = rfrt
					.getEspecies();

			for (RentaFijaResumenEspecie rfv : rfrtEspecies.values()) {
				listadoDeudaPrivada.add(rfv);
			}
		}

		rf.setListadoTitulosDeudaPrivada(listadoDeudaPrivada);

		return rf;
	}

	/**
	 * Gets the fecha anterior.
	 * 
	 * @param fechaIni
	 *            the fecha ini
	 * 
	 * @return the fecha anterior
	 */
	@SuppressWarnings("unused")
	private String getFechaAnterior(String fechaIni) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date _dateFin = null;
		try {
			_dateFin = df.parse(fechaIni);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String fechaFin = "";

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(_dateFin);

		gc.set(Calendar.DAY_OF_MONTH, gc.get(Calendar.DAY_OF_MONTH) - 1);
		_dateFin = gc.getTime();

		fechaFin = df.format(_dateFin);
		fechaFin += ":00:00:00";
		return fechaFin;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IRentaFija#getResumenEmisor(java.
	 * lang.String)
	 */
	public EmisorTitulo getResumenEmisor(String nemo)
			throws PersistenciaException {

		EmisorTitulo re = new EmisorTitulo();
		re = this.rentaFijaDao.getEmisor(nemo);
		// this.emisorFinal = re;
		return re;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IRentaFija#getResumenTitulo(java.
	 * lang.String)
	 */
	public RentaFijaResumenTitulo getResumenTitulo(String nemo)
			throws PersistenciaException {
		RentaFijaResumenTitulo rt = new RentaFijaResumenTitulo();
		rt = rentaFijaDao.getRentaFijaTitulo(nemo);
		return rt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.servicio.IRentaFija#getSumaOperacionesGrafica
	 * (java.lang.String, java.lang.String, java.lang.String, int,
	 * java.lang.String)
	 */
	public List<RentaFijaSumaOperacion> getSumaOperacionesGrafica(String dia1,
			String dia2, String nemo)
			throws PersistenciaException {
		EmisorTitulo emi = getResumenEmisor(nemo);
		List<RentaFijaSumaOperacion> ret = rentaFijaDao.getDetalleExcel(dia1, dia2, nemo);
		
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
	 * co.com.bvc.portal.mercados.servicio.IRentaFija#getRentaFijaAutocomplete()
	 */
	public List<RentaFijaAutocomplete> getRentaFijaAutocomplete()
			throws PersistenciaException {
		List<RentaFijaAutocomplete> res = this.rentaFijaDao
				.getDatosAutocomplete();
		return res;
	}

	private static int getIndiceTipoOperacion(String tipoO){
		for (int i = 0; i<IRentaFija.operaciones.length; i++) {
			String tipoOp = IRentaFija.operaciones[i];
			if (tipoOp.equals(tipoO)){
				return i;
			}
		}
		return 0;
	}
	
}