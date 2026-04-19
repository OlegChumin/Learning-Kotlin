package bot

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Проверяет чистые вычисления учебного чат-бота.
 */
class SimpleBotTest {
    /**
     * Должен рассчитывать возраст по остаткам от деления на 3, 5 и 7.
     */
    @Test
    @DisplayName("Возраст рассчитывается по трем остаткам")
    fun ageIsCalculatedFromRemainders() {
        assertEquals(22, calculateAge(remainderByThree = 1, remainderByFive = 2, remainderBySeven = 1))
    }

    /**
     * Должен подставлять имя пользователя в приветственный ответ.
     */
    @Test
    @DisplayName("Ответ с именем пользователя содержит переданное имя")
    fun nameReminderContainsUserName() {
        assertEquals("What a great name you have, Ada!", buildNameReminder("Ada"))
    }
}
