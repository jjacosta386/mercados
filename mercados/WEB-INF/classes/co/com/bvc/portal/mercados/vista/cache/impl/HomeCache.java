package co.com.bvc.portal.mercados.vista.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import co.com.bvc.portal.mercados.vista.cache.IHomeCache;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeAccionBVCPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeAccionesPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeAccionesMGCPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeDerivadosPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeDerivadosPortletDrvx;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeDivisasPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeIndicePortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeRentaFijaPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeEtfsMGCPortlet;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeCache.
 */
public class HomeCache implements IHomeCache {

	/** The log. */
	private static Logger log = Logger.getLogger(HomeCache.class);

	/** The mapa. */
	private static Map<Integer, ObjetoCache> mapa = new HashMap<Integer, ObjetoCache>();

	/**
	 * Gets the model.
	 * 
	 * @param home the home
	 * @param portlet the portlet
	 * 
	 * @return the model
	 */
	public static synchronized Model getModel(Integer home, Object portlet) {
		long currTime = System.currentTimeMillis();
		ObjetoCache cache = mapa.get(home);
		if (cache != null) {
			if ((currTime - cache.ultimaActualizacion) < TIEMPO_CACHE) {
				log.info("retornando el home " + home + " desde el cache");
				return cache.model;
			}
		}
		log.info("ejecutando la carga del home " + home);
		Model model = new ExtendedModelMap();
		switch (home) {
		case HOME_INDICES:
			((HomeIndicePortlet) portlet).cargar(model);
			break;
		case HOME_ACCIONES:
			((HomeAccionesPortlet) portlet).cargar(model);
			break;
		case HOME_DERIVADOS:
			((HomeDerivadosPortlet) portlet).cargar(model);
			break;
		case HOME_DERIVADOS_DRVX:
			((HomeDerivadosPortletDrvx) portlet).cargar(model);
			break;
		case HOME_RENTA_FIJA:
			((HomeRentaFijaPortlet) portlet).cargarHome(model);
			break;
		case HOME_DIVISAS:
			((HomeDivisasPortlet) portlet).cargar(model);
			break;
		case HOME_ACCION_BVC:
			((HomeAccionBVCPortlet) portlet).cargar(model);
			break;
		case HOME_ACCIONES_MGC:
			((HomeAccionesMGCPortlet) portlet).cargar(model);
			break;
		case HOME_ETF_MGC:
			((HomeEtfsMGCPortlet) portlet).cargar(model);
			break;
		default:
			break;
		}
		mapa.put(home, (new HomeCache()).new ObjetoCache(model, System
				.currentTimeMillis()));
		return model;
	}

	/**
	 * Clean cache.
	 */
	public static void cleanCache(){
		mapa = new HashMap<Integer, ObjetoCache>();
	}
	
	/**
	 * The Class ObjetoCache.
	 */
	private class ObjetoCache {

		/**
		 * Instantiates a new objeto cache.
		 * 
		 * @param model the model
		 * @param ultimaActualizacion the ultima actualizacion
		 */
		public ObjetoCache(Model model, long ultimaActualizacion) {
			super();
			this.model = model;
			this.ultimaActualizacion = ultimaActualizacion;
		}

		/** The ultima actualizacion. */
		public long ultimaActualizacion;
		
		/** The model. */
		public Model model;
	}

	
}
