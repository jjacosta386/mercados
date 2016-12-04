package co.com.bvc.portal.mercados.servicio.ajax.imp;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.com.bvc.portal.mercados.modelo.DerivadosAutocomplete;
import co.com.bvc.portal.mercados.servicio.IDerivados;
import co.com.bvc.portal.mercados.servicio.ajax.IDerivadosAjax;


// TODO: Auto-generated Javadoc
/**
 * The Class DerivadosAjax.
 */
public class DerivadosAjax implements IDerivadosAjax
{

	/** The derivados. */
	private IDerivados derivados;  
	

	/**
	 * Sets the derivados.
	 * 
	 * @param derivados the new derivados
	 */
	public void setDerivados(IDerivados derivados)
	{
		this.derivados = derivados;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.ajax.IDerivadosAjax#getNemotecnicos()
	 */
	@SuppressWarnings("unchecked")
	public String[] getNemotecnicos()
	{
		List<DerivadosAutocomplete> auto = new ArrayList<DerivadosAutocomplete>();
		auto = derivados.derivadosTodas();
		Iterator it = auto.iterator();
		String [] res = new String[auto.size()];
		int i =0;
		while(it.hasNext()){
			DerivadosAutocomplete tmp = (DerivadosAutocomplete)it.next();
			res[i] = tmp.getNemo();
			i++;
		}
		
		return res;
	}

}
