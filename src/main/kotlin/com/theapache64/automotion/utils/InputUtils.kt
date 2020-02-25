package com.theapache64.automotion.utils

import java.lang.NumberFormatException
import java.util.*

/**
 * Operations related to reading text input from console
 */
object InputUtils {

    private val scanner by lazy { Scanner(System.`in`) }

    /**
     * Get a String with given prompt as prompt
     */
    fun getString(prompt: String, isRequired: Boolean): String {
        print("$prompt: ")
        val value = scanner.nextLine()
        while (value.trim().isEmpty() && isRequired) {
            println("Invalid ${prompt.toLowerCase()} `$value`")
            return getString(prompt, isRequired)
        }
        return value
    }

    fun getInt(prompt: String, lowerBound: Int, upperBound: Int): Int {

        print("$prompt :")

        val sVal = scanner.nextLine()
        try {
            val value = sVal.toInt()
            if (value < lowerBound || value > upperBound) {
                println("Input must between $lowerBound and $upperBound")
                return getInt(prompt, lowerBound, upperBound)
            }
            return value
        } catch (e: NumberFormatException) {
            println("Invalid input `$sVal`")
            return getInt(prompt, lowerBound, upperBound)
        }
    }

}