<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ include file="/WEB-INF/jsp/cotizacionEncabezadoMGC.jsp"%>


	
	  		<div class="lisemi_resultados_busqueda"  id="texto_24">
      			Resultados de la B&uacute;squeda
      		</div>
  			<div class="contenedor_acordeon_sin_borde" id="tbAcNegociadas" style="display: block">
		<display:table name="listaCotizaPais" defaultorder="descending" defaultsort="3"
			class="tabla_general" id="textCotiza" >
			
			
			<display:column title="Empresa &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="<c:out value='${textCotiza.url_emisor}'/>" target="_blank"
			onclick="alerta_salir();">
					<c:out value="${textCotiza.empresa}" /></a>
			</display:column>
			
			<display:column title="Acci&oacute;n &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="<c:out value='${textCotiza.url_bolsa}'/>" target="_blank"
			onclick="alerta_salir();">
					<c:out value="${textCotiza.accion}" /></a>
			</display:column>
			
			
			<display:column  title="Cotizaci&oacute;n Principal * &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				 style="text-align: center;" media="html">
				 <c:out value="${textCotiza.cotizacion}" />
			</display:column>
			
			<display:column  title="Cantidad &nbsp;&nbsp;&nbsp;&nbsp;" 
				 style="text-align: right;" media="html">
				 <c:out value="${textCotiza.cantidad}" />
			</display:column>
			<display:column  title="Volumen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				 style="text-align: right;" media="html">
				 <c:out value="${textCotiza.volumen}" />
			</display:column>
			<display:column  title="Precio Máximo &nbsp;&nbsp;&nbsp;&nbsp;" 
				 style="text-align: right;" media="html">
				 <c:out value="${textCotiza.precioMaximo}" />
			</display:column>
			<display:column  title="Precio Mínimo &nbsp;&nbsp;&nbsp;&nbsp;" 
				 style="text-align: right;" media="html">
				 <c:out value="${textCotiza.precioMinimo}" />
			</display:column>
			<display:column  title="Precio Medio &nbsp;&nbsp;&nbsp;&nbsp;" 
				 style="text-align: right;" media="html">
				 <c:out value="${textCotiza.precioMedio}" />
			</display:column>
			<display:column  title="Precio Último &nbsp;&nbsp;&nbsp;&nbsp;" 
				 style="text-align: right;" media="html">
				 <c:out value="${textCotiza.precioUltimo}" />
			</display:column>
			<display:column  title="Precio Anterior &nbsp;&nbsp;&nbsp;&nbsp;" 
				 style="text-align: right;" media="html">
				 <c:out value="${textCotiza.precioAnterior}" />
			</display:column>
			<display:column  title="Variación Puntos &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				 style="text-align: right;" media="html">
				 <c:out value="${textCotiza.variacion}" />
			</display:column>
			<display:column  title="Variación % &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				 style="text-align: right;" media="html" format="{0,number,###,##0.0000}%" >
				 <c:out value="${textCotiza.variacionPorcentual}" />
			</display:column>
			<display:column media="html" style="text-align: center; padding-left:6px; padding-right:6px;">
				<c:choose>
					<c:when test="${textCotiza.variacionPorcentual>0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif" alt="Sube" style="margin-left:0px" />
					</c:when>
					<c:when test="${textCotiza.variacionPorcentual==0}">
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual" style="margin-left:0px" />
					</c:when>
					<c:otherwise>
						<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif" alt="Baja" style="margin-left:0px"  />
					</c:otherwise>
				</c:choose>
			</display:column>
			
		</display:table> 
	</div>
	<div id="nota_titulo"><h3 style="width:100%">* Cotizaci&oacute;n principal inicial en el momento de la inscripci&oacute;n en la BVC por parte del patrocinador</h3></div>
	
	