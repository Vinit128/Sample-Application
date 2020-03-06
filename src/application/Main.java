package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	public static Stage stage_x;
	@Override
	public void start(Stage stage) throws Exception {
		stage_x = stage;
		try {
		System.out.println(getClass());
		Parent root = FXMLLoader.load(getClass().getResource("/Resources/Loginpage.fxml"));
		stage.setTitle("User Login");
		stage.setScene(new Scene(root, 800, 500));
		stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void MID(Stage stage) throws Exception {
		try {
		System.out.println(getClass());
		Parent root = FXMLLoader.load(getClass().getResource("/Resources/Adminpage.fxml"));
		stage.setTitle("Admin Login");
		stage.setScene(new Scene(root, 800, 500));
		stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
