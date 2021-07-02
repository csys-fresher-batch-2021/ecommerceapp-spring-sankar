<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<main class="container-fluid"></main>
<meta charset="ISO-8859-1">
<title>E-CommerceApp</title>
</head>

<body>
   <h3>Password Reset</h3>
	<form onsubmit="sendUserDetails()" method="post">
      
		<label>MobileNumber</label> <input type="number" id="mobilenumber"
			placeholder="Enter phone.no" pattern="^[6-9][0-9]{9}$ "
			title="phonenumber should be in valid format" required><br/>
		your Security Question:<select id="securityQuestions">
			<option
				value="What was the name of your first manager at your first job?">What
				was the name of your first manager at your first job?</option>
			<option value="What was your favorite subject in high school?">What
				was your favorite subject in high school?</option>
			<option value="What is your employee ID number?">What is
				your employee ID number?</option>
			<option
				value="Where did you go on your favorite vacation as a child?">Where
				did you go on your favorite vacation as a child?</option>
			<option value="What is the name of the road you grew up on?">What
				is the name of the road you grew up on?</option>
		</select><br /> 
		<label> your SecurityAnswer:</label> 
		<input type="text"
			id="securityanswer" placeholder="Enter Security Answer " title ="Should be valid" required><br />
		<label> New Password</label> <input type="password"  id="password"
			placeholder="Enter New password" pattern="^[A-Za-z0-9]{5,8}$"
			title="password can have alphabets and digits" required><br /> 
			<label>Confirm
			Password</label> <input type="password" id="confirmPassword"
			placeholder="retype password" pattern="^[A-Za-z0-9]{5,8}$"
			title="password can have alphabets and digits" required><br />
		<button class="btn btn-primary" type="submit">Reset Password</button>
		<button class="btn btn-danger" type="reset">cancel</button>
		<a href="LoginPage.jsp">Login</a>

	</form>
	<script>
function sendUserDetails() {
	event.preventDefault();
	
	
	let mobileNumber = document.getElementById("mobilenumber").value;
	let password= document.getElementById("password").value;
	let confirmPassword= document.getElementById("confirmPassword").value;
	let securityQuestion= document.getElementById("securityQuestions").value;
	let securityAnswer= document.getElementById("securityanswer").value;
	
	let data={
			"mobileNumber":mobileNumber,
			"password":password,
			"confirmPassword":confirmPassword,
			"securityQuestion":securityQuestion,
			"securityAnswer":securityAnswer
		};
	console.log(data);				
	let url = "updateCredentials";	
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