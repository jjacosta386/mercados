<%@ include file="includeCalendario.jsp"%>
<script type='text/javascript' src='/mercados/js/tab/tabber.js'></script>
<link rel="stylesheet" href="/mercados/css/tabs/example.css" TYPE="text/css" MEDIA="screen">
<script type='text/javascript' src='/mercados/dwr/interface/rentaFijaAjax.js'></script>




<portlet:actionURL var="formAction">
	<portlet:param name="action" value="buscar" />
</portlet:actionURL>
<!-- InstanceBeginEditable name="area_trabajo_central" -->

<script type="text/javascript">

function timerIntradiaEnJSP() {
}


var array;
jQuery(document).ready
(       

        function( ) 
	{
		rentaFijaAjax.getNemotecnicos( getAnswer );
	}
);


        var getAnswer = function( answer )
        {
        array = answer;
        jQuery("#renta_detalle3").autocompleteArray ( array, { delay:10, minChars:1, matchSubset:1, onItemSelect:selectItem, onFindValue:findValue, autoFill:true, maxItemsToShow:10 } );
        }

function MM_changeProp(objId,x,theProp,theValue) { //v9.0
  var obj = null; with (document){ if (getElementById)
  obj = getElementById(objId); }
  if (obj){
    if (theValue == true || theValue == false)
      eval("obj.style."+theProp+"="+theValue);
    else eval("obj.style."+theProp+"='"+theValue+"'");
  }
}


//-->
</script>
<div class="tab_contenedor_mercados" style="float:left; display:block; position:relative; z-index:100">
  <div class="tab_contenido_mercados">        
         
      <form id="formulario" method="post" name="forma"	action="<%=formAction%>" >
     	<div id="renta" class="panelactivo_merc_divisas">
      
          <ul class="mercados">
            <li>
              <label><span id="text_6">Tipo de mercado :</span>
              <select name="tipoMercado" size="1" class="operacion" id="renta_detalle1">
              	<option value="%" selected="selected">Todos</option>
          		<option 
					<c:if test="${RentaFijaBusqueda.tipoMercado eq 'T'}"> 
						selected="selected"
					</c:if> 
					value="T" >
					Transaccional
				</option>  
         		<option 
					<c:if test="${RentaFijaBusqueda.tipoMercado eq 'R'}"> 
						selected="selected"
					</c:if> value="R">
					Registro
				</option>        
               </select>
            </label>
            </li>
            <li class="fechaneg">
            <label>
              <span id="text_7">Fecha de negociación :</span>
              <span class="fechalista">
              <select name="diaFecha" size="1" id="derivados_dia">
                                                        <c:forEach items="${dias}" var="dia">
                                                            <option <c:if test="${RentaFijaBusqueda.diaFecha==dia.key}">selected="selected"</c:if> value='<c:out value="${dia.key}"/>'><c:out value="${dia.value}"/></option>
                                                        </c:forEach>
              </select>
            </span>
            </label>
            <label>
            <span class="fechalista">
            <select name="mesFecha" size="1" id="derivados_mes">
                                                        <c:forEach items="${meses}" var="mes">
                                                            <option <c:if test="${RentaFijaBusqueda.mesFecha==mes.key}">selected="selected"</c:if> value='<c:out value="${mes.key}"/>'><c:out value="${mes.value}"/></option>
                                                        </c:forEach>
            </select>
            </span>
            </label>
            <label>
            <span class="fechalista">
            <select name="anioFecha" size="1" id="derivados_ano">
                                                        <c:forEach items="${anios}" var="anio">
                                                            <option <c:if test="${RentaFijaBusqueda.anioFecha==anio.key}">selected="selected"</c:if> value='<c:out value="${anio.key}"/>'><c:out value="${anio.value}"/></option>
                                                        </c:forEach>
            </select>
            </span>
            </label>
            </li>
            <li>
              <label><span id="text_9">Tipo de operación :</span>
              <select name="tipoOperacion" size="1" class="operacion" id="renta_detalle2">
              <option value="%" selected="selected">Todos</option>
								                <option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'CV'}">selected="selected"</c:if> value="CV">Compraventas</option>
								                <option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'RR'}">selected="selected"</c:if> value="RR">Repos</option>
								                <option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'SI'}">selected="selected"</c:if> value="SI">Simultaneas</option>
								                <option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'TR'}">selected="selected"</c:if> value="TR">TTV</option>
								                <option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'CA'}">selected="selected"</c:if> value="CA">Carrusel</option>
								                <option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'SW'}">selected="selected"</c:if> value="SW">Swap</option>
								                <option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'PL'}">selected="selected"</c:if> value="PL">Plazo</option>								          	
              </select></label></li>
            <li style="width:350px">
              <label><span id="text_10">Sesión de negociación :</span>
			  <select name="tipoSesion" size="1" class="operacion" id="renta_detalle2" style="width:170px">
              <option value="TODAS" selected="selected">Todos</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'REG'}">selected="selected"</c:if> value="REG">REG</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'TRD'}">selected="selected"</c:if> value="TRD">TRD</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'REPI'}">selected="selected"</c:if> value="REPI">REPI</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'SPOT'}">selected="selected"</c:if> value="SPOT">SPOT</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'SOLO'}">selected="selected"</c:if> value="SOLO">SOLO</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'RYS'}">selected="selected"</c:if> value="RYS">RYS</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'BALO'}">selected="selected"</c:if> value="BALO">BALO</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'DEXT'}">selected="selected"</c:if> value="DEXT">DEXT</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'COTI'}">selected="selected"</c:if> value="COTI">COTI</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'TTV'}">selected="selected"</c:if> value="TTV">TTV</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'DEES'}">selected="selected"</c:if> value="DEES">DEES</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'LIQ'}">selected="selected"</c:if> value="LIQ">LIQ</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'RYSE'}">selected="selected"</c:if> value="RYSE">RYSE</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'SUBA'}">selected="selected"</c:if> value="SUBA">SUBA</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'DSER'}">selected="selected"</c:if> value="DSER">DSER</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'TTVP'}">selected="selected"</c:if> value="TTVP">TTVP</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'SERI'}">selected="selected"</c:if> value="SERI">SERI*</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'CONT'}">selected="selected"</c:if> value="CONT">CONT*</option>
								              <option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'INVE'}">selected="selected"</c:if> value="INVE">INVE*</option>
              </select><b class="link"><a href="#">(?)<span style="width:1000px;">    
									  <h2>Transaccional:</h2>
						          <h3>Deuda Pública de Orden Nacional</h4>
						                <h4>SPOT  : Sobre Lote T=0</h4>
						                <h4>SOLO  : Sobre Lote plazo mayor a T=0</h4>
						                <h4>RYS    : Repos y Simultáneas</h4>
						                <h4>BALO  : Bajo Lote</h4>
						                <h4>DEXT  : Deuda Externa</h4>
						                <h4>COTI  : Cotizaciones</h4>
						                <h4>TTV   : Transferencia Temporal de Valores</h4>
						          <h3>Deuda Privada y Deuda Pública de Orden No Nacional</h3>
						                <h4>DEES  : Deuda Estandarizada</h4>
						                <h4>RYSE  : Repos y Simultáneas Deuda Estandarizada</h4>
						                <h4>SUBA  : Subastas Deuda Estandarizada</h4>
						                <h4>DSER  : Remate Serializado Deuda No Estandarizada</h4>
						                <h4>TTVP  : Transferencia Temporal de Valores</h4>
						    <h2>Registro:</h2>
						          <h4>REG    : Repos, Simultáneas y TTV Registro con Confirmación</h4>
						          <h4>TRD    : C/V Registro con Confirmación</h4>
						          <h4>REPI   : Registro sin Confirmación</h4>
								  </span></a></b>
			  </label>
            </li>
             <li class="fechaneg" style="width:100%">
               <label><span id="text_11">Consultar otro titulo :</span>
               <input name="nemo" type="text" id="renta_detalle3" value="<c:out value="${RentaFijaBusqueda.nemo}"/>" />
               </label>
			   <div class="boton_regular" >
			   		<a href="javascript:document.forma.submit();">Buscar</a>
				</div>
             </li>
          </ul>

      </div>
      </form>   
  </div>	
  <div class="limpiar"></div>
</div>
<br clear="all" />
<br/> 
 <div class="titulo_seccion_unacolumna" id="text_12">Titulo <c:out value="${tituloRF.tipoTitulo}"/>
   <b><c:out value="${tituloRF.nemo}"/></b></div>
  <div id="nota_titulo">
    <h3>&nbsp;</h3>
    <h2>Informaci&oacute;n con 20 minutos de retraso</h2></div>
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
<br clear="all" />
     <div class="contenedor_acordeon_borde">
      <div class="contenedor_acordeon_sin_borde">
    <div id="acordeon_contenedor">
    <div class="acordeon_titulo_interior">
      <div class="acordeon_titulo_segmento">
        <p id="text_14">Resumen del mercado <c:out value="${tituloRF.tipoTitulo}"/></p>
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
				 	<form id="formhistorico" action="/mercados/DescargaXlsServlet" method="get" name="formhistorico">
								<input type="hidden" name="archivo" value="rentafija_detalle"/>
								<input type="hidden" name="nemo" value="<c:out value='${tituloRF.nemo}'/>"/>
								<input type="hidden" name="tipoOperacion" value="<c:out value='${RentaFijaBusqueda.tipoOperacion}'/>"/>
								<input type="hidden" name="tipoSesion" value="<c:out value='${RentaFijaBusqueda.tipoSesion}'/>"/>
								<ul id="text_19">
									<li>
										<label>
											<span id="texto_1">Descargar Historico desde: </span>
											<input type="text" name="dia1" style="width:83px"
												readonly="readonly" id="dia1" 
												onClick="return pintarCal('dia1', '%Y-%m-%d');"/>
										</label>
									</li>
									<li>
											<img  width="17" height="21" border="0" alt="cal" src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/images/calendario.gif"
												onClick="simularDesde('dia1', 1);" onMouseOver="simularDesde('dia1', 0);"
												style="cursor: pointer;"/>
									</li>
									<li>
										<label>
											<span id="texto_2">Hasta: </span>
											<input type="text" name="dia2" style="width:83px"
												readonly="readonly" id="dia2" 
												onClick="return pintarCal('dia2', '%Y-%m-%d');"/>
										</label>
									</li>
									<li>
											<img  width="17" height="21" border="0" alt="cal" src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/images/calendario.gif"
												onClick="simularHasta('dia2', 1);" onMouseOver="simularHasta('dia2', 0);"
												style="cursor: pointer;"/>
									</li>
									<li>
										<a id="texto_3" class="excel" href="#" onClick="validarFormFechas('dia1', 'dia2', 'yyyy-MM-dd', 'formhistorico', true);">Descargar </a>
									</li>
								</ul>
							</form>
				</div>
      </div>
    </div>
    <br clear="all" />
    <div class="acordeon_titulo_interior">
      <div class="acordeon_titulo_segmento">
        <p id="text_19">Operaciones </p>
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

    
    
    <!-- InstanceEndEditable -->
<!--cierre del contenedor del area de trabajo central-->



