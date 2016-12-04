var recargarHomeDivisas = function(){
	homeAjax.getDataDivisasHome(ponerMercadoSpot);
	homeAjax.getVolumenesDivisasHome(ponerVolumenes);
	setTimeout("recargarHomeDivisas()", TIEMPO_RECARGA);
}



var ponerVolumenes = function(data){
	if (data != null && data.length > 0){
	    var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
	    if (ppal != null){
	         for (var x = 0; x<ppal.size(); x++){
	             var filaact = ppal[x].split(SEPARADOR_SEGUNDO_NIVEL);
	             var nombre = filaact[0]; 
	             var valor = filaact[1];
	             var eleNombre = document.getElementById("textVolumenNombreResumen" + x);
	             var eleValor = document.getElementById("textVolumenValorResumen" + x);
	             if (eleNombre != null && eleValor != null){
	            	 eleNombre.innerHTML = "<strong>Volumen "+ nombre + "</strong>";
	            	 eleValor.innerHTML = valor;
	             }
	         }
	    }
	}

	
}

var ponerMercadoSpot = function(data){
	if (data != null && data.length > 0){
		 var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
		 var eleVolumen = document.getElementById("textoVolumenTablaSpot");
		 var elePrecio = document.getElementById("textoPrecioTablaSpot");
		 var eleTRM = document.getElementById("textoTRMTablaSpot");
		 var eleVariacion = document.getElementById("textoVariacionTablaSpot");
		 eleVolumen.innerHTML = ppal[0] ;
		 elePrecio.innerHTML = ppal[1];
		 eleTRM.innerHTML = ppal[2];
		 eleVariacion.innerHTML =  formatNumber(ppal[3]) + '%'; 
	}
}