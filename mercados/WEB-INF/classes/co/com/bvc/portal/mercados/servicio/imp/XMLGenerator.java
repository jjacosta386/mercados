package co.com.bvc.portal.mercados.servicio.imp;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.InformacionTicker;
import co.com.bvc.portal.mercados.persistencia.IAccionesDao;
import co.com.bvc.portal.mercados.persistencia.IDerivadosDao;
import co.com.bvc.portal.mercados.persistencia.IRentaFijaDao;
import co.com.bvc.portal.mercados.servicio.IXMLGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLGenerator.
 */
public class XMLGenerator implements IXMLGenerator {

	/** The acciones dao. */
	private IAccionesDao accionesDao;

	/** The renta fija dao. */
	private IRentaFijaDao rentaFijaDao;

	/** The derivados dao. */
	private IDerivadosDao derivadosDao;

	/*
	 * xml generados, sobre ellos se genera la sincronización, si se quisiera no
	 * usar un singleton (mirar rendimiento) estas variables deberian ser
	 * estaticas.
	 */

	/** The xml acciones. */
	private StringBuilder xmlAcciones = new StringBuilder();

	/** The xml renta fija. */
	private StringBuilder xmlRentaFija = new StringBuilder();

	/** The xml derivados. */
	private StringBuilder xmlDerivados = new StringBuilder();

	/** The log. */
	private Logger log = Logger.getLogger(XMLGenerator.class);

	/*
	 * getters y setters de los xml, cada par está sincronizado sobre el xml que
	 * se desea manipular, de tal forma que si lo estoy accediendo no lo estoy
	 * modificando al mismo tiempo. La otra opción sería usar StringBuffer en
	 * vez de StringBuilder pero eso podría ir en decrimento del rendimiento de
	 * la creación y pintado del ticker.
	 * 
	 * Además los setter son privados dado que solo esta instancia (teniendo en
	 * cuenta que es singleton, en caso contrario deberian ser ademas estáticos)
	 * deberia generar los xml.
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IXMLGenerator#getXmlAcciones()
	 */
	public StringBuilder getXmlAcciones() {
		synchronized (xmlAcciones) {
			return xmlAcciones;
		}
	}

	/**
	 * Sets the xml acciones.
	 * 
	 * @param xmlAccionesp
	 *            the new xml acciones
	 */
	private void setXmlAcciones(StringBuilder xmlAccionesp) {
		synchronized (this.xmlAcciones) {
			this.xmlAcciones = xmlAccionesp;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IXMLGenerator#getXmlRentaFija()
	 */
	public StringBuilder getXmlRentaFija() {
		synchronized (xmlRentaFija) {
			return xmlRentaFija;
		}
	}

	/**
	 * Sets the xml renta fija.
	 * 
	 * @param xmlRentaFijap
	 *            the new xml renta fija
	 */
	private void setXmlRentaFija(StringBuilder xmlRentaFijap) {
		synchronized (this.xmlRentaFija) {
			this.xmlRentaFija = xmlRentaFijap;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IXMLGenerator#getXmlDerivados()
	 */
	public StringBuilder getXmlDerivados() {
		synchronized (xmlDerivados) {
			return xmlDerivados;
		}
	}

	/**
	 * Sets the xml derivados.
	 * 
	 * @param xmlDerivadosp
	 *            the new xml derivados
	 */
	private void setXmlDerivados(StringBuilder xmlDerivadosp) {
		synchronized (this.xmlDerivados) {
			this.xmlDerivados = xmlDerivadosp;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.mercados.servicio.IXMLGenerator#construirXML()
	 */
	public void construirXML() throws PersistenciaException {
		construirXMLAcciones();
		construirXMLDerivados();
		construirXMLRentaFija();
	}

	/**
	 * Se encarga de consultar las acciones que deberian mostrarse en el ticker
	 * y generar el xml adecuado.
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	private void construirXMLAcciones() throws PersistenciaException {
		List<InformacionTicker> lista = accionesDao.accionesTicker();
		String link = "/pps/tibco/portalbvc/Home/Mercados/enlinea/acciones?com.tibco.ps.pagesvc.action=portletAction&amp;action=detalleAccion&amp;nemoTecnico=";
		setXmlAcciones(generarXml(lista, new String[] { link,
				"&amp;tipoMercado=1" }, MERCADO_ACCIONES));
	}

	/**
	 * Se encarga de consultar los derivados que deberian mostrarse en el ticker
	 * y generar el xml adecuado.
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	private void construirXMLDerivados() throws PersistenciaException {
		String link = "/pps/tibco/portalbvc/Home/Mercados/enlinea/derivados?com.tibco.ps.pagesvc.action=portletAction&amp;action=detalleDummy&amp;nemo=";
		List<InformacionTicker> lista = derivadosDao.derivadosTicker();
		setXmlDerivados(generarXml(lista, new String[] { link, "&amp;opcf=" },
				MERCADO_DERIVADOS));
	}

	/**
	 * Se encarga de consultar los titulos de renta fija que deberian mostrarse
	 * en el ticker y generar el xml adecuado.
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	private void construirXMLRentaFija() throws PersistenciaException {
		String link = "/pps/tibco/portalbvc/Home/Mercados/enlinea/rentafija?com.tibco.ps.pagesvc.action=portletAction&amp;action=detalleDummy&amp;nemo=";
		List<InformacionTicker> lista = rentaFijaDao.rentaFijaTicker();
		setXmlRentaFija(generarXml(lista, new String[] { link, "" },
				MERCADO_RENTA_FIJA));
	}

	/**
	 * Construye el xml como tal.
	 * 
	 * @param listaTitulos
	 *            listado de los titulos que deben ir en el ticker
	 * @param enlace
	 *            contiene el enlace (sufijo y prefijo) que debe construirse por
	 *            cada titulo. En el xml se construye de la forma enlace[0] +
	 *            nemo + enlace[1]
	 * @param mercado
	 *            nombre del mercado, útil para efectos de debug o para mostrar
	 *            el mensaje en caso de no tener titulos
	 * 
	 * @return the string builder
	 */
	private StringBuilder generarXml(List<InformacionTicker> listaTitulos,
			String[] enlace, String mercado) {
		log.debug("¡¡¡ Generando el xml para el ticker de " + mercado + " !!!");
		StringBuilder ret = new StringBuilder();
		DateFormat df = new SimpleDateFormat("HH:mm:ss a");
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es","CO"));
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		String mensaje = (listaTitulos == null || listaTitulos.isEmpty()) ? "No hay operaciones del mercado de "
				+ mercado
				: "null";

		// Generando el XML
		ret.append("<?xml version='1.0' encoding='UTF-8'?>");
		ret.append("<ticker>");
		ret.append("<mensaje texto = \"" + mensaje + "\"></mensaje>");
		ret.append("<titulos>");
		

		// se construye el tag por cada titulo si lo hay

		if (listaTitulos != null && !listaTitulos.isEmpty()) {
			for (InformacionTicker tit : listaTitulos) {
				int variacion = 3;
				if (tit.getVariacion() != null && tit.getVariacion() > 0) {
					variacion = 1;
				} else if (tit.getVariacion() != null && tit.getVariacion() < 0) {
					variacion = 2;
				}
				String hora = tit.getCalendarFecha() != null ? df.format(tit
						.getCalendarFecha().getTime()) : "";

				ret.append("<titulo>");
				ret.append("<hora>" + hora + "</hora>");
				ret.append("<nemotecnico>" + tit.getNemo().trim() + "</nemotecnico>");
				ret
						.append("<precio>" + nf.format(tit.getUltimoPrecioTasa())
								+ "</precio>");
				ret.append("<variacion>" + variacion + "</variacion>");
				ret.append("<link>" + enlace[0] + tit.getNemo().trim() + enlace[1]
						+ "</link>");
				ret.append("</titulo>");
			}
		}
		// se finaliza el XML
		ret.append("</titulos>");
		ret.append("</ticker>");
		log.debug(ret);
		return ret;
	}

	/*
	 * getters y setters de los daos, los usa spring para instanciar el bean.
	 */

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
	 * @param dao
	 *            the new acciones dao
	 */
	public void setAccionesDao(IAccionesDao dao) {
		accionesDao = dao;
	}

	/**
	 * Sets the renta fija dao.
	 * 
	 * @param dao
	 *            the new renta fija dao
	 */
	public void setRentaFijaDao(IRentaFijaDao dao) {
		rentaFijaDao = dao;
	}

	/**
	 * Gets the renta fija dao.
	 * 
	 * @return the renta fija dao
	 */
	public IRentaFijaDao getRentaFijaDao() {
		return rentaFijaDao;
	}

	/**
	 * Sets the derivados dao.
	 * 
	 * @param dao
	 *            the new derivados dao
	 */
	public void setDerivadosDao(IDerivadosDao dao) {
		derivadosDao = dao;
	}

	/**
	 * Gets the derivados dao.
	 * 
	 * @return the derivados dao
	 */
	public IDerivadosDao getDerivadosDao() {
		return derivadosDao;
	}

}
