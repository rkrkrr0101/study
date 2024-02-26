package com.example.demo.job.ValidatedParam

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
class ValidatedParamJobConfig {

    @Bean
    fun validatedParamJob(jobRepository: JobRepository,validatedParamStep: Step):Job{
        return JobBuilder("validatedParamJob",jobRepository)
            .incrementer(RunIdIncrementer())
            .validator(FileParamValidator())
            .start(validatedParamStep)
            .build()
    }
    private fun multiValidator():CompositeJobParametersValidator{
        val validator = CompositeJobParametersValidator()
        validator.setValidators(listOf(FileParamValidator()))
        return validator
    }
    @Bean
    @JobScope
    fun validatedParamStep(jobRepository: JobRepository,
                       transactionManager: PlatformTransactionManager,
                           validatedParamTaskLet: ValidatedParamTaskLet): Step {
        return StepBuilder("validatedParamStep",jobRepository)
            .tasklet(validatedParamTaskLet,transactionManager)
            .build()
    }
    @Bean
    @StepScope
    fun validatedParamTasklet(@Value("#{jobParameters['fileName']}") fileName:String):ValidatedParamTaskLet{
        return ValidatedParamTaskLet(fileName)
    }


}
open class ValidatedParamTaskLet(private val fileName:String):Tasklet{
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        println(fileName)
        println("valid batch")
        return RepeatStatus.FINISHED
    }
}