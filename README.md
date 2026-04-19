# Learning Kotlin

Этот проект - сборник учебных программ и материалов, которые использовались для изучения Kotlin.

Здесь будут собраны учебные примеры, эксперименты, заметки и небольшие проекты, связанные с Kotlin и JVM-разработкой.

## Цель

Сохранить в одном месте весь прогресс по изучению Kotlin, чтобы к примерам и наработкам было удобно возвращаться позже.

## Технологии

- Kotlin
- Gradle
- JVM

## Программы на Kotlin

| Программа | Файл | Команда запуска | Описание |
| --- | --- | --- | --- |
| Hello Kotlin | `src/main/kotlin/Main.kt` | `.\gradlew.bat runHello` | Простой пример с приветствием и счетчиком. |
| Cinema | `src/main/kotlin/Cinema.kt` | `.\gradlew.bat runCinema` | Интерактивное бронирование мест в кинотеатре. |
| Minesweeper | `src/main/kotlin/Minesweeper.kt` | `.\gradlew.bat runMinesweeper` | Консольная игра "Сапер" на поле 9x9. |
| Simple Bot | `src/main/kotlin/SimpleBot.kt` | `.\gradlew.bat runSimpleBot` | Учебный чат-бот с вопросами и счетчиком. |
| Unit Converter | `src/main/kotlin/UnitConverter.kt` | `.\gradlew.bat runUnitConverter` | Конвертер длины, веса и температуры. |
| Zookeeper | `src/main/kotlin/Zookeeper.kt` | `.\gradlew.bat runZookeeper` | Просмотр ASCII-камер зоопарка. |

## Проверка проекта

```powershell
.\gradlew.bat build
```

Отдельную программу можно запустить командой из таблицы выше. Для завершения интерактивных программ используйте пункт выхода в меню или команду `exit`, если она поддерживается программой.
