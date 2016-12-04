package co.com.bvc.portal.mercados.vista.portlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.InfoIndicesSectoriales;
import co.com.bvc.portal.mercados.modelo.ResumenIndice;
import co.com.bvc.portal.mercados.servicio.IIndices;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;
import co.com.bvc.portal.mercados.util.IConstantesJSP;
import co.com.bvc.portal.mercados.vista.form.FormularioIndices;

/**
 * @author BVC
 * @descriptionThe Class IndicesPortlet. Portlet para el manejo de los indices
 */

@Controller
@RequestMapping("VIEW")
public class IndicesPortlet {

	/** The log. */
	private Logger log = Logger.getLogger(IndicesPortlet.class);

	/** The indices. Servicio de manejo de indices */
	private IIndices indices;

	/**
	 * fase de pintado, obtiene el resumen de los indices y sale hacia la
	 * pantalla correspondiente.
	 * 
	 * @param model
	 *            Objeto de comunicación con la jsp
	 * 
	 * @return la jsp de salida
	 * 
	 * @see IConstantesJSP.RESUMEN_INDICE
	 */
	@RequestMapping
	// (params = "action=defecto")
	public String mostrarResumen(Model model) {
		Long t1 = System.currentTimeMillis();
		log.debug("Render phase, accion default");
		FormularioIndices form = new FormularioIndices();
		
		if (model.containsAttribute(IConstantesAtributosModelo.FORMULARIO)) {
			form = (FormularioIndices) model.asMap().get(
					IConstantesAtributosModelo.FORMULARIO);
			model.addAttribute(IConstantesAtributosModelo.MENSAJE_MERCADO,
					indices.getMensajeMercadoAbierto(form));
			model.addAttribute(IConstantesAtributosModelo.FECHA_BUSCAR,
					form.getTimeRequerido());
		} else {
			model.addAttribute(IConstantesAtributosModelo.MENSAJE_MERCADO,
					indices.getMensajeMercadoAbierto(null));
		}
		List<List<ResumenIndice>> listas = indices.obtenerResumenIndices(form);
		List<ResumenIndice> listasRF = indices.obtenerResumenIndicesRF(form);
		List<ResumenIndice> listasMM = indices.obtenerResumenIndicesMM(form);
		List<ResumenIndice> listasIN = new ArrayList<ResumenIndice>();
		listasIN.addAll(listas.get(0));
		//listasIN.addAll(listas.get(1));
		listasIN.addAll(listasRF);
		listasIN.addAll(listasMM);
		model.addAttribute(
				IConstantesAtributosModelo.LISTA_INDICES_PRINCIPALES,
				listasIN);
		/*model.addAttribute(
				IConstantesAtributosModelo.LISTA_INDICES_PRINCIPALES,
				listas.get(0));
		model.addAttribute(
				IConstantesAtributosModelo.LISTA_INDICES_SECCIONALES,
				listas.get(1));
		model.addAttribute(IConstantesAtributosModelo.LISTA_INDICES_RENTAFIJA,
				listasRF);
		model.addAttribute(IConstantesAtributosModelo.LISTA_INDICES_MONETARIO,
				listasMM);*/
		model.addAttribute(IConstantesAtributosModelo.INDICE_ACT, "all");
		model.addAttribute(IConstantesAtributosModelo.FORMULARIO, form);
		setearFormulario(model);

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método mostrarResumen de IndicesPortlet es ="
				+ t);

		return IConstantesJSP.RESUMEN_INDICE;
	}

	/**
	 * Fase de pintado, carga los datos del formulario de busqueda y sale hacia
	 * la pantalla del detalle de los indices.
	 * 
	 * @param model
	 *            Objeto de comunicación con la jsp
	 * 
	 * @return el nombre de la jsp del detalle
	 * 
	 * @see IConstantesJSP.DETALLE_INDICE
	 */
	@RequestMapping(params = "action=detallar")
	public String mostrarDetalle(Model model) {

		Long t1 = System.currentTimeMillis();
		log.debug("Render phase, accion default");
		setearFormulario(model);

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método mostrarDetalle de IndicesPortlet es ="
				+ t);

		return IConstantesJSP.DETALLE_INDICE;
	}
	
	@RequestMapping(params = "action=mercado")
	public String mostrarMercado(Model model) {

		log.debug("Render phase, accion default");
		FormularioIndices form = new FormularioIndices();
		model.addAttribute(IConstantesAtributosModelo.INDICE_ACT, "all");
		model.addAttribute(IConstantesAtributosModelo.FORMULARIO, form);
		setearFormulario(model);

		/*Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método mostrarResumen de IndicesPortlet es ="
				+ t);*/

		return IConstantesJSP.RESUMEN_INDICE;
	}

	/**
	 * Fase de pintado, a partir de los parametros, carga el contenido estático
	 * (formula, descripción, etc) del indice. Y sale hacia la página de
	 * contenidos.
	 * 
	 * @param codIndice
	 *            código interno del indice
	 * @param fecha
	 *            fecha consultada (yyyyMMdd)
	 * @param tipoContenido
	 *            tipo de contenido: generalidades, formula, seleccionCanasta,
	 *            calculoPonderados
	 * @param model
	 *            Objeto de comunicación con la JSP
	 * 
	 * @return el nombre de la JSP de contenidos
	 */
	@RequestMapping(params = "action=contenido")
	public String mostrarContenido(@RequestParam("codIndice") String codIndice,
			@RequestParam("fecha") String fecha,
			@RequestParam("tipoContenido") String tipoContenido, Model model) {
		Long t1 = System.currentTimeMillis();
		log.debug("render phase, action contenido, tipo contenido: "
				+ tipoContenido);
		FormularioIndices form = new FormularioIndices();
		form.setCodigoIndice(codIndice);
		form.setTimeRequerido(fecha);
		setearFormulario(model);
		model.addAttribute(IConstantesAtributosModelo.CONTENIDO,
				indices.obtenerContenidoIndice(codIndice, tipoContenido));
		model.addAttribute(IConstantesAtributosModelo.FORMULARIO, form);
		model.addAttribute(IConstantesAtributosModelo.INDICE_ACT, codIndice);
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método mostrarContenido de IndicesPortlet es ="
				+ t);
		return IConstantesJSP.CONTENIDOS_INDICE;
	}

	/**
	 * Fase de acción, Obtiene toda la información necesaria para detallar el
	 * indice dado. (Los paths de las canastas, su nombre externo, sus valores
	 * actuales, etc). Si no se selecciono un indice, Sale hacia el resumen.
	 * Luego llama a la fase de pintado.
	 * 
	 * @param request
	 *            La petición del cliente
	 * @param response
	 *            La salida hacia el cliente
	 * @param form
	 *            Formulario con los filtros ingresados por el usuario
	 *            (co.com.bvc.portal.mercados.vista.form.FormularioIndices)
	 * @param result
	 *            the result
	 * @param model
	 *            Objeto de comunicación con la jsp
	 * 
	 * @See 
	 *      co.com.bvc.portal.mercados.vista.portlet.IndicesPortlet.mostrarDetalle
	 * @see co.com.bvc.portal.mercados.vista.portlet.IndicesPortlet.mostrarResumen
	 *      (Model model)
	 */
	@RequestMapping(params = "action=buscar")
	public void detallarIndice(
			ActionRequest request,
			ActionResponse response,
			@ModelAttribute(IConstantesAtributosModelo.FORMULARIO) FormularioIndices form,
			BindingResult result, Model model) {
		Long t1 = System.currentTimeMillis();
		log.debug("action phase, action buscar, detallar Indice");
		if ("all".equals(form.getCodigoIndice()) && "all".equals(form.getMercadoIn())) {
			response.setRenderParameter("action", "defecto");
			return;
		}
		InfoIndicesSectoriales inf = indices.obtenerIndiceMetadata(form);
		model.addAttribute(IConstantesAtributosModelo.INDICE_ACT,
				form.getCodigoIndice());
		model.addAttribute(IConstantesAtributosModelo.INDICE_MER,
				form.getMercadoIn());
		model.addAttribute(IConstantesAtributosModelo.MENSAJE_MERCADO,
				indices.getMensajeMercadoAbierto(form));
		if(!"all".equals(form.getCodigoIndice())){
		 if("RENTA VARIABLE".equals(form.getMercadoIn())){	
			model.addAttribute(IConstantesAtributosModelo.RESUMEN,
					indices.obtenerResumenIndice(form));
		 }
		 if("RENTA FIJA".equals(form.getMercadoIn())){
			 model.addAttribute(IConstantesAtributosModelo.RESUMEN_RF, indices
					 .obtenerResumenIndicesBucarRF(form));
		 }
		 if("MERCADO MONETARIO".equals(form.getMercadoIn())){
			 model.addAttribute(IConstantesAtributosModelo.RESUMEN_MM, indices
					 .obtenerResumenIndicesBuscarMM(form));
		 }
		}
		else if ("all".equals(form.getCodigoIndice()) && "RENTA VARIABLE".equals(form.getMercadoIn())){
			List<List<ResumenIndice>> listas = indices.obtenerResumenIndices(form);
			model.addAttribute(IConstantesAtributosModelo.LISTA_INDICES_PRINCIPALES,
					listas.get(0));
			response.setRenderParameter("action", "mercado");
			return;
		}
		else if ("all".equals(form.getCodigoIndice()) && "RENTA FIJA".equals(form.getMercadoIn())){
			List<ResumenIndice> listasRF = indices.obtenerResumenIndicesRF(form);
			model.addAttribute(IConstantesAtributosModelo.LISTA_INDICES_PRINCIPALES,
					listasRF);
			response.setRenderParameter("action", "mercado");
			return;
		}
		else if ("all".equals(form.getCodigoIndice()) && "MERCADO MONETARIO".equals(form.getMercadoIn())){
			List<ResumenIndice> listasMM = indices.obtenerResumenIndicesMM(form);
			model.addAttribute(IConstantesAtributosModelo.LISTA_INDICES_PRINCIPALES,
					listasMM);
			response.setRenderParameter("action", "mercado");
			return;
		}
		model.addAttribute(IConstantesAtributosModelo.METADATA, inf);
		model.addAttribute(IConstantesAtributosModelo.MOSTRAR_RESUMEN, true);
        
		
		
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método detallarIndice de IndicesPortlet es ="
				+ t);
		response.setRenderParameter("action", "detallar");
	}

	/**
	 * Gets the indices.
	 * 
	 * @return the indices
	 */
	public IIndices getIndices() {
		return indices;
	}

	/**
	 * Sets the indices.
	 * 
	 * @param indices
	 *            the new indices
	 */
	public void setIndices(IIndices indices) {
		this.indices = indices;
	}

	/**
	 * 
	 * 
	 * @param model
	 *            the new ear formulario
	 */
	private void setearFormulario(Model model) {

		Long t1 = System.currentTimeMillis();
		List<ResumenIndice> indMerc = indices.obtenerIndicesMercado();
		model.addAttribute(IConstantesAtributosModelo.LISTA_DIAS,
				UtilFechas.getDias());
		model.addAttribute(IConstantesAtributosModelo.LISTA_MESES,
				UtilFechas.getMeses());
		model.addAttribute(IConstantesAtributosModelo.LISTA_ANIOS,
				UtilFechas.getAnios());
		model.addAttribute(IConstantesAtributosModelo.LISTA_INDICES,
				indices.obtenerPosiblesIndices());
		model.addAttribute(IConstantesAtributosModelo.LISTA_INDICES_MERCADO,
				indMerc);
		if (!model
				.containsAttribute(IConstantesAtributosModelo.MOSTRAR_RESUMEN)) {
			model.addAttribute(IConstantesAtributosModelo.MOSTRAR_RESUMEN,
					false);
		}

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método setearFormulario de IndicesPortlet es ="
				+ t);
	}

	/**
	 * Link detallar indice.
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
	@RequestMapping(params = "action=link")
	public void linkDetallarIndice(ActionRequest request,
			ActionResponse response, Model model,
			@RequestParam("nemo") String nemos) {
		Long t1 = System.currentTimeMillis();
		FormularioIndices form = new FormularioIndices();

		Date fecha = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String _hoy = df.format(fecha);

		String anio = _hoy.substring(0, 4);
		String mes = _hoy.substring(5, 7);
		String dia = _hoy.substring(8, 10);

		form.setAnio(anio);
		form.setDia(dia);
		form.setMes(mes);
		form.setCodigoIndice(nemos);

		InfoIndicesSectoriales inf = indices.obtenerIndiceMetadata(form);
		model.addAttribute(IConstantesAtributosModelo.INDICE_ACT,
				form.getCodigoIndice());
		model.addAttribute(IConstantesAtributosModelo.MENSAJE_MERCADO,
				indices.getMensajeMercadoAbierto(form));
		model.addAttribute(IConstantesAtributosModelo.RESUMEN,
				indices.obtenerResumenIndice(form));
		model.addAttribute(IConstantesAtributosModelo.RESUMEN_RF, indices
				.obtenerResumenIndicesRF(form).get(0));
		model.addAttribute(IConstantesAtributosModelo.METADATA, inf);
		model.addAttribute(IConstantesAtributosModelo.MOSTRAR_RESUMEN, true);

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método linkDetallarIndice de IndicesPortlet es = "
				+ t);
		response.setRenderParameter("action", "detallar");
	}

}
/*************************************************End of IndicesPortlet.java**********************************/