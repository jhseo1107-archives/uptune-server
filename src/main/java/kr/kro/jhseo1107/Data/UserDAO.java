package kr.kro.jhseo1107.Data;

import java.sql.*;

public class UserDAO {
    private static Connection con = null;

    public UserDAO() {
        try
        {
            Class.forName("org.mariadb.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uptune","root","password");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void write(UserDTO dto)  {
        PreparedStatement statement = null;
        ResultSet set = null;
        try
        {
            statement = con.prepareStatement("insert into USER_TABLE values (?,?,?,?)");
            statement.setInt(1, dto.getUserNo());
            statement.setString(2, dto.getUserName());
            statement.setString(3, dto.getUserEmail());
            statement.setString(4, dto.getUserPassword());

            set = statement.executeQuery();

            while(set.next())
            {

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try
            {
                if(statement != null)
                    statement.close();
                if(set != null)
                    set.close();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
    public int count()
    {
        int returnvalue = 0;
        try {
            PreparedStatement statement = null;
            ResultSet set = null;

            statement = con.prepareStatement("select count(*) from USER_TABLE");
            set = statement.executeQuery();

            while(set.next())
            {
                returnvalue = set.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
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
    public UserDTO getFromUserNo(int userNo)
    {
        UserDTO returndto = new UserDTO();

        return returndto;
    }
    public UserDTO getFromUserEmail(String userEmail)
    {
        UserDTO returndto = new UserDTO();

        return returndto;
    }
}
