
package co.com.bvc.portal.mercados.util;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConstantesConsultasDivisas.
 */
public interface IConstantesConsultasDivisas {

	/** The Constant MONTO_TOTAL_REGISTRO_POR_FECHA. */
	public static final String MONTO_TOTAL_REGISTRO_POR_FECHA = "obtenerMontoTotalRegistroPorFecha";
	
	/** The Constant TOTAL_REGISTRO_DIVISA_MERCADO_POR_FECHA. */
	public static final String TOTAL_REGISTRO_DIVISA_MERCADO_POR_FECHA = "obtenerTotalRegistroDivisaYMercadoPorFecha";
	
	/** The Constant DETALLE_REGISTRO_POR_DIVISA_MERCADO_FECHA. */
	public static final String DETALLE_REGISTRO_POR_DIVISA_MERCADO_FECHA = "obtenerDetalleRegistroPorDivisaYMercado";
	
	/** The Constant FESTIVO_AMERICANO. */
	public static final String FESTIVO_AMERICANO = "obtenerFestivoAmericanoPorFecha";
	
	/** devuelve los datos necesarios para generar la gráfica del mercado de divisas set-fx. */
	public static final String OBTENER_GRAFICA = "obtenerDatosGraficoSetFx";
	
	/** Obtiene el estado del dolar en set-fx para la fecha y hora dadas.  recibe parametros: */
	public static final String OBTENER_DETALLE_SET_FX = "ObtenerSetFxPorFecha";
	
	/** The Constant TOTAL_VOLUMEN_SET_FX. */
	public static final String TOTAL_VOLUMEN_SET_FX= "ObtenerVolumenTotalSetFx";
	
	/** The Constant TRM_POR_FECHA. */
	public static final String TRM_POR_FECHA = "ObtenerValorTRMDia";
	
	/** The Constant SUFIJO_TABLA. */
	public static final String SUFIJO_TABLA= "a";
	
	/** parametro de la fecha. */
	public static final String FECHA = "fecha";
	
	/** parametro de hora. */
	public static final String HORA = "hora";
	
	/** The Constant LINEA_HORA. */
	public static final String LINEA_HORA = "filtroHora";
	
	/** The Constant LIMITE. */
	public static final String LIMITE = "limite";
	
	/** The Constant INICIAL_MERCADO. */
	public static final String INICIAL_MERCADO = "inicialMercado";
	
	/** The Constant NOMBRE_DIVISA. */
	public static final String NOMBRE_DIVISA = "nombreDivisa";
	
	/** The Constant FECHA_INICIAL. */
	public static final String FECHA_INICIAL = "fechaIni";
	
	/** The Constant FECHA_FINAL. */
	public static final String FECHA_FINAL = "fechaFin";
	
	/** codigo del mercado indica next-day o spot. */
	public static final String FILTRO_CODIGO_MERCADO = "codigoMercado";
}
