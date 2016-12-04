package co.com.bvc.portal.mercados.persistencia;

import java.util.GregorianCalendar;
import java.util.List;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.HorarioMercado;

// TODO: Auto-generated Javadoc
/**
 * The Interface IMercadoDao.
 */
public interface IMercadoDao {

	/** The ACCIONES. */
	public static int ACCIONES = 1;

	/** The RENT a_ fija. */
	public static int RENTA_FIJA = 2;

	/** The DERIVADOS. */
	public static int DERIVADOS = 3;

	/** The DIVISAS. */
	public static int DIVISAS = 4;
	
	/** The REPOS. */
	public static int REPOS = 4;

	/**TTVS*/
	public static int TTVS = 5;
	
	/** ACCIONES MGC */
	public static int ACCIONES_MGC = 6;
	
	/** REPOS MGC */
	public static int REPOS_MGC = 6;
	
	/** TTVS MGC */
	public static int TTVS_MGC = 6;
	
	/** ETF MGC */
	public static int ETF_MGC = 6;
	
	
	/** The MERCAD o_ cerrado. */
	public static String MERCADO_CERRADO = "Mercado Cerrado";

	/** The MERCAD o_ abierto. */
	public static String MERCADO_ABIERTO = "Mercado Abierto";

	/** The DI a_ n o_ bursatil. */
	public static String DIA_NO_BURSATIL = "Día no bursátil";

	/**
	 * Obtener segmento mercado.
	 * 
	 * @return the list< horario mercado>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<HorarioMercado> obtenerSegmentoMercado()
			throws PersistenciaException;

	/**
	 * Devuelve el mensaje del estado del mercado (abierto, cerrado, día no bursátil).
	 * 
	 * @param fechaConsulta Calendario que representa la hora que de la que se quiere obtener el mensaje de estado
	 * @param tipoMercado indica el segmento de mercado consultado
	 * @param delay indica si se debe, o no, retrasar el calendario 20 minutos
	 * 
	 * @return the string
	 * 
	 * @throws PersistenciaException the persistencia exception
	 * 
	 * @see IMercadosDao.ACCIONES
	 * @see IMercadosDao.RENTA_FIJA
	 * @see IMercadosDao.DERIVADOS
	 * @see IMercadosDao.DIVISAS
	 * @see IMercadosDao.REPOS
	 * El mensaje del estado del mercado
	 * @see IMercadosDao.MERCADO_CERRADO
	 * @see IMercadosDao.MERCADO_ABIERTO
	 * @see IMercadosDao.DIA_NO_BURSATIL
	 */
	public String mercadoAbierto(GregorianCalendar fechaConsulta,
			int tipoMercado, boolean delay) throws PersistenciaException;
}