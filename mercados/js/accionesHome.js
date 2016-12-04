var recargarHomeAcciones = function(){
	homeAjax.getFechaAccionesHome(ponerFechaAcciones);
	homeAjax.getVolumenAccionesHome(ponerVolumenAcciones);
	homeAjax.getDataAccionesHome("listaAcciones", ponerTablaNegociadasAcciones);
	homeAjax.getDataAccionesHome("listaAccionesSube", ponerTablaSubenAcciones);
	homeAjax.getDataAccionesHome("listaAccionesBaja", ponerTablaBajanAcciones);
	setTimeout("recargarHomeAcciones()", TIEMPO_RECARGA);
}

var ponerFechaAcciones = function(data){
	if (data != null && data.length > 0){
		document.getElementById("textHorarioAccionesHome").innerHTML = data;
	}
}

var ponerVolumenAcciones = function(data){
	if (data != null && data.length > 0){
		document.getElementById("textoVolumenResumenAccionesHome").innerHTML = data;
	}
}

var ponerTablaNegociadasAcciones = function(data){
	ponerDatosTabla(data, "listaAcciones");
}

var ponerTablaSubenAcciones = function(data){
	ponerDatosTabla(data, "listaAccionesSube");
}

var ponerTablaBajanAcciones = function(data){
	ponerDatosTabla(data, "listaAccionesBaja");
}

var ponerDatosTabla = function(data, sufijo){
	if (data != null && data.length > 0){
		var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
		if (ppal != null){
			for (var i = 0; i< ppal.size(); i++){
				var linea = ppal[i].split(SEPARADOR_SEGUNDO_NIVEL);
				var eleNemo = document.getElementById("texto" + sufijo + "Nemo" + i);
				var eleVolumen = document.getElementById("texto" + sufijo + "Volumen" + i);
				var elePrecio = document.getElementById("texto" + sufijo + "Precio" + i);
				var eleVariacion = document.getElementById("texto" + sufijo + "Variacion" + i);
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