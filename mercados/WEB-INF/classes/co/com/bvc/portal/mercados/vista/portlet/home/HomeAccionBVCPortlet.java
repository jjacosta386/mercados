package co.com.bvc.portal.mercados.vista.portlet.home;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.servicio.IAcciones;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;

// TODO: Auto-generated Javadoc
/** 
 * The Class HomeAccionBVCPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class HomeAccionBVCPortlet {

	/** The log. */
	private Logger log = Logger.getLogger(HomeAccionBVCPortlet.class);

	/** The acciones servicio. */
	private IAcciones accionesServicio;

	/**
	 * Cargar.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	public String cargar(Model model) {
		Long t1 = System.currentTimeMillis();
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<OperacionDiaAcciones> listOp = this.accionesServicio
					.operacionesDia("BVC", null,
							IAcciones.TIPO_MERCADO_COMPRAVENTAS, false);
			// obtiene los datos del titulo sin delay, posiblemente a partir de
			// las operaciones cargadas
			TituloAccion ta = this.accionesServicio.cargarDatosTituloDia(
					"BVC", null, IAcciones.TIPO_MERCADO_COMPRAVENTAS, listOp,
					false);
			model
					.addAttribute(IConstantesAtributosModelo.TITULO_ACCION_BVC,
							ta);
			model.addAttribute(IConstantesAtributosModelo.FECHA_ACCION_BVC, df
					.format(ta.getHora().getTime()));
		} catch (Exception e) {
			log.error("Error al cargar home Mercados - Accion BVC", e);
			e.printStackTrace();
		}
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método cargar de HomeAccionBVCPortlet es ="
				+ t);
		return "home_accion_bvc";
	}

	/**
	 * Gets the acciones servicio.
	 * 
	 * @return the acciones servicio
	 */
	public IAcciones getAccionesServicio() {
		return accionesServicio;
	}

	/**
	 * Sets the acciones servicio.
	 * 
	 * @param accionesServicio the new acciones servicio
	 */
	public void setAccionesServicio(IAcciones accionesServicio) {
		this.accionesServicio = accionesServicio;
	}

}
