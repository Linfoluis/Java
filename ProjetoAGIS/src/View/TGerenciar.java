package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TGerenciar extends Application{
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		TabPane tabPane = (TabPane)FXMLLoader.load(this.getClass().getResource("TGerenciar.fxml"));
		Scene scn = new Scene(tabPane);	
		primaryStage.setScene(scn);
		primaryStage.getIcons().add(new Image(TGerenciar.class.getResourceAsStream("logoagis.PNG")));
		primaryStage.setTitle("Visão Geral");
		primaryStage.show();
	}
}
