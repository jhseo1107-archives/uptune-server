package kr.kro.uptune.Data;

import kr.kro.uptune.Util.TomcatProperties;

import java.sql.*;

public class UTUCDAO {
    private static Connection con = null;
    public UTUCDAO()
    {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uptune", "root", TomcatProperties.DBPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(String id, String hashedpw) {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("INSERT INTO UTUC_TABLE VALUES (?,?)");
            statement.setString(1, id);
            statement.setString(2, hashedpw);

            set = statement.executeQuery();

            while (set.next()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (set != null)
                    set.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public void disconnect() {
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public String pwFromId(String userID) {
        String returnstring = null;
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            statement = con.prepareStatement("SELECT * FROM UTUC_TABLE WHERE USERNAME = ?");
            statement.setString(1, userID);
            set = statement.executeQuery();

            while (set.next()) {
                returnstring = set.getString(2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (set != null)
                    set.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return returnstring;
    }
}
