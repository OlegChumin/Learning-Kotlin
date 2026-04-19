package lesson_05

const val AGE_OF_MAJORITY = 18
private const val OTHER_NUMBER_MESSAGE = "Ввели другое число"

fun main() {

    val userAge = readln().toInt()

    println(getContentAccessMessage(userAge))

    when {
        userAge == 10 -> println("Ввели число $userAge")
        userAge == 20 -> println("Ввели число $userAge")
        userAge == 42 -> println("Ввели число $userAge")
        else -> println(OTHER_NUMBER_MESSAGE)
    }

    when(userAge) {
        10 -> println("Ввели число $userAge")
        20 -> println("Ввели число $userAge")
        42 -> println("Ввели число $userAge")
        else -> println(OTHER_NUMBER_MESSAGE)
    }
    println()
    println()

    val numberMessage = when(userAge) {
        10 -> {
            println("Дополнительное действие если ввели $userAge")
            "Ввели число $userAge"}
        20 -> "Ввели число $userAge"
        42 -> "Ввели число $userAge"
        else -> OTHER_NUMBER_MESSAGE
    }
    println(numberMessage)

}

fun getContentAccessMessage(userAge: Int): String = when {
    userAge >= AGE_OF_MAJORITY -> "Показать экран со скрытым контентом"
    userAge == 16 || userAge == 17 -> "Показать экран с ограниченным контентом"
    else -> "Вернуть на главный экран приложения"
}
