package tobyspring.hellospring.api

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.util.stream.Collectors

class SimpleApiExecutor : ApiExecutor {
    override fun execute(uri: URI): String {
        val connection = uri.toURL().openConnection() as HttpURLConnection
        val response =
            BufferedReader(InputStreamReader(connection.inputStream)).use {
                it.lines().collect(Collectors.joining())
            }
        return response
    }
}
