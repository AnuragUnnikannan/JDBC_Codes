import java.io.*;
import java.sql.*;
public class CheckQuery
{
    static Connection con;
    public static void main(String args[])throws Exception
    {
        con = DBConnection.getConnection("mydb");
        String query = "SELECT * FROM accounts";
        ResultSet rs = DBConnection.select(query);
        DBConnection.printQueryResult(rs);
    }
}