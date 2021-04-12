package Persistence;

import java.sql.SQLException;
import java.util.List;

import Entity.Disciplina;
import Entity.Nota;
import javafx.collections.ObservableList;

public interface INotasDAO {
	public void insereNota(Nota n) throws SQLException;
	public ObservableList<Nota> buscaNotas(int i, String s) throws SQLException;
}
