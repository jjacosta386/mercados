package co.com.bvc.portal.mercados.vista.portlet.busquedas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
// TODO: Auto-generated Javadoc
/**
 * The Class BusquedaAvanzadaIndices.
 */
@Controller
@RequestMapping("VIEW")
public class BusquedaAvanzadaIndices {
	
	/**
	 * Buscar.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	public String buscar(Model model) {
		return "busquedaAvanzadaIndices";
	}
}
