package kr.kro.uptune.Util

import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.mail.Authenticator

fun SendMail(sender: String, reciever: String, content: String) {
    var from = sender
    var to = reciever
    var host = "smtp.gmail.com"

    var properties: Properties = System.getProperties()
    properties.put("mail.smtp.host", host)
    properties.put("mail.smtp.port", "587")
    properties.put("mail.smtp.starttls.enable", "true")
    properties.put("mail.smtp.auth", "true")

    var session = Session.getInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(from, TomcatProperties.EmailPassword())
        }
    });

    // session.debug = true


    var message = MimeMessage(session)
    message.setFrom(InternetAddress(from, "Uptune"))
    message.addRecipient(Message.RecipientType.TO, InternetAddress(to))
    message.setSubject("업튠 가입을 환영합니다!")
    message.setContent("<!DOCTYPE HTML>\n" +
            "<html>\n" +
            "    <body style=\"text-align: center\">\n" +
            "        <h1>저희 업튠을 이용해주셔서 감사합니다!</h1>\n" +
            "        <h2>보안 코드는 "+content+" 입니다.</h2>\n" +
            "        <h3>* 해당 보안 코드를 입력하면 <a href=\"https://jhseo1107.kro.kr/uptune/legal.jsp\">업튠 이용약관</a>에 동의하는것으로 간주합니다.</h3>\n" +
            "    </body>\n" +
            "</html>","text/html; charset=UTF-8")

    Transport.send(message)

}
fun SendTrendReportMail(sender: String, reciever: String, trendid: Int, trendfilextension:String, content: String, trendwriteremail : String) {
    var from = sender
    var to = reciever
    var host = "smtp.gmail.com"

    var properties: Properties = System.getProperties()
    properties.put("mail.smtp.host", host)
    properties.put("mail.smtp.port", "587")
    properties.put("mail.smtp.starttls.enable", "true")
    properties.put("mail.smtp.auth", "true")

    var session = Session.getInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(from, TomcatProperties.EmailPassword())
        }
    });

    // session.debug = true


    var message = MimeMessage(session)
    message.setFrom(InternetAddress(from, "Uptune"))
    message.addRecipient(Message.RecipientType.TO, InternetAddress(to))
    message.setSubject("Trend Report")
    message.setContent("<!DOCTYPE HTML>\n" +
            "<html>\n" +
            "    <body style=\"text-align: center\">\n" +
            "        <h1>Trend Report</h1>\n" +
            "        <h2>Trend code : <a href=\"https://jhseo1107.kro.kr/uptune/Videos/"+trendid+"."+trendfilextension+"\">"+trendid+"</a> by "+trendwriteremail+"</h2>\n" +
            "        <h3>Reason : "+content+"</h3>\n" +
            "    </body>\n" +
            "</html>","text/html; charset=UTF-8")

    Transport.send(message)


}
fun SendCommentReportMail(sender: String, reciever: String, commentid: Int, content: String, commentwriteremail : String, commentcontent: String) {
    var from = sender
    var to = reciever
    var host = "smtp.gmail.com"

    var properties: Properties = System.getProperties()
    properties.put("mail.smtp.host", host)
    properties.put("mail.smtp.port", "587")
    properties.put("mail.smtp.starttls.enable", "true")
    properties.put("mail.smtp.auth", "true")

    var session = Session.getInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(from, TomcatProperties.EmailPassword())
        }
    });

    // session.debug = true


    var message = MimeMessage(session)
    message.setFrom(InternetAddress(from, "Uptune"))
    message.addRecipient(Message.RecipientType.TO, InternetAddress(to))
    message.setSubject("Comment Report")
    message.setContent("<!DOCTYPE HTML>\n" +
            "<html>\n" +
            "    <body style=\"text-align: center\">\n" +
            "        <h1>Comment Report</h1>\n" +
            "        <h2>Comment code : "+commentid+" by "+commentwriteremail+" : "+commentcontent+"</h2>\n" +
            "        <h3>Reason : "+content+"</h3>\n" +
            "    </body>\n" +
            "</html>","text/html; charset=UTF-8")

    Transport.send(message)


}
fun SendCommentReplyReportMail(sender: String, reciever: String, commentreplyid: Int, content: String, commentreplywriteremail : String, commentreplycontent: String) {
    var from = sender
    var to = reciever
    var host = "smtp.gmail.com"

    var properties: Properties = System.getProperties()
    properties.put("mail.smtp.host", host)
    properties.put("mail.smtp.port", "587")
    properties.put("mail.smtp.starttls.enable", "true")
    properties.put("mail.smtp.auth", "true")

    var session = Session.getInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(from, TomcatProperties.EmailPassword())
        }
    });

    // session.debug = true


    var message = MimeMessage(session)
    message.setFrom(InternetAddress(from, "Uptune"))
    message.addRecipient(Message.RecipientType.TO, InternetAddress(to))
    message.setSubject("CommentReply Report")
    message.setContent("<!DOCTYPE HTML>\n" +
            "<html>\n" +
            "    <body style=\"text-align: center\">\n" +
            "        <h1>CommentReply Report</h1>\n" +
            "        <h2>CommentReply code : "+commentreplyid+" by "+commentreplywriteremail+" : "+commentreplycontent+"</h2>\n" +
            "        <h3>Reason : "+content+"</h3>\n" +
            "    </body>\n" +
            "</html>","text/html; charset=UTF-8")

    Transport.send(message)


}