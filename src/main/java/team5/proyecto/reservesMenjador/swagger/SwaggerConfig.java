package team5.proyecto.reservesMenjador.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("team5.proyecto.reservesMenjador.controller"))
				.paths(PathSelectors.any()).build();
	}
	
	@SuppressWarnings("unchecked")
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"·T··Como·",
				"API Documentation",
				"v1.0",
				"https://github.com/Arini11/back-reserves-menjador",
				null,
				null,
				null,
				Collections.EMPTY_LIST
			);
	}


}