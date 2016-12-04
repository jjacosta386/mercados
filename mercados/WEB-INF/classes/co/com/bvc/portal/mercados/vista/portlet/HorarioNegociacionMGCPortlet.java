package co.com.bvc.portal.mercados.vista.portlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestParam;

import co.com.bvc.portal.mercados.modelo.AccionMGCComparador;
import co.com.bvc.portal.mercados.modelo.AccionesMGCBusquedaForm;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.ResumenEtfsMGC;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.modelo.VolumenAccionMGC;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IAccionesMGC;
import co.com.bvc.portal.comun.util.UtilFechas;

@Controller
@RequestMapping("VIEW")
public class HorarioNegociacionMGCPortlet {
	
	

		/** The log. */
		private Logger log = Logger.getLogger(this.getClass());

		/** The acciones servicio. */
		private IAccionesMGC accionesMGCServicio;

		/**
		 * carga el horario de negociación para le Mercado Global Colombiano.
		 * 
		 * @param model the model
		 * 
		 * @return "HorarioNegociacion"
		 */
		@RequestMapping
		// default render ()
		public String cargarHorarioNegociacionMGC(Model model) {
			Long t1 = System.currentTimeMillis();
			try {
				model.addAttribute("horarioNegociacion", accionesMGCServicio.obtenerHorarioNegociacionMGC(IMercadoDao.ACCIONES_MGC));
				
				Long t2 = System.currentTimeMillis();
				Long t = (t2 - t1) / 1000;
				log.info("El tiempo en el método cargarHorarioNegociacionMGC de HorarioNegociacionMGCPortlet es =" + t);
				
			} catch(Exception e) {
				log.error("Error al cargar horario Negociación - MGC", e);
				e.printStackTrace();
			}
			return "horarioNegociacionMGC";
		}

		/**
		 * @return the accionesMGCServicio
		 */
		public IAccionesMGC getAccionesMGCServicio() {
			return accionesMGCServicio;
		}

		/**
		 * @param accionesMGCServicio the accionesMGCServicio to set
		 */
		public void setAccionesMGCServicio(IAccionesMGC accionesMGCServicio) {
			this.accionesMGCServicio = accionesMGCServicio;
		}
		
		
}
