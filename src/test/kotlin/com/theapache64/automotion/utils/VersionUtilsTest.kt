package com.theapache64.automotion.utils

import com.winterbe.expekt.should
import org.junit.Assert.*
import org.junit.Test

class VersionUtilsTest {
    @Test
    fun testVersionSuccess() {
        VersionUtils.getVersion().should.equal("v1.0.0-alpha01")
    }
}