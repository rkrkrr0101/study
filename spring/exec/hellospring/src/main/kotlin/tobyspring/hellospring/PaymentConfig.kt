package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import tobyspring.hellospring.api.ApiExecutor
import tobyspring.hellospring.api.ApiTemplate
import tobyspring.hellospring.api.ErApiExRateExtractor
import tobyspring.hellospring.api.ExRateExtractor
import tobyspring.hellospring.api.SimpleApiExecutor
import tobyspring.hellospring.exrate.CachedExRateProvider
import tobyspring.hellospring.exrate.RestClientExRateProvider
import tobyspring.hellospring.payment.ExRateProvider
import tobyspring.hellospring.payment.PaymentService
import java.time.Clock

@Configuration
class PaymentConfig {
    @Bean
    fun apiTemplate(): ApiTemplate {
        return ApiTemplate(apiExecutor(), exRateExtractor())
    }

    @Bean
    fun exRateExtractor(): ExRateExtractor {
        return ErApiExRateExtractor()
    }

    @Bean
    fun apiExecutor(): ApiExecutor {
        return SimpleApiExecutor()
    }

    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(cachedExRateProvider(), clock())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return RestClientExRateProvider(restClient())
    }

    @Bean
    fun restClient(): RestClient {
        return RestClient.create()
    }

    @Bean
    fun cachedExRateProvider(): ExRateProvider {
        return CachedExRateProvider(exRateProvider())
    }

    @Bean
    fun clock(): Clock {
        return Clock.systemDefaultZone()
    }
}
