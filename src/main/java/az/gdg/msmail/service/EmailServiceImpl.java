package az.gdg.msmail.service;

import az.gdg.msmail.entity.EmailDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender sender;


    public void processEmailRequest(EmailDetails emailDetails){
        logger.info("ActionLog.processEmailRequest.start");
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            helper.setTo(emailDetails.getEmailTo());
            helper.setText(emailDetails.getEmailBody(),true);
            helper.setSubject(emailDetails.getEmailSubject());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
        logger.info("ActionLog.processEmailRequest.success");
    }

}