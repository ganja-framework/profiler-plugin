package ganja.plugin.profiler.collector

interface DataCollectorInterface {

    def collect(def request, def response, Exception exception)

    String getName()
}