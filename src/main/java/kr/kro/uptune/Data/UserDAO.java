package kr.kro.uptune.Data;

import kr.kro.uptune.Util.TomcatProperties;

import java.sql.*;

public class UserDAO {
    private static Connection con = null;

    public UserDAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uptune", "root", TomcatProperties.DBPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(UserDTO dto) {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("INSERT INTO USER_TABLE VALUES (?,?,?,?)");
            statement.setInt(1, dto.getUserNo());
            statement.setString(2, dto.getUserName());
            statement.setString(3, dto.getUserEmail());
            statement.setString(4, dto.getUserPassword());

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

    public int count() {
        int returnvalue = 0;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {

            statement = con.prepareStatement("SELECT COUNT(*) FROM USER_TABLE");
            set = statement.executeQuery();

            while (set.next()) {
                returnvalue = set.getInt(1);
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
        return returnvalue;
    }

    public void disconnect() {
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean existFromUserNo(int userNo) {
        PreparedStatement statement = null;
        ResultSet set = null;
        Boolean returnvalue = false;

        try {
            statement = con.prepareStatement("SELECT count(*) FROM USER_TABLE WHERE USER_NO = ?");
            statement.setInt(1, userNo);
            set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt(1) > 0)
                    returnvalue = true;
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
        return returnvalue;
    }

    public UserDTO getFromUserNo(int userNo) {
        UserDTO returndto = new UserDTO();
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            statement = con.prepareStatement("SELECT * FROM USER_TABLE WHERE USER_NO = ?");
            statement.setInt(1, userNo);
            set = statement.executeQuery();

            while (set.next()) {
                returndto.setUserNo(set.getInt(1));
                returndto.setUserEmail(set.getString(2));
                returndto.setUserName(set.getString(3));
                returndto.setUserPassword(set.getString(4));
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

        return returndto;
    }

    public boolean existFromUserEmail(String userEmail) {
        PreparedStatement statement = null;
        ResultSet set = null;
        Boolean returnvalue = false;

        try {
            statement = con.prepareStatement("SELECT count(*) FROM USER_TABLE WHERE MAIL = ?");
            statement.setString(1, userEmail);
            set = statement.executeQuery();

            while (set.next()) {
                if (set.getInt(1) > 0)
                    returnvalue = true;
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
        return returnvalue;
    }

    public UserDTO getFromUserEmail(String userEmail) {
        UserDTO returndto = new UserDTO();
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            statement = con.prepareStatement("SELECT * FROM USER_TABLE WHERE MAIL = ?");
            statement.setString(1, userEmail);
            set = statement.executeQuery();

            while(set.next())
            {
                returndto.setUserNo(set.getInt("USER_NO"));
                returndto.setUserEmail(set.getString("USERNAME"));
                returndto.setUserName(set.getString("MAIL"));
                returndto.setUserPassword(set.getString("PW"));
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

        return returndto;
    }
}
