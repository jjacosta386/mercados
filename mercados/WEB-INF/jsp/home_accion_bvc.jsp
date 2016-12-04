<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type='text/javascript' src='/mercados/js/accionBVCHome.js'></script>
<script type="text/javascript">
	var cerradoAccionBVC = true;
	manejarAcordeonAccionBVC = function(){
                if ( "acordeon_resumen_activo" ==
                     document.getElementById('titulo_5').getAttribute("class")
                    || "acordeon_resumen_activo" ==
                     document.getElementById('titulo_5').getAttribute("className")
                    )
                {
                mostrar_segmento_acordeon_inactivo('titulo_5');
                }
                else {
                mostrar_segmento_acordeon_activo('titulo_5');
                }
 
		MM_effectBlind('seg_res_5', 500, '0%', '100%', true);
		ele = document.getElementById('graficoBVC');
		if (!cerradoAccionBVC){
			ele.style.display = 'none';
			cerradoAccionBVC = true;
		}else{
			ele.style.display = 'block';
			cerradoAccionBVC = false;
		}
	}
	setTimeout("recargarHomeAccionBVC()", TIEMPO_RECARGA);
</script>



<div class="acordeon_titulo_interior">
	<div id="titulo_5" class="acordeon_resumen_inactivo" onclick="manejarAcordeonAccionBVC();">
		<div class="resumen_mercado_titulo_azul">Acción </div>
		<div class="resumen_mercado_titulo_rojo">BVC</div>
	</div>
	<div class="contenedor_tabla">
		<div id="textoFechaAccionBVCHome" class="resumen_fecha" style="padding-left: 5px; color: rgb(112, 112, 112);">
			<strong><c:out value="${fechaAccionBVC}" /></strong>
		</div>
		<div class="resumen_info_linea" style="padding-right: 5px;">
			<strong>Información en línea</strong>
		</div>
	</div>
	<div class="contenedor_tabla">
		<table class="resumen_tabla" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<td style="border-right: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(239, 237, 238);">Volumen</td>
					<td width="50" style="border-right: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(239, 237, 238);">Último precio</td>
					<td style="padding: 5px; background-color: rgb(239, 237, 238);">Variación</td>
				</tr>
			</thead>
			<tbody>
				<tr class="resumen_indices_tabla_datos_fila_resaltada" style="background-color: rgb(255, 255, 255);">
					<td id="textoVolumenAccionBVC" align="center" style="background-color: rgb(255, 255, 255);">
						<fmt:formatNumber pattern="###,##0.00">
							<c:out value="${accionBVC.volumen}" />
						</fmt:formatNumber>
					</td>
					<td id="textoPrecioAccionBVC" align="center" style="background-color: rgb(255, 255, 255);">
						<fmt:formatNumber pattern="###,##0.00">
							<c:out value="${accionBVC.precioCierre}" />
						</fmt:formatNumber>
					</td>
					<td id="textoVariacionAccionBVC" align="right" style="padding-right: 5px; background-color: rgb(255, 255, 255);">
						<fmt:formatNumber pattern="###,##0.00">
							<c:out value="${accionBVC.variacion}" />
						</fmt:formatNumber>%
						<c:choose>
							<c:when test="${accionBVC.variacion gt 0}">
								<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif" alt="Sube"/>
							</c:when>
							<c:when test="${accionBVC.variacion lt 0}">
								<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif" alt="Baja"/>
							</c:when>
							<c:otherwise>
								<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual"/>								
							</c:otherwise>
						</c:choose>
					</td>
				</tr>	
			</tbody>
		</table>
	</div>
</div>
<div id="seg_res_5" class="acordeon_interior_contenido">
	<div class="acordeon_titulo_contenedor_resumen_mercado">
		<div class="contenedor_tabla">
			<div class="resumen_fecha" style="padding-left: 5px; color: rgb(112, 112, 112);">Precio cierre anterior:</div>
			<div class="resumen_info_linea" style="padding-right: 5px;">
				<fmt:formatNumber pattern="###,###.00">
					<c:out value="${accionBVC.precioCierreAnterior}" />
				</fmt:formatNumber>
			</div>
		</div>
		<div class="contenedor_tabla" id="graficoBVC">
				<script type="text/javascript">
					var graficoBvc = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf", "amstockBVC", "248", "175", "8", "#FFFFFF");
					
					graficoBvc.addVariable("path", "");
					graficoBvc.addVariable("settings_file",
							escape("/mercados/GraficosServlet?conf=BVC&url=/amstock/settings/bvc_stock_settings.xml&tipoG=BVC&home=SI"));
					graficoBvc.addVariable("chart_id", "amstockBVC");
					graficoBvc.addVariable("preloader_color", "#999999");
					licenciaGrafica(graficoBvc);
					graficoBvc.write('graficoBVC');
				</script>
					
		</div>
		<div class="resumen_periodo" style="padding-left: 5px;">
			<label>
				&nbsp;
			</label>
			<div class="resumen_informacion_detallada" style="padding-right: 5px;">
				<a href="/pps/tibco/portalbvc/Home/Mercados/enlinea/acciones?com.tibco.ps.pagesvc.action=portletAction&action=detalleAccion&nemoTecnico=BVC&tipoMercado=1">Información detallada</a>
			</div>
		</div>
	</div>
</div>
<br clear="all"/>
