package br.com.aal.grpc_clean_architecture.application.usecase;

import br.com.aal.grpc_clean_architecture.domain.model.User;
import br.com.aal.grpc_clean_architecture.domain.repository.UserRepository;

public class GetUserUseCase {

    private final UserRepository repository;

    public GetUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(Long id) {
        return repository.findById(id);
    }
}
