<%@include file="../include.jsp"%>

<%@ include file="buscadorIndice.jsp"%>

<script type="text/javascript">
function ponerFlechaN(numero){
		if (numero > 0) {
			document.write("<img style=\"vertical-align: baseline\" alt=\"Subio\" src=\"%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif\"/>");
		}else{
			if (numero < 0) {
				document.write("<img style=\"vertical-align: baseline\" alt=\"Bajo\" src=\"%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif\"/>");
			}else{
				document.write("<img style=\"vertical-align: baseline\" alt=\"Igual\" src=\"%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif\"/>");				
			}
		}
	}
</script>


<div id="text_12" class="titulo_seccion_unacolumna">
	<div class="subtitulo_mercados">&Iacute;ndices</div>
	<div id="text_13" class="subtitulo_mercados_pequeno">
		<c:out value="${mensajeMercado}"></c:out>
	</div>
</div>
<div id="nota_titulo">
	<h3> </h3>
	
          

          
             <h2 id="text_14">Información en línea</h2>
          
       
</div>

<div class="contenedor_acordeon_borde">
	<div class="contenedor_acordeon_sin_borde" id="text_27">
		
		
					<display:table name="listaPpal" defaultorder="descending" defaultsort="3" id="textTitulos" class="tabla_general" cellspacing="0" cellpadding="0"  style="border: 0px none width=100%;">
						<display:column title="Indices &nbsp;&nbsp;&nbsp;&nbsp;"  media="html" sortable="true"  class="specblue"  style="text-align: left; width=144">
				             <a href="javascript:document.forma.submit();" onclick="enviarFormularioDesdeEnlace('<c:out value="${textTitulos.indice}"/>')"><c:out value="${textTitulos.nombreIndice}"/></a>
			            </display:column>
                        <display:column title="Mercado &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"   style="text-align: left; width=144">
				             <c:choose>
								<c:when test="${textTitulos.mercado==''}">
									RENTA VARIABLE
								</c:when>	
								<c:otherwise>
									<c:out value="${textTitulos.mercado}"></c:out>	   		
								</c:otherwise>
							 </c:choose>
			            </display:column>	
						<display:column title="Valor Hoy" media="html"   style="text-align: right; width=132">
				             <fmt:formatNumber pattern="###,##0.0000">
											<c:out value="${textTitulos.valorHoy}"></c:out>
							</fmt:formatNumber>
			            </display:column>
						<display:column title="Valor Ayer" media="html"   style="text-align: right; width=143">
				             <fmt:formatNumber pattern="###,##0.0000">
											<c:out value="${textTitulos.valorAyer}"></c:out>
							</fmt:formatNumber>
			            </display:column>
						<display:column title=" Variación Absoluta" media="html"   style="text-align: right; width=123">
				             <fmt:formatNumber pattern="###,##0.0000">
											<c:out value="${textTitulos.variacionAbs}"></c:out>
							</fmt:formatNumber>
			            </display:column>
						<display:column title="Variación % &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"   style="text-align: right; width=90">
				             <fmt:formatNumber pattern="###,##0.0000">
											<c:out value="${textTitulos.variacionHoy}"></c:out>
							</fmt:formatNumber>
			            </display:column>
						<display:column  media="html"   style="text-align: left; width=10">
				             <script type="text/javascript">
										ponerFlechaN(<c:out value="${textTitulos.variacionHoy}"/>);
							</script>
			            </display:column>
					</display:table>	
			
			<div class="contenedor_boton_imprimir">
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<tr>
				<td width="45%">&nbsp;</td>
				<td width="10%">
					<!--div class="boton_regular"><a href="#">Imprimir</a></div-->
				</td>
				<td width="45%" align="right"><a id="texto_1" href="/mercados/DescargaXlsServlet?archivo=indices&tipoMI=RESUMEN&fecha=<c:out value='${fechaBuscar}'/>&codIndice=<c:out value='${textTitulos.indice}'/>" class="excelneg">Descargar
					información de negociación</a></td>
			</tr>
		</table>
	</div>
			
			
			
		
	</div>
</div>