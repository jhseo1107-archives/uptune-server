package kr.kro.uptune.Data;

import kr.kro.uptune.Util.TomcatProperties;

import java.sql.*;

public class ClassDAO {
    protected static Connection con = null;

    public ClassDAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uptune", "root", TomcatProperties.DBPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(ClassDTO dto) {
        PreparedStatement statement = null;
        ResultSet set = null;
        PreparedStatement tablewritestmnt = null;
        ResultSet tablewriteset = null;
        try {
            statement = con.prepareStatement("INSERT INTO CLASS_TABLE VALUES (?,?,?,?)");
            statement.setInt(1, dto.getClassId());
            statement.setString(2, dto.getClassName());
            statement.setInt(3, dto.getClassWriter());
            statement.setTimestamp(4, dto.getClassTimeStamp());

            set = statement.executeQuery();

            while (set.next()) {

            }

            tablewritestmnt = con.prepareStatement("CREATE TABLE CLASSVIDEO_?_TABLE" +
                    "(ID BIGINT NOT NULL," +
                    " NAME varchar(20) NOT NULL," +
                    " TIMESTAMP timestamp NOT NULL," +
                    " WRITERID BIGINT NOT NULL," +
                    " LINK varchar(20) NOT NULL," +
                    " PRIMARY KEY(ID))" +
                    " ENGINE = InnoDB CHARSET = utf8;");
            tablewritestmnt.setInt(1, dto.getClassId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (set != null)
                    set.close();
                if(tablewritestmnt != null)
                    tablewritestmnt.close();
                if(tablewriteset != null)
                    tablewriteset.close();

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

            statement = con.prepareStatement("SELECT COUNT(*) FROM CLASSVIDEO_?_TABLE");
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

    public ClassDTO getFromClassId(int classId) {
        ClassDTO returndto = new ClassDTO();
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            statement = con.prepareStatement("SELECT * FROM CLASS_TABLE WHERE ID = ?");
            statement.setInt(1, classId);
            set = statement.executeQuery();

            while (set.next()) {
                returndto.setClassId(set.getInt(1));
                returndto.setClassName(set.getString(2));
                returndto.setClassTimeStamp(set.getTimestamp(3));
                returndto.setClassWriter(set.getInt(4));
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
