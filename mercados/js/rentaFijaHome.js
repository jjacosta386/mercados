var recargarHomeRentaFija = function(){
	homeAjax.getFechaRentaFijaHome(ponerFechaRentaFijaHome);
	homeAjax.getVolumenesRentafijaHome(ponerVolumenRentaFijaHome);
	homeAjax.getDataRentaFijaHome(ponerTablaRentaFijaHome);
	setTimeout("recargarHomeRentaFija()", TIEMPO_RECARGA);
}

var ponerFechaRentaFijaHome = function(data){
	if (data != null && data.length > 0){
		document.getElementById("textoFechaRentaFija").innerHTML = data;
	}
}

var ponerVolumenRentaFijaHome = function(data){
	if (data != null && data.length > 0){
		var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
		if (ppal != null){
			document.getElementById("textoVolumenRegistradoRentaFija").innerHTML = ppal[1];
			document.getElementById("textoVolumenTransadasRentaFija").innerHTML = ppal[0];
		}
	}
}

var ponerTablaRentaFijaHome = function(data){
	if (data != null && data.length > 0){
		var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
		if (ppal != null){
			ponerTablaRentaFijaSegunNombre(ppal[0], "Publica");
			ponerTablaRentaFijaSegunNombre(ppal[1], "Privada");
		}
	}
}

var ponerTablaRentaFijaSegunNombre = function(data, sufijo){
	if (data != null && data.length > 0){
		var ppal = data.split(SEPARADOR_SEGUNDO_NIVEL);
		if (ppal != null){
			for (var i=0; i<ppal.size();i++){
				var linea = ppal[i].split(SEPARADOR_TERCER_NIVEL);
				var eleNemo = document.getElementById("textoNemoDeuda" + sufijo + i);
				var eleVolumen = document.getElementById("textoVolumenDeuda" + sufijo + i);
				if (eleNemo != null && eleVolumen != null){
					eleNemo.innerHTML = linea[0];
					eleVolumen.innerHTML = linea[1];
				}
			}
		}
	}
}