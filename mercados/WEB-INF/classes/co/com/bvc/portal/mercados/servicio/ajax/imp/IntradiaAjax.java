package co.com.bvc.portal.mercados.servicio.ajax.imp;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;
import co.com.bvc.portal.mercados.servicio.IServicioMercadosUtil;
import co.com.bvc.portal.mercados.vista.cache.impl.IntradiaCache;

// TODO: Auto-generated Javadoc
/**
 * The Class IntradiaAjax.
 */
public class IntradiaAjax
{
	
	/** The intradia cache. */
	private IntradiaCache intradiaCache;
	
	/** The servicio mercados util. */
	private IServicioMercadosUtil servicioMercadosUtil;
	
	/**
	 * Sets the servicio mercados util.
	 * 
	 * @param servicioMercadosUtil the new servicio mercados util
	 */
	public void setServicioMercadosUtil(IServicioMercadosUtil servicioMercadosUtil) {
		
		this.servicioMercadosUtil = servicioMercadosUtil;
	}

	/**
	 * Sets the intradia cache.
	 * 
	 * @param intradiaCache the new intradia cache
	 */
	public void setIntradiaCache(IntradiaCache intradiaCache)
	{
		this.intradiaCache = intradiaCache;
	}
	
	/**
	 * Retorna la ultima operacion accion disponible en el dia actual.
	 * 
	 * @param titulo Nemotecnico de la accion requerida.
	 * 
	 * @return Una cadena separada por comas con los datos de la accion.
	 */
	public String getIntradiaAccion( String titulo )
	{	
		String out = "";
		if (isMarketOpen("ACCION")){
			ICierre accionIntradia = intradiaCache.getAccionIntradia(titulo);
			SimpleDateFormat frm = new SimpleDateFormat( "yyyy-MM-dd kk:mm" );
			String fecha = frm.format(accionIntradia.getFechaHora().getTime());
			out = fecha + "," + String.valueOf( accionIntradia.getValorCierre() ) + "," + String.valueOf( accionIntradia.getVolumen() ) + "," + String.valueOf( accionIntradia.getValorUltimoCierre() );
		} else {
			out = "MERCADO_CERRADO";
		}
		return out;
	}
	
	/**
	 * Retorna la ultima operacion derivado disponible en el dia actual.
	 * 
	 * @param titulo Nemotecnico de la accion requerida.
	 * 
	 * @return Una cadena separada por comas con los datos del derivado.
	 */
	public String getIntradiaDerivado ( String titulo )
	{
		String out = "";
		if (isMarketOpen("DERIVADO")){
			ICierre derivadoIntradia = intradiaCache.getDerivadoIntradia(titulo);
			SimpleDateFormat frm = new SimpleDateFormat( "yyyy-MM-dd kk:mm" );
			String fecha = frm.format(derivadoIntradia.getFechaHora().getTime());
			out = fecha + "," + String.valueOf( derivadoIntradia.getValorCierre() ) + "," + String.valueOf( derivadoIntradia.getValorUltimoCierre() );
		} else {
			out = "MERCADO_CERRADO";
		}
		return out;
	}
	
	/**
	 * Retorna el último valor del índice disponible en el día actual.
	 * 
	 * @param titulo Nemotecnico del indice requerido
	 * 
	 * @return Una cadena separada por comas con los datos del indice.
	 */
	public String getIntradiaIndice ( String titulo )
	{
		String out = "";
		if (isMarketOpen("INDICE")){
			ICierre indiceIntradia = intradiaCache.getIndiceIntradia(titulo);
			SimpleDateFormat frm = new SimpleDateFormat( "yyyy-MM-dd kk:mm" );
			String fecha = frm.format(indiceIntradia.getFechaHora().getTime());
			out = fecha + "," + String.valueOf( indiceIntradia.getValorCierre() ) + ",0.0" + "," + String.valueOf( indiceIntradia.getValorUltimoCierre() );
		} else {
			out = "MERCADO_CERRADO";
		}
		return out;
	}
	
	/**
	 * Retorna el último valor del índice disponible en el día actual.
	 * 
	 * @param titulo Nemotecnico del indice requerido
	 * 
	 * @return Una cadena separada por comas con los datos de la renta fija.
	 */
	public String getIntradiaRentaFija ( String titulo )
	{
		String out = "";
		if (isMarketOpen("RENTA_FIJA")){
			ICierre rentaFinaIntradia = intradiaCache.getRentaFijaIntradia(titulo);
			SimpleDateFormat frm = new SimpleDateFormat( "yyyy-MM-dd kk:mm" );
			String fecha = frm.format(rentaFinaIntradia.getFechaHora().getTime());
			out = fecha + "," + String.valueOf( rentaFinaIntradia.getValorCierre() ) + ",0.0" + "," + String.valueOf( rentaFinaIntradia.getValorUltimoCierre() );
		} else {
			out = "MERCADO_CERRADO";
		}
		return out;
	}
	
	/**
	 * Checks if is market open.
	 * 
	 * @param tipoTitulo the tipo titulo
	 * 
	 * @return true, if is market open
	 */
	private boolean isMarketOpen(String tipoTitulo){	
		
		try {
			return IMercadoDao.MERCADO_ABIERTO.equals(servicioMercadosUtil.getMensajeMercadoAbierto(new GregorianCalendar(), IMercadoDao.ACCIONES));
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return false;
	}


}
