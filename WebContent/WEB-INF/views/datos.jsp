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
	  <a href="/ElBanco/client/operaciones">Operaciones</a>
	  <a href="/ElBanco/client/datos"class="active" >Datos</a>
  
	  <form style="float: right;" action="/ElBanco/logout" method="GET">
		<input value="LOGOUT" type="submit" />
		</form>
	  
	  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
	</div>

<form>	
<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-top-width:1px;border-bottom-width:1px;border-color:black;}
.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-top-width:1px;border-bottom-width:1px;border-color:black;}
.tg .tg-1u7c{background-color:#343434;color:#ffffff;border-color:#343434;text-align:center}
.tg .tg-baqh{text-align:center;vertical-align:top}
.tg .tg-c3ow{border-color:inherit;text-align:center;vertical-align:top}
.tg .tg-uys7{border-color:inherit;text-align:center}
</style>
<table class="tg" style="undefined;table-layout: fixed; width: 1250px">
<colgroup>
<col style="width: 170.2px">
<col style="width: 261.2px">
<col style="width: 819px">
</colgroup>
  <tr>
    <th class="tg-1u7c">Nombre</th>
    <th class="tg-uys7">${userBean.nombre}</th>
    <th class="tg-baqh"></th>
  </tr>
  <tr>
    <td class="tg-1u7c">Apellidos</td>
    <td class="tg-uys7">${userBean.apellidos}</td>
    <td class="tg-c3ow"></td>
  </tr>
  <tr>
    <td class="tg-1u7c">Email</td>
    <td class="tg-uys7">${userBean.email}</td>
    <td class="tg-c3ow"></td>
  </tr>
  <tr>
    <td class="tg-1u7c">Telefono</td>
    <td class="tg-uys7">${userBean.telefono}</td>
    <td class="tg-c3ow"></td>
  </tr>
  <tr>
    <td class="tg-1u7c">Direccion</td>
    <td class="tg-uys7">${userBean.direccion}</td>
    <td class="tg-c3ow"></td>
  </tr>

</table>

<input type="submit" value="Actualizar datos"/>
</form>

</body>
</html>