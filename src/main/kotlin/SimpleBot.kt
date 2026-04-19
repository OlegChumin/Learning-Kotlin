package bot

import java.util.Scanner


/** Сканер для чтения пользовательского ввода в программе Simple Bot. */
val inputScanner = Scanner(System.`in`) // Do not change this line

/**
 * Запускает учебного чат-бота и последовательно выполняет все этапы диалога.
 */
fun main() {
    greet("Aid", "2023")
    remindName()
    guessAge()
    count()
    test()
    end()
}

/**
 * Приветствует пользователя и сообщает имя бота.
 *
 * @param assistantName имя бота.
 * @param birthYear год создания бота.
 */
fun greet(assistantName: String, birthYear: String) {
    println("Hello! My name is ${assistantName}.")
    println("I was created in ${birthYear}.")
    println("Please, remind me your name.")
}

/**
 * Читает имя пользователя и выводит ответное сообщение.
 */
fun remindName() {
    val userName = inputScanner.nextLine()
    println(buildNameReminder(userName))
}

/**
 * Вычисляет возраст пользователя по остаткам от деления на 3, 5 и 7.
 */
fun guessAge() {
    println("Let me guess your age.")
    println("Enter remainders of dividing your age by 3, 5 and 7.")
    val remainderByThree = inputScanner.nextInt()
    val remainderByFive = inputScanner.nextInt()
    val remainderBySeven = inputScanner.nextInt()
    val age = calculateAge(remainderByThree, remainderByFive, remainderBySeven)
    println("Your age is $age; that's a good time to start programming!")
}

/**
 * Считает от нуля до введенного пользователем числа.
 */
fun count() {
    println("Now I will prove to you that I can count to any number you want.")
    val maxNumber = inputScanner.nextInt()
    for (number in 0..maxNumber) {
        print(number)
        println("!")
    }
}

/**
 * Задает пользователю вопрос по основам программирования и ждет правильный ответ.
 */
fun test() {
    println("Let's test your programming knowledge.")
    println("""
        Why do we use methods?
        1. To repeat a statement multiple times.
        2. To decompose a program into several small subroutines.
        3. To determine the execution time of a program.
        4. To interrupt the execution of a program.
    """)
    var answer = inputScanner.nextInt()
    while (answer != 2) {
        println("Please, try again.")
        answer = inputScanner.nextInt()
    }
}

/**
 * Завершает диалог с пользователем.
 */
fun end() {
    println("Congratulations, have a nice day!") // Do not change this text
}

/**
 * Формирует ответ бота после получения имени пользователя.
 *
 * @param userName имя пользователя.
 * @return текст ответа.
 */
fun buildNameReminder(userName: String): String = "What a great name you have, $userName!"

/**
 * Рассчитывает возраст по остаткам от деления на 3, 5 и 7.
 *
 * @param remainderByThree остаток от деления возраста на 3.
 * @param remainderByFive остаток от деления возраста на 5.
 * @param remainderBySeven остаток от деления возраста на 7.
 * @return рассчитанный возраст.
 */
fun calculateAge(remainderByThree: Int, remainderByFive: Int, remainderBySeven: Int): Int =
    (remainderByThree * 70 + remainderByFive * 21 + remainderBySeven * 15) % 105
