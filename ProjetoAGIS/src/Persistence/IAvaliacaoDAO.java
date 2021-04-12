package Persistence;

import java.sql.SQLException;

import Entity.Avaliacao;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public interface IAvaliacaoDAO {
	public ObservableList<Avaliacao> BuscaAvaliacao(int i) throws SQLException;
	public double consultaPeso(int i) throws SQLException;
}
