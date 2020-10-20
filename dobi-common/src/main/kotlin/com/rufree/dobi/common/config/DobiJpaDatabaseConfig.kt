package com.rufree.dobi.common.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.*
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jmx.export.MBeanExporter
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import javax.sql.DataSource

@Configuration
@ComponentScan(basePackages = [
    "com.rufree.dobi.common.repository"
])
@EnableJpaRepositories(
        basePackages = ["com.rufree.dobi.common.repository"],
        entityManagerFactoryRef = "dobiEntityManagerFactory",
        transactionManagerRef = "dobiTransactionManager"
)
@PropertySource("classpath:properties/database/dobi-database-\${spring.profiles.active}.properties")
class DobiJpaDatabaseConfig(private val mbeanExporter: MBeanExporter) {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "dobi.jpa")
    fun dobiJpaProperties(): JpaProperties {
        return JpaProperties()
    }

    @Bean
    @Primary
    fun dobiHibernateSettings(): HibernateSettings {
        return HibernateSettings()
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "dobi")
    fun dobiHikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    @Primary
    fun dobiDataSource(): DataSource {
        val dataSource = HikariDataSource(dobiHikariConfig())
        mbeanExporter.addExcludedBean("dobiDataSource")
        return dataSource
    }

    @Bean
    @Primary
    fun dobiEntityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
                .dataSource(dobiDataSource())
                .packages("com.rufree.dobi.common.entity")
                .persistenceUnit("dobiPersistenceUnit")
                .properties(getVendorProperties(dobiDataSource()))
                .build()
    }

    private fun getVendorProperties(dataSource: DataSource): Map<String, String> {
        var properties = dobiJpaProperties().properties
//        properties.put("hibernate.dialec", "org.hibernate.dialect.MySQL5InnoDBDialect")
        return properties
    }

    @Bean(name = ["dobiJdbcTemplate"])
    fun dobiJdbcTemplate(@Qualifier("dobiDataSource") dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }

    @Bean
    @Primary
    fun dobiTransactionManager(builder: EntityManagerFactoryBuilder): PlatformTransactionManager {
        return JpaTransactionManager(dobiEntityManagerFactory(builder).getObject()!!)
    }

    @Bean
    @Primary
    fun dobiTransactionTemplate(builder: EntityManagerFactoryBuilder): TransactionTemplate {
        return TransactionTemplate(dobiTransactionManager(builder))
    }
}