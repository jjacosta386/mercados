package co.com.bvc.portal.mercados.persistencia;

import java.util.List;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.mercados.modelo.OperacionDiaAcciones;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.TituloAccion;

public interface IAccionesMGCReposDao {
	
	/**
	 * trae la lista de repos más transados en el dia actual con 20 minutos de retraso.
	 * 
	 * @return the list< resumen accion>
	 * 
	 * @throws Exception the exception
	 * 
	 * lista de repos
	 */
	public List<ResumenAccionMGC> cargarMasTranzadasDiaUltDia()
			throws Exception;

	/**
	 * Cargar mas tranzadas dia.
	 * 
	 * @param fecha the fecha
	 * 
	 * @return the list< resumen accion>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<ResumenAccionMGC> cargarMasTranzadasDia(String fecha)
			throws PersistenciaException;

	/**
	 * Operaciones ultimo dia.
	 * 
	 * @param nemo the nemo
	 * 
	 * @return the list< operacion dia acciones>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<OperacionDiaAcciones> operacionesUltimoDia(String nemo)
			throws PersistenciaException;

	/**
	 * Operaciones.
	 * 
	 * @param nemo the nemo
	 * @param fecha the fecha
	 * 
	 * @return the list< operacion dia acciones>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<OperacionDiaAcciones> operaciones(String nemo, String fecha)
			throws PersistenciaException;

	/**
	 * Carga datos titulo ultimo dia.
	 * 
	 * @param titulo the titulo
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public void cargaDatosTituloUltimoDia(TituloAccion titulo)
			throws PersistenciaException;

	/**
	 * Carga datos titulo fecha.
	 * 
	 * @param titulo the titulo
	 * @param fecha the fecha
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public void cargaDatosTituloFecha(TituloAccion titulo, String fecha)
			throws PersistenciaException;
	
	/**
	 * Carga datos titulo fecha.
	 * 
	 * @param nemo the nemo
	 * @param fechaInicio the fecha inicio
	 * @param fechaFin the fecha fin
	 * 
	 * @return the list< titulo accion>
	 * 
	 * @throws PersistenciaException the persistencia exception
	 */
	public List<TituloAccion> cargaDatosTituloFecha(String nemo,
			String fechaInicio, String fechaFin) throws PersistenciaException;


}
