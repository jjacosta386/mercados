var recargarHomeEtfsMGC = function(){
	homeAjax.getFechaETFsMGCHome(ponerFechaEtfsMGC);
	homeAjax.getVolumenETFsMGCHome(ponerVolumenEtfsMGC);
	homeAjax.getDataEtfsMGCHome("listaEtfsMGC", ponerTablaNegociadasEtfsMGC);
	homeAjax.getDataEtfsMGCHome("listaEtfsMGCSube", ponerTablaSubenEtfsMGC);
	homeAjax.getDataEtfsMGCHome("listaEtfsMGCBaja", ponerTablaBajanEtfsMGC);
	setTimeout("recargarHomeEtfsMGC()", TIEMPO_RECARGA);
}

var ponerFechaEtfsMGC = function(data){
	if (data != null && data.length > 0){
		document.getElementById("textHorarioETFsHome").innerHTML = data;
	}
}

var ponerVolumenEtfsMGC = function(data){
	if (data != null && data.length > 0){
		document.getElementById("textoVolumenResumenEtfsHome").innerHTML = data;
	}
}

var ponerTablaNegociadasEtfsMGC = function(data){
	ponerDatosTablaEtfsMGC(data, "listaEtfsMGC");
}

var ponerTablaSubenEtfsMGC = function(data){
	ponerDatosTablaEtfsMGC(data, "listaEtfsMGCSube");
}

var ponerTablaBajanEtfsMGC = function(data){
	ponerDatosTablaEtfsMGC(data, "listaEtfsMGCBaja");
}

var ponerDatosTablaEtfsMGC = function(data, sufijo){
	if (data != null && data.length > 0){
		var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
		if (ppal != null){
			for (var i = 0; i< ppal.size(); i++){
				var linea = ppal[i].split(SEPARADOR_SEGUNDO_NIVEL);
				var eleNemo = document.getElementById("textoEtfMGC" + sufijo + "Nemo" + i);
				var eleVolumen = document.getElementById("textoEtfMGC" + sufijo + "Volumen" + i);
				var elePrecio = document.getElementById("textoEtfMGC" + sufijo + "Precio" + i);
				var eleVariacion = document.getElementById("textoEtfMGC" + sufijo + "Variacion" + i);
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