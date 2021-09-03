package com.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebsocketDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketDemoApplication.class, args);
	}
	
	@Bean(name = "markerConfig")
	public freemarker.template.Configuration getMarkerConfiguration() {
		freemarker.template.Configuration config = new freemarker.template.Configuration(
				freemarker.template.Configuration.getVersion());
		config.setClassForTemplateLoading(this.getClass(), "/templates/");
		return config;
	}
}
