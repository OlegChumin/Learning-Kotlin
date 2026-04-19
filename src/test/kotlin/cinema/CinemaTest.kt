package cinema

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Проверяет расчеты кинотеатра без запуска интерактивного меню.
 */
class CinemaTest {
    /**
     * Должен считать цену билета в маленьком зале как единую цену 10 долларов.
     */
    @Test
    @DisplayName("Маленький зал продает все билеты по 10 долларов")
    fun smallCinemaTicketCostsTenDollars() {
        assertEquals(10, calculateTicketPrice(rowNumber = 3, rowCount = 6, totalSeats = 36))
    }

    /**
     * Должен считать цену билета в задней половине большого зала как 8 долларов.
     */
    @Test
    @DisplayName("Большой зал продает задние ряды по 8 долларов")
    fun largeCinemaBackRowTicketCostsEightDollars() {
        assertEquals(8, calculateTicketPrice(rowNumber = 6, rowCount = 9, totalSeats = 81))
    }

    /**
     * Должен считать максимальную выручку с учетом разных цен по половинам большого зала.
     */
    @Test
    @DisplayName("Максимальная выручка большого зала учитывает дешевые задние ряды")
    fun totalIncomeUsesTieredPricingForLargeCinema() {
        assertEquals(720, calculateTotalIncome(rowCount = 9, seatsPerRow = 9))
    }

    /**
     * Должен формировать статистику продаж с процентом до двух знаков.
     */
    @Test
    @DisplayName("Статистика продаж содержит процент с двумя знаками")
    fun statisticsTextContainsFormattedPercentage() {
        val statisticsText = buildStatisticsText(totalSeats = 81, totalIncome = 720, soldSeats = 2, currentIncome = 20)

        assertEquals(
            "\nNumber of purchased tickets: 2\n" +
                    "Percentage: 2.47%\n" +
                    "Current income: $20\n" +
                    "Total income: $720",
            statisticsText,
        )
    }
}
