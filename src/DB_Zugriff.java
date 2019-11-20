import java.sql.*;
public class DB_Zugriff {

	String url = "localhost";
	String database = "dbi";
	String user = "root";
	String pw = "root";
	
	public void main(String[] args) throws SQLException {
		
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
