package minesweeper

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Проверяет вспомогательную валидацию игры "Сапер".
 */
class MinesweeperTest {
    /**
     * Должен определять число вне допустимого диапазона.
     */
    @Test
    @DisplayName("Число вне диапазона считается некорректным")
    fun numberOutsideRangeIsInvalid() {
        assertTrue(Minesweeper.isOutOfRange(10, 1..9))
    }

    /**
     * Должен принимать число внутри допустимого диапазона.
     */
    @Test
    @DisplayName("Число внутри диапазона считается корректным")
    fun numberInsideRangeIsValid() {
        assertFalse(Minesweeper.isOutOfRange(5, 1..9))
    }
}
