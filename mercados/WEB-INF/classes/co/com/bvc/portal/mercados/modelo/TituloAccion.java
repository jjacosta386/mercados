package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.util.Calendar;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * The Class TituloAccion.
 */
public class TituloAccion implements IEntidadPortal {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2892530700383803093L;

	/** The nemotecnico. */
	private String nemotecnico = "";

	/** The codigo super. */
	private String codigoSuper = "";

	/** The dividendos. */
	private String dividendos = "";

	/** The acciones circulacion. */
	private double accionesCirculacion = 0;

	/** The valor nominal. */
	private double valorNominal = 0;

	/** The precio base. */
	private double precioBase = 0;

	/** The precio minimo. */
	private double precioMinimo = 0;

	/** The precio maximo. */
	private double precioMaximo = 0;

	/** The bursatilidad. */
	private String bursatilidad = "";

	/** The precio mayor. */
	private double precioMayor = 0;

	/** The precio medio. */
	private double precioMedio = 0;

	/** The precio menor. */
	private double precioMenor = 0;

	/** The precio cierre. */
	private double precioCierre = 0;

	/** The precio cierre anterior. */
	private double precioCierreAnterior = 0;

	/** The variacion. */
	private double variacion = 0;

	/** The volumen. */
	private double volumen = 0;

	/** The hora. */
	private Calendar hora = Calendar.getInstance();

	/** The estado. */
	private String estado = "";

	/** The cantidad. */
	private double cantidad = 0;

	/** The emisor. */
	private EmisorTitulo emisor = new EmisorTitulo();

	/** The isin. */
	private String isin = "";

	/** The var absoluta. */
	private Double varAbsoluta = null;
	
	/** The idEmisor. */
	private String idEmisor = "";
	
	/** The nombreEmr. */
	private String nombreEmr = "";
	
	/** The paisOrigen. */
	private String paisOrigen = "";

	/** The patrocinador. */
	private String patrocinador = "";
	
	/** The cotizacionPrincipal. */
	private String cotizacionPrincipal = "";
	
	/** The otrasBolsas. */
	private String otrasBolsas = "";
	
	/** The cusip. */
	private String cusip = "";
	
	/**
	 * Gets the var absoluta.
	 * 
	 * @return the var absoluta
	 */
	public double getVarAbsoluta() {
		if (varAbsoluta == null) {
			return this.precioCierre - this.precioCierreAnterior;
		}
		return varAbsoluta;
	}

	/**
	 * Sets the var absoluta.
	 * 
	 * @param varAbsoluta the new var absoluta
	 */
	public void setVarAbsoluta(Double varAbsoluta) {
		this.varAbsoluta = varAbsoluta;
	}

	/** The qtobin. */
	private double qtobin = 0;

	/** The rpg. */
	private double rpg = 0;

	/**
	 * Gets the qtobin.
	 * 
	 * @return the qtobin
	 */
	public double getQtobin() {
		return qtobin;
	}

	/**
	 * Sets the qtobin.
	 * 
	 * @param qtobin the new qtobin
	 */
	public void setQtobin(double qtobin) {
		this.qtobin = qtobin;
	}

	/**
	 * Gets the rpg.
	 * 
	 * @return the rpg
	 */
	public double getRpg() {
		return rpg;
	}

	/**
	 * Sets the rpg.
	 * 
	 * @param rpg the new rpg
	 */
	public void setRpg(double rpg) {
		this.rpg = rpg;
	}

	/**
	 * Gets the isin.
	 * 
	 * @return the isin
	 */
	public String getIsin() {
		return isin;
	}

	/**
	 * Sets the isin.
	 * 
	 * @param isin the new isin
	 */
	public void setIsin(String isin) {
		this.isin = isin;
	}

	/**
	 * Gets the emisor.
	 * 
	 * @return the emisor
	 */
	public EmisorTitulo getEmisor() {
		return emisor;
	}

	/**
	 * Sets the emisor.
	 * 
	 * @param emisor the new emisor
	 */
	public void setEmisor(EmisorTitulo emisor) {
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
	 * Gets the codigo super.
	 * 
	 * @return the codigo super
	 */
	public String getCodigoSuper() {
		return codigoSuper;
	}

	/**
	 * Sets the codigo super.
	 * 
	 * @param codigoSuper the new codigo super
	 */
	public void setCodigoSuper(String codigoSuper) {
		this.codigoSuper = codigoSuper;
	}

	/**
	 * Gets the dividendos.
	 * 
	 * @return the dividendos
	 */
	public String getDividendos() {
		return dividendos;
	}

	/**
	 * Sets the dividendos.
	 * 
	 * @param dividendos the new dividendos
	 */
	public void setDividendos(String dividendos) {
		this.dividendos = dividendos;
	}

	/**
	 * Gets the acciones circulacion.
	 * 
	 * @return the acciones circulacion
	 */
	public double getAccionesCirculacion() {
		return accionesCirculacion;
	}

	/**
	 * Sets the acciones circulacion.
	 * 
	 * @param accionesCirculacion the new acciones circulacion
	 */
	public void setAccionesCirculacion(double accionesCirculacion) {
		this.accionesCirculacion = accionesCirculacion;
	}

	/**
	 * Gets the valor nominal.
	 * 
	 * @return the valor nominal
	 */
	public double getValorNominal() {
		return valorNominal;
	}

	/**
	 * Sets the valor nominal.
	 * 
	 * @param valorNominal the new valor nominal
	 */
	public void setValorNominal(double valorNominal) {
		this.valorNominal = valorNominal;
	}

	/**
	 * Gets the precio base.
	 * 
	 * @return the precio base
	 */
	public double getPrecioBase() {
		return precioBase;
	}

	/**
	 * Sets the precio base.
	 * 
	 * @param precioBase the new precio base
	 */
	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	/**
	 * Gets the precio minimo.
	 * 
	 * @return the precio minimo
	 */
	public double getPrecioMinimo() {
		return precioMinimo;
	}

	/**
	 * Sets the precio minimo.
	 * 
	 * @param precioMinimo the new precio minimo
	 */
	public void setPrecioMinimo(double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	/**
	 * Gets the precio maximo.
	 * 
	 * @return the precio maximo
	 */
	public double getPrecioMaximo() {
		return precioMaximo;
	}

	/**
	 * Sets the precio maximo.
	 * 
	 * @param precioMaximo the new precio maximo
	 */
	public void setPrecioMaximo(double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	/**
	 * Gets the bursatilidad.
	 * 
	 * @return the bursatilidad
	 */
	public String getBursatilidad() {
		return bursatilidad;
	}

	/**
	 * Sets the bursatilidad.
	 * 
	 * @param bursatilidad the new bursatilidad
	 */
	public void setBursatilidad(String bursatilidad) {
		this.bursatilidad = bursatilidad;
	}

	/**
	 * Gets the precio mayor.
	 * 
	 * @return the precio mayor
	 */
	public double getPrecioMayor() {
		return precioMayor;
	}

	/**
	 * Sets the precio mayor.
	 * 
	 * @param precioMayor the new precio mayor
	 */
	public void setPrecioMayor(double precioMayor) {
		this.precioMayor = precioMayor;
	}

	/**
	 * Gets the precio medio.
	 * 
	 * @return the precio medio
	 */
	public double getPrecioMedio() {
		return precioMedio;
	}

	/**
	 * Sets the precio medio.
	 * 
	 * @param precioMedio the new precio medio
	 */
	public void setPrecioMedio(double precioMedio) {
		this.precioMedio = precioMedio;
	}

	/**
	 * Gets the precio menor.
	 * 
	 * @return the precio menor
	 */
	public double getPrecioMenor() {
		return precioMenor;
	}

	/**
	 * Sets the precio menor.
	 * 
	 * @param precioMenor the new precio menor
	 */
	public void setPrecioMenor(double precioMenor) {
		this.precioMenor = precioMenor;
	}

	/**
	 * Gets the precio cierre.
	 * 
	 * @return the precio cierre
	 */
	public double getPrecioCierre() {
		return precioCierre;
	}

	/**
	 * Sets the precio cierre.
	 * 
	 * @param precioCierre the new precio cierre
	 */
	public void setPrecioCierre(double precioCierre) {
		this.precioCierre = precioCierre;
	}

	/**
	 * Gets the precio cierre anterior.
	 * 
	 * @return the precio cierre anterior
	 */
	public double getPrecioCierreAnterior() {
		return precioCierreAnterior;
	}

	/**
	 * Sets the precio cierre anterior.
	 * 
	 * @param precioCierreAnterior the new precio cierre anterior
	 */
	public void setPrecioCierreAnterior(double precioCierreAnterior) {
		this.precioCierreAnterior = precioCierreAnterior;
	}

	/**
	 * Gets the variacion.
	 * 
	 * @return the variacion
	 */
	public double getVariacion() {
		return variacion;
	}

	/**
	 * Sets the variacion.
	 * 
	 * @param variacion the new variacion
	 */
	public void setVariacion(double variacion) {
		this.variacion = variacion;
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
	 * Gets the hora.
	 * 
	 * @return the hora
	 */
	public Calendar getHora() {
		return hora;
	}

	/**
	 * Sets the hora.
	 * 
	 * @param hora the new hora
	 */
	public void setHora(Calendar hora) {
		this.hora = hora;
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
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#getId()
	 */
	public Serializable getId() {
		return this.nemotecnico;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#setId(java.io.Serializable)
	 */
	public void setId(Serializable id) {
		this.nemotecnico = (String) id;
	}
	
	public String getIdEmisor() {
		return idEmisor;
	}

	/**
	 * Sets the bursatilidad.
	 * 
	 * @param bursatilidad the new bursatilidad
	 */
	public void setIdEmisor(String idEmisor) {
		this.idEmisor = idEmisor;
	}
	
	public String getNombreEmr() {
		return nombreEmr;
	}

	/**
	 * Sets the bursatilidad.
	 * 
	 * @param bursatilidad the new bursatilidad
	 */
	public void setNombreEmr(String nombreEmr) {
		this.nombreEmr = nombreEmr;
	}

	/**
	 * @return the paisOrigen
	 */
	public String getPaisOrigen() {
		return paisOrigen;
	}

	/**
	 * @param paisOrigen the paisOrigen to set
	 */
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	/**
	 * @return the patrocinador
	 */
	public String getPatrocinador() {
		return patrocinador;
	}

	/**
	 * @param patrocinador the patrocinador to set
	 */
	public void setPatrocinador(String patrocinador) {
		this.patrocinador = patrocinador;
	}

	/**
	 * @return the cotizacionPrincipal
	 */
	public String getCotizacionPrincipal() {
		return cotizacionPrincipal;
	}

	/**
	 * @param cotizacionPrincipal the cotizacionPrincipal to set
	 */
	public void setCotizacionPrincipal(String cotizacionPrincipal) {
		this.cotizacionPrincipal = cotizacionPrincipal;
	}

	/**
	 * @return the otrasBolsas
	 */
	public String getOtrasBolsas() {
		return otrasBolsas;
	}

	/**
	 * @param otrasBolsas the otrasBolsas to set
	 */
	public void setOtrasBolsas(String otrasBolsas) {
		this.otrasBolsas = otrasBolsas;
	}

	/**
	 * @return the cusip
	 */
	public String getCusip() {
		return cusip;
	}

	/**
	 * @param cusip the cusip to set
	 */
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	
}