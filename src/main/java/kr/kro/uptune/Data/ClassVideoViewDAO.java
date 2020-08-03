package kr.kro.uptune.Data;

import kr.kro.uptune.Util.TomcatProperties;

import java.sql.*;

public class ClassVideoViewDAO { // 시청 여부 저장 DAO. 클래스명 비직관적이어서 주석처리
    private static Connection con = null;

    public ClassVideoViewDAO()
    {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uptune", "root", TomcatProperties.DBPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(int userId, int videoId)
    {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("INSERT INTO CLASS_VIDEO_VIEW_TABLE VALUES (?,?,?)");
            statement.setInt(1, count()+1);
            statement.setInt(2, userId);
            statement.setInt(3, videoId);

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

    public int count()
    {
        int returnvalue = 0;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {

            statement = con.prepareStatement("SELECT COUNT(*) FROM CLASS_VIDEO_VIEW_TABLE");
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

    public void disconnect()
    {
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean hasViewed(int userId, int videoId)
    {
        boolean returnvalue = false;

        PreparedStatement statement = null;
        ResultSet set = null;
        try {

            statement = con.prepareStatement("SELECT COUNT(*) FROM CLASS_VIDEO_VIEW_TABLE WHERE USERID = ? AND VIDEOID = ?");
            statement.setInt(1, userId);
            statement.setInt(2, userId);
            set = statement.executeQuery();

            while (set.next()) {
                if(set.getInt(1) > 0)
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
}
