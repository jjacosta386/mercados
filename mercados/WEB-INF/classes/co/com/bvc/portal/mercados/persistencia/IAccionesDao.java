package co.com.bvc.portal.mercados.persistencia;

import java.util.ArrayList;
import java.util.List;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InformacionTicker;
import co.com.bvc.portal.mercados.modelo.IntradiaTitulo;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.TituloAccion;

// TODO: Auto-generated Javadoc
/**
 * The Interface IAccionesDao.
 */
public interface IAccionesDao {

	/**
	 * consulta y retorna los volumenes negociados para los mercados de acciones (compraventas, repos y ttv).
	 * 
	 * @param fecha the fecha
	 * 
	 * @return the double[]
	 * 
	 * @throws PersistenciaException En caso de problemas al ejecutar la consulta.
	 * 
	 * double[] los volumenes negociados [compraventas, repos, ttv]
	 */
	public double[] obtenerVolumenesNegociados(String fecha)
			throws PersistenciaException;

	
	/**
	 * retorna la lista de las acciones mas transadas de la fecha dada.
	 * 
	 * @param fecha (yyyy-MM-dd)
	 * fecha consultada, nulo o vacio para la fecha actual
	 * 
	 * @return una lista con los registros con las acciones más tranzadas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenAccion> accionesMasTranzadasDia(String fecha) throws PersistenciaException;
	

	/**
	 * retorna el último precio de la acción en una fecha menor que la que se da como parametro.
	 * 
	 * @param fecha limite superior de la consulta, nulo o vacio para el día actual. (yyyyMMdd)
	 * @param nemo acción que se quiere consultar
	 * 
	 * @return último precio para el nemo en una fecha menor a la fecha dada y el día en el que ocurrio.
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public IntradiaTitulo ultimoPrecioAccion(String fecha, String nemo) throws PersistenciaException;

	/**
	 * Cargar historico titulo.
	 * 
	 * @param titulo the titulo
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ICierre> cargarHistoricoTitulo(String titulo)
			throws PersistenciaException;

	/**
	 * Devuelve los datos del titulo: `nemotecnico` ,<br/> `emisor` ,<br/>
	 * `codigoSuper` ,<br/> `dividendos` ,<br/> `accionesCirculacion` ,<br/>
	 * `valorNominal` ,<br/> `precioBase` ,<br/> `precioMinimo` ,<br/>
	 * `precioMaximo` ,<br/> `bursatilidad` ,<br/> `estado` ,<br/> `isin` ,<br/>
	 * `.
	 * 
	 * @param nemo the nemo
	 * 
	 * @return the titulo accion
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public TituloAccion cargarDatosTituloUltimoDia(String nemo)
			throws Exception;

	/**
	 * devuelve las operaciones de la accion en la fecha dada.
	 * 
	 * @param nemo de la accion
	 * @param fecha en formato yyyy-MM-dd O NULO en caso del día actual
	 * @param delay indica si se debe tener un retraso de 20 minutos o no en la consulta del intradía
	 * 
	 * @return the list< operacion dia acciones>
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public List<OperacionDiaAcciones> operacionesDia(String nemo, String fecha, boolean delay)
			throws Exception;

	/**
	 * Cargar emisor.
	 * 
	 * @param emisor the emisor
	 * 
	 * @return the emisor titulo
	 * 
	 * @throws Exception the exception
	 */
	public EmisorTitulo cargarEmisor(String emisor)
			throws Exception;

	/**
	 * Acciones ticker.
	 * 
	 * @return the list< informacion ticker>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<InformacionTicker> accionesTicker() throws PersistenciaException;

	/**
	 * Cargar datos titulo.
	 * 
	 * @param nemo the nemo
	 * @param fechaInic the fecha inic
	 * @param fechaFin the fecha fin
	 * 
	 * @return the list< titulo accion>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<TituloAccion> cargarDatosTitulo(String nemo, String fechaInic,
			String fechaFin) throws PersistenciaException;

	/**
	 * Gets the acciones activas.
	 * 
	 * @return the acciones activas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public ArrayList<String> getAccionesActivas() throws PersistenciaException;

	/**
	 * Gets the acciones todas.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<AccionesAutocomplete> getAccionesTodas()
			throws PersistenciaException;

	/**
	 * Cargar intradia titulo.
	 * 
	 * @param titulo The titulo to be queried
	 * 
	 * @return @see {@link IntradiaTitulo}
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public ICierre cargarIntradiaTitulo(String titulo)
			throws PersistenciaException;

	/**
	 * Cargar intradia titulo historico dia.
	 * 
	 * @param titulo The titulo to be queried
	 * @param fechaIni The begin date
	 * @param fechaFin The end date
	 * @param delay the delay
	 * 
	 * @return {@link IntradiaTitulo}
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ICierre> cargarIntradiaTituloHistoricoDia(String titulo,
			String fechaIni, String fechaFin, boolean delay) throws PersistenciaException;

	
	/**
	 * retorna la lista de las acciones mas transadas de la fecha dada.
	 * 
	 * @param fecha (yyyy-MM-dd)
	 * fecha consultada, nulo o vacio para la fecha actual
	 * 
	 * @return una lista con los registros con las acciones más tranzadas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenAccion> accionesMasTranzadasHome(String fecha) throws PersistenciaException;
	
	/**
	 * Cargar historico titulo.
	 * 
	 * @param titulo the titulo
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ICierre> cargarHistoricoTituloBVC(String titulo)
			throws PersistenciaException;
	
	

}