package com.example.demo.job.HelloWorld

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class HelloWorldJobConfig {

    @Bean
    fun helloWorldJob(jobRepository: JobRepository,helloWorldStep: Step):Job{
        return JobBuilder("helloWorldJob",jobRepository)
            .incrementer(RunIdIncrementer())
            .start(helloWorldStep)
            .build()
    }
    @Bean
    @JobScope
    fun helloWorldStep(jobRepository: JobRepository,
                       transactionManager: PlatformTransactionManager,
                       helloWorldTaskLet: HelloWorldTaskLet): Step {
        return StepBuilder("helloWorldStep",jobRepository)
            .tasklet(helloWorldTaskLet,transactionManager)
            .build()
    }
    @Bean
    @StepScope
    fun helloWorldTasklet():HelloWorldTaskLet{
        return HelloWorldTaskLet()
    }


}
open class HelloWorldTaskLet:Tasklet{
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        println("hello World batch")
        return RepeatStatus.FINISHED
    }
}