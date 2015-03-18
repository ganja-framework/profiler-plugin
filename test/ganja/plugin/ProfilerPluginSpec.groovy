package ganja.plugin

import ganja.component.di.Container
import ganja.component.di.Definition
import ganja.component.event.Dispatcher
import ganja.plugin.profiler.listener.ProfilerListener
import spock.lang.Specification

class ProfilerPluginSpec extends Specification {

    void "it can register services in the container"() {

        given:
        Container container = GroovyMock()
        Definition definition = GroovyMock()
        def subject = new ProfilerPlugin()

        and:
        2 * container.register(_,_) >> definition

        when:
        subject.registerServices(container)

        then:
        1 * definition.setArguments(_)
    }

    void "it can register listeners to dispatcher"() {

        given:
        Container container = GroovyMock()
        Dispatcher dispatcher = GroovyMock()
        ProfilerListener listener = GroovyMock()
        def subject = new ProfilerPlugin()

        and:
        1 * container.get('dispatcher') >> dispatcher
        1 * container.get('profiler.listener') >> listener

        when:
        subject.registerListeners(container)

        then:
        1 * dispatcher.addListener(_,_)
    }
}
