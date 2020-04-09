package az.gdg.msmail.service;


import az.gdg.msmail.dto.MailDTO;

public interface MailService {
    void processMailRequest(MailDTO mailDTO);
}
