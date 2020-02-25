package com.theapache64.automotion.utils

import com.winterbe.expekt.should
import org.junit.Assert.*
import org.junit.Test

class SpacePrinterTest {
    @Test
    fun testPrint() {
        val spaces = SpacePrinter.getSpace(10)
        spaces.length.should.equal(10)
    }
}