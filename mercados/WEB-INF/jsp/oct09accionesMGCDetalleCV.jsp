


<div class="acordeon_titulo_interior">
<div class="acordeon_titulo_segmento">
<p id="texto_26">Mercado Global - Operaciones de Compraventa</p>
<div id="text_ver_seg_2" class="ver_detalles_bvc"
	onclick="manejarAcordeon(2, 'detallar');" style="display: block;">
Ver detalles</div>
<div id="text_cerrar_seg_2" class="cerrar_detalle_bvc"
	onclick="manejarAcordeon(2, 'cerrar');" style="display: none;">
cerrar</div>
</div>

</div>

<div id="seg_acc_2" class="acordeon_interior_contenido"
	style="display: none;">
<div class="acordeon_titulo_contenedor_resumen">
<div class="acciones_encabezado_tabla">
	<table id="texto_33" class="tabla_basica" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<th width="40">PRECIO</th>
				<th width="110">CANTIDAD</th>
				<th width="111">VOLUMEN </th>
				<th width="60">TIPO DE OPERACIÓN</th>
				<th width="61">PROMOTOR LIQUIDEZ</th>
				<th width="58">CREACIÓN DEL MERCADO</th>
				<th width="76">FECHA DE CUMPLIMIENTO</th>
				<th width="70">MERCADO</th>
			</tr>
		</tbody>
	</table>
</div>
<div class="mercado_acciones_scroll">
<div class="mercado_acciones_scroll_interior">
	<table id="texto_27" class="tabla_basica" cellspacing="0" cellpadding="0">
		<tbody>
			<c:forEach items="${listaOperaciones}" var="operaciones">
				<tr>
					<td align="right" width="40">
						<fmt:formatNumber pattern="###,##0.00">
							<c:out value="${operaciones.precio}"/>
						</fmt:formatNumber>
					</td>
					<td align="right" width="110">
						<fmt:formatNumber pattern="###,##0.00">
							<c:out value="${operaciones.cantidad}"/>
						</fmt:formatNumber>
					</td>
					<td align="right" width="110">
						<fmt:formatNumber pattern="###,##0.00">
							<c:out value="${operaciones.volumen}"/>
						</fmt:formatNumber>
					</td>
					<td align="right" width="60">
						<c:out value="${operaciones.tipoOperacion}"/>
					</td>
					<td align="right" width="61">
						<c:out value="${operaciones.promotorLiquidez}"/>
					</td>	
					<td align="right" width="58">
					 	<c:out value="${operaciones.creadormercado}"/>
					</td>
					<td align="right" width="76">
						<fmt:formatDate value="${operaciones.fechaCumpliento}"
						  pattern="yyyy-MM-dd"/> 
					</td>
					<td align="right" width="71">
						<c:out value="${operaciones.mercado}"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>
</div>
</div>