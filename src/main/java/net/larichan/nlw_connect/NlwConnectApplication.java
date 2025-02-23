package net.larichan.nlw_connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Event API", version = "1", description = "API do projeto de Inscrições em Eventos"))
public class NlwConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NlwConnectApplication.class, args);
	}

}
