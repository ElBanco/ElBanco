<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head>
      <title>Access Granted</title>
      <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="/resources/css/estilos.css" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Francois+One" rel="stylesheet">
   </head>
   
   <body>
   
      <p>Student First Name: ${userBean.nombreUsuario}</p>
      <p>Student First Name: ${userBean.rolID}</p>
      <p>Student First Name: ${userBean.nombre}</p>
      <p>Student First Name: ${userBean.apellidos}</p>
      <p>Student First Name: ${userBean.email}</p>
      <p>Student First Name: ${userBean.telefono}</p>
      <p>Student First Name: ${userBean.fechaCreacion}</p>
      
	<br />
	
	<h2>Transferencia</h2>
	<form action="/ElBanco/client" method="POST">
		Cantidad : <input name="cantidad" type="text"/> <br />
		Numero Cuenta Destino: <input name="numeroCuentaDestino" type="text"/> <br />
		<input type="submit" value="Enviar"/>
	</form>
	
	<h2>Datos Cuenta</h2>
	  <c:forEach items="${cuentas}" var="cuenta">
         <p>numeroCuenta: ${cuenta.numeroCuenta}</p>
         <p>saldo: ${cuenta.saldo}</p>
         <p>limiteDiario: ${cuenta.limiteDiario}</p>
         <p>limiteInferior: ${cuenta.limiteInferior}</p>
      </c:forEach>
	
	
	<form action="/ElBanco/logout" method="GET">
		<input value="LOGOUT" type="submit" />
	</form>
	
	
	<h2>Cambiar Limite inferior de una cuenta ya creada</h2>
	<form action="/ElBanco/client" method="POST">
	
		Numero de Cuenta : <input name="numeroCuenta" type="text"/> <br />
		Limite Inferior: <input name="limiteInferior" type="text"/> <br />
		
		<input type="submit" name = "action" value="cambiarLimiteCuentaInferior"/>
	</form>
	
	<h2>Cambiar Limite Diario de una cuenta ya creada</h2>
	<form action="/ElBanco/client" method="POST">
	
		Numero de Cuenta : <input name="numeroCuenta" type="text"/> <br />
		Limite Diario : <input name="limiteDiario" type="text"/> <br />

		<input type="submit" name = "action" value="cambiarLimiteCuentaDiario"/>
	</form>
	
	<h2>Cambiar Limite Superior del monedero</h2>
	<form action="/ElBanco/client" method="POST">
	
		Nombre de Usuario : <input name="nombreUsuario" type="text"/> <br />
		Limite Superior : <input name="limiteSuperior" type="text"/> <br />

		<input type="submit" name = "action" value="cambiarLimiteMonedero"/>
	</form>
	
	<h2>Cambiar Limite Superior de su tarjeta debito</h2>
	<form action="/ElBanco/client" method="POST">
	
		Numero de Tarjeta : <input name="numeroTarjeta" type="text"/> <br />
		Limite Superior : <input name="limiteSuperior" type="text"/> <br />

		<input type="submit" name = "action" value="cambiarLimiteDebitoSuperior"/>
	</form>
	
	<h2>Cambiar Limite Diario de su tarjeta debito</h2>
	<form action="/ElBanco/client" method="POST">
	
		Numero de Tarjeta : <input name="numeroTarjeta" type="text"/> <br />
		Limite Diario : <input name="limiteDiario" type="text"/> <br />

		<input type="submit" name = "action" value="cambiarLimiteDebitoDiario"/>
	</form>
	
   </body>
   
</html>