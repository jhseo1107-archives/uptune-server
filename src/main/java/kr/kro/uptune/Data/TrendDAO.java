package kr.kro.uptune.Data;

import kr.kro.uptune.Util.ParentType;
import kr.kro.uptune.Util.TomcatProperties;

import java.sql.*;
import java.util.ArrayList;

public class TrendDAO {
    protected static Connection con = null;

    public TrendDAO() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uptune", "root", TomcatProperties.DBPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(TrendDTO dto) {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("INSERT INTO TREND_TABLE VALUES (?,?,?,?,?,?)");
            statement.setInt(1, dto.getTrendId());
            statement.setString(2, dto.getTrendName());
            statement.setTimestamp(3, dto.getTrendTimeStamp());
            statement.setInt(4, dto.getTrendWriter());
            statement.setInt(5, dto.getTrendLikes());
            statement.setString(6, dto.getTrendFileExtension());

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
    public void update(TrendDTO dto)
    {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("UPDATE TREND_TABLE SET NAME = ? ,LIKES = ? ,TIMESTAMP = ? WHERE ID = ?");
            statement.setString(1, dto.getTrendName());
            statement.setInt(2, dto.getTrendLikes());
            statement.setTimestamp(3, dto.getTrendTimeStamp());
            statement.setInt(4, dto.getTrendId());
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

            statement = con.prepareStatement("SELECT COUNT(*) FROM TREND_TABLE");
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

    public TrendDTO getFromTrendId(int trendId) {
        TrendDTO returndto = new TrendDTO();
        PreparedStatement statement = null;
        ResultSet set = null;

        CommentDAO childdao = new CommentDAO();

        try {
            statement = con.prepareStatement("SELECT * FROM TREND_TABLE WHERE ID = ?");
            statement.setInt(1, trendId);
            set = statement.executeQuery();

            while (set.next()) {
                returndto.setTrendId(set.getInt(1));
                returndto.setTrendName(set.getString(2));
                returndto.setTrendTimeStamp(set.getTimestamp(3));
                returndto.setTrendWriter(set.getInt(4));
                returndto.setTrendLikes(set.getInt(5));
                returndto.setTrendFileExtension(set.getString(6));
                returndto.setTrendComments(childdao.getFromParentId(trendId, ParentType.TREND_VIDEO));
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
    public ArrayList<TrendDTO> getHighLikes(int num)
    {
        ArrayList<TrendDTO> returndto = new ArrayList<TrendDTO>();
        PreparedStatement statement = null;
        ResultSet set = null;

        CommentDAO childdao = new CommentDAO();

        try {
            statement = con.prepareStatement("SELECT * FROM TREND_TABLE ORDER BY LIKES DESC LIMIT ?");
            statement.setInt(1, num);
            set = statement.executeQuery();

            while (set.next()) {
                TrendDTO tempdto = new TrendDTO();
                tempdto.setTrendId(set.getInt(1));
                tempdto.setTrendName(set.getString(2));
                tempdto.setTrendTimeStamp(set.getTimestamp(3));
                tempdto.setTrendWriter(set.getInt(4));
                tempdto.setTrendLikes(set.getInt(5));
                tempdto.setTrendFileExtension(set.getString(6));
                tempdto.setTrendComments(childdao.getFromParentId(set.getInt(1), ParentType.TREND_VIDEO));

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
    public ArrayList<TrendDTO> getRecent(int num, int count)
    {
        ArrayList<TrendDTO> returndto = new ArrayList<TrendDTO>();
        PreparedStatement statement = null;
        ResultSet set = null;

        CommentDAO childdao = new CommentDAO();

        try {
            statement = con.prepareStatement("SELECT * FROM TREND_TABLE ORDER BY ID DESC LIMIT ?, ?");
            statement.setInt(1, num);
            statement.setInt(2, count);
            set = statement.executeQuery();

            while (set.next()) {
                TrendDTO tempdto = new TrendDTO();
                tempdto.setTrendId(set.getInt(1));
                tempdto.setTrendName(set.getString(2));
                tempdto.setTrendTimeStamp(set.getTimestamp(3));
                tempdto.setTrendWriter(set.getInt(4));
                tempdto.setTrendLikes(set.getInt(5));
                tempdto.setTrendFileExtension(set.getString(6));
                tempdto.setTrendComments(childdao.getFromParentId(set.getInt(1), ParentType.TREND_VIDEO));

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
