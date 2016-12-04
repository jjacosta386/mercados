<%@include file="../include.jsp" %>

<portlet:renderURL var="irHomeAccionBVC">
	<portlet:param name="action" value="irHome" />
</portlet:renderURL>
    
<form method="POST" action="<%=irHomeAccionBVC%>" id="enlaceAlHomeAccionBVC" ></form>
 
 
 <script type="text/javascript">
	var ele = document.getElementById("enlaceAlHomeAccionBVC");
	ele.submit();
 </script>   