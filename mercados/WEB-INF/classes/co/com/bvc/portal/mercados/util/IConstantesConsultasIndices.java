package co.com.bvc.portal.mercados.util;

/**
 * @author BVC
 * @description The Interface IConstantesConsultasIndices. Definición de las constantes
 * utilizadas en las consultas que se hacen sobre indices
 */

public interface IConstantesConsultasIndices {

	/** The Constant INDICES_PARA_SELECT. */
	public static final String INDICES_PARA_SELECT = "obtenerPosiblesIndices";

	/** The Constant RESUMEN_INDICE_POR_FECHA. */
	public static final String RESUMEN_INDICE_POR_FECHA = "obtenerResumenPorIndiceYFecha";
	
	/** The Constant INDICES_POR_MERCADO. */
	public static final String INDICES_POR_MERCADO = "obtenerIndicesMercado";

	/** The Constant RESUMEN_INDICE_POR_RANGO_FECHA. */
	public static final String RESUMEN_INDICE_POR_RANGO_FECHA = "obtenerResumenPorIndiceYRangoFecha";

	/** The Constant RESUMEN_INDICE_POR_RANGO_FECHA RENTA FIJA. */
	public static final String RESUMEN_INDICE_POR_RANGO_FECHA_RENTA_FIJA = "obtenerResumenPorIndiceYRangoFechaRF";
	
	/** The Constant RESUMEN_INDICE_POR_RANGO_FECHA Mercado Monetario. */
	public static final String RESUMEN_INDICE_POR_RANGO_FECHA_MONETARIO = "obtenerResumenPorIndiceYRangoFechaMM";
	
	/** The Constant MAPA_FECHA_VALOR_HOY. */
	public static final String MAPA_FECHA_VALOR_HOY = "obtenerMapaFechaValorHoyPorRango";

	/** The Constant MAPA_FECHA_VALOR_HOY RENTA FIJA. */
	public static final String MAPA_FECHA_VALOR_HOY_RENTA_FIJA = "obtenerMapaFechaValorHoyPorRangoRF";
	
	/** The Constant RESUMEN_INDICE_POR_RANGO_FECHA_RECIENTES. */
	public static final String RESUMEN_INDICE_POR_RANGO_FECHA_RECIENTES = "obtenerResumenPorIndiceYRangoFechaRecientes";

	/** The Constant RESUMEN_INDICES. */
	public static final String RESUMEN_INDICES = "obtenerResumenIndices";
	
	/** The Constant RESUMEN_INDICES. */
	public static final String RESUMEN_INDICES_HOME = "obtenerResumenIndicesHome";
	
	/** The Constant RESUMEN_INDICES_RF. */
	public static final String RESUMEN_INDICES_RF = "obtenerResumenIndicesRF";
	
	/** The Constant RESUMEN_INDICES_RF. */
	public static final String RESUMEN_INDICES_HOMEMM = "obtenerResumenIndicesHOMEMM";
	
	/** The Constant RESUMEN_INDICES. */
	public static final String RESUMEN_INDICES_HOMERF = "obtenerResumenIndicesHOMERF";
	
	/******************Se agrega funcionalidad para Mercado Monetario***************/
	/** The Constant RESUMEN_INDICES. */
	public static final String RESUMEN_INDICES_MM = "obtenerResumenIndicesMM";
	/******************************************************************************/
	
	/** The Constant RESUMEN_INDICES. */
	public static final String RESUMEN_INDICES_BUSCARMM = "obtenerResumenIndicesBuscarMM";
	
	/** The Constant RESUMEN_INDICES. */
	public static final String RESUMEN_INDICES_BUSCARRF = "obtenerResumenIndicesBuscarRF";

	/** The Constant METADATA_INDICE. */
	public static final String METADATA_INDICE = "obtenerMetaDataPorIndice";

	/**
	 * Obtiene los datos necesarios para graficar el historico de un indice.
	 * recibe como parametros
	 */
	public static final String GRAFICA_HISTORICA = "GraficoHistoricoPorIndice";
	
	/**
	 * Obtiene los datos necesarios para graficar el historico de un indice.
	 * recibe como parametros
	 */
	public static final String GRAFICA_HISTORICAHOME = "GraficoHistoricoPorIndiceHOME";
	
	/**
	 * Obtiene los datos necesarios para graficar el historico de un indice.
	 * recibe como parametros
	 */
	public static final String GRAFICA_HISTORICA_RF = "GraficoHistoricoPorIndiceRF";
	
	/**
	 * Obtiene los datos necesarios para graficar el historico de un indice.
	 * recibe como parametros
	 */
	public static final String GRAFICA_HISTORICA_HOMERF = "GraficoHistoricoPorIndiceHOMERF";
	
	/**
	 * Obtiene los datos necesarios para graficar el historico de un indice.
	 * recibe como parametros
	 */
	public static final String GRAFICA_HISTORICA_MM = "GraficoHistoricoPorIndiceMM";
	
	/**
	 * Obtiene los datos necesarios para graficar el historico de un indice.
	 * recibe como parametros
	 */
	public static final String GRAFICA_HISTORICA_HOMEMM = "GraficoHistoricoPorIndiceHOMEMM";
	
	/**
	 * Obtiene los datos necesarios para graficar el historico de un indice.
	 * recibe como parametros
	 */
	public static final String GRAFICA_HISTORICA_HOME = "GraficoHistoricoPorIndiceHome";

	/**
	 * Obtiene los datos necesarios para graficar el intradia de un indice
	 * recibe como parametros.
	 */
	public static final String GRAFICA_INTRADIA = "GraficoIntradiaPorIndice";
	
	/**
	 * Obtiene los datos necesarios para graficar el intradia de un indice
	 * recibe como parametros.
	 */
	public static final String GRAFICA_INTRADIA_RF = "GraficoIntradiaPorIndiceRF";

	/** The Constant COLUMNA_CLAVE. */
	public static final String COLUMNA_CLAVE = "clave";

	/** The Constant COLUMNA_VALOR. */
	public static final String COLUMNA_VALOR = "value";
	
	/** The Constant COLUMNA_MERCADO. */
	public static final String COLUMNA_MERCADO = "mercado";

	/** The Constant FILTRO_FECHA. */
	public static final String FILTRO_FECHA = "fecha";

	/** The Constant FILTRO_FECHA_MAX. */
	public static final String FILTRO_FECHA_MAX = "fechaMax";

	/** The Constant FILTRO_FECHA_MIN. */
	public static final String FILTRO_FECHA_MIN = "fechaMin";

	/** The Constant FILTRO_FECHA_MENOR. */
	public static final String FILTRO_FECHA_MENOR = "fechaMenor";

	/** Código interno del indice, por ejemplo: AGRC, ICAP, etc. */
	public static final String FILTRO_CODIGO = "codIndice";

	/** The Constant ACTUALIZAR_CONTENIDOS. */
	public static final String ACTUALIZAR_CONTENIDOS = "actualizarContenidosPorIndice";

	/** The Constant SETER. */
	public static final String SETER = "seters";

	/** The Constant OPERADOR. */
	public static final String OPERADOR = "operador";

	/** The Constant FECHA_DOCE_MESES. */
	public static final String FECHA_DOCE_MESES = "fecha12Meses";
}
/************************************************End of IConstantesConsultasIndices.java***********************/