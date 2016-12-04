package co.com.bvc.portal.mercados.vista.portlet.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.bvc.portal.mercados.modelo.AccionMGCComparador;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.VolumenAccionMGC;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IAccionesMGC;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;



// TODO: Auto-generated Javadoc
/**
 * The Class HomeAccionesMGCPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class HomeAccionesMGCPortlet {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** The accionesMGC dao. */
	private IAccionesMGCDao accionesMGCDao;
	
	/** The mercado dao. */
	private IMercadoDao mercadoDao;
	
	/** The accionesMGC servicio. */
	private IAccionesMGC accionesMGCServicio;
	
	/**
	 * Cargar.
	 * 
	 * @param model the model
	 * 
	 * @return the string
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping
	/*public String cargarDummy(){
		return "dummyHome/accionesDummyHome";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "action=irHome")*/
	public String cargar(Model model) {
		try {
			log.info("Petición a portlet carga home - acciones - MGC");
			//List<ResumenAccionMGC> listaAcciones = accionesMGCServicio.accionesMasTranzadasDia(null, 100, IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC);
			String mensajeHorario = this.mercadoDao.mercadoAbierto(new GregorianCalendar(), IMercadoDao.ACCIONES_MGC, true);
			
			Map<String, List<ResumenAccionMGC>> listas = new LinkedMap();
			listas = accionesMGCServicio.accionesMasTranzadasHome(null, 100, IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC);

			/*List<ResumenAccionMGC> accionesSuben = new ArrayList<ResumenAccionMGC>(listaAcciones);
			List<ResumenAccionMGC> accionesBajan = new ArrayList<ResumenAccionMGC>(listaAcciones);
			
			Collections.sort(accionesSuben, new AccionMGCComparador(AccionMGCComparador.SUBE));
			Collections.sort(accionesBajan, new AccionMGCComparador(AccionMGCComparador.BAJA));
			*/
			VolumenAccionMGC vol = accionesMGCServicio.cargarVolumenDia(null).get(0);
			
			model.addAttribute(IConstantesAtributosModelo.HORARIO_ACCIONES_MGC,mensajeHorario);
			model.addAttribute(IConstantesAtributosModelo.VOLUMEN_TOTAL_ACCIONES_MGC, vol.getVolumenFormato() );
			log.info("Cargado volumen dia acciones MGC");
		
			/*Map<String, List<ResumenAccionMGC>> listas = new LinkedMap();
			if (listaAcciones != null && listaAcciones.size() > 5){
				listas.put("listaAccionesMGC", listaAcciones.subList(0, 5) );
			}else{
				listas.put("listaAccionesMGC", listaAcciones);
			}
			if (accionesSuben != null && accionesSuben.size() > 5){
				listas.put("listaAccionesMGCSube", accionesMGCServicio.sacarAcciones(AccionMGCComparador.SUBE, accionesSuben.subList(0, 5) ));
			}else{
				listas.put("listaAccionesMGCSube", accionesMGCServicio.sacarAcciones(AccionMGCComparador.SUBE, accionesSuben));
			}
			if (accionesBajan != null && accionesBajan.size() > 5){
				listas.put("listaAccionesMGCBaja", accionesMGCServicio.sacarAcciones(AccionMGCComparador.BAJA,  accionesBajan.subList(0, 5) ));
			}else{
				listas.put("listaAccionesMGCBaja", accionesMGCServicio.sacarAcciones(AccionMGCComparador.BAJA,  accionesBajan));
			}*/
			
			model.addAttribute(IConstantesAtributosModelo.LISTAS_ACCIONES_MGC, listas);
		
		} catch(Exception e) {
			log.error("Error al cargar home Mercados - Acciones - MGC", e);
			e.printStackTrace();
		}

		return "home_acciones_mgc";
		
	}
	
	/**
	 * Gets the acciones mgc dao.
	 * 
	 * @return the acciones dao MGC
	 */
	public IAccionesMGCDao getAccionesMGCDao() {
		return accionesMGCDao;
	}

	/**
	 * Sets the acciones mgc dao MGC.
	 * 
	 * @param accionesDao the new acciones dao
	 */
	public void setAccionesMGCDao(IAccionesMGCDao accionesMGCDao) {
		this.accionesMGCDao = accionesMGCDao;
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
	 * @param accionesMGCServicio the new acciones servicio
	 */
	public void setAccionesMGCServicio(IAccionesMGC accionesMGCServicio) {
		this.accionesMGCServicio = accionesMGCServicio;
	}

}
