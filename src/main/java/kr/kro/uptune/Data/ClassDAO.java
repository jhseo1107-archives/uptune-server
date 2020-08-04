package kr.kro.uptune.Data;

import kr.kro.uptune.Util.TomcatProperties;

import java.sql.*;
import java.util.ArrayList;

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
        PreparedStatement insertstatement = null;
        ResultSet set = null;
        try {
            insertstatement = con.prepareStatement("INSERT INTO CLASS_TABLE VALUES (?,?,?,?)");
            insertstatement.setInt(1, dto.getClassId());
            insertstatement.setString(2, dto.getClassName());
            insertstatement.setTimestamp(3, dto.getClassTimeStamp());
            insertstatement.setInt(4, dto.getClassWriter());

            set = insertstatement.executeQuery();

            while (set.next()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (insertstatement != null)
                    insertstatement.close();
                if (set != null)
                    set.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public void update(ClassDTO dto)
    {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("UPDATE CLASS_TABLE SET NAME = ? ,TIMESTAMP = ? WHERE ID = ?");
            statement.setString(1, dto.getClassName());
            statement.setTimestamp(2, dto.getClassTimeStamp());
            statement.setInt(3, dto.getClassId());
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

            statement = con.prepareStatement("SELECT COUNT(*) FROM CLASS_TABLE");
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

        ClassVideoDAO childdao = new ClassVideoDAO();

        try {
            statement = con.prepareStatement("SELECT * FROM CLASS_TABLE WHERE ID = ?");
            statement.setInt(1, classId);
            set = statement.executeQuery();

            while (set.next()) {
                returndto.setClassId(set.getInt(1));
                returndto.setClassName(set.getString(2));
                returndto.setClassTimeStamp(set.getTimestamp(3));
                returndto.setClassWriter(set.getInt(4));
                returndto.setClassVideo(childdao.getFromParentId(classId));
            }




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (set != null)
                    set.close();
                childdao.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return returndto;
    }
    public ArrayList<ClassDTO> getAll()
    {
        ArrayList<ClassDTO> returndto = new ArrayList<ClassDTO>();
        PreparedStatement statement = null;
        ResultSet set = null;

        ClassVideoDAO childdao = new ClassVideoDAO();

        try {
            statement = con.prepareStatement("SELECT * FROM CLASS_TABLE");
            set = statement.executeQuery();

            while (set.next()) {
                ClassDTO tempdto = new ClassDTO();

                tempdto.setClassId(set.getInt(1));
                tempdto.setClassName(set.getString(2));
                tempdto.setClassTimeStamp(set.getTimestamp(3));
                tempdto.setClassWriter(set.getInt(4));
                tempdto.setClassVideo(childdao.getFromParentId(set.getInt(1)));

                returndto.add(tempdto);
            }




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (set != null)
                    set.close();
                childdao.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return returndto;
    }
}
