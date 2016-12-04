<%@ include file="/WEB-INF/jsp/include.jsp"%>

	<div class="contenedor_acordeon_sin_borde" id="tbAcNegociadas" style="display: block">
		<display:table name="listaAcciones" defaultorder="descending" defaultsort="4"
			class="tabla_general" id="textTitulos" >
			
			<display:column title="Acci&oacute;n &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="javascript:document.busqueda.submit();" 
					onclick="enviarFormularioDesdeEnlace('<c:out value="${textTitulos.nemoTecnico}"/>')">
					<c:out value="${textTitulos.nemoTecnico}" /></a>
			</display:column>
			
			<display:column title="Emisores &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="/pps/tibco/portalbvc/Home/Mercado+Global/Listado+Emisores?com.tibco.ps.pagesvc.action=portletAction&com.tibco.ps.pagesvc.targetSubscription=-612b1fd1_12af04d0305_-7f74c0a85586&action=link&MGC=true&emisorId=<c:out value="${textTitulos.idEmisor}"/>">
					<c:out value="${textTitulos.nombreEmr}" /></a>
			</display:column>
			
			<display:column property="cantidad" format="{0,number,###,###}"
				title="Cantidad" style="text-align: right;" />
			<display:column property="volumen" title="Volumen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				format="{0,number,###,###.00}" style="text-align: right;"/>
			<display:column format="{0,number,###,###.00}"
				title="Precio Cierre" style="text-align: right;" media="html">
				<c:if test="${textTitulos.ultimoPrecio ne 0.0}">
					<fmt:formatNumber pattern="###,##0.00">
						<c:out value="${textTitulos.ultimoPrecio}"/>
					</fmt:formatNumber>
				</c:if>
			</display:column>
			<display:column property="variacion" title="Variación &nbsp;&nbsp;&nbsp;&nbsp;"
				format="{0,number,###,##0.00}%" sortable="true" style="text-align: right;"/>
			<display:column media="html" style="text-align: center; padding-left:6px; padding-right:6px;">
				<c:choose>
					<c:when test="${textTitulos.variacion>0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/greenup.gif" alt="Sube" style="margin-left:0px" />
					</c:when>
					<c:when test="${textTitulos.variacion==0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual" style="margin-left:0px" />
					</c:when>
					<c:otherwise>
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/reddown.gif" alt="Baja" style="margin-left:0px" />
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table> 
	</div>
	<div class="contenedor_acordeon_sin_borde" id="tbAcSube" style="display: none">
		<display:table name="listaAccionesSube" 
			class="tabla_general" id="textTitulos" defaultorder="descending" defaultsort="5">
			
			<display:column title="Acci&oacute;n &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				class="specblue" style="text-align: center">
				<a href="javascript:document.busqueda.submit();" 
					onclick="enviarFormularioDesdeEnlace('<c:out value="${textTitulos.nemoTecnico}"/>')">
					<c:out value="${textTitulos.nemoTecnico}" /></a>
			</display:column>
			
			<display:column title="Emisores &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="/pps/tibco/portalbvc/Home/Mercado+Global/Listado+Emisores?com.tibco.ps.pagesvc.action=portletAction&com.tibco.ps.pagesvc.targetSubscription=-612b1fd1_12af04d0305_-7f74c0a85586&action=link&MGC=true&emisorId=<c:out value="${textTitulos.idEmisor}"/>">
					<c:out value="${textTitulos.nombreEmr}" /></a>
			</display:column>
			
			<display:column property="cantidad" format="{0,number,###,###}"
				title="Cantidad" style="text-align: right;"/>
			<display:column property="volumen" title="Volumen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				format="{0,number,###,###.00}" style="text-align: right;"/>
			<display:column property="ultimoPrecio" format="{0,number,###,###.00}"
				title="Precio Cierre" style="text-align: right;"/>
			<display:column property="variacion" title="Variación  &nbsp;&nbsp;&nbsp;&nbsp;"
				format="{0,number,###,##0.0000}%" sortable="true" style="text-align: right;"/>
			<display:column media="html" style="text-align: center; padding-left:6px; padding-right:6px;">
				<c:choose>
					<c:when test="${textTitulos.variacion>0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif" alt="Sube" style="margin-left:0px"/>
					</c:when>
					<c:when test="${textTitulos.variacion==0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual" style="margin-left:0px" />
					</c:when>
					<c:otherwise>
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif" alt="Baja" style="margin-left:0px" />
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table> 
	</div>
	<div class="contenedor_acordeon_sin_borde" id="tbAcBaja" style="display: none">
		<display:table name="listaAccionesBaja" 
			class="tabla_general" id="textTitulos" defaultorder="ascending" defaultsort="5">
			
			<display:column title="Acci&oacute;n &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="javascript:document.busqueda.submit();" 
					onclick="enviarFormularioDesdeEnlace('<c:out value="${textTitulos.nemoTecnico}"/>')">
					<c:out value="${textTitulos.nemoTecnico}" /></a>
			</display:column>
			
			<display:column title="Emisores &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="/pps/tibco/portalbvc/Home/Mercado+Global/Listado+Emisores?com.tibco.ps.pagesvc.action=portletAction&com.tibco.ps.pagesvc.targetSubscription=-612b1fd1_12af04d0305_-7f74c0a85586&action=link&MGC=true&emisorId=<c:out value="${textTitulos.idEmisor}"/>">
					<c:out value="${textTitulos.nombreEmr}" /></a>
			</display:column>
			
			<display:column property="cantidad" format="{0,number,###,###}"
				title="Cantidad" style="text-align: right;"/>
			<display:column property="volumen" title="Volumen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				format="{0,number,###,###.00}" style="text-align: right;"/>
			<display:column property="ultimoPrecio" format="{0,number,###,###.00}"
				title="Precio Cierre" style="text-align: right;"/>
			<display:column property="variacion" title="Variación &nbsp;&nbsp;&nbsp;&nbsp;"
				format="{0,number,###,##0.0000}%" sortable="true"  style="text-align: right;"/>
			<display:column media="html" style="text-align: center; padding-left:6px; padding-right:6px;">
				<c:choose>
					<c:when test="${textTitulos.variacion>0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif" alt="Sube" style="margin-left:0px" />
					</c:when>
					<c:when test="${textTitulos.variacion==0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual" style="margin-left:0px" />
					</c:when>
					<c:otherwise>
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif" alt="Baja" style="margin-left:0px"  />
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table> 
	</div>