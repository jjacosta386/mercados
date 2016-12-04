package co.com.bvc.portal.mercados.modelo;

import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class AccionComparador.
 */
public class EtfsMGCComparador implements Comparator<ResumenEtfsMGC> {

	/** The Constant VOLUMEN. */
	public static final int VOLUMEN = 1;
	
	/** The Constant SUBE. */
	public static final int SUBE = 2;
	
	/** The Constant BAJA. */
	public static final int BAJA = 3;
	
	/** The comparar. */
	private int comparar = 1;
	
	/**
	 * Instantiates a new ETF's comparador.
	 * 
	 * @param compararPor the comparar por
	 */
	public EtfsMGCComparador (int compararPor){
		this.comparar = compararPor;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(ResumenEtfsMGC o1, ResumenEtfsMGC o2) {
		if(comparar == VOLUMEN){
			return (new Double(o2.getVolumen())).compareTo(o1.getVolumen());
		}
		if(comparar == SUBE){
			return (new Double(o2.getVariacion())).compareTo(o1.getVariacion());
		}
		if(comparar == BAJA){
			return (new Double(o1.getVariacion())).compareTo(o2.getVariacion());
		}
		return 0;
	}

}
