package minesweeper

import java.util.Random
import java.util.Scanner

/**
 * Консольная игра "Сапер" на поле 9x9.
 */
object Minesweeper {
    /** Сканер для чтения команд пользователя. */
    private val scanner = Scanner(System.`in`)
    /** Игровое поле: 0-8 обозначают количество мин рядом, 11 обозначает мину. */
    private var minefield = Array(9) { Array(9) { 0 } }
    /** Видимое состояние клеток: 0 - скрыта, 1 - открыта, 2 - отмечена. */
    private var visibleCells = Array(9) { Array(9) { 0 } }
    /** Количество еще не найденных настоящих мин. */
    private var remainingMines = 0
    /** Количество ошибочно отмеченных мин. */
    private var wrongMineMarks = 0
    /** Количество безопасных клеток, которые еще не открыты пользователем. */
    private var hiddenSafeCells = 81
    /** Флаг, показывающий, были ли уже размещены мины. */
    private var minesPlaced = false
    /** Флаг первого прохода при проверке соседних клеток. */
    private var isFirstNeighborPass = true
    /** Флаг поражения пользователя. */
    private var isLost = false

    /**
     * Запускает игру, выполняет инициализацию и основной игровой цикл.
     */
    fun run() {
        initialize()
        while ((wrongMineMarks > 0 || remainingMines > 0) && !isLost && hiddenSafeCells != 0) {
            printField()
            fieldAction()
        }
        printField()
        println(if (isLost) "You stepped on a mine and failed!" else "Congratulations! You found all the mines!")
    }

    /**
     * Читает количество мин, готовит поле и размещает мины после первого безопасного хода.
     */
    private fun initialize() {
        remainingMines = getNum("How many mines do you want on the field? ", false)
        if (isOutOfRange(remainingMines, 1..71)) remainingMines = getRange(remainingMines, 1..71)
        hiddenSafeCells -= remainingMines

        while (!minesPlaced) {
            printField()
            fieldAction()
        }
        for (rowIndex in minefield.indices) { // in case user marked bombs before freeing a field
            for (columnIndex in minefield.indices) {
                if (visibleCells[rowIndex][columnIndex] == 2 && minefield[rowIndex][columnIndex] == 11) {
                    wrongMineMarks -= 1
                    remainingMines -= 1
                }
            }
        }
    }

    /**
     * Печатает текущее состояние поля с учетом открытых и отмеченных клеток.
     */
    private fun printField() {
        val strLine = "-|---------|"
        println("\n |123456789|")
        println(strLine)
        for (rowIndex in minefield.indices) {
            print("${rowIndex + 1}|")
            for (columnIndex in minefield[rowIndex].indices) {
                print(
                    when {
                        minefield[rowIndex][columnIndex] == 11 && isLost -> "X"
                        visibleCells[rowIndex][columnIndex] == 2 && !isLost -> "*"
                        visibleCells[rowIndex][columnIndex] == 1 -> {
                            if (minefield[rowIndex][columnIndex] == 0) "/" else minefield[rowIndex][columnIndex]
                        }
                        else -> "."
                    }
                )
            }
            println("|")
        }
        println(strLine)
    }

    /**
     * Читает координаты и действие пользователя для одной клетки.
     */
    private fun fieldAction() {
        var marked = false
        while (!marked) {
            print("Set/unset mine marks or claim a cell as free: ")
            val columnText = scanner.next().trim()
            val rowText = scanner.next().trim()
            val action = scanner.next().trim().lowercase()
            var columnNumber = if (isNumber(columnText)) columnText.toInt() else getNum(columnText)
            var rowNumber = if (isNumber(rowText)) rowText.toInt() else getNum(rowText)
            if (isOutOfRange(rowNumber, 1..9)) rowNumber = getRange(rowNumber, 1..9)
            if (isOutOfRange(columnNumber, 1..9)) columnNumber = getRange(columnNumber, 1..9)
            val rowIndex = rowNumber - 1
            val columnIndex = columnNumber - 1

            when (action) {
                "free" -> marked = free(rowIndex, columnIndex)
                "mine" -> marked = markMine(rowIndex, columnIndex)
            }
        }
    }

    /**
     * Открывает клетку и при необходимости раскрывает соседние пустые области.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     * @return true, если действие успешно обработано.
     */
    private fun free(rowIndex: Int, columnIndex: Int): Boolean {
        if (!minesPlaced) {
            return openFirstCell(rowIndex, columnIndex)
        }
        if (minefield[rowIndex][columnIndex] == 11) {
            isLost = true
            return true
        }
        return openSafeCell(rowIndex, columnIndex)
    }

    /**
     * Обрабатывает первый ход: гарантирует безопасную клетку и только потом размещает мины.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     * @return true после успешной обработки первого хода.
     */
    private fun openFirstCell(rowIndex: Int, columnIndex: Int): Boolean {
        if (visibleCells[rowIndex][columnIndex] == 2) wrongMineMarks -= 1
        visibleCells[rowIndex][columnIndex] = 1
        checkNeighbors(rowIndex, columnIndex)
        isFirstNeighborPass = false
        placeMines()
        clearVisibleCells()
        visibleCells[rowIndex][columnIndex] = 1
        minesPlaced = true
        hiddenSafeCells -= 1
        checkNeighbors(rowIndex, columnIndex)
        return true
    }

    /**
     * Сбрасывает временно открытые клетки, которые использовались при безопасном размещении мин.
     */
    private fun clearVisibleCells() {
        for (rowIndex in visibleCells.indices) {
            for (columnIndex in visibleCells[rowIndex].indices) {
                if (visibleCells[rowIndex][columnIndex] == 1) visibleCells[rowIndex][columnIndex] = 0
            }
        }
    }

    /**
     * Открывает безопасную клетку после размещения мин.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     * @return true, если клетка была открыта, false для уже открытой клетки.
     */
    private fun openSafeCell(rowIndex: Int, columnIndex: Int): Boolean {
        return when (visibleCells[rowIndex][columnIndex]) {
            0 -> {
                revealCell(rowIndex, columnIndex)
                true
            }
            1 -> {
                println("field is already free")
                false
            }
            2 -> {
                revealCell(rowIndex, columnIndex)
                wrongMineMarks -= 1
                true
            }
            else -> false
        }
    }

    /**
     * Открывает клетку и раскрывает соседей, если клетка пустая.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     */
    private fun revealCell(rowIndex: Int, columnIndex: Int) {
        visibleCells[rowIndex][columnIndex] = 1
        hiddenSafeCells -= 1
        if (minefield[rowIndex][columnIndex] == 0) checkNeighbors(rowIndex, columnIndex)
    }

    /**
     * Ставит или снимает отметку мины на выбранной клетке.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     * @return true, если отметка была изменена.
     */
    private fun markMine(rowIndex: Int, columnIndex: Int): Boolean {
        return if (minefield[rowIndex][columnIndex] == 11) {
            toggleRealMineMark(rowIndex, columnIndex)
        } else {
            toggleEmptyCellMark(rowIndex, columnIndex)
        }
    }

    /**
     * Ставит или снимает отметку с настоящей мины.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     * @return true после изменения отметки.
     */
    private fun toggleRealMineMark(rowIndex: Int, columnIndex: Int): Boolean {
        if (visibleCells[rowIndex][columnIndex] == 0) {
            remainingMines -= 1
            visibleCells[rowIndex][columnIndex] = 2
        } else {
            remainingMines += 1
            visibleCells[rowIndex][columnIndex] = 0
        }
        return true
    }

    /**
     * Ставит или снимает ошибочную отметку с безопасной клетки.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     * @return true при изменении отметки, false если открытая клетка не может быть отмечена.
     */
    private fun toggleEmptyCellMark(rowIndex: Int, columnIndex: Int): Boolean {
        return when (visibleCells[rowIndex][columnIndex]) {
            0 -> {
                visibleCells[rowIndex][columnIndex] = 2
                wrongMineMarks += 1
                true
            }
            2 -> {
                visibleCells[rowIndex][columnIndex] = 0
                wrongMineMarks -= 1
                true
            }
            else -> {
                println("open field cannot be marked")
                false
            }
        }
    }

    /**
     * Размещает мины на поле после первого хода пользователя.
     */
    private fun placeMines() {
        repeat(remainingMines) {
            var changed = false
            while (!changed) {
                val rowIndex = (0..8).random()
                val columnIndex = (0..8).random()
                if (minefield[rowIndex][columnIndex] != 11 && visibleCells[rowIndex][columnIndex] != 1) {
                    minefield[rowIndex][columnIndex] = 11
                    changed = true
                    checkNeighbors(rowIndex, columnIndex)
                }
            }
        }
    }

    /**
     * Проверяет соседние клетки вокруг указанной позиции.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     */
    private fun checkNeighbors(rowIndex: Int, columnIndex: Int) {
        for (rowOffset in -1..1) {
            for (columnOffset in -1..1) {
                if (rowOffset == 0 && columnOffset == 0) continue
                val neighborRow = rowIndex + rowOffset
                val neighborColumn = columnIndex + columnOffset
                if (isValidSafeCell(neighborRow, neighborColumn)) {
                    updateNeighbor(neighborRow, neighborColumn)
                }
            }
        }
    }

    /**
     * Проверяет, что соседняя клетка находится на поле и не содержит мину.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     * @return true для безопасной клетки внутри поля.
     */
    private fun isValidSafeCell(rowIndex: Int, columnIndex: Int): Boolean =
        rowIndex in minefield.indices &&
                columnIndex in minefield[rowIndex].indices &&
                minefield[rowIndex][columnIndex] != 11

    /**
     * Обрабатывает соседнюю клетку: считает мину рядом или раскрывает безопасную область.
     *
     * @param rowIndex индекс строки.
     * @param columnIndex индекс столбца.
     */
    private fun updateNeighbor(rowIndex: Int, columnIndex: Int) {
        if (!minesPlaced && !isFirstNeighborPass) {
            minefield[rowIndex][columnIndex] += 1
        } else {
            if (visibleCells[rowIndex][columnIndex] != 1) {
                if (visibleCells[rowIndex][columnIndex] == 2) wrongMineMarks -= 1
                visibleCells[rowIndex][columnIndex] = 1
                if (!isFirstNeighborPass) hiddenSafeCells -= 1
                if (minefield[rowIndex][columnIndex] == 0 && !isFirstNeighborPass) checkNeighbors(rowIndex, columnIndex)
            }
        }
    }

    /**
     * Читает целое число из консоли и повторяет запрос при неверном формате.
     *
     * @param text текст приглашения.
     * @param defaultMessage нужно ли сразу показывать сообщение об ошибке формата.
     * @return введенное целое число.
     */
    private fun getNum(text: String, defaultMessage: Boolean = true): Int {
        val invalidNumberMessage = " was not a number, please try again: "
        var userInput = text
        var shouldShowError = defaultMessage

        do {
            print(if (shouldShowError) userInput + invalidNumberMessage else userInput)
            if (!shouldShowError) shouldShowError = true
            userInput = readLine()!!
        } while (!isNumber(userInput))

        return userInput.toInt()
    }

    /**
     * Запрашивает число, пока оно не попадет в указанный диапазон.
     *
     * @param currentNumber исходное значение.
     * @param range допустимый диапазон.
     * @return число из допустимого диапазона.
     */
    private fun getRange(currentNumber: Int, range: IntRange): Int {
        var numberInRange = currentNumber
        do {
            numberInRange = getNum("$numberInRange was out of range. Please enter a number ${range.first} to ${range.last}: ", false)
        } while (isOutOfRange(numberInRange, range))
        return numberInRange
    }

    /**
     * Проверяет, находится ли число вне диапазона.
     *
     * @param number проверяемое число.
     * @param range допустимый диапазон.
     * @return true, если число вне диапазона.
     */
    fun isOutOfRange(number: Int, range: IntRange) = number !in range

    /**
     * Проверяет, можно ли преобразовать строку в целое число.
     *
     * @param number проверяемая строка.
     * @return true, если строка является целым числом.
     */
    private fun isNumber(number: String) = number.toIntOrNull() != null

    /**
     * Возвращает случайное число из диапазона.
     *
     * @return случайное значение в пределах диапазона.
     */
    private fun IntRange.random() = Random().nextInt(endInclusive + 1 - start) + start
}

/**
 * Точка входа для запуска игры "Сапер".
 */
fun main() {
    Minesweeper.run()
}
