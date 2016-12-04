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

import co.com.bvc.portal.mercados.modelo.EtfsMGCComparador;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.ResumenEtfsMGC;
import co.com.bvc.portal.mercados.modelo.VolumenAccionMGC;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IAccionesMGC;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;



// TODO: Auto-generated Javadoc
/**
 * The Class HomeEtfsMGCPortlet.
 */
@Controller
@RequestMapping("VIEW")
public class HomeEtfsMGCPortlet {
	
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
			log.info("Petición a portlet carga home - etf's - MGC");
			//List<ResumenEtfsMGC> listaEtfs = accionesMGCServicio.etfsMasTranzadasDia(null, 100, IAccionesMGC.TIPO_MERCADO_ETF_MGC);
			String mensajeHorario = this.mercadoDao.mercadoAbierto(new GregorianCalendar(), IMercadoDao.ETF_MGC, true);
			
			Map<String, List<ResumenEtfsMGC>> listas = new LinkedMap();
			listas = accionesMGCServicio.etfsMasTranzadasDiaHome(null, 100, IAccionesMGC.TIPO_MERCADO_ETF_MGC);

			/*List<ResumenEtfsMGC> etfsSuben = new ArrayList<ResumenEtfsMGC>(listaEtfs);
			List<ResumenEtfsMGC> etfsBajan = new ArrayList<ResumenEtfsMGC>(listaEtfs);
			
			Collections.sort(etfsSuben, new EtfsMGCComparador(EtfsMGCComparador.SUBE));
			Collections.sort(etfsBajan, new EtfsMGCComparador(EtfsMGCComparador.BAJA));
			*/
			VolumenAccionMGC vol = accionesMGCServicio.cargarVolumenDia(null).get(1);
			
			model.addAttribute(IConstantesAtributosModelo.HORARIO_ETFS_MGC, mensajeHorario);
			model.addAttribute(IConstantesAtributosModelo.VOLUMEN_TOTAL_ETFS_MGC, vol.getVolumenFormato() );
			log.info("Cargado volumen dia etf's MGC");
		
			/*Map<String, List<ResumenEtfsMGC>> listas = new LinkedMap();
			if (listaEtfs != null && listaEtfs.size() > 5){
				listas.put("listaEtfsMGC", listaEtfs.subList(0, 5) );
			}else{
				listas.put("listaEtfsMGC", listaEtfs);
			}
			if (etfsSuben != null && etfsSuben.size() > 5){
				listas.put("listaEtfsMGCSube", accionesMGCServicio.sacarEtfs(EtfsMGCComparador.SUBE, etfsSuben.subList(0, 5) ));
			}else{
				listas.put("listaEtfsMGCSube", accionesMGCServicio.sacarEtfs(EtfsMGCComparador.SUBE, etfsSuben));
			}
			if (etfsBajan != null && etfsBajan.size() > 5){
				listas.put("listaEtfsMGCBaja", accionesMGCServicio.sacarEtfs(EtfsMGCComparador.BAJA,  etfsBajan.subList(0, 5) ));
			}else{
				listas.put("listaEtfsMGCBaja", accionesMGCServicio.sacarEtfs(EtfsMGCComparador.BAJA,  etfsBajan));
			}*/
			
			model.addAttribute(IConstantesAtributosModelo.LISTAS_ETFS_MGC, listas);
		
		} catch(Exception e) {
			log.error("Error al cargar home Mercados - ETF's - MGC", e);
			e.printStackTrace();
		}

		return "home_etfs_mgc";
		
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
