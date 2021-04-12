package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoUsuario;
import entity.Usuario;

@WebServlet("/login")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("./login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		Usuario usuario = new Usuario();
		usuario.setUsuario(request.getParameter("usuario"));
		usuario.setSenha(request.getParameter("senha"));
		DaoUsuario daoUsuario = new DaoUsuario();
		if (usuario.getUsuario().equals("admin")) {
			usuario.setPermissao(true);
		}
		if (cmd.equals("add")) {
			if(daoUsuario.insereUsuario(usuario)) {
				request.getSession().setAttribute("msg", "Usuário cadastrado com sucesso!");
			} else {
				request.getSession().setAttribute("msg", "Usuário já cadastrado!");
			}
			response.sendRedirect("./login.jsp");
		} else {
			if(daoUsuario.pesquisaSeExisteUsuario(usuario)) {
				request.getSession().setAttribute("usuario", usuario);
				response.sendRedirect("./");
			} else {
				request.getSession().setAttribute("msg", "Usuário não encontrado");
				response.sendRedirect("./login.jsp");
			}
		}
	}


}
