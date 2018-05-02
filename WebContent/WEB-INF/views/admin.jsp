<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  
  <script language="Javascript" type="text/javascript">
  	<c:if test="${not empty message}">
    	window.onload = alert("${message}")
	</c:if>
	
	function isBlank(str) {
		return !str.replace(/^\s+/g, '').length
	}
	
	function validateEmail(email) {
  		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  		return re.test(email);
	}
	
	function phonenumber(inputtxt) {
  		return inputtxt.match(/\d/g).length===9
  		
	}
	
	function validateSaldo(input){
	
		var saldo = parseFloat(input.value)
    	
    	if(isNaN(saldo)){
    		console.log("nan");
    		return false;
    	}else if(saldo < 0){
    		console.log("negativo");
    		return false;
    	}else{
    		input.value = saldo;
    	}
    	
    	return true;
	}
	
	function validateUserForm(){
    
    	var form = document.getElementById('user_form');
    	var inputs = form.getElementsByTagName('input');
    	var result = true;
    	
    	for (var i = 0; i < inputs.length; i++) {
    		var input = inputs[i];
    		console.log(input.name);
    		if (input.name == 'nombreUsuario'){
    			console.log(input.value);
    			if(isBlank(input.value)){
    				console.log("nombre");
    				result = false;
    				break;
    			}
    		}else if(input.name == 'nombre'){

    			if(isBlank(input.value)){
    				console.log("nombre");
    				result = false;
    				break;
    			}
    		}else if(input.name == 'apellidos'){

    			if(isBlank(input.value)){
    				console.log("apellidos");
    				result = false;
    				break;
    			}
    		}else if(input.name == 'email'){

    			if(!validateEmail(input.value)){
    				console.log("email");
    				result = false;
    				break;
    			}
    		}else if(input.name == 'telefono'){

    			if(!phonenumber(input.value)){
    				console.log("telefono");
    				result = false;
    				break;
    			}
    		}else if(input.name == 'direccion'){
 
    			if(isBlank(input.value)){
    				console.log("direccion");
    				result = false;
    				break;
    			}
    		}else if(input.name == 'cantidadDinero'){

    			if(!validateSaldo(input)){
    				console.log("cantidad");
    				result = false;
    				break;
    			}
    		}
    		
    	}
    	
    	if(result){
    		console.log("paso");
    	}else{
    		console.log("no paso");
    		return false;
    	}
   		
    }
  </script>
  
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
	<form action="/ElBanco/admin" method="POST" id="user_form" onsubmit="return validateUserForm()">
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
		<button type="submit" name="action" value="addUser">Crear usuario</button>
	</form>
</div>

	 <div class="form-lg" >
	<form action="/ElBanco/logout" method="GET">
		<!-- <input value="LOGOUT" type="submit" />-->
		<button type="submit" value="LOGOUT">LOGOUT</button>
	</form>
	</div>
	
	
</body>
</html>