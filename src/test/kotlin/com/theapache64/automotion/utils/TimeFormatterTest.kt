package com.theapache64.automotion.utils

import com.winterbe.expekt.should
import org.junit.Test

class TimeFormatterTest {

    @Test
    fun testAllCase() {
        DateTimeUtils.getTimeFormatted(0).should.equal("00:00:00")
        DateTimeUtils.getTimeFormatted(1).should.equal("00:00:01")
        DateTimeUtils.getTimeFormatted(1.4).should.equal("00:00:01")
        DateTimeUtils.getTimeFormatted(1.5).should.equal("00:00:02")
        DateTimeUtils.getTimeFormatted(10).should.equal("00:00:10")
        DateTimeUtils.getTimeFormatted(59).should.equal("00:00:59")
        DateTimeUtils.getTimeFormatted(60).should.equal("00:01:00")
        DateTimeUtils.getTimeFormatted(61).should.equal("00:01:01")
        DateTimeUtils.getTimeFormatted(65).should.equal("00:01:05")
        DateTimeUtils.getTimeFormatted(70).should.equal("00:01:10")
        DateTimeUtils.getTimeFormatted(75).should.equal("00:01:15")
    }
}