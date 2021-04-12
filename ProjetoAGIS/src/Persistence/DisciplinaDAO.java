package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Disciplina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DisciplinaDAO implements IDisciplinaDAO {
	private Connection cn;
	
	public DisciplinaDAO() throws ClassNotFoundException, SQLException
	{
		GenericDAO gdao = new GenericDAO();
		cn = gdao.getConnection();
	}
	
	public ObservableList<Disciplina> BuscaDisciplina() throws SQLException {
		String sql = "select * from disciplina";
		PreparedStatement ps = cn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ObservableList<Disciplina> obsdisc = FXCollections.observableArrayList();
		while(rs.next())
		{
			Disciplina d = new Disciplina();
			d.setCodigoDisciplina(rs.getInt("codigo"));
			d.setNomeDisciplina(rs.getString("nome"));
			d.setSiglaDisciplina(rs.getString("sigla"));
			d.setTurnoDisciplina(rs.getString("turno"));
			d.setnAulasDisciplina(rs.getInt("numaulas"));
			obsdisc.add(d);
		}
		rs.close();
		ps.close();
		return obsdisc;
	}
}
