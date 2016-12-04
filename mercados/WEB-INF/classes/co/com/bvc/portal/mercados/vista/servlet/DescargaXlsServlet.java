package co.com.bvc.portal.mercados.vista.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import co.com.bvc.portal.comun.servicio.IServicioXls;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.BusquedaDerivados;
import co.com.bvc.portal.mercados.modelo.Derivado;
import co.com.bvc.portal.mercados.modelo.DerivadoResumenContrato;
import co.com.bvc.portal.mercados.modelo.DerivadoResumenExtendido;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumen;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.ResumenBoceas;
import co.com.bvc.portal.mercados.modelo.ResumenIndice;
import co.com.bvc.portal.mercados.modelo.ResumenIndiceLista;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.persistencia.IAccionesBoceasDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesReposDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesTTVDao;
import co.com.bvc.portal.mercados.servicio.IAcciones;
import co.com.bvc.portal.mercados.servicio.IDerivados;
import co.com.bvc.portal.mercados.servicio.IIndices;
import co.com.bvc.portal.mercados.servicio.IRentaFija;
import co.com.bvc.portal.mercados.vista.form.FormularioIndices;

/**
 * @author BVC
 * @contributor Andres_Leon
 * @description Servlet implementation class {@link DescargaXlsServlet}.
 */

public class DescargaXlsServlet extends HttpServlet {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8500845602822082052L;

	private static final double RANGO_MAXIMO_MILISEGUNDOS = 1000 * 60 * 60 * 12
			* 365.25; // 6 meses

	private static final String TEXTO_SALIDA_ERROR_RANGO = "Recuerde que la descarga de éste tipo de información está condicionada así: "
			+ "\r\n\r\n1. Debe seleccionar ambas fechas. "
			+ "\r\n2. La fecha inicial debe ser menor a la fecha final"
			+ "\r\n3. El Rango máximo de descarga es de 6 meses.";

	/** The spring context. */
	ApplicationContext springContext;

	/**
	 * The Constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public DescargaXlsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	/**
	 * Do get.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(request, response);
		this.doPost(request, response);
	}

	/**
	 * Do post.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(request, response);
		log.info("Entrando al doPost");
		String archivo = request.getParameter("archivo");
		String tipoMercado = request.getParameter("tipoMercado");
		if (StringUtils.isEmpty(archivo)) {
			return;
		}

		/*
		 * Archivo: derivados, derivados_detalle, rentafija, rentafija_detalle,
		 * indices, acciones, acciones_detalle
		 */

		// String tipoMercado = "01";
		if (archivo.equalsIgnoreCase("boceas_detalle")) {
			construirXlsBoceasDetalle(request, response);
		} else if (null != request.getParameter("tipoMercado")
				&& tipoMercado.equalsIgnoreCase("4")) {
			construirXlsBoceas(request, response);
		} else if (null != request.getParameter("tipoMercado")
				&& tipoMercado.equalsIgnoreCase("5")) {
			construirXlsBoceasSimul(request, response);
		} else if (null != request.getParameter("tipoMercado")
				&& tipoMercado.equalsIgnoreCase("3")) {
			if (archivo.equalsIgnoreCase("acciones_detalle"))
				construirXlsAccionesDetalle(request, response);
			else
				construirXlsTTVs(request, response);
		} else if (null != request.getParameter("tipoMercado")
				&& tipoMercado.equalsIgnoreCase("2")) {
			if (archivo.equalsIgnoreCase("acciones_detalle"))
				construirXlsAccionesDetalle(request, response);
			else
				construirXlsRepos(request, response);
		} else if (archivo.equalsIgnoreCase("derivados")) {
			construirXlsDerivados(request, response);
		} else if (archivo.equalsIgnoreCase("derivados_detalle")) {
			construirXlsDerivadosDetalle(request, response);
		} else if (archivo.equalsIgnoreCase("derivados_detalle_drvx")) {
			construirXlsDerivadosDetalleDrvx(request, response);
		} else if (archivo.equalsIgnoreCase("rentafija")) {
			construirXlsRentaFija(request, response);
		} else if (archivo.equalsIgnoreCase("rentafija_detalle")) {
			construirXlsRentaFijaDetalle(request, response);
		} else if (archivo.equalsIgnoreCase("indices")) {
			construirXlsIndices(request, response);
		} else if (archivo.equalsIgnoreCase("acciones")) {
			construirXlsAcciones(request, response);
		} else if (archivo.equalsIgnoreCase("acciones_detalle")) {
			construirXlsAccionesDetalle(request, response);
		}
	}

	/**
	 * Construir xls derivados.
	 * 
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
	public void construirXlsDerivados(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls derivados");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IDerivados derivados = (IDerivados) this.springContext
					.getBean("derivados");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");
			String anio = request.getParameter("anio");
			String mes = request.getParameter("mes");
			String dia = request.getParameter("dia");
			BusquedaDerivados busquedaDerivados = new BusquedaDerivados();
			busquedaDerivados.setAnioFecha(anio);
			busquedaDerivados.setMesFecha(mes);
			busquedaDerivados.setDiaFecha(dia);
			if (BusquedaDerivados.isFechaHoy(busquedaDerivados))
				busquedaDerivados = new BusquedaDerivados();
			Set<Derivado> derivadosResumen = derivados
					.getDerivados(busquedaDerivados);

			IDerivados derivadosDrvx = (IDerivados) this.springContext
					.getBean("derivadosDrvx");

			Set<Derivado> derivadosResumenDrvx = derivadosDrvx
					.getDerivados(busquedaDerivados);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=Derivados.xls");

			derivadosResumen.addAll(derivadosResumenDrvx);
			servicioXls.getXls(derivadosResumen, response.getOutputStream(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Construir xls derivados detalle.
	 * 
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
	public void construirXlsDerivadosDetalle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls derivados detalle");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IDerivados derivados = (IDerivados) this.springContext
					.getBean("derivados");

			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String nemo = request.getParameter("nemo");
			String opcf = request.getParameter("opcf");

			BusquedaDerivados busquedaDerivados = new BusquedaDerivados();

			DerivadoResumenContrato resumenContrato = new DerivadoResumenContrato();

			if (StringUtils.isEmpty(opcf) || !opcf.equalsIgnoreCase("opcf"))
				resumenContrato = derivados.getDerivadoResumenContrato(nemo,
						busquedaDerivados);
			else
				derivados.getDerivadoResumenContratoOPCF(nemo,
						busquedaDerivados);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ nemo + ".xls");
			List<DerivadoResumenContrato> listaRes = new ArrayList<DerivadoResumenContrato>();
			listaRes.add(resumenContrato);

			servicioXls.getXls(listaRes, response.getOutputStream(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Construir xls derivados detalle drvx.
	 * 
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
	public void construirXlsDerivadosDetalleDrvx(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls derivados detalle drvx");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IDerivados derivados = (IDerivados) this.springContext
					.getBean("derivadosDrvx");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String nemo = request.getParameter("nemo");
			String fechaIni = request.getParameter("fechaIni");
			String fechaFin = request.getParameter("fechaFin");
			String[] fechaIniDet = fechaIni.split("-");
			String[] fechaFinDet = fechaFin.split("-");

			BusquedaDerivados busquedaDerivados = new BusquedaDerivados();

			busquedaDerivados.setAnioFecha(fechaIniDet[0]);
			busquedaDerivados.setMesFecha(fechaIniDet[1]);
			busquedaDerivados.setDiaFecha(fechaIniDet[2]);

			busquedaDerivados.setAnioFechaFin(fechaFinDet[0]);
			busquedaDerivados.setMesFechaFin(fechaFinDet[1]);
			busquedaDerivados.setDiaFechaFin(fechaFinDet[2]);

			List<DerivadoResumenExtendido> resumenContrato;

			resumenContrato = derivados.getDerivadoResumenExtendidoGrafica(
					nemo, busquedaDerivados);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ nemo + ".xls");

			servicioXls.getXls(resumenContrato, response.getOutputStream(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Construir xls renta fija.
	 * 
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
	public void construirXlsRentaFija(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls renta fija");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IRentaFija rentaFija = (IRentaFija) this.springContext
					.getBean("rentafija");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String fechaIni = request.getParameter("fechaIni");
			String deuda = request.getParameter("deuda");
			String ope = request.getParameter("ope") == null ? IRentaFija.ALL_OPE
					: request.getParameter("ope");
			String sesion = request.getParameter("sesion");
			String tipoMercado = request.getParameter("tipoMercado") == null ? IRentaFija.MERCADO_TODOS
					: request.getParameter("tipoMercado");
			RentaFijaResumen rfr = new RentaFijaResumen();
			if (StringUtils.isEmpty(fechaIni))
				rfr = rentaFija.getResumenMerge(null, null, deuda, ope, sesion,
						tipoMercado);
			else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date _dateFin = df.parse(fechaIni);

				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(_dateFin);

				gc.set(Calendar.DAY_OF_MONTH, gc.get(Calendar.DAY_OF_MONTH) + 1);

				_dateFin = gc.getTime();

				String fechaFin = df.format(_dateFin);
				fechaFin += ":00:00";
				fechaIni += ":00:00";
				rfr = rentaFija.getResumenMerge(fechaIni, fechaFin, deuda, ope,
						sesion, tipoMercado);
			}
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=RentaFija.xls");
			List<RentaFijaResumen> resp = new ArrayList<RentaFijaResumen>();
			resp.add(rfr);

			servicioXls.getXls(resp, response.getOutputStream(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Construir xls renta fija detalle.
	 * 
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
	public void construirXlsRentaFijaDetalle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls renta fija detalle");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IRentaFija rentaFija = (IRentaFija) this.springContext
					.getBean("rentafija");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");
			String dia1 = request.getParameter("dia1");
			String dia2 = request.getParameter("dia2");
			String nemo = request.getParameter("nemo");

			if (validarFechas(dia1, dia2, "yyyy-MM-dd", response)) {
				List<RentaFijaSumaOperacion> res = rentaFija
						.getSumaOperacionesGrafica(dia1, dia2, nemo);

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=RentaFijaDetalle.xls");

				servicioXls.getXls(res, response.getOutputStream(), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Construir xls indices.
	 * 
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
	public void construirXlsIndices(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls indices");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IIndices indices = (IIndices) this.springContext.getBean("indices");
			FormularioIndices form = new FormularioIndices();
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String codIndice = request.getParameter("codIndice");
			String mercado = request.getParameter("tipoMI");
			String fecha = request.getParameter("fecha");
			List<ResumenIndice> indiceResumen = null;
			List<ResumenIndiceLista> indiceResumenLista = null;
			
			
			if ("RENTA FIJA".equalsIgnoreCase(mercado)) {
				indiceResumen = indices.obtenerResumenRangoFechaRF(
						request.getParameter("codIndice"),
						request.getParameter("fechaMin"),
						request.getParameter("fechaMax"));
			}else if ("MERCADO MONETARIO".equalsIgnoreCase(mercado)) {
				indiceResumen = indices.obtenerResumenRangoFechaMM(
						request.getParameter("codIndice"),
						request.getParameter("fechaMin"),
						request.getParameter("fechaMax"));
			} else if("VARIABLE".equalsIgnoreCase(mercado)) {
				indiceResumen = indices.obtenerResumenRangoFecha(
						request.getParameter("codIndice"),
						request.getParameter("fechaMin"),
						request.getParameter("fechaMax"));
			}else if("RESUMEN".equalsIgnoreCase(mercado)) {
				indiceResumenLista = indices.obtenerResumenIndicesXLS(
						fecha);
			}

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=Indices.xls");
            
			if("RESUMEN".equalsIgnoreCase(mercado)){
				servicioXls.getXls(indiceResumenLista, response.getOutputStream(), null);
				
		    }else  {
		    	servicioXls.getXls(indiceResumen, response.getOutputStream(), null);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Construir xls acciones.
	 * 
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
	public void construirXlsAcciones(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls acciones");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IAcciones acciones = (IAcciones) this.springContext
					.getBean("accionesServicio");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String fecha = request.getParameter("fecha");
			log.info(" construirXlsAcciones fecha: " + fecha);
			int resultados = Integer.parseInt(request
					.getParameter("resultados"));

			String tipoM = request.getParameter("tipoMercado");
			log.info(" construirXlsAcciones tipoMercado: " + tipoM);

			int tipoMercado = 0;

			try {
				tipoMercado = tipoM == null || tipoM.length() == 0 ? 1
						: Integer.parseInt(tipoM);
				log.info(" construirXlsAcciones tipoMercadoParsed: "
						+ tipoMercado);
			} catch (Throwable e) {
				log.warn(e.getMessage());
			}

			List<ResumenAccion> res = null;

			res = acciones.accionesMasTranzadasDia(fecha, resultados,
					tipoMercado);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=Acciones.xls");

			servicioXls.getXls(res, response.getOutputStream(), null);
		} catch (Exception e) {
			log.error(e);
		}

	}

	/**
	 * Construir xls ttvs.
	 * 
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
	public void construirXlsTTVs(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls acciones");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());

			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String fecha = request.getParameter("fecha");
			log.info(" construirXlsTTVs fecha: " + fecha);
			// int resultados = Integer.parseInt(request
			// .getParameter("resultados"));

			IAccionesTTVDao ttv = (IAccionesTTVDao) this.springContext
					.getBean("accionesTTVDao");

			int tipoMercado = 0;

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			if (sdf.format(new Date()).equalsIgnoreCase(fecha)) {
				fecha = null;
			}

			List<ResumenBoceas> resttv = ttv.cargarMasTranzadasExcel(fecha);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=AccionesTTvs.xls");
			servicioXls.getXls(resttv, response.getOutputStream(), null);

		} catch (Exception e) {
			log.error(e);
		}

	}

	/**
	 * Construir xls repos.
	 * 
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
	public void construirXlsRepos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls acciones");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());

			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String fecha = request.getParameter("fecha");
			log.info(" construirXlsTTVs fecha: " + fecha);
			// int resultados = Integer.parseInt(request
			// .getParameter("resultados"));

			IAccionesReposDao repo = (IAccionesReposDao) this.springContext
					.getBean("accionesReposDao");

			int tipoMercado = 0;

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			if (sdf.format(new Date()).equalsIgnoreCase(fecha)) {
				fecha = null;
			}

			List<ResumenBoceas> resrepo = repo.cargarMasTranzadasExcel(fecha);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=AccionesRepos.xls");
			servicioXls.getXls(resrepo, response.getOutputStream(), null);

		} catch (Exception e) {
			log.error(e);
		}

	}

	/**
	 * Construir xls acciones detalle.
	 * 
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
	public void construirXlsAccionesDetalle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls acciones detalle");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IAcciones acciones = (IAcciones) this.springContext
					.getBean("accionesServicio");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");
			String nemo = request.getParameter("nemo");
			int tipoMercado = Integer.parseInt(request
					.getParameter("tipoMercado"));
			String fechaIni = request.getParameter("fechaIni");
			String fechaFin = request.getParameter("fechaFin");
			if (validarFechas(fechaIni, fechaFin, "yyyy-MM-dd", response)) {
				List<TituloAccion> resp = acciones.cargarDatosTituloDia(nemo,
						tipoMercado, fechaIni, fechaFin);

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=AccionDetalle.xls");

				servicioXls.getXls(resp, response.getOutputStream(), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Valida si las fechas dadas cumplen con el rango máximo establecido . En
	 * caso de que no sea un rango valido, usa el outputstream para devolver un
	 * archivo de texto con el texto predefinido de error, se encarga ademas de
	 * cerrar el outputStream.
	 * 
	 * @param fechaIni
	 *            Fecha inicial del rango
	 * @param fechaFin
	 *            Fecha final del rango
	 * @param patron
	 *            formato en el que se encuentran las fechas
	 * @return <strong>true</strong> si el rango es correcto <br/>
	 *         <strong>false</stong> en caso contrario
	 */
	private boolean validarFechas(String fechaIni, String fechaFin,
			String patron, HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(patron);
			Calendar calIni = new GregorianCalendar();
			calIni.setTime(sdf.parse(fechaIni));
			Calendar calFin = new GregorianCalendar();
			calFin.setTime(sdf.parse(fechaFin));
			if (calIni.compareTo(calFin) < 0
					&& RANGO_MAXIMO_MILISEGUNDOS >= (calFin.getTimeInMillis() - calIni
							.getTimeInMillis())) {
				return true;
			} else {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=InstruccionesDescargaDetalleRentaFija.txt");
				response.getOutputStream().write(
						TEXTO_SALIDA_ERROR_RANGO.getBytes());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} catch (Exception ex) {
			return false;
		}
		return false;
	}

	/**
	 * Construir xls acciones detalle.
	 * 
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
	public void construirXlsBoceasDetalle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls acciones detalle");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IAcciones acciones = (IAcciones) this.springContext
					.getBean("accionesServicio");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");
			String nemo = request.getParameter("nemo");
			int tipoMercado = Integer.parseInt(request
					.getParameter("tipoMercado"));
			String fechaIni = request.getParameter("fechaIni");
			String fechaFin = request.getParameter("fechaFin");
			if (validarFechas(fechaIni, fechaFin, "yyyy-MM-dd", response)) {
				List<RentaFijaSumaOperacion> resp = acciones
						.getSumaOperacionesGraficaBocea(fechaIni, fechaFin,
								nemo);

				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=AccionDetalle.xls");

				servicioXls.getXls(resp, response.getOutputStream(), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Construir xls Boceas.
	 * 
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
	public void construirXlsBoceas(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls boceas");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IAccionesBoceasDao acciones = (IAccionesBoceasDao) this.springContext
					.getBean("accionesBoceasDao");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String fecha = request.getParameter("fecha");
			log.info(" construirXlsBoceas fecha: " + fecha);

			String tipoM = request.getParameter("tipoMercado");
			log.info(" construirXlsBoceas tipoMercado: " + tipoM);

			int tipoMercado = 0;

			try {
				tipoMercado = tipoM == null || tipoM.length() == 0 ? 4
						: Integer.parseInt(tipoM);
				log.info(" construirXlsAcciones tipoMercadoParsed: "
						+ tipoMercado);
			} catch (Throwable e) {
				log.warn(e.getMessage());
			}

			List<ResumenBoceas> res = acciones.ExcelBoceas(fecha);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=Acciones.xls");

			servicioXls.getXls(res, response.getOutputStream(), null);
		} catch (Exception e) {
			log.error(e);
		}

	}

	/**
	 * Construir xls Boceas Simultaneas.
	 * 
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
	public void construirXlsBoceasSimul(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("Construir xls boceas");
		try {
			// Realizando la consulta al DAO
			this.springContext = WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext());
			IAccionesBoceasDao acciones = (IAccionesBoceasDao) this.springContext
					.getBean("accionesBoceasDao");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String fecha = request.getParameter("fecha");
			log.info(" construirXlsBoceas fecha: " + fecha);

			String tipoM = request.getParameter("tipoMercado");
			log.info(" construirXlsBoceas tipoMercado: " + tipoM);

			int tipoMercado = 0;

			try {
				tipoMercado = tipoM == null || tipoM.length() == 0 ? 5
						: Integer.parseInt(tipoM);
				log.info(" construirXlsAcciones tipoMercadoParsed: "
						+ tipoMercado);
			} catch (Throwable e) {
				log.warn(e.getMessage());
			}

			List<ResumenBoceas> res = acciones.ExcelBoceasSimul(fecha);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=Acciones.xls");

			servicioXls.getXls(res, response.getOutputStream(), null);
		} catch (Exception e) {
			log.error(e);
		}

	}
}
/********************************************************End of DescargaXlsServlet.java**************************/