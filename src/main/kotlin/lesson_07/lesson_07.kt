package lesson_07

fun main() {
    // диапазоны
    val closedIntRange = 42..442
    val untilIntRange = 42 until 442
    val openEndIntRange = 42..<442
    val lowercaseLetterRange = 'a'..'z'
    val doubleRange = 42.1..442.5 // невозможно итерироваться
    val floatRange = 42.1F..442.5F // невозможно итерироваться

    val evenStepRange = 42..442 step 2 // по умолчанию step == 1
    val descendingStepRange = 422 downTo 42 step 2 // по умолчанию step == 1

    println(52 in closedIntRange)
    println(52 !in closedIntRange)
    println(openEndIntRange)
    println(lowercaseLetterRange)
    println(doubleRange)
    println(floatRange)
    println(evenStepRange)
    println(descendingStepRange)

    // Цикл for используется, если количество итераций известно.
    for (secondsLeft in 5 downTo 1) {
        print("\r")
        print("Реклама закончится через $secondsLeft")
        Thread.sleep(500)
    }
    println()

    for (number in untilIntRange step 16) {
        print("$number ")
    }
    println()

    //break
    println("break")
    for (secondsLeft in 5 downTo 1) {
        if (secondsLeft == 3) {
            println()
            println("Пользователь нажал на кнопку \"Пропустить\"")
            break
        }
        print("\r")
        print("Реклама закончится через $secondsLeft")
        Thread.sleep(500)
    }
    println()

    //continue
    println("continue")
    for (secondsLeft in 5 downTo 1) {
        if (secondsLeft == 3) {
            println()
            println("i=3 не будет распечатана")
            continue
        }
        print("\r")
        print("Реклама закончится через $secondsLeft")
        Thread.sleep(500)
    }
    println()
    println("Продолжение работы программы вне цикла - после continue")


    // Демонстрация return.
    println("return")
    for (secondsLeft in 5 downTo 1) {
        if (secondsLeft == 3) {
            println()
            println("завершение работы main")
            return
        }
        print("\r")
        print("Реклама закончится через $secondsLeft")
        Thread.sleep(500)
    }
    println()

    println("Продолжение работы программы вне цикла - после return")


}
