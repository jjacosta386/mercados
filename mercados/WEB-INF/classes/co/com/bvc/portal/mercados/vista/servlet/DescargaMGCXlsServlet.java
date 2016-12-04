package co.com.bvc.portal.mercados.vista.servlet;

import java.io.IOException;
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
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.ResumenEtfsMGC;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.servicio.IAccionesMGC;

/**
 * Servlet implementation class {@link DescargaMGCXlsServlet}.
 */
public class DescargaMGCXlsServlet extends HttpServlet {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8500845602822082052L;
	
	private static final double RANGO_MAXIMO_MILISEGUNDOS = 1000*60*60*12*365.25; //6 meses
	
	private static final String TEXTO_SALIDA_ERROR_RANGO = "Recuerde que la descarga de éste tipo de información está condicionada así: " +
			"\r\n\r\n1. Debe seleccionar ambas fechas. " +
			"\r\n2. La fecha inicial debe ser menor a la fecha final" +
			"\r\n3. El Rango máximo de descarga es de 6 meses.";

	/** The spring context. */
	ApplicationContext springContext;

	/**
	 * The Constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public DescargaMGCXlsServlet() {
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

		if (StringUtils.isEmpty(archivo)) {
			return;
		}

		/*
		 * Archivo: derivados, derivados_detalle, rentafija, rentafija_detalle,
		 * indices, acciones, acciones_detalle
		 */

		// String tipoMercado = "01";
		if (archivo.equalsIgnoreCase("accionesMGC")) {
			construirXlsAcciones(request, response);
		} else if (archivo.equalsIgnoreCase("acciones_detalleMGC")) {
			construirXlsAccionesDetalle(request, response);
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
			IAccionesMGC acciones = (IAccionesMGC) this.springContext
					.getBean("accionesMGCServicio");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");

			String fecha = request.getParameter("fecha");
			log.info(" construirXlsAcciones fecha: " + fecha);
			int resultados = Integer.parseInt(request
					.getParameter("resultados"));

			String tipoM = request.getParameter("tipoMercado");
			String tipoI = request.getParameter("tipoInstrumento");
			log.info(" construirXlsAcciones tipoMercado: " + tipoM + " y tipoInstrumento: " + tipoI);

			int tipoMercado = 0;
			int tipoInstrumento = 0;

			try {
				tipoMercado = tipoM == null || tipoM.length() == 0 ? 4
						: Integer.parseInt(tipoM);
				tipoInstrumento = tipoI == null || tipoI.length() == 0 ? 0
						: Integer.parseInt(tipoI);
				log.info(" construirXlsAcciones tipoMercadoParsed: " + tipoMercado + " y tipoInstrumentoParsed: " + tipoInstrumento);
			} catch (Throwable e) {
				log.warn(e.getMessage());
			}

			List<ResumenAccionMGC> lista = new ArrayList<ResumenAccionMGC>();
			List<ResumenEtfsMGC> listaETF = new ArrayList<ResumenEtfsMGC>();
			
			if(tipoInstrumento == IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC){
				lista = acciones.accionesMasTranzadasDia(fecha, 100, tipoMercado);
			}else if(tipoInstrumento == IAccionesMGC.TIPO_MERCADO_ETF_MGC){
				listaETF = acciones.etfsMasTranzadasDia(fecha, 100, IAccionesMGC.TIPO_MERCADO_ETF_MGC);
			}
			else{
				lista = acciones.accionesMasTranzadasDia(fecha, 100, IAccionesMGC.TIPO_MERCADO_ACCIONES_MGC);
				listaETF = acciones.etfsMasTranzadasDia(fecha, 100, IAccionesMGC.TIPO_MERCADO_ETF_MGC);
				
			}
			
			for (ResumenEtfsMGC res : listaETF) {
				ResumenAccionMGC resTmp = new ResumenAccionMGC();
				resTmp.setCantidad(res.getCantidad());
				resTmp.setCierreAnterior(res.getCierreAnterior());
				resTmp.setCotizacion(res.getCotizacion());
				resTmp.setFechaList(res.getFechaList());
				resTmp.setHora(res.getHora());
				resTmp.setId(res.getId());
				resTmp.setIdEmisor(res.getIdEmisor());
				resTmp.setIsin(res.getIsin());
				resTmp.setMoneda(res.getMoneda());
				resTmp.setNemoTecnico(res.getNemoTecnico());
				resTmp.setNombreEmr(res.getNombreEmr());
				resTmp.setOrden(res.getOrden(),0);
				resTmp.setPais(res.getPais());
				resTmp.setPatrocinador(res.getPatrocinador());
				resTmp.setPlazoFinal(res.getPlazoFinal());
				resTmp.setPlazoInicial(res.getPlazoInicial());
				resTmp.setRazonSocial(res.getRazonSocial());
				resTmp.setTipoMercado(res.getTipoMercado());
				resTmp.setUltimoPrecio(res.getUltimoPrecio());
				resTmp.setVariacion(res.getVariacion());
				resTmp.setVolumen(res.getVolumen());
				resTmp.setVolumenFinal(res.getVolumenFinal());
				resTmp.setVolumenInicial(res.getVolumenInicial());
				lista.add(resTmp);
			}
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=AccionesMGC.xls");

			servicioXls.getXls(lista, response.getOutputStream(), null);
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
			IAccionesMGC acciones = (IAccionesMGC) this.springContext
					.getBean("accionesMGCServicio");
			IServicioXls servicioXls = (IServicioXls) this.springContext
					.getBean("servicioTransformarXls");
			String nemo = request.getParameter("nemo");
			int tipoMercado = Integer.parseInt(request
					.getParameter("tipoMercado"));
			String fechaIni = request.getParameter("fechaIni");
			String fechaFin = request.getParameter("fechaFin");
			if (validarFechas(fechaIni, fechaFin, "yyyy-MM-dd", response)){
				List<TituloAccion> resp = acciones.cargarDatosTituloDia(nemo,
						tipoMercado, fechaIni, fechaFin);
	
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=AccionDetalleMGC.xls");
	
				servicioXls.getXls(resp, response.getOutputStream(), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Valida si las fechas dadas cumplen con el rango máximo establecido . En caso de que no sea un rango valido, usa el outputstream para devolver un archivo de texto con el texto predefinido de error, se encarga ademas de cerrar el outputStream.
	 * 
	 * @param fechaIni
	 * 		Fecha inicial del rango
	 * @param fechaFin
	 * 		Fecha final del rango
	 * @param patron
	 * 		formato en el que se encuentran las fechas
	 * @return
	 * <strong>true</strong> si el rango es correcto <br/>
	 * <strong>false</stong> en caso contrario
	 */
	private boolean validarFechas(String fechaIni, String fechaFin, String patron, HttpServletResponse response){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(patron);
			Calendar calIni = new GregorianCalendar();
			calIni.setTime(sdf.parse(fechaIni));
			Calendar calFin = new GregorianCalendar();
			calFin.setTime(sdf.parse(fechaFin));
			if (calIni.compareTo(calFin) < 0 && RANGO_MAXIMO_MILISEGUNDOS >= (calFin.getTimeInMillis() - calIni.getTimeInMillis())){
				return true;
			}else{
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
						"attachment;filename=InstruccionesDescargaDetalleRentaFija.txt");
				response.getOutputStream().write(TEXTO_SALIDA_ERROR_RANGO.getBytes());
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}catch (Exception ex){
			return false;
		}
		return false;
	}
}