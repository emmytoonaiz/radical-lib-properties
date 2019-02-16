package radical.lib.properties.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	@Bean
    public Docket easyCoopAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("EasyCoop API - Radical-Monitor-Alert")
                .apiInfo(apiInfo())                
                .select()                
                .paths(regex("/api/radicalmonitor/v1.*"))
                .build();
    }
	
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Easy Coop API")
                .description("Easy Coop API - Radical-Monitor-Alert")
                .termsOfServiceUrl("http://www.africaprudential.com/terms-conditions")
                .version("1.0")
                .build();
    }
}
