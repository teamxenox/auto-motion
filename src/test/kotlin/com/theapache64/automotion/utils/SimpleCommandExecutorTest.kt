package com.theapache64.automotion.utils

import com.winterbe.expekt.should
import org.junit.Test

class SimpleCommandExecutorTest {

    @Test
    fun testCommand() {
        val helloWorld = SimpleCommandExecutor.executeCommand("echo HelloWorld")
        helloWorld.should.equal("HelloWorld")
    }

}