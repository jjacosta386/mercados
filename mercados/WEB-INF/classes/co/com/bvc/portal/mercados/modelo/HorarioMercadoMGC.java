package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * The Class HorarioMercado.
 */
@SuppressWarnings("unchecked")
public class HorarioMercadoMGC implements IEntidadPortal, Comparable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1619199604692236646L;

	/** The id hor. */
	private int idHor = 0;

	/** The nombre mercado. */
	private String nombreMercado = "";

	/** The hora inicio. */
	private String horaInicio = "";

	/** The hora fin. */
	private String horaFin = "";
	
	/** The abierto. */
	private boolean abierto;

	/**
	 * Checks if is abierto.
	 * 
	 * @return true, if is abierto
	 */
	public boolean isAbierto() {
		this.abierto = (Calendar.getInstance().before(this.horaFin) && Calendar
				.getInstance().after(this.horaInicio));
		return abierto;
	}

	/**
	 * Gets the nombre mercado.
	 * 
	 * @return the nombre mercado
	 */
	public String getNombreMercado() {
		return nombreMercado;
	}

	/**
	 * Sets the nombre mercado.
	 * 
	 * @param nombreMercado the new nombre mercado
	 */
	public void setNombreMercado(String nombreMercado) {
		this.nombreMercado = nombreMercado;
	}

	/**
	 * Gets the hora inicio.
	 * 
	 * @return the hora inicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * Sets the hora inicio.
	 * 
	 * @param horaInicio the new hora inicio
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * Gets the hora fin.
	 * 
	 * @return the hora fin
	 */
	public String getHoraFin() {
		return horaFin;
	}

	/**
	 * Sets the hora fin.
	 * 
	 * @param horaFin the new hora fin
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * Gets the id hor.
	 * 
	 * @return the id hor
	 */
	public Integer getIdHor() {
		return this.idHor;
	}

	/**
	 * Sets the id hor.
	 * 
	 * @param idHor the new id hor
	 */
	public void setIdHor(Integer idHor) {
		this.idHor = (Integer) idHor;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.idHor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}
		if (obj instanceof Integer) {
			return ((Integer) obj).equals(this.idHor);
		}

		if (!(obj instanceof HorarioMercadoMGC)) {
			return false;
		}

		return ((HorarioMercadoMGC) obj).getIdHor().equals(this.idHor);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}
		
		if (obj instanceof Integer) {
			return ((Integer) obj) - (this.idHor);
		}

		if (!(obj instanceof HorarioMercadoMGC)) {
			return -1;
		}

		return ((Integer)((HorarioMercadoMGC) obj).getIdHor())-(this.idHor);
	
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#getId()
	 */
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#setId(java.io.Serializable)
	 */
	public void setId(Serializable id) {
		// TODO Auto-generated method stub
		
	}

}