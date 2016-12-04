<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type='text/javascript' src='/mercados/js/derivadosHomeDrvx.js'></script>  
<script>
	 cambiarDerivadosDrvx = function() {

		 var obj = document.getElementById("listaFuturosDrvx");//futuros energéticos

		 var lista = document.getElementById("filtroDerivados");
		
		if (lista.value=="F") {
			obj.style.display = "block";
		}else {
			obj.style.display = "none";
		}
	 }

	manejarAcordeonDerivadosDrvx = function(){
                if ( "acordeon_resumen_activo" ==
                     document.getElementById('titulo_drvx').getAttribute("class")
                     || "acordeon_resumen_activo" ==
                     document.getElementById('titulo_drvx').getAttribute("className") )
                {    
		mostrar_segmento_acordeon_inactivo('titulo_drvx');
                }
                else {
                mostrar_segmento_acordeon_activo('titulo_drvx');
                }
                MM_effectBlind('seg_res_drvx', 500, '0%', '100%', true);
	}

	setTimeout("recargarHomeDerivadosDrvx()", TIEMPO_RECARGA);
	 
</script>

<div class="acordeon_titulo_interior">
<div id="titulo_drvx" class="acordeon_titulo_resumen_mercados" onclick="manejarAcordeonDerivadosDrvx();">
            <p>Derivados Energéticos</p>
          </div>

<div class="contenedor_tabla" style="padding:5px 0px 0px 0px">
<table class="resumen_tabla" cellpadding="0" cellspacing="0">
	<tbody>
		<c:forEach items="${mercadoDerivados}" var="md" varStatus="stat">
			<c:if test="${md.contrato ne 'Total' && md.contrato ne 'OPCF'}" >
				<tr class="resumen_indices_tabla_datos_fila_resaltada">
					<td id="textNombreResumenGnralDerivado<c:out value='${stat.index}'/>" align="left" class="resumen_tabla_fila_resaltada" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;padding:5px 5px 5px 5px">
						<strong>
							Volumen Contratos 
							<c:if test="${md.contrato eq 'Futuros Energéticos'}">
								<c:out value="Futuros"/>
							</c:if>
							<c:if test="${md.contrato ne 'Futuros Energéticos'}">
								<c:out value="${md.contrato}"/>
							</c:if>
						</strong>
					</td>
					<td id="textValorResumenGnralDerivado<c:out value='${stat.index}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;padding-right:5px">
						<fmt:formatNumber pattern="###,###" >
							<c:out value="${md.contratos}" />
						</fmt:formatNumber>
					</td>
				</tr>
			</c:if>       
		</c:forEach>
	</tbody>
</table>
</div>
</div>
<div id="seg_res_drvx" class="acordeon_interior_contenido">
<div class="acordeon_titulo_contenedor_resumen_mercado">
<div class="resumen_info_contenedor">
<div id="textEstadoMercadoDerivadoDrvx" class="resumen_info_titulo" style="padding: 0px 0px 0px 5px; display:block; float:left; position:relative; height:20px; line-height:20px; font-size:11px">
	<c:out value="${estadoMercado}"/>
</div>
<div class="resumen_info" style="padding: 0px 5px 0px 0px; display:block; float:right; position:relative; height:20px; line-height:20px; font-size:11px">Retraso: <c:out value="${retraso}" /> minutos</div>
</div>
<portlet:actionURL var="formCambio">
	<portlet:param name="action" value="buscar"/>
</portlet:actionURL>

<div class="resumen_select_1" style="padding-top:5px;"/>
  
<div class="contenedor_tabla">
<div id="listaFuturosDrvx" style="display:block;height:175px;" >
<table class="resumen_tabla" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
		<td style="border-right:#DBDBDB 1px solid; background-color:#EFEDEE">Nombre</td>
        <td width="40px" align="center" style="border-right:#DBDBDB 1px solid; background-color:#EFEDEE; padding:5px 0px 5px 5px; text-align:center">&Uacute;ltimo Precio</td>
        <td width="70px" style="border-right:#DBDBDB 1px solid; background-color:#EFEDEE">Volumen Contratos</td>
        <td style="background-color:#EFEDEE">Variaci&oacute;n</td>		
		</tr>
	</thead>
	<tbody>
		<c:set var="idxFuturo" value="0"/>
		<c:forEach items="${derivadosResumenFuturos}" var="dr" varStatus="statusFut">
			<c:forEach items="${dr.derivadosResumen}" var="fr">
				<c:forEach items="${fr.derivadosExtendidos}" var="dre" begin="0" end="5" varStatus="statFut">
					<c:if test="${idxFuturo<5}">
					<tr id='derivados<c:out value="${dre.volumen}"/>'style="cursor:pointer" 
					onmouseover="resumen_indice_activo('derivados<c:out value="${dre.volumen}"/>');" 
        onmouseout="resumen_indice_inactivo('derivados<c:out value="${dre.volumen}"/>');"   >
						<td style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;padding:5px 5px 5px 5px">
							<a id="textoNombreContratoDerivadoFuturo<c:out value='${idxFuturo}'/>" href='/pps/tibco/portalbvc/Home/Mercados/enlinea/derivados?com.tibco.ps.pagesvc.action=portletAction&action=detalleDerivadoDrvx&nemo=<c:out value="${dre.contrato}"/>&opcf='>
								<c:out value="${dre.contrato}" />
							</a>
						</td>
						<td id="textoPrecioContratoDerivadoFuturo<c:out value='${idxFuturo}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;"><fmt:formatNumber
							pattern="###,###.00">
							<c:out value="${dre.precio}" />
						</fmt:formatNumber></td>
						<td id="textoNumeroContratoDerivadoFuturo<c:out value='${idxFuturo}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;" >
							<fmt:formatNumber pattern="###,###">
									<c:out value="${dre.contratos}" />
							</fmt:formatNumber>
						</td>
						<td id="textoVariacionContratoDerivadoFuturo<c:out value='${idxFuturo}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid; padding:5px 5px 5px 5px; text-align:right"><fmt:formatNumber
							pattern="0.00">
							<c:out value="${dre.variacion}" />
						</fmt:formatNumber>% <c:choose>
													<c:when test="${dre.variacion gt 0}">
														<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/greenup.gif" alt="Sube"/>
													</c:when>
													<c:when test="${dre.variacion lt 0}">
														<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/reddown.gif" alt="Baja"/>
													</c:when>
													<c:otherwise>
														<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual"/>														
													</c:otherwise>
												</c:choose>
												</td>
					</tr>
					<c:set var="idxFuturo" value="${idxFuturo + 1}"/>
					</c:if>
				</c:forEach>
			</c:forEach>
		</c:forEach>
	</tbody>

</table>
</div>
<div id="listaOpciones" style="display:none;height:175px;">
<table class="resumen_tabla" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
		<td style="border-right:#DBDBDB 1px solid; background-color:#EFEDEE">Nombre</td>
        <td width="40px" align="center" style="border-right:#DBDBDB 1px solid; background-color:#EFEDEE; padding:5px 0px 5px 5px; text-align:center">&Uacute;ltimo Precio</td>
        <td width="70px" style="border-right:#DBDBDB 1px solid; background-color:#EFEDEE">Volumen Contratos</td>
        <td style="background-color:#EFEDEE">Variaci&oacute;n</td>		
		</tr>
	</thead>
	<tbody>
		<c:set var="idxOpciones" value="0"/>
		<c:forEach items="${derivadosResumenOpciones}" var="dr" varStatus="status">
			<c:forEach items="${dr.derivadosResumen}" var="fr">
				<c:forEach items="${fr.derivadosExtendidos}" var="dre" begin="0" end="5" varStatus="statOpciones">
					<c:if test="${idxOpciones < 5 }">
					<tr id='derivados<c:out value="${dre.volumen}"/>'style="cursor:pointer" 
					onmouseover="resumen_indice_activo('derivados<c:out value="${dre.volumen}"/>');" 
        onmouseout="resumen_indice_inactivo('derivados<c:out value="${dre.volumen}"/>');"   >
						<td style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;padding:5px 5px 5px 5px">
							<a id="textoNombreContratoDerivadoOpciones<c:out value='${idxOpciones}'/>" href='/pps/tibco/portalbvc/Home/Mercados/enlinea/derivados?com.tibco.ps.pagesvc.action=portletAction&action=detalleDerivadoDrvx&nemo=<c:out value="${dre.contrato}"/>'>
								<c:out value="${dre.contrato}" />
							</a>
						</td>
						<td id="textoPrecioContratoDerivadoOpciones<c:out value='${idxOpciones}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;"><fmt:formatNumber
							pattern="###,###.00">
							<c:out value="${dre.precio}" />
						</fmt:formatNumber></td>
						<td id="textoNumeroContratoDerivadoOpciones<c:out value='${idxOpciones}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;" ><fmt:formatNumber
							pattern="###,###">
							<c:out value="${dre.contratos}" />
						</fmt:formatNumber></td>
						<td id="textoVariacionContratoDerivadoOpciones<c:out value='${idxOpciones}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid; padding:5px 5px 5px 5px; text-align:right"><fmt:formatNumber
							pattern="0.00">
							<c:out value="${dre.variacion}" />
						</fmt:formatNumber>% <c:choose>
													<c:when test="${dre.variacion gt 0}">
														<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/greenup.gif" alt="Sube"/>
													</c:when>
													<c:when test="${dre.variacion lt 0}">
														<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/reddown.gif" alt="Baja"/>
													</c:when>
													<c:otherwise>
														<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual"/>														
													</c:otherwise>
												</c:choose>
												</td>
					</tr>
					<c:set var="idxOpciones" value="${idxOpciones + 1}"/>
					</c:if>
				</c:forEach>
			</c:forEach>
		</c:forEach>
	</tbody>

</table>
</div>
<div id="listaOPCF" style="display:none;height:175px;" >
<table class="resumen_tabla" cellpadding="0" cellspacing="0">
	<thead>
		<tr>
		<td style="border-right:#DBDBDB 1px solid; background-color:#EFEDEE">Vencimiento</td>
        <td width="40px" align="center" style="border-right:#DBDBDB 1px solid; background-color:#EFEDEE; padding:5px 0px 5px 5px; text-align:center">&Uacute;ltimo Precio</td>
        <td width="70px" style="border-right:#DBDBDB 1px solid; background-color:#EFEDEE">Volumen Contratos</td>
        <td style="background-color:#EFEDEE">Variaci&oacute;n</td>		
		</tr>
	</thead>
	<tbody>
		<c:set var="idxOpcf" value="0"/>
		<c:forEach items="${derivadosResumenOPCF}" var="dr" varStatus="status" >
			<c:forEach items="${dr.derivadosResumen}" var="fr" varStatus = "statfr">
				<c:forEach items="${fr.derivadosExtendidos}" var="dre" varStatus="statOPCF">
					
					<c:if test="${idxOpcf < 5}">
						
						<tr id='derivados<c:out value="${dre.volumen}"/>'style="cursor:pointer" 
						onmouseover="resumen_indice_activo('derivados<c:out value="${dre.volumen}"/>');" 
	        onmouseout="resumen_indice_inactivo('derivados<c:out value="${dre.volumen}"/>');"   >
							<td style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;padding:5px 5px 5px 5px">
								<a id="textoNombreContratoDerivadoOPCF<c:out value='${idxOpcf}'/>" href='/pps/tibco/portalbvc/Home/Mercados/enlinea/derivados?com.tibco.ps.pagesvc.action=portletAction&action=detalleDerivadoDrvx&nemo=<c:out value="${dre.contrato}"/>&opcf=opcf'>
										<c:out value="${dre.contrato}" />
								</a>
							</td>
							<td id="textoPrecioContratoDerivadoOPCF<c:out value='${idxOpcf}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;"><fmt:formatNumber
								pattern="###,###.00">
								<c:out value="${dre.precio}" />
							</fmt:formatNumber></td>
							<td id="textoNumeroContratoDerivadoOPCF<c:out value='${idxOpcf}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;" >
								<fmt:formatNumber pattern="###,###">
									<c:out value="${dre.contratos}" />
								</fmt:formatNumber>
							</td>
							<td id="textoVariacionContratoDerivadoOPCF<c:out value='${idxOpcf}'/>" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid; padding:5px 5px 5px 5px; text-align:right">
								<fmt:formatNumber pattern="0.00">
								<c:out value="${dre.variacion}" />
							</fmt:formatNumber>% <c:choose>
														<c:when test="${dre.variacion gt 0}">
															<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/greenup.gif" alt="Sube"/>
														</c:when>
														<c:when test="${dre.variacion lt 0}">
															<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/reddown.gif" alt="Baja"/>
														</c:when>
														<c:otherwise>
															<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual"/>														
														</c:otherwise>
													</c:choose>
													</td>   
						</tr>
						<c:set var="idxOpcf" value="${idxOpcf + 1}"/>
					</c:if>
				</c:forEach>
			</c:forEach>
		</c:forEach>
	</tbody>

</table>
</div>
</div>
<div class="resumen_informacion_detallada" style="display: block;"><a href="/pps/tibco/portalbvc/Home/Mercados/enlinea/derivados?action=dummy">Información
detallada</a></div>
<div class="contenedor_tabla"style="display:block; float:left; position:relative; overflow:hidden; width:100%; height:auto; padding:0px 0px 5px 0px">
		<a href="http://www.derivex.com.co" target="_blank"> 
			<img border = 0 src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/Derivex.jpg" alt="Derivex" />
		</a>
	</div>
</div>

</div>
</div>
<script>
cambiarDerivadosDrvx();
</script>
