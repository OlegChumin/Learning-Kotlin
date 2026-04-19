package lesson_04

fun main() {
    // логические операторы
    // операторы сравнения
    println("1 + 1 = ${1 + 1}")
    val expectedNumber = 1
    val actualNumber = 1
    println("expectedNumber == actualNumber: ${expectedNumber == actualNumber}")

    // <, >, >=, <=, == !=
    // операторы ссылочного сравнения

    val userAge = 42
    var comparisonResult = userAge >= AGE_OF_MAJORITY && userAge <= RETIREMENT_AGE
    println("Результат проверки пользователя $comparisonResult")
    // < > <= >= == !=
    // &&, ||, !
    comparisonResult = userAge in AGE_OF_MAJORITY ..RETIREMENT_AGE
    println("Результат проверки пользователя $comparisonResult")

}

const val AGE_OF_MAJORITY = 18
const val RETIREMENT_AGE = 65
