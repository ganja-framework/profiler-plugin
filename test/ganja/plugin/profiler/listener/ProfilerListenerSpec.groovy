package ganja.plugin.profiler.listener

import ganja.component.http.event.EngineResponseEvent
import ganja.plugin.profiler.Profiler
import spock.lang.Specification

class ProfilerListenerSpec extends Specification {

    void "it listens to event and when it happens it passes data to profiler"() {

        given:
        Profiler profiler = GroovyMock()
        EngineResponseEvent event = GroovyMock()
        def subject = new ProfilerListener(profiler: profiler)

        when:
        subject.onResponse(event)

        then:
        1 * event.getRequest()
        1 * event.getResponse()
        1 * event.getException()
        1 * profiler.collect(_,_,_)
    }
}
