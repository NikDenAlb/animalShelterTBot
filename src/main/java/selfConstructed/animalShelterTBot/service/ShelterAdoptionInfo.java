package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShelterAdoptionInfo {
    private final Logger logger = LoggerFactory.getLogger(WelcomeHandler.class);
    private final TelegramBot telegramBot;
    @Getter
    private Integer messageId;


    public ShelterAdoptionInfo(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendAdoptionInfo(Long chatId) {
        String info = """
                Чтобы взять животное из нашего приюта, пожалуйста, следуйте этим шагам:
                1. Посетите наш приют в указанное время.
                2. Обратитесь к нашему персоналу для получения информации о доступных животных.
                3. Познакомьтесь с животными, которые вам понравились.
                4. Если вы найдете подходящего питомца, обсудите условия его усыновления с нашим персоналом.
                5. Заполните анкету на усыновление и пройдите с нами процедуру проверки.
                6. Подпишите договор об усыновлении и заберите вашего нового друга домой!

                Спасибо, что рассматриваете возможность усыновления животного из нашего приюта!""";
        SendMessage sendMessage = new SendMessage(chatId, info);
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        if (sendResponse.isOk()) {
            messageId = sendResponse.message().messageId();
            logger.info("Отправлено сообщение об усыновлении в чат {}: {}", chatId, info);
        } else {
            logger.error("Не удалось отправить сообщение об усыновлении в чат {}", chatId);
        }
    }
}