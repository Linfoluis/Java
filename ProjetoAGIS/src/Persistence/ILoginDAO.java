package Persistence;

import java.sql.SQLException;

import Entity.Login;

public interface ILoginDAO {
	public String consultaLogin(Login l) throws SQLException;
}
