import java.sql.*;
public class DB_Zugriff {

	static String url = "localhost";
	static String database = "dbi_benchmark";
	static String user = "root";
	static String pw = "root";
	static int n = 20;
	static int threads = 10;
	static Statement state;
	
	//Threads fuer account
	
	
	
	
	public static void main(String[] args) throws SQLException {
		
		
		
		//Starte run
		double t1 = System.currentTimeMillis();
<<<<<<< Updated upstream
=======
		
		//connect to DB
>>>>>>> Stashed changes
		Connect connector = new Connect(); 
		System.out.println(connector.connectToDB(url, database, user, pw));
		state = connector.connection.createStatement();
		System.out.println("verbunden: "+ (System.currentTimeMillis()-t1)+" ms");
		
		//Lösche alte Daten
		deleteDaten();
		System.out.println("gelöscht: "+ (System.currentTimeMillis()-t1)+" ms");
		
		//starte befuellen
		connector.connection.setAutoCommit(false);
<<<<<<< Updated upstream
		PreparedStatement pState = connector.connection.prepareStatement("");
		String sql = "";
=======
		
		//Zeit neu initialisieren
>>>>>>> Stashed changes
		t1 = System.currentTimeMillis();
		//Branches
		for(int i = 1; i<=n; i++)
		{
<<<<<<< Updated upstream
			pState = connector.connection.prepareStatement("Insert into branches(branchid,branchname,balance,address) VALUES(?,'abcdefghijklmnopqrst',0,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsiudza')");
			pState.setInt(1,i);
			pState.addBatch();
		}
		
=======
			state.executeQuery("Insert into branches(branchid,branchname,balance,address) VALUES('"+i+"','abcdefghijklmnopqrst',0,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsiudza')");
		}
>>>>>>> Stashed changes
		System.out.println("Branches Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
		
		
		//Accounts
<<<<<<< Updated upstream
		for(int i = 1; i<=n*1000; i++)
		{
			pState = connector.connection.prepareStatement("Insert into accounts(accid,name,balance,branchid, address) VALUES(?,'aaaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");
			pState.setInt(2, (int)(Math.random()*n+1)); 
			pState.setInt(1,i);
		
			pState.addBatch();
		}
=======
		try {
			AccountThread aT1 = new AccountThread();
			 Thread zwei = new Thread(aT1,"zwei");
			Thread drei = new Thread(aT1,"drei");
			Thread vier = new Thread(aT1,"vier");
			Thread funf = new Thread(aT1,"funf");
			Thread sechs = new Thread(aT1,"sechs");
			Thread sieben = new Thread(aT1,"sieben");
			Thread acht = new Thread(aT1,"acht");
			
			state.executeQuery("SET FOREIGN_KEY_CHECKS=0");
			zwei.start();
			drei.start();
			vier.start();
			funf.start();
			sechs.start();
			sieben.start();
			acht.start();
			aT1.run();
		}
		catch (Exception e) {
			e.printStackTrace(); 
		}
		
		
>>>>>>> Stashed changes

		System.out.println("Accounts Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
	
		//Teller
<<<<<<< Updated upstream
		for(int i = 1; i<=n*1000; i++)
		{
			pState = connector.connection.prepareStatement("Insert into tellers(tellerid,tellername,balance,branchid, address) VALUES(?,'taaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");
			pState.setInt(2, (int)(Math.random()*n+1)); 
			pState.setInt(1,i);
			
			pState.addBatch();
		}
		pState.executeBatch();
=======
		/*
		for(int i = 1; i<=n*100000; i++)
		{

		}
		System.out.println("Tellers Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
		*/
		
		System.out.println("Alle Batches executet "+ (System.currentTimeMillis()-t1)+" ms");
		//batches commiten
>>>>>>> Stashed changes
		connector.connection.commit();

		
		
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
