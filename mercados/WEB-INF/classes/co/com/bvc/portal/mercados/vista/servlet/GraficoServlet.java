package co.com.bvc.portal.mercados.vista.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.persistencia.IAccionesDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao;
import co.com.bvc.portal.mercados.persistencia.IDerivadosDao;
import co.com.bvc.portal.mercados.persistencia.IIndiceDao;
import co.com.bvc.portal.mercados.persistencia.IRentaFijaDao;
import co.com.bvc.portal.mercados.servicio.IIndices;
import co.com.bvc.portal.mercados.vista.IGeneradorDatos;
import co.com.bvc.portal.mercados.vista.cache.IIntradiaCache;
import co.com.bvc.portal.mercados.vista.imp.GeneradorCSV;

/**
 * @author BVC
 * @contributor Andres_Leon
 * @description Servlet implementation class GraficosServlet.
 * 
 */

public class GraficoServlet extends HttpServlet {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The Constant NUMERO_MAXIMO_DATOS. */
	private static final int NUMERO_MAXIMO_DATOS = 400;

	private static final String TIEMPO_HOY = "hoy";

	private static final String TIEMPO_AYER = "ayer";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8100652803298874616L;

	/** The spring context. */
	ApplicationContext springContext;

	/**
	 * The Constructor.
	 * 
	 * @author BVC
	 * @see HttpServlet#HttpServlet()
	 */

	public GraficoServlet() {
		super();
	}

	/**
	 * Service.
	 * 
	 * @param request
	 *            the request
	 * 
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			// super.service(request, response);

			String tipoG = request.getParameter("tipoG");
			String tipoGrafico = request.getParameter("tipo");
			String nemo = request.getParameter("nemo");
			String conf = request.getParameter("conf");
			String urlArchivo = request.getParameter("url");
			String titulo = request.getParameter("titulo");
			String tiempo = request.getParameter("tiempo");
			String tip1 = "";
			

			if (request.getParameter("tipo") != null) {
				if ("ETFMGC".equals(request.getParameter("tipo"))) {
					tipoG = "ETFMGC";
					tipoGrafico = "ETFMGC";
					tip1 = "E";
				} else if ("ACCIONMGC".equals(request.getParameter("tipo"))) {
					tipoG = "ACCIONMGC";
					tipoGrafico = "ACCIONMGC";
					tip1 = "M";
				}
			}
			if ((conf == null || conf.equals(""))
					&& (tipoGrafico == null || tipoGrafico.equals(""))) {
				return;
			}

			if (conf != null && conf.length() > 0) {
				String[] params = new String[4];

				params[0] = conf;
				params[1] = (titulo == null ? conf : titulo);
				params[2] = tipoG;
				params[3] = tiempo;
				log.info("solicitando archivo de configuracion: " + urlArchivo);

				log.debug("va a llamar a la función cargarArchivoConfiguracion conf= "
						+ conf);

				this.cargarArchivoConfiguracion(urlArchivo, params, request,
						response);
				return;
			}

			if (tipoGrafico.equalsIgnoreCase("INDICE")) {
				log.debug("se metio en el if INDICE conf= " + conf);
				this.cargarDatosGraficoIndice(nemo, request, response);
			} else if (tipoGrafico.equalsIgnoreCase("ACCION")) {
				this.cargarDatosGraficoAccion(nemo, request, response, true);
			} else if (tipoGrafico.equalsIgnoreCase("ACCIONMGC")
					|| tipoGrafico.equalsIgnoreCase("ETFMGC")) {
				this.cargarDatosGraficoAccionMGC(nemo, tip1, request, response,
						true);
			} else if (tipoGrafico.equalsIgnoreCase("DERIVADO")
					|| tipoGrafico.equalsIgnoreCase("DRVX")) {
				this.cargarDatosGraficoDerivadosTodos(nemo, request, response);
			} else if (tipoGrafico.equalsIgnoreCase("RENTAFIJA")) {
				this.cargarDatosGraficoRF(nemo, request, response);
			} else if (tipoGrafico.equalsIgnoreCase("DIVISAS")) {
				this.cargarDatosGraficoDivisa(request, response);
			} else if (tipoGrafico.equalsIgnoreCase("BVC")) {
				this.cargarDatosGraficoAccion(nemo, request, response, false);
			}
		} catch (Exception e) {
			log.error("Error cargando el archivo de configuración: " + e);
		}
	}

	/**
	 * Cargar datos grafico indice.
	 * 
	 * @param idIndice
	 *            the id indice
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void cargarDatosGraficoIndice(String idIndice,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("Entró a la función cargarDatosGraficoIndice conf= "
				+ request.getParameter("conf"));
		log.debug("Entró a la función cargarDatosGraficoIndice mercInd= "
				+ request.getParameter("mercInd"));
		log.debug("Entró a la función cargarDatosGraficoIndice nemo= "
				+ request.getParameter("nemo"));

		try {
			String tiempo = request.getParameter("tiempo");
			String ind = request.getParameter("nemo");
			String mercadoInd = request.getParameter("mercInd");
			String homesi= request.getParameter("home");
			String home = "no";
			if ("SI".equals(homesi) ) {
				home="SI";
			}
			List<ICierre> indicesIntradia = new ArrayList<ICierre>();
			List<ICierre> listaAyer = new ArrayList<ICierre>();

			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IIndiceDao indiceDao = (IIndiceDao) this.springContext
					.getBean("indicesDao");
			IIntradiaCache cache = (IIntradiaCache) this.springContext
					.getBean("intradiaCache");

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_AYER.equals(tiempo)) {log.debug("Entró a la tiempo = "+ request.getParameter("tiempo"));
				// if ("ITES".equals(ind)) {
				if ((mercadoInd !=null || mercadoInd.trim().length() != 0) && !"RV".equals(mercadoInd)) {
					if ("RF".equals(mercadoInd)) {
					   log.debug("Entró en el if mercadoInd de  la función cargarDatosGraficoIndice conf= "
							   + request.getParameter("conf"));
					   if("SI".equals(home)){
						   listaAyer = indiceDao.cargarHistoricoIndiceHOMERF(idIndice,
								   UtilFechas.getHoy("yyyy-MM-dd"));
					   }
					   else{
						   listaAyer = indiceDao.cargarHistoricoIndiceRF(idIndice,
								   UtilFechas.getHoy("yyyy-MM-dd"));
					   }
					} else if(("MM".equals(mercadoInd))){
						log.debug("Entró en el if mercadoInd de  la función cargarDatosGraficoIndice conf= "
								   + request.getParameter("conf"));
						if("SI".equals(home)){
						   listaAyer = indiceDao.cargarHistoricoIndiceHOMEMM(idIndice,
								   UtilFechas.getHoy("yyyyMMdd"));
						}else{
							listaAyer = indiceDao.cargarHistoricoIndiceMM(idIndice,
									   UtilFechas.getHoy("yyyyMMdd"));
						}
						
					}
				} else {
					if("SI".equals(home)){log.debug("Entró cargarHistoricoHOMEIndice ");
						listaAyer = indiceDao.cargarHistoricoHOMEIndice(idIndice,
								UtilFechas.getHoy("yyyyMMdd"));
					}else{log.debug("Entró cargarHistoricoIndice ");
						listaAyer = indiceDao.cargarHistoricoIndice(idIndice,
								UtilFechas.getHoy("yyyyMMdd"));
					}
				}
				verificarUltimoDia(listaAyer);
			}
			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_HOY.equals(tiempo)) {log.debug("Entró a la tiempo = "+ request.getParameter("tiempo"));
			  if ((mercadoInd !=null || mercadoInd.trim().length() != 0) && "RF".equals(mercadoInd)) {	
				indicesIntradia = cache.getIndicesIntradiaRF(idIndice);
				if (indicesIntradia.size() > NUMERO_MAXIMO_DATOS) {
					indicesIntradia = discriminarCada3Min(indicesIntradia);
				}
			  }	
			  else{
				  indicesIntradia = cache.getIndicesIntradia(idIndice);
					if (indicesIntradia.size() > NUMERO_MAXIMO_DATOS) {
						indicesIntradia = discriminarCada3Min(indicesIntradia);
					}  
			  }
			}

			indicesIntradia.addAll(listaAyer);

			IGeneradorDatos idatos = new GeneradorCSV();
			byte[] datos;

			datos = idatos
					.generarSalida(new ArrayList<ICierre>(indicesIntradia));

			response.getOutputStream().write(datos);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cargar datos grafico divisa.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public void cargarDatosGraficoDivisa(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IIntradiaCache cache = (IIntradiaCache) this.springContext
					.getBean("intradiaCache");

			List<ICierre> dolarIntradia = cache.getDolaresIntradia();
			if (dolarIntradia.size() > NUMERO_MAXIMO_DATOS) {
				dolarIntradia = discriminarCada3Min(dolarIntradia);
			}

			IGeneradorDatos idatos = new GeneradorCSV();
			byte[] datos;

			datos = idatos.generarSalida(new ArrayList<ICierre>(dolarIntradia));

			response.getOutputStream().write(datos);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cargar datos grafico accion.
	 * 
	 * @param nemo
	 *            the nemo
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param delay
	 *            the delay
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void cargarDatosGraficoAccion(String nemo,
			HttpServletRequest request, HttpServletResponse response,
			boolean delay) throws ServletException, IOException {
		try {
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			String tiempo = request.getParameter("tiempo");
			List<ICierre> accionesIntradia = new ArrayList<ICierre>();
			List<ICierre> lista = new ArrayList<ICierre>();
			IAccionesDao accionDao = (IAccionesDao) this.springContext
					.getBean("accionesDao");
			IIntradiaCache cache = (IIntradiaCache) this.springContext
					.getBean("intradiaCache");

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_AYER.equals(tiempo)) {
				lista = accionDao.cargarHistoricoTitulo(nemo);
				verificarUltimoDia(lista);
			}

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_HOY.equals(tiempo)) {
				accionesIntradia = cache.getAccionesIntradia(nemo, delay);
				if (accionesIntradia.size() > NUMERO_MAXIMO_DATOS) {
					accionesIntradia = discriminarCada3Min(accionesIntradia);
				}
			}

			accionesIntradia.addAll(lista);
			IGeneradorDatos idatos = new GeneradorCSV();
			byte[] datos;
			datos = idatos.generarSalida(new ArrayList<ICierre>(
					accionesIntradia));
			response.getOutputStream().write(datos);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cargar datos grafico accion MGC.
	 * 
	 * @param nemo
	 *            the nemo
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param delay
	 *            the delay
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void cargarDatosGraficoAccionMGC(String nemo, String tipoMGC,
			HttpServletRequest request, HttpServletResponse response,
			boolean delay) throws ServletException, IOException {
		try {
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			String tiempo = request.getParameter("tiempo");
			List<ICierre> accionesIntradia = new ArrayList<ICierre>();
			List<ICierre> lista = new ArrayList<ICierre>();
			IAccionesMGCDao accionMGCDao = (IAccionesMGCDao) this.springContext
					.getBean("accionesMGCDao");
			IIntradiaCache cache = (IIntradiaCache) this.springContext
					.getBean("intradiaCache");

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_AYER.equals(tiempo)) {
				lista = accionMGCDao.cargarHistoricoMGCTitulo(nemo, tipoMGC);
				verificarUltimoDia(lista);
			}

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_HOY.equals(tiempo)) {
				accionesIntradia = cache.getAccionesMGCIntradia(nemo, delay);
				if (accionesIntradia.size() > NUMERO_MAXIMO_DATOS) {
					accionesIntradia = discriminarCada3Min(accionesIntradia);
				}
			}

			accionesIntradia.addAll(lista);
			IGeneradorDatos idatos = new GeneradorCSV();
			byte[] datos;
			datos = idatos.generarSalida(new ArrayList<ICierre>(
					accionesIntradia));
			response.getOutputStream().write(datos);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cargar datos grafico rf.
	 * 
	 * @param nemo
	 *            the nemo
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void cargarDatosGraficoRF(String nemo, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long t1 = System.currentTimeMillis();
		try {
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IRentaFijaDao rfDao = (IRentaFijaDao) this.springContext
					.getBean("rentaFijaDao");
			IIntradiaCache cache = (IIntradiaCache) this.springContext
					.getBean("intradiaCache");
			String tiempo = request.getParameter("tiempo");
			List<ICierre> lista = new ArrayList<ICierre>();
			List<ICierre> rentasFijaIntradia = new ArrayList<ICierre>();

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_AYER.equals(tiempo)) {
				lista = rfDao.graficaRentaFija(false, nemo);
				verificarUltimoDia(lista);
			}

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_HOY.equals(tiempo)) {
				rentasFijaIntradia = cache.getRentasFijaIntradia(nemo);

				if (rentasFijaIntradia.size() > NUMERO_MAXIMO_DATOS) {
					rentasFijaIntradia = discriminarCada3Min(rentasFijaIntradia);
				}
			}

			rentasFijaIntradia.addAll(lista);

			IGeneradorDatos idatos = new GeneradorCSV();
			byte[] datos;

			datos = idatos.generarSalida(new ArrayList<ICierre>(
					rentasFijaIntradia));

			response.getOutputStream().write(datos);

		} catch (Throwable e) {
			e.printStackTrace();
		}

		Long t2 = System.currentTimeMillis();
		Long t = (t2 - t1) / 1000;
		log.info("El tiempo en el método cargarDatosGraficoRF de GraficoServlet es ="
				+ t);
	}

	public void cargarDatosGraficoDerivadosTodos(String nemo,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String opcf = request.getParameter("opcf");
			if ("opcf".equals(opcf)) {
				cargarDatosGraficoDerivado(nemo, request, response);
			} else if (nemo != null && nemo != "") {
				this.springContext = WebApplicationContextUtils
						.getWebApplicationContext(this.getServletContext());
				IDerivadosDao derivadosDao = (IDerivadosDao) this.springContext
						.getBean("derivadosDao");
				Integer cuenta = derivadosDao.verificarContratoDerivados(nemo);
				if (cuenta == 0)
					this.cargarDatosGraficoDerivadoDrvx(nemo, request, response);
				else
					this.cargarDatosGraficoDerivado(nemo, request, response);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cargar datos grafico derivado.
	 * 
	 * @param nemo
	 *            the nemo
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void cargarDatosGraficoDerivado(String nemo,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String opcf = request.getParameter("opcf");
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IDerivadosDao derivadosDao = (IDerivadosDao) this.springContext
					.getBean("derivadosDao");
			IIntradiaCache cache = (IIntradiaCache) this.springContext
					.getBean("intradiaCache");
			String tiempo = request.getParameter("tiempo");
			List<ICierre> lista = new ArrayList<ICierre>();
			List<ICierre> derivadosIntradia = new ArrayList<ICierre>();

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_AYER.equals(tiempo)) {
				if ("opcf".equals(opcf)) {
					lista = derivadosDao.cargarHistoricoDerivadoOPCF(nemo);
				} else {
					lista = derivadosDao.cargarHistoricoDerivado(nemo);
				}
				verificarUltimoDia(lista);
			}

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_HOY.equals(tiempo)) {
				derivadosIntradia = cache.getDerivadosIntradia(nemo,
						"opcf".equals(opcf));
				if (derivadosIntradia.size() > NUMERO_MAXIMO_DATOS) {
					derivadosIntradia = discriminarCada3Min(derivadosIntradia);
				}
			}

			derivadosIntradia.addAll(lista);

			IGeneradorDatos idatos = new GeneradorCSV();
			byte[] datos;

			datos = idatos.generarSalida(new ArrayList<ICierre>(
					derivadosIntradia));

			response.getOutputStream().write(datos);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cargar datos grafico derivado energético.
	 * 
	 * @param nemo
	 *            the nemo
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void cargarDatosGraficoDerivadoDrvx(String nemo,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IDerivadosDao derivadosDao = (IDerivadosDao) this.springContext
					.getBean("derivadosDrvxDao");
			IIntradiaCache cache = (IIntradiaCache) this.springContext
					.getBean("intradiaCache");
			String tiempo = request.getParameter("tiempo");
			List<ICierre> lista = new ArrayList<ICierre>();
			List<ICierre> derivadosIntradia = new ArrayList<ICierre>();

			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_AYER.equals(tiempo)) {
				lista = derivadosDao.cargarHistoricoDerivado(nemo);
				verificarUltimoDia(lista);
			}
			if (tiempo == null || tiempo.trim().length() == 0
					|| TIEMPO_HOY.equals(tiempo)) {
				derivadosIntradia = cache.getDerivadosDrvxIntradia(nemo);
				if (derivadosIntradia.size() > NUMERO_MAXIMO_DATOS) {
					derivadosIntradia = discriminarCada3Min(derivadosIntradia);
				}
			}

			derivadosIntradia.addAll(lista);

			IGeneradorDatos idatos = new GeneradorCSV();
			byte[] datos;

			datos = idatos.generarSalida(new ArrayList<ICierre>(
					derivadosIntradia));

			response.getOutputStream().write(datos);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cargar archivo configuracion.
	 * 
	 * @param urlArchivo
	 *            the url archivo
	 * @param filtro
	 *            the filtro
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void cargarArchivoConfiguracion(String urlArchivo, String[] filtro,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("Ingresó a la función cargarArchivoConfiguracion  conf= "
				+ filtro[0]);

		this.springContext = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());

		IIndices indicesComp = (IIndices) this.springContext.getBean("indices");

		StringBuffer archivo = new StringBuffer("");

		int tmp = 0;

		InputStream input = request.getSession().getServletContext()
				.getResourceAsStream(urlArchivo);

		if (input == null) {
			throw new ServletException(
					("El archivo de configuración no se encuentra: " + urlArchivo));
		}

		try {
			// input = new FileInputStream(file);

			while ((tmp = input.read()) != -1) {
				// archivo.append(Integer.valueOf(tmp).byteValue());
				// response.getOutputStream().write(tmp);
				archivo.append((char) tmp);
			}

			String dataSet = "";
			String mercadoInd = request.getParameter("mercInd");
			String mercado="RV";

			if ("INDICE".equalsIgnoreCase(filtro[2])) {
				log.debug("Ingresó a la función cargarArchivoConfiguracion en el if Indice  conf= "
						+ filtro[0]);
				dataSet = indicesComp.compararIndices(filtro[0], filtro[1],
						null, filtro[2], filtro[3],mercadoInd,null);
				//log.debug("Estoy en INDICE: " + dataSet);
			} else if ("ACCION".equalsIgnoreCase(filtro[2])
					|| "BVC".equalsIgnoreCase(filtro[2])) {
				String nemo = filtro[0];
				List<String> indicesAComparar = new ArrayList<String>();
				List<String> mercadosIndices = new ArrayList<String>();
				indicesAComparar.add("ICAP");
				indicesAComparar.add("ICSC");
				indicesAComparar.add("IIRS");
				indicesAComparar.add("CTES");
				indicesAComparar.add("CTLP");
				indicesAComparar.add("CTCP");
				indicesAComparar.add("IIBR");
				mercadosIndices.add("ICAPRV");
				mercadosIndices.add("ICSCRV");
				mercadosIndices.add("IIRSRV");
				mercadosIndices.add("CTESRF");
				mercadosIndices.add("CTLPRF");
				mercadosIndices.add("CTCPRF");
				mercadosIndices.add("IIBRMM");
				dataSet = indicesComp.compararIndices(nemo, nemo,
						indicesAComparar, filtro[2], filtro[3],mercado,mercadosIndices);
				//log.debug("Estoy en ACCION: " + dataSet);
			} else if ("ACCIONMGC".equalsIgnoreCase(filtro[2])
					|| "ETFMGC".equalsIgnoreCase(filtro[2])) {
				String nemo = filtro[0];
				List<String> indicesAComparar = new ArrayList<String>();
				// indicesAComparar.add("IGBC");
				// indicesAComparar.add("IC20");
				// indicesAComparar.add("ITES");
				dataSet = indicesComp.compararIndices(nemo, nemo,
						indicesAComparar, filtro[2], filtro[3],mercado,null);
				//log.debug("Estoy en ACCION: " + dataSet);
			} else if ("DERIVADOS".equals(filtro[2])) {
				String opcf = request.getParameter("opcf");
				if ("opcf".equals(opcf)) {
					filtro[1] = "opcf";
				} else {
					filtro[1] = "futuro";
				}
			}

			String tmp1 = new String(archivo);
			tmp1 = tmp1.replaceAll("#param1#", filtro[0]);
			tmp1 = tmp1.replaceAll("#param2#", filtro[1]);
			tmp1 = tmp1.replaceAll("#paramDataSet#", dataSet);

			response.getOutputStream().write(tmp1.getBytes("UTF-8"));

		} catch (Exception e) {
			log.error("Error cargando el archivo de configuración: "
					+ urlArchivo, e);
		}
		log.debug("Salio de la función cargarArchivoConfiguracion  conf= "
				+ filtro[0]);
	}

	/**
	 * Verificar ultimo dia.
	 * 
	 * @param lista
	 *            the lista
	 */
	private void verificarUltimoDia(List<ICierre> lista) {
		if (lista == null || lista.isEmpty())
			return;
		ICierre ultimoCierre = lista.get(0);
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (sdf.format(new Date()).equals(
				sdf.format(ultimoCierre.getFechaHora().getTime()))) {
			lista.remove(0);
		}
	}

	/**
	 * Discriminar cada3 min.
	 * 
	 * @param listaIntradia
	 *            the lista intradia
	 * 
	 * @return the list< i cierre>
	 */
	private List<ICierre> discriminarCada3Min(List<ICierre> listaIntradia) {
		if (listaIntradia == null || listaIntradia.isEmpty()) {
			return new ArrayList<ICierre>();
		}
		List<ICierre> temp = new ArrayList<ICierre>();
		for (int j = 0; j < listaIntradia.size(); j++) {
			if (j % 2 == 0) {
				temp.add(listaIntradia.get(j));
			}
		}
		return temp;
	}
}
/********************************************** End of GraficoServlet.java *****************************************************/