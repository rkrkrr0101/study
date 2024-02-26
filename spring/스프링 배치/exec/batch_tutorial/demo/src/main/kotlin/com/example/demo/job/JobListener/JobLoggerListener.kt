package com.example.demo.job.JobListener

import org.slf4j.LoggerFactory
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener


class JobLoggerListener:JobExecutionListener {
    private val log=LoggerFactory.getLogger(this.javaClass)
    private val BEFORE_MESSAGE="{} Job is Running"
    private val AFTER_MESSAGE="{} Job is Done. (Status: {})"

    override fun beforeJob(jobExecution: JobExecution) {
        log.info(BEFORE_MESSAGE,jobExecution.jobInstance.jobName)

    }

    override fun afterJob(jobExecution: JobExecution) {
        log.info(AFTER_MESSAGE,jobExecution.jobInstance.jobName,jobExecution.status)
        if (jobExecution.status==BatchStatus.FAILED){
            //슬랙같은거로 던지게 할수있음
            log.info("Job is fail")
        }

    }
}