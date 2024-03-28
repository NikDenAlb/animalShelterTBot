package selfConstructed.animalShelterTBot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.Getter;
import org.springframework.stereotype.Service;
import selfConstructed.animalShelterTBot.keyboard.Keyboard;
import selfConstructed.animalShelterTBot.repository.ReportRepository;
import selfConstructed.animalShelterTBot.repository.UserRepository;
import selfConstructed.animalShelterTBot.service.messageText.ReplyForUserInReports;

@Service
public class ProcessReport {

    private final TelegramBot telegramBot;
    private final Keyboard keyboard;
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    @Getter
    private Integer messageId;

    public ProcessReport(TelegramBot telegramBot, Keyboard keyboard, ReportRepository reportRepository,
                         UserRepository userRepository) {
        this.telegramBot = telegramBot;
        this.keyboard = keyboard;
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public void processReportCallBack(long chatId, CallbackQuery callbackQuery) {
        if (userRepository.findByStatusAdopted(chatId).isPresent()) {
            SendMessage sendMessage = new SendMessage(chatId, ReplyForUserInReports.PET_PHOTO.getMessage());
            SendResponse sendResponse = telegramBot.execute(sendMessage);
            if (sendResponse.isOk()) {
                messageId = sendResponse.message().messageId();
            }
        } else {
            SendMessage sendMessage = new SendMessage(chatId, "\uD83E\uDD21 Вы еще не являетесь счастливым " +
                    "обладателем лучшего друга");
            SendResponse sendResponse = telegramBot.execute(sendMessage);
            if (sendResponse.isOk()) {
                messageId = sendResponse.message().messageId();
            }
        }
    }
}