package ganja.plugin

import ganja.component.di.Container
import ganja.component.di.Reference
import ganja.component.event.DispatcherInterface
import ganja.component.http.event.EngineEvents
import ganja.plugin.profiler.listener.ProfilerListener

class ProfilerPlugin {

    void registerServices(Container container) {

        container
            .register('profiler.listener', 'ganja.plugin.profiler.listener.ProfilerListener')
            .setArguments([profiler: new Reference('profiler')])

        container
            .register('profiler', 'ganja.plugin.profiler.Profiler')
    }

    void registerListeners(Container container) {

        DispatcherInterface dispatcher = container.get('dispatcher')

        ProfilerListener profilerListener = container.get('profiler.listener')
        dispatcher.addListener(EngineEvents.RESPONSE, profilerListener.&onResponse)
    }
}
