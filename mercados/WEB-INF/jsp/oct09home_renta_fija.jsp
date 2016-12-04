<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type='text/javascript' src='/mercados/js/rentaFijaHome.js'></script>
<script>     
	function cambiarDeuda(id, id2){
		var obj = document.getElementById(id);
		var obj2 = document.getElementById(id2);
		  if(obj.style.display == "block"){
			   obj.style.display = "none";
			   obj2.style.display = "block";
		  }  else {
			  obj2.style.display = "none";
			  obj.style.display = "block";
		  }
	}
         manejarAcordeonRenta = function(){
                if ( "acordeon_resumen_activo" ==
                     document.getElementById('titulo_3').getAttribute("class")
                    || "acordeon_resumen_activo" ==
                     document.getElementById('titulo_3').getAttribute("className")
                    )
                {
                mostrar_segmento_acordeon_inactivo('titulo_3');
                }
                else {
                mostrar_segmento_acordeon_activo('titulo_3');
                }

                MM_effectBlind('seg_res_3', 500, '0%', '100%', true);
        }


	setTimeout("recargarHomeRentaFija()", TIEMPO_RECARGA);

</script>


	<div class="acordeon_titulo_interior">
          <div id="titulo_3" class="acordeon_titulo_resumen_mercados" 
          onclick="manejarAcordeonRenta();">

<p>Renta fija</p>
</div>
<div class="contenedor_tabla" style="padding:5px 0px 5px 0px; height:auto">
<table class="resumen_tabla" cellpadding="0" cellspacing="0" border="0px">
	<tbody>
		<tr class="resumen_indices_tabla_datos_fila_resaltada">
		     <td align="left" class="resumen_tabla_fila_resaltada"
		      style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid;padding:5px 5px 5px 5px"><strong>Volumen Transado</strong></td>

			<td id="textoVolumenTransadasRentaFija" align="right" style="background-color:#FFFFFF;border-bottom:#DBDBDB 1px solid; padding-right:5px">
			<fmt:formatNumber pattern="###,###.00">
				<c:out value="${rentaFijaTransadas}" />
			</fmt:formatNumber></td>
		</tr>
		<tr>
		 <td align="left" class="resumen_tabla_fila_resaltada" 
		 style="background-color:#FFFFFF;padding:5px 5px 5px 5px"><strong>Volumen Registrado</strong></td>

			<td id="textoVolumenRegistradoRentaFija" align="right" style="background-color:#FFFFFF; padding-right:5px">
			<fmt:formatNumber pattern="###,###.00">
				<c:out value="${rentaFijaRegistrado}" />
			</fmt:formatNumber></td>
		</tr>
	</tbody>
</table>
</div>
</div>



<div id="seg_res_3" class="acordeon_interior_contenido" style="padding:0px; margin:0px">
<div class="acordeon_titulo_contenedor_resumen_mercado">
 
<div class="resumen_info_contenedor">


<div id="textoFechaRentaFija" class="resumen_info_titulo" style="padding: 0px 0px 0px 5px; display:block; float:left; position:relative; height:20px; line-height:20px; font-size:11px">
	<c:out value="${horarioRentaFija}"/>
</div>
    <div class="resumen_info"
     style="padding: 0px 5px 0px 0px; display:block; float:right; position:relative; height:20px; line-height:20px; font-size:11px">Retraso: 20 minutos</div>
</div>


   <div class="resumen_select_1" style="padding-top:5px">
<label> <select
	name="opcionesDeuda" class="resumen_select_1_valores" id="opcionesDeuda" onChange="cambiarDeuda('rf_publica', 'rf_privada'); ">
	<option value="1">Deuda Pública</option>
	<option value="2">Deuda Privada</option>
</select> </label></div>


<div class="contenedor_tabla" >
<div id="rf_publica" style="display:block" >
	<table class="resumen_tabla" cellspacing="0" cellpadding="0" id="titulos">
		<thead>
			<tr>
				<td style="border-right: 1px solid rgb(219, 219, 219); background-color: rgb(239, 237, 238);">
					Título
				</td>
				<td style="border-right: 1px solid rgb(219, 219, 219); background-color: rgb(239, 237, 238);">
					Volumen
				</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rentaFija.especies5Publica}" var="rfAct" varStatus="statInt">
				<tr id="rentafija<c:out value='${statInt.index}'/>" 
				  onmouseout=" resumen_indice_inactivo('rentafija' + <c:out value='${statInt.index}'/>);" 
				  onmouseover=" resumen_indice_activo('rentafija' + <c:out value='${statInt.index}'/>);" 
				  style="cursor: pointer;">
					<td style="border-bottom: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(255, 255, 255);">
						<a id="textoNemoDeudaPublica<c:out value='${statInt.index}'/>" href="/pps/tibco/portalbvc/Home/Mercados/enlinea/rentafija?com.tibco.ps.pagesvc.action=portletAction&action=detalle&nemo=<c:out value="${rfAct.nemotecnico}"/>">
							<c:out value="${rfAct.nemotecnico}" />
						</a>
					</td>
					<td id="textoVolumenDeudaPublica<c:out value='${statInt.index}'/>" align="right" style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255); padding-right:5px">
						<fmt:formatNumber pattern="###,###.00">
							<c:out value="${rfAct.volumen}" />
						</fmt:formatNumber>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div id="rf_privada" style="display:none" >
	<table class="resumen_tabla" cellspacing="0" cellpadding="0" id="titulos">
		<thead>
			<tr>
				<td style="border-right: 1px solid rgb(219, 219, 219); background-color: rgb(239, 237, 238);">
					Título
				</td>
				<td style="border-right: 1px solid rgb(219, 219, 219); background-color: rgb(239, 237, 238);">
					Volumen
				</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rentaFija.especies5Privada}" var="rfAct" varStatus="statInt">
				<tr id="rentafija1<c:out value='${statInt.index}'/>" 
				  onmouseout=" resumen_indice_inactivo('rentafija1' + <c:out value='${statInt.index}'/>);" 
				  onmouseover=" resumen_indice_activo('rentafija1' + <c:out value='${statInt.index}'/>);" 
				  style="cursor: pointer;">
					<td style="border-bottom: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(255, 255, 255);">
						<a id="textoNemoDeudaPrivada<c:out value='${statInt.index}'/>" href="/pps/tibco/portalbvc/Home/Mercados/enlinea/rentafija?com.tibco.ps.pagesvc.action=portletAction&action=detalle&nemo=<c:out value="${rfAct.nemotecnico}"/>">
							<c:out value="${rfAct.nemotecnico}" />
						</a>
					</td>
					<td id="textoVolumenDeudaPrivada<c:out value='${statInt.index}'/>" align="right" style="border-bottom: 1px solid rgb(219, 219, 219); background-color: rgb(255, 255, 255); padding-right:5px">
						<fmt:formatNumber pattern="###,###.00">
							<c:out value="${rfAct.volumen}" />
						</fmt:formatNumber>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>


<div class="resumen_informacion_detallada"><a href="/pps/tibco/portalbvc/Home/Mercados/enlinea/rentafija?action=dummy">Información
detallada</a></div>
</div>
</div>
