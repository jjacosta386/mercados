package co.com.bvc.portal.mercados.servicio.ajax.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.RentaFijaAutocomplete;
import co.com.bvc.portal.mercados.servicio.IRentaFija;
import co.com.bvc.portal.mercados.servicio.ajax.IRentaFijaAjax;


// TODO: Auto-generated Javadoc
/**
 * The Class RentaFijaAjax.
 */
public class RentaFijaAjax implements IRentaFijaAjax
{

	/** The rentafija. */
	private IRentaFija rentafija;

	/**
	 * Sets the rentafija.
	 * 
	 * @param rentafija the new rentafija
	 */
	public void setRentafija(IRentaFija rentafija)
	{
		this.rentafija = rentafija;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.ajax.IRentaFijaAjax#getNemotecnicos()
	 */
	@SuppressWarnings("unchecked")
	public String[] getNemotecnicos()
	{
		List<RentaFijaAutocomplete> auto = new ArrayList<RentaFijaAutocomplete>();
		try {
			 auto = rentafija.getRentaFijaAutocomplete();
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator it = auto.iterator();
		String [] res = new String[auto.size()];
		int i =0;
		while(it.hasNext()){
			RentaFijaAutocomplete tmp = (RentaFijaAutocomplete)it.next();
			res[i] = tmp.getNemo();
			i++;
		}
		
		return res;
	}

}
