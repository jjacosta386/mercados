package co.com.bvc.portal.mercados.vista.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;
import co.com.bvc.portal.mercados.util.IConstantesJSP;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.vista.form.FormularioDivisas;

/**
 * The Class DivisasPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class DivisasPortlet {

	/** The log. */
	private Logger log = Logger.getLogger(DivisasPortlet.class);

	/** The formulario divisas. */
	private FormularioDivisas formularioDivisas;

	/**
	 * fase de pintado por defecto sale hacia el detalle de las divisas.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	// (action=default)
	public String mostrarFormulario(Model model) {
		
		Long t1 = System.currentTimeMillis();
		log.info("render phase, action default");
		model.addAttribute(IConstantesAtributosModelo.LISTA_DIAS, UtilFechas.getDias());
		model.addAttribute(IConstantesAtributosModelo.LISTA_MESES, UtilFechas.getMeses());
		model.addAttribute(IConstantesAtributosModelo.LISTA_ANIOS, UtilFechas.getAnios());
		model.addAttribute(IConstantesAtributosModelo.FECHA, UtilFechas.getHoy("yyyy-MM-dd"));
		if (!model.containsAttribute(IConstantesAtributosModelo.FORMULARIO)) {
			model.addAttribute(IConstantesAtributosModelo.FORMULARIO, new FormularioDivisas());
		}
		if (!model.containsAttribute(IConstantesAtributosModelo.RESUMEN_MERCADO)) {
			formularioDivisas.cargarResumenesHoy(model);
		}
		
		Long t2 = System.currentTimeMillis();
		Long t = (t2 -t1)/1000;
		log.info("El tiempo en el método mostrarFormulario de DivisasPortlet es ="+t);
		return IConstantesJSP.DETALLE_DIVISA;
	}

	/**
	 * Detallar divisa.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param form the form
	 * @param result the result
	 * @param model the model
	 */
	@RequestMapping(params = "action=buscar")
	public void detallarDivisa(ActionRequest request, ActionResponse response, @ModelAttribute(IConstantesAtributosModelo.FORMULARIO) FormularioDivisas form, BindingResult result, Model model) {
		
		Long t1 = System.currentTimeMillis();
		log.info("action phase, action buscar, detallarDivisa");
		formularioDivisas.cargarResumenesDia(form.getTimeRequerido(), model);
		
		Long t2 = System.currentTimeMillis();
		Long t = (t2 -t1)/1000;
		log.info("El tiempo en el método detallarDivisa de DivisasPortlet es ="+t);
		response.setRenderParameter("action", "default");
	}

	/**
	 * Defecto.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param form the form
	 * @param result the result
	 * @param model the model
	 */
	@RequestMapping(params = "action=defecto")
	public void defecto(ActionRequest request, ActionResponse response, @ModelAttribute(IConstantesAtributosModelo.FORMULARIO) FormularioDivisas form, BindingResult result, Model model) {
		
		Long t1 = System.currentTimeMillis();
		log.info("action phase, action defecto, defecto");
		
		Long t2 = System.currentTimeMillis();
		Long t = (t2 -t1)/1000;
		log.info("El tiempo en el método defecto de DivisasPortlet es ="+t);
		response.setRenderParameter("action", "default");
	}

	/**
	 * Gets the formulario divisas.
	 * 
	 * @return the formulario divisas
	 */
	public FormularioDivisas getFormularioDivisas() {
		return formularioDivisas;
	}

	/**
	 * Sets the formulario divisas.
	 * 
	 * @param formularioDivisas the new formulario divisas
	 */
	public void setFormularioDivisas(FormularioDivisas formularioDivisas) {
		this.formularioDivisas = formularioDivisas;
	}

}
