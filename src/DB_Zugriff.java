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
		String sql = "";
		t1 = System.currentTimeMillis();
		//Branches
		for(int i = 1; i<=n; i++)
		{
			sql = "Insert into branches(branchid,branchname,balance,address) VALUES('"+i+"','abcdefghijklmnopqrst',0,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsiudza')";
			state.addBatch(sql);
		}
		state.executeBatch();
		connector.connection.commit();
		state.clearBatch();
		sql = "";
		System.out.println("Branches Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
		
		
		//Accounts
		for(int i = 1; i<=n*1000; i++)
		{
			if(n%10000 == 0)
			{
				System.out.println(n);
			}
			int zahl = (int)(Math.random()*n+1);
			sql="Insert into accounts(accid,name,balance,branchid, address) VALUES('"+i+"','aaaaaaaaaaaaaaaaaaaa',0,'"+zahl+"','tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')";
			state.addBatch(sql);
		}
		state.executeBatch();
		connector.connection.commit();
		state.clearBatch();
		sql = "";
		System.out.println("Accounts Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
	
		//Teller
		for(int i = 1; i<=n*1000; i++)
		{
			int zahl = (int)(Math.random()*n+1);
			
			sql="Insert into tellers(tellerid,tellername,balance,branchid, address) VALUES('"+i+"','taaaaaaaaaaaaaaaaaaa',0,'"+zahl+"','tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')";
			state.addBatch(sql);
		}
		state.executeBatch();
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
