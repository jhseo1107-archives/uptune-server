package kr.kro.jhseo1107.Util;

public class TomcatProperties {
    public static String DBPassword() {
        String returntext = System.getProperty("dbpassword");
        return returntext;
    }
    public static String EmailPassword() {
        String returntext = System.getProperty("uptunemailpassword");
        return returntext;
    }
}
