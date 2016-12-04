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
public class ResumenAccionesMGCPortlet {
	
	

		/** The log. */
		private Logger log = Logger.getLogger(this.getClass());

		/** The acciones servicio. */
		private IAccionesMGC accionesMGCServicio;

		/** The mercado dao. */
		private IMercadoDao mercadoDao;

		/** The fecha consulta. */
		private String fechaConsulta;

		/** The sdf. */
		private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		/**
		 * carga los datos necesarios y sale hacia el resumen de acciones para el
		 * día de hoy.
		 * 
		 * @param model the model
		 * 
		 * @return "accionesMGCResumen"
		 */
		@RequestMapping
		// default render ()
		public String cargarResumenMGC(Model model) {
			Long t1 = System.currentTimeMillis();
			this.cargaDatosBasicos(model);
			this.cargarResumenFecha(null, IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC,
					model, 0);
			model.addAttribute("resumen", true);
			model.addAttribute("tipoMercado", IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC);
			model.addAttribute("tipoInstrumento", 0);
			
			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log.info("El tiempo en el método cargarResumen de ResumenAccionesMGCPortlet es =" + t);
			return "accionesMGCResumen";
		}

		/**
		 * carga los datos del formulario y sale hacia el resumen de acciones.
		 * 
		 * @param model the model
		 * 
		 * @return the string
		 */
		@RequestMapping(params = "action=fecha")
		public String cargarResumenDiaMGC(Model model) {
			Long t1 = System.currentTimeMillis();
			this.cargaDatosBasicos(model);
			model.addAttribute("resumen", true);

			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método cargarResumenDia de ResumenAccionesMGCPortlet es ="
							+ t);
			return "accionesMGCResumen";
		}

		/**
		 * Entrada desde el ticker y desde el listado en el home, muestra el detalle
		 * de una acción, para esto debe cargar el resumen del mercado de
		 * compraventa, las operaciones del tipo de mercado escogido y el detalle
		 * del emisor.
		 * 
		 * @param request the request
		 * @param response the response
		 * @param nemoTecnico the nemo tecnico
		 * @param tipoMercado the tipo mercado
		 * @param model the model
		 */
		@RequestMapping(params = "action=detalleAccion")
		public void verDetalleAccionMGC(ActionRequest request,
				ActionResponse response,
				@RequestParam("nemoTecnico") String nemoTecnico,
				@RequestParam("tipoMercado") int tipoMercado, Model model) {
			Long t1 = System.currentTimeMillis();
			log.info("nemotecnico: " + nemoTecnico);
			nemoTecnico = nemoTecnico.toUpperCase();
			AccionesMGCBusquedaForm forma = new AccionesMGCBusquedaForm();
			forma.setNemo(nemoTecnico);
			forma.setTipoMercado(tipoMercado);
			model.addAttribute("formulario", forma);
			this.cargaDatosBasicos(model);
			AccionesMGCBusquedaForm form = new AccionesMGCBusquedaForm();
			//form.setAnioFecha(null);
			form.setNemo(nemoTecnico);
			form.setTipoMercado(IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC);
			this.cargaTitulo(form, model);
			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método verDetalleAccion de ResumenAccionesMGCPortlet es ="
							+ t);
			response.setRenderParameter("action", "accionesDetalleView");
		}

		/**
		 * hace una salida limpia a la jsp del detalle de la acción.
		 * 
		 * @param model the model
		 * 
		 * @return "accionesMGCDetalle"
		 */
		@RequestMapping(params = "action=accionesDetalleView")
		public String irDetalleMGC(Model model) {
			Long t1 = System.currentTimeMillis();
			model.addAttribute("resumen", false);
			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método irDetalle de ResumenAccionesMGCPortlet es ="
							+ t);
			return "accionesMGCDetalle";
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
		public void cargarResumenFiltrosMGC(ActionRequest request,
				ActionResponse response,
				@ModelAttribute("formulario") AccionesMGCBusquedaForm form,
				BindingResult result, Model model) {

			Long t1 = System.currentTimeMillis();
			log.info("buscando la fecha " + form.getFechaRequerida() + " nemo: "
					+ form.getNemo());
			String fecha = form.getFechaRequerida();
			String fechaValidar = form.getDiaFecha() + "-" + form.getMesFecha()
					+ "-" + form.getAnioFecha();
			fechaConsulta = fecha;
			Integer tipoInstrumento = form.getTipoInstrumento();

			model.addAttribute("tipoMercado", form.getTipoMercado());
			model.addAttribute("tipoInstrumento", form.getTipoInstrumento());

			if (UtilFechas.getFechaConsultaUltimoDia(fechaValidar)) {
				fecha = null;
				if (form.getNemo() != null && form.getNemo().length() > 0) {
					//form.setAnioFecha(null);
					this.cargaTitulo(form, model);
					response.setRenderParameter("action", "buscar");
					return;
				}
			}
			if (form.getNemo() != null && form.getNemo().length() > 0) {
				this.cargaTitulo(form, model);
				response.setRenderParameter("action", "buscar");
				return;
			}
			this.cargarResumenFecha(fecha, form.getTipoMercado(), model, tipoInstrumento);
			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método cargarResumenFiltros de ResumenAccionesMGCPortlet es ="
							+ t);
			response.setRenderParameter("action", "fecha");

		}

		/**
		 * carga al modelo los datos del buscador y redirecciona el flujo hacia el
		 * detalle de las acciones.
		 * 
		 * @param model the model
		 * 
		 * @return "accionesMGCDetalle"
		 */
		@RequestMapping(params = "action=buscar")
		public String BuscarComisionistasMGC(Model model) {
			Long t1 = System.currentTimeMillis();
			cargaDatosBasicos(model);
			model.addAttribute("resumen", false);

			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método BuscarComisionistas de ResumenAccionesMGCPortlet es ="
							+ t);
			return "accionesMGCDetalle";
		}

		/**
		 * Se encarga de poner los datos que por defecto debe tener el modelo.
		 * Listados del formulario (tipo de mercado y fecha), mensaje de mercado
		 * abierto.
		 * 
		 * @param model the model
		 */
		private void cargaDatosBasicos(Model model) {
			Long t1 = System.currentTimeMillis();
			AccionesMGCBusquedaForm form = null;
			LinkedHashMap<String, String> dias = UtilFechas.getDias();
			LinkedHashMap<String, String> meses = UtilFechas.getMeses();
			LinkedHashMap<String, String> anios = UtilFechas.getAnios();
			model.addAttribute("dias", dias);
			model.addAttribute("meses", meses);
			model.addAttribute("anios", anios);
			if (!model.containsAttribute("formulario")) {
				form = new AccionesMGCBusquedaForm();
				model.addAttribute("formulario", form);
			} else {
				form = (AccionesMGCBusquedaForm) model.asMap().get("formulario");
			}
	/* Se ajusta para mercado TTV 
		try {
				int tipoMercado = form.getTipoMercado() == IAcciones.TIPO_MERCADO_REPOS ? IMercadoDao.REPOS
						: IMercadoDao.ACCIONES;
				GregorianCalendar cal = new GregorianCalendar();
				if (StringUtils.isNotEmpty(fechaConsulta)
						&& !fechaConsulta.equals(sdf.format(new Date()))) {
					cal.setTime(sdf.parse(fechaConsulta));
				}
				String mensajeHorario = this.mercadoDao.mercadoAbierto(cal,
						tipoMercado, true);
				model.addAttribute("horarioAcciones", mensajeHorario);
			} catch (Exception e) {
				log.error("Error al cargar mensajeHorario", e);
			}
	*/
			try {
				int tipoMercado = IAccionesMGC.TIPO_MERCADO_REPOS_MGC; 
//				? IMercadoDao.REPOS : IMercadoDao.ACCIONES ;
				GregorianCalendar cal = new GregorianCalendar();
				if (StringUtils.isNotEmpty(fechaConsulta)
						&& !fechaConsulta.equals(sdf.format(new Date()))) {
					cal.setTime(sdf.parse(fechaConsulta));
				}
				String mensajeHorario = this.mercadoDao.mercadoAbierto(cal,
						tipoMercado, true);
				model.addAttribute("horarioAcciones", mensajeHorario);
			} catch (Exception e) {
				log.error("Error al cargar mensajeHorario", e);
			}
	//*
			
			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método cargaDatosBasicos de ResumenAccionesMGCPortlet es ="
							+ t);
		}

		/**
		 * carga el resumen de acciones para la fecha dada y el tipo de mercado
		 * dado. Carga una lista de las 100 acciones mas tranzadas de la fecha y las
		 * ordena según 3 criterios (las que suben, las que bajan y las mas
		 * negociadas, y las carga en el modelo para que en la vista se de la
		 * ilusión de ajax.
		 * 
		 * @param fecha : la fecha que se quiere consultar, o nulo si se quiere
		 * consultar el día actual
		 * @param tipoMercado the tipo mercado
		 * @param model the model
		 */
		private void cargarResumenFecha(String fecha, Integer tipoMercado, Model model, Integer tipoInstrumento) {
			List<ResumenAccionMGC> lista = new ArrayList<ResumenAccionMGC>();
			List<ResumenEtfsMGC> listaETF = new ArrayList<ResumenEtfsMGC>();
			List<ResumenAccionMGC> listaRepos = new ArrayList<ResumenAccionMGC>();
			List<ResumenAccionMGC> listaTTV = new ArrayList<ResumenAccionMGC>();
			Long t1 = System.currentTimeMillis();
			try {
				if (tipoMercado == IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC){
					if(tipoInstrumento == IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC){
						lista.addAll(this.accionesMGCServicio.accionesMasTranzadasDia(fecha, 100, IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC));
												
					}else if(tipoInstrumento == IAccionesMGC.TIPO_MERCADO_ETF_MGC){
						listaETF.addAll(this.accionesMGCServicio.etfsMasTranzadasDia(fecha, 100, IAccionesMGC.TIPO_MERCADO_ETF_MGC));
												
					}
					else{
						lista.addAll(this.accionesMGCServicio.accionesMasTranzadasDia(fecha, 100, IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC));
						listaETF.addAll(this.accionesMGCServicio.etfsMasTranzadasDia(fecha, 100, IAccionesMGC.TIPO_MERCADO_ETF_MGC));
												
					}
				} else if (tipoMercado == IAccionesMGC.TIPO_MERCADO_REPOS_MGC){
					listaRepos = this.accionesMGCServicio.reposMasTranzadasDia(fecha, 100, IAccionesMGC.TIPO_MERCADO_REPOS_MGC);
				} else if (tipoMercado == IAccionesMGC.TIPO_MERCADO_TTV_MGC) {
					listaTTV = this.accionesMGCServicio.ttvMasTranzadasDia(fecha, 100, IAccionesMGC.TIPO_MERCADO_REPOS_MGC);
				}
				
				lista.addAll(listaRepos);
				lista.addAll(listaTTV);
				
				for (ResumenEtfsMGC res : listaETF) {
					ResumenAccionMGC resTmp = new ResumenAccionMGC();
					resTmp.setCantidad(res.getCantidad());
					resTmp.setCierreAnterior(res.getCierreAnterior());
					resTmp.setCotizacion(res.getCotizacion());
					resTmp.setFechaList(res.getFechaList());
					resTmp.setHora(res.getHora());
					resTmp.setId(res.getId());
					resTmp.setIdEmisor(res.getIdEmisor());
					resTmp.setIsin(res.getIsin());
					resTmp.setMoneda(res.getMoneda());
					resTmp.setNemoTecnico(res.getNemoTecnico());
					resTmp.setNombreEmr(res.getNombreEmr());
					resTmp.setOrden(res.getOrden(),0);
					resTmp.setPais(res.getPais());
					resTmp.setPatrocinador(res.getPatrocinador());
					resTmp.setPlazoFinal(res.getPlazoFinal());
					resTmp.setPlazoInicial(res.getPlazoInicial());
					resTmp.setRazonSocial(res.getRazonSocial());
					resTmp.setTipoMercado(res.getTipoMercado());
					resTmp.setUltimoPrecio(res.getUltimoPrecio());
					resTmp.setVariacion(res.getVariacion());
					resTmp.setVolumen(res.getVolumen());
					resTmp.setVolumenFinal(res.getVolumenFinal());
					resTmp.setVolumenInicial(res.getVolumenInicial());
					lista.add(resTmp);
				}
				
				List<VolumenAccionMGC> listaVol = this.accionesMGCServicio
						.cargarVolumenDia(fecha);

				VolumenAccionMGC volTotal = listaVol.remove(listaVol.size() - 1);

				List<ResumenAccionMGC> accionesSuben = new ArrayList<ResumenAccionMGC>(
						lista);
				List<ResumenAccionMGC> accionesBajan = new ArrayList<ResumenAccionMGC>(
						lista);
				
				Collections.sort(accionesSuben, new AccionMGCComparador(
						AccionMGCComparador.SUBE));
				Collections.sort(accionesBajan, new AccionMGCComparador(
						AccionMGCComparador.BAJA));

				model.addAttribute("listaAcciones", lista);
				model.addAttribute("volumenAcciones", listaVol);
				model.addAttribute("volumenTotal", volTotal);
				model.addAttribute("listaAccionesBaja", accionesMGCServicio
						.sacarAcciones(AccionMGCComparador.BAJA, accionesBajan));
				model.addAttribute("listaAccionesSube", accionesMGCServicio
						.sacarAcciones(AccionMGCComparador.SUBE, accionesSuben));
								
			} catch (Exception e) {
				log.error("Error en portlet Acciones ", e);
			}

			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log.info("El tiempo en el método cargarResumenFecha de ResumenAccionesMGCPortlet es ="
							+ t);
		}

		/**
		 * Carga los datos del titulo utilizando los criterios de busqueda del
		 * formulario, carga el resumen de compraventas, las operaciones del mercado
		 * que este en el formulario en el modelo, y el detalle del emisor.
		 * 
		 * @param form the form
		 * @param model the model
		 */
		private void cargaTitulo(AccionesMGCBusquedaForm form, Model model) {

			Long t1 = System.currentTimeMillis();
			try {
				List<OperacionDiaAcciones> listaOperaciones = this.accionesMGCServicio
						.operacionesDia(form.getNemo(), form.getFechaRequerida(),
								form.getTipoMercado(), true);
				TituloAccion titulo = this.accionesMGCServicio.cargarDatosTituloDia(
						form.getNemo(), form.getFechaRequerida(), form
								.getTipoMercado(), listaOperaciones, true);
				log.info(" titulo.nemotecnico " + titulo.getNemotecnico());
				log.info(" titulo.codigo " + titulo.getCodigoSuper());
				log.info(" titulo.cusid " + titulo.getCusip());
				model.addAttribute("titulo", titulo);
				model.addAttribute("listaOperaciones", listaOperaciones);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String fechaUltimaMarcacion = df.format(titulo.getHora().getTime());
				df.applyPattern("yyyy-MM-dd");
				if (!df.format(new Date()).equals(
						df.format(titulo.getHora().getTime()))) {
					fechaUltimaMarcacion = fechaUltimaMarcacion.substring(0, 10);
				}
				model.addAttribute("horaTransaccion", fechaUltimaMarcacion);
				model.addAttribute("nemoTecnico", form.getNemo());
			} catch (Throwable e) {
				e.printStackTrace();
			}

			Long t2 = System.currentTimeMillis();
			Long t = (t2 - t1) / 1000;
			log
					.info("El tiempo en el método cargaTitulo de cargaTitulo(AccionesBusquedaForm form, Model model) es ="
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
