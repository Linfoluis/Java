package Persistence;

import java.sql.SQLException;

import Entity.Aluno;
import Entity.Disciplina;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public interface IAlunoDAO {
	public ObservableList<Aluno> BuscaAluno(int i) throws SQLException;
}
