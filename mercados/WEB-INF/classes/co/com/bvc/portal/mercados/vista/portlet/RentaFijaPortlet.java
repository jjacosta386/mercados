package co.com.bvc.portal.mercados.vista.portlet;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaBusqueda;
import co.com.bvc.portal.mercados.modelo.RentaFijaOperacion;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumen;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IRentaFija;
import co.com.bvc.portal.mercados.servicio.IServicioMercadosUtil;
import co.com.bvc.portal.mercados.servicio.imp.RentaFija;

/**
 * The Class RentaFijaPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class RentaFijaPortlet {

	/** The log. */
	private static Logger log = Logger.getLogger(RentaFijaPortlet.class);

	/** The rentafija. */
	private IRentaFija rentafija;

	/** The mercado dao. */
	// private IMercadoDao mercadoDao;
	private IServicioMercadosUtil servicioMercadosUtil;

	/** The renta fija busqueda. */
	private RentaFijaBusqueda rentaFijaBusqueda = new RentaFijaBusqueda();

	/**
	 * Cargar resumen. Entrada por defecto, carga la página del resumen por
	 * defecto
	 * 
	 * @param model
	 *            the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	public String cargarResumen(Model model) {
		Long t1 = System.currentTimeMillis();
		try {
			// caso por defecto, consulta sin filtros
			RentaFijaResumen resumenRF = rentafija.getResumen(UtilFechas
					.getHoy(DATE_PATTERN), IRentaFija.DEUDA_ALL,
					IRentaFija.ALL_OPE, IRentaFija.SESION_ALL,
					IRentaFija.MERCADO_TODOS);
			rentaFijaBusqueda = new RentaFijaBusqueda();
			String _hoy = UtilFechas.getHoy(DATE_PATTERN);
			// determina si el mercado está abierto cerrado o es día no bursátil
			String estadoMercado = servicioMercadosUtil
					.getMensajeMercadoAbierto(new GregorianCalendar(),
							IMercadoDao.RENTA_FIJA);
			model.addAttribute("estadoMercado", estadoMercado);
			log.debug(estadoMercado);
			// se cargan los datos del buscador
			String date_atoms[] = _hoy.split("-");
			rentaFijaBusqueda.setAnioFecha(date_atoms[0]);
			rentaFijaBusqueda.setMesFecha(date_atoms[1]);
			rentaFijaBusqueda.setDiaFecha(date_atoms[2]);
			RentaFijaResumen resumenRFTotales = resumenRF;
			model.addAttribute("resumenRFTotales", resumenRFTotales);
			model.addAttribute("resumenRF", resumenRF);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log
				.debug("El tiempo en el método cargarResumen de RentaFijaPortlet es ="
						+ t);
		cargarDatosComunes(model);
		return "rentaFija";
	}

	/**
	 * Cargar resumen filtros. Entrada desde el buscador
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param form
	 *            the form
	 * @param result
	 *            the result
	 * @param model
	 *            the model
	 */
	@RequestMapping(params = "action=buscar")
	public void cargarResumenFiltros(ActionRequest request,
			ActionResponse response,
			@ModelAttribute("formulario") RentaFijaBusqueda form,
			BindingResult result, Model model) {
		Long t1 = System.currentTimeMillis();
		String fechaIni = form.getAnioFecha() + "-" + form.getMesFecha() + "-"
				+ form.getDiaFecha();
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		if (!form.getNemo().equals("")) {
			/*
			 * caso en el que se quiere consultar un nemo, usando el buscador
			 */
			log.info("Enviando nemo:" + form.getNemo());
			detalleBusqueda(request, response, form, model);
			response.setRenderParameter("action", "detalleView");
			return;
		}
		RentaFijaResumen resumenRF = new RentaFijaResumen();
		try {
			if (!UtilFechas.getHoy(DATE_PATTERN).equals(fechaIni)) {
				gc.setTime(sdf.parse(fechaIni));
			}
			String estadoMercado = servicioMercadosUtil
					.getMensajeMercadoAbierto(gc, IMercadoDao.RENTA_FIJA);
			resumenRF = rentafija.getResumen(fechaIni, form.getTipoDeuda(),
					form.getTipoOperacion(), form
							.getTipoSesion(), form.getTipoMercado());
			RentaFijaResumen resumenRFTotales = rentafija.getResumen(fechaIni,
					RentaFija.DEUDA_ALL, RentaFija.ALL_OPE,
					RentaFija.SESION_ALL, IRentaFija.MERCADO_TODOS);
			rentaFijaBusqueda = form;
			model.addAttribute("estadoMercado", estadoMercado);
			model.addAttribute("resumenRFTotales", resumenRFTotales);
			model.addAttribute("resumenRF", resumenRF);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log
				.info("El tiempo en el método cargarResumenFiltros de RentaFijaPortlet es ="
						+ t);
		response.setRenderParameter("action", "buscar");
	}

	/**
	 * Cargar detalle. Entrada cuando se hace click en el nombre de un titulo
	 * estando en el resumen de renta fija o en el home
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param model
	 *            the model
	 * @param nemos
	 *            the nemos
	 */
	@RequestMapping(params = "action=detalle")
	public void cargarDetalle(ActionRequest request, ActionResponse response,
			Model model, @RequestParam("nemo") String nemos) {
		Long t1 = System.currentTimeMillis();
		rentaFijaBusqueda.setNemo(nemos);
		detalleBusqueda(request, response, rentaFijaBusqueda, model);
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método "
				+ "cargarDetalle de RentaFijaPortlet es =" + t);
		response.setRenderParameter("action", "detalleView");
	}

	/**
	 * Ver detalle renta fija dummy. Entrada desde el ticker
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param model
	 *            the model
	 * @param nemos
	 *            the nemos
	 */
	@RequestMapping(params = "action=detalleDummy")
	// action phase
	public void verDetalleRentaFijaDummy(ActionRequest request,
			ActionResponse response, Model model,
			@RequestParam("nemo") String nemos) {

		Long t1 = System.currentTimeMillis();
		RentaFijaBusqueda rentaFijaBusqueda = new RentaFijaBusqueda();
		rentaFijaBusqueda.setNemo(nemos);
		String[] hoyS = UtilFechas.getHoy(DATE_PATTERN).split("-");
		rentaFijaBusqueda.setAnioFecha(hoyS[0]);
		rentaFijaBusqueda.setMesFecha(hoyS[1]);
		rentaFijaBusqueda.setDiaFecha(hoyS[2]);
		detalleBusqueda(request, response, rentaFijaBusqueda, model);
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.info("El tiempo en el método "
				+ "cargarDetalleDummy de RentaFijaPortlet es =" + t);
		response.setRenderParameter("action", "detalleView");
	}

	/**
	 * Detalle busqueda. Carga el detalle de un titulo
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param form
	 *            the form
	 * @param result
	 *            the result
	 * @param model
	 *            the model
	 */
	private void detalleBusqueda(ActionRequest request,
			ActionResponse response, RentaFijaBusqueda form, Model model) {

		List<RentaFijaOperacion> operacionesRF = null;
		EmisorTitulo emisorRF = null;
		RentaFijaSumaOperacion sumaRF = null;
		RentaFijaResumenTitulo tituloRF = null;

		String fecha = form.getAnioFecha() + "-" + form.getMesFecha() + "-"
				+ form.getDiaFecha();

		String[] fechasComp = UtilFechas.getFechasComparador(fecha);
		
		String nemo = form.getNemo();
		String sesion = form.getTipoSesion();
		String operacion = form.getTipoOperacion();

		try {
			operacionesRF = rentafija.getRentaFijaDetalleOperaciones(fecha,
					sesion, operacion, nemo, form
							.getTipoMercado());
			emisorRF = rentafija.getResumenEmisor(nemo);
			List<RentaFijaSumaOperacion> op = rentafija.getSumaOperacionesGrafica(fechasComp[0], fechasComp[1], nemo);
			if (op != null && !op.isEmpty()){
				sumaRF = op.get(0);
			}else{
				sumaRF = new RentaFijaSumaOperacion();
				sumaRF.setEmisor(emisorRF.getSigla());
				sumaRF.setNemo(nemo);
			}
			tituloRF = rentafija.getResumenTitulo(nemo);
			model.addAttribute("operacionesRF", operacionesRF);
			model.addAttribute("emisorRF", emisorRF);
			model.addAttribute("sumaRF", sumaRF);
			model.addAttribute("tituloRF", tituloRF);
			rentaFijaBusqueda = form;
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Detalle. Sale hacia la pantalla de detalle
	 * 
	 * @param model
	 *            the model
	 * 
	 * @return the string
	 */
	@RequestMapping(params = "action=detalleView")
	public String detalle(Model model) {
		cargarDatosComunes(model);
		return "rentaFijaDetalle";
	}

	/**
	 * Buscar. Carga los datos comunes del buscador y sale hacia la pantalla de
	 * resumen.
	 * 
	 * @param model
	 *            the model
	 * 
	 * @return the string
	 */
	@RequestMapping(params = "action=buscar")
	public String buscar(Model model) {
		cargarDatosComunes(model);
		return "rentaFija";
	}

	public IServicioMercadosUtil getServicioMercadosUtil() {
		return servicioMercadosUtil;
	}

	public void setServicioMercadosUtil(
			IServicioMercadosUtil servicioMercadosUtil) {
		this.servicioMercadosUtil = servicioMercadosUtil;
	}

	/**
	 * Gets the rentafija.
	 * 
	 * @return the rentafija
	 */
	public IRentaFija getRentafija() {
		return rentafija;
	}

	/**
	 * Sets the rentafija.
	 * 
	 * @param _rentafija
	 *            the new rentafija
	 */
	public void setRentafija(IRentaFija _rentafija) {
		this.rentafija = _rentafija;
	}

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * carga los datos que son comunes a las pantallas de resumen y detalle.
	 * 
	 * Carga la lista de dias, meses y años, ademas del formulario.
	 * 
	 * @param model
	 *            Objeto de comunicación con la jsp
	 */
	private void cargarDatosComunes(Model model) {
		model.addAttribute("dias", UtilFechas.getDias());
		model.addAttribute("meses", UtilFechas.getMeses());
		model.addAttribute("anios", UtilFechas.getAnios());
		model.addAttribute("RentaFijaBusqueda", rentaFijaBusqueda);
	}
}
