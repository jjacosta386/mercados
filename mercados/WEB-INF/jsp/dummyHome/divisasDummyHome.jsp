<%@include file="../include.jsp" %>

<portlet:renderURL var="irHomeDivisas">
	<portlet:param name="action" value="irHome" />
</portlet:renderURL>
    
<form method="POST" action="<%=irHomeDivisas%>" id="enlaceAlHomeDiv" ></form>
 
 
 <script type="text/javascript">
	var ele = document.getElementById("enlaceAlHomeDiv");
	ele.submit();
 </script>