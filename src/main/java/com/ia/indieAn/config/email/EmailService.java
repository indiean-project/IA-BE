package com.ia.indieAn.config.email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Slf4j
@Service
@Transactional
public class EmailService {

    @Autowired
    JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private String sendNum;

    // 메일 내용
    public MimeMessage sendMessage(String userId) throws MessagingException, UnsupportedEncodingException {
        log.info("보내는 사람 : {} ", sender);
        log.info("받는 사람 : {}", userId);
        log.info("인증 번호 : {}", sendNum);

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, userId);
        message.setSubject("INDIE:AN 회원 가입 이메일 인증");

        String msg = "<h2>INDIE:AN 이메일 인증 번호 입니다.</h2><br>";
        msg += "<div style='border:1px solid black; font-size:120%'>"+sendNum+"</div>";
        msg += "<br>감사합니다.";
        message.setText(msg, "UTF-8", "html");
        message.setFrom(sender);

        return message;
    }

    // 랜덤 인증 코드 작성
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) {
            int index = rnd.nextInt(2);
            switch(index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));  // A~Z
                    break;
                case 1:
                    key.append(rnd.nextInt(10));
                    break;
            }
        }
        return key.toString();
    }

    // 메일 발송
    public String sendVerifyMessage(String userId) throws MessagingException, UnsupportedEncodingException {
        sendNum = createKey();  // 랜덤 인증번호 생성

        MimeMessage message = sendMessage(userId);
        try {
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return sendNum;
    }
}
