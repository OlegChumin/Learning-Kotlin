# Learning Kotlin

Проект собирает учебные программы, уроки и небольшие консольные проекты, которые используются для изучения Kotlin и JVM-разработки.

Материалы из бывшего подпроекта `Udemy_Kotlin_Base_1_2` перенесены в основной Gradle-проект. Отдельной сборки, wrapper и `build.gradle.kts` для Udemy-материалов больше нет.

## Технологии

- Kotlin JVM
- Gradle
- JDK 21
- JUnit 5
- kotlin-test

## Структура

| Путь | Содержимое |
| --- | --- |
| `src/main/kotlin` | Основные консольные программы и учебные уроки. |
| `src/main/kotlin/lesson_*` | Уроки 1-10 из Udemy-материалов. |
| `src/main/kotlin/part_two` | Материалы второй части Udemy-курса. |
| `src/main/kotlin/udemy/Main.kt` | Демонстрационный `main` из бывшего Udemy-подпроекта. |
| `src/test/kotlin` | Тесты для чистой логики программ и уроков. |

## Программы

| Программа | Файл | Команда запуска | Описание |
| --- | --- | --- | --- |
| Hello Kotlin | `src/main/kotlin/MainSample.kt` | `.\gradlew.bat runHello` | Простой пример с приветствием и счетчиком. |
| Cinema | `src/main/kotlin/Cinema.kt` | `.\gradlew.bat runCinema` | Интерактивное бронирование мест в кинотеатре. |
| Minesweeper | `src/main/kotlin/Minesweeper.kt` | `.\gradlew.bat runMinesweeper` | Консольная игра "Сапер" на поле 9x9. |
| Simple Bot | `src/main/kotlin/SimpleBot.kt` | `.\gradlew.bat runSimpleBot` | Учебный чат-бот с вопросами и счетчиком. |
| Unit Converter | `src/main/kotlin/UnitConverter.kt` | `.\gradlew.bat runUnitConverter` | Конвертер длины, веса и температуры. |
| Zookeeper | `src/main/kotlin/Zookeeper.kt` | `.\gradlew.bat runZookeeper` | Просмотр ASCII-камер зоопарка. |

## Тесты

Тесты находятся в `src/test/kotlin`. Для тестов используются JUnit 5 и `kotlin-test`.

Покрытая логика:

- расчеты кинотеатра;
- расчет возраста и сообщения Simple Bot;
- конвертация единиц измерения;
- проверка диапазонов Minesweeper;
- выбор камеры Zookeeper;
- логика доступа к контенту из `lesson_05`.

Каждый тест оформлен с KDoc и `@DisplayName`.

## Команды

Полная проверка проекта:

```powershell
.\gradlew.bat build
```

Запуск только тестов:

```powershell
.\gradlew.bat test
```

Запуск отдельной программы:

```powershell
.\gradlew.bat runCinema
```

Для интерактивных программ ввод выполняется через консоль. Для завершения используйте пункт выхода в меню или команду `exit`, если программа ее поддерживает.

## Поддерживаемые соглашения

- Имена переменных и функций приведены к осмысленному `lowerCamelCase`.
- Константы оформлены в `UPPER_SNAKE_CASE`.
- Чистая логика по возможности вынесена из консольного ввода, чтобы ее можно было тестировать.
- Закомментированный мертвый код и очевидные Sonar-замечания удаляются, если они не несут учебной ценности.
