package co.com.bvc.portal.mercados.modelo;

import java.util.Comparator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class RentaFijaResumenEspecie.
 */
public class RentaFijaResumenEspecie implements Comparator<RentaFijaResumenEspecie> {
	
	/** The nemotecnico. */
	private String nemotecnico;
	
	/** The emisor. */
	private String emisor;
	
	/** The cantidad. */
	private double cantidad;
	
	/** The volumen. */
	private double volumen;
	
	/** The ultimo precio sucio. */
	private double ultimoPrecioSucio;
	
	/** The variacion. */
	private float variacion;
	
	/** The hor gra. */
	private String horGra; //fecha y hora de la ultima grabacion
	
	/** The operaciones. */
	private List<RentaFijaOperacion> operaciones;

	/**
	 * Gets the nemotecnico.
	 * 
	 * @return the nemotecnico
	 */
	public String getNemotecnico() {
		return nemotecnico;
	}

	/**
	 * Sets the nemotecnico.
	 * 
	 * @param nemotecnico the new nemotecnico
	 */
	public void setNemotecnico(String nemotecnico) {
		this.nemotecnico = nemotecnico;
	}

	/**
	 * Gets the emisor.
	 * 
	 * @return the emisor
	 */
	public String getEmisor() {
		return emisor;
	}

	/**
	 * Sets the emisor.
	 * 
	 * @param emisor the new emisor
	 */
	public void setEmisor(String emisor) {
		this.emisor = emisor;
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
	 * Gets the ultimo precio sucio.
	 * 
	 * @return the ultimo precio sucio
	 */
	public double getUltimoPrecioSucio() {
		return ultimoPrecioSucio;
	}

	/**
	 * Sets the ultimo precio sucio.
	 * 
	 * @param ultimoPrecioSucio the new ultimo precio sucio
	 */
	public void setUltimoPrecioSucio(double ultimoPrecioSucio) {
		this.ultimoPrecioSucio = ultimoPrecioSucio;
	}

	/**
	 * Gets the fecha hora.
	 * 
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return horGra;
	}

	/**
	 * Sets the fecha hora.
	 * 
	 * @param fechaHora the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.horGra = fechaHora;
	}

	/**
	 * Gets the operaciones.
	 * 
	 * @return the operaciones
	 */
	public List<RentaFijaOperacion> getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 * 
	 * @param operaciones the new operaciones
	 */
	public void setOperaciones(List<RentaFijaOperacion> operaciones) {
		this.operaciones = operaciones;
	}

	/**
	 * Sets the variacion.
	 * 
	 * @param variacion the new variacion
	 */
	public void setVariacion(float variacion) {
		this.variacion = variacion;
	}

	/**
	 * Gets the variacion.
	 * 
	 * @return the variacion
	 */
	public float getVariacion() {
		return variacion;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(RentaFijaResumenEspecie o1, RentaFijaResumenEspecie o2) {
		
		if ( o1.getVolumen() < o2.getVolumen())
			return 1;
		
		if  ( o1.getVolumen() > o2.getVolumen())
			return -1;

		return 0;
	}
	
	
	

}
