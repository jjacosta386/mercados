<%@ include file="includeCalendario.jsp"%>
<script type='text/javascript' src='/mercados/dwr/interface/intradiaAjax.js'></script>
<script type='text/javascript' src='/mercados/js/tab/tabber.js'></script>
<link rel="stylesheet" href="/mercados/css/tabs/example.css" TYPE="text/css" MEDIA="screen">

<script>

function timerIntradiaEnJSP() {
}

</script>

  <!-- InstanceBeginEditable name="area_trabajo_central" -->
	<div id="tab_contenedor_mercados">
		<div class="tab_contenido_mercados">
			<portlet:actionURL var="formAction">
    			<portlet:param name="action" value="buscarDetalle"/>
			</portlet:actionURL>
			<form id="busqueda" action="<%=formAction%>" method="post" name="formDerivadosDetalle">
				<input name="nemo" type="hidden" id="nemoDetalle" value='<c:out value="${nemo}"/>'/>
				<div id="derivados" class="panelactivo_merc_divisas">
					<ul class="mercados">
            			<li>
              				<label><span id="text_1">Tipo de contrato :</span>
              					<select name="tipoContrato" size="1" class="operacion" id="operacion" onchange="desactivaSelect(this.value,'tipo','O');">
									<option value="" selected="selected">Todos</option>
									<c:forEach items="${tiposContrato}" var="tc" >
										<option <c:if test="${busquedaDerivados.tipoContrato==tc.key}">selected="selected"</c:if> value='<c:out value="${tc.key}"/>'><c:out value="${tc.value}"/></option>
									</c:forEach>
								</select>
              				</label>
            			</li>
            			<li class="fechaneg">
              				<label>
								<span id="text_2">Fecha de negociación :</span>
								<span class="fechalista">
	              					<select name="diaFecha" size="1" id="derivados_dia">
										<c:forEach items="${dias}" var="dia">
											<option <c:if test="${busquedaDerivados.diaFecha==dia.key}">selected="selected"</c:if> value='<c:out value="${dia.key}"/>'><c:out value="${dia.value}"/></option>
										</c:forEach>
									</select>
              					 </span>             					
							 </label>
							 <label>
                                <span class="fechalista">
									<select name="mesFecha" size="1" id="derivados_mes">
										<c:forEach items="${meses}" var="mes">
											<option <c:if test="${busquedaDerivados.mesFecha==mes.key}">selected="selected"</c:if> value='<c:out value="${mes.key}"/>'><c:out value="${mes.value}"/></option>
										</c:forEach>
									</select>
              					</span>              					
                              </label>
              				  <label>
                             	 <span class="fechalista">
              						<select name="anioFecha" size="1" id="derivados_ano">
										<c:forEach items="${anios}" var="anio">
											<option <c:if test="${busquedaDerivados.anioFecha==anio.key}">selected="selected"</c:if> value='<c:out value="${anio.key}"/>'><c:out value="${anio.value}"/></option>
										</c:forEach>
									</select>
              					 </span>
							  </label>
            			</li>
            			<li>
              				<label><span id="text_3">Tipo de opci&oacute;n:</span>
								<select name="tipoOpcion" size="1" class="operacion" id="tipo" 
									<c:if test="${busquedaDerivados.tipoContrato ne 'O'}">
										disabled="disabled"
									</c:if>
								>
									<option value="" selected="selected">Todos</option>
									<option value="C" <c:if test="${busquedaDerivados.tipoOpcion=='C' }">selected="selected"</c:if>>CALL</option>
									<option value="P" <c:if test="${busquedaDerivados.tipoOpcion=='P' }">selected="selected"</c:if>>PUT</option>
								</select>
                			</label>
            			</li>
            			<li>
              				<label><span id="text_4">Filtrar por :</span>
              					<select name="operacion" size="1" class="operacion" id="caso2" disabled="disabled">
                					<option>OPT1</option>
                					<option>OPT2</option>
                					<option>OPT3</option>
              					</select>
              				</label>
            			</li>
            			<li>
              				<label><span id="text_5">Consultar otro contrato :</span>
								<c:if test="${busquedaDerivados.contrato ne null and  busquedaDerivados.contrato ne ''}">
									<input name="contrato" type="text" id="contrato" value='<c:out value="${busquedaDerivados.contrato}"/>'/>
								</c:if>
								<c:if test="${busquedaDerivados.contrato eq null or busquedaDerivados.contrato eq ''}">
									<input name="contrato" type="text" id="contrato" value='<c:out value="${nemo}"/>'/>
								</c:if>
                			</label>
            			</li>
            			<li class="fechaneg">
              				<div class="boton_regular"> <a href="javascript:document.formDerivadosDetalle.submit();">Buscar</a></div>
            			</li>
          			</ul>
				</div>
			</form>
		</div>
		<div class="limpiar"></div>
	</div>
  		
	<br clear="all" />
	<div class="titulo_seccion_unacolumna" id="texto_2">
	<c:out value="${resumenContrato.contrato}"/> <c:out value="${nemo}"/>
    </div>
  	<div id="nota_titulo">
    	<h3>&nbsp;</h3>
    	<h2 id="texto_9">Informaci&oacute;n con <c:out value="${retraso}" /> minutos de retraso</h2>
	</div>
	
	<br clear="all" />
	
	<div class="grafica_mercado_accionistas" id="texto_3">
    
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
        		<td class="detalles_accionista_td8">
					<div class="tabber" title="gráfica">
						<div class="tabbertab"  title="Diaria">
							<div id="texto_grafica_dia" style="height: 100%">
								<script type="text/javascript">
									var derivadoGraficoDia = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf",
											"amstock", "500", "300", "8", "#FFFFFF");
									derivadoGraficoDia.addVariable("path", "");
									derivadoGraficoDia
											.addVariable(
													"settings_file",
													escape('/mercados/GraficosServlet?conf=<c:out value="${nemo}"/>&url=/amstock/settings/derivados_drvx_hoy_stock_settings.xml&tipoG=DERIVADOS&tiempo=hoy&opcf=<c:out value="${opcf}"/>'));
									derivadoGraficoDia.addVariable("preloader_color", "#999999");
									derivadoGraficoDia.addVariable("chart_id", "amstockDia");
									licenciaGrafica(derivadoGraficoDia);
									derivadoGraficoDia.write('texto_grafica_dia');
								</script>
							</div>
						</div>
						<div class="tabbertab"  title="Histórica">
							<div id="texto_grafica_hist" style="height: 100%">
								<script
									type="text/javascript">
									var derivadoGraficoHist = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf",
											"amstock", "500", "300", "8", "#FFFFFF");
									derivadoGraficoHist.addVariable("path", "");
									derivadoGraficoHist
											.addVariable(
													"settings_file",
													escape('/mercados/GraficosServlet?conf=<c:out value="${nemo}"/>&url=/amstock/settings/derivados_drvx_stock_settings.xml&tipoG=DERIVADOS&tiempo=ayer&opcf=<c:out value="${opcf}"/>'));
									derivadoGraficoHist.addVariable("preloader_color", "#999999");
									derivadoGraficoHist.addVariable("chart_id", "amstockHist");
									licenciaGrafica(derivadoGraficoHist);
									derivadoGraficoHist.write('texto_grafica_hist');
								</script>
							</div>
						</div>
					</div>
				</td>
      		</tr>
    	</table>
	</div>
    
    <br clear="all" />
    
    <div class="contenedor_acordeon_borde">
    	<div class="contenedor_acordeon_sin_borde">
    
    		<div id="acordeon_contenedor">
    			<div class="acordeon_titulo_interior">
      				<div class="acordeon_titulo_segmento">
        				<p id="texto_8">Resumen del mercado</p>
        				<div id="text_ver_seg_1" class="ver_detalles_bvc" onclick="
					        mostrar_segmento_acordeon('seg_acc_1');
					        mostrar_segmento_acordeon('text_cerrar_seg_1');
					        ocultar_segmento_acordeon('text_ver_seg_1');
					        ocultar_segmento_acordeon('seg_acc_2');
					        ocultar_segmento_acordeon('seg_acc_3');
					        ocultar_segmento_acordeon('text_cerrar_seg_2');
					        ocultar_segmento_acordeon('text_cerrar_seg_3');
					        mostrar_segmento_acordeon('text_ver_seg_2');
					        mostrar_segmento_acordeon('text_ver_seg_3');        
							" style="display:none"> Ver detalles
						</div>
        				<div id="text_cerrar_seg_1" class="cerrar_detalle_bvc" onclick="
        					ocultar_segmento_acordeon('seg_acc_1');
        					ocultar_segmento_acordeon('text_cerrar_seg_1');
        					mostrar_segmento_acordeon('text_ver_seg_1');
        					" style="display:block"> cerrar 
						</div>
      				</div>
      				<br clear="all"/>
    			</div>
    			<div id="seg_acc_1" class="acordeon_interior_contenido" style="display:block"> 
    				<div class="acordeon_titulo_contenedor_resumen">     
						<div class="nombre_contrator"><c:out value="${nemo}"/></div>
						<div class="fecha_contrator">Ultima operaci&oacute;n: <c:out value="${resumenContrato.ultimaOperacion}"/></div>
					</div>
      				<div class="acordeon_titulo_contenedor_resumen">

				        <table cellpadding="0" cellspacing="0" class="tabla_basica_derivados_detalle" id="texto_7">

				        	<tr>
				            	<th>No. contratos</th>
								<c:if test="${opcf ne 'opcf'}">
				            		<th>open interest</th>
								</c:if>
								<c:if test="${opcf eq 'opcf'}">
				            		<th>vencimiento</th>
								</c:if>
				            	<th>volumen (COP)</th>
								<th>volumen (MWH)</th>
								<c:if test="${opcf ne 'opcf'}">
				            		<th>&Uacute;ltimo Precio</th>
								</c:if>
					
								<c:if test="${opcf eq 'opcf'}">
									<th>&Uacute;ltimo Precio</th>
								</c:if>
				            	<th>variacion %</th>
				          	</tr>
				          	<tr>
				            	<td align="right" ><c:out value="${resumenContrato.contratos}"/></td>
								<c:if test="${opcf ne 'opcf'}">
				            		<td align="right"><c:out value="${resumenContrato.openInterest}"/></td>
								</c:if>
								<c:if test="${opcf eq 'opcf'}">
				            		<td align="center"><c:out value="${nemo}"/></td>
								</c:if>
				            	<td align="right"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${resumenContrato.volumen}"  /> </fmt:formatNumber></td>
				            	<td align="right"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${resumenContrato.volumenMwh}"  /> </fmt:formatNumber></td>
				            	<td align="right"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${resumenContrato.precio}"  /> </fmt:formatNumber></td>
				            	<td align="right"><fmt:formatNumber pattern="#0.00" > <c:out value="${resumenContrato.variacion}"  /> </fmt:formatNumber>
									<c:choose>
										<c:when test="${resumenContrato.variacion gt 0}">
											<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/greenup.gif" alt="Sube"/>
										</c:when>
										<c:when test="${resumenContrato.variacion eq 0}">
											<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual"/>
										</c:when>
										<c:otherwise>
											<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/reddown.gif" alt="Baja"/>
										</c:otherwise>
									</c:choose>
								</td>
				          	</tr>
				          	<tr>
				            	<th align="center" >Precio mayor</th>    
				            	<th align="center" >precio menor</th>
				            	<th align="center" >precio apertura</th>
				            	<th align="center" >mejor bid</th>
				            	<th align="center" >mejor offer</th>
				            	<th align="center" > </th>
				          	</tr>
				          	<tr>
				            	<td align="right" ><fmt:formatNumber pattern="###,##0.00" > <c:out value="${resumenContrato.precioMayor}"  /> </fmt:formatNumber></td>
				            	<td align="right"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${resumenContrato.precioMenor}"  /> </fmt:formatNumber></td>
				            	<td align="right"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${resumenContrato.precioApertura}"  /> </fmt:formatNumber></td>
				            	<td align="right"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${resumenContrato.mejorBid}"  /> </fmt:formatNumber></td>
				            	<td align="right"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${resumenContrato.mejorOffer}"  /> </fmt:formatNumber></td>
				          	</tr>
        				</table>
        				<div class="menu_tabla">
							<form id="formhistorico" action="/mercados/DescargaXlsServlet"
								method="post" name="formhistorico">
								<input type="hidden" name="archivo" value="derivados_detalle_drvx" /> 
								<input type="hidden" name="nemo" value="<c:out value='${nemo}'/>" /> 
								<input type="hidden" name="opcf"	value="<c:out value='${opcf}'/>" />
								<ul id="text_19">
									<li><label> <span id="texto_1">Descargar Historico desde: </span> <input
										type="text" name="fechaIni" style="width: 83px" readonly="readonly"
										id="fechaIni"  
										onClick="return pintarCal('fechaIni', '%Y-%m-%d');"/>
									</label></li>
									<li><img width="17" height="21" border="0" alt="cal"
										src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/images/calendario.gif"
										onClick="simularDesde('fechaIni', 1);" onMouseOver="simularDesde('fechaIni', 0);"
										style="cursor: pointer;" /></li>
									<li><label> <span id="texto_100">Hasta: </span> <input type="text"
										name="fechaFin" style="width: 83px" readonly="readonly" id="fechaFin"
										onClick="return pintarCal('fechaFin', '%Y-%m-%d');"/>
									</label></li>
									<li><img width="17" height="21" border="0" alt="cal"
										src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/images/calendario.gif"
										onClick="simularHasta('fechaFin', 1);" onMouseOver="simularHasta('fechaFin', 0);"
										style="cursor: pointer;" /></li>
									<li><a id="texto_40" class="excel"
										href="#" onClick="validarFormFechas('fechaIni', 'fechaFin', 'yyyy-MM-dd', 'formhistorico', true);">Descargar </a></li>
								</ul>
							</form>
						</div>
      				</div>
      				<br clear="all"/>
    			</div>
	    		<div class="acordeon_titulo_interior">
	      			<div class="acordeon_titulo_segmento">
	        			<p id="texto_4">Operaciones</p>
						<div id="text_ver_seg_2" class="ver_detalles_bvc" onclick="
					        mostrar_segmento_acordeon('seg_acc_2');
					        mostrar_segmento_acordeon('text_cerrar_seg_2');
					        ocultar_segmento_acordeon('text_ver_seg_2');
					        ocultar_segmento_acordeon('seg_acc_1');
					        ocultar_segmento_acordeon('seg_acc_3');
					        ocultar_segmento_acordeon('text_cerrar_seg_1');
					        ocultar_segmento_acordeon('text_cerrar_seg_3');
					        mostrar_segmento_acordeon('text_ver_seg_1');
					        mostrar_segmento_acordeon('text_ver_seg_3');        
					        "> Ver detalles
						</div>
	        			<div id="text_cerrar_seg_2" class="cerrar_detalle_bvc" onclick="
					        ocultar_segmento_acordeon('seg_acc_2');
					        ocultar_segmento_acordeon('text_cerrar_seg_2');
					        mostrar_segmento_acordeon('text_ver_seg_2');
					        "> cerrar 
						</div>
	      			</div>
	      			<br clear="all"/>
	    		</div>
	    		<div id="seg_acc_2" class="acordeon_interior_contenido">
	    			<div class="acciones_encabezado_tabla">
	                	<table width="100%" cellpadding="0" cellspacing="0" class="tabla_basica" id="texto_10">
					  		<tr>
								<th width="100">HORA DE OPERACI&Oacute;N</th>
					    		<th width="200">PRECIO</th>
					    		<th width="250">No. DE CONTRATOS</th>
					    		<th>VOLUMEN (MWh)</th>
					  		</tr>
						</table>
	    			</div>
	      			<div class="acordeon_titulo_contenedor_resumen">
	            		<div class="mercado_acciones_scroll">
	
	      					<div class="mercado_acciones_scroll_interior">
	        					<table width="100%" cellpadding="0" cellspacing="0" class="tabla_basica" id="texto_5">
	          						<c:forEach items="${derivadosResumenDia}" var="drd" varStatus="stat">
										<tr>
											<td width="100" align="right" style="padding: 6px;"><c:out value="${drd.hora}"/></td>
							            	<td width="200" align="right" style="padding: 6px;"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${drd.precio}"/></fmt:formatNumber></td>
							            	<td width="250" align="right" style="padding: 6px;"><c:out value="${drd.contratos}"/></td>
							            	<td align="right" style="padding: 6px;"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${drd.volumen}"/></fmt:formatNumber></td>
							  			</tr>
	          						</c:forEach>
								</table>
	        				</div>
	        				<br clear="all" />
	        			</div>
	        			<br clear="all"/>
	      			</div>
	      			<br clear="all"/>
	    		</div>
				<c:if test="${opcf ne 'opcf'}">
		    		<div class="acordeon_titulo_interior">
		      			<div class="acordeon_titulo_segmento">
		        			<p id="texto_6">Caracter&iacute;sticas del contrato</p>
							<div id="text_ver_seg_3" class="ver_detalles_bvc" onclick="
						        mostrar_segmento_acordeon('seg_acc_3');
						        mostrar_segmento_acordeon('text_cerrar_seg_3');
						        ocultar_segmento_acordeon('text_ver_seg_3');
						        ocultar_segmento_acordeon('seg_acc_1');
						        ocultar_segmento_acordeon('seg_acc_2');
						        ocultar_segmento_acordeon('text_cerrar_seg_1');
						        ocultar_segmento_acordeon('text_cerrar_seg_2');
						        mostrar_segmento_acordeon('text_ver_seg_1');
						        mostrar_segmento_acordeon('text_ver_seg_2');        
						        "> Ver detalles
							</div>
					        <div id="text_cerrar_seg_3" class="cerrar_detalle_bvc" onclick="
						        ocultar_segmento_acordeon('seg_acc_3');
						        ocultar_segmento_acordeon('text_cerrar_seg_3');
						        mostrar_segmento_acordeon('text_ver_seg_3');
						        "> cerrar 
							</div>
							<br clear="all" />
		      			</div>
		      			<br clear="all"/>
		    		</div>
		    		<br clear="all"/>
		    		<div id="seg_acc_3" class="acordeon_interior_contenido">
		      			<div class="acordeon_titulo_contenedor_resumen">
		        			<table id="texto_9" width="100%" border="0" cellspacing="0" cellpadding="0" class="detalles_texto_tabla_accionistas">
				          		<tr>
				            		<td class="detalles_accionista_td1">Contrato:</td>
				            		<td class="detalles_accionista_td2"><c:out value="${caracteristicasContrato.contrato}"/></td>
				            		<td class="detalles_accionista_td1">Fecha de vencimiento:</td>
				            		<td class="detalles_accionista_td2"><fmt:formatDate value="${caracteristicasContrato.fechaVencimiento}" pattern="dd/MM/yyyy"/></td>
				          		</tr>
					          	<tr>
					            	<td class="detalles_accionista_td1">Estado:</td>
					            	<td class="detalles_accionista_td2"><c:out value="${caracteristicasContrato.estado}"/></td>
					            	<td class="detalles_accionista_td1">Valor nominal:</td>
					            	<td class="detalles_accionista_td2"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${caracteristicasContrato.valorNominal}"/></fmt:formatNumber></td>
					          	</tr>
					          	<tr>
					            	<td class="detalles_accionista_td1">Canasta de entregables:</td>
					            	<td class="detalles_accionista_td2"><c:out value="${caracteristicasContrato.canastaEntregables}"/></td>
					            	<td class="detalles_accionista_td1">Factor de conversi&oacute;n.</td>
					            	<td class="detalles_accionista_td2"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${caracteristicasContrato.factorConversion}"/></fmt:formatNumber></td>
					          	</tr>
					          	<tr>
					            	<td class="detalles_accionista_td1">Strike:</td>
					            	<td class="detalles_accionista_td2"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${caracteristicasContrato.strike}"/></fmt:formatNumber></td>
					            	<td class="detalles_accionista_td1">Movimiento m&iacute;nimo:</td>
					            	<td class="detalles_accionista_td2"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${caracteristicasContrato.movimientoMinimo}"/></fmt:formatNumber></td>
					          	</tr>
					          	<tr>
					            	<td class="detalles_accionista_td1">Ciclo del contrato:</td>
					            	<td class="detalles_accionista_td2"><c:out  value="${caracteristicasContrato.ciclo}"/></td>
					            	<td class="detalles_accionista_td1">Fecha de inscripci&oacute;n:</td>
					            	<td class="detalles_accionista_td2"><fmt:formatDate value="${caracteristicasContrato.fechaInscripcion}" pattern="dd/MM/yyyy"/></td>
					          	</tr>
					          	<tr>
					            	<td class="detalles_accionista_td1">Tipo de opci&oacute;n:</td>
					            	<td class="detalles_accionista_td2"><c:out value="${caracteristicasContrato.tipoOpcion}"/></td>
					            	<td class="detalles_accionista_td1">Tipo de opci&oacute;n:</td>
					            	<td class="detalles_accionista_td2"><c:out value="${caracteristicasContrato.tipo}"/></td>
					          	</tr>
					          	<tr>
					            	<td class="detalles_accionista_td1">M&aacute;ximo transaccional:</td>
					            	<td class="detalles_accionista_td2"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${caracteristicasContrato.maximoTransaccional}"/></fmt:formatNumber></td>
					            	<td class="detalles_accionista_td1">Tipo de liquidaci&oacute;n:</td>
					            	<td class="detalles_accionista_td2"><c:out value="${caracteristicasContrato.tipoLiquidacion}"/></td>
					          	</tr>
					          	<tr>
					            	<td class="detalles_accionista_td1">M&iacute;nimo de registro:</td>
					            	<td class="detalles_accionista_td2"><fmt:formatNumber pattern="###,##0.00" > <c:out value="${caracteristicasContrato.minimoRegistro}"/></fmt:formatNumber></td>
					            	<td class="detalles_accionista_td1">&nbsp;</td>
					            	<td class="detalles_accionista_td2">&nbsp;</td>
					          	</tr>
		        			</table>
		      			</div>
		      			<br clear="all"/>
		    		</div>
		    		<br clear="all" />
				</c:if>
    		</div>
    		<br clear="all"/>
  		</div>
  		<br clear="all"/>
	</div>
	