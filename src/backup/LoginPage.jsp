<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>E-CommerceApp</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid"> </main>
	<div id="message">
	</div>
<form onsubmit="sendUserInputs()" method ="post">
<h3> User Login</h3>
<label>Username</label>
<input type="text"  id="username"  placeholder="Enter your username" 
			  required><br/>
<label>Password</label>
<input type="password"  id="password"  placeholder="Enter your password" 
			  required><br/>
<button class="btn btn-primary">Login</button>
<button class="btn btn-danger" type="reset">cancel</button>

<p>Don't have an account ?<a href="register.jsp">  Register</a></p> 
<a href="ForgotPassword.jsp">Forgot password ?</a> 
<p>For admin Credentials use Username : "admin34"  and password="admin"</p>
</form>
<script>
function sendUserInputs() {
	event.preventDefault();
	let userName = document.getElementById("username").value;
	let password= document.getElementById("password").value;
	let data={
			"userName":userName,
			"password":password
		};
	console.log(data);				
	let url = "UserLogin";	
	axios.post(url,data).then(res=>{
		console.log(res.data.message);
		toastr.success(res.data.message);
		setTimeout(function(){ window.location="ListProducts.jsp"; }, 2000);
		
		
	}).catch(err=>{
		console.log(err.response.data.message);
		toastr.error(err.response.data.message);
						
	});	
	
}
</script>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js" integrity="sha512-bZS47S7sPOxkjU/4Bt0zrhEtWx0y0CRkhEp8IckzK+ltifIIE9EMIMTuT/mEzoIMewUINruDBIR/jJnbguonqQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</html>