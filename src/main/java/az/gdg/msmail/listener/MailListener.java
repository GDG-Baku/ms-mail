package az.gdg.msmail.listener;

import az.gdg.msmail.dto.MailDTO;
import az.gdg.msmail.service.MailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@AllArgsConstructor
public class MailListener {

    private static final Logger logger = LoggerFactory.getLogger(MailListener.class);
    private final MailService mailService;

    @StreamListener(Sink.INPUT)
    public void onNewMailRequest(MailDTO mailDTO) {
        logger.debug("onNewMailRequest mail {} from queue start", mailDTO.getMailTo());
        mailService.processMailRequest(mailDTO);
        logger.debug("onNewMailRequest mail {} from queue end", mailDTO.getMailTo());
    }
}