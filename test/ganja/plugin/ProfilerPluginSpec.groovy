package ganja.plugin

import ganja.common.di.ContainerInterface
import ganja.common.di.DefinitionInterface
import ganja.common.plugin.PluginInterface
import spock.lang.Specification

class ProfilerPluginSpec extends Specification {

    void "it know which template engine it supports"() {

        given:
        def subject = new ProfilerPlugin()

        expect:
        subject.supports('jtwig')
        ! subject.supports('thymeleaf')
        ! subject.supports('handlebars')
    }

    void "it is initializable"() {

        given:
        def subject = new ProfilerPlugin()

        expect:
        subject instanceof ProfilerPlugin
        subject instanceof PluginInterface
    }

    void "it can register services"() {

        given:
        def subject = new ProfilerPlugin()
        ContainerInterface container = Mock()
        DefinitionInterface definition = Mock()

        when:
        subject.registerServices(container)

        then:
        (1.._) * container.register(_,_) >> definition
        (1.._) * definition.setArguments(_)
    }
}
