package com.kanaa

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class UtilTagLibSpec extends Specification implements TagLibUnitTest<UtilTagLib> {

    static final def MSIE = "MSIE"
    static final def TEST_PHRASE = "TestPhrase"

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "Test of condition when #valueInHeader=#valueInTag expected \"#expectedResult\""() {
        given:
        request.addHeader("User-Agent", "$valueInHeader")

        expect:
        applyTemplate("<hub:certainBrowser userAgent=\"$valueInTag\">$TEST_PHRASE</hub:certainBrowser") == expectedResult

        where:
        valueInHeader   | valueInTag    || expectedResult
        MSIE            | MSIE          || TEST_PHRASE
        MSIE            | "Other"       || ""
    }

    void "Test tinyThumbnail"() {
        expect:
        tagLib.tinyThumbnail(loginId: "glen")
    }
}
