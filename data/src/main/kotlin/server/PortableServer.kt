package server

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router

class PortableServer: AbstractVerticle() {
    val port: Int = 80
    override fun start() {
        val router = Router.router(vertx);

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(port)
            .onSuccess { server ->
                println("Server listening on port ${server.actualPort()}")
            }
    }
}