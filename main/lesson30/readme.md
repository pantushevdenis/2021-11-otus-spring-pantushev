# Spring boot actuator
Домашняя работа

Пантюшев Д.А.  
2021-11-otus-spring  
otus.ru  
2021-2022 гг.

### Краткое описание
Использовать метрики, healthchecks и logfile

### Цель
Цель: реализовать production-grade мониторинг и прозрачность в приложении
Результат: приложение с применением Spring Boot Actuator.

### Требования
1. Подключить Spring Boot Actuator в приложение.
2. Включить метрики, healthchecks и logfile.
3. 3. Реализовать свой собственный HealthCheck индикатор
4. UI для данных от Spring Boot Actuator реализовывать не нужно.
5. Опционально: переписать приложение на HATEOAS принципах с помощью Spring Data REST Repository Данное задание НЕ засчитывает предыдущие!

### Примечания 
Это домашнее задание является основой для следующих.

### Замечания по реализации
1. Актуатор logfile работает при включении в конфигураци запуска 
active profiele: prod (настроено в application.yml logging.file.name=app.log)
