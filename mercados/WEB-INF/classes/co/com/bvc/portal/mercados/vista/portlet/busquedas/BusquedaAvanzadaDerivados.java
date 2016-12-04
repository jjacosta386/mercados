package co.com.bvc.portal.mercados.vista.portlet.busquedas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO: Auto-generated Javadoc
/**
 * The Class BusquedaAvanzadaDerivados.
 */
@Controller
@RequestMapping("VIEW")
public class BusquedaAvanzadaDerivados {
	
	/**
	 * Buscar.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	public String buscar(Model model) {
		return "busquedaAvanzadaDerivados";
	}
}
