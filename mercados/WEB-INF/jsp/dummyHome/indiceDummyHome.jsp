<%@include file="../include.jsp" %>

<portlet:renderURL var="irHomeIndices">
	<portlet:param name="action" value="irHome" />
</portlet:renderURL>
    
<form method="POST" action="<%=irHomeIndices%>" id="enlaceAlHomeInd" ></form>
 
 
 <script type="text/javascript">
	var ele = document.getElementById("enlaceAlHomeInd");
	ele.submit();
 </script>