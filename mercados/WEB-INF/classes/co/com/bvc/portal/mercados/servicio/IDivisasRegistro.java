package co.com.bvc.portal.mercados.servicio;

import java.util.List;

import co.com.bvc.portal.mercados.modelo.DivisasRegistroTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDivisasRegistro.
 */
public interface IDivisasRegistro extends IDivisas {

	/**
	 * retorna el resumen por divisa.
	 * 
	 * @param fecha the fecha
	 * 
	 * @return the list< divisas registro t o>
	 */
	public List<DivisasRegistroTO> obtenerResumenDivisaYMercadoPorFecha(String fecha);
	
	/**
	 * retorna el detalle de cada divisa en su mercado.
	 * 
	 * @param codigo the codigo
	 * @param mercado the mercado
	 * @param fecha the fecha
	 * 
	 * @return the list< divisas registro t o>
	 */
	public List<DivisasRegistroTO> obtenerDetallePorDivisaYMercado(String codigo, String mercado, String fecha);
	
}
