package ru.nsu.usoltsev.task_3.model

data class CurrencyResponseDto (
    val Date : String,
    val Valute: Map<String, Currency>
)

data class Currency(
    val CharCode: String,
    val Name: String,
    val Nominal: Double,
    val Value: Double,
    var NormalizeValue: Double
)