package com.example.nvt_kts_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    @Async
    public void sendPasswordResetMail(String sendToEmail, String tempCode) throws MailException {
        javaMailSender.send(
                makeMail(
                        sendToEmail,
                        "Password reset",
                        "You have requested a password reset. If this wasn't you, simply ignore this message." +
                                "Otherwise, click this <a href=\"https//localhost:4200/login/reset-password\">link</a> " +
                                "to proceed with the action and use given 4-digit code to verify yourself. \n\nCode: " +
                                tempCode
                )
        );
    }

    private MimeMessage makeMail(String sendToEmail, String mailSubj, String mailText) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(sendToEmail);

            mail.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
            mail.setSubject(mailSubj);
            mail.setText(mailText, "UTF-8", "html");
        } catch (MessagingException ignored) {
        }

        return mail;
    }
}
