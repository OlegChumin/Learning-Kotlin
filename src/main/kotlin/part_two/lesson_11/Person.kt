package part_two.lesson_11

class Person(
    val firstName: String,
    val lastName: String,
)

fun main() {
    // Создание экземпляра класса и инициализация полей
    val person = Person("John", "Doe")
    
    // Доступ к полям
    println("First Name: ${person.firstName}")
    println("Last Name: ${person.lastName}")
}
