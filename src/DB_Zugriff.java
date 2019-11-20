import java.sql.*;
public class DB_Zugriff {

	static String url = "localhost";
	static String database = "dbi";
	static String user = "root";
	static String pw = "root";
	
	public static void main(String[] args) throws SQLException {
		
		Connect connector = new Connect();
		System.out.println(connector.connectToDB(url, database, user, pw));
		Statement state = connector.connection.createStatement();
		ResultSet rs = state.executeQuery("Select * FROM agents");
		while(rs.next())
		{
			System.out.println(rs.getString(1));
		}
		
	}

}
