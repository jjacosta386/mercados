package co.com.bvc.portal.mercados.modelo;

import java.util.Comparator;
import java.util.SortedMap;


// TODO: Auto-generated Javadoc
/**
 * The Class RentaFijaResumenTipoTitulo.
 */
public class RentaFijaResumenTipoTitulo  implements Comparator<RentaFijaResumenTipoTitulo>{
	
	/** The nombre. */
	private String nombre;
	
	/** The codigo. */
	private String codigo;
	
	/** The cantidad. */
	private double cantidad;
	
	/** The volumen. */
	private double volumen;
	
	/** The especies. */
	private SortedMap<String, RentaFijaResumenEspecie> especies;
	
	
	/**
	 * Gets the nombre.
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 * 
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the cantidad.
	 * 
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 * 
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the volumen.
	 * 
	 * @return the volumen
	 */
	public double getVolumen() {
		return volumen;
	}

	/**
	 * Sets the volumen.
	 * 
	 * @param volumen the new volumen
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	/**
	 * Sets the especies.
	 * 
	 * @param especies the especies
	 */
	public void setEspecies(SortedMap<String, RentaFijaResumenEspecie> especies) {
		this.especies = especies;
	}

	/**
	 * Gets the especies.
	 * 
	 * @return the especies
	 */
	public SortedMap<String, RentaFijaResumenEspecie> getEspecies() {
		return especies;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(RentaFijaResumenTipoTitulo o1,
			RentaFijaResumenTipoTitulo o2) {
		
		if ( o1.getVolumen() < o2.getVolumen())
			return 1;
		
		else if ( o1.getVolumen() > o2.getVolumen())
			return -1;
			
			
		
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Sets the codigo.
	 * 
	 * @param codigo the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the codigo.
	 * 
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	


	
	
	


}
