package hxk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

public class Dao {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/seer?useUnicode=true&characterEncoding=UTF-8&useSSL=true&verifyServerCertificate=false";
    private static String user = "hxk";
    private static String password = "xxx";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }


    public static String executeSQL(String preparedSql, Object[] param) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String json = "";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(preparedSql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setObject(i + 1, param[i]);
                }
            }
            rs = pstmt.executeQuery();
            json = resultSetToJson(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeAll(conn, pstmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    public static String resultSetToJson(ResultSet rs) throws SQLException, JSONException {
        JSONArray array = new JSONArray();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.put(jsonObj);
        }
        return array.toString();
    }

    public static String createArrayQuery(String query, String[] strings) {
        StringBuilder queryBuilder = new StringBuilder(query);
        for (String s : strings) {
            queryBuilder.append("?,");
        }
        queryBuilder.deleteCharAt(queryBuilder.length() - 1).append(");");
        return new String(queryBuilder);
    }
}
