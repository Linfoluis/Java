package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Aluno;
import Entity.Disciplina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class AlunoDAO implements IAlunoDAO{
	private Connection cn;
	
	public AlunoDAO() throws ClassNotFoundException, SQLException{
		GenericDAO gdao = new GenericDAO();
		cn = gdao.getConnection();
	}

	@Override
	public ObservableList<Aluno> BuscaAluno(int i) throws SQLException {
		String sql = "select a.ra, a.nome from aluno a inner join matricula m on a.ra = m.aluno_ra inner join disciplina d on m.Disciplina_codigo = d.codigo where d.codigo="+i+"";
		PreparedStatement ps = cn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ObservableList<Aluno> obsal = FXCollections.observableArrayList();
		while(rs.next())
		{
			Aluno a = new Aluno();
			a.setRa(rs.getInt("ra"));
			a.setNome(rs.getString("nome"));
			obsal.add(a);
		}
		rs.close();
		ps.close();
		return obsal;
	}
}
