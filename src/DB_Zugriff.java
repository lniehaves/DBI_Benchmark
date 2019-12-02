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
		//connect to DB
		Connect connector = new Connect(); 
		System.out.println(connector.connectToDB(url, database, user, pw));
		state = connector.connection.createStatement();
		System.out.println("verbunden: "+ (System.currentTimeMillis()-t1)+" ms");
		
		//Lösche alte Daten
		
		deleteDaten();
		System.out.println("gelöscht: "+ (System.currentTimeMillis()-t1)+" ms");
		//Fülle mit neuen Daten
		connector.connection.setAutoCommit(false);
		//prepare statements
		PreparedStatement pStateBranches = connector.connection.prepareStatement("Insert into branches(branchid,branchname,balance,address) VALUES(?,'abcdefghijklmnopqrst',0,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsiudza')");
		PreparedStatement pStateAccounts = connector.connection.prepareStatement("Insert into accounts(accid,name,balance,branchid, address) VALUES(?,'aaaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");
		PreparedStatement pStateTellers = connector.connection.prepareStatement("Insert into tellers(tellerid,tellername,balance,branchid, address) VALUES(?,'taaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");

		//Zeit neu initialisieren
		t1 = System.currentTimeMillis();
		
		//Branches
		for(int i = 1; i<=n; i++)
		{
			pStateBranches.setInt(1, i);
			pStateBranches.addBatch();
		}
		pStateBranches.executeBatch();
		
		System.out.println("Branches Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
		
		//Accounts
		state.executeQuery("SET FOREIGN_KEY_CHECKS=0");
		for(int i = 1; i<=n*100000; i++)
		{
			pStateAccounts.setInt(1,i);
			pStateAccounts.setInt(2, (int)(Math.random()*n+1)); 
			pStateAccounts.addBatch();
		}
		pStateAccounts.executeBatch();

		System.out.println("Accounts Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
	
		//Teller

		for(int i = 1; i<=n*100000; i++)
		{
			pStateTellers.setInt(1,i);
			pStateTellers.setInt(2, (int)(Math.random()*n+1)); 
			pStateTellers.addBatch();
		}
		pStateTellers.executeBatch();
		
		//batches commiten
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
