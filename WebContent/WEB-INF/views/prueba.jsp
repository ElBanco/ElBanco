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
  <link href="https://fonts.googleapis.com/css?family=Francois+One" rel="stylesheet">
  
  <script language="Javascript" type="text/javascript"> 

    var counter = 1; 
    var limit = 3; 
    function addInput(divName){ 
    	var newdiv = document.createElement('div'); 
        newdiv.innerHTML = "Titular <input type='text' name='titular'/> Enviar <input type='submit'/>"; 
        document.getElementById(divName).appendChild(newdiv); 
    } 

  </script> 
</head>
<!--background: #76b852;-->

<body id=index>
	
	<div id="logo">
		<h1  title="ElBanco" > 
			ErBanco
		</h1> 
	</div> 

	<form action="/ElBanco/login" method="POST"> 
	<input type="button" value="Añade tarjeta" onClick="addInput('dynamicInput');">
	<input type="hidden" name="country" value="Norway">
	<div id="dynamicInput"> </div>
	</form> 


</body>
</html>