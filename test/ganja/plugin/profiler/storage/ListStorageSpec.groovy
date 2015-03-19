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

    void "it can get all profiles saved"() {

        given:
        Profile profile = GroovyMock()
        def subject = new ListStorage()

        when:
        subject.save(profile)


        then:
        subject.getAll().get(0) == profile
    }

    void "it can return single profile based on token"() {

        given:
        Profile profile1 = GroovyMock()
        Profile profile2 = GroovyMock()
        def subject = new ListStorage()

        and:
        profile1.getToken() >> 'token1'
        profile2.getToken() >> 'token2'

        when:
        subject.save(profile1)
        subject.save(profile2)


        then:
        subject.get('token2') == profile2
        subject.get('token1') == profile1
    }
}
