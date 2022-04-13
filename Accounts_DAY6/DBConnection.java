import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
public class DBConnection
{
    static final String URI = "jdbc:mysql://localhost:3306/";   //For connection to database
    public static Connection con;
    static ResultSet rs;

    public static void main(String args[])
    {
        return;
    }

    public static void printQueryResult(ResultSet rs)throws Exception
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println("column count = "+rsmd.getColumnCount());
        for(int i = 1; i <= rsmd.getColumnCount(); i++)
        {
            System.out.print(rsmd.getColumnName(i)+"\t\t");
        }
        System.out.println();
        while(rs.next())
        {
            for(int i = 1; i <= rsmd.getColumnCount(); i++)
            {
                System.out.print(rs.getString(i)+"\t\t");
            }
            System.out.println();
        }
    }

    public static void executeChanges(String query)throws Exception
    {
        Statement st = con.createStatement();
        st.executeUpdate(query);
        /* query = "SELECT * FROM "+tableName;
        rs = st.executeQuery(query);
        printQueryResult(rs); */
    }

    public static Connection getConnection(String dbName)throws Exception
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Enter username:");
            String username = br.readLine();
            System.out.println("Enter password");
            String password = br.readLine();
            con = DriverManager.getConnection(URI+dbName, username, password);
            System.out.println("Connection successful");
            return con;
        }
        catch(Exception e)
        {
            System.out.println("Connection unsuccessful");
            return con;
        }
    }

    public static void DESC(String table) throws Exception
    {
//        table=table.toUpperCase();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from "+table);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnnum = rsmd.getColumnCount();
        for (int i = 1; i <= columnnum; i++) {
            String name = rsmd.getColumnName(i);
            String type=rsmd.getColumnTypeName(i);
            int typenum=rsmd.getPrecision(i);
            System.out.println(name + "\t\t|\t" + type+"("+typenum+")");
        }
    }

    public static int generateID()throws Exception
    {
        try
        {
            Statement st = con.createStatement();
            String query = "SELECT MAX(accno) FROM accounts";
            int x;
            ResultSet rs = st.executeQuery(query);
            rs.next();
            x = rs.getInt(1);
            x++;
            return x;
        }
        catch(Exception e)
        {
            return 1;
        }
    }
}