package project;

import java.sql.*;
import java.util.*;

import javafx.scene.text.*;
import project.ui.MainDialogController;
import project.util.*;

public class DBClass {
    static Connection conn;
    static String sqlString = "";

    public static void makeDBConnection() throws ClassNotFoundException, SQLException
    {
        // For MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbURL = "jdbc:mysql://localhost:3306/Project304";

        conn = DriverManager.getConnection(dbURL, "root", "mysql");
        if (conn != null)
            {
                System.out.println("Connected");
            }
    }



    public static ResultSet execSQLQuery(String sql)
    {
        boolean autoCommit=false;
        try
            {
                autoCommit = conn.getAutoCommit();
            }
        catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }

        if (autoCommit)
            {
                MainDialogController.updateSQLDisplay(SQLFormat.formatSQLText(sql));
            }
        else
            {
                sqlString = sqlString + sql + " \n\n ";
            }
        try
            {

                Statement statement = conn.createStatement();
                return statement.executeQuery(sql);
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }

        return null;
    }



    public static int execSQLUpdate(String sql)
    {
        boolean autoCommit=false;
        try
            {
                autoCommit = conn.getAutoCommit();
            }
        catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }

        if (autoCommit)
            {
                MainDialogController.updateSQLDisplay(SQLFormat.formatSQLText(sql));
            }
        else
            {
                sqlString = sqlString + sql + " \n\n ";
            }

        try
            {
                Statement statement = conn.createStatement();
                return statement.executeUpdate(sql);
            }
        catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        return 0;
    }

    public static void setAutoCommitOFF() throws SQLException
    {
        conn.setAutoCommit(false);
        sqlString="";
    }

    public static void setAutoCommitON() throws SQLException
    {
        if (!sqlString.equals(""))
            {
                MainDialogController.updateSQLDisplay(SQLFormat.formatSQLText(sqlString));
            }
        conn.setAutoCommit(true);
        sqlString="";
    }

    public static void performCommit() throws SQLException
    {
        conn.commit();
    }

    public static void performRollback() throws SQLException
    {
        conn.rollback();
    }
    public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }
}
