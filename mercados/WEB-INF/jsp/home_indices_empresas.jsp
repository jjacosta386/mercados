<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type='text/javascript' src='/mercados/js/indicesHome.js'></script>

            <div class="home_empresas_indices" style="width: 238px; float: right; padding-left: 0px; margin-left:0px;" >
            <div  style="width: 100%; display:block; float:left; position:relative;">
            	<div class="accordion_toggle" style="display:block; font-weight:normal; height:auto; margin:0; outline-color:-moz-use-text-color; outline-style:none; outline-width:medium; overflow:hidden; padding:0; text-decoration:none; width:100%;">
                	<div class="acordeon_resumen_activo"><p>Indices</p></div>
           	  </div>
                   		<div style="display: block; float: left; position: relative; width: 100%;">
                          <div class="tabla_indices">
                                 <table class="resumen_tabla" cellpadding="0" cellspacing="0">
                                   <thead>
                                     <tr>
                                       <th height="33">&nbsp;</th>
                                       <th>(Unidades)</th>
                                       <th>Variaci&oacute;n</th>
                                     </tr>
                                   </thead>
                                   <tbody>
                                  		<c:forEach items="${indiceResumen}" var="indice" varStatus="stat">
														<tr class="resumen_indices_tabla_datos_fila_resaltada">
					                                       <td class="resumen_tabla_fila_resaltada"><strong><c:out value="${indice.nombreIndice}" /></strong></td>
					                                       <td align="center" ><fmt:formatNumber pattern="###,##0.00">
																	<c:out value="${indice.valorHoy}" />
																</fmt:formatNumber></td>
					                                       <td align="right"><fmt:formatNumber pattern="###,##0.00">
																	<c:out value="${indice.variacionHoy}" />
																</fmt:formatNumber>%
																<c:choose>
																	<c:when test="${indice.variacionHoy gt 0}">
																		<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif" alt="Sube"/>
																	</c:when>
																	<c:when test="${indice.variacionHoy lt 0}">
																		<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif" alt="Baja"/>
																	</c:when>
																	<c:otherwise>
																		<img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif" alt="Igual"/>
																	</c:otherwise>
																</c:choose></td>
					                                     </tr>
										</c:forEach>
                                   </tbody>
                                 </table>
                          </div>
                    	</div>
            </div>
            </div><!-- Cierra home_empresas_indices -->
            <br clear="all"/>

