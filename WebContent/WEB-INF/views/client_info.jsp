<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head>
      <title>Client Information</title>
   </head>
   
   <body>
   	  <h2>Datos Personales</h2>
      <p>nombreUsuario: ${client.nombreUsuario}</p>
      <p>nombre: ${client.nombre}</p>
      <p>apellidos: ${client.apellidos}</p>
      <p>email: ${client.email}</p>
      <p>telefono: ${client.telefono}</p>
      <p>fechaCreacion: ${client.fechaCreacion}</p>
      <p>fechaModificacion: ${client.fechaCreacion}</p>
      <p>fechaBaja: ${client.fechaBaja}</p>
	  <br />
	  
	  
	  <h2>Datos Cuenta</h2>
	  <c:forEach items="${cuentas}" var="cuenta">
         <p>numeroCuenta: ${cuenta.numeroCuenta}</p>
      	 <p>nombreUsuario: ${cuenta.nombreUsuario}</p>
         <p>saldo: ${cuenta.saldo}</p>
         <p>limiteDiario: ${cuenta.limiteDiario}</p>
         <p>limiteInferior: ${cuenta.limiteInferior}</p>
         <p>fechaCreacion: ${cuenta.fechaCreacion}</p>
         <p>fechaModificacion: ${cuenta.fechaModificacion}</p>
        <p>fechaBaja: ${cuenta.fechaBaja}</p>
      </c:forEach>
	
	  
	  <h2>Datos Monedero</h2>
      <p>numeroTarjeta: ${monedero.numeroTarjeta}</p>
      <p>PIN: ${monedero.pin}</p>
      <p>saldo: ${monedero.saldo}</p>
      <p>fechaCancelacion: ${monedero.fechaCancelacion}</p>
      <p>nombreUsuario: ${monedero.nombreUsuario}</p>
      <p>fechaCreacion: ${monedero.fechaCreacion}</p>
      <p>fechaBaja: ${mondero.fechaBaja}</p>
	  <br />
	
	
	<form action="/ElBanco/admin" method="GET">
		<input value="VOLVER" type="submit" />
	</form>
   </body>
   
</html>