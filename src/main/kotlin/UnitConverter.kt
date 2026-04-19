package converter

import kotlin.system.exitProcess

/**
 * Точка входа для запуска интерактивного конвертера единиц измерения.
 */
fun main() {
    UnitsConverter()
}

/**
 * Консольный конвертер длины, веса и температуры.
 */
class UnitsConverter {
    /** Последняя разобранная команда пользователя. */
    var input = arrayOf<String>()

    /**
     * Запускает бесконечный цикл чтения команд до ввода exit.
     */
    init {
        while (true) {
            println("Enter what you want to convert (or exit): ")
            input = readln().lowercase().replace("degrees ", "").replace("degree ", "").split(" ").toTypedArray()
            if (input[0].equals("exit", true)) {
                exitProcess(0)
            }
            if (input.size < 4 || input[0].toDoubleOrNull() == null) {
                println("Parse error\n")
                continue
            }
            converter()
        }
    }

    /**
     * Выполняет конвертацию последней введенной команды и печатает результат.
     */
    fun converter() {
        val num = input[0].toDouble()
        val inU = UNITS.values().find { input[1] in it.input }?.input
        val outU = UNITS.values().find { input[3] in it.input }?.input
        val inUnit = UNITS.values().mapNotNull { if (input[1] in it.input) input[1] else null }.joinToString("")
        val outUnit = UNITS.values().mapNotNull { if (input[3] in it.input) input[3] else null }.joinToString("")
        val inRates = UNITS.values().mapNotNull { if (input[1] in it.input) it.rates else null }
        val outRates = UNITS.values().mapNotNull { if (input[3] in it.input) it.rates else null }

        if (input[1] != inUnit && input[3] != outUnit) {
            println("Conversion from ??? to ??? is impossible\n")
            return
        }
        if (input[1] != inUnit) {
            println("Conversion from ??? to ${outU?.get(2)} is impossible\n")
            return
        }
        if (input[3] != outUnit) {
            println("Conversion from ${inU?.get(2)} to ??? is impossible\n")
            return
        }

        if (UNITS.values().find { input[1] in it.input }?.toUnit == UNITS.values()
                .find { input[3] in it.input }?.toUnit
        ) {
            if (num < 0.0 && UNITS.values().find { input[1] in it.input }?.toUnit != "temp") {
                println("${UNITS.values().find { input[1] in it.input }?.toUnit} shouldn't be negative")
                return
            }

            if (UNITS.values().find { input[1] in it.input }?.toUnit != "temp" && UNITS.values()
                    .find { input[3] in it.input }?.toUnit != "temp"
            ) {
                val conv = (num * inRates[0]) / outRates[0]
                println(
                    "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $conv ${
                        if (conv == 1.0) outU?.get(1) else outU?.get(
                            2
                        )
                    }\n"
                )
            }

            if (input[1] in UNITS.CELSIUS.input) {
                if (input[3] in UNITS.KELVIN.input) {
                    val cK = num + UNITS.KELVIN.rates
                    println(
                        "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $cK ${
                            if (cK == 1.0) outU?.get(1) else outU?.get(
                                2
                            )
                        }\n"
                    )

                }
                if (input[3] in UNITS.FAHRENHEIT.input) {
                    val cF = (num * 9 / 5) + UNITS.CELSIUS.rates
                    println(
                        "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $cF ${
                            if (cF == 1.0) outU?.get(1) else outU?.get(
                                2
                            )
                        }\n"
                    )

                }
                if (input[3] in UNITS.CELSIUS.input) {
                    println(
                        "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $num ${
                            if (num == 1.0) outU?.get(1) else outU?.get(
                                2
                            )
                        }\n"
                    )
                }
            }
            if (input[1] in UNITS.FAHRENHEIT.input) {
                if (input[3] in UNITS.KELVIN.input) {
                    val fK = (num + UNITS.FAHRENHEIT.rates) * 5 / 9
                    println(
                        "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $fK ${
                            if (fK == 1.0) outU?.get(1) else outU?.get(
                                2
                            )
                        }\n"
                    )

                }
                if (input[3] in UNITS.CELSIUS.input) {
                    val fC = (num - UNITS.CELSIUS.rates) * 5 / 9
                    println(
                        "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $fC ${
                            if (fC == 1.0) outU?.get(1) else outU?.get(
                                2
                            )
                        }\n"
                    )

                }
                if (input[3] in UNITS.FAHRENHEIT.input) {
                    println(
                        "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $num ${
                            if (num == 1.0) outU?.get(1) else outU?.get(
                                2
                            )
                        }\n"
                    )
                }
            }
            if (input[1] in UNITS.KELVIN.input) {
                if (input[3] in UNITS.CELSIUS.input) {
                    val kC = num - UNITS.KELVIN.rates
                    println(
                        "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $kC ${
                            if (kC == 1.0) outU?.get(1) else outU?.get(
                                2
                            )
                        }\n"
                    )

                }
                if (input[3] in UNITS.FAHRENHEIT.input) {
                    val kF = (num * 9 / 5) - UNITS.FAHRENHEIT.rates
                    println(
                        "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $kF ${
                            if (kF == 1.0) outU?.get(1) else outU?.get(
                                2
                            )
                        }\n"
                    )

                }
                if (input[3] in UNITS.KELVIN.input) {
                    println(
                        "$num ${if (num == 1.0) inU?.get(1) else inU?.get(2)} is $num ${
                            if (num == 1.0) outU?.get(1) else outU?.get(
                                2
                            )
                        }\n"
                    )
                }

            }
        } else {
            println("Conversion from ${inU?.get(2)} to ${outU?.get(2)} is impossible\n")
        }

    }

    /**
     * Справочник поддерживаемых единиц измерения.
     *
     * @property rates коэффициент пересчета для длины и веса или температурная константа.
     * @property input допустимые варианты ввода единицы.
     * @property toUnit тип величины: length, weight или temp.
     */
    enum class UNITS(val rates: Double, val input: Array<String>, val toUnit: String) {
        /** Метр. */
        METER(1.0, arrayOf("m", "meter", "meters"), "length"),
        /** Километр. */
        KILOMETER(1000.0, arrayOf("km", "kilometer", "kilometers"), "length"),
        /** Сантиметр. */
        CENTIMETER(0.01, arrayOf("cm", "centimeter", "centimeters"), "length"),
        /** Миллиметр. */
        MILLIMETER(0.001, arrayOf("mm", "millimeter", "millimeters"), "length"),
        /** Миля. */
        MILES(1609.35, arrayOf("mi", "mile", "miles"), "length"),
        /** Ярд. */
        YARDS(0.9144, arrayOf("yd", "yard", "yards"), "length"),
        /** Фут. */
        FEET(0.3048, arrayOf("ft", "foot", "feet"), "length"),
        /** Дюйм. */
        INCHES(0.0254, arrayOf("in", "inch", "inches"), "length"),

        /** Грамм. */
        GRAMS(1.0, arrayOf("g", "gram", "grams"), "weight"),
        /** Килограмм. */
        KILOGRAMS(1000.0, arrayOf("kg", "kilogram", "kilograms"), "weight"),
        /** Миллиграмм. */
        MILLIGRAMS(0.001, arrayOf("mg", "milligram", "milligrams"), "weight"),
        /** Фунт. */
        POUNDS(453.592, arrayOf("lb", "pound", "pounds"), "weight"),
        /** Унция. */
        OUNCES(28.3495, arrayOf("oz", "ounce", "ounces"), "weight"),

        /** Градус Цельсия. */
        CELSIUS(32.0, arrayOf("c", "degree Celsius", "degrees Celsius", "celsius", "dc"), "temp"),
        /** Градус Фаренгейта. */
        FAHRENHEIT(459.67, arrayOf("f", "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df"), "temp"),
        /** Кельвин. */
        KELVIN(273.15, arrayOf("k", "kelvin", "kelvins"), "temp")

    }
}
