package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Aluno;
import Entity.Avaliacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class AvaliacaoDAO implements IAvaliacaoDAO{
	private Connection cn;
	
	public AvaliacaoDAO() throws ClassNotFoundException, SQLException{
		GenericDAO gdao = new GenericDAO();
		cn = gdao.getConnection();
	}
	
	public ObservableList<Avaliacao> BuscaAvaliacao(int i) throws SQLException {
		String sql = "select av.cod_disc, av.codigo, av.tipo, av.peso from avaliacao av inner join disciplina d on av.cod_disc = d.codigo where d.codigo="+i+"";
		PreparedStatement ps = cn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ObservableList<Avaliacao> obsal = FXCollections.observableArrayList();
		while(rs.next())
		{
			Avaliacao a = new Avaliacao();
			a.setCodDisciplina(rs.getInt("cod_disc"));
			a.setCodAvalicao(rs.getInt("codigo"));
			a.setTipo(rs.getString("tipo"));
			a.setPeso(rs.getDouble("peso"));
			obsal.add(a);
		}
		rs.close();
		ps.close();
		return obsal;
	}

	public double consultaPeso(int i) throws SQLException {
		Double d = 0.0;
		Avaliacao av = new Avaliacao();
		String sql = "select av.peso from avaliacao av where av.codigo= ?";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setInt(1, i);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			d = rs.getDouble("peso");
		}
		rs.close();
		ps.close();
		cn.close();
		System.out.println(d);
		return d;
	}	
}
