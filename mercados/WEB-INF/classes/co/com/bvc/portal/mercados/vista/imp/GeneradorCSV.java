package co.com.bvc.portal.mercados.vista.imp;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import co.com.bvc.portal.mercados.modelo.CierreGeneral;
import co.com.bvc.portal.mercados.modelo.ICierre;
import co.com.bvc.portal.mercados.vista.IGeneradorDatos;

//  Generated by StarUML(tm) Java Add-In

/**
 *  @description The Class GeneradorCSV.
 *  @ Project : PortalBVC
 *  @ File Name : GeneradorCSV.java
 *  @ Date : 03/09/2008
 *  @ Author : BVC
 */

public class GeneradorCSV implements IGeneradorDatos {

	/** The log. */
	private Logger log = Logger.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.IGeneradorDatos#generarSalida(java.util.List)
	 */
	public byte[] generarSalida(List<ICierre> datos)
			throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm");

		Set<ICierre> cierres = new TreeSet<ICierre>(datos);
		
		if(cierres.size()==0){
			cierres.add(new CierreGeneral());
		}
		int i = 0;
		for (ICierre tmp : cierres) {
			log.debug("index: " + (i++) + " dato " + tmp.getFechaHora().getTime());
			sb.append(df.format(tmp.getFechaHora().getTime()));
			sb.append(",");
			sb.append(tmp.getValorCierre());
			sb.append(",");
			sb.append(tmp.getVolumen());
			sb.append(",");
			sb.append(tmp.getValorUltimoCierre());
			sb.append("\n");
		}

		return sb.toString().getBytes("UTF-8");
	}
}
/******************************************End of GeneradorCSV.java*************************************************************/