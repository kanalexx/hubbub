package hubbub

import com.kanaa.Post
import com.kanaa.Profile
import com.kanaa.User
import grails.gorm.transactions.Transactional
import groovy.time.TimeCategory

import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId

import static java.util.Calendar.*

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                if (!Post.count()) createSampleData()
                createAdminUserIfRequired()
            }
            test {
                if (!Post.count()) createSampleData()
            }
        }
    }

    def destroy = {
    }

    @Transactional
    void createSampleData() {
        println "Creating sample data"

        def now = LocalDate.now()
        def chuck = new User(
            loginId: "chuck_norris",
            password: "highkick",
            profile: new Profile(fullName: "Chuck Norris", email: "chuck@nowhere.net"),
            dateCreated: Date.valueOf(now)).save(failOnError: true)
        def glen = new User(
            loginId: "glen",
            password: "sheldon",
            profile: new Profile(fullName: "Glen Smith", email: "glen@nowhere.net"),
            dateCreated: Date.valueOf(now)).save(failOnError: true)
        def peter = new User(
            loginId: "peter",
            password: "mandible",
            profile: new Profile(fullName: "Peter Ledbrook", email: "peter@nowhere.net"),
            dateCreated: Date.valueOf(now)).save(failOnError: true)
        def frankie = new User(
            loginId: "frankie",
            password: "testing",
            profile: new Profile(fullName: "Frankie Goes to Hollywood", email: "frankie@nowhere.net"),
            dateCreated: Date.valueOf(now)).save(failOnError: true)
        def sara = new User(
            loginId: "sara",
            password: "crikey",
            profile: new Profile(fullName: "Sara Miles", email: "sara@nowhere.net"),
            dateCreated: Date.valueOf(now - Period.ofDays(2))).save(failOnError: true)
        def phil = new User(
            loginId: "phil",
            password: "thomas",
            profile: new Profile(fullName: "Phil Potts", email: "phil@nowhere.net"),
            dateCreated: Date.valueOf(now))
        def dillon = new User(loginId: "dillon",
            password: "crikey",
            profile: new Profile(fullName: "Dillon Jessop", email: "dillon@nowhere.net"),
            dateCreated: Date.valueOf(now - Period.ofDays(2))).save(failOnError: true)

        chuck.addToFollowing(phil)
        chuck.addToPosts(content: "Been working my roundhouse kicks.")
        chuck.addToPosts(content: "Working on a few new moves. Bit sluggish today.")
        chuck.addToPosts(content: "Tinkering with the hubbub app.")
        chuck.save(failOnError: true)

        phil
            .addToFollowing(frankie)
            .addToFollowing(sara)
            .save(failOnError: true)

        phil.addToPosts(content: "Very first post")
        phil.addToPosts(content: "Second post")
        phil.addToPosts(content: "Time for a BBQ!")
        phil.addToPosts(content: "Writing a very very long book")
        phil.addToPosts(content: "Tap dancing")
        phil.addToPosts(content: "Pilates is killing me")
        phil.save(failOnError: true)

        sara.addToPosts(content: "My first post")
        sara.addToPosts(content: "Second post")
        sara.addToPosts(content: "Time for a BBQ!")
        sara.addToPosts(content: "Writing a very very long book")
        sara.addToPosts(content: "Tap dancing")
        sara.addToPosts(content: "Pilates is killing me")
        sara.save(failOnError: true)

        dillon.addToPosts(content: "Pilates is killing me as well")
        dillon.save(failOnError: true, flush: true)

        // We have to update the 'dateCreated' field after the initial save to
        // work around Grails' auto-timestamping feature. Note that this trick
        // won't work for the 'lastUpdated' field.
        def postsAsList = phil.posts as List
        postsAsList[0].addToTags(user: phil, name: "groovy")
        postsAsList[0].addToTags(user: phil, name: "grails")
        postsAsList[0].dateCreated = Date.valueOf(LocalDate.of(2004, MAY, 1))

        postsAsList[1].addToTags(user: phil, name: "grails")
        postsAsList[1].addToTags(user: phil, name: "ramblings")
        postsAsList[1].addToTags(user: phil, name: "second")
        postsAsList[1].dateCreated = Date.valueOf(LocalDate.of(2007, FEBRUARY, 13))

        postsAsList[2].addToTags(user: phil, name: "groovy")
        postsAsList[2].addToTags(user: phil, name: "bbq")
        postsAsList[2].dateCreated = Date.valueOf(LocalDate.of(2009, OCTOBER, 1))

        postsAsList[3].addToTags(user: phil, name: "groovy")
        postsAsList[3].dateCreated = Date.valueOf(LocalDate.of(2011, MAY, 1))

        postsAsList[4].dateCreated = Date.valueOf(LocalDate.of(2011, DECEMBER, 4))
        postsAsList[5].dateCreated = Date.valueOf(LocalDate.of(2012, DECEMBER, 10))
        phil.save(failOnError: true)

        postsAsList = sara.posts as List
        postsAsList[0].dateCreated = Date.valueOf(LocalDate.of(2007, MAY, 1))
        postsAsList[1].dateCreated = Date.valueOf(LocalDate.of(2008, APRIL, 13))
        postsAsList[2].dateCreated = Date.valueOf(LocalDate.of(2008, APRIL, 24))
        postsAsList[3].dateCreated = Date.valueOf(LocalDate.of(2011, NOVEMBER, 8))
        postsAsList[4].dateCreated = Date.valueOf(LocalDate.of(2011, DECEMBER, 4))
        postsAsList[5].dateCreated = Date.valueOf(LocalDate.of(2012, AUGUST, 1))

        sara.dateCreated = Date.valueOf(now - Period.ofDays(2))
        sara.save(failOnError: true)

        dillon.dateCreated = Date.valueOf(now - Period.ofDays(2))
        dillon.save(failOnError: true, flush: true)
    }

    void createAdminUserIfRequired() {
        if (!User.findByLoginId("admin")) {
            println "Fresh DataBase. Creating ADMIN user."
            def profile = new Profile(fullName: "Administrator", email: "admin@mail.com")
            new User(loginId: "admin", password: "secret", profile: profile).save()
        } else {
            println "Existing admin user, skipping creation."
        }
    }
}
