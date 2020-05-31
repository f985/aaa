package am.rockstars.service.user;

import am.rockstars.dto.EmailPayload;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmailVerificationEvent extends ApplicationEvent {

    private final String email;
    private final String subject;
    private final String content;

    public EmailVerificationEvent(final EmailPayload emailPayload) {
        super(emailPayload);
        this.content = emailPayload.getContent();
        this.subject = emailPayload.getSubject();
        this.email = emailPayload.getRecipients().iterator().next();
    }
}
