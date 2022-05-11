import java.io.*;
import java.sql.*;
public class Balance
{
    public static void main(String args[])throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
        try
        {
            System.out.println("Enter account no.:");
            int accno = Integer.parseInt(br.readLine());
            String query = "SELECT COUNT(accno) FROM accounts WHERE accno="+accno;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            if(rs.getInt(1) == 0)
            {
                System.out.println("Account does not exist");
            }
            else
            {
                query = "SELECT * FROM accounts WHERE accno="+accno;
                st = con.createStatement();
                rs = st.executeQuery(query);
                rs.next();
                System.out.println("Balance: Rs. "+rs.getInt(3));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Connection success");
    }
}