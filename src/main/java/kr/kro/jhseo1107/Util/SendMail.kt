package kr.kro.jhseo1107.Util

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
        protected override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(from, TomcatProperties.EmailPassword())
        }
    });

    session.debug = true


    var message = MimeMessage(session)
    message.setFrom(InternetAddress(from))
    message.addRecipient(Message.RecipientType.TO, InternetAddress(to))
    message.setSubject("업튠 가입을 환영합니다!")
    message.setText("저희 업튠을 이용해주셔서 감사합니다! 입력해야 하는 보안 코드는 " + content + "입니다.")

    Transport.send(message)

    
}