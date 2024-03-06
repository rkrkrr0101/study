package com.example.demo.job.DbDateReadWrite

import com.example.demo.core.domain.accounts.AccountRepository
import com.example.demo.core.domain.accounts.Accounts
import com.example.demo.core.domain.orders.Orders
import com.example.demo.core.domain.orders.OrderRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.data.RepositoryItemReader
import org.springframework.batch.item.data.RepositoryItemWriter
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import org.springframework.transaction.PlatformTransactionManager
import java.util.Collections

@Configuration
class TrMigrationConfig @Autowired constructor(
    val orderRepository: OrderRepository,
    val accountRepository: AccountRepository) {

    @Bean
    fun trMigrationJob(jobRepository: JobRepository,trMigrationStep:Step):Job{
        return JobBuilder("trMigrationJob",jobRepository)
            .incrementer(RunIdIncrementer())
            .start(trMigrationStep)
            .build()

    }
    @JobScope
    @Bean
    fun trMigrationStep(jobRepository: JobRepository,
                        transactionManager: PlatformTransactionManager,
                        trOrderReader:ItemReader<Orders>,
                        trOrderProcessor:ItemProcessor<Orders,Accounts>,
                        trAccountWriter: ItemWriter<Accounts>
    ):Step{
        return StepBuilder("trMigrationStep",jobRepository)
            .chunk<Orders, Accounts>(5,transactionManager)
            .reader(trOrderReader)
            .processor(trOrderProcessor)
            .writer(trAccountWriter)
            .build()
    }
    @StepScope
    @Bean
    fun trOrderReader():ItemReader<Orders>{
        return RepositoryItemReaderBuilder<Orders>()
            .name("trOrder")//리더이름
            .repository(orderRepository)//사용할 레포지토리
            .methodName("findAll")//내가 사용할 메서드명
            .arguments()//메서드에서 사용할 매개변수,여기선 없으니까 안넘김,있으면 리스트에 담아서 넘기면됨(listOf<String>())
            .pageSize(5)//청크사이즈와 일치시키면됨
            .sorts(Collections.singletonMap("id",Sort.Direction.ASC))//정렬추가
            .build()
    }
    @StepScope
    @Bean
    fun trAccountWriter():ItemWriter<Accounts>{
        return RepositoryItemWriterBuilder<Accounts>()
            .repository(accountRepository)
            .methodName("save")
            .build()
    }
    @StepScope
    @Bean
    fun trOrderProcessor():ItemProcessor<Orders,Accounts>{
        return ItemProcessor { Accounts.orderToAccount(it) }
    }
}