package co.com.bvc.portal.mercados.vista.portlet.home;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.bvc.portal.mercados.modelo.DivisasSetFxTO;
import co.com.bvc.portal.mercados.servicio.IDivisas;
import co.com.bvc.portal.mercados.servicio.IDivisasSetFx;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeDivisasPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class HomeDivisasPortlet {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** The divisas registro. */
	private IDivisas divisasRegistro;
	
	/** The divisas set fx. */
	private IDivisasSetFx divisasSetFx;
	
	/**
	 * Cargar.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@RequestMapping
	/*public String cargarDummy(){
		return "dummyHome/divisasDummyHome";
	}
	
	@RequestMapping(params = "action=irHome")*/
	public String cargar(Model model) {
		Long t1 = System.currentTimeMillis();
		log.info("Petición a portlet carga home-divisas");
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es", "CO"));
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		Map<String, String> resumenMercados = new TreeMap<String, String>();
		try {
			DivisasSetFxTO resumenSetFx = divisasSetFx.getDetalleDia(sdf.format(new Date()));
			Double totalRegistro = divisasRegistro.obtenerResumenMercadoPorFecha(sdf.format(new Date()));
			resumenMercados.put("Registro", nf.format(totalRegistro));
			model.addAttribute(IConstantesAtributosModelo.RESUMEN_MERCADO, resumenMercados);
			model.addAttribute(IConstantesAtributosModelo.DETALLE_SET_FX, resumenSetFx);
		}catch (Exception e) {
			log.error("Error al cargar home Mercados - Divisas", e);
			e.printStackTrace();
		}
		Long t2 = System.currentTimeMillis();
		Long t = (t2 -t1)/1000;
		log.info("El tiempo en el método cargar de HomeDivisasPortlet es ="+t);
		return "home_divisas";
	}

	/**
	 * Gets the divisas registro.
	 * 
	 * @return the divisas registro
	 */
	public IDivisas getDivisasRegistro() {
		return divisasRegistro;
	}

	/**
	 * Sets the divisas registro.
	 * 
	 * @param divisasRegistro the new divisas registro
	 */
	public void setDivisasRegistro(IDivisas divisasRegistro) {
		this.divisasRegistro = divisasRegistro;
	}

	/**
	 * Gets the divisas set fx.
	 * 
	 * @return the divisas set fx
	 */
	public IDivisasSetFx getDivisasSetFx() {
		return divisasSetFx;
	}

	/**
	 * Sets the divisas set fx.
	 * 
	 * @param divisasSetFx the new divisas set fx
	 */
	public void setDivisasSetFx(IDivisasSetFx divisasSetFx) {
		this.divisasSetFx = divisasSetFx;
	}

	
}
