package converter

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Проверяет конвертацию единиц измерения без консольного ввода.
 */
class UnitConverterTest {
    /**
     * Должен конвертировать длину через базовые коэффициенты.
     */
    @Test
    @DisplayName("Метры конвертируются в километры")
    fun metersAreConvertedToKilometers() {
        assertEquals("1000.0 meters is 1.0 kilometer", convertMeasurement("1000 meters to kilometers"))
    }

    /**
     * Должен запрещать отрицательные значения для веса.
     */
    @Test
    @DisplayName("Отрицательный вес возвращает ошибку")
    fun negativeWeightReturnsError() {
        assertEquals("Weight shouldn't be negative", convertMeasurement("-5 pounds to ounces"))
    }

    /**
     * Должен конвертировать температуру по специальным формулам.
     */
    @Test
    @DisplayName("Градусы Цельсия конвертируются в Фаренгейты")
    fun celsiusIsConvertedToFahrenheit() {
        assertEquals("0.0 degrees Celsius is 32.0 degrees Fahrenheit", convertMeasurement("0 degrees Celsius to Fahrenheit"))
    }

    /**
     * Должен сообщать об ошибке при конвертации между разными типами величин.
     */
    @Test
    @DisplayName("Разные типы величин нельзя конвертировать")
    fun differentMeasurementTypesCannotBeConverted() {
        assertEquals("Conversion from meters to grams is impossible", convertMeasurement("1 meter to gram"))
    }
}
