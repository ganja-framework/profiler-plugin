package ganja.plugin.profiler.collector

class DummyCollector implements DataCollectorInterface {

    @Override
    def collect(def request, def response, Exception exception) {
        new Object()
    }

    @Override
    String getName() {
        'dummy'
    }
}