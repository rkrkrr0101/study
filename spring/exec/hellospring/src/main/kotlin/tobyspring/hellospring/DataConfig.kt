package tobyspring.hellospring

import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
class DataConfig {
    @Bean
    fun dataSource(): DataSource {
        return EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build()
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val emf = LocalContainerEntityManagerFactoryBean()

        emf.dataSource = dataSource()
        emf.setPackagesToScan("tobyspring.hellospring")
        emf.jpaVendorAdapter =
            object : HibernateJpaVendorAdapter() {
                init {
                    database = Database.H2
                    isGenerateDdl = true
                    isShowSql = true
                }
            }

        return emf
    }

    @Bean
    fun persistenceAnnotationBeanPostProcessor(): BeanPostProcessor {
        return PersistenceAnnotationBeanPostProcessor()
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory): JpaTransactionManager {
        return JpaTransactionManager(emf)
    }
}
