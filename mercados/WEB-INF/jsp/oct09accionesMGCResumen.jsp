<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ include file="/WEB-INF/jsp/accionesMGCEncabezado.jsp"%>

<!-- InstanceBeginEditable name="area_trabajo_central" -->


<br clear="all" />

<div class="titulo_seccion_unacolumna" id="text_2">
	<div class="subtitulo_mercados">Mercado Global Colombiano - Acciones y ETFs <b></b></div>
	<div class="subtitulo_mercados_pequeno"><c:out value="${horarioAcciones}" /></div>
</div>
<div id="nota_titulo">
	<h3>* Incluye operaciones de contado y excluidas</h3>
	<h2>Informaci&oacute;n con 20 minutos de retraso</h2>
</div>
<br clear="all" />

<%@ include file="/WEB-INF/jsp/accionesMGCTotales.jsp"%>

<br clear="all"/>


<div class="contenedor_acordeon_borde">
<%@ include file="/WEB-INF/jsp/accionesMGCResumenCV.jsp" %>


	<div class="contenedor_boton_imprimir">
		<table width="100%" border="0" cellspacing="0" cellpadding="10">
			<tr>
				<td width="45%">&nbsp;</td>
				<td width="10%">
					<!--div class="boton_regular"><a href="#">Imprimir</a></div-->
				</td>
				<td width="45%" align="right"><a id="texto_1" href="/mercados/DescargaMGCXlsServlet?archivo=accionesMGC&fecha=<c:out value='${formulario.fechaRequerida}'/>&resultados=100&tipoMercado=<c:out value='${tipoMercado}'/>&tipoInstrumento=<c:out value='${tipoInstrumento}'/>" class="excelneg">Descargar
					información de negociación</a></td>
			</tr>
		</table>
	</div>
</div>
<script type="text/javascript">
	cambiarAcciones();
</script>
