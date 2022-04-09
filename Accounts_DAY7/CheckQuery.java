import java.io.*;
import java.sql.*;
public class CheckQuery
{
    static Connection con;
    public static void main(String args[])throws Exception
    {
        con = DBConnection.getConnection("mydb", "system", "mysql");
        String query = "SELECT * FROM Accounts";
        ResultSet rs = DBConnection.select(query, con);
        DBConnection.printQueryResult(rs);
    }
}