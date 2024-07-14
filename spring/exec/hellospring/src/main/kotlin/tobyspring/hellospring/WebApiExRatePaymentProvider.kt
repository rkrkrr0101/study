package tobyspring.hellospring

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors

@Component
class WebApiExRatePaymentProvider : ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url =
            URL("https://open.er-api.com/v6/latest/$currency")
        val connection = url.openConnection() as HttpURLConnection
        val br = BufferedReader(InputStreamReader(connection.inputStream))
        val response = br.lines().collect(Collectors.joining())
        br.close()

        val mapper = jacksonObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        val exRate = data.rates["KRW"] ?: throw IllegalArgumentException("KRW is null")
        return exRate
    }
}
