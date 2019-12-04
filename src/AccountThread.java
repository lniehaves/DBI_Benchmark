import java.sql.*;

public class AccountThread extends Thread{

	String url = "localhost";
	String database = "dbi_benchmark";
	String user = "root";
	String pw = "root";
	private int n = 10;
	private int j;
	int k;
	
	public AccountThread(int j, int k)
	{
		this.j=j;
		this.k=k;
	}
	
	public void run()
	{
		
		Thread thread = Thread.currentThread();
		try {
			
			Connect connector = new Connect();
			connector.connectToDB(url, database, user, pw);
			Statement state = connector.connection.createStatement();
			state.executeQuery("SET FOREIGN_KEY_CHECKS=0");
			PreparedStatement pState = connector.connection.prepareStatement("Insert into accounts(accid,name,balance,branchid, address) VALUES(?,'aaaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");		
			for(int i = j;i<=k;i++)
			{
				pState.setInt(1, i);
				pState.setInt(2, (int)(Math.random()*n+1));
				pState.executeQuery();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
