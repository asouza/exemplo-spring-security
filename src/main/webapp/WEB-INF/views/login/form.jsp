
<html>
<head>
<title>Login Page--</title>
</head>
<body onload='document.f.username.focus();'>
	<h3>Login with Username and Passworddd</h3>
	<form name='f' action='/loja-super-segura/login' method='POST'>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			
			<tr>
				<td>Estado:</td>
				<td><input type='text' name='unidade' /></td>
			</tr>
			<tr>
				<td>Remember me</td>
				<td><input type="checkbox" name="remember-me" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>