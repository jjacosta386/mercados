package co.com.bvc.portal.mercados.servicio;

import java.util.GregorianCalendar;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IServicioMercadosUtil.
 */
public interface IServicioMercadosUtil {

	/**
	 * Indica si el calendario dado está en un mercado abierto o cerrado.
	 * 
	 * @param fecha the fecha
	 * @param tipoMercado the tipo mercado
	 * 
	 * @return the mensaje mercado abierto
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public String getMensajeMercadoAbierto(GregorianCalendar fecha, int tipoMercado) throws PersistenciaException;
	
}
