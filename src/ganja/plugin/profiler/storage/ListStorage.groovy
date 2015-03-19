package ganja.plugin.profiler.storage

import ganja.plugin.profiler.Profile

class ListStorage implements ProfilerStorageInterface {

    @Delegate List<Profile> data = []

    @Override
    void save(Profile profile) {

        if(data.size() > 9) {
            data.pop()
        }

        data.add(profile)
    }

    @Override
    Profile get(String token) {

        data.get(token)
    }

    @Override
    List<Profile> getAll() {

        data
    }
}
