package co.com.bvc.portal.mercados.vista.cache;

import java.util.List;

import co.com.bvc.portal.mercados.modelo.ICierre;

/**
 * @author BVC
 * @descriptionIntradiaCache contract.
 * 
 */

public interface IIntradiaCache {

	/**
	 * Actualiza el cache con los valores del Intradia de divisas.
	 * 
	 * @throws Exception the exception
	 */
	public void updateDolarIntradia() throws Exception;

	/**
	 * Retorna el listado del Intradia de operaciones para divisas.
	 * 
	 * @return Una lista con las operaciones de cierre asociadas al mercado de divisas
	 * 
	 * @throws Exception the exception
	 */
	public List<ICierre> getDolaresIntradia() throws Exception ;

	/**
	 * Retorna la ultima operacion de divisas realizada.
	 * 
	 * @return La ultima operacion de divisas.
	 * 
	 * @throws Exception the exception
	 */
	public ICierre getDolarIntradia() throws Exception;
	
	// ******************************
	// INTRADIA ACCIONES
	// ******************************

	/**
	 * Actualiza el cache con los valores del Intradia de Acciones.
	 * 
	 * @throws Exception the exception
	 */
	public void updateAccionesIntradia() throws Exception;

	/**
	 * Retorna el listado del Intradia de operaciones para el titulo
	 * (nemotecnico) requerido del mercado de acciones.
	 * 
	 * @param titulo Nemotecnico requerido
	 * @param delay the delay
	 * 
	 * @return Una lista con las operaciones de cierre asociadas al titulo
	 * requerido efectuadas en el dia actual
	 * 
	 * @throws Exception 	 */
	public List<ICierre> getAccionesIntradia(String titulo, boolean delay) ;
	
	/**
	 * Retorna el listado del Intradia de operaciones para el titulo
	 * (nemotecnico) requerido del mercado de acciones.
	 * 
	 * @param titulo Nemotecnico requerido
	 * @param delay the delay
	 * 
	 * @return Una lista con las operaciones de cierre asociadas al titulo
	 * requerido efectuadas en el dia actual
	 * 
	 * @throws Exception 	 */
	public List<ICierre> getAccionesMGCIntradia(String titulo, boolean delay) ;

	/**
	 * Retorna la ultima operacion realizada para el titulo (nemotecnico)
	 * requerido del mercado de acciones.
	 * 
	 * @param titulo Nemotecnico requerido
	 * 
	 * @return La ultima operacion de cierre asociada al titulo requerido
	 * efectuada en el dia actual
	 */
	public ICierre getAccionIntradia(String titulo);

	// ******************************
	// INTRADIA DERIVADOS ENERGÉTICOS
	// ******************************

	/**
	 * Actualiza el cache con los valores del Intradia de Acciones del mercado
	 * de derivados energéticos.
	 * 
	 * @throws Exception the exception
	 */
	public void updateDerivadosDrvxIntradia() throws Exception;

	/**
	 * Retorna el listado del Intradia de operaciones para el titulo
	 * (nemotecnico) requerido del mercado de derivados energéticos.  
	 * 
	 * @param titulo Nemotecnico requerido
	 * 
	 * @return Una lista con las operaciones de cierre asociadas al titulo
	 * requerido efectuadas en el dia actual
	 * 
	 * @throws Exception 	 */
	public List<ICierre> getDerivadosDrvxIntradia(String titulo) ;

	/**
	 * Retorna la ultima operacion realizada para el titulo (nemotecnico)
	 * requerido del mercado de derivados energéticos.
	 * 
	 * @param titulo Nemotecnico requerido
	 * 
	 * @return La ultima operacion de cierre asociada al titulo requerido
	 * efectuada en el dia actual
	 */
	public ICierre getDerivadoDrvxIntradia(String titulo);
	
	// ******************************
	// INTRADIA DERIVADOS
	// ******************************

	/**
	 * Actualiza el cache con los valores del Intradia de Acciones del mercado
	 * de derivados.
	 * 
	 * @throws Exception the exception
	 */
	public void updateDerivadosIntradia() throws Exception;

	/**
	 * Retorna el listado del Intradia de operaciones para el titulo
	 * (nemotecnico) requerido del mercado de derivados.
	 * 
	 * @param titulo Nemotecnico requerido
	 * @param opcf the opcf
	 * 
	 * @return Una lista con las operaciones de cierre asociadas al titulo
	 * requerido efectuadas en el dia actual
	 * 
	 * @throws Exception 	 */
	public List<ICierre> getDerivadosIntradia(String titulo, boolean opcf) ;

	/**
	 * Retorna la ultima operacion realizada para el titulo (nemotecnico)
	 * requerido del mercado de derivados.
	 * 
	 * @param titulo Nemotecnico requerido
	 * 
	 * @return La ultima operacion de cierre asociada al titulo requerido
	 * efectuada en el dia actual
	 */
	public ICierre getDerivadoIntradia(String titulo);

	// ******************************
	// INTRADIA RENTA FIJA
	// ******************************

	/**
	 * Actualiza el cache con los valores del Intradia de Renta Fija del mercado
	 * de renta fija.
	 * 
	 * @throws Exception the exception
	 */
	public void updateRentasFijasIntradia() throws Exception;

	/**
	 * Retorna el listado del Intradia de operaciones para el titulo
	 * (nemotecnico) requerido del mercado de Renta Fija.
	 * 
	 * @param titulo Nemotecnico requerido
	 * 
	 * @return Una lista con las operaciones de cierre asociadas al titulo
	 * requerido efectuadas en el dia actual
	 * 
	 * @throws Exception 	 */
	public List<ICierre> getRentasFijaIntradia(String titulo);

	/**
	 * Retorna la ultima operacion realizada para el titulo (nemotecnico)
	 * requerido del mercado de Renta Fija.
	 * 
	 * @param titulo Nemotecnico requerido
	 * 
	 * @return La ultima operacion de cierre asociada al titulo requerido
	 * efectuada en el dia actual
	 */
	public ICierre getRentaFijaIntradia(String titulo);

	// ******************************
	// INTRADIA INDICE
	// ******************************

	/**
	 * Retorna el listado del Intradia de operaciones para el titulo
	 * (nemotecnico) requerido del mercado de Indices.
	 * 
	 * @param titulo Nemotecnico requerido
	 * 
	 * @return Una lista con las operaciones de cierre asociadas al titulo
	 * requerido efectuadas en el dia actual
	 * 
	 * @throws Exception the exception
	 */
	public ICierre getIndiceIntradia(String titulo) throws Exception;

	/**
	 * Retorna el listado del Intradia de operaciones para el titulo
	 * (nemotecnico) requerido del mercado de Indices.
	 * 
	 * @param titulo Nemotecnico requerido
	 * 
	 * @return Una lista con las operaciones de cierre asociadas al titulo
	 * requerido efectuadas en el dia actual
	 * 
	 * @throws Exception the exception
	 */
	public List<ICierre> getIndicesIntradia(String titulo) throws Exception;
	
	/**
	 * Retorna el listado del Intradia de operaciones para el titulo
	 * (nemotecnico) requerido del mercado de Indices.
	 * 
	 * @param titulo Nemotecnico requerido
	 * 
	 * @return Una lista con las operaciones de cierre asociadas al titulo
	 * requerido efectuadas en el dia actual
	 * 
	 * @throws Exception the exception
	 */
	public List<ICierre> getIndicesIntradiaRF(String titulo) throws Exception;

}
/*************************************************End of IIntradiaCache.java**********************************/