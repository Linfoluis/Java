package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO implements IGenericDAO {
	private Connection cn;
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=siga;namedPipes=true","admin","admin");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cn;
	}
}
