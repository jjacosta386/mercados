package co.com.bvc.portal.mercados.persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.IJdbcDAO;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InfoIndicesSectoriales;
import co.com.bvc.portal.mercados.modelo.ResumenIndice;
import co.com.bvc.portal.mercados.modelo.ResumenIndiceLista;

/**
 * @author BVC
 * @contributor Andres_Leon
 * @description The Interface IIndiceDao. Define el acceso a los datos de indices tanto accionarios como de Renta fija
 * se llama el .xml consultasIndices.xml dentro del DAO en la carpeta conf.
 */

public interface IIndiceDao extends IJdbcDAO {

	/**
	 * retorna el resumen de los indices IGBC, COLCAP y COl20.
	 * 
	 * @param fecha
	 *            the fecha
	 * 
	 * @return the list< resumen indice>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarDatosHome(String fecha) throws Exception;
    
	/**
	 * retorna el resumen de los indices Renta fija COLTES.
	 * 
	 * @param fecha
	 *            the fecha
	 * 
	 * @return the list< resumen indice>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarDatosHomeRF(String fecha) throws Exception;
	
	/******************Se agrega funcionalidad para Mercado Monetario***************/
	/**
	 * retorna el resumen de los indices Renta fija COLIBR.
	 * 
	 * @param fecha
	 *            the fecha
	 * 
	 * @return the list< resumen indice>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarDatosHomeMM(String fecha) throws Exception;
	/******************************************************************************/
	
	/**
	 * retorna el listado de los datos (fecha, último valor de la fecha) para el
	 * indice en todos los días menores a la fecha.
	 * 
	 * @param indice
	 *            código del indice que se desea consultar.
	 * @param fecha
	 *            fecha (yyyyMMdd) tope superior de la consulta, no se incluye
	 *            en el resultado
	 * 
	 * @return el listado con los datos del indice en fechas menores a la fecha.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ICierre> cargarHistoricoIndice(String indice, String fecha)
			throws Exception;
	
	/**
	 * retorna el listado de los datos (fecha, último valor de la fecha) para el
	 * indice en todos los días menores a la fecha.
	 * 
	 * @param indice
	 *            código del indice que se desea consultar.
	 * @param fecha
	 *            fecha (yyyyMMdd) tope superior de la consulta, no se incluye
	 *            en el resultado
	 * 
	 * @return el listado con los datos del indice en fechas menores a la fecha.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ICierre> cargarHistoricoHOMEIndice(String indice, String fecha)
			throws Exception;
	
	/**
	 * retorna el listado de los datos (fecha, último valor de la fecha) para el
	 * indice en todos los días menores a la fecha.
	 * 
	 * @param indice
	 *            código del indice que se desea consultar.
	 * @param fecha
	 *            fecha (yyyyMMdd) tope superior de la consulta, no se incluye
	 *            en el resultado
	 * 
	 * @return el listado con los datos del indice en fechas menores a la fecha.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ICierre> cargarHistoricoIndiceRF(String indice, String fecha)
			throws Exception;
	
	/**
	 * retorna el listado de los datos (fecha, último valor de la fecha) para el
	 * indice en todos los días menores a la fecha.
	 * 
	 * @param indice
	 *            código del indice que se desea consultar.
	 * @param fecha
	 *            fecha (yyyyMMdd) tope superior de la consulta, no se incluye
	 *            en el resultado
	 * 
	 * @return el listado con los datos del indice en fechas menores a la fecha.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ICierre> cargarHistoricoIndiceHOMERF(String indice, String fecha)
			throws Exception;
	
	/******************Se agrega funcionalidad para Mercado Monetario***************/
	/**
	 * retorna el listado de los datos (fecha, último valor de la fecha) para el
	 * indice en todos los días menores a la fecha.
	 * 
	 * @param indice
	 *            código del indice que se desea consultar.
	 * @param fecha
	 *            fecha (yyyyMMdd) tope superior de la consulta, no se incluye
	 *            en el resultado
	 * 
	 * @return el listado con los datos del indice en fechas menores a la fecha.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ICierre> cargarHistoricoIndiceMM(String indice, String fecha)
			throws Exception;
	
	/**
	 * retorna el listado de los datos (fecha, último valor de la fecha) para el
	 * indice en todos los días menores a la fecha.
	 * 
	 * @param indice
	 *            código del indice que se desea consultar.
	 * @param fecha
	 *            fecha (yyyyMMdd) tope superior de la consulta, no se incluye
	 *            en el resultado
	 * 
	 * @return el listado con los datos del indice en fechas menores a la fecha.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ICierre> cargarHistoricoIndiceHOMEMM(String indice, String fecha)
			throws Exception;
	/******************************************************************************/
	
	/**
	 * retorna el listado de los datos (fecha, último valor de la fecha) para el
	 * indice en todos los días menores a la fecha.
	 * 
	 * @param indice
	 *            código del indice que se desea consultar.
	 * @param fecha
	 *            fecha (yyyyMMdd) tope superior de la consulta, no se incluye
	 *            en el resultado
	 * 
	 * @return el listado con los datos del indice en fechas menores a la fecha.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ICierre> cargarHistoricoIndiceHome(String indice, String fecha)
			throws Exception;

	/**
	 * Retorna el resumen de un indice en una fecha determinada.
	 * 
	 * @param codigoIndice
	 *            Codigo interno del indice por ejemplo 'ICAP' NO 'COLCAP'
	 * @param fecha
	 *            Fecha del dia que será llamado hoy
	 * @param fechaHace12Meses
	 *            Fecha del dia bursatil que corresponde a la fecha -12 meses
	 * 
	 * @return el resumen
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 * @throws Exception
	 *             the exception
	 */
	public ResumenIndice cargarResumenPorIndiceYFecha(String codigoIndice,
			String fecha, String fechaHace12Meses)
			throws PersistenciaException, Exception;

	/**
	 * Obtener metadata por indice.
	 * 
	 * @param codigoIndice
	 *            the codigo indice
	 * 
	 * @return the info indices sectoriales
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public InfoIndicesSectoriales obtenerMetadataPorIndice(String codigoIndice)
			throws Exception;

	/**
	 * Salvar info indice.
	 * 
	 * @param parametro
	 *            the parametro
	 * @param codigo
	 *            the codigo
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void salvarInfoIndice(String parametro, String codigo)
			throws Exception;

	/**
	 * Cargar resumen por indice y rango fecha.
	 * 
	 * @param codIndice
	 *            the cod indice
	 * @param fechaMax
	 *            the fecha max
	 * @param fechaMin
	 *            the fecha min
	 * 
	 * @return the list< resumen indice>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarResumenPorIndiceYRangoFecha(
			String codIndice, String fechaMax, String fechaMin)
			throws Exception;

	/**
	 * Cargar resumen por indice y rango fecha para Renta Fija.
	 * 
	 * @param codIndice
	 *            the cod indice
	 * @param fechaMax
	 *            the fecha max
	 * @param fechaMin
	 *            the fecha min
	 * 
	 * @return the list< resumen indice>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarResumenPorIndiceYRangoFechaRF(
			String codIndice, String fechaMax, String fechaMin)
			throws Exception;
	
	/**
	 * Cargar resumen por indice y rango fecha para mercado moentario.
	 * 
	 * @param codIndice
	 *            the cod indice
	 * @param fechaMax
	 *            the fecha max
	 * @param fechaMin
	 *            the fecha min
	 * 
	 * @return the list< resumen indice>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarResumenPorIndiceYRangoFechaMM(
			String codIndice, String fechaMax, String fechaMin)
			throws Exception;

	
	/**
	 * Gets the mapa fecha valor hoy.
	 * 
	 * @param codIndice
	 *            the cod indice
	 * @param fechaMax
	 *            the fecha max
	 * @param fechaMin
	 *            the fecha min
	 * 
	 * @return the mapa fecha valor hoy
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public Map<Integer, Double> getMapaFechaValorHoy(String codIndice,
			String fechaMax, String fechaMin) throws Exception;

	/**
	 * Gets the mapa fecha valor hoy para Renta Fija.
	 * 
	 * @param codIndice
	 *            the cod indice
	 * @param fechaMax
	 *            the fecha max
	 * @param fechaMin
	 *            the fecha min
	 * 
	 * @return the mapa fecha valor hoy
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public Map<Integer, Double> getMapaFechaValorHoyRF(String codIndice,
			String fechaMax, String fechaMin) throws Exception;

	
	/**
	 * Cargar resumen por indice y rango fecha recientes.
	 * 
	 * @param codIndice
	 *            the cod indice
	 * @param fechaMax
	 *            the fecha max
	 * @param fechaMin
	 *            the fecha min
	 * 
	 * @return the list< resumen indice>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarResumenPorIndiceYRangoFechaRecientes(
			String codIndice, String fechaMax, String fechaMin)
			throws Exception;

	/**
	 * devuelve una lista del resumen de un grupo (seccionales o no seccionales)
	 * de indices por fecha.
	 * 
	 * @param fecha
	 *            the fecha
	 * @param seccional
	 *            the seccional
	 * 
	 * @return Lista con el resumen del grupo de indices dado.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarResumenIndices(String fecha,
			boolean seccional) throws Exception;
	
	/**
	 * devuelve una lista del resumen de un grupo (seccionales o no seccionales)
	 * de indices por fecha.
	 * 
	 * @param fecha
	 *            the fecha
	 * @param seccional
	 *            the seccional
	 * 
	 * @return Lista con el resumen del grupo de indices dado.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndiceLista> cargarResumenIndicesXLS(String fecha,
			boolean seccional) throws Exception;
	
	/**
	 * devuelve una lista del resumen de un grupo (seccionales o no seccionales)
	 * de indices por fecha.
	 * 
	 * @param fecha
	 *            the fecha
	 * @param seccional
	 *            the seccional
	 * 
	 * @return Lista con el resumen del grupo de indices dado.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarResumenIndicesRF(String fecha) throws Exception;
	
	/**
	 * devuelve una lista del resumen de un grupo (seccionales o no seccionales)
	 * de indices por fecha.
	 * 
	 * @param fecha
	 *            the fecha
	 * @param seccional
	 *            the seccional
	 * 
	 * @return Lista con el resumen del grupo de indices dado.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndiceLista> cargarResumenIndicesXLSRF(String fecha) throws Exception;
	
	/**
	 * devuelve una lista del resumen de un grupo (seccionales o no seccionales)
	 * de indices por fecha.
	 * 
	 * @param fecha
	 *            the fecha
	 * @param seccional
	 *            the seccional
	 * 
	 * @return Lista con el resumen del grupo de indices dado.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ResumenIndice cargarResumenIndicesBuscarRF(String fecha,String codIndice) throws Exception;
	
	/**
	 * devuelve una lista del resumen de un grupo (seccionales o no seccionales)
	 * de indices por fecha.
	 * 
	 * @param fecha
	 *            the fecha
	 * @param seccional
	 *            the seccional
	 * 
	 * @return Lista con el resumen del grupo de indices dado.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndice> cargarResumenIndicesMM(String fecha) throws Exception;
	
	/**
	 * devuelve una lista del resumen de un grupo (seccionales o no seccionales)
	 * de indices por fecha.
	 * 
	 * @param fecha
	 *            the fecha
	 * @param seccional
	 *            the seccional
	 * 
	 * @return Lista con el resumen del grupo de indices dado.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ResumenIndiceLista> cargarResumenIndicesXLSMM(String fecha) throws Exception;
	
	/**
	 * devuelve una lista del resumen de un grupo (seccionales o no seccionales)
	 * de indices por fecha.
	 * 
	 * @param fecha
	 *            the fecha
	 * @param seccional
	 *            the seccional
	 * 
	 * @return Lista con el resumen del grupo de indices dado.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public ResumenIndice cargarResumenIndicesBuscarMM(String fecha,String codIndice) throws Exception;

	/**
	 * Retorna el comportamiento del indice dado en el intradía.
	 * 
	 * @param titulo
	 *            código interno del indice a consultar
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws PersistenciaException 	 *
	 *             @throws Exception the exception
	 * 
	 *             el comportamiento del indice.
	 */
	public List<ICierre> cargarIntradiaIndicesHistorico(String titulo)
			throws Exception;
	
	/**
	 * Retorna el comportamiento del indice dado en el intradía.
	 * 
	 * @param titulo
	 *            código interno del indice a consultar
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws PersistenciaException 	 *
	 *             @throws Exception the exception
	 * 
	 *             el comportamiento del indice.
	 */
	public List<ICierre> cargarIntradiaIndicesHistoricoRF(String titulo)
			throws Exception;

	/**
	 * Cargar intradia indice.
	 * 
	 * @param titulo
	 *            the titulo
	 * 
	 * @return the i cierre
	 */
	public ICierre cargarIntradiaIndice(String titulo);

	/**
	 * Gets the indices comparacion.
	 * 
	 * @return the indices comparacion
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public ArrayList<String> getIndicesComparacion()
			throws PersistenciaException;
	
	/**
	 * Gets the obtenerIndicesMercado.
	 * 
	 * @return the obtenerIndicesMercado
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public List <ResumenIndice> obtenerIndicesMercado()
			throws Exception;

	/**
	 * Truncates the table unicabd.tbindtra when the cache is cleaned
	 */
	public void deleteOperationsData();

}
/***********************************************End of IIndiceDao.java***************************************/