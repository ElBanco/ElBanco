<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head>
      <title>Access Granted</title>
   </head>
   
   <body>
   
	
	<h2>Datos Cuenta</h2>
	  <c:forEach items="${cuentas}" var="cuenta">
	  <tr>
    	<td class="tg-8qkv" rowspan="3" ><br>${cuenta.numeroCuenta}</td>
    	<td class="tg-7boo">Saldo</td>
    	<td class="tg-7boo" colspan="2">165677,45 €<br></td>
    	<td class="tg-uys7"></td>
    	<td class="tg-uys7" rowspan="3"><button id="blackbutton" type="button" onclick="alert('Hello world!')">realizar nueva operacion</button><br></td>
    	<td class="tg-uys7"></td>
    	<td class="tg-uys7" rowspan="3"><button id="blackbutton" onclick="alert('Hello world!')">ver ultimas operaciones</button><br></td>
  	</tr>
         <p>numeroCuenta: ${cuenta.numeroCuenta}</p>
         <p>saldo: ${cuenta.saldo}</p>
      </c:forEach>
	
	
	<form action="/ElBanco/logout" method="GET">
		<input value="LOGOUT" type="submit" />
	</form>
   </body>
   
</html>