package com.example.demo.core.domain.scheduler

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class SampleSchedule(
    val jobLauncher: JobLauncher,
    val helloWorldJob: Job)  {

    @Scheduled(cron = "0 */1 * * * *")
    fun helloWorldJobRun(){
        val jobParameters =JobParametersBuilder()
            .addLong("reqTime",System.currentTimeMillis())
            .toJobParameters()
        jobLauncher.run(helloWorldJob,jobParameters)
    }

}