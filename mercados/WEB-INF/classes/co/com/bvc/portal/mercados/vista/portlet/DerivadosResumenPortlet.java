package co.com.bvc.portal.mercados.vista.portlet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.BusquedaDerivados;
import co.com.bvc.portal.mercados.modelo.Derivado;
import co.com.bvc.portal.mercados.modelo.DerivadoCaracteristicasContrato;
import co.com.bvc.portal.mercados.modelo.DerivadoResumenContrato;
import co.com.bvc.portal.mercados.modelo.DerivadoResumenExtendido;
import co.com.bvc.portal.mercados.modelo.MercadoDerivados;
import co.com.bvc.portal.mercados.persistencia.IDerivadosDao;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IDerivados;


// TODO: Auto-generated Javadoc
/**
 * The Class DerivadosResumenPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class DerivadosResumenPortlet {

	/** The derivados dao. */
	private IDerivadosDao derivadosDao;

	/** The derivados. */
	private IDerivados derivados;
	
	/** The derivados Drvx. */
	private IDerivados derivadosDrvx;

	public IDerivados getDerivadosDrvx() {
		return derivadosDrvx;
	}


	public void setDerivadosDrvx(IDerivados derivadosDrvx) {
		this.derivadosDrvx = derivadosDrvx;
	}

	/** The mercado dao. */
	private IMercadoDao mercadoDao;

	private BusquedaDerivados busquedaDerivados = new BusquedaDerivados();

	/** The log. */
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(DerivadosResumenPortlet.class);

	/**
	 * Cargar home.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	// default render (action=list)
	public String cargarHome(Model model) {
		Long t1 = System.currentTimeMillis();
		if(!model.asMap().containsKey("mercadoDerivados")){
			busquedaDerivados = new BusquedaDerivados();
			cargarResumen(model);
		}
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.info("El tiempo en el método cargarHome de DerivadosResumenPortlet es ="
						+ t);
		return "mercados_derivados";
	}

	/**
	 * Busqueda derivados home. Entrada desde el buscador del resumen
	 * 
	 * @param request the request
	 * @param response the response
	 * @param busqueda the busqueda
	 * @param result the result
	 * @param model the model
	 */
	@RequestMapping(params = "action=buscar")
	// action phase
	public void busquedaDerivadosHome(ActionRequest request,ActionResponse response,
						@ModelAttribute("busqueda") BusquedaDerivados busqueda,BindingResult result, Model model) {
		Long t1 = System.currentTimeMillis();
		busquedaDerivados = busqueda;
		cargarResumen(model);
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.info("El tiempo en el método busquedaDerivadosHome de DerivadosResumenPortlet es ="
						+ t);
		response.setRenderParameter("action", "list");
	}

	/**
	 * Ver detalle derivado.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param nemo the nemo
	 * @param opcf the opcf
	 * @param busqueda the busqueda
	 * @param result the result
	 * @param model the model
	 */
	@RequestMapping(params = "action=detalleDerivado")
	// action phase
	public void verDetalleDerivado(ActionRequest request,
			ActionResponse response, @RequestParam("nemo") String nemo,
			@RequestParam("opcf") String opcf,
			@ModelAttribute("busqueda") BusquedaDerivados busqueda,
			BindingResult result, Model model) {

		Long t1 = System.currentTimeMillis();
		log.info("opcf en: " + opcf);

		try {
			HashMap<String, String> tiposContrato = derivados
					.getTiposContrato();
			LinkedHashMap<String, String> dias = UtilFechas.getDias();
			LinkedHashMap<String, String> meses = UtilFechas.getMeses();
			LinkedHashMap<String, String> anios = UtilFechas.getAnios();
			DerivadoCaracteristicasContrato caracteristicasContrato = new DerivadoCaracteristicasContrato();
			DerivadoResumenContrato resumenContrato = new DerivadoResumenContrato();
			DerivadoResumenContrato resumenContratoDrvx = new DerivadoResumenContrato();
			Set<DerivadoResumenExtendido> derivadosResumenDia = new LinkedHashSet<DerivadoResumenExtendido>();
			if (StringUtils.isNotEmpty(opcf) && opcf.equals("opcf")) {
				model.addAttribute("opcf", opcf);
				resumenContrato = derivados.getDerivadoResumenContratoOPCF(
						nemo, busquedaDerivados);
				derivadosResumenDia = derivadosDao.getDerivadosDiaOPCF(nemo,
						busquedaDerivados);
			} else {
				model.addAttribute("opcf", "otraCosa");
				
				if (derivadosDao.verificarContratoDerivados(nemo) != 0)
					caracteristicasContrato = derivados.getCaracteristicasContrato(nemo, busquedaDerivados);
				else
					caracteristicasContrato = derivadosDrvx.getCaracteristicasContrato(nemo, busquedaDerivados);
				
				if (derivadosDao.verificarContratoDerivados(nemo) != 0)
					resumenContrato = derivados.getDerivadoResumenContrato(nemo,busquedaDerivados);
				else
					resumenContrato = derivadosDrvx.getDerivadoResumenContrato(nemo,busquedaDerivados);
				
				if (derivadosDao.verificarContratoDerivados(nemo) != 0)
					derivadosResumenDia = derivados.getDerivadosDia(nemo,busquedaDerivados);
				else
					derivadosResumenDia = derivadosDrvx.getDerivadosDia(nemo,busquedaDerivados);
			}
			
			model.addAttribute("tiposContrato", tiposContrato);
			model.addAttribute("dias", dias);
			model.addAttribute("meses", meses);
			model.addAttribute("anios", anios);
			model.addAttribute("nemo", nemo);
			model.addAttribute("caracteristicasContrato",caracteristicasContrato);
			model.addAttribute("resumenContrato", resumenContrato);
			model.addAttribute("derivadosResumenDia", derivadosResumenDia);
			model.addAttribute("busquedaDerivados", busquedaDerivados);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.info("El tiempo en el método verDetalleDerivado de DerivadosResumenPortlet es ="
						+ t);

		response.setRenderParameter("action", "detalleDerivadoRender");
	}

	/**
	 * Ver detalle derivado.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping(params = "action=detalleDerivadoRender")
	// render phase
	public String verDetalleDerivado(Model model) {

		return "derivados_detalle";
	}

	/**
	 * Busqueda derivados detalle.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param busqueda the busqueda
	 * @param result the result
	 * @param model the model
	 */
	@RequestMapping(params = "action=buscarDetalle")
	// action phase
	public void busquedaDerivadosDetalle(ActionRequest request,
			ActionResponse response,
			@ModelAttribute("busqueda") BusquedaDerivados busqueda,
			BindingResult result, Model model) {
		busquedaDerivados = busqueda;
		verDetalleDerivado(request, response, busquedaDerivados.getNemo(), "",
				busqueda, result, model);
	}

	/**
	 * Ver detalle derivado dummy.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param nemo the nemo
	 * @param busqueda the busqueda
	 * @param result the result
	 * @param model the model
	 */
	@RequestMapping(params = "action=detalleDummy")
	// action phase
	public void verDetalleDerivadoDummy(ActionRequest request,
			ActionResponse response, @RequestParam("nemo") String nemo,
			@ModelAttribute("busqueda") BusquedaDerivados busqueda,
			BindingResult result, Model model) {
		verDetalleDerivado(request, response, nemo, "",
				new BusquedaDerivados(), result, model);
	}

	/**
	 * Cargar home dummy.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping(params = "action=dummy")
	public String cargarHomeDummy(Model model) {
		busquedaDerivados = new BusquedaDerivados();
		return cargarHome(model);
	}
	
	private void cargarResumen(Model model){
		try {
			GregorianCalendar fecha = new GregorianCalendar();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			if (StringUtils.isNotEmpty(busquedaDerivados.getAnioFecha())
					&& StringUtils.isNotEmpty(busquedaDerivados.getMesFecha())
					&& StringUtils.isNotEmpty(busquedaDerivados.getDiaFecha())){
				//si pusieron fecha en el buscador
				fecha.setTime(df.parse(busquedaDerivados.getDiaFecha() + "-" + busquedaDerivados.getMesFecha() + "-" + busquedaDerivados.getAnioFecha()));
			}
			if (df.format(fecha.getTime()).equals(df.format(new Date()))){
				//si la fecha es hoy
				fecha = new GregorianCalendar();
			}
			String estadoMercado = mercadoDao.mercadoAbierto(fecha, IMercadoDao.DERIVADOS, true);
			
			Set<MercadoDerivados> mercadoDerivados = new LinkedHashSet<MercadoDerivados>();
			Set<MercadoDerivados> mercadoDerivadosDrvx = new LinkedHashSet<MercadoDerivados>();
			Set<MercadoDerivados> resumen = new LinkedHashSet<MercadoDerivados>();
			Set<Derivado> derivadosResumen = new LinkedHashSet<Derivado>();
			Set<Derivado> derivadosDrvxResumen = new LinkedHashSet<Derivado>();
			
			if (!estadoMercado.equals(IMercadoDao.DIA_NO_BURSATIL)) {
				//sí es día hábil bursátil
				mercadoDerivados = derivados.getMercadoDerivados(busquedaDerivados);//volumenes para la fecha
				mercadoDerivadosDrvx = derivadosDrvx.getMercadoDerivados(busquedaDerivados);//volumenes para la fecha
				
				MercadoDerivados totalFinancieros = null;
				MercadoDerivados totalEnergeticos = null;
				 
				Iterator<MercadoDerivados> it = mercadoDerivadosDrvx.iterator();
				while(it.hasNext()){
					MercadoDerivados md = it.next();
					if("Total".equalsIgnoreCase(md.getContrato())){
						totalEnergeticos = md;
						continue;
					}else{
						resumen.add(md);
					}
				}		
				it = mercadoDerivados.iterator();
				while(it.hasNext()){
					MercadoDerivados md = it.next();
					if("Total".equalsIgnoreCase(md.getContrato())){
						totalFinancieros = md;
						continue;
					}else{
						resumen.add(md);
					}
				}
				
								
				if(totalFinancieros != null && totalEnergeticos != null){
					totalFinancieros.setOpenInterest(totalFinancieros.getOpenInterest() + totalEnergeticos.getOpenInterest());
					totalFinancieros.setParticipacion(totalFinancieros.getParticipacion() + totalEnergeticos.getParticipacion());
					totalFinancieros.setVolumen(totalFinancieros.getVolumen() + totalEnergeticos.getVolumen());
					totalFinancieros.setContratos(totalFinancieros.getContratos() + totalEnergeticos.getContratos());
					totalFinancieros.setOrder(999);
					
					resumen.add(totalFinancieros);
				}				
				it = resumen.iterator();
				while(it.hasNext())
				{
					MercadoDerivados md = it.next();
					if(md.getVolumen() != null && md.getVolumen()>0)
					{
					   md.setParticipacion(new Double( (md.getVolumen() * 100.0 )/totalFinancieros.getVolumen()));
					}
					else {
						    md.setParticipacion(0.0);
					}
				}
				

				if (busquedaDerivados.getTipoContrato().compareToIgnoreCase("E") == 0) {
					derivadosDrvxResumen = derivadosDrvx.getDerivados(busquedaDerivados);
				} else if (busquedaDerivados.getTipoContrato().compareToIgnoreCase("") == 0) {
					derivadosDrvxResumen = derivadosDrvx.getDerivados(busquedaDerivados);
					derivadosResumen = derivados.getDerivados(busquedaDerivados);
				} else {

					derivadosResumen = derivados.getDerivados(busquedaDerivados);
				}
				derivadosDrvxResumen.addAll(derivadosResumen);
			}
			
			HashMap<String, String> tiposContrato = derivados.getTiposContrato();
			LinkedHashMap<String, String> dias = UtilFechas.getDias();
			LinkedHashMap<String, String> meses = UtilFechas.getMeses();
			LinkedHashMap<String, String> anios = UtilFechas.getAnios();
			if (BusquedaDerivados.isBusquedaVacia(busquedaDerivados)) {
				String fechaHoy = df.format(fecha.getTime());
				StringTokenizer token = new StringTokenizer(fechaHoy, "-");
				busquedaDerivados.setDiaFecha(token.nextToken());
				busquedaDerivados.setMesFecha(token.nextToken());
				busquedaDerivados.setAnioFecha(token.nextToken());
			}
			model.addAttribute("mercadoDerivados", resumen);
			model.addAttribute("derivadosResumen", derivadosDrvxResumen);
			model.addAttribute("tiposContrato", tiposContrato);
			model.addAttribute("dias", dias);
			model.addAttribute("estadoMercado", estadoMercado);
			model.addAttribute("meses", meses);
			model.addAttribute("anios", anios);
			model.addAttribute("busquedaDerivados", busquedaDerivados);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the derivados dao.
	 * 
	 * @param derivadosDao the derivadosDao to set
	 */
	public void setDerivadosDao(IDerivadosDao derivadosDao) {
		this.derivadosDao = derivadosDao;
	}

	/**
	 * Sets the derivados.
	 * 
	 * @param derivados the derivados to set
	 */
	public void setDerivados(IDerivados derivados) {
		this.derivados = derivados;
	}

	/**
	 * Gets the busqueda derivados.
	 * 
	 * @return the busquedaDerivados
	 */
	public BusquedaDerivados getBusquedaDerivados() {
		return busquedaDerivados;
	}

	/**
	 * Sets the busqueda derivados.
	 * 
	 * @param busquedaDerivados the busquedaDerivados to set
	 */
	public void setBusquedaDerivados(BusquedaDerivados busquedaDerivados) {
		this.busquedaDerivados = busquedaDerivados;
	}

	/**
	 * Gets the derivados dao.
	 * 
	 * @return the derivadosDao
	 */
	public IDerivadosDao getDerivadosDao() {
		return derivadosDao;
	}

	/**
	 * Gets the derivados.
	 * 
	 * @return the derivados
	 */
	public IDerivados getDerivados() {
		return derivados;
	}

	/**
	 * Sets the mercado dao.
	 * 
	 * @param mercadoDao the mercadoDao to set
	 */
	public void setMercadoDao(IMercadoDao mercadoDao) {
		this.mercadoDao = mercadoDao;
	}
	
	/**
	 * Ver detalle derivado Drvx.
	 * 
	 * @param request the request
	 * @param response the response
	 * @param nemo the nemo
	 * @param opcf the opcf
	 * @param busqueda the busqueda
	 * @param result the result
	 * @param model the model
	 */
	@RequestMapping(params = "action=detalleDerivadoDrvx")
	// action phase
	public void verDetalleDerivadoDrvx(ActionRequest request,
			ActionResponse response, @RequestParam("nemo") String nemo,
			@RequestParam("opcf") String opcf,
			@ModelAttribute("busqueda") BusquedaDerivados busqueda,
			BindingResult result, Model model) {

		Long t1 = System.currentTimeMillis();
		
		try {
			HashMap<String, String> tiposContrato = derivadosDrvx.getTiposContrato();
			
			LinkedHashMap<String, String> dias = UtilFechas.getDias();
			LinkedHashMap<String, String> meses = UtilFechas.getMeses();
			LinkedHashMap<String, String> anios = UtilFechas.getAnios();
			
			DerivadoCaracteristicasContrato caracteristicasContrato = new DerivadoCaracteristicasContrato();
			DerivadoResumenContrato resumenContrato = new DerivadoResumenContrato();
			Set<DerivadoResumenExtendido> derivadosResumenDia = new LinkedHashSet<DerivadoResumenExtendido>();
			
			caracteristicasContrato = derivadosDrvx.getCaracteristicasContrato(nemo, busquedaDerivados);
			resumenContrato = derivadosDrvx.getDerivadoResumenContrato(nemo, busquedaDerivados);
			derivadosResumenDia = derivadosDrvx.getDerivadosDia(nemo, busquedaDerivados);

			model.addAttribute("tiposContrato", tiposContrato);
			model.addAttribute("dias", dias);
			model.addAttribute("meses", meses);
			model.addAttribute("anios", anios);
			model.addAttribute("nemo", nemo);
			model.addAttribute("caracteristicasContrato", caracteristicasContrato);
			model.addAttribute("resumenContrato", resumenContrato);
			model.addAttribute("derivadosResumenDia", derivadosResumenDia);
			model.addAttribute("busquedaDerivados", busquedaDerivados);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.info("El tiempo en el método verDetalleDerivado de DerivadosResumenPortlet es ="
						+ t);

		response.setRenderParameter("action", "detalleDerivadoDrvxRender");
	}

	/**
	 * Ver detalle derivado Drvx.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping(params = "action=detalleDerivadoDrvxRender")
	// render phase
	public String verDetalleDerivadoDrvx(Model model) {

		return "derivados_detalle_drvx";
	}

}