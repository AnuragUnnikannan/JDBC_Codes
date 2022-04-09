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
            con = DBConnection.getConnection("mydb", "system", "mysql");
            String query = "CREATE TABLE IF NOT EXISTS Accounts(accno int primary key, accname varchar(20), balance float)";
            DBConnection.executeChanges(query, con);
            DBConnection.DESC("Accounts", con);
            int initialID = DBConnection.generateID(con);
            for (int i = initialID; i < initialID + 2; i++)
            {
                System.out.println("Account No. "+i);
                System.out.println("Enter account name: ");
                String accname = br.readLine();
                System.out.println("Enter balance: ");
                float balance = Float.parseFloat(br.readLine());
                query = "INSERT INTO Accounts VALUES(" + i + ", '" + accname + "', " + balance + ")";
                DBConnection.executeChanges(query, con);
                
            }
            st = con.createStatement();
            DBConnection.printQueryResult(st.executeQuery("SELECT * FROM Accounts"));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}