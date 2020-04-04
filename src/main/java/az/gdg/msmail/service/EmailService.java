package az.gdg.msmail.service;

import az.gdg.msmail.entity.EmailDetails;

public interface EmailService {
    void processEmailRequest(EmailDetails emailDetails);
}
