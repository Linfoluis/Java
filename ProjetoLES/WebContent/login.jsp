 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/Logincss.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet"> 
<title>Upsell Hardware - Login</title>
</head>
<body>
	<!-- Formulário de Login -->
	<div class="caixa">
		<h2> Login </h2>
		<form action="./login" method="post">
			<div class="input">
				<input type="text" name="usuario" required id="usuario">
				<label> Usuário </label>
			</div>
			<div class="input">
				<input type="password" name="senha" required id="senha">
				<label> Senha </label>
			</div>
			<button type="submit" id="cmd" name="cmd" value="login">Entrar</button>
			<button type="submit" id="cmd" name="cmd" value="add">Cadastrar</button>
		</form>
		<%
			String msg = (String) session.getAttribute("msg");
			if (msg != null){
				session.setAttribute("msg", null);
				%><h3 style="color: white;"><%=msg %></h3> <%
			}
		%>
	</div>
</body>
</html>