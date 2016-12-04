package co.com.bvc.portal.mercados.persistencia;

import java.util.Calendar;
import java.util.List;

import co.com.bvc.portal.comun.persistencia.IJdbcDAO;
import co.com.bvc.portal.mercados.modelo.DivisasRegistroTO;
import co.com.bvc.portal.mercados.modelo.DivisasSetFxTO;
import co.com.bvc.portal.mercados.modelo.ICierre;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDivisasDao.
 */
public interface IDivisasDao extends IJdbcDAO {
	
	/** The Constant KEY_CONSULTA_SET_FX. */
	public static final String KEY_CONSULTA_SET_FX = "ESTADO_DIA_SET-FX";
	
	/** The Constant KEY_CONSULTA_TRM. */
	public static final String KEY_CONSULTA_TRM = "TRM";

	/**
	 * Obtener detalle por divisa y mercado.
	 * 
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @param codigo the codigo
	 * @param mercado the mercado
	 * @param hoy the hoy
	 * 
	 * @return the list< divisas registro t o>
	 * 
	 * @throws Exception the exception
	 */
	public List<DivisasRegistroTO> obtenerDetallePorDivisaYMercado(String fechaIni, String fechaFin,String codigo, String mercado, boolean hoy) throws Exception;
	
	/**
	 * Obtener resumen divisa y mercado por fecha.
	 * 
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @param hoy the hoy
	 * 
	 * @return the list< divisas registro t o>
	 * 
	 * @throws Exception the exception
	 */
	public List<DivisasRegistroTO> obtenerResumenDivisaYMercadoPorFecha(String fechaIni, String fechaFin, boolean hoy) throws Exception;
	
	/**
	 * Obtener resumen mercado por fecha.
	 * 
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @param hoy the hoy
	 * 
	 * @return the double
	 * 
	 * @throws Exception the exception
	 */
	public Double obtenerResumenMercadoPorFecha(String fechaIni, String fechaFin, boolean hoy) throws Exception;

	/**
	 * retorna la lista de operaciones de dolar spot para la fecha dada -20 minutos.
	 * 
	 * @param fecha fecha en formato "yyyy-MM-dd:kk:mm"
	 * 
	 * @return the intradia historico dolar
	 * 
	 * @throws Exception the exception
	 */
	public List<ICierre> getIntradiaHistoricoDolar(String fecha) throws Exception;
	
	/**
	 * retorna la última operacion de dolar spot.
	 * 
	 * @return the intradia dolar
	 * 
	 * @throws Exception the exception
	 */
	 public ICierre getIntradiaDolar() throws Exception;
	
	 
	 /**
 	 * retorna el estado de la fecha dada.
 	 * 
 	 * @param fecha en formato "yyyy-MM-dd:HH:mm"
 	 * @param codMercado el código del mercado que se quiere consultar (71 spot, 76 next day)
 	 * 
 	 * @return the divisas set fx to
 	 * 
 	 * @throws Exception the exception
 	 */
	public DivisasSetFxTO obtenerEstadoDia(String fecha, Integer codMercado) throws Exception;
	
	/**
	 * Retorna el valor de la TRM para la fecha dada.
	 * 
	 * @param fecha en formato "yyyyMMdd"
	 * 
	 * @return the valor trm por fecha
	 * 
	 * @throws Exception the exception
	 */
	public Double getValorTRMPorFecha (String fecha) throws Exception;
	
	/**
	 * mira si la fecha parametro es un festivo americano.
	 * 
	 * @param fecha fecha a consultar
	 * 
	 * @return <strong> true </strong> si la fecha es un festivo americano.
	 * <br/><strong> false </strong> en cualquier otro caso.
	 * 
	 * @throws Exception the exception
	 */
	public boolean isFestivoAmericano (Calendar fecha) throws Exception;
}
