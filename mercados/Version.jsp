
<%@ page language="java" import="com.tibco.ps.svcruntime.version.*,
                                 java.util.List" %>

<%
  Portal product = new Portal(application);
  PortalService service = product.getCurrentService();
%>

<html><head><title>Service Version</title>

<link href="/sta-pb/PortalTemplates/Root/System Sites/PBC/Templates/Skins/pbc/res/styles.css" rel="stylesheet" name="default" type="text/css">
</head>
<body>

<!-- before banner-area -->
<table class="banner-area">
<tbody><tr>
<th>&nbsp;</th>
<td>
&nbsp;
</td>
</tr>
</tbody></table>
<!-- after banner-area -->

<!-- before heading-area -->
<table class="heading-area">
<td>
<!-- before heading -->
<tr>
<th></th>
<td class="heading"><%=service.getName()%></th>
</tr>
<!-- after heading -->
</td>
</table>
<!-- after heading-area -->
<!-- before body-area -->
<table cellpadding="0" cellspacing="0" class="body-area">
<tr>
<td class="body-all">
<!-- before content-area -->
<!-- before vspace --><div class="vspace-large">&nbsp;</div>
<!-- after vspace -->
<!-- before property-sheet -->
<table class="property-sheet">
<!-- before property -->
<tr class="property-sheet">
<th class="property-sheet"><!-- before property-name --><span class=";property-name">Service Name: </span><!-- after property-name --></th>
<td class="property-sheet"><%=service.getName()%></td>
</tr>
<!-- after property -->
<!-- before property -->
<tr class="property-sheet">
<th class="property-sheet"><!-- before property-name --><span class=";property-name">Service Version: </span><!-- after property-name --></th>
<td class="property-sheet"><%=service.getVersion()%></td>
</tr>
<!-- after property -->
<!-- before property -->
<tr class="property-sheet">
<th class="property-sheet"><!-- before property-name --><span class=";property-name">Product: </span><!-- after property-name --></th>
<td class="property-sheet"><%=product.getVersion()%> Build <%=product.getBuild()%></td>
</tr>
<!-- after property -->
</table>
<!-- after property-sheet -->
<!-- before vspace --><div class="vspace-large">&nbsp;</div>
<!-- after vspace -->
<!-- before supertext --><div class="supertext">Service Packages:</div><!-- after supertext -->
<!-- before vspace --><div class="vspace-small">&nbsp;</div>
<!-- after vspace -->
<!-- before table -->
<!-- before table-body -->
<table class="table-body-heavy">
<tr class="table-body-heavy">
<th class="table-body-heavy" valign="top" width="49%">Package</th>
<th class="table-body-heavy" valign="top">Vendor</th>
<th class="table-body-heavy" valign="top">Version</th>
</tr>
<%
  List dependencies = product.getDependencies();
  for(int i=0; i<dependencies.size(); i++){
   PortalDependency dependency = (PortalDependency)dependencies.get(i);
   String name = dependency.getName();
   String vendor = dependency.getVendor();
   if(vendor == null) vendor = "Not Available";
   String version = dependency.getVersion();
   if(version == null) version = "Not Available";
%>
<tr class="table-body-heavy">
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=name%></div><!-- after text --></td>
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=vendor%></div><!-- after text --></td>
<td class="table-body-heavy" valign="top">
<!-- before text --><div class="text"><%=version%></div><!-- after text --></td>
</tr>
<%
  }
%>
</table>
<!-- after table-body -->
<!-- after table -->
<!-- after content-area -->
</td>
</tr>
</table>
<!-- after body-area -->

<!-- before footer-area -->
<!-- before vspace --><div class="vspace-medium">&nbsp;</div>
<!-- after vspace -->
<!-- before separator --><div class="separator">&nbsp;</div><!-- after separator -->
<table class="footer-area" cellpadding="0" cellspacing="0">
<tbody><tr>
<td>
<!-- before text --><div class="text">(c) 1999-2006 TIBCO Software Inc. All Rights Reserved.</div><!-- after text -->
</td>
</tr>
</tbody></table>
<!-- after footer-area -->
</body></html>