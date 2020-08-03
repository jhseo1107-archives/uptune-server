package kr.kro.uptune.Data;

import kr.kro.uptune.Util.TomcatProperties;

import java.sql.*;
import java.util.ArrayList;

public class CommentReplyDAO {
    protected static Connection con = null;

    public CommentReplyDAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uptune", "root", TomcatProperties.DBPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(CommentReplyDTO dto) {
        PreparedStatement insertstatement = null;
        ResultSet set = null;
        try {
            insertstatement = con.prepareStatement("INSERT INTO COMMENT_REPLY_TABLE VALUES (?,?,?,?,?)");
            insertstatement.setInt(1, dto.getCommentReplyId());
            insertstatement.setString(2, dto.getCommentReplyContent());
            insertstatement.setTimestamp(3, dto.getCommentReplyTimestamp());
            insertstatement.setInt(4, dto.getCommentReplyWriter());
            insertstatement.setInt(5, dto.getCommentReplyParentId());

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
    public void update(CommentReplyDTO dto)
    {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("UPDATE COMMENT_REPLY_TABLE SET CONTENT = ? WHERE ID = ?");
            statement.setString(1, dto.getCommentReplyContent());
            statement.setInt(2, dto.getCommentReplyId());
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

            statement = con.prepareStatement("SELECT COUNT(*) FROM COMMENT_REPLY_TABLE");
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

    public CommentReplyDTO getFromCommentReplyId(int commentReplyId) {
        CommentReplyDTO returndto = new CommentReplyDTO();
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            statement = con.prepareStatement("SELECT * FROM COMMENT_REPLY_TABLE WHERE ID = ?");
            statement.setInt(1, commentReplyId);
            set = statement.executeQuery();

            while (set.next()) {
                returndto.setCommentReplyId(set.getInt(1));
                returndto.setCommentReplyContent(set.getString(2));
                returndto.setCommentReplyTimestamp(set.getTimestamp(3));
                returndto.setCommentReplyWriter(set.getInt(4));
                returndto.setCommentReplyParentId(set.getInt(5));
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

    public ArrayList<CommentReplyDTO> getFromParentId(int parentId) {
        ArrayList<CommentReplyDTO> returndto = new ArrayList<CommentReplyDTO>();
        PreparedStatement statement = null;
        ResultSet set = null;

        try {
            statement = con.prepareStatement("SELECT * FROM COMMENT_REPLY_TABLE WHERE PARENTID = ?");
            statement.setInt(1, parentId);
            set = statement.executeQuery();

            while (set.next()) {
                CommentReplyDTO tempdto = new CommentReplyDTO();

                tempdto.setCommentReplyId(set.getInt(1));
                tempdto.setCommentReplyContent(set.getString(2));
                tempdto.setCommentReplyTimestamp(set.getTimestamp(3));
                tempdto.setCommentReplyWriter(set.getInt(4));
                tempdto.setCommentReplyParentId(set.getInt(5));

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

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return returndto;
    }


}
