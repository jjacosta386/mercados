<%@ include file="/WEB-INF/jsp/include.jsp"%>

<%@ include file="/WEB-INF/jsp/listadoEncabezadoMGC.jsp"%>


	
	  		<div class="lisemi_resultados_busqueda"  id="texto_24" style="width: 780px">
      			Resultados de la B&uacute;squeda
      		</div>
      		<br>
      		</br>
      		<br>
      		</br>
  			<div class="lisemi_resultados_tabla">
			<display:table  name="listaAccionesEtf" pagesize="30" htmlId="texto_28" cellpadding="0" cellspacing="0" class="tabla_general" id="textTitulos" style="width: 100%; border:0px;">			
			<display:column title="Emisor &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true" defaultorder="ascending"
				 class="specblue" style="text-align: center">
				<a href="/pps/tibco/portalbvc/Home/Mercado+Global/Listado+Emisores?com.tibco.ps.pagesvc.action=portletAction&com.tibco.ps.pagesvc.targetSubscription=-612b1fd1_12af04d0305_-7f74c0a85586&action=link&MGC=true&emisorId=<c:out value="${textTitulos.idEmisor}"/>">
					<c:out value="${textTitulos.razonSocial}" /></a>
			</display:column>
			
			<display:column title="Acci&oacute;n &nbsp;&nbsp;&nbsp;&nbsp;" media="html" sortable="true"
				 class="specblue" style="text-align: center">
				<a href="/pps/tibco/portalbvc/Home/Mercados/enlinea/accionesMGC?com.tibco.ps.pagesvc.action=portletAction&action=detalleAccion&nemoTecnico=<c:out value='${textTitulos.nemoTecnico}'/>&tipoMercado=2">
					<c:out value="${textTitulos.nemoTecnico}" /></a>
			</display:column>
			
			
			<display:column  title="ISIN &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				 style="text-align: right;" media="html">
				 <c:out value="${textTitulos.isin}" />
			</display:column>
			<display:column  title="País de Orígen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				 style="text-align: center;" media="html">
				 <c:out value="${textTitulos.pais}" />
			</display:column>
			<display:column  title="Moneda de Orígen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				 style="text-align: center;" media="html">
				 <c:out value="${textTitulos.moneda}" />
			</display:column>
			<display:column  title="Cotizaci&oacute;n Principal * &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				 style="text-align: center;" media="html">
				 <c:out value="${textTitulos.cotizacion}" />
			</display:column>
			<display:column  title="Fecha de Listado &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"
				 style="text-align: center;" media="html">
				 <c:out value="${textTitulos.fechaList}" />
			</display:column>
			<display:column  title="Patrocinador &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true" class="specblue"
				 style="text-align: center;" media="html">
			<a href="<c:out value='${textTitulos.pag_patro}'/>" target="_blank"
			onclick="alerta_salir();">	 
				 <c:out value="${textTitulos.patrocinador}" /></a>
			</display:column>
			
			
		</display:table> 
	</div>
	<div id="nota_titulo"><h3 style="width:100%">* Cotizaci&oacute;n principal inicial en el momento de la inscripci&oacute;n en la BVC por parte del patrocinador</h3></div>
	
	