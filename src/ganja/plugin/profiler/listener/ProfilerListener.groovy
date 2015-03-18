package ganja.plugin.profiler.listener

import ganja.component.http.event.EngineResponseEvent
import ganja.plugin.profiler.Profiler

class ProfilerListener {

    Profiler profiler

    void onResponse(EngineResponseEvent event) {

        profiler.collect(event.request, event.response, event.exception)
    }
}
