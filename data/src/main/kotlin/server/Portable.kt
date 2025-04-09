package server

import data.PortableRouter
import data.PortableTraffic
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import java.util.*

val HTTP_PORT = 80
val HTTPS_SSL_PORT = 443

val portableRouters = arrayListOf<PortableRouter>( // SAMPLE
    PortableRouter("root", "/", HttpMethod.GET),
    PortableRouter("addUser", "/user", HttpMethod.POST),
    PortableRouter("removeUser", "/user", HttpMethod.DELETE),
    PortableRouter("modifyUser", "/user", HttpMethod.UPDATE),
    PortableRouter("getUser", "/user", HttpMethod.GET),
)

val portableTraffics = arrayListOf<PortableTraffic>( // SAMPLE
    PortableTraffic("22.20.14.70", "/sigLeader", Date()),
    PortableTraffic("91.63.40.27", "/oldHQ", Date()),
    PortableTraffic("22.20.14.243", "/printer", Date()),
    PortableTraffic("91.63.6.38", "/bunker", Date()),
    PortableTraffic("91.63.5.29", "/HQ", Date()),
)

fun deployVerticle() {
    Vertx.vertx().deployVerticle(PortableServer())
}