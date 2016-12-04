package co.com.bvc.portal.mercados.util;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConstantesConsultasAcciones.
 */
public interface IConstantesConsultasAcciones {

	/** devuelve el resumen de ttv para el día actual entre las horas dadas,  recibe parametros:. */
	public static final String RESUMEN_TTV_ACTUAL = "TTVMasNegociadosDiaActual";

	/** devuelve el resumen de ttv para el día dado  recibe parametros:. */
	public static final String RESUMEN_TTV_HISTORIA = "TTVMasNegociadosDiaPasado";

	/** devuelve los volumenes totales negociados para una fecha o para una hora del día [ fecha | compraventa | repos | ttv ]  recibe parametros:. */
	public static final String VOLUMENES_TOTALES_POR_FECHA = "obtenerVolumenTotalPorFecha";

	/** trae la lista de las acciones transadas en el día dado  recibe parametros:. */
	public static final String LISTA_ACCIONES_DIA_PASADO = "accionesMasTransadasDiaPasado";

	/** trae la lista de las acciones transadas antes de la fechaHora especificada  recibe parametros:. */
	public static final String LISTA_ACCIONES_HOY = "accionesMasTransadasDiaActual";
	
	/** trae la la hora de la ultima operación realizada en la fecha especificada recibe parametros:. */
	public static final String HORA_ULTIMA_OPERACION = "HoraUltimaOperacion";
	
	/** trae la lista de las acciones transadas antes de la fechaHora especificada  recibe parametros:. */
	public static final String LISTA_ACCIONES_HOY_SUBE = "accionesMasTransadasDiaHomeSube";
	
	/** trae la lista de las etfs transadas antes de la fechaHora especificada  recibe parametros:. */
	public static final String LISTA_ETF_HOY = "etfsMasTransadasDiaActual";
	
	/** trae la lista de las etfs transadas en el día dado  recibe parametros:. */
	public static final String LISTA_ETF_DIA_PASADO = "etfsMasTransadasDiaPasado";

	/** Obtiene el precio de cierre correspondiente a la mayor fecha de las menores a la fecha dada.  recibe parametros: */
	public static final String ULTIMO_PRECIO_POR_ACCION = "ultimoPrecioAccion";

	/** Obtiene la lista de las acciones más transadas en repos para el día actual  recibe parametros:. */
	public static final String LISTA_REPOS_HOY = "ReposMasTransadosDiaActual";

	/** Obtiene la lista de las acciones más transadas en repos para una fecha dada.  recibe parametros: */
	public static final String LISTA_REPOS_HISTORIA = "ReposMasTransadosDiaPasado";
	
	/** Obtiene la lista de las acciones más transadas en repos para el día actual  recibe parametros:. */
	public static final String LISTA_TTVS_HOY = "TTVMasNegociadosDiaActual";

	/** Obtiene la lista de las acciones más transadas en repos para una fecha dada.  recibe parametros: */
	public static final String LISTA_TTVS_HISTORIA = "TTVMasNegociadosDiaPasado";

	/** Obtiene la información del emisor según el código dado  recibe parametros:. */
	public static final String EMISOR_POR_CODIGO = "EmisorPorCodigo";

	/** Obtiene el qtobin y el rpg de la acción en cuestión [qtobin, rpg]  recibe como parametros. */
	public static final String QTOBIN_RPG_POR_NEMO = "qtobinYRpgPorNemo";

	/** obtiene las operaciones del día actual para la acción dada, que ocurrieron antes de la fecha hora dada  recibe como parametros. */
	public static final String OPERACIONES_COMPRAVENTA = "operacionesCompraventa";

	/** obtiene los datos necesarios para graficar el comprtamiento intradia de una acción  recibe como parametros. */
	public static final String GRAFICO_INTRADIA_ACCION = "GraficoIntradia";

	/** retorna los titulos de acciones que deben ir en el ticker (los 20 más transados o los que sean por defecto)  recibe como parametros. */
	public static final String TICKER_ACCIONES = "TickerAcciones";
	
	/** trae el ID y el nombre del Emisor */
	public static final String CONSULTA_EMISOR = "consultaNemoEmisor";
	
	/** trae la información extendida de acciones MGC*/
	public static final String DATOS_EXTENDIDOS_ACCIONES_MGC = "datosExtendidosAccionesMGC";
	
	/** nemoEmisor */
	public static final String NEMO_EMISOR = "nemoEmisor";

	/** fechaIni. */
	public static final String FECHA_INI = "fechaIni";

	/** fechaFin. */
	public static final String FECHA_FIN = "fechaFin";
	
	/** fecha. */
	public static final String FECHA = "fecha";

	/** tabla. */
	public static final String TABLA = "tabla";

	/** operador. */
	public static final String OPERADOR = "operador";

	/** nemo. */
	public static final String NEMOTECNICO = "nemo";

	/** a. */
	public static final String SUFIJO_TABLA = "a";
	
	/** tipoMercado. */
	public static final String TIPO_MERCADO = "tipoMercado";
	
	/** consultaListaMGC. */
	public static final String CONSULTA_LISTA_MGC = "consultaListaMGC";
	
	/** segmentoMercado. */
	public static final String SEGMENTO_MERCADO = "segmentoMercado";

	/** nombre de la tabla que mantiene los volumenes totales negociados por minuto. */
	public static final String TABLA_VOLMENES_TOTALES_DIA = "unicabd.p_volumen_total_acciones";

	/** nombre de la tabla que mantiene los volumenes totales negociados por dia. */
	public static final String TABLA_VOLMENES_TOTALES_HISTORIA = "unicabd.p_volumen_total_dia_acciones";

	/** Obtiene la lista de las  TTVs más transadas  para el día actual  recibe parametros:. */
	public static final String LISTA_TTV_HOY = "TTVMasNegociadosDiaActual";

	/** Obtiene la lista de las TTV más transadas para una fecha dada.  recibe parametros: */
	public static final String LISTA_TTV_HISTORIA = "TTVMasNegociadosDiaPasado";
	
	/** trae la lista de las acciones  inscritas en el Mercado Global Colombiano:. */
	public static final String LISTA_ACCIONES_MGC = "listaAccionesMgc";
	
	/** trae la lista de las etf's  inscritas en el Mercado Global Colombiano:. */
	public static final String LISTA_ETF_MGC = "listaEtfMgc";
	
	/** trae la lista de las acciones y ETF's inscritas en el Mercado Global Colombiano:. */
	public static final String LISTA_TOTAL_ACCIONES_ETF_MGC = "listaTotalAccionesETFMgc";
	
	/** trae la lista de los paises Mercado Global Colombiano:. */
	public static final String LISTA_PAIS_MGC = "listaPaises";
	
	/** trae la lista de los patrocinadores Mercado Global Colombiano:. */
	public static final String LISTA_PATROCINADOR_MGC = "listaPatrocinador";
	
	/** trae la lista de los cotizacion Mercado Global Colombiano:. */
	public static final String LISTA_COTIZACION_MGC = "listaCotiza";
	
	/** trae la consulta de la cotizacion Mercado Global Colombiano:. */
	public static final String CONSULTA_COTIZACION_MGC = "consultaListaCotiza";
	
	/** trae la consulta de la cotizacion Mercado Global Colombiano:. */
	public static final String CONSULTA_PATROCINADOR_MGC = "consultaListaPatrocinador";
	
	/** trae la consulta de la cotizacion Mercado Global Colombiano:. */
	public static final String CONSULTA_PAIS_MGC = "consultaListaPais";
	
	/** trae la consulta de la cotizacion Mercado Global Colombiano:. */
	public static final String CONSULTA_FILTRO_MGC = "consultaListaFiltro";
	
	/** trae la consulta de la tipo Mercado Global Colombiano:. */
	public static final String CONSULTA_TIPO_MGC = "consultaTipoMGC";
	
	/** trae la consulta del horario de negociación del Mercado Global Colombiano:. */
	public static final String HORARIO_NEGOCIACION_MGC = "horarioNegociacionMGC";
	
	/** trae la consulta de Cotizacion por pais Mercado Global Colombiano:. */
	public static final String CONSULTA_COTIZA_PAIS_MGC = "consultaCotizaPaisMGC";
	
	/** trae la lista de los cotizacion Mercado Global Colombiano:. */
	public static final String LISTA_COTIZACION_PAIS_MGC = "listaCotizaPaisMGC";
	
	/** trae la consulta de la cotizacion Mercado Global Colombiano:. */
	public static final String CONSULTA_COTIZACION_PAIS_MGC = "consultaListaCotizaPais";
	
	/** trae la consulta de la cotizacion Mercado Global Colombiano:. */
	public static final String CONSULTA_COTIZA_FILTRO_MGC = "consultaCotizaFiltro";
	
	/** consultaListaMGC. */
	public static final String CONSULTA_COTIZA_MGC = "consultaCotizaMGC";
	
	/** Obtiene la lista de las  TTVs más transadas  para el día actual  recibe parametros:. */
	public static final String LISTA_BOCEAS_HOY = "BoceasMasNegociadosDiaActual";

	/** Obtiene la lista de las TTV más transadas para una fecha dada.  recibe parametros: */
	public static final String LISTA_BOCEAS_HISTORIA = "BoceasMasNegociadosDiaPasado";
	
	/** consulta BOCEAS_DETALLE_SIMULTANEAS*/
	public static final String BOCEAS_DETALLE_SIMULTANEAS = "detalleBoceasSimultaneas";
	
	/** consulta BOCEAS_DETALLE_GENERAL*/
	public static final String BOCEAS_DETALLE_GENERAL = "detalleBoceasGeneral";
	
	/** consulta BOCEAS_EMISOR*/
	public static final String BOCEAS_EMISOR = "queryEmisorBoceas";
	
	/** consulta BOCEA_DETALLE_EXCEL*/
	public static final String BOCEA_DETALLE_EXCEL = "DetalleExcelBoceas";
	
	/** consulta BOCEAS_TITULO*/
	public static final String BOCEAS_TITULO = "queryTituloBoceas";
	
	/** Obtiene la lista de las  TTVs más transadas  para el día actual  recibe parametros:. */
	public static final String LISTA_BOCEASIMUL_HOY = "BoceasSimulMasNegociadosDiaActual";

	/** Obtiene la lista de las TTV más transadas para una fecha dada.  recibe parametros: */
	public static final String LISTA_BOCEASIMUL_HISTORIA = "BoceasSimulMasNegociadosDiaPasado";
}
