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
   	  <h2>Datos Personales</h2>
      <div class="row">
	  <div class="col-4">
	  <h6>Nombre de usuario</h6>
	  <span>${client.nombreUsuario}</span>
	  </div>
	  <div class="col-4">
	  <h6>Nombre</h6>
	  <span>${client.nombre}</span>
	  </div>
	  <div class="col-4">
	  <h6>Apellidos</h6>
	  <span>${client.apellidos}</span>
	  </div>
	  <div class="col-4">
	  <h6>Email</h6>
	  <span>${client.email}</span>
	  </div>
	  <div class="col-4">
	  <h6>Tel�fono</h6>
	  <span>${client.telefono}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha Creaci�n</h6>
	  <span>${client.fechaCreacion}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha modificaci�n</h6>
	  <span>${client.fechaCreacion}</span>
	  </div>
	  <div class="col-4" id="baja_${client.nombreUsuario}">
	  <h6>Fecha baja</h6>
	  <span>${client.fechaBaja}</span>
	  </div>
	  </div>
	   <br/>	  	   
	  
	  <form action="/ElBanco/admin/client_info?action=darBajaUsuario" method="POST" onsubmit="return validateBaja('${client.nombreUsuario}')">
	  <input type="hidden" name="nombreUsuario" value="${client.nombreUsuario}">
	  <input type="submit" value="Dar usuario de baja" id="blackbutton">
	  </form>
	
	  </div>
	  <div class="form-lg" >
	  <h2>Datos Cuentas</h2>
	  <c:forEach items="${cuentas}" var="cuenta">
  	  <div class="form-lg">
	  <div class="row">
	  <div class="col-3">
	  <h6>N�mero de cuenta</h6>
	  <span>${cuenta.numeroCuenta}</span>
	  </div>
	  <div class="col-3">
	  <h6>Saldo</h6>
	  <span>${cuenta.saldo}</span>
	  </div>
	  <div class="col-3">
	  <h6>L�mite diario</h6>
	  <span>${cuenta.limiteDiario}</span>
	  </div>
	  <div class="col-3">
	  <h6>L�mite inferior</h6>
	  <span>${cuenta.limiteInferior}</span>
	  </div>
	  <div class="col-3">
	  <h6>Fecha creaci�n</h6>
	  <span>${cuenta.fechaCreacion}</span>
	  </div>
	  <div class="col-3">
	  <h6>Fecha modificaci�n</h6>
	  <span>${cuenta.fechaModificacion}</span>
	  </div>
	  <div class="col-3" id="baja_${cuenta.numeroCuenta}">
	  <h6>Fecha baja</h6>
	  <span>${cuenta.fechaBaja}</span>
	  </div>
	  </div>
	  <br/>
	  <div class="row">
	  <div class="col-6">
	  <button id="blackbutton" onclick="toggleDiv('${cuenta.numeroCuenta}')">Crear tarjeta</button>
	  
	  <div id="${cuenta.numeroCuenta}" style="display: none;">
	  <form action="/ElBanco/admin/client_info?action=addDebitCard" method="POST" onsubmit="return validateCardForm('${cuenta.numeroCuenta}')">
	  Titular: <input type='text' name='titular'/>
	  <input type="hidden" name="numeroCuenta" value="${cuenta.numeroCuenta}">
	  <input type="submit" value="Enviar" id="blackbutton">
	  </form>
	  </div>
	  
	  </div>
	  
	  <div class="col-6">
	  
	  <form action="/ElBanco/admin/client_info?action=darBajaCuenta" method="POST" onsubmit="return validateBaja('${cuenta.numeroCuenta}')">
	  <input type="hidden" name="numeroCuenta" value="${cuenta.numeroCuenta}">
	  <input type="submit" value="Dar cuenta de baja" id="blackbutton">
	  </form>
	  
	  </div>
	  </div>
	  </div>
         <!-- <p>numeroCuenta: ${cuenta.numeroCuenta}</p>
      	 <p>nombreUsuario: ${cuenta.nombreUsuario}</p>
         <p>saldo: ${cuenta.saldo}</p>
         <p>limiteDiario: ${cuenta.limiteDiario}</p>
         <p>limiteInferior: ${cuenta.limiteInferior}</p>
         <p>fechaCreacion: ${cuenta.fechaCreacion}</p>
         <p>fechaModificacion: ${cuenta.fechaModificacion}</p>
        <p>fechaBaja: ${cuenta.fechaBaja}</p>
        
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
				   <td class="tg-7boo" colspan="2">${cuenta.saldo}<br></td>
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
		</div> -->
      </c:forEach>
      
	  <br/>
		  <button id="blackbutton" onclick="toggleDiv('${client.nombreUsuario}')">A�adir cuenta</button>
		  
		  <div id="${client.nombreUsuario}" style="display: none;">
	  	  <form action="/ElBanco/admin/client_info?action=addAccount" method="POST" onsubmit="return validateAccountForm('${client.nombreUsuario}')">
	  	  Saldo: <input type='text' name='saldo'/>
	  	  <input type="hidden" name="nombreUsuario" value="${client.nombreUsuario}">
	  	  <input type="submit" value="Enviar" id="blackbutton">
	  	  </form>
	  	  </div>
	
	  </div>
	  	  <div class="form-lg" >
	  
	  <h2>Datos Monedero</h2>
      <!-- <p>numeroTarjeta: ${monedero.numeroTarjeta}</p>
      <p>PIN: ${monedero.pin}</p>
      <p>saldo: ${monedero.saldo}</p>
      <p>fechaCancelacion: ${monedero.fechaCancelacion}</p>
      <p>nombreUsuario: ${monedero.nombreUsuario}</p>
      <p>fechaCreacion: ${monedero.fechaCreacion}</p>
      <p>fechaBaja: ${mondero.fechaBaja}</p>-->
      
	  <div class="row">
	  <div class="col-12">
	  <h6>N�mero de tarjeta</h6>
	  <span>${monedero.numeroTarjeta}</span>
	  </div>
	  <div class="col-4">
	  <h6>PIN</h6>
	  <span>${monedero.pin}</span>
	  </div>
	  <div class="col-4">
	  <h6>Saldo</h6>
	  <span>${monedero.saldo}</span>
	  </div>
	  <div class="col-4">
	  <h6>Titular</h6>
	  <span>${monedero.nombreUsuario}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha Cancelacion</h6>
	  <span>${monedero.fechaCancelacion}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha creaci�n</h6>
	  <span>${monedero.fechaCreacion}</span>
	  </div>
	  <div class="col-4" id="baja_${monedero.numeroTarjeta}">
	  <h6>Fecha baja</h6>
	  <span>${monedero.fechaBaja}</span>
	  </div>
	  </div>
	   <br/>
		  
		  <form action="/ElBanco/admin/client_info?action=darBajaMonedero" method="POST" onsubmit="return validateBaja('${monedero.numeroTarjeta}')">
	  	  <input type="hidden" name="monedero" value="${monedero.numeroTarjeta}">
	  	  <input type="submit" value="Dar monedero de baja" id="blackbutton">
	     </form>
	
	
	  </div>
		  <div class="form-lg" >
	
		<form action="/ElBanco/admin" method="GET">
	  		<button type="submit" value="VOLVER">VOLVER</button>
		</form>
		</div>
		</div>
	</body>

</html>