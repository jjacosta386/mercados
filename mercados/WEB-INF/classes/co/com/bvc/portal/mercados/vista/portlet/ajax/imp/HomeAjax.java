package co.com.bvc.portal.mercados.vista.portlet.ajax.imp;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.ui.Model;

import co.com.bvc.portal.mercados.modelo.Derivado;
import co.com.bvc.portal.mercados.modelo.DerivadoResumen;
import co.com.bvc.portal.mercados.modelo.DerivadoResumenExtendido;
import co.com.bvc.portal.mercados.modelo.DivisasSetFxTO;
import co.com.bvc.portal.mercados.modelo.MercadoDerivados;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumen;
import co.com.bvc.portal.mercados.modelo.RentaFijaResumenEspecie;
import co.com.bvc.portal.mercados.modelo.ResumenAccion;
import co.com.bvc.portal.mercados.modelo.ResumenAccionMGC;
import co.com.bvc.portal.mercados.modelo.ResumenIndice;
import co.com.bvc.portal.mercados.modelo.TituloAccion;
import co.com.bvc.portal.mercados.util.IConstantesAtributosModelo;
import co.com.bvc.portal.mercados.vista.cache.IHomeCache;
import co.com.bvc.portal.mercados.vista.cache.impl.HomeCache;
import co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeAccionBVCPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeAccionesPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeDerivadosPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeDerivadosPortletDrvx;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeDivisasPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeIndicePortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeRentaFijaPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeAccionesMGCPortlet;
import co.com.bvc.portal.mercados.vista.portlet.home.HomeEtfsMGCPortlet;
import co.com.bvc.portal.mercados.modelo.ResumenEtfsMGC;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeAjax.
 */
@SuppressWarnings("unchecked")
public class HomeAjax implements IHomeAjax {

	/** The home indices. */
	private HomeIndicePortlet homeIndices;

	/** The home divisas. */
	private HomeDivisasPortlet homeDivisas;

	/** The home accion bvc. */
	private HomeAccionBVCPortlet homeAccionBVC;

	/** The home acciones. */
	private HomeAccionesPortlet homeAcciones;

	/** The home derivados. */
	private HomeDerivadosPortlet homeDerivados;

	/** The home derivados Energeticos. */
	private HomeDerivadosPortletDrvx homeDerivadosDrvx;
	
	/** The home renta fija. */
	private HomeRentaFijaPortlet homeRentaFija;
	
	/** The home acciones MGC. */
	private HomeAccionesMGCPortlet homeAccionesMGC;
	
	/** The home etf MGC. */
	private HomeEtfsMGCPortlet homeEtfsMGC;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getDataIndicesHome
	 * ()
	 */
	public String getDataIndicesHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_INDICES, homeIndices);
		List<ResumenIndice> lista = (List<ResumenIndice>) model.asMap().get(
				IConstantesAtributosModelo.RESUMEN_INDICES);
		String ret = "";
		if (lista != null && !lista.isEmpty()) {
			boolean primero = true;
			for (Iterator<ResumenIndice> iterator = lista.iterator(); iterator
					.hasNext();) {
				if (!primero) {
					ret += SEPARADOR_PRIMER_NIVEL;
				}
				ResumenIndice indice = iterator.next();
				ret += indice.getNombreIndice() + SEPARADOR_SEGUNDO_NIVEL
						+ formatearNumero(indice.getValorHoy()) + SEPARADOR_SEGUNDO_NIVEL
						+ indice.getVariacionHoy();
				primero = false;
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getFechaIndicesHome
	 * ()
	 */
	public String getFechaIndicesHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_INDICES, homeIndices);
		String ret = (String) model.asMap().get(IConstantesAtributosModelo.FECHA_INDICES);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getDataDivisasHome
	 * ()
	 */
	public String getDataDivisasHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_DIVISAS, homeDivisas);
		DivisasSetFxTO res = (DivisasSetFxTO) model.asMap().get(
				IConstantesAtributosModelo.DETALLE_SET_FX);
		String ret = "";
		if (res != null) {
			ret = formatearNumero(res.getVolumenNegociado() / 1000) + SEPARADOR_PRIMER_NIVEL
					+ formatearNumero(res.getPrecioPromedio()) + SEPARADOR_PRIMER_NIVEL
					+ formatearNumero(res.getTrm()) + SEPARADOR_PRIMER_NIVEL
					+ res.getVariacion();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#
	 * getVolumenesDivisasHome()
	 */
	public String getVolumenesDivisasHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_DIVISAS, homeDivisas);
		Map<String, String> res = (Map<String, String>) model.asMap().get(
				IConstantesAtributosModelo.RESUMEN_MERCADO);
		String ret = "";
		if (res != null && !res.isEmpty()) {
			boolean primero = true;
			for (Iterator<String> iterator = res.keySet().iterator(); iterator
					.hasNext();) {
				if (!primero) {
					ret += SEPARADOR_PRIMER_NIVEL;
				}
				String key = (String) iterator.next();
				ret += key + SEPARADOR_SEGUNDO_NIVEL + res.get(key);
				primero = false;
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getDataAccionBvcHome
	 * ()
	 */
	public String getDataAccionBvcHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_ACCION_BVC,
				homeAccionBVC);
		TituloAccion tit = (TituloAccion) model.asMap().get(IConstantesAtributosModelo.TITULO_ACCION_BVC);
		String ret = "";
		if (tit != null) {
			ret = formatearNumero(tit.getVolumen()) + SEPARADOR_PRIMER_NIVEL
					+ formatearNumero(tit.getPrecioCierre()) + SEPARADOR_PRIMER_NIVEL
					+ tit.getVariacion();
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getFechaAccionBvcHome
	 * ()
	 */
	public String getFechaAccionBvcHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_ACCION_BVC,
				homeAccionBVC);
		return (String) model.asMap().get(IConstantesAtributosModelo.FECHA_ACCION_BVC);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getDataAccionesHome
	 * (java.lang.String)
	 */
	public String getDataAccionesHome(String nomLista) {
		Model model = HomeCache
				.getModel(IHomeCache.HOME_ACCIONES, homeAcciones);
		Map<String, List<ResumenAccion>> res = (Map<String, List<ResumenAccion>>) model
				.asMap().get(IConstantesAtributosModelo.LISTAS_ACCIONES);
		String ret = "";
		if (res != null && !res.isEmpty()) {
			List<ResumenAccion> lista = res.get(nomLista);
			boolean primero = true;
			for (Iterator<ResumenAccion> iterator = lista.iterator(); iterator
					.hasNext();) {
				if (!primero) {
					ret += SEPARADOR_PRIMER_NIVEL;
				}
				ResumenAccion resumenAccion = iterator.next();
				ret += resumenAccion.getNemoTecnico() + SEPARADOR_SEGUNDO_NIVEL
						+ formatearNumero((resumenAccion.getVolumen() / 1000000))
						+ SEPARADOR_SEGUNDO_NIVEL
						+ formatearNumero(resumenAccion.getUltimoPrecio())
						+ SEPARADOR_SEGUNDO_NIVEL
						+ resumenAccion.getVariacion();
				primero = false;
			}
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getDataAccionesMGCHome
	 * (java.lang.String)
	 */
	public String getDataAccionesMGCHome(String nomLista) {
		Model model = HomeCache
				.getModel(IHomeCache.HOME_ACCIONES_MGC, homeAccionesMGC);
		Map<String, List<ResumenAccionMGC>> res = (Map<String, List<ResumenAccionMGC>>) model
				.asMap().get(IConstantesAtributosModelo.LISTAS_ACCIONES_MGC);
		String ret = "";
		if (null != res && !res.isEmpty()) {
			List<ResumenAccionMGC> lista = res.get(nomLista);
			boolean primero = true;
			for (Iterator<ResumenAccionMGC> iterator = lista.iterator(); iterator
					.hasNext();) {
				if (!primero) {
					ret += SEPARADOR_PRIMER_NIVEL;
				}
				ResumenAccionMGC resumenAccionMGC = iterator.next();
				ret += resumenAccionMGC.getNemoTecnico() + SEPARADOR_SEGUNDO_NIVEL
						+ formatearNumero((resumenAccionMGC.getVolumen() / 1000000))
						+ SEPARADOR_SEGUNDO_NIVEL
						+ formatearNumero(resumenAccionMGC.getUltimoPrecio())
						+ SEPARADOR_SEGUNDO_NIVEL
						+ resumenAccionMGC.getVariacion();
				primero = false;
			}
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getDataEtfsMGCHome
	 * (java.lang.String)
	 */
	public String getDataEtfsMGCHome(String nomLista) {
		Model model = HomeCache
				.getModel(IHomeCache.HOME_ETF_MGC, homeEtfsMGC);
		Map<String, List<ResumenEtfsMGC>> res = (Map<String, List<ResumenEtfsMGC>>) model
				.asMap().get(IConstantesAtributosModelo.LISTAS_ETFS_MGC);
		String ret = "";
		if (null != res && !res.isEmpty()) {
			List<ResumenEtfsMGC> lista = res.get(nomLista);
			boolean primero = true;
			for (Iterator<ResumenEtfsMGC> iterator = lista.iterator(); iterator
					.hasNext();) {
				if (!primero) {
					ret += SEPARADOR_PRIMER_NIVEL;
				}
				ResumenEtfsMGC resumenAccionMGC = iterator.next();
				ret += resumenAccionMGC.getNemoTecnico() + SEPARADOR_SEGUNDO_NIVEL
						+ formatearNumero((resumenAccionMGC.getVolumen() / 1000000))
						+ SEPARADOR_SEGUNDO_NIVEL
						+ formatearNumero(resumenAccionMGC.getUltimoPrecio())
						+ SEPARADOR_SEGUNDO_NIVEL
						+ resumenAccionMGC.getVariacion();
				primero = false;
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getFechaAccionesHome
	 * ()
	 */
	public String getFechaAccionesHome() {
		Model model = HomeCache
				.getModel(IHomeCache.HOME_ACCIONES, homeAcciones);
		return (String) model.asMap().get(IConstantesAtributosModelo.HORARIO_ACCIONES);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#
	 * getVolumenAccionesHome()
	 */
	public String getVolumenAccionesHome() {
		Model model = HomeCache
				.getModel(IHomeCache.HOME_ACCIONES, homeAcciones);
		return (String) model.asMap().get(IConstantesAtributosModelo.VOLUMEN_TOTAL_ACCIONES);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getFechaAccionesMGCHome
	 * ()
	 */
	public String getFechaAccionesMGCHome() {
		Model model = HomeCache
				.getModel(IHomeCache.HOME_ACCIONES_MGC, homeAccionesMGC);
		return (String) model.asMap().get(IConstantesAtributosModelo.HORARIO_ACCIONES_MGC);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#
	 * getVolumenAccionesMGCHome()
	 */
	public String getVolumenAccionesMGCHome() {
		Model model = HomeCache
				.getModel(IHomeCache.HOME_ACCIONES_MGC, homeAccionesMGC);
		return (String) model.asMap().get(IConstantesAtributosModelo.VOLUMEN_TOTAL_ACCIONES_MGC);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getFechaETFsMGCHome
	 * ()
	 */
	public String getFechaETFsMGCHome() {
		Model model = HomeCache
				.getModel(IHomeCache.HOME_ETF_MGC, homeEtfsMGC);
		return (String) model.asMap().get(IConstantesAtributosModelo.HORARIO_ETFS_MGC);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#
	 * getVolumenAccionesMGCHome()
	 */
	public String getVolumenETFsMGCHome() {
		Model model = HomeCache
				.getModel(IHomeCache.HOME_ETF_MGC, homeEtfsMGC);
		return (String) model.asMap().get(IConstantesAtributosModelo.VOLUMEN_TOTAL_ETFS_MGC);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getFechaDerivadosHome
	 * ()
	 */
	public String getFechaDerivadosHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_DERIVADOS,
				homeDerivados);
		return (String) model.asMap().get(IConstantesAtributosModelo.ESTADO_MERCADO_DERIVADOS);
	}

	public String getFechaDerivadosHomeDrvx() {
		Model model = HomeCache.getModel(IHomeCache.HOME_DERIVADOS_DRVX,
				homeDerivadosDrvx);
		return (String) model.asMap().get(IConstantesAtributosModelo.ESTADO_MERCADO_DERIVADOS);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#
	 * getResumenGeneralDerivadosHome()
	 */
	public String getResumenGeneralDerivadosHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_DERIVADOS,homeDerivados);
		Set<MercadoDerivados> res = (Set<MercadoDerivados>) model.asMap().get(IConstantesAtributosModelo.RESUMEN_GENERAL_DERIVADOS);
		String ret = "";
		if (res != null && !res.isEmpty()) {
			boolean primero = true;
			for (Iterator<MercadoDerivados> iterator = res.iterator(); iterator.hasNext();) {
				if (!primero) {
					ret += SEPARADOR_PRIMER_NIVEL;
				}
				MercadoDerivados mercadoDerivados = iterator.next();
				ret += "Volumen contratos " + mercadoDerivados.getContrato().split(" ")[0] + SEPARADOR_SEGUNDO_NIVEL + mercadoDerivados.getContratos();
				primero = false;
			}
		}
		return ret;
	}

	public String getResumenGeneralDerivadosHomeDrvx() {
		Model model = HomeCache.getModel(IHomeCache.HOME_DERIVADOS_DRVX,
				homeDerivadosDrvx);
		Set<MercadoDerivados> res = (Set<MercadoDerivados>) model.asMap().get(
				IConstantesAtributosModelo.RESUMEN_GENERAL_DERIVADOS);
		String ret = "";
		if (res != null && !res.isEmpty()) {
			boolean primero = true;
			for (Iterator<MercadoDerivados> iterator = res.iterator(); iterator
					.hasNext();) {
				if (!primero) {
					ret += SEPARADOR_PRIMER_NIVEL;
				}
				MercadoDerivados mercadoDerivados = iterator.next();
				ret += "Volumen contratos " + mercadoDerivados.getContrato().split(" ")[0] + SEPARADOR_SEGUNDO_NIVEL
						+ mercadoDerivados.getContratos();
				primero = false;
			}
		}
		return ret;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getDataDerivadosHome
	 * (java.lang.String)
	 */
	public String getDataDerivadosHome(String nomLista) {
		Model model = HomeCache.getModel(IHomeCache.HOME_DERIVADOS,
				homeDerivados);
		Set<Derivado> res = (Set<Derivado>) model.asMap().get(nomLista);
		String ret = "";
		if (res != null && !res.isEmpty()) {
			boolean primero = true;
			for (Iterator<Derivado> iterator = res.iterator(); iterator
					.hasNext();) {
				if (!primero) {
					ret += SEPARADOR_PRIMER_NIVEL;
				}
				Derivado derivado = iterator.next();
				boolean primerResumen = true;
				for (Iterator<DerivadoResumen> iterInterno = derivado
						.getDerivadosResumen().iterator(); iterInterno
						.hasNext();) {
					if (!primerResumen) {
						ret += SEPARADOR_SEGUNDO_NIVEL;
					}
					DerivadoResumen derivadoResumen = iterInterno.next();
					boolean primerExtendido = true;
					for (Iterator<DerivadoResumenExtendido> iterMuyInterno = derivadoResumen
							.getDerivadosExtendidos().iterator(); iterMuyInterno
							.hasNext();) {
						if (!primerExtendido) {
							ret += SEPARADOR_TERCER_NIVEL;
						}
						DerivadoResumenExtendido extendido = iterMuyInterno
								.next();
						ret += extendido.getContrato() + SEPARADOR_CUARTO_NIVEL
								+ extendido.getContratos().intValue()
								+ SEPARADOR_CUARTO_NIVEL
								+ formatearNumero(extendido.getPrecio())
								+ SEPARADOR_CUARTO_NIVEL
								+ extendido.getVariacion();
						primerExtendido = false;
					}
					primerResumen = false;
				}
				primero = false;
			}
		}
		return ret;
	}

	
	public String getDataDerivadosHomeDrvx(String nomLista) {
		Model model = HomeCache.getModel(IHomeCache.HOME_DERIVADOS_DRVX,
				homeDerivadosDrvx);
		Set<Derivado> res = (Set<Derivado>) model.asMap().get(nomLista);
		String ret = "";
		if (res != null && !res.isEmpty()) {
			boolean primero = true;
			for (Iterator<Derivado> iterator = res.iterator(); iterator
					.hasNext();) {
				if (!primero) {
					ret += SEPARADOR_PRIMER_NIVEL;
				}
				Derivado derivado = iterator.next();
				boolean primerResumen = true;
				for (Iterator<DerivadoResumen> iterInterno = derivado
						.getDerivadosResumen().iterator(); iterInterno
						.hasNext();) {
					if (!primerResumen) {
						ret += SEPARADOR_SEGUNDO_NIVEL;
					}
					DerivadoResumen derivadoResumen = iterInterno.next();
					boolean primerExtendido = true;
					for (Iterator<DerivadoResumenExtendido> iterMuyInterno = derivadoResumen
							.getDerivadosExtendidos().iterator(); iterMuyInterno
							.hasNext();) {
						if (!primerExtendido) {
							ret += SEPARADOR_TERCER_NIVEL;
						}
						DerivadoResumenExtendido extendido = iterMuyInterno
								.next();
						ret += extendido.getContrato() + SEPARADOR_CUARTO_NIVEL
								+ extendido.getContratos().intValue()
								+ SEPARADOR_CUARTO_NIVEL
								+ formatearNumero(extendido.getPrecio())
								+ SEPARADOR_CUARTO_NIVEL
								+ extendido.getVariacion();
						primerExtendido = false;
					}
					primerResumen = false;
				}
				primero = false;
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getDataRentaFijaHome
	 * ()
	 */
	public String getDataRentaFijaHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_RENTA_FIJA,
				homeRentaFija);
		RentaFijaResumen res = (RentaFijaResumen) model.asMap()
				.get(IConstantesAtributosModelo.DATOS_RENTA_FIJA);
		String ret = "";
		if (res != null) {
			if (res.getEspecies5Publica() != null
					&& !res.getEspecies5Publica().isEmpty()) {
				boolean primeraEspecie = true;
				for (Iterator<RentaFijaResumenEspecie> iter = res
						.getEspecies5Publica().iterator(); iter.hasNext();) {
					if (!primeraEspecie) {
						ret += SEPARADOR_SEGUNDO_NIVEL;
					}
					RentaFijaResumenEspecie especie = iter.next();
					ret += especie.getNemotecnico() + SEPARADOR_TERCER_NIVEL
							+ formatearNumero(especie.getVolumen());
					primeraEspecie = false;
				}
			}
			if (res.getEspecies5Privada() != null
					&& !res.getEspecies5Privada().isEmpty()) {
				ret += SEPARADOR_PRIMER_NIVEL;
				boolean primeraEspecie = true;
				for (Iterator<RentaFijaResumenEspecie> iter = res
						.getEspecies5Privada().iterator(); iter.hasNext();) {
					if (!primeraEspecie) {
						ret += SEPARADOR_SEGUNDO_NIVEL;
					}
					RentaFijaResumenEspecie especie = iter.next();
					ret += especie.getNemotecnico() + SEPARADOR_TERCER_NIVEL
							+ formatearNumero(especie.getVolumen());
					primeraEspecie = false;
				}
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#getFechaRentaFijaHome
	 * ()
	 */
	public String getFechaRentaFijaHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_RENTA_FIJA,
				homeRentaFija);
		return (String) model.asMap().get(IConstantesAtributosModelo.HORARIO_RENTA_FIJA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeco.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#
	 * getVolumenesRentafijaHome()
	 */
	public String getVolumenesRentafijaHome() {
		Model model = HomeCache.getModel(IHomeCache.HOME_RENTA_FIJA,
				homeRentaFija);
		Double volTransado = (Double) model.asMap().get(IConstantesAtributosModelo.VOLUMEN_RENTA_FIJA_TRANSADAS);
		Double volRegistrado = (Double) model.asMap()
				.get(IConstantesAtributosModelo.VOLUMEN_RENTA_FIJA_REGISTRADAS);
		String ret = formatearNumero(volTransado) + SEPARADOR_PRIMER_NIVEL + formatearNumero(volRegistrado);
		return ret;
	}

	/* (non-Javadoc)
	 * @see co.com.bvc.portal.mercados.vista.portlet.ajax.IHomeAjax#cleanCache()
	 */
	public String cleanCache() {
		try{
			HomeCache.cleanCache();
		}catch(Exception ex){
			return "false";
		}
		return "true";
	}
	
	/**
	 * Formatear numero.
	 * 
	 * @param number the number
	 * 
	 * @return the string
	 */
	private String formatearNumero(Double number){
		DecimalFormat myFormatter = new DecimalFormat(PATRON_NUMERICO);
	    return myFormatter.format(number);
	}
	
	/**
	 * Sets the home indices.
	 * 
	 * @param homeIndices the new home indices
	 */
	public void setHomeIndices(HomeIndicePortlet homeIndices) {
		this.homeIndices = homeIndices;
	}

	/**
	 * Sets the home divisas.
	 * 
	 * @param homeDivisas the new home divisas
	 */
	public void setHomeDivisas(HomeDivisasPortlet homeDivisas) {
		this.homeDivisas = homeDivisas;
	}

	/**
	 * Sets the home accion bvc.
	 * 
	 * @param homeAccionBVC the new home accion bvc
	 */
	public void setHomeAccionBVC(HomeAccionBVCPortlet homeAccionBVC) {
		this.homeAccionBVC = homeAccionBVC;
	}

	/**
	 * Sets the home acciones.
	 * 
	 * @param homeAcciones the new home acciones
	 */
	public void setHomeAcciones(HomeAccionesPortlet homeAcciones) {
		this.homeAcciones = homeAcciones;
	}
	
	/**
	 * Sets the home acciones mgc.
	 * 
	 * @param homeAccionesMGC the new home acciones mgc
	 */
	public void setHomeAccionesMGC(HomeAccionesMGCPortlet homeAccionesMGC) {
		this.homeAccionesMGC = homeAccionesMGC;
	}
	
	/**
	 * Sets the home etf mgc.
	 * 
	 * @param homeEtfsMGC the new home etfs mgc 
	 */
	public void setHomeEtfsMGC(HomeEtfsMGCPortlet homeEtfsMGC) {
		this.homeEtfsMGC = homeEtfsMGC;
	}

	/**
	 * Sets the home derivados.
	 * 
	 * @param homeDerivados the new home derivados
	 */
	public void setHomeDerivados(HomeDerivadosPortlet homeDerivados) {
		this.homeDerivados = homeDerivados;
	}

	public void setHomeDerivadosDrvx(HomeDerivadosPortletDrvx homeDerivadosDrvx) {
		this.homeDerivadosDrvx = homeDerivadosDrvx;
	}
	
	/**
	 * Sets the home renta fija.
	 * 
	 * @param homeRentaFija the new home renta fija
	 */
	public void setHomeRentaFija(HomeRentaFijaPortlet homeRentaFija) {
		this.homeRentaFija = homeRentaFija;
	}

}
