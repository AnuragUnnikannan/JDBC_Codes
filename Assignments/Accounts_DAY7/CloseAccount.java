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
            con = DBConnection.getConnection("mydb");
            System.out.println("Enter account:");
            int accno = Integer.parseInt(br.readLine());
            String query = "DELETE FROM accounts WHERE accno="+accno;
            st = con.createStatement();
            DBConnection.executeChanges(query);
            query = "SELECT * FROM accounts";
            DBConnection.printQueryResult(st.executeQuery(query));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}