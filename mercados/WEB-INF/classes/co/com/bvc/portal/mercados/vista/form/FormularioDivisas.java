package co.com.bvc.portal.mercados.vista.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.ui.Model;

import co.com.bvc.portal.comun.servicio.IServicioUtil;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.DivisasRegistroTO;
import co.com.bvc.portal.mercados.modelo.DivisasSetFxTO;
import co.com.bvc.portal.mercados.servicio.IDivisasRegistro;
import co.com.bvc.portal.mercados.servicio.IDivisasSetFx;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;

// TODO: Auto-generated Javadoc
/**
 * The Class FormularioDivisas.
 */
public class FormularioDivisas implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8015201813280006463L;

	/** The fecha format. */
	private final String fechaFormat = "yyyy-MM-dd";

	/** The dia. */
	private String dia;

	/** The mes. */
	private String mes;

	/** The anio. */
	private String anio;

	/** The divisas registro. */
	private IDivisasRegistro divisasRegistro;

	/** The divisas set fx. */
	private IDivisasSetFx divisasSetFx;

	/** The servicio util. */
	private IServicioUtil servicioUtil;

	/**
	 * Instantiates a new formulario divisas.
	 */
	public FormularioDivisas() {
		Calendar cal = new GregorianCalendar();
		dia = completarTamanio(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)),
				2);
		mes = completarTamanio(String.valueOf(cal.get(Calendar.MONTH) + 1), 2);
		anio = completarTamanio(String.valueOf(cal.get(Calendar.YEAR)), 4);
	}

	/**
	 * Gets the dia.
	 * 
	 * @return the dia
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * Sets the dia.
	 * 
	 * @param dia the new dia
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * Gets the mes.
	 * 
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 * 
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the anio.
	 * 
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 * 
	 * @param anio the new anio
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Gets the time requerido.
	 * 
	 * @return the time requerido
	 */
	public String getTimeRequerido() {
		if (anio != null && mes != null && dia != null) {
			if (anio.trim().length() > 0 && mes.trim().length() > 0
					&& dia.trim().length() > 0) {
				return anio + "-" + mes + "-" + dia;
			}
		}
		return null;
	}

	/**
	 * Cargar resumenes dia.
	 * 
	 * @param fecha the fecha
	 * @param model the model
	 */
	public void cargarResumenesDia(String fecha, Model model) {
		Map<String, Double> resumenMercados = new TreeMap<String, Double>();
		Map<DivisasRegistroTO, List<DivisasRegistroTO>> detalleDivisas = new HashMap<DivisasRegistroTO, List<DivisasRegistroTO>>();
		boolean esHoy =  false;
		// DateFormat sdf = new SimpleDateFormat(fechaFormat);
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal = servicioUtil.getUltimoDiaHabilBursatil(cal);

		/*
		 * resumen de setFX, siempre se consulta la fecha actual.
		 */

		DivisasSetFxTO resumenSetFx = divisasSetFx.getDetalleDia(UtilFechas.getHoy(fechaFormat));
		

		/*
		 * sacar el total de todos los mercados, y sumarlos en el totalMercados
		 */
		Double totalRegistro = divisasRegistro
				.obtenerResumenMercadoPorFecha(fecha);
		Double totalSetFx = resumenSetFx.getVolumenNegociado();
		Double totalMercados = esHoy? totalRegistro + totalSetFx : totalRegistro;
		resumenMercados.put("Registro", totalRegistro);
		resumenMercados.put("SET - FX", totalSetFx);

		/*
		 * sacar el detalle de las divisas (DOLAR)
		 */
		List<DivisasRegistroTO> detallePrincipal = divisasRegistro
				.obtenerResumenDivisaYMercadoPorFecha(fecha);

		for (Iterator<DivisasRegistroTO> iter = detallePrincipal.iterator(); iter
				.hasNext();) {
			DivisasRegistroTO divAct = iter.next();
			detalleDivisas.put(divAct, divisasRegistro
					.obtenerDetallePorDivisaYMercado(divAct.getDivisa(), divAct
							.getMercadoInicial(), fecha));
		}

		model.addAttribute(IConstantesAtributosModelo.RESUMEN_MERCADO,
				resumenMercados);
		model.addAttribute(IConstantesAtributosModelo.TOTAL_MERCADOS,
				totalMercados);
		model.addAttribute(IConstantesAtributosModelo.DETALLE_DIVISAS,
				detalleDivisas);
		model.addAttribute(IConstantesAtributosModelo.DETALLE_SET_FX,
				resumenSetFx);
		model.addAttribute(IConstantesAtributosModelo.ES_HOY, esHoy);
	}

	/**
	 * Cargar resumenes hoy.
	 * 
	 * @param model the model
	 */
	public void cargarResumenesHoy(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat(fechaFormat);
		cargarResumenesDia(sdf.format(new Date()), model);
	}

	/**
	 * Gets the divisas registro.
	 * 
	 * @return the divisas registro
	 */
	public IDivisasRegistro getDivisasRegistro() {
		return divisasRegistro;
	}

	/**
	 * Sets the divisas registro.
	 * 
	 * @param divisasRegistro the new divisas registro
	 */
	public void setDivisasRegistro(IDivisasRegistro divisasRegistro) {
		this.divisasRegistro = divisasRegistro;
	}

	/**
	 * Completar tamanio.
	 * 
	 * @param valor the valor
	 * @param tamanio the tamanio
	 * 
	 * @return the string
	 */
	private String completarTamanio(String valor, int tamanio) {
		if (valor.length() < tamanio) {
			valor = completarTamanio("0" + valor, tamanio);
		}
		return valor;
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

	/**
	 * Gets the servicio util.
	 * 
	 * @return the servicio util
	 */
	public IServicioUtil getServicioUtil() {
		return servicioUtil;
	}

	/**
	 * Sets the servicio util.
	 * 
	 * @param servicioUtil the new servicio util
	 */
	public void setServicioUtil(IServicioUtil servicioUtil) {
		this.servicioUtil = servicioUtil;
	}
}
