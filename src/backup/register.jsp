<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>E-CommerceApp</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid"></main>
	<%
	String infoMessage = request.getParameter("infoMessage");
	if (infoMessage != null) {
		out.println("<font color='green'>" + infoMessage + "</font>");
	}
	%>
	<%
	String errorMessage = request.getParameter("errorMessage");
	if (errorMessage != null) {
		out.println("<font color ='red'>" + errorMessage + "</font>");
	}
	%>
	<form onsubmit="sendUserDetails()" method="post">
		<h3>User Registration</h3>

		<label>FullName</label> <input type="text" id="fullname"
			placeholder="Enter Fullname" pattern="^[A-Z][A-Za-z_ ]{4,29}$"
			title="Name should be in alphabets and should start with Caps"
			 autofocus required><br />
		<label>UserName</label> <input type="text" id="username"
			placeholder="Enter username" pattern="^[A-Za-z][A-Za-z0-9]{5,12}$"
			title="username can contain alphabets and numerals" required><br /> 
			<label>MobileNumber</label>
		<input type="number" id="mobilenumber" placeholder="Enter phone.no"
			pattern="^[6-9][0-9]{9}$"
			title="phonenumber should be in valid format" required><br />
		<label>E-mail</label> <input type="email" id="email"
			placeholder="Enter your email" pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$" title="INvalid email format" required><br /> <label>Password</label>
		<input type="password" id="password" placeholder="Enter password"
			pattern="^[A-Za-z0-9]{5,8}$"
			title="password can have both alphabets and digits" required><br /> 
			<label>Confirm
			Password</label> <input type="password" id="confirmPassword"
			placeholder="retype password" pattern="^[A-Za-z0-9]{5,8}$"
			title="password can have both alphabets and digits" required><br />
			Security Question:<select id="securityQuestions">
  <option value="What was the name of your first manager at your first job?">What was the name of your first manager at your first job?</option>
  <option value="What was your favorite subject in high school?">What was your favorite subject in high school?</option>
  <option value="What is your employee ID number?">What is your employee ID number?</option>
  <option value="Where did you go on your favorite vacation as a child?">Where did you go on your favorite vacation as a child?</option>
  <option value="What is the name of the road you grew up on?">What is the name of the road you grew up on?</option>
</select><br/>
<label>SecurityAnswer</label> <input type="text" id="securityanswer"
			placeholder="Enter Security Answer "  title="should be valid one "
			  required ><br />
		<button class="btn btn-primary">Register</button>
		<button class="btn btn-danger" type="reset">Reset</button>
		<h5>
			<a href="LoginPage.jsp">Login</a>
		</h5>
	</form>
	<script>
function sendUserDetails() {
	event.preventDefault();
	let fullName = document.getElementById("fullname").value;
	let userName = document.getElementById("username").value;
	let mobileNumber = document.getElementById("mobilenumber").value;
	let email= document.getElementById("email").value;
	let password= document.getElementById("password").value;
	let confirmPassword= document.getElementById("confirmPassword").value;
	let securityQuestion= document.getElementById("securityQuestions").value;
	let securityAnswer= document.getElementById("securityanswer").value;
	
	let data={
			"fullName":fullName,
			"userName":userName,
			"mobileNumber":mobileNumber,
			"email":email,
			"password":password,
			"confirmPassword":confirmPassword,
			"securityQuestion":securityQuestion,
			"securityAnswer":securityAnswer
		};
	console.log(data);				
	let url = "AddUser";	
	axios.post(url,data).then(res=>{
		console.log(res.data);
		console.log(res.data.message);
		toastr.success(res.data.message);
		setTimeout(function(){ window.location="LoginPage.jsp";}, 1000);
		
	}).catch(err=>{
		console.log(err.response.data.message);
		toastr.error(err.response.data.message);
						
	});
	
}

</script>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js" integrity="sha512-bZS47S7sPOxkjU/4Bt0zrhEtWx0y0CRkhEp8IckzK+ltifIIE9EMIMTuT/mEzoIMewUINruDBIR/jJnbguonqQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</html>