<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type='text/javascript' src='/mercados/js/divisasHome.js'></script>
<script type="text/javascript">
    setTimeout("recargarHomeDivisas()", TIEMPO_RECARGA);
</script>
<script type="text/javascript">
    var cerradoDivisas = true;
    manejarAcordeonDivisas = function () {
        if ("acordeon_resumen_activo" ===
                document.getElementById('titulo_4').getAttribute("class")
                || "acordeon_resumen_activo" ===
                document.getElementById('titulo_4').getAttribute("className")
                && !cerradoDivisas)//2
        {
            mostrar_segmento_acordeon_inactivo('titulo_4');
        }
        else {
            mostrar_segmento_acordeon_activo('titulo_4');
        }

        MM_effectBlind('seg_res_4', 500, '0%', '100%', true);
        ele = document.getElementById('graficaDivisas');
        if (!cerradoDivisas) {
            ele.style.display = 'none';
            cerradoDivisas = true;
        } else {
            ele.style.display = 'block';
            cerradoDivisas = false;
        }
    }
</script>
<div class="acordeon_titulo_interior">
    <div id="titulo_4" class="acordeon_titulo_resumen_mercados" onclick="manejarAcordeonDivisas();">
        <p>Divisas</p>
    </div>
    <div class="contenedor_tabla" style="padding:5px 0px 5px 0px">
        <c:forEach items="${resumenMercado}" var="resAct" varStatus="stat">
            <div id="textVolumenNombreResumen<c:out value='${stat.index}'/>" class="resumen_titulo2" style="padding-left: 3px;">
                <strong>
                    Volumen <c:out value="${resAct.key}"></c:out> 
                    </strong>
                </div>
                <div id="textVolumenValorResumen<c:out value='${stat.index}'/>" class="resumen_titulo1" style="padding-right:5px">
                <c:out value="${resAct.value}"></c:out>
                </div>
        </c:forEach>
    </div>
</div>
<div id="seg_res_4" class="acordeon_interior_contenido">
    <div class="acordeon_titulo_contenedor_resumen_mercado">
        <div class="resumen_info_contenedor" style="background-color: rgb(255, 255, 255);">
            <div class="resumen_info_titulo" style="background-color: rgb(255, 255, 255); padding-left: 5px; font-size: 11px;">
                Mercado <c:out value="${detalleSetFx.tipoMercado}"></c:out>
                </div>
                <div class="resumen_info" style="background-color: rgb(255, 255, 255); font-size: 11px;">
                    Retraso: <c:out value="${retraso}" /> minutos 
                </div>
            </div>

            <div class="contenedor_tabla">
                <table class="resumen_tabla" cellspacing="0" cellpadding="0" id="textoTablaResumenMercadoSpot">
                    <thead>
                        <tr>
                            <td style="border-right: 1px solid rgb(219, 219, 219); padding: 3px; background-color: rgb(239, 237, 238);">
                                Volumen*
                            </td>
                            <td style="border-right: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(239, 237, 238);" width="70px">
                                Precio Promedio
                            </td>
                            <td style="border-right: 1px solid rgb(219, 219, 219); padding: 5px; background-color: rgb(239, 237, 238);" width="50px">
                                TRM
                            </td>
                            <td style="padding: 5px; background-color: rgb(239, 237, 238);" >
                                Variación
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="resumen_indices_tabla_datos_fila_resaltada" style="background-color: rgb(255, 255, 255);">
                            <td id="textoVolumenTablaSpot" style="padding: 5px; background-color: rgb(255, 255, 255);">
                    <fmt:formatNumber pattern="###,##0.00">
                    <c:out value="${detalleSetFx.volumenNegociado/1000}"></c:out>
                    </fmt:formatNumber>
                    </td>
                    <td id="textoPrecioTablaSpot" align="right" style="background-color: rgb(255, 255, 255); padding: 5px;">
                    <fmt:formatNumber pattern="###,##0.00">
                    <c:out value="${detalleSetFx.precioPromedio}"></c:out>
                    </fmt:formatNumber> 
                    </td>
                    <td id="textoTRMTablaSpot" align="center" style="background-color: rgb(255, 255, 255); padding: 5px;">
                    <fmt:formatNumber pattern="###,##0.00">
                    <c:out value="${detalleSetFx.trm}"></c:out>
                    </fmt:formatNumber> 
                    </td>
                    <td id="textoVariacionTablaSpot" align="right" style="padding-right: 5px; background-color: rgb(255, 255, 255); padding: 5px;">
                    <fmt:formatNumber pattern="###,##0.00">
                    <c:out value="${detalleSetFx.variacion}"></c:out>
                </fmt:formatNumber>%
                </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div style="padding: 0px 0px 0px 5px; font-family: Arial,Helvetica,sans-serif; font-size: 11px; width: 200px; display: block; float: left; position: relative;">
            * Datos en miles de d&oacute;lares
        </div>

        <div class="contenedor_tabla" id="graficaDivisas">
            <script type="text/javascript">
                var graficoDivisa = new SWFObject("%%SiteContent.Portlet('common.bvc.recursos')%%/amstock/amstock.swf", "amstockDivisa", "248", "175", "8", "#FFFFFF");

                graficoDivisa.addVariable("path", "");
                graficoDivisa.addVariable("settings_file",
                        escape("/mercados/GraficosServlet?conf=dolar&url=/amstock/settings/divisas_stock_settings.xml&tipoG=DIVISAS"));
                graficoDivisa.addVariable("chart_id", "amstockDivisa");
                graficoDivisa.addVariable("preloader_color", "#999999");
                licenciaGrafica(graficoDivisa);
                graficoDivisa.write('graficaDivisas');
            </script>        
        </div>

        <div class="resumen_informacion_detallada" style="padding-right:5px">
            <a href="/pps/tibco/portalbvc/Home/Mercados/enlinea/divisas?action=dummy">
                Información detallada
            </a>
        </div>
    </div>
    <div class="contenedor_tabla"style="display:block; float:left; position:relative; overflow:hidden; width:100%; height:auto; padding:0px 0px 5px 0px">
        <a href="http://www.set-fx.com/" target="_blank"> 
            <img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/set.gif" alt="SET" />
        </a>
    </div>
</div>
<script type="text/javascript">
	manejarAcordeonDivisas();
</script>