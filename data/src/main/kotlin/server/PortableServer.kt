package server

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.net.PfxOptions
import io.vertx.ext.web.Router

class PortableServer: AbstractVerticle() {
    override fun start() {
        val router = Router.router(vertx);

        vertx.createHttpServer(
            HttpServerOptions() // SSL
                .setUseAlpn(true)
                .setSsl(true)
                .setKeyCertOptions(
                    PfxOptions().setPath("PortableServer.p12").setPassword("123011")
                )
        )
            .requestHandler(router)
            .listen(HTTPS_SSL_PORT)
            .onSuccess { server ->
                println("Server listening on port ${server.actualPort()}")
            }
            .onFailure { server ->
                println("Failed to open server : ${server.message}")
            }


        for(route in portableRouters) { // portableRouters 등록
            router.route(route.route)
                .method(HttpMethod.valueOf(route.method))
                .setName(route.name)
                .handler { request ->
                    request.response().setStatusCode(200)
                    request.response().end("Hello World!")
                    addTraffic(request.request().remoteAddress().hostAddress(), request.request().uri())
                }
        }
    }
}