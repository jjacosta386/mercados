package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class IntradiaTitulo.
 */
public class IntradiaTituloMGC implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 80467697000003L;

	/** The hora. */
	private String hora;
	
	/** The precio. */
	private Double precio;
	
	/** The volumen. */
	private Double volumen;
	
	/** The precio anterior. */
	private Double precioAnterior;
	
	/**
	 * Instantiates a new intradia titulo.
	 */
	public IntradiaTituloMGC()
	{
		super();
	}

	/**
	 * Gets the volumen.
	 * 
	 * @return the volumen
	 */
	public Double getVolumen()
	{
		return volumen;
	}
	
	/**
	 * Sets the volumen.
	 * 
	 * @param volumen the new volumen
	 */
	public void setVolumen(Double volumen)
	{
		this.volumen = volumen;
	}
	
	/**
	 * Gets the hora.
	 * 
	 * @return the hora
	 */
	public String getHora()
	{
		return hora;
	}
	
	/**
	 * Sets the hora.
	 * 
	 * @param hora the new hora
	 */
	public void setHora(String hora)
	{
		this.hora = hora;
	}
	
	/**
	 * Gets the precio.
	 * 
	 * @return the precio
	 */
	public Double getPrecio()
	{
		return precio ;
	}
	
	/**
	 * Sets the precio.
	 * 
	 * @param precio the new precio
	 */
	public void setPrecio(Double precio)
	{
		this.precio = precio== null? 0 : precio;
	}
	
	/**
	 * Gets the precio anterior.
	 * 
	 * @return the precio anterior
	 */
	public Double getPrecioAnterior()
	{
		return precioAnterior;
	}
	
	/**
	 * Sets the precio anterior.
	 * 
	 * @param precioAnterior the new precio anterior
	 */
	public void setPrecioAnterior(Double precioAnterior)
	{
		this.precioAnterior = precioAnterior;
	}
}
