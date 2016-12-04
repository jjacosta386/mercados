package co.com.bvc.portal.mercados.persistencia;

import java.util.List;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.IJdbcDAO;
import co.com.bvc.portal.mercados.modelo.EmisorTitulo;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.modelo.InformacionTicker;
import co.com.bvc.portal.mercados.modelo.RentaFijaAutocomplete;
import co.com.bvc.portal.mercados.modelo.RentaFijaOperacion;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenTitulo;
import co.com.bvc.portal.mercados.modelo.RentaFijaSumaOperacion;

/**
 * The Interface IRentaFijaDao.
 */
public interface IRentaFijaDao extends IJdbcDAO {

	/**
	 * Retorna los datos necesarios para el excel del detalle de una acción en un rango de fechas
	 * 
	 * @param fechaIni fecha inicial del rango
	 * @param fechaFin fecha final del rango
	 * @param nemo nemotecnico del titulo
	 * @return
	 */
	public List<RentaFijaSumaOperacion> getDetalleExcel(String fechaIni, String fechaFin, String nemo);
	
	/**
	 * Devuelve las operaciones del mercado de renta fija que cumplan con el
	 * filtro dado
	 * 
	 * @param fechaIni
	 *            limite inferior del rango de fecha consultada
	 *            (yyyy-MM-dd:HH:mm). Si es nulo se consultara sobre el día
	 *            actual
	 * @param fechaFin
	 *            limite superior del rango de fecha consultada
	 *            (yyyy-MM-dd:HH:mm).
	 * @param tipoSesion
	 *            sesión de negociación (TRD, REG, etc).
	 * @param tipoOperacion
	 *            tipo de operación consultado (compraventas, repos, ttv, etc).
	 * @param tipoMercado
	 *            Tipo de mercado consultado (Transaccional, Registro, todos)
	 * @return Un listado de las operaciones de renta fija que cumplan con los
	 *         filtros dados
	 * @throws PersistenciaException
	 */
	public List<RentaFijaOperacion> getOperaciones(String fechaIni,
			String fechaFin, String tipoSesion, String tipoOperacion,
			String tipoMercado) throws PersistenciaException;

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
	public List<RentaFijaOperacion> getRentaFijaOperaciones(String dia,
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
	 * Gets the renta fija titulo.
	 * 
	 * @param nemo
	 *            the nemo
	 * 
	 * @return the renta fija titulo
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public RentaFijaResumenTitulo getRentaFijaTitulo(String nemo)
			throws PersistenciaException;

	/**
	 * Renta fija ticker.
	 * 
	 * @return the list< informacion ticker>
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public List<InformacionTicker> rentaFijaTicker()
			throws PersistenciaException;

	/**
	 * Historico nemo.
	 * 
	 * @param nemo
	 *            the nemo
	 * 
	 * @return the list< i cierre>
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public List<ICierre> graficaRentaFija(boolean hoy, String nemo) throws PersistenciaException;

	/**
	 * Gets the datos autocomplete.
	 * 
	 * @return the datos autocomplete
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public List<RentaFijaAutocomplete> getDatosAutocomplete()
			throws PersistenciaException;


	/**
	 * Cargar intradia rentafija.
	 * 
	 * @param nemo
	 *            the nemo
	 * 
	 * @return the i cierre
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public ICierre cargarIntradiaRentafija(String nemo)
			throws PersistenciaException;

	/**
	 * Cargar ultima tasa rentafija.
	 * 
	 * @param nemo
	 *            the nemo
	 * 
	 * @return the double
	 * 
	 * @throws PersistenciaException
	 *             the persistencia exception
	 */
	public Double cargarUltimaTasaRentafija(String nemo)
			throws PersistenciaException;
}
