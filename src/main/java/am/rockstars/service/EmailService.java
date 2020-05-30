package am.rockstars.service;

import am.rockstars.dto.EmailPayload;

public interface EmailService {

    void send(EmailPayload payload);
}
