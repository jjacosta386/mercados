var recargarHomeAccionBVC = function(){
	homeAjax.getFechaAccionBvcHome(ponerFechaAccionBVC);
	homeAjax.getDataAccionBvcHome(ponerDataAccionBVC);
	setTimeout("recargarHomeAccionBVC()", TIEMPO_RECARGA);
}

var ponerFechaAccionBVC = function(data){
	if (data != null && data.length > 0){
		document.getElementById("textoFechaAccionBVCHome").innerHTML = "<strong>" + data + "</strong>";
	}
}

var ponerDataAccionBVC = function(data){
	if (data != null && data.length > 0){
		var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
		document.getElementById("textoVolumenAccionBVC").innerHTML = ppal[0];
		document.getElementById("textoPrecioAccionBVC").innerHTML = ppal[1];
		document.getElementById("textoVariacionAccionBVC").innerHTML = formatNumber(ppal[2]) + '%' + textoImagenVariacion(ppal[2]); 
	}
}