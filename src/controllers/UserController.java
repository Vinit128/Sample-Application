package controllers;

import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import model.UserModel;
 
public class UserController implements Initializable {
	
	static int userid;
	UserModel cm;
	
	/***** TABLEVIEW intel *********************************************************************/

	@FXML
	private TableView<UserModel> tblAccounts;
	@FXML
	private TableColumn<UserModel, String> tid;
	@FXML
	private TableColumn<UserModel, String> balance;

	public void initialize(URL location, ResourceBundle resources) {
		tid.setCellValueFactory(new PropertyValueFactory<UserModel, String>("FoodItem"));
		balance.setCellValueFactory(new PropertyValueFactory<UserModel, String>("Price"));

		// auto adjust width of columns depending on their content
		tblAccounts.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblAccounts));

		tblAccounts.setVisible(false); // set invisible initially
	}

    public void customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }
    
	public void viewAccounts() throws IOException {

		tblAccounts.getItems().setAll(cm.getFoodItems(userid)); // load table data from UserModel List
		tblAccounts.setVisible(true); // set tableview to visible if not
		
		System.out.println(cm.getClientInfo());

	}

	/***** End TABLEVIEW intel *********************************************************************/

	public void logout() {
		// System.exit(0);
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("/resources/Loginpage.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/application/styles.css").toExternalForm());
			Main.stage_x.setScene(scene);
			Main.stage_x.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public void createTransaction() {

		TextInputDialog dialog = new TextInputDialog("Enter dollar amount");
		dialog.setTitle("Bank Account Entry Portal");
		dialog.setHeaderText("Enter Transaction");
		dialog.setContentText("Please enter your balance:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("Balance entry: " + result.get());
			cm.insertRecord(userid,Double.parseDouble(result.get()));
		}

		// The Java 8 way to get the response value (with lambda expression).
		result.ifPresent(balance -> System.out.println("Balance entry: " + balance));

	}

	public static void setUserid(int user_id) {
		userid = user_id;
		System.out.println("Welcome id " + userid);
	}

	public UserController() {

		/*
		 * Alert alert = new Alert(AlertType.INFORMATION);
		 * alert.setTitle("From Customer controller");
		 * alert.setHeaderText("Bank Of IIT- Chicago Main Branch");
		 * alert.setContentText("Welcome !"); alert.showAndWait();
		 */

		cm = new UserModel();

	}

}
