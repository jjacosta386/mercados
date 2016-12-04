package co.com.bvc.portal.mercados.vista.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.mercados.servicio.IXMLGenerator;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ServletTicker.
 */
public class ServletTicker extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant CONTENT_TYPE. */
	@SuppressWarnings("unused")
	private static final String CONTENT_TYPE = "text/html";

	private Logger log = Logger.getLogger(this.getClass());

	/** The spring context. */
	ApplicationContext springContext;

	/**
	 * The Constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTicker() {
		super();
	}

	/**
	 * Do get.
	 * 
	 * @param request the request
	 * @param response the response
	 * 
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * Do post.
	 * 
	 * @param request the request
	 * @param response the response
	 * 
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String tempTipoMercado = request.getParameter("tipoMercado");
		String tipoMercado = "";

		if (tempTipoMercado == null || tempTipoMercado.equals("")) {
			return;
		} else {
			int pos = tempTipoMercado.indexOf("?");
			if (pos > 0) {
				tipoMercado = tempTipoMercado.substring(0, pos);
			} else {
				tipoMercado = tempTipoMercado;
			}
		}

		ControladorCacheJCS cache = JCSFactory
		.getRegionControladorCache(JCSFactory.MEM_30SECS);	
		String queryKey;
		
		/*
		 * TipoMercado: 01: Acciones 02: Rentas Fijas 03: Derivados
		 */
		// String tipoMercado = "01";
		if (tipoMercado.equalsIgnoreCase("01")) {
			
			queryKey = "TICKER_ACCIONES";			
			String ret = (String)cache.getObject(queryKey);
			
			if (ret == null) {
				synchronized (this) {
					ret = (String) cache.getObject(queryKey);
					if (ret == null) {
						log.debug("Procesando Ticker Acciones  ");
						this.springContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
						IXMLGenerator xmlGenerator = (IXMLGenerator) this.springContext.getBean("XMLGenerator");
						ret = xmlGenerator.getXmlAcciones().toString();
						cache.putObject(queryKey, ret);
					}
				}
			} else {
				log.debug("Cargando desde Ticker Acciones ");
			}		
			
			response.getWriter().print( ret );
			
		} else if (tipoMercado.equalsIgnoreCase("02")) {

			this.springContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			IXMLGenerator xmlGenerator = (IXMLGenerator) this.springContext.getBean("XMLGenerator");
			response.getWriter().print( xmlGenerator.getXmlRentaFija().toString() );		
			
		} else if (tipoMercado.equalsIgnoreCase("03")) {
			
			queryKey = "TICKER_DERIVADOS";
			
		    String ret = (String)cache.getObject(queryKey);
			
			if (ret == null) {
				synchronized (this) {
					ret = (String) cache.getObject(queryKey);
					if (ret == null) {
						log.debug("Procesando Ticker Derivados  ");
						this.springContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
						IXMLGenerator xmlGenerator = (IXMLGenerator) this.springContext.getBean("XMLGenerator");
						ret = xmlGenerator.getXmlDerivados().toString();
						cache.putObject(queryKey, ret);
					}
				}
			} else {
				log.debug("Cargando desde Ticker Derivados ");
			}		
			
			response.getWriter().print( ret );		
			
		}
	}


}