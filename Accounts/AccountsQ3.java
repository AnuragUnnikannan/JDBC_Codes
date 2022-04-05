import java.lang.Thread.State;
import java.sql.*;
public class AccountsQ3
{
    static Connection con;
    static Statement st;
    public static void main(String args[])throws Exception
    {
        try
        {
            con = DBConnection.getConnection("mydb", "system", "mysql");
            String query = "UPDATE Accounts SET balance=balance+500";
            DBConnection.executeChanges(query, con);
            st = con.createStatement();
            DBConnection.printQueryResult(st.executeQuery("SELECT * FROM Accounts"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}