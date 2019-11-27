import java.sql.*;
public class DB_Zugriff {

	static String url = "localhost";
	static String database = "dbi_benchmark";
	static String user = "root";
	static String pw = "root";
	static int n = 10;
	static Statement state;
	
	
	
	public static void main(String[] args) throws SQLException {
		
		
		
		//Starte run
		double t1 = System.currentTimeMillis();
		Connect connector = new Connect(); 
		System.out.println(connector.connectToDB(url, database, user, pw));
		state = connector.connection.createStatement();
		System.out.println("verbunden: "+ (System.currentTimeMillis()-t1)+" ms");
		
		//Lösche alte Daten
		
		deleteDaten();
		System.out.println("gelöscht: "+ (System.currentTimeMillis()-t1)+" ms");
		//Fülle mit neuen Daten
		//Branches
		for(int i = 1; i<=n; i++)
		{
			state.executeQuery("Insert into branches(branchid,branchname,balance,address) VALUES('"+i+"','abcdefghijklmnopqrst',0,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsiudza')");
		}
		
		//Accounts
		for(int i = 1; i<=n*100000; i++)
		{
			state.executeQuery("Insert into accounts(accid,name,balance,branchid, address) VALUES('"+i+"','aaaaaaaaaaaaaaaaaaaa',0,'"+((int)Math.random()*n+1)+"','tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");
		}
		System.out.println("Accounts Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
		
	}
	
	public static void deleteDaten()
	{
		try {
			state.executeUpdate("DELETE FROM history");
			state.executeUpdate("DELETE FROM accounts");
			state.executeUpdate("DELETE FROM tellers");
			state.executeUpdate("DELETE FROM branches");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
