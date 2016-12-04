<%@include file="../include.jsp"%>

<portlet:actionURL var="formBuscarAction">
	<portlet:param name="action" value="buscar" />
</portlet:actionURL>


<div class="tab_contenedor_mercados">
	<div class="tab_contenido_mercados">
		
			<form name="forma" id="form" method="post" action="<%=formBuscarAction%>">
			     <div id="divisas" class="panelactivo_merc_divisas">
				<ul class="mercados">
					<li class="fechacen">
						<label style="width: auto;"> 
							<span id="text_6">Fecha de Registro :</span> 
							<span class="fechalista"> 
								<select name="dia" size="1" id="dia">
					              	<c:forEach items="${dias}" var="dia">
					                	<option value="<c:out value='${dia.key}'/>"
					                		<c:if test="${form.dia == dia.key}">
				              					selected="selected"
				              				</c:if>	
					                	>
					                		<c:out value="${dia.value}"></c:out>
					                	</option>
					                </c:forEach>
					             </select>
							</span> 
						 </label>
						 <label style="width: auto;">
							<span class="fechalista"> 
								<select name="mes" size="1" id="mes">
					                <c:forEach items="${meses}" var="mes">
					                	<option value="<c:out value='${mes.key}'/>"
					                		<c:if test="${form.mes == mes.key}">
				              					selected="selected"
				              				</c:if>
					                	>
					                		<c:out value="${mes.value}"></c:out>
					                	</option>
					                </c:forEach>
					             </select>
							</span>
						  </label>
						  <label style="width: auto;">	 
							<span class="fechalista"> 
								<select name="anio" size="1" id="anio">
					                <c:forEach items="${anios}" var="anio">
					                	<option value="<c:out value='${anio.key}'/>"
					                		<c:if test="${form.anio == anio.key}">
				              					selected="selected"
				              				</c:if>
					                	>
					                		<c:out value="${anio.value}"></c:out>
					                	</option>
					                </c:forEach>
					             </select>
							</span> 
						</label>
						<div id="contenedor_buscador">
							<div class="boton_regular">
								<a href="javascript:document.forma.submit();">Buscar</a>
							</div>
						</div>
					</li>
				</ul>
				</div>
			</form>
		
	</div>
	<div class="limpiar" /></div>
</div>
<br clear="all"/>