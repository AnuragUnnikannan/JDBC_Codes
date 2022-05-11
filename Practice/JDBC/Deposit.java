import java.io.*;
import java.sql.*;
public class Deposit
{
    public static void main(String args[])throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
        Statement st = con.createStatement();
        try
        {
            System.out.println("Enter account number:");
            int accno = Integer.parseInt(br.readLine());
            System.out.println("Enter amount to be deposited:");
            int amt = Integer.parseInt(br.readLine());
            String query = "UPDATE accounts SET balance=balance+"+amt+" WHERE accno="+accno;
            st.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}