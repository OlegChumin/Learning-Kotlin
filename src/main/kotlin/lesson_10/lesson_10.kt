package lesson_10

fun main() {
    val nullableName: String? = null
    println(nullableName)
    try{
        val requiredName = readLine()!! //(String    String + null == String?)
        println(requiredName)
    } catch (exception: Exception) {
        println(exception.message)
    }

    val userName = readln() //String
    println(userName)
}



