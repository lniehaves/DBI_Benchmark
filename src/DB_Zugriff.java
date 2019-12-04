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

		
		//connect to DB

		Connect connector = new Connect(); 
		System.out.println(connector.connectToDB(url, database, user, pw));
		state = connector.connection.createStatement();
		System.out.println("verbunden: "+ (System.currentTimeMillis()-t1)+" ms");
		
		//Lösche alte Daten
		deleteDaten();
		System.out.println("gelöscht: "+ (System.currentTimeMillis()-t1)+" ms");
		
		//starte befuellen
		connector.connection.setAutoCommit(false);
		PreparedStatement pState = connector.connection.prepareStatement("");
		String sql = "";

		
		//Zeit neu initialisieren
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
		state.executeQuery("SET FOREIGN_KEY_CHECKS=0");
		
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
		
		


		System.out.println("Accounts Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
	
		//Teller

		


		/*
		for(int i = 1; i<=n*100000; i++)
		{

		}
		System.out.println("Tellers Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
		*/
		
		System.out.println("Alle Batches executet "+ (System.currentTimeMillis()-t1)+" ms");
		//batches commiten

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
