import java.io.*;
import java.sql.*;
public class CloseAccount
{
    static Connection con;
    static Statement st;
    public static void main(String args[])throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            con = DBConnection.getConnection("mydb", "system", "mysql");
            System.out.println("Enter account:");
            int accno = Integer.parseInt(br.readLine());
            String query = "DELETE FROM Accounts WHERE accno="+accno;
            st = con.createStatement();
            DBConnection.executeChanges(query, con);
            query = "SELECT * FROM Accounts";
            DBConnection.printQueryResult(st.executeQuery(query));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}