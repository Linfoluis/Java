package Persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Entity.Aluno;
import Entity.Falta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class FaltaDAO implements IFaltaDAO{
	private Connection cn;
	
	public FaltaDAO() throws ClassNotFoundException, SQLException
	{
		GenericDAO gdao = new GenericDAO();
		cn = gdao.getConnection();
	}
	
	public ObservableList<Aluno> buscaFalta(int i) throws SQLException {
		String sql = "select a.ra, a.nome from aluno a inner join matricula m on a.ra = m.aluno_ra inner join disciplina d on m.Disciplina_codigo = d.codigo where d.codigo="+i+"";
		PreparedStatement ps = cn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ObservableList<Aluno> obsaluno = FXCollections.observableArrayList();
		while(rs.next())
		{
			Aluno a = new Aluno();
			a.setRa(rs.getInt("ra"));
			a.setNome(rs.getString("nome"));
			obsaluno.add(a);
		}
		rs.close();
		ps.close();
		return obsaluno;
	}

	public void insereFalta(Falta f) throws SQLException {
		String sql = "{CALL sp_faltas(?,?,?,?,?)}";
		CallableStatement cs = cn.prepareCall(sql);
		cs.setInt(1, f.getAlunoRA());
		cs.setInt(2, f.getDisccodigo());
		cs.setInt(3, f.getPresencas());
		cs.setDate(4, java.sql.Date.valueOf(f.getData()));
		cs.setInt(5, f.getnAulas());
		cs.execute();
		JOptionPane.showMessageDialog(null, "Falta inserida com sucesso !");
		cs.close();
	}
}
