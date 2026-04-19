package converter

/**
 * Точка входа для запуска интерактивного конвертера единиц измерения.
 */
fun main() {
    UnitsConverter().run()
}

/**
 * Консольный конвертер длины, веса и температуры.
 */
class UnitsConverter {
    /**
     * Запускает цикл чтения команд до ввода exit.
     */
    fun run() {
        while (true) {
            println("Enter what you want to convert (or exit): ")
            val command = readln()
            if (command.equals("exit", ignoreCase = true)) {
                return
            }
            println(convert(command))
        }
    }

    /**
     * Конвертирует одну команду пользователя в текст результата.
     *
     * @param command строка вида "15 meters to feet".
     * @return текст результата или сообщение об ошибке.
     */
    fun convert(command: String): String = convertMeasurement(command)
}

/**
 * Конвертирует одну команду пользователя в текст результата.
 *
 * @param command строка вида "15 meters to feet".
 * @return текст результата или сообщение об ошибке.
 */
fun convertMeasurement(command: String): String {
    val commandParts = command.lowercase()
        .replace("degrees ", "")
        .replace("degree ", "")
        .split(" ")

    if (commandParts.size < 4 || commandParts[0].toDoubleOrNull() == null) {
        return "Parse error"
    }

    val sourceValue = commandParts[0].toDouble()
    val sourceUnit = MeasurementUnit.entries.find { commandParts[1] in it.aliases }
    val targetUnit = MeasurementUnit.entries.find { commandParts[3] in it.aliases }

    if (sourceUnit == null && targetUnit == null) {
        return "Conversion from ??? to ??? is impossible"
    }
    if (sourceUnit == null) {
        return "Conversion from ??? to ${targetUnit!!.pluralName} is impossible"
    }
    if (targetUnit == null) {
        return "Conversion from ${sourceUnit.pluralName} to ??? is impossible"
    }
    if (sourceUnit.measurementType != targetUnit.measurementType) {
        return "Conversion from ${sourceUnit.pluralName} to ${targetUnit.pluralName} is impossible"
    }
    if (sourceValue < 0.0 && sourceUnit.measurementType != MeasurementType.TEMPERATURE) {
        return "${sourceUnit.measurementType.displayName} shouldn't be negative"
    }

    val convertedValue = when (sourceUnit.measurementType) {
        MeasurementType.LENGTH,
        MeasurementType.WEIGHT -> sourceValue * sourceUnit.baseRate / targetUnit.baseRate
        MeasurementType.TEMPERATURE -> convertTemperature(sourceValue, sourceUnit, targetUnit)
    }
    return "$sourceValue ${sourceUnit.nameFor(sourceValue)} is $convertedValue ${targetUnit.nameFor(convertedValue)}"
}

/**
 * Конвертирует температуру между шкалами Цельсия, Фаренгейта и Кельвина.
 *
 * @param sourceValue исходное значение температуры.
 * @param sourceUnit исходная единица температуры.
 * @param targetUnit целевая единица температуры.
 * @return сконвертированное значение.
 */
fun convertTemperature(sourceValue: Double, sourceUnit: MeasurementUnit, targetUnit: MeasurementUnit): Double {
    val celsiusValue = when (sourceUnit) {
        MeasurementUnit.CELSIUS -> sourceValue
        MeasurementUnit.FAHRENHEIT -> (sourceValue - 32.0) * 5 / 9
        MeasurementUnit.KELVIN -> sourceValue - 273.15
        else -> error("${sourceUnit.singularName} is not a temperature unit")
    }

    return when (targetUnit) {
        MeasurementUnit.CELSIUS -> celsiusValue
        MeasurementUnit.FAHRENHEIT -> celsiusValue * 9 / 5 + 32.0
        MeasurementUnit.KELVIN -> celsiusValue + 273.15
        else -> error("${targetUnit.singularName} is not a temperature unit")
    }
}

/**
 * Тип измеряемой величины.
 *
 * @property displayName название типа для сообщений об ошибках.
 */
enum class MeasurementType(val displayName: String) {
    /** Длина. */
    LENGTH("Length"),

    /** Вес. */
    WEIGHT("Weight"),

    /** Температура. */
    TEMPERATURE("Temperature")
}

/**
 * Справочник поддерживаемых единиц измерения.
 *
 * @property baseRate коэффициент пересчета к базовой единице для длины и веса.
 * @property aliases допустимые варианты ввода единицы.
 * @property measurementType тип измеряемой величины.
 * @property singularName название единицы в единственном числе.
 * @property pluralName название единицы во множественном числе.
 */
enum class MeasurementUnit(
    val baseRate: Double,
    val aliases: Set<String>,
    val measurementType: MeasurementType,
    val singularName: String,
    val pluralName: String,
) {
    /** Метр. */
    METER(1.0, setOf("m", "meter", "meters"), MeasurementType.LENGTH, "meter", "meters"),

    /** Километр. */
    KILOMETER(1000.0, setOf("km", "kilometer", "kilometers"), MeasurementType.LENGTH, "kilometer", "kilometers"),

    /** Сантиметр. */
    CENTIMETER(0.01, setOf("cm", "centimeter", "centimeters"), MeasurementType.LENGTH, "centimeter", "centimeters"),

    /** Миллиметр. */
    MILLIMETER(0.001, setOf("mm", "millimeter", "millimeters"), MeasurementType.LENGTH, "millimeter", "millimeters"),

    /** Миля. */
    MILE(1609.35, setOf("mi", "mile", "miles"), MeasurementType.LENGTH, "mile", "miles"),

    /** Ярд. */
    YARD(0.9144, setOf("yd", "yard", "yards"), MeasurementType.LENGTH, "yard", "yards"),

    /** Фут. */
    FOOT(0.3048, setOf("ft", "foot", "feet"), MeasurementType.LENGTH, "foot", "feet"),

    /** Дюйм. */
    INCH(0.0254, setOf("in", "inch", "inches"), MeasurementType.LENGTH, "inch", "inches"),

    /** Грамм. */
    GRAM(1.0, setOf("g", "gram", "grams"), MeasurementType.WEIGHT, "gram", "grams"),

    /** Килограмм. */
    KILOGRAM(1000.0, setOf("kg", "kilogram", "kilograms"), MeasurementType.WEIGHT, "kilogram", "kilograms"),

    /** Миллиграмм. */
    MILLIGRAM(0.001, setOf("mg", "milligram", "milligrams"), MeasurementType.WEIGHT, "milligram", "milligrams"),

    /** Фунт. */
    POUND(453.592, setOf("lb", "pound", "pounds"), MeasurementType.WEIGHT, "pound", "pounds"),

    /** Унция. */
    OUNCE(28.3495, setOf("oz", "ounce", "ounces"), MeasurementType.WEIGHT, "ounce", "ounces"),

    /** Градус Цельсия. */
    CELSIUS(1.0, setOf("c", "celsius", "dc"), MeasurementType.TEMPERATURE, "degree Celsius", "degrees Celsius"),

    /** Градус Фаренгейта. */
    FAHRENHEIT(1.0, setOf("f", "fahrenheit", "df"), MeasurementType.TEMPERATURE, "degree Fahrenheit", "degrees Fahrenheit"),

    /** Кельвин. */
    KELVIN(1.0, setOf("k", "kelvin", "kelvins"), MeasurementType.TEMPERATURE, "kelvin", "kelvins");

    /**
     * Выбирает форму названия единицы по значению.
     *
     * @param value числовое значение.
     * @return название в единственном или множественном числе.
     */
    fun nameFor(value: Double): String = if (value == 1.0) singularName else pluralName
}
