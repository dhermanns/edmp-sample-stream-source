package de.codecentric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.core.MessagingTemplate;

@SpringBootApplication
@EnableBinding(Source.class)
public class EdmpSampleStreamApplication {

    @Autowired
    MessageWriter messageWriter;

    @Bean
    public MessagingTemplate messagingTemplate() {
        return new MessagingTemplate();
    }

    @Bean
    public MessageWriter messageWriter() {
        return new MessageWriter();
    }

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EdmpSampleStreamApplication.class, args);

        MessageWriter writer = (MessageWriter) context.getBean("messageWriter");
        //writer.writeMessages();
        writer.writeTextMessages();
	}
}

