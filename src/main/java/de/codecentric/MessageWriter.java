package de.codecentric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;

import java.util.Date;

/**
 * Created by dirk on 14.07.16.
 */
public class MessageWriter {

    private static Logger logger = LoggerFactory.getLogger(MessageWriter.class);

    @Autowired
    private Source source;

    @Autowired
    private MessagingTemplate messagingTemplate;

    public void writeMessages() {
        for (int i = 0; i < 1000000; i++) {
            messagingTemplate.send(source.output(), MessageBuilder.withPayload(new TimeInfo(i, new Date().getTime()+"","Label")).build());
        }
    }

    public void writeTextMessages() {

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {

            messagingTemplate.send(source.output(), MessageBuilder.withPayload(i).build());
            //messagingTemplate.send(source.output(), MessageBuilder.withPayload(new TimeInfo(i, String.valueOf(System.currentTimeMillis()), "")).build());
            if (i % 10000 == 0) {
                logger.info("Writing Message nr " + i);
            }
        }

        long endTime = System.currentTimeMillis();
        double durationInSeconds = (endTime - startTime) / 1000;
        logger.info(String.format("Send %s messages per second", 1000000.0 / durationInSeconds));
        logger.info("Overall time was " + durationInSeconds + "sec");
    }

    public class TimeInfo{

        private Integer counter;
        private String time;
        private String label;

        public TimeInfo(Integer counter, String time, String label) {
            super();
            this.counter= counter;
            this.time = time;
            this.label = label;
        }

        public String getTime() {
            return time;
        }

        public String getLabel() {
            return label;
        }

        public Integer getCounter() {
            return counter;
        }

    }
}
