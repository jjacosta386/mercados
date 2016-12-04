package co.com.bvc.portal.mercados.servicio;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDivisas.
 */
public interface IDivisas {

	/**
	 * retornma el volumen total del mercado por fecha.
	 * 
	 * @param fecha en formato "yyyy-MM-dd"
	 * 
	 * @return the double
	 */
	public Double obtenerResumenMercadoPorFecha(String fecha);

}
