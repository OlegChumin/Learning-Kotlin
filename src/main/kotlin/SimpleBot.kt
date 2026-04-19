package bot

import java.util.Scanner


/** Сканер для чтения пользовательского ввода в программе Simple Bot. */
val scanner = Scanner(System.`in`) // Do not change this line

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
    val name = scanner.nextLine()
    println("What a great name you have, ${name}!")
}

/**
 * Вычисляет возраст пользователя по остаткам от деления на 3, 5 и 7.
 */
fun guessAge() {
    println("Let me guess your age.")
    println("Enter remainders of dividing your age by 3, 5 and 7.")
    val rem3 = scanner.nextInt()
    val rem5 = scanner.nextInt()
    val rem7 = scanner.nextInt()
    val age = (rem3 * 70 + rem5 * 21 + rem7 * 15) % 105
    println("Your age is ${age}; that's a good time to start programming!")
}

/**
 * Считает от нуля до введенного пользователем числа.
 */
fun count() {
    println("Now I will prove to you that I can count to any number you want.")
    val num = scanner.nextInt()
    for (i in 0..num) {
        print(i)
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
    var answer = scanner.nextInt()
    while (answer != 2) {
        println("Please, try again.")
        answer = scanner.nextInt()
    }
}

/**
 * Завершает диалог с пользователем.
 */
fun end() {
    println("Congratulations, have a nice day!") // Do not change this text
}
