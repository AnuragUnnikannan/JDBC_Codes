import java.io.*;
import java.sql.*;
public class StoreInfo
{
    static Connection con;
    static PreparedStatement ps;
    static Statement st;
    public static void main(String args[])throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            con = DBConnection.getConnection("mydb");
            int initialID = DBConnection.generateID();
            for (int i = initialID; i < initialID + 2; i++)
            {
                System.out.println("Account No. "+i);
                System.out.println("Enter account name: ");
                String accname = br.readLine();
                System.out.println("Enter balance: ");
                float balance = Float.parseFloat(br.readLine());
                String query = "INSERT INTO accounts VALUES(?, ?, ?)";
                ps = con.prepareStatement(query);
                ps.setInt(1, i);
                ps.setString(2, accname);
                ps.setFloat(3, balance);
                ps.executeUpdate();
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