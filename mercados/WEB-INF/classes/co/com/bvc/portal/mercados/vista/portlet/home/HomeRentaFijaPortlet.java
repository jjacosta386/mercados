package co.com.bvc.portal.mercados.vista.portlet.home;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumen;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IRentaFija;
import co.com.bvc.portal.mercados.servicio.imp.RentaFija;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeRentaFijaPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class HomeRentaFijaPortlet {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The mercado dao. */
	private IMercadoDao mercadoDao;

	/** The rentafija. */
	private IRentaFija rentafija;

	/**
	 * Cargar home.
	 * 
	 * @param model
	 *            the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	/*
	 * public String cargarDummy(){ return "dummyHome/rentaFijaDummyHome"; }
	 * 
	 * @RequestMapping(params = "action=irHome")
	 */
	public String cargarHome(Model model) {
		Long t1 = System.currentTimeMillis();
		try {
			log.info("Petición a portlet carga home - renta fija");

			String mensajeHorario = this.mercadoDao.mercadoAbierto(
					new GregorianCalendar(), IMercadoDao.RENTA_FIJA, true);

			model.addAttribute(IConstantesAtributosModelo.HORARIO_RENTA_FIJA,
					mensajeHorario);

			RentaFijaResumen resumenRF = rentafija.getResumen(UtilFechas.getHoy("yyyy-MM-dd"),
					RentaFija.DEUDA_ALL, RentaFija.ALL_OPE,
					RentaFija.SESION_ALL, IRentaFija.MERCADO_TODOS);

			model.addAttribute(
					IConstantesAtributosModelo.VOLUMEN_RENTA_FIJA_TRANSADAS,
					resumenRF.getTotalVolumenTransaccional());
			model.addAttribute(
					IConstantesAtributosModelo.VOLUMEN_RENTA_FIJA_REGISTRADAS,
					resumenRF.getTotalVolumenRegistro());
			model.addAttribute(IConstantesAtributosModelo.DATOS_RENTA_FIJA,
					resumenRF);

		} catch (Exception e) {
			log.error("Error al cargar home Mercados - Renta Fija", e);
			e.printStackTrace();
		}

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.debug("El tiempo en el método cargar de HomeRentaFijaPortlet es ="
				+ t);
		return "home_renta_fija";
	}

	/**
	 * Gets the rentafija.
	 * 
	 * @return the rentafija
	 */
	public IRentaFija getRentafija() {
		return rentafija;
	}

	/**
	 * Sets the rentafija.
	 * 
	 * @param _rentafija
	 *            the new rentafija
	 */
	public void setRentafija(IRentaFija _rentafija) {
		this.rentafija = _rentafija;
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
