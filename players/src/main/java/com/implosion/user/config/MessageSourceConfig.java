package com.implosion.user.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MessageSourceConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(300); // reload messages every 300 seconds
		return messageSource;
	}

	/**
	   * To use custom name messages in a properties file like we need to define a
	   * LocalValidatorFactoryBean and register the messageSource.<br>
	   * <br>
	   * Example:<br>
	   * 
	   * <pre>
	   * &#64;NotEmpty(message = "{email.notempty}")<br>
	   * &#64;Email<br>
	   * private String email;<br>
	   * </pre>
	   * 
	   * @return The LocalValidatorFactoryBean instance
	   */
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
