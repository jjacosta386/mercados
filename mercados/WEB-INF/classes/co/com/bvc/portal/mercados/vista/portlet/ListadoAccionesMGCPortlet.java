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
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IAccionesMGC;
import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.util.UtilFechas;



@Controller
@RequestMapping("VIEW")
public class ListadoAccionesMGCPortlet {
	
	

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
		 * @return "listadoAccionesMGC"
		 */
		@RequestMapping
		// (params = "action=default")
		public String mostrarFormulario(Model model) {
			Long t1 = System.currentTimeMillis();
			this.cargaDatosIniciales(model);
			try {
				model= accionesMGCServicio.cargarlistasMGC(model);
			} catch (PersistenciaException e) {
				log.error("Error en portlet Acciones MGC", e);
			}
			this.cargarListaTotal(null, IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC,
					model);
			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método cargarResumen de ListadoAccionesMGCPortlet es ="
							+ t);
			return "listadoAccionesMGC";
		}
		
		/**
		 * Metodo que lista todos las acciones y etf's de MGC.
		 * 
		 * @param model
		 *            the model
		 * 
		 * @return the string
		 */
		@RequestMapping(params = "action=listarTodos")
		public String listarTodos(Model model) {
			Long t1 = System.currentTimeMillis();
			this.cargaDatosIniciales(model);
			try {
				model= accionesMGCServicio.cargarlistasMGC(model);
			} catch (PersistenciaException e) {
				log.error("Error en portlet Acciones MGC", e);
			}
			this.cargarListaTotal(null, IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC,
					model);
			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método cargarResumen de ListadoAccionesMGCPortlet es ="
							+ t);
			return "listadoAccionesMGC";
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
				@RequestParam("pais") String pais,
				@RequestParam("cotizacion") String cotizacion,
				@RequestParam("patrocinador") String patrocinador,
				@RequestParam("tipoMercado") String tipoMercado,
				@ModelAttribute("formularioBusqueda") AccionesMGCBusquedaForm form,
				BindingResult result, Model model) {

			String fechaValidar = form.getFechaRequerida();
			ResumenAccionMGC listaMGC = new ResumenAccionMGC(); ;
			
			if(form.getPais()!= null && !"%".equals(form.getPais()) && !"".equals(form.getPais()) )
				listaMGC.setPais(form.getPais().toUpperCase());
			else
				listaMGC.setPais(pais);
			
			if(form.getPatrocinador()!= null && !"%".equals(form.getPatrocinador()) && !"".equals(form.getPatrocinador()))
				listaMGC.setPatrocinador(form.getPatrocinador().toUpperCase());
			else
				listaMGC.setPatrocinador(patrocinador);
			
			if(form.getCotizacion()!= null && !"%".equals(form.getCotizacion()) && !"".equals(form.getCotizacion()))
				listaMGC.setCotizacion(form.getCotizacion().toUpperCase());
			else
				listaMGC.setCotizacion(cotizacion);
			
			listaMGC.setRazonSocial(form.getNombreEmisor());
						
			if(tipoMercado!= null && !"0".equals(tipoMercado) && !"".equals(tipoMercado) )
				if("4".equals(tipoMercado))
					listaMGC.setNombreEmr(IAccionesMGC.TIPO_LISTA_ACCIONES_MGC);
				else listaMGC.setNombreEmr(IAccionesMGC.TIPO_LISTA_ETF_MGC);
			else
				listaMGC.setNombreEmr("0");
			
			/*if(form.getTipoMercado() == IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC)
				listaMGC.setNombreEmr(IAccionesMGC.TIPO_LISTA_ACCIONES_MGC);
			else if(form.getTipoMercado() == IAccionesMGC.TIPO_MERCADO_ETF_MGC)
				listaMGC.setNombreEmr(IAccionesMGC.TIPO_LISTA_ETF_MGC);*/
			
			listaMGC.setFechaList(fechaValidar);

			model.addAttribute("tipoMercado", tipoMercado);
			List<ResumenAccionMGC> lista = null;
			try {
				lista= accionesMGCServicio.listasFiltroMGC(listaMGC);
			} catch (PersistenciaException e) {
				log.error("Error en portlet Acciones MGC", e);
			}
			
			model.addAttribute("listaAccionesEtf", lista);
			response.setRenderParameter("action", "fecha");
			return ;
		}
		
		@RequestMapping(params = "action=fecha")
		public String cargarListaAccEtfMGC(Model model) {
			this.cargaDatosIniciales(model);
			model.addAttribute("resumen", true);
			return "listadoAccionesMGC";
		}
		
		/**
		 * Se encarga de poner los datos que por defecto debe tener el modelo.
		 * Listados del formulario (y fecha).
		 * 
		 * @param model the model
		 */
		private void cargaDatosIniciales(Model model) {
			AccionesMGCBusquedaForm form = null;
			
			LinkedHashMap<String, String> dias = new LinkedHashMap<String, String>();
			dias.put("-", "-");
			dias.putAll(UtilFechas.getDias());
			
			LinkedHashMap<String, String> meses = new LinkedHashMap<String, String>();
			meses.put("-", "-");
			meses.putAll(UtilFechas.getMeses());
			
			LinkedHashMap<String, String> anios = new LinkedHashMap<String, String>();
			anios.put("-", "-");
			anios.putAll(UtilFechas.getAnios());
			
			model.addAttribute("dias", dias);
			model.addAttribute("meses", meses);
			model.addAttribute("anios", anios);
			
			if (!model.containsAttribute("formularioBusqueda")) {
				form = new AccionesMGCBusquedaForm();
				form.setTipoMercado(0);
				model.addAttribute("formularioBusqueda", form);
			} else {
				form = (AccionesMGCBusquedaForm) model.asMap().get("formularioBusqueda");
			}
	        
			try {
				model= accionesMGCServicio.cargarlistasMGC(model);
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
				List<ResumenAccionMGC> lista = this.accionesMGCServicio
						.listadoTotalAccionesETFMGC(fecha, 100, tipoMercado);
				
				for(ResumenAccionMGC objResumenAccionMGC: lista){
					log.info("La razon social es: "+objResumenAccionMGC.getRazonSocial());
				}

				model.addAttribute("listaAccionesEtf", lista);
				log.info("El modelo es: "+lista);
				

			} catch (Exception e) {
				log.error("Error en portlet Acciones ", e);
			}

			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log.info("El tiempo en el método cargarListaTotal de ResumenAccionesMGCPortlet es ="
							+ t);
		}

		
		/**
		 * Carga el listado de acciones y etf´s.
		 * 
		 * @param fecha : la fecha que se quiere consultar, o nulo si se quiere
		 * consultar el día actual
		 * @param tipoMercado the tipo mercado
		 * @param model the model
		 */
		/*private void cargarListaFiltro(String fecha, Integer tipoMercado,
				Model model) {

			Long t1 = System.currentTimeMillis();
			try {
				List<ResumenAccionMGC> lista = this.accionesMGCServicio
						.listadoAccionesETFMGC(fecha, 100, tipoMercado);
				
				

				model.addAttribute("listaAccionesEtf", lista);
				

			} catch (Exception e) {
				log.error("Error en portlet Acciones ", e);
			}

			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log.info("El tiempo en el método cargarListaTotal de ResumenAccionesMGCPortlet es ="
							+ t);
		}*/

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
