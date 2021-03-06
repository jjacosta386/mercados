package co.com.bvc.portal.mercados.servicio;

import java.util.List;
import java.util.Map;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.RentaFijaOperacion;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.modelo.VolumenAccion;

// TODO: Auto-generated Javadoc
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : IAcciones.java
//  @ Date : 03/09/2008
//  @ Author : 
//
//

/**
 * The Interface IAcciones.
 */
public interface IAcciones {
	
	/** The Constant TIPO_MERCADO_COMPRAVENTAS. */
	public static final int TIPO_MERCADO_COMPRAVENTAS = 1;
	
	/** The Constant TIPO_MERCADO_REPOS. */
	public static final int TIPO_MERCADO_REPOS	 = 2;
	
	/** The Constant TIPO_MERCADO_TTV. */
	public static final int TIPO_MERCADO_TTV = 3;
	
	/** The Constant TIPO_MERCADO_BOSEAS. */
	public static final int TIPO_MERCADO_BOCEAS = 4;
	
	/** The Constant TIPO_MERCADO_BOSEAS. */
	public static final int TIPO_MERCADO_BOCEAS_SIMUL = 5;

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
	 * @return the list< resumen accion>
	 * 
	 * @throws Exception the exception
	 */
	public List<ResumenAccion> accionesMasTranzadasDia(String fecha,
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
	public List<VolumenAccion> cargarVolumenDia(String fecha) throws Exception;
	

	/**
	 * Saca de la lista aquellos elementos que no cumplen con el filtro.
	 * 
	 * @param tipoLista indica si es una lista con las acciones que bajan o las que suben. @see AccionComparador.SUBE @see AccionComparador.BAJA
	 * @param lista lista con los elementos debidamente ordenados
	 * 
	 * @return the list< resumen accion>
	 */
	public List<ResumenAccion> sacarAcciones (int tipoLista, List<ResumenAccion> lista);
	
	/**
	 * Acciones todas.
	 * 
	 * @return the list< acciones autocomplete>
	 */
	public List<AccionesAutocomplete> accionesTodas();
	
	/**
	 * Gets the Boceas detalle operaciones.
	 * 
	 * @param dia
	 *            the dia
	 * @param tipoSesion
	 *            the tipo sesion
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param nemo
	 *            the nemo
	 * 
	 * @return the Boceas detalle operaciones
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */

	public List<RentaFijaOperacion> getBoceasDetalleOperaciones(String dia,
			String tipoSesion, String tipoOperacion, String nemo, String tipoMercado)
			throws PersistenciaException;
	
	/**
	 * Gets the resumen emisor.
	 * 
	 * @param nemo
	 *            the nemo
	 * 
	 * @return the resumen emisor
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public EmisorTitulo getResumenEmisorBoceas(String nemo)
			throws PersistenciaException;
	
	/**
	 * Gets the suma operaciones grafica Boceas.
	 * 
	 * @param dia1
	 *            the dia1
	 * @param dia2
	 *            the dia2
	 * @param tipoSesion
	 *            the tipo sesion
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param nemo
	 *            the nemo
	 * 
	 * @return the suma operaciones grafica Boceas
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public List<RentaFijaSumaOperacion> getSumaOperacionesGraficaBocea(String dia1,
			String dia2, String nemo)
			throws PersistenciaException;
	
	/**
	 * Gets the resumen titulo Boceas.
	 * 
	 * @param nemo
	 *            the nemo
	 * 
	 * @return the resumen titulo Boceas
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public RentaFijaResumenTitulo getResumenTituloBoceas(String nemo)
			throws PersistenciaException;
	
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
	public Map<String, List<ResumenAccion>> accionesMasTranzadasHome(String fecha,
			int resultados, int tipoMercado) throws Exception;
	
	
	
}
