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
    
    function validateTransfer(divName){
    
    	var form = document.getElementById('transfer_form-'+divName);
    	var inputs = form.getElementsByTagName('input');
    	var result = true;
    	
    	for (var i = 0; i < inputs.length; i++) {
    		var input = inputs[i];
    		console.log(input.name);
    		if (input.name == 'numeroCuentaDestino'){
    			console.log(input.value);
    			if(isBlank(input.value)){
    				console.log("numeroCuentaDestino");
    				result = false;
    				break;
    			}
    			
    		}else if(input.name == 'cantidad'){

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
    
    function validateUpdate(divName){
    
    	var form = document.getElementById('update_form-'+divName);
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
	  <h2>Recargas Monedero</h2>
	  
	  <c:forEach items="${updates}" var="update">
  	  <div class="form-lg">
	  <div class="row">
	  
	  <div class="col-8">
	  <h6>Fecha</h6>
	  <span>${update.fecha}</span>
	  </div>
	  
	  <div class="col-4">
	  <h6>Cantidad</h6>
	  <span>${update.cantidad}</span>
	  </div>
	  
	  </div>
	  <br/>
	  
	 </div>
	 </c:forEach>
      
      
	
		<form action="/ElBanco/client/monedero" method="GET">
	  		<button type="submit" value="VOLVER">VOLVER</button>
		</form>
		</div>
	  </div>
	</body>

</html>