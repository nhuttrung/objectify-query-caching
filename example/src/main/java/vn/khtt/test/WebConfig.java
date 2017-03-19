package vn.khtt.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import vn.khtt.spring.AppEngineResourceBundleMessageSource;
import vn.khtt.spring.ApplicationContextHolder;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
  public WebConfig() {
  }

  @Bean
  public ResourceBundleMessageSource messageSource(){
    ResourceBundleMessageSource messageSource = new AppEngineResourceBundleMessageSource();
    return messageSource;
  }

  @Bean
  public ApplicationContextHolder applicationContextHolder(){
    return new ApplicationContextHolder();
  }
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}
