package com.sample.cafekiosk.spring.client.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailSendClient {

    /**
     * 메일 전송
     *
     * @param fromEmail
     * @param toEmail
     * @param subject
     * @param content
     * @return
     */
    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {
        log.info("메일 전송");
        throw new IllegalArgumentException("메일 전송");
    }

    public void a() {
        log.info("AAAAAAAAAAAAAAAAAAA");
    }

    public void b() {
        log.info("BBBBBBBBBBBBBBBBBBBBBBBBBBBB");
    }

    public void c() {
        log.info("CCCCCCCCCCCCCCCCCCCCCCCCCCC");
    }

}
