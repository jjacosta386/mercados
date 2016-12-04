package co.com.bvc.portal.mercados.persistencia.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import co.com.bvc.portal.comun.excepcion.PersistenciaException;
import co.com.bvc.portal.comun.persistencia.jdbc.JDBCDaoImp;
import co.com.bvc.portal.comun.servicio.imp.ControladorCacheJCS;
import co.com.bvc.portal.comun.servicio.imp.JCSFactory;
import co.com.bvc.portal.comun.util.UtilFechas;
import co.com.bvc.portal.mercados.modelo.HorarioMercado;
import co.com.bvc.portal.mercados.persistencia.IMercadoDao;

// TODO: Auto-generated Javadoc
/**
 * The Class MercadoDaoSpringJdbc.
 */
public class MercadoDaoSpringJdbc extends JDBCDaoImp implements IMercadoDao {
	
	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IMercadoDao#obtenerSegmentoMercado()
	 */
	@SuppressWarnings("unchecked")
	public List<HorarioMercado> obtenerSegmentoMercado()
			throws PersistenciaException {
		String consulta = "SELECT * FROM portalbvc.segmento_mercado s order by id_segmento_mercado";
		List<HorarioMercado> respuesta = new ArrayList<HorarioMercado>();		
		Collection tmp = this.getJdbcTemplate().query(consulta,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						HorarioMercado resumen = new HorarioMercado();
						resumen.setIdHor(rs.getInt("ID_SEGMENTO_MERCADO"));
						resumen.setNombreMercado(rs.getString("DESCRIPCION"));

						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String sHoraAp = rs.getString("HORA_APERTURA");
						String sHoraCi = rs.getString("HORA_CIERRE");
						Date horaAp;
						Date horaCi;
						try {
							horaAp = sdf.parse(sHoraAp);
							horaCi = sdf.parse(sHoraCi);
							resumen.getHoraInicio().setTimeInMillis(
									horaAp.getTime());
							resumen.getHoraFin().setTimeInMillis(
									horaCi.getTime());
						} catch (ParseException e) {
							e.printStackTrace();
						}

						return resumen;
					}
				});

		respuesta = new ArrayList<HorarioMercado>(tmp);
		return respuesta;

	}

	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.persistencia.IMercadoDao#mercadoAbierto(java.util.GregorianCalendar, int, boolean)
	 */
	@SuppressWarnings("unchecked")
	public String mercadoAbierto(GregorianCalendar fechaConsulta,
			int tipoMercado, boolean delay) throws PersistenciaException {
		
		
		
		ControladorCacheJCS cachePersistente = JCSFactory.getRegionControladorCache(JCSFactory.DISK_DAILY_12AM);		
		String queryKey = "FECHAS_NO_BURSATIL." +UtilFechas.getHoy("yyyyMMdd");
		List<Map> result;		
		result = (List<Map>) cachePersistente.getObject(queryKey);  //INDICES-005  
		
		if (null == result) {			                              	  
	             synchronized(this) {
	             	result = (List<Map>) cachePersistente.getObject(queryKey); 
	                 if ( null == result){ 
	                	 String consulta = "";
	                 	log.debug("Procesando dias no bursatil");
	                 	if(tipoMercado == IMercadoDao.ACCIONES_MGC)
	                 		consulta = "SELECT fecha FROM portalbvc.fecha_no_bursatil_mgc f";
	                 	else 
	                 		consulta = "SELECT fecha FROM portalbvc.fecha_no_bursatil f";
	            		result = cargarPorQuery(consulta);                                            
	                    cachePersistente.putObject(queryKey, result);	                   
	                 }                      
	             }               
	    }					
        else {
        	log.debug("Cargando desde cache dias no bursatil. QueryKey : " + queryKey); 
        }	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (delay && sdf.format(fechaConsulta.getTime()).equals(sdf.format(new Date())))
			fechaConsulta.set(Calendar.MINUTE, fechaConsulta
					.get(Calendar.MINUTE) - 20);
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			GregorianCalendar fecha = new GregorianCalendar();
			try {
				fecha.setTime(sdf.parse(map.get("fecha").toString()));
			} catch (Exception e) {

			}
			if (sdf.format(fechaConsulta.getTime()).equals(
					sdf.format(fecha.getTime()))) { 
				return DIA_NO_BURSATIL;
			}
		}
		if (fechaConsulta.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| fechaConsulta.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return DIA_NO_BURSATIL;
		}
		
		
		queryKey = "SEGMENTO_MERCADO."+UtilFechas.getHoy("yyyyMMdd");;
		List<HorarioMercado> horarios;		
		horarios = (List<HorarioMercado>) cachePersistente.getObject(queryKey);  //INDICES-005  
		
		if (null == horarios) {			                              	  
	             synchronized(this) {
	             	horarios = (List<HorarioMercado>) cachePersistente.getObject(queryKey); 
	                 if ( null == horarios){                     
	                 	log.debug("Procesando segmento mercado");
	                 	horarios = this.obtenerSegmentoMercado();                                        
	                    cachePersistente.putObject(queryKey, horarios);	                   
	                 }                      
	             }               
	    }					
        else {
        	log.debug("Cargando desde cache  dias no bursatil. QueryKey : " + queryKey); 
        }	
		
		
		
		for (Iterator iterator = horarios.iterator(); iterator.hasNext();) {
			HorarioMercado horarioMercado = (HorarioMercado) iterator.next();
			if (horarioMercado.getIdHor() == tipoMercado
					&& (menor(horarioMercado.getHoraInicio(), fechaConsulta) && menor(
							fechaConsulta, horarioMercado.getHoraFin()))){ 
				return MERCADO_ABIERTO;
			}
		}
		return MERCADO_CERRADO;
	}

	/**
	 * Devuelve true si la hora de la fecha de consulta es menor que la hora de
	 * la hora fin.
	 * 
	 * @param fechaConsulta Fecha que se quiere sea menor
	 * @param horaFin fecha que se quiere que sea mayor
	 * 
	 * @return true, if menor
	 */
	private boolean menor(GregorianCalendar fechaConsulta,
			GregorianCalendar horaFin) {
		if (fechaConsulta.get(Calendar.HOUR_OF_DAY) == horaFin
				.get(Calendar.HOUR_OF_DAY)) {
			if (fechaConsulta.get(Calendar.MINUTE) == horaFin
					.get(Calendar.MINUTE)) {
				if (fechaConsulta.get(Calendar.SECOND) <= horaFin
						.get(Calendar.SECOND))
					return true;
				else
					return false;
			} else if (fechaConsulta.get(Calendar.MINUTE) < horaFin
					.get(Calendar.MINUTE))
				return true;
			else
				return false;
		} else if (fechaConsulta.get(Calendar.HOUR_OF_DAY) < horaFin
				.get(Calendar.HOUR_OF_DAY))
			return true;
		return false;
	}

}