package Persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import Entity.Login;
import View.TLogin;

public class LoginDAO implements ILoginDAO{
private Connection cn;
	
	public LoginDAO() throws ClassNotFoundException, SQLException
	{
		GenericDAO gdao = new GenericDAO();
		cn = gdao.getConnection();
	}

	@Override
	public String consultaLogin(Login l) throws SQLException {
		String sql = "{CALL sp_validaLogin(?,?,?)}";
		CallableStatement cs = cn.prepareCall(sql);
		cs.setString(1, l.getRG());
		cs.setString(2, l.getSenha());
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(3);
		cs.close();
		if(saida.contentEquals("Conectado!")) {
			JOptionPane.showMessageDialog(null, "Login Efetuado com Sucesso !");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Credenciais Incorretas !");
		}
		return saida;
	}
	
}
