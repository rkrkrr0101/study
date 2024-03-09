package com.example.demo.job.DbDateReadWrite

import com.example.demo.core.domain.accounts.AccountRepository
import com.example.demo.core.domain.orders.OrderRepository
import com.example.demo.core.domain.orders.Orders
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.Job
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.util.Date

@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class TrMigrationConfigTest @Autowired constructor(
    val orderRepository: OrderRepository,
    val accountRepository: AccountRepository,
    val jobLauncherTestUtils: JobLauncherTestUtils,){


    @Test
    fun success_noData(@Autowired trMigrationJob:Job){
        jobLauncherTestUtils.job=trMigrationJob
        val jobExecution = jobLauncherTestUtils.launchJob()

        Assertions.assertThat(jobExecution.status).isEqualTo(BatchStatus.COMPLETED)
    }
    @Test
    fun success_yesData(@Autowired trMigrationJob:Job){


        orderRepository.save(Orders("kkgift1",13000,Date()))
        orderRepository.save(Orders("nngift2",13000,Date()))

        jobLauncherTestUtils.job=trMigrationJob
        val jobExecution = jobLauncherTestUtils.launchJob()

        Assertions.assertThat(jobExecution.status).isEqualTo(BatchStatus.COMPLETED)

    }


}