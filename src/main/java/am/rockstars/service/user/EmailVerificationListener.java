package am.rockstars.service.user;

import am.rockstars.dto.EmailPayload;
import am.rockstars.service.EmailService;
import lombok.NonNull;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class EmailVerificationListener implements ApplicationListener<EmailVerificationEvent> {

    private final EmailService emailService;

    public EmailVerificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(@NonNull final EmailVerificationEvent event) {
        final EmailPayload emailPayload = EmailPayload.builder()
                .recipients(Collections.singleton(event.getEmail()))
                .subject(event.getSubject())
                .content(event.getContent())
                .build();
        emailService.send(emailPayload);
    }
}
