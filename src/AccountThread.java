import java.sql.*;

public class AccountThread implements Runnable{

	String url = "localhost";
	String database = "dbi_benchmark";
	String user = "root";
	String pw = "root";
	int n = 10;
	
	public void run()
	{
		
		Thread thread = Thread.currentThread();
		try {
			
			Connect connector = new Connect();
			connector.connectToDB(url, database, user, pw);
			
			
			PreparedStatement pState = connector.connection.prepareStatement("Insert into accounts(accid,name,balance,branchid, address) VALUES(?,'aaaaaaaaaaaaaaaaaaaa',0,?,'tdduzstiduzsidgsdiucgoxyuiztcyxhgcoisazdioasgduzgasIUAgsiuaTSIUDatsi')");
			for(int i =1 ;i<=n*1000;i++)
			{
//				System.out.println(thread+""+ i);
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
