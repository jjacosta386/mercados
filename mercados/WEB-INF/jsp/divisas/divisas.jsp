<%@ include  file="buscadorDivisa.jsp"%>

<script type="text/javascript">     
	function mostrarDetalle(num){
		MM_changeProp('deuda_public' + num,'','display','block','DIV');
		MM_changeProp('cerrar_public' + num,'','display','block','DIV');
		MM_changeProp('ver_public'+ num,'','display','none','DIV');
	}

	function ocultarDetalle(num){
		MM_changeProp('deuda_public' + num,'','display','none','DIV');
		MM_changeProp('cerrar_public' + num,'','display','none','DIV');
		MM_changeProp('ver_public' + num,'','display','block','DIV');
	}

	function manejarAcordeonDivisas (numElem, abrir){
		var otroSeg = 3-numElem;
		if (abrir){
			mostrar_segmento_acordeon('seg_acc_' + numElem); 
			mostrar_segmento_acordeon('text_cerrar_seg_' + numElem); 
			ocultar_segmento_acordeon('text_ver_seg_' + numElem); 
			ocultar_segmento_acordeon('seg_acc_' + otroSeg); 
			ocultar_segmento_acordeon('text_cerrar_seg_' + otroSeg); 
			mostrar_segmento_acordeon('text_ver_seg_' + otroSeg);
		}else{
			ocultar_segmento_acordeon('seg_acc_' + numElem); 
			ocultar_segmento_acordeon('text_cerrar_seg_' + numElem); 
			mostrar_segmento_acordeon('text_ver_seg_' + numElem);
		}
	}

	function activar_setFX(boton){
		document.getElementById(boton).className="setFXactivo";
	}

	function inactivar_setFX(boton){
		document.getElementById(boton).className="setFXinactivo";
	}
	
</script>                

<div id="text_7" class="titulo_seccion_unacolumna">
	Mercado de Divisas	
</div>
<div id="nota_titulo">
	<h3> </h3>
	<h2>Información con <c:out value="${retraso}" /> minutos de retraso</h2>
</div>
<br clear="all"/>
<div class="derivados_detalle_tabla_contenedor">
	<div class="derivados_detalle_tabla">
		<table id="text_8" class="tabla_general_mercado_divisas" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<th width="25%">Mercado</th>
					<th width="30%">Volumen (COP)</th>
					<th width="30%">Participación %</th>
					<th align="center" width="15%">
						<c:if test="${esHoy}">
							<div id="text_btn_ver" class="btn_ver" onclick=" MM_changeProp('tabla_derivados_superior','','display','block','DIV'); MM_changeProp('text_btn_cerrar','','display','block','DIV'); MM_changeProp('text_btn_ver','','display','none','DIV'); ">
								<a id= "text_var_link" href="#">
									Ver detalles
								</a>
								<img border="0" alt="Ver" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/flecha_inactivo.gif"/>
							</div>
							<div id="text_btn_cerrar" class="btn_cerrar" onclick=" MM_changeProp('tabla_derivados_superior','','display','none','DIV'); MM_changeProp('text_btn_cerrar','','display','none','DIV'); MM_changeProp('text_btn_ver','','display','block','DIV'); ">
								<a id= "text_cerrar_link" href="#">
									Cerrar									
								</a>
								<img border="0" alt="Cerrar" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/flecha_activo.gif"/>
							</div>
						</c:if>
					</th>
				</tr>
			</tbody>
		</table>
	</div>
	<c:if test="${esHoy}">
		<div id="tabla_derivados_superior" class="derivados_detalle_tabla">
			<table id="text_9" class="tabla_general_mercado_divisas" cellspacing="0" cellpadding="0">
				<tbody>
					<c:forEach items="${resumenMercado}" var="item">
						<tr>
							<th class="specblue" align="left" width="25%"><c:out value="${item.key}"/></th>
							<td align="right" width="30%">
								<fmt:formatNumber pattern="###,##0.00">
									<c:out value="${item.value}"/>
								</fmt:formatNumber>
							</td>
							<td align="right" width="30%">
								<c:if test="${totalMercados != 0}">
									<fmt:formatNumber pattern="###,##0.00">
										<c:out value="${(item.value/totalMercados) * 100}"/>
									</fmt:formatNumber>%
								</c:if>
								<c:if test="${totalMercados == 0}">
									0%
								</c:if>
							</td>
							<td width="15%"> </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:if>
	<div id="tabla_derivados_superior2" class="derivados_detalle_tabla">
		<table id="text_12" class="tabla_general_mercado_divisas" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<c:if test="${esHoy}">
						<th class="specalt" align="left" width="25%">Total</th>
					</c:if>
					<c:if test="${!esHoy}">
						<th class="specalt" align="left" width="25%">Total Registro</th>
					</c:if>
					<td class="alt" align="right" width="30%">
						<fmt:formatNumber pattern="###,##0.00">
							<c:out value="${totalMercados}"/>
						</fmt:formatNumber>
					</td>
					<td class="alt" align="right" width="30%">100.00%</td>
					<td class="alt" width="15%"> </td>
				</tr>
			</tbody>
		</table>
	</div>
	<br clear="all"/>
</div>
<div class="contenedor_acordeon_borde" style="border-bottom:0px;">
	<div class="contenedor_acordeon_sin_borde">
		<div id="acordeon_contenedor">
			<div class="acordeon_titulo_interior">
				<div class="acordeon_titulo_segmento">
					<p id="text_10">SET - FX <c:out value="${detalleSetFx.tipoMercado}"/>  <c:out value="(${fecha})"/> </p>
					<div id="text_ver_seg_1" class="ver_detalles_bvc" style="display: none;" onclick="manejarAcordeonDivisas(1, true);"> 
						Ver detalles
					</div>
					<div id="text_cerrar_seg_1" class="cerrar_detalle_bvc" style="display: block;" onclick="manejarAcordeonDivisas(1, false);"> 
						cerrar 
					</div>
				</div>
			</div>
			<div id="seg_acc_1" class="acordeon_interior_contenido" style="display: block;">
				<div class="contenedor_resumen" style="width:100%">
					<table cellspacing="0" cellpadding="0" border="0" width="100%">
						<tbody>
							<tr>
								<td>
									<div class="mercado_divisas_grafica" id="graficaDivisas">
										<script type="text/javascript">
											var graficoDivisa = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf", "amstockDivisa", "530", "280", "8", "#FFFFFF");
											
											graficoDivisa.addVariable("path", "");
											graficoDivisa.addVariable("settings_file",
													escape("/mercados/GraficosServlet?conf=dolar&url=/amstock/settings/divisas_stock_settings.xml&tipoG=DIVISAS"));
											graficoDivisa.addVariable("chart_id", "amstockDivisa");
											graficoDivisa.addVariable("preloader_color", "#999999");
											licenciaGrafica(graficoDivisa);
											graficoDivisa.write('graficaDivisas');
										</script>       
									</div>
								</td>
								<td valign="top">
									<div class="mercado_divisas_precios_volumenes">
										<div class="tab_contenedor_divisas">
											<div class="tab_botones_contenedor_divisas">

													<div id="text_setFXprecios" class="setFXactivo" onclick="activar_setFX('text_setFXprecios'); inactivar_setFX('text_setFXvolumenes');MM_showHideLayers('volumenes','','hide','precios','','show');">
														<a id="sitio_set" name="sitio_set" href="#">Precios</a>
													</div>


													<div id="text_setFXvolumenes" class="setFXinactivo" onclick="activar_setFX('text_setFXvolumenes'); inactivar_setFX('text_setFXprecios');MM_showHideLayers('precios','','hide','volumenes','','show')" style="padding:0 4px;">
														<a href="#">Vol&uacute;menes (USD)</a>
													</div>

											</div>	
											<div class="tab_contenido_contactenos_divisas" style="height:172px">
												<div id="precios" class="paneles_home_contenido_contactenos_divisas" style="visibility: visible; overflow:hidden; width:257px;">
													<table id="text_11" class="mercado_divisas_tabla_pv" cellspacing="0" cellpadding="0" border="0" style="width:251px;">
														<tbody>
															<tr class="mercado_divisas_tabla_pv_resaltado">
																<td>Precio apertura</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.precioApertura}"></c:out>
																	</fmt:formatNumber>
																</td>
																
															</tr>
															<tr>
																<td>Precio &uacute;ltimo</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.precioUltimo}"></c:out>
																	</fmt:formatNumber>
																</td>
															</tr>
															<tr class="mercado_divisas_tabla_pv_resaltado">
																<td>Precio promedio</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.precioPromedio}"></c:out>
																	</fmt:formatNumber>
																</td>
															</tr>
															<tr>
																<td>Precio m&iacute;nimo</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.precioMinimo}"></c:out>
																	</fmt:formatNumber>
																</td>
															</tr>
															<tr class="mercado_divisas_tabla_pv_resaltado">
																<td>Precio m&aacute;ximo</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.precioMaximo}"></c:out>
																	</fmt:formatNumber>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
												<div id="volumenes" class="paneles_home_contenido_contactenos_divisas" style="visibility: none; overflow:hidden; width:257px;">
													<table id="text_18" class="mercado_divisas_tabla_pv" cellspacing="0" cellpadding="0" border="0" style="width:251px;">
														<tbody>
															<tr>
																<td>Volumen negociado</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.volumenNegociado}"></c:out>
																	</fmt:formatNumber>
																</td>
															</tr>
															<tr>
																<td>Volumen &uacute;ltimo</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.volumenUltimo}"></c:out>
																	</fmt:formatNumber>
																</td>
															</tr>
															<tr class="mercado_divisas_tabla_pv_resaltado">
																<td>Volumen promedio</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.volumenPromedio}"></c:out>
																	</fmt:formatNumber>
																</td>
															</tr>
															<tr>
																<td>Volumen m&iacute;nimo</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.volumenMinimo}"></c:out>
																	</fmt:formatNumber>
																</td>
															</tr>
															<tr class="mercado_divisas_tabla_pv_resaltado">
																<td>Volumen m&aacute;ximo</td>
																<td align="right">
																	<fmt:formatNumber pattern="###,##0.00">
																		<c:out value="${detalleSetFx.volumenMaximo}"></c:out>
																	</fmt:formatNumber>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="mercado_divisas_set">
											<a href="http://www.set-fx.com/" target="_blank"> 
												<img border="0" alt="SETFX" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/set_mercados.jpg"/>
											</a>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="mercado_divisas_grafica"/></div>
				</div>
			</div>
			<div class="acordeon_titulo_interior">
				<div class="acordeon_titulo_segmento">
					<p id="text_17">Operaciones de Registro</p>
					<div id="text_ver_seg_2" class="ver_detalles_bvc" onclick="manejarAcordeonDivisas(2, true);" style="display: block;">
						Ver detalles
					</div>
					<div id="text_cerrar_seg_2" class="cerrar_detalle_bvc" onclick="manejarAcordeonDivisas(2, false);" style="display: none;"> 
						cerrar 
					</div>
				</div>
			</div>
			<div id="seg_acc_2" class="acordeon_interior_contenido" style="display: none;">
				<div class="acordeon_titulo_contenedor_resumen">
					<table id="text_21" class="tabla_basica" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<th width="160">DIVISA</th>
								<th width="100">MERCADO</th>
								<th width="150">MONTO EN DÓLARES</th>
								<th>MONTO EN PESOS</th>
								<th width="120"/>
							</tr>
						</tbody>
					</table>
				</div>
				<c:forEach items="${detalleDivisas}" var="det" varStatus="stat">
					<div class="acordeon_titulo_contenedor_resumen">
						<table id="text_19" class="tabla_general" cellspacing="0" cellpadding="0" align="left" style="border:0px">
							<tbody>
								<tr>
									<td align="center" width="160"><c:out value="${det.key.divisa}"/></td>
									<td align="center" width="100"><c:out value="${det.key.mercado}"></c:out></td>
									<td align="right" width="150">
										<fmt:formatNumber pattern="###,##0.00">
											<c:out value="${det.key.montoDolares}"/>
										</fmt:formatNumber>
									</td>
									<td align="right">
										<span class="alt">
											<fmt:formatNumber pattern="###,##0.00">
												<c:out value="${det.key.monto}"/>
											</fmt:formatNumber>
										</span>
									</td>
									<td align="center" width="120" style="border-right:0px">
										<div id="cerrar_public<c:out value="${stat.index}"/>" class="renta_btn_cerrar" onclick="ocultarDetalle(<c:out value="${stat.index}"/>);" style="display: none">
											Cerrar
											<img border="0" alt="Cerrar" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/ico_cerrar.gif"/>
										</div>
										<div id="ver_public<c:out value="${stat.index}"/>" class="renta_btn" onclick="mostrarDetalle(<c:out value="${stat.index}"/>);" style="display: block" >
											Ver detalle
											<img border="0" alt="Cerrar" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/ico_cerrar.gif"/>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id='deuda_public<c:out value="${stat.index}"/>' class="renta_acordeon_interior" style="display: none;">
						<div class="mercado_acciones_scroll_superior">
							<div class="acciones_encabezado_tabla">
								<table id="text_13" class="tabla_basica" cellspacing="0" cellpadding="0">
									<tbody>
										<tr>
											<th width="150">tasa de cambio</th>
											<th width="100">fecha liquidaci&oacute;n</th>
											<th width="150">monto en dolares</th>
											<th>monto en pesos</th>
											<th width="88"/>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="mercado_acciones_scroll">
								<div class="mercado_acciones_scroll_interior">
									<table id="text_15" class="tabla_basica" cellspacing="0" cellpadding="0">
										<tbody>
											<c:forEach items="${det.value}" var="oper">
												<tr>
													<td align="center" width="150">
														<fmt:formatNumber pattern="###,##0.00">
															<c:out value="${oper.tasa}"/>
														</fmt:formatNumber>
													</td>
													<td align="center" width="100">
														<c:out value="${oper.fechaLiquidacion}"/>
													</td>
													<td align="right" width="150">
														<span class="alt">
															<fmt:formatNumber pattern="###,##0.00">
																<c:out value="${oper.montoDolares}"/>
															</fmt:formatNumber>
														</span>
													</td>
													<td align="right">
														<fmt:formatNumber pattern="###,##0.00">
															<c:out value="${oper.monto}"/>
														</fmt:formatNumber>
													</td>
													<td width="88"/>
												</tr>
											</c:forEach>	
										</tbody>
									</table>
								</div>
								<br clear="all"/>
							</div>
							<br clear="all"/>
						</div>
						<br clear="all"/>
					</div>	
				</c:forEach>
				<br clear="all"/>
			</div>
			<br clear="all"/>
		</div>		
		<br clear="all"/>
	</div>
	<br clear="all"/>
</div>