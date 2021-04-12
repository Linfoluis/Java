package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Produto;

public class DaoProduto {

	private Connection c;
	
	public DaoProduto() {
		DaoGenerica dao = new  DaoGenerica();
		c = dao.getConnection();
	}
	
	public boolean insereProduto(Produto p) {
		try {
			String sql = "INSERT INTO produto VALUES ('"+p.getNome()+"', '"+p.getDescricao()+"', "+p.getPreco()+")";
			Statement stmt = c.createStatement();
			stmt.execute(sql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean excluirProduto(Long id) {
		try {
			String sql = "DELETE produto WHERE id = "+id+"";
			Statement stmt = c.createStatement();
			stmt.execute(sql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean atualizarProduto(Produto p) {
		try {
			String sql = "UPDATE produto SET nome = '"+p.getNome()+"', "
											+ "descricao = '"+p.getDescricao()+"', "
											+ "preco = "+p.getPreco()+" "
											+ "where id = "+p.getId()+"";
			Statement stmt = c.createStatement();
			stmt.execute(sql);
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Produto> listaProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			String sql = "SELECT * FROM produto";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setPreco(rs.getDouble("preco"));
				produtos.add(p);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}
	
	public Produto getProdutoPorId(long id) {
		Produto p = new Produto();
		try {
			String sql = "Select * from produto where id = "+id+"";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				p = new Produto();
				p.setId(id);
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setPreco(rs.getDouble("preco"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public Produto getProdutoPorNome(String nome) {
		Produto p = new Produto();
		try {
			String sql = "Select * from produto where nome like '"+nome+"' + '%'";			
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				p = new Produto();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setPreco(rs.getDouble("preco"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
}
