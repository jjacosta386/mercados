<%@ include file="/WEB-INF/jsp/include.jsp"%>

	<div class="contenedor_acordeon_sin_borde" id="tbEtfNegociadas" style="display: block">
		<display:table name="listaAccionesEtf" defaultorder="descending" defaultsort="3"
			class="tabla_general" id="textTitulosII" >
			
			<display:column title="Acci&oacute;n &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="javascript:document.busqueda.submit();" 
					onclick="enviarFormularioDesdeEnlace('<c:out value="${textTitulosII.nemoTecnico}"/>')">
					<c:out value="${textTitulosII.nemoTecnico}" /></a>
			</display:column>
			
			<display:column title="Emisores &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="/pps/tibco/portalbvc/Home/Empresas/Emisores+BVC/Listado+de+Emisores?com.tibco.ps.pagesvc.action=portletAction&com.tibco.ps.pagesvc.targetSubscription=5d9e2b27_11de9ed172b_-71c17f000001&action=link&emisorId='<c:out value="${textTitulosII.idEmisor}"/>')">
					<c:out value="${textTitulosII.nombreEmr}" /></a>
			</display:column>
			
			<display:column property="cantidad" format="{0,number,###,###}"
				title="Cantidad" style="text-align: right;" />
			<display:column property="volumen" title="Volumen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				format="{0,number,###,###.00}" style="text-align: right;"/>
			<display:column format="{0,number,###,###.00}"
				title="Precio Cierre" style="text-align: right;" media="html">
				<c:if test="${textTitulosII.ultimoPrecio ne 0.0}">
					<fmt:formatNumber pattern="###,##0.00">
						<c:out value="${textTitulosII.ultimoPrecio}"/>
					</fmt:formatNumber>
				</c:if>
			</display:column>
			<display:column property="variacion" title="Variación &nbsp;&nbsp;&nbsp;&nbsp;"
				format="{0,number,###,##0.00}%" sortable="true" style="text-align: right;"/>
			<display:column media="html" style="text-align: center; padding-left:6px; padding-right:6px;">
				<c:choose>
					<c:when test="${textTitulosII.variacion>0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/greenup.gif" alt="Sube" style="margin-left:0px" />
					</c:when>
					<c:when test="${textTitulosII.variacion==0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual" style="margin-left:0px" />
					</c:when>
					<c:otherwise>
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/reddown.gif" alt="Baja" style="margin-left:0px" />
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table> 
	</div>
	<div class="contenedor_acordeon_sin_borde" id="tbEtfSube" style="display: none">
		<display:table name="listaAccionesSubeEtf" 
			class="tabla_general" id="textTitulosII" defaultorder="descending" defaultsort="5">
			
			<display:column title="Acci&oacute;n &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				class="specblue" style="text-align: center">
				<a href="javascript:document.busqueda.submit();" 
					onclick="enviarFormularioDesdeEnlace('<c:out value="${textTitulosII.nemoTecnico}"/>')">
					<c:out value="${textTitulosII.nemoTecnico}" /></a>
			</display:column>
			
			<display:column title="Emisores &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="/pps/tibco/portalbvc/Home/Empresas/Emisores+BVC/Listado+de+Emisores?com.tibco.ps.pagesvc.action=portletAction&com.tibco.ps.pagesvc.targetSubscription=5d9e2b27_11de9ed172b_-71c17f000001&action=link&emisorId='<c:out value="${textTitulosII.idEmisor}"/>')">
					<c:out value="${textTitulosII.nombreEmr}" /></a>
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
					<c:when test="${textTitulosII.variacion>0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif" alt="Sube" style="margin-left:0px"/>
					</c:when>
					<c:when test="${textTitulosII.variacion==0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual" style="margin-left:0px" />
					</c:when>
					<c:otherwise>
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif" alt="Baja" style="margin-left:0px" />
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table> 
	</div>
	<div class="contenedor_acordeon_sin_borde" id="tbEtfBaja" style="display: none">
		<display:table name="listaAccionesBajaEtf" 
			class="tabla_general" id="textTitulosII" defaultorder="ascending" defaultsort="5">
			
			<display:column title="Acci&oacute;n &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="javascript:document.busqueda.submit();" 
					onclick="enviarFormularioDesdeEnlace('<c:out value="${textTitulosII.nemoTecnico}"/>')">
					<c:out value="${textTitulosII.nemoTecnico}" /></a>
			</display:column>
			
			<display:column title="Emisores &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="/pps/tibco/portalbvc/Home/Empresas/Emisores+BVC/Listado+de+Emisores?com.tibco.ps.pagesvc.action=portletAction&com.tibco.ps.pagesvc.targetSubscription=5d9e2b27_11de9ed172b_-71c17f000001&action=link&emisorId='<c:out value="${textTitulosII.idEmisor}"/>')">
					<c:out value="${textTitulosII.nombreEmr}" /></a>
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
					<c:when test="${textTitulosII.variacion>0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif" alt="Sube" style="margin-left:0px" />
					</c:when>
					<c:when test="${textTitulosII.variacion==0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual" style="margin-left:0px" />
					</c:when>
					<c:otherwise>
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif" alt="Baja" style="margin-left:0px"  />
					</c:otherwise>
				</c:choose>
			</display:column>
		</display:table> 
	</div>