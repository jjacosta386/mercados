<script type='text/javascript' src='/mercados/js/tab/tabber.js'></script>
<link rel="stylesheet" href="/mercados/css/tabs/example.css"
	TYPE="text/css" MEDIA="screen">

<script type="text/javascript">
	function timerIntradiaEnJSP() {
		//alert("timerIntradiaIndices");
	}
</script>
<div id="text_15" class="grafica_mercado_accionistas"
	style="height: 381px">

	<div class="tabber" title="gráfica">
		<c:if test="${nombreIndice != 'IIBR'}">
			<div class="tabbertab" title="Diaria">
				<div id="texto_grafica_dia" style="height: 100%">
					<script type="text/javascript">
						var indiceGraficoDia = new SWFObject(
								"%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf",
								"amstock", "100%", "328", "8", "#FFFFFF");
						indiceGraficoDia.addVariable("path", "");
						<c:if test="${nombreMercado != 'RENTA FIJA'}">
							indiceGraficoDia
									.addVariable(
											"settings_file",
											escape("/mercados/GraficosServlet?conf=<c:out value='${nombreIndice}'/>&titulo=<c:out value='${nombreMostrarIndice}'/>&url=/amstock/settings/indice_hoy_stock_settings.xml&tipoG=INDICE&mercInd=RV&tiempo=hoy"));
						</c:if>
						<c:if test="${nombreMercado == 'RENTA FIJA'}">
							indiceGraficoDia
									.addVariable(
											"settings_file",
											escape("/mercados/GraficosServlet?conf=<c:out value='${nombreIndice}'/>&titulo=<c:out value='${nombreMostrarIndice}'/>&url=/amstock/settings/indice_hoy_stock_settings.xml&tipoG=INDICE&mercInd=RF&tiempo=hoy"));
						</c:if>					
						indiceGraficoDia.addVariable("preloader_color",
								"#999999");
						indiceGraficoDia.addVariable("chart_id", "amstockDia");
						indiceGraficoDia.addParam("wmode", "opaque");
						licenciaGrafica(indiceGraficoDia);
						indiceGraficoDia.write('texto_grafica_dia');
					</script>
				</div>
			</div>
			<div class="tabbertab" title="Histórica">
			<div id="texto_grafica_hist" style="height: 100%">
				<script type="text/javascript">
					var indiceGraficoHist = new SWFObject(
							"%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf",
							"amstock", "100%", "328", "8", "#FFFFFF");
					indiceGraficoHist.addVariable("path", "");
					<%-- Se pasan parametros al GraficoServlet.java, incluyendo el archvio de configuracion .xml que determina como graficar --%>
					<c:if test="${nombreMercado != 'RENTA FIJA'}">
							indiceGraficoHist
									.addVariable(
											"settings_file",
											escape("/mercados/GraficosServlet?conf=<c:out value='${nombreIndice}'/>&titulo=<c:out value='${nombreMostrarIndice}'/>&url=/amstock/settings/indice_stock_settings.xml&tipoG=INDICE&mercInd=RV&tiempo=ayer"));
						</c:if>
						<c:if test="${nombreMercado == 'RENTA FIJA'}">
							indiceGraficoHist
									.addVariable(
											"settings_file",
											escape("/mercados/GraficosServlet?conf=<c:out value='${nombreIndice}'/>&titulo=<c:out value='${nombreMostrarIndice}'/>&url=/amstock/settings/indice_stock_settings.xml&tipoG=INDICE&mercInd=RF&tiempo=ayer"));
						</c:if>
					indiceGraficoHist.addVariable("preloader_color", "#999999");
					indiceGraficoHist.addVariable("chart_id", "amstockHist");
					indiceGraficoHist.addParam("wmode", "opaque");
					licenciaGrafica(indiceGraficoHist);
					indiceGraficoHist.write('texto_grafica_hist');
				</script>
			</div>
		</div>
		</c:if>
		<%-- Para cuando el indice sea de Renta fija en este caso no esta generalizado sino solo esta para COLIBR --%>
		<c:if test="${nombreIndice == 'IIBR'}">
		<div class="tabbertab" title="Histórica">
			<div id="texto_grafica_hist" style="height: 100%">
				<script type="text/javascript">
					var indiceGraficoHist = new SWFObject(
							"%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf",
							"amstock", "100%", "328", "8", "#FFFFFF");
					indiceGraficoHist.addVariable("path", "");
					indiceGraficoHist
							.addVariable(
									"settings_file",
									escape("/mercados/GraficosServlet?conf=<c:out value='${nombreIndice}'/>&titulo=<c:out value='${nombreMostrarIndice}'/>&url=/amstock/settings/indice_stock_settings_RF.xml&tipoG=INDICE&mercInd=MM&tiempo=ayer"));
					indiceGraficoHist.addVariable("preloader_color", "#999999");
					indiceGraficoHist.addVariable("chart_id", "amstockHist");
					indiceGraficoHist.addParam("wmode", "opaque");
					licenciaGrafica(indiceGraficoHist);
					indiceGraficoHist.write('texto_grafica_hist');
				</script>
			</div>
		</div>
		</c:if>		
	</div>
</div>