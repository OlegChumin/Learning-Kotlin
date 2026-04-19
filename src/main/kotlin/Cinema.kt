package cinema

import java.util.Locale

/**
 * Запускает интерактивную систему бронирования мест в кинотеатре.
 */
fun main() {
    val rowCount = getNum("Enter the number of rows:")
    val seatsPerRow = getNum("Enter the number of seats in each row:")

    if (rowCount < 1 || seatsPerRow < 1) {
        println("please enter positive numbers greater than zero for rows and seats.")
        main()
    } else {
        val cinemaHall = Array(rowCount) { Array(seatsPerRow) { 'S' } }
        val totalSeats = rowCount * seatsPerRow
        val totalIncome = calculateTotalIncome(rowCount, seatsPerRow)
        var soldSeats = 0
        var currentIncome = 0
        var command = getNum(printMenu())

        while (command != 0) {
            when (command) {
                1 -> printSeats(cinemaHall)
                2 -> {
                    currentIncome += bookSeat(cinemaHall, totalSeats)
                    soldSeats++
                }

                3 -> statistics(totalSeats, totalIncome, soldSeats, currentIncome)
            }
            command = getNum(printMenu())
        }
    }
}

/**
 * Читает целое число из консоли и повторяет запрос, пока пользователь не введет корректное значение.
 *
 * @param text текст приглашения для пользователя.
 * @param defaultMessage нужно ли сразу показывать сообщение об ошибке формата.
 * @return введенное пользователем целое число.
 */
fun getNum(text: String, defaultMessage: Boolean = false): Int {
    val invalidNumberMessage = " was not a number, please try again: "
    var userInput = text
    var shouldShowError = defaultMessage

    do {
        userInput = getString(if (shouldShowError) userInput + invalidNumberMessage else userInput)
        if (!shouldShowError) shouldShowError = true
    } while (!isNumber(userInput))

    return userInput.toInt()
}

/**
 * Формирует текст главного меню кинотеатра.
 *
 * @return строка с доступными командами.
 */
fun printMenu(): String {
    return "\n1. Show the seats\n" +
            "2. Buy a ticket\n" +
            "3. Statistics\n" +
            "0. Exit"
}

/**
 * Печатает схему зала с доступными и купленными местами.
 *
 * @param theater матрица мест кинотеатра.
 */
fun printSeats(cinemaHall: Array<Array<Char>>) {
    print("\nCinema:\n ")
    for (seatIndex in cinemaHall[0].indices) print(" ${seatIndex + 1}")
    println()

    for (rowIndex in cinemaHall.indices) {
        print(rowIndex + 1)
        for (seatState in cinemaHall[rowIndex]) print(" $seatState")
        println()
    }
}

/**
 * Покупает одно место и возвращает стоимость билета.
 *
 * @param theater матрица мест кинотеатра.
 * @param totalSeats общее количество мест в зале.
 * @return цена успешно купленного билета.
 */
fun bookSeat(cinemaHall: Array<Array<Char>>, totalSeats: Int): Int {
    return try {
        val row = getNum("\nEnter a row number:")
        val seat = getNum("Enter a seat number in that row:")
        val price = calculateTicketPrice(row, cinemaHall.size, totalSeats)

        if (cinemaHall[row - 1][seat - 1] == 'B') {
            println("\nThat ticket has already been purchased!")
            bookSeat(cinemaHall, totalSeats)
        } else {
            cinemaHall[row - 1][seat - 1] = 'B'
            println("\nTicket price: $$price")
            price
        }
    } catch (e: IndexOutOfBoundsException) {
        println("\nWrong input!")
        bookSeat(cinemaHall, totalSeats)
    }
}

/**
 * Рассчитывает цену билета для указанного ряда.
 *
 * @param rowNumber номер ряда с единицы.
 * @param rowCount количество рядов в зале.
 * @param totalSeats общее количество мест.
 * @return стоимость билета.
 */
fun calculateTicketPrice(rowNumber: Int, rowCount: Int, totalSeats: Int): Int =
    if (totalSeats > 60 && rowNumber > rowCount / 2) 8 else 10

/**
 * Рассчитывает максимально возможную выручку зала.
 *
 * @param rowCount количество рядов.
 * @param seatsPerRow количество мест в каждом ряду.
 * @return максимальная выручка при продаже всех билетов.
 */
fun calculateTotalIncome(rowCount: Int, seatsPerRow: Int): Int {
    val totalSeats = rowCount * seatsPerRow
    return if (totalSeats > 60) {
        rowCount / 2 * 10 * seatsPerRow + (rowCount / 2 + rowCount % 2) * 8 * seatsPerRow
    } else {
        totalSeats * 10
    }
}

/**
 * Формирует текст статистики продаж.
 *
 * @param totalSeats общее количество мест.
 * @param totalIncome максимально возможная выручка.
 * @param soldSeats количество проданных мест.
 * @param currentIncome текущая выручка.
 * @return готовая строка статистики.
 */
fun buildStatisticsText(totalSeats: Int, totalIncome: Int, soldSeats: Int, currentIncome: Int): String {
    val soldSeatsPercentage = String.format(Locale.US, "%.2f", soldSeats.toDouble() / totalSeats * 100)
    return "\nNumber of purchased tickets: $soldSeats\n" +
            "Percentage: $soldSeatsPercentage%\n" +
            "Current income: $$currentIncome\n" +
            "Total income: $$totalIncome"
}

/**
 * Выводит статистику продаж билетов.
 *
 * @param totalSeats общее количество мест.
 * @param totalIncome максимально возможная выручка.
 * @param soldSeats количество проданных мест.
 * @param currentIncome текущая выручка.
 */
fun statistics(totalSeats: Int, totalIncome: Int, soldSeats: Int, currentIncome: Int) {
    println(buildStatisticsText(totalSeats, totalIncome, soldSeats, currentIncome))
}

/**
 * Выводит приглашение и читает строку из консоли.
 *
 * @param text текст приглашения.
 * @return введенная строка.
 */
fun getString(text: String): String {
    println(text)
    return readln()
}

/**
 * Проверяет, можно ли преобразовать строку в целое число.
 *
 * @param number проверяемая строка.
 * @return true, если строка является целым числом.
 */
fun isNumber(number: String) = number.toIntOrNull() != null
