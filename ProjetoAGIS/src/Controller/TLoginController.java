package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Entity.Login;
import Persistence.LoginDAO;
import View.TGerenciar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TLoginController implements Initializable{
	@FXML
	private TextField txtUsuario;
	@FXML
	private TextField txtSenha;
	@FXML
	private Button btnLogin;

	@FXML
	public void ValidaLogin(ActionEvent event) throws Exception {
		try {
			LoginDAO ld = new LoginDAO();
			Login l = new Login();
			l.setRG(txtUsuario.getText());
			l.setSenha(txtSenha.getText());
			if(ld.consultaLogin(l).contentEquals("Conectado!"))
			{
				btnLogin.getScene().getWindow().hide();
				TGerenciar tg = new TGerenciar();
				tg.start(new Stage());
			}
			else
			{
				txtUsuario.clear();
				txtSenha.clear();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
	
