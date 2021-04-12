package Persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.swing.JOptionPane;

import Entity.Aluno;
import Entity.Disciplina;
import Entity.Nota;
import Persistence.GenericDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NotasDAO implements INotasDAO {

	private Connection cn;

	public NotasDAO() throws ClassNotFoundException, SQLException {
		GenericDAO gdao = new GenericDAO();
		cn = gdao.getConnection();
	}

	@Override
	public void insereNota(Nota n) throws SQLException {
		String sql = "{CALL sp_Notas(?,?,?,?)}";
		CallableStatement cs = cn.prepareCall(sql);
		cs.setInt(1, n.getRA());
		cs.setInt(2, n.getCodDisc());
		cs.setInt(3, n.getAvalCod());
		cs.setDouble(4, n.getnNota());
		cs.execute();
		JOptionPane.showMessageDialog(null, "Nota Inserida com Sucesso !");
		cs.close();
	}

	@Override
	public ObservableList<Nota> buscaNotas(int i, String s) throws SQLException {
		ObservableList<Nota> obsnota2 = FXCollections.observableArrayList();
		String sql = "select n.aluno_ra, n.nota from notas n inner join avaliacao av on n.aval_codigo = av.codigo where n.disci_codigo = "+i+" and av.tipo = '"+s+"'";
		PreparedStatement cs = cn.prepareStatement(sql);
		ResultSet rs = cs.executeQuery();
		while (rs.next()) {
			Nota n = new Nota();
			n.setRA(rs.getInt("aluno_ra"));
			n.setnNota(rs.getDouble("nota"));
			System.out.println(rs.getDouble("nota"));
			obsnota2.add(n);
		}
		cs.execute();
		cs.close();
		return obsnota2;
	}
}
