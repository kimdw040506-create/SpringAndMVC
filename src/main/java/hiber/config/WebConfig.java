package hiber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("hiber.controller")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public SpringResourceTemplateResolver templateResolver() {

        SpringResourceTemplateResolver resolver =
                new SpringResourceTemplateResolver();

        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");

        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {

        SpringTemplateEngine engine =
                new SpringTemplateEngine();

        engine.setTemplateResolver(templateResolver());

        return engine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {

        ThymeleafViewResolver resolver =
                new ThymeleafViewResolver();

        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");

        return resolver;
    }
}