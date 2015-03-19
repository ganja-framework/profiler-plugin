package ganja.plugin.profiler.controller

import ganja.component.http.Request
import ganja.component.http.Response
import ganja.plugin.profiler.Profile
import ganja.plugin.profiler.storage.ProfilerStorageInterface
import spock.lang.Specification

class ProfilerControllerSpec extends Specification {

    void "it can list all available profiles in the storage"() {

        given:
        Request request = GroovyMock()
        ProfilerStorageInterface storage = GroovyMock()
        def subject = new ProfilerController(storage: storage)

        expect:
        subject.list(request) instanceof Response
    }

    void "it can show individual profile"() {

        given:
        Request request = GroovyMock()
        ProfilerStorageInterface storage = GroovyMock()
        Profile profile = GroovyMock() { asBoolean() >> true }
        def subject = new ProfilerController(storage: storage)

        and:
        1 * storage.get('ok') >> profile
        1 * profile.getToken() >> 'ok'
        1 * request.getParameter('token') >> 'ok'

        expect:
        subject.show(request) instanceof Response
    }
}
