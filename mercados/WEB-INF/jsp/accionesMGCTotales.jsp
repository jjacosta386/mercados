<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div class="derivados_detalle_tabla_contenedor">
	<div class="derivados_detalle_tabla">
		<table cellpadding="0" cellspacing="0" class="tabla_general_acciones" id="text_3">
			<tbody>
				<tr>
					<th width="35%" >Tipo de Instrumento</th>
					<th width="30%">Volumen (COP)</th>
					<th width="20%">Participaci&oacute;n %</th>
					<th width="15%">
						<div id="btn_cerrar" class="btn_cerrar" onclick="MM_changeProp('tabla_derivados_superior','','display','none','DIV'); MM_changeProp('btn_cerrar','','display','none','DIV'); MM_changeProp('btn_ver','','display','block','DIV'); " style="display: block;">
							<a href="#">
								Cerrar 
							</a>
							<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/flecha_activo.gif" alt="Drop" width="14" height="14" border="0"/>
						</div>
						<div id="btn_ver" class="btn_ver" onclick="MM_changeProp('tabla_derivados_superior','','display','block','DIV'); MM_changeProp('btn_cerrar','','display','block','DIV'); MM_changeProp('btn_ver','','display','none','DIV'); " style="display: none;">
							<a href="#">
								Ver detalles
							</a>
							<img border="0" alt="Ver" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/flecha_inactivo.gif"/>
						</div>
					</th>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="derivados_detalle_tabla" id="tabla_derivados_superior" style="display: block;">	
		<table cellpadding="0" cellspacing="0" class="tabla_general_acciones" id="text_15">
		<c:forEach items="${volumenAcciones}" var="vitem" >
			<tr>
				<th width="35%" align="left" class="specblue"><c:out value="${vitem.descripcionVolumen}" /></th>
				<td width="30%" align="right">

						<c:out value="${vitem.volumenFormato}" />
					
				</td>
				<td width="20%" align="right"><fmt:formatNumber pattern="###,###.##">
						<c:out value="${vitem.porcentajeParticipacion}" />
					</fmt:formatNumber>%</td>
				<td width="15%">&nbsp;</td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<br clear="all"/>
	<div class="derivados_detalle_tabla">
		<table id="text_16" class="tabla_general_acciones" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<th align="left" class="specalt"  width="35%">Total</th>
					<td align="right" class="alt" width="30%">
						<c:out value="${volumenTotal.volumenFormato}" />
					</td>
					<td align="right" class="alt" width="20%">100%</td>
					<td align="center" class="alt" width="15%"> &nbsp;</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>