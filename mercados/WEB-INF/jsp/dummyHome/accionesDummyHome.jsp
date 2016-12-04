<%@include file="../include.jsp" %>

<portlet:renderURL var="irHomeAcciones">
	<portlet:param name="action" value="irHome" />
</portlet:renderURL>
    
<form method="POST" action="<%=irHomeAcciones%>" id="enlaceAlHomeAcc" ></form>
 
 
 <script type="text/javascript">
	var ele = document.getElementById("enlaceAlHomeAcc");
	ele.submit();
 </script>