package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class ShelterInfoSender {
    private final TelegramBot telegramBot;


    public ShelterInfoSender(TelegramBot telegramBot) {
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
        telegramBot.execute(sendMessage);
    }
}
