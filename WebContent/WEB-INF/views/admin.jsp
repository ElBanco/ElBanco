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
		<input type="submit" />
	</form>
	<br />
	
	
	<form action="/ElBanco/logout" method="GET">
		<input value="LOGOUT" type="submit" />
	</form>
	
</body>
</html>