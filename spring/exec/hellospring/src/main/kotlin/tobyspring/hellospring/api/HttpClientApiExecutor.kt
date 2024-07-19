package tobyspring.hellospring.api

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class HttpClientApiExecutor : ApiExecutor {
    override fun execute(uri: URI): String {
        val request =
            HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build()
        return HttpClient.newBuilder()
            .build()
            .send(request, BodyHandlers.ofString()).body()
    }
}
