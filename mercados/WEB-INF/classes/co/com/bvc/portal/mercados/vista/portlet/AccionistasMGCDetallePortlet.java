package co.com.bvc.portal.mercados.vista.portlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.AccionesMGCBusquedaForm;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IAccionesMGC;

// TODO: Auto-generated Javadoc
/**
 * The Class AccionistasDetallePortlet.
 */
@Controller
@RequestMapping("VIEW")
public class AccionistasMGCDetallePortlet {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The acciones servicio. */
	private IAccionesMGC accionesMGCServicio;

	/** The mercado dao. */
	private IMercadoDao mercadoDao;

	/** The sdf. */
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Ir al detalle.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping(params = "action=buscar")
	public String irAlDetalle(Model model) {
		log.info("render phase irAlDetalle");
		cargaDatosBasicos(model);
		return "accionMGCBVCDetalle";
	}

	/**
	 * Cargar resumen.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	// default render ()
	public String cargarResumen(Model model) {
		log.info("render phase, cargarResumen, acción por defecto");
		model.addAttribute("tipoMercado", IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC);
		cargaTitulo(new AccionesMGCBusquedaForm(), model);
		cargaDatosBasicos(model);;
		return "accionMGCBVCDetalle";
	}

	// llegada desde el buscador
	/**
	 * Cargar resumen filtros.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param form the form
	 * @param result the result
	 * @param model the model
	 */
	@RequestMapping(params = "action=buscar")
	public void cargarResumenFiltros(ActionRequest request,
			ActionResponse response,
			@ModelAttribute("formulario") AccionesMGCBusquedaForm form,
			BindingResult result, Model model) {
		log.info("action phase, cargarResumenFiltros");
		model.addAttribute("tipoMercado", form.getTipoMercado());
		this.cargaTitulo(form, model);
		response.setRenderParameter("action", "buscar");
		return;
	}

	/**
	 * Carga titulo.
	 * 
	 * @param form the form
	 * @param model the model
	 */
	private void cargaTitulo(AccionesMGCBusquedaForm form, Model model) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			// obtiene las operaciones del tipo de mercado seleccionado, no pide
			// delay
			List<OperacionDiaAcciones> listOp = this.accionesMGCServicio
					.operacionesDia("BVC", form.getFechaRequerida(), form
							.getTipoMercado(), false);
			// obtiene los datos del titulo sin delay, posiblemente a partir de
			// las operaciones cargadas
			TituloAccion titulo = this.accionesMGCServicio.cargarDatosTituloDia(
					"BVC", form.getFechaRequerida(), form.getTipoMercado(),
					listOp, false);
			model.addAttribute("titulo", titulo);
			model.addAttribute("listaOperaciones", listOp);
			model.addAttribute("horaTransaccion", df.format(titulo.getHora()
					.getTime()));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carga los datos basicos y necesarios en todas las pantallas del portlet.
	 * Carga: nemotecnico, Años, meses, dias, mensaje de estado del mercado
	 * 
	 * @param model the model
	 */
	private void cargaDatosBasicos(Model model) {
		AccionesMGCBusquedaForm form = null;
		LinkedHashMap<String, String> dias = UtilFechas.getDias();
		LinkedHashMap<String, String> meses = UtilFechas.getMeses();
		LinkedHashMap<String, String> anios = UtilFechas.getAnios();
		model.addAttribute("dias", dias);
		model.addAttribute("meses", meses);
		model.addAttribute("anios", anios);
		model.addAttribute("nemoTecnico", "BVC");
		model.addAttribute("resumen", false);
		if (!model.containsAttribute("formulario")) {
			form = new AccionesMGCBusquedaForm();
			model.addAttribute("formulario", form);
		} else {
			form = (AccionesMGCBusquedaForm) model.asMap().get("formulario");
		}

		try {
			int tipoMercado = form.getTipoMercado() == IAccionesMGC.TIPO_MERCADO_REPOS_MGC ? IMercadoDao.REPOS
					: IMercadoDao.ACCIONES;
			GregorianCalendar cal = new GregorianCalendar();
			if (StringUtils.isNotEmpty(form.getFechaRequerida())
					&& !form.getFechaRequerida().equals(sdf.format(new Date()))) {
				cal.setTime(sdf.parse(form.getFechaRequerida()));
			}
			String mensajeHorario = this.mercadoDao.mercadoAbierto(cal,
					tipoMercado, false);
			model.addAttribute("horarioAcciones", mensajeHorario);
		} catch (Exception e) {

		}
	}

	/**
	 * Gets the acciones servicio.
	 * 
	 * @return the acciones servicio
	 */
	public IAccionesMGC getAccionesMGCServicio() {
		return accionesMGCServicio;
	}

	/**
	 * Sets the acciones servicio.
	 * 
	 * @param accionesServicio the new acciones servicio
	 */
	public void setAccionesMGCServicio(IAccionesMGC accionesMGCServicio) {
		this.accionesMGCServicio = accionesMGCServicio;
	}

	/**
	 * Gets the mercado dao.
	 * 
	 * @return the mercado dao
	 */
	public IMercadoDao getMercadoDao() {
		return mercadoDao;
	}

	/**
	 * Sets the mercado dao.
	 * 
	 * @param mercadoDao the new mercado dao
	 */
	public void setMercadoDao(IMercadoDao mercadoDao) {
		this.mercadoDao = mercadoDao;
	}
}
