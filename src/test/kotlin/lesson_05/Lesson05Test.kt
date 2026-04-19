package lesson_05

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Проверяет выбор сообщения о доступе к контенту по возрасту пользователя.
 */
class Lesson05Test {
    /**
     * Должен разрешать полный доступ совершеннолетнему пользователю.
     */
    @Test
    @DisplayName("Совершеннолетний пользователь получает полный доступ")
    fun adultUserGetsFullAccessMessage() {
        assertEquals("Показать экран со скрытым контентом", getContentAccessMessage(18))
    }

    /**
     * Должен показывать ограниченный контент пользователям 16 и 17 лет.
     */
    @Test
    @DisplayName("Пользователь 16 лет получает ограниченный доступ")
    fun sixteenYearsOldUserGetsLimitedAccessMessage() {
        assertEquals("Показать экран с ограниченным контентом", getContentAccessMessage(16))
    }

    /**
     * Должен возвращать несовершеннолетнего пользователя на главный экран.
     */
    @Test
    @DisplayName("Пользователь младше 16 лет возвращается на главный экран")
    fun childUserGetsMainScreenMessage() {
        assertEquals("Вернуть на главный экран приложения", getContentAccessMessage(15))
    }
}
