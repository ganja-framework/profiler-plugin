package ganja.plugin.profiler.collector

import ganja.component.http.Request
import ganja.component.http.Response
import spock.lang.Specification

class DummyCollectorSpec extends Specification {

    void "it implements interface" () {

        given:
        Request request = GroovyMock()
        Response response = GroovyMock()
        Exception exception = new Exception()
        def subject = new DummyCollector()

        expect:
        subject.getName() == 'dummy'
        subject.collect(request, response, exception) instanceof Object
    }
}
