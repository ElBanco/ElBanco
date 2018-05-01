<html>
<head>
<title>Admin</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="resources/css/estilos.css" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Francois+One" rel="stylesheet">
</head>
<body>
<div class="Other-page">
	  <div class="form-lg" >
	<h3>Buscar usuario</h3>
	<form action="/ElBanco/admin/client_info" method="GET">
		Nombre Usuario <input name="nombreUsuario" type="text"/> <br />
		<!--<input type="submit" value="Buscar"/>-->
		<button type="submit"  value="Buscar">Buscar</button>
	</form>
	<br />
</div>

	  <div class="form-lg" >
	<h3>Anadir nuevo cliente</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
	<div class="row">
	<div class="col-6">
		Nombre Usuario <input name="nombreUsuario" type="text"/>
		</div>
		
	<div class="col-6">
		Nombre  <input name="nombre" type="text"/></div>
		
	<div class="col-6">
		Apellidos <input name="apellidos" type="text"/> 
		</div>
		
	<div class="col-6">
		Email <input name="email" type="text"/> 
		</div>
	<div class="col-6">
		Telefono <input name="telefono" type="text"/> 
		</div>
	<div class="col-6">
		Direccion <input name="direccion" type="text"/> 
		</div>
	<div class="col-6">
		Cantidad inicial <input name="cantidadDinero" type="text"/> 
		</div>
		</div>
		<!--<input type="submit" name="action" value="addUser" />-->
		<button type="submit" name="action" value="addUser">Añadir usuario</button>
	</form>
</div>

	  <div class="form-lg" >
	
	
	<h3>Crear <b>Cuenta</b> a un usuario ya creado</h3>
	
	<br />
	<form action="/ElBanco/admin" method="POST">
	<div class="row">
	<div class="col-6">
		Nombre Usuario  <input name="nombreUsuario" type="text"/> </div>
	<div class="col-6">
		Saldo <input name="saldo" type="text"/> </div>
</div>
		<!-- <input type="submit" name="action" value="addNewAccount"/> -->
		<button type="submit" name="action" value="addNewAccount">Añadir cuenta</button>
		
	</form>
	<br />
	
</div>

	  <div class="form-lg" >
	
		<h3>Añadir <b>Tarjeta de Debito</b> a una cuenta ya creada</h3>
	
	<br />
	
	<form action="/ElBanco/admin" method="POST">
	<div class="row">
	<div class="col-6">
		Titular  <input name="titular" type="text"/> </div>
	<div class="col-6">
		Numero Cuenta <input name="numeroCuenta" type="text"/> </div>
</div>
		<!-- <input type="submit" name="action" value="addDebitCard"/>-->
		<button type="submit" name="action" value="addDebitCard">Añadir tarjeta</button>
	</form>
	<br />
	
</div>

	  <div class="form-lg" >
	
		<h3>Dar de Baja a un Usuario</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
	
		Nombre de Usuario  <input name="nombreUsuario" type="text"/> <br />

		<!-- <input type="submit" name="action" value="darBajaUser"/>-->
		<button type="submit" name="action" value="darBajaUser">Dar usuario de baja</button>
	</form>
	<br />
	
</div>

	  <div class="form-lg" >
	<h3>Dar de Baja a una Cuenta</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
	
		Numero de Cuenta  <input name="numeroCuenta" type="text"/> <br />

		<!-- <input type="submit" name="action" value="darBajaCuenta"/>-->
		<button type="submit" name="action" value="darBajaCuenta">Dar cuenta de baja</button>
	</form>
	<br />
	
</div>

	  <div class="form-lg" >
	<h3>Dar de Baja a una Tarjeta (Debito o Monedero)</h3>
	<br />
	<form action="/ElBanco/admin" method="POST">
	
		Numero de Tarjeta  <input name="numeroTarjeta" type="text"/> <br />

		<!-- <input type="submit" name="action" value="darBajaTarjeta"/>-->
		<button type="submit" name="action" value="darBajaTarjeta">Dar tarjeta de baja</button>
	</form>
	<br />
	
</div>

	  <div class="form-lg" >
	<form action="/ElBanco/logout" method="GET">
		<!-- <input value="LOGOUT" type="submit" />-->
		<button type="submit" value="LOGOUT">LOGOUT</button>
	</form>
	</div>
	</div>
	
</body>
</html>