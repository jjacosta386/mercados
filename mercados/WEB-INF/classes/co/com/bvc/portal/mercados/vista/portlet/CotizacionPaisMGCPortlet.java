package co.com.bvc.portal.mercados.vista.portlet;

import java.util.LinkedHashMap;
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

import co.com.bvc.portal.mercados.modelo.AccionesMGCBusquedaForm;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.CotizaPaisMGC;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IAccionesMGC;
import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.util.UtilFechas;



@Controller
@RequestMapping("VIEW")
public class CotizacionPaisMGCPortlet {
	
	

	 	/** The log. */
		private Logger log = Logger.getLogger(this.getClass());

		/** The acciones servicio. */
		private IAccionesMGC accionesMGCServicio;

		/** The mercado dao. */
		private IMercadoDao mercadoDao;

		/**
		 * Metodo que hace la carga inicial del formulario de busqueda de acciones y ETF's.
		 * 
		 * @param model
		 *            the model
		 * 
		 * @return the string
		 * 
		 * @return "CotizaPaisMGC"
		 */
		@RequestMapping
		// (params = "action=default")
		public String mostrarFormulario(Model model) {
			Long t1 = System.currentTimeMillis();
			this.cargaDatosIniciales(model);
			/*try {
				model= accionesMGCServicio.cargarlistasMGC(model);
			} catch (PersistenciaException e) {
				log.error("Error en portlet Acciones MGC", e);
			}*/
			this.cargarListaTotal(null, IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC,
					model);
			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método cargarResumen de ListadoAccionesMGCPortlet es ="
							+ t);
			return "CotizaPaisMGC";
		}
		
		
		
		/**
		 * Llegada desde el buscador y desde el resumen, carga los datos de la
		 * acción de acuerdo a los parametros del buscador, los links del resumen
		 * hacen uso del buscador.
		 * 
		 * @param request the request
		 * @param response the response
		 * @param form the form
		 * @param result the result
		 * @param model the model
		 */
		@RequestMapping(params = "action=buscar")
		public void cargarResumenFiltrosListaMGC(ActionRequest request,
				ActionResponse response,
				@RequestParam("cotizacion") String cotizacion,
				@RequestParam("empresa") String empresa,
				@RequestParam("accion") String accion,
				@ModelAttribute("formularioBusqueda") AccionesMGCBusquedaForm form,
				BindingResult result, Model model) {

			CotizaPaisMGC listaMGC = new CotizaPaisMGC(); ;
			
						
			if(form.getCotizacion()!= null && !"%".equals(form.getCotizacion()) && !"".equals(form.getCotizacion()))
				listaMGC.setCotizacion(form.getCotizacion().toUpperCase());
			else
				listaMGC.setCotizacion(cotizacion);
			
			listaMGC.setAccion(form.getAccion());
			listaMGC.setEmpresa(form.getEmpresa());
			
			
			List<CotizaPaisMGC> lista = null;
			try {
				lista= accionesMGCServicio.CotizaFiltroMGC(listaMGC);
			} catch (PersistenciaException e) {
				log.error("Error en portlet Acciones MGC", e);
			}
			
			model.addAttribute("listaCotizaPais", lista);
			response.setRenderParameter("action", "fecha");
			return ;
		}
		
		@RequestMapping(params = "action=fecha")
		public String cargarListaAccEtfMGC(Model model) {
			this.cargaDatosIniciales(model);
			model.addAttribute("resumen", true);
			return "CotizaPaisMGC";
		}
		
		/**
		 * Se encarga de poner los datos que por defecto debe tener el modelo.
		 * Listados del formulario (y fecha).
		 * 
		 * @param model the model
		 */
		private void cargaDatosIniciales(Model model) {
			AccionesMGCBusquedaForm form = null;
			
			try {
				model= accionesMGCServicio.cargarlistasCotizaMGC(model);
			} catch (PersistenciaException e) {
				log.error("Error en portlet Acciones MGC", e);
			}
		}
		
		/**
		 * Carga el listado de acciones y etf´s.
		 * 
		 * @param fecha : la fecha que se quiere consultar, o nulo si se quiere
		 * consultar el día actual
		 * @param tipoMercado the tipo mercado
		 * @param model the model
		 */
		private void cargarListaTotal(String fecha, Integer tipoMercado,
				Model model) {

			Long t1 = System.currentTimeMillis();
			try {
				List<CotizaPaisMGC> lista = this.accionesMGCServicio
						.cotizaTotalPaisMGC();
				
				
				model.addAttribute("listaCotizaPais", lista);
				

			} catch (Exception e) {
				log.error("Error en portlet Acciones ", e);
			}

			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log.info("El tiempo en el método cargarListaTotal de ResumenAccionesMGCPortlet es ="
							+ t);
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
		 * @param globalServicio the new acciones servicio
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
