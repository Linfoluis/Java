package Persistence;

import java.sql.SQLException;

import Entity.Aluno;
import Entity.Falta;
import javafx.collections.ObservableList;

public interface IFaltaDAO {
	public ObservableList<Aluno> buscaFalta(int i) throws SQLException;
	public void insereFalta(Falta f) throws SQLException;
}
