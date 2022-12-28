package ru.sb066coder.bincheck.dto


data class CardInfo(
    val bin: Int? = null,
    val number: Number? = Number(),
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = null,
    val country: Country? = Country(),
    val bank: Bank? = Bank()
)

data class Number(
    val length: Int? = null,
    val luhn: Boolean? = null
)

data class Country(
    val name: String? = "",
    val emoji: String? = "",
    val currency: String? = "",
    val latitude: Int? = null,
    val longitude: Int? = null
)

data class Bank(
    val name: String? = "",
    val url: String? = "",
    val phone: String? = "",
    val city: String? = ""
)
