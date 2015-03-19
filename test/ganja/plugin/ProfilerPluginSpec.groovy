package ganja.plugin

import ganja.common.di.ContainerInterface
import ganja.component.di.Container
import ganja.component.di.Definition
import ganja.component.event.Dispatcher
import ganja.plugin.profiler.Profiler
import ganja.plugin.profiler.collector.DataCollectorInterface
import ganja.plugin.profiler.listener.ProfilerListener
import spock.lang.Specification

class ProfilerPluginSpec extends Specification {

    void "it can register services in the container"() {

        given:
        ContainerInterface container = GroovyMock()
        Definition definition = GroovyMock()
        def subject = new ProfilerPlugin()

        and:
        4 * container.register(_,_) >> definition

        when:
        subject.registerServices(container)

        then:
        2 * definition.setArguments(_)
    }

    void "it can register listeners to dispatcher"() {

        given:
        ContainerInterface container = GroovyMock()
        Dispatcher dispatcher = GroovyMock()
        ProfilerListener listener = GroovyMock()
        Profiler profiler = GroovyMock()
        DataCollectorInterface collector = GroovyMock()
        def subject = new ProfilerPlugin()

        and:
        1 * container.get('dispatcher') >> dispatcher
        1 * container.get('profiler.listener') >> listener
        1 * listener.getProfiler() >> profiler
        1 * profiler.getCollectors() >> []
        1 * container.get('dummy.collector') >> collector

        when:
        subject.registerListeners(container)

        then:
        1 * dispatcher.addListener(_,_)
    }
}
