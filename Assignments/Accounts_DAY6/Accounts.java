import java.io.*;
import java.sql.*;

public class Accounts
{
    static Statement st;
    static Connection con;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            con = DBConnection.getConnection("mydb");
            String query = "CREATE TABLE IF NOT EXISTS accounts(accno int primary key, accname varchar(20), balance float)";
            DBConnection.executeChanges(query);
            DBConnection.DESC("accounts");
            int initialID = DBConnection.generateID();
            for (int i = initialID; i < initialID + 2; i++)
            {
                System.out.println("Account No. "+i);
                System.out.println("Enter account name: ");
                String accname = br.readLine();
                System.out.println("Enter balance: ");
                float balance = Float.parseFloat(br.readLine());
                query = "INSERT INTO accounts VALUES(" + i + ", '" + accname + "', " + balance + ")";
                DBConnection.executeChanges(query);
                
            }
            st = con.createStatement();
            DBConnection.printQueryResult(st.executeQuery("SELECT * FROM accounts"));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}