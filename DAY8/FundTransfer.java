import java.util.*;
import java.sql.*;
public class FundTransfer
{
    public static boolean checkExistence(int accno)throws Exception
    {
        ResultSet rs = DBConnection.select("SELECT EXISTS(SELECT * FROM accounts WHERE accno="+accno+")");
        rs.next();
        if(rs.getInt(1) == 0)
        {
            System.out.println("Account does not exist");
            return false;
        }
        return true;
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection("mydb");
        con.setAutoCommit(false);
        System.out.println("Enter your account number:");
        int srcAccno = sc.nextInt();
        if(!checkExistence(srcAccno))
        {
            System.out.println("Account does not exist");
            System.exit(0);
        }
        System.out.println("Enter amount to be transferred:");
        int transferAmt = sc.nextInt();
        ResultSet rs = DBConnection.select("SELECT balance FROM accounts WHERE accno="+srcAccno);
        rs.next();
        if(transferAmt > rs.getInt(1))
        {
            System.out.println("Insufficient balance");
            System.exit(0);
        }
        DBConnection.executeChanges("UPDATE accounts SET balance=balance-"+transferAmt+" WHERE accno="+srcAccno);
        System.out.println("Enter destination account number:");
        int destAccno = sc.nextInt();
        int oldBalance, newBalance;
        if(!checkExistence(destAccno))
        {
            System.out.println("Account does not exist");
            System.exit(0);
        }
        else
        {
            rs = DBConnection.select("SELECT balance FROM accounts WHERE accno="+destAccno);
            rs.next();
            oldBalance = rs.getInt(1);
            DBConnection.executeChanges("UPDATE accounts SET balance=balance+"+transferAmt+" WHERE accno="+destAccno);
            rs = DBConnection.select("SELECT balance FROM accounts WHERE accno="+destAccno);
            rs.next();
            newBalance = rs.getInt(1);
            if(oldBalance + transferAmt == newBalance)
            {
                con.commit();
                System.out.println("Transaction Successful");
            }
            else
            {
                con.rollback();
                System.out.println("Transaction Failed");
            }
        }
        sc.close();
    }
}