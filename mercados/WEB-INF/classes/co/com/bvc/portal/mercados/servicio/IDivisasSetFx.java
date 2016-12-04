package co.com.bvc.portal.mercados.servicio;

import co.com.bvc.portal.mercados.modelo.DivisasSetFxTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDivisasSetFx.
 */
public interface IDivisasSetFx extends IDivisas {

	/** The Constant CODIGO_MERCADO_SPOT. */
	public static final Integer CODIGO_MERCADO_SPOT = 71;
	
	/** The Constant CODIGO_MERCADO_NEXT_DAY. */
	public static final Integer CODIGO_MERCADO_NEXT_DAY = 76;
	
	/**
	 * retorna el último estado de la fecha -20 minutos.
	 * 
	 * @param fecha en formato "yyyy-MM-dd"
	 * 
	 * @return the detalle dia
	 */
	public DivisasSetFxTO getDetalleDia (String fecha);
	
}
