# EmailExample

in build.gradle you will need:
```java
implementation 'org.springframework.boot:spring-boot-starter-mail'
 ```
in application.properties you will need:
```java
# Email Settings
spring.mail.protocol=smtp
spring.mail.host: smtp.gmail.com
spring.mail.port: 465
spring.mail.username: [your gmail address here]
spring.mail.password: [your google app access password here]
spring.mail.properties.mail.smtp.auth: true
spring.mail.properties.mail.smtp.starttls.enable: true
mail.smtp.starttls.enable=false
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.ssl.enable=true
```
to generate Google App Password:
1.  Open Browser
2.  Navigate to Google Account (myaccount.google.com)
3.  Select "Security" from the menu on the left
4.  Click "App passwords" in the "Signing in to Google" section.
5.  Select "Mail" in the "Select app" dropdown
6.  Select a default or set custom app name in "Select Device" dropdown
7.  Click "Generate"
8.  Copy generated password
9.  Use this password on line 5 of the application.properties settings shown above

create EmailService:
```java
package com.example.sendmail.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendMail(String toEmail, String htmlText, String subject) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom("no-reply@test.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlText,true);
        javaMailSender.send(mimeMessage);
    }
}
```
send mail in controller using: 
```java
String emailString= """
      <!DOCTYPE html>
      <html lang="en" xmlns:th="https://www.thymeleaf.org">
      <head>
          <meta charset="utf-8"/>
          <title></title>
      </head>
      <body>
      <p>This is a test email.</p>
      </body>
      </html>
    """;
emailService.sendMail(form.getEmail(),emailString,"Test E-mail");
```
