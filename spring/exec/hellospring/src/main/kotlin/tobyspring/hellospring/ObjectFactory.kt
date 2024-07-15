package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tobyspring.hellospring.exrate.CachedExRateProvider
import tobyspring.hellospring.exrate.WebApiExRatePaymentProvider
import tobyspring.hellospring.payment.ExRateProvider
import tobyspring.hellospring.payment.PaymentService

@Configuration
class ObjectFactory {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(CachedExRateProvider())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return WebApiExRatePaymentProvider()
    }

    @Bean
    fun cachedExRateProvider(): ExRateProvider {
        return CachedExRateProvider()
    }
}
