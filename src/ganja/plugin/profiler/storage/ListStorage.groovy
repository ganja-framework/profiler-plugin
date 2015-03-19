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
}
