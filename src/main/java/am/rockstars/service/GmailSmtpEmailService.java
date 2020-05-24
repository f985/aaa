package am.rockstars.service;

import am.rockstars.dto.EmailPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GmailSmtpEmailService implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void send(final EmailPayload payload) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(payload.getSubject());
        message.setText(payload.getContent());
        message.setTo(payload.getRecipients().toArray(new String[0]));
        javaMailSender.send(message);
    }
}
