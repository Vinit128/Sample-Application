package controllers;

import java.sql.SQLException;
import java.sql.Statement;

import application.DynamicTable;
import dao.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AdminController {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtName2;
	@FXML
	private TextField txtPrice;
	@FXML
	private TextField txtID;
	
	

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public AdminController() {
		conn = new DBConnect();
	}

	public void viewAccounts() {

		DynamicTable d = new DynamicTable();
		// call method from DynamicTable class and pass some arbitrary query string
		d.buildData("Select tid,balance from vpatel_menu");
	}

	public void updateRec() {

		pane3.setVisible(false);
		pane2.setVisible(false);
		pane1.setVisible(true);

	}

	public void deleteRec() {

		pane1.setVisible(false);
		pane2.setVisible(true);
		pane3.setVisible(false);
	}

	public void addBankRec() {

		pane1.setVisible(false);
		pane2.setVisible(false);
		pane3.setVisible(true);

	}

	public void submitFoodItem() {

		// INSERT INTO FOOD TABLE
		try {
			// Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include all object data to the database table

			sql = "insert into vpatel_menu(Item,Price) values ('" + txtName.getText() + "','" + txtAddress.getText()
					+ "')";
			stmt.executeUpdate(sql);
			System.out.println("Added item to the menu");

			conn.getConnection().close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void submitUpdateFoodItem() {
		//UPDATE `vpatel_menu` SET `Item` = 'uio', `Price` = '99' WHERE `vpatel_menu`.`ID` = 25;
		System.out.print("I am updating menu");
		// INSERT INTO FOOD TABLE
				try {
					// Execute a query
					System.out.println("Inserting records into the table...");
					stmt = conn.getConnection().createStatement();
					String sql = null;

					// Include all object data to the database table

					sql = "UPDATE `vpatel_menu` SET `Price` = "+txtPrice.getText()+" WHERE `vpatel_menu`.`Item` = '"+txtName2.getText()+"';"; 
					//sql = "insert into vpatel_menu(Item,Price) values ('" + txtName2.getText() + "','" + txtPrice.getText() + "')";
					stmt.executeUpdate(sql);
					System.out.println("Added item to the menu");

					conn.getConnection().close();
				} catch (SQLException se) {
					se.printStackTrace();
				}

	}

	public void submitDeleteFoodItem() {
		
			System.out.print("I am delete menu");
				
			try {
				// Execute a query
				System.out.println("Deleting records into the table...");
				stmt = conn.getConnection().createStatement();
				String sql = null;
				// Include all object data to the database table

				sql = "DELETE FROM `vpatel_menu` WHERE `vpatel_menu`.`ID` = "+txtID.getText()+""; 
				stmt.executeUpdate(sql);
				System.out.println("Deleting item to the menu");
				conn.getConnection().close();
				} catch (SQLException se) {
					se.printStackTrace();
			}
	}

}
