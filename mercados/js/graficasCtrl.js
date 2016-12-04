function cambiarFormatoGrafico(){
		debugger;
		var ele = document.getElementById('modoGraf');
		if (ele.value == 'L'){
			indiceGrafico.setSettings("<settings><graphs><graph gid = '1'><type>line</type></graph></graphs></settings>");
			indiceGrafico.rebuild();
		}else{
			indiceGrafico.setSettings("<settings><graphs><graph gid = '1'><type>candlestick</type></graph></graphs></settings>");
			indiceGrafico.rebuild();
		}
	}
