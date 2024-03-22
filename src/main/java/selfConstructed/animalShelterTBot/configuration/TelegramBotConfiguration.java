package selfConstructed.animalShelterTBot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TelegramBotConfiguration {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotConfiguration.class);
    @Value("${telegram.bot.token}")
    private String token;


    @Bean
    public TelegramBot telegramBot() {
        logger.info("Создан объект телеграмбота");
        return new TelegramBot(token);
    }
}