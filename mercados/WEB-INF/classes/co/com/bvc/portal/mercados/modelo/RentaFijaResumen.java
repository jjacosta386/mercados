package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.util.Collection;

import co.com.bvc.portal.mercados.servicio.imp.RentaFija;

// TODO: Auto-generated Javadoc
/**
 * The Class RentaFijaResumen.
 */
public class RentaFijaResumen implements Serializable {
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8046769700004L;

	/** The volumen registrox ope. */
	private double  volumenRegistroxOPE[];
	
	/** The porcentajes registrox ope. */
	private float   porcentajesRegistroxOPE[];
	
	/** The volumen transaccionalx ope. */
	private double  volumenTransaccionalxOPE[];
	
	/** The porcentajes transaccionalx ope. */
	private float   porcentajesTransaccionalxOPE[];
	
	/** The total volumen registro. */
	private Double totalVolumenRegistro;
	
	/** The total volumen transaccional. */
	private Double totalVolumenTransaccional;
	
	/** The titulos deuda publica. */
	private Collection<RentaFijaResumenTipoTitulo> titulosDeudaPublica;
	
	/** The titulos deuda privada. */
	private Collection<RentaFijaResumenTipoTitulo> titulosDeudaPrivada;
	
	/** The listado titulos deuda publica. */
	private Collection<RentaFijaResumenEspecie> listadoTitulosDeudaPublica;
	
	/** The listado titulos deuda privada. */
	private Collection<RentaFijaResumenEspecie> listadoTitulosDeudaPrivada;
	
	/** The especies5 publica. */
	private Collection<RentaFijaResumenEspecie> especies5Publica;
	
	/** The especies5 privada. */
	private Collection<RentaFijaResumenEspecie> especies5Privada;
	
	
	
	/**
	 * Instantiates a new renta fija resumen.
	 */
	public RentaFijaResumen(){
		volumenRegistroxOPE = new double[ RentaFija.operaciones.length];		
		volumenTransaccionalxOPE = new double[ RentaFija.operaciones.length];
		porcentajesRegistroxOPE = new float[ RentaFija.operaciones.length];
		porcentajesTransaccionalxOPE =  new float[ RentaFija.operaciones.length];
		
		for (int i=0; i < RentaFija.operaciones.length; i++) {
			volumenRegistroxOPE[i] = 0;
			volumenTransaccionalxOPE[i] = 0;
			porcentajesRegistroxOPE[i] = 0f;
			porcentajesTransaccionalxOPE[i] = 0f;
		}
	
		
	}
	
	/**
	 * Gets the volumen registrox ope.
	 * 
	 * @return the volumen registrox ope
	 */
	public double[] getVolumenRegistroxOPE() {
		return volumenRegistroxOPE;
	}
	
	/**
	 * Sets the volumen registrox ope.
	 * 
	 * @param volumenRegistroxOPE the new volumen registrox ope
	 */
	public void setVolumenRegistroxOPE(double[] volumenRegistroxOPE) {
		this.volumenRegistroxOPE = volumenRegistroxOPE;
	}
	
	/**
	 * Gets the volumen transaccionalx ope.
	 * 
	 * @return the volumen transaccionalx ope
	 */
	public double[] getVolumenTransaccionalxOPE() {
		return volumenTransaccionalxOPE;
	}
	
	/**
	 * Sets the volumen transaccionalx ope.
	 * 
	 * @param volumenTransaccionalxOPE the new volumen transaccionalx ope
	 */
	public void setVolumenTransaccionalxOPE(double[] volumenTransaccionalxOPE) {
		this.volumenTransaccionalxOPE = volumenTransaccionalxOPE;
	}
	
	/**
	 * Gets the titulos deuda publica.
	 * 
	 * @return the titulos deuda publica
	 */
	public Collection<RentaFijaResumenTipoTitulo> getTitulosDeudaPublica() {
		return titulosDeudaPublica;
	}
	
	/**
	 * Sets the titulos deuda publica.
	 * 
	 * @param titulosDeudaPublica the new titulos deuda publica
	 */
	public void setTitulosDeudaPublica(
			Collection<RentaFijaResumenTipoTitulo> titulosDeudaPublica) {
		this.titulosDeudaPublica = titulosDeudaPublica;
	}
	
	/**
	 * Gets the titulos deuda privada.
	 * 
	 * @return the titulos deuda privada
	 */
	public Collection<RentaFijaResumenTipoTitulo> getTitulosDeudaPrivada() {
		return titulosDeudaPrivada;
	}
	
	/**
	 * Sets the titulos deuda privada.
	 * 
	 * @param titulosDeudaPrivada the new titulos deuda privada
	 */
	public void setTitulosDeudaPrivada(
			Collection<RentaFijaResumenTipoTitulo> titulosDeudaPrivada) {
		this.titulosDeudaPrivada = titulosDeudaPrivada;
	}

	/**
	 * Sets the porcentajes registrox ope.
	 * 
	 * @param porcentajesRegistroxOPE the new porcentajes registrox ope
	 */
	public void setPorcentajesRegistroxOPE(float porcentajesRegistroxOPE[]) {
		this.porcentajesRegistroxOPE = porcentajesRegistroxOPE;
	}

	/**
	 * Gets the porcentajes registrox ope.
	 * 
	 * @return the porcentajes registrox ope
	 */
	public float[] getPorcentajesRegistroxOPE() {
		return porcentajesRegistroxOPE;
	}

	/**
	 * Sets the porcentajes transaccionalx ope.
	 * 
	 * @param porcentajesTransaccionalxOPE the new porcentajes transaccionalx ope
	 */
	public void setPorcentajesTransaccionalxOPE(
			float porcentajesTransaccionalxOPE[]) {
		this.porcentajesTransaccionalxOPE = porcentajesTransaccionalxOPE;
	}

	/**
	 * Gets the porcentajes transaccionalx ope.
	 * 
	 * @return the porcentajes transaccionalx ope
	 */
	public float[] getPorcentajesTransaccionalxOPE() {
		return porcentajesTransaccionalxOPE;
	}

	/**
	 * Sets the total volumen registro.
	 * 
	 * @param totalVolumenRegistro the new total volumen registro
	 */
	public void setTotalVolumenRegistro(double totalVolumenRegistro) {
		this.totalVolumenRegistro = totalVolumenRegistro;
	}

	/**
	 * Gets the total volumen registro.
	 * 
	 * @return the total volumen registro
	 */
	public Double getTotalVolumenRegistro() {
		return totalVolumenRegistro;
	}

	/**
	 * Sets the total volumen transaccional.
	 * 
	 * @param totalVolumenTransaccional the new total volumen transaccional
	 */
	public void setTotalVolumenTransaccional(double totalVolumenTransaccional) {
		this.totalVolumenTransaccional = totalVolumenTransaccional;
	}

	/**
	 * Gets the total volumen transaccional.
	 * 
	 * @return the total volumen transaccional
	 */
	public Double getTotalVolumenTransaccional() {
		return totalVolumenTransaccional;
	}

	/**
	 * Sets the especies5 publica.
	 * 
	 * @param especies5Publica the new especies5 publica
	 */
	public void setEspecies5Publica(Collection<RentaFijaResumenEspecie> especies5Publica) {
		this.especies5Publica = especies5Publica;
	}

	/**
	 * Gets the especies5 publica.
	 * 
	 * @return the especies5 publica
	 */
	public Collection<RentaFijaResumenEspecie> getEspecies5Publica() {
		return especies5Publica;
	}

	/**
	 * Sets the especies5 privada.
	 * 
	 * @param especies5Privada the new especies5 privada
	 */
	public void setEspecies5Privada(Collection<RentaFijaResumenEspecie> especies5Privada) {
		this.especies5Privada = especies5Privada;
	}

	/**
	 * Gets the especies5 privada.
	 * 
	 * @return the especies5 privada
	 */
	public Collection<RentaFijaResumenEspecie> getEspecies5Privada() {
		return especies5Privada;
	}

	/**
	 * Gets the listado titulos deuda publica.
	 * 
	 * @return the listado titulos deuda publica
	 */
	public Collection<RentaFijaResumenEspecie> getListadoTitulosDeudaPublica() {
		return listadoTitulosDeudaPublica;
	}

	/**
	 * Sets the listado titulos deuda publica.
	 * 
	 * @param listadoTitulosDeudaPublica the new listado titulos deuda publica
	 */
	public void setListadoTitulosDeudaPublica(
			Collection<RentaFijaResumenEspecie> listadoTitulosDeudaPublica) {
		this.listadoTitulosDeudaPublica = listadoTitulosDeudaPublica;
	}

	/**
	 * Gets the listado titulos deuda privada.
	 * 
	 * @return the listado titulos deuda privada
	 */
	public Collection<RentaFijaResumenEspecie> getListadoTitulosDeudaPrivada() {
		return listadoTitulosDeudaPrivada;
	}

	/**
	 * Sets the listado titulos deuda privada.
	 * 
	 * @param listadoTitulosDeudaPrivada the new listado titulos deuda privada
	 */
	public void setListadoTitulosDeudaPrivada(
			Collection<RentaFijaResumenEspecie> listadoTitulosDeudaPrivada) {
		this.listadoTitulosDeudaPrivada = listadoTitulosDeudaPrivada;
	}


	
}
