package com.kanaa

class DateTagLib {
    static defaultEncodeAs = [taglib:'html']
    // <hub:
    static namespace = "hub"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    /**
     * <hub:dateFromNow date="date"/>
     */
    def dateFromNow = { attrs ->
        Date date = attrs.date as Date
        def niceDate = getNiceDate(date)
        out << niceDate
    }

    static String getNiceDate(Date date) {
        def now = new Date()
        long diff = Math.abs(now.time - date.time)

        final long second = 1000
        final long minute = second * 60
        final long hour = minute * 60
        final long day = hour * 24

        def niceDate = ""
        long calc = 0

        calc = (long) Math.floor(diff / day)
        if (calc) {
            diff %= day
            niceDate = "$calc day" + (calc > 1 ? "s" : "")
        }

        calc = (long) Math.floor(diff / hour)
        if (calc) {
            diff %= hour
            niceDate += " $calc hour" + (calc > 1 ? "s" : "")
        }

        calc = (long) Math.floor(diff / minute)
        if (calc) {
            niceDate += " $calc minute" + (calc > 1 ? "s" : "")
        }

        if (!niceDate) {
            niceDate += "right now"
        } else {
            if (date.time > now.time) {
                niceDate = "from now $niceDate"
            } else {
                niceDate += " ago"
            }
        }

        return niceDate

    }
}
