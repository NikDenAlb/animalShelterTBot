package selfConstructed.animalShelterTBot.service.messageText;

import lombok.Getter;

@Getter
public enum ReplyForUserInReports {
    PET_PHOTO("""
            НАЧАЛО ЗАПОЛНЕНИЯ ОТЧЁТА

            Пришлите фото питомца"""),
    DIET("Пришлите описание диеты питомца"),
    GENERAL_INFORMATION("Пришлите описание общего самочувствия и привыкания к новому месту"),
    CHANGE_IN_BEHAVIOR("Пришлите описание изменения в поведении: отказ от старых привычек, приобретение новых");

    private final String message;

    ReplyForUserInReports(String message) {
        this.message = message;
    }
}
