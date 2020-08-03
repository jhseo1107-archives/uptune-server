package kr.kro.uptune.Data;

import kr.kro.uptune.Util.TomcatProperties;

import java.sql.*;
import java.util.ArrayList;

public class CommentDAO {
    protected static Connection con = null;

    public CommentDAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uptune", "root", TomcatProperties.DBPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(CommentDTO dto) {
        PreparedStatement insertstatement = null;
        ResultSet set = null;
        try {
            insertstatement = con.prepareStatement("INSERT INTO COMMENT_TABLE VALUES (?,?,?,?,?,?)");
            insertstatement.setInt(1, dto.getCommentId());
            insertstatement.setString(2, dto.getCommentContent());
            insertstatement.setTimestamp(3, dto.getCommentTimeStamp());
            insertstatement.setInt(4, dto.getCommentWriter());
            insertstatement.setInt(5, dto.getCommentParentId());
            insertstatement.setInt(6, dto.getCommentParentType());

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
    public void update(CommentDTO dto)
    {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("UPDATE COMMENT_TABLE SET CONTENT = ? WHERE ID = ?");
            statement.setString(1, dto.getCommentContent());
            statement.setInt(2, dto.getCommentId());
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

            statement = con.prepareStatement("SELECT COUNT(*) FROM COMMENT_TABLE");
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

    public CommentDTO getFromCommentId(int commentId) {
        CommentDTO returndto = new CommentDTO();
        PreparedStatement statement = null;
        ResultSet set = null;

        CommentReplyDAO childdao = new CommentReplyDAO();

        try {
            statement = con.prepareStatement("SELECT * FROM COMMENT_TABLE WHERE ID = ?");
            statement.setInt(1, commentId);
            set = statement.executeQuery();

            while (set.next()) {
                returndto.setCommentId(set.getInt(1));
                returndto.setCommentContent(set.getString(2));
                returndto.setCommentTimeStamp(set.getTimestamp(3));
                returndto.setCommentWriter(set.getInt(4));
                returndto.setCommentParentId(set.getInt(5));
                returndto.setCommentParentType(set.getInt(6));
                returndto.setCommentReply(childdao.getFromParentId(commentId));
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

    public ArrayList<CommentDTO> getFromParentId(int parentId, int parentType) {
        ArrayList<CommentDTO> returndto = new ArrayList<CommentDTO>();
        PreparedStatement statement = null;
        ResultSet set = null;

        CommentReplyDAO childdao = new CommentReplyDAO();

        try {
            statement = con.prepareStatement("SELECT * FROM COMMENT_TABLE WHERE PARENTID = ? AND PARENTTYPE = ?");
            statement.setInt(1, parentId);
            statement.setInt(2, parentType);
            set = statement.executeQuery();

            while (set.next()) {
                CommentDTO tempdto = new CommentDTO();
                tempdto.setCommentId(set.getInt(1));
                tempdto.setCommentContent(set.getString(2));
                tempdto.setCommentTimeStamp(set.getTimestamp(3));
                tempdto.setCommentWriter(set.getInt(4));
                tempdto.setCommentParentId(set.getInt(5));
                tempdto.setCommentParentType(set.getInt(6));
                tempdto.setCommentReply(childdao.getFromParentId(set.getInt(1)));

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
