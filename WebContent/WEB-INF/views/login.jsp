<!DOCTYPE html>
<html lang="en">
<head>
  <title>erBanco</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="/resources/css/estilos.css" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Francois+One" rel="stylesheet">
</head>
<!--background: #76b852;-->

<body id=index>
	
	<div id="logo">
		<h1  title="ElBanco" > 
			ErBanco
		</h1> 
	</div> 

	<div class="login-page">
	  <div class="form" >
		<!-- <form action="/ElBanco/login" method="POST" class="login-form">
		  <input type="text" placeholder="username"/>
		  <input type="password" placeholder="password"/>
		  <button>login</button>
		</form>-->
		<form action="/ElBanco/login" method="POST">
		User : <input name="username" type="text"/> 
		Password : <input name="password" type="password"/>
		<input type="submit" />
		</form>
	  </div>
	</div>


</body>
</html>
