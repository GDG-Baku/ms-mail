package az.gdg.msmail.service;

import az.gdg.msmail.dto.MailDTO;
import az.gdg.msmail.util.MailUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    private final JavaMailSender sender;
    @Value("${spring.mail.username}")
    private final String senderMail;

    public void processMailRequest(MailDTO mailDTO) {
        logger.info("ActionLog.processMailRequest.start");
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            message.setFrom(new InternetAddress(senderMail, "Rubber Duck"));
            helper.setText(mailDTO.getBody(), true);
            helper.setSubject(mailDTO.getSubject());
            InternetAddress[] recipients = InternetAddress.parse(MailUtil.listToString(mailDTO.getTo()));
            message.setRecipients(Message.RecipientType.TO, recipients);
        } catch (MessagingException e) {
            logger.error("Thrown.MessagingException.Thrown", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("Thrown.UnsupportedEncodingException.Thrown", e);
        }
        sender.send(message);
        logger.info("ActionLog.processMailRequest.end");
    }

}