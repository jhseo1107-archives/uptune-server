package kr.kro.uptune.Data;

import kr.kro.uptune.Util.ParentType;
import kr.kro.uptune.Util.TomcatProperties;
import org.w3c.dom.Comment;

import java.sql.*;
import java.util.ArrayList;

public class ClassVideoDAO {
    protected static Connection con = null;

    public ClassVideoDAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uptune", "root", TomcatProperties.DBPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(ClassVideoDTO dto) {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("INSERT INTO CLASS_VIDEO_TABLE VALUES (?,?,?,?,?,?)");
            statement.setInt(1, dto.getClassVideoId());
            statement.setString(2, dto.getClassVideoName());
            statement.setTimestamp(3, dto.getClassVideoTimeStamp());
            statement.setInt(4, dto.getClassVideoWriter());
            statement.setInt(5, dto.getClassVideoParentId());
            statement.setString(6, dto.getClassVideoLink());

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
    public void update(ClassVideoDTO dto)
    {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("UPDATE CLASS_VIDEO_TABLE SET NAME = ? WHERE ID = ?");
            statement.setString(1, dto.getClassVideoName());
            statement.setInt(2, dto.getClassVideoId());
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

            statement = con.prepareStatement("SELECT COUNT(*) FROM CLASS_VIDEO_TABLE");
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

    public ClassVideoDTO getFromClassVideoId(int classVideoId) {
        ClassVideoDTO returndto = new ClassVideoDTO();
        PreparedStatement statement = null;
        ResultSet set = null;

        CommentDAO childdao = new CommentDAO();

        try {
            statement = con.prepareStatement("SELECT * FROM CLASS_VIDEO_TABLE WHERE ID = ?");
            statement.setInt(1, classVideoId);
            set = statement.executeQuery();

            while (set.next()) {
                returndto.setClassVideoId(set.getInt(1));
                returndto.setClassVideoName(set.getString(2));
                returndto.setClassVideoTimeStamp(set.getTimestamp(3));
                returndto.setClassVideoWriter(set.getInt(4));
                returndto.setClassVideoParentId(set.getInt(5));
                returndto.setClassVideoLink(set.getString(6));
                returndto.setClassVideoComment(childdao.getFromParentId(classVideoId, ParentType.CLASS_VIDEO));
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

    public ArrayList<ClassVideoDTO> getFromParentId(int ParentId)
    {
        ArrayList<ClassVideoDTO> returndto = new ArrayList<ClassVideoDTO>();
        PreparedStatement statement = null;
        ResultSet set = null;

        CommentDAO childdao = new CommentDAO();

        try {
            statement = con.prepareStatement("SELECT * FROM CLASS_VIDEO_TABLE WHERE PARENTID = ?");
            statement.setInt(1, ParentId);
            set = statement.executeQuery();

            while (set.next()) {
                ClassVideoDTO tempdto = new ClassVideoDTO();
                tempdto.setClassVideoId(set.getInt(1));
                tempdto.setClassVideoName(set.getString(2));
                tempdto.setClassVideoTimeStamp(set.getTimestamp(3));
                tempdto.setClassVideoWriter(set.getInt(4));
                tempdto.setClassVideoParentId(set.getInt(5));
                tempdto.setClassVideoLink(set.getString(6));
                tempdto.setClassVideoComment(childdao.getFromParentId(set.getInt(1), ParentType.CLASS_VIDEO));
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
