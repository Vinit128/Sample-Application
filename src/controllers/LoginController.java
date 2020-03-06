package controllers;
import java.sql.SQLException;

import application.Main;
import dao.DBConnect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class LoginController {

	@FXML
	private TextField emailIdField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button submitButton;

	@FXML
	public void login(ActionEvent event) throws SQLException {

		Window owner = submitButton.getScene().getWindow();

		/*
		 * System.out.println(emailIdField.getText());
		 * System.out.println(passwordField.getText());
		 */

		if (emailIdField.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
					"Please enter your email id");
			return;
		}
		if (passwordField.getText().isEmpty()) {
			showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
					"Please enter a password");
			return;
		}

		String emailId = emailIdField.getText();
		System.out.println("E"+emailId);
		String password = passwordField.getText();
		System.out.println("P"+password);		

		System.out.println("reached db details");

		DBConnect dbConnect = new DBConnect();

		System.out.println("reached out of db details");
		
		try {
		boolean flag = dbConnect.validate(emailId, password);
		System.out.println("reached inside validation");

		if (!flag) { 
			infoBox("Please enter correct Email and Password", null,"Failed"); 
		} else { 
			AnchorPane root;
			if(dbConnect.adminStatus == 10) {
				//Admin
				System.out.print("Admin Page");
				//infoBox("Login Successful Admin Page!", null, "Success");
				//FXMLLoader.load(getClass().getResource("/Resources/Loginpage.fxml"));
				//root = (AnchorPane) FXMLLoader.load(getClass().getResource("/Resources/Loginpage.fxml"));
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
				//Main.stage.setTitle("Admin View");
				//Main.stage.setTitle("Admin View");
				Main.stage_x.setTitle("Admin View");
			}else {
				//User
				System.out.print("User Page");
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ClientView.fxml"));
				Main.stage_x.setTitle("Client View");
				//infoBox("Login Successful! User Page", null, "Success");
			}
			Scene scene = new Scene(root);
			Main.stage_x.setScene(scene);
		}
		System.out.println("reached out of validation");}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText(infoMessage);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.showAndWait();
	}

	private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
}