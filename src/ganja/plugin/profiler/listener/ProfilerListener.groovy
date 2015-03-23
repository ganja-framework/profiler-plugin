package ganja.plugin.profiler.listener

import ganja.component.http.event.EngineResponseEvent
import ganja.plugin.profiler.Profiler

class ProfilerListener {

    def logger
    Profiler profiler

    void onResponse(EngineResponseEvent event) {

        logger?.info("Received event ${event.name}")
        profiler.collect(event.request, event.response, event.exception)
    }
}
