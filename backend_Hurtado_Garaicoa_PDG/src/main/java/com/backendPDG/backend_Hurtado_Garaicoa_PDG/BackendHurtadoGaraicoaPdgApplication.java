package com.backendPDG.backend_Hurtado_Garaicoa_PDG;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendHurtadoGaraicoaPdgApplication {
	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(BackendHurtadoGaraicoaPdgApplication.class, args);
	}

}
