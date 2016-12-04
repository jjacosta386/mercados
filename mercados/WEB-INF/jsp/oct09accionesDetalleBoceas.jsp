<%@ include file="includeCalendario.jsp"%>
<script type='text/javascript' src='/mercados/dwr/interface/rentaFijaAjax.js'></script>
<script type='text/javascript' src='/mercados/js/tab/tabber.js'></script>
<link rel="stylesheet" href="/mercados/css/tabs/example.css" TYPE="text/css" MEDIA="screen">
<script type='text/javascript' src='/mercados/dwr/interface/intradiaAjax.js'></script>
<script type='text/javascript' src='/mercados/dwr/interface/comparacionesAjax.js'></script>
<script type='text/javascript' src='/mercados/dwr/engine.js'></script>


<script type="text/javascript">
<!--

	
	function manejarAcordeon (numSeg, tipo){

		if (tipo == 'detallar'){
			mostrar_segmento_acordeon('seg_acc_' + numSeg); 
			mostrar_segmento_acordeon('text_cerrar_seg_' + numSeg); 
			ocultar_segmento_acordeon('text_ver_seg_' + numSeg);
			for (i = 1; i<5; i++){
				if (i != numSeg){
					manejarAcordeon(i, 'cerrar');
				}		
			}	
		}else{
			ocultar_segmento_acordeon('seg_acc_' + numSeg); 
			ocultar_segmento_acordeon('text_cerrar_seg_' + numSeg); 
			mostrar_segmento_acordeon('text_ver_seg_' + numSeg);
		}
	}

	function timerIntradiaEnJSP() {
	
	}

//-->
</script>

<%@ include file="/WEB-INF/jsp/accionesEncabezado.jsp"%>

<!-- InstanceBeginEditable name="area_trabajo_central" -->


<br clear="all" />

<c:if test="${empty tituloRF.nemo}">
	<div class="titulo_seccion_unacolumna" id="text_2">
	<div class="subtitulo_mercados">
		BOCEAS 
	</div>
	<div class="subtitulo_mercados_pequeno"><c:out value="${horarioAcciones}" /></div>
</div>

<div id="nota_titulo">
<h3>&nbsp;</h3>
<h2 id="texto_9">Informaci&oacute;n con 20 minutos de retraso</h2>
</div>
<br clear="all" />
			<div align= "center">
				<h3>&nbsp;</h3>
				<h2>No existe informaci&oacute;n suficiente de este t&iacute;tulo en la fecha para generar la gr&aacute;fica hist&oacute;rica</h2>
			</div>					
</c:if>
<c:if test="${not empty tituloRF.nemo}">
<div class="titulo_seccion_unacolumna" id="text_2">
	<div class="subtitulo_mercados">
		BOCEAS <b><c:out value="${tituloRF.nemo}" /></b>
	</div>
	<div class="subtitulo_mercados_pequeno"><c:out value="${horarioAcciones}" /></div>
</div>

<div id="nota_titulo">
<h3>&nbsp;</h3>
<h2 id="texto_9">Informaci&oacute;n con 20 minutos de retraso</h2>
</div>

<br clear="all" />

<div class="grafica_mercado_accionistas" id="graficaRF">
	<div class="tabber" title="gráfica">
		<div class="tabbertab"  title="Diaria">
			<div id="texto_grafica_dia" style="height: 100%">
				<script type="text/javascript">
					var rfGraficoDia = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf",
							"amstock", "500", "370", "8", "#FFFFFF");
					rfGraficoDia.addVariable("path", "");
					rfGraficoDia
							.addVariable(
									"settings_file",
									escape('/mercados/GraficosServlet?conf=<c:out value="${tituloRF.nemo}" />&url=/amstock/settings/rentaFija_hoy_stock_settings.xml&tipoG=RF&tiempo=hoy'));
					rfGraficoDia.addVariable("chart_id", "amstockDia");
					rfGraficoDia.addVariable("preloader_color", "#999999");
					rfGraficoDia.addParam( "wmode", "opaque" );
					licenciaGrafica(rfGraficoDia);
					rfGraficoDia.write('texto_grafica_dia');
				</script>
			</div>
		</div>
		<div class="tabbertab"  title="Histórica">
			<div id="texto_grafica_hist" style="height: 100%">
				<script type="text/javascript">
					var rfGraficoHist = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf",
							"amstock", "500", "370", "8", "#FFFFFF");
					rfGraficoHist.addVariable("path", "");
					rfGraficoHist
							.addVariable(
									"settings_file",
									escape('/mercados/GraficosServlet?conf=<c:out value="${tituloRF.nemo}" />&url=/amstock/settings/rentaFija_stock_settings.xml&tipoG=RF&tiempo=ayer'));
					rfGraficoHist.addVariable("chart_id", "amstockHist");
					rfGraficoHist.addVariable("preloader_color", "#999999");
					rfGraficoHist.addParam( "wmode", "opaque" );
					licenciaGrafica(rfGraficoHist);
					rfGraficoHist.write('texto_grafica_hist');
				</script>
			</div>
		</div>
	</div>
</div>
</c:if>
<br clear="all" />
<div class="contenedor_acordeon_borde">
      <div class="contenedor_acordeon_sin_borde">
    <div id="acordeon_contenedor">
    <div class="acordeon_titulo_interior">
      <div class="acordeon_titulo_segmento">
        <p id="text_14">Resumen de mercado BOCEA</p>
        <div id="text_ver_seg_1" class="ver_detalles_bvc" onclick="
        mostrar_segmento_acordeon('seg_acc_1');
        mostrar_segmento_acordeon('text_cerrar_seg_1');
        ocultar_segmento_acordeon('text_ver_seg_1');
        ocultar_segmento_acordeon('seg_acc_2');
        ocultar_segmento_acordeon('seg_acc_3');
        ocultar_segmento_acordeon('seg_acc_4');
        ocultar_segmento_acordeon('text_cerrar_seg_2');
        ocultar_segmento_acordeon('text_cerrar_seg_3');
        ocultar_segmento_acordeon('text_cerrar_seg_4');
        mostrar_segmento_acordeon('text_ver_seg_2');
        mostrar_segmento_acordeon('text_ver_seg_3');        
        mostrar_segmento_acordeon('text_ver_seg_4');        

        "> Ver detalles</div>
        <div id="text_cerrar_seg_1" class="cerrar_detalle_bvc" onclick="
        ocultar_segmento_acordeon('seg_acc_1');
        ocultar_segmento_acordeon('text_cerrar_seg_1');
        mostrar_segmento_acordeon('text_ver_seg_1');
        "> cerrar </div>
      </div>
    </div>
    <div id="seg_acc_1" class="acordeon_interior_contenido" style="display:block">
      <div class="acordeon_titulo_contenedor_resumen">
        <table cellpadding="0" cellspacing="0" class="tabla_basica" id="text_15">
          <caption>
            &Uacute;ltima operaci&oacute;n de Compraventa: <c:out value="${sumaRF.fechaUltimaOperacionFormat}"/>
          </caption>
          <tr>
            <th width="19%">NEMOT&Eacute;CNICO</th>
            <th width="8%">EMISOR</th>
            <th width="17%">CANTIDAD</th>
            <th width="13%">VOLUMEN</th>
            <th width="14%">&Uacute;LTIMO PRECIO SUCIO</th>
            <th width="11%">&Uacute;LTIMA TASA</th>
          </tr>
          <tr>
            <th class="specblue"><c:out value="${sumaRF.nemo}"/></th>
            <td align="center"><c:out value="${sumaRF.emisor}"/></td>
            <td align="center"><fmt:formatNumber pattern="###,###.##" ><c:out value="${sumaRF.cantidad}"/></fmt:formatNumber></td>
            <td align="center"><fmt:formatNumber pattern="###,###.##" ><c:out value="${sumaRF.volumen}"/></fmt:formatNumber></td>
            <td align="center"><fmt:formatNumber pattern="###.###" ><c:out value="${sumaRF.ultimoPrecioSucio}"/></fmt:formatNumber></td>
            <td align="center"><c:out value="${sumaRF.ultimaTasa}"/></td>
          </tr>
        </table>
  		        
  		        
  		        
  		        <div class="menu_tabla">
				 	<form id="formhistorico" action="/mercados/DescargaXlsServlet"	method="get" name="formhistorico">
	   					<input type="hidden" name="archivo" value="boceas_detalle" /> 
						<input type="hidden" name="nemo" value="<c:out value='${sumaRF.nemo}'/>" /> 
						<input type="hidden" name="tipoMercado"	value="<c:out value='${formulario.tipoMercado}'/>" />
						<ul id="text_19">
						<li><label> <span id="texto_1">Descargar Historico desde: </span> <input
							type="text" name="fechaIni" style="width: 83px" readonly="readonly"
							id="fechaIni"
							onClick="return pintarCal('fechaIni', '%Y-%m-%d');"/>
							</label></li>
						<li><img width="17" height="21" border="0" alt="cal"
							src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/images/calendario.gif"
							onClick="simularDesde('fechaIni', 1);" onMouseOver="simularDesde('fechaIni', 0);"
							style="cursor: pointer;" /></li>
						<li><label> <span id="texto_2">Hasta: </span> <input type="text"
							name="fechaFin" style="width: 83px" readonly="readonly" id="fechaFin"
							onClick="return pintarCal('fechaFin', '%Y-%m-%d');"/>
						</label></li>
						<li><img width="17" height="21" border="0" alt="cal"
							src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/images/calendario.gif"
							onClick="simularHasta('fechaFin', 1);" onMouseOver="simularHasta('fechaFin', 0);"
							style="cursor: pointer;" /></li>
						<li><a id="texto_3" class="excel"
							href="#" onClick="validarFormFechas('fechaIni', 'fechaFin', 'yyyy-MM-dd', 'formhistorico', true);">Descargar </a></li>
						</ul>
				</form>
				</div>
      </div>
    </div>
    <br clear="all" />
    <div class="acordeon_titulo_interior">
      <div class="acordeon_titulo_segmento">
        <p id="text_19">Operaciones BOCEA</p>
<div id="text_ver_seg_2" class="ver_detalles_bvc" onclick="
        mostrar_segmento_acordeon('seg_acc_2');
        mostrar_segmento_acordeon('text_cerrar_seg_2');
        ocultar_segmento_acordeon('text_ver_seg_2');
        ocultar_segmento_acordeon('seg_acc_1');
        ocultar_segmento_acordeon('seg_acc_3');
        ocultar_segmento_acordeon('seg_acc_4');
        ocultar_segmento_acordeon('text_cerrar_seg_1');
        ocultar_segmento_acordeon('text_cerrar_seg_3');
        ocultar_segmento_acordeon('text_cerrar_seg_4');
        mostrar_segmento_acordeon('text_ver_seg_1');
        mostrar_segmento_acordeon('text_ver_seg_3');        
        mostrar_segmento_acordeon('text_ver_seg_4');  
        "> Ver detalles</div>
        <div id="text_cerrar_seg_2" class="cerrar_detalle_bvc" onclick="
        ocultar_segmento_acordeon('seg_acc_2');
        ocultar_segmento_acordeon('text_cerrar_seg_2');
        mostrar_segmento_acordeon('text_ver_seg_2');
        "> cerrar </div>
      </div>
    </div>
    <div id="seg_acc_2" class="acordeon_interior_contenido">
      <div class="acordeon_titulo_contenedor_resumen">
      <div class="acciones_encabezado_tabla">
              <table cellpadding="0" cellspacing="0" class="tabla_basica" id="text_20">
          <!--caption>
            Ultima operaci&oacute;n: 2008-07-31  12:58 p.m.
          </caption-->
          <tr>
            <th width="40" class="renta_detalle_titulo">PRECIO SUCIO</th>
            <th width="39" class="renta_detalle_titulo">TASA</th>
    <th width="80" class="renta_detalle_titulo">CANTIDAD</th>
    <th class="renta_detalle_titulo">VOLUMEN</th>
    <th width="74" class="renta_detalle_titulo">TIPO DE OPERACION</th>
    <th width="45" class="renta_detalle_titulo">TIPO PLAZO</th>
    <th width="45" class="renta_detalle_titulo">TASA CUPON</th>
    <th width="65" class="renta_detalle_titulo">MODALIDAD PAGO</th>
    <th width="50" class="renta_detalle_titulo">FECHA EMISION</th>
    <th width="70" class="renta_detalle_titulo">FECHA VENCIMIENTO</th>
    <th width="80" class="renta_detalle_titulo">SESIÓN NEGOCIACIÓN</th>
    <th width="60" class="renta_detalle_titulo">MERCADO</th>
  </tr>
            </table>
      </div>
                  <div class="mercado_acciones_scroll">
      <div class="mercado_acciones_scroll_interior">
        <table width="748" cellpadding="0" cellspacing="0" class="tabla_basica" id="text_23">
  <c:forEach items="${operacionesRF}" var="operacion">
  <tr>
    <td width="40" class="celda_tabla_basica"><fmt:formatNumber pattern="###.###" ><c:out value="${operacion.precioSucio}"/></fmt:formatNumber></td>
    <td width="39" class="celda_tabla_basica"><c:out value="${operacion.tasa}"/></td>
    <td width="80" class="celda_tabla_basica"><fmt:formatNumber pattern="###,###.##" ><c:out value="${operacion.cantidad}"/></fmt:formatNumber></td>
    <td class="celda_tabla_basica"><fmt:formatNumber pattern="###,###.##" ><c:out value="${operacion.volumen}"/></fmt:formatNumber></td>
    <td width="74" class="celda_tabla_basica"><c:if test="${operacion.tipoOperacion eq 'CV'}">Compraventas</c:if>
								                <c:if test="${operacion.tipoOperacion eq 'RR'}">Repos</c:if>
								                <c:if test="${operacion.tipoOperacion eq 'SI'}">Simultaneas</c:if>
								                <c:if test="${operacion.tipoOperacion eq 'TR'}">TTV</c:if>
								                <c:if test="${operacion.tipoOperacion eq 'CA'}">Carrusel</c:if>
								                <c:if test="${operacion.tipoOperacion eq 'SW'}">Swap</c:if>
								                <c:if test="${operacion.tipoOperacion eq 'PL'}">Plazo</c:if>								          	
								              </td>
    <td width="45" class="celda_tabla_basica"><c:out value="${operacion.tipoPlazo}"/></td>
    <td width="45" class="celda_tabla_basica"><c:out value="${operacion.tasaCupon}"/></td>
    <td width="65" class="celda_tabla_basica"><c:out value="${operacion.modalidadPago}"/></td>
    <td width="50" class="celda_tabla_basica"><c:out value="${operacion.fechaEmisionFormat}"/></td>
    <td width="70" class="celda_tabla_basica"><c:out value="${operacion.fechaVencimientoFormat}"/></td>
    <td width="80" class="celda_tabla_basica"><c:out value="${operacion.sesionNegociacion}"/></td>
    <td width="60" class="celda_tabla_basica"><c:out value="${operacion.mercado}"/></td>
  </tr>
  </c:forEach>
</table>
        </div>
        </div>
      </div>
    </div>
    <br clear="all" />
    <div class="acordeon_titulo_interior">
      <div class="acordeon_titulo_segmento">
        <p id="text_21">Caracter&iacute;sticas de la Especie</p>
<div id="text_ver_seg_3" class="ver_detalles_bvc" onclick="
        mostrar_segmento_acordeon('seg_acc_3');
        mostrar_segmento_acordeon('text_cerrar_seg_3');
        ocultar_segmento_acordeon('text_ver_seg_3');
        ocultar_segmento_acordeon('seg_acc_1');
        ocultar_segmento_acordeon('seg_acc_2');
        ocultar_segmento_acordeon('seg_acc_4');
        ocultar_segmento_acordeon('text_cerrar_seg_1');
        ocultar_segmento_acordeon('text_cerrar_seg_2');
        ocultar_segmento_acordeon('text_cerrar_seg_4');
        mostrar_segmento_acordeon('text_ver_seg_1');
        mostrar_segmento_acordeon('text_ver_seg_2');        
        mostrar_segmento_acordeon('text_ver_seg_4');  
        "> Ver detalles</div>
        <div id="text_cerrar_seg_3" class="cerrar_detalle_bvc" onclick="
        ocultar_segmento_acordeon('seg_acc_3');
        ocultar_segmento_acordeon('text_cerrar_seg_3');
        mostrar_segmento_acordeon('text_ver_seg_3');
        "> cerrar </div>
      </div>
    </div>
    <div id="seg_acc_3" class="acordeon_interior_contenido">
      <div class="acordeon_titulo_contenedor_resumen">
        <table id="text_24" width="100%" border="0" cellspacing="0" cellpadding="0" class="detalles_texto_tabla_accionistas">
          <tr>
            <td class="detalles_accionista_td1">Nemot&eacute;cnico:</td>
            <td class="detalles_accionista_td5"><c:out value="${tituloRF.nemo}"/></td>
            <td class="detalles_accionista_td1">C&oacute;digo Superfinanciera:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.codSuper}"/></td>
          </tr>
          <tr>
            <td class="detalles_accionista_td1">Tipo de T&iacute;tulo</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.tipoTitulo}"/></td>
            <td class="detalles_accionista_td1">ISIN:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.isin1}"/></td>
          </tr>
          <tr>
            <td class="detalles_accionista_td1">Emisor:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.emisor}"/></td>
            <td class="detalles_accionista_td1">Fecha Emisi&oacute;n:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.fechaEmisionFormat}"/></td>
          </tr>
          <tr>
            <td class="detalles_accionista_td1">Fecha Vencimiento:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.fechaVencimientoFormat}"/></td>
            <td class="detalles_accionista_td1">Modalidad de Pago:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.modalidadPago}"/></td>
          </tr>
          <tr>
            <td class="detalles_accionista_td1">Estado:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.estado}"/></td>
            <td class="detalles_accionista_td1">Moneda:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.moneda}"/></td>
          </tr>
          <tr>
            <td class="detalles_accionista_td1">Tasa de Referencia:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.tasaReferencia}"/></td>
            <td class="detalles_accionista_td1">Tasa Cup&oacute;n:</td>
            <td class="detalles_accionista_td2"><c:out value="${tituloRF.tasaCupon}"/></td>
          </tr>
        </table>
      </div>
    </div>
    <br clear="all" />
    <div class="acordeon_titulo_interior">
      <div class="acordeon_titulo_segmento">
        <p id="text_22">Informaci&oacute;n del Emisor</p>
<div id="text_ver_seg_4" class="ver_detalles_bvc" onclick="
        mostrar_segmento_acordeon('seg_acc_4');
        mostrar_segmento_acordeon('text_cerrar_seg_4');
        ocultar_segmento_acordeon('text_ver_seg_4');
        ocultar_segmento_acordeon('seg_acc_1');
        ocultar_segmento_acordeon('seg_acc_3');
        ocultar_segmento_acordeon('seg_acc_2');
        ocultar_segmento_acordeon('text_cerrar_seg_1');
        ocultar_segmento_acordeon('text_cerrar_seg_3');
        ocultar_segmento_acordeon('text_cerrar_seg_2');
        mostrar_segmento_acordeon('text_ver_seg_1');
        mostrar_segmento_acordeon('text_ver_seg_3');        
        mostrar_segmento_acordeon('text_ver_seg_2');  
        "> Ver detalles</div>
        <div id="text_cerrar_seg_4" class="cerrar_detalle_bvc" onclick="
        ocultar_segmento_acordeon('seg_acc_4');
        ocultar_segmento_acordeon('text_cerrar_seg_4');
        mostrar_segmento_acordeon('text_ver_seg_4');
        "> cerrar </div>
      </div>
    </div>
    <div id="seg_acc_4" class="acordeon_interior_contenido">
      <div class="acordeon_titulo_contenedor_resumen">
        <table id="text_25" width="100%" border="0" cellspacing="0" cellpadding="0" class="detalles_texto_tabla_accionistas">
          <tr>
            <td align="center" class="detalles_accionista_td4">RAZ&Oacute;N SOCIAL</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.razonSocial}"/></td>
            <td align="center" class="detalles_accionista_td4">SIGLA</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.sigla}"/></td>
          </tr>
          <tr>
            <td align="center" class="detalles_accionista_td4">NIT</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.nit}"/></td>
            <td align="center" class="detalles_accionista_td4">SECTOR</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.sector}"/></td>
          </tr>
          <tr>
            <td align="center" class="detalles_accionista_td4">PRESIDENTE</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.presidente}"/></td>
            <td align="center" class="detalles_accionista_td4">DIRECCI&Oacute;N</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.direccion}"/></td>
            </tr>
          <tr>
            <td align="center" class="detalles_accionista_td4">CIUDAD</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.ciudad}"/></td>
            <td align="center" class="detalles_accionista_td4">SITUACI&Oacute;N DEL EMISOR</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.situacion}"/></td>
            </tr>
          <tr>
            <td align="center" class="detalles_accionista_td4">TEL&Eacute;FONO</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.telefono}"/></td>
            <td align="center" class="detalles_accionista_td4">FAX</td>
            <td class="detalles_accionista_td2"><c:out value="${emisorRF.fax}"/></td>
            </tr>
        </table>
      </div>
    </div>
    <br clear="all" />
    </div>
    </div>
    </div>