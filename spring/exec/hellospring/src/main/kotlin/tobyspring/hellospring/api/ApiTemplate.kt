package tobyspring.hellospring.api

import java.math.BigDecimal
import java.net.URI

class ApiTemplate(
    private val apiExecutor: ApiExecutor,
    private val exRateExtractor: ExRateExtractor,
) {
    fun getExRate(url: String): BigDecimal {
        return getExRate(url, apiExecutor, exRateExtractor)
    }

    fun getExRate(
        url: String,
        apiExecutor: ApiExecutor,
        exRateExtractor: ExRateExtractor,
    ): BigDecimal {
        val uri = URI(url)
        val response = apiExecutor.execute(uri)
        val exRate = exRateExtractor.extract(response)

        println(exRate)

        return exRate
    }
}
