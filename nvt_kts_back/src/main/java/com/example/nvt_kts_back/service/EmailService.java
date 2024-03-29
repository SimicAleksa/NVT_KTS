package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.configurations.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    @Async
    public void sendPasswordResetMail(final String sendToEmail, final String tempCode) throws MailException {
        javaMailSender.send(
                makeMail(
                        sendToEmail,
                        "Password reset",
                        "You have requested a password reset. If this wasn't you, simply ignore this message." +
                                "Otherwise, click this <a href=\"" + Settings.RESET_PASS_PAGE_URL + sendToEmail +
                                "\">link</a> to proceed with the action and use given 4-digit code to verify yourself." +
                                " \n\nCode: " + tempCode
                )
        );
    }

    private MimeMessage makeMail(final String sendToEmail, final String mailSubj, final String mailText) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(sendToEmail);

            mail.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
            mail.setSubject(mailSubj);
            mail.setText(mailText, "UTF-8", "html");
        } catch (MessagingException ignored) {}

        return mail;
    }

    @Async
    public void sendPasswordConfirmMail(String email) {
        System.out.println(email + " je mejl na koji saljem");
        //javaMailSender.send(makeMail(email, "Naslov", "probni sadrzaj mejla"));

        javaMailSender.send(
                makeMail(
                        email,
                        "Registration confirmation",
                        "You have registered to Uber application. If this wasn't you, simply ignore this message." +
                                "Otherwise, click this <a href=\"" + Settings.CONFIRM_PASS_PAGE_URL + email +
                                "\">link</a>"
                )
        );
    }


}
