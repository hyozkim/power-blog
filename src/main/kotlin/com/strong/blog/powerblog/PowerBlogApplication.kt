package com.strong.blog.powerblog

import com.strong.blog.powerblog.common.config.BlogApiConfig
import com.strong.blog.powerblog.common.config.SwaggerConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Import

@EnableFeignClients
@Import(BlogApiConfig::class, SwaggerConfig::class)
@SpringBootApplication(exclude = [HibernateJpaAutoConfiguration::class])
class PowerBlogApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<PowerBlogApplication>(*args)
        }
    }
}
