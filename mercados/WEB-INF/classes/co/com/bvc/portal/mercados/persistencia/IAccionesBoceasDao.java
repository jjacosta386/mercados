package co.com.bvc.portal.mercados.persistencia;

import java.util.List;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.RentaFijaOperacion;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.ResumenBoceas;
import co.com.bvc.portal.mercados.modelo.TituloAccion;

public interface IAccionesBoceasDao {
	
	/**
	 * retorna los primeros resumenes de las TTV con más volumen.
	 * 
	 * @param fecha fecha (yyyy-MM-dd) de la que se quiere obtener el resumen, <strong>null</strong> en caso de requerir el resumen del dia actual.
	 * @param resultados número máximo de resultados que se quieren obtener
	 * 
	 * @return the list< resumen accion>
	 * 
	 * @throws Exception the exception
	 */
/*	public List<ResumenAccion> accionesMasTranzadasDiaTTV(String fecha, int resultados)
		throws Exception;
*/
	
	public List<ResumenAccion> cargarMasTranzadasDiaUltDia()
	throws Exception;

/**
* Cargar mas tranzadas dia.
* 
* @param fecha the fecha
* 
* @return the list< resumen accion>
* 
* @throws PersistenciaException the persistencia exception
*/
public List<ResumenAccion> cargarMasTranzadasDia(String fecha)
	throws PersistenciaException;

/**
* Cargar mas tranzadas dia Simultaneas.
* 
* @param fecha the fecha
* 
* @return the list< resumen accion>
* 
* @throws PersistenciaException the persistencia exception
*/
public List<ResumenAccion> cargarMasTranzadasDiaSimul(String fecha)
	throws PersistenciaException;

/**
* Cargar mas tranzadas dia Simultaneas.
* 
* @param fecha the fecha
* 
* @return the list< resumen accion>
* 
* @throws PersistenciaException the persistencia exception
*/
public List<ResumenAccion> cargarMasTranzadasDiaUltDiaSimul()
throws Exception;

/**
* Operaciones ultimo dia.
* 
* @param nemo the nemo
* 
* @return the list< operacion dia acciones>
* 
* @throws PersistenciaException the persistencia exception
*/
public List<OperacionDiaAcciones> operacionesUltimoDia(String nemo)
	throws PersistenciaException;

/**
* Operaciones ultimo dia Simultaneas.
* 
* @param nemo the nemo
* 
* @return the list< operacion dia acciones>
* 
* @throws PersistenciaException the persistencia exception
*/
public List<OperacionDiaAcciones> operacionesSimulUltimoDia(String nemo)
	throws PersistenciaException;

/**
* Operaciones.
* 
* @param nemo the nemo
* @param fecha the fecha
* 
* @return the list< operacion dia acciones>
* 
* @throws PersistenciaException the persistencia exception
*/
public List<OperacionDiaAcciones> operaciones(String nemo, String fecha)
	throws PersistenciaException;

/**
* Operaciones Simultaneas.
* 
* @param nemo the nemo
* @param fecha the fecha
* 
* @return the list< operacion dia acciones>
* 
* @throws PersistenciaException the persistencia exception
*/
public List<OperacionDiaAcciones> operacionesSimul(String nemo, String fecha)
	throws PersistenciaException;

/**
* Carga datos titulo ultimo dia.
* 
* @param titulo the titulo
* 
* @throws PersistenciaException the persistencia exception
*/
public void cargaDatosTituloUltimoDia(TituloAccion titulo)
	throws PersistenciaException;

/**
* Carga datos titulo fecha.
* 
* @param titulo the titulo
* @param fecha the fecha
* 
* @throws PersistenciaException the persistencia exception
*/
public void cargaDatosTituloFecha(TituloAccion titulo, String fecha)
	throws PersistenciaException;

/**
* Carga datos titulo fecha.
* 
* @param nemo the nemo
* @param fechaInicio the fecha inicio
* @param fechaFin the fecha fin
* 
* @return the list< titulo accion>
* 
* @throws PersistenciaException the persistencia exception
*/
public List<TituloAccion> cargaDatosTituloFecha(String nemo,
	String fechaInicio, String fechaFin) throws PersistenciaException;

/**
* Carga datos titulo fecha.
* 
* @param nemo the nemo
* @param fechaInicio the fecha inicio
* @param fechaFin the fecha fin
* 
* @return the list< titulo accion>
* 
* @throws PersistenciaException the persistencia exception
*/
public List<TituloAccion> cargaDatosSimulTituloFecha(String nemo,
	String fechaInicio, String fechaFin) throws PersistenciaException;

/**
 * Devuelve las operaciones del mercado de renta fija que cumplan con el
 * filtro dado para el nemo dado
 * 
 * @param dia
 *            Fecha consultada en formato (yyyy-MM-dd). Si viene nulo se
 *            consultará sobre el día actual
 * @param sesion
 *            Rueda de negociación (TRD, REG, etc).
 * @param tipoMercado
 *            Tipo de mercado consultado (Transaccional, Registro, todos)
 * @param tipoOperacion
 *            tipo de operación consultado (compraventas, repos, ttv, etc).
 * @param nemo
 *            Nemotécnico del titulo consultado
 * @return El listado de operaciones de renta fija que cumplen con los
 *         filtros recibidos como parametro
 * @throws PersistenciaException
 *             En caso de error con el cargue de los datos
 */
public List<RentaFijaOperacion> getBoceasOperaciones(String dia,
		String sesion, String tipoMercado, String tipoOperacion, String nemo)
		throws PersistenciaException;

/**
 * Gets the emisor.
 * 
 * @param nemo
 *            the nemo
 * 
 * @return the emisor
 * 
 * @throws PersistenciaException
 *             the persistencia exception
 */
public EmisorTitulo getEmisor(String nemo) throws PersistenciaException;

/**
 * Retorna los datos necesarios para el excel del detalle de una acción en un rango de fechas
 * 
 * @param fechaIni fecha inicial del rango
 * @param fechaFin fecha final del rango
 * @param nemo nemotecnico del titulo
 * @return
 */
public List<RentaFijaSumaOperacion> getDetalleExcelBocea(String fechaIni, String fechaFin, String nemo);

/**
 * Gets the renta fija titulo Boceas.
 * 
 * @param nemo
 *            the nemo
 * 
 * @return the renta fija titulo Boceas
 * 
 * @throws PersistenciaException
 *             the persistencia exception
 */
public RentaFijaResumenTitulo getRentaFijaTituloBoceas(String nemo)
		throws PersistenciaException;



public List<ResumenBoceas> ExcelBoceas(String fecha)
throws Exception;

public List<ResumenBoceas> ExcelBoceasSimul(String fecha)
throws Exception;

}
