package az.gdg.msmail.listener;

import az.gdg.msmail.entity.EmailDetails;
import az.gdg.msmail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@RequiredArgsConstructor
public class EmailListener {

    private static final Logger logger = LoggerFactory.getLogger(EmailListener.class);
    private final EmailService emailService;

    @StreamListener(Sink.INPUT)
    public void onNewEmailRequest(EmailDetails emailDetails) {
        logger.debug("Receive email from queue start");
        emailService.processEmailRequest(emailDetails);
        logger.debug("Sent email to customer");
    }
}