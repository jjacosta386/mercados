package co.com.bvc.portal.mercados.servicio;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IXMLGenerator.
 */
public interface IXMLGenerator {

	/*
	 * nombre de los mercados tal como saldrá en el ticker en caso de no tener titulos para mostrar.
	 */
	/** The Constant MERCADO_ACCIONES. */
	public static final String MERCADO_ACCIONES = "Acciones";
	
	/** The Constant MERCADO_DERIVADOS. */
	public static final String MERCADO_DERIVADOS = "Derivados";
	
	/** The Constant MERCADO_RENTA_FIJA. */
	public static final String MERCADO_RENTA_FIJA = "Renta Fija";
	
	/**
	 * Consulta los titulos de acciones que deberían ir en el ticker, con ellos
	 * construye el xml que usará el objeto flash.
	 * 
	 * @return el xml con la información necesaria para mostrar el ticker de
	 * acciones
	 */
	public StringBuilder getXmlAcciones();

	/**
	 * Consulta los titulos de derivados que deberían ir en el ticker, con ellos
	 * construye el xml que usará el objeto flash.
	 * 
	 * @return el xml con la información necesaria para mostrar el ticker de
	 * derivados
	 */
	public StringBuilder getXmlDerivados();

	/**
	 * Consulta los titulos de renta fija que deberían ir en el ticker, con
	 * ellos construye el xml que usará el objeto flash.
	 * 
	 * @return el xml con la información necesaria para mostrar el ticker de
	 * Renta Fija
	 */
	public StringBuilder getXmlRentaFija();

	/**
	 * método que genera los tres xml (acciones, renta fija y derivados) lo
	 * llamara un cron de quarz cada minuto.
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public void construirXML() throws PersistenciaException;
}