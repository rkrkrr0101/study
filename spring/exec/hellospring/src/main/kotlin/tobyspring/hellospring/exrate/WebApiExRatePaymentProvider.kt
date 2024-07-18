package tobyspring.hellospring.exrate

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import tobyspring.hellospring.payment.ExRateProvider
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URI
import java.util.stream.Collectors

class WebApiExRatePaymentProvider : ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/$currency"
        return runApiForExRate(url)
    }

    private fun runApiForExRate(url: String): BigDecimal {
        val uri = URI(url)
        val connection = uri.toURL().openConnection() as HttpURLConnection
        val response = executeApi(connection)
        val exRate = extractExRate(response)

        println(exRate)

        return exRate
    }

    private fun extractExRate(response: String): BigDecimal {
        val mapper = jacksonObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        val exRate = data.rates["KRW"] ?: throw IllegalArgumentException("KRW is null")
        return exRate
    }

    private fun executeApi(connection: HttpURLConnection): String {
        val response =
            BufferedReader(InputStreamReader(connection.inputStream)).use {
                it.lines().collect(Collectors.joining())
            }
        return response
    }
}
