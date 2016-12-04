package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;

import co.com.bvc.portal.comun.modelo.IEntidadPortal;

// TODO: Auto-generated Javadoc
/**
 * The Class InfoIndicesSectoriales.
 */
@SuppressWarnings("serial")
public class InfoIndicesSectoriales implements IEntidadPortal {

	/** The indice. */
	private String indice = "";
	
	/** The nombre indice. */
	private String nombreIndice = "";
	
	/** The contenido generalidades. */
	private String contenidoGeneralidades = "";
	
	/** The contenido formulas. */
	private String contenidoFormulas = "";
	
	/** The contenido seleccion canasta. */
	private String contenidoSeleccionCanasta = "";
	
	/** The contenido calculo ponderacion. */
	private String contenidoCalculoPonderacion = "";
	
	/** The path canasta. */
	private String pathCanasta = "";
	
	private String pathCanastaHist = "";

	/**
	 * Gets the indice.
	 * 
	 * @return the indice
	 */
	public String getIndice() {
		return indice;
	}

	/**
	 * Sets the indice.
	 * 
	 * @param indice the new indice
	 */
	public void setIndice(String indice) {
		this.indice = indice;
	}

	/**
	 * Gets the nombre indice.
	 * 
	 * @return the nombre indice
	 */
	public String getNombreIndice() {
		return nombreIndice;
	}

	/**
	 * Sets the nombre indice.
	 * 
	 * @param indice the new nombre indice
	 */
	public void setNombreIndice(String indice) {
		this.nombreIndice = indice;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#getId()
	 */
	public Serializable getId() {
		return this.indice;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.comun.modelo.IEntidadPortal#setId(java.io.Serializable)
	 */
	public void setId(Serializable indice) {
		this.indice = (String) indice;
	}

	/**
	 * Gets the contenido generalidades.
	 * 
	 * @return the contenido generalidades
	 */
	public String getContenidoGeneralidades() {
		return eliminarBlancos(contenidoGeneralidades);
	}

	/**
	 * Sets the contenido generalidades.
	 * 
	 * @param contenidoGeneralidades the new contenido generalidades
	 */
	public void setContenidoGeneralidades(String contenidoGeneralidades) {
		this.contenidoGeneralidades = contenidoGeneralidades;
	}

	/**
	 * Gets the contenido formulas.
	 * 
	 * @return the contenido formulas
	 */
	public String getContenidoFormulas() {
		return eliminarBlancos(contenidoFormulas);
	}

	/**
	 * Sets the contenido formulas.
	 * 
	 * @param contenidoFormulas the new contenido formulas
	 */
	public void setContenidoFormulas(String contenidoFormulas) {
		this.contenidoFormulas = contenidoFormulas;
	}

	/**
	 * Gets the contenido seleccion canasta.
	 * 
	 * @return the contenido seleccion canasta
	 */
	public String getContenidoSeleccionCanasta() {
		return eliminarBlancos(contenidoSeleccionCanasta);
	}

	/**
	 * Sets the contenido seleccion canasta.
	 * 
	 * @param contenidoSeleccionCanasta the new contenido seleccion canasta
	 */
	public void setContenidoSeleccionCanasta(String contenidoSeleccionCanasta) {
		this.contenidoSeleccionCanasta = contenidoSeleccionCanasta;
	}

	/**
	 * Gets the contenido calculo ponderacion.
	 * 
	 * @return the contenido calculo ponderacion
	 */
	public String getContenidoCalculoPonderacion() {
		return eliminarBlancos(contenidoCalculoPonderacion);
	}

	/**
	 * Sets the contenido calculo ponderacion.
	 * 
	 * @param contenidoCalculoPonderacion the new contenido calculo ponderacion
	 */
	public void setContenidoCalculoPonderacion(String contenidoCalculoPonderacion) {
		this.contenidoCalculoPonderacion = contenidoCalculoPonderacion;
	}

	/**
	 * Gets the path canasta.
	 * 
	 * @return the path canasta
	 */
	public String getPathCanasta() {
		return pathCanasta;
	}

	/**
	 * Sets the path canasta.
	 * 
	 * @param pathCanasta the new path canasta
	 */
	public void setPathCanasta(String pathCanasta) {
		this.pathCanasta = pathCanasta;
	}

	/**
	 * Eliminar blancos.
	 * 
	 * @param l the l
	 * 
	 * @return the string
	 */
	private String eliminarBlancos(String l){
		if (l != null){
			l = l.replaceAll("[\t]", "");
			l = l.replaceAll("[\n]", "");
		}
		return l;
	}

	public String getPathCanastaHist() {
		return pathCanastaHist;
	}

	public void setPathCanastaHist(String pathCanastaHist) {
		this.pathCanastaHist = pathCanastaHist;
	}
}
