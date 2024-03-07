package com.example.demo.job.FileDataReadWrite

import com.example.demo.core.domain.accounts.AccountRepository
import com.example.demo.core.domain.accounts.Accounts
import com.example.demo.core.domain.orders.Orders
import com.example.demo.core.domain.orders.OrderRepository
import com.example.demo.job.DbDateReadWrite.PlayerFieldSetMapper
import com.example.demo.job.FileDataReadWrite.dto.Player
import com.example.demo.job.FileDataReadWrite.dto.PlayerYear
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
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.FlatFileItemWriter
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor
import org.springframework.batch.item.file.transform.DelimitedLineAggregator
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import org.springframework.data.domain.Sort
import org.springframework.transaction.PlatformTransactionManager
import java.util.Collections

@Configuration
class FileDataReadWriteConfig  {

    @Bean
    fun fileDataReadWriteJob(jobRepository: JobRepository,fileDataReadWriteStep:Step):Job{
        return JobBuilder("fileDataReadWriteJob",jobRepository)
            .incrementer(RunIdIncrementer())
            .start(fileDataReadWriteStep)
            .build()

    }
    @JobScope
    @Bean
    fun fileDataReadWriteStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager,
        fileDataReadWriteReader: ItemReader<Player>,
        fileDataReadWriteProcessor:ItemProcessor<Player,PlayerYear>,
        fileDataReadWriteWriter: ItemWriter<PlayerYear>

    ):Step{
        return StepBuilder("trMigrationStep",jobRepository)
            .chunk<Player, PlayerYear>(5,transactionManager)
            .reader(fileDataReadWriteReader)
            .processor (fileDataReadWriteProcessor)
            .writer (fileDataReadWriteWriter)

            .build()
    }
    @StepScope
    @Bean
    fun fileDataReadWriteReader():FlatFileItemReader<Player>{
        return FlatFileItemReaderBuilder<Player>()
            .name("fileDataReadWriteReader")
            .resource(FileSystemResource("player.csv"))//파일을 담으면됨
            .lineTokenizer(DelimitedLineTokenizer())//데이터를 나누는기준,delimitedLineTokenizer은 ,기준으로 분할
            .fieldSetMapper (PlayerFieldSetMapper())//파일과 엔티티를 매핑시키는 매퍼
            .linesToSkip(1)//파일의 첫줄스킵,엑셀파일의 맨위의 줄 스킵
            .build()
    }

    @StepScope
    @Bean
    fun fileDataReadWriteWriter(): FlatFileItemWriter<PlayerYear> {
        val filedExtractor=BeanWrapperFieldExtractor<PlayerYear>()//적을 필드명을 나열
        filedExtractor.setNames(arrayOf("ID","lastName","firstName","position","birthYear","debutYear","yearsExperience"))

        val lineAggregator=DelimitedLineAggregator<PlayerYear>()//어떤기준으로 나눠적을지
        lineAggregator.setDelimiter(",")
        lineAggregator.setFieldExtractor(filedExtractor)

        return FlatFileItemWriterBuilder<PlayerYear>()
            .name("fileDataReadWriteWriter")
            .resource(FileSystemResource("playerYear.csv"))
            .lineAggregator(lineAggregator)
            .build()
    }
    @StepScope
    @Bean
    fun fileDataReadWriteProcessor():ItemProcessor<Player,PlayerYear>{
        return ItemProcessor { PlayerYear.playerToPlayerYear(it) }
    }
}