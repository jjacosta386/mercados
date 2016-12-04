<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div class="contenedor_acordeon_sin_borde" id="tbAcNegociadas" style="display: block">
	<display:table name="listaAcciones" defaultorder="ascending"
		class="tabla_general" id="textTitulos">
	
		<display:column title="Acci&oacute;n &nbsp;&nbsp;&nbsp;&nbsp;"
			media="html" sortable="true" class="specblue">
			<a href="javascript:document.busqueda.submit();" 
						onclick="enviarFormularioDesdeEnlace('<c:out value="${textTitulos.nemoTecnico}"/>')">
						<c:out value="${textTitulos.nemoTecnico}" /></a>
		</display:column>
		<display:column property="cantidad" format="{0,number,###,##0.00}"
			title="Cantidad" style="text-align: right;"/>
		<display:column property="volumen"
			title="Volumen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
			format="{0,number,###,##0.00}" style="text-align: right;"/>
	</display:table>
</div>
