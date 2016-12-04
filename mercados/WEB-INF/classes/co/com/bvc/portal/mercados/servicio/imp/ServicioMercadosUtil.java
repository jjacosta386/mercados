package co.com.bvc.portal.mercados.servicio.imp;

import java.util.GregorianCalendar;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IServicioMercadosUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMercadosUtil.
 */
public class ServicioMercadosUtil implements IServicioMercadosUtil {

	/** The mercado dao. */
	private IMercadoDao mercadoDao;

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.IServicioMercadosUtil#getMensajeMercadoAbierto(java.util.GregorianCalendar, int)
	 */
	public String getMensajeMercadoAbierto(GregorianCalendar fecha,
			int tipoMercado) throws PersistenciaException {
		return mercadoDao.mercadoAbierto(fecha, tipoMercado, true);
	}

	/**
	 * Gets the mercado dao.
	 * 
	 * @return the mercado dao
	 */
	public IMercadoDao getMercadoDao() {
		return mercadoDao;
	}

	/**
	 * Sets the mercado dao.
	 * 
	 * @param mercadoDao the new mercado dao
	 */
	public void setMercadoDao(IMercadoDao mercadoDao) {
		this.mercadoDao = mercadoDao;
	}

}
