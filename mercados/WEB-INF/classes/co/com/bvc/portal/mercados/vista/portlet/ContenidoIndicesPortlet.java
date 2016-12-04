package co.com.bvc.portal.mercados.vista.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.bvc.portal.mercados.modelo.InfoIndicesSectoriales;
import co.com.bvc.portal.mercados.servicio.IIndices;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;
import co.com.bvc.portal.mercados.util.IConstantesJSP;

// TODO: Auto-generated Javadoc
/**
 * The Class ContenidoIndicesPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class ContenidoIndicesPortlet {

	/** The log. */
	Logger log = Logger.getLogger(ContenidoIndicesPortlet.class);

	/** The indices. */
	private IIndices indices;

	/**
	 * Mostrar formulario.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	// (action=default)
	public String mostrarFormulario(Model model) {
		log.info("Render phase, accion default, mostrarFormulario");
		model.addAttribute(IConstantesAtributosModelo.LISTA_INDICES, indices.obtenerPosiblesIndices());
		if (!model.containsAttribute("mensaje")){
			model.addAttribute("mensaje", "");
		}
		return IConstantesJSP.EDITAR_CONTENIDO_INDICE;
	}

	/**
	 * Detallar indice.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param indMetadata the ind metadata
	 * @param result the result
	 * @param model the model
	 */
	@RequestMapping(params = "action=editar")
	public void DetallarIndice(ActionRequest request, ActionResponse response, @ModelAttribute(IConstantesAtributosModelo.METADATA) InfoIndicesSectoriales indMetadata, BindingResult result,
			Model model) {
		log.info("action phase, action listar, listarEmisores");
		if (indMetadata != null && indMetadata.getIndice() != null && indMetadata.getIndice().trim().length() > 0 && !indMetadata.equals("%")){
			indices.salvarMetadataIndice(indMetadata);
			model.addAttribute("mensaje", "La información ha sido salvada con exito");
		}else{
			model.addAttribute("mensaje", "Debe seleccionar un indice");
		}
		response.setRenderParameter("action", "default");
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
	 * @param indices the new indices
	 */
	public void setIndices(IIndices indices) {
		this.indices = indices;
	}

}
