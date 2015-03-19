package ganja.plugin.profiler

import ganja.plugin.profiler.collector.DataCollectorInterface
import ganja.plugin.profiler.storage.ProfilerStorageInterface
import org.apache.commons.lang3.RandomStringUtils

class Profiler {

    ProfilerStorageInterface storage
    List<DataCollectorInterface> collectors = []

    void collect(def request, def response = null, Exception exception = null) {

        def profile = new Profile(token: RandomStringUtils.random(6, true, true))

        collectors.each({ DataCollectorInterface collector ->
            profile.put(collector.name, collector.collect(request, response, exception))
        })

        storage.save(profile)
    }
}
