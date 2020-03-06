package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {

	
	// Replace below database url, username and password with your actual database credentials
	private static final String DATABASE_URL = "jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useSSL=false";
	private static final String DATABASE_USERNAME = "fp510";
	private static final String DATABASE_PASSWORD = "510";
	private static final String SELECT_QUERY = "SELECT * FROM vpatel_admin WHERE email_id = ? and password = ?";
	private static final String SELECT_QUERY1 = "SELECT * FROM vpatel_adminview WHERE email_id = ? and password = ? ";
	public int adminStatus = 0;
	//Some tests 
	protected Connection connection;
	private static String url = "jdbc:mysql://www.papademas.net:3307/510fp";
	private static String username = "fp510";
	private static String password = "510";

	public boolean validate(String emailId, String password) throws SQLException {
		adminStatus = 0;
		// Step 1: Establishing a Connection and 
		// try-with-resource statement will auto close the connection.
		Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);           
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY); 
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, password);

			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				adminStatus = resultSet.getInt("admin");
				return true;
			}
		return false;
	}
	/*
	 * String query = "SELECT * FROM vpatel_admin WHERE uname = ? and passwd = ?;";
            try(PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()) { 
                 
                	setId(rs.getInt("id"));
                	setAdmin(rs.getBoolean("admin"));
                	return true;
               	}
             }catch (SQLException e) {
            	e.printStackTrace();   
             }
			return false;
	 */

	/*public boolean getCredentials(String emailId, String password) throws SQLException {

		// Step 1: Establishing a Connection and 
		// try-with-resource statement will auto close the connection.
		Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);           
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY1); 
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, password);

			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		return false;
	}*/

	public static void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
		
	public Connection getConnection() {
		return connection;
	}

	public DBConnect() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
	}
}