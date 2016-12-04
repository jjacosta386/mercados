
<%@ page language="java" import="java.io.IOException,
				java.lang.reflect.InvocationTargetException,
				java.lang.reflect.Method,
				java.net.InetAddress,
				java.sql.DriverManager,
				java.sql.Driver,
				java.util.Date,
				java.util.Enumeration,
				java.util.HashMap,
				java.util.Hashtable,
				java.util.Iterator,
				java.util.NoSuchElementException,
				java.util.Properties,
				java.util.SortedMap,
				java.util.StringTokenizer,
				java.util.TreeMap,
				com.tibco.ps.svcruntime.version.*,
                                java.util.List" %>

<%
  Portal product = new Portal(application);
  PortalService service = product.getCurrentService();
%>

<html><head><title>Printsys</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link href="/sta-pb/PortalTemplates/Root/System Sites/PBC/Templates/Skin/pbc/styles.css" rel="stylesheet" name="default" type="text/css">

</head>
<body>

<!-- before banner-area -->
<table class="banner-area">
<tbody>
<tr>
<th>&nbsp;</th>
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=(new Date()).toString()%></div><!-- after text --></td>
</tr>
</tbody></table>
<!-- after banner-area -->

<!-- before heading-area -->
<table class="heading-area">
<td>
<!-- before heading -->
<tr>
<th></th>
<td class="heading" align="center">TIBCO PortalBuilder - Configuration & Environment Details</th>
</tr>
<!-- after heading -->
</td>
</table>

<table class="table-body-heavy">
<!-- before vspace --><div class="vspace-large">&nbsp;</div>
<tr class="table-body-heavy">
<th class="table-body-heavy" valign="top">Portal Services currently Deployed</th>
<th class="table-body-heavy" valign="top">Version</th>
</tr>

<!-- before property -->
<tr class="property-sheet">
<%
  List allsvcs = product.getServices();
 
  for(int i=0; i<allsvcs.size(); i++){
   PortalService current_service = (PortalService)allsvcs.get(i);
   String name    = current_service.getName();
   String version = product.getVersion();
   String build	  = product.getBuild();   
%>
<tr class="table-body-heavy">
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=name%></div><!-- after text --></td>
<td class="table-body-heavy" valign="top" align="center">
<!-- before text --><div class="text"><%=version%> Build <%=build%></div><!-- after text -->

</td>
</tr>
<%
  }
%>
<!-- after property -->
</table>

<table class="table-body-heavy">
<!-- before vspace --><div class="vspace-large">&nbsp;</div>
<tr class="table-body-heavy">
<th class="table-body-heavy" valign="top"></th>
<th class="table-body-heavy" valign="top">Request Headers</th>
</tr>


<%
 for (Enumeration names = request.getHeaderNames(); names.hasMoreElements(); ) {
            final String name = (String)names.nextElement();
	          for (Enumeration values = request.getHeaders(name); values.hasMoreElements(); ) {
	                   final String value = (String)values.nextElement();
           
%>

<tr class="table-body-heavy">
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=name%></div><!-- after text -->
</td>
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=value%></div><!-- after text -->
</td>

</tr>


<%
  } //for <value> 
} //for <name>
%>  

<!-- after property -->
</table>

<table class="table-body-heavy">
<!-- before vspace --><div class="vspace-large">&nbsp;</div>
<tr class="table-body-heavy">
<th class="table-body-heavy" valign="top">JDBC Drivers</th>
<th class="table-body-heavy" valign="top"></th>
</tr>

<%
String jdbc_driver = " ";
     for (Enumeration e = DriverManager.getDrivers(); e.hasMoreElements(); ) {
                final Driver d = (Driver)e.nextElement();
                jdbc_driver += d.getClass().getName()+" "+d.getMajorVersion()+"."+d.getMinorVersion()
                    +(d.jdbcCompliant() ? " (JDBC compliant)" : "");
                 if (jdbc_driver.length()<2)
                   {
                     jdbc_driver = "none";
                    } 
%>


<tr class="table-body-heavy">
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text">drivers registered with the DriverManager</div><!-- after text --></td>
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=jdbc_driver%></div><!-- after text --></td>

</td>
</tr>
<%
  }
%> 
</table>

<table class="table-body-heavy">
<!-- before vspace --><div class="vspace-large">&nbsp;</div>
<tr class="table-body-heavy">
<th class="table-body-heavy" valign="top">System Properties</th>
<th class="table-body-heavy" valign="top"></th>
</tr>

<%

          SortedMap sortedProps = new TreeMap(System.getProperties());
  
          boolean even = false;
          String  error;
          for (Iterator keys = sortedProps.keySet().iterator(); keys.hasNext();) {
              String key   = (String)(keys.next());
              String value = (String)(sortedProps.get(key));
              if (value == null) {
                  value = "<i class=\"empty\">null</i>";
              } else if ("".equals(value)) {
                  value = "<i class=\"empty\">empty</i>";
              } else if ("".equals(value.trim())) {
                  value = "<i class=\"empty\">non printable<!--"+value+"--><i>";
              } 
              
              even = !even;

%>

<tr class="table-body-heavy">
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=key%></div><!-- after text -->
</td>
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=value%></div><!-- after text -->
</td>

</tr>


<%
  } //for
%>  


</table>

<!-- after body-area -->

<!-- before footer-area -->
<!-- before vspace --><div class="vspace-medium">&nbsp;</div>
<!-- after vspace -->
<!-- before separator --><div class="separator">&nbsp;</div><!-- after separator -->
</table>
<!-- after property-sheet -->
</table>

</table>

<table class="footer-area" cellpadding="0" cellspacing="0">
<tbody><tr>
<td>
<!-- before text --><div class="text">(c) 1999-2006 TIBCO Software Inc. All Rights Reserved.</div><!-- after text -->
</td>
</tr>
</tbody></table>
<!-- after footer-area -->
</body></html>