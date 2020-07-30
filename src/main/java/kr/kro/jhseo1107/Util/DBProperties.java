package kr.kro.jhseo1107.Util;

public class DBProperties {
    public static String DBPassword()
    {
        String returntext = System.getProperty("dbpassword");
        return returntext;
    }
}
