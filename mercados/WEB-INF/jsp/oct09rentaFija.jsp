<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type='text/javascript' src='/mercados/dwr/interface/rentaFijaAjax.js'></script>

<script type="text/javascript">

	function manejarUltimoDetalleTabla(tipo, num){
		var valorPropiedad = 'none';
		var valorPropiedadNegada = 'block';
		if (tipo == '0'){
			//detallar
			valorPropiedad = 'block';
			valorPropiedadNegada = 'none';
		}
		MM_changeProp('deuda_pu' + num,'','display',valorPropiedad,'DIV');
		MM_changeProp('cerrar_pu' + num,'','display',valorPropiedad,'DIV');
		MM_changeProp('ver_pu' + num,'','display',valorPropiedadNegada,'DIV');
//		document.getElementById('detalle' + num).style.display = valorPropiedad	;
	}
	function manejarUltimoDetalleTablaPrivada(tipo, num){
		var valorPropiedad = 'none';
		var valorPropiedadNegada = 'block';
		if (tipo == '0'){
			//detallar
			valorPropiedad = 'block';
			valorPropiedadNegada = 'none';
		}
		MM_changeProp('deuda_pri' + num,'','display',valorPropiedad,'DIV');
		MM_changeProp('text_cerrar_pri' + num,'','display',valorPropiedad,'DIV');
		MM_changeProp('text_ver_pri' + num,'','display',valorPropiedadNegada,'DIV');
//		document.getElementById('detalle' + num).style.display = valorPropiedad	;
	}

</script>

<portlet:actionURL var="formAction">
	<portlet:param name="action" value="buscar" />
</portlet:actionURL>

<script type="text/javascript">
//eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('(J(){7(1e.19)L w=1e.19;L E=1e.19=J(a,b){K 1D E.2m.4Y(a,b)};7(1e.$)L D=1e.$;1e.$=E;L u=/^[^<]*(<(.|\\s)+>)[^>]*$|^#(\\w+)$/;L G=/^.[^:#\\[\\.]*$/;E.1i=E.2m={4Y:J(d,b){d=d||T;7(d.15){6[0]=d;6.M=1;K 6}N 7(1v d=="25"){L c=u.39(d);7(c&&(c[1]||!b)){7(c[1])d=E.5c([c[1]],b);N{L a=T.5N(c[3]);7(a)7(a.2s!=c[3])K E().2r(d);N{6[0]=a;6.M=1;K 6}N d=[]}}N K 1D E(b).2r(d)}N 7(E.1q(d))K 1D E(T)[E.1i.21?"21":"43"](d);K 6.6G(d.1n==1N&&d||(d.5j||d.M&&d!=1e&&!d.15&&d[0]!=10&&d[0].15)&&E.2H(d)||[d])},5j:"1.2.2",82:J(){K 6.M},M:0,22:J(a){K a==10?E.2H(6):6[a]},2E:J(b){L a=E(b);a.56=6;K a},6G:J(a){6.M=0;1N.2m.1h.1j(6,a);K 6},V:J(a,b){K E.V(6,a,b)},5E:J(b){L a=-1;6.V(J(i){7(6==b)a=i});K a},1K:J(c,a,b){L d=c;7(c.1n==4d)7(a==10)K 6.M&&E[b||"1K"](6[0],c)||10;N{d={};d[c]=a}K 6.V(J(i){P(c 1r d)E.1K(b?6.Y:6,c,E.1l(6,d[c],b,i,c))})},1m:J(b,a){7((b==\'29\'||b==\'1P\')&&2M(a)<0)a=10;K 6.1K(b,a,"2q")},1t:J(b){7(1v b!="4D"&&b!=W)K 6.4B().3t((6[0]&&6[0].2u||T).5v(b));L a="";E.V(b||6,J(){E.V(6.3p,J(){7(6.15!=8)a+=6.15!=1?6.6M:E.1i.1t([6])})});K a},5r:J(b){7(6[0])E(b,6[0].2u).5J().3n(6[0]).2a(J(){L a=6;2e(a.1B)a=a.1B;K a}).3t(6);K 6},8t:J(a){K 6.V(J(){E(6).6C().5r(a)})},8m:J(a){K 6.V(J(){E(6).5r(a)})},3t:J(){K 6.3P(1a,R,S,J(a){7(6.15==1)6.3k(a)})},6s:J(){K 6.3P(1a,R,R,J(a){7(6.15==1)6.3n(a,6.1B)})},6o:J(){K 6.3P(1a,S,S,J(a){6.1b.3n(a,6)})},5a:J(){K 6.3P(1a,S,R,J(a){6.1b.3n(a,6.2J)})},3h:J(){K 6.56||E([])},2r:J(b){L c=E.2a(6,J(a){K E.2r(b,a)});K 6.2E(/[^+>] [^+>]/.17(b)||b.1g("..")>-1?E.57(c):c)},5J:J(e){L f=6.2a(J(){7(E.14.1d&&!E.3W(6)){L a=6.6c(R),5u=T.2R("1u"),4T=T.2R("1u");5u.3k(a);4T.38=5u.38;K 4T.1B}N K 6.6c(R)});L d=f.2r("*").4R().V(J(){7(6[F]!=10)6[F]=W});7(e===R)6.2r("*").4R().V(J(i){7(6.15==3)K;L c=E.Q(6,"2N");P(L a 1r c)P(L b 1r c[a])E.16.1c(d[i],a,c[a][b],c[a][b].Q)});K f},1F:J(b){K 6.2E(E.1q(b)&&E.3x(6,J(a,i){K b.1O(a,i)})||E.3d(b,6))},4I:J(b){7(b.1n==4d)7(G.17(b))K 6.2E(E.3d(b,6,R));N b=E.3d(b,6);L a=b.M&&b[b.M-1]!==10&&!b.15;K 6.1F(J(){K a?E.35(6,b)<0:6!=b})},1c:J(a){K!a?6:6.2E(E.34(6.22(),a.1n==4d?E(a).22():a.M!=10&&(!a.12||E.12(a,"3i"))?a:[a]))},3K:J(a){K a?E.3d(a,6).M>0:S},7g:J(a){K 6.3K("."+a)},5P:J(b){7(b==10){7(6.M){L c=6[0];7(E.12(c,"2y")){L e=c.44,5L=[],11=c.11,30=c.U=="2y-30";7(e<0)K W;P(L i=30?e:0,2b=30?e+1:11.M;i<2b;i++){L d=11[i];7(d.2p){b=E.14.1d&&!d.9s.1C.9o?d.1t:d.1C;7(30)K b;5L.1h(b)}}K 5L}N K(6[0].1C||"").1p(/\\r/g,"")}K 10}K 6.V(J(){7(6.15!=1)K;7(b.1n==1N&&/5w|5y/.17(6.U))6.3o=(E.35(6.1C,b)>=0||E.35(6.37,b)>=0);N 7(E.12(6,"2y")){L a=b.1n==1N?b:[b];E("90",6).V(J(){6.2p=(E.35(6.1C,a)>=0||E.35(6.1t,a)>=0)});7(!a.M)6.44=-1}N 6.1C=b})},3q:J(a){K a==10?(6.M?6[0].38:W):6.4B().3t(a)},6P:J(a){K 6.5a(a).1Y()},6N:J(i){K 6.2V(i,i+1)},2V:J(){K 6.2E(1N.2m.2V.1j(6,1a))},2a:J(b){K 6.2E(E.2a(6,J(a,i){K b.1O(a,i,a)}))},4R:J(){K 6.1c(6.56)},3P:J(g,f,h,d){L e=6.M>1,3m;K 6.V(J(){7(!3m){3m=E.5c(g,6.2u);7(h)3m.8I()}L b=6;7(f&&E.12(6,"1V")&&E.12(3m[0],"4x"))b=6.3V("1S")[0]||6.3k(6.2u.2R("1S"));L c=E([]);E.V(3m,J(){L a=e?E(6).5J(R)[0]:6;7(E.12(a,"1o")){c=c.1c(a)}N{7(a.15==1)c=c.1c(E("1o",a).1Y());d.1O(b,a)}});c.V(6D)})}};E.2m.4Y.2m=E.2m;J 6D(i,a){7(a.3R)E.3Q({1f:a.3R,3l:S,1G:"1o"});N E.5l(a.1t||a.6A||a.38||"");7(a.1b)a.1b.2X(a)}E.1s=E.1i.1s=J(){L b=1a[0]||{},i=1,M=1a.M,5i=S,11;7(b.1n==8f){5i=b;b=1a[1]||{};i=2}7(1v b!="4D"&&1v b!="J")b={};7(M==1){b=6;i=0}P(;i<M;i++)7((11=1a[i])!=W)P(L a 1r 11){7(b===11[a])6z;7(5i&&11[a]&&1v 11[a]=="4D"&&b[a]&&!11[a].15)b[a]=E.1s(b[a],11[a]);N 7(11[a]!=10)b[a]=11[a]}K b};L F="19"+(1D 3O()).3N(),6y=0,5e={};L H=/z-?5E|89-?87|1y|6q|85-?1P/i;E.1s({81:J(a){1e.$=D;7(a)1e.19=w;K E},1q:J(a){K!!a&&1v a!="25"&&!a.12&&a.1n!=1N&&/J/i.17(a+"")},3W:J(a){K a.1I&&!a.1k||a.28&&a.2u&&!a.2u.1k},5l:J(a){a=E.3f(a);7(a){L b=T.3V("6k")[0]||T.1I,1o=T.2R("1o");1o.U="1t/4l";7(E.14.1d)1o.1t=a;N 1o.3k(T.5v(a));b.3k(1o);b.2X(1o)}},12:J(b,a){K b.12&&b.12.2F()==a.2F()},1Q:{},Q:J(c,d,b){c=c==1e?5e:c;L a=c[F];7(!a)a=c[F]=++6y;7(d&&!E.1Q[a])E.1Q[a]={};7(b!=10)E.1Q[a][d]=b;K d?E.1Q[a][d]:a},3H:J(c,b){c=c==1e?5e:c;L a=c[F];7(b){7(E.1Q[a]){2T E.1Q[a][b];b="";P(b 1r E.1Q[a])1T;7(!b)E.3H(c)}}N{1R{2T c[F]}1W(e){7(c.55)c.55(F)}2T E.1Q[a]}},V:J(c,a,b){7(b){7(c.M==10){P(L d 1r c)7(a.1j(c[d],b)===S)1T}N P(L i=0,M=c.M;i<M;i++)7(a.1j(c[i],b)===S)1T}N{7(c.M==10){P(L d 1r c)7(a.1O(c[d],d,c[d])===S)1T}N P(L i=0,M=c.M,1C=c[0];i<M&&a.1O(1C,i,1C)!==S;1C=c[++i]){}}K c},1l:J(b,a,c,i,d){7(E.1q(a))a=a.1O(b,i);K a&&a.1n==53&&c=="2q"&&!H.17(d)?a+"2P":a},1w:{1c:J(c,b){E.V((b||"").2d(/\\s+/),J(i,a){7(c.15==1&&!E.1w.3E(c.1w,a))c.1w+=(c.1w?" ":"")+a})},1Y:J(c,b){7(c.15==1)c.1w=b!=10?E.3x(c.1w.2d(/\\s+/),J(a){K!E.1w.3E(b,a)}).6g(" "):""},3E:J(b,a){K E.35(a,(b.1w||b).3D().2d(/\\s+/))>-1}},6e:J(b,c,a){L e={};P(L d 1r c){e[d]=b.Y[d];b.Y[d]=c[d]}a.1O(b);P(L d 1r c)b.Y[d]=e[d]},1m:J(d,e,c){7(e=="29"||e=="1P"){L b,3S={3C:"4Z",4X:"23",18:"3u"},3r=e=="29"?["7P","7M"]:["7L","7K"];J 4S(){b=e=="29"?d.7J:d.7I;L a=0,3a=0;E.V(3r,J(){a+=2M(E.2q(d,"7H"+6,R))||0;3a+=2M(E.2q(d,"3a"+6+"62",R))||0});b-=1Z.7E(a+3a)}7(E(d).3K(":4b"))4S();N E.6e(d,3S,4S);K 1Z.2b(0,b)}K E.2q(d,e,c)},2q:J(e,k,j){L d;J 3y(b){7(!E.14.26)K S;L a=T.4a.4L(b,W);K!a||a.4K("3y")==""}7(k=="1y"&&E.14.1d){d=E.1K(e.Y,"1y");K d==""?"1":d}7(E.14.2B&&k=="18"){L c=e.Y.18;e.Y.18="3u";e.Y.18=c}7(k.1E(/4c/i))k=y;7(!j&&e.Y&&e.Y[k])d=e.Y[k];N 7(T.4a&&T.4a.4L){7(k.1E(/4c/i))k="4c";k=k.1p(/([A-Z])/g,"-$1").2w();L h=T.4a.4L(e,W);7(h&&!3y(e))d=h.4K(k);N{L f=[],2L=[];P(L a=e;a&&3y(a);a=a.1b)2L.4U(a);P(L i=0;i<2L.M;i++)7(3y(2L[i])){f[i]=2L[i].Y.18;2L[i].Y.18="3u"}d=k=="18"&&f[2L.M-1]!=W?"2D":(h&&h.4K(k))||"";P(L i=0;i<f.M;i++)7(f[i]!=W)2L[i].Y.18=f[i]}7(k=="1y"&&d=="")d="1"}N 7(e.4j){L g=k.1p(/\\-(\\w)/g,J(a,b){K b.2F()});d=e.4j[k]||e.4j[g];7(!/^\\d+(2P)?$/i.17(d)&&/^\\d/.17(d)){L l=e.Y.2c,3A=e.3A.2c;e.3A.2c=e.4j.2c;e.Y.2c=d||0;d=e.Y.7l+"2P";e.Y.2c=l;e.3A.2c=3A}}K d},5c:J(l,h){L k=[];h=h||T;7(1v h.2R==\'10\')h=h.2u||h[0]&&h[0].2u||T;E.V(l,J(i,d){7(!d)K;7(d.1n==53)d=d.3D();7(1v d=="25"){d=d.1p(/(<(\\w+)[^>]*?)\\/>/g,J(b,a,c){K c.1E(/^(7k|7h|5Q|7f|48|5O|a3|3v|9Y|9W|9T)$/i)?b:a+"></"+c+">"});L f=E.3f(d).2w(),1u=h.2R("1u");L e=!f.1g("<9R")&&[1,"<2y 78=\'78\'>","</2y>"]||!f.1g("<9O")&&[1,"<77>","</77>"]||f.1E(/^<(9K|1S|9I|9F|9A)/)&&[1,"<1V>","</1V>"]||!f.1g("<4x")&&[2,"<1V><1S>","</1S></1V>"]||(!f.1g("<9y")||!f.1g("<9v"))&&[3,"<1V><1S><4x>","</4x></1S></1V>"]||!f.1g("<5Q")&&[2,"<1V><1S></1S><76>","</76></1V>"]||E.14.1d&&[1,"1u<1u>","</1u>"]||[0,"",""];1u.38=e[1]+d+e[2];2e(e[0]--)1u=1u.5D;7(E.14.1d){L g=!f.1g("<1V")&&f.1g("<1S")<0?1u.1B&&1u.1B.3p:e[1]=="<1V>"&&f.1g("<1S")<0?1u.3p:[];P(L j=g.M-1;j>=0;--j)7(E.12(g[j],"1S")&&!g[j].3p.M)g[j].1b.2X(g[j]);7(/^\\s/.17(d))1u.3n(h.5v(d.1E(/^\\s*/)[0]),1u.1B)}d=E.2H(1u.3p)}7(d.M===0&&(!E.12(d,"3i")&&!E.12(d,"2y")))K;7(d[0]==10||E.12(d,"3i")||d.11)k.1h(d);N k=E.34(k,d)});K k},1K:J(d,e,c){7(!d||d.15==3||d.15==8)K 10;L f=E.3W(d)?{}:E.3S;7(e=="2p"&&E.14.26)d.1b.44;7(f[e]){7(c!=10)d[f[e]]=c;K d[f[e]]}N 7(E.14.1d&&e=="Y")K E.1K(d.Y,"9r",c);N 7(c==10&&E.14.1d&&E.12(d,"3i")&&(e=="9q"||e=="9p"))K d.9n(e).6M;N 7(d.28){7(c!=10){7(e=="U"&&E.12(d,"48")&&d.1b)6Z"U 9i 9g\'t 9b 9a";d.99(e,""+c)}7(E.14.1d&&/6T|3R/.17(e)&&!E.3W(d))K d.4z(e,2);K d.4z(e)}N{7(e=="1y"&&E.14.1d){7(c!=10){d.6q=1;d.1F=(d.1F||"").1p(/6W\\([^)]*\\)/,"")+(2M(c).3D()=="93"?"":"6W(1y="+c*6S+")")}K d.1F&&d.1F.1g("1y=")>=0?(2M(d.1F.1E(/1y=([^)]*)/)[1])/6S).3D():""}e=e.1p(/-([a-z])/92,J(a,b){K b.2F()});7(c!=10)d[e]=c;K d[e]}},3f:J(a){K(a||"").1p(/^\\s+|\\s+$/g,"")},2H:J(b){L a=[];7(1v b!="91")P(L i=0,M=b.M;i<M;i++)a.1h(b[i]);N a=b.2V(0);K a},35:J(b,a){P(L i=0,M=a.M;i<M;i++)7(a[i]==b)K i;K-1},34:J(a,b){7(E.14.1d){P(L i=0;b[i];i++)7(b[i].15!=8)a.1h(b[i])}N P(L i=0;b[i];i++)a.1h(b[i]);K a},57:J(a){L c=[],2j={};1R{P(L i=0,M=a.M;i<M;i++){L b=E.Q(a[i]);7(!2j[b]){2j[b]=R;c.1h(a[i])}}}1W(e){c=a}K c},3x:J(c,a,d){7(1v a=="25")a=4A("S||J(a,i){K "+a+"}");L b=[];P(L i=0,M=c.M;i<M;i++)7(!d&&a(c[i],i)||d&&!a(c[i],i))b.1h(c[i]);K b},2a:J(d,a){L c=[];P(L i=0,M=d.M;i<M;i++){L b=a(d[i],i);7(b!==W&&b!=10){7(b.1n!=1N)b=[b];c=c.6Q(b)}}K c}});L v=8X.8V.2w();E.14={5n:(v.1E(/.+(?:8R|8Q|8P|8O)[\\/: ]([\\d.]+)/)||[])[1],26:/6L/.17(v),2B:/2B/.17(v),1d:/1d/.17(v)&&!/2B/.17(v),3X:/3X/.17(v)&&!/(8M|6L)/.17(v)};L y=E.14.1d?"6K":"6J";E.1s({8J:!E.14.1d||T.6I=="6H",3S:{"P":"8G","8E":"1w","4c":y,6J:y,6K:y,38:"38",1w:"1w",1C:"1C",2W:"2W",3o:"3o",8C:"8B",2p:"2p",8A:"8z",44:"44",6F:"6F",28:"28",12:"12"}});E.V({6E:"O.1b",8y:"19.4w(O,\'1b\')",8x:"19.31(O,2,\'2J\')",8w:"19.31(O,2,\'4v\')",8v:"19.4w(O,\'2J\')",8u:"19.4w(O,\'4v\')",8s:"19.5m(O.1b.1B,O)",8r:"19.5m(O.1B)",6C:"19.12(O,\'8q\')?O.8p||O.8o.T:19.2H(O.3p)"},J(c,d){d=4A("S||J(O){K "+d+"}");E.1i[c]=J(b){L a=E.2a(6,d);7(b&&1v b=="25")a=E.3d(b,a);K 6.2E(E.57(a))}});E.V({6B:"3t",8n:"6s",3n:"6o",8l:"5a",8k:"6P"},J(c,b){E.1i[c]=J(){L a=1a;K 6.V(J(){P(L i=0,M=a.M;i<M;i++)E(a[i])[b](6)})}});E.V({8j:J(a){E.1K(6,a,"");7(6.15==1)6.55(a)},8i:J(a){E.1w.1c(6,a)},8h:J(a){E.1w.1Y(6,a)},8g:J(a){E.1w[E.1w.3E(6,a)?"1Y":"1c"](6,a)},1Y:J(a){7(!a||E.1F(a,[6]).r.M){E("*",6).1c(6).V(J(){E.16.1Y(6);E.3H(6)});7(6.1b)6.1b.2X(6)}},4B:J(){E(">*",6).1Y();2e(6.1B)6.2X(6.1B)}},J(a,b){E.1i[a]=J(){K 6.V(b,1a)}});E.V(["8e","62"],J(i,c){L b=c.2w();E.1i[b]=J(a){K 6[0]==1e?E.14.2B&&T.1k["5h"+c]||E.14.26&&1e["8d"+c]||T.6I=="6H"&&T.1I["5h"+c]||T.1k["5h"+c]:6[0]==T?1Z.2b(1Z.2b(T.1k["5g"+c],T.1I["5g"+c]),1Z.2b(T.1k["5f"+c],T.1I["5f"+c])):a==10?(6.M?E.1m(6[0],b):W):6.1m(b,a.1n==4d?a:a+"2P")}});L C=E.14.26&&4t(E.14.5n)<8c?"(?:[\\\\w*4s-]|\\\\\\\\.)":"(?:[\\\\w\\8b-\\8a*4s-]|\\\\\\\\.)",6w=1D 4r("^>\\\\s*("+C+"+)"),6v=1D 4r("^("+C+"+)(#)("+C+"+)"),6u=1D 4r("^([#.]?)("+C+"*)");E.1s({5d:{"":"m[2]==\'*\'||19.12(a,m[2])","#":"a.4z(\'2s\')==m[2]",":":{88:"i<m[3]-0",86:"i>m[3]-0",31:"m[3]-0==i",6N:"m[3]-0==i",3j:"i==0",3M:"i==r.M-1",6r:"i%2==0",6p:"i%2","3j-4m":"a.1b.3V(\'*\')[0]==a","3M-4m":"19.31(a.1b.5D,1,\'4v\')==a","84-4m":"!19.31(a.1b.5D,2,\'4v\')",6E:"a.1B",4B:"!a.1B",83:"(a.6A||a.80||19(a).1t()||\'\').1g(m[3])>=0",4b:\'"23"!=a.U&&19.1m(a,"18")!="2D"&&19.1m(a,"4X")!="23"\',23:\'"23"==a.U||19.1m(a,"18")=="2D"||19.1m(a,"4X")=="23"\',7Y:"!a.2W",2W:"a.2W",3o:"a.3o",2p:"a.2p||19.1K(a,\'2p\')",1t:"\'1t\'==a.U",5w:"\'5w\'==a.U",5y:"\'5y\'==a.U",5b:"\'5b\'==a.U",3J:"\'3J\'==a.U",59:"\'59\'==a.U",6n:"\'6n\'==a.U",6m:"\'6m\'==a.U",2G:\'"2G"==a.U||19.12(a,"2G")\',48:"/48|2y|6l|2G/i.17(a.12)",3E:"19.2r(m[3],a).M",7X:"/h\\\\d/i.17(a.12)",7W:"19.3x(19.3I,J(1i){K a==1i.O;}).M"}},6j:[/^(\\[) *@?([\\w-]+) *([!*$^~=]*) *(\'?"?)(.*?)\\4 *\\]/,/^(:)([\\w-]+)\\("?\'?(.*?(\\(.*?\\))?[^(]*?)"?\'?\\)/,1D 4r("^([:.#]*)("+C+"+)")],3d:J(a,c,b){L d,2o=[];2e(a&&a!=d){d=a;L f=E.1F(a,c,b);a=f.t.1p(/^\\s*,\\s*/,"");2o=b?c=f.r:E.34(2o,f.r)}K 2o},2r:J(t,p){7(1v t!="25")K[t];7(p&&p.15!=1&&p.15!=9)K[];p=p||T;L d=[p],2j=[],3M,12;2e(t&&3M!=t){L r=[];3M=t;t=E.3f(t);L o=S;L g=6w;L m=g.39(t);7(m){12=m[1].2F();P(L i=0;d[i];i++)P(L c=d[i].1B;c;c=c.2J)7(c.15==1&&(12=="*"||c.12.2F()==12))r.1h(c);d=r;t=t.1p(g,"");7(t.1g(" ")==0)6z;o=R}N{g=/^([>+~])\\s*(\\w*)/i;7((m=g.39(t))!=W){r=[];L l={};12=m[2].2F();m=m[1];P(L j=0,3g=d.M;j<3g;j++){L n=m=="~"||m=="+"?d[j].2J:d[j].1B;P(;n;n=n.2J)7(n.15==1){L h=E.Q(n);7(m=="~"&&l[h])1T;7(!12||n.12.2F()==12){7(m=="~")l[h]=R;r.1h(n)}7(m=="+")1T}}d=r;t=E.3f(t.1p(g,""));o=R}}7(t&&!o){7(!t.1g(",")){7(p==d[0])d.4k();2j=E.34(2j,d);r=d=[p];t=" "+t.6i(1,t.M)}N{L k=6v;L m=k.39(t);7(m){m=[0,m[2],m[3],m[1]]}N{k=6u;m=k.39(t)}m[2]=m[2].1p(/\\\\/g,"");L f=d[d.M-1];7(m[1]=="#"&&f&&f.5N&&!E.3W(f)){L q=f.5N(m[2]);7((E.14.1d||E.14.2B)&&q&&1v q.2s=="25"&&q.2s!=m[2])q=E(\'[@2s="\'+m[2]+\'"]\',f)[0];d=r=q&&(!m[3]||E.12(q,m[3]))?[q]:[]}N{P(L i=0;d[i];i++){L a=m[1]=="#"&&m[3]?m[3]:m[1]!=""||m[0]==""?"*":m[2];7(a=="*"&&d[i].12.2w()=="4D")a="3v";r=E.34(r,d[i].3V(a))}7(m[1]==".")r=E.58(r,m[2]);7(m[1]=="#"){L e=[];P(L i=0;r[i];i++)7(r[i].4z("2s")==m[2]){e=[r[i]];1T}r=e}d=r}t=t.1p(k,"")}}7(t){L b=E.1F(t,r);d=r=b.r;t=E.3f(b.t)}}7(t)d=[];7(d&&p==d[0])d.4k();2j=E.34(2j,d);K 2j},58:J(r,m,a){m=" "+m+" ";L c=[];P(L i=0;r[i];i++){L b=(" "+r[i].1w+" ").1g(m)>=0;7(!a&&b||a&&!b)c.1h(r[i])}K c},1F:J(t,r,h){L d;2e(t&&t!=d){d=t;L p=E.6j,m;P(L i=0;p[i];i++){m=p[i].39(t);7(m){t=t.7V(m[0].M);m[2]=m[2].1p(/\\\\/g,"");1T}}7(!m)1T;7(m[1]==":"&&m[2]=="4I")r=G.17(m[3])?E.1F(m[3],r,R).r:E(r).4I(m[3]);N 7(m[1]==".")r=E.58(r,m[2],h);N 7(m[1]=="["){L g=[],U=m[3];P(L i=0,3g=r.M;i<3g;i++){L a=r[i],z=a[E.3S[m[2]]||m[2]];7(z==W||/6T|3R|2p/.17(m[2]))z=E.1K(a,m[2])||\'\';7((U==""&&!!z||U=="="&&z==m[5]||U=="!="&&z!=m[5]||U=="^="&&z&&!z.1g(m[5])||U=="$="&&z.6i(z.M-m[5].M)==m[5]||(U=="*="||U=="~=")&&z.1g(m[5])>=0)^h)g.1h(a)}r=g}N 7(m[1]==":"&&m[2]=="31-4m"){L e={},g=[],17=/(-?)(\\d*)n((?:\\+|-)?\\d*)/.39(m[3]=="6r"&&"2n"||m[3]=="6p"&&"2n+1"||!/\\D/.17(m[3])&&"7U+"+m[3]||m[3]),3j=(17[1]+(17[2]||1))-0,d=17[3]-0;P(L i=0,3g=r.M;i<3g;i++){L j=r[i],1b=j.1b,2s=E.Q(1b);7(!e[2s]){L c=1;P(L n=1b.1B;n;n=n.2J)7(n.15==1)n.4p=c++;e[2s]=R}L b=S;7(3j==0){7(j.4p==d)b=R}N 7((j.4p-d)%3j==0&&(j.4p-d)/3j>=0)b=R;7(b^h)g.1h(j)}r=g}N{L f=E.5d[m[1]];7(1v f!="25")f=E.5d[m[1]][m[2]];f=4A("S||J(a,i){K "+f+"}");r=E.3x(r,f,h)}}K{r:r,t:t}},4w:J(b,c){L d=[];L a=b[c];2e(a&&a!=T){7(a.15==1)d.1h(a);a=a[c]}K d},31:J(a,e,c,b){e=e||1;L d=0;P(;a;a=a[c])7(a.15==1&&++d==e)1T;K a},5m:J(n,a){L r=[];P(;n;n=n.2J){7(n.15==1&&(!a||n!=a))r.1h(n)}K r}});E.16={1c:J(f,i,g,e){7(f.15==3||f.15==8)K;7(E.14.1d&&f.54!=10)f=1e;7(!g.2A)g.2A=6.2A++;7(e!=10){L h=g;g=J(){K h.1j(6,1a)};g.Q=e;g.2A=h.2A}L j=E.Q(f,"2N")||E.Q(f,"2N",{}),1x=E.Q(f,"1x")||E.Q(f,"1x",J(){L a;7(1v E=="10"||E.16.52)K a;a=E.16.1x.1j(1a.3G.O,1a);K a});1x.O=f;E.V(i.2d(/\\s+/),J(c,b){L a=b.2d(".");b=a[0];g.U=a[1];L d=j[b];7(!d){d=j[b]={};7(!E.16.2l[b]||E.16.2l[b].4i.1O(f)===S){7(f.3F)f.3F(b,1x,S);N 7(f.6h)f.6h("4h"+b,1x)}}d[g.2A]=g;E.16.2g[b]=R});f=W},2A:1,2g:{},1Y:J(e,h,f){7(e.15==3||e.15==8)K;L i=E.Q(e,"2N"),2f,5E;7(i){7(h==10)P(L g 1r i)6.1Y(e,g);N{7(h.U){f=h.2k;h=h.U}E.V(h.2d(/\\s+/),J(b,a){L c=a.2d(".");a=c[0];7(i[a]){7(f)2T i[a][f.2A];N P(f 1r i[a])7(!c[1]||i[a][f].U==c[1])2T i[a][f];P(2f 1r i[a])1T;7(!2f){7(!E.16.2l[a]||E.16.2l[a].4g.1O(e)===S){7(e.6f)e.6f(a,E.Q(e,"1x"),S);N 7(e.6d)e.6d("4h"+a,E.Q(e,"1x"))}2f=W;2T i[a]}}})}P(2f 1r i)1T;7(!2f){L d=E.Q(e,"1x");7(d)d.O=W;E.3H(e,"2N");E.3H(e,"1x")}}},1U:J(f,b,c,d,g){b=E.2H(b||[]);7(!c){7(6.2g[f])E("*").1c([1e,T]).1U(f,b)}N{7(c.15==3||c.15==8)K 10;L a,2f,1i=E.1q(c[f]||W),16=!b[0]||!b[0].32;7(16)b.4U(6.51({U:f,2K:c}));b[0].U=f;7(E.1q(E.Q(c,"1x")))a=E.Q(c,"1x").1j(c,b);7(!1i&&c["4h"+f]&&c["4h"+f].1j(c,b)===S)a=S;7(16)b.4k();7(g&&E.1q(g)){2f=g.1j(c,a==W?b:b.6Q(a));7(2f!==10)a=2f}7(1i&&d!==S&&a!==S&&!(E.12(c,\'a\')&&f=="50")){6.52=R;1R{c[f]()}1W(e){}}6.52=S}K a},1x:J(c){L a;c=E.16.51(c||1e.16||{});L b=c.U.2d(".");c.U=b[0];L f=E.Q(6,"2N")&&E.Q(6,"2N")[c.U],3B=1N.2m.2V.1O(1a,1);3B.4U(c);P(L j 1r f){L d=f[j];3B[0].2k=d;3B[0].Q=d.Q;7(!b[1]||d.U==b[1]){L e=d.1j(6,3B);7(a!==S)a=e;7(e===S){c.32();c.41()}}}7(E.14.1d)c.2K=c.32=c.41=c.2k=c.Q=W;K a},51:J(c){L a=c;c=E.1s({},a);c.32=J(){7(a.32)a.32();a.7T=S};c.41=J(){7(a.41)a.41();a.7S=R};7(!c.2K)c.2K=c.7R||T;7(c.2K.15==3)c.2K=a.2K.1b;7(!c.4W&&c.4V)c.4W=c.4V==c.2K?c.7Q:c.4V;7(c.6b==W&&c.6a!=W){L b=T.1I,1k=T.1k;c.6b=c.6a+(b&&b.2i||1k&&1k.2i||0)-(b.68||0);c.7O=c.7N+(b&&b.2x||1k&&1k.2x||0)-(b.67||0)}7(!c.3r&&((c.4f||c.4f===0)?c.4f:c.66))c.3r=c.4f||c.66;7(!c.65&&c.64)c.65=c.64;7(!c.3r&&c.2G)c.3r=(c.2G&1?1:(c.2G&2?3:(c.2G&4?2:0)));K c},2l:{21:{4i:J(){5A();K},4g:J(){K}},47:{4i:J(){7(E.14.1d)K S;E(6).2z("4Q",E.16.2l.47.2k);K R},4g:J(){7(E.14.1d)K S;E(6).42("4Q",E.16.2l.47.2k);K R},2k:J(a){7(I(a,6))K R;1a[0].U="47";K E.16.1x.1j(6,1a)}},46:{4i:J(){7(E.14.1d)K S;E(6).2z("4P",E.16.2l.46.2k);K R},4g:J(){7(E.14.1d)K S;E(6).42("4P",E.16.2l.46.2k);K R},2k:J(a){7(I(a,6))K R;1a[0].U="46";K E.16.1x.1j(6,1a)}}}};E.1i.1s({2z:J(c,a,b){K c=="4O"?6.30(c,a,b):6.V(J(){E.16.1c(6,c,b||a,b&&a)})},30:J(d,b,c){K 6.V(J(){E.16.1c(6,d,J(a){E(6).42(a);K(c||b).1j(6,1a)},c&&b)})},42:J(a,b){K 6.V(J(){E.16.1Y(6,a,b)})},1U:J(c,a,b){K 6.V(J(){E.16.1U(c,a,6,R,b)})},63:J(c,a,b){7(6[0])K E.16.1U(c,a,6[0],S,b);K 10},2h:J(){L b=1a;K 6.50(J(a){6.4N=0==6.4N?1:0;a.32();K b[6.4N].1j(6,1a)||S})},7F:J(a,b){K 6.2z(\'47\',a).2z(\'46\',b)},21:J(a){5A();7(E.2Q)a.1O(T,E);N E.3w.1h(J(){K a.1O(6,E)});K 6}});E.1s({2Q:S,3w:[],21:J(){7(!E.2Q){E.2Q=R;7(E.3w){E.V(E.3w,J(){6.1j(T)});E.3w=W}E(T).63("21")}}});L x=S;J 5A(){7(x)K;x=R;7(T.3F&&!E.14.2B)T.3F("61",E.21,S);7(E.14.1d&&1e==3b)(J(){7(E.2Q)K;1R{T.1I.7D("2c")}1W(3e){3z(1a.3G,0);K}E.21()})();7(E.14.2B)T.3F("61",J(){7(E.2Q)K;P(L i=0;i<T.4M.M;i++)7(T.4M[i].2W){3z(1a.3G,0);K}E.21()},S);7(E.14.26){L a;(J(){7(E.2Q)K;7(T.3c!="60"&&T.3c!="1z"){3z(1a.3G,0);K}7(a===10)a=E("Y, 5O[7B=7A]").M;7(T.4M.M!=a){3z(1a.3G,0);K}E.21()})()}E.16.1c(1e,"43",E.21)}E.V(("7z,7y,43,7x,5g,4O,50,7w,"+"7v,7u,7C,4Q,4P,7t,2y,"+"59,7s,7r,7G,3e").2d(","),J(i,b){E.1i[b]=J(a){K a?6.2z(b,a):6.1U(b)}});L I=J(a,c){L b=a.4W;2e(b&&b!=c)1R{b=b.1b}1W(3e){b=c}K b==c};E(1e).2z("4O",J(){E("*").1c(T).42()});E.1i.1s({43:J(g,d,c){7(E.1q(g))K 6.2z("43",g);L e=g.1g(" ");7(e>=0){L i=g.2V(e,g.M);g=g.2V(0,e)}c=c||J(){};L f="4J";7(d)7(E.1q(d)){c=d;d=W}N{d=E.3v(d);f="5Z"}L h=6;E.3Q({1f:g,U:f,1G:"3q",Q:d,1z:J(a,b){7(b=="1X"||b=="5Y")h.3q(i?E("<1u/>").3t(a.4e.1p(/<1o(.|\\s)*?\\/1o>/g,"")).2r(i):a.4e);h.V(c,[a.4e,b,a])}});K 6},7q:J(){K E.3v(6.5X())},5X:J(){K 6.2a(J(){K E.12(6,"3i")?E.2H(6.7p):6}).1F(J(){K 6.37&&!6.2W&&(6.3o||/2y|6l/i.17(6.12)||/1t|23|3J/i.17(6.U))}).2a(J(i,c){L b=E(6).5P();K b==W?W:b.1n==1N?E.2a(b,J(a,i){K{37:c.37,1C:a}}):{37:c.37,1C:b}}).22()}});E.V("5W,5V,5U,69,5T,5S".2d(","),J(i,o){E.1i[o]=J(f){K 6.2z(o,f)}});L B=(1D 3O).3N();E.1s({22:J(d,b,a,c){7(E.1q(b)){a=b;b=W}K E.3Q({U:"4J",1f:d,Q:b,1X:a,1G:c})},7o:J(b,a){K E.22(b,W,a,"1o")},7n:J(c,b,a){K E.22(c,b,a,"2O")},7m:J(d,b,a,c){7(E.1q(b)){a=b;b={}}K E.3Q({U:"5Z",1f:d,Q:b,1X:a,1G:c})},7Z:J(a){E.1s(E.4H,a)},4H:{2g:R,U:"4J",2U:0,5R:"49/x-7j-3i-7i",6x:R,3l:R,Q:W,6t:W,3J:W,4n:{3L:"49/3L, 1t/3L",3q:"1t/3q",1o:"1t/4l, 49/4l",2O:"49/2O, 1t/4l",1t:"1t/7e",4o:"*/*"}},4q:{},3Q:J(s){L f,2Y=/=\\?(&|$)/g,1A,Q;s=E.1s(R,s,E.1s(R,{},E.4H,s));7(s.Q&&s.6x&&1v s.Q!="25")s.Q=E.3v(s.Q);7(s.1G=="4u"){7(s.U.2w()=="22"){7(!s.1f.1E(2Y))s.1f+=(s.1f.1E(/\\?/)?"&":"?")+(s.4u||"7d")+"=?"}N 7(!s.Q||!s.Q.1E(2Y))s.Q=(s.Q?s.Q+"&":"")+(s.4u||"7d")+"=?";s.1G="2O"}7(s.1G=="2O"&&(s.Q&&s.Q.1E(2Y)||s.1f.1E(2Y))){f="4u"+B++;7(s.Q)s.Q=(s.Q+"").1p(2Y,"="+f+"$1");s.1f=s.1f.1p(2Y,"="+f+"$1");s.1G="1o";1e[f]=J(a){Q=a;1X();1z();1e[f]=10;1R{2T 1e[f]}1W(e){}7(h)h.2X(g)}}7(s.1G=="1o"&&s.1Q==W)s.1Q=S;7(s.1Q===S&&s.U.2w()=="22"){L i=(1D 3O()).3N();L j=s.1f.1p(/(\\?|&)4s=.*?(&|$)/,"$a2="+i+"$2");s.1f=j+((j==s.1f)?(s.1f.1E(/\\?/)?"&":"?")+"4s="+i:"")}7(s.Q&&s.U.2w()=="22"){s.1f+=(s.1f.1E(/\\?/)?"&":"?")+s.Q;s.Q=W}7(s.2g&&!E.5M++)E.16.1U("5W");7((!s.1f.1g("9Z")||!s.1f.1g("//"))&&(s.1G=="1o"||s.1G=="2O")&&s.U.2w()=="22"){L h=T.3V("6k")[0];L g=T.2R("1o");g.3R=s.1f;7(s.7c)g.9X=s.7c;7(!f){L l=S;g.9V=g.9U=J(){7(!l&&(!6.3c||6.3c=="60"||6.3c=="1z")){l=R;1X();1z();h.2X(g)}}}h.3k(g);K 10}L m=S;L k=1e.7a?1D 7a("9S.9Q"):1D 79();k.9P(s.U,s.1f,s.3l,s.6t,s.3J);1R{7(s.Q)k.4G("9N-9M",s.5R);7(s.5I)k.4G("9L-5H-9J",E.4q[s.1f]||"9H, 9G 9E 9B 5G:5G:5G 9z");k.4G("X-9x-9u","79");k.4G("9t",s.1G&&s.4n[s.1G]?s.4n[s.1G]+", */*":s.4n.4o)}1W(e){}7(s.75)s.75(k);7(s.2g)E.16.1U("5S",[k,s]);L c=J(a){7(!m&&k&&(k.3c==4||a=="2U")){m=R;7(d){74(d);d=W}1A=a=="2U"&&"2U"||!E.73(k)&&"3e"||s.5I&&E.72(k,s.1f)&&"5Y"||"1X";7(1A=="1X"){1R{Q=E.71(k,s.1G)}1W(e){1A="5C"}}7(1A=="1X"){L b;1R{b=k.5B("70-5H")}1W(e){}7(s.5I&&b)E.4q[s.1f]=b;7(!f)1X()}N E.5t(s,k,1A);1z();7(s.3l)k=W}};7(s.3l){L d=54(c,13);7(s.2U>0)3z(J(){7(k){k.9m();7(!m)c("2U")}},s.2U)}1R{k.9l(s.Q)}1W(e){E.5t(s,k,W,e)}7(!s.3l)c();J 1X(){7(s.1X)s.1X(Q,1A);7(s.2g)E.16.1U("5T",[k,s])}J 1z(){7(s.1z)s.1z(k,1A);7(s.2g)E.16.1U("5U",[k,s]);7(s.2g&&!--E.5M)E.16.1U("5V")}K k},5t:J(s,a,b,e){7(s.3e)s.3e(a,b,e);7(s.2g)E.16.1U("69",[a,s,e])},5M:0,73:J(r){1R{K!r.1A&&9k.9j=="5b:"||(r.1A>=6Y&&r.1A<9h)||r.1A==6X||r.1A==9e||E.14.26&&r.1A==10}1W(e){}K S},72:J(a,c){1R{L b=a.5B("70-5H");K a.1A==6X||b==E.4q[c]||E.14.26&&a.1A==10}1W(e){}K S},71:J(r,b){L c=r.5B("9d-U");L d=b=="3L"||!b&&c&&c.1g("3L")>=0;L a=d?r.9c:r.4e;7(d&&a.1I.28=="5C")6Z"5C";7(b=="1o")E.5l(a);7(b=="2O")a=4A("("+a+")");K a},3v:J(a){L s=[];7(a.1n==1N||a.5j)E.V(a,J(){s.1h(3s(6.37)+"="+3s(6.1C))});N P(L j 1r a)7(a[j]&&a[j].1n==1N)E.V(a[j],J(){s.1h(3s(j)+"="+3s(6))});N s.1h(3s(j)+"="+3s(a[j]));K s.6g("&").1p(/%20/g,"+")}});E.1i.1s({1J:J(c,b){K c?6.27({1P:"1J",29:"1J",1y:"1J"},c,b):6.1F(":23").V(J(){6.Y.18=6.5x||"";7(E.1m(6,"18")=="2D"){L a=E("<"+6.28+" />").6B("1k");6.Y.18=a.1m("18");7(6.Y.18=="2D")6.Y.18="3u";a.1Y()}}).3h()},1H:J(b,a){K b?6.27({1P:"1H",29:"1H",1y:"1H"},b,a):6.1F(":4b").V(J(){6.5x=6.5x||E.1m(6,"18");6.Y.18="2D"}).3h()},6U:E.1i.2h,2h:J(a,b){K E.1q(a)&&E.1q(b)?6.6U(a,b):a?6.27({1P:"2h",29:"2h",1y:"2h"},a,b):6.V(J(){E(6)[E(6).3K(":23")?"1J":"1H"]()})},98:J(b,a){K 6.27({1P:"1J"},b,a)},97:J(b,a){K 6.27({1P:"1H"},b,a)},96:J(b,a){K 6.27({1P:"2h"},b,a)},95:J(b,a){K 6.27({1y:"1J"},b,a)},94:J(b,a){K 6.27({1y:"1H"},b,a)},9f:J(c,a,b){K 6.27({1y:a},c,b)},27:J(l,k,j,h){L i=E.6V(k,j,h);K 6[i.2S===S?"V":"2S"](J(){7(6.15!=1)K S;L g=E.1s({},i);L f=E(6).3K(":23"),4y=6;P(L p 1r l){7(l[p]=="1H"&&f||l[p]=="1J"&&!f)K E.1q(g.1z)&&g.1z.1j(6);7(p=="1P"||p=="29"){g.18=E.1m(6,"18");g.36=6.Y.36}}7(g.36!=W)6.Y.36="23";g.40=E.1s({},l);E.V(l,J(c,a){L e=1D E.2v(4y,g,c);7(/2h|1J|1H/.17(a))e[a=="2h"?f?"1J":"1H":a](l);N{L b=a.3D().1E(/^([+-]=)?([\\d+-.]+)(.*)$/),24=e.2o(R)||0;7(b){L d=2M(b[2]),2C=b[3]||"2P";7(2C!="2P"){4y.Y[c]=(d||1)+2C;24=((d||1)/e.2o(R))*24;4y.Y[c]=24+2C}7(b[1])d=((b[1]=="-="?-1:1)*d)+24;e.3Z(24,d,2C)}N e.3Z(24,a,"")}});K R})},2S:J(a,b){7(E.1q(a)||(a&&a.1n==1N)){b=a;a="2v"}7(!a||(1v a=="25"&&!b))K A(6[0],a);K 6.V(J(){7(b.1n==1N)A(6,a,b);N{A(6,a).1h(b);7(A(6,a).M==1)b.1j(6)}})},8Z:J(b,c){L a=E.3I;7(b)6.2S([]);6.V(J(){P(L i=a.M-1;i>=0;i--)7(a[i].O==6){7(c)a[i](R);a.6R(i,1)}});7(!c)6.5z();K 6}});L A=J(b,c,a){7(!b)K 10;c=c||"2v";L q=E.Q(b,c+"2S");7(!q||a)q=E.Q(b,c+"2S",a?E.2H(a):[]);K q};E.1i.5z=J(a){a=a||"2v";K 6.V(J(){L q=A(6,a);q.4k();7(q.M)q[0].1j(6)})};E.1s({6V:J(b,a,c){L d=b&&b.1n==8Y?b:{1z:c||!c&&a||E.1q(b)&&b,2t:b,3Y:c&&a||a&&a.1n!=8W&&a};d.2t=(d.2t&&d.2t.1n==53?d.2t:{9w:8U,8T:6Y}[d.2t])||8S;d.5o=d.1z;d.1z=J(){7(d.2S!==S)E(6).5z();7(E.1q(d.5o))d.5o.1j(6)};K d},3Y:{6O:J(p,n,b,a){K b+a*p},5F:J(p,n,b,a){K((-1Z.9C(p*1Z.9D)/2)+0.5)*a+b}},3I:[],3T:W,2v:J(b,c,a){6.11=c;6.O=b;6.1l=a;7(!c.3U)c.3U={}}});E.2v.2m={4C:J(){7(6.11.33)6.11.33.1j(6.O,[6.2I,6]);(E.2v.33[6.1l]||E.2v.33.4o)(6);7(6.1l=="1P"||6.1l=="29")6.O.Y.18="3u"},2o:J(a){7(6.O[6.1l]!=W&&6.O.Y[6.1l]==W)K 6.O[6.1l];L r=2M(E.1m(6.O,6.1l,a));K r&&r>-8N?r:2M(E.2q(6.O,6.1l))||0},3Z:J(c,b,d){6.5s=(1D 3O()).3N();6.24=c;6.3h=b;6.2C=d||6.2C||"2P";6.2I=6.24;6.4E=6.4F=0;6.4C();L e=6;J t(a){K e.33(a)}t.O=6.O;E.3I.1h(t);7(E.3T==W){E.3T=54(J(){L a=E.3I;P(L i=0;i<a.M;i++)7(!a[i]())a.6R(i--,1);7(!a.M){74(E.3T);E.3T=W}},13)}},1J:J(){6.11.3U[6.1l]=E.1K(6.O.Y,6.1l);6.11.1J=R;6.3Z(0,6.2o());7(6.1l=="29"||6.1l=="1P")6.O.Y[6.1l]="8L";E(6.O).1J()},1H:J(){6.11.3U[6.1l]=E.1K(6.O.Y,6.1l);6.11.1H=R;6.3Z(6.2o(),0)},33:J(a){L t=(1D 3O()).3N();7(a||t>6.11.2t+6.5s){6.2I=6.3h;6.4E=6.4F=1;6.4C();6.11.40[6.1l]=R;L b=R;P(L i 1r 6.11.40)7(6.11.40[i]!==R)b=S;7(b){7(6.11.18!=W){6.O.Y.36=6.11.36;6.O.Y.18=6.11.18;7(E.1m(6.O,"18")=="2D")6.O.Y.18="3u"}7(6.11.1H)6.O.Y.18="2D";7(6.11.1H||6.11.1J)P(L p 1r 6.11.40)E.1K(6.O.Y,p,6.11.3U[p])}7(b&&E.1q(6.11.1z))6.11.1z.1j(6.O);K S}N{L n=t-6.5s;6.4F=n/6.11.2t;6.4E=E.3Y[6.11.3Y||(E.3Y.5F?"5F":"6O")](6.4F,n,0,1,6.11.2t);6.2I=6.24+((6.3h-6.24)*6.4E);6.4C()}K R}};E.2v.33={2i:J(a){a.O.2i=a.2I},2x:J(a){a.O.2x=a.2I},1y:J(a){E.1K(a.O.Y,"1y",a.2I)},4o:J(a){a.O.Y[a.1l]=a.2I+a.2C}};E.1i.5f=J(){L b=0,3b=0,O=6[0],5q;7(O)8K(E.14){L d=O.1b,45=O,1M=O.1M,1L=O.2u,5p=26&&4t(5n)<8H,2Z=E.1m(O,"3C")=="2Z";7(O.7b){L c=O.7b();1c(c.2c+1Z.2b(1L.1I.2i,1L.1k.2i),c.3b+1Z.2b(1L.1I.2x,1L.1k.2x));1c(-1L.1I.68,-1L.1I.67)}N{1c(O.5k,O.5K);2e(1M){1c(1M.5k,1M.5K);7(3X&&!/^t(8F|d|h)$/i.17(1M.28)||26&&!5p)3a(1M);7(!2Z&&E.1m(1M,"3C")=="2Z")2Z=R;45=/^1k$/i.17(1M.28)?45:1M;1M=1M.1M}2e(d&&d.28&&!/^1k|3q$/i.17(d.28)){7(!/^a0|1V.*$/i.17(E.1m(d,"18")))1c(-d.2i,-d.2x);7(3X&&E.1m(d,"36")!="4b")3a(d);d=d.1b}7((5p&&(2Z||E.1m(45,"3C")=="4Z"))||(3X&&E.1m(45,"3C")!="4Z"))1c(-1L.1k.5k,-1L.1k.5K);7(2Z)1c(1Z.2b(1L.1I.2i,1L.1k.2i),1Z.2b(1L.1I.2x,1L.1k.2x))}5q={3b:3b,2c:b}}J 3a(a){1c(E.2q(a,"a1",R),E.2q(a,"8D",R))}J 1c(l,t){b+=4t(l)||0;3b+=4t(t)||0}K 5q}})();',62,624,'||||||this|if||||||||||||||||||||||||||||||||||||||function|return|var|length|else|elem|for|data|true|false|document|type|each|null||style||undefined|options|nodeName||browser|nodeType|event|test|display|jQuery|arguments|parentNode|add|msie|window|url|indexOf|push|fn|apply|body|prop|css|constructor|script|replace|isFunction|in|extend|text|div|typeof|className|handle|opacity|complete|status|firstChild|value|new|match|filter|dataType|hide|documentElement|show|attr|doc|offsetParent|Array|call|height|cache|try|tbody|break|trigger|table|catch|success|remove|Math||ready|get|hidden|start|string|safari|animate|tagName|width|map|max|left|split|while|ret|global|toggle|scrollLeft|done|handler|special|prototype||cur|selected|curCSS|find|id|duration|ownerDocument|fx|toLowerCase|scrollTop|select|bind|guid|opera|unit|none|pushStack|toUpperCase|button|makeArray|now|nextSibling|target|stack|parseFloat|events|json|px|isReady|createElement|queue|delete|timeout|slice|disabled|removeChild|jsre|fixed|one|nth|preventDefault|step|merge|inArray|overflow|name|innerHTML|exec|border|top|readyState|multiFilter|error|trim|rl|end|form|first|appendChild|async|elems|insertBefore|checked|childNodes|html|which|encodeURIComponent|append|block|param|readyList|grep|color|setTimeout|runtimeStyle|args|position|toString|has|addEventListener|callee|removeData|timers|password|is|xml|last|getTime|Date|domManip|ajax|src|props|timerId|orig|getElementsByTagName|isXMLDoc|mozilla|easing|custom|curAnim|stopPropagation|unbind|load|selectedIndex|offsetChild|mouseleave|mouseenter|input|application|defaultView|visible|float|String|responseText|charCode|teardown|on|setup|currentStyle|shift|javascript|child|accepts|_default|nodeIndex|lastModified|RegExp|_|parseInt|jsonp|previousSibling|dir|tr|self|getAttribute|eval|empty|update|object|pos|state|setRequestHeader|ajaxSettings|not|GET|getPropertyValue|getComputedStyle|styleSheets|lastToggle|unload|mouseout|mouseover|andSelf|getWH|container2|unshift|fromElement|relatedTarget|visibility|init|absolute|click|fix|triggered|Number|setInterval|removeAttribute|prevObject|unique|classFilter|submit|after|file|clean|expr|windowData|offset|scroll|client|deep|jquery|offsetLeft|globalEval|sibling|version|old|safari2|results|wrapAll|startTime|handleError|container|createTextNode|radio|oldblock|checkbox|dequeue|bindReady|getResponseHeader|parsererror|lastChild|index|swing|00|Modified|ifModified|clone|offsetTop|values|active|getElementById|link|val|col|contentType|ajaxSend|ajaxSuccess|ajaxComplete|ajaxStop|ajaxStart|serializeArray|notmodified|POST|loaded|DOMContentLoaded|Width|triggerHandler|ctrlKey|metaKey|keyCode|clientTop|clientLeft|ajaxError|clientX|pageX|cloneNode|detachEvent|swap|removeEventListener|join|attachEvent|substr|parse|head|textarea|reset|image|before|odd|zoom|even|prepend|username|quickClass|quickID|quickChild|processData|uuid|continue|textContent|appendTo|contents|evalScript|parent|defaultValue|setArray|CSS1Compat|compatMode|cssFloat|styleFloat|webkit|nodeValue|eq|linear|replaceWith|concat|splice|100|href|_toggle|speed|alpha|304|200|throw|Last|httpData|httpNotModified|httpSuccess|clearInterval|beforeSend|colgroup|fieldset|multiple|XMLHttpRequest|ActiveXObject|getBoundingClientRect|scriptCharset|callback|plain|img|hasClass|br|urlencoded|www|abbr|pixelLeft|post|getJSON|getScript|elements|serialize|keypress|keydown|change|mouseup|mousedown|dblclick|resize|focus|blur|stylesheet|rel|mousemove|doScroll|round|hover|keyup|padding|offsetHeight|offsetWidth|Bottom|Top|Right|clientY|pageY|Left|toElement|srcElement|cancelBubble|returnValue|0n|substring|animated|header|enabled|ajaxSetup|innerText|noConflict|size|contains|only|line|gt|weight|lt|font|uFFFF|u0128|417|inner|Height|Boolean|toggleClass|removeClass|addClass|removeAttr|replaceAll|insertAfter|wrap|prependTo|contentWindow|contentDocument|iframe|children|siblings|wrapInner|prevAll|nextAll|prev|next|parents|maxLength|maxlength|readOnly|readonly|borderTopWidth|class|able|htmlFor|522|reverse|boxModel|with|1px|compatible|10000|ie|ra|it|rv|400|fast|600|userAgent|Function|navigator|Object|stop|option|array|ig|NaN|fadeOut|fadeIn|slideToggle|slideUp|slideDown|setAttribute|changed|be|responseXML|content|1223|fadeTo|can|300|property|protocol|location|send|abort|getAttributeNode|specified|method|action|cssText|attributes|Accept|With|th|slow|Requested|td|GMT|cap|1970|cos|PI|Jan|colg|01|Thu|tfoot|Since|thead|If|Type|Content|leg|open|XMLHTTP|opt|Microsoft|embed|onreadystatechange|onload|area|charset|hr|http|inline|borderLeftWidth|1_|meta'.split('|'),0,{}))
//jQuery.noConflict();


var array;
jQuery(document).ready
(       

        function( ) 
	{
		rentaFijaAjax.getNemotecnicos( getAnswer );
	}
);


        var getAnswer = function( answer )
        {
        array = answer;
        jQuery("#renta3").autocompleteArray ( array, { delay:10, minChars:1, matchSubset:1, onItemSelect:selectItem, onFindValue:findValue, autoFill:true, maxItemsToShow:10 } );
        }

function MM_changeProp(objId,x,theProp,theValue) { //v9.0
  var obj = null; with (document){ if (getElementById)
  obj = getElementById(objId); }
  if (obj){
    if (theValue == true || theValue == false)
      eval("obj.style."+theProp+"="+theValue);
    else eval("obj.style."+theProp+"='"+theValue+"'");
  }
}

mostrar_renta_aclaracion = function(){
	document.getElementById('text_renta_aclaracion').style.display='block';
}

ocultar_renta_aclaracion = function(){
	document.getElementById('text_renta_aclaracion').style.display='none';
}
//-->
</script>
 
<div class="tab_contenedor_mercados" style="float:left; display:block; position:relative; z-index:100"> 
	<div class="tab_contenido_mercados">        
    	<form id="formulario" method="post"	name="formulario" action="<%=formAction%>" >
      		<div id="renta" class="panelactivo_merc_divisas">
     	    	<ul class="mercados">
					<li id="text_29">
						<label><span id="text_6">Tipo de Mercado :</span>
							<select name="tipoMercado" size="1" class="operacion" id="renta_detalle1" >
     							<option value="%" selected="selected">Todos</option>
   								<option 
									<c:if test="${RentaFijaBusqueda.tipoMercado eq 'T'}">
										selected="selected"
									</c:if> 
									value="T" >
									Transaccional
								</option>  
      							<option 
									<c:if test="${RentaFijaBusqueda.tipoMercado eq 'R'}">
										selected="selected"
									</c:if> 
									value="R">
									Registro
								</option>
   							</select>
   						</label>
					</li>
					<li class="fechaneg">
						<label style="width: auto;">
							<span id="text_7">Fecha de negociación :</span>
							<span class="fechalista">
                            	<select name="diaFecha" size="1" id="derivados_dia">                                                   
                                	<c:forEach items="${dias}" var="dia">
                                    	<option <c:if test="${RentaFijaBusqueda.diaFecha==dia.key}">selected="selected"</c:if> value='<c:out value="${dia.key}"/>'><c:out value="${dia.value}"/></option>
                                    </c:forEach>
                                </select>
                            </span>
                        </label>
                        <label style="width: auto;">            
                        	<span class="fechalista" style="width: 50px">
                            	<select name="mesFecha" size="1" id="derivados_mes">
                                	<c:forEach items="${meses}" var="mes">
                                    	<option <c:if test="${RentaFijaBusqueda.mesFecha==mes.key}">selected="selected"</c:if> value='<c:out value="${mes.key}"/>'><c:out value="${mes.value}"/></option>
                                    </c:forEach>
                                </select>
                            </span>
                        </label>
                        <label style="width: auto;">
                        	<span class="fechalista" style="width: 100px">
                            	<select name="anioFecha" size="1" id="derivados_ano">                                                   
                                	<c:forEach items="${anios}" var="anio">
                                    	<option <c:if test="${RentaFijaBusqueda.anioFecha==anio.key}">selected="selected"</c:if> value='<c:out value="${anio.key}"/>'><c:out value="${anio.value}"/></option>
                                    </c:forEach>
                                </select>
                           	</span>
                        </label>
					</li>
					<li>
						<label><span id="text_9">Tipo de operaci&oacute;n:</span>
							<select name="tipoOperacion"  class="operacion" id="renta_detalle2" >
								<option value="%" selected="selected">Todos</option>
								<option 
									<c:if test="${RentaFijaBusqueda.tipoOperacion eq 'CV'}">
										selected="selected"
									</c:if> 
									value="CV">
									Compraventas
								</option>
								<option 
									<c:if test="${RentaFijaBusqueda.tipoOperacion eq 'RR'}">
										selected="selected"
									</c:if> 
									value="RR">
									Repos
								</option>
								<option 
									<c:if test="${RentaFijaBusqueda.tipoOperacion eq 'SI'}">
										selected="selected"
									</c:if> 
									value="SI">
									Simultaneas
								</option>
								<option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'TR'}">selected="selected"</c:if> value="TR">TTV</option>
								<option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'CA'}">selected="selected"</c:if> value="CA">Carrusel</option>
								<option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'SW'}">selected="selected"</c:if> value="SW">Swap</option>
								<option <c:if test="${RentaFijaBusqueda.tipoOperacion eq 'PL'}">selected="selected"</c:if> value="PL">Plazo</option>
							</select>
						</label>
					</li>
				    <li style="width: 350px;">
						<label><span id="text_8">Títulos de deuda :</span>	
             				<select name="tipoDeuda" size="1" class="operacion" id="renta_detalle2" style="width: 180px;">
             					<option value="TODOS" selected="selected">Todos</option>
						        <option <c:if test="${RentaFijaBusqueda.tipoDeuda eq 'PUBLICO'}">selected="selected"</c:if> value="PUBLICO" >Pública</option>
						        <option <c:if test="${RentaFijaBusqueda.tipoDeuda eq 'PRIVADO'}">selected="selected"</c:if> value="PRIVADO">Privada</option>
             				</select>
						</label>
					</li>
              		<li style="position:relative">
						<label><span id="text_10">Sesión de negociación :</span>
							<select name="tipoSesion" size="1" class="operacion" id="renta_detalle2">
								<option value="TODAS" selected="selected">Todos</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'REG'}">selected="selected"</c:if> value="REG">REG</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'TRD'}">selected="selected"</c:if> value="TRD">TRD</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'REPI'}">selected="selected"</c:if> value="REPI">REPI</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'SPOT'}">selected="selected"</c:if> value="SPOT">SPOT</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'SOLO'}">selected="selected"</c:if> value="SOLO">SOLO</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'RYS'}">selected="selected"</c:if> value="RYS">RYS</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'BALO'}">selected="selected"</c:if> value="BALO">BALO</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'DEXT'}">selected="selected"</c:if> value="DEXT">DEXT</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'COTI'}">selected="selected"</c:if> value="COTI">COTI</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'TTV'}">selected="selected"</c:if> value="TTV">TTV</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'DEES'}">selected="selected"</c:if> value="DEES">DEES</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'LIQ'}">selected="selected"</c:if> value="LIQ">LIQ</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'RYSE'}">selected="selected"</c:if> value="RYSE">RYSE</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'SUBA'}">selected="selected"</c:if> value="SUBA">SUBA</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'DSER'}">selected="selected"</c:if> value="DSER">DSER</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'TTVP'}">selected="selected"</c:if> value="TTVP">TTVP</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'SERI'}">selected="selected"</c:if> value="SERI">SERI*</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'CONT'}">selected="selected"</c:if> value="CONT">CONT*</option>
								<option <c:if test="${RentaFijaBusqueda.tipoSesion eq 'INVE'}">selected="selected"</c:if> value="INVE">INVE*</option>
							</select>
						</label>
						<b class="link" onmouseover="mostrar_renta_aclaracion();" onmouseout="ocultar_renta_aclaracion();" style="float:left; position:relative">
							<a href="#">(?)</a>
						</b>
								
								<span id="text_renta_aclaracion" style="display:none;position:absolute;float: left; height: auto; width:200px; margin:0px; padding:0px;z-index:850; left:100%">    
								<div class="ra_h2" id="text_ra_h">Transaccional:</div>
						        <div class="ra_h3" id="text_ra_h">Deuda Pública de Orden Nacional</div>
						                <div class="ra_h4" id="text_ra_h">SPOT  : Sobre Lote T=0</div>
						                <div class="ra_h4" id="text_ra_h">SOLO  : Sobre Lote plazo mayor a T=0</div>
						                <div class="ra_h4" id="text_ra_h">RYS    : Repos y Simultáneas</div>
						                <div class="ra_h4" id="text_ra_h">BALO  : Bajo Lote</div>
						                <div class="ra_h4" id="text_ra_h">DEXT  : Deuda Externa</div>
						                <div class="ra_h4" id="text_ra_h">BOCE  : Deuda Externa</div>
						                <div class="ra_h4" id="text_ra_h">SIBO  : Deuda Externa</div>
						                <div class="ra_h4" id="text_ra_h">COTI  : Cotizaciones</div>
						                <div class="ra_h4" id="text_ra_h">TTV   : Transferencia Temporal de Valores</div>
						         <div class="ra_h3" id="text_ra_h">Deuda Privada y Deuda Pública de Orden No Nacional</div>
						                <div class="ra_h4" id="text_ra_h">DEES  : Deuda Estandarizada</div>
						                <div class="ra_h4" id="text_ra_h">RYSE  : Repos y Simultáneas Deuda Estandarizada</div>
						                <div class="ra_h4" id="text_ra_h">SUBA  : Subastas Deuda Estandarizada</div>
						                <div class="ra_h4" id="text_ra_h">DSER  : Remate Serializado Deuda No Estandarizada</div>
						                <div class="ra_h4" id="text_ra_h">TTVP  : Transferencia Temporal de Valores</div>
						    	<div class="ra_h2" id="text_ra_h">Registro:</div>
						          <div class="ra_h4" id="text_ra_h">REG    : Repos, Simultáneas y TTV Registro con Confirmación</div>
						          <div class="ra_h4" id="text_ra_h">TRD    : C/V Registro con Confirmación</div>
						          <div class="ra_h4" id="text_ra_h">REPI   : Registro sin Confirmación</div>
								  </span>
					</li>
					<li class="fechaneg" >
               			<label><span id="text_11">Consultar otro titulo :</span>
               				<input name="nemo" type="text" id="renta3" style="width: 180px;"/>
               			</label>
			   			<div class="boton_regular" >
			   				<a href="javascript:document.formulario.submit();">Buscar</a>
						</div>
             		</li>
				</ul>
			</div>
		</form>
  	</div>
  	<div class="limpiar"></div>
</div>
<br clear="all" />
<br/>
<div class="titulo_seccion_unacolumna" id="text_12">
	<div class="subtitulo_mercados">Mercado de Renta Fija </div>
	<div class="subtitulo_mercados_pequeno"><c:out value="${estadoMercado}"/></div>
</div>
<div id="nota_titulo">
	<h3>&nbsp;</h3>
    <h2>Informaci&oacute;n con 20 minutos de retraso</h2>
</div>
<br clear="all" />
<div class="contenedor_acordeon_borde">
	<div class="contenedor_acordeon_sin_borde">
  		<div id="acordeon_contenedor" >
    		<div class="acordeon_titulo_interior">
    			<div class="acordeon_titulo_segmento">
       				<p id="text_13">Vol&uacute;menes negociados</p>
       				<div id="text_ver_seg_1" class="ver_detalles_bvc" onclick="
       					mostrar_segmento_acordeon('seg_acc_1');
       					mostrar_segmento_acordeon('text_cerrar_seg_1');
       					ocultar_segmento_acordeon('text_ver_seg_1');
       					ocultar_segmento_acordeon('seg_acc_2');
       					ocultar_segmento_acordeon('seg_acc_3');
       					ocultar_segmento_acordeon('seg_acc_4');
       					ocultar_segmento_acordeon('text_cerrar_seg_2');
       					ocultar_segmento_acordeon('text_cerrar_seg_3');
       					ocultar_segmento_acordeon('text_cerrar_seg_4');
       					mostrar_segmento_acordeon('text_ver_seg_2');
       					mostrar_segmento_acordeon('text_ver_seg_3');        
       					mostrar_segmento_acordeon('text_ver_seg_4');        
       					" style="display:none"> Ver detalles
					</div>
       				<div id="text_cerrar_seg_1" class="cerrar_detalle_bvc" onclick="
       					ocultar_segmento_acordeon('seg_acc_1');
       					ocultar_segmento_acordeon('text_cerrar_seg_1');
       					mostrar_segmento_acordeon('text_ver_seg_1');
       					" style="display:block"> cerrar 
					</div>
    			</div>
    			<div class="encabezado_tablas_volumenes">
       				<table align="left" cellpadding="0" cellspacing="0" class="tabla_renta_fija" id="text_31">
       					<tr>
           					<th width="29%" align="left">Tipo de operaci&oacute;n</th>
           					<th width="30%">Volumen (COP)</th>
           					<th width="31%">Participaci&oacute;n %</th>
       					</tr>
       				</table>
    			</div>
    		</div>
    		<div id="seg_acc_1" class="acordeon_interior_contenido" style="display:block">
    			<!--div class="acordeon_titulo_contenedor_resumen"-->
       			<table align="left" cellpadding="0" cellspacing="0" class="tabla_renta_fija" id="text_14">
       				<tr>
           				<th width="29%" align="left" class="specblue">Compraventas (C/V)</th>
           				<td width="30%" align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenTransaccionalxOPE[6]}"/></fmt:formatNumber></td>
           				<td width="31%" align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesTransaccionalxOPE[6]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
           				<th align="left" class="specblue">Repos</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenTransaccionalxOPE[1]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesTransaccionalxOPE[1]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
           				<th align="left" class="specblue">Simult&aacute;neas</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenTransaccionalxOPE[0]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesTransaccionalxOPE[0]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
           				<th align="left" class="specblue">Transferencia Temporal de Valores (TTV)</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenTransaccionalxOPE[2]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesTransaccionalxOPE[2]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
           				<th align="left" class="specblue">Carrusel</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenTransaccionalxOPE[4]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesTransaccionalxOPE[4]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
           				<th align="left" class="specblue">Swap</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenTransaccionalxOPE[3]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesTransaccionalxOPE[3]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
         				<th align="left" class="specblue">Plazo</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenTransaccionalxOPE[5]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesTransaccionalxOPE[5]}"/></fmt:formatNumber>%</td>
       				</tr>
       			</table>
    			<!--/div-->
    		</div>
    		<div class="acordeon_titulo_contenedor_resumen">
    			<table align="left" cellpadding="0" cellspacing="0" class="tabla_renta_fija" id="text_30">
      				<tr>
       					<th width="29%" align="left" class="specalt">Total</th>
       					<td width="30%" align="right" class="alt"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.totalVolumenTransaccional}"/></fmt:formatNumber></td>
       					<td width="31%" align="right" class="alt">100.00%</td>
       				</tr>
    			</table>
    		</div>
    		<div class="acordeon_titulo_interior">
    			<div class="acordeon_titulo_segmento">
      				<p id="text_15">Vol&uacute;menes registrados</p>
					<div id="text_ver_seg_2" class="ver_detalles_bvc" onclick="
       					mostrar_segmento_acordeon('seg_acc_2');
       					mostrar_segmento_acordeon('text_cerrar_seg_2');
       					ocultar_segmento_acordeon('text_ver_seg_2');
       					ocultar_segmento_acordeon('seg_acc_1');
       					ocultar_segmento_acordeon('seg_acc_3');
       					ocultar_segmento_acordeon('seg_acc_4');
       					ocultar_segmento_acordeon('text_cerrar_seg_1');
       					ocultar_segmento_acordeon('text_cerrar_seg_3');
       					ocultar_segmento_acordeon('text_cerrar_seg_4');
       					mostrar_segmento_acordeon('text_ver_seg_1');
       					mostrar_segmento_acordeon('text_ver_seg_3');
       					mostrar_segmento_acordeon('text_ver_seg_4');
       					"> Ver detalles
					</div>
       				<div id="text_cerrar_seg_2" class="cerrar_detalle_bvc" onclick="
       					ocultar_segmento_acordeon('seg_acc_2');
       					ocultar_segmento_acordeon('text_cerrar_seg_2');
       					mostrar_segmento_acordeon('text_ver_seg_2');
       					"> cerrar 
					</div>
    			</div>
    			<div class="encabezado_tablas_volumenes">
       				<table align="left" cellpadding="0" cellspacing="0" class="tabla_renta_fija" id="text_32">
       					<tr>
           					<th width="29%" align="left">Tipo de operaci&oacute;n</th>
           					<th width="30%">Volumen (COP)</th>
           					<th width="31%">Participaci&oacute;n %</th>
       					</tr>
       				</table>
    			</div>
       			<br clear="all" />
    		</div>
    		<div id="seg_acc_2" class="acordeon_interior_contenido">
    			<!--div class="acordeon_titulo_contenedor_resumen"-->
       			<table align="left" cellpadding="0" cellspacing="0" class="tabla_renta_fija" id="text_16">
       				<tr>
           				<th width="29%" align="left" class="specblue">Compraventas (C/V)</th>
           				<td width="30%" align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenRegistroxOPE[6]}"/></fmt:formatNumber></td>
           				<td width="31%" align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesRegistroxOPE[6]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
          				<th align="left" class="specblue">Repos</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenRegistroxOPE[1]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesRegistroxOPE[1]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
           				<th align="left" class="specblue">Simult&aacute;neas</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenRegistroxOPE[0]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesRegistroxOPE[0]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
           				<th align="left" class="specblue">Transferencia Temporal de Valores (TTV)</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenRegistroxOPE[2]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesRegistroxOPE[2]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
           				<th align="left" class="specblue">Carrusel</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenRegistroxOPE[4]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesRegistroxOPE[4]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
          				<th align="left" class="specblue">Swap</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenRegistroxOPE[3]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesRegistroxOPE[3]}"/></fmt:formatNumber>%</td>
       				</tr>
       				<tr>
           				<th align="left" class="specblue">Plazo</th>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.volumenRegistroxOPE[5]}"/></fmt:formatNumber></td>
           				<td align="right"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.porcentajesRegistroxOPE[5]}"/></fmt:formatNumber>%</td>
       				</tr>
       			</table>
    			<!--/div-->
    		</div>
       		<br clear="all" />
    		<div class="acordeon_titulo_contenedor_resumen">
    			<table align="left" cellpadding="0" cellspacing="0" class="tabla_renta_fija" id="text_33">
       				<tr>
       					<th width="29%" align="left" class="specalt">Total</th>
       					<td width="30%" align="right" class="alt"><fmt:formatNumber pattern="###,##0.00" ><c:out value="${resumenRFTotales.totalVolumenRegistro}"/></fmt:formatNumber></td>
       					<td width="31%" align="right" class="alt">100.00%</td>
       				</tr>
    			</table>
    		</div>
        	<br clear="all" />
    		<div class="acordeon_titulo_interior">
        		<div class="acordeon_titulo_segmento">
           			<p id="text_17">Deuda p&uacute;blica</p>
					<div id="text_ver_seg_3" class="ver_detalles_bvc" onclick="
        				mostrar_segmento_acordeon('seg_acc_3');
        				mostrar_segmento_acordeon('text_cerrar_seg_3');
        				ocultar_segmento_acordeon('text_ver_seg_3');
        				ocultar_segmento_acordeon('seg_acc_1');
        				ocultar_segmento_acordeon('seg_acc_2');
        				ocultar_segmento_acordeon('seg_acc_4');
        				ocultar_segmento_acordeon('text_cerrar_seg_1');
        				ocultar_segmento_acordeon('text_cerrar_seg_2');
        				ocultar_segmento_acordeon('text_cerrar_seg_4');
        				mostrar_segmento_acordeon('text_ver_seg_1');
        				mostrar_segmento_acordeon('text_ver_seg_2');
        				mostrar_segmento_acordeon('text_ver_seg_4');  
        				"> Ver detalles
					</div>
        			<div id="text_cerrar_seg_3" class="cerrar_detalle_bvc" onclick="
        				ocultar_segmento_acordeon('seg_acc_3');
        				ocultar_segmento_acordeon('text_cerrar_seg_3');
        				mostrar_segmento_acordeon('text_ver_seg_3');
        				"> cerrar 
					</div>
          		</div>
    			<br clear="all" />
        	</div>
    		<br clear="all" />
			<div id="seg_acc_3" class="acordeon_interior_contenido">
				<display:table name="resumenRF.titulosDeudaPublica" defaultorder="ascending" class="tabla_general" id="text_titulo" cellpadding="0" cellspacing="0" >
					<display:column style="text-align: left; width:220px;" property="nombre" title="Tipo de t&iacute;tulo"/>
					<display:column format="{0,number,###,###}" style="text-align: right; width:180px;" property="cantidad" title="Cantidad &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"/>
					<display:column format="{0,number,###,###.00}" style="text-align: right;" property="volumen" title="Volumen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"/>
					<display:column style="text-align: center; width:180px">
						<div style="width:180px; margin:0px; padding:0px;" id="cerrar_pu<c:out value="${text_titulo_rowNum}"/>" class="renta_btn_cerrar" onclick="manejarUltimoDetalleTabla('1', '<c:out value="${text_titulo_rowNum}"/>');">
							Cerrar <img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/ico_cerrar.gif" alt="Cerrar" border="0" />
						</div>
	               		<div style="width:180px; margin:0px; padding:0px;" id="ver_pu<c:out value="${text_titulo_rowNum}"/>" class="renta_btn" onclick="manejarUltimoDetalleTabla('0', '<c:out value="${text_titulo_rowNum}"/>');">
	               			Ver detalle <img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/ico_cerrar.gif" alt="Cerrar" border="0" />
	               		</div>
						</td></tr>
						<tr id="detalle<c:out value="${text_titulo_rowNum}"/>" >
							<td colspan="4" style="padding: 0px; border-right: 0px none; border-bottom: 0px none; margin:0px;">
								<div id="deuda_pu<c:out value="${text_titulo_rowNum}"/>" class="renta_acordeon_interior" style="padding: 0px; border-bottom: none" >
	           						<table align="left" cellpadding="0" cellspacing="0" class="tabla_general" id="text_20" style="border: none;">
               								<tr>
				                   				<th width="220px" align="left" class="specblue" style="border-left: none;">Nemot&eacute;cnico</th>
				                   				<th width="180px" class="specblue">Emisor</th>
				                   				<th  class="specblue"  >Cantidad</th>
				                   				<th  class="specblue"  style="border-right: none; width:180px;">Volumen</th>
			                   				</tr>
	               							<c:forEach items="${text_titulo.especies}" var="especie">
				                  				<tr>
				                    				<td class="specblue" width="220px" style="border-left: none;"><a href="<portlet:actionURL><portlet:param name="action" value="detalle"/><portlet:param name="nemo"><jsp:attribute name="value"><c:out value="${especie.value.nemotecnico}"/></jsp:attribute>
					                					</portlet:param></portlet:actionURL>"><c:out value="${especie.value.nemotecnico}"/></a></td>
				                    				<td class="spec" width="180px"><c:out value="${especie.value.emisor}"/></td>
				                    				<td align="right"><fmt:formatNumber pattern="###,###" ><c:out value="${especie.value.cantidad}"/></fmt:formatNumber></td>
				                    				<td align="right" style="width:180px; border-right:  none;"><fmt:formatNumber pattern="###,###.##" ><c:out value="${especie.value.volumen}"/></fmt:formatNumber></td>
				                  				</tr>
				                  			</c:forEach>
			                		</table>
			              		</div>
					</display:column>
				</display:table>
			</div>
        	<div class="acordeon_titulo_interior">
          		<div class="acordeon_titulo_segmento">
            		<p id="text_22">Deuda privada</p>
					<div id="text_ver_seg_4" class="ver_detalles_bvc" onclick="
        				mostrar_segmento_acordeon('seg_acc_4');
        				mostrar_segmento_acordeon('text_cerrar_seg_4');
        				ocultar_segmento_acordeon('text_ver_seg_4');
        				ocultar_segmento_acordeon('seg_acc_1');
        				ocultar_segmento_acordeon('seg_acc_3');
        				ocultar_segmento_acordeon('seg_acc_2');
        				ocultar_segmento_acordeon('text_cerrar_seg_1');
        				ocultar_segmento_acordeon('text_cerrar_seg_3');
        				ocultar_segmento_acordeon('text_cerrar_seg_2');
        				mostrar_segmento_acordeon('text_ver_seg_1');
        				mostrar_segmento_acordeon('text_ver_seg_3');
        				mostrar_segmento_acordeon('text_ver_seg_2');
        				"> Ver detalles
					</div>
        			<div id="text_cerrar_seg_4" class="cerrar_detalle_bvc" onclick="
        				ocultar_segmento_acordeon('seg_acc_4');
        				ocultar_segmento_acordeon('text_cerrar_seg_4');
        				mostrar_segmento_acordeon('text_ver_seg_4');
        				"> cerrar 
					</div>
					
          		</div>
        	</div>
        	<div id="seg_acc_4" class="acordeon_interior_contenido">
          		<display:table name="resumenRF.titulosDeudaPrivada" defaultorder="ascending" class="tabla_general" id="text_tituloPrivada" cellpadding="0" cellspacing="0" >
					<display:column style="text-align: left; width:227px;" property="nombre" title="Tipo de t&iacute;tulo"/>
					<display:column format="{0,number,###,###}" style="text-align: right; width:180px;" property="cantidad" title="Cantidad &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"/>
					<display:column format="{0,number,###,###.00}" style="text-align: right;" property="volumen" title="Volumen &nbsp;&nbsp;&nbsp;&nbsp;" sortable="true"/>
					<display:column style="text-align: center; width:180px">
						<div style="width:180px; margin:0px; padding:0px;" id="text_cerrar_pri<c:out value="${text_tituloPrivada_rowNum}"/>" class="renta_btn_cerrar" onclick="manejarUltimoDetalleTablaPrivada('1', '<c:out value="${text_tituloPrivada_rowNum}"/>');">
							Cerrar <img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/ico_cerrar.gif" alt="Cerrar" border="0" />
						</div>
	               		<div style="width:180px; margin:0px; padding:0px;" id="text_ver_pri<c:out value="${text_tituloPrivada_rowNum}"/>" class="renta_btn" onclick="manejarUltimoDetalleTablaPrivada('0', '<c:out value="${text_tituloPrivada_rowNum}"/>');">
	               			Ver detalle <img src="%%SiteContent.Portlet('common.bvc.recursos')%%/images/ico_cerrar.gif" alt="Cerrar" border="0" />
	               		</div>
						<tr id="detalle<c:out value='${text_tituloPrivada_rowNum}'/>" >
							<td colspan="4" style="padding: 0px; border-right: 0px none; border-bottom: 0px none; margin:0px;">
								<div id="deuda_pri<c:out value="${text_tituloPrivada_rowNum}"/>" class="renta_acordeon_interior" style="padding: 0px; border-bottom: none">
	           						<table align="left" cellpadding="0" cellspacing="0" class="tabla_general" id="text_20" style="border: none; ">
               							<tr>
			                   				<th width="227px" align="left" class="specblue" style="border-left: none; ">Nemot&eacute;cnico</th>
			                   				<th width="180px" class="specblue">Emisor</th>
			                   				<th  class="specblue"  >Cantidad</th>
				                   			<th  class="specblue"  style="border-right: none; width:180px;">Volumen</th>
			               				</tr>
               							<c:forEach items="${text_tituloPrivada.especies}" var="especie">
			                  				<tr>
			                    				<td class="specblue" width="227px" style="border-left:  none;"><a href="<portlet:actionURL><portlet:param name="action" value="detalle"/><portlet:param name="nemo"><jsp:attribute name="value"><c:out value="${especie.value.nemotecnico}"/></jsp:attribute>
				                					</portlet:param></portlet:actionURL>"><c:out value="${especie.value.nemotecnico}"/></a></td>
			                    				<td class="spec" width="180px"><c:out value="${especie.value.emisor}"/></td>
			                    				<td align="right"><fmt:formatNumber pattern="###,###" ><c:out value="${especie.value.cantidad}"/></fmt:formatNumber></td>
			                    				<td align="right" style="width:180px; border-right:  none;"><fmt:formatNumber pattern="###,###.##" ><c:out value="${especie.value.volumen}"/></fmt:formatNumber></td>
			                  				</tr>
			                  			</c:forEach>
			                		</table>
			              		</div>
                             </td>
                         </tr>
					</display:column>
				</display:table>
				
  			</div>
    	</div>
    	<div class="contenedor_boton_imprimir">
    		<table width="100%" border="0" cellspacing="0" cellpadding="10">
  				<tr>
    				<td width="45%">&nbsp;</td>
    				<td width="10%">&nbsp;</td>
    				<td width="45%" align="right">
						<a href="/mercados/DescargaXlsServlet?archivo=rentafija<c:if test="${RentaFijaBusqueda.params eq null}">&deuda=TODOS&ope=-1&sesion=TODAS&tipoMercado=%</c:if><c:out value="${RentaFijaBusqueda.params}"/>" class="excelneg">Descargar información de negociación</a></td>
  				</tr>
			</table>
		</div>
    </div>
    <br clear="all" />
	<br clear="all" />
</div>