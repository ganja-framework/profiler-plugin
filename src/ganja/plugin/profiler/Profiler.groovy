package ganja.plugin.profiler

import ganja.plugin.profiler.collector.DataCollectorInterface
import ganja.plugin.profiler.storage.ProfilerStorageInterface
import org.apache.commons.lang3.RandomStringUtils

class Profiler {

    def logger
    ProfilerStorageInterface storage
    List<DataCollectorInterface> collectors = []

    void collect(def request, def response = null, Exception exception = null) {

        def profile = new Profile()

        profile.setToken(RandomStringUtils.random(6, true, true))

        logger?.debug("Creating profile with token ${profile.getToken()}")

        collectors.each({ DataCollectorInterface collector ->
            profile.put(collector.name, collector.collect(request, response, exception))
        })

        storage.save(profile)
    }
}
