<%@include file="../include.jsp" %>

<%@ include file="buscadorIndice.jsp"%>

<div style="width: 100%" id="divUnica" >
	
</div>
<script>
	ele = document.getElementById('divUnica');
	ele.innerHTML = '<%=request.getAttribute("contenido")%>';
</script>