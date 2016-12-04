package co.com.bvc.portal.mercados.servicio;

import java.util.List;
import java.util.Map;

import co.com.bvc.portal.mercados.modelo.IndicesAutocomplete;
import co.com.bvc.portal.mercados.modelo.InfoIndicesSectoriales;
import co.com.bvc.portal.mercados.modelo.ResumenIndice;
import co.com.bvc.portal.mercados.modelo.ResumenIndiceLista;
import co.com.bvc.portal.mercados.vista.form.FormularioIndices;

/**
 * @author BVC
 * @description The Interface IIndices. Define la logica de manejo de los datos de Indices
 */

public interface IIndices {

	/**
	 * Retorna un mapa con la traducción de los códigos de los indices con sus
	 * restectivos códigos externos.
	 * 
	 * @return Un mapa que relaciona código interno del indice = código externo
	 *         del indice
	 */
	public Map<String, String> obtenerPosiblesIndices();
	
	/**
	 * Retorna un mapa con la traducción de los códigos de los indices con sus
	 * restectivos códigos externos.
	 * 
	 * @return Un mapa que relaciona código interno del indice = código externo
	 *         del indice
	 */
	public List<ResumenIndice> obtenerIndicesMercado() ;

	/**
	 * retorna el detalle según los filtros dados en el formulario.
	 * 
	 * @param form
	 *            the form
	 * 
	 * @return the resumen indice
	 */
	public ResumenIndice obtenerResumenIndice(FormularioIndices form);
	
	/**
	 * retorna el detalle según los filtros dados en el formulario.
	 * 
	 * @param form
	 *            the form
	 * 
	 * @return the resumen indice Lista
	 */
	public List<ResumenIndiceLista> obtenerResumenIndicesXLS(String fecha);

	/**
	 * Obtener resumen rango fecha.
	 * 
	 * @param codIndice
	 *            the cod indice
	 * @param fechaMin
	 *            the fecha min
	 * @param fechaMax
	 *            the fecha max
	 * 
	 * @return the list< resumen indice>
	 */
	public List<ResumenIndice> obtenerResumenRangoFecha(String codIndice,
			String fechaMin, String fechaMax);

	/**
	 * Obtener resumen rango fecha Renta Fija.
	 * 
	 * @param codIndice
	 *            the cod indice
	 * @param fechaMin
	 *            the fecha min
	 * @param fechaMax
	 *            the fecha max
	 * 
	 * @return the list< resumen indice>
	 */
	public List<ResumenIndice> obtenerResumenRangoFechaRF(String codIndice,
			String fechaMin, String fechaMax);
	
	/**
	 * Obtener resumen rango fecha Mercado Monetario.
	 * 
	 * @param codIndice
	 *            the cod indice
	 * @param fechaMin
	 *            the fecha min
	 * @param fechaMax
	 *            the fecha max
	 * 
	 * @return the list< resumen indice>
	 */
	public List<ResumenIndice> obtenerResumenRangoFechaMM(String codIndice,
			String fechaMin, String fechaMax);

	
	/**
	 * Obtener indice metadata.
	 * 
	 * @param form
	 *            the form
	 * 
	 * @return the info indices sectoriales
	 */
	public InfoIndicesSectoriales obtenerIndiceMetadata(FormularioIndices form);

	/**
	 * Obtener contenido indice.
	 * 
	 * @param codigo
	 *            the codigo
	 * @param tipoCont
	 *            the tipo cont
	 * 
	 * @return the string
	 */
	public String obtenerContenidoIndice(String codigo, String tipoCont);

	/**
	 * Salvar metadata indice.
	 * 
	 * @param infoInd
	 *            the info ind
	 */
	public void salvarMetadataIndice(InfoIndicesSectoriales infoInd);

	/**
	 * Devuelve 2 listados de indices, uno con los principales y uno con los
	 * sectoriales.
	 * 
	 * @param form
	 *            Formulario con los filtros de la consulta
	 * 
	 * @return the list< list< resumen indice>>
	 * 
	 *         Los datos de los indices
	 */
	public List<List<ResumenIndice>> obtenerResumenIndices(
			FormularioIndices form);
	
	/**
	 * Devuelve indices de renta fija.
	 * 
	 * @param form
	 *            Formulario con los filtros de la consulta
	 * 
	 * @return the list< list< resumen indice>>
	 * 
	 *         Los datos de los indices
	 */
	public List<ResumenIndice> obtenerResumenIndicesRF(
			FormularioIndices form);
	
	/**
	 * Devuelve indices de renta fija.
	 * 
	 * @param form
	 *            Formulario con los filtros de la consulta
	 * 
	 * @return the list< list< resumen indice>>
	 * 
	 *         Los datos de los indices
	 */
	public ResumenIndice obtenerResumenIndicesBucarRF(
			FormularioIndices form);
	
	/**
	 * Devuelve indices de renta fija.
	 * 
	 * @param form
	 *            Formulario con los filtros de la consulta
	 * 
	 * @return the list< list< resumen indice>>
	 * 
	 *         Los datos de los indices
	 */
	public List<ResumenIndice> obtenerResumenIndicesMM(
			FormularioIndices form);
	
	/**
	 * Devuelve indices de renta fija.
	 * 
	 * @param form
	 *            Formulario con los filtros de la consulta
	 * 
	 * @return the list< list< resumen indice>>
	 * 
	 *         Los datos de los indices
	 */
	public ResumenIndice obtenerResumenIndicesBuscarMM(
			FormularioIndices form);

	/**
	 * Gets the mensaje mercado abierto.
	 * 
	 * @param form
	 *            the form
	 * 
	 * @return the mensaje mercado abierto
	 */
	public String getMensajeMercadoAbierto(FormularioIndices form);

	/**
	 * Comparar indices.
	 * 
	 * @param nemo
	 *            the nemo
	 * @param nombreMostrar
	 *            the nombre mostrar
	 * @param indicesAMostrar
	 *            the indices a mostrar
	 * @param tipoGrafico
	 *            the tipo grafico
	 * 
	 * @return the string
	 */
	public String compararIndices(String nemo, String nombreMostrar,
			List<String> indicesAMostrar, String tipoGrafico, String tiempo ,String tipomercado, List<String> mercados);

	/**
	 * carga los datos del home de indices: valor del último calculo y variación
	 * para COLCAP, COL20 e IGBC en el último día hábil bursátil menor o igual
	 * al día actual.
	 * 
	 * @return la lista de los datos
	 */
	public List<ResumenIndice> cargarHome();
	
	/**
	 * carga los datos del home de indices: valor del último calculo y variación
	 * para COLTES en el último día hábil bursátil menor o igual
	 * al día actual.
	 * 
	 * @return la lista de los datos
	 */
	public List<ResumenIndice> cargarHomeRF();
	
	/******************Se agrega funcionalidad para Mercado Monetario***************/
	/**
	 * carga los datos del home de indices: valor del último calculo y variación
	 * para COLIBR en el último día hábil bursátil menor o igual
	 * al día actual.
	 * 
	 * @return la lista de los datos
	 */
	public List<ResumenIndice> cargarHomeMM();
	/******************************************************************************/

	/**
	 * Gets the indices listar.
	 * 
	 * @return the indices listar
	 */
	public List<IndicesAutocomplete> getIndicesListar();
}
/*****************************************End of IIndices.java***********************************************************/