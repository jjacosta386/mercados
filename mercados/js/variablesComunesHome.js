var SEPARADOR_PRIMER_NIVEL = "$%$";
var SEPARADOR_SEGUNDO_NIVEL = "#%#";
var SEPARADOR_TERCER_NIVEL = "&%&";
var SEPARADOR_CUARTO_NIVEL = "@%@"; 

var TIEMPO_RECARGA = 120000;

var flashMovieBVC = null;
var flashMovieDivisas = null;
var flashMovieIndiceCOLCAP = null;
var flashMovieIndiceCOL20 = null;
var flashMovieIndiceIGBC = null;
var flashMovie = null;

var textoImagenVariacion = function(numero){
	if (numero > 0) {
		return '<img alt="Subio" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif"/>';
	}else{
		if (numero < 0) {
			return '<img alt="Bajo" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif"/>';
		}else{
			return '<img alt="Igual" src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif"/>';				
		}
	}
}

var cadenaADos = function(num){
	if (num.length > 2){
		num = num.substring(0,2);
	}else{
		if(num.length < 2){
			num = cadenaADos(num + '0');
		}    
	}
	return num;
}

var formatNumber = function (num){
	num += '';
	var splitStr = num.split('.');
	var splitLeft = splitStr[0];
	var splitRight = splitStr.length > 1 ? splitStr[1] : '';
	splitRight = cadenaADos(splitRight);
	var regx = /(\d+)(\d{3})/;
	while (regx.test(splitLeft)) {
		splitLeft = splitLeft.replace(regx, '$1' + ',' + '$2');
	}
	return  splitLeft + '.' + splitRight;
}



function timerIntradiaBVC() {
	//intradiaAjax.getIntradiaAccion("BVC", agregarDatoBVC);
}

var agregarDatoBVC = function (data) {
	if (data != "MERCADO_CERRADO" && flashMovieBVC.appendData) {
		flashMovieBVC.appendData(data, 0);
		setTimeout("timerIntradiaBVC()", 65000);
	} 
}

var timerIntradiaDivisas = function(){
	
}

var timerIntradiaIndiceHome = function (nombreIndice){
	/*if ("ICAP" == nombreIndice){
		intradiaAjax.getIntradiaIndice(nombreIndice, agregarDatoIndiceCOLCAP);
	}else if ("IC20" == nombreIndice){
		intradiaAjax.getIntradiaIndice(nombreIndice, agregarDatoIndiceCOL20);
	}else{
		intradiaAjax.getIntradiaIndice(nombreIndice, agregarDatoIndiceIGBC);
	}*/
}

var agregarDatoIndiceCOLCAP = function (data){
	if (data != "MERCADO_CERRADO" && flashMovieIndiceCOLCAP.appendData) {
		flashMovieIndiceCOLCAP.appendData(data, 0);
		setTimeout("timerIntradiaIndiceHome('ICAP')", 65000);
	}
}

var agregarDatoIndiceCOL20 = function (data){
	if (data != "MERCADO_CERRADO" && flashMovieIndiceCOL20.appendData) {
		flashMovieIndiceCOL20.appendData(data, 0);
		setTimeout("timerIntradiaIndiceHome('IC20')", 65000);
	}
}

var agregarDatoIndiceIGBC = function (data){
	if (data != "MERCADO_CERRADO" && flashMovieIndiceIGBC.appendData) {
		flashMovieIndiceIGBC.appendData(data, 0);
		setTimeout("timerIntradiaIndiceHome('IGBC')", 65000);
	}
}

function amChartInited(chart_id) {
	// get the flash object into "flashMovie" variable
	if ("amstockDivisa" == chart_id){
		flashMovieDivisas = document.getElementById(chart_id);
		alertTimerId = setTimeout("timerIntradiaDivisas()", 65000);
	} else if ("amstockBVC" == chart_id) {
		flashMovieBVC = document.getElementById(chart_id);
		alertTimerId = setTimeout("timerIntradiaBVC()", 65000);
	} else if (chart_id.indexOf("amstockIndice") > -1){
		var nombreIndice = chart_id.substring(13);
		if ("ICAP" == nombreIndice){                                 
			flashMovieIndiceCOLCAP = document.getElementById(chart_id);
		}else if ("IC20" == nombreIndice){
			flashMovieIndiceCOL20 = document.getElementById(chart_id);
		}else{ 
			flashMovieIndiceIGBC = document.getElementById(chart_id);
		}
		alertTimerId = setTimeout("timerIntradiaIndiceHome('" + nombreIndice +"')", 65000);
	} else if ("amstockDia" == chart_id) {
		flashMovie = document.getElementById(chart_id);
		try{
			alertTimerId = setTimeout("timerIntradiaEnJSP()", 65000);
		}catch(e){}
	}
}

entro = false;				
function amGetZoom(chart_id, from, to){
	var flashMovieZoomIn = document.getElementById(chart_id);
	var fechaIni = from.substring(0,10);
	var fechaFin = to.substring(0,10);
	if (fechaIni != fechaFin){
		if (!entro){
			entro = true;
			var arreglo = fechaFin.split("-");
			var fecha = new Date(arreglo[0],arreglo[1] - 1,arreglo[2]);
			var tieAyer  = fecha.getTime() - (60000*60*24);
			fecha.setTime(tieAyer);
			while(fecha.getDay() == 0  || fecha.getDay() == 6)
			{	
				tieAyer -= (60000*60*24);
				fecha.setTime(tieAyer);
			}
			//alert(fecha.getDay());
			fechaFin = fecha.getFullYear() + "-";
			var mes = (fecha.getMonth() + 1) + "";
			if (mes.length < 2){
				mes = "0" + mes;
			}
			fechaFin += mes + "-";
			var dia = fecha.getDate() + "";
			if(dia.length < 2){
				dia = "0" + dia;
			}
			fechaFin +=  dia + " 00:01";
			//alert(from + " - " + fechaFin);
			flashMovieZoomIn.setZoom(from, fechaFin);
			
		}else{
			entro = false;
		}
	}
}

