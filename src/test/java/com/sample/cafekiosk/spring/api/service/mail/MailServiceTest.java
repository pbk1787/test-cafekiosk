package com.sample.cafekiosk.spring.api.service.mail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.sample.cafekiosk.spring.client.mail.MailSendClient;
import com.sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import com.sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Spy
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        //given
//        MailSendClient mailSendClient = mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);
//        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        //stubbing
//        when(mailSendClient.sendMail(anyString(), anyString(), anyString(), anyString()))
//            .thenReturn(true);

        //SPY는 실제 객체를 기반으로 만들어지기 떄문에 Stubber 객체를 사용하여야 한다
        doReturn(true)
            .when(mailSendClient)
            .sendMail(anyString(), anyString(), anyString(), anyString()); //sendMail만 Mocking함

        //when
        boolean result = mailService.sendMail("", "", "", "");

        //then
        assertThat(result).isTrue();

        //save 행위가 1번 불렸는지 검증
        verify(mailSendHistoryRepository, times(1))
            .save(any(MailSendHistory.class));

    }
}