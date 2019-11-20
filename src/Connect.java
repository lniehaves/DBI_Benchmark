import java.sql.*;

public class Connect {
	Connection connection;

	public boolean connectToDB(String host,String database,String user, String pw)
	{
		
		try {
			String connectionCommand = "jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+pw;
			connection = DriverManager.getConnection(connectionCommand);
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
			
		}
	
	}
	

}
