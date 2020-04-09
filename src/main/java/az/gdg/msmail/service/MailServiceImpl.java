package az.gdg.msmail.service;

import az.gdg.msmail.dto.MailDTO;
import az.gdg.msmail.util.MailUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    private final JavaMailSender sender;


    public void processMailRequest(MailDTO mailDTO) {
        logger.info("ActionLog.processMailRequest.start");
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            helper.setText(mailDTO.getMailBody(), true);
            helper.setSubject(mailDTO.getMailSubject());
            InternetAddress[] recipients = InternetAddress.parse(MailUtil.listToString(mailDTO.getMailTo()));
            message.setRecipients(Message.RecipientType.TO, recipients);
        } catch (MessagingException e) {
            logger.error("ActionLog.MessagingException.Thrown", e);
        }
        sender.send(message);
        logger.info("ActionLog.processMailRequest.end");
    }

}