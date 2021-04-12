<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Produto"%>
<%@page import="java.util.List"%>
<%@page import="entity.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<head>
<meta charset="UTF-8">
	<title>Upsell Hardware - Carrinho</title>
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/carrinho.css">
</head>
<body>
	<!-- Menu de Navegação -->
	<nav class="navbar navbar-expand-lg fixed-top">
		<div class="container" id="containerid">
			<a class="navbar-brand" href="./">Upsell Hardware</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#sobre">Sobre</a></li>
					<li class="nav-item"><a class="nav-link" href="#produtos">Produtos</a></li>
					<%
						Usuario usuario = new Usuario();
						usuario = (Usuario) session.getAttribute("usuario");
						if (usuario != null) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <span style="color: black;">Olá, <%=usuario.getUsuario()%></span>
					</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="./usuario">Sair</a>
						</div></li>
					<%
						if (usuario.temPermissaoAdmin()) {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <span style="color: black;">Gerenciar</span>
					</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="./inserirproduto.jsp">Inserir Novo Produto</a>
							<a class="dropdown-item" href="./relatorio.jsp">Relátorio de Vendas</a>
						</div></li>
					<%
						}	
						List<Produto> carrinho = new ArrayList<Produto>();
						if (session.getAttribute("carrinho") != null){
							carrinho = (List<Produto>) session.getAttribute("carrinho");
						}
					%><li class="nav-item"><a class="nav-link" href="./carrinho/"><img src="./img/carrinho.svg">(<%=carrinho.size() %>)</a></li><%
						} else {
					%>
					<li class="nav-item"><a class="nav-link" href="./login">Cadastro
							/ Login</a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</nav>
	<!-- FIM NAV BAR -->

	<h1>Carrinho</h1>

	<div class="shopping-cart">

		<div class="column-labels">
			<label class="product-image">Image,</label> <label
				class="product-details">Produto</label> <label class="product-price">Preço</label>
			<label class="product-quantity">Quantidade</label> <label
				class="product-removal">Remover</label>
		</div>

		<%
			List<Produto> carrinho = new ArrayList<Produto>();
			if (session.getAttribute("carrinho") != null){
				carrinho = (List<Produto>) session.getAttribute("carrinho");
			}
			Double total = 0.0;
			for(Produto p : carrinho) {
				total += p.getPreco();
				%>
					<div class="product">
						<div class="product-image">
							<img src="img/cpu.svg">
						</div>
						<div class="product-details">
							<div class="product-title"><%=p.getNome() %></div>
							<p class="product-description"><%=p.getDescricao() %></p>
						</div>
						<div class="product-price"><%=p.getPreco() %></div>
						<div class="product-quantity">
							<input type="number" value="1" min="1" max="10">
						</div>
						<div class="product-removal">
							<a href="/ProjetoLES/carrinho/remover?id=<%=p.getId() %>"><img class="trashCan" src="img/trash.svg"/></a>
						</div>
					</div>
				<%
			}
			
			DecimalFormat df = new DecimalFormat("###,##0.00");
		%>

		<div class="totals">
			<div class="totals-item">
				<label>Frete</label>
				<div class="totals-value" id="cart-shipping"><%=df.format(total*0.05) %></div>
			</div>
			<div class="totals-item totals-item-total">
				<label>Total</label>
				<div class="totals-value" id="cart-total"><%=df.format(total) %></div>
			</div>
		</div>

		<button class="checkout" onclick="checkoutCart()">Finalizar Compra</button>

	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="./javascript/carrinho.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>