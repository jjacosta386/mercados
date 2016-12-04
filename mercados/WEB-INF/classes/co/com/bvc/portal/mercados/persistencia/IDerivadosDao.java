/**
 * 
 */
package co.com.bvc.portal.mercados.persistencia;

import java.util.List;
import java.util.Set;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.IJdbcDAO;
import co.com.bvc.portal.mercados.modelo.BusquedaDerivados;
import co.com.bvc.portal.mercados.modelo.DerivadoCaracteristicasContrato;
import co.com.bvc.portal.mercados.modelo.DerivadoResumen;
import co.com.bvc.portal.mercados.modelo.DerivadoResumenContrato;
import co.com.bvc.portal.mercados.modelo.DerivadoResumenExtendido;
import co.com.bvc.portal.mercados.modelo.DerivadosAutocomplete;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InformacionTicker;
import co.com.bvc.portal.mercados.modelo.MercadoDerivados;

// TODO: Auto-generated Javadoc
/**
 * Interfaz que define las funciones de acceso a los datos de derivados.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public interface IDerivadosDao extends IJdbcDAO {

	/**
	 * obtiene el resumen de derivados para el día actual.
	 * 
	 * @param fechaAyer Mayor Día Hábil Bursátil menor a la fecha actual (yyyyMMdd).
	 * 
	 * @return {@link Set} resumen de cada uno de los tipos de contrato para el
	 * día
	 * 
	 * @throws Exception the exception
	 */
	public Set<MercadoDerivados> getMercadoDerivados(String fechaAyer)
			throws Exception;

	/**
	 * obtiene el resumen de derivados para un día pasado.
	 * 
	 * @param busquedaDerivados filtros de la consulta
	 * 
	 * @return {@link Set} resumen de cada uno de los tipos de contrato para el
	 * día
	 * 
	 * @throws Exception the exception
	 */
	public Set<MercadoDerivados> getMercadoDerivadosHistoria(
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que obtiene la información de los derivados del día del cual se
	 * pasa el parametro.
	 * 
	 * @param opcion tipo de contrato del cual se quiere obtener el resumen
	 * @param nombreDerivado the nombre derivado
	 * 
	 * @return {@link Set} resumen de los derivados que se quieren consultar
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Set<DerivadoResumen> getDerivados(String opcion,
			String nombreDerivado) throws Exception;

	/**
	 * Metodo obtiene la información de los derivados del día según los
	 * parametros de búsqueda que se envian como parametro.
	 * 
	 * @param busquedaDerivados {@link BusquedaDerivados} parametros de la busqueda de los
	 * derivados
	 * @param fechaAyer the fecha ayer
	 * 
	 * @return {@link Set} lista de los derivados que cumplen con los parametros
	 * de busqueda
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Set<DerivadoResumen> getDerivados(BusquedaDerivados busquedaDerivados)
			throws Exception;


	/**
	 * Metodo que obtiene la información de los derivados extendidos a partir de
	 * un derivado dado.
	 * 
	 * @param nemo Nemo del derivado del que se quiere buscar la información
	 * extendida
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return {@link Set} información de los derivados extendidos del derivado
	 * que se paso como parametro
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Set<DerivadoResumenExtendido> getDerivadosExtendidos(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que obtiene todas las operaciones que se han dado en el día para
	 * el derivado que esta identificado con el nemo que se pasa como parametro.
	 * 
	 * @param nemoDerivado Nemo que identifica el derivado el cual se quieren obtener las
	 * operaciones del día
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return {@link Set} set de las operaciones que se han realizado en el día
	 * del derivado especificado
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Set<DerivadoResumenExtendido> getDerivadosDia(String nemoDerivado,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que obtiene todas las operaciones que se han dado en el día para
	 * el derivado que esta identificado con el nemo que se pasa como parametro.
	 * 
	 * @param nemoDerivado Nemo que identifica el derivado el cual se quieren obtener las
	 * operaciones del día
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return {@link Set} set de las operaciones que se han realizado en el día
	 * del derivado especificado
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Set<DerivadoResumenExtendido> getDerivadosDiaOPCF(
			String nemoDerivado, BusquedaDerivados busquedaDerivados)
			throws Exception;

	/**
	 * Metodo que encuentra el mayor precio para un derivado en un día.
	 * 
	 * @param nemo Nemo del derivado del que se busca la información
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return mayor precio del derivado en el día
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Double getPrecioMayorContrato(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que encuentra el mayor precio para un derivado en un día.
	 * 
	 * @param nemo Nemo del derivado del que se busca la información
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return mayor precio del derivado en el día
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Double getPrecioMayorContratoOPCF(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que encuentra el menor precio para un derivado en un día.
	 * 
	 * @param nemo Nemo del derivado del que se busca la información
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return menor precio del derivado en el día
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Double getPrecioMenorContrato(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que encuentra el menor precio para un derivado en un día.
	 * 
	 * @param nemo Nemo del derivado del que se busca la información
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return menor precio del derivado en el día
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Double getPrecioMenorContratoOPCF(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que encuentra el precio de apertura para un derivado en un día.
	 * 
	 * @param nemo Nemo del derivado del que se busca la información
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return precio de apertura del derivado en el día
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public Double getPrecioApertura(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Gets the precio apertura opcf.
	 * 
	 * @param nemo the nemo
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return the precio apertura opcf
	 * 
	 * @throws Exception the exception
	 */
	public Double getPrecioAperturaOPCF(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que devuelve la última fecha de operación del derivado del que se
	 * pasa el parametro.
	 * 
	 * @param nemo Nemo del derivado del que se busca la ultima fecha de
	 * operacion
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return {@link String} que representa la última fecha de operación del
	 * derivado del cual se pasa como parametro el nemo
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public String getUltimaOperacionDerivado(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que devuelve la última fecha de operación del derivado del que se
	 * pasa el parametro.
	 * 
	 * @param nemo Nemo del derivado del que se busca la ultima fecha de
	 * operacion
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return {@link String} que representa la última fecha de operación del
	 * derivado del cual se pasa como parametro el nemo
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public String getUltimaOperacionDerivadoOPCF(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que encuentra el resumen del contrato de un derivado dado su nemo.
	 * 
	 * @param nemo nemo del derivado del que se quiere encontrar el resumen del
	 * contrato
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return {@link DerivadoResumenContrato} informacion del resumen del
	 * contrato
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public DerivadoCaracteristicasContrato getDerivadoResumenContrato(
			String nemo, BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que encuentra el resumen extendido de un derivado dado su nemo.
	 * 
	 * @param nemo Nemo del derivado del que se quiere encontrar la información
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return {@link DerivadoResumenExtendido} con la información del derivado
	 * que pertenece al nemo que se paso como parametro
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public DerivadoResumenExtendido getDerivadoResumenExtendido(String nemo,
			BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que encuentra el resumen extendido de un OPCF dado su nemo.
	 * 
	 * @param nemo the nemo
	 * @param busquedaDerivados the busqueda derivados
	 * 
	 * @return the derivado resumen extendido opcf
	 * 
	 * @throws Exception the exception
	 */
	public DerivadoResumenExtendido getDerivadoResumenExtendidoOPCF(
			String nemo, BusquedaDerivados busquedaDerivados) throws Exception;

	/**
	 * Metodo que carga el historico de un derivado dado su nemo.
	 * 
	 * @param nemo nemo del que se busca el historico del derivado
	 * 
	 * @return {@link List} con los datos historicos del derivado deseado
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public List<ICierre> cargarHistoricoDerivado(String nemo) throws Exception;

	/**
	 * Metodo que carga el historico de un derivado de opcf dado su nemo.
	 * 
	 * @param nemo nemo del que se busca el historico del derivado
	 * 
	 * @return {@link List} con los datos historicos del derivado deseado
	 * 
	 * @throws PersistenciaException 	 * @throws Exception the exception
	 */
	public List<ICierre> cargarHistoricoDerivadoOPCF(String nemo)
			throws Exception;

	/**
	 * Metodo que obtiene el mejor offer de un derivado dado su nemo y unos
	 * datos de busqueda sobre el si existen.
	 * 
	 * @param nemo Nemo del derivado
	 * @param busquedaDerivados Datos para hacer la busqueda sobre los derivados
	 * 
	 * @return Mejor Offer del derivado
	 * 
	 * @throws Exception the exception
	 */
	public Double getMejorOffer(String nemo, BusquedaDerivados busquedaDerivados)
			throws Exception;

	/**
	 * Metodo que obtiene el mejor bid de un derivado dado su nemo y unos datos
	 * de busqueda sobre el si existen.
	 * 
	 * @param nemo Nemo del derivado
	 * @param busquedaDerivados Datos para hacer la busqueda sobre los derivados
	 * 
	 * @return Mejor Bis del derivado
	 * 
	 * @throws Exception the exception
	 */
	public Double getMejorBid(String nemo, BusquedaDerivados busquedaDerivados)
			throws Exception;

	/**
	 * Metodo que encuentra la información referente a derivados para el Ticker.
	 * 
	 * @return {@link List} Datos del ticker para mercados
	 * 
	 * @throws Exception 	 * @throws PersistenciaException the persistencia exception
	 */
	public List<InformacionTicker> derivadosTicker() throws PersistenciaException;

	/**
	 * Método que retorna el total de contratos realizados en el dia habil
	 * bursátil inmediatamente anterior al dia actual.
	 * 
	 * @return el número de contratos
	 * 
	 * @throws Exception si hay error
	 */
	public Double obtenerTotalContratosUltDia() throws Exception;

	/**
	 * Gets the acciones todas.
	 * 
	 * @return the acciones todas
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<DerivadosAutocomplete> getAccionesTodas()
			throws PersistenciaException;

	/**
	 * Cargar intradia derivados historico.
	 * 
	 * @param nemo the nemo
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ICierre> cargarIntradiaDerivadosHistorico(String nemo)
			throws PersistenciaException;

	/**
	 * Cargar intradia derivados historico opcf.
	 * 
	 * @param nemo the nemo
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ICierre> cargarIntradiaDerivadosHistoricoOPCF(String nemo)
			throws PersistenciaException;

	/**
	 * Cargar intradia derivados.
	 * 
	 * @param nemo the nemo
	 * 
	 * @return the i cierre
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public ICierre cargarIntradiaDerivados(String nemo)
			throws PersistenciaException;
	
	public Integer verificarContratoDerivados(String nemo) throws PersistenciaException ;
	
	public List<DerivadoResumenExtendido> getDerivadoResumenExtendidoGrafica(String nemo,BusquedaDerivados busquedaDerivados) throws Exception ;

}