package ganja.plugin.profiler.controller

import ganja.common.http.ResponseInterface
import ganja.common.view.TemplateEngineInterface
import ganja.component.http.Request
import ganja.plugin.profiler.Profile
import ganja.plugin.profiler.storage.ProfilerStorageInterface
import spock.lang.Specification

class ProfilerControllerSpec extends Specification {

    void "it can list all available profiles in the storage"() {

        given:
        Request request = GroovyMock()
        ProfilerStorageInterface storage = GroovyMock()
        TemplateEngineInterface engine = GroovyMock()
        ResponseInterface response = GroovyMock()

        def subject = new ProfilerController(storage: storage, engine: engine)

        when:
        subject.list(request)

        then:
        1 * engine.render(_,_) >> response
    }

    void "it can show individual profile"() {

        given:
        Request request = GroovyMock()
        ProfilerStorageInterface storage = GroovyMock()
        TemplateEngineInterface engine = GroovyMock()
        ResponseInterface response = GroovyMock()
        Profile profile = GroovyMock() { asBoolean() >> true }

        def subject = new ProfilerController(storage: storage, engine: engine)

        and:
        1 * storage.get(_) >> profile
        1 * request.getParameter('token')

        when:
        subject.show(request)

        then:
        1 * engine.render(_,_) >> response
    }
}
