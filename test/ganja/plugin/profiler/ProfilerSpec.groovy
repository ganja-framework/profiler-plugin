package ganja.plugin.profiler

import ganja.component.http.Request
import ganja.component.http.Response
import ganja.plugin.profiler.collector.DataCollectorInterface
import ganja.plugin.profiler.storage.ProfilerStorageInterface
import spock.lang.Specification

class ProfilerSpec extends Specification {

    void "it can collect data and add it to profile"() {

        given:
        ProfilerStorageInterface storage = GroovyMock()
        DataCollectorInterface collector = GroovyMock()
        Request request = GroovyMock()
        Response response = GroovyMock()
        Exception exception = new Exception()
        def subject = new Profiler(collectors: [collector], storage: storage)

        when:
        subject.collect(request)
        subject.collect(request, response)
        subject.collect(request, response, exception)

        then:
        6 * collector.name
        1 * collector.collect(request, null, null)
        1 * collector.collect(request, response, null)
        1 * collector.collect(request, response, exception)
        3 * storage.save(_)
    }
}
