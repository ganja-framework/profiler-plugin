package ganja.plugin.profiler.controller

import ganja.common.http.exception.NotFoundException
import ganja.component.http.Request
import ganja.component.http.Response
import ganja.plugin.profiler.Profile
import ganja.plugin.profiler.storage.ProfilerStorageInterface

class ProfilerController {

    def logger
    ProfilerStorageInterface storage

    def list(Request request) {

        logger?.info('profiler:list action')


        def response = new Response()
        String params = ''

        logger?.info("Storage size: ${storage.size()}")

        for(profile in storage.getAll()) {
            logger?.info(profile.getToken())
            params += "<li>${profile.getToken()}</li>"
        }

        response.setContent("<h1>Profiles</h1><ul>${params}</ul>")

        response
    }

    Response show(Request request) {

        String token = request.getParameter('token')

        def profile = storage.get(token)

        if(profile) {
            def response = new Response()
            response.setContent("<h1>Profile ${profile.getToken()}</h1>")

            return response
        }

        throw new NotFoundException("Profile with token: ${token} not found")
    }
}
