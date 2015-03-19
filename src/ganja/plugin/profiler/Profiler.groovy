package ganja.plugin.profiler

import ganja.plugin.profiler.collector.DataCollectorInterface
import ganja.plugin.profiler.storage.ProfilerStorageInterface

class Profiler {

    ProfilerStorageInterface storage
    List<DataCollectorInterface> collectors = []

    void collect(def request, def response = null, Exception exception = null) {

        // create and save profile
        def profile = new Profile()

        collectors.each({ DataCollectorInterface collector ->
            profile.put(collector.name, collector.collect(request, response, exception))
        })

        storage.save(profile)
    }
}
