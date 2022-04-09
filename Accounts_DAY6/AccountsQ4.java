import java.sql.*;
public class AccountsQ4
{
    static Connection con;
    static Statement st;
    public static void main(String[] args)throws Exception
    {
        try
        {
            con = DBConnection.getConnection("mydb", "system", "mysql");
            String query = "DELETE FROM Accounts WHERE balance < 10000";
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