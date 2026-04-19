package cinema

/**
 * Запускает интерактивную систему бронирования мест в кинотеатре.
 */
fun main() {
    val rows = getNum("Enter the number of rows:")
    val seats = getNum("Enter the number of seats in each row:")

    if (rows < 1 || seats < 1) {
        println("please enter positive numbers greater than zero for rows and seats.")
        main()
    } else {
        val theater = Array(rows) { Array(seats) { 'S' } }
        val totalSeats = rows * seats
        val totalIncome = if (totalSeats > 60) rows / 2 * 10 * seats +
                (rows / 2 + rows % 2) * 8 * seats else totalSeats * 10
        var soldSeats = 0
        var currentIncome = 0
        var command = getNum(printMenu())

        while (command != 0) {
            when (command) {
                1 -> printSeats(theater)
                2 -> {
                    currentIncome += bookSeat(theater, totalSeats)
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
    val strErrorNum = " was not a number, please try again: "
    var num = text
    var default = defaultMessage

    do {
        num = getString(if (default) num + strErrorNum else num)
        if (!default) default = true
    } while (!isNumber(num))

    return num.toInt()
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
fun printSeats(theater: Array<Array<Char>>) {
    print("\nCinema:\n ")
    for (num in theater[0].indices) print(" ${num + 1}")
    println()

    for (rowNum in theater.indices) {
        print(rowNum + 1)
        for (seat in theater[rowNum]) print(" $seat")
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
fun bookSeat(theater: Array<Array<Char>>, totalSeats: Int): Int {
    return try {
        val row = getNum("\nEnter a row number:")
        val seat = getNum("Enter a seat number in that row:")
        val price = if (totalSeats > 60 && row > theater.size / 2) 8 else 10

        if (theater[row - 1][seat - 1] == 'B') {
            println("\nThat ticket has already been purchased!")
            bookSeat(theater, totalSeats)
        } else {
            theater[row - 1][seat - 1] = 'B'
            println("\nTicket price: $$price")
            price
        }
    } catch (e: IndexOutOfBoundsException) {
        println("\nWrong input!")
        bookSeat(theater, totalSeats)
    }
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
    val percent = "%.2f".format(soldSeats.toDouble() / totalSeats * 100)
    val statString = "\nNumber of purchased tickets: $soldSeats\n" +
            "Percentage: $percent%\n" +
            "Current income: $$currentIncome\n" +
            "Total income: $$totalIncome"

    println(statString)
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
