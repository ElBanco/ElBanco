<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head>
      <title>Tarjetas cuenta</title>
        <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="/ElBanco/resources/css/estilos.css" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Francois+One" rel="stylesheet">
  
  <script language="Javascript" type="text/javascript"> 
  
	<c:if test="${not empty message}">
    	window.onload = alert("${message}")
	</c:if>
 	
    function toggleDiv(divName){
    
    	var div = document.getElementById(divName);
    	div.style.display = div.style.display == "none" ? "block" : "none";
    	
    }
    
    function isBlank(str) {
		return !str.replace(/^\s+/g, '').length
	}
	
	function validateSaldo(input){
	
		var saldo = parseFloat(input.value);
    	
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
    
    
    function validateDatos(divName){
    
    	var form = document.getElementById("monederoDatos-form");
    	var input = form.getElementsByTagName('input')[0];
    	
    	if(!validateSaldo(input)){
    		console.log("no paso");
    		return false;
    	}
    	
    }
    
  </script> 
  
   </head>
   	
   <body>
   <div class="Other-page">
	  <div class="form-lg" >
	  <h2>Monedero</h2>
	  
	  <div class="row">
	  <div class="col-4">
	  <h6>Usuario</h6>
	  <span>${monedero.nombreUsuario}</span>
	  </div>
	  
	  <div class="col-4">
	  <h6>Saldo</h6>
	  <span>${monedero.saldo}</span>
	  </div>
	  
	  <div class="col-4">
	  <h6>PIN</h6>
	  <span>${monedero.pin}</span>
	  </div>
	  
	  <div class="col-4">
	  <h6>PIN</h6>
	  <span>${monedero.pin}</span>
	  </div>
	  
	  <div class="col-4">
	  <h6>Numero de Tarjeta</h6>
	  <span>${monedero.numeroTarjeta}</span>
	  </div>
	  
	  <div class="col-4">
	  <h6>Fecha de Creacion</h6>
	  <span>${monedero.fechaCreacion}</span>
	  </div>
	  
	  <div class="col-4">
	  <h6>Fecha de Baja</h6>
	  <span>${monedero.fechaBaja}</span>
	  </div>
	  
	  <div class="col-4">
	  <h6>Limite Superior</h6>
	  <span>${monedero.limiteSuperior}</span>
	  </div>
	  </div>
	  
	  <br/>
	  <div class="row">
	  <div class="col-6">
	  
	  <form action="/ElBanco/client/updates_monedero" method="GET">
	  <input type="submit" value="Ver Recargas del Monedero" id="blackbutton">
	  </form>
	  
	  </div>
	  
	  <div class="col-6">
	  
	  <button id="blackbutton" onclick="toggleDiv('monederoDatos')">Cambiar Limite Superior</button>
	  
	  <div id="monederoDatos" style="display: none;">
	  <form action="/ElBanco/client/monedero" method="POST" id="monederoDatos-form" onsubmit="return validateDatos()">
	  Limite Superior: <input type='text' name='limiteSuperior'/>
	  <input type="submit" value="Enviar" id="blackbutton">
	  </form>
	  </div>
	  
	  </div>
	  </div>
	  
	  <br/>
	  
      
      
	
		<form action="/ElBanco/client" method="GET">
	  		<button type="submit" value="VOLVER">VOLVER</button>
		</form>
		</div>
	  </div>
	</body>

</html>