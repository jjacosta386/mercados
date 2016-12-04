<%@ include file="/WEB-INF/jsp/include.jsp"%>

	<div class="contenedor_acordeon_sin_borde" id="tbAcNegociadas" style="display: block">
		<display:table name="listaAcciones" defaultorder="ascending"
			class="tabla_general" id="textTitulos">
			
			<display:column title="Nombre de la Especie &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue">
				<a href="javascript:document.busqueda.submit();" 
					onclick="enviarFormularioDesdeEnlace('<c:out value="${textTitulos.nemoTecnico}"/>')">
					<c:out value="${textTitulos.nemoTecnico}" /></a>
			</display:column>
			
			<display:column property="cantidad" format="{0,number,###,###}"
				title="Cantidad" style="text-align: right;"/>
			<display:column property="volumen" 
			    title="Volumen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
			format="{0,number,###,##0.00}" style="text-align: right;"/>
			
 <%--Se ajusta para requerimiento de TTVS
            <display:column property="volumenInicial" title="Volumen Inicial &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				format="{0,number,###,###.##}" style="text-align: right;"/>
			<display:column property="volumenFinal" title="Volumen Final &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				format="{0,number,###,###.##}" style="text-align: right;"/>
			<display:column property="plazoInicial" title="Plazo Inicial &nbsp;&nbsp;&nbsp;&nbsp;" style="text-align: right;"/>
			<display:column property="plazoFinal" title="Plazo Final &nbsp;&nbsp;&nbsp;&nbsp;" style="text-align: right;"/>
			 --%>	
		</display:table> 
	</div>

	
	