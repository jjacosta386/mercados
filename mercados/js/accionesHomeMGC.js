var recargarHomeAccionesMGC = function(){
	homeAjax.getFechaAccionesMGCHome(ponerFechaAccionesMGC);
	homeAjax.getVolumenAccionesMGCHome(ponerVolumenAccionesMGC);
	homeAjax.getDataAccionesMGCHome("listaAccionesMGC", ponerTablaNegociadasAccionesMGC);
	homeAjax.getDataAccionesMGCHome("listaAccionesMGCSube", ponerTablaSubenAccionesMGC);
	homeAjax.getDataAccionesMGCHome("listaAccionesMGCBaja", ponerTablaBajanAccionesMGC);
	setTimeout("recargarHomeAccionesMGC()", TIEMPO_RECARGA);
}

var ponerFechaAccionesMGC = function(data){
	if (data != null && data.length > 0){
		document.getElementById("textHorarioAccionesMGCHome").innerHTML = data;
	}
}

var ponerVolumenAccionesMGC = function(data){
	if (data != null && data.length > 0){
		document.getElementById("textoVolumenResumenAccionesMGCHome").innerHTML = data;
	}
}

var ponerTablaNegociadasAccionesMGC = function(data){
	ponerDatosTablaMGC(data, "listaAccionesMGC");
}

var ponerTablaSubenAccionesMGC = function(data){
	ponerDatosTablaMGC(data, "listaAccionesMGCSube");
}

var ponerTablaBajanAccionesMGC = function(data){
	ponerDatosTablaMGC(data, "listaAccionesMGCBaja");
}

var ponerDatosTablaMGC = function(data, sufijo){
	if (data != null && data.length > 0){
		var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
		if (ppal != null){
			for (var i = 0; i< ppal.size(); i++){
				var linea = ppal[i].split(SEPARADOR_SEGUNDO_NIVEL);
				var eleNemo = document.getElementById("textoMGC" + sufijo + "Nemo" + i);
				var eleVolumen = document.getElementById("textoMGC" + sufijo + "Volumen" + i);
				var elePrecio = document.getElementById("textoMGC" + sufijo + "Precio" + i);
				var eleVariacion = document.getElementById("textoMGC" + sufijo + "Variacion" + i);
				if (eleNemo != null && eleVolumen != null && elePrecio != null && eleVariacion != null){
					eleNemo.innerHTML = linea[0];
					eleVolumen.innerHTML = linea[1];
					elePrecio.innerHTML = linea[2];
					eleVariacion.innerHTML = formatNumber(linea[3]) + '%' + textoImagenVariacion(linea[3]);
				}
			}
		}
	}
}