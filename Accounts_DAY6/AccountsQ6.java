import java.io.*;
import java.sql.*;
public class AccountsQ6
{
    static Connection con;
    static Statement st;
    public static void main(String[] args)throws Exception
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            con = DBConnection.getConnection("mydb");
            System.out.println("Enter account number:");
            int accno = Integer.parseInt(br.readLine());
            System.out.println("Enter amount to be deposited:");
            float amt = Float.parseFloat(br.readLine());
            String query = "UPDATE accounts SET balance=balance+"+amt+" WHERE accno="+accno;
            DBConnection.executeChanges(query);
            st = con.createStatement();
            DBConnection.printQueryResult(st.executeQuery("SELECT * FROM accounts"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}