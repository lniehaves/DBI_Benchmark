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
		connector.connection.setAutoCommit(false);
		PreparedStatement pState = connector.connection.prepareStatement("");
		String sql = "";
		t1 = System.currentTimeMillis();
		//Branches
		for(int i = 1; i<=n; i++)
		{
			pState = connector.connection.prepareStatement("Insert into branches(branchid,branchname,balance,address) VALUES(?,'abcdefghijklmnopqrst',0,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsiudza')");
			pState.setInt(1,i);
			pState.addBatch();
		}
		
		System.out.println("Branches Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
		
		
		//Accounts
		for(int i = 1; i<=n*1000; i++)
		{
			pState = connector.connection.prepareStatement("Insert into accounts(accid,name,balance,branchid, address) VALUES(?,'aaaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");
			pState.setInt(2, (int)(Math.random()*n+1)); 
			pState.setInt(1,i);
		
			pState.addBatch();
		}

		System.out.println("Accounts Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
	
		//Teller
		for(int i = 1; i<=n*1000; i++)
		{
			pState = connector.connection.prepareStatement("Insert into tellers(tellerid,tellername,balance,branchid, address) VALUES(?,'taaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");
			pState.setInt(2, (int)(Math.random()*n+1)); 
			pState.setInt(1,i);
			
			pState.addBatch();
		}
		pState.executeBatch();
		connector.connection.commit();

		System.out.println("Tellers Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
		
		//history
		
		System.out.println("Alle Tabellen Befüllt Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
				
		
		
		
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
