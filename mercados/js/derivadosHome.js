var recargarHomeDerivados = function(){
	homeAjax.getFechaDerivadosHome(ponerFechaDerivados);
	homeAjax.getResumenGeneralDerivadosHome(ponerVolumenesDerivados);
	homeAjax.getDataDerivadosHome("derivadosResumenOPCF",ponerTablaDerivadosOPCF);
	homeAjax.getDataDerivadosHome("derivadosResumenOpciones",ponerTablaDerivadosOpciones);
	homeAjax.getDataDerivadosHome("derivadosResumenFuturos",ponerTablaDerivadosFuturos);
	setTimeout("recargarHomeDerivados()", TIEMPO_RECARGA);
}

var ponerFechaDerivados = function( data ){
	if (data != null && data.length > 0){
		document.getElementById("textEstadoMercadoDerivado").innerHTML = data;
	}
}

var ponerVolumenesDerivados = function (data){
	if (data != null && data.length > 0){
		var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
		for (var x = 0; x<ppal.size(); x++){
			var linea = ppal[x].split(SEPARADOR_SEGUNDO_NIVEL);
			var nombreDer = linea[0];
			var valorDer = linea[1];
			if (nombreDer != "Total" && nombreDer != "OPCF"){
				var eleNombre = document.getElementById("textNombreResumenGnralDerivado" + x);
				var eleValor = document.getElementById("textValorResumenGnralDerivado" + x);
				if (eleNombre != null && eleValor != null){
					if (nombreDer == "Volumen contratos Futuros Financieros") {
						eleNombre.innerHTML = "<strong>Volumen contratos Futuros</strong>";
						eleValor.innerHTML = valorDer;
					} else {
						eleNombre.innerHTML = "<strong>" + nombreDer + "</strong>";
						eleValor.innerHTML = valorDer;
					}
				}
			}
		}
	}
}

var ponerTablaDerivadosOPCF = function(data){
	ponerTablaDerivados(data, "OPCF");
}

var ponerTablaDerivadosOpciones = function(data){
	ponerTablaDerivados(data, "Opciones");
}

var ponerTablaDerivadosFuturos = function(data){
	ponerTablaDerivados(data, "Futuro");
}

var ponerTablaDerivados = function(data, sufijo){
	if (data != null && data.length > 0){
		var ppal = data.split(SEPARADOR_PRIMER_NIVEL);
		var cont = 0;
		if (ppal != null){
			for (var x = 0; x<ppal.size(); x++){
				var segDos = ppal[x].split(SEPARADOR_SEGUNDO_NIVEL);
				if (segDos != null){
					for (var i =0; i<segDos.size(); i++){
						var segTres = segDos[i].split(SEPARADOR_TERCER_NIVEL);
						if (segTres != null){
							for (var j = 0; (j<segTres.size() && j < 5); j++){
								var linea = segTres[j].split(SEPARADOR_CUARTO_NIVEL);
								var eleContrato = document.getElementById("textoNombreContratoDerivado" + sufijo + cont);
								var eleContratos = document.getElementById("textoNumeroContratoDerivado" + sufijo + cont);
								var elePrecio = document.getElementById("textoPrecioContratoDerivado" + sufijo + cont);
								var eleVariacion = document.getElementById("textoVariacionContratoDerivado" + sufijo + cont);
								if (eleContrato != null && eleContratos != null && elePrecio != null && eleVariacion != null){
									eleContrato.innerHTML = linea[0];
									eleContratos.innerHTML =  linea[1];
									elePrecio.innerHTML = linea[2];
									eleVariacion.innerHTML =  formatNumber(linea[3]) + '%' + textoImagenVariacion(linea[3]);
								}
								cont++;
							}
						}
					}
				}
			}
		}
	}
}
