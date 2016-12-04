package co.com.bvc.portal.mercados.servicio.ajax.imp;

import java.util.ArrayList;
import java.util.List;

import co.com.bvc.portal.mercados.servicio.IIndices;

/**
 * @author BVC
 * @description The Class ComparacionesAjax.
 */

public class ComparacionesAjax {

	/** The indices comp. */
	private IIndices indicesComp;

	/**
	 * Comparar acciones.
	 * 
	 * @param nemo the nemo
	 * 
	 * @return the string
	 */
	public String compararAcciones(String nemo) {
		List<String> indicesAComparar = new ArrayList<String>();
		indicesAComparar.add("ICAP");
		indicesAComparar.add("ICSC");
		indicesAComparar.add("IIRS");
		String res = this.indicesComp.compararIndices(nemo, nemo, indicesAComparar, "ACCION", null, "RV", null);
		return res;
	}

	/**
	 * Comparar indices.
	 * 
	 * @param nemo the nemo
	 * @param nombreMostrar the nombre mostrar
	 * 
	 * @return the string
	 */
	public String compararIndices(String nemo, String nombreMostrar) {
		String res = this.indicesComp.compararIndices(nemo, nombreMostrar, null, "INDICE", null, "RF",null);
		return res;
	}

	/**
	 * Sets the indices comp.
	 * 
	 * @param indicesComp the new indices comp
	 */
	public void setIndicesComp(IIndices indicesComp) {
		this.indicesComp = indicesComp;
	}

}
/**************************************************End of ComparacionesAjax.java*******************************/