/**
 * 
 */
package co.com.bvc.portal.mercados.util;

// TODO: Auto-generated Javadoc
/**
 * Interfaz que define las consultas de mercados.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public interface IConsultasMercados {
	
	/** The Constant TOTAL_CONTRATOS_DIA_ANTERIOR. */
	public static final String TOTAL_CONTRATOS_DIA_ANTERIOR = "getTotalContratosDiaAnterior";

	/** The DERIVAD o_ preci o_ apertur a_ historico. */
	public static String DERIVADO_PRECIO_APERTURA_HISTORICO = "derivadoPrecioAperturaHistorico";

	/** The DERIVAD o_ preci o_ apertur a_ historic o_ opcf. */
	public static String DERIVADO_PRECIO_APERTURA_HISTORICO_OPCF = "derivadoPrecioAperturaHistoricoOpcf";

	/** The MERCADO s_ derivados. */
	public static String MERCADOS_DERIVADOS = "mercadosDerivados";

	/** The MERCADO s_ derivados. */
	public static String MERCADOS_DERIVADOS_DRVX = "mercadosDerivados";
	
	/** The MERCADO s_ derivado s_ historico. */
	public static String MERCADOS_DERIVADOS_HISTORICO = "mercadosDerivadosHistorico";

	/** The MERCADO s_ derivado s_ historico. */
	public static String MERCADOS_DERIVADOS_HISTORICO_DRVX = "mercadosDerivadosHistorico";

	/** The TOTA l_ derivados. */
	public static String TOTAL_DERIVADOS = "totalDerivados";

	/** The TOTA l_ derivado s_ historico. */
	public static String TOTAL_DERIVADOS_HISTORICO = "totalDerivadosHistorico";

	/** The DERIVADO s_ resumen. */
	public static String DERIVADOS_RESUMEN = "derivadosResumen";

	/** The DERIVADO s_ resume n_ opcf. */
	public static String DERIVADOS_RESUMEN_OPCF = "derivadosResumenOPCF";

	/** The DERIVADO s_ parametro s_ busqueda. */
	public static String DERIVADOS_PARAMETROS_BUSQUEDA = "derivadosParametrosBusqueda";

	/** The DERIVADO s_ parametro s_ busqued a_ historico. */
	public static String DERIVADOS_PARAMETROS_BUSQUEDA_HISTORICO = "derivadosParametrosBusquedaHistorico";

	/** The DERIVADO s_ dia. */
	public static String DERIVADOS_DIA = "derivadosDia";

	/** The DERIVADO s_ di a_ opcf. */
	public static String DERIVADOS_DIA_OPCF = "derivadosDiaOpcf";

	/** The DERIVADO s_ di a_ historico. */
	public static String DERIVADOS_DIA_HISTORICO = "derivadosDiaHistorico";

	/** The DERIVADO s_ di a_ historic o_ opcf. */
	public static String DERIVADOS_DIA_HISTORICO_OPCF = "derivadosDiaHistoricoOpcf";

	/** The DERIVADO s_ extendidos. */
	public static String DERIVADOS_EXTENDIDOS = "derivadosExtendidos";

	/** The DERIVADO s_ extendido s_ historico. */
	public static String DERIVADOS_EXTENDIDOS_HISTORICO = "derivadosExtendidosHistorico";

	/** The DERIVAD o_ preci o_ apertura. */
	public static String DERIVADO_PRECIO_APERTURA = "derivadoPrecioApertura";

	/** The DERIVAD o_ preci o_ apertur a_ opcf. */
	public static String DERIVADO_PRECIO_APERTURA_OPCF = "derivadoPrecioAperturaOpcf";

	/** The DERIVAD o_ preci o_ mayo r_ contrato. */
	public static String DERIVADO_PRECIO_MAYOR_CONTRATO = "derivadoPrecioMayorContrato";

	/** The DERIVAD o_ preci o_ mayo r_ contrat o_ opcf. */
	public static String DERIVADO_PRECIO_MAYOR_CONTRATO_OPCF = "derivadoPrecioMayorContratoOpcf";

	/** The DERIVAD o_ preci o_ mayo r_ contrat o_ historico. */
	public static String DERIVADO_PRECIO_MAYOR_CONTRATO_HISTORICO = "derivadoPrecioMayorContratoHistorico";

	/** The DERIVAD o_ preci o_ mayo r_ contrat o_ historic o_ opcf. */
	public static String DERIVADO_PRECIO_MAYOR_CONTRATO_HISTORICO_OPCF = "derivadoPrecioMayorContratoHistoricoOpcf";

	/** The DERIVAD o_ preci o_ meno r_ contrato. */
	public static String DERIVADO_PRECIO_MENOR_CONTRATO = "derivadoPrecioMenorContrato";

	/** The DERIVAD o_ preci o_ meno r_ contrat o_ opcf. */
	public static String DERIVADO_PRECIO_MENOR_CONTRATO_OPCF = "derivadoPrecioMenorContratoOpcf";

	/** The DERIVAD o_ preci o_ meno r_ contrat o_ historico. */
	public static String DERIVADO_PRECIO_MENOR_CONTRATO_HISTORICO = "derivadoPrecioMenorContratoHistorico";

	/** The DERIVAD o_ preci o_ meno r_ contrat o_ historic o_ opcf. */
	public static String DERIVADO_PRECIO_MENOR_CONTRATO_HISTORICO_OPCF = "derivadoPrecioMenorContratoHistoricoOpcf";

	/** The ULTIM a_ operacio n_ derivado. */
	public static String ULTIMA_OPERACION_DERIVADO = "ultimaOperacionDerivado";

	/** The ULTIM a_ operacio n_ derivad o_ opcf. */
	public static String ULTIMA_OPERACION_DERIVADO_OPCF = "ultimaOperacionDerivadoOpcf";

	/** The ULTIM a_ operacio n_ derivad o_ historico. */
	public static String ULTIMA_OPERACION_DERIVADO_HISTORICO = "ultimaOperacionDerivadoHistorico";

	/** The ULTIM a_ operacio n_ derivad o_ historic o_ opcf. */
	public static String ULTIMA_OPERACION_DERIVADO_HISTORICO_OPCF = "ultimaOperacionDerivadoHistoricoOpcf";

	/** The DERIVAD o_ resume n_ contrato. */
	public static String DERIVADO_RESUMEN_CONTRATO = "derivadoResumenContrato";

	/** The DERIVAD o_ resume n_ extendido. */
	public static String DERIVADO_RESUMEN_EXTENDIDO = "derivadoResumenExtendido";

	/** The DERIVAD o_ resume n_ extendid o_ opcf. */
	public static String DERIVADO_RESUMEN_EXTENDIDO_OPCF = "derivadoResumenExtendidoOpcf";

	/** The DERIVAD o_ resume n_ extendid o_ historic o_ opcf. */
	public static String DERIVADO_RESUMEN_EXTENDIDO_HISTORICO_OPCF = "derivadoResumenExtendidoHistoricoOpcf";

	/** The FECHA s_ par a_ resume n_ extendid o_ historic o_ opcf. */
	public static String FECHAS_PARA_RESUMEN_EXTENDIDO_HISTORICO_OPCF = "obtenerFechasparaConsultaResumenExtHistOPCF";

	/** The DERIVAD o_ resume n_ extendid o_ historico. */
	public static String DERIVADO_RESUMEN_EXTENDIDO_HISTORICO = "derivadoResumenExtendidoHistorico";
	
	public static String DERIVADO_RESUMEN_EXTENDIDO_HISTORICO_GRAFICA = "derivadoResumenExtendidoHistoricoGrafica";

	/** The HISTORIC o_ derivado. */
	public static String HISTORICO_DERIVADO = "historicoDerivado";

	/** The HISTORIC o_ derivad o_ fecha. */
	public static String HISTORICO_DERIVADO_FECHA = "historicoDerivadoFecha";

	/** The HISTORIC o_ derivad o_ opcf. */
	public static String HISTORICO_DERIVADO_OPCF = "historicoDerivadoOPCF";

	/** The HISTORIC o_ derivad o_ opc f_ fecha. */
	public static String HISTORICO_DERIVADO_OPCF_FECHA = "historicoDerivadoOPCFFecha";

	/** The DERIVAD o_ meno r_ bid. */
	public static String DERIVADO_MENOR_BID = "derivadoMenorBid";

	/** The DERIVAD o_ mejo r_ offer. */
	public static String DERIVADO_MEJOR_OFFER = "derivadoMejorOffer";

	/** The RENTAFIJ a_ emisor. */
	public static String RENTAFIJA_EMISOR = "queryEmisor";

	/** The RENTAFIJ a_ titulo. */
	public static String RENTAFIJA_TITULO = "queryTitulo";

	public static String RENTAFIJA_OPERACIONES = "rentafijaOperaciones";
	
	public static String RENTAFIJA_DETALLE_EXCEL = "DetalleExcel";

	/** The DERIVADO s_ ticker. */
	public static String DERIVADOS_TICKER = "derivadoTicker";

	public static String RENTA_FIJA_DETALLE_SIMULTANEAS = "detalleNemoSimultaneas";

	public static String RENTA_FIJA_DETALLE_GENERAL = "detalleNemoGeneral";

	public static String RENTA_FIJA_DETALLE_REPOS = "detalleNemoRepos";

	/** The RENT a_ fij a_ ticker. */
	public static String RENTA_FIJA_TICKER = "tickerRentaFija";

	/** The RENT a_ fij a_ drop. */
	public static String RENTA_FIJA_DROP = "rentaFijaDrop";

	/** The RENT a_ fij a_ create. */
	public static String RENTA_FIJA_CREATE = "rentaFijaCreate";

	/** The RENT a_ fij a_ autocomplete. */
	public static String RENTA_FIJA_AUTOCOMPLETE = "queryAutocomplete";

	/** The CARGA r_ intradi a_ derivados. */
	public static String CARGAR_INTRADIA_DERIVADOS = "cargarIntradiaDerivados";

	/** The CARGA r_ intradi a_ derivado s_ historico. */
	public static String CARGAR_INTRADIA_DERIVADOS_HISTORICO = "cargarIntradiaDerivadosHistorico";

	/** The CARGA r_ intradi a_ derivado s_ historic o_ opcf. */
	public static String CARGAR_INTRADIA_DERIVADOS_HISTORICO_OPCF = "cargarIntradiaDerivadosHistoricoOPCF";

	public static String VERIFICAR_CONTRATO_DERIVADOS = "esContratoDeDerivado";

	/**
	 * Identificador de la consulta para la gráfica de rentaFija
	 */
	public static String RENTA_FIJA_GRAFICA = "graficaRentaFija";
	
	public static final String RENTA_FIJA_GRAFICA_HIST = "graficaRentaFijaHist";
	
	
	/** The CARGA r_ ultim a_ tas a_ rentafija. */
	public static String CARGAR_ULTIMA_TASA_RENTAFIJA = "cargarUltimaTasaRentafija";
}