package com.kanaa

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification
import spock.lang.Unroll

import java.sql.Timestamp
import java.time.LocalDateTime

class DateTagLibSpec extends Specification implements TagLibUnitTest<DateTagLib> {

    static final def NOW = LocalDateTime.now()
    static final def NOW_1DAY = NOW.minusDays(1)
    static final def NOW_2DAYS = NOW.minusDays(2)
    static final def NOW_1DAY_1HOUR_1MINUTE = NOW.minusDays(1).minusHours(1).minusMinutes(1)
    static final def NOW_2DAYS_3HOURS_4MINUTES = NOW.minusDays(2).minusHours(3).minusMinutes(4)

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "Conversion of #testName matching #expectedNiceDate"() {
        expect:
            applyTemplate('<hub:dateFromNow date="${date}"/>', [date: Timestamp.valueOf(testDate)]) == expectedNiceDate

        where:
        testName                        | testDate                  | expectedNiceDate
        "Current time"                  | NOW                       | "right now"
        "Now - 1 day"                   | NOW_1DAY                  | "1 day ago"
        "Now - 2 days"                  | NOW_2DAYS                 | "2 days ago"
        "Now - 1 day 1 hour 1 minute"   | NOW_1DAY_1HOUR_1MINUTE    | "1 day 1 hour 1 minute ago"
        "Now - 2 days 3 hours 4 minutes"| NOW_2DAYS_3HOURS_4MINUTES | "2 days 3 hours 4 minutes ago"
    }
}
