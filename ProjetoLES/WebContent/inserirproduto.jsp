<%@page import="entity.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Produto"%>
<%@page import="java.util.List"%>
<%@page import="dao.DaoProduto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/inserirproduto.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap"
	rel="stylesheet">
<title>Upsell Hardware</title>
</head>
<body>

	<%
		Usuario usuario = ((Usuario) session.getAttribute("usuario"));
		if (usuario == null || !usuario.getUsuario().equals("admin")){
			response.sendRedirect("./");
		}
		
		Produto p1 = new Produto();
		if (session.getAttribute("produto") == null) {
			p1.setNome("");
			p1.setDescricao("");
		} else {
			p1 = (Produto) session.getAttribute("produto");
		}
		session.setAttribute("produto", null);
	%>

	<div class="caixa">
		<h2>Produto</h2>
		<form action="./produto" method="post" autocomplete="off">
			<div class="input">
				<label>Nome</label> 
				<input type="text" name="nome" list="produtos" required placeholder="Digite aqui o nome do Produto" value="<%=p1.getNome() %>"/> 
				<div class="botoes">
					<button type="submit" id="cmd" name="cmd" value="pesquisar">Pesquisar</button>
				</div>
					<datalist id="produtos">
					<%  DaoProduto daoProduto = new DaoProduto();
						List<Produto> produtos = new ArrayList<Produto>();
						produtos = daoProduto.listaProdutos();
						
						for(Produto p : produtos) {
							%>
								<option value="<%=p.getNome() %>"></option>
							<%
						}
					%>
					</datalist> 
			</div>
			<div class="input">
				<label>Preço</label> 
				<input value="<%=p1.getPreco() %>"type="text" data-prefix="R$ " name="preco" id="preco" class="form-control" placeholder="R$ 0.00">
			</div>
			<div class="input">
				<label class="descp">Descrição</label><br> <br>
				<textarea rows="4" cols="50"
					placeholder="Digite a descrição do produto" name="descricao"><%=p1.getDescricao() %></textarea>
			</div>
			<div class="botoes">
				<button type="submit" id="cmd" name="cmd" value="inserir">Inserir</button>
				<button type="submit" id="cmd" name="cmd" value="atualizar">Atualizar</button>
				<button type="submit" id="cmd" name="cmd" value="excluir">Excluir</button>
			</div>
		</form>
		
		<%
			String msg = (String) session.getAttribute("mensagem");
			if (msg != null){
				session.setAttribute("mensagem", null);
				%><h3 style="color: black;"><%=msg %></h3> <%
			}
		%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
	<script>
		jQuery(function() {
			jQuery('#preco').maskMoney();
		});
	</script>
</body>
</html>