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
            .setArguments([
                storage: new Reference('profiler.storage')
            ])

        container
            .register('profiler.storage', 'ganja.plugin.profiler.storage.ListStorage')

        container
            .register('dummy.collector', 'ganja.plugin.profiler.collector.DummyCollector')
    }

    void registerListeners(Container container) {

        DispatcherInterface dispatcher = container.get('dispatcher')

        ProfilerListener profilerListener = container.get('profiler.listener')
        profilerListener.getProfiler().getCollectors().add(container.get('dummy.collector'))
        dispatcher.addListener(EngineEvents.RESPONSE, profilerListener.&onResponse)
    }
}
