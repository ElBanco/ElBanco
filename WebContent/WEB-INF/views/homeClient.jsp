<!DOCTYPE html>
<html lang="en">
<head>
  <title>elBanco</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="/ElBanco/estilos.css">
  <link href="https://fonts.googleapis.com/css?family=Francois+One" rel="stylesheet">
</head>

<body >
	
	<div id="logo">
		<h2  title="ElBanco" > 
			ErBanco
		</h2> 
	</div> 
	
	<div class="topnav" id="myTopnav">
	  <a href="/ElBanco/client" class="active">Home</a>
	  <a href="/ElBanco/client/cuentas?nombreUsuario=${user.nombreUsuario}">Cuentas</a>
	  <a href="/ElBanco/client/monedero?nombreUsuario=${user.nombreUsuario}">Monedero</a>
	  <!--<a href="/ElBanco/client/datos?nombreUsuario=${user.nombreUsuario}">Datos</a>-->
	  <a href="/ElBanco/logout"  style="float: right;">Cerrar sesion</a>
	  <!--<form action="/ElBanco/logout" method="GET">
		<button type="submit" value="LOGOUT">LOGOUT</button>
	</form>-->
	  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
	</div>

<div style="padding:8%">
  <a href="/ElBanco/client/cuentas?nombreUsuario=${user.nombreUsuario}"><img class="mySlides" src="resources/money.jpg" style="width:auto; height:400px; opacity:0.5"></a>
  <a href="/ElBanco/client/monedero?nombreUsuario=${user.nombreUsuario}"><img class="mySlides" src="resources/tajeta.jpg" style="width:auto; height:400px; opacity:0.7"></a>
  <!-- <a href="/ElBanco/client/datos"><img class="mySlides" src="resources/yourself.jpg" style="width:auto; height:400px"> -->

  <div class="centered" id="carrousel2">Comprueba tus cuentas</div>
  <div class="centered" id="carrousel1">Consulta tu tarjeta monedero</div>
   <!--<div class="centered" id="carrousel3">Actualiza tus datos</div>-->
</div>

<script>
var myIndex = 0;
carousel();

function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
       x[i].style.display = "none";  
    }
    myIndex++;
    if (myIndex > x.length) {myIndex = 1}    
    x[myIndex-1].style.display = "block";  
    setTimeout(carousel, 3000); // Change image every 2 seconds
}
</script>

<script>
var myIndexT = 0;
carouselT();

function carouselT() {
    var iT;
    var xT = document.getElementsByClassName("centered");
    for (iT = 0; iT < xT.length; iT++) {
       xT[iT].style.display = "none";  
    }
    myIndexT++;
    if (myIndexT > xT.length) {myIndexT = 1}    
    xT[myIndexT-1].style.display = "block";  
    setTimeout(carouselT, 3000); // Change image every 2 seconds
}
</script>

</body>
</html>
