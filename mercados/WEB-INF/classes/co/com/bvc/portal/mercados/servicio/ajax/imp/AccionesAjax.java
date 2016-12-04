package co.com.bvc.portal.mercados.servicio.ajax.imp;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.com.bvc.portal.mercados.modelo.AccionesAutocomplete;
import co.com.bvc.portal.mercados.servicio.IAcciones;
import co.com.bvc.portal.mercados.servicio.ajax.IAccionesAjax;

// TODO: Auto-generated Javadoc
/**
 * The Class AccionesAjax.
 */
public class AccionesAjax implements IAccionesAjax
{

	/** The acciones servicio. */
	private IAcciones accionesServicio;  
	

	/**
	 * Sets the acciones servicio.
	 * 
	 * @param accionesServicio the new acciones servicio
	 */
	public void setAccionesServicio(IAcciones accionesServicio) {
		this.accionesServicio = accionesServicio;
	}


	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.ajax.IAccionesAjax#getNemotecnicos()
	 */
	@SuppressWarnings("unchecked")
	public String[] getNemotecnicos()
	{
		List<AccionesAutocomplete> auto = new ArrayList<AccionesAutocomplete>();
		auto = accionesServicio.accionesTodas();
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
