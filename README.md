### animalShelterTBot
Мы международная команда разработчиков из 3 человек. Целью работы нашей команды является разработка решений для автоматизации общения с клиентами.

Итогом данного проекта будет телеграмм-бот, который нужен для помощи людям, обратившимся в приют домашних питомцев. Клиенты, обратившиеся впервые, захотят узнать информацию о приюте, ответы на популярные вопросы, что нужно знать и уметь, чтобы забрать животное из приюта и уточнить как проходит процесс передачи питомца новым хозяевам. 

В течение месяца новые хозяева присылают волонтёрам приюта ежедневный отчет о том, как животное приспосабливается к новой обстановке. Эту часть работы бот тоже поможет автоматизировать.
### Описание работы бота
Ник бота в телеграм @AnimalShelterTBot

Пользователь нажимает "/start" и получает в ответ "Меню начала работы"  / Добро пожаловать! Я бот.
Для начала работы нажми кнопку `Начнем работу`

Пользователь нажимает кнопку `Начнем работу` и получает в ответ меню "Выберите нужный приют":

`Коты` `Собаки`

`Вернутся предыдущее меню`

Пользователь выбирает приют и получает в ответ меню "В следующем меню выберите интересующий вас пункт":

`Узнать информацию о приюте для собак`

`Как взять животное`

`Прислать отчет о питомце`

`Вернутся предыдущее меню`

При выборе кнопки `Узнать информацию о приюте для собак`  пользователь получает сообщение: "Вы нажали на кнопку Информация о приюте для собак"/

" Адрес : Адрес приюта

Контактная информация : Контактная информация приюта

Наименование : Название приюта

Время работы: 9:00-21:00 "

При выборе остальных вариантов пользователем, бот выдаст сообщение "🛑Ведутся ремонтные работы🛑", данная часть меню находится на доработке

---

### Технологии в проекте
Язык и окружение - Java 21, Spring Boot, Hibernate, PostgreSQL, Liquibase. Тестирование - JUnit, Mockito. Для работы с проектом используется Swagger UI.
