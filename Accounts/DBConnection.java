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

    public static void executeChanges(String query, Connection con)throws Exception
    {
        Statement st = con.createStatement();
        st.executeUpdate(query);
        /* query = "SELECT * FROM "+tableName;
        rs = st.executeQuery(query);
        printQueryResult(rs); */
    }

    public static Connection getConnection(String dbName, String username, String password)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
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

    public static void DESC(String table, Connection con) throws Exception
    {
        table=table.toUpperCase();
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

    public static int generateID(Connection con)throws Exception
    {
        try
        {
            Statement st = con.createStatement();
            String query = "SELECT MAX(accno) FROM Accounts";
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