package com.strong.blog.powerblog.common.config

import com.strong.blog.powerblog.BlogApiBase
import com.strong.blog.powerblog.common.config.BlogServiceJpaConfig.Companion.BLOG_SERVICE_ENTITY_MANAGER_FACTORY
import com.strong.blog.powerblog.common.config.BlogServiceJpaConfig.Companion.BLOG_SERVICE_TRANSACTION_MANAGER
import com.zaxxer.hikari.HikariDataSource
import org.hibernate.cfg.AvailableSettings
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.hibernate5.SpringBeanContainer
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.util.ClassUtils
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackageClasses = [BlogApiBase::class],
    transactionManagerRef = BLOG_SERVICE_TRANSACTION_MANAGER,
    entityManagerFactoryRef = BLOG_SERVICE_ENTITY_MANAGER_FACTORY,
)
@EntityScan(basePackageClasses = [BlogApiBase::class])
@EnableJpaAuditing
class BlogServiceJpaConfig {

    @Bean(name = [BLOG_SERVICE_ENTITY_MANAGER_FACTORY])
    fun entityManagerFactory(
        @Qualifier(BLOG_SERVICE_DATA_SOURCE) dataSource: DataSource,
        @Qualifier(BLOG_SERVICE_JPA_PROPERTIES) jpaProperties: JpaProperties,
        @Qualifier(BLOG_SERVICE_HIBERNATE_PROPERTIES) hibernateProperties: HibernateProperties,
        beanFactory: ConfigurableListableBeanFactory,
    ): LocalContainerEntityManagerFactoryBean {
        val hibernateSettings = HibernateSettings()
        if (ClassUtils.isPresent("org.hibernate.resource.beans.container.spi.BeanContainer", javaClass.classLoader)) {
            val customizer = HibernatePropertiesCustomizer { hibernateProperties: MutableMap<String?, Any?> ->
                hibernateProperties[AvailableSettings.BEAN_CONTAINER] = SpringBeanContainer(beanFactory)
            }
            hibernateSettings.hibernatePropertiesCustomizers(listOf(customizer))
        }
        return EntityManagerFactoryBuilder(HibernateJpaVendorAdapter(), jpaProperties.properties, null)
            .dataSource(dataSource)
            .properties(hibernateProperties.determineHibernateProperties(jpaProperties.properties, hibernateSettings))
            .persistenceUnit(BLOG_SERVICE_PERSISTENCE_UNIT_NAME)
            .packages(BlogApiBase::class.java)
            .build()
    }

    @Bean(name = [BLOG_SERVICE_JPA_PROPERTIES])
    @ConfigurationProperties(prefix = "blog.service.jpa")
    fun jpaProperties(): JpaProperties = JpaProperties()

    @Bean(name = [BLOG_SERVICE_HIBERNATE_PROPERTIES])
    @ConfigurationProperties(prefix = "blog.service.jpa.hibernate")
    fun hibernateProperties(): HibernateProperties = HibernateProperties()

    @Primary
    @Bean(name = [BLOG_SERVICE_TRANSACTION_MANAGER])
    fun transactionManager(
        @Qualifier(BLOG_SERVICE_ENTITY_MANAGER_FACTORY) entityManagerFactory: EntityManagerFactory,
    ) = JpaTransactionManager(entityManagerFactory)

    @Primary
    @Bean(name = [BLOG_SERVICE_DATA_SOURCE])
    @ConfigurationProperties("blog.service.datasource")
    fun blogServiceDataSource(): DataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

    companion object {
        const val BLOG_SERVICE_DATA_SOURCE = "blogServiceDataSource"
        const val BLOG_SERVICE_TRANSACTION_MANAGER = "blogServiceTransactionManager"
        const val BLOG_SERVICE_ENTITY_MANAGER_FACTORY = "blogServiceEntityManagerFactory"
        const val BLOG_SERVICE_JPA_PROPERTIES = "blogServiceJpaProperties"
        const val BLOG_SERVICE_HIBERNATE_PROPERTIES = "blogServiceHibernateProperties"
        const val BLOG_SERVICE_PERSISTENCE_UNIT_NAME = "blogServicePersistenceUnitName"
    }
}
