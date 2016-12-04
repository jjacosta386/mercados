package co.com.bvc.portal.mercados.servicio.ajax.imp;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.servicio.IAccionesMGC;
import co.com.bvc.portal.mercados.servicio.ajax.IAccionesMGCAjax;

// TODO: Auto-generated Javadoc
/**
 * The Class AccionesAjax.
 */
public class AccionesMGCAjax implements IAccionesMGCAjax
{

	/** The acciones servicio. */
	private IAccionesMGC accionesMGCServicio;  
	

	/**
	 * Sets the acciones servicio.
	 * 
	 * @param accionesServicio the new acciones servicio
	 */
	public void setAccionesMGCServicio(IAccionesMGC accionesMGCServicio) {
		this.accionesMGCServicio = accionesMGCServicio;
	}


	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.ajax.IAccionesAjax#getNemotecnicos()
	 */
	@SuppressWarnings("unchecked")
	public String[] getNemotecnicos()
	{
		List<AccionesAutocomplete> auto = new ArrayList<AccionesAutocomplete>();
		auto = accionesMGCServicio.accionesTodas();
		Iterator it = auto.iterator();
		String [] res = new String[auto.size()];
		int i =0;
		while(it.hasNext()){
			AccionesAutocomplete tmp = (AccionesAutocomplete)it.next();
			res[i] = tmp.getNemo();
			i++;
		}
		
		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.ajax.IAccionesAjax#getEmisores()
	 */
	@SuppressWarnings("unchecked")
	public String[] getEmisores()
	{
		List<AccionesAutocomplete> auto = new ArrayList<AccionesAutocomplete>();
		auto = accionesMGCServicio.emisoresTodos();
		Iterator it = auto.iterator();
		String [] res = new String[auto.size()];
		int i =0;
		while(it.hasNext()){
			AccionesAutocomplete tmp = (AccionesAutocomplete)it.next();
			res[i] = tmp.getNombreEmisor();
			i++;
		}
		
		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.ajax.IAccionesAjax#getEmisores()
	 */
	@SuppressWarnings("unchecked")
	public String[] getEmisoresCotiza()
	{
		List<AccionesAutocomplete> auto = new ArrayList<AccionesAutocomplete>();
		auto = accionesMGCServicio.CotizaEmisoresTodos();
		Iterator it = auto.iterator();
		String [] res = new String[auto.size()];
		int i =0;
		while(it.hasNext()){
			AccionesAutocomplete tmp = (AccionesAutocomplete)it.next();
			res[i] = tmp.getNombreEmisor();
			i++;
		}
		
		return res;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.ajax.IAccionesAjax#getNemotecnicos()
	 */
	@SuppressWarnings("unchecked")
	public String[] getAccionesCotiza()
	{
		List<AccionesAutocomplete> auto = new ArrayList<AccionesAutocomplete>();
		auto = accionesMGCServicio.CotizaAccionesTodas();
		Iterator it = auto.iterator();
		String [] res = new String[auto.size()];
		int i =0;
		while(it.hasNext()){
			AccionesAutocomplete tmp = (AccionesAutocomplete)it.next();
			res[i] = tmp.getNemo();
			i++;
		}
		
		return res;
	}

}
