package co.com.bvc.portal.mercados.vista.cache.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.persistencia.IAccionesDao;
import co.com.bvc.portal.mercados.persistencia.IAccionesMGCDao;
import co.com.bvc.portal.mercados.persistencia.IDerivadosDao;
import co.com.bvc.portal.mercados.persistencia.IDivisasDao;
import co.com.bvc.portal.mercados.persistencia.IIndiceDao;
import co.com.bvc.portal.mercados.persistencia.IRentaFijaDao;
import co.com.bvc.portal.mercados.vista.cache.IIntradiaCache;

/**
 * @author BVC
 * @description IntradiaCache.
 * 
 */

public class IntradiaCache implements IIntradiaCache {
	// -------------------------------
	// Attributes
	// -------------------------------

	/** Mutex for the shared resource. */
	@SuppressWarnings("unused")
	private boolean mutex = true;

	/** The clean version of the cache per titulo requested. */
	private Hashtable<String, List<ICierre>> cleanCache;

	/** The dirty version of the cache per titulo requested. */
	private Hashtable<String, List<ICierre>> dirtyCache;

	/** The acciones DAO. */
	private IAccionesDao accionesDao;
	
	/** The acciones DAO. */
	private IAccionesMGCDao accionesMGCDao;

	/** The derivados DAO. */
	private IDerivadosDao derivadosDao;
	
	/** The derivados Drvx DAO. */
	private IDerivadosDao derivadosDrvxDao;

	public IDerivadosDao getDerivadosDrvxDao() {
		return derivadosDrvxDao;
	}

	public void setDerivadosDrvxDao(IDerivadosDao derivadosDrvxDao) {
		this.derivadosDrvxDao = derivadosDrvxDao;
	}


	/** The rentaFijaDao DAO. */
	private IRentaFijaDao rentasFijasDao;

	/** The indicesDao DAO. */
	private IIndiceDao indicesDao;
	
	/** The divisas dao. */
	private IDivisasDao divisasDao;
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	// -------------------------------
	// Constructors
	// -------------------------------

	/**
	 * Constructor.
	 */
	public IntradiaCache() {
		super();
		cleanCache = new Hashtable<String, List<ICierre>>();
		dirtyCache = new Hashtable<String, List<ICierre>>();
	}

	// -------------------------------
	// Methods
	// -------------------------------

	/**
	 * Sets the acciones dao.
	 * 
	 * @param accionesDao The DAO set by IoC
	 */
	public void setAccionesDao(IAccionesDao accionesDao) {
		this.accionesDao = accionesDao;
	}
	
	/**
	 * Gets the acciones dao.
	 * 
	 * @return the acciones dao
	 */
	public IAccionesMGCDao getAccionesMGCDao() {
		return accionesMGCDao;
	}

	/**
	 * Sets the acciones dao.
	 * 
	 * @param accionesDao
	 *            the new acciones dao
	 */
	public void setAccionesMGCDao(IAccionesMGCDao accionesMGCDao) {
		this.accionesMGCDao = accionesMGCDao;
	}
	
	
	/**
	 * Sets the divisas dao.
	 * 
	 * @param divisasDao the new divisas dao
	 */
	public void setDivisasDao(IDivisasDao divisasDao) {
		this.divisasDao = divisasDao;
	}

	/**
	 * Sets the derivados dao.
	 * 
	 * @param derivadosDao The DAO set by IoC
	 */
	public void setDerivadosDao(IDerivadosDao derivadosDao) {
		this.derivadosDao = derivadosDao;
	}

	/**
	 * Sets the indices dao.
	 * 
	 * @param indicesDao the indices dao
	 */
	public void setIndicesDao(IIndiceDao indicesDao) {
		this.indicesDao = indicesDao;
	}

	/**
	 * Sets the rentas fijas dao.
	 * 
	 * @param rentasFijasDao The DAO set by IoC
	 */
	public void setRentasFijasDao(IRentaFijaDao rentasFijasDao) {
		this.rentasFijasDao = rentasFijasDao;
	}

	// ******************************
	// INTRADIA ACCIONES
	// ******************************

	/*
	 * 
	 */
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#updateAccionesIntradia()
	 */
	public void updateAccionesIntradia() throws Exception {
		ICierre cierre = null;

		// Update each titulo of the cache per minute
		Set<String> titulos = cleanCache.keySet();
		for (String titulo : titulos) {
			// Update the dirty verion of data
			cierre = accionesDao.cargarIntradiaTitulo(titulo);
			mutex = false;
			{
				dirtyCache.get(titulo).add(cierre);
			}
			mutex = true;

			// Update the clean version of data
			cleanCache.get(titulo).add(cierre);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getAccionIntradia
	 * (java.lang.String)
	 */
	public ICierre getAccionIntradia(String titulo) {
		/*
		 * ICierre cierre = dirtyCache.get( titulo ).get( dirtyCache.size() );
		 * return cierre;
		 */

		try {
			return accionesDao.cargarIntradiaTitulo(titulo);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getAccionesIntradia
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> getAccionesIntradia(String titulo, boolean delay) {
		
		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_60SECS);
		String queryKey = "ACCIONES_INTRADIA."+titulo;
		
		List<ICierre> ret = (List) cache.getObject(queryKey);  //ACCIONES-015 

		if (ret == null) {                              	  
			synchronized(this) {
				ret = (List) cache.getObject(queryKey); 
				if ( ret == null){                     
					log.debug("Procesando Acciones Intradia: " + titulo);	
					String[] fechas = getRangoFecha();
					try {
						ret = accionesDao.cargarIntradiaTituloHistoricoDia(titulo, fechas[0], fechas[1], delay);
						cache.putObject(queryKey, ret);
						return ret;
					} catch (PersistenciaException e) {
						e.printStackTrace();
					}				                    
				}                      
			}               
		}
		else {
			log.debug("Cargando desde Cache Acciones Intradia :" + titulo); 
		}		
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getAccionesMGCIntradia
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ICierre> getAccionesMGCIntradia(String titulo, boolean delay) {
		
		ControladorCacheJCS cache = JCSFactory.getRegionControladorCache(JCSFactory.MEM_60SECS);
		String queryKey = "ACCIONESMGC_INTRADIA."+titulo;
		
		List<ICierre> ret = (List) cache.getObject(queryKey);  //ACCIONES-015 

		if (ret == null) {                              	  
			synchronized(this) {
				ret = (List) cache.getObject(queryKey); 
				if ( ret == null){                     
					log.debug("Procesando Acciones Intradia: " + titulo);	
					String[] fechas = getRangoFecha();
					try {
						ret = accionesMGCDao.cargarIntradiaTituloHistoricoDia(titulo, fechas[0], fechas[1], delay);
						cache.putObject(queryKey, ret);
						return ret;
					} catch (PersistenciaException e) {
						e.printStackTrace();
					}				                    
				}                      
			}               
		}
		else {
			log.debug("Cargando desde Cache Acciones Intradia :" + titulo); 
		}		
		return ret;
	}

	// ******************************
	// INTRADIA DERIVADOS
	// ******************************

	/*
	 * 
	 */
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#updateDerivadosIntradia()
	 */
	public void updateDerivadosIntradia() throws Exception {
		ICierre cierre = null;

		// Update each titulo of the cache per minute
		Set<String> titulos = cleanCache.keySet();
		for (String titulo : titulos) {
			// Update the dirty verion of data
			cierre = derivadosDao.cargarIntradiaDerivados(titulo);
			mutex = false;
			{
				dirtyCache.get(titulo).add(cierre);
			}
			mutex = true;

			// Update the clean version of data
			cleanCache.get(titulo).add(cierre);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getDerivadoIntradia
	 * (java.lang.String)
	 */
	public ICierre getDerivadoIntradia(String titulo) {
		/*
		 * try { return derivadosDao.cargarIntradiaDerivados(titulo); } catch
		 * (PersistenciaException e) { e.printStackTrace(); } return null;
		 */
		try {
			return derivadosDao.cargarIntradiaDerivados(titulo);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getDerivadosIntradia
	 * (java.lang.String)
	 */
	public List<ICierre> getDerivadosIntradia(String titulo, boolean opcf) {
		/*
		 * String tipoTitulo = "DERIVADO"; if ( mutex ) { if (
		 * !dirtyCache.containsKey( titulo ) ) loadTituloCache(tipoTitulo,
		 * titulo);
		 * 
		 * List<ICierre> list = dirtyCache.get( titulo );
		 * 
		 * return list; } else { if ( !cleanCache.containsKey( titulo ) )
		 * loadTituloCacheClean( tipoTitulo, titulo ); return cleanCache.get(
		 * titulo); }
		 */
		try {
			if (opcf){
				return derivadosDao.cargarIntradiaDerivadosHistoricoOPCF(titulo);
			}
			return derivadosDao.cargarIntradiaDerivadosHistorico(titulo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	// ******************************
	// INTRADIA DERIVADOS ENERGÉTICOS
	// ******************************

	/*
	 * 
	 */
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#updateDerivadosDrvxIntradia()
	 */
	public void updateDerivadosDrvxIntradia() throws Exception {
		ICierre cierre = null;

		// Update each titulo of the cache per minute
		Set<String> titulos = cleanCache.keySet();
		for (String titulo : titulos) {
			// Update the dirty verion of data
			cierre = derivadosDrvxDao.cargarIntradiaDerivados(titulo);
			mutex = false;
			{
				dirtyCache.get(titulo).add(cierre);
			}
			mutex = true;

			// Update the clean version of data
			cleanCache.get(titulo).add(cierre);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getDerivadoDrvxIntradia
	 * (java.lang.String)
	 */
	public ICierre getDerivadoDrvxIntradia(String titulo) {

		try {
			return derivadosDrvxDao.cargarIntradiaDerivados(titulo);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getDerivadosDrvxIntradia
	 * (java.lang.String)
	 */
	public List<ICierre> getDerivadosDrvxIntradia(String titulo) {
		try {
			return derivadosDrvxDao.cargarIntradiaDerivadosHistorico(titulo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// ******************************
	// INTRADIA RENTA FIJA
	// ******************************

	/*
	 * 
	 */
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#updateRentasFijasIntradia()
	 */
	public void updateRentasFijasIntradia() throws Exception {
		ICierre cierre = null;

		// Update each titulo of the cache per minute
		Set<String> titulos = cleanCache.keySet();
		for (String titulo : titulos) {
			// Update the dirty verion of data
			cierre = rentasFijasDao.cargarIntradiaRentafija(titulo);
			mutex = false;
			{
				dirtyCache.get(titulo).add(cierre);
			}
			mutex = true;

			// Update the clean version of data
			cleanCache.get(titulo).add(cierre);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getDerivadoIntradia
	 * (java.lang.String)
	 */
	public ICierre getRentaFijaIntradia(String titulo) {
		try {
			return rentasFijasDao.cargarIntradiaRentafija(titulo);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getDerivadosIntradia
	 * (java.lang.String)
	 */
	public List<ICierre> getRentasFijaIntradia(String titulo) {
		/*
		 * String tipoTitulo = "RENTA_FIJA"; if ( mutex ) { if (
		 * !dirtyCache.containsKey( titulo ) ) loadTituloCache(tipoTitulo,
		 * titulo);
		 * 
		 * List<ICierre> list = dirtyCache.get( titulo );
		 * 
		 * return list; } else { if ( !cleanCache.containsKey( titulo ) )
		 * loadTituloCacheClean( tipoTitulo, titulo ); return cleanCache.get(
		 * titulo); }
		 */
		try {
			return rentasFijasDao.graficaRentaFija(true, titulo);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ******************************
	// INTRADIA DIVISAS
	// ******************************
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#updateDolarIntradia()
	 */
	public void updateDolarIntradia() throws Exception {
		//JHACER cache en completo desuso
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getDolarIntradia()
	 */
	public ICierre getDolarIntradia() throws Exception {
		return divisasDao.getIntradiaDolar();
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getDolaresIntradia()
	 */
	public List<ICierre> getDolaresIntradia() throws Exception{
		String[] fechas = getRangoFecha();
		return divisasDao.getIntradiaHistoricoDolar(fechas[1]);
	}
	
	// ******************************
	// INTRADIA INDICE
	// ******************************

	/*
	 * 
	 */
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getIndiceIntradia(java.lang.String)
	 */
	public ICierre getIndiceIntradia(String titulo) {
		ICierre cargarIntradiaIndice = indicesDao.cargarIntradiaIndice(titulo);
		return cargarIntradiaIndice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getIndicesIntradia
	 * (java.lang.String)
	 */
	public List<ICierre> getIndicesIntradia(String titulo) throws Exception {
		return indicesDao.cargarIntradiaIndicesHistorico(titulo);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.cache.IIntradiaCache#getIndicesIntradiaRF
	 * (java.lang.String)
	 */
	public List<ICierre> getIndicesIntradiaRF(String titulo) throws Exception {
		return indicesDao.cargarIntradiaIndicesHistoricoRF(titulo);
	}


	// -------------------------------
	// Private methods
	// -------------------------------

	/**
	 * Gets the rango fecha.
	 * 
	 * @return the rango fecha
	 */
	private String[] getRangoFecha() {

		// Estableciendo la hora actual.
		Calendar instance = new GregorianCalendar();
		Date fechaFin = instance.getTime();
		SimpleDateFormat frmFin = new SimpleDateFormat("yyyy-MM-dd:kk:mm");

		// Estableciendo la hora de apertura del mercado de acciones.
		instance.set(Calendar.HOUR_OF_DAY, 0);
		instance.set(Calendar.MINUTE, 1);
		Date fechaIni = instance.getTime();
		SimpleDateFormat frmIni = new SimpleDateFormat("yyyy-MM-dd:HH:mm");

		return new String[] { frmIni.format(fechaIni), frmFin.format(fechaFin) };
	}
}
/****************************************************************End of IntradiaCache.java**********************/