package ganja.plugin.profiler.storage

import ganja.plugin.profiler.Profile
import spock.lang.Specification

class ListStorageSpec extends Specification {

    void "it acts as list and can store data"() {

        given:
        Profile profile = GroovyMock()
        def subject = new ListStorage()

        when:
        subject.save(profile)

        then:
        subject.size() == 1
    }

    void "it can store up to 10 items"() {

        given:
        Profile profile = GroovyMock()
        def subject = new ListStorage()

        when:
        for (i in 0..20) {
            subject.save(profile)
        }

        then:
        subject.size() == 10
    }
}
