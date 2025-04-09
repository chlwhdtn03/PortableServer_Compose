package data

import io.vertx.core.http.HttpMethod

/**
 * @param name 라우터 별명
 * @param route 라우터 위치(ex. '/getuser')
 */
data class PortableRouter(
    val name: String,
    val route: String,
    val method: String
) {
    constructor(
        name: String,
        route: String,
        method: HttpMethod
    ) : this(name, route, method.name())
}