<%@include file="../include.jsp" %>

<portlet:renderURL var="irHomeRFija">
	<portlet:param name="action" value="irHome" />
</portlet:renderURL>
    
<form method="POST" action="<%=irHomeRFija%>" id="enlaceAlHomeRF" ></form>
 
 
 <script type="text/javascript">
	var ele = document.getElementById("enlaceAlHomeRF");
	ele.submit();
 </script>