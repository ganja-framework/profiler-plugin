package ganja.plugin.profiler.storage

import ganja.plugin.profiler.Profile

class ListStorage implements ProfilerStorageInterface {

    def logger
    List<Profile> data = []

    @Override
    void save(Profile profile) {

        if(data.size() > 9) {
            data.remove(0)
        }

        logger?.info("Adding new profile ${profile.getToken()}")
        data.add(profile)
        logger?.info("Current storage size: ${data.size()}")
    }

    @Override
    Profile get(String token) {

        for(profile in data) {
            if(profile.getToken() == token) {
                return profile
            }
        }
    }

    @Override
    List<Profile> getAll() {

        data
    }

    @Override
    Integer size() {

        data.size()
    }
}
