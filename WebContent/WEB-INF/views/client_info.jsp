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
	  <h6>Teléfono</h6>
	  <span>${client.telefono}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha Creación</h6>
	  <span>${client.fechaCreacion}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha modificación</h6>
	  <span>${client.fechaCreacion}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha baja</h6>
	  <span>${client.fechaBaja}</span>
	  </div>
	  </div>
	   <br/>
		  <button id="blackbutton" onclick="alert('Hello world!')">Dar de baja</button>
	
	  </div>
	  <div class="form-lg" >
	  <h2>Datos Cuentas</h2>
	  <c:forEach items="${cuentas}" var="cuenta">
  	  <div class="form-lg">
	  <div class="row">
	  <div class="col-3">
	  <h6>Número de cuenta</h6>
	  <span>${cuenta.numeroCuenta}</span>
	  </div>
	  <div class="col-3">
	  <h6>Saldo</h6>
	  <span>${cuenta.saldo}</span>
	  </div>
	  <div class="col-3">
	  <h6>Límite diario</h6>
	  <span>${cuenta.limiteDiario}</span>
	  </div>
	  <div class="col-3">
	  <h6>Límite inferior</h6>
	  <span>${cuenta.limiteInferior}</span>
	  </div>
	  <div class="col-3">
	  <h6>Fecha creación</h6>
	  <span>${cuenta.fechaCreacion}</span>
	  </div>
	  <div class="col-3">
	  <h6>Fecha modificación</h6>
	  <span>${cuenta.fechaModificacion}</span>
	  </div>
	  <div class="col-3">
	  <h6>Fecha baja</h6>
	  <span>${cuenta.fechaBaja}</span>
	  </div>
	  </div>
	  <br/>
	  <div class="row">
	  <div class="col-6">
	  <button id="blackbutton" onclick="alert('Hello world!')">Crear tarjeta</button>
	  	  </div>
	  
	  <div class="col-6">
	  <button id="blackbutton" onclick="alert('Hello world!')">Dar cuenta de baja</button>
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
				   <td class="tg-w9sc" colspan="2">${cuenta.limiteDiario} â‚¬</td>
				   <td class="tg-uys7"></td>
				   <td class="tg-uys7"></td>
				 </tr>
				 <tr>
				   <td class="tg-w9sc">Limite inferior<br></td>
				   <td class="tg-w9sc" colspan="2">${cuenta.limiteInferior} â‚¬</td>
				   <td class="tg-uys7"></td>
				   <td class="tg-uys7"></td>
				 </tr>
			</table>
		</div> -->
      </c:forEach>
      
	  <br/>
		  <button id="blackbutton" onclick="alert('Hello world!')">Añadir cuenta</button>
	
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
	  <h6>Número de tarjeta</h6>
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
	  <h6>Fecha Creación</h6>
	  <span>${monedero.fechaCreacion}</span>
	  </div>
	  <div class="col-4">
	  <h6>Fecha baja</h6>
	  <span>${monedero.fechaBaja}</span>
	  </div>
	  </div>
	   <br/>
		  <button id="blackbutton" onclick="alert('Hello world!')">Dar de baja</button>
	
	
	  </div>
		  <div class="form-lg" >
	
		<form action="/ElBanco/admin" method="GET">
	  		<button type="submit" value="VOLVER">VOLVER</button>
		</form>
		</div>
		</div>
	</body>

</html>