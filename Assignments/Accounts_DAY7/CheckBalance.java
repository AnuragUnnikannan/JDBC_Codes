import java.sql.*;
public class CheckBalance
{
    static Connection con;
    static Statement st;
    public static void main(String args[])
    {
        try
        {
            con = DBConnection.getConnection("mydb");
            String query = "SELECT EXISTS(SELECT accname, balance FROM accounts WHERE balance>5000)";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int res = rs.getInt(1);
            if(res == 1)
            {
                query = "SELECT accname, balance FROM accounts WHERE balance>5000";
                DBConnection.printQueryResult(st.executeQuery(query));
            }
            else
            {
                System.out.println("No user has more than balance of Rs. 5000");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
