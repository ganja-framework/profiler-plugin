package ganja.plugin.profiler

import spock.lang.Specification

class ProfileSpec extends Specification {

    void "it can store data in key value pairs"() {

        given:
        def subject = new Profile()

        when:
        subject.put('memory', '128MB')
        subject.put('request', [ created: new Date() ])

        then:
        subject.get('memory') == '128MB'
        subject.get('request') instanceof Map
    }
}
