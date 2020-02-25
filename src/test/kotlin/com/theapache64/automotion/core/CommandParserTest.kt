package com.theapache64.automotion.core

import org.apache.commons.cli.MissingOptionException
import org.junit.Test


class CommandParserTest {

    @Test
    fun testParserFail() {
        try {
            CommandParser(arrayOf()).getInputVideo()
            assert(false)
        } catch (e: MissingOptionException) {
            assert(true)
        }
    }

    @Test
    fun testParserSuccess() {
        try {
            CommandParser(arrayOf("-h")).printHelp()
            assert(true)
        } catch (e: MissingOptionException) {
            assert(false)
        }
    }
}