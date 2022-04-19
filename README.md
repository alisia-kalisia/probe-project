# Probe Project

Приложение для практики тестирования REST сервиса через API и UI. Функционал выдуман
и представляет из себя супер упрощенную эмуляцию открытия счета клиенту банка.

### Тут есть 3 бага
В приложении намерено допущено минимум **3 бага** серьезностью не ниже **Major**.
Попробуйте отыскать их все. Речь идет только про функциональные баги,
UI\UX и опечатки не в счет.

### Тестовые пользователи
* Пупов Пуп Пупович ИНН 111111111111 (нет комплаенс блокировок)
* Пупова Пупа Пуповна ИНН 111111111112 (есть комплаенс блокировки)

### Функциональные требования
В приложении есть 3 основные сущности:
* CLIENT
* PROCESS
* ACCOUNT

Клиенты уже заведены (2 тестовых). Необходимо проверить возможность открытия счета
на них. Для этого:

* найти клиента по ИНН
* начать новый процесс (одновременно у клиента может быть только 1 незавершенный процесс)
* дождаться завершения комплаенс проверки на новом процессе (занимает 10-20 секунд)
* если проверка прошла успешно, резервируем счет в выбранной валюте (можно зарезервировать более 1 счета)
* после этого зарезервированные счета можно отправить на открытие (занимает 10-20 секунд)
* после того как процедура открытия счетов завершится, процесс также завершится
* чтобы открыть последующие счета, необходимо создать новый процесс и пройти все остальные шаги

Описание статусов процесса (PROCESS_STATUSES) и счета (ACCOUNT_STATUSES), а также
справочник валют представлены в интерфейсе пользователя или при вызове
следующих эндпоинтов:

* http://localhost:8080/process-statuses
* http://localhost:8080/account-statuses
* http://localhost:8080/dictionaries/currencies

### Запуск в Docker
docker run --rm -p 8080:8080 aleksrad/probe-project

### Запуск через Maven
Для запуска выполните следующие команды:

`mvn clean package` или `./mvnw clean package`

затем из папки с проектом

`java -jar ./target/probeproject-1.0.jar`

### После запуска приложение доступно через
#### UI
http://localhost:8080/web/app
![image](https://user-images.githubusercontent.com/10981830/164065630-d904793e-427b-4059-b6f5-d2e3edb1b278.png)
#### API
Swagger:
http://localhost:8080/probe-project/swagger-ui.html

### Особенности
Для запуска через Maven вам потребуется **Java 11**

В проекте используется openapi (swagger) для генерирования интерфейсов
и dto. Необходимые классы станут доступны после выполнения команды мавен:

`mvn compile` или `./mvnw compile`

в следующих packages:

* ru.axl.probeproject.api
* ru.axl.probeproject.model

В проекте используется база данных H2. Таблицы будут предзаполнены скриптом **data.sql**,
автоматически при старте приложения. После запуска к БД можно обратиться так:

http://localhost:8080/h2-console
