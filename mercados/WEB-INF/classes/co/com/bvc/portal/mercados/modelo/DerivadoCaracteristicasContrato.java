/**
 * 
 */
package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.util.Date;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * Clase que define los datos que se muestran en la interfaz sobre las
 * caracteristicas del contrato de derivados.
 * 
 * @author <a href="mailto:william.bernal@gmail.com">William A. Bernal</a>
 */
public class DerivadoCaracteristicasContrato implements IEntidadPortal {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4363321771195994499L;

	/** The contrato. */
	private String contrato;

	/** The estado. */
	private String estado;

	/** The canasta entregables. */
	private String canastaEntregables;

	/** The strike. */
	private Float strike;

	/** The ciclo. */
	private Integer ciclo;

	/** The tipo opcion. */
	private String tipoOpcion;
	
	/** The tipo. */
	private String tipo;

	/** The maximo transaccional. */
	private Float maximoTransaccional;

	/** The minimo registro. */
	private Float minimoRegistro;

	/** The fecha vcto. */
	private Integer fechaVcto;

	/** The fecha vencimiento. */
	private Date fechaVencimiento;

	/** The valor nominal. */
	private Float valorNominal;

	/** The factor conversion. */
	private Float factorConversion;

	/** The movimiento minimo. */
	private Float movimientoMinimo;

	/** The fecha insc. */
	private Integer fechaInsc;

	/** The fecha inscripcion. */
	private Date fechaInscripcion;

	/** The tipo liquidacion. */
	private String tipoLiquidacion;
	
	/**
	 * Gets the contrato.
	 * 
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}

	/**
	 * Sets the contrato.
	 * 
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	/**
	 * Gets the estado.
	 * 
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 * 
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the canasta entregables.
	 * 
	 * @return the canastaEntregables
	 */
	public String getCanastaEntregables() {
		return canastaEntregables;
	}

	/**
	 * Sets the canasta entregables.
	 * 
	 * @param canastaEntregables the canastaEntregables to set
	 */
	public void setCanastaEntregables(String canastaEntregables) {
		this.canastaEntregables = canastaEntregables;
	}

	/**
	 * Gets the strike.
	 * 
	 * @return the strike
	 */
	public Float getStrike() {
		return strike;
	}

	/**
	 * Sets the strike.
	 * 
	 * @param strike the strike to set
	 */
	public void setStrike(Float strike) {
		this.strike = strike;
	}

	/**
	 * Gets the ciclo.
	 * 
	 * @return the ciclo
	 */
	public Integer getCiclo() {
		return ciclo;
	}

	/**
	 * Sets the ciclo.
	 * 
	 * @param ciclo the ciclo to set
	 */
	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	/**
	 * Gets the tipo opcion.
	 * 
	 * @return the tipoOpcion
	 */
	public String getTipoOpcion() {
		return tipoOpcion;
	}

	/**
	 * Sets the tipo opcion.
	 * 
	 * @param tipoOpcion the tipoOpcion to set
	 */
	public void setTipoOpcion(String tipoOpcion) {
		this.tipoOpcion = tipoOpcion;
	}
	
	/**
	 * Gets the tipo.
	 * 
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 * 
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the maximo transaccional.
	 * 
	 * @return the maximoTransaccional
	 */
	public Float getMaximoTransaccional() {
		return maximoTransaccional;
	}

	/**
	 * Sets the maximo transaccional.
	 * 
	 * @param maximoTransaccional the maximoTransaccional to set
	 */
	public void setMaximoTransaccional(Float maximoTransaccional) {
		this.maximoTransaccional = maximoTransaccional;
	}

	/**
	 * Gets the minimo registro.
	 * 
	 * @return the minimoRegistro
	 */
	public Float getMinimoRegistro() {
		return minimoRegistro;
	}

	/**
	 * Sets the minimo registro.
	 * 
	 * @param minimoRegistro the minimoRegistro to set
	 */
	public void setMinimoRegistro(Float minimoRegistro) {
		this.minimoRegistro = minimoRegistro;
	}

	/**
	 * Gets the fecha vcto.
	 * 
	 * @return the fechaVcto
	 */
	public Integer getFechaVcto() {
		return fechaVcto;
	}

	/**
	 * Sets the fecha vcto.
	 * 
	 * @param fechaVcto the fechaVcto to set
	 */
	public void setFechaVcto(Integer fechaVcto) {
		this.fechaVcto = fechaVcto;
	}

	/**
	 * Gets the fecha vencimiento.
	 * 
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 * 
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the valor nominal.
	 * 
	 * @return the valorNominal
	 */
	public Float getValorNominal() {
		return valorNominal;
	}

	/**
	 * Sets the valor nominal.
	 * 
	 * @param valorNominal the valorNominal to set
	 */
	public void setValorNominal(Float valorNominal) {
		this.valorNominal = valorNominal;
	}

	/**
	 * Gets the factor conversion.
	 * 
	 * @return the factorConversion
	 */
	public Float getFactorConversion() {
		return factorConversion;
	}

	/**
	 * Sets the factor conversion.
	 * 
	 * @param factorConversion the factorConversion to set
	 */
	public void setFactorConversion(Float factorConversion) {
		this.factorConversion = factorConversion;
	}

	/**
	 * Gets the movimiento minimo.
	 * 
	 * @return the movimientoMinimo
	 */
	public Float getMovimientoMinimo() {
		return movimientoMinimo;
	}

	/**
	 * Sets the movimiento minimo.
	 * 
	 * @param movimientoMinimo the movimientoMinimo to set
	 */
	public void setMovimientoMinimo(Float movimientoMinimo) {
		this.movimientoMinimo = movimientoMinimo;
	}

	/**
	 * Gets the fecha insc.
	 * 
	 * @return the fechaInsc
	 */
	public Integer getFechaInsc() {
		return fechaInsc;
	}

	/**
	 * Sets the fecha insc.
	 * 
	 * @param fechaInsc the fechaInsc to set
	 */
	public void setFechaInsc(Integer fechaInsc) {
		this.fechaInsc = fechaInsc;
	}

	/**
	 * Gets the fecha inscripcion.
	 * 
	 * @return the fechaInscripcion
	 */
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	/**
	 * Sets the fecha inscripcion.
	 * 
	 * @param fechaInscripcion the fechaInscripcion to set
	 */
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	/**
	 * Gets the tipo liquidacion.
	 * 
	 * @return the tipoLiquidacion
	 */
	public String getTipoLiquidacion() {
		return tipoLiquidacion;
	}

	/**
	 * Sets the tipo liquidacion.
	 * 
	 * @param tipoLiquidacion the tipoLiquidacion to set
	 */
	public void setTipoLiquidacion(String tipoLiquidacion) {
		this.tipoLiquidacion = tipoLiquidacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#getId()
	 */
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.comun.modelo.IEntidadPortal#setId(java.io.Serializable)
	 */
	public void setId(Serializable arg0) {
		// TODO Auto-generated method stub

	}

}