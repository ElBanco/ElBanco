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
  <link rel="stylesheet" href="../resources/css/estilos.css" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Francois+One" rel="stylesheet">
  
  <script language="Javascript" type="text/javascript"> 
  
	<c:if test="${not empty message}">
    	window.onload = alert("${message}")
	</c:if>
 	
    function toggleDiv(divName){
    
    	var div = document.getElementById(divName);
    	div.style.display = div.style.display == "none" ? "block" : "none";
    	
    } 
    
    function validateCardForm(divName){
    
    	var form = document.getElementById(divName).getElementsByTagName('form')[0];
    	var input = form.getElementsByTagName('input')[0];
    	
    	if(input.value == ""){
    		return false;
    	}
    	
    }
    
    function validateAccountForm(divName){
    
    	var form = document.getElementById(divName).getElementsByTagName('form')[0];
    	var input = form.getElementsByTagName('input')[0];
    	var saldo = parseFloat(input.value)
    	
    	if(input.value == ""){
    		console.log("vacio");
    		return false;
    	}else if(isNaN(saldo)){
    		console.log("nan");
    		return false;
    	}else if(saldo < 0){
    		console.log("negativo");
    		return false;
    	}else{
    		console.log("bien");
    		input.value = saldo;
    	}
    	
    }
    
    function validateBaja(divName){
    
    	var bajaDiv = document.getElementById('baja_' + divName);
    	var span = bajaDiv.getElementsByTagName('span')[0];
    	console.log(span.innerHTML);
    	
    	if(span.innerHTML != ""){
    		return false;
    	}
    	
    }
    
  </script> 
  
   </head>
   	
   <body>
   <div class="Other-page">
	  <div class="form-lg" >
	  <h2>Tarjetas de la cuenta</h2>
	  <c:forEach items="${tarjetas}" var="tarjeta">
  	  <div class="form-lg">
	  <div class="row">
	  <div class="col-8">
	  <h6>Numero de tarjeta</h6>
	  <span>${tarjeta.numeroTarjeta}</span>
	  </div>
	  <div class="col-4">
	  <h6>PIN</h6>
	  <span>${tarjeta.pin}</span>
	  </div>
	  <div class="col-4">
	  <h6>Limite diario</h6>
	  <span>${tarjeta.limiteDiario}</span>
	  </div>
	  <div class="col-4">
	  <h6>Limite superior</h6>
	  <span>${tarjeta.limiteSuperior}</span>
	  </div>
	  <div class="col-4">
	  <h6>Titular</h6>
	  <span>${tarjeta.nombreUsuario}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha Cancelacion</h6>
	  <span>${tarjeta.fechaCancelacion}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha creacion</h6>
	  <span>${tarjeta.fechaCreacion}</span>
	  </div>
	  <div class="col-4" id="baja_${tarjeta.numeroTarjeta}">
	  <h6>Fecha baja</h6>
	  <span>${tarjeta.fechaBaja}</span>
	  </div>
	  </div>
	  <br/>
	  
	  <div class="row">
	   <div class="col-12">
	  
	  <form action="/ElBanco/admin/client_info?action=darBajaTarjetaDebito" method="POST" onsubmit="return validateBajaDebito('${tarjeta.numeroTarjeta}')">
	  <input type="hidden" name="numeroTarjeta" value="${tarjeta.numeroTarjeta}">
	  <input type="submit" value="Dar tarjeta de baja" id="blackbutton">
	  </form>
	  
	  </div>
	  </div>
	  </div>
      </c:forEach>
	  <div class="row">
	  <div class="col-12">
	  <button id="blackbutton" onclick="toggleDiv('${cuenta.numeroCuenta}')">Crear tarjeta</button>
	  
	  <div id="${cuenta.numeroCuenta}" style="display: none;">
	  <form action="/ElBanco/admin/client_info?action=addDebitCard" method="POST" onsubmit="return validateCardForm('${cuenta.numeroCuenta}')">
	  Titular <input type='text' name='titular'/>
	  <input type="hidden" name="numeroCuenta" value="${cuenta.numeroCuenta}">
	  <input type="submit" value="Enviar" id="blackbutton">
	  </form>
	  </div>
	  
	  </div>
	  </div>
	  </div>
      
	  <br/>
	  	 
		<div class="form-lg" >
	
		<form action="/ElBanco/admin" method="GET">
	  		<button type="submit" value="VOLVER">VOLVER</button>
		</form>
		</div>
	  </div>
	</body>

</html>