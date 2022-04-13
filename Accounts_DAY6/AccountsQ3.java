import java.sql.*;
public class AccountsQ3
{
    static Connection con;
    static Statement st;
    public static void main(String args[])throws Exception
    {
        try
        {
            con = DBConnection.getConnection("mydb");
            String query = "UPDATE accounts SET balance=balance+500";
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
