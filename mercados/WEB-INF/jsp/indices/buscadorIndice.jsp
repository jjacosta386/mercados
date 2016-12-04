<script type="text/javascript">
<!--
	function enviarFormularioDesdeEnlace(codigo){
		ele = document.getElementById('codigoIndice');
		ele.value = codigo;
		lme = document.getElementById('mercadono');
	    mer = document.getElementById('mercadoIn');
	    for (i=0; i<lme.options.length;i++) {
            if(lme.options[i].value==ele.value){
            	if(lme.options[i].text=="N/A"){
	    			mer.value="all";
	    			return true;
	    	   }else{
	    		mer.value=lme.options[i].text;
	    		return true;
	    	   }
	    	
           }   
        }
		return true; 		
	}
	function SelMercado(sel){
	    cod = sel.options[sel.selectedIndex].value;
	    lme = document.getElementById('mercadono');
	    mer = document.getElementById('mercadoIn');
	    for (i=0; i<lme.options.length;i++) {
            if(lme.options[i].value==cod){
            	if(lme.options[i].text=="N/A"){
	    			mer.value="all";
	    			return true;
	    	   }else{
	    		mer.value=lme.options[i].text;
	    		return true;
	    	   }
	    	
           }   
        }
		
		 		
	 }
	 function optadd(){
			cod1 = document.getElementById('codigoIndice');
			 nome1 = document.getElementById('mercadono');
			 
			var z=1;
			if(nome1.options.length+1!=cod1.options.length){
			 
			   for (y=0; y<nome1.options.length;y++) {
				if (z<cod1.options.length) {
				  if(nome1.options[y].value!=cod1.options[z].value){
					var opt = document.createElement('option');
			        var selC = document.getElementById('codigoIndice');
					opt.text = nome1.options[y].className;
					opt.value = nome1.options[y].value;
					try {
						selC.add(opt, null); 
					}
					catch(ex) {
						selC.add(opt); 
					}
					
				  } else {z++; }
				} 
				else {
				    var opt = document.createElement('option');
			        var selC = document.getElementById('codigoIndice');
					opt.text = nome1.options[y].className;
					opt.value = nome1.options[y].value;
					try {
						selC.add(opt, null); 
					}
					catch(ex) {
						selC.add(opt); 
					}
				} 
			  }
			}
		 
		 }
		 function Selcod(mercado){
			 mer = mercado.options[mercado.selectedIndex].value;
			 cod = document.getElementById('codigoIndice');
			 nome = document.getElementById('mercadono');
			inme = document.getElementById('mercadoIn');
			if(mer!="all"){
			var arr = []; 
			arr.length=nome.options.length;
			for (i=0; i<nome.options.length;i++) {
				if(nome.options[i].text!=mer){
					arr.push(nome.options[i].value);
					
				} 
			  }
			for (x=0; x<arr.length;x++) {		
				for (j=1; j<cod.options.length;j++) {
					   if(arr[x]==cod.options[j].value){
						  cod.remove(j);
						  
					   }
					}
			 }   				
			}
					
		 }
		 
//-->
</script>

<portlet:actionURL var="formBuscarAction">
	<portlet:param name="action" value="buscar" />
</portlet:actionURL>

<div class="tab_contenedor_mercados">
   <div class="tab_contenido_mercados">
 
 	<form id="form" name="forma" method="post" action="<%=formBuscarAction%>">
        <div id="indices" class="panelactivo_merc_divisas">
          <ul class="mercados">
          	<li class="fechaneg" style="width: auto;">
              <label style="width: auto;">
                <span id="text_6" style="padding: 0px 10px; width: auto;">Fecha :</span>
                <span class="fechalista">
                     <select id="text_7" style="width: 40px;" size="1" name="dia">                     
              	       <c:forEach items="${dias}" var="dia">
              			<option 
              				<c:if test="${form.dia == dia.key}">
              					selected="selected"
              				</c:if>	
              				value="<c:out value='${dia.key}'/>">
    	            		<c:out value="${dia.value}"></c:out>
        	        	</option>
                         </c:forEach>
                     </select>
                </span>
              </label>
              <label style="width: auto;">
                 <span class="fechalista">
                 <select id="text_8" style="width: 40px;" size="1" name="mes">
                    <c:forEach items="${meses}" var="mes">
                	<option 
                		<c:if test="${form.mes == mes.key}">
              				selected="selected"
              			</c:if>
                		value="<c:out value='${mes.key}'/>">
                		<c:out value="${mes.value}"></c:out>
                	</option>
                </c:forEach>
                 </select>
                </span>
              </label>
              <label style="width: auto;">  
                 <span class="fechalista">
                 <select id="text_9" style="width: 70px;" size="1" name="anio">
                    <c:forEach items="${anios}" var="anio">
                	<option 
                		<c:if test="${form.anio == anio.key}">
              				selected="selected"
              			</c:if>
                		value="<c:out value='${anio.key}'/>">
                			<c:out value="${anio.value}"></c:out>
                	</option>
                   </c:forEach>
                 </select>
                 </span>
               </label>
            </li>
            <li style="width: auto; padding-left: 10px;">
              <label><span id="text_10" style="width:auto; padding:0px 10px 0px 10px">&Iacute;ndice</span>              
              <select id="codigoIndice" class="operacion" style="width: 170px;" size="1" name="codigoIndice" onChange="SelMercado(this)">
              		<option value="all">Todos los indices</option>
               		<c:forEach items="${listaIndices}" var="ind">
               			<option value="<c:out value='${ind.key}'/>"
               				<c:if test="${nombreIndice == ind.key}">
              					selected="selected"
              				</c:if>
              			>
	                		<c:out value="${ind.value}"></c:out>
	                	</option>
	                </c:forEach>
              </select>
                </label>
            </li>
            <li style="width: auto; padding-left: 10px;">
              <label><span id="text_10" style="width:auto; padding:0px 10px 0px 10px">Mercado</span>              
              <select id="mercadoIn" class="operacion" style="width: 150px;" size="1" name="mercadoIn" onChange="optadd();Selcod(this);">
              		<option value="all"
              		<c:if test="${nombreMercado == 'all'}">
              					selected="selected"
              				</c:if>
              		>Todos los mercados</option>
              		<option value="RENTA VARIABLE"
              		<c:if test="${nombreMercado == 'RENTA VARIABLE'}">
              					selected="selected"
              				</c:if>
              		>Renta Variable</option>
              		<option value="RENTA FIJA"
              		<c:if test="${nombreMercado == 'RENTA FIJA'}">
              					selected="selected"
              				</c:if>
              		>Renta Fija</option>
              		<option value="MERCADO MONETARIO"
              		<c:if test="${nombreMercado == 'MERCADO MONETARIO'}">
              					selected="selected"
              				</c:if>
              		>Mercado Monetario</option>
               	</select>
              <select id="mercadono" class="operacion" style="width: 1px;visibility:hidden;" size="1" name="mercadono">
              		  <c:forEach items="${listaIndicesMercado}" var="indm">
               			<option class="<c:out value='${indm.value}'/>"  value="<c:out value='${indm.clave}'/>"
               				<c:if test="${nombreIndice == indm.clave}">
              					selected="selected"
              				</c:if>
              			>
	                		<c:out value="${indm.mercado}"></c:out>
	                	</option>
	                </c:forEach>	                
              </select>
                </label>
            </li>
            <li class="fechaneg" style="width: 80px;">
            
              <div class="boton_regular">
              	<a href="javascript:document.forma.submit();" >Buscar</a>
              </div>
            </li>
          </ul>
          </div>
        </form>
     </div>
     <div class="limpiar" /></div>
  </div>     
<br clear="all"/>

