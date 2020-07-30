package kr.kro.jhseo1107.Util

import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

fun SendMail (sender: String, reciever: String, content: String){
    var from = sender
    var to = reciever
    var host = "smtp.gmail.com"

    var properties:Properties = System.getProperties()
    properties.put("mail.smtp.host", host)
    properties.put("mail.smtp.port", 465)
    properties.put("mail.smtp.ssl.enable", true)
    properties.put("mail.smtp.auth", true)

    var session = Session.getInstance(properties, object: Authenticator() {
       override fun getPasswordAuthentication(): PasswordAuthentication
       {
           return PasswordAuthentication(from, TomcatProperties.EmailPassword())
       }
    });

    session.debug = true

    var message =  MimeMessage(session)
    message.setFrom(InternetAddress(from))
    message.addRecipient(Message.RecipientType.TO, InternetAddress(to))
    message.setSubject("Uptune Register Verification")
    message.setText("Your 6-digit code is "+content+".")

    Transport.send(message)
}