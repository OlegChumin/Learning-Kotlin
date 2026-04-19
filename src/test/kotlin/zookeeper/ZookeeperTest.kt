package zookeeper

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Проверяет выбор камеры зоопарка по номеру habitat.
 */
class ZookeeperTest {
    /**
     * Должен возвращать изображение камеры для корректного номера habitat.
     */
    @Test
    @DisplayName("Корректный номер habitat возвращает камеру животного")
    fun validHabitatNumberReturnsAnimalCamera() {
        assertEquals(CAMEL_CAMERA, findAnimalCamera(0))
    }

    /**
     * Должен возвращать null для номера вне доступного диапазона.
     */
    @Test
    @DisplayName("Номер вне диапазона не возвращает камеру")
    fun invalidHabitatNumberReturnsNull() {
        assertNull(findAnimalCamera(6))
    }
}
