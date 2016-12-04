package co.com.bvc.portal.mercados.vista.portlet.ajax;

// TODO: Auto-generated Javadoc
/**
 * The Interface IHomeAjax.
 */
public interface IHomeAjax {

	/** The Constant SEPARADOR_PRIMER_NIVEL. */
	public static final String SEPARADOR_PRIMER_NIVEL = "$%$";
	
	/** The Constant SEPARADOR_SEGUNDO_NIVEL. */
	public static final String SEPARADOR_SEGUNDO_NIVEL = "#%#";
	
	/** The Constant SEPARADOR_TERCER_NIVEL. */
	public static final String SEPARADOR_TERCER_NIVEL = "&%&";
	
	/** The Constant SEPARADOR_CUARTO_NIVEL. */
	public static final String SEPARADOR_CUARTO_NIVEL = "@%@"; 
	
	/** The Constant PATRON_NUMERICO. */
	public static final String PATRON_NUMERICO = "###,##0.00";
	
	/**
	 * Gets the data indices home.
	 * 
	 * @return nombreInd1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + valorHoy1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + variacion1
	 * + SEPARADOR_PRIMER_NIVEL
	 * + nombreInd2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + valorHoy2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + variacion2
	 */
	public String getDataIndicesHome();
	
	/**
	 * Gets the fecha indices home.
	 * 
	 * @return fecha
	 */
	public String getFechaIndicesHome();
	
	/**
	 * Gets the data acciones home.
	 * 
	 * @param nomLista (listaAcciones, listaAccionesSube, listaAccionesBaja)
	 * 
	 * @return de acuerdo al nombre de la lista
	 * nemo1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + volumenYaDividido1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + ultimoPrecio1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + variacion1
	 * + SEPARADOR_PRIMER_NIVEL
	 * + nemo2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + volumenYaDividido2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + ultimoPrecio2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + variacion2
	 */
	public String getDataAccionesHome(String nomLista);
	
	/**
	 * Gets the data acciones mgc home.
	 * 
	 * @param nomLista (listaAccionesMGC, listaAccionesMGCSube, listaAccionesMGCBaja)
	 * 
	 * @return de acuerdo al nombre de la lista
	 * nemo1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + volumenYaDividido1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + ultimoPrecio1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + variacion1
	 * + SEPARADOR_PRIMER_NIVEL
	 * + nemo2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + volumenYaDividido2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + ultimoPrecio2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + variacion2
	 */
	public String getDataAccionesMGCHome(String nomLista);
	
	/**
	 * Gets the data etf mgc home.
	 * 
	 * @param nomLista (listaEtfsMGC, listaEtfsMGCSube, listaEtfsMGCBaja)
	 * 
	 * @return de acuerdo al nombre de la lista
	 * nemo1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + volumenYaDividido1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + ultimoPrecio1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + variacion1
	 * + SEPARADOR_PRIMER_NIVEL
	 * + nemo2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + volumenYaDividido2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + ultimoPrecio2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + variacion2
	 */
	public String getDataEtfsMGCHome(String nomLista);
	
	/**
	 * Gets the fecha acciones home.
	 * 
	 * @return fecha
	 */
	public String getFechaAccionesHome();
	
	/**
	 * Gets the fecha acciones MGC home.
	 * 
	 * @return fecha
	 */
	public String getFechaAccionesMGCHome();
	
	/**
	 * Gets the fecha etfs MGC home.
	 * 
	 * @return fecha
	 */
	public String getFechaETFsMGCHome();
	
	/**
	 * Gets the volumen acciones home.
	 * 
	 * @return volumenAcciones
	 */
	public String getVolumenAccionesHome();
	
	/**
	 * Gets the volumen acciones home.
	 * 
	 * @return volumenAcciones
	 */
	public String getVolumenAccionesMGCHome();
	
	/**
	 * Gets the volumen etfs home.
	 * 
	 * @return volumenAcciones
	 */
	public String getVolumenETFsMGCHome();
	
	/**
	 * Gets the fecha derivados home.
	 * 
	 * @return fecha
	 */
	public String getFechaDerivadosHome();
	
	/**
	 * Gets the resumen general derivados home.
	 * 
	 * @return contrato1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + contratos1
	 * + SEPARADOR_PRIMER_NIVEL
	 * + contrato2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + contratos2
	 */
	public String getResumenGeneralDerivadosHome();
	
	/**
	 * Gets the data derivados home.
	 * 
	 * @param nomLista the nom lista
	 * 
	 * @return de acuerdo al nombre de la lista (derivadosResumenFuturos, derivadosResumenOpciones, derivadosResumenOPCF)
	 * der1ext1contrato1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1contratos1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1precio1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1variacion1
	 * + SEPARADOR_TERCER_NIVEL
	 * der1ext1contrato2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1contratos2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1precio2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1variacion2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + der1ext2contrato1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2contratos1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2precio1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2variacion1
	 * + SEPARADOR_TERCER_NIVEL
	 * der1ext2contrato2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2contratos2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2precio2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2variacion2
	 * + SEPARADFOR_PRIMER_NIVEL
	 * + der2ext1contrato1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1contratos1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1precio1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1variacion1
	 * + SEPARADOR_TERCER_NIVEL
	 * der2ext1contrato2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1contratos2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1precio2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1variacion2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + der2ext2contrato1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2contratos1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2precio1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2variacion1
	 * + SEPARADOR_TERCER_NIVEL
	 * der2ext2contrato2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2contratos2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2precio2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2variacion2
	 */
	public String getDataDerivadosHome(String nomLista);
	
	
public String getFechaDerivadosHomeDrvx();
	
	/**
	 * Gets the resumen general derivados home.
	 * 
	 * @return contrato1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + contratos1
	 * + SEPARADOR_PRIMER_NIVEL
	 * + contrato2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + contratos2
	 */
	public String getResumenGeneralDerivadosHomeDrvx();
	
	/**
	 * Gets the data derivados home.
	 * 
	 * @param nomLista the nom lista
	 * 
	 * @return de acuerdo al nombre de la lista (derivadosResumenFuturos, derivadosResumenOpciones, derivadosResumenOPCF)
	 * der1ext1contrato1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1contratos1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1precio1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1variacion1
	 * + SEPARADOR_TERCER_NIVEL
	 * der1ext1contrato2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1contratos2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1precio2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext1variacion2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + der1ext2contrato1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2contratos1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2precio1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2variacion1
	 * + SEPARADOR_TERCER_NIVEL
	 * der1ext2contrato2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2contratos2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2precio2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der1ext2variacion2
	 * + SEPARADFOR_PRIMER_NIVEL
	 * + der2ext1contrato1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1contratos1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1precio1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1variacion1
	 * + SEPARADOR_TERCER_NIVEL
	 * der2ext1contrato2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1contratos2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1precio2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext1variacion2
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + der2ext2contrato1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2contratos1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2precio1
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2variacion1
	 * + SEPARADOR_TERCER_NIVEL
	 * der2ext2contrato2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2contratos2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2precio2
	 * + SEPARADOR_CUARTO_NIVEL
	 * + der2ext2variacion2
	 */
	public String getDataDerivadosHomeDrvx(String nomLista);

		
	/**
	 * Gets the fecha renta fija home.
	 * 
	 * @return fecha
	 */
	public String getFechaRentaFijaHome();
	
	/**
	 * Gets the volumenes rentafija home.
	 * 
	 * @return volTransado + SEPARADOR_PRIMER_NIVEL + volRegistrado
	 */
	public String getVolumenesRentafijaHome();
	
	/**
	 * Gets the data renta fija home.
	 * 
	 * @return the data renta fija home
	 * 
	 * pubnemo1
	 * + SEPARADOR_TERCER_NIVEL
	 * + pubVolumen1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + pubnemo2
	 * + SEPARADOR_TERCER_NIVEL
	 * + pubVolumen2
	 * + SEPARADOR_PRIMER_NIVEL
	 * + prinemo1
	 * + SEPARADOR_TERCER_NIVEL
	 * + priVolumen1
	 * + SEPARADOR_SEGUNDO_NIVEL
	 * + prinemo2
	 * + SEPARADOR_TERCER_NIVEL
	 * + priVolumen2
	 */
	public String getDataRentaFijaHome();
	
	/**
	 * Gets the data divisas home.
	 * 
	 * @return  res.getVolumenNegociado() + SEPARADOR_PRIMER_NIVEL
	 * + res.getPrecioPromedio() + SEPARADOR_PRIMER_NIVEL
	 * + res.getTrm() + SEPARADOR_PRIMER_NIVEL
	 * + res.getVariacion()
	 */
	public String getDataDivisasHome();
	
	/**
	 * Gets the volumenes divisas home.
	 * 
	 * @return the volumenes divisas home
	 */
	public String getVolumenesDivisasHome();
	
	/**
	 * Gets the data accion bvc home.
	 * 
	 * @return  tit.getVolumen() + SEPARADOR_PRIMER_NIVEL
	 * + tit.getPrecioCierre() + SEPARADOR_PRIMER_NIVEL
	 * + tit.getVariacion()
	 */
	public String getDataAccionBvcHome();
	
	/**
	 * Gets the fecha accion bvc home.
	 * 
	 * @return fecha
	 */
	public String getFechaAccionBvcHome();

	/**
	 * Se encarga de limpiar el cache de los home.
	 * 
	 * @return true si pudo limpiar el cache con exito false en otro caso
	 */
	public String cleanCache();
	
}
