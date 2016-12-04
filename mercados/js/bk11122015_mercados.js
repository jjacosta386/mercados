var simulo1 = false;
var simulo2 = false;

var desarrollo = "114-16d4bc7c280bb0265a4af861674047c3";
var pruebas = "114-9b81bd39490422a44e90f1b4af6d3bfb";
var produccionIP = "113-f91744591836e58854ac416c38dc6d84";
var produccionBVC = "110-5781c9839ee9d8b789efe9d5415a8b61";


var desactivaSelect = function(val, id, key){
    if(val == key){
      document.getElementById(id).disabled = false;
    }else{
      document.getElementById(id).disabled = true;
    }
 }



function licenciaGrafica (so) {
	var licencia = produccionBVC;
	so.addVariable("key", licencia);
}

function ponerFlecha(numero){
		if (numero > 0) {
			document.write("<img style=\"vertical-align: baseline\" alt=\"Subio\" src=\"%%SiteContent.Portlet('common.bvc.recursos')%%/images/up.gif\"/>");
		}else{
			if (numero < 0) {
				document.write("<img style=\"vertical-align: baseline\" alt=\"Bajo\" src=\"%%SiteContent.Portlet('common.bvc.recursos')%%/images/down.gif\"/>");
			}else{
				document.write("<img style=\"vertical-align: baseline\" alt=\"Igual\" src=\"%%SiteContent.Portlet('common.bvc.recursos')%%/images/equal.gif\"/>");				
			}
		}
	}
	
function simularDesde(idCampo, num){
		if (num==0){
			if (simulo1){
				return;
			}
		}
		simulo1 = true;
		ele = document.getElementById(idCampo);
		ele.onclick();
	}
	
function simularHasta(idCampo, num){
		if (num==0){
			if (simulo2){
				return;
			}
		}
		simulo2 = true;
		ele = document.getElementById(idCampo);
		ele.onclick();
	}
	
function fechaConGuion(cadena){
	      var arreglo = cadena.split("-");
	      return new Date(arreglo[0],arreglo[1] - 1,arreglo[2]);
	    }

function fechaSinGuion(cadena){
     var anio = cadena.substring(0,4);
     var mes = cadena.substring(4,6);
     var dia = cadena.substring(6, 8);
     return new Date(anio, mes - 1 , dia);
  }

function validarFormFechas(idFechaDesde, idFechaHasta, formato, nombreForm, anioRest){
	    var cadenaFechaIni = document.getElementById(idFechaDesde).value;
		var cadenaFechaFin = document.getElementById(idFechaHasta).value;
		var fecha1 = null;
		var fecha2 = null;
		if (cadenaFechaIni == null || cadenaFechaIni.length < 1 
				|| cadenaFechaFin == null || cadenaFechaFin.length < 1){
			alert("Debe seleccionar ambas fechas");
		}else{
			if (formato.search("-") > -1){
				fecha1 = fechaConGuion(cadenaFechaIni);
				fecha2 = fechaConGuion(cadenaFechaFin);
			}else{
				fecha1 = fechaSinGuion(cadenaFechaIni);
				fecha2 = fechaSinGuion(cadenaFechaFin);
			}
			 var fechaDiff = fecha2.getTime() - fecha1.getTime();
		     fechaDiff = fechaDiff/(3600000*12*365.25);
		     if (fechaDiff < 0){
		         alert("La fecha inicial debe ser menor o igual a la fecha final");
		     }else{
		    	 if(fechaDiff > 1.001 && anioRest){
		    		 alert("El rango m\u00e1ximo para descargas es de 6 meses");
		    	 }else{
		    		 eval("document."+nombreForm+".submit();");
		    	 }
		     }
		}
	}    
	
function getRangeSizeInDays(fechaIni, fechaFin){
	fecha1 = fechaConGuion(fechaIni);
	fecha2 = fechaConGuion(fechaFin);
	var fechaDiff = fecha2.getTime() - fecha1.getTime();
    fechaDiff = fechaDiff/(3600000*24);
    return fechaDiff;
}
	
function isGetZoomFromButton(fechaDiff){
	if (fechaDiff == 7 || fechaDiff == 28 || fechaDiff == 91 || fechaDiff == 181 || fechaDiff == 364){
    	return true;
    }
    return false;
}

	
