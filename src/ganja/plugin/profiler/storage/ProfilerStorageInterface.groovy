package ganja.plugin.profiler.storage

import ganja.plugin.profiler.Profile

interface ProfilerStorageInterface {

    void save(Profile profile)

    Profile get(String token)

    List<Profile> getAll()

    Integer size()
}