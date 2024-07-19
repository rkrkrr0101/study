package tobyspring.hellospring.exrate

import tobyspring.hellospring.api.ApiTemplate
import tobyspring.hellospring.payment.ExRateProvider
import java.math.BigDecimal

class WebApiExRatePaymentProvider(private val apiTemplate: ApiTemplate) : ExRateProvider {
    override fun getExRate(currency: String): BigDecimal {
        val url = "https://open.er-api.com/v6/latest/$currency"
        return apiTemplate.getExRate(url)
    }
}
