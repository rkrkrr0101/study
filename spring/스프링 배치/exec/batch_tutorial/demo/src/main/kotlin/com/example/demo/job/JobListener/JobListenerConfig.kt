package com.example.demo.job.JobListener

import com.example.demo.job.ValidatedParam.Validator.FileParamValidator
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.CompositeJobParametersValidator
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class JobListenerConfig {

    @Bean
    fun jobListenerJob(jobRepository: JobRepository,jobListenerStep: Step):Job{
        return JobBuilder("jobListenerJob",jobRepository)
            .incrementer(RunIdIncrementer())
            .listener(JobLoggerListener())
            .start(jobListenerStep)
            .build()
    }

    @Bean
    @JobScope
    fun jobListenerStep(jobRepository: JobRepository,
                       transactionManager: PlatformTransactionManager,
                        jobListenerTaskLet: JobListenerTaskLet): Step {
        return StepBuilder("jobListenerStep",jobRepository)
            .tasklet(jobListenerTaskLet,transactionManager)
            .build()
    }
    @Bean
    @StepScope
    fun jobListenerTasklet():JobListenerTaskLet{
        return JobListenerTaskLet()
    }


}
open class JobListenerTaskLet():Tasklet{
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        println("jobListener batch")

        return RepeatStatus.FINISHED
    }
}