<%@include file="include.jsp"%>

<script type="text/javascript" src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/js/calendar.js"></script>
<script type="text/javascript" src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/js/calendar-setup.js"></script>
<script type="text/javascript" src="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/js/calendar-es.js"></script>
<link rel='stylesheet' type='text/css' media='all'  href="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/css/skins/aqua/theme.css" title='Aqua' />
<link rel='stylesheet' type='text/css' media='all'  href="%%SiteContent.Portlet('common.bvc.recursos')%%/calendar/css/calendar-win2k-2.css"/>

<script type="text/javascript">
function pintarCal(id, format){
	var a = document.getElementById(id);
	var p = Calendar.getAbsolutePos(a);
	var X = p.x;
	var Y = p.y;
	Calendar.setup({
			inputField     :    id,
			singleClick    :    false,
			ifFormat       :    format,
			position       :    [X,Y] 
	});
}
</script>