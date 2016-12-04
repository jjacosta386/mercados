
<div class="acordeon_titulo_interior">
<div class="acordeon_titulo_segmento">
<p id="texto_26">Operaciones Repos</p>
<div id="ver_seg_2" class="ver_detalles_bvc"
	onclick="manejarAcordeon(2, 'detallar');" style="display: block;">
Ver detalles</div>
<div id="cerrar_seg_2" class="cerrar_detalle_bvc"
	onclick="manejarAcordeon(2, 'cerrar');" style="display: none;">
cerrar</div>
</div>
</div>

<div id="seg_acc_2" class="acordeon_interior_contenido"
	style="display: none;">
<div class="acordeon_titulo_contenedor_resumen">
<div class="acciones_encabezado_tabla">
	<table id="operaciones" class="tabla_basica">
		<thead>
			<tr>
				<th width="85">TASA</th>
				<th width="110">CANTIDAD</th>
				<th width="110">VOLUMEN</th>
				<th width="110">VOLUMEN RECOMPRA</th>
				<th width="96">FECHA REPO</th>
				<th width="75">MERCADO</th>
			</tr>
		</thead>
	</table>
</div>
<div class="mercado_acciones_scroll">
<div class="mercado_acciones_scroll_interior">

<table id="texto_27" class="tabla_basica" cellspacing="0" cellpadding="0">
		<tbody>
			<c:forEach items="${listaOperaciones}" var="operaciones">
				<tr>
					<td align="right" width="85">
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
					<td align="right" width="110">
						<fmt:formatNumber pattern="###,##0.00">
							<c:out value="${operaciones.volumenRecompra}"/>
						</fmt:formatNumber>
					</td>
					<td align="right" width="96">
						<fmt:formatDate value="${operaciones.fechaCumpliento}"
						  pattern="dd-MM-yy"/>
					</td>	
					<td align="right" width="75">
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