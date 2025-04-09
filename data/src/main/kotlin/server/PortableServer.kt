package server

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router

class PortableServer: AbstractVerticle() {
    override fun start() {
        val router = Router.router(vertx);

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(HTTP_PORT)
            .onSuccess { server ->
                println("Server listening on port ${server.actualPort()}")
            }
    }
}