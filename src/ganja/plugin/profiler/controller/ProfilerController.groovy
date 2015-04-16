package ganja.plugin.profiler.controller

import ganja.common.http.ResponseInterface
import ganja.common.http.exception.NotFoundException
import ganja.common.view.TemplateEngineInterface
import ganja.component.http.Request
import ganja.plugin.profiler.storage.ProfilerStorageInterface

class ProfilerController {

    ProfilerStorageInterface storage

    TemplateEngineInterface engine

    ResponseInterface list(Request request) {

        engine.render('profiler/list', storage.getAll())
    }

    ResponseInterface show(Request request) {

        String token = request.getParameter('token')

        def profile = storage.get(token)

        if(profile) {
            return engine.render('profiler/list', profile)
        }

        throw new NotFoundException("Profile with token: ${token} not found")
    }
}
