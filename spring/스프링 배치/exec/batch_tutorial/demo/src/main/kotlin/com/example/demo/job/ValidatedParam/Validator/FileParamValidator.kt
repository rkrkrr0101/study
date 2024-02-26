package com.example.demo.job.ValidatedParam.Validator

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersInvalidException
import org.springframework.batch.core.JobParametersValidator
import org.springframework.util.StringUtils

class FileParamValidator:JobParametersValidator {
    override fun validate(parameters: JobParameters?) {
        parameters!!
        val fileName = parameters.getString("fileName")
        if (!StringUtils.endsWithIgnoreCase(fileName,"csv")){
            throw JobParametersInvalidException("not csv")
        }
    }
}