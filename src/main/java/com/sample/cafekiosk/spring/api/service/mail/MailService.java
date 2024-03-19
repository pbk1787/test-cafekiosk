package com.sample.cafekiosk.spring.api.service.mail;

import com.sample.cafekiosk.spring.client.mail.MailSendClient;
import com.sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import com.sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailSendClient mailSendClient;
    private final MailSendHistoryRepository mailSendHistoryRepository;

    public boolean sendMail(String fromEmail, String toEmail, String subject, String content) {

        boolean result = mailSendClient.sendMail(fromEmail, toEmail, subject, content);
        if (result) {
            mailSendHistoryRepository.save(MailSendHistory.builder()
                .fromEmail(fromEmail)
                .toEmail(toEmail)
                .subject(subject)
                .content(content)
                .build());

            //sendMail 만 stubbing 하고 나머지 method는 원본 객체를 사용하고 싶다면? spy사용
            mailSendClient.a();
            mailSendClient.b();
            mailSendClient.c();
            
            return true;
        }

        return false;
    }
}
