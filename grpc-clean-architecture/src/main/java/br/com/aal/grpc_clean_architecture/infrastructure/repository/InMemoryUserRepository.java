package br.com.aal.grpc_clean_architecture.infrastructure.repository;

import br.com.aal.grpc_clean_architecture.domain.model.User;
import br.com.aal.grpc_clean_architecture.domain.repository.UserRepository;

import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private static final Map<Long, User> USERS = Map.of(
            1L, new User(1L, "Adriano"),
            2L, new User(2L, "Lopes")
    );

    @Override
    public User findById(Long id) {
        return USERS.get(id);
    }
}
