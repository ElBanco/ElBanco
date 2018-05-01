<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head>
      <title>Client Information</title>
        <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="/ElBanco/estilos.css" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Francois+One" rel="stylesheet">
   </head>
   
   <body>
   <div class="Other-page">
   	  <h2>Datos Personales</h2>
      <p>nombreUsuario: ${client.nombreUsuario}</p>
      <p>nombre: ${client.nombre}</p>
      <p>apellidos: ${client.apellidos}</p>
      <p>email: ${client.email}</p>
      <p>telefono: ${client.telefono}</p>
      <p>fechaCreacion: ${client.fechaCreacion}</p>
      <p>fechaModificacion: ${client.fechaCreacion}</p>
      <p>fechaBaja: ${client.fechaBaja}</p>
	  <br />
	  
	  
	  <h2>Datos Cuentas</h2>
	  <c:forEach items="${cuentas}" var="cuenta">
         <!-- <p>numeroCuenta: ${cuenta.numeroCuenta}</p>
      	 <p>nombreUsuario: ${cuenta.nombreUsuario}</p>
         <p>saldo: ${cuenta.saldo}</p>
         <p>limiteDiario: ${cuenta.limiteDiario}</p>
         <p>limiteInferior: ${cuenta.limiteInferior}</p>
         <p>fechaCreacion: ${cuenta.fechaCreacion}</p>
         <p>fechaModificacion: ${cuenta.fechaModificacion}</p>
        <p>fechaBaja: ${cuenta.fechaBaja}</p> -->
        
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
				   <td class="tg-7boo" colspan="2">${cuenta.saldo} €<br></td>
				   <td class="tg-uys7"></td>
				   <td class="tg-uys7" rowspan="3"><button id="blackbutton" type="button" onclick="alert('Hello world!')">realizar nueva operacion</button><br></td>
				   <td class="tg-uys7"></td>
				   <td class="tg-uys7" rowspan="3"><button id="blackbutton" onclick="alert('Hello world!')">ver ultimas operaciones</button><br></td>
				 </tr>
				 <tr>
				   <td class="tg-w9sc">Limite diario<br></td>
				   <td class="tg-w9sc" colspan="2">${cuenta.limiteDiario} €</td>
				   <td class="tg-uys7"></td>
				   <td class="tg-uys7"></td>
				 </tr>
				 <tr>
				   <td class="tg-w9sc">Limite inferior<br></td>
				   <td class="tg-w9sc" colspan="2">${cuenta.limiteInferior} €</td>
				   <td class="tg-uys7"></td>
				   <td class="tg-uys7"></td>
				 </tr>
			</table>
		</div>
      </c:forEach>
	
	  
	  <h2>Datos Monedero</h2>
      <p>numeroTarjeta: ${monedero.numeroTarjeta}</p>
      <p>PIN: ${monedero.pin}</p>
      <p>saldo: ${monedero.saldo}</p>
      <p>fechaCancelacion: ${monedero.fechaCancelacion}</p>
      <p>nombreUsuario: ${monedero.nombreUsuario}</p>
      <p>fechaCreacion: ${monedero.fechaCreacion}</p>
      <p>fechaBaja: ${mondero.fechaBaja}</p>
	  <br />
	
	
		<form action="/ElBanco/admin" method="GET">
			<input value="VOLVER" type="submit" />
		</form>
		</div>
	</body>

</html>