package top.medicine.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDOCConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("智慧医药接口文档")
                        .description("程序员的大本营")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("kagg886")
                                .email("iveour@163.com").url("https://kagg886.top")));
    }
}
