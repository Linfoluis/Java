package Persistence;

import java.sql.Connection;
import java.sql.SQLException;

public interface IGenericDAO {
	public Connection getConnection() throws SQLException;
}
