import java.sql.*;
public class AccountsQ4
{
    static Connection con;
    static Statement st;
    public static void main(String[] args)throws Exception
    {
        try
        {
            con = DBConnection.getConnection("mydb");
            String query = "DELETE FROM accounts WHERE balance < 10000";
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