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
	  <a href="/ElBanco/client/cuentas" class="active">Cuentas</a>
	  <a href="/ElBanco/client/operaciones">Operaciones</a>
	  <a href="/ElBanco/client/datos">Datos</a>
  
	  <form style="float: right;" action="/ElBanco/logout" method="GET">
		<input value="LOGOUT" type="submit" />
		</form>
	  
	  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
	</div>

<c:forEach items="${cuentas}" var="cuenta">

<div id="infocuenta" style="margin:2%;">
			<table class="tg" style="undefined;table-layout: fixed; width: 100%;">
<colgroup>
<col style="width: 215.2px">
<col style="width: 112.2px">
<col style="width: 101.2px">
<col style="width: 101.2px">
<col style="width: 24.2px">
<col style="width: 142.2px">
<col style="width: 22.2px">
<col style="width: 151px">
</colgroup>
  <tr>
    <th class="tg-ng61" colspan="8">Cuenta</th>
  </tr>
  <tr>
    <td class="tg-8qkv" rowspan="3">${cuenta.numeroCuenta}<br></td>
    <td class="tg-7boo">Saldo</td>
    <td class="tg-7boo" colspan="2">${cuenta.saldo} &#8364<br></td>
    <td class="tg-uys7"></td>
    <td class="tg-uys7" rowspan="3"><button id="blackbutton" type="button" onclick="alert('Hello world!')">realizar nueva operacion</button><br></td>
    <td class="tg-uys7"></td>
    <td class="tg-uys7" rowspan="3"><button id="blackbutton" onclick="alert('Hello world!')">ver ultimas operaciones</button><br></td>
  </tr>
  
  <tr>
  
  	<c:if test="${cuenta.limiteDiario > 0}">
		<td class="tg-w9sc">Limite diario<br></td>
  		<td class="tg-w9sc" colspan="2">${cuenta.limiteDiario} &#8364</td>
	</c:if>
	  	<c:if test="${cuenta.limiteDiario <= 0}">
		<td class="tg-w9sc"></td>
    	<td class="tg-w9sc" colspan="2"></td>
	</c:if>
	
    <td class="tg-uys7"></td>
    <td class="tg-uys7"></td>
  </tr>
  <tr>
  	<c:if test="${cuenta.limiteDiario > 0}">
		<td class="tg-w9sc">Limite inferior<br></td>
    	<td class="tg-w9sc" colspan="2">${cuenta.limiteInferior} &#8364</td>
	</c:if>
  	<c:if test="${cuenta.limiteDiario <= 0}">
		<td class="tg-w9sc"></td>
    	<td class="tg-w9sc" colspan="2"></td>
	</c:if>
    
    <td class="tg-uys7"></td>
    <td class="tg-uys7"></td>
  </tr>

</table>
	</div>

    
</c:forEach>


</body>
</html>