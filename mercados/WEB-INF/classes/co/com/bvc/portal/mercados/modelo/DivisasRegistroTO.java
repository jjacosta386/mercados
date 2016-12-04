package co.com.bvc.portal.mercados.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * The Class DivisasRegistroTO.
 */
public class DivisasRegistroTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5540885628673017004L;

	/** The fecha liquidacion int. */
	private Integer fechaLiquidacionInt = 0;
	
	/** The monto. */
	private Double monto = 0D;

	/** The divisa. */
	private String divisa = "";

	/** The mercado. */
	private String mercado = "";
	
	/** The mercado inicial. */
	private String mercadoInicial = "";

	/** The monto dolares. */
	private Double montoDolares = 0D;

	/** The tasa. */
	private Double tasa = 0D;

	/** The fecha liquidacion. */
	private Calendar fechaLiquidacion = new GregorianCalendar();

	/**
	 * Gets the monto.
	 * 
	 * @return the monto
	 */
	public Double getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 * 
	 * @param monto the new monto
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	/**
	 * Gets the divisa.
	 * 
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 * 
	 * @param divisa the new divisa
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the mercado.
	 * 
	 * @return the mercado
	 */
	public String getMercado() {
		return mercado;
	}

	/**
	 * Sets the mercado.
	 * 
	 * @param mercado the new mercado
	 */
	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	/**
	 * Gets the monto dolares.
	 * 
	 * @return the monto dolares
	 */
	public Double getMontoDolares() {
		return montoDolares;
	}

	/**
	 * Sets the monto dolares.
	 * 
	 * @param montoDolares the new monto dolares
	 */
	public void setMontoDolares(Double montoDolares) {
		this.montoDolares = montoDolares;
	}

	/**
	 * Gets the tasa.
	 * 
	 * @return the tasa
	 */
	public Double getTasa() {
		return tasa;
	}

	/**
	 * Sets the tasa.
	 * 
	 * @param tasa the new tasa
	 */
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	/**
	 * Gets the fecha liquidacion.
	 * 
	 * @return the fecha liquidacion
	 */
	public String getFechaLiquidacion() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(fechaLiquidacion.getTime());
	}

	/**
	 * Sets the fecha liquidacion.
	 * 
	 * @param fechaLiquidacion the new fecha liquidacion
	 */
	public void setFechaLiquidacion(Calendar fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the mercado inicial.
	 * 
	 * @return the mercado inicial
	 */
	public String getMercadoInicial() {
		return mercadoInicial;
	}

	/**
	 * Sets the mercado inicial.
	 * 
	 * @param mercadoInicial the new mercado inicial
	 */
	public void setMercadoInicial(String mercadoInicial) {
		this.mercadoInicial = mercadoInicial;
		if ("L".equals(mercadoInicial)){
			mercado = "Libre";
		}else{
			mercado = "Cambiario";
		}
	}

	/**
	 * Gets the fecha liquidacion int.
	 * 
	 * @return the fecha liquidacion int
	 */
	public Integer getFechaLiquidacionInt() {
		return fechaLiquidacionInt;
	}

	/**
	 * Sets the fecha liquidacion int.
	 * 
	 * @param fechaLiquidacionInt the new fecha liquidacion int
	 */
	public void setFechaLiquidacionInt(Integer fechaLiquidacionInt) {
		this.fechaLiquidacionInt = fechaLiquidacionInt;
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try{
			this.fechaLiquidacion.setTime(sdf.parse(fechaLiquidacionInt.toString()));
		}catch (Exception e) {
			this.fechaLiquidacion.setTime(new Date());
		}
	}

}
