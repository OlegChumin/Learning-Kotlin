package org.example

/**
 * Запускает простой демонстрационный пример с выводом приветствия и счетчика.
 */
fun main() {
    val languageName = "Kotlin"
    println("Hello, $languageName!")

    for (counter in 1..5) {
        println("i = $counter")
    }
}
