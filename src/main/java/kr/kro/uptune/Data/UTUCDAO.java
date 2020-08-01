package kr.kro.uptune.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UTUCDAO extends  UserDAO{
    public void editToAdmin(String mail)
    {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = con.prepareStatement("UPDATE USER_TABLE SET ISADMIN = 1 WHERE MAIL = ?");
            statement.setString(1, mail);

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
}
