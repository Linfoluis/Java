package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoProduto;
import entity.Produto;

@WebServlet("/produto")
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto p = new Produto();
		DaoProduto daoProduto = new DaoProduto();
		
		String msg = "";
		String cmd = request.getParameter("cmd");
		switch (cmd ) {
		case "inserir":
			p = new Produto();
			p.setNome(request.getParameter("nome"));
			p.setDescricao(request.getParameter("descricao"));
			p.setPreco(Double.parseDouble(request.getParameter("preco").replace("R$ ", "").replace(",", "")));
			if(daoProduto.insereProduto(p)) {
				msg = "Produto inserido com sucesso!";
			} else {
				msg = "Falha ao Inserir o Produto.";
			}
			break;
		
		case "atualizar":
			p = new Produto();
			p = daoProduto.getProdutoPorNome(request.getParameter("nome"));
			p.setDescricao(request.getParameter("descricao"));
			p.setPreco(Double.parseDouble(request.getParameter("preco").replace("R$ ", "").replace(",", "")));
			if(daoProduto.atualizarProduto(p)) {
				msg = "Produto atualizado com sucesso!";
			} else {
				msg = "Produto não existente!";
			}
			break;
		case "excluir":
			p = new Produto();
			p = daoProduto.getProdutoPorNome(request.getParameter("nome"));
			if(daoProduto.excluirProduto(p.getId())) {
				msg = "Produto excluído com sucesso!";
			} else {
				msg = "Produto não existente!";
			}
			break;
		
		case "pesquisar":
			p = new Produto();
			p = daoProduto.getProdutoPorNome(request.getParameter("nome"));
			if (p.getNome() != null ) {
				request.getSession().setAttribute("produto", p);
			} else {
				msg = "Produto não encontrado!";
			}
		default:
			break;
		}
		
		request.getSession().setAttribute("mensagem", msg);
		response.sendRedirect("./inserirproduto.jsp");
	}

}
