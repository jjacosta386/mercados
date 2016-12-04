

<form id="formhistorico" action="/mercados/DescargaXlsServlet"
	method="get" name="formhistorico">
	<input type="hidden" name="archivo" value="acciones_detalle" /> 
	<input type="hidden" name="nemo" value="<c:out value='${titulo.nemotecnico}'/>" /> 
	<input type="hidden" name="tipoMercado"	value="<c:out value='${formulario.tipoMercado}'/>" />
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
	<li><label> <span id="texto_2">Hasta: </span> <input type="text"
		name="fechaFin" style="width: 83px" readonly="readonly" id="fechaFin"
		onClick="return pintarCal('fechaFin', '%Y-%m-%d');"/>
	</label></li>
	<li><img width="17" height="21" border="0" alt="cal"
		src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/images/calendario.gif"
		onClick="simularHasta('fechaFin', 1);" onMouseOver="simularHasta('fechaFin', 0);"
		style="cursor: pointer;" /></li>
	<li><a id="texto_3" class="excel"
		href="#" onClick="validarFormFechas('fechaIni', 'fechaFin', 'yyyy-MM-dd', 'formhistorico', true);">Descargar </a></li>
</ul>
</form>