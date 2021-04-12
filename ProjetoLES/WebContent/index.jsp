<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="entity.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.DaoProduto"%>
<%@page import="entity.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./css/index.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<title>Upsell Hardware - Home</title>
</head>
<body>
	<!-- Menu de Navegação -->
	<nav class="navbar navbar-expand-lg fixed-top">
		<div class="container" id="containerid">
			<a class="navbar-brand" href="#">Upsell Hardware</a>
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
	
	<!-- Slider -->
	<div id="carouselExampleIndicators" class="carousel slide"
		data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="./img/intel.png" class="d-block w-100">
			</div>
			<div class="carousel-item">
				<img src="./img/nvidiaslider.png" class="d-block w-100" alt="...">
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	<!-- Sobre -->
	<Section id="sobre">
		<h2>Qualidade Garantida!</h2>
		<p>Somos uma empresa focada na melhor experiência possível de nossos cliente, <br><nobr>
			para isso oferecemos <b>GARANTIA</b> de 3 <b>MESES</b> para qualquer um de nosssos <b>PRODUTOS</b>.</nobr></p>
	</section>
	<!-- Produtos -->
	
	<%
		DaoProduto daoProduto = new DaoProduto();
		List<Produto> produtos = new ArrayList<Produto>();
		produtos = daoProduto.listaProdutos();
	%>
	
	
	<div class="container" id="produtos">
		<div class="row row-cols-1 row-cols-md-3">
		
		<%
			for(Produto produto : produtos) {
				%>
				<div class="col mb-4">
					<div class="card">
						<img src="./img/cpu.svg" class="card-img-top">
						<div class="card-body">
							<h5 class="card-title" id="nome"><%=produto.getNome().toUpperCase() %></h5>
							<p class="card-text"><%=produto.getDescricao() %></p>
							<p class="card-text" id="preco"><%=NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(produto.getPreco()) %></p>
							<button class="btn btn-primary" onclick="addCart(<%=produto.getId()%>)">Comprar</button>
						</div>
					</div>
				</div>
				<%
			}
		%>
		
			
		</div>
	</div>
	<!-- Rodapé -->
	<footer class="footer">
		<div class="footerdireita">
			<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
				class="fa fa-twitter"></i></a> <a href="#"><i class="fa fa-linkedin"></i></a>
			<a href="#"><i class="fa fa-instagram"></i></a>
		</div>
		<div class="footeresquerda">
			<p class="footerbotoes">
				<a href="#home">Home</a> · <a href="#sobre">Sobre</a> · <a
					href="#produtos">Produtos</a> · <a href="#produtos">Carrinho</a> ·
				<a href="#">Cadastro / Login</a>
			</p>
			<p>Upsell Hardware &copy; 2020</p>
		</div>
	</footer>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
		
	<script src="./javascript/carrinho.js"></script>
</body>
</html>