package server

import data.PortableRouter
import data.PortableTraffic
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.http.HttpMethod
import java.text.SimpleDateFormat
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

val portableTraffics = arrayListOf<PortableTraffic>()

fun deployVerticle() {
    Vertx.vertx(
        VertxOptions()
    ).deployVerticle(PortableServer())
}

fun addRouter(router: PortableRouter) {

}

fun removeRouter(router: PortableRouter) {

}

fun addTraffic(traffic: PortableTraffic) {
    portableTraffics.add(traffic)
    println("[${SimpleDateFormat("HH:mm:ss").format(traffic.time)}] ${traffic.address}가 ${traffic.route}에 요청했습니다.")
}

fun addTraffic(address: String, route: String, time: Date = Date()) {
    addTraffic(
        PortableTraffic(address, route, time)
    )
}

fun removeTraffic(traffic: PortableTraffic) {

}