import java.io.*;
import java.sql.*;
public class FundTransfer
{
    static Connection con;
    static Statement st;
    public static boolean checkExistence(int accno)throws Exception
    {
        String query = "SELECT COUNT(accno) FROM accounts WHERE accno="+accno;
        st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        if(rs.getInt(1) == 0)
        {
            System.out.println("Account does not exist");
            return false;
        }
        return true;
    }
    public static void main(String args[])throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "oracle");
        con.setAutoCommit(false);
        try
        {
            System.out.println("Enter account number:");
            int srcAccno = Integer.parseInt(br.readLine());
            if(checkExistence(srcAccno) == false)
            {
                System.out.println("Account number does not exist");
                System.exit(0);
            }
            else
            {
                System.out.println("Enter amount:");
                int amt = Integer.parseInt(br.readLine());
                String query = "SELECT balance FROM accounts WHERE accno="+srcAccno;
                ResultSet rs = st.executeQuery(query);
                rs.next();
                System.out.println(rs.getInt(1));
                if(amt > rs.getInt(1))
                {
                    System.out.println("Insufficient balance");
                    System.exit(0);
                }
                else
                {
                    query = "UPDATE accounts SET balance=balance-"+amt+"WHERE accno="+srcAccno;
                    st.executeUpdate(query);
                    System.out.println("Enter destination account number:");
                    int destAccno = Integer.parseInt(br.readLine());
                    if(checkExistence(destAccno) == false)
                    {
                        System.out.println("Account does not exist");
                        //con.rollback();
                        System.exit(0);
                    }
                    else
                    {
                        query = "SELECT balance FROM accounts WHERE accno="+destAccno;
                        rs = st.executeQuery(query);
                        rs.next();
                        int oldBalance = rs.getInt(1);
                        query = "UPDATE accounts SET balance=balance+"+amt+" WHERE accno="+destAccno;
                        st.executeUpdate(query);
                        query = "SELECT balance FROM accounts WHERE accno="+destAccno;
                        rs = st.executeQuery(query);
                        rs.next();
                        int newBalance = rs.getInt(1);
                        if(oldBalance + amt == newBalance)
                        {
                            con.commit();
                            System.out.println("Transaction Success");
                        }
                        else
                        {
                            con.rollback();
                            System.out.println("Transaction failed");
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}