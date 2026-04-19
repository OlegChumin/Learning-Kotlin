package lesson_01

fun main() {
    val numberOfOrders: Int = 42

    var notificationText = "Не забудьте маску и перчатки!"

    println("$numberOfOrders\n$notificationText\n")

    notificationText = "Маски больше не потребуются"

    clearScreen()

    println("$numberOfOrders\n$notificationText\n")

    println("Данные")
    drawLine()
    drawLine()
    println("1. Целые")
    drawLine()

    println("Byte == 8bit signed")
    println("${Byte.MIN_VALUE}...${Byte.MAX_VALUE}")
    println("Byte == 8bit unsigned")
    println("${UByte.MIN_VALUE}...${UByte.MAX_VALUE}")
    drawLine()

    println("Short == 16bit == 2Byte signed")
    println("${Short.MIN_VALUE}...${Short.MAX_VALUE}")
    println("UShort == 16bit == 2Byte unsigned")
    println("${UShort.MIN_VALUE}...${UShort.MAX_VALUE}")
    drawLine()

    println("Int == 32bit == 4Byte signed DEFAULT")
    println("${Int.MIN_VALUE}...${Int.MAX_VALUE}")
    println("Int == 32bit == 4Byte unsigned")
    println("${UInt.MIN_VALUE}...${UInt.MAX_VALUE}")
    drawLine()

    println("Long == 64bit == 8Byte signed")
    println("${Long.MIN_VALUE}...${Long.MAX_VALUE}")
    println("ULong == 64bit == 8Byte unsigned")
    println("${ULong.MIN_VALUE}...${ULong.MAX_VALUE}")
    drawLine()

    println("2. Вещественные")
    println("Float == 32bit == 4Byte signed")
    println("${Float.MIN_VALUE}...${Float.MAX_VALUE}")
    println("Double == 64bit == 8Byte signed DEFAULT")
    println("${Double.MIN_VALUE}...${Double.MAX_VALUE}")
    drawLine()

    println("3. Логические")
    println("Boolean == true || false")
    val firstBooleanCheck = 1 == 2
    val expectedNumber = 1
    val actualNumber = 1
    val secondBooleanCheck = expectedNumber == actualNumber
    println("$firstBooleanCheck или $secondBooleanCheck")
    drawLine()

    println("4. Строковые")
    println("String")
    println("Какая-то строка")
    drawLine()

    println("5. Символьные")
    println("Char")
    println("${Char.MIN_VALUE}...${Char.MAX_VALUE}")
    for (char in 'A'..'Z') {
        print("$char ")
    }
    println()
    printUtf16Table()
    drawLine()


}

fun clearScreen() {
    print("\u001b[H\u001b[2J")
}

fun drawLine() = println("-----------------------------------------")

fun printUtf16Table() {
    println("UTF-16")
    val startChar = Char.MIN_VALUE.code
    val endChar = Char.MAX_VALUE.code

    for (characterCode in startChar..endChar) {
        val character = characterCode.toChar()
        print("$characterCode: $character; ")
    }
}

