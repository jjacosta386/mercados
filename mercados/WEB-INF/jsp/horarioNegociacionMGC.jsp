<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div class="titulo_seccion_lisemi" id="texto_1">Horarios de Negociación</div>

<div id="texto_2_desc" class="text_contenido_general"> <p><style type="text/css"><!--body {font-family: Arial, Helvetica, sans-serif;font-size:12px;}--></style></p>
</div>
<p>&nbsp;</p>
<p style="text-align: center;">
</p><table width="524" border="1" cellpadding="0" cellspacing="0">
    <tbody>
    	
        <tr>
            <td valign="top" width="327">
            <div style="text-align: center;"><b>MERCADOS</b></div>
            </td>
            <td valign="top" width="98">
            <div style="text-align: center;"><b>Apertura</b></div>
            </td>
            <td valign="top" width="99">
            <div style="text-align: center;"><b>Cierre</b></div>
            </td>
        </tr>
        <c:forEach items="${horarioNegociacion}" var="horarioNegociacion">
	        <tr>
	            <td valign="top" width="327">
	            <div style="text-align: left;"><b>&nbsp; <c:out value="${horarioNegociacion.nombreMercado}"/></b></div>
	            </td>
	            <td width="98">
	            <div style="text-align: center;"><c:out value="${horarioNegociacion.horaInicio}"/></div>
	            </td>
	            <td width="99">
	            <div style="text-align: center;"><c:out value="${horarioNegociacion.horaFin}"/></div>
	            </td>
	        </tr>
	     </c:forEach>
        </tbody>
        </table>