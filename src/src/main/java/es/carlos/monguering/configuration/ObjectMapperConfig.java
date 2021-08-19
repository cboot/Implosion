package es.carlos.monguering.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.carlos.monguering.rest.util.CustomObjectMapper;

@Configuration
public class ObjectMapperConfig {

	@Bean
	@Primary
	public ObjectMapper getObjectMapper() {
		return new CustomObjectMapper();
	}
}
