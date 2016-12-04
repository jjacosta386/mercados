package co.com.bvc.portal.mercados.persistencia;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.modelo.AccionesMGCBusquedaForm;
import co.com.bvc.portal.mercados.modelo.CotizaPaisMGC;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InformacionTicker;
import co.com.bvc.portal.mercados.modelo.IntradiaTituloMGC;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.ResumenEtfsMGC;
import co.com.bvc.portal.mercados.modelo.HorarioMercadoMGC;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import org.springframework.ui.Model;

public interface IAccionesMGCDao {
	
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
	public List<ResumenAccionMGC> accionesMasTranzadasDia(String fecha) throws PersistenciaException;
	

	/**
	 * retorna la lista de las ETF's mas transadas de la fecha dada.
	 * 
	 * @param fecha (yyyy-MM-dd)
	 * fecha consultada, nulo o vacio para la fecha actual
	 * 
	 * @return una lista con los registros con las ETF's más tranzadas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenEtfsMGC> etfsMasTranzadasDia(String fecha) throws PersistenciaException;

	
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
	public IntradiaTituloMGC ultimoPrecioAccion(String fecha, String nemo) throws PersistenciaException;
	
	/**
	 * retorna el último precio de la etf en una fecha menor que la que se da como parametro.
	 * 
	 * @param fecha limite superior de la consulta, nulo o vacio para el día actual. (yyyyMMdd)
	 * @param nemo acción que se quiere consultar
	 * 
	 * @return último precio para el nemo en una fecha menor a la fecha dada y el día en el que ocurrio.
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public IntradiaTituloMGC ultimoPrecioEtf(String fecha, String nemo) throws PersistenciaException;

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
	 * Cargar historico titulo MGC.
	 * 
	 * @param titulo the titulo
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ICierre> cargarHistoricoMGCTitulo(String titulo, String tipoMGC)
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
	 * Gets the emisores todos.
	 * 
	 * @return the emisores todos
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<AccionesAutocomplete> getEmisoresTodos()
			throws PersistenciaException;
	
	/**
	 * Gets the emisores de cotizaciones todos.
	 * 
	 * @return the emisores de cotizaciones todos
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<AccionesAutocomplete> getCotizacionEmisorTodos()
			throws PersistenciaException;
	
	/**
	 * Gets the acciones de cotizaciones todos.
	 * 
	 * @return the acciones de cotizaciones todos
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<AccionesAutocomplete> getCotizacionAccionTodos()
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
	 * retorna el ID y el nombre del Emisor.
	 * 
	 * @param nemo 
	 * nemo técnico de emisor
	 * 
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenAccionMGC> consultaDatosEmisor(String nemo) throws PersistenciaException;
	
	/**
	 * Gets the acciones o etf de MGC.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenAccionMGC> listaAccionesMGC()
			throws PersistenciaException;
	
	/**
	 * Gets the etf de MGC.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenAccionMGC> listaEtfMGC()
			throws PersistenciaException;
	
	/**
	 * Gets the acciones y etf de MGC todas.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenAccionMGC> listaTotalAccionesEtf()
			throws PersistenciaException;
	
	/**
	 * Gets the listas de busqueda de MGC todas.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public Model consultaListasMGC(Model model)
			throws PersistenciaException;
	
	/**
	 * Gets the listas de busqueda de MGC todas.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenAccionMGC> tipoMercadoMGC(String nemo)
			throws PersistenciaException;
	
	/**
	 * Gets the listas filtradas de MGC todas.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenAccionMGC> consultaListasFiltroMGC(ResumenAccionMGC listaMGC)
			throws PersistenciaException;
	
	/**
	 * Gets the horario de negociación del mercado global colombiano.
	 * 
	 * @param int mercado
	 * 
	 * @return the horario de negociación
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<HorarioMercadoMGC> obtenerHorarioNegociacionMGC(int mercado)
			throws PersistenciaException;
	
	/**
	 * Gets the cotizacion por pais mercado global colombiano.
	 * 
	 * @param int mercado
	 * 
	 * @return the cotizacion por pais
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<CotizaPaisMGC> cotizaTotalPaisDaoMGC()
			throws PersistenciaException;
	
	/**
	 * Gets the listas de busqueda de MGC todas.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public Model consultaListaCotizaMGC(Model model)
			throws PersistenciaException;
	
	/**
	 * Gets the listas filtradas de MGC todas.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<CotizaPaisMGC> consultaCotizaFiltroMGC(CotizaPaisMGC listaMGC)
			throws PersistenciaException;


	public List<ResumenAccionMGC> reposMasTranzadasDia(String fecha) throws PersistenciaException, ParseException;


	public List<ResumenAccionMGC> ttvMasTranzadasDia(String fecha) throws PersistenciaException, ParseException;
	
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
	public List<ResumenAccionMGC> accionesMasTranzadasHome(String fecha) throws PersistenciaException;
	
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
	public List<ResumenEtfsMGC> etfsMasTranzadasDiaHome(String fecha) throws PersistenciaException;

}
