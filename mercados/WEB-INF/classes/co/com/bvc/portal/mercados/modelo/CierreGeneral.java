package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import co.com.bvc.portal.comun.util.UtilFechas;

// TODO: Auto-generated Javadoc
/**
 * The Class CierreGeneral.
 */
public class CierreGeneral implements ICierre, Serializable {

	private static final long serialVersionUID = 80467697000001L;

	/** The log. */
	private  transient Logger log = Logger.getLogger(CierreGeneral.class);
	
	/** The fecha hora. */
	private Calendar fechaHora = Calendar.getInstance();

	/** The valor cierre. */
	private double valorCierre = 0;

	/** The valor min. */
	private double valorMin = 0;

	/** The valor max. */
	private double valorMax = 0;

	/** The volumen. */
	private double volumen = 0;

	/** The volumen. */
	private double volumenMwh = 0;

	/** The fecha string. */
	private String fechaString = "";

	/** The valor ultimo cierre. */
	private double valorUltimoCierre = 0;

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#getFechaHora()
	 */
	public Calendar getFechaHora() {
		return fechaHora;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#setFechaHora(java.util.Calendar)
	 */
	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#getValorCierre()
	 */
	public double getValorCierre() {
		return valorCierre;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#setValorCierre(double)
	 */
	public void setValorCierre(double valorCierre) {
		this.valorCierre = valorCierre;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#getValorMin()
	 */
	public double getValorMin() {
		return valorMin;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#setValorMin(double)
	 */
	public void setValorMin(double valorMin) {
		this.valorMin = valorMin;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#getValorMax()
	 */
	public double getValorMax() {
		return valorMax;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#setValorMax(double)
	 */
	public void setValorMax(double valorMax) {
		this.valorMax = valorMax;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#getVolumen()
	 */
	public double getVolumen() {
		return volumen;
	}

	public double getVolumenMwh() {
		return volumenMwh;
	}
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#setVolumen(double)
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public void setVolumenMwh(double volumenMwh) {
		this.volumenMwh = volumenMwh;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ICierre o) {
		return o.getFechaHora().compareTo(this.fechaHora);
	}

	/**
	 * Gets the fecha string.
	 * 
	 * @return the fechaString
	 */
	public String getFechaString() {
		return fechaString;
	}

	/**
	 * Sets the fecha string.
	 * 
	 * @param fechaString the fechaString to set
	 */
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
		if (fechaString!= null && fechaString.length() == 16){
			fechaString +=":00";
		}
		try {
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:kk:mm:ss");
			this.fechaHora = new GregorianCalendar();
			this.fechaHora.setTime(sdf.parse(fechaString));
		} catch (Exception e) {
			log.error("error al intentar setear la fecha del cierre: " + fechaString);
			this.fechaHora = Calendar.getInstance();
		}
	}
	
	/**
	 * Sets the fecha grafico.
	 * 
	 * @param fechaGrafico the new fecha grafico
	 */
	public void setFechaGrafico(String fechaGrafico){
		
		if(fechaGrafico !=null && UtilFechas.getHoy("yyyy").equals(fechaGrafico.subSequence(0, 4))){
			fechaGrafico += "1600";
		}else {
		 fechaGrafico += "1300";
		}
		try {
			DateFormat sdf = new SimpleDateFormat("yyyyMMddkkmm");
			this.fechaHora = new GregorianCalendar();
			this.fechaHora.setTime(sdf.parse(fechaGrafico));
		} catch (Exception e) {
			log.error("error al intentar setear la fecha del cierre: " + fechaString);
			this.fechaHora = Calendar.getInstance();
		}
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#setValorUltimoCierre(double)
	 */
	public void setValorUltimoCierre(double valor){
		this.valorUltimoCierre =  valor;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.modelo.ICierre#getValorUltimoCierre()
	 */
	public double getValorUltimoCierre(){
		return this.valorUltimoCierre;
	}
}
