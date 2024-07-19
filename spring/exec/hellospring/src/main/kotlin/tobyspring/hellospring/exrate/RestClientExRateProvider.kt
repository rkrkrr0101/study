package tobyspring.hellospring.exrate

import org.springframework.web.client.RestClient
import tobyspring.hellospring.payment.ExRateProvider
import java.math.BigDecimal

class RestClientExRateProvider(private val restClient: RestClient) : ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/$currency"
        return restClient.get().uri(url).retrieve().body(ExRateData::class.java)?.rates?.get("KRW")
            ?: throw IllegalArgumentException("KRW is null")
    }
}
