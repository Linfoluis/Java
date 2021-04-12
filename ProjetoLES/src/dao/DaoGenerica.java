package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoGenerica {
	
	private Connection conexao;

	public Connection getConnection() {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=Sistema_Vendas;namedPipes=True", "admin", "admin");
			System.out.println("CONECTADO!");
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println("NÃO FOI POSSÍVEL CONECTAR!");
			e.printStackTrace();
		}
		return conexao;
	}
}
