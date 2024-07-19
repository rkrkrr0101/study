package tobyspring.hellospring.api

import java.net.URI

fun interface ApiExecutor {
    fun execute(uri: URI): String
}
