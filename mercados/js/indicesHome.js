var recargarHomeIndices = function(){
	homeAjax.getFechaIndicesHome(ponerFechaIndices);
	homeAjax.getDataIndicesHome(ponerDataIndices);
	setTimeout("recargarHomeIndices()", TIEMPO_RECARGA);
}

var ponerFechaIndices = function(data){
	if (data != null && data.length > 0){
		 var eleFecha = document.getElementById("textoFechaIndice");
		 eleFecha.innerHTML = "<strong>"+ data + "</strong>";
	}
}

var ponerDataIndices = function (data){
	if (data != null && data.length > 0){
	    var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
	    if (ppal != null){
			for (var x = 0; x<ppal.size(); x++){
	             var filaact = ppal[x].split(SEPARADOR_SEGUNDO_NIVEL);
	             var eleNombre = document.getElementById("textoNombreIndice" + x);
	             var eleValor = document.getElementById("textoValorHoyIndice" + x);
	             var eleVariacion = document.getElementById("textoVariacionHoyIndice" + x);
	             var nombreInd  = filaact[0];
	             var valorHoy = filaact[1];
	             var variacion = filaact[2];
	             if (eleNombre != null && eleValor != null && eleVariacion != null){
	            	 eleNombre.innerHTML = "<strong>" + nombreInd +"</strong>";
	            	 eleValor.innerHTML = valorHoy;
	            	 eleVariacion.innerHTML = formatNumber(variacion) + '%' + textoImagenVariacion(variacion);
	             }
	         }
	    }
	}
}