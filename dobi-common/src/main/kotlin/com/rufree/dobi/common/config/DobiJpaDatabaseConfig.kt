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
    "com.rich.joint.common.repository"
])
@EnableJpaRepositories(
        basePackages = ["com.rich.joint.common.repository"],
        entityManagerFactoryRef = "jointEntityManagerFactory",
        transactionManagerRef = "jointTransactionManager"
)
@PropertySource("classpath:properties/database/joint-database-\${spring.profiles.active}.properties")
class JointJpaDatabaseConfig(private val mbeanExporter: MBeanExporter) {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "joint.jpa")
    fun jointJpaProperties(): JpaProperties {
        return JpaProperties()
    }

    @Bean
    @Primary
    fun jointHibernateSettings(): HibernateSettings {
        return HibernateSettings()
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "joint")
    fun jointHikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    @Primary
    fun jointDataSource(): DataSource {
        val dataSource = HikariDataSource(jointHikariConfig())
        mbeanExporter.addExcludedBean("jointDataSource")
        return dataSource
    }

    @Bean
    @Primary
    fun jointEntityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        return builder
                .dataSource(jointDataSource())
                .packages("com.rich.joint.common.entity")
                .persistenceUnit("jointPersistenceUnit")
                .properties(getVendorProperties(jointDataSource()))
                .build()
    }

    private fun getVendorProperties(dataSource: DataSource): Map<String, String> {
        var properties = jointJpaProperties().properties
//        properties.put("hibernate.dialec", "org.hibernate.dialect.MySQL5InnoDBDialect")
        return properties
    }

    @Bean(name = ["jointJdbcTemplate"])
    fun jointJdbcTemplate(@Qualifier("jointDataSource") dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }

    @Bean
    @Primary
    fun jointTransactionManager(builder: EntityManagerFactoryBuilder): PlatformTransactionManager {
        return JpaTransactionManager(jointEntityManagerFactory(builder).getObject()!!)
    }

    @Bean
    @Primary
    fun jointTransactionTemplate(builder: EntityManagerFactoryBuilder): TransactionTemplate {
        return TransactionTemplate(jointTransactionManager(builder))
    }
}