package Persistence;

import java.sql.SQLException;

import Entity.Disciplina;
import javafx.collections.ObservableList;

public interface IDisciplinaDAO {
	public ObservableList<Disciplina> BuscaDisciplina() throws SQLException;
}
