package co.com.bvc.portal.mercados.servicio.ajax.imp;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.com.bvc.portal.mercados.modelo.IndicesAutocomplete;
import co.com.bvc.portal.mercados.servicio.IIndices;
import co.com.bvc.portal.mercados.servicio.ajax.IAccionesAjax;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicesAjax.
 */
public class IndicesAjax implements IAccionesAjax
{

	/** The indices. */
	private IIndices indices;

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.servicio.ajax.IAccionesAjax#getNemotecnicos()
	 */
	@SuppressWarnings("unchecked")
	public String[] getNemotecnicos()
	{
		List<IndicesAutocomplete> auto = new ArrayList<IndicesAutocomplete>();
		auto = indices.getIndicesListar();
		Iterator it = auto.iterator();
		String [] res = new String[auto.size()];
		int i =0;
		while(it.hasNext()){
			IndicesAutocomplete tmp = (IndicesAutocomplete)it.next();
			res[i] = tmp.getNemo();
			i++;
		}
		
		return res;
	}


	/**
	 * Sets the indices.
	 * 
	 * @param indices the new indices
	 */
	public void setIndices(IIndices indices) {
		this.indices = indices;
	}


	/**
	 * Gets the indices.
	 * 
	 * @return the indices
	 */
	public IIndices getIndices() {
		return indices;
	}

}
