<html>
<head>
<title>Login</title>
</head>
<body>

	<h3>Buscar usuario</h3>
	<br />
	<form action="/ElBanco/admin/client_info" method="GET">
		NombreUsuario : <input name="nombreUsuario" type="text"/> <br />
		<input type="submit" value="Buscar"/>
	</form>
	<br />

	<h3>Anadir nuevo cliente</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
		NombreUsuario : <input name="nombreUsuario" type="text"/> <br />
		Nombre : <input name="nombre" type="text"/><br />
		Apellidos : <input name="apellidos" type="text"/> <br />
		Email : <input name="email" type="text"/> <br />
		Telefono : <input name="telefono" type="text"/> <br />
		Direccion : <input name="direccion" type="text"/> <br />
		HashContrasena : <input name="hashContrasena" type="text"/> <br />
		Cantidad inicial : <input name="cantidadDinero" type="text"/> <br />
		<input type="submit" name="action" value="addUser"/>
	</form>
	<br />
	
		<h3>Enlazar Cuenta a un usuario ya creado</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
		NombreUsuario : <input name="nombreUsuario" type="text"/> <br />
		NumeroCuenta : <input name="numeroCuenta" type="text"/> <br />
		<input type="submit" name="action" value="bindAccount"/>
	</form>
	<br />
			
	<form action="/ElBanco/logout" method="GET">
		<input value="LOGOUT" type="submit" />
	</form>
	
	<h3>Crear <b>Cuenta</b> a un usuario ya creado</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
	
		NombreUsuario : <input name="nombreUsuario" type="text"/> <br />
		Saldo : <input name="saldo" type="text"/> <br />
		LimiteDiario : <input name="limiteDiario" type="text"/> <br />
		LimiteInferior : <input name="limiteInferior" type="text"/> <br />

		<input type="submit" name="action" value="addNewAccount"/>
	</form>
	<br />
	
		<h3>Añadir <b>Monedero</b> a un usuario ya creado</h3>
	<br />	
	<form action="/ElBanco/admin" method="POST">
	
		NombreUsuario : <input name="nombreUsuario" type="text"/> <br />
		Saldo : <input name="saldo" type="text"/> <br />
		LimiteSuperior : <input name="limiteSuperior" type="text"/> <br />

		<input type="submit" name="action" value="addMonedero"/>
	</form>
	<br />
	
		<h3>Añadir <b>Tarjeta de Debito</b>a una cuenta ya creada</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
	
		Titular : <input name="titular" type="text"/> <br />
		NumeroCuenta: <input name="numeroCuenta" type="text"/> <br/>
		LimiteDiario : <input name="limiteDiario" type="text"/> <br />
		LimiteSuperior : <input name="limiteSuperior" type="text"/> <br />

		<input type="submit" name="action" value="addDebitCard"/>
	</form>
	<br />
	
	
		<h3>Dar de Baja a un Usuario</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
	
		Nombre de Usuario : <input name="nombreUsuario" type="text"/> <br />

		<input type="submit" name="action" value="darBajaUser"/>
	</form>
	<br />
	
	<h3>Dar de Baja a una Cuenta</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
	
		Numero de Cuenta : <input name="numeroCuenta" type="text"/> <br />

		<input type="submit" name="action" value="darBajaCuenta"/>
	</form>
	<br />
	
	<h3>Dar de Baja a una Tarjeta (Debito o Monedero)</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
	
		Numero de Tarjeta : <input name="numeroTarjeta" type="text"/> <br />

		<input type="submit" name="action" value="darBajaTarjeta"/>
	</form>
	<br />
	
	<form action="/ElBanco/logout" method="GET">
		<input value="LOGOUT" type="submit" />
	</form>
	
</body>
</html>