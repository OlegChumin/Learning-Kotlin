package lesson_02

fun main() {
    val firstNumber = 5
    val secondNumber = 7
    val numbersSum = firstNumber + secondNumber

    println(firstNumber + secondNumber)
    println(numbersSum)

    println(10 / 3)

    val firstIntNumber = 10
    val secondIntNumber = 3
    println(firstIntNumber / secondIntNumber)

    val firstFloatNumber = 10f
    val secondFloatNumber = 3f
    println(firstFloatNumber / secondFloatNumber)

    val firstDoubleNumber: Double = 10.0
    val secondDoubleNumber: Double = 3.0
    println(firstDoubleNumber / secondDoubleNumber)

    // остаток от деления %
    println(10 % 3)
    println(10 % 2)
    val intNumber = 10
    println(intNumber.rem(2))

    println(firstIntNumber + firstFloatNumber)
    println("${firstIntNumber + firstFloatNumber + firstDoubleNumber}   текст")

    val mixedTypeSum = secondIntNumber + firstFloatNumber
    println(mixedTypeSum::class.simpleName)

    var counter = 0
    counter = counter + 1
    counter += 1
    counter++


    counter = counter - 1
    counter -= 1
    counter--

    ++counter
    --counter

    var counter1 = 5
    println("prefix result = ${++counter1}, counter = $counter1")

    var counter2 = 5
    println("postfix result = ${counter2++}, counter = $counter2")

    // операторы сравнения
    println("a = $firstNumber, b = $secondNumber")
    println("a > b ${firstNumber > secondNumber}")
    println("a >= b ${firstNumber >= secondNumber}")
    println("a < b ${firstNumber < secondNumber}")
    println("a <= b ${firstNumber <= secondNumber}")
    println("a != b ${firstNumber != secondNumber}")
    println("a == b ${firstNumber == secondNumber}")

    val sameNumberValue = firstNumber
    println("a == sameNumberValue ${firstNumber == sameNumberValue}")
    println("Операторы ссылочного сравнения: === и !==")

}
