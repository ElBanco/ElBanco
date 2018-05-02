<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>elBanco</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="/ElBanco/css/estilos.css" type="text/css">
    <link rel="stylesheet" href="/ElBanco/css/tuvieja.css" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Francois+One" rel="stylesheet">
  
  
  
  <style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;border-width:1px;border-style:solid;border-color:#ccc;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
.tg .tg-w9sc{font-size:13px;border-color:inherit;text-align:center}
.tg .tg-ng61{font-size:22px;background-color:#343434;color:#ffffff;border-color:#000000;text-align:center;vertical-align:top}
.tg .tg-uys7{border-color:inherit;text-align:center}
.tg .tg-7boo{font-size:28px;border-color:inherit;text-align:center}
.tg .tg-8qkv{background-color:#9b9b9b;border-color:#000000;text-align:center}
</style>
</head>

<body >
	
	<div id="logo">
		<h2  title="ElBanco" > 
			ErBanco
		</h2> 
	</div> 
	
	<div class="topnav" id="myTopnav">
	  <a href="/ElBanco/client">Home</a>
	  <a href="/ElBanco/client/cuentas" >Cuentas</a>
	  <a href="/ElBanco/client/operaciones" class="active"> Operaciones</a>
	  <a href="/ElBanco/client/datos">Datos</a>
  
	  <form style="float: right;" action="/ElBanco/logout" method="GET">
		<input value="LOGOUT" type="submit" />
		</form>
	  
	  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
	</div>

<div style="margin:2% auto;text-align: center;">
<a  href="/ElBanco/client/nuevaoperacion"><button id="blackbutton" style="width: 30%; margin:2%;">NUEVA OPERACION</button></a>	
<button id="blackbutton" style="width: 30%; margin:2%;" onclick="alert('Hello world!')">RECARGA MONEDERO</button>	
</div>

<c:forEach items="${cuentas}" var="cuenta">


<div id="infocuenta" style="margin:10%; width: 100%"; "float: left;">	
<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;border-width:1px;border-style:solid;border-color:black;}
.tg td{font-family:Arial, sans-serif;cpadding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;}
.tg th{font-family:Arial, sans-serif;font-size:20px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;}
.tg .tg-n7lu{background-color:#343434;text-align:right;vertical-align:top;color:#ffffff;font-size:25px;}
.tg .tg-p6v9{background-color:#343434;text-align:center;vertical-align:top;color:#ffffff;font-size:25px;}
.tg .tg-c3ow{border-color:inherit;text-align:center;vertical-align:top}
.tg .tg-us36{border-color:inherit;vertical-align:top}
.tg .tg-li6d{font-family:Arial,background-color:#c0c0c0;text-align:center;vertical-align:center;font-size:35px}
</style>

<table class="tg" style="undefined;table-layout: fixed; width: 80%; align=center">
<colgroup>
<col style="width: 233.2px">
<col style="width: 136.2px">
<col style="width: 171px">
</colgroup>

  <tr>
    <th class="tg-n7lu">Cuenta</th>
    <th class="tg-p6v9" colspan="2">${cuenta.numeroCuenta}</th>
  </tr>
  
<c:forEach items="${transferencias}" var="operacion">

	<c:if test="${cuenta.numeroCuenta == operacion.numeroCuentaOrigen}">
		<tr>
		    <td class="tg-li6d" rowspan="2" style="color:red">${operacion.cantidad}</td>
		    <td class="tg-c3ow">A</td>
		    <td class="tg-us36">${operacion.numeroCuentaDestino}</td>
		  </tr>
		  <tr style="border-bottom:1px solid black;">
		    <td class="tg-c3ow">Fecha</td>
		    <td class="tg-us36">15/07/2018</td>
		  </tr>
	</c:if>
	<c:if test="${cuenta.numeroCuenta == operacion.numeroCuentaDestino}">
		<tr>
		    <td class="tg-li6d" rowspan="2" style="color:green">${operacion.cantidad}</td>
		    <td class="tg-c3ow">Desde</td>
		    <td class="tg-us36">${operacion.numeroCuentaOrigen}</td>
		  </tr>
		  <tr style="border-bottom:1px solid black;">
		    <td class="tg-c3ow">Fecha</td>
		    <td class="tg-us36">15/07/2018</td>
	</c:if>
	</c:forEach>	
</table>
</div>
</c:forEach>

</body>
</html>