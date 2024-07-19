package tobyspring.hellospring.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import tobyspring.hellospring.exrate.ExRateData
import java.math.BigDecimal

class ErApiExRateExtractor : ExRateExtractor {
    override fun extract(response: String): BigDecimal {
        val mapper = jacksonObjectMapper()
        val data = mapper.readValue(response, ExRateData::class.java)
        return data.rates["KRW"] ?: throw IllegalArgumentException("KRW is null")
    }
}
