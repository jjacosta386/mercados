<%@include file="../include.jsp" %>

<portlet:renderURL var="irHomeDerivados">
	<portlet:param name="action" value="irHome" />
</portlet:renderURL>
    
<form method="POST" action="<%=irHomeDerivados%>" id="enlaceAlHomeDer" ></form>
 
 
 <script type="text/javascript">
	var ele = document.getElementById("enlaceAlHomeDer");
	ele.submit();
 </script>