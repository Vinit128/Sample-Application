package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.DBConnect;

public class UserModel extends dao.DBConnect implements User<Bank>{

	private int cid;
	private String foodItem;
	private String Price;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;
	
	Bank custBank; //set up Bank object

	public UserModel() {

		conn = new DBConnect();
		
		//simulate bank data affliation of client
		custBank = new Bank();
		custBank.setBankId(100);
		custBank.setBankName("Bank of IIT");
		custBank.setBankAddress("10 W 35th St, Chicago, IL 60616");
	}
 
	/* getters & setters */
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setFoodItem(String fi) {
		this.foodItem = fi;
	}

	public String getFoodItem() {
		return foodItem;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		this.Price = price;
	}

	// INSERT INTO METHOD
	public void insertRecord(int cid, double bal) {

		try {
			setCid(cid);
			// Execute a query
			System.out.println("Inserting record into the table...");
			stmt = conn.getConnection().createStatement();
			String sql = null;

			// Include data to the database table

			sql = " insert into jpapa_accounts(cid, balance) values('" + cid + "', '" + bal + "')";

			stmt.executeUpdate(sql);
			conn.getConnection().close();

			System.out.println("Balance inserted $" + bal + " for ClientID " + cid);

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public List<UserModel> getFoodItems(int cid) {
		List<UserModel> fooditems = new ArrayList<>();
		String query = "SELECT FoodItem,Price FROM vpatel_products;"; //tid, ba;ance
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			//statement.setInt(1, cid);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserModel food = new UserModel();
				// grab record data by table field name into ClientModel account object
				food.setFoodItem(resultSet.getString("FoodItem"));
				food.setPrice(resultSet.getString("Price"));
				fooditems.add(food); // add account data to arraylist
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Accounts: " + e);
		}
		return fooditems; // return arraylist
	}

	@Override
	public Bank getClientInfo() {
		// TODO Auto-generated method stub
		return custBank;
	}
}