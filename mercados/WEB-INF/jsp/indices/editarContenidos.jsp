 <%@include file="../include.jsp" %>
 
<portlet:actionURL var="editarAction">
	<portlet:param name="action" value="editar" />
</portlet:actionURL>
 
 
 <div id="derivados" class="panelactivo_merc_divisas">
 	
 	<form id="indMetaData" method="post" action="<%=editarAction%>" >
 		<table>
 			<tr>
 				<td colspan="2">
 					<c:out value="${mensaje}"></c:out>			
				</td>
 			</tr>
 			<tr>
 				<td id="texto_1">
 					Indice:
 				</td>
 				<td>
 					<select name="indice" size="1" class="operacion" id="indice" style="width:170px">
		               		<c:forEach items="${listaIndices}" var="ind">
			                	<option value="<c:out value='${ind.key}'/>">
			                		<c:out value="${ind.value}"></c:out>
			                	</option>
			                </c:forEach>
		              </select>
 				</td>
 			</tr>
 			<tr>
 				<td id="texto_1">Generalidades:</td>
 				<td>
 					<textarea name="contenidoGeneralidades" rows="6" cols="30" id="contenidoGeneralidades">
 					</textarea>
 				</td>
 			</tr>
 			<tr>
 				<td id="texto_1">Formula:</td>
 				<td>
 					<textarea name="contenidoFormulas" rows="6" cols="30" id="contenidoFormulas">
 					</textarea>
 				</td>
 			</tr>
 			<tr>
 				<td id="texto_1">Selección de canasta:</td>
 				<td>
 					<textarea name="contenidoSeleccionCanasta" rows="6" cols="30" id="contenidoSeleccionCanasta">
 					</textarea>
 				</td>
 			</tr>
 			<tr>
 				<td id="texto_1">Calculo de ponderadores:</td>
 				<td>
 					<textarea name="contenidoCalculoPonderacion" rows="6" cols="30" id="contenidoCalculoPonderacion">
 					</textarea>
 				</td>
 			</tr>
 			<tr>
 				<td colspan="2" align="center">
 					<input type="submit" value="Enviar"/>
 				</td>
 			</tr>
 		</table>
 	</form>
 
 </div>