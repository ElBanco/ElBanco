<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head>
      <title>Access Granted</title>
   </head>
   
   <body>
   
      <p>Student First Name: ${userBean.nombreUsuario}</p>
      <p>Student First Name: ${userBean.rolID}</p>
      <p>Student First Name: ${userBean.nombre}</p>
      <p>Student First Name: ${userBean.apellidos}</p>
      <p>Student First Name: ${userBean.email}</p>
      <p>Student First Name: ${userBean.telefono}</p>
      <p>Student First Name: ${userBean.fechaCreacion}</p>
      
	<br />
	
	<h2>Transferencia</h2>
	<form action="/ElBanco/client" method="POST">
		Cantidad : <input name="cantidad" type="text"/> <br />
		Numero Cuenta Destino: <input name="numeroCuentaDestino" type="text"/> <br />
		<input type="submit" value="Enviar"/>
	</form>
	
	<h2>Datos Cuenta</h2>
	  <c:forEach items="${cuentas}" var="cuenta">
         <p>numeroCuenta: ${cuenta.numeroCuenta}</p>
         <p>saldo: ${cuenta.saldo}</p>
      </c:forEach>
	
	
	<form action="/ElBanco/logout" method="GET">
		<input value="LOGOUT" type="submit" />
	</form>
   </body>
   
</html>