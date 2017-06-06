<html>
<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/css/materialize.min.css">
 <title>Welcome!</title>
</head>
<body>
	<div class="container">
		<h3>Login</h3>
	 	<div class="row">
		 	<form name='form' action='j_spring_security_check' method='POST' class="col s4">
		      <div class="row">
		        <div class="input-field col s12">
		          <input  id="username" name='j_username' type="text" class="validate" placeholder="User Name">
		        </div>
		      </div>
		      <div class="row">
		        <div class="input-field col s12">
		          <input id="password" name="j_password" type="password" class="validate" placeholder="Password">
		        </div>
		      </div>
		       <div class="row">
		        <div class="input-field col s12">
		            <input type="checkbox" name="remember-me" id="remember-me" class="filled-in"  />
      				<label for="remember-me">Remember me</label>
      			</div>

		      </div>
		       <input name="submit" type="submit" class="btn" value="Login"/>
			</form>
		</div>
	</div>
	 <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.8/js/materialize.min.js"></script>
</body>
</html>