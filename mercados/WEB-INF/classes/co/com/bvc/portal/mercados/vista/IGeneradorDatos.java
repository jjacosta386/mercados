package co.com.bvc.portal.mercados.vista;

import java.io.UnsupportedEncodingException;
import java.util.List;

import co.com.bvc.portal.mercados.modelo.ICierre;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGeneradorDatos.
 */
public interface IGeneradorDatos {
	
	/**
	 * Generar salida.
	 * 
	 * @param datos the datos
	 * 
	 * @return the byte[]
	 * 
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public byte[] generarSalida(List<ICierre> datos) throws UnsupportedEncodingException;

}