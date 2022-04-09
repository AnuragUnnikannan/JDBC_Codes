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
            con = DBConnection.getConnection("mydb", "system", "mysql");
            System.out.println("Enter account number:");
            int accno = Integer.parseInt(br.readLine());
            System.out.println("Enter amount to be deposited:");
            float amt = Float.parseFloat(br.readLine());
            String query = "UPDATE Accounts SET balance=balance+"+amt+" WHERE accno="+accno;
            DBConnection.executeChanges(query, con);
            st = con.createStatement();
            DBConnection.printQueryResult(st.executeQuery("SELECT * FROM ACCOUNTS"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}