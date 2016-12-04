package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

// TODO: Auto-generated Javadoc
/**
 * The Class VolumenAccion.
 */
public class VolumenAccionMGC implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6186288512993798902L;

	/** The descripcion volumen. */
	private String descripcionVolumen = "";
	
	/** The volumen. */
	private double volumen = 0;

	/**
	 * Gets the descripcion volumen.
	 * 
	 * @return the descripcion volumen
	 */
	public String getDescripcionVolumen() {
		return descripcionVolumen;
	}

	/**
	 * Sets the descripcion volumen.
	 * 
	 * @param descripcionVolumen the new descripcion volumen
	 */
	public void setDescripcionVolumen(String descripcionVolumen) {
		this.descripcionVolumen = descripcionVolumen;
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
	 * Gets the porcentaje participacion.
	 * 
	 * @return the porcentaje participacion
	 */
	public double getPorcentajeParticipacion() {
		return porcentajeParticipacion;
	}

	/**
	 * Sets the porcentaje participacion.
	 * 
	 * @param porcentajeParticipacion the new porcentaje participacion
	 */
	public void setPorcentajeParticipacion(double porcentajeParticipacion) {
		this.porcentajeParticipacion = porcentajeParticipacion;
	}
	
	/**
	 * Gets the volumen formato.
	 * 
	 * @return the volumen formato
	 */
	public String getVolumenFormato(){
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("es","CO"));
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		return nf.format(this.volumen);
	}
	
	/** The porcentaje participacion. */
	private double porcentajeParticipacion = 0;

}
