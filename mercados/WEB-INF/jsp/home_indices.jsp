<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type='text/javascript' src='/mercados/js/indicesHome.js'></script>
<script type="text/javascript">

	var abierto = true;

	function manejarSegmento(numSeg){
		mostrar_grafica_indice('indice_grafica_' + numSeg);
		mostrar_resumen_indice_activo('resumen_indice_' + numSeg);
		for (var i=1;i<4;i++){
			if (i != numSeg){
				mostrar_resumen_indice_inactivo('resumen_indice_' + i);
			}
		}	
	}
	
	function manejarSegmentoRF(numSeg2){
		mostrar_grafica_indice('indice_grafica_RF_' + numSeg2);
		mostrar_resumen_indice_activo('resumen_indice_RF_' + numSeg2);
		for (var i=1;i<4;i++){
			if (i != numSeg2){
				mostrar_resumen_indice_inactivo('resumen_indice_RF_' + i);
				document.getElementById('indice_grafica_RF_' + i ).style.display="none";
			}else{
			    document.getElementById('indice_grafica_RF_' + i ).style.display="inline";
			}
		}	
	}

	function cambiarIndices() {
		var lista = document.getElementById("filtroIndices");
		try {
			var obj = document.getElementById("lista_de_indices_1");//negociadas
		  	var obj2 = document.getElementById("lista_de_indices_2");//suben
		  	var obj5 = document.getElementById("lista_de_indices_3");//suben
		  	var obj3 = document.getElementById("msj");//negociadas
		  	var obj4 = document.getElementById("msjrf");//suben
		  	if (lista.value=="1") {
				obj.style.display = "block";
		   		obj2.style.display = "none";
		   		obj5.style.display = "none";
		   		obj3.style.display = "block";
		   		obj4.style.display = "none";
		   		opcionAcciones = 0;
		  	} else if (lista.value=="2") {
		   		obj.style.display = "none";
		   		obj5.style.display = "none";
		   		obj2.style.display = "block";
		   		obj3.style.display = "block";
		   		obj4.style.display = "none";
		   		opcionAcciones = 1;
		  	}else if (lista.value=="3") {
		   		obj.style.display = "none";
		   		obj2.style.display = "none";
		   		obj5.style.display = "block";
		   		obj3.style.display = "none";
		   		obj4.style.display = "block";
		   		opcionAcciones = 1;
		  	}
	  	}catch (err) {
	  	}
 	}

	
	function manejarIndicesHome(){
		abierto = !abierto; 
		if (abierto){
			manejarSegmento(1);	
			manejarSegmentoRF(1);	
                         mostrar_segmento_acordeon_activo('titulo_7');

		}else{
			  mostrar_segmento_acordeon_inactivo('titulo_7');

			for (var i=1;i<4;i++){
				mostrar_resumen_indice_inactivo('resumen_indice_' + i);
				mostrar_resumen_indice_inactivo('resumen_indice_RF_' + i);
				document.getElementById('indice_grafica_' + i ).style.display="none";
				document.getElementById('indice_grafica_RF_' + i ).style.display="none";
			}	
		}
	} 

	setTimeout("recargarHomeIndices()", TIEMPO_RECARGA);

</script>

<div id="titulo_7" class="acordeon_resumen_activo"
	onclick="manejarIndicesHome();">
	<p>Índices</p>
</div>

<div id="resumenes_mercado_indices"
	style="display: block; float: left; position: relative;">
	<div class="contenedor_tabla" style="padding: 5px 0px;">
		<div id="textoFechaIndice" class="resumen_fecha"
			style="padding-left: 5px;">
			<strong><c:out value="${fechaIndice}" /></strong>
		</div>
		<div class="resumen_info_linea" style="padding-right: 5px;" id="msj">
			<strong>Información en línea</strong>
		</div>
		<div class="resumen_info_linea"
			style="padding-right: 5px; display: none;" id="msjrf">
			<strong>Información de cierre</strong>
		</div>
	</div>

	<div class="resumen_select_1" style="padding-top: 5px;">
		<label> <select class="resumen_select_1_valores"
			onChange="cambiarIndices();" id="filtroIndices">
				<option value="1">Accionarios</option>
				<option value="2">Renta Fija</option>
				<option value="3">Mercado Monetario</option>
		</select>
		</label>
	</div>

	<div class="contenedor_tabla">
		<table class="resumen_tabla" cellspacing="0" cellpadding="0"
			style="width: 250px;">
			<thead>
				<tr>
					<td width="120"
						style="border-right: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(239, 237, 238);">
						Índices</td>
					<td width="70"
						style="border-right: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(239, 237, 238);">
						Unidades</td>
					<td style="padding: 5px; background-color: rgb(239, 237, 238);">
						Variación</td>
				</tr>
			</thead>
		</table>
	</div>
	<div id="lista_de_indices_1">
		<c:forEach items="${indiceResumen}" var="indice" varStatus="stat">
			<div class="contenedor_tabla"
				onclick="manejarSegmento(<c:out value='${stat.index + 1}'/>);">
				<table class="resumen_tabla" cellspacing="0" cellpadding="0"
					style="width: 250px;">
					<tbody>
						<tr id="indices2">
							<td class="resumen_tabla_fila_resaltada" width="108"
								style="border-bottom: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(255, 255, 255);">
								<div id="resumen_indice_<c:out value="${stat.index + 1}"/>"
									class="opcion_resumen_indice_activo">
									<a id="textoNombreIndice<c:out value='${stat.index}'/>"
										href="#"> <strong> <c:out
												value="${indice.nombreIndice}" />
									</strong>
									</a>
								</div>
							</td>
							<td id="textoValorHoyIndice<c:out value='${stat.index}'/>"
								align="right" width="70"
								style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255);">
								<fmt:formatNumber pattern="###,##0.00">
									<c:out value="${indice.valorHoy}" />
								</fmt:formatNumber>
							</td>
							<td id="textoVariacionHoyIndice<c:out value='${stat.index}'/>"
								align="right"
								style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255); padding: 5px 5px 5px 5px; text-align: right">
								<fmt:formatNumber pattern="###,##0.00">
									<c:out value="${indice.variacionHoy}" />
								</fmt:formatNumber>% <c:choose>
									<c:when test="${indice.variacionHoy gt 0}">
										<img
											src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif"
											alt="Sube" />
									</c:when>
									<c:when test="${indice.variacionHoy lt 0}">
										<img
											src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif"
											alt="Baja" />
									</c:when>
									<c:otherwise>
										<img
											src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif"
											alt="Igual" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="indice_grafica_<c:out value="${stat.index + 1}"/>"
					style="display: inline;">
					<div class="contenedor_tabla"
						style="overflow: hidden; display: block; float: left; width: 250px;"
						id="graficoIndice<c:out value="${stat.index + 1}"/>">
						<script type="text/javascript">
						var indiceGrafico<c:out value='${stat.index + 1}'/> = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf","amstockIndice<c:out value='${indice.indice}'/>", "248", "175", "8", "#FFFFFF");
						indiceGrafico<c:out value='${stat.index + 1}'/>.addVariable("path", "");
						indiceGrafico<c:out value='${stat.index + 1}'/>.addVariable("settings_file",
								escape("/mercados/GraficosServlet?conf=<c:out value='${indice.indice}'/>&titulo=<c:out value='${indice.nombreIndice}'/>&url=/amstock/settings/indice_home_stock_settings.xml&tipoG=INDICE&mercInd=RV&home=SI"));
						indiceGrafico<c:out value='${stat.index + 1}'/>.addVariable("preloader_color", "#999999");
						indiceGrafico<c:out value='${stat.index + 1}'/>.addVariable("chart_id", "amstockIndice<c:out value='${indice.indice}'/>");
						licenciaGrafica(indiceGrafico<c:out value='${stat.index + 1}'/>);
						indiceGrafico<c:out value='${stat.index + 1}'/>.write('graficoIndice<c:out value="${stat.index + 1}"/>');
					</script>

					</div>
					<div class="resumen_periodo"
						style="padding-left: 5px; width: 250px;">
						<label> &nbsp; </label>
						<div class="resumen_informacion_detallada"
							style="padding-right: 5px;">
							<a
								href="/pps/tibco/portalbvc/Home/Mercados/enlinea/indicesbursatiles?action=dummy">Información
								detallada</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="lista_de_indices_2" style="display: none;">
		<c:forEach items="${indiceResumenRF}" var="indice2" varStatus="stat2">
			<div class="contenedor_tabla"
				onclick="manejarSegmentoRF(<c:out value='${stat2.index + 1}'/>);">
				<table class="resumen_tabla" cellspacing="0" cellpadding="0"
					style="width: 250px;">
					<tbody>
						<tr id="indicesRF">
							<td class="resumen_tabla_fila_resaltada" width="108"
								style="border-bottom: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(255, 255, 255);">
								<div id="resumen_indice_RF_<c:out value="${stat2.index + 1}"/>"
									class="opcion_resumen_indice_activo">
									<a id="textoNombreIndiceRF<c:out value='${stat2.index}'/>"
										href="#">

									<strong> <c:out value="${indice2.nombreIndice}" />
									</strong>
                                     </a>
								</div>
							</td>
							<td id="textoValorHoyIndiceRF<c:out value='${stat2.index}'/>"
								align="right" width="70"
								style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255);">
								<fmt:formatNumber pattern="###,##0.00">
									<c:out value="${indice2.valorHoy}" />
								</fmt:formatNumber>
							</td>
							<td id="textoVariacionHoyIndiceRF<c:out value='${stat2.index}'/>"
								align="right"
								style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255); padding: 5px 5px 5px 5px; text-align: right">
								<fmt:formatNumber pattern="###,##0.00">
									<c:out value="${indice2.variacionHoy}" />
								</fmt:formatNumber>% <c:choose>
									<c:when test="${indice2.variacionHoy gt 0}">
										<img
											src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif"
											alt="Sube" />
									</c:when>
									<c:when test="${indice2.variacionHoy lt 0}">
										<img
											src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif"
											alt="Baja" />
									</c:when>
									<c:otherwise>
										<img
											src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif"
											alt="Igual" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="indice_grafica_RF_<c:out value="${stat2.index + 1}"/>"
					style="display: inline;">
					<div class="contenedor_tabla"
						style="overflow: hidden; display: block; float: left; width: 250px;"
						id="graficoIndiceRF<c:out value="${stat2.index + 1}"/>">
						<script type="text/javascript">
						var indiceGraficoRF<c:out value='${stat2.index + 1}'/> = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf","amstockIndice<c:out value='${indice2.indice}'/>", "248", "175", "8", "#FFFFFF");
						indiceGraficoRF<c:out value='${stat2.index + 1}'/>.addVariable("path", "");
						indiceGraficoRF<c:out value='${stat2.index + 1}'/>.addVariable("settings_file",
								escape("/mercados/GraficosServlet?conf=<c:out value='${indice2.indice}'/>&titulo=<c:out value='${indice2.nombreIndice}'/>&url=/amstock/settings/indice_home_stock_settings_RF.xml&tipoG=INDICE&mercInd=RF&home=SI"));
						indiceGraficoRF<c:out value='${stat2.index + 1}'/>.addVariable("preloader_color", "#999999");
						indiceGraficoRF<c:out value='${stat2.index + 1}'/>.addVariable("chart_id", "amstockIndice<c:out value='${indice2.indice}'/>");
						licenciaGrafica(indiceGraficoRF<c:out value='${stat2.index + 1}'/>);
						indiceGraficoRF<c:out value='${stat2.index + 1}'/>.write('graficoIndiceRF<c:out value="${stat2.index + 1}"/>');
					</script>
					</div>
					
					<div class="resumen_periodo"
						style="padding-left: 5px; width: 250px;">
						<label> &nbsp; </label>
						<div class="resumen_informacion_detallada"
							style="padding-right: 5px;">
							<a
								href="/pps/tibco/portalbvc/Home/Mercados/enlinea/indicesbursatiles?action=dummy">Información
								detallada</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>


<div id="lista_de_indices_3" style="display: none;">
		<c:forEach items="${indiceResumenMM}" var="indice3" varStatus="stat3">
			<div class="contenedor_tabla">
				<table class="resumen_tabla" cellspacing="0" cellpadding="0"
					style="width: 250px;">
					<tbody>
						<tr id="indicesMM">
							<td class="resumen_tabla_fila_resaltada" width="108"
								style="border-bottom: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(255, 255, 255);">
								<div id="resumen_indice_MM" class="opcion_resumen_indice_activo">

									<strong> <c:out value="${indice3.nombreIndice}" />
									</strong>

								</div>
							</td>
							<td id="textoValorHoyIndiceMM1" align="right" width="70"
								style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255);">
								<fmt:formatNumber pattern="###,##0.00">
									<c:out value="${indice3.valorHoy}" />
								</fmt:formatNumber>
							</td>
							<td id="textoVariacionHoyIndiceMM2" align="right"
								style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255); padding: 5px 5px 5px 5px; text-align: right">
								<fmt:formatNumber pattern="###,##0.00">
									<c:out value="${indice3.variacionHoy}" />
								</fmt:formatNumber>% <c:choose>
									<c:when test="${indice3.variacionHoy gt 0}">
										<img
											src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif"
											alt="Sube" />
									</c:when>
									<c:when test="${indice3.variacionHoy lt 0}">
										<img
											src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif"
											alt="Baja" />
									</c:when>
									<c:otherwise>
										<img
											src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif"
											alt="Igual" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="indice_grafica_MM" style="display: inline;">
					<div class="contenedor_tabla"
						style="overflow: hidden; display: block; float: left; width: 250px;"
						id="graficoIndice7">
						<script type="text/javascript">
						var indiceGrafico7 = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf","amstockIndice<c:out value='${indice3.indice}'/>", "248", "175", "8", "#FFFFFF");
						indiceGrafico7.addVariable("path", "");
						indiceGrafico7.addVariable("settings_file",
								escape("/mercados/GraficosServlet?conf=<c:out value='${indice3.indice}'/>&titulo=<c:out value='${indice3.nombreIndice}'/>&url=/amstock/settings/indice_home_stock_settings_MM.xml&tipoG=INDICE&mercInd=MM&home=SI"));
						indiceGrafico7.addVariable("preloader_color", "#999999");
						indiceGrafico7.addVariable("chart_id", "amstockIndice<c:out value='${indice3.indice}'/>");
						licenciaGrafica(indiceGrafico7);
						indiceGrafico7.write('graficoIndice7');
					</script>
					</div>
					
					<div class="resumen_periodo"
						style="padding-left: 5px; width: 250px;">
						<label> &nbsp; </label>
						<div class="resumen_informacion_detallada"
							style="padding-right: 5px;">
							<a
								href="/pps/tibco/portalbvc/Home/Mercados/enlinea/indicesbursatiles?action=dummy">Información
								detallada</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<script type="text/javascript">
	manejarSegmento(1);
	manejarSegmentoRF(1);
</script>
