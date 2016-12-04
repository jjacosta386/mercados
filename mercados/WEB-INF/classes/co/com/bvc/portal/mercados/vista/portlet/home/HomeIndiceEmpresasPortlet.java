package co.com.bvc.portal.mercados.vista.portlet.home;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.bvc.portal.mercados.modelo.ResumenIndice;
import co.com.bvc.portal.mercados.servicio.IIndices;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeIndiceEmpresasPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class HomeIndiceEmpresasPortlet {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The indices. */
	private IIndices indices;

	/**
	 * Cargar.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	// default render (action=list)
	public String cargar(Model model) {
		Long t1 = System.currentTimeMillis();
		try {
			log.info("Petición a portlet carga Home - Parte Índices");

			List<ResumenIndice> listaIndices = indices.cargarHome();
			log.info("Cargado datos de indices");

			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date fechaIndice = Calendar.getInstance().getTime();

			if (listaIndices.size() > 0) {
				fechaIndice = listaIndices.get(0).getFecha().getTime();
			}
			model.addAttribute(IConstantesAtributosModelo.FECHA_INDICES, df.format(fechaIndice));
			model.addAttribute(IConstantesAtributosModelo.RESUMEN_INDICES, listaIndices);

			log.info("Fin carga Home Indices ./.. .");

		} catch (Exception e) {
			log.error("Error al cargar home Mercados - Parte índices", e);
			e.printStackTrace();
		}
		Long t2 = System.currentTimeMillis();
		Long t = (t2 -t1)/1000;
		log.info("El tiempo en el método cargar de HomeIndiceEmpresasPortlet es ="+t);
		return "home_indices_empresas";
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
