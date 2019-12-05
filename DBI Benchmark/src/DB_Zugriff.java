import java.sql.*;
public class DB_Zugriff {

	static String url = "192.168.122.70";
	static String database = "dbi_benchmark";
	static String user = "root";
	static String pw = "root";
	static int n = 10;
	static int tLimit = 100;
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
		
		//starte befuellen
		connector.connection.setAutoCommit(false);
		PreparedStatement pStateBranches = connector.connection.prepareStatement("Insert into branches(branchid,branchname,balance,address) VALUES(?,'abcdefghijklmnopqrst',0,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsiudza')");
		PreparedStatement pStateTellers = connector.connection.prepareStatement("Insert into tellers(tellerid,tellername,balance,branchid, address) VALUES(?,'taaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");
		state.executeQuery("SET FOREIGN_KEY_CHECKS=0");
		
		//Zeit neu initialisieren
		t1 = System.currentTimeMillis();
		

		//Branches befüllen
		for(int i = 1; i<=n; i++)
		{
			pStateBranches.setInt(1,i);
			pStateBranches.addBatch();
		}
		pStateBranches.executeBatch();
		System.out.println("Branches Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
		
		
		
		
		//Accounts befüllen	
		try {
			
			for (int i = 0; i <= tLimit; i++) {
				AccountThread aT =  new AccountThread(n*100000/tLimit*i+1, n*100000/tLimit*(i+1));
				aT.start();
			}
			
			
//			AccountThread aT1 = new AccountThread(1,n*100000/40*1);
//			AccountThread aT2 = new AccountThread(n*100000/40*1+1,n*100000/40*2);
//			AccountThread aT3 = new AccountThread(n*100000/40*2+1,n*100000/40*3);
//			AccountThread aT4 = new AccountThread(n*100000/40*3+1,n*100000/40*4);
//			AccountThread aT5 = new AccountThread(n*100000/40*4+1,n*100000/40*5);
//			AccountThread aT6 = new AccountThread(n*100000/40*5+1,n*100000/40*6);
//			AccountThread aT7 = new AccountThread(n*100000/40*6+1,n*100000/40*7);
//			AccountThread aT8 = new AccountThread(n*100000/40*7+1,n*100000/40*8);		
//			AccountThread aT9 = new AccountThread(n*100000/40*8+1,n*100000/40*9);
//			AccountThread aT10 = new AccountThread(n*100000/40*9+1,n*100000/40*10);
//			AccountThread aT11 = new AccountThread(n*100000/40*10+1,n*100000/40*11);
//			AccountThread aT12 = new AccountThread(n*100000/40*11+1,n*100000/40*12);
//			AccountThread aT13 = new AccountThread(n*100000/40*12+1,n*100000/40*13);
//			AccountThread aT14 = new AccountThread(n*100000/40*13+1,n*100000/40*14);
//			AccountThread aT15 = new AccountThread(n*100000/40*14+1,n*100000/40*15);
//			AccountThread aT16 = new AccountThread(n*100000/40*15+1,n*100000/40*16);		
//			AccountThread aT17 = new AccountThread(n*100000/40*16+1,n*100000/40*17);
//			AccountThread aT18 = new AccountThread(n*100000/40*17+1,n*100000/40*18);
//			AccountThread aT19 = new AccountThread(n*100000/40*18+1,n*100000/40*19);
//			AccountThread aT20 = new AccountThread(n*100000/40*19+1,n*100000/40*20);		
//			AccountThread aT21 = new AccountThread(n*100000/40*20+1,n*100000/40*21);
//			AccountThread aT22 = new AccountThread(n*100000/40*21+1,n*100000/40*22);
//			AccountThread aT23 = new AccountThread(n*100000/40*22+1,n*100000/40*23);
//			AccountThread aT24 = new AccountThread(n*100000/40*23+1,n*100000/40*24);
//			AccountThread aT25 = new AccountThread(n*100000/40*24+1,n*100000/40*25);
//			AccountThread aT26 = new AccountThread(n*100000/40*25+1,n*100000/40*26);
//			AccountThread aT27 = new AccountThread(n*100000/40*26+1,n*100000/40*27);
//			AccountThread aT28 = new AccountThread(n*100000/40*27+1,n*100000/40*28);
//			AccountThread aT29 = new AccountThread(n*100000/40*28+1,n*100000/40*29);
//			AccountThread aT30 = new AccountThread(n*100000/40*29+1,n*100000/40*30);
//			
//			AccountThread aT31 = new AccountThread(n*100000/40*30+1,n*100000/40*31);
//			AccountThread aT32 = new AccountThread(n*100000/40*31+1,n*100000/40*32);
//			AccountThread aT33 = new AccountThread(n*100000/40*32+1,n*100000/40*33);
//			AccountThread aT34 = new AccountThread(n*100000/40*33+1,n*100000/40*34);
//			AccountThread aT35 = new AccountThread(n*100000/40*34+1,n*100000/40*35);
//			AccountThread aT36 = new AccountThread(n*100000/40*35+1,n*100000/40*36);
//			AccountThread aT37 = new AccountThread(n*100000/40*36+1,n*100000/40*37);
//			AccountThread aT38 = new AccountThread(n*100000/40*37+1,n*100000/40*38);
//			AccountThread aT39 = new AccountThread(n*100000/40*38+1,n*100000/40*39);
//			AccountThread aT40 = new AccountThread(n*100000/40*39+1,n*100000/40*40);
//			
//			aT1.start();
//			aT2.start();
//			aT3.start();
//			aT4.start();
//			aT5.start();
//			aT6.start();
//			aT7.start();
//			aT8.start();
//			aT9.start();
//			aT10.start();
//			aT11.start();
//			aT12.start();
//			aT13.start();
//			aT14.start();
//			aT15.start();
//			aT16.start();
//			aT17.start();
//			aT18.start();
//			aT19.start();
//			aT20.start();
//			aT21.start();
//			aT22.start();
//			aT23.start();
//			aT24.start();
//			aT25.start();
//			aT26.start();
//			aT27.start();
//			aT28.start();
//			aT29.start();
//			aT30.start();	
//			aT31.start();
//			aT32.start();
//			aT33.start();
//			aT34.start();
//			aT35.start();
//			aT36.start();
//			aT37.start();
//			aT38.start();
//			aT39.start();
//			aT40.start();
			
		
			
		}
		catch (Exception e) {
			e.printStackTrace(); 
		}
	
		//Teller befüllen
		for(int i = 1; i<=n*10; i++)
		{
			int zahl = (int)(Math.random()*n+1);
			pStateTellers.setInt(2, (int)(Math.random()*n+1)); 
			pStateTellers.setInt(1,i);
			pStateTellers.addBatch();
		}
		pStateTellers.executeBatch();
		System.out.println("Tellers Befüllt: "+ (System.currentTimeMillis()-t1)+" ms");

	
		//batches commiten

		connector.connection.commit();
		
		
				
		
		
		
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
