package co.com.bvc.portal.mercados.servicio;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.modelo.AccionesMGCBusquedaForm;
import co.com.bvc.portal.mercados.modelo.CotizaPaisMGC;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.ResumenEtfsMGC;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.modelo.VolumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.HorarioMercadoMGC;

public interface IAccionesMGC {
	
	/** The Constant TIPO_MERCADO_COMPRAVENTAS. */
	public static final int TIPO_MERCADO_COMPRAVENTAS = 1;
	
	/** The Constant TIPO_MERCADO_REPOS. */
	public static final int TIPO_MERCADO_REPOS	 = 2;
	
	/** The Constant TIPO_MERCADO_TTV. */
	public static final int TIPO_MERCADO_TTV = 3;
	
	/**  The Constant TIPO_MERCADO_ACCIONES MGC. */
	public static final int TIPO_MERCADO_ACCIONES_MGC = 4;
	
	/** The Constant TIPO_MERCADO_ETF MGC. */
	public static final int TIPO_MERCADO_ETF_MGC = 5;
	
	/** The Constant TIPO_MERCADO_REPOS MGC. */
	public static final int TIPO_MERCADO_REPOS_MGC = 6;
	
	/** The Constant TIPO_MERCADO_TTV MGC. */
	public static final int TIPO_MERCADO_TTV_MGC = 7;
	
	/** The Constant TIPO_LISTA_ACCIONES_MGC. */
	public static final String TIPO_LISTA_ACCIONES_MGC = "M";
	
	/** The Constant TIPO_LISTA_ETF_MGC. */
	public static final String TIPO_LISTA_ETF_MGC = "E";
	
	/**
	 * Operaciones dia.
	 * 
	 * @param nemo the nemo
	 * @param fecha the fecha
	 * @param tipoMercado the tipo mercado
	 * @param delay the delay
	 * 
	 * @return the list< operacion dia acciones>
	 * 
	 * @throws Exception the exception
	 */
	public List<OperacionDiaAcciones> operacionesDia(String nemo, String fecha,
			int tipoMercado, boolean delay) throws Exception;


	/**
	 * retorna a la vista por fecha y tipo mercado.
	 * 
	 * @param fecha si es nulo debe retornar el dia de hoy
	 * @param resultados the resultados
	 * @param tipoMercado the tipo mercado
	 * 
	 * @return the list< resumen accion mgc>
	 * 
	 * @throws Exception the exception
	 */
	public List<ResumenAccionMGC> accionesMasTranzadasDia(String fecha,
			int resultados, int tipoMercado) throws Exception;
	
	/**
	 * retorna a la vista por fecha y tipo mercado.
	 * 
	 * @param fecha si es nulo debe retornar el dia de hoy
	 * @param resultados the resultados
	 * @param tipoMercado the tipo mercado
	 * 
	 * @return the list< resumen etf's mgc>
	 * 
	 * @throws Exception the exception
	 */
	public List<ResumenEtfsMGC> etfsMasTranzadasDia(String fecha,
			int resultados, int tipoMercado) throws Exception;
	
	/**
	 * util para el excel (rango de datos).
	 * 
	 * @param nemo the nemo
	 * @param tipoMercado the tipo mercado
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * 
	 * @return the list< titulo accion>
	 * 
	 * @throws Exception the exception
	 */
	public List<TituloAccion> cargarDatosTituloDia(String nemo,
			int tipoMercado, String fechaIni, String fechaFin) throws Exception;

	/**
	 * Cargar historico titulo.
	 * 
	 * @param titulo the titulo
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws Exception the exception
	 */
	public List<ICierre> cargarHistoricoTitulo(String titulo) throws Exception;


	/**
	 * devuelve a la vista la información de la accion a partir del tipo de mercado y de la fecha.
	 * 
	 * @param nemo the nemo
	 * @param fecha the fecha
	 * @param tipoMercado the tipo mercado
	 * @param operaciones the operaciones
	 * @param delay the delay
	 * 
	 * @return the titulo accion
	 * 
	 * @throws Exception the exception
	 */
	public TituloAccion cargarDatosTituloDia(String nemo, String fecha, int tipoMercado, List<OperacionDiaAcciones> operaciones, boolean delay)
		throws Exception;
	
	/**
	 * calcula el volumen total negociado para los mercados de acciones, con 20 minutos de atraso ( compraventa, repos, ttv y total ).
	 * 
	 * @param fecha la fecha a consultar o "" o null para consultar la fecha actual
	 * 
	 * @return List< VolumenAccion > los totales [compraventa, repos, ttv, total]
	 * 
	 * @throws Exception the exception
	 */
	public List<VolumenAccionMGC> cargarVolumenDia(String fecha) throws Exception;
	
	/**
	 * Saca de la lista aquellos elementos que no cumplen con el filtro.
	 * 
	 * @param tipoLista indica si es una lista con las acciones que bajan o las que suben. @see AccionComparador.SUBE @see AccionComparador.BAJA
	 * @param lista lista con los elementos debidamente ordenados
	 * 
	 * @return the list< resumen accionMGC>
	 */
	public List<ResumenAccionMGC> sacarAcciones (int tipoLista, List<ResumenAccionMGC> lista);
	
	/**
	 * Saca de la lista aquellos elementos que no cumplen con el filtro.
	 * 
	 * @param tipoLista indica si es una lista con las acciones que bajan o las que suben. @see AccionComparador.SUBE @see AccionComparador.BAJA
	 * @param lista lista con los elementos debidamente ordenados
	 * 
	 * @return the list< resumen accionMGC>
	 */
	public List<ResumenEtfsMGC> sacarAccionesETF (int tipoLista, List<ResumenEtfsMGC> lista);

	/**
	 * Saca de la lista aquellos elementos que no cumplen con el filtro.
	 * 
	 * @param tipoLista indica si es una lista con las ETF's que bajan o las que suben. @see EtfsMGCComparador.SUBE @see EtfsMGCComparador.BAJA
	 * @param lista lista con los elementos debidamente ordenados
	 * 
	 * @return the list< resumen ETF>
	 */
	public List<ResumenEtfsMGC> sacarEtfs (int tipoLista, List<ResumenEtfsMGC> lista);

	/**
	 * Acciones todas.
	 * 
	 * @return the list< acciones autocomplete>
	 */
	public List<AccionesAutocomplete> accionesTodas();
	
	/**
	 * Emisores todos.
	 * 
	 * @return the list< acciones autocomplete>
	 */
	public List<AccionesAutocomplete> emisoresTodos();
	
	/**
	 * Emisores de Cotizacion todos.
	 * 
	 * @return the list< acciones autocomplete>
	 */
	public List<AccionesAutocomplete> CotizaEmisoresTodos();
	
	/**
	 * Acciones de Cotizacion todos.
	 * 
	 * @return the list< acciones autocomplete>
	 */
	public List<AccionesAutocomplete> CotizaAccionesTodas();
	
	/**
	 * retorna a la vista el listado por Acciones o etf's de MGC.
	 * 
	 * 
	 * @throws Exception the exception
	 */
	public List<ResumenAccionMGC> listadoAccionesETFMGC(String fecha,
			int resultados, int tipoMercado) throws Exception;
	
	/**
	 * retorna a la vista el listado total de Acciones y etf's de MGC.
	 * 
	 * 
	 * @throws Exception the exception
	 */
	public List<ResumenAccionMGC> listadoTotalAccionesETFMGC(String fecha,
			int resultados, int tipoMercado) throws Exception;
	
	 /* retorna a la vista el listado de Cotizaciones por paises.
	 * 
	 * 
	 * @throws Exception the exception
	 */
	public List<CotizaPaisMGC> cotizaTotalPaisMGC() throws Exception;
	
	/**
	 * retorna a la vista el listado para la busqueda de MGC.
	 * 
	 * 
	 * @throws Exception the exception
	 */
	public Model cargarlistasMGC(Model model) throws PersistenciaException;
	
	/**
	 * retorna a la vista el listado para la busqueda de MGC.
	 * 
	 * 
	 * @throws Exception the exception
	 */
	public List<ResumenAccionMGC> listasFiltroMGC(ResumenAccionMGC listaMGC) throws PersistenciaException;
	
	/**
	 * retorna a la vista el listado para la busqueda de MGC.
	 * 
	 * 
	 * @throws Exception the exception
	 */
	public List<ResumenAccionMGC> tipoMercadoMGCService(String nemo) throws PersistenciaException;
	
	/**
	 * retorna a la vista el listado de horarios del mercado MGC.
	 * 
	 * @param mercado
	 * 
	 * @return La lista de horarios del mercado Global Colombiano.
	 * 
	 * @throws Exception the exception
	 */
	public List<HorarioMercadoMGC> obtenerHorarioNegociacionMGC(int mercado) throws PersistenciaException;
	
	/**
	 * retorna a la vista el listado para la busqueda de MGC.
	 * 
	 * 
	 * @throws Exception the exception
	 */
	public Model cargarlistasCotizaMGC(Model model) throws PersistenciaException;
	
	/**
	 * retorna a la vista el listado para la busqueda de MGC.
	 * 
	 * 
	 * @throws Exception the exception
	 */
	public List<CotizaPaisMGC> CotizaFiltroMGC(CotizaPaisMGC listaMGC) throws PersistenciaException;


	public List<ResumenAccionMGC> reposMasTranzadasDia(String fecha, int i,int tipoMercadoReposMgc) throws Exception;


	public List<ResumenAccionMGC> ttvMasTranzadasDia(String fecha, int i, int tipoMercadoReposMgc) throws Exception;
	
	
	/**
	 * retorna a la vista por fecha y tipo mercado.
	 * 
	 * @param fecha si es nulo debe retornar el dia de hoy
	 * @param resultados the resultados
	 * @param tipoMercado the tipo mercado
	 * 
	 * @return the list< resumen accion>
	 * 
	 * @throws Exception the exception
	 */
	public Map<String, List<ResumenAccionMGC>> accionesMasTranzadasHome(String fecha,
			int resultados, int tipoMercado) throws Exception;
	
	/**
	 * retorna a la vista por fecha y tipo mercado.
	 * 
	 * @param fecha si es nulo debe retornar el dia de hoy
	 * @param resultados the resultados
	 * @param tipoMercado the tipo mercado
	 * 
	 * @return the list< resumen accion>
	 * 
	 * @throws Exception the exception
	 */
	public Map<String, List<ResumenEtfsMGC>> etfsMasTranzadasDiaHome(String fecha,
			int resultados, int tipoMercado) throws Exception;
	

}
