package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TLogin extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane Pane = (Pane)FXMLLoader.load(this.getClass().getResource("TLogin.fxml"));
		Scene scn = new Scene(Pane);
		primaryStage.getIcons().add(new Image(TGerenciar.class.getResourceAsStream("logoagis.PNG")));
		primaryStage.setTitle("Seja Bem Vindo");
		primaryStage.setScene(scn);
		primaryStage.show();
	}
}
