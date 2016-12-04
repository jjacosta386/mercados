<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript" src="/mercados/js/accionesHome.js"></script>
<script>
	 cambiarAcciones= function() {
		var obj = document.getElementById("lista_de_acciones_0");//negociadas
		var obj2 = document.getElementById("lista_de_acciones_1");//suben
		var obj3 = document.getElementById("lista_de_acciones_2");//bajan

		var lista = document.getElementById("filtroAcciones");
		
		if (lista.value=="1") {
			obj.style.display = "block";
			obj2.style.display = "none";
			obj3.style.display = "none";
			opcionAcciones = 0;
		} else if (lista.value=="2") {
			obj.style.display = "none";
			obj2.style.display = "block";
			obj3.style.display = "none";
			opcionAcciones = 1;
		} else {
			obj.style.display = "none";
			obj2.style.display = "none";
			obj3.style.display = "block";
			opcionAcciones = 2;
		}
	}

	manejarAcordeonAcciones = function(){
                if ( "acordeon_resumen_activo" ==
                     document.getElementById('titulo_1').getAttribute("class")
                    || "acordeon_resumen_activo" ==
                     document.getElementById('titulo_1').getAttribute("className")
                    )
                {
                mostrar_segmento_acordeon_inactivo('titulo_1');
                }
                else {
                mostrar_segmento_acordeon_activo('titulo_1');
                }

		MM_effectBlind('seg_res_1', 500, '0%', '100%', true);	
	}

	setTimeout("recargarHomeAcciones()", TIEMPO_RECARGA);
</script>

<div class="acordeon_titulo_interior">
	<div id="titulo_1" class="acordeon_titulo_resumen_mercados" onclick="manejarAcordeonAcciones();">
    	<p>Acciones</p>
    </div>
	<div class="contenedor_tabla" style="padding:5px 0px 5px 0px">
		<div class="resumen_titulo2" style="padding-left: 5px;">
			<strong>Volumen </strong>
		</div>
		<div id="textoVolumenResumenAccionesHome" class="resumen_titulo1" style="padding-right:5px; text-align:right">
			<c:out value="${volumenAcciones}" />
		</div>
	</div>
</div>
<div id="seg_res_1" class="acordeon_interior_contenido">
	<div class="acordeon_titulo_contenedor_resumen_mercado">
		<div class="resumen_info_contenedor">
			<div id="textHorarioAccionesHome" class="resumen_info_titulo" style="padding: 0px 0px 0px 5px; display:block; float:left; position:relative; height:20px; line-height:20px; font-size:11px">
				<c:out value="${horarioAcciones}" /> 
			</div>
			<div class="resumen_info" style="padding: 0px 1px 0px 0px; display: block; float: right; position: relative; height: 20px; line-height: 20px; font-size: 11px;">
				Retraso: 20 minutos
			</div>
		</div>
		<div class="resumen_select_1" style="padding-top:5px;">
			<label>
				<select class="resumen_select_1_valores" onChange="cambiarAcciones();" id="filtroAcciones" >
					<option value="1">Las más negociadas</option>
					<option value="2">Las más valorizadas</option>
					<option value="3">Las más desvalorizadas</option>
				</select>
			</label>
		</div>
		
		<c:forEach items="${listas}" var="listaAct" varStatus="stat">
			<div class="contenedor_tabla" id="lista_de_acciones_<c:out value='${stat.index}'/>" style="display: none; height: 170px;">
				<table class="resumen_tabla" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							<td style="border-right: 1px solid rgb(219, 219, 219); padding: 5px 0px 5px 2px; background-color: rgb(239, 237, 238);">
								Acción
							</td>
							<td style="border-right: 1px solid rgb(219, 219, 219); background-color: rgb(239, 237, 238);">
								Volumen *
							</td>
							<td style="border-right: 1px solid rgb(219, 219, 219); background-color: rgb(239, 237, 238);">
								Precio
								<br/>
								Cierre
							</td>
							<td style="background-color: rgb(239, 237, 238);">
								Variación
								<br/>
								%
							</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaAct.value}" var="accAct" varStatus="statInt">
							<tr id="acciones<c:out value='${stat.index}'/><c:out value='${statInt.index}'/>" 
							  onmouseout=" resumen_indice_inactivo('acciones' + <c:out value='${stat.index}'/> +  <c:out value='${statInt.index}'/>);" 
							  onmouseover=" resumen_indice_activo('acciones' + <c:out value='${stat.index}'/> + <c:out value='${statInt.index}'/>);" 
							  style="cursor: pointer;">
								<td style="border-bottom: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(255, 255, 255);">
									<a id="texto<c:out value='${listaAct.key}'/>Nemo<c:out value='${statInt.index}'/>" href="/pps/tibco/portalbvc/Home/Mercados/enlinea/acciones?com.tibco.ps.pagesvc.action=portletAction&action=detalleAccion&nemoTecnico=<c:out value='${accAct.nemoTecnico}'/>&tipoMercado=1">
										<c:out value="${accAct.nemoTecnico}" />
									</a>
								</td>
								<td id="texto<c:out value='${listaAct.key}'/>Volumen<c:out value='${statInt.index}'/>" align="right" style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255);">
									<fmt:formatNumber pattern="###,##0.00">
										<c:out value="${accAct.volumen/1000000}" />
									</fmt:formatNumber>
								</td>
								<td id="texto<c:out value='${listaAct.key}'/>Precio<c:out value='${statInt.index}'/>" align="right" style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255);">
									<fmt:formatNumber pattern="###,##0.00">
										<c:out value="${accAct.ultimoPrecio}" />
									</fmt:formatNumber>
								</td>
								<td id="texto<c:out value='${listaAct.key}'/>Variacion<c:out value='${statInt.index}'/>" align="right" style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255); padding-right: 5px;">
									<fmt:formatNumber pattern="###,##0.00">
										<c:out value="${accAct.variacion}" />
									</fmt:formatNumber>
									<c:choose>
										<c:when test="${accAct.variacion gt 0}">
											<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif" alt="Sube"/>
										</c:when>
										<c:when test="${accAct.variacion lt 0}">
											<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif" alt="Baja"/>
										</c:when>
										<c:otherwise>
											<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual"/>											
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>				
			</div>
		</c:forEach>

		<div class="resumen_informacion_detallada" style="padding-right:5px">
			<a href="/pps/tibco/portalbvc/Home/Mercados/enlinea/acciones?action=dummy">
				Información detallada
			</a>
		</div>
		<div style="padding: 0px 0px 0px 5px; font-family: Arial,Helvetica,sans-serif; font-size: 11px; width: 200px; display: block; float: left; position: relative;">
			* Datos en millones de pesos
		</div>
	</div>
</div>

<script  type="text/javascript">
	cambiarAcciones();
</script>
