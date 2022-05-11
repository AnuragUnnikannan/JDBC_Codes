import java.io.*;
import java.sql.*;
public class Bank
{
    static Connection con;
    static Statement st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void storeInfo()throws Exception
    {
        try
        {
            int initialID = DBConnection.generateID();
            System.out.println("Account No. "+initialID);
            System.out.println("Enter account name: ");
            String accname = br.readLine();
            System.out.println("Enter balance: ");
            float balance = Float.parseFloat(br.readLine());
            String query = "INSERT INTO accounts VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, initialID);
            ps.setString(2, accname);
            ps.setFloat(3, balance);
            ps.executeUpdate();
            st = con.createStatement();
            DBConnection.printQueryResult(st.executeQuery("SELECT * FROM accounts"));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static void closeAccount()
    {
        try
        {
            System.out.println("Enter account:");
            int accno = Integer.parseInt(br.readLine());
            String query = "DELETE FROM accounts WHERE accno="+accno;
            st = con.createStatement();
            DBConnection.executeChanges(query);
            DBConnection.printQueryResult(st.executeQuery("SELECT * FROM accounts"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void deposit()
    {
        try
        {
            System.out.println("Enter account number:");
            int accno = Integer.parseInt(br.readLine());
            System.out.println("Enter amount to be deposited:");
            float amt = Float.parseFloat(br.readLine());
            String query = "UPDATE accounts SET balance=balance+"+amt+" WHERE accno="+accno;
            DBConnection.executeChanges(query);
            st = con.createStatement();
            DBConnection.printQueryResult(st.executeQuery("SELECT * FROM accounts"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void checkInfo()
    {
        try
        {
            System.out.println("Enter account number:");
            int accno = Integer.parseInt(br.readLine());
            String query = "SELECT EXISTS(SELECT accname, balance FROM accounts WHERE accno="+accno+")";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int res = rs.getInt(1);
            if(res == 1)
            {
                query = "SELECT accname, balance FROM accounts WHERE accno="+accno;
                DBConnection.printQueryResult(st.executeQuery(query));
            }
            else
            {
                System.out.println("User does not exist");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void main(String args[])throws Exception
    {
        con = DBConnection.getConnection("mydb");
        while(true)
        {
            System.out.println("1. Store Info");
            System.out.println("2. Close Account");
            System.out.println("3. Deposit");
            System.out.println("4. Check Info");
            System.out.println("5. Exit");
            int choice = Integer.parseInt(br.readLine());
            switch(choice)
            {
                case 1:
                storeInfo();
                break;
                case 2:
                closeAccount();
                break;
                case 3:
                deposit();
                break;
                case 4:
                checkInfo();
                break;
                case 5:
                System.exit(0);
            }
        }
    }
}