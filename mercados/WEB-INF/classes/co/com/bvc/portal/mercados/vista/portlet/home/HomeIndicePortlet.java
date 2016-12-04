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

/**
 * @author BVC
 * @description The Class HomeIndicePortlet.
 */

@Controller
@RequestMapping("VIEW")
public class HomeIndicePortlet {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The indices. */
	private IIndices indices;

	/**
	 * Cargar.
	 * 
	 * @param model
	 *            the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	/*
	 * public String cargarDummy(){ return "dummyHome/indiceDummyHome"; }
	 * 
	 * @RequestMapping(params = "action=irHome")
	 */
	public String cargar(Model model) {
		Long t1 = System.currentTimeMillis();
		try {
			log.info("Petición a portlet carga Home - Parte Índices");

			List<ResumenIndice> listaIndices = indices.cargarHome();
			List<ResumenIndice> listaIndices2 = indices.cargarHomeRF();
			/******************Se agrega funcionalidad para Mercado Monetario***************/
			List<ResumenIndice> listaIndices3 = indices.cargarHomeMM();
			/******************************************************************************/
			log.info("Cargado datos de indices");

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaIndice = Calendar.getInstance().getTime();

			if (listaIndices.size() > 0) {
				fechaIndice = listaIndices.get(0).getFecha().getTime();
			}
			model.addAttribute(IConstantesAtributosModelo.FECHA_INDICES,
					df.format(fechaIndice));
			model.addAttribute(IConstantesAtributosModelo.RESUMEN_INDICES,
					listaIndices);
			model.addAttribute(IConstantesAtributosModelo.RESUMEN_INDICES_RF,
					listaIndices2);
			/******************Se agrega funcionalidad para Mercado Monetario***************/
			model.addAttribute(IConstantesAtributosModelo.RESUMEN_INDICES_MM,
					listaIndices3);
			/******************************************************************************/
			log.info("Fin carga Home Indices ./.. .");

		} catch (Exception e) {
			log.error("Error al cargar home Mercados - Parte índices", e);
			e.printStackTrace();
		}
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.info("El tiempo en el método cargar de HomeIndicePortlet es =" + t);
		return "home_indices";
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

}
/****************************************** End of HomeIndicePortlet.java **************************************************/
