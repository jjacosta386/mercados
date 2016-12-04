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

import co.com.bvc.portal.mercados.modelo.AccionComparador;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.VolumenAccion;
import co.com.bvc.portal.mercados.persistencia.IAccionesDao;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IAcciones;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;



// TODO: Auto-generated Javadoc
/**
 * The Class HomeAccionesPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class HomeAccionesPortlet {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());
	
	/** The acciones dao. */
	private IAccionesDao accionesDao;
	
	/** The mercado dao. */
	private IMercadoDao mercadoDao;
	
	/** The acciones servicio. */
	private IAcciones accionesServicio;
	
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
			log.info("Petición a portlet carga home - acciones");
			//List<ResumenAccion> listaAcciones = accionesServicio.accionesMasTranzadasDia(null, 100, IAcciones.TIPO_MERCADO_COMPRAVENTAS);
			String mensajeHorario = this.mercadoDao.mercadoAbierto(new GregorianCalendar(), IMercadoDao.ACCIONES, true);
			
			Map<String, List<ResumenAccion>> listas = new LinkedMap();
			
			listas = accionesServicio.accionesMasTranzadasHome(null, 100, IAcciones.TIPO_MERCADO_COMPRAVENTAS);
			
			//List<ResumenAccion> accionesSuben = new ArrayList<ResumenAccion>(listaAcciones);
			//List<ResumenAccion> accionesBajan = new ArrayList<ResumenAccion>(listaAcciones);
			
			//Collections.sort(accionesSuben, new AccionComparador(AccionComparador.SUBE));
			//Collections.sort(accionesBajan, new AccionComparador(AccionComparador.BAJA));
			
			VolumenAccion vol = accionesServicio.cargarVolumenDia(null).get(0);
			
			model.addAttribute(IConstantesAtributosModelo.HORARIO_ACCIONES, mensajeHorario);
			model.addAttribute(IConstantesAtributosModelo.VOLUMEN_TOTAL_ACCIONES, vol.getVolumenFormato() );
			log.info("Cargado volumen dia acciones");
		
			/*Map<String, List<ResumenAccion>> listas = new LinkedMap();
			if (listaAcciones != null && listaAcciones.size() > 5){
				listas.put("listaAcciones", listaAcciones.subList(0, 5) );
			}else{
				listas.put("listaAcciones", listaAcciones);
			}
			if (accionesSuben != null && accionesSuben.size() > 5){
				listas.put("listaAccionesSube", accionesServicio.sacarAcciones(AccionComparador.SUBE, accionesSuben.subList(0, 5) ));
			}else{
				listas.put("listaAccionesSube", accionesServicio.sacarAcciones(AccionComparador.SUBE, accionesSuben));
			}
			
			if (accionesBajan != null && accionesBajan.size() > 5){
				listas.put("listaAccionesBaja", accionesServicio.sacarAcciones(AccionComparador.BAJA,  accionesBajan.subList(0, 5) ));
			}else{
				listas.put("listaAccionesBaja", accionesServicio.sacarAcciones(AccionComparador.BAJA,  accionesBajan));
			}*/
			
			model.addAttribute(IConstantesAtributosModelo.LISTAS_ACCIONES, listas);
		
		} catch(Exception e) {
			log.error("Error al cargar home Mercados - Acciones", e);
			e.printStackTrace();
		}

		return "home_acciones";
		
	}
	
	/**
	 * Gets the acciones dao.
	 * 
	 * @return the acciones dao
	 */
	public IAccionesDao getAccionesDao() {
		return accionesDao;
	}

	/**
	 * Sets the acciones dao.
	 * 
	 * @param accionesDao the new acciones dao
	 */
	public void setAccionesDao(IAccionesDao accionesDao) {
		this.accionesDao = accionesDao;
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

}
