package com.strong.blog.powerblog.common.config

import com.strong.blog.powerblog.BlogApiBase
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackageClasses = [BlogApiBase::class])
class BlogApiConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/v1/**")
            .allowedOrigins("*")
            .allowedMethods("*")
    }
}
