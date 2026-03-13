package br.com.aal.grpc_clean_architecture;

import br.com.aal.grpc_clean_architecture.application.usecase.GetUserUseCase;
import br.com.aal.grpc_clean_architecture.domain.repository.UserRepository;
import br.com.aal.grpc_clean_architecture.infrastructure.repository.InMemoryUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GrpcCleanArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcCleanArchitectureApplication.class, args);
	}

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public GetUserUseCase getUserUseCase(UserRepository repo) {
        return new GetUserUseCase(repo);
    }

}
