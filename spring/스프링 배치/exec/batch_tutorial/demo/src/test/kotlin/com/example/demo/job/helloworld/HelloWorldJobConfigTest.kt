package com.example.demo.job.helloworld

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBatchTest
@SpringBootTest(classes = [SpringBatchTestConfig::class, HelloWorldJobConfig::class])
@ActiveProfiles("test")
class HelloWorldJobConfigTest
    @Autowired
    constructor(val jobLauncherTestUtils: JobLauncherTestUtils) {
        @Test
        fun success() {
            val jobExecution = jobLauncherTestUtils.launchJob()
            Assertions.assertThat(jobExecution.status).isEqualTo(BatchStatus.COMPLETED)
        }
    }
