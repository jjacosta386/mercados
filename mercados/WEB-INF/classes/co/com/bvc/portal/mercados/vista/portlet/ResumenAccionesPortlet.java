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

import co.com.bvc.portal.mercados.modelo.AccionComparador;
import co.com.bvc.portal.mercados.modelo.AccionesBusquedaForm;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.RentaFijaOperacion;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.modelo.VolumenAccion;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IAcciones;
import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.util.IConstantesAcciones;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumenAccionesPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class ResumenAccionesPortlet {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The acciones servicio. */
	private IAcciones accionesServicio;

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
	 * @return "accionesResumen"
	 */
	@RequestMapping
	// default render ()
	public String cargarResumen(Model model) {
		Long t1 = System.currentTimeMillis();
		this.cargaDatosBasicos(model);
		this.cargarResumenFecha(null, IAcciones.TIPO_MERCADO_COMPRAVENTAS,
				model);
		model.addAttribute("resumen", true);

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log
				.info("El tiempo en el método cargarResumen de ResumenAccionesPortlet es ="
						+ t);
		return "accionesResumen";
	}

	/**
	 * carga los datos del formulario y sale hacia el resumen de acciones.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping(params = "action=fecha")
	public String cargarResumenDia(Model model) {
		Long t1 = System.currentTimeMillis();
		this.cargaDatosBasicos(model);
		model.addAttribute("resumen", true);

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log
				.info("El tiempo en el método cargarResumenDia de ResumenAccionesPortlet es ="
						+ t);
		return "accionesResumen";
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
	public void verDetalleAccion(ActionRequest request,
			ActionResponse response,
			@RequestParam("nemoTecnico") String nemoTecnico,
			@RequestParam("tipoMercado") int tipoMercado, Model model) {
		Long t1 = System.currentTimeMillis();
		log.info("nemotecnico: " + nemoTecnico);
		nemoTecnico = nemoTecnico.toUpperCase();
		AccionesBusquedaForm forma = new AccionesBusquedaForm();
		forma.setNemo(nemoTecnico);
		forma.setTipoMercado(tipoMercado);
		model.addAttribute("formulario", forma);
		this.cargaDatosBasicos(model);
		AccionesBusquedaForm form = new AccionesBusquedaForm();
		form.setAnioFecha(null);
		form.setNemo(nemoTecnico);
		form.setTipoMercado(IAcciones.TIPO_MERCADO_COMPRAVENTAS);
		this.cargaTitulo(form, model);
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log
				.info("El tiempo en el método verDetalleAccion de ResumenAccionesPortlet es ="
						+ t);
		response.setRenderParameter("action", "accionesDetalleView");
	}

	/**
	 * hace una salida limpia a la jsp del detalle de la acción.
	 * 
	 * @param model the model
	 * 
	 * @return "accionesDetalle"
	 */
	@RequestMapping(params = "action=accionesDetalleView")
	public String irDetalle(Model model) {
		Long t1 = System.currentTimeMillis();
		model.addAttribute("resumen", false);
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log
				.info("El tiempo en el método irDetalle de ResumenAccionesPortlet es ="
						+ t);
		return "accionesDetalle";
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
	public void cargarResumenFiltros(ActionRequest request,
			ActionResponse response,
			@ModelAttribute("formulario") AccionesBusquedaForm form,
			BindingResult result, Model model) {

		Long t1 = System.currentTimeMillis();
		log.info("buscando la fecha " + form.getFechaRequerida() + " nemo: "
				+ form.getNemo());
		String fechaTmp=form.getAnioFecha();
		String fecha = form.getFechaRequerida();
		String fechaValidar = form.getDiaFecha() + "-" + form.getMesFecha()
				+ "-" + form.getAnioFecha();
		fechaConsulta = fecha;

		model.addAttribute("tipoMercado", form.getTipoMercado());
		
		

			if (UtilFechas.getFechaConsultaUltimoDia(fechaValidar)) {
				fecha = null;
				if (form.getNemo() != null && form.getNemo().length() > 0) {
					form.setAnioFecha(null);
					this.cargaTitulo(form, model);
					if(form.getTipoMercado() != null && (form.getTipoMercado()== IAcciones.TIPO_MERCADO_BOCEAS || form.getTipoMercado()== IAcciones.TIPO_MERCADO_BOCEAS_SIMUL) ){
						form.setAnioFecha(fechaTmp);
						response.setRenderParameter("action", "boceas");
						return;
					}else{
					response.setRenderParameter("action", "buscar");
					return;
					}
				}
			}
			if (form.getNemo() != null && form.getNemo().length() > 0) {
				this.cargaTitulo(form, model);
				if(form.getTipoMercado() != null && (form.getTipoMercado()== IAcciones.TIPO_MERCADO_BOCEAS || form.getTipoMercado()== IAcciones.TIPO_MERCADO_BOCEAS_SIMUL) ){
					response.setRenderParameter("action", "boceas");
					return;
				}else{
				response.setRenderParameter("action", "buscar");
				return;
				}
			}
		
		this.cargarResumenFecha(fecha, form.getTipoMercado(), model);
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log
				.info("El tiempo en el método cargarResumenFiltros de ResumenAccionesPortlet es ="
						+ t);
		response.setRenderParameter("action", "fecha");

	}

	/**
	 * carga al modelo los datos del buscador y redirecciona el flujo hacia el
	 * detalle de las acciones.
	 * 
	 * @param model the model
	 * 
	 * @return "accionesDetalle"
	 */
	@RequestMapping(params = "action=buscar")
	public String BuscarComisionistas(Model model) {
		Long t1 = System.currentTimeMillis();
		cargaDatosBasicos(model);
		model.addAttribute("resumen", false);

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log
				.info("El tiempo en el método BuscarComisionistas de ResumenAccionesPortlet es ="
						+ t);
		return "accionesDetalle";
	}
	
	/**
	 * carga al modelo los datos del buscador y redirecciona el flujo hacia el
	 * detalle de las acciones.
	 * 
	 * @param model the model
	 * 
	 * @return "accionesDetalleBoceas"
	 */
	@RequestMapping(params = "action=boceas")
	public String DetalleBoceas(Model model) {
		Long t1 = System.currentTimeMillis();
		AccionesBusquedaForm form = null;
		
		if (!model.containsAttribute("formulario")) {
			form = new AccionesBusquedaForm();
			model.addAttribute("formulario", form);
		} else {
			form = (AccionesBusquedaForm) model.asMap().get("formulario");
		}
		
		
		List<RentaFijaOperacion> operacionesRF = null;
		EmisorTitulo emisorRF = null;
		RentaFijaSumaOperacion sumaRF = null;
		RentaFijaResumenTitulo tituloRF = null;

		String fecha = form.getAnioFecha() + "-" + form.getMesFecha() + "-"
				+ form.getDiaFecha();

		String[] fechasComp = UtilFechas.getFechasComparador(fecha);
		
		String nemo = form.getNemo();
		String operacion =IConstantesAcciones.SIMULTANEA;
		String sesion =IConstantesAcciones.BOCEA_SI;
		String mercado =null;
		if(form.getTipoMercado()== IAcciones.TIPO_MERCADO_BOCEAS){
			operacion=IConstantesAcciones.COMPRAVENTA;
			sesion=IConstantesAcciones.BOCEA_CV;
		}
		//String sesion = form.getTipoSesion();
		//String operacion = form.getTipoMercado();

		try {
			operacionesRF = this.accionesServicio.getBoceasDetalleOperaciones(fecha,
					sesion, operacion, nemo, mercado);
			emisorRF = this.accionesServicio.getResumenEmisorBoceas(nemo);
			List<RentaFijaSumaOperacion> op = this.accionesServicio.getSumaOperacionesGraficaBocea(fechasComp[0], fechasComp[1], nemo);
			if (op != null && !op.isEmpty()){
				sumaRF = op.get(0);
			}else{
				sumaRF = new RentaFijaSumaOperacion();
				sumaRF.setEmisor(emisorRF.getSigla());
				sumaRF.setNemo(nemo);
			}
			tituloRF = this.accionesServicio.getResumenTituloBoceas(nemo);
			model.addAttribute("operacionesRF", operacionesRF);
			model.addAttribute("emisorRF", emisorRF);
			model.addAttribute("sumaRF", sumaRF);
			model.addAttribute("tituloRF", tituloRF);
			model.addAttribute("dias", UtilFechas.getDias());
			model.addAttribute("meses", UtilFechas.getMeses());
			model.addAttribute("anios", UtilFechas.getAnios());
			model.addAttribute("formulario", form);
			try {
			GregorianCalendar cal = new GregorianCalendar();
			if (StringUtils.isNotEmpty(fechaConsulta)
					&& !fechaConsulta.equals(sdf.format(new Date()))) {
				cal.setTime(sdf.parse(fechaConsulta));
			}
			String mensajeHorario = this.mercadoDao.mercadoAbierto(cal,
					form.getTipoMercado(), true);
			model.addAttribute("horarioAcciones", mensajeHorario);
			} catch (Exception e) {
				log.error("Error al cargar mensajeHorario", e);
			}
			
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log
				.info("El tiempo en el método DetalleBoceas de ResumenAccionesPortlet es ="
						+ t);
		return "accionesDetalleBoceas";
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
		AccionesBusquedaForm form = null;
		LinkedHashMap<String, String> dias = UtilFechas.getDias();
		LinkedHashMap<String, String> meses = UtilFechas.getMeses();
		LinkedHashMap<String, String> anios = UtilFechas.getAnios();
		model.addAttribute("dias", dias);
		model.addAttribute("meses", meses);
		model.addAttribute("anios", anios);
		if (!model.containsAttribute("formulario")) {
			form = new AccionesBusquedaForm();
			model.addAttribute("formulario", form);
		} else {
			form = (AccionesBusquedaForm) model.asMap().get("formulario");
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
			int tipoMercado = form.getTipoMercado() == IAcciones.TIPO_MERCADO_REPOS
			? IMercadoDao.TTVS : IMercadoDao.ACCIONES ; 
//			? IMercadoDao.REPOS : IMercadoDao.ACCIONES ;
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
				.info("El tiempo en el método cargaDatosBasicos de ResumenAccionesPortlet es ="
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
	private void cargarResumenFecha(String fecha, Integer tipoMercado,
			Model model) {

		Long t1 = System.currentTimeMillis();
		try {
			List<ResumenAccion> lista = this.accionesServicio
					.accionesMasTranzadasDia(fecha, 100, tipoMercado);
			List<VolumenAccion> listaVol = this.accionesServicio
					.cargarVolumenDia(fecha);

			VolumenAccion volTotal = listaVol.remove(listaVol.size() - 1);

			List<ResumenAccion> accionesSuben = new ArrayList<ResumenAccion>(
					lista);
			List<ResumenAccion> accionesBajan = new ArrayList<ResumenAccion>(
					lista);

			Collections.sort(accionesSuben, new AccionComparador(
					AccionComparador.SUBE));
			Collections.sort(accionesBajan, new AccionComparador(
					AccionComparador.BAJA));

			model.addAttribute("listaAcciones", lista);
			model.addAttribute("volumenAcciones", listaVol);
			model.addAttribute("volumenTotal", volTotal);
			model.addAttribute("listaAccionesBaja", accionesServicio
					.sacarAcciones(AccionComparador.BAJA, accionesBajan));
			model.addAttribute("listaAccionesSube", accionesServicio
					.sacarAcciones(AccionComparador.SUBE, accionesSuben));

		} catch (Exception e) {
			log.error("Error en portlet Acciones ", e);
		}

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.info("El tiempo en el método cargarResumenFecha de ResumenAccionesPortlet es ="
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
	private void cargaTitulo(AccionesBusquedaForm form, Model model) {

		Long t1 = System.currentTimeMillis();
		try {
			List<OperacionDiaAcciones> listaOperaciones = this.accionesServicio
					.operacionesDia(form.getNemo(), form.getFechaRequerida(),
							form.getTipoMercado(), true);
			TituloAccion titulo = this.accionesServicio.cargarDatosTituloDia(
					form.getNemo(), form.getFechaRequerida(), form
							.getTipoMercado(), listaOperaciones, true);
			log.info(" titulo.nemotecnico " + titulo.getNemotecnico());
			log.info(" titulo.codigo " + titulo.getCodigoSuper());
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
