import java.sql.*;
public class CreateDB
{
    public static void main(String args[])throws Exception
    {
        Connection con = DBConnection.getConnection("mydb", "system", "mysql");
        // String query = "CREATE TABLE Accounts(accno int primary key, accname varchar(20), balance float)";
        DBConnection.createTable("Accounts", "accno int primary key,accname varchar(20),balance float", con);
        DBConnection.DESC("Accounts", con);
        //DBConnection.raw(query, con);
        // for(int i = 1; i <= 2; i++)
        // {
        //     String 
        // }
        // query = "INSERT INTO Accounts"
        // System.out.println(DBConnection.generateID("Accounts", "accno", con));
    }
}