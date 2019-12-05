import java.sql.*;

public class AccountThread extends Thread{

	String url = "192.168.122.70";
	String database = "dbi_benchmark";
	String user = "root";
	String pw = "root";
	private int n = 10;
	private int oberGrenze;
	private int unterGrenze;
	static int threads = 100;
	static int counter = 0;
	long t1;
	
	public AccountThread(int j, int k)
	{
		this.unterGrenze=j;
		this.oberGrenze=k;
		t1 = System.currentTimeMillis();
		
	}
	
	public void run()
	{
		try {
			
			Connect connector = new Connect();
			connector.connectToDB(url, database, user, pw);
			Statement state = connector.connection.createStatement();
			state.executeQuery("SET FOREIGN_KEY_CHECKS=0");
			PreparedStatement pState = connector.connection.prepareStatement("Insert into accounts(accid,name,balance,branchid, address) VALUES(?,'aaaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");		
			for(int i = unterGrenze;i<=oberGrenze;i++)
			{
				pState.setInt(1, i);
				pState.setInt(2, (int)(Math.random()*n+1));
				pState.addBatch();	
			}
			
			pState.executeBatch();
			connector.connection.commit();
			counter++;
			
			if (counter == threads) {
				System.out.println("Alle Tabellen befüllt: "+ (System.currentTimeMillis()-t1)+" ms");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
