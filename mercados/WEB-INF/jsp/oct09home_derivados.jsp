<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type='text/javascript' src='/mercados/js/derivadosHome.js'></script>  
<script>
	 cambiarDerivados= function() {
		var obj = document.getElementById("listaFuturos");//futuros
		var obj2 = document.getElementById("listaOpciones");//opciones
		var obj3 = document.getElementById("listaOPCF");//opcfs

		var lista = document.getElementById("filtroDerivados");
		
		if (lista.value=="F") {
			obj.style.display = "block";
			obj2.style.display = "none";
			obj3.style.display = "none";
		} else if (lista.value=="O") {
			obj.style.display = "none";

			obj3.style.display = "none";
		} else {
			obj.style.display = "none";
			obj2.style.display = "none";
			obj3.style.display = "block";
		}
	}

	manejarAcordeonDerivados = function(){
                if ( "acordeon_resumen_activo" ==
                     document.getElementById('titulo_2').getAttribute("class")
                     || "acordeon_resumen_activo" ==
                     document.getElementById('titulo_2').getAttribute("className") )
                {    
		mostrar_segmento_acordeon_inactivo('titulo_2');
                }
                else {
                mostrar_segmento_acordeon_activo('titulo_2');
                }
                MM_effectBlind('seg_res_2', 500, '0%', '100%', true);
	}

	setTimeout("recargarHomeDerivados()", TIEMPO_RECARGA);
	 
</script>

<div class="acordeon_titulo_interior">
<div id="titulo_2" class="acordeon_titulo_resumen_mercados" onclick="manejarAcordeonDerivados();">
            <p>Derivados Financieros</p>
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
							<c:if test="${md.contrato eq 'Futuros Financieros'}">
								<c:out value="Futuros"/>
							</c:if>
							<c:if test="${md.contrato ne 'Futuros Financieros'}">
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
<div id="seg_res_2" class="acordeon_interior_contenido">
<div class="acordeon_titulo_contenedor_resumen_mercado">
<div class="resumen_info_contenedor">
<div id="textEstadoMercadoDerivado" class="resumen_info_titulo" style="padding: 0px 0px 0px 5px; display:block; float:left; position:relative; height:20px; line-height:20px; font-size:11px">
	<c:out value="${estadoMercado}"/>
</div>
<div class="resumen_info" style="padding: 0px 5px 0px 0px; display:block; float:right; position:relative; height:20px; line-height:20px; font-size:11px">Retraso: 20 minutos</div>
</div>
<portlet:actionURL var="formCambio">
	<portlet:param name="action" value="buscar"/>
</portlet:actionURL>
<form id="busqueda" action="<%=formCambio%>" method="post" name="formDerivados">
	<div class="resumen_select_1" style="padding-top:5px;">
		<label>
	    	<select name="tipoContrato" class="resumen_select_1_valores" onchange="cambiarDerivados();" id="filtroDerivados">
	      		<option value="F">Futuros (contratos mas negociados)</option>
		  		<option value="O">Opciones</option>
				<option value="OPCF">OPCF - TRM</option>
	    	</select>
	    </label>
	</div>
</form>
  
<div class="contenedor_tabla">
<div id="listaFuturos" style="display:block;height:175px;" >
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
							<a id="textoNombreContratoDerivadoFuturo<c:out value='${idxFuturo}'/>" href='/pps/tibco/portalbvc/Home/Mercados/enlinea/derivados?com.tibco.ps.pagesvc.action=portletAction&action=detalleDerivado&nemo=<c:out value="${dre.contrato}"/>&opcf='>
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
							<a id="textoNombreContratoDerivadoOpciones<c:out value='${idxOpciones}'/>" href='/pps/tibco/portalbvc/Home/Mercados/enlinea/derivados?com.tibco.ps.pagesvc.action=portletAction&action=detalleDerivado&nemo=<c:out value="${dre.contrato}"/>'>
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
								<a id="textoNombreContratoDerivadoOPCF<c:out value='${idxOpcf}'/>" href='/pps/tibco/portalbvc/Home/Mercados/enlinea/derivados?com.tibco.ps.pagesvc.action=portletAction&action=detalleDerivado&nemo=<c:out value="${dre.contrato}"/>&opcf=opcf'>
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

</div>

</div>




<script>
cambiarDerivados();
</script>
