import java.io.*;
import java.sql.*;
public class SearchAccount
{
    static Connection con;
    static Statement st;
    static ResultSet rs;
    public static void main(String args[])throws Exception
    {
        con = DBConnection.getConnection("mydb");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter account number:");
        int accno = Integer.parseInt(br.readLine());
        String query = "SELECT EXISTS(SELECT * FROM accounts WHERE accno="+accno+")";
        st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int res = rs.getInt(1);
        if(res == 1)
        {
            query = "SELECT * FROM accounts WHERE accno="+accno;
            DBConnection.printQueryResult(st.executeQuery(query));
        }
        else
        {
            System.out.println("User does not exists");
        }
    }
}