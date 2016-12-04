<%@include file="../includeCalendario.jsp"%>


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
<c:if test="${detallar}">
	<c:set var="nombreMostrarIndice" value="${indMetaData.nombreIndice}"></c:set>
	<div id="text_12" class="titulo_seccion_unacolumna">
		<div class="subtitulo_mercados">
			Índice
			<b>
				<c:out value="${indMetaData.nombreIndice}"/>
			</b>
		</div>
		<div id="text_13" class="subtitulo_mercados_pequeno">
			<c:out value="${mensajeMercado}"></c:out>
		</div>
	</div>
	<div id="nota_titulo">
		<h3> </h3>
		<c:choose>
          <c:when test="${indMetaData.nombreIndice=='COLTES'}">
             <h2 id="text_14">Información de cierre</h2>
          </c:when>

          <c:otherwise>
             <h2 id="text_14">Información en línea</h2>
          </c:otherwise>
       </c:choose>
		
	</div>
	<br clear="all"/>
		<%@include file="../grafica/grafica.jsp" %>
	<br clear="all"/>
	<div class="contenedor_acordeon_borde">
		<div class="contenedor_acordeon_sin_borde">
			<div id="acordeon_contenedor">
				<div class="acordeon_titulo_interior">
					<div class="acordeon_titulo_segmento">
						<p id="text_16">Resumen del mercado</p>
						<div id="ver_seg_1" class="ver_detalles_bvc" style="display: none;" onclick=" mostrar_segmento_acordeon('seg_acc_1'); mostrar_segmento_acordeon('cerrar_seg_1'); ocultar_segmento_acordeon('ver_seg_1'); ocultar_segmento_acordeon('seg_acc_2'); ocultar_segmento_acordeon('seg_acc_3'); ocultar_segmento_acordeon('cerrar_seg_2'); ocultar_segmento_acordeon('cerrar_seg_3'); mostrar_segmento_acordeon('ver_seg_2'); mostrar_segmento_acordeon('ver_seg_3'); "> Ver detalles</div>
						<div id="cerrar_seg_1" class="cerrar_detalle_bvc" style="display: block;" onclick=" ocultar_segmento_acordeon('seg_acc_1'); ocultar_segmento_acordeon('cerrar_seg_1'); mostrar_segmento_acordeon('ver_seg_1'); "> cerrar </div>
					</div>
				</div>
				<div id="seg_acc_1" class="acordeon_interior_contenido" style="display: block;">
					<div class="acordeon_titulo_contenedor_resumen" style="padding-left: 2%; width: 98%;">
						<table id="text_17" class="tabla_basica" cellspacing="0" cellpadding="0" style="width: 100%;">
							<caption id="text_18"> Fecha último cálculo: <c:out value="${form.anio}-${form.mes}-${form.dia}"></c:out> </caption>
							<tbody>
								<tr>
									<th align="left" style="text-align: left;">INDICE</th>
									<th>VALOR HOY</th>
									<c:if test="${resumenRF.mercado=='RENTA FIJA'}">
											<th>TIR</th>
											<th>DURACION</th>
									</c:if>
									<th>VALOR AYER</th>
									<th>
										VARIACION
										<br/>
										%
									</th>
									<th>
										VARIACION
										<br/>
										ABSOLUTA
									</th>
									<th>
										VARIACION
										<br/>
										12 MESES
									</th>
									<th>
										VARIACION
										<br/>
										AÑO
									</th>
								</tr>
								<tr>
									<td align="left"><c:out value="${indMetaData.nombreIndice}"/></td>
									<td align="center">
										<fmt:formatNumber pattern="###,##0.0000">
										<c:choose>
										  <c:when test="${resumenRF.mercado=='RENTA FIJA'}">
											<c:out value="${resumenRF.valorHoy}"/>
										  </c:when>	
										  <c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
											<c:out value="${resumenMM.valorHoy}"/>
										  </c:when>	
										  <c:otherwise>
										     <c:out value="${resumen.valorHoy}"/>
										  </c:otherwise>
										 </c:choose> 
										</fmt:formatNumber>
									</td>
									<c:if test="${resumenRF.mercado=='RENTA FIJA'}">
											<td align="center">
										<c:out value="${resumenRF.tir}"/>
									</td>
									</c:if>
									<c:if test="${resumenRF.mercado=='RENTA FIJA'}">
											<td align="center">
										<c:out value="${resumenRF.duracion}"/>
									</td>
									</c:if>
									<td align="center">
										<fmt:formatNumber pattern="###,##0.0000">
										<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
											<c:out value="${resumenRF.valorAyer}"/>
										  </c:when>	
										  <c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
											<c:out value="${resumenMM.valorAyer}"/>
										  </c:when>
										  <c:otherwise>
										     <c:out value="${resumen.valorAyer}"/>
										  </c:otherwise>
										  </c:choose>
										</fmt:formatNumber>
									</td>
									<td align="center">
										<fmt:formatNumber pattern="###,##0.0000">
											<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
											<c:out value="${resumenRF.variacionHoy}"/>
										  </c:when>	
										  <c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
											<c:out value="${resumenMM.variacionHoy}"/>
										  </c:when>	
										  <c:otherwise>
										     <c:out value="${resumen.variacionHoy}"/>
										  </c:otherwise>
										  </c:choose>
										</fmt:formatNumber>
										<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
												<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumenRF.variacionHoy}"/>);
												</script>
											</c:when>	
											<c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
												<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumenMM.variacionHoy}"/>);
												</script>
											</c:when>
										  <c:otherwise>
										   		<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumen.variacionHoy}"/>);
												</script>
										</c:otherwise>
										  </c:choose>
									</td>
									<td align="center">
										<fmt:formatNumber pattern="###,##0.0000">
											<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
											<c:out value="${resumenRF.variacionAbs}"/>
										  </c:when>	
										  <c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
											<c:out value="${resumenMM.variacionAbs}"/>
										  </c:when>	
										  <c:otherwise>
										     <c:out value="${resumen.variacionAbs}"/>
										  </c:otherwise>
										  </c:choose>
										</fmt:formatNumber>	
										<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
												<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumenRF.variacionAbs}"/>);
												</script>
											</c:when>	
											<c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
												<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumenMM.variacionAbs}"/>);
												</script>
											</c:when>
										  <c:otherwise>
										   		<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumen.variacionAbs}"/>);
												</script>
										</c:otherwise>
										  </c:choose>
									</td>
									<td align="center">
										<fmt:formatNumber pattern="###,##0.0000">
											<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
											<c:out value="${resumenRF.variacion12Meses}"/>
										  </c:when>	
										  <c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
											<c:out value="${resumenMM.variacion12Meses}"/>
										  </c:when>	
										  <c:otherwise>
										     <c:out value="${resumen.variacion12Meses}"/>
										  </c:otherwise>
										  </c:choose>
										</fmt:formatNumber>	
										<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
												<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumenRF.variacion12Meses}"/>);
												</script>
											</c:when>	
											<c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
												<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumenMM.variacion12Meses}"/>);
												</script>
											</c:when>
										  <c:otherwise>
										   		<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumen.variacion12Meses}"/>);
												</script>
										</c:otherwise>
										  </c:choose>
									</td>
									<td align="center">
										<fmt:formatNumber pattern="###,##0.0000">
											<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
											<c:out value="${resumenRF.variacionAnual}"/>
										  </c:when>	
										  <c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
											<c:out value="${resumenMM.variacionAnual}"/>
										  </c:when>	
										  <c:otherwise>
										     <c:out value="${resumen.variacionAnual}"/>
										  </c:otherwise>
										  </c:choose>
										</fmt:formatNumber>
										<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
												<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumenRF.variacionAnual}"/>);
												</script>
											</c:when>	
											<c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
												<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumenMM.variacionAnual}"/>);
												</script>
											</c:when>
										  <c:otherwise>
										   		<script type="text/javascript">
												ponerFlechaN(<c:out value="${resumen.variacionAnual}"/>);
												</script>
										</c:otherwise>
										  </c:choose>
									</td>
								</tr>
							</tbody>
						</table>
						<br clear="all"/>
						<div class="menu_tabla">
							<form id="formhistorico" action="/mercados/DescargaXlsServlet" method="get" name="formhistorico"
								>
								<c:choose>
											<c:when test="${resumenRF.mercado=='RENTA FIJA'}">
											<input type="hidden" name="tipoMI" value="<c:out value='${resumenRF.mercado}'/>"/>
										  </c:when>	
										  <c:when test="${resumenMM.mercado=='MERCADO MONETARIO'}">
											<input type="hidden" name="tipoMI" value="<c:out value='${resumenMM.mercado}'/>"/>
										  </c:when>	
										  <c:otherwise>
										     <input type="hidden" name="tipoMI" value="VARIABLE"/>
										  </c:otherwise>
										  </c:choose>
								
								<input type="hidden" name="archivo" value="indices"/>
								<input type="hidden" name="codIndice" value="<c:out value='${indMetaData.indice}'/>"/>
								<ul id="text_19">
									<li>
										<label>
											<span id="texto_1">Descargar Hist&oacute;rico desde: </span>
											<input type="text" name="fechaMin" style="width:83px"
												readonly="readonly" id="fechaMin" 
												onClick="return pintarCal('fechaMin', '%Y%m%d');"/>
										</label>
									</li>
									<li>
											<img  width="17" height="21" border="0" alt="cal" src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/images/calendario.gif"
												onClick="simularDesde('fechaMin', 1);" onMouseOver="simularDesde('fechaMin', 0);"
												style="cursor: pointer;"/>
									</li>
									<li>
										<label>
											<span id="texto_1">Hasta: </span>
											<input type="text" name="fechaMax" style="width:83px"
												readonly="readonly" id="fechaMax" 
												onClick="return pintarCal('fechaMax', '%Y%m%d');"/>
										</label>
									</li>
									<li>
											<img  width="17" height="21" border="0" alt="cal" src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/images/calendario.gif"
												onClick="simularHasta('fechaMax', 1);" onMouseOver="simularHasta('fechaMax', 0);"
												style="cursor: pointer;"/>
									</li>
									<li>
										<a id="texto_1" class="excel" href="#" onClick="validarFormFechas('fechaMin', 'fechaMax', 'yyyyMMdd', 'formhistorico', false);" >Descargar </a>
									</li>
								</ul>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="contenedor_acordeon_borde">
		<div class="acordeon_titulo_interior">
			<div class="acordeon_titulo_segmento">
				<p id="text_20" style="width: 50%;">Metodolog&iacute;a de c&aacute;lculo de los &iacute;ndices</p>
				<div id="ver_seg_2" class="ver_detalles_bvc" onclick=" mostrar_segmento_acordeon('seg_acc_2'); mostrar_segmento_acordeon('cerrar_seg_2'); ocultar_segmento_acordeon('ver_seg_2'); ocultar_segmento_acordeon('seg_acc_1'); ocultar_segmento_acordeon('seg_acc_3'); ocultar_segmento_acordeon('cerrar_seg_1'); ocultar_segmento_acordeon('cerrar_seg_3'); mostrar_segmento_acordeon('ver_seg_1'); mostrar_segmento_acordeon('ver_seg_3'); " style="display: block;"> Ver detalles</div>
				<div id="cerrar_seg_2" class="cerrar_detalle_bvc" onclick=" ocultar_segmento_acordeon('seg_acc_2'); ocultar_segmento_acordeon('cerrar_seg_2'); mostrar_segmento_acordeon('ver_seg_2'); " style="display: none"> cerrar </div>
			</div>
		</div>
	<div id="seg_acc_2" class="acordeon_interior_contenido" style="display: none;">
		<div id="text_21" class="merc_ind_det">
			<div class="merc_ind_det_int">
				<portlet:renderURL var="generalidades" >
                      <portlet:param name="action" value="contenido"/>
                      <portlet:param name="codIndice">
                          <jsp:attribute name="value">
                              <c:out value="${indMetaData.indice}"/>
                          </jsp:attribute>
                      </portlet:param>
					  <portlet:param name="fecha">
					  	<jsp:attribute name="value">
								<c:out value="${form.anio}${form.mes}${form.dia}"/>
					  	</jsp:attribute>
					  </portlet:param>
                      <portlet:param name="tipoContenido" value="generalidades"/>
                   </portlet:renderURL>
				<a href="<%=generalidades%>">Generalidades</a>
			</div>
			<div class="merc_ind_det_int">
				<portlet:renderURL var="formula" >
                      <portlet:param name="action" value="contenido"/>
                      <portlet:param name="codIndice">
                          <jsp:attribute name="value">
                              <c:out value="${indMetaData.indice}"/>
                          </jsp:attribute>
                      </portlet:param>
 					  <portlet:param name="fecha">
					  	<jsp:attribute name="value">
								<c:out value="${form.anio}${form.mes}${form.dia}"/>
					  	</jsp:attribute>
					  </portlet:param>
                      <portlet:param name="tipoContenido" value="formula"/>
                   </portlet:renderURL>
				<a href="<%=formula%>">F&oacute;rmula</a>
			</div>
			<div class="merc_ind_det_int">
				<portlet:renderURL var="seleccionCanasta" >
                      <portlet:param name="action" value="contenido"/>
                      <portlet:param name="codIndice">
                          <jsp:attribute name="value">
                              <c:out value="${indMetaData.indice}"/>
                          </jsp:attribute>
                      </portlet:param>
					   <portlet:param name="fecha">
					  	<jsp:attribute name="value">
								<c:out value="${form.anio}${form.mes}${form.dia}"/>
					  	</jsp:attribute>
					  </portlet:param>
                      <portlet:param name="tipoContenido" value="seleccionCanasta"/>
                   </portlet:renderURL>
				<a href="<%=seleccionCanasta%>">Selecci&oacute;n de Canasta</a>
				</div>
				<div class="merc_ind_det_int">
					<portlet:renderURL var="calculoPonderados" >
	                      <portlet:param name="action" value="contenido"/>
	                      <portlet:param name="codIndice">
	                          <jsp:attribute name="value">
	                              <c:out value="${indMetaData.indice}"/>
	                          </jsp:attribute>
	                      </portlet:param>
						   <portlet:param name="fecha">
						  	<jsp:attribute name="value">
									<c:out value="${form.anio}${form.mes}${form.dia}"/>
						  	</jsp:attribute>
						  </portlet:param>
	                      <portlet:param name="tipoContenido" value="calculoPonderados"/>
	                   </portlet:renderURL>
					<a href="<%=calculoPonderados%>">Calculo de Ponderadores</a>
				</div>
			</div>
		</div>
	</div>
	<br clear="all"/>
	<div class="contenedor_acordeon_borde">
		<div class="acordeon_titulo_interior">
			<div class="acordeon_titulo_segmento">
				<p id="text_22">Canasta</p>
				<div id="ver_seg_3" class="ver_detalles_bvc" onclick=" mostrar_segmento_acordeon('seg_acc_3'); mostrar_segmento_acordeon('cerrar_seg_3'); ocultar_segmento_acordeon('ver_seg_3'); ocultar_segmento_acordeon('seg_acc_1'); ocultar_segmento_acordeon('seg_acc_2'); ocultar_segmento_acordeon('cerrar_seg_1'); ocultar_segmento_acordeon('cerrar_seg_2'); mostrar_segmento_acordeon('ver_seg_1'); mostrar_segmento_acordeon('ver_seg_2'); " style="display: block;"> Ver detalles</div>
				<div id="cerrar_seg_3" class="cerrar_detalle_bvc" onclick=" ocultar_segmento_acordeon('seg_acc_3'); ocultar_segmento_acordeon('cerrar_seg_3'); mostrar_segmento_acordeon('ver_seg_3'); " style="display: none;"> cerrar </div>
			</div>     
		</div>
		<div id="seg_acc_3" class="acordeon_interior_contenido" style="display: none;">
		<div id="text_23" class="merc_ind_det">
			<div class="merc_ind_det_int">
				<div class="merc_ind_det_texto">Canasta Vigente</div>
				<div class="merc_ind_det_enlace">
					<a href="/mercados/DescargaCanastaServlet?path=<c:out value='${indMetaData.pathCanasta}'/>">Descargar Informaci&oacute;n </a>
					<img alt="Excel" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/ico_excel.gif"/>
				</div>
			</div>
			<div class="merc_ind_det_int">
				<div class="merc_ind_det_texto">Canasta Histórica</div>
				<div class="merc_ind_det_enlace">
					<a href="/mercados/DescargaCanastaServlet?path=<c:out value='${indMetaData.pathCanastaHist}'/>">Descargar Informaci&oacute;n </a>
					<img alt="Excel" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/ico_excel.gif"/> 
				</div>
			</div>
		</div>
	</div>
	</div>
	</c:if>