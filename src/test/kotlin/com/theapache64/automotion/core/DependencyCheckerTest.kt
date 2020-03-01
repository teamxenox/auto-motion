package com.theapache64.automotion.core

import com.winterbe.expekt.should
import org.junit.Assert.*
import org.junit.Test

class DependencyCheckerTest {
    @Test
    fun testFFmpegTestSuccess() {
        DependencyChecker.isFFmpegOkay().should.`true`
    }
}