package tobyspring.hellospring

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime
import java.util.stream.Collectors

class PaymentService {
    fun prepare(
        orderId: Long,
        currency: String,
        foreignCurrencyAmount: BigDecimal,
    ): Payment {
        // 환율받기
        val url =
            URL("https://open.er-api.com/v6/latest/$currency")
        val connection = url.openConnection() as HttpURLConnection
        val br = BufferedReader(InputStreamReader(connection.inputStream))
        val response = br.lines().collect(Collectors.joining())
        br.close()

        val mapper = jacksonObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        val exRate = data.rates["KRW"] ?: throw IllegalArgumentException("KRW is null")
        // 금액계산
        val convertedAmount = foreignCurrencyAmount * exRate
        // 유효시간계산
        val validUntil = LocalDateTime.now().plusMinutes(30)

        return Payment(
            orderId,
            currency,
            foreignCurrencyAmount,
            exRate,
            convertedAmount,
            validUntil,
        )
    }
}

fun main(args: Array<String>) {
    val paymentService = PaymentService()
    val prepare = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7))
    println(prepare)
}
