<%@ include file="includeCalendario.jsp"%>
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

<c:if test="${empty nemoTecnico}">
	<div class="titulo_seccion_unacolumna" id="text_2">
	<div class="subtitulo_mercados">
		Acci&oacute;n 
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
<c:if test="${not empty nemoTecnico}">
<div class="titulo_seccion_unacolumna" id="text_2">
	<div class="subtitulo_mercados">
		Acci&oacute;n <b><c:out value="${nemoTecnico}" /></b>
	</div>
	<div class="subtitulo_mercados_pequeno"><c:out value="${horarioAcciones}" /></div>
</div>

<div id="nota_titulo">
<h3>&nbsp;</h3>
<h2 id="texto_9">Informaci&oacute;n con 20 minutos de retraso</h2>
</div>

<br clear="all" />

<div class="grafica_mercado_accionistas">

	<div class="tabber" title="gráfica">
		<div class="tabbertab"  title="Diaria">
			<div id="texto_grafica_dia" style="height: 100%">
				<script
					type="text/javascript">
					var accionGraficoDia = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf",
							"amstock", "100%", "370", "8", "#FFFFFF");
					accionGraficoDia.addVariable("path", "");
					accionGraficoDia.addParam("wmode","opaque");
					accionGraficoDia
							.addVariable(
									"settings_file",
									escape("/mercados/GraficosServlet?conf=<c:out value='${titulo.nemotecnico}'/>&url=/amstock/settings/acciones_hoy_stock_settings.xml&tipoG=ACCION&tiempo=hoy"));
					accionGraficoDia.addVariable("chart_id", "amstockDia");
					accionGraficoDia.addVariable("preloader_color", "#999999");
					licenciaGrafica(accionGraficoDia);
					accionGraficoDia.write('texto_grafica_dia');
				</script>
			</div>
		</div>
		<div class="tabbertab"  title="Histórica">
			<div id="texto_grafica_hist" style="height: 100%">
				<script
					type="text/javascript">
					var accionGraficoHist = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf",
							"amstock", "100%", "370", "8", "#FFFFFF");
					accionGraficoHist.addVariable("path", "");
					accionGraficoHist.addParam("wmode","opaque");
					accionGraficoHist
							.addVariable(
									"settings_file",
									escape("/mercados/GraficosServlet?conf=<c:out value='${titulo.nemotecnico}'/>&url=/amstock/settings/acciones_stock_settings.xml&tipoG=ACCION&tiempo=ayer"));
					accionGraficoHist.addVariable("chart_id", "amstock");
					accionGraficoHist.addVariable("preloader_color", "#999999");
					licenciaGrafica(accionGraficoHist);
					accionGraficoHist.write('texto_grafica_hist');
				</script>
			</div>
		</div>
	</div>
</div>
<div id="texto_1" class="piedegrafica"><strong>&middot;</strong> Corresponde a
informaci&oacute;n de operaciones de compraventa</div>
<%request.getSession().getAttribute("Comparacion");%>

</c:if>
<br clear="all" />

<div class="contenedor_acordeon_borde">
<div class="contenedor_acordeon_sin_borde">
<div id="acordeon_contenedor">


<div class="acordeon_titulo_interior">
<div class="acordeon_titulo_segmento">
<p id="texto_23" style="width: 400px">Resumen del mercado - Compraventas</p>
<div id="text_ver_seg_1" class="ver_detalles_bvc" style="display: none;"
	onclick="manejarAcordeon(1, 'detallar');">Ver detalles</div>
<div id="text_cerrar_seg_1" class="cerrar_detalle_bvc"
	style="display: block;" onclick="manejarAcordeon(1, 'cerrar');">
cerrar</div>
</div>
</div>
<div class="acordeon_interior_contenido" id="seg_acc_1"
	style="display: block;">
<div class="acordeon_titulo_contenedor_resumen">
<table cellpadding="0" cellspacing="0" class="tabla_basica"
	id="texto_24">
	<caption id="texto_30">Ultima Marcaci&oacute;n: <c:out
		value="${horaTransaccion}" /></caption>
	<tbody>
		<tr>
			<th>Nombre</th>
			<th>Cantidad</th>
			<th>Volumen</th>
			<th>Precio Cierre</th>
			<th>Precio Cierre Anterior</th>
			<th>Precio Mayor</th>
			<th>Precio Medio</th>
			<th>Precio Menor</th>
			<th width="80">Variación %</th>
			<th>Variación Absoluta</th>
		</tr>
		<tr>
			<th class="specblue" style="padding-left:3px; padding-right:3px;"><c:out value="${titulo.nemotecnico}" /></th>
			<td align="right" style="padding-left:3px; padding-right:3px;"><fmt:formatNumber pattern="###,##0.00">
				<c:out value="${titulo.cantidad}" />
			</fmt:formatNumber></td>
			<td align="right" style="padding-left:3px; padding-right:3px;"><fmt:formatNumber pattern="###,##0.00">
				<c:out value="${titulo.volumen}" />
			</fmt:formatNumber></td>
			<td align="right" style="padding-left:3px; padding-right:3px;"><c:if  test="${titulo.precioCierre ne 0.0}"><fmt:formatNumber pattern="###,##0.00">
				<c:out value="${titulo.precioCierre}" />
			</fmt:formatNumber></c:if></td>
			<td align="right" style="padding-left:3px; padding-right:3px;"><c:if  test="${titulo.precioCierreAnterior ne 0.0}"><fmt:formatNumber pattern="###,##0.00">
				<c:out value="${titulo.precioCierreAnterior}" />
			</fmt:formatNumber></c:if></td>
			<td align="right" style="padding-left:3px; padding-right:3px;"><fmt:formatNumber pattern="###,##0.00">
				<c:out value="${titulo.precioMayor}" />
			</fmt:formatNumber></td>
			<td align="right" style="padding-left:3px; padding-right:3px;"><fmt:formatNumber pattern="###,##0.00">
				<c:out value="${titulo.precioMedio}" />
			</fmt:formatNumber></td>
			<td align="right" style="padding-left:3px; padding-right:3px;"><fmt:formatNumber pattern="###,##0.00">
				<c:out value="${titulo.precioMenor}" />
			</fmt:formatNumber></td>
			<td align="right" style="padding-left:3px; padding-right:3px;"><fmt:formatNumber pattern="###,##0.00">
				<c:out value="${titulo.variacion}" />
			</fmt:formatNumber>%
			<c:choose>
				<c:when test="${titulo.variacion lt 0}">
					<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/reddown.gif" alt="Baja" style="margin-left:3px;"/>
				</c:when>
				<c:when test="${titulo.variacion eq 0}">
					<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual" style="margin-left:3px;"/>
				</c:when>
				<c:otherwise>
					<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/greenup.gif" alt="Sube" style="margin-left:3px;"/>
				</c:otherwise>
			</c:choose>
			</td>
			<td align="right" style="padding-left:3px; padding-right:3px;"><fmt:formatNumber pattern="###,##0.00">
				<c:out value="${titulo.varAbsoluta}" />
			</fmt:formatNumber>
			<c:choose>
				<c:when test="${titulo.varAbsoluta>0}">
					<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif" alt="Sube" style="margin-left:3px;"/>
				</c:when>
				<c:when test="${titulo.varAbsoluta<0}">
					<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif" alt="Baja" style="margin-left:3px;"/>
				</c:when>
				<c:otherwise>
					<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual" style="margin-left:3px;"/>					
				</c:otherwise>
			</c:choose>				
			</td>
		</tr>
	</tbody>
</table>
<br clear="all" />
<div class="menu_tabla">
<%@ include file="/WEB-INF/jsp/accionesDetalleDescarga.jsp"%>
</div>
</div>
</div>
<c:choose>
	<c:when test="${tipoMercado==null || tipoMercado == 1}">
		<%@ include file="/WEB-INF/jsp/accionesDetalleCV.jsp"%>
	</c:when>
	<c:when test="${tipoMercado == 2}">
		<%@ include file="/WEB-INF/jsp/accionesDetalleRepos.jsp"%>
	</c:when>
	<c:otherwise>
		<%@ include file="/WEB-INF/jsp/accionesDetalleTTVS.jsp" %>
	</c:otherwise>
</c:choose> 

<br clear="all" />
<div class="acordeon_titulo_interior">
<div class="acordeon_titulo_segmento">
<p id="texto_28">Caracter&iacute;sticas de la Acci&oacute;n</p>
<div id="text_ver_seg_3" class="ver_detalles_bvc"
	onclick="manejarAcordeon(3, 'detallar');" style="display: block;">
Ver detalles</div>
<div id="text_cerrar_seg_3" class="cerrar_detalle_bvc"
	onclick="manejarAcordeon(3, 'cerrar'); " style="display: none;">
cerrar</div>
</div>
</div>
<br clear="all" />
<div class="accordion_content" id="seg_acc_3" style="display: none;">
<div class="acordeon_titulo_contenedor_resumen">
<table id="texto_31" width="100%" border="0" cellspacing="0"
	cellpadding="0" class="detalles_texto_tabla_accionistas">
	<tr>
		<td class="detalles_accionista_td1">Nemot&eacute;cnico:</td>
		<td class="detalles_accionista_td5"><c:out value="${nemoTecnico}" /></td>
		<td class="detalles_accionista_td1">Liquidez:</td>
		<td class="detalles_accionista_td2"><c:choose>
			<c:when test="${titulo.bursatilidad == 'A'}">
										Alta
									</c:when>
			<c:when test="${titulo.bursatilidad == 'M'}">
										Media
									</c:when>
			<c:otherwise>
										Baja
									</c:otherwise>
		</c:choose></td>
	</tr>
	<tr>
		<td class="detalles_accionista_td1">Estado:</td>
		<td class="detalles_accionista_td2"><c:choose>
			<c:when test="${titulo.estado == '0'}">
										Activo
									</c:when>
			<c:otherwise>
										Inactivo
									</c:otherwise>
		</c:choose></td>
		<td class="detalles_accionista_td1">Valor nominal:</td>
		<td class="detalles_accionista_td2"><c:out
			value="${titulo.valorNominal}" /></td>
	</tr>
	<tr>
		<td class="detalles_accionista_td1">Condici&oacute;n mercado:</td>
		<td class="detalles_accionista_td2">Libre</td>
		<td class="detalles_accionista_td1">Precio base:</td>
		<td class="detalles_accionista_td2"><c:out
			value="${titulo.precioBase}" /></td>
	</tr>
	<tr>
		<td class="detalles_accionista_td1">En circulaci&oacute;n:</td>
		<td class="detalles_accionista_td2"><fmt:formatNumber
			pattern="###,###.##">
			<c:out value="${titulo.accionesCirculacion}" />
		</fmt:formatNumber></td>
		<td class="detalles_accionista_td1">Precio m&iacute;nimo:</td>
		<td class="detalles_accionista_td2"><c:out
			value="${titulo.precioMinimo}" /></td>
	</tr>
	<tr>
		<td class="detalles_accionista_td1">C&oacute;digo Supervalores:</td>
		<td class="detalles_accionista_td2"><c:out
			value="${titulo.codigoSuper}" /></td>
		<td class="detalles_accionista_td1">Precio m&aacute;ximo:</td>
		<td class="detalles_accionista_td2"><c:out
			value="${titulo.precioMaximo}" /></td>
	</tr>
	<tr>
		<td class="detalles_accionista_td1">ISIN:</td>
		<td class="detalles_accionista_td2"><c:out value="${titulo.isin}" /></td>
		<td class="detalles_accionista_td1">Dividendos:</td>
		<td class="detalles_accionista_td2" style="width: 40%;"><c:out
			value="${titulo.dividendos}" /></td>
	</tr>
	<tr>
		<td class="detalles_accionista_td1">QTOBIN:</td>
		<td class="detalles_accionista_td2"><fmt:formatNumber
			pattern="###,###.####">
			<c:out value="${titulo.qtobin}" />
		</fmt:formatNumber></td>
		<td class="detalles_accionista_td1">RPG:</td>
		<td class="detalles_accionista_td2"><fmt:formatNumber
			pattern="###,###.####">
			<c:out value="${titulo.rpg}" />
		</fmt:formatNumber></td>
	</tr>
</table>
</div>
</div>
<div class="acordeon_titulo_interior">
<div class="acordeon_titulo_segmento">
<p id="texto_29">Informaci&oacute;n del Emisor</p>
<div id="text_ver_seg_4" class="ver_detalles_bvc"
	onclick="manejarAcordeon(4, 'detallar');" style="display: block;">
Ver detalles</div>
<div id="text_cerrar_seg_4" class="cerrar_detalle_bvc"
	onclick="manejarAcordeon(4, 'cerrar');" style="display: none;">
cerrar</div>
</div>
</div>
<div id="seg_acc_4" class="acordeon_interior_contenido"
	style="display: none;">
<div class="acordeon_titulo_contenedor_resumen">
<table id="texto_32" width="100%" border="0" cellspacing="0"
	cellpadding="0" class="detalles_texto_tabla_accionistas">
	<tbody>
		<tr>
			<td class="detalles_accionista_td4" align="center">RAZÓN SOCIAL</td>
			<td class="detalles_accionista_td2"><c:out
				value="${titulo.emisor.razonSocial}" /></td>
			<td class="detalles_accionista_td4" align="center">NOMBRE DEL
			EMISOR (SIGLA)</td>
			<td class="detalles_accionista_td2"><c:out
				value="${titulo.emisor.nombre} (${titulo.emisor.sigla})" /></td>
		</tr>
		<tr>
			<td class="detalles_accionista_td4" align="center">NIT</td>
			<td class="detalles_accionista_td2"><c:out
				value="${titulo.emisor.nit}" /></td>
			<td class=" detalles_accionista_td4" align="center">SECTOR</td>
			<td class="detalles_accionista_td2"><c:out
				value="${titulo.emisor.sector}" /></td>
		</tr>
		<tr>
			<td class="detalles_accionista_td4" align="center">PRESIDENTE</td>
			<td class="detalles_accionista_td2"><c:out
				value="${titulo.emisor.presidente}" /></td>
			<td class=" detalles_accionista_td4" align="center">DIRECCIÓN</td>
			<td class="detalles_accionista_td2"><c:out
				value="${titulo.emisor.direccion}" /></td>
			</td>
		</tr>
		<tr>
			<td class="detalles_accionista_td4" align="center">CIUDAD</td>
			<td class="detalles_accionista_td2"><c:out
				value="${titulo.emisor.ciudad}" /></td>
			<td class=" detalles_accionista_td4" align="center">SITUACIÓN</td>
			<td class="detalles_accionista_td2"><c:choose>
				<c:when test="${titulo.emisor.situacion == 'A'}">
											Abierto
										</c:when>
				<c:otherwise>
											Cerrado
										</c:otherwise>
			</c:choose></td>
		</tr>
		<tr>
			<td class="detalles_accionista_td4" align="center">TELEFONO</td>
			<td class="detalles_accionista_td2"><c:out
				value="${titulo.emisor.telefono}" /></td>
			<td class=" detalles_accionista_td4" align="center">FAX</td>
			<td class="detalles_accionista_td2"><c:out
				value="${titulo.emisor.fax}" /></td>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
</div>
</div>


<!-- div class="contenedor_acordeon_borde">
<table width="90" border="0" align="center" cellpadding="0"
	cellspacing="10">
	<tr>
		<td><div class="boton_regular"><a href="#">Imprimir</a></div>
		</td>
	</tr>
</table>
</div-->
</div>

<br clear="all" />
<br clear="all" />
