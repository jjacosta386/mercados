package co.com.bvc.portal.mercados.vista.portlet.home;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.bvc.portal.mercados.modelo.BusquedaDerivados;
import co.com.bvc.portal.mercados.modelo.Derivado;
import co.com.bvc.portal.mercados.modelo.MercadoDerivados;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IDerivados;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeDerivadosDrvxPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class HomeDerivadosPortletDrvx {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());


	/** The busqueda derivados. */
	private BusquedaDerivados busquedaDerivados = new BusquedaDerivados();

	/** The derivados. */
	private IDerivados derivados;

	/** The mercado dao. */
	private IMercadoDao mercadoDao;

	/**
	 * Cargar.
	 * 
	 * @param model
	 *            the model
	 * 
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping
	/*
	 * public String cargarDummy(){ return "dummyHome/derivadosDummyHome"; }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @RequestMapping(params = "action=irHome")
	 */
	public String cargar(Model model) {
		Long t1 = System.currentTimeMillis();
		try {
			String estadoMercado = mercadoDao.mercadoAbierto(new GregorianCalendar(), IMercadoDao.DERIVADOS, true);
			model.addAttribute(IConstantesAtributosModelo.ESTADO_MERCADO_DERIVADOS,estadoMercado);
			try {
				Set<MercadoDerivados> mercadoDerivados = derivados.getMercadoDerivados(null);
				if (mercadoDerivados.isEmpty()) {
					MercadoDerivados md1 = new MercadoDerivados();
					md1.setContrato("Futuros Energéticos");
					md1.setContratos(0);
					mercadoDerivados.add(md1);
				} else {
					boolean futuro = false;
					for (Iterator iterator = mercadoDerivados.iterator(); iterator.hasNext();) {
						MercadoDerivados mercadoDerivados2 = (MercadoDerivados) iterator.next();
						if (mercadoDerivados2.getContrato().equalsIgnoreCase("Futuros Energéticos")){
					     futuro = true;
						}
					}
					if (!futuro) {
						MercadoDerivados md1 = new MercadoDerivados();
						md1.setContrato("Futuros Energéticos");
						md1.setContratos(0);
						mercadoDerivados.add(md1);
					}
				}
				model.addAttribute(IConstantesAtributosModelo.RESUMEN_GENERAL_DERIVADOS,mercadoDerivados);
				model.addAttribute("busquedaDerivados", busquedaDerivados);
				log.info("Cargado resumen derivados drvx con " + mercadoDerivados.size() + " totales");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Set<Derivado> derivadosResumenFuturos = new LinkedHashSet<Derivado>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String[] tok = (sdf.format((new GregorianCalendar()).getTime())).split("-");
				
				busquedaDerivados.setAnioFecha(tok[0]);
				
				
				
				busquedaDerivados.setMesFecha(tok[1]);
				busquedaDerivados.setDiaFecha(tok[2]);
				
				Long t4 = System.currentTimeMillis();
				Long tttt = (t4 - t1) / 1000;
				log.info("El tiempo antes de los servicios: =" + tttt);

				busquedaDerivados.setTipoContrato("E");
				derivadosResumenFuturos = derivados.getDerivados(busquedaDerivados);
				model.addAttribute("derivadosResumenFuturos",derivadosResumenFuturos);
				log.info("Cargado resumen derivados drvx titulo");
			} catch (Exception e) {
				log.error("Error en portlet: ", e);
				e.printStackTrace();
			}
			log.info("Fin carga  Mercados  Home Derivados Drvx./.. .");

		} catch (Exception e) {
			log.error("Error al cargar home Mercados Drvx - Derivados", e);
			e.printStackTrace();
		}

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.info("El tiempo en el método cargar de HomeDerivadosDrvxPortlet es ="
				+ t);

		return "home_derivados_drvx";

	}

	/**
	 * Busqueda derivados home.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param busqueda
	 *            the busqueda
	 * @param result
	 *            the result
	 * @param model
	 *            the model
	 */
	@RequestMapping(params = "action=buscar")
	// action phase
	public void busquedaDerivadosHome(ActionRequest request, ActionResponse response, @ModelAttribute("busqueda") BusquedaDerivados busqueda, BindingResult result, Model model) {
		busquedaDerivados = busqueda;
		response.setRenderParameter("action", "list");
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
	 * @param busquedaDerivados
	 *            the busquedaDerivados to set
	 */
	public void setBusquedaDerivados(BusquedaDerivados busquedaDerivados) {
		this.busquedaDerivados = busquedaDerivados;
	}

	/**
	 * Gets the derivados.
	 * 
	 * @return the derivados
	 */
	public IDerivados getDerivadosDrvx() {
		return derivados;
	}
	
	/**
	 * Sets the derivados.
	 * 
	 * @param derivados
	 *            the new derivados
	 */
	public void setDerivados(IDerivados derivados) {
		this.derivados = derivados;
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
	 * @param mercadoDao
	 *            the new mercado dao
	 */
	public void setMercadoDao(IMercadoDao mercadoDao) {
		this.mercadoDao = mercadoDao;
	}

}